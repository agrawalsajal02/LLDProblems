package interviewpatterns.behavioral.strategy;

public class CheckoutService {
    private PricingStrategy pricingStrategy;

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public int checkout(int basePrice) {
        if (pricingStrategy == null) {
            throw new IllegalStateException("Pricing strategy not set");
        }
        return pricingStrategy.calculatePrice(basePrice);
    }
}
