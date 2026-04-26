package splitwise.phase1;

public class Split {
    private final User user;
    private final double amountOwe;

    public Split(User user, double amountOwe) {
        this.user = user;
        this.amountOwe = amountOwe;
    }

    public User getUser() {
        return user;
    }

    public double getAmountOwe() {
        return amountOwe;
    }
}
