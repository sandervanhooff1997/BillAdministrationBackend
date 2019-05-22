package domain.services.auth;

import domain.models.auth.Employee;
import domain.repositories.auth.EmployeeRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class EmployeeService {

    @EJB
    private EmployeeRepository repository;

    public List<Employee> getAll() {
        return repository.getAll();
    }

    public Employee getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Employee employee) {
        if (employee == null)
            return false;

        repository.create(employee);
        return true;
    }

    public boolean update(Employee employee) {
        if (employee == null)
            return false;

        repository.update(employee);
        return true;
    }

    public boolean delete(Long id) {
        Employee employee = repository.getById(id);

        if (employee == null)
            return false;

        repository.delete(employee);
        return true;
    }

    public Employee getByEmail(String email) {
        return repository.getByEmail(email);
    }

    public Employee getByEmailAndPassword(String email, String password) {
        return repository.getByEmailAndPassword(email, password);
    }
}
