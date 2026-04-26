package interviewpatterns.behavioral.template;

public final class TemplateDemo {
    public static void main(String[] args) {
        OrderProcessor creditCardProcessor = new CreditCardOrderProcessor();
        OrderProcessor cashOnDeliveryProcessor = new CashOnDeliveryOrderProcessor();

        creditCardProcessor.processOrder();
        System.out.println("---");
        cashOnDeliveryProcessor.processOrder();
    }
}
