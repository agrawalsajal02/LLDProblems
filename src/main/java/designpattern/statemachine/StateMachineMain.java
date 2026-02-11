package designpattern.statemachine;

public final class StateMachineMain {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        machine.selectProduct();
        machine.insertCoin();
        machine.selectProduct();
        machine.dispense();
    }
}
