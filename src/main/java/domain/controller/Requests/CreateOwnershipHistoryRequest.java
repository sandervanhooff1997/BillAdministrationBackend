package domain.controller.Requests;

public class CreateOwnershipHistoryRequest {
    private Long vehicleId;

    private Long ownerCredentialsId;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getOwnerCredentialsId() {
        return ownerCredentialsId;
    }

    public void setOwnerCredentialsId(Long ownerCredentialsId) {
        this.ownerCredentialsId = ownerCredentialsId;
    }
}
