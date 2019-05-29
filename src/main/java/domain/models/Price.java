package domain.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Price.getById", query = "select b from Price b where b.id = :id"),
        @NamedQuery(name = "Price.getAll", query = "select b from Price b")
})
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue
    private Long id;
    private Double price;
    private Date begin;
    private Date end;

    public Price() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
}
