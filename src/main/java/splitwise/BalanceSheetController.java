package splitwise;

import splitwise.expense.split.Split;
import splitwise.user.User;

import java.util.List;
import java.util.Map;

public class BalanceSheetController {
    public void updateUserExpenseBalanceSheet(User expensePaidBy, List<Split> splits, double totalExpenseAmount) {
        UserExpenseBalanceSheet paidByUserExpenseSheet = expensePaidBy.getUserExpenseBalanceSheet();
        // Payer ne full amount diya hai, isliye sabse pehle uska total payment update karo.
        addToTotalPayment(paidByUserExpenseSheet, totalExpenseAmount);

        for (Split split : splits) {
            User userOwe = split.getUser();
            UserExpenseBalanceSheet oweUserExpenseSheet = userOwe.getUserExpenseBalanceSheet();
            double oweAmount = split.getAmountOwe();

            if (expensePaidBy.getUserId().equals(userOwe.getUserId())) {
                // Agar payer ka khud ka share hai, to usse self-expense me count karo.
                addToSelfExpense(paidByUserExpenseSheet, oweAmount);
                continue;
            }

            // Agar split kisi aur user ka hai, to payer aur borrower dono side ke ledgers update karo.
            updateBorrowerBalances(expensePaidBy, paidByUserExpenseSheet, userOwe, oweUserExpenseSheet, oweAmount);
        }
    }

    public void showBalanceSheetOfUser(User user) {
        System.out.println("---------------------------------------");
        System.out.println("Balance sheet of user : " + user.getUserId());

        UserExpenseBalanceSheet userExpenseBalanceSheet = user.getUserExpenseBalanceSheet();

        System.out.println("TotalYourExpense: " + userExpenseBalanceSheet.getTotalYourExpense());
        System.out.println("TotalGetBack: " + userExpenseBalanceSheet.getTotalYouGetBack());
        System.out.println("TotalYourOwe: " + userExpenseBalanceSheet.getTotalYouOwe());
        System.out.println("TotalPaymnetMade: " + userExpenseBalanceSheet.getTotalPayment());
        for (Map.Entry<String, Balance> entry : userExpenseBalanceSheet.getUserVsBalance().entrySet()) {
            String userId = entry.getKey();
            Balance balance = entry.getValue();

            System.out.println(
                "userID:" + userId + " YouGetBack:" + balance.getAmountGetBack() + " YouOwe:" + balance.getAmountOwe()
            );
        }

        System.out.println("---------------------------------------");
    }

    private void addToTotalPayment(UserExpenseBalanceSheet balanceSheet, double amount) {
        balanceSheet.setTotalPayment(balanceSheet.getTotalPayment() + amount);
    }

    private void addToSelfExpense(UserExpenseBalanceSheet balanceSheet, double amount) {
        balanceSheet.setTotalYourExpense(balanceSheet.getTotalYourExpense() + amount);
    }

    private void updateBorrowerBalances(
        User expensePaidBy,
        UserExpenseBalanceSheet paidBySheet,
        User userOwe,
        UserExpenseBalanceSheet oweUserSheet,
        double oweAmount
    ) {
        // Payer ko dusre user se paisa lena hai, isliye receivable summary aur per-user balance dono update karo.
        addToTotalGetBack(paidBySheet, oweAmount);
        addToReceivableBalance(paidBySheet, userOwe.getUserId(), oweAmount);

        // Borrower ne apna share khud pay nahi kiya, isliye uska total owe aur per-user payable balance update karo.
        addToTotalOwe(oweUserSheet, oweAmount);
        addToSelfExpense(oweUserSheet, oweAmount);
        addToPayableBalance(oweUserSheet, expensePaidBy.getUserId(), oweAmount);
    }

    private void addToTotalGetBack(UserExpenseBalanceSheet balanceSheet, double amount) {
        balanceSheet.setTotalYouGetBack(balanceSheet.getTotalYouGetBack() + amount);
    }

    private void addToTotalOwe(UserExpenseBalanceSheet balanceSheet, double amount) {
        balanceSheet.setTotalYouOwe(balanceSheet.getTotalYouOwe() + amount);
    }

    private void addToReceivableBalance(UserExpenseBalanceSheet balanceSheet, String borrowerUserId, double amount) {
        Balance balance = getOrCreateBalance(balanceSheet.getUserVsBalance(), borrowerUserId);
        balance.setAmountGetBack(balance.getAmountGetBack() + amount);
    }

    private void addToPayableBalance(UserExpenseBalanceSheet balanceSheet, String payerUserId, double amount) {
        Balance balance = getOrCreateBalance(balanceSheet.getUserVsBalance(), payerUserId);
        balance.setAmountOwe(balance.getAmountOwe() + amount);
    }

    private Balance getOrCreateBalance(Map<String, Balance> userVsBalance, String userId) {
        return userVsBalance.computeIfAbsent(userId, ignored -> new Balance());
    }
}
