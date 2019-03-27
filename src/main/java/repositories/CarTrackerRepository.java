package repositories;

import models.*;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;

@Local
@Stateless
public class CarTrackerRepository {
    @PersistenceContext(unitName = "billAdministrationPU")
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

    public void create(CarTracker carTracker) {
        em.persist(carTracker);
    }

    public void update(CarTracker carTracker) {
        em.merge(carTracker);
    }

    public void delete(CarTracker carTracker) {
        em.remove(carTracker);
    }
}
