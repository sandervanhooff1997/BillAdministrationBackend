package models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Vehicle.getById", query = "select v from Vehicle v where v.id = :id"),
        @NamedQuery(name = "Vehicle.getAll", query = "select v from Vehicle v")
})
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @Min(6)
    @Max(6)
    private String licencePlate;

    @ManyToOne
    private RateCategory rateCategory;

    @OneToOne
    private CarTracker carTracker;

    @OneToMany(targetEntity = OwnerCredentials.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List ownerCredentials;

    public Vehicle() {
    }

    public Vehicle(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public RateCategory getRateCategory() {
        return rateCategory;
    }

    public void setRateCategory(RateCategory rateCategory) {
        this.rateCategory = rateCategory;
    }

    public CarTracker getCarTracker() {
        return carTracker;
    }

    public void setCarTracker(CarTracker carTracker) {
        this.carTracker = carTracker;
    }

    public List getOwnerCredentials() {
        return ownerCredentials;
    }

    public void setOwnerCredentials(List ownerCredentials) {
        this.ownerCredentials = ownerCredentials;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", licencePlate='" + licencePlate + '\'' +
                ", rateCategory=" + rateCategory +
                ", carTracker=" + carTracker +
                ", ownerCredentials=" + ownerCredentials +
                '}';
    }
}
