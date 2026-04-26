package splitwise.expense;

import splitwise.expense.split.Split;
import splitwise.user.User;

import java.util.ArrayList;
import java.util.List;

public class Expense {
    private final String expenseId;
    private final String description;
    private final double expenseAmount;
    private final User paidByUser;
    private final ExpenseSplitType splitType;
    private final List<Split> splitDetails = new ArrayList<>();

    public Expense(
        String expenseId,
        double expenseAmount,
        String description,
        User paidByUser,
        ExpenseSplitType splitType,
        List<Split> splitDetails
    ) {
        this.expenseId = expenseId;
        this.expenseAmount = expenseAmount;
        this.description = description;
        this.paidByUser = paidByUser;
        this.splitType = splitType;
        this.splitDetails.addAll(splitDetails);
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getDescription() {
        return description;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public User getPaidByUser() {
        return paidByUser;
    }

    public ExpenseSplitType getSplitType() {
        return splitType;
    }

    public List<Split> getSplitDetails() {
        return splitDetails;
    }
}
