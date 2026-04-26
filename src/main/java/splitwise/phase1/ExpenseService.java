package splitwise.phase1;

import java.util.List;

public class ExpenseService {
    private final BalanceSheetService balanceSheetService;

    public ExpenseService(BalanceSheetService balanceSheetService) {
        this.balanceSheetService = balanceSheetService;
    }

    public Expense addExpense(String expenseId, String description, double amount, User paidBy, List<Split> splits) {
        Expense expense = new Expense(expenseId, description, amount, paidBy, splits);
        balanceSheetService.updateBalances(paidBy, expense);
        return expense;
    }
}
