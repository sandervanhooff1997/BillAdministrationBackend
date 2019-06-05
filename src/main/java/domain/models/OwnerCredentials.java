package domain.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "OwnerCredentials.getById", query = "select oc from OwnerCredentials oc where oc.id = :id"),
        @NamedQuery(name = "OwnerCredentials.getByBsnAndPostalCode", query = "select oc from OwnerCredentials oc where oc.bsn = :bsn AND oc.postalCode = :postalCode"),
        @NamedQuery(name = "OwnerCredentials.getByBsn", query = "select oc from OwnerCredentials oc where oc.bsn = :bsn"),
        @NamedQuery(name = "OwnerCredentials.getAll", query = "select oc from OwnerCredentials oc")
})
@Table(name = "ownercredentials")
public class OwnerCredentials  implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    private Long bsn;

    private boolean isAccountRider;

    private String city;

    private String postalCode;

    private String streetName;

    private int houseNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Bill> bills;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Vehicle> vehicles;

    public OwnerCredentials() {
        bills = new ArrayList<>();
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

    public Long getBsn() {
        return bsn;
    }

    public void setBsn(Long bsn) {
        this.bsn = bsn;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
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
                '}';
    }
}
