package domain.services;

import domain.models.*;
import domain.models.Comparators.MovementComparer;
import domain.repositories.BillRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;
import java.util.*;

@Local
@Stateless
public class BillService {

    @EJB
    private BillRepository repository;

    @EJB
    private VehicleService vehicleService;

    @EJB
    private CarTrackerService carTrackerService;

    @EJB
    private CountryService countryService;

    public boolean generateBills(List<Movement> movements) throws Exception {

        /*
         * Voor elke maand, voor elke auto, een rekening opstellen.
         * Stap 1: Filter movements per auto
         * Stap 2: Filter movements van de auto voor een specifieke maand.
         * Stap 3: rekening aanmaken voor de gereden kilometers uit de movements
         * */

        if (movements == null) {
            return false;
        }

        // list with all distinct cars and their movements
        List<CarMovements> carMovements = new ArrayList<>();

        for (Movement m : movements) {

            // get vehicle with the cartracker in the movement
            Vehicle v = vehicleService.getByCarTrackerId(m.getCarTracker().getId());

            if (v != null) {
                String licencePlate = v.getLicencePlate();

                // see if this car already exists
                CarMovements carMovement = carMovements
                        .stream()
                        .filter( x -> x.getLicencePlate().equals(licencePlate))
                        .findFirst()
                        .orElse( null );

                boolean isNew = false;

                // new car
                if (carMovement == null){
                    isNew = true;
                    carMovement = new CarMovements(licencePlate);
                }

                // add movement to this car
                carMovement.addMovement( m );

                if (isNew) {
                    // add new list item to carmovements
                    carMovements.add( carMovement );
                }
            }
        }

        // for each car with all its movements
        for (CarMovements cm : carMovements) {

            // for each month of movements for the current car
            for (Map.Entry<String, List<Movement>> monthCm: cm.getMonthMovements().entrySet()) {

                // get the list of movements for this month
                List<Movement> monthMovements = monthCm.getValue();

                // get the movement with the highest mileage on the cartracker
                Movement m = Collections.max(monthMovements, new MovementComparer());

                // get carTracker from database
                CarTracker ct = carTrackerService.getById(m.getCarTracker().getId());

                // calculate difference
                if (ct != null) {
                    int current = ct.getMileage();
                    int target = m.getCarTracker().getMileage();
                    int difference = target - current;

                    if (difference < 0) {
                        throw new Exception("A car can\'t go back in mileage");
                    }

                    Vehicle v = vehicleService.getByLicencePlate(cm.getLicencePlate());
                    if (v == null)
                        throw new NotFoundException("Vehicle with licenceplate " + cm.getLicencePlate() + " not found.");

                    // create the bill
                    Bill b = new Bill();
                    b.setMonth(m.getMonthIndex());
                    b.setPaymentStatus(PaymentStatus.OPEN);
                    b.setTotalAmount(calculateMileageCosts(difference, v));
                    b.setCarTrackers(v.getCarTrackers());
                    b.setOwnerCredentials(v.getOwnerCredentials().get(v.getOwnerCredentials().size() -1));

                    // persist to database !!
                    create(b);
                }
            }
        }

            return true;
    }

    public double calculateMileageCosts (int kms, Vehicle vehicle) {

        /*
        Stap 1: Checken of de binnengekomen movements in de spits waren, of niet.
        Stap 2: RateCategory van het betreffende voertuig ophalen.
        Stap 3: Controleren of de movements binnen een regio vallen, anders de toenmalige weg ophalen en deze doorberekenen.
        Stap 4: Totaalprijs berekenen op basis van bovenstaande gegevens.
        Stap 5: Totaaltarief retourneren
         */

        Double totalPrice = 0.0;
        List<Movement> movements = vehicle.getCarTracker().getMovements();
        Movement previousMovement;


        for (int i = 0; i < movements.size(); i++) {
            //Movement object uit array ophalen, zodat deze uitgelezen kan worden.
            Movement movement = movements.get(i);

            //Controleren of de movement wel of niet in de spits valt.
            totalPrice = totalPrice + countryService.getRushHourRate(movement);

            //Rate category ophalen van het betreffende vehicle en controleren of de laatste movement uit de cartracker van deze vehicle binnen een regio valt of
            totalPrice = totalPrice + countryService.getTotalPricePerKilometer(vehicle);

            //Vorige movement opslaan.
            previousMovement = movement;
        }

        return kms * 0.5;
    }

    public boolean changePaymenStatus (Bill bill) {
        if (bill == null)
            return false;

        if (bill.getId() == null || bill.getPaymentStatus() == null)
            return false;

        try {
            repository.changePaymentStatus(bill);
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

    public Bill getById(Long id) {
        return repository.getById(id);
    }

    public Bill recalculateBill(Long id) {
        Bill b = getById(id);

        if (b == null)
            return null;

        ;

        /** todo: get movements by cartracker and month of bill
         *  List<Movement></Movement> movements = movementService.getMovementsFromCarTrackersAndId(b.getCarTrackers(), b.getMonthIndex());
         *  recalculate(movements);
         */

        return b;
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
