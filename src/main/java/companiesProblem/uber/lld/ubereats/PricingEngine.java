package companiesProblem.uber.lld.ubereats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public final class PricingEngine {
    private static final int MONEY_SCALE = 2;

    private final List<TaxRule> taxRules;

    public PricingEngine(List<TaxRule> taxRules) {
        this.taxRules = new ArrayList<>(taxRules);
    }

    public PricingSummary price(Cart cart) {
        List<LineItemPriceBreakdown> lineItems = new ArrayList<>();
        BigDecimal rawSubtotal = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            BigDecimal unitBasePrice = cartItem.getMenuItem().getBasePrice();
            BigDecimal rawBaseTotal = unitBasePrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));

            BigDecimal upgradesPerUnit = BigDecimal.ZERO;
            for (UpgradeOption upgrade : cartItem.getSelectedUpgrades()) {
                upgradesPerUnit = upgradesPerUnit.add(upgrade.getPriceDelta());
            }

            BigDecimal rawUpgradesTotal = upgradesPerUnit.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            BigDecimal rawLineTotal = rawBaseTotal.add(rawUpgradesTotal);
            BigDecimal displayedLineTotal = roundMoney(rawLineTotal);

            lineItems.add(
                new LineItemPriceBreakdown(
                    cartItem.getMenuItem().getName(),
                    cartItem.getQuantity(),
                    roundMoney(rawBaseTotal),
                    roundMoney(rawUpgradesTotal),
                    displayedLineTotal
                )
            );
            rawSubtotal = rawSubtotal.add(rawLineTotal);
        }

        BigDecimal subtotal = roundMoney(rawSubtotal);

        List<TaxBreakdown> taxes = new ArrayList<>();
        BigDecimal totalTax = BigDecimal.ZERO;
        for (TaxRule taxRule : taxRules) {
            BigDecimal taxAmount = roundMoney(taxRule.calculateTax(subtotal, cart));
            taxes.add(new TaxBreakdown(taxRule.name(), taxAmount));
            totalTax = totalTax.add(taxAmount);
        }

        BigDecimal roundedTotalTax = roundMoney(totalTax);
        BigDecimal finalTotal = roundMoney(subtotal.add(roundedTotalTax));

        return new PricingSummary(lineItems, taxes, subtotal, roundedTotalTax, finalTotal);
    }

    private BigDecimal roundMoney(BigDecimal amount) {
        return amount.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }
}

//
//Yeah — fair pushback. We are still calling roundMoney(...) multiple times, and the reason is:
//
//not for math necessity everywhere, but for output consistency of each money field.
//
//Short answer
//I am rounding again and again because these are different billable/display fields:
//
//base total
//upgrades total
//line total
//subtotal
//each tax amount
//total tax
//final total

