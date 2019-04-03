package services;

import models.*;
import repositories.BillRepository;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Local
@Stateless
public class BillService {

    @EJB
    private BillRepository repository;

    public List<Bill> getAll() {
        return repository.getAll();
    }

    public Bill getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Bill bill) {
        if (bill == null)
            return false;

        repository.create(bill);
        return true;
    }

    public boolean update(Bill bill) {
        if (bill == null)
            return false;

        repository.update(bill);
        return true;
    }

    public boolean delete(Long id) {
        Bill bill = repository.getById(id);

        if (bill == null)
            return false;

        repository.delete(bill);
        return true;
    }

    public boolean generateBill(List<Movement> movements) {

        /*
         * Voor elke maand, voor elke auto, een rekening opstellen.
         * Stap 1: Filter movements per auto
         * Stap 2: Filter movements van de auto voor een specifieke maand.
         * Stap 3: rekening aanmaken voor de gereden kilometers uit de movements
         * */

        if (movements == null) {
            return false;
        }

        List<CarMovements> carMovements = new ArrayList<>();

        for (Movement m : movements) {

            String licensplate = m.getCarTracker().getVehicle().getLicencePlate();

            CarMovements carMovement = (CarMovements) carMovements
                    .stream()
                    .filter( x -> x.getLicencePlate().equals( licensplate ))
                    .findFirst()
                    .orElse( null );

                    boolean isNew = false;

                    if (carMovement == null){
                        isNew = true;
                        carMovement = new CarMovements(licensplate);
                    }

                    // always add movement
                    carMovement.addMovement( m );

                    if (isNew) {
                        carMovements.add( carMovement );
                    }
        }

        return true;
    }

//     List<Movement>movementList;
//
//     List<Vehicle>vehicles = null;
//
//        for (Movement m : movements)
//        {
//            for (Vehicle v : vehicles)
//            {
//
//            }
//        }
//    }
}
