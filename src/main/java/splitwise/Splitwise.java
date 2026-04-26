package splitwise;

import splitwise.expense.ExpenseService;
import splitwise.expense.ExpenseSplitType;
import splitwise.expense.split.Split;
import splitwise.group.Group;
import splitwise.group.GroupController;
import splitwise.user.User;
import splitwise.user.UserController;

import java.util.ArrayList;
import java.util.List;

public class Splitwise {
    private final UserController userController;
    private final GroupController groupController;
    private final BalanceSheetController balanceSheetController;
    private final ExpenseService expenseService;

    Splitwise() {
        userController = new UserController();
        groupController = new GroupController();
        balanceSheetController = new BalanceSheetController();
        expenseService = new ExpenseService(balanceSheetController);
    }

    public void demo() {
        setupUserAndGroup();

        Group group = groupController.getGroup("G1001");
        group.addMember(userController.getUser("U2001"));
        group.addMember(userController.getUser("U3001"));

        List<Split> splits = new ArrayList<>();
        splits.add(new Split(userController.getUser("U1001"), 300));
        splits.add(new Split(userController.getUser("U2001"), 300));
        splits.add(new Split(userController.getUser("U3001"), 300));
        expenseService.addExpense(
            group,
            "Exp1001",
            "Breakfast",
            900,
            splits,
            ExpenseSplitType.EQUAL,
            userController.getUser("U1001")
        );

        List<Split> splits2 = new ArrayList<>();
        splits2.add(new Split(userController.getUser("U1001"), 400));
        splits2.add(new Split(userController.getUser("U2001"), 100));
        expenseService.addExpense(
            group,
            "Exp1002",
            "Lunch",
            500,
            splits2,
            ExpenseSplitType.UNEQUAL,
            userController.getUser("U2001")
        );

        for (User user : userController.getAllUsers()) {
            balanceSheetController.showBalanceSheetOfUser(user);
        }
    }

    public void setupUserAndGroup() {
        addUsersToSplitwiseApp();

        User user1 = userController.getUser("U1001");
        groupController.createNewGroup("G1001", "Outing with Friends", user1);
    }

    private void addUsersToSplitwiseApp() {
        userController.addUser(new User("U1001", "User1"));
        userController.addUser(new User("U2001", "User2"));
        userController.addUser(new User("U3001", "User3"));
    }
}
