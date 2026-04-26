package interviewpatterns.behavioral.strategy;

public class FlatPricing implements PricingStrategy {
    @Override
    public int calculatePrice(int basePrice) {
        return basePrice;
    }
}
