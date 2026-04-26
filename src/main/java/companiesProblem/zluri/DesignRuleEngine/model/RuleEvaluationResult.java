package companiesProblem.zluri.DesignRuleEngine.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RuleEvaluationResult {
    private final String ruleId;
    private final boolean matched;
    private final boolean success;
    private final String errorMessage;
    private final List<RuleActionResult> actionResults;

    public RuleEvaluationResult(
        String ruleId,
        boolean matched,
        boolean success,
        String errorMessage,
        List<RuleActionResult> actionResults
    ) {
        this.ruleId = ruleId;
        this.matched = matched;
        this.success = success;
        this.errorMessage = errorMessage;
        this.actionResults = new ArrayList<>(actionResults);
    }

    public String getRuleId() {
        return ruleId;
    }

    public boolean isMatched() {
        return matched;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<RuleActionResult> getActionResults() {
        return Collections.unmodifiableList(actionResults);
    }
}
