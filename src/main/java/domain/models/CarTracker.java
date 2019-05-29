package domain.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "CarTracker.getById", query = "select ct from CarTracker ct where ct.id = :id"),
        @NamedQuery(name = "CarTracker.getAll", query = "select ct from CarTracker ct")
})
@Table(name = "cartrackers")
public class CarTracker implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private int mileage;

    private String hardware;

    private boolean isDeleted;

    private Date deletedOn;

    @Transient
    private List movements;

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

    public List getMovements() {
        return movements;
    }

    public void setMovements(List movements) {
        this.movements = movements;
    }

    public Date getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
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
