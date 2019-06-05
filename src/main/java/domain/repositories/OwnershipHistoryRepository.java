package domain.repositories;

import domain.models.OwnershipHistory;
import org.hibernate.HibernateException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class OwnershipHistoryRepository {
    @PersistenceContext(unitName = "billadministrationPU")
    private EntityManager em;

    public OwnershipHistoryRepository() {
    }

    public List<OwnershipHistory> getAll() {
        try {
            return em
                    .createNamedQuery("OwnershipHistory.getAll", OwnershipHistory.class)
                    .getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public List<OwnershipHistory> getAllByLicensePlate(String licensePlate) {
        try {
            return em
                    .createNamedQuery("OwnershipHistory.getAllByLicensePlate", OwnershipHistory.class)
                    .setParameter("licensePlate", licensePlate)
                    .getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public OwnershipHistory getCurrentOwnerHistory(String licensePlate) {
        try {
            return em
                    .createNamedQuery("OwnershipHistory.getCurrentOwnerHistory", OwnershipHistory.class)
                    .setParameter("licensePlate", licensePlate)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public OwnershipHistory getById(Long id) {
        try {
            return em
                    .createNamedQuery("OwnershipHistory.getById", OwnershipHistory.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public OwnershipHistory create(OwnershipHistory ownershipHistory) throws HibernateException {
        try {

            em.getTransaction().begin();
            em.merge(ownershipHistory);
            em.getTransaction().commit();
            return ownershipHistory;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void update(OwnershipHistory ownershipHistory) {
        em.merge(ownershipHistory);
    }

    public void delete(OwnershipHistory ownershipHistory) {
        em.remove(ownershipHistory);
    }
}
