package splitwise.expense.split;

import java.util.List;

public class PercentageExpenseSplit implements ExpenseSplit {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {
        throw new UnsupportedOperationException(
            "Percentage split is intentionally left out in this 1-hour LLD version because Split does not carry percentage data."
        );
    }
}
