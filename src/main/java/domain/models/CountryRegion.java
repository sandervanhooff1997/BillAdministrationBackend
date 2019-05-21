package domain.models;

import com.snatik.polygon.Polygon;

public class CountryRegion {

    //https://github.com/sromku/polygon-contains-point
    private Polygon polygon;
    private Double price;
    private String name;

    public CountryRegion() {
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}