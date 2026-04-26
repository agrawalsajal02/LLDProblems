package interviewpatterns.behavioral.statemachine;

public final class StateMachineDemo {
    public static void main(String[] args) {
        Order order = new Order();
        order.ship();
        order.pay();
        order.ship();
        order.deliver();
    }
}
