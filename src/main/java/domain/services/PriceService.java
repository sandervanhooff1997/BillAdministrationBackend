package domain.services;

import domain.models.Price;
import domain.repositories.PriceRepository;
import org.hibernate.HibernateException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Local
@Stateless
public class PriceService {
    @EJB
    private PriceRepository repository;

    public List<Price> getAll() {
        return repository.getAll();
    }


    public boolean create(Price price) {
        if (price == null)
            return false;

        try {
            price.setBegin(new Date());
            price.setEnd(null);
            repository.create(price);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public Price getById(Long id) {
        return repository.getById(id);
    }

    public boolean update(Price price) {
        if (price == null)
            return false;

        repository.update(price);
        return true;
    }

    public boolean delete(Long id) {
        Price price = repository.getById(id);

        if (price == null)
            return false;

        repository.delete(price);
        return true;
    }
}
