package companiesProblem.uber.lld.ubereats;

import java.math.BigDecimal;

public final class LineItemPriceBreakdown {
    private final String itemName;
    private final int quantity;
    private final BigDecimal baseTotal;
    private final BigDecimal upgradesTotal;
    private final BigDecimal lineTotal;

    public LineItemPriceBreakdown(
        String itemName,
        int quantity,
        BigDecimal baseTotal,
        BigDecimal upgradesTotal,
        BigDecimal lineTotal
    ) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.baseTotal = baseTotal;
        this.upgradesTotal = upgradesTotal;
        this.lineTotal = lineTotal;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getBaseTotal() {
        return baseTotal;
    }

    public BigDecimal getUpgradesTotal() {
        return upgradesTotal;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    @Override
    public String toString() {
        return "LineItemPriceBreakdown{"
            + "itemName='" + itemName + '\''
            + ", quantity=" + quantity
            + ", baseTotal=" + baseTotal
            + ", upgradesTotal=" + upgradesTotal
            + ", lineTotal=" + lineTotal
            + '}';
    }
}
