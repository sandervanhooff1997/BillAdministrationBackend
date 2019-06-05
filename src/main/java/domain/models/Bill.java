package domain.models;

import com.sun.istack.internal.NotNull;
import domain.models.enumerators.PaymentStatus;
import domain.utils.DateUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private int monthIndex;

    private String monthName;

    @ManyToOne(cascade = CascadeType.ALL)
    private OwnerCredentials ownerCredentials;

    @CreationTimestamp
    private Date createDate;

    public Bill() {
        carTrackers = new ArrayList();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getCreateDateFormatted () {
        return DateUtils.getDateFormatted(createDate);
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public void setMonth(int index) {
        this.monthIndex = index;
        this.monthName = new DateFormatSymbols().getMonths()[index];
    }

    public String getMonthName() {
        return monthName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void addTotalAmount(Double totalAmount) {
        this.totalAmount += totalAmount;
    }

    public int getMonthIndex() {
        return monthIndex;
    }

    public void setMonthIndex(int monthIndex) {
        this.monthIndex = monthIndex;
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

    public OwnerCredentials getOwnerCredentials() {
        return ownerCredentials;
    }

    public void setOwnerCredentials(OwnerCredentials ownerCredentials) {
        this.ownerCredentials = ownerCredentials;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", carTrackers=" + carTrackers +
                ", paymentStatus=" + paymentStatus +
                ", totalAmount=" + totalAmount +
                ", month='" + monthIndex + '\'' +
                '}';
    }
}
