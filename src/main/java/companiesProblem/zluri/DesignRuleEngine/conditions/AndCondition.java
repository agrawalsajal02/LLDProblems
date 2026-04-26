package companiesProblem.zluri.DesignRuleEngine.conditions;

import companiesProblem.zluri.DesignRuleEngine.model.RuleContext;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public final class AndCondition implements RuleCondition {
    private final List<RuleCondition> conditions;

    public AndCondition(List<RuleCondition> conditions) {
        this.conditions = new ArrayList<>(conditions);
    }

    @Override
    public boolean matches(RuleContext context) {
        for (RuleCondition condition : conditions) {
            if (!condition.matches(context)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String describe() {
        StringJoiner joiner = new StringJoiner(" AND ");
        for (RuleCondition condition : conditions) {
            joiner.add(condition.describe());
        }
        return joiner.toString();
    }
}
