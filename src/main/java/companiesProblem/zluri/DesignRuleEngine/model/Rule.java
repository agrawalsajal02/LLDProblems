package companiesProblem.zluri.DesignRuleEngine.model;

import companiesProblem.zluri.DesignRuleEngine.actions.RuleAction;
import companiesProblem.zluri.DesignRuleEngine.conditions.RuleCondition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Rule {
    private final String ruleId;
    private final String name;
    private final int priority;
    private final RuleExecutionMode executionMode;
    private final RuleCondition condition;
    private final List<RuleAction> actions;
    private final List<String> dependsOnRuleIds;
    private RuleStatus status;

    public Rule(
        String ruleId,
        String name,
        int priority,
        RuleExecutionMode executionMode,
        RuleCondition condition,
        List<RuleAction> actions,
        List<String> dependsOnRuleIds
    ) {
        this.ruleId = ruleId;
        this.name = name;
        this.priority = priority;
        this.executionMode = executionMode;
        this.condition = condition;
        this.actions = new ArrayList<>(actions);
        this.dependsOnRuleIds = new ArrayList<>(dependsOnRuleIds);
        this.status = RuleStatus.ENABLED;
    }

    public String getRuleId() {
        return ruleId;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public RuleExecutionMode getExecutionMode() {
        return executionMode;
    }

    public RuleCondition getCondition() {
        return condition;
    }

    public List<RuleAction> getActions() {
        return Collections.unmodifiableList(actions);
    }

    public List<String> getDependsOnRuleIds() {
        return Collections.unmodifiableList(dependsOnRuleIds);
    }

    public RuleStatus getStatus() {
        return status;
    }

    public void enable() {
        this.status = RuleStatus.ENABLED;
    }

    public void disable() {
        this.status = RuleStatus.DISABLED;
    }
}
