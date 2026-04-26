package splitwise.phase1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("U1", "User1");
        User user2 = new User("U2", "User2");
        User user3 = new User("U3", "User3");

        ExpenseService expenseService = new ExpenseService(new BalanceSheetService());
        expenseService.addExpense(
            "E1",
            "Dinner",
            900,
            user1,
            List.of(
                new Split(user1, 300),
                new Split(user2, 300),
                new Split(user3, 300)
            )
        );

        System.out.println("User1 gets back: " + user1.getBalanceSheet().getTotalGetBack());
        System.out.println("User2 owes: " + user2.getBalanceSheet().getTotalOwe());
        System.out.println("User3 owes: " + user3.getBalanceSheet().getTotalOwe());
    }
}
