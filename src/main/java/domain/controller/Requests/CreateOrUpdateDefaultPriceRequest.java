package domain.controller.Requests;

public class CreateOrUpdateDefaultPriceRequest {
    private double price;

    private boolean rushPrice;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRushPrice() {
        return rushPrice;
    }

    public void setRushPrice(boolean rushPrice) {
        this.rushPrice = rushPrice;
    }
}
