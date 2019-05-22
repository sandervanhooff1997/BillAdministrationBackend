package domain.services;

import domain.models.CarTracker;
import domain.models.Vehicle;
import domain.repositories.CarTrackerRepository;
import org.hibernate.HibernateException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CarTrackerService {
    @EJB
    private CarTrackerRepository repository;

    @EJB
    private VehicleService vehicleService;

    public List<CarTracker> getAll() {
        return repository.getAll();
    }

    public List<CarTracker> getAllUnused() {
        List<CarTracker> carTrackers = getAll();
        List<Vehicle> vehicles = vehicleService.getAll();

        // remove used cartrackers
        for (Vehicle v : vehicles) {
            if (v.getCarTrackers() != null)
                carTrackers.remove(v.getCarTracker());
        }

        return carTrackers;
    }

    public CarTracker getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(CarTracker carTracker) {
        if (carTracker == null)
            return false;

        try {
            repository.create(carTracker);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public boolean update(CarTracker carTracker) {
        if (carTracker == null)
            return false;

        repository.update(carTracker);
        return true;
    }

    public boolean delete(Long id) {
        CarTracker carTracker = repository.getById(id);

        if (carTracker == null)
            return false;

        repository.delete(carTracker);
        return true;
    }
}
