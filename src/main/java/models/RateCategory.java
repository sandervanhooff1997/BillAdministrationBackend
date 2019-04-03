package models;

//import com.sun.istack.NotNull;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.*;
//import javax.validation.constraints.PositiveOrZero;
//import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "RateCategory.getById", query = "select rc from RateCategory rc where rc.id = :id"),
        @NamedQuery(name = "RateCategory.getAll", query = "select rc from RateCategory rc")
})
@Table(name = "ratecategories")
public class RateCategory {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private Double price;

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

    @Override
    public String toString() {
        return "RateCategoryController{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
