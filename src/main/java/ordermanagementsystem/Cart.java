package ordermanagementsystem;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Cart {
    private final Map<Product, Integer> items = new LinkedHashMap<>();

    public void add(Product product, int quantity) {
        items.merge(product, quantity, Integer::sum);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
}
