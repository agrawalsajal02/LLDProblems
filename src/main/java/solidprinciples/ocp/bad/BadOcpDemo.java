package solidprinciples.ocp.bad;

public final class BadOcpDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.process("CARD");
        processor.process("UPI");
    }
}
