package companiesProblem.zluri.DesignRuleEngine.monitoring;

import companiesProblem.zluri.DesignRuleEngine.model.RuleEvaluationResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class InMemoryAuditLogger implements AuditLogger {
    private final List<RuleEvaluationResult> logs = new ArrayList<>();

    @Override
    public void log(RuleEvaluationResult result) {
        logs.add(result);
    }

    public List<RuleEvaluationResult> getLogs() {
        return Collections.unmodifiableList(logs);
    }
}
