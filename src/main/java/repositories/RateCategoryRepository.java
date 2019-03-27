package repositories;

import models.*;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;

@Local
@Stateless
public class RateCategoryRepository {
    @PersistenceContext(unitName = "billAdministrationPU")
    private EntityManager em;

    public RateCategoryRepository() {
    }

    public List<RateCategory> getAll() {
        try {
            return em.createNamedQuery("RateCategory.getAll", RateCategory.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public RateCategory getById(Long id) {
        try {
            return em.createNamedQuery("RateCategory.getById", RateCategory.class).setParameter("id", id).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void create(RateCategory rateCategory) {
        em.persist(rateCategory);
    }

    public void update(RateCategory rateCategory) {
        em.merge(rateCategory);
    }

    public void delete(RateCategory rateCategory) {
        em.remove(rateCategory);
    }
}
