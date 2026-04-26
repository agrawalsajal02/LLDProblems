package splitwise.phase2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.addUser(new User("U1", "User1"));
        userController.addUser(new User("U2", "User2"));
        userController.addUser(new User("U3", "User3"));

        GroupController groupController = new GroupController();
        Group group = groupController.createGroup("G1", "Trip", userController.getUser("U1"));
        group.addMember(userController.getUser("U2"));
        group.addMember(userController.getUser("U3"));

        ExpenseService expenseService = new ExpenseService(new BalanceSheetController());
        expenseService.addExpense(
            group,
            "E1",
            "Breakfast",
            900,
            List.of(
                new Split(userController.getUser("U1"), 300),
                new Split(userController.getUser("U2"), 300),
                new Split(userController.getUser("U3"), 300)
            ),
            ExpenseSplitType.EQUAL,
            userController.getUser("U1")
        );

        System.out.println("User1 get back: " + userController.getUser("U1").getUserExpenseBalanceSheet().getTotalYouGetBack());
        System.out.println("User2 owe: " + userController.getUser("U2").getUserExpenseBalanceSheet().getTotalYouOwe());
    }
}
