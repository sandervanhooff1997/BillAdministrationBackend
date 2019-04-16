package domain.services;

import domain.models.Bill;
import domain.models.CarMovements;
import domain.models.Movement;
import domain.repositories.BillRepository;

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

    public boolean generateBill(List<Movement> movements) {

        /*
         * Voor elke maand, voor elke auto, een rekening opstellen.
         * Stap 1: Filter movements per auto
         * Stap 2: Filter movements van de auto voor een specifieke maand.
         * Stap 3: rekening aanmaken voor de gereden kilometers uit de movements
         * */

//        if (movements == null) {
//            return false;
//        }
//
//        List<CarMovements> carMovements = new ArrayList<>();
//
//        for (Movement m : movements) {
//
//            String licensplate = m.getCarTracker().getVehicle().getLicencePlate();
//
//            CarMovements carMovement = (CarMovements) carMovements
//                    .stream()
//                    .filter( x -> x.getLicencePlate().equals( licensplate ))
//                    .findFirst()
//                    .orElse( null );
//
//            boolean isNew = false;
//
//            if (carMovement == null){
//                isNew = true;
//                carMovement = new CarMovements(licensplate);
//            }
//
//            // always add movement
//            carMovement.addMovement( m );
//
//            if (isNew) {
//                carMovements.add( carMovement );
//            }
//        }

        return true;
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
