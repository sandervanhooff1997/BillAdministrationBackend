package domain.models;

import java.util.ArrayList;
import java.util.List;

public class CarMovements {
    private Vehicle vehicle;
    private OwnerCredentials ownerCredentials;
    private List<Movement>  movements;

    public CarMovements() {
    }

    public CarMovements(Vehicle v, OwnerCredentials oc) {
        this.vehicle = v;
        this.ownerCredentials = oc;
        this.movements = new ArrayList<>();
    }

    public void addMovement(Movement m) {
        movements.add( m );
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    public OwnerCredentials getOwnerCredentials() {
        return ownerCredentials;
    }

    public void setOwnerCredentials(OwnerCredentials ownerCredentials) {
        this.ownerCredentials = ownerCredentials;
    }

    @Override
    public String toString() {
        return "CarMovements{" +
                "vehicle=" + vehicle +
                ", ownerCredentials=" + ownerCredentials +
                ", movements=" + movements +
                '}';
    }
}
