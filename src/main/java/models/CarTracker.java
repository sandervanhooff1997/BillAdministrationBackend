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

    @Positive
    private int kilometers;

    private String hardware;

    private boolean isDeleted;

    public CarTracker() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    @Override
    public String toString() {
        return "CarTracker{" +
                "id=" + id +
                ", kilometers=" + kilometers +
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
