package atm.statepatternexamples.vendingmachine;

public final class HasMoneyState extends VendingMachineState {
    @Override
    public void insertMoney(VendingMachine machine, int amount) {
        machine.addInsertedMoney(amount);
        System.out.println("Extra money inserted: " + amount);
    }

    @Override
    public void selectItem(VendingMachine machine, Item item) {
        if (machine.getInsertedMoney() < item.getPrice()) {
            throw new IllegalArgumentException("Insufficient money for item " + item.getName());
        }
        machine.setSelectedItem(item);
        machine.setCurrentState(new DispenseState());
        System.out.println("Selected item: " + item.getName());
    }
}
