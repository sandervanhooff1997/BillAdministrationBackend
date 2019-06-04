package domain.models;

import com.sun.istack.internal.NotNull;
import domain.enums.VehicleType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@NamedQueries({
        @NamedQuery(name = "Vehicle.getById", query = "select v from Vehicle v where v.id = :id"),
        @NamedQuery(name = "Vehicle.getAll", query = "select v from Vehicle v")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Vehicle.getByOwnerCredentialsId", query = "select * from vehicles v where v.id in (select Vehicle_id from vehicles_ownercredentials where ownerCredentials_id = :id)", resultClass = Vehicle.class)
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

    @OneToMany(targetEntity = OwnerCredentials.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OwnerCredentials> ownerCredentials;

    @NotNull
    @Enumerated(STRING)
    @Column(nullable = false)
    private VehicleType vehicleType = VehicleType.COMBUSTION;

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

    public OwnerCredentials getOwnerCredential() {
        if (ownerCredentials.size() == 0) return null;

        return ownerCredentials.get(ownerCredentials.size()-1);
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

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public OwnerCredentials getOwnerCredentialsOnDate(Date d){
        for (OwnerCredentials oc : ownerCredentials) {
            // if begin is set and d after begin
            if (oc.getBegin() != null && oc.getBegin().before(d)) {
                // if no end or between dates
                if (oc.getEnd() == null || oc.getEnd().after(d)) return oc;
            }
        }

        return null;
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
