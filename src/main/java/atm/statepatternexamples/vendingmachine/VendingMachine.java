package atm.statepatternexamples.vendingmachine;

public final class VendingMachine {
    private VendingMachineState currentState;
    private Item selectedItem;
    private int insertedMoney;

    public VendingMachine() {
        this.currentState = new IdleState();
    }

    public void insertMoney(int amount) {
        currentState.insertMoney(this, amount);
    }

    public void selectItem(Item item) {
        currentState.selectItem(this, item);
    }

    public void dispense() {
        currentState.dispense(this);
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getInsertedMoney() {
        return insertedMoney;
    }

    public void addInsertedMoney(int amount) {
        insertedMoney += amount;
    }

    public void clearTransaction() {
        selectedItem = null;
        insertedMoney = 0;
    }
}
