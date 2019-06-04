package domain.controllers.Requests;

public class AddPriceToRoadRequest {
    private Long roadId;

    private Long priceId;


    public Long getRoadId() {
        return roadId;
    }

    public void setRoadId(Long roadId) {
        this.roadId = roadId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

}
