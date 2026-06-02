package companiesProblem.rippling.ruleengine.expense;

import java.util.List;
import java.util.Map;

public final class TripTotalLimitRule implements Rule {
    private final String name;
    private final double maxTripTotal;

    public TripTotalLimitRule(String name, double maxTripTotal) {
        this.name = name;
        this.maxTripTotal = maxTripTotal;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void evaluate(List<Expense> expenses, EvaluationReport report) {
        Map<String, List<Expense>> tripToExpenses = RuleUtils.groupByTrip(expenses);

        for (Map.Entry<String, List<Expense>> entry : tripToExpenses.entrySet()) {
            String tripId = entry.getKey();
            List<Expense> tripExpenses = entry.getValue();
            double total = 0;

            for (Expense expense : tripExpenses) {
                total += expense.getAmountInUsd();
            }

            if (total > maxTripTotal) {
                report.addTripViolation(tripId, name,
                        "Trip " + tripId + " total " + total + " exceeds " + maxTripTotal);

                for (Expense expense : tripExpenses) {
                    report.addExpenseViolation(expense.getExpenseId(), name,
                            "Trip " + tripId + " total " + total + " exceeds " + maxTripTotal);
                }
            }
        }
    }
}
