package domain.services;

import domain.models.Price;
import domain.models.Road;
import domain.models.Vehicle;
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

    public Road getById(Long id) {
        return repository.getById(id);
    }


    public boolean create(String name, Long priceId, Long rushHourPriceId) {
        if (name == null || priceId == null || rushHourPriceId == null)
            return false;

        try {
            Price price = priceService.getById(priceId);
            if (price == null) return false;

            Price rushHourPrice = priceService.getById(rushHourPriceId);
            if (rushHourPrice == null) return false;

            Road r = new Road();
            r.setName(name);
            r.addPrice(price);
            r.addRushPrice(rushHourPrice);

            repository.create(r);
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

    public boolean addPriceToRoad(Long roadId, Long priceId) {
        if (roadId == null || priceId == null)
            return false;

        Road r = getById(roadId);
        if (r == null) return false;

        Price p = priceService.getById(priceId);
        if (p == null) return false;

        r.addPrice(p);

        repository.update(r);
        return true;
    }

    public boolean addRushPriceToRoad(Long roadId, Long priceId) {
        if (roadId == null || priceId == null)
            return false;

        Road r = getById(roadId);
        if (r == null) return false;

        Price p = priceService.getById(priceId);
        if (p == null) return false;

        r.addRushPrice(p);

        repository.update(r);
        return true;
    }

    public boolean delete(Long id) {
        Road road = repository.getById(id);

        if (road == null)
            return false;

        repository.delete(road);
        return true;
    }
}
