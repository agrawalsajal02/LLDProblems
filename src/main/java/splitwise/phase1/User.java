package splitwise.phase1;

public class User {
    private final String userId;
    private final String name;
    private final UserBalanceSheet balanceSheet;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.balanceSheet = new UserBalanceSheet();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public UserBalanceSheet getBalanceSheet() {
        return balanceSheet;
    }
}
