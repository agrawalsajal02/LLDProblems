package companiesProblem.rippling.ruleengine.expense;

import java.util.List;

public interface Rule {
    String name();

    void evaluate(List<Expense> expenses, EvaluationReport report);
}
