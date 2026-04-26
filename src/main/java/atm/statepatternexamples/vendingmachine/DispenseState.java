package atm.statepatternexamples.vendingmachine;

public final class DispenseState extends VendingMachineState {
    @Override
    public void dispense(VendingMachine machine) {
        Item item = machine.getSelectedItem();
        int change = machine.getInsertedMoney() - item.getPrice();
        System.out.println("Dispensed: " + item.getName());
        System.out.println("Returned change: " + change);
        machine.clearTransaction();
        machine.setCurrentState(new IdleState());
    }
}
