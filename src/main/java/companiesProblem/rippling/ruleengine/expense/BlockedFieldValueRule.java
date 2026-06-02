package companiesProblem.rippling.ruleengine.expense;

import java.util.List;

public final class BlockedFieldValueRule implements Rule {
    public enum Field {
        EXPENSE_TYPE,
        VENDOR_TYPE,
        VENDOR_NAME
    }

    private final String name;
    private final Field field;
    private final String blockedValue;

    public BlockedFieldValueRule(String name, Field field, String blockedValue) {
        this.name = name;
        this.field = field;
        this.blockedValue = blockedValue;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void evaluate(List<Expense> expenses, EvaluationReport report) {
        for (Expense expense : expenses) {
            String actualValue = valueFor(expense);
            if (RuleUtils.equalsIgnoreCase(actualValue, blockedValue)) {
                report.addExpenseViolation(expense.getExpenseId(), name,
                        field + " value " + blockedValue + " is not allowed");
            }
        }
    }

    private String valueFor(Expense expense) {
        switch (field) {
            case EXPENSE_TYPE:
                return expense.getExpenseType();
            case VENDOR_TYPE:
                return expense.getVendorType();
            case VENDOR_NAME:
                return expense.getVendorName();
            default:
                throw new IllegalStateException("Unsupported field: " + field);
        }
    }
}
