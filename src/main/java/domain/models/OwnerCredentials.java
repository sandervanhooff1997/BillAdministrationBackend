package domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.Positive;

@Entity
@NamedQueries({
        @NamedQuery(name = "OwnerCredentials.getById", query = "select oc from OwnerCredentials oc where oc.id = :id"),
        @NamedQuery(name = "OwnerCredentials.getAll", query = "select oc from OwnerCredentials oc")
})
@Table(name = "ownercredentials")
public class OwnerCredentials  implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private boolean isAccountRider;

    private String city;

    private String postalCode;

    private String streetName;

    private int houseNumber;

    private Date begin;

    private Date end;

    public OwnerCredentials() {
    }

    public String getAddress () {
        return this.streetName + " " + this.houseNumber + ", " + postalCode + ", " + this.city;
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

    public boolean isAccountRider() {
        return isAccountRider;
    }

    public void setAccountRider(boolean accountRider) {
        isAccountRider = accountRider;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "OwnerCredentials{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isAccountRider=" + isAccountRider +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
