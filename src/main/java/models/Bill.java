package models;

import com.sun.istack.internal.NotNull;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import static javax.persistence.EnumType.STRING;


@Entity
@NamedQueries({
        @NamedQuery(name = "Bill.getById", query = "select b from Bill b where b.id = :id"),
        @NamedQuery(name = "Bill.getAll", query = "select b from Bill b")
})
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private CarTracker carTracker;

    @NotNull
    @Enumerated(STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.OPEN;

    private Double totalAmount;

    private Date date;

    public Bill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarTracker getCarTracker() {
        return carTracker;
    }

    public void setCarTracker(CarTracker carTracker) {
        this.carTracker = carTracker;
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

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", carTracker=" + carTracker +
                ", paymentStatus=" + paymentStatus +
                ", totalAmount=" + totalAmount +
                ", date=" + date +
                '}';
    }
}
