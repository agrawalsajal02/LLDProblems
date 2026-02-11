package designpattern.statemachine;

public class VendingMachine {
    private VendingMachineState currentState;

    public VendingMachine() {
        this.currentState = new NoCoinState();
    }

    public void insertCoin() {
        currentState.insertCoin(this);
    }

    public void selectProduct() {
        currentState.selectProduct(this);
    }

    public void dispense() {
        currentState.dispense(this);
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }
}
