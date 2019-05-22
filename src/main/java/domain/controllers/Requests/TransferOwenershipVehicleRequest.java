package domain.controllers.Requests;

public class TransferOwenershipVehicleRequest {
    private Long vehicleId;

    private Long ocId;

    public TransferOwenershipVehicleRequest() {
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getOcId() {
        return ocId;
    }

    public void setOcId(Long ocId) {
        this.ocId = ocId;
    }
}
