package interviewpatterns.behavioral.template;

public final class CreditCardOrderProcessor extends OrderProcessor {
    @Override
    protected void chargePayment() {
        System.out.println("Charging credit card");
    }

    @Override
    protected void shipOrder() {
        System.out.println("Shipping with standard courier");
    }
}
