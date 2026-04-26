package ordermanagementsystem;

import java.util.List;

public final class Main {
    public static void main(String[] args) {
        Product laptop = new Product("P1", "Laptop");
        User user = new User("U1", "Sajal", new Address("Bangalore"));
        user.getCart().add(laptop, 1);

        Warehouse bangaloreWarehouse = new Warehouse("W1", new Address("Bangalore"));
        bangaloreWarehouse.stock(laptop, 2);
        Warehouse delhiWarehouse = new Warehouse("W2", new Address("Delhi"));
        delhiWarehouse.stock(laptop, 5);

        OrderService orderService = new OrderService(List.of(bangaloreWarehouse, delhiWarehouse));
        Order order = orderService.placeOrder("O1", user, new NearestWarehouseSelectionStrategy());

        System.out.println("Order status: " + order.getStatus());
        System.out.println("Warehouse used: " + order.getWarehouse().getId());
    }
}
