package splitwise.phase2;

import java.util.List;

public interface ExpenseSplit {
    void validateSplitRequest(List<Split> splitList, double totalAmount);
}
