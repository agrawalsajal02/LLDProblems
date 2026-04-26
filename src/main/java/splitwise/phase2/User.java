package splitwise.phase2;

public class User {
    private final String userId;
    private final String userName;
    private final UserExpenseBalanceSheet userExpenseBalanceSheet;

    public User(String userId, String userName) {
        this.userId = userId;
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
