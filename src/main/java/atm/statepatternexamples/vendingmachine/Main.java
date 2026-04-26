package atm.statepatternexamples.vendingmachine;

public final class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        Item coke = new Item("Coke", 20);

        machine.insertMoney(10);
        machine.insertMoney(10);
        machine.selectItem(coke);
        machine.dispense();
    }
}
