package companiesProblem.uber.lld.ubereats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cart {
    private final List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem cartItem) {
        items.add(cartItem);
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
