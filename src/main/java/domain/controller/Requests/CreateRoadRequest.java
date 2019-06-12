package domain.controller.Requests;

public class CreateRoadRequest {
    private String name;

    private Long priceId;

    private Long rushHourPriceId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public Long getRushHourPriceId() {
        return rushHourPriceId;
    }

    public void setRushHourPriceId(Long rushHourPriceId) {
        this.rushHourPriceId = rushHourPriceId;
    }
}
