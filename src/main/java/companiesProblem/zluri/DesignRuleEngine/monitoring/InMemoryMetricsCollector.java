package companiesProblem.zluri.DesignRuleEngine.monitoring;

import companiesProblem.zluri.DesignRuleEngine.model.RuleEvaluationResult;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class InMemoryMetricsCollector implements MetricsCollector {
    private final Map<String, RuleMetrics> metricsByRuleId = new HashMap<>();

    @Override
    public void record(RuleEvaluationResult result) {
        metricsByRuleId
            .computeIfAbsent(result.getRuleId(), ignored -> new RuleMetrics())
            .recordExecution(result.isMatched(), result.isSuccess());
    }

    public Map<String, RuleMetrics> getMetricsByRuleId() {
        return Collections.unmodifiableMap(metricsByRuleId);
    }
}
