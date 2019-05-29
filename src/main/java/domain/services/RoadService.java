package domain.services;

import domain.models.Road;
import domain.repositories.RoadRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class RoadService {
    @EJB
    private RoadRepository repository;

    @EJB
    private PriceService priceService;

    public List<Road> getAll() {
        return repository.getAll();
    }


    public boolean create(Road road) {
        if (road == null)
            return false;

        try {
            repository.create(road);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Road road) {
        if (road == null)
            return false;

        repository.update(road);
        return true;
    }

    public boolean delete(Long id) {
        Road road = repository.getById(id);

        if (road == null)
            return false;

        repository.delete(road);
        return true;
    }

    public double getDefaultKilometerRate () {
        return 0.1;
    }

    public double getDefaultRushHourKilometerRate () {
        return 0.2;
    }
}
