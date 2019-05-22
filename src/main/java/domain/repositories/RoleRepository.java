package domain.repositories;

import domain.models.auth.Role;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Local
@Stateless
public class RoleRepository {
    @PersistenceContext(unitName = "billadministrationPU")
    private EntityManager em;

    public Role getById(Long id) {
        return em.createNamedQuery("Role.getRoleById", Role.class).setParameter("id", id).getSingleResult();
    }

    public void delete(Role role) {
        em.remove(role);
    }
}
