package domain.models;

import domain.utils.DateUtils;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "OwnershipHistory.getById", query = "select b from OwnershipHistory b where b.id = :id"),
        @NamedQuery(name = "OwnershipHistory.getAll", query = "select b from OwnershipHistory b"),
        @NamedQuery(name = "OwnershipHistory.getAllByLicensePlate", query = "select b from OwnershipHistory b where b.vehicle.licencePlate = :licensePlate"),
        @NamedQuery(name = "OwnershipHistory.getCurrentOwnerHistory", query = "select b from OwnershipHistory b where b.vehicle.licencePlate = :licensePlate order by b.begin desc")
})
@Table(name = "ownershiphistory")
public class OwnershipHistory implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private OwnerCredentials ownerCredentials;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    @CreationTimestamp
    private Date begin;

    private Date end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OwnerCredentials getOwnerCredentials() {
        return ownerCredentials;
    }

    public void setOwnerCredentials(OwnerCredentials ownerCredentials) {
        this.ownerCredentials = ownerCredentials;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getBegin() {
        return begin;
    }

    public String getBeginFormatted() {
        return DateUtils.getDateFormatted(begin);
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public String getEndFormatted() {
        return DateUtils.getDateFormatted(end);
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "OwnershipHistory{" +
                "id=" + id +
                ", ownerCredentials=" + ownerCredentials +
                ", vehicle=" + vehicle +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
