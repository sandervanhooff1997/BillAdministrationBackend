package models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "CarTracker.getById", query = "select ct from CarTracker ct where ct.id = :id"),
        @NamedQuery(name = "CarTracker.getAll", query = "select ct from CarTracker ct")
})
@Table(name = "cartrackers")
public class CarTracker {

    @Id
    @GeneratedValue
    private Long id;

    private int mileage;

    private String hardware;

    private boolean isDeleted;

    @OneToOne
    private Vehicle vehicle;

    public CarTracker() {
    }

    public CarTracker(int mileage, String hardware, boolean isDeleted, Vehicle vehicle) {
        this.mileage = mileage;
        this.hardware = hardware;
        this.isDeleted = isDeleted;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "CarTracker{" +
                "id=" + id +
                ", Mileage=" + mileage +
                ", hardware='" + hardware + '\'' +
                '}';
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
