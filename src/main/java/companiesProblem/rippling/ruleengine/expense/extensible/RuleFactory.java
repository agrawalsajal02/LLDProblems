package companiesProblem.rippling.ruleengine.expense.extensible;

import companiesProblem.rippling.ruleengine.expense.BlockedExpenseTypeRule;
import companiesProblem.rippling.ruleengine.expense.BlockedFieldValueRule;
import companiesProblem.rippling.ruleengine.expense.ExpenseTypeTotalPerTripLimitRule;
import companiesProblem.rippling.ruleengine.expense.MaxSingleExpenseAmountRule;
import companiesProblem.rippling.ruleengine.expense.Rule;
import companiesProblem.rippling.ruleengine.expense.TripTotalLimitRule;
import companiesProblem.rippling.ruleengine.expense.VendorTypeSingleExpenseLimitRule;

import java.util.Map;

public final class RuleFactory {
    public Rule createRule(RuleDefinition definition) {
        Map<String, String> params = definition.getParams();

        switch (definition.getType()) {
            case MAX_SINGLE_EXPENSE_AMOUNT:
                return new MaxSingleExpenseAmountRule(
                        definition.getName(),
                        doubleParam(params, "maxAmount"));
            case VENDOR_TYPE_SINGLE_EXPENSE_LIMIT:
                return new VendorTypeSingleExpenseLimitRule(
                        definition.getName(),
                        requiredParam(params, "vendorType"),
                        doubleParam(params, "maxAmount"));
            case BLOCKED_FIELD_VALUE:
                return new BlockedFieldValueRule(
                        definition.getName(),
                        BlockedFieldValueRule.Field.valueOf(requiredParam(params, "field")),
                        requiredParam(params, "blockedValue"));
            case BLOCKED_EXPENSE_TYPE:
                return new BlockedExpenseTypeRule(
                        definition.getName(),
                        requiredParam(params, "expenseType"));
            case TRIP_TOTAL_LIMIT:
                return new TripTotalLimitRule(
                        definition.getName(),
                        doubleParam(params, "maxTripTotal"));
            case EXPENSE_TYPE_TOTAL_PER_TRIP_LIMIT:
                return new ExpenseTypeTotalPerTripLimitRule(
                        definition.getName(),
                        requiredParam(params, "expenseType"),
                        doubleParam(params, "maxTotal"));
            default:
                throw new IllegalArgumentException("Unsupported rule type: " + definition.getType());
        }
    }

    private String requiredParam(Map<String, String> params, String key) {
        String value = params.get(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required rule param: " + key);
        }
        return value;
    }

    private double doubleParam(Map<String, String> params, String key) {
        return Double.parseDouble(requiredParam(params, key));
    }
}
