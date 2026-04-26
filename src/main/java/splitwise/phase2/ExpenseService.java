package splitwise.phase2;

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
        double amount,
        List<Split> splitDetails,
        ExpenseSplitType splitType,
        User paidByUser
    ) {
        ExpenseSplit splitValidator = SplitFactory.getSplitObject(splitType);
        splitValidator.validateSplitRequest(splitDetails, amount);

        Expense expense = new Expense(expenseId, description, amount, paidByUser, splitType, splitDetails);
        group.addExpense(expense);
        balanceSheetController.updateBalanceSheet(paidByUser, expense);
        return expense;
    }
}
