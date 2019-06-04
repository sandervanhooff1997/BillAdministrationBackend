package domain.services;

import domain.enums.VehicleType;
import domain.models.CarTracker;
import domain.models.OwnerCredentials;
import domain.models.Vehicle;
import domain.repositories.VehicleRepository;
import org.hibernate.HibernateException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class VehicleService {
    @EJB
    private VehicleRepository repository;

    @EJB
    private CarTrackerService carTrackerService;

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

    public List<String> getAllStolenLicencePlates() {
        try {
            List<Vehicle> vehicles = repository.getAll();
            List<String> stolen = new ArrayList<>();

            for (Vehicle v : vehicles) {
                if (v.isStolen())
                    stolen.add(v.getLicencePlate());
            }

            return stolen;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<String> getAllLicencePlates() {
        try {
            List<Vehicle> vehicles = repository.getAll();
            List<String> licencePlates = new ArrayList<>();

            for (Vehicle v : vehicles) {
                licencePlates.add(v.getLicencePlate());
            }

            return licencePlates;
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

    public boolean  create(String licencePlate, VehicleType vehicleType, Long carTrackerId, Long ownerCredentialsId) {
        if (licencePlate.isEmpty())
            return false;

        Vehicle v = new Vehicle();
        v.setLicencePlate(licencePlate);
        v.setVehicleType(vehicleType);

        OwnerCredentials oc = ownerCredentialService.getById(ownerCredentialsId);

        if (oc != null)
        {
            oc.setBegin(new Date());
            v.addOwnerCredentials(oc);
        }

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

    public boolean markAsStolen(String licencePlate) {
        if (licencePlate.isEmpty())
        return false;

        Vehicle v = getByLicencePlate(licencePlate);

        if (v == null)
            return false;

        // make the mark
        v.setStolen(true);

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

    public Double getVehicleTypeMultiplier(VehicleType type) {
        switch(type) {
            case ELECTRIC: return 0.75;
            case COMBUSTION: return 1.0;
            default: return 1.0;
        }
    }
}
