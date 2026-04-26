package companiesProblem.uber.lld.ubereats;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public final class Main {
    public static void main(String[] args) {
        MenuItem burger = new MenuItem("I1", "Burger", new BigDecimal("199.00"));
        burger.addUpgrade(new UpgradeOption("U1", "Extra Cheese", new BigDecimal("30.00")));
        burger.addUpgrade(new UpgradeOption("U2", "Bacon", new BigDecimal("50.00")));

        MenuItem fries = new MenuItem("I2", "Fries", new BigDecimal("99.00"));
        fries.addUpgrade(new UpgradeOption("U3", "Large Size", new BigDecimal("20.00")));

        Cart cart = new Cart();
        cart.addItem(new CartItem(burger, 2, Arrays.asList("U1", "U2")));
        cart.addItem(new CartItem(fries, 1, List.of("U3")));

        PricingEngine pricingEngine = new PricingEngine(
            Arrays.asList(
                new PercentageTaxRule("GST", new BigDecimal("5.0")),
                new PercentageTaxRule("City Tax", new BigDecimal("2.5"))
            )
        );

        PricingSummary summary = pricingEngine.price(cart);
        System.out.println(summary);
    }
}
