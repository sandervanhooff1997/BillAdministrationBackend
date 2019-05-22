package domain.services.auth;

import domain.models.auth.Employee;
import domain.utils.AuthenticationUtils;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.ws.rs.NotFoundException;
import java.util.logging.Logger;

@Local
@Stateless
@Default
public class AuthenticationService {
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @EJB
    JWTService jwtService;

    @EJB
    EmployeeService employeeService;

    public void register (Employee employee) throws Exception {
        if (employee == null)
            throw new Exception("401");

        if (!employee.validForRegistration())
            throw new Exception("401");

        try {
            // encode password with SHA256
            employee.setPassword(AuthenticationUtils.encodeSHA256(employee.getPassword()));
        } catch (Exception e) {
            logger.warning(e.getMessage());
            throw new Exception("401");
        }

        Employee emp = employeeService.getByEmail(employee.getEmail());

        if (emp != null) throw new Exception("Account allready exists");

        employeeService.create(employee);
    }

    public String login (Employee e) throws Exception {
        if (e == null) throw new NullPointerException();

        if (e.getEmail().isEmpty() || e.getPassword().isEmpty()) throw new NullPointerException();

        Employee emp = authenticateEmloyee(e);

        // new device
        if (emp == null) throw new NotFoundException();

        return jwtService.createJWT(emp);

    }

    private Employee authenticateEmloyee (Employee employee) {
        if (employee.getEmail().isEmpty() || employee.getPassword().isEmpty())
            return null;

        try {
            // encode password with SHA256
            employee.setPassword(AuthenticationUtils.encodeSHA256(employee.getPassword()));
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }

        return getByEmailAndPassword(employee.getEmail(), employee.getPassword());
    }

    public Employee getById(Long id) {
        return employeeService.getById(id);
    }

    public Employee getByEmail(String email) {
        return employeeService.getByEmail(email);
    }

    public Employee getByEmailAndPassword(String email, String password) {
        return employeeService.getByEmailAndPassword(email, password);
    }
}
