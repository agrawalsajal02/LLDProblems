package companiesProblem.uber.lld.inventoryManagement;

import java.util.List;

public interface CarSelectionStrategy {
    Car choose(List<Car> availableCars);
}
