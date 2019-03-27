package services;

import models.*;
import repositories.*;

import javax.ejb.*;
import java.util.*;

@Local
@Stateless
public class CarTrackerService {
    @EJB
    private CarTrackerRepository repository;

    public List<CarTracker> getAll() {
        return repository.getAll();
    }

    public CarTracker getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(CarTracker carTracker) {
        if (carTracker == null)
            return false;

        repository.create(carTracker);
        return true;
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
