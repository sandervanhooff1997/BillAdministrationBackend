package domain.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Bill> bills;

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

    public Date getBegin() {
        return begin;
    }

    public String getBeginFormatted () {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return simpleDateFormat.format(begin);
        } catch (NullPointerException ex) {
            return null;
        }
        catch (Exception ex
        ) {
            return begin.toString();
        }
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public String getEndFormatted () {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return simpleDateFormat.format(begin);
        } catch (NullPointerException ex) {
            return null;
        }
        catch (Exception ex
        ) {
            return begin.toString();
        }
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
