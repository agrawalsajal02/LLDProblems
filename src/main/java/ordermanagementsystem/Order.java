package ordermanagementsystem;

public final class Order {
    private final String id;
    private final User user;
    private final Warehouse warehouse;
    private OrderStatus status;

    public Order(String id, User user, Warehouse warehouse) {
        this.id = id;
        this.user = user;
        this.warehouse = warehouse;
        this.status = OrderStatus.CREATED;
    }

    public void confirm() {
        status = OrderStatus.CONFIRMED;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
}
