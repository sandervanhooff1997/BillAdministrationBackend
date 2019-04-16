package domain.services;

import domain.models.RateCategory;
import domain.repositories.RateCategoryRepository;
import org.hibernate.HibernateException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class RateCategoryService {
    @EJB
    private RateCategoryRepository repository;

    public List<RateCategory> getAll() {
        return repository.getAll();
    }

    public RateCategory getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(RateCategory rateCategory) {
        if (rateCategory == null)
            return false;

        try {
            repository.create(rateCategory);
            return true;
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean update(RateCategory rateCategory) {
        if (rateCategory == null)
            return false;

        repository.update(rateCategory);
        return true;
    }

    public boolean delete(Long id) {
        RateCategory rateCategory = repository.getById(id);

        if (rateCategory == null)
            return false;

        repository.delete(rateCategory);
        return true;
    }
}
