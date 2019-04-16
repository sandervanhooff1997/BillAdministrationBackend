package domain.services;

import domain.models.CarTracker;
import domain.models.RateCategory;
import domain.models.Vehicle;
import domain.repositories.VehicleRepository;
import org.hibernate.HibernateException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Local
@Stateless
public class VehicleService {
    @EJB
    private VehicleRepository repository;

    @EJB
    private CarTrackerService carTrackerService;

    @EJB
    private RateCategoryService rateCategoryService;

    public List<Vehicle> getAll() {
        try {
            return repository.getAll();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Vehicle> getAllStolen() {
        try {
            List<Vehicle> vehicles = repository.getAll();
            List<Vehicle> stolen = new ArrayList<>();

            for (Vehicle v : vehicles) {
                if (v.isStolen())
                    stolen.add(v);
            }

            return stolen;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Vehicle getById(Long id) {
        return repository.getById(id);
    }

    public Vehicle getByLicencePlate(String licencePlate) {
        return repository.getByLicencePlate(licencePlate);
    }

    public boolean  create(String licencePlate, Long rateCategoryId, Long carTrackerId) {
        if (licencePlate.isEmpty())
            return false;

        Vehicle v = new Vehicle();

        v.setLicencePlate(licencePlate);

        if (v == null)
            return false;

        RateCategory rc = rateCategoryService.getById(rateCategoryId);
        if (rc != null)
            v.setRateCategory(rc);

        CarTracker ct = carTrackerService.getById(carTrackerId);
        if (ct != null)
            v.addCarTracker(ct);

        try {
            System.out.println("Saving: " + v);
            repository.create(v);
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean markAsStolen(String licencePlate, boolean isStolen) {
        if (licencePlate.isEmpty())
            return false;

        Vehicle v = getByLicencePlate(licencePlate);

        if (v == null)
            return false;

        // make the mark
        v.setStolen(isStolen);

        repository.update(v);
        return true;
    }

    public boolean update(Vehicle vehicle) {
        if (vehicle == null)
            return false;

        repository.update(vehicle);
        return true;
    }

    public boolean delete(Long id) {
        Vehicle vehicle = repository.getById(id);

        if (vehicle == null)
            return false;

        repository.delete(vehicle);
        return true;
    }

    public boolean addCarTracker(String licencePlate, String hardware) {
        if (licencePlate.isEmpty() || hardware.isEmpty())
            return false;

        Vehicle v = getByLicencePlate(licencePlate);

        if (v == null)
            return false;

        // get most recent car tracker
        CarTracker ct = v.getCarTracker();

        // delete the old one
        if (ct != null)
            ct.setDeleted(true);

        update(v);

        return true;
    }
}
