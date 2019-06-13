package domain.repositories;

import domain.models.Bill;
import domain.models.Price;
import org.hibernate.HibernateException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
public class BillRepository {
    @PersistenceContext(unitName = "billadministrationPU")
    private EntityManager em;

    public Bill getById (Long id) throws HibernateException {
        return em.createNamedQuery("Bill.getById", Bill.class).setParameter("id", id).getSingleResult();
    }

    public List<Bill> getAll() {
        try {
            List<Bill> bills = em.createQuery("SELECT c FROM Bill c", Bill.class).getResultList();
            return bills;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public List<Bill> getAllByBsn(Long bsn) {
        try {
            return em.createNamedQuery("Bill.getAllByBsn", Bill.class)
                    .setParameter("bsn", bsn)
                    .getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void create(Bill bill) {
        try {
            em.getTransaction().begin();
            em.merge(bill);
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Bill bill) {
        try {
            em.merge(bill);
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
