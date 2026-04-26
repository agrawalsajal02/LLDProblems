package solidprinciples.ocp.good;

public class UpiPayment implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Processing UPI payment");
    }
}
