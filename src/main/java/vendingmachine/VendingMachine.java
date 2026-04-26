package vendingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class VendingMachine {
    private final Map<Integer, Slot> slots = new HashMap<>();
    private final List<Coin> insertedCoins = new ArrayList<>();
    private MachineState state = MachineState.IDLE;

    public VendingMachine(int slotCount) {
        for (int i = 1; i <= slotCount; i++) {
            slots.put(i, new Slot(i));
        }
    }

    public void stock(int slotCode, Item item) {
        slots.get(slotCode).stock(item);
    }

    public void insertCoin(Coin coin) {
        insertedCoins.add(coin);
        state = MachineState.HAS_MONEY;
    }

    public Item selectAndDispense(int slotCode) {
        Slot slot = slots.get(slotCode);
        if (slot == null || slot.isSoldOut()) {
            throw new IllegalArgumentException("Selected slot is empty");
        }
        int insertedAmount = getInsertedAmount();
        if (insertedAmount < slot.getItem().getPrice()) {
            throw new IllegalArgumentException("Insufficient amount inserted");
        }

        state = MachineState.ITEM_SELECTED;
        Item dispensedItem = slot.getItem();
        int change = insertedAmount - dispensedItem.getPrice();
        slot.markSoldOut();
        insertedCoins.clear();
        state = MachineState.IDLE;

        // Change calculation yahan simple subtraction hai kyunki interview focus flow par hai, exact coin-return optimization par nahi.
        System.out.println("Return change: " + change);
        return dispensedItem;
    }

    public int refund() {
        int refundAmount = getInsertedAmount();
        insertedCoins.clear();
        state = MachineState.IDLE;
        return refundAmount;
    }

    private int getInsertedAmount() {
        int total = 0;
        for (Coin coin : insertedCoins) {
            total += coin.getValue();
        }
        return total;
    }
}
