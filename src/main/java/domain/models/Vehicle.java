package domain.models;

import com.sun.istack.internal.NotNull;
import domain.models.enumerators.VehicleTypeEnum;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.enterprise.inject.Default;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@NamedQueries({
        @NamedQuery(name = "Vehicle.getById", query = "select v from Vehicle v where v.id = :id"),
        @NamedQuery(name = "Vehicle.getAll", query = "select v from Vehicle v")
})
@Table(name = "vehicles")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String licencePlate;

    private boolean isStolen;

    @NotNull
    @Enumerated(STRING)
    @Column(nullable = false)
    private Enum<VehicleTypeEnum> vehicleType;

    @OneToMany(targetEntity = CarTracker.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CarTracker> carTrackers;

    @OneToMany(targetEntity = OwnerCredentials.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OwnerCredentials> ownerCredentials;

    public Vehicle() {
        carTrackers = new ArrayList<>();
        ownerCredentials = new ArrayList<>();
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

    public List<OwnerCredentials> getOwnerCredentials() {
        return ownerCredentials;
    }

    public void setOwnerCredentials(List<OwnerCredentials> ownerCredentials) {
        this.ownerCredentials = ownerCredentials;
    }

    public void addOwnerCredentials(OwnerCredentials oc) {
        this.ownerCredentials.add(oc);
    }

    public List<CarTracker> getCarTrackers() {
        return carTrackers;
    }

    public void addCarTracker(CarTracker carTracker) {
        this.carTrackers.add(carTracker);
    }

    public CarTracker getCarTracker() {
        return carTrackers.get(carTrackers.size()-1);
    }

    public void setCarTrackers(List<CarTracker> carTrackers) {
        this.carTrackers = carTrackers;
    }

    public boolean isStolen() {
        return isStolen;
    }

    public void setStolen(boolean stolen) {
        isStolen = stolen;
    }

    public Enum<VehicleTypeEnum> getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Enum<VehicleTypeEnum> vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", licencePlate='" + licencePlate + '\'' +
                ", isStolen=" + isStolen +
                ", vehicleType=" + vehicleType +
                ", carTrackers=" + carTrackers +
                ", ownerCredentials=" + ownerCredentials +
                '}';
    }
}
