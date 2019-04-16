package domain.models;

//import com.sun.istack.NotNull;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//import javax.validation.constraints.PositiveOrZero;
//import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "RateCategory.getById", query = "select rc from RateCategory rc where rc.id = :id"),
        @NamedQuery(name = "RateCategory.getAll", query = "select rc from RateCategory rc")
})
@Table(name = "ratecategories")
public class RateCategory implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private Double price;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Vehicle> vehicles = new ArrayList<>();

    public RateCategory() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "RateCategoryController{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
