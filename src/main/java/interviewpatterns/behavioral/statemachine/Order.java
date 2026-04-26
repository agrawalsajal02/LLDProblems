package interviewpatterns.behavioral.statemachine;

public class Order {
    private OrderState state;

    public Order() {
        this.state = new CreatedState();
    }

    public void pay() {
        state.pay(this);
    }

    public void ship() {
        state.ship(this);
    }

    public void deliver() {
        state.deliver(this);
    }

    void setState(OrderState state) {
        this.state = state;
    }
}
