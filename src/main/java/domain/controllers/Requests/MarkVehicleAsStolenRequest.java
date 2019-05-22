package domain.controllers.Requests;

public class MarkVehicleAsStolenRequest {
    private String licencePlate;

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

}
