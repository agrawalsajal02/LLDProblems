package companiesProblem.zluri.DesignRuleEngine.monitoring;

import companiesProblem.zluri.DesignRuleEngine.model.RuleEvaluationResult;

public interface AuditLogger {
    void log(RuleEvaluationResult result);
}
