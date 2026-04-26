package companiesProblem.uber.lld.inventoryManagement;

import java.util.Comparator;
import java.util.List;

public final class LowestPriceCarSelectionStrategy implements CarSelectionStrategy {
    @Override
    public Car choose(List<Car> availableCars) {
        return availableCars.stream()
            .min(Comparator.comparing(Car::getHourlyRate).thenComparing(Car::getCarId))
            .orElse(null);
    }
}
