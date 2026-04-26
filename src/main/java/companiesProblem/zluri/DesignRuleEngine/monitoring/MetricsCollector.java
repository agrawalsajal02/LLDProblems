package companiesProblem.zluri.DesignRuleEngine.monitoring;

import companiesProblem.zluri.DesignRuleEngine.model.RuleEvaluationResult;

public interface MetricsCollector {
    void record(RuleEvaluationResult result);
}
