package domain.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarMovements {
    private String licencePlate;
    private List<Movement>  movements;
    private Map<String, List<Movement>> monthMovements;

    public CarMovements() {}

    public CarMovements(String licencePlate) {
        this.licencePlate = licencePlate;
        movements = new ArrayList<>(  );
        monthMovements = new HashMap<>(  );
    }

    public Map<String, List<Movement>> getMonthMovements() {
        return monthMovements;
    }

    public void setMonthMovements(Map<String, List<Movement>> monthMovements) {
        this.monthMovements = monthMovements;
    }

    public void addMovement(Movement m) {
        movements.add( m );
        addMonthMovement( m );
    }

    private void addMonthMovement(Movement m) {
        List<Movement> movements = monthMovements.get( m.getMonthName() );

        if (movements == null) {
            movements = new ArrayList<>(  );
            monthMovements.put( m.getMonthName(), movements );
        }

        movements.add( m );
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }
}
