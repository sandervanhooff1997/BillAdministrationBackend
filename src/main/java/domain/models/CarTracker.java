package domain.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "CarTracker.getById", query = "select ct from CarTracker ct where ct.id = :id"),
        @NamedQuery(name = "CarTracker.getAll", query = "select ct from CarTracker ct"),
        @NamedQuery(name = "CarTracker.getAllNotDeleted", query = "select ct from CarTracker ct where ct.isDeleted = false")
})
@Table(name = "cartrackers")
public class CarTracker implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private int mileage;

    private String hardware;

    private boolean isDeleted;

    public CarTracker() {
    }

    public CarTracker(int mileage, String hardware, boolean isDeleted) {
        this.mileage = mileage;
        this.hardware = hardware;
        this.isDeleted = isDeleted;
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


    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "CarTracker{" +
                "id=" + id +
                ", mileage=" + mileage +
                ", hardware='" + hardware + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
