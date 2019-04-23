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
                    b.setTotalAmount(calculateMileageCosts(difference));
                    b.setCarTrackers(v.getCarTrackers());

                    // persist to database !!
                    create(b);
                }
            }
        }

            return true;
    }

    public double calculateMileageCosts (int kms) {
        // todo: berekening met Product Owner bespreken
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

}
