package interviewpatterns.structural.facade;

public class PaymentService {
    public boolean charge(String userId, int amount) {
        return userId != null && amount > 0;
    }
}
