package splitwise.phase2;

import java.util.List;

public class EqualExpenseSplit implements ExpenseSplit {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {
        double expectedAmount = totalAmount / splitList.size();
        for (Split split : splitList) {
            if (split.getAmountOwe() != expectedAmount) {
                throw new IllegalArgumentException("Equal split amount mismatch");
            }
        }
    }
}
