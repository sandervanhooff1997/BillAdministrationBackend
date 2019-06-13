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

    public List<Price> getAllUnused() {
        return repository.getAllUnused();
    }

    public Price getDefaultPrice() {
        Price p = repository.getDefaultPrice();

        // create if doesnt exist
        if (p == null){
            p = new Price();
            p.setPrice(0D);
            p.setDefaultPrice(true);
            create(p);
        }

        return p;
    }

    public Price getDefaultRushPrice() {
        Price p = repository.getDefaultRushPrice();

        // create if doesnt exist
        if (p == null){
            p = new Price();
            p.setPrice(0D);
            p.setDefaultRushPrice(true);
            create(p);
        }

        return p;
    }

    public Price create(Price price) {
        if (price == null)
            return null;

        try {
            price.setBegin(new Date());
            price.setEnd(null);
            return repository.create(price);
        } catch (HibernateException e) {
            return null;
        }
    }

    public Price createOrUpdateDefaultPrice(double price, boolean isRushHour) {
        try {
            Price p = isRushHour ? getDefaultRushPrice() : getDefaultPrice();

            // new price
            if (p == null) {
                p = new Price();
                p.setPrice(price);

                if (isRushHour)
                    p.setDefaultRushPrice(true);
                else
                    p.setDefaultPrice(true);

                repository.create(p);
            }
            //existing price
            else {
                p.setPrice(price);
                repository.update(p);
            }

            return p;
        } catch (HibernateException e) {
            return null;
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
