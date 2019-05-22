package domain.models.auth;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.getById", query = "select e from Employee e where e.id = :id"),
        @NamedQuery(name = "Employee.getByEmail", query = "select e from Employee e where e.email = :email"),
        @NamedQuery(name = "Employee.getByEmailAndPassword", query = "select e from Employee e where e.email = :email and e.password = :password"),
        @NamedQuery(name = "Employee.getAll", query = "select e from Employee e")
})
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String email;

    private String password;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;


    @OneToMany(targetEntity = Role.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Role> roles;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validForRegistration () {
        return !this.email.isEmpty() && !this.password.isEmpty() && !this.firstname.isEmpty() && !this.lastname.isEmpty();
    }

    public void AssignRole (Role role) {
        this.roles.add(role);
    }

    public void RemoveRole (Role role) {
        this.roles.remove(role);
    }

    public List getRoles() {
        return roles;
    }

    public String getCommaSeperatedRoles () {
        String result = "";

        if (roles.size() > 0) {
            StringBuilder sb = new StringBuilder();

            // append each role name to result
            for (Role role : roles) {
                sb.append(role.getName()).append(",");
            }

            result = sb.deleteCharAt(sb.length() - 1).toString();
        }

        return result;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
