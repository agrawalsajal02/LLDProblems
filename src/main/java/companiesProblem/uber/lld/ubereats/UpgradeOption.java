package companiesProblem.uber.lld.ubereats;

import java.math.BigDecimal;

public final class UpgradeOption {
    private final String upgradeId;
    private final String name;
    private final BigDecimal priceDelta;

    public UpgradeOption(String upgradeId, String name, BigDecimal priceDelta) {
        if (upgradeId == null || upgradeId.isEmpty()) {
            throw new IllegalArgumentException("Upgrade id is required");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Upgrade name is required");
        }
        if (priceDelta == null || priceDelta.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Upgrade price delta must be non-negative");
        }
        this.upgradeId = upgradeId;
        this.name = name;
        this.priceDelta = priceDelta;
    }

    public String getUpgradeId() {
        return upgradeId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceDelta() {
        return priceDelta;
    }
}
