package companiesProblem.rippling.ruleengine.expense;

import java.util.List;
import java.util.Map;

public class ExpenseRuleEngineDemo {

    public static void main(String[] args) {
        List<Map<String, String>> expenses = List.of(
                Map.of(
                        "expense_id", "001",
                        "trip_id", "T1",
                        "item_id", "I1",
                        "amount_usd", "49.99",
                        "expense_type", "client_hosting",
                        "vendor_type", "restaurant",
                        "vendor_name", "Outback Roadhouse"
                ),
                Map.of(
                        "expense_id", "002",
                        "trip_id", "T1",
                        "item_id", "I2",
                        "amount_usd", "90",
                        "expense_type", "meal",
                        "vendor_type", "restaurant",
                        "vendor_name", "Burger King"
                ),
                Map.of(
                        "expense_id", "003",
                        "trip_id", "T1",
                        "item_id", "I3",
                        "amount_usd", "300",
                        "expense_type", "airfare",
                        "vendor_type", "airline",
                        "vendor_name", "United"
                ),
                Map.of(
                        "expense_id", "006",
                        "trip_id", "T1",
                        "item_id", "I6",
                        "amount_usd", "130",
                        "expense_type", "meal",
                        "vendor_type", "restaurant",
                        "vendor_name", "Fancy Client Dinner"
                ),
                Map.of(
                        "expense_id", "004",
                        "trip_id", "T2",
                        "item_id", "I4",
                        "amount_usd", "2000",
                        "expense_type", "entertainment",
                        "vendor_type", "cinema",
                        "vendor_name", "AMC"
                ),
                Map.of(
                        "expense_id", "005",
                        "trip_id", "T2",
                        "item_id", "I5",
                        "amount_usd", "100",
                        "expense_type", "meal",
                        "vendor_type", "restaurant",
                        "vendor_name", "Chipotle"
                )
        );

        List<Rule> rules = List.of(
                new VendorTypeSingleExpenseLimitRule("RESTAURANT_SINGLE_EXPENSE_75", "restaurant", 75),
                new BlockedFieldValueRule("NO_AIRFARE", BlockedFieldValueRule.Field.EXPENSE_TYPE, "airfare"),
                new BlockedFieldValueRule("NO_ENTERTAINMENT", BlockedFieldValueRule.Field.EXPENSE_TYPE, "entertainment"),
                new MaxSingleExpenseAmountRule("MAX_SINGLE_EXPENSE_250", 250),
                new TripTotalLimitRule("TRIP_TOTAL_2000", 2000),
                new ExpenseTypeTotalPerTripLimitRule("MEAL_PER_TRIP_200", "meal", 200)
        );

        RuleEngine engine = new RuleEngine();
        EvaluationReport report = engine.evaluateRules(rules, expenses);

        System.out.println("Expense Rule Evaluation Report");
        for (Map<String, String> expense : expenses) {
            String expenseId = expense.get("expense_id");
            List<RuleViolation> violations = report.getExpenseViolations().get(expenseId);
            System.out.println(expenseId + " ["
                    + expense.get("expense_type") + ", $"
                    + expense.get("amount_usd") + "]");

            if (violations.isEmpty()) {
                System.out.println("  PASSED");
            } else {
                System.out.println("  FAILED");
                for (RuleViolation violation : violations) {
                    System.out.println("  - " + violation);
                }
            }
        }

        System.out.println();
        System.out.println("Trip Rule Evaluation Report");
        for (Map.Entry<String, List<RuleViolation>> entry : report.getTripViolations().entrySet()) {
            if (entry.getValue().isEmpty()) {
                continue;
            }

            System.out.println(entry.getKey() + " FAILED");
            for (RuleViolation violation : entry.getValue()) {
                System.out.println("  - " + violation);
            }
        }
    }
}
