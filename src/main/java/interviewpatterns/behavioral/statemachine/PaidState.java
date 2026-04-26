package interviewpatterns.behavioral.statemachine;

public class PaidState implements OrderState {
    @Override
    public void pay(Order order) {
        System.out.println("Already paid");
    }

    @Override
    public void ship(Order order) {
        System.out.println("Order shipped");
        order.setState(new ShippedState());
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Ship before deliver");
    }
}
