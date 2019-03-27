package repositories;

import models.*;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;

@Local
@Stateless
public class VehicleRepository {
    @PersistenceContext(unitName = "billAdministrationPU")
    private EntityManager em;

    public VehicleRepository() {
    }

    public List<Vehicle> getAll() {
        try {
            return em.createNamedQuery("Vehicle.getAll", Vehicle.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public Vehicle getById(Long id) {
        try {
            return em.createNamedQuery("Vehicle.getById", Vehicle.class).setParameter("id", id).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void create(Vehicle rateCategory) {
        em.persist(rateCategory);
    }

    public void update(Vehicle rateCategory) {
        em.merge(rateCategory);
    }

    public void delete(Vehicle rateCategory) {
        em.remove(rateCategory);
    }
}
