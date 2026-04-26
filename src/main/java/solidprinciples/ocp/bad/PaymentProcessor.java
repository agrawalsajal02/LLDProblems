package solidprinciples.ocp.bad;

public class PaymentProcessor {
    public void process(String paymentType) {
        if ("CARD".equalsIgnoreCase(paymentType)) {
            System.out.println("Processing card payment");
        } else if ("UPI".equalsIgnoreCase(paymentType)) {
            System.out.println("Processing UPI payment");
        } else if ("NETBANKING".equalsIgnoreCase(paymentType)) {
            System.out.println("Processing net banking payment");
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }
    }
}
