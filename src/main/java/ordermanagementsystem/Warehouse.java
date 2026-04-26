package ordermanagementsystem;

import java.util.HashMap;
import java.util.Map;

public final class Warehouse {
    private final String id;
    private final Address address;
    private final Map<Product, Integer> inventory = new HashMap<>();

    public Warehouse(String id, Address address) {
        this.id = id;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void stock(Product product, int quantity) {
        inventory.merge(product, quantity, Integer::sum);
    }

    public boolean canFulfill(Cart cart) {
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            if (inventory.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public void reserve(Cart cart) {
        if (!canFulfill(cart)) {
            throw new IllegalArgumentException("Warehouse cannot fulfill cart");
        }
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            inventory.put(entry.getKey(), inventory.get(entry.getKey()) - entry.getValue());
        }
    }
}
