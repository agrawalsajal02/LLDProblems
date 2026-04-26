package interviewpatterns.behavioral.strategy;

public class SurgePricing implements PricingStrategy {
    private final double multiplier;

    public SurgePricing(double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public int calculatePrice(int basePrice) {
        return (int) Math.round(basePrice * multiplier);
    }
}
