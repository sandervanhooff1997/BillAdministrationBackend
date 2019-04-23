package domain.models.Comparators;

import domain.models.Movement;
import java.util.Comparator;

public class MovementComparer implements Comparator<Movement> {
    @Override
    public int compare(Movement x, Movement y) {
        int startComparison = compare(x.getCarTracker().getMileage(), y.getCarTracker().getMileage());
        return startComparison != 0 ? startComparison
                : compare(x.getCarTracker().getMileage(), y.getCarTracker().getMileage());
    }

    // I don't know why this isn't in Long...
    private static int compare(long a, long b) {
        return a < b ? -1
                : a > b ? 1
                : 0;
    }
}
