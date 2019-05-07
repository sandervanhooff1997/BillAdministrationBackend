package domain.models;

import com.snatik.polygon.Polygon;

public class CountryRegion {

    private Polygon polygon;
    private RateCategory rateCategory;
    private String name;

    public CountryRegion() {
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public RateCategory getRateCategory() {
        return rateCategory;
    }

    public void setRateCategory(RateCategory rateCategory) {
        this.rateCategory = rateCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
