package solidprinciples.ocp.good;

public class NetBankingPayment implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Processing net banking payment");
    }
}
