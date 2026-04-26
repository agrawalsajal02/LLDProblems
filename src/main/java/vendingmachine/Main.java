package vendingmachine;

public final class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(3);
        machine.stock(1, new Item(ItemType.COKE, 12));

        machine.insertCoin(Coin.TEN);
        machine.insertCoin(Coin.TWO);
        Item item = machine.selectAndDispense(1);

        System.out.println("Dispensed item: " + item.getType());
    }
}
