package interviewpatterns.behavioral.template;

public abstract class OrderProcessor {
    public final void processOrder() {
        validateOrder();
        chargePayment();
        shipOrder();
        sendConfirmation();
    }

    protected void validateOrder() {
        System.out.println("Validating order");
    }

    protected abstract void chargePayment();

    protected abstract void shipOrder();

    protected void sendConfirmation() {
        System.out.println("Sending confirmation");
    }
}
