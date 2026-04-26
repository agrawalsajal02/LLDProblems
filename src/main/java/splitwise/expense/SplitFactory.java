package splitwise.expense;

import splitwise.expense.split.EqualExpenseSplit;
import splitwise.expense.split.ExpenseSplit;
import splitwise.expense.split.PercentageExpenseSplit;
import splitwise.expense.split.UnequalExpenseSplit;

public class SplitFactory {
    public static ExpenseSplit getSplitObject(ExpenseSplitType splitType) {
        switch (splitType) {
            case EQUAL:
                return new EqualExpenseSplit();
            case UNEQUAL:
                return new UnequalExpenseSplit();
            case PERCENTAGE:
                return new PercentageExpenseSplit();
            default:
                throw new IllegalArgumentException("Unsupported split type: " + splitType);
        }
    }
}
