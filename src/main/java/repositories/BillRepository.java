package repositories;

import models.Bill;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class BillRepository {

    @PersistenceContext(unitName = "billAdministrationPU")
    private EntityManager em;

    public BillRepository() {
    }

    public List<Bill> getAll() {
        try {
            return em.createNamedQuery("Bill.getAll", Bill.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public Bill getById(Long id) {
        try {
            return em.createNamedQuery("Bill.getById", Bill.class).setParameter("id", id).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void create(Bill bill) {
        em.persist(bill);
    }

    public void update(Bill bill) {
        em.merge(bill);
    }

    public void delete(Bill bill) {
        em.remove(bill);
    }
}
