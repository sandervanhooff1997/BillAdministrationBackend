package domain.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Road.getById", query = "select rc from Road rc where rc.id = :id"),
        @NamedQuery(name = "Road.getAll", query = "select b from Road b")
})
@Table(name = "roads")
public class Road {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(targetEntity = Price.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "road_prices")
    private List<Price> prices;

    @OneToMany(targetEntity = Price.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "road_rush_prices")
    private List<Price> rushPrices;

    public Road() {
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
        prices.add(p);
    }

    public Price getRushPrice() {
        return rushPrices.get(rushPrices.size()-1);
    }

    public void addRushPrice(Price p){
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
       return getMovementPriceGeneric(d, getPrices());
    }

    public Price getMovementRushHourPrice(Date d) {
        return getMovementPriceGeneric(d, getRushPrices());
    }

    private Price getMovementPriceGeneric(Date d, List<Price> prices) {
        for (Price p : prices) {
            // if begin is set and d after begin
            if (p.getBegin() != null && p.getBegin().before(d)) {
                // if no end or between dates
                if (p.getEnd() == null || p.getEnd().after(d)) return p;
            }
        }

        return null;
    }


}
