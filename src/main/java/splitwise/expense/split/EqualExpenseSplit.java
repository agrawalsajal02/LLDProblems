package splitwise.expense.split;

import java.util.List;

public class EqualExpenseSplit implements ExpenseSplit {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {
        if (splitList == null || splitList.isEmpty()) {
            throw new IllegalArgumentException("Split list cannot be empty");
        }
        double amountShouldBePresent = totalAmount / splitList.size();
        for (Split split : splitList) {
            if (split.getAmountOwe() != amountShouldBePresent) {
                throw new IllegalArgumentException("Equal split amounts do not match total amount");
            }
        }
    }
}
