package atm;

import java.util.LinkedHashMap;
import java.util.Map;

public final class CashInventory {
    private final Map<Integer, Integer> notesByDenomination = new LinkedHashMap<>();

    public CashInventory(int twoThousand, int fiveHundred, int oneHundred) {
        notesByDenomination.put(2000, twoThousand);
        notesByDenomination.put(500, fiveHundred);
        notesByDenomination.put(100, oneHundred);
    }

    public Map<Integer, Integer> snapshot() {
        return new LinkedHashMap<>(notesByDenomination);
    }

    public int getTotalCash() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : notesByDenomination.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }
        return total;
    }

    public int getCount(int denomination) {
        return notesByDenomination.getOrDefault(denomination, 0);
    }

    public void deduct(int denomination, int count) {
        int available = getCount(denomination);
        if (count > available) {
            throw new IllegalArgumentException("Not enough notes for denomination " + denomination);
        }
        notesByDenomination.put(denomination, available - count);
    }
}
