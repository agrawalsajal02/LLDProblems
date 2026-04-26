package splitwise.phase1;

import java.util.HashMap;
import java.util.Map;

public class UserBalanceSheet {
    private final Map<String, Balance> userBalances = new HashMap<>();
    private double totalPaid;
    private double totalOwe;
    private double totalGetBack;

    public Map<String, Balance> getUserBalances() {
        return userBalances;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public double getTotalOwe() {
        return totalOwe;
    }

    public void setTotalOwe(double totalOwe) {
        this.totalOwe = totalOwe;
    }

    public double getTotalGetBack() {
        return totalGetBack;
    }

    public void setTotalGetBack(double totalGetBack) {
        this.totalGetBack = totalGetBack;
    }
}
