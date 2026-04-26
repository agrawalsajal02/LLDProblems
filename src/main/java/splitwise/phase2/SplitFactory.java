package splitwise.phase2;

public class SplitFactory {
    public static ExpenseSplit getSplitObject(ExpenseSplitType splitType) {
        switch (splitType) {
            case EQUAL:
                return new EqualExpenseSplit();
            case UNEQUAL:
                return new UnequalExpenseSplit();
            default:
                throw new IllegalArgumentException("Unsupported split type: " + splitType);
        }
    }
}
