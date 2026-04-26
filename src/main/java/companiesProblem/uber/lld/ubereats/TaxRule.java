package companiesProblem.uber.lld.ubereats;

import java.math.BigDecimal;

public interface TaxRule {
    String name();

    BigDecimal calculateTax(BigDecimal subtotal, Cart cart);
}
