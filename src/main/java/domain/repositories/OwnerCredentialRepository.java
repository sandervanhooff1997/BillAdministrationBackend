package domain.repositories;


import domain.models.OwnerCredentials;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class OwnerCredentialRepository {
    @PersistenceContext(unitName = "billadministrationPU")
    private EntityManager em;

    public OwnerCredentialRepository() {
    }

    public List<OwnerCredentials> getAll() {
        try {
            return em.createNamedQuery("OwnerCredentials.getAll", OwnerCredentials.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public OwnerCredentials getById(Long id) {
        try {
            return em.createNamedQuery("OwnerCredentials.getById", OwnerCredentials.class).setParameter("id", id).getSingleResult();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void create(OwnerCredentials ownerCredentials) {
        em.persist(ownerCredentials);
    }

    public void update(OwnerCredentials ownerCredentials) {
        em.merge(ownerCredentials);
    }

    public void delete(OwnerCredentials ownerCredentials) {
        em.remove(ownerCredentials);
    }
}
