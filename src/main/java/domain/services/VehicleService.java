package domain.services;

import domain.models.CarTracker;
import domain.models.OwnerCredentials;
import domain.models.RateCategory;
import domain.models.Vehicle;
import domain.repositories.VehicleRepository;
import org.hibernate.HibernateException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.security.acl.Owner;
import java.util.*;
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

    @EJB
    private OwnerCredentialService ownerCredentialService;

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

    public Vehicle getByCarTrackerId(Long id) {
        List<Vehicle> vehicles = getAll();
        Vehicle vehicle = null;

        for (Vehicle v : vehicles) {
            for (CarTracker ct: v.getCarTrackers()) {
                if (ct.getId().equals(id))
                    vehicle = v;
            }
        }

        return vehicle;
    }

    public Vehicle getByLicencePlate(String licencePlate) {
        return repository.getByLicencePlate(licencePlate);
    }

    public boolean  create(String licencePlate, Long rateCategoryId, Long carTrackerId, Long ownerCredentialsId) {
        if (licencePlate.isEmpty())
            return false;

        Vehicle v = new Vehicle();
        v.setLicencePlate(licencePlate);

        OwnerCredentials oc = ownerCredentialService.getById(ownerCredentialsId);

        if (oc != null)
        {
            oc.setBegin(new Date());
            v.addOwnerCredentials(oc);
        }

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

    public boolean transferOwnership (Long vId, Long ocId) {
        OwnerCredentials oc = ownerCredentialService.getById(ocId);

        if (oc == null)
            return false;

        Vehicle v = getById(vId);

        if (v == null)
            return false;

        v.getOwnerCredentials().add(oc);

        return update(v);
    }

    public boolean changeCarTracker (Long vId, Long ctId) {
        CarTracker ct = carTrackerService.getById(ctId);

        if (ct == null)
            return false;

        Vehicle v = getById(vId);

        if (v == null)
            return false;

        v.getCarTrackers().add(ct);

        return update(v);
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
