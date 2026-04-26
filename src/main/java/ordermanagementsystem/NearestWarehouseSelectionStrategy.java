package ordermanagementsystem;

import java.util.List;

public final class NearestWarehouseSelectionStrategy implements WarehouseSelectionStrategy {
    @Override
    public Warehouse select(User user, Cart cart, List<Warehouse> warehouses) {
        Warehouse best = null;
        int bestDistance = Integer.MAX_VALUE;

        for (Warehouse warehouse : warehouses) {
            if (!warehouse.canFulfill(cart)) {
                continue;
            }
            int distance = cityDistance(user.getAddress().getCity(), warehouse.getAddress().getCity());
            // Greedy strategy: jo fulfill bhi kar sake aur user ke sabse paas ho, us warehouse ko choose karo.
            if (distance < bestDistance) {
                bestDistance = distance;
                best = warehouse;
            }
        }
        if (best == null) {
            throw new IllegalArgumentException("No warehouse can fulfill this cart");
        }
        return best;
    }

    private int cityDistance(String userCity, String warehouseCity) {
        return userCity.equalsIgnoreCase(warehouseCity) ? 0 : 1;
    }
}
