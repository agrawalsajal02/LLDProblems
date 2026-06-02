package companiesProblem.rippling.ruleengine.expense;

import java.util.List;

public final class VendorTypeSingleExpenseLimitRule implements Rule {
    private final String name;
    private final String vendorType;
    private final double maxAmount;

    public VendorTypeSingleExpenseLimitRule(String name, String vendorType, double maxAmount) {
        this.name = name;
        this.vendorType = vendorType;
        this.maxAmount = maxAmount;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void evaluate(List<Expense> expenses, EvaluationReport report) {
        for (Expense expense : expenses) {
            if (RuleUtils.equalsIgnoreCase(expense.getVendorType(), vendorType)
                    && expense.getAmountInUsd() > maxAmount) {
                report.addExpenseViolation(expense.getExpenseId(), name,
                        "Vendor type " + vendorType + " expense " + expense.getAmountInUsd()
                                + " exceeds " + maxAmount);
            }
        }
    }
}
