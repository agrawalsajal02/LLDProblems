package splitwise.phase2;

import java.util.List;

public class UnequalExpenseSplit implements ExpenseSplit {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {
        double totalSplitAmount = 0;
        for (Split split : splitList) {
            totalSplitAmount += split.getAmountOwe();
        }
        if (Double.compare(totalSplitAmount, totalAmount) != 0) {
            throw new IllegalArgumentException("Unequal split total mismatch");
        }
    }
}
