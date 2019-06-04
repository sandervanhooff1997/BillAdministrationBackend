package domain.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Price.getById", query = "select b from Price b where b.id = :id"),
        @NamedQuery(name = "Price.getAll", query = "select b from Price b")
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
            return simpleDateFormat.format(end);
        } catch (NullPointerException ex) {
            return null;
        }
        catch (Exception ex
        ) {
            return end.toString();
        }
    }


    public void setEnd(Date end) {
        this.end = end;
    }
}
