package companiesProblem.rippling.ruleengine.expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class RuleUtils {
    private RuleUtils() {
    }

    static Map<String, List<Expense>> groupByTrip(List<Expense> expenses) {
        Map<String, List<Expense>> tripToExpenses = new HashMap<>();
        for (Expense expense : expenses) {
            tripToExpenses.computeIfAbsent(expense.getTripId(), tripId -> new ArrayList<>()).add(expense);
        }
        return tripToExpenses;
    }

    static boolean equalsIgnoreCase(String first, String second) {
        if (first == null || second == null) {
            return first == second;
        }
        return first.equalsIgnoreCase(second);
    }
}
