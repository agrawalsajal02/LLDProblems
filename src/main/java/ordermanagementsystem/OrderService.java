package ordermanagementsystem;

import java.util.List;

public final class OrderService {
    private final List<Warehouse> warehouses;

    public OrderService(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Order placeOrder(String orderId, User user, WarehouseSelectionStrategy strategy) {
        Warehouse warehouse = strategy.select(user, user.getCart(), warehouses);
        warehouse.reserve(user.getCart());
        Order order = new Order(orderId, user, warehouse);
        order.confirm();
        return order;
    }
}
