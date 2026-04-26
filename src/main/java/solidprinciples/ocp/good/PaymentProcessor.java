package solidprinciples.ocp.good;

public class PaymentProcessor {
    public void process(PaymentMethod paymentMethod) {
        // Hinglish: processor ko payment ke internal type se matlab nahi, bas contract follow hona chahiye.
        paymentMethod.pay();
    }
}
