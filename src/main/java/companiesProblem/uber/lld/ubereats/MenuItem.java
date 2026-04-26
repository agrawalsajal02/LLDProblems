package companiesProblem.uber.lld.ubereats;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class MenuItem {
    private final String itemId;
    private final String name;
    private final BigDecimal basePrice;
    private final Map<String, UpgradeOption> upgradesById;

    public MenuItem(String itemId, String name, BigDecimal basePrice) {
        if (itemId == null || itemId.isEmpty()) {
            throw new IllegalArgumentException("Item id is required");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Item name is required");
        }
        if (basePrice == null || basePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Base price must be non-negative");
        }
        this.itemId = itemId;
        this.name = name;
        this.basePrice = basePrice;
        this.upgradesById = new HashMap<>();
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void addUpgrade(UpgradeOption upgradeOption) {
        upgradesById.put(upgradeOption.getUpgradeId(), upgradeOption);
    }

    public UpgradeOption getUpgradeById(String upgradeId) {
        return upgradesById.get(upgradeId);
    }

    public Map<String, UpgradeOption> getUpgradesById() {
        return Collections.unmodifiableMap(upgradesById);
    }
}
