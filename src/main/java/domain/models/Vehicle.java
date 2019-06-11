package domain.models;

import com.sun.istack.internal.NotNull;
import domain.enums.VehicleType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static javax.persistence.EnumType.STRING;

@Entity
@NamedQueries({
        @NamedQuery(name = "Vehicle.getById", query = "select v from Vehicle v where v.id = :id"),
        @NamedQuery(name = "Vehicle.getAll", query = "select v from Vehicle v")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Vehicle.getByOwnerCredentialsId", query = "select * from vehicles v where v.ownerCredentials_id = :id", resultClass = Vehicle.class)
})
@Table(name = "vehicles")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String licencePlate;

    private boolean isStolen;

    @OneToMany(targetEntity = CarTracker.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CarTracker> carTrackers;

    @ManyToOne(cascade = CascadeType.ALL)
    private OwnerCredentials ownerCredentials;

    @NotNull
    @Enumerated(STRING)
    @Column(nullable = false)
    private VehicleType vehicleType = VehicleType.COMBUSTION;

    public Vehicle() {
        carTrackers = new ArrayList<>();
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

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public OwnerCredentials getOwnerCredentials() {
        return ownerCredentials;
    }

    public void setOwnerCredentials(OwnerCredentials ownerCredentials) {
        this.ownerCredentials = ownerCredentials;
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
