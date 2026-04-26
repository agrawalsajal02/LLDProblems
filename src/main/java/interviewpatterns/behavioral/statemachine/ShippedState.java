package interviewpatterns.behavioral.statemachine;

public class ShippedState implements OrderState {
    @Override
    public void pay(Order order) {
        System.out.println("Already paid");
    }

    @Override
    public void ship(Order order) {
        System.out.println("Already shipped");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order delivered");
        order.setState(new DeliveredState());
    }
}
