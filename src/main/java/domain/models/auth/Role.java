package domain.models.auth;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@NamedQueries({
        @NamedQuery(name = "Role.getById", query = "select c from Role c where c.id = :id"),
        @NamedQuery(name = "Role.getAll", query = "select c from Role c")
})
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
