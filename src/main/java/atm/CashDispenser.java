package atm;

public final class CashDispenser {
    public WithdrawalBreakdown dispense(CashInventory inventory, int amount) {
        if (amount <= 0 || amount % 100 != 0) {
            throw new IllegalArgumentException("Amount should be positive and multiple of 100");
        }
        if (inventory.getTotalCash() < amount) {
            throw new IllegalArgumentException("ATM does not have enough cash");
        }

        int remaining = amount;
        WithdrawalBreakdown breakdown = new WithdrawalBreakdown();

        // Greedy approach: bade denomination ko pehle use karo taaki notes kam niklein aur state flow simple rahe.
        for (int denomination : inventory.snapshot().keySet()) {
            int usableNotes = Math.min(remaining / denomination, inventory.getCount(denomination));
            breakdown.add(denomination, usableNotes);
            remaining -= usableNotes * denomination;
        }

        if (remaining != 0) {
            throw new IllegalArgumentException("Amount cannot be dispensed with available denominations");
        }

        for (var entry : breakdown.getNotes().entrySet()) {
            inventory.deduct(entry.getKey(), entry.getValue());
        }
        return breakdown;
    }
}
