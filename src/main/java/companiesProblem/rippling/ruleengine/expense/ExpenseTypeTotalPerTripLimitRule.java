package companiesProblem.rippling.ruleengine.expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ExpenseTypeTotalPerTripLimitRule implements Rule {
    private final String name;
    private final String expenseType;
    private final double maxTotal;

    public ExpenseTypeTotalPerTripLimitRule(String name, String expenseType, double maxTotal) {
        this.name = name;
        this.expenseType = expenseType;
        this.maxTotal = maxTotal;
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
            List<Expense> matchingExpenses = new ArrayList<>();
            double total = 0;

            for (Expense expense : entry.getValue()) {
                if (RuleUtils.equalsIgnoreCase(expense.getExpenseType(), expenseType)) {
                    matchingExpenses.add(expense);
                    total += expense.getAmountInUsd();
                }
            }

            if (total > maxTotal) {
                report.addTripViolation(tripId, name,
                        "Trip " + tripId + " " + expenseType + " total " + total
                                + " exceeds " + maxTotal);

                for (Expense expense : matchingExpenses) {
                    report.addExpenseViolation(expense.getExpenseId(), name,
                            "Trip " + tripId + " " + expenseType + " total " + total
                                    + " exceeds " + maxTotal);
                }
            }
        }
    }
}
