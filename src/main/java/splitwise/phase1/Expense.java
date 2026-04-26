package splitwise.phase1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Expense {
    private final String expenseId;
    private final String description;
    private final double amount;
    private final User paidBy;
    private final List<Split> splits;

    public Expense(String expenseId, String description, double amount, User paidBy, List<Split> splits) {
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = new ArrayList<>(splits);
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return Collections.unmodifiableList(splits);
    }
}
