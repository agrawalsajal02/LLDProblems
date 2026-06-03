package companiesProblem.rippling.ruleengine.expense.extensible;

import companiesProblem.rippling.ruleengine.expense.EvaluationReport;
import companiesProblem.rippling.ruleengine.expense.RuleEngine;
import companiesProblem.rippling.ruleengine.expense.RuleViolation;

import java.util.List;
import java.util.Map;

public class ExtensibleExpenseRuleEngineDemo {
    public static void main(String[] args) {
        ExpenseRuleApi api = new ExpenseRuleApi(
                new RuleService(new InMemoryRuleRepository(), new RuleFactory()),
                new RuleEngine());

        api.createRule(new CreateRuleRequest(
                "MAX_SINGLE_EXPENSE_250",
                RuleType.MAX_SINGLE_EXPENSE_AMOUNT,
                1,
                Map.of("maxAmount", "250")));

        api.createRule(new CreateRuleRequest(
                "NO_AIRFARE",
                RuleType.BLOCKED_FIELD_VALUE,
                2,
                Map.of("field", "EXPENSE_TYPE", "blockedValue", "airfare")));

        api.createRule(new CreateRuleRequest(
                "MEAL_PER_TRIP_200",
                RuleType.EXPENSE_TYPE_TOTAL_PER_TRIP_LIMIT,
                3,
                Map.of("expenseType", "meal", "maxTotal", "200")));

        List<Map<String, String>> expenses = List.of(
                Map.of(
                        "expense_id", "expense-1",
                        "trip_id", "trip-1",
                        "item_id", "item-1",
                        "expense_type", "meal",
                        "amount_usd", "120",
                        "vendor_type", "restaurant",
                        "vendor_name", "A"),
                Map.of(
                        "expense_id", "expense-2",
                        "trip_id", "trip-1",
                        "item_id", "item-2",
                        "expense_type", "meal",
                        "amount_usd", "110",
                        "vendor_type", "restaurant",
                        "vendor_name", "B"),
                Map.of(
                        "expense_id", "expense-3",
                        "trip_id", "trip-2",
                        "item_id", "item-3",
                        "expense_type", "airfare",
                        "amount_usd", "300",
                        "vendor_type", "airline",
                        "vendor_name", "C"));

        EvaluationReport report = api.evaluateExpenses(expenses);

        System.out.println("Created rules: " + api.listRules().size());
        System.out.println("Expense Violations");
        for (Map.Entry<String, List<RuleViolation>> entry : report.getExpenseViolations().entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().size());
        }
        System.out.println("Trip Violations");
        for (Map.Entry<String, List<RuleViolation>> entry : report.getTripViolations().entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().size());
        }
    }
}
