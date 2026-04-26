package companiesProblem.uber.lld.ubereats;

import java.math.BigDecimal;

public final class TaxBreakdown {
    private final String taxName;
    private final BigDecimal amount;

    public TaxBreakdown(String taxName, BigDecimal amount) {
        this.taxName = taxName;
        this.amount = amount;
    }

    public String getTaxName() {
        return taxName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "TaxBreakdown{"
            + "taxName='" + taxName + '\''
            + ", amount=" + amount
            + '}';
    }
}
