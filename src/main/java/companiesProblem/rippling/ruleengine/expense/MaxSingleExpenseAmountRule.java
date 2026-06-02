package companiesProblem.rippling.ruleengine.expense;

import java.util.List;

public final class MaxSingleExpenseAmountRule implements Rule {
    private final String name;
    private final double maxAmount;

    public MaxSingleExpenseAmountRule(String name, double maxAmount) {
        this.name = name;
        this.maxAmount = maxAmount;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void evaluate(List<Expense> expenses, EvaluationReport report) {
        for (Expense expense : expenses) {
            if (expense.getAmountInUsd() > maxAmount) {
                report.addExpenseViolation(expense.getExpenseId(), name,
                        "Expense amount " + expense.getAmountInUsd() + " exceeds " + maxAmount);
            }
        }
    }
}
