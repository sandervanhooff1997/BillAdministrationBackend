package domain.repositories;

import domain.models.Bill;
import domain.models.Vehicle;
import org.hibernate.HibernateException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Local
@Stateless
public class BillRepository {
    @PersistenceContext(unitName = "billadministrationPU")
    private EntityManager em;

    public Bill getById (Long id) throws HibernateException {
        return em.createNamedQuery("Bill.getById", Bill.class).setParameter("id", id).getSingleResult();
    }

    public void changePaymentStatus(Bill bill) throws HibernateException, NotFoundException {
        Bill b = getById(bill.getId());

        if (b == null)
            throw new NotFoundException("Bill could not be found");

        // update payment status
        b.setPaymentStatus(bill.getPaymentStatus());
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

    public void create(Bill bill) {
        try {
            em.getTransaction().begin();
            em.merge(bill);
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
