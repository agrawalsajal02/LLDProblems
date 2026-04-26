package interviewpatterns.behavioral.strategy;

public final class StrategyDemo {
    public static void main(String[] args) {
        CheckoutService service = new CheckoutService();
        service.setPricingStrategy(new FlatPricing());
        System.out.println(service.checkout(100));

        service.setPricingStrategy(new SurgePricing(1.5));
        System.out.println(service.checkout(100));
    }
}
