package designpattern.statemachine;

public class DispenseState implements VendingMachineState {
    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Please wait, dispensing");
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Please wait, dispensing");
    }

    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Dispensing product");
        machine.setState(new NoCoinState());
    }
}
