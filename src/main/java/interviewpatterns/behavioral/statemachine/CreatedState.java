package interviewpatterns.behavioral.statemachine;

public class CreatedState implements OrderState {
    @Override
    public void pay(Order order) {
        System.out.println("Payment accepted");
        order.setState(new PaidState());
    }

    @Override
    public void ship(Order order) {
        System.out.println("Pay before shipping");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order not shipped yet");
    }
}
