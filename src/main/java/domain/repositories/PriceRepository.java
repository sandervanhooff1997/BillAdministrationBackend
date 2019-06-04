package domain.repositories;

import domain.models.Price;
import org.hibernate.HibernateException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class PriceRepository {
    @PersistenceContext(unitName = "billadministrationPU")
    private EntityManager em;

    public PriceRepository() {
    }

    public List<Price> getAll() {
        try {
            return em.createNamedQuery("Price.getAll", Price.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public List<Price> getAllUnused() {
        try {
            return em.createNamedQuery("Price.getAllUnused", Price.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public Price getById(Long id) {
        try {
            return em.createNamedQuery("Price.getById", Price.class).setParameter("id", id).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Price create(Price price) throws HibernateException {
        try {
            em.persist(price);
            return price;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void update(Price price) {
        em.merge(price);
    }

    public void delete(Price price) {
        em.remove(price);
    }
}
