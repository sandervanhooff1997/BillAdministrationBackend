package domain.repositories.auth;

import domain.models.auth.Role;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class AuthRepository {
    @PersistenceContext(unitName = "billadministrationPU")
    private EntityManager em;

    public List<Role> getAllRoles() {
        try {
            return em.createQuery("SELECT g FROM Role g", Role.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Role getRoleById(Long id) {
        return em.createQuery("SELECT e FROM Role e where e.id = :id", Role.class).setParameter("id", id).getSingleResult();
    }

    public void createRole(Role role) {
        em.persist(role);
    }

    public void updateRole(Role role) {
        em.merge(role);
    }

    public void deleteRole(Role role) {
        em.remove(role);
    }

}
