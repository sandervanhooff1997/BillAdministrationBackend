package domain.repositories;

import domain.models.CarTracker;
import org.hibernate.HibernateException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CarTrackerRepository {
    @PersistenceContext(unitName = "billadministrationPU")
    private EntityManager em;

    public CarTrackerRepository() {
    }

    public List<CarTracker> getAll() {
        try {
            return em.createNamedQuery("CarTracker.getAll", CarTracker.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public CarTracker getById(Long id) {
        try {
            return em.createNamedQuery("CarTracker.getById", CarTracker.class).setParameter("id", id).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void create(CarTracker carTracker) throws HibernateException {
        em.persist(carTracker);
    }

    public void update(CarTracker carTracker) {
        em.merge(carTracker);
    }

    public void delete(CarTracker carTracker) {
        em.remove(carTracker);
    }
}
