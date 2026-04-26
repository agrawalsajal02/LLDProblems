package splitwise.phase2;

public class BalanceSheetController {
    public void updateBalanceSheet(User paidByUser, Expense expense) {
        UserExpenseBalanceSheet paidBySheet = paidByUser.getUserExpenseBalanceSheet();
        // Payer ne poora group expense diya hai, isliye uske total payment me full amount add karo.
        paidBySheet.setTotalPayment(paidBySheet.getTotalPayment() + expense.getAmount());

        for (Split split : expense.getSplitDetails()) {
            User oweUser = split.getUser();
            double oweAmount = split.getAmountOwe();

            if (paidByUser.getUserId().equals(oweUser.getUserId())) {
                // Payer ka khud ka share uska personal expense hai, isliye usse self-expense me count karo.
                paidBySheet.setTotalYourExpense(paidBySheet.getTotalYourExpense() + oweAmount);
                continue;
            }

            // Dusre users ka share payer ko wapas milna chahiye, isliye uske get-back total ko update karo.
            paidBySheet.setTotalYouGetBack(paidBySheet.getTotalYouGetBack() + oweAmount);
            // Payer ke ledger me is specific user ke against receivable amount maintain karo.
            Balance getBackBalance = paidBySheet.getUserVsBalance().computeIfAbsent(oweUser.getUserId(), ignored -> new Balance());
            getBackBalance.setAmountGetBack(getBackBalance.getAmountGetBack() + oweAmount);

            UserExpenseBalanceSheet oweSheet = oweUser.getUserExpenseBalanceSheet();
            // Owe-user ka total outstanding badhao kyunki uska share payer ne cover kiya hai.
            oweSheet.setTotalYouOwe(oweSheet.getTotalYouOwe() + oweAmount);
            // Owe-user ka personal expense bhi update karo taaki uska consumed share visible rahe.
            oweSheet.setTotalYourExpense(oweSheet.getTotalYourExpense() + oweAmount);

            // Owe-user ke ledger me payer ke against payable amount store karo taaki settlement possible ho.
            Balance oweBalance = oweSheet.getUserVsBalance().computeIfAbsent(paidByUser.getUserId(), ignored -> new Balance());
            oweBalance.setAmountOwe(oweBalance.getAmountOwe() + oweAmount);
        }
    }
}
