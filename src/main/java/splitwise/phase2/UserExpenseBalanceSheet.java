package splitwise.phase2;

import java.util.HashMap;
import java.util.Map;

public class UserExpenseBalanceSheet {
    private final Map<String, Balance> userVsBalance = new HashMap<>();
    private double totalYourExpense;
    private double totalPayment;
    private double totalYouOwe;
    private double totalYouGetBack;

    public Map<String, Balance> getUserVsBalance() {
        return userVsBalance;
    }

    public double getTotalYourExpense() {
        return totalYourExpense;
    }

    public void setTotalYourExpense(double totalYourExpense) {
        this.totalYourExpense = totalYourExpense;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public double getTotalYouOwe() {
        return totalYouOwe;
    }

    public void setTotalYouOwe(double totalYouOwe) {
        this.totalYouOwe = totalYouOwe;
    }

    public double getTotalYouGetBack() {
        return totalYouGetBack;
    }

    public void setTotalYouGetBack(double totalYouGetBack) {
        this.totalYouGetBack = totalYouGetBack;
    }
}
