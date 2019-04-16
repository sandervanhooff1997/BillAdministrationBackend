package domain.services;

import domain.models.CarTracker;
import domain.repositories.CarTrackerRepository;
import org.hibernate.HibernateException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

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
