package splitwise.user;

import splitwise.UserExpenseBalanceSheet;

public class User {
    private final String userId;
    private final String userName;
    private final UserExpenseBalanceSheet userExpenseBalanceSheet;

    public User(String id, String userName) {
        this.userId = id;
        this.userName = userName;
        this.userExpenseBalanceSheet = new UserExpenseBalanceSheet();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public UserExpenseBalanceSheet getUserExpenseBalanceSheet() {
        return userExpenseBalanceSheet;
    }
}
