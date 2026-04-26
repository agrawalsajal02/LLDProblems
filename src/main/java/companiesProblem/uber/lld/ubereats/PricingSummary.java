package companiesProblem.uber.lld.ubereats;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PricingSummary {
    private final List<LineItemPriceBreakdown> lineItems;
    private final List<TaxBreakdown> taxes;
    private final BigDecimal subtotal;
    private final BigDecimal totalTax;
    private final BigDecimal finalTotal;

    public PricingSummary(
        List<LineItemPriceBreakdown> lineItems,
        List<TaxBreakdown> taxes,
        BigDecimal subtotal,
        BigDecimal totalTax,
        BigDecimal finalTotal
    ) {
        this.lineItems = new ArrayList<>(lineItems);
        this.taxes = new ArrayList<>(taxes);
        this.subtotal = subtotal;
        this.totalTax = totalTax;
        this.finalTotal = finalTotal;
    }

    public List<LineItemPriceBreakdown> getLineItems() {
        return Collections.unmodifiableList(lineItems);
    }

    public List<TaxBreakdown> getTaxes() {
        return Collections.unmodifiableList(taxes);
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public BigDecimal getFinalTotal() {
        return finalTotal;
    }

    @Override
    public String toString() {
        return "PricingSummary{"
            + "lineItems=" + lineItems
            + ", taxes=" + taxes
            + ", subtotal=" + subtotal
            + ", totalTax=" + totalTax
            + ", finalTotal=" + finalTotal
            + '}';
    }
}
