package companiesProblem.uber.lld.ubereats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CartItem {
    private final MenuItem menuItem;
    private final int quantity;
    private final List<UpgradeOption> selectedUpgrades;

    public CartItem(MenuItem menuItem, int quantity, List<String> selectedUpgradeIds) {
        if (menuItem == null) {
            throw new IllegalArgumentException("Menu item is required");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        this.menuItem = menuItem;
        this.quantity = quantity;
        this.selectedUpgrades = new ArrayList<>();

        for (String upgradeId : selectedUpgradeIds) {
            UpgradeOption upgrade = menuItem.getUpgradeById(upgradeId);
            if (upgrade == null) {
                throw new IllegalArgumentException("Invalid upgrade " + upgradeId + " for item " + menuItem.getName());
            }
            selectedUpgrades.add(upgrade);
        }
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<UpgradeOption> getSelectedUpgrades() {
        return Collections.unmodifiableList(selectedUpgrades);
    }
}
