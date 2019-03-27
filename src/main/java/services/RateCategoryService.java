package services;

import models.*;
import repositories.*;

import javax.ejb.*;
import java.util.*;

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

        repository.create(rateCategory);
        return true;
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
