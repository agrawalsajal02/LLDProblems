package splitwise.phase2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Expense {
    private final String expenseId;
    private final String description;
    private final double amount;
    private final User paidByUser;
    private final ExpenseSplitType splitType;
    private final List<Split> splitDetails;

    public Expense(
        String expenseId,
        String description,
        double amount,
        User paidByUser,
        ExpenseSplitType splitType,
        List<Split> splitDetails
    ) {
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.paidByUser = paidByUser;
        this.splitType = splitType;
        this.splitDetails = new ArrayList<>(splitDetails);
    }

    public String getExpenseId() {
        return expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidByUser() {
        return paidByUser;
    }

    public List<Split> getSplitDetails() {
        return Collections.unmodifiableList(splitDetails);
    }
}
