package domain.controller.Requests;

import domain.enums.VehicleType;

public class CreateVehicleRequest {
    private String licencePlate;

    private Long carTrackerId;

    private VehicleType vehicleType;

    private Long ownerCredentialsId;

    public Long getOwnerCredentialsId() {
        return ownerCredentialsId;
    }

    public void setOwnerCredentialsId(Long ownerCredentialsId) {
        this.ownerCredentialsId = ownerCredentialsId;
    }

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

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
