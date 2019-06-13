package domain.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Region.getById", query = "select r from Region r where r.id = :id"),
        @NamedQuery(name = "Region.getAll", query = "select r from Region r")
})
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Polygon point;

    @OneToMany(targetEntity = Price.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "region_prices")
    private List<Price> prices;

    @OneToMany(targetEntity = Price.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "region_rush_prices")
    private List<Price> rushPrices;

    public Region() {
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

    public List<Price> getPrices() {
        return prices;
    }

    public Price getPrice() {
        return prices.get(prices.size()-1);
    }

    public void addPrice(Price p){
        if (prices == null) prices = new ArrayList<>();
        prices.add(p);
    }

    public Price getRushPrice() {
        return rushPrices.get(rushPrices.size()-1);
    }

    public void addRushPrice(Price p){
        if (rushPrices == null) rushPrices = new ArrayList<>();
        rushPrices.add(p);
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<Price> getRushPrices() {
        return rushPrices;
    }

    public void setRushPrices(List<Price> rushPrices) {
        this.rushPrices = rushPrices;
    }

    public Price getMovementPrice(Date d) {
       return getMovementPrice(d, getPrices());
    }

    public Price getMovementRushHourPrice(Date d) {
        return getMovementPrice(d, getRushPrices());
    }

    private Price getMovementPrice(Date d, List<Price> prices) {
        for (Price p : prices) {
            // if begin is set and d after begin
            if (p.getBegin() != null && p.getBegin().before(d)) {
                // if no end or between dates
                if (p.getEnd() == null || p.getEnd().after(d)) return p;
            }
        }

        return null;
    }

    public Polygon getPoint() {
        return point;
    }

    public void setPoint(Polygon point) {
        this.point = point;
    }
}
