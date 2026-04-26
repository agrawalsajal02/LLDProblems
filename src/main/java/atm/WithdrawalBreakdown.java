package atm;

import java.util.LinkedHashMap;
import java.util.Map;

public final class WithdrawalBreakdown {
    private final Map<Integer, Integer> notes = new LinkedHashMap<>();

    public void add(int denomination, int count) {
        if (count > 0) {
            notes.put(denomination, count);
        }
    }

    public Map<Integer, Integer> getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return notes.toString();
    }
}
