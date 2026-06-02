package companiesProblem.rippling.ruleengine.expense;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class EvaluationReport {
    private final Map<String, List<RuleViolation>> expenseViolations = new LinkedHashMap<>();
    private final Map<String, List<RuleViolation>> tripViolations = new LinkedHashMap<>();

    public void addExpense(String expenseId) {
        expenseViolations.putIfAbsent(expenseId, new ArrayList<>());
    }

    public void addTrip(String tripId) {
        tripViolations.putIfAbsent(tripId, new ArrayList<>());
    }

    public void addExpenseViolation(String expenseId, String ruleName, String message) {
        expenseViolations.computeIfAbsent(expenseId, id -> new ArrayList<>())
                .add(new RuleViolation(ruleName, expenseId, message));
    }

    public void addTripViolation(String tripId, String ruleName, String message) {
        tripViolations.computeIfAbsent(tripId, id -> new ArrayList<>())
                .add(new RuleViolation(ruleName, tripId, message));
    }

    public Map<String, List<RuleViolation>> getExpenseViolations() {
        return expenseViolations;
    }

    public Map<String, List<RuleViolation>> getTripViolations() {
        return tripViolations;
    }
}
