package domain.repositories;

import domain.models.Road;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class RoadRepository {
    @PersistenceContext(unitName = "billadministrationPU")
    private EntityManager em;

    public RoadRepository() {
    }

    public List<Road> getAll() {
        try {
            return em.createNamedQuery("Road.getAll", Road.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public Road getById(Long id) {
        try {
            return em.createNamedQuery("Road.getById", Road.class).setParameter("id", id).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void create(Road road) {
        em.getTransaction().begin();
        em.merge(road);
        em.getTransaction().commit();
    }

    public void update(Road road) {
        em.getTransaction().begin();
        em.merge(road);
        em.getTransaction().commit();
    }

    public void delete(Road road) {
        em.remove(road);
    }
}
