package domain.models;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static javax.persistence.EnumType.STRING;


@Entity
@NamedQueries({
        @NamedQuery(name = "Bill.getById", query = "select b from Bill b where b.id = :id"),
        @NamedQuery(name = "Bill.getAll", query = "select b from Bill b")
})
@Table(name = "bills")
public class Bill implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(targetEntity = CarTracker.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List carTrackers;

    @NotNull
    @Enumerated(STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.OPEN;

    private Double totalAmount;

    private Date date;

    public Bill() {
        carTrackers = new ArrayList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(@NotNull PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMonth() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);

        return String.valueOf( month );
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public List getCarTrackers() {
        return carTrackers;
    }

    public void addCarTracker(CarTracker carTracker) {
        this.carTrackers.add(carTracker);
    }

    public CarTracker getCarTracker() {
        return (CarTracker) carTrackers.get(carTrackers.size()-1);
    }

    public void setCarTrackers(List carTrackers) {
        this.carTrackers = carTrackers;
    }
}
