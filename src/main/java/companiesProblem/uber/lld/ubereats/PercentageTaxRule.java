package companiesProblem.uber.lld.ubereats;

import java.math.BigDecimal;
public final class PercentageTaxRule implements TaxRule {
    private final String name;
    private final BigDecimal percentage;

    public PercentageTaxRule(String name, BigDecimal percentage) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Tax name is required");
        }
        if (percentage == null || percentage.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Tax percentage must be non-negative");
        }
        this.name = name;
        this.percentage = percentage;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public BigDecimal calculateTax(BigDecimal subtotal, Cart cart) {
        return subtotal.multiply(percentage.movePointLeft(2));
    }
}
