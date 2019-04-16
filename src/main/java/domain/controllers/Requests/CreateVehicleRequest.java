package domain.controllers.Requests;

public class CreateVehicleRequest {
    private String licencePlate;

    private Long carTrackerId;

    private Long rateCategoryId;

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Long getCarTrackerId() {
        return carTrackerId;
    }

    public void setCarTrackerId(Long carTrackerId) {
        this.carTrackerId = carTrackerId;
    }

    public Long getRateCategoryId() {
        return rateCategoryId;
    }

    public void setRateCategoryId(Long rateCategoryId) {
        this.rateCategoryId = rateCategoryId;
    }
}
