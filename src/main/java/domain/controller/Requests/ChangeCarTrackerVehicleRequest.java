package domain.controller.Requests;

public class ChangeCarTrackerVehicleRequest {
    private Long vehicleId;

    private Long ctId;

    public ChangeCarTrackerVehicleRequest() {
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCtId() {
        return ctId;
    }

    public void setCtId(Long ctId) {
        this.ctId = ctId;
    }
}
