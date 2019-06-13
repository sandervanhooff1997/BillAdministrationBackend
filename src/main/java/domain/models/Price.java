package domain.models;

import domain.utils.DateUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Price.getById", query = "select b from Price b where b.id = :id"),
        @NamedQuery(name = "Price.getAll", query = "select b from Price b"),
        @NamedQuery(name = "Price.getDefaultPrice", query = "select p from Price p where p.defaultPrice = true"),
        @NamedQuery(name = "Price.getDefaultRushPrice", query = "select p from Price p where p.defaultRushPrice = true")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Price.getAllUnused", query = "select * from prices p where p.id not in (select prices_id from road_prices)", resultClass = Price.class)
})
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue
    private Long id;
    private Double price;
    private Date begin;
    private Date end;
    private boolean defaultPrice;
    private boolean defaultRushPrice;

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

    public String getBeginFormatted () {
        return DateUtils.getDateFormatted(begin);
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public String getEndFormatted () {
        return DateUtils.getDateFormatted(end);
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(boolean defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public boolean isDefaultRushPrice() {
        return defaultRushPrice;
    }

    public void setDefaultRushPrice(boolean defaultRushPrice) {
        this.defaultRushPrice = defaultRushPrice;
    }
}
