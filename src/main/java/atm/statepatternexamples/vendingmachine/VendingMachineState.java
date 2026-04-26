package atm.statepatternexamples.vendingmachine;

public abstract class VendingMachineState {
    public void insertMoney(VendingMachine machine, int amount) {
        throw new IllegalStateException("Insert money not allowed in " + getClass().getSimpleName());
    }

    public void selectItem(VendingMachine machine, Item item) {
        throw new IllegalStateException("Select item not allowed in " + getClass().getSimpleName());
    }

    public void dispense(VendingMachine machine) {
        throw new IllegalStateException("Dispense not allowed in " + getClass().getSimpleName());
    }
}
