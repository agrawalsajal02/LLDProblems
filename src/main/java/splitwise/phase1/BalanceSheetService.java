package splitwise.phase1;

public class BalanceSheetService {
    public void updateBalances(User paidBy, Expense expense) {
        UserBalanceSheet paidBySheet = paidBy.getBalanceSheet();
        // Payer ne abhi full bill diya hai, isliye uske total paid amount me poora expense add karo.
        paidBySheet.setTotalPaid(paidBySheet.getTotalPaid() + expense.getAmount());

        for (Split split : expense.getSplits()) {
            User userWhoOwes = split.getUser();
            double oweAmount = split.getAmountOwe();

            if (paidBy.getUserId().equals(userWhoOwes.getUserId())) {
                // Payer ka apna share alag se owe nahi hota, isliye is split ko borrowing flow se skip karo.
                continue;
            }

            // Jo amount dusre users ne payer ko dena hai, usko payer ke get-back total me add karo.
            paidBySheet.setTotalGetBack(paidBySheet.getTotalGetBack() + oweAmount);
            // Payer ke per-user ledger me record rakho ki is specific user se kitna recover karna hai.
            Balance balanceToReceive = paidBySheet.getUserBalances().computeIfAbsent(userWhoOwes.getUserId(), ignored -> new Balance());
            balanceToReceive.setAmountGetBack(balanceToReceive.getAmountGetBack() + oweAmount);

            UserBalanceSheet oweSheet = userWhoOwes.getBalanceSheet();
            // Borrower ke total owe ko badhao kyunki uska share kisi aur ne pay kiya hai.
            oweSheet.setTotalOwe(oweSheet.getTotalOwe() + oweAmount);
            // Borrower ke per-user ledger me record rakho ki usko payer ko kitna wapas dena hai.
            Balance balanceToPay = oweSheet.getUserBalances().computeIfAbsent(paidBy.getUserId(), ignored -> new Balance());
            balanceToPay.setAmountOwe(balanceToPay.getAmountOwe() + oweAmount);
        }
    }
}
