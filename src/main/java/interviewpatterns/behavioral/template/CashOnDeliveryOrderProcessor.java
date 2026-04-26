package interviewpatterns.behavioral.template;

public final class CashOnDeliveryOrderProcessor extends OrderProcessor {
    @Override
    protected void chargePayment() {
        System.out.println("Marking payment as cash on delivery");
    }

    @Override
    protected void shipOrder() {
        System.out.println("Shipping with cash collection note");
    }
}
