package interviewpatterns.behavioral.statemachine;

public class DeliveredState implements OrderState {
    @Override
    public void pay(Order order) {
        System.out.println("Order already closed");
    }

    @Override
    public void ship(Order order) {
        System.out.println("Order already closed");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order already delivered");
    }
}
