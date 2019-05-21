package domain.repositories;

import domain.models.Vehicle;
import org.hibernate.HibernateException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class VehicleRepository {
    @PersistenceContext(unitName = "billadministrationPU")
    private EntityManager em;

    public VehicleRepository() {
    }

    public List<Vehicle> getAll() throws HibernateException {
        return em.createNamedQuery("Vehicle.getAll", Vehicle.class).getResultList();
    }

    public Vehicle getById(Long id) {
        try {
            return em.createNamedQuery("Vehicle.getById", Vehicle.class).setParameter("id", id).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Vehicle getByLicencePlate(String licencePlate) {
        try {
            return em
                    .createQuery("select v from Vehicle v where v.licencePlate = :licencePlate", Vehicle.class)
                    .setParameter("licencePlate", licencePlate)
                    .getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void create(Vehicle vehicle) {
        em.getTransaction().begin();
        em.merge(vehicle);
        em.getTransaction().commit();
    }

    public void update(Vehicle vehicle) {
        em.merge(vehicle);
    }

    public void delete(Vehicle rateCategory) {
        em.remove(rateCategory);
    }
}
