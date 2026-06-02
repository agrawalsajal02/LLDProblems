package companiesProblem.rippling.ruleengine.expense;

import java.util.List;

public final class BlockedExpenseTypeRule implements Rule {
    private final String name;
    private final String blockedExpenseType;

    public BlockedExpenseTypeRule(String name, String blockedExpenseType) {
        this.name = name;
        this.blockedExpenseType = blockedExpenseType;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void evaluate(List<Expense> expenses, EvaluationReport report) {
        for (Expense expense : expenses) {
            if (RuleUtils.equalsIgnoreCase(expense.getExpenseType(), blockedExpenseType)) {
                report.addExpenseViolation(expense.getExpenseId(), name,
                        "Expense type " + blockedExpenseType + " is not allowed");
            }
        }
    }
}
