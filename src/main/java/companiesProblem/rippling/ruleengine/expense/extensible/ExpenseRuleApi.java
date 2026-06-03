package companiesProblem.rippling.ruleengine.expense.extensible;

import companiesProblem.rippling.ruleengine.expense.EvaluationReport;
import companiesProblem.rippling.ruleengine.expense.RuleEngine;

import java.util.List;
import java.util.Map;

public final class ExpenseRuleApi {
    private final RuleService ruleService;
    private final RuleEngine ruleEngine;

    public ExpenseRuleApi(RuleService ruleService, RuleEngine ruleEngine) {
        this.ruleService = ruleService;
        this.ruleEngine = ruleEngine;
    }

    public RuleDefinition createRule(CreateRuleRequest request) {
        return ruleService.createRule(request);
    }

    public List<RuleDefinition> listRules() {
        return ruleService.listRules();
    }

    public EvaluationReport evaluateExpenses(List<Map<String, String>> rawExpenses) {
        return ruleEngine.evaluateRules(ruleService.getEnabledRules(), rawExpenses);
    }
}
