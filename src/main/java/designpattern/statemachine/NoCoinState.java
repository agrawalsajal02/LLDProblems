package designpattern.statemachine;

public class NoCoinState implements VendingMachineState {
    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin inserted");
        machine.setState(new HasCoinState());
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Insert coin first");
    }

    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Insert coin first");
    }
}
