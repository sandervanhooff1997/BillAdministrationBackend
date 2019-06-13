package domain.services;

import domain.enums.VehicleType;
import domain.models.*;
import domain.models.enumerators.PaymentStatus;
import domain.models.Bill;
import domain.models.CarMovements;
import domain.repositories.BillRepository;
import domain.utils.DateUtils;
import domain.utils.NumberUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class BillService {

    @EJB
    private BillRepository repository;

    @EJB
    private VehicleService vehicleService;

    @EJB
    private OwnershipHistoryService ownershipHistoryService;

    @EJB
    private RoadService roadService;

    @EJB
    private PriceService priceService;

    /**
     * Sort all movements by account rider and car
     */
    public List<CarMovements> sortMovements(List<Movement> movements) {
        if (movements == null)
            return null;

        // list with distinct cars and their movements
        List<CarMovements> carMovements = new ArrayList<>();

        for (Movement m : movements) {
            // select the corrosponding vehicle
            Vehicle vehicle = vehicleService.getByCarTrackerId(m.getCarTrackerId());

            // skip movement if no vehicle was found
            if (vehicle == null) continue;

            // get the owner at the time of this movement
            OwnershipHistory ownershipHistory = ownershipHistoryService.getOnDate(vehicle.getLicencePlate(), m.getDate());
            if (ownershipHistory == null) continue;

            OwnerCredentials currentOwner = ownershipHistory.getOwnerCredentials();

            Long currentOwnerId = currentOwner.getId();
            Long vehicleId = vehicle.getId();

            // see if this car already exists
            CarMovements carMovement = carMovements
                    .stream()
                    .filter(x -> x.getVehicle().getId().equals(vehicleId) && x.getOwnerCredentials().getId().equals(currentOwnerId))
                    .findFirst()
                    .orElse(null);

            boolean isNew = false;

            // new car & owner
            if (carMovement == null){
                isNew = true;
                carMovement = new CarMovements(vehicle, currentOwner);
            }

            // add movement to this car
            carMovement.addMovement( m );

            if (isNew) {
                // add new list item to carmovements
                carMovements.add( carMovement );
            }
        }

        return carMovements;
    }

    /**
     * - Spitstarief per weg - DONE
     * - Verschillende tarieven op 1 weg —> tot datum X tarief X, vanaf datum Y tarief Y - DONE
     * - Verschillende eigenaren —> 2 rekeningen naar beide eigenaren DONE
     * - Verschillende type auto’s —> wordt over het totaalbedrag berekend - DONE
     */
    public List<Bill> generateBills(List<Movement> movements) throws Exception {
        List<CarMovements> carMovements = sortMovements(movements);
        if (carMovements == null) throw new Exception("Invalid data");

        List<Road> roads = roadService.getAll();
        if (roads == null) throw new Exception("Invalid data");

        return makeBills(carMovements, roads);
    }

    public List<Bill> makeBills(List<CarMovements> carMovements, List<Road> roads) {
        List<Bill> bills = new ArrayList<>();

        // for each car with all its movements
        for (CarMovements cm : carMovements) {
            Bill b = makeBill(cm, roads);
            if (b != null) bills.add(b);
        }

        return bills;
    }

    public Bill makeBill (CarMovements cm, List<Road> roads) {
        // calculate difference
        // create the bill
        Bill b = new Bill();
        b.setMonth(DateUtils.getMonthIndex(cm.getMovements().get(0).getDate()));
        b.setTotalAmount(getTotalAmount(cm.getMovements(), cm.getVehicle(), roads));
        b.setPaymentStatus(PaymentStatus.OPEN);

        if (cm.getVehicle().getCarTrackers().size() > 0) {
            // convert list to set
            Set<CarTracker> set = new HashSet<>(getCarTrackersInCarMovements(cm));
            b.setCarTrackers(set);
        }

        if (cm.getVehicle().getOwnerCredentials() != null)
            b.setOwnerCredentials(cm.getOwnerCredentials());

        // persist & return
        create(b);
        return b;
    }

    public Bill makeBill (Bill b, List<Movement> movements, List<Road> roads) throws Exception {
        if (b == null || movements == null || movements.size() == 0)
            throw new Exception("Invalid data");

        b.setMonth(DateUtils.getMonthIndex(movements.get(0).getDate()));
        Vehicle vehicle = vehicleService.getByCarTrackerId(movements.get(0).getCarTrackerId());
        if (vehicle == null) throw new Exception("Vehicle with cartrackerID " + movements.get(0).getCarTrackerId() + " not found");
        b.setTotalAmount(getTotalAmount(movements, vehicle, roads));

        b.setPaymentStatus(PaymentStatus.OPEN);
        b.setCarTrackers(b.getCarTrackers());

        // persist & return
        repository.update(b);
        return b;
    }

    public double getTotalAmount(List<Movement> movements, Vehicle v, List<Road> roads) {
        // check if the previous movement is in a rectangle box
        boolean previousInRegion = false;
        double amount = 0D;

        for (Movement m : movements) {
            // find the road
            Road r = roads
                    .stream()
                    .filter( x -> x.getName().equals(m.getAddress()))
                    .findFirst()
                    .orElse( null );

            boolean inRushHour = false;
            try {
                inRushHour = isRushHour(m.getDate());
            } catch (Exception e) {

            }

            // check if the current movement is in a rectangle box
            boolean currentlyInRegion = isInRectangle(m.getCoordinate());

            if (!previousInRegion && currentlyInRegion) {
                previousInRegion = true;
                // todo: change hardcoded 5 to a database managed amount per region
                amount += 10;
            } else if (!currentlyInRegion) {
                previousInRegion = false;
                amount += calculateTaxes(m, r, inRushHour);
            }
        }

        amount = applyVehicleTypeFactor(v.getVehicleType(), amount);

        return NumberUtils.round(amount, 2);
    }

    public List<CarTracker> getCarTrackersInCarMovements(CarMovements cm) {
        // get the max date of movements in this set
        Date maxDate = cm.getMovements().stream().map(x -> x.getDate()).max(Date::compareTo).get();

        // get all active cartrackers for this movements
        return cm
                .getVehicle()
                .getCarTrackers()
                .stream()
                .filter( x -> x.getDeletedOn() == null || x.getDeletedOn().after(maxDate))
                .collect(Collectors.toList());
    }

    public Double applyVehicleTypeFactor (VehicleType type, Double amount) {
        Double multiplier = vehicleService.getVehicleTypeMultiplier(type);
        return amount * multiplier;
    }

    public Double calculateTaxes (Movement m, Road r, boolean inRushHour) {
        // if no road was found, apply default rates
        if (r == null && inRushHour) return m.getDistance() * priceService.getDefaultRushPrice().getPrice();
        if (r == null) return m.getDistance() * priceService.getDefaultPrice().getPrice();

        if (inRushHour) {
            // rush hour for found road
            Price p = r.getMovementRushHourPrice(m.getDate());
            if (p == null) return m.getDistance() * priceService.getDefaultRushPrice().getPrice();
            return m.getDistance() * p.getPrice();
        }

        // default rate for found road
        Price p = r.getMovementPrice(m.getDate());
        if (p == null) return m.getDistance() * priceService.getDefaultPrice().getPrice();
        return m.getDistance() * p.getPrice();
    }

    public boolean isRushHour(Date d) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;

        // todo ochtendspits

        return dateFormat.parse(dateFormat.format(d)).after(dateFormat.parse("17:00"))
                && dateFormat.parse(dateFormat.format(d)).before(dateFormat.parse("19:00"));
    }

    // todo: manage regions from database
    public boolean isInRectangle (Point2D.Double p) {
        Point2D.Double leftTop = new Point2D.Double(6.585,51.56);
        Point2D.Double rightBottom = new Point2D.Double(7.16,51.21);

        Rectangle2D.Double rect = new Rectangle2D.Double(leftTop.getX(), leftTop.getY(), rightBottom.getX() - leftTop.getX(), rightBottom.getY() - leftTop.getY());

        return rect.contains(p);
    }

    public boolean changePaymenStatus (Bill bill) {
        if (bill == null)
            return false;

        if (bill.getId() == null || bill.getPaymentStatus() == null)
            return false;

        try {
            repository.create(bill);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void create(Bill b) {
        try {
            repository.create(b);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Bill> getAll() {
        return repository.getAll();
    }
    public List<Bill> getAllByBsn(Long bsn) {
        return repository.getAllByBsn(bsn);
    }

    public Bill getById(Long id) {
        return repository.getById(id);
    }

    public Bill recalculateBill(Long id, List<Movement> movements) throws Exception {
        if (id == null || movements == null || movements.size() == 0)
            return null;

        List<Road> roads = roadService.getAll();
        if (roads == null) throw new Exception("Invalid data");

        Bill b = getById(id);
        if (b == null)
            return null;

        return makeBill(b, movements, roads);
    }

    public List<Bill> getAllByVehicleId(Long vehicleId) {
        Vehicle v = vehicleService.getById(vehicleId);

        if (v == null)
            return null;

        List<Bill> all = getAll();
        List<Bill> bills = new ArrayList<>();

        if (all == null)
            return null;

        for (Bill b : all) {
            if (!Collections.disjoint(b.getCarTrackers(), v.getCarTrackers()))
                bills.add(b);
        }

        return bills;
    }
}
