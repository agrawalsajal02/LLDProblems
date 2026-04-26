package solidprinciples.ocp.good;

public final class GoodOcpDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.process(new CardPayment());
        processor.process(new UpiPayment());
        processor.process(new NetBankingPayment());
    }
}
