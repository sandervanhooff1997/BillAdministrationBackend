package domain.repositories;

import domain.models.RateCategory;
import org.hibernate.HibernateException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RateCategoryRepository {
    @PersistenceContext(unitName = "billadministrationPU")
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

    public void create(RateCategory rateCategory) throws HibernateException {
        em.persist(rateCategory);
    }

    public void update(RateCategory rateCategory) {
        em.merge(rateCategory);
    }

    public void delete(RateCategory rateCategory) {
        em.remove(rateCategory);
    }
}
