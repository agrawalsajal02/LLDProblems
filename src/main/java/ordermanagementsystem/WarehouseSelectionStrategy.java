package ordermanagementsystem;

import java.util.List;

public interface WarehouseSelectionStrategy {
    Warehouse select(User user, Cart cart, List<Warehouse> warehouses);
}
