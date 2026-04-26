package solidprinciples.ocp.good;

public class CardPayment implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Processing card payment");
    }
}
