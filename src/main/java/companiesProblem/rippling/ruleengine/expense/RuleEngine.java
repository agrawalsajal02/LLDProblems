package companiesProblem.rippling.ruleengine.expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class RuleEngine {
    public EvaluationReport evaluateRules(List<Rule> rules, List<Map<String, String>> rawExpenses) {
        List<Expense> expenses = new ArrayList<>();
        for (Map<String, String> rawExpense : rawExpenses) {
            expenses.add(Expense.fromMap(rawExpense));
        }
        return evaluate(expenses, rules);
    }

    public EvaluationReport evaluate(List<Expense> expenses, List<Rule> rules) {
        EvaluationReport report = new EvaluationReport();
        for (Expense expense : expenses) {
            report.addExpense(expense.getExpenseId());
            report.addTrip(expense.getTripId());
        }

        for (Rule rule : rules) {
            rule.evaluate(expenses, report);
        }

        return report;
    }
}
