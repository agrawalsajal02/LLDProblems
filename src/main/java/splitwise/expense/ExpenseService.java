package splitwise.expense;

import splitwise.BalanceSheetController;
import splitwise.expense.split.ExpenseSplit;
import splitwise.expense.split.Split;
import splitwise.group.Group;
import splitwise.user.User;

import java.util.List;

public class ExpenseService {
    private final BalanceSheetController balanceSheetController;

    public ExpenseService(BalanceSheetController balanceSheetController) {
        this.balanceSheetController = balanceSheetController;
    }

    public Expense addExpense(
        Group group,
        String expenseId,
        String description,
        double expenseAmount,
        List<Split> splitDetails,
        ExpenseSplitType splitType,
        User paidByUser
    ) {
        ExpenseSplit expenseSplit = SplitFactory.getSplitObject(splitType);
        expenseSplit.validateSplitRequest(splitDetails, expenseAmount);

        Expense expense = new Expense(expenseId, expenseAmount, description, paidByUser, splitType, splitDetails);
        balanceSheetController.updateUserExpenseBalanceSheet(paidByUser, splitDetails, expenseAmount);
        group.addExpense(expense);
        return expense;
    }
}
