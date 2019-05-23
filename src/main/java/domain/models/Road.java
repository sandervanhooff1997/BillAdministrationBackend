package domain.models;

public class Road {
    private String name;
    private Price pricePerKilometer;
    private Price rushHourPricePerKilometer;

    public Road() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPricePerKilometer() {
        return pricePerKilometer;
    }

    public void setPricePerKilometer(Price pricePerKilometer) {
        this.pricePerKilometer = pricePerKilometer;
    }

    public Price getRushHourPricePerKilometer() {
        return rushHourPricePerKilometer;
    }

    public void setRushHourPricePerKilometer(Price rushHourPricePerKilometer) {
        this.rushHourPricePerKilometer = rushHourPricePerKilometer;
    }
}
