package designpattern.statemachine;

public class HasCoinState implements VendingMachineState {
    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin already inserted");
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Product selected");
        machine.setState(new DispenseState());
    }

    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Select product first");
    }
}
