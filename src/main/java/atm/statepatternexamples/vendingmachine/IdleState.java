package atm.statepatternexamples.vendingmachine;

public final class IdleState extends VendingMachineState {
    @Override
    public void insertMoney(VendingMachine machine, int amount) {
        // Idle state me machine bas paise accept karti hai aur flow ko next state me bhejti hai.
        machine.addInsertedMoney(amount);
        machine.setCurrentState(new HasMoneyState());
        System.out.println("Inserted money: " + amount);
    }
}
