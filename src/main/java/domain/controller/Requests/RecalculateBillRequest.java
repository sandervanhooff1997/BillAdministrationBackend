package domain.controller.Requests;

import domain.models.Movement;

import java.util.List;

public class RecalculateBillRequest {
    private Long billId;

    private List<Movement> movements;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }
}
