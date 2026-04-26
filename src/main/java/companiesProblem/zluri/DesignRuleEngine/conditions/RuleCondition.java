package companiesProblem.zluri.DesignRuleEngine.conditions;

import companiesProblem.zluri.DesignRuleEngine.model.RuleContext;

public interface RuleCondition {
    boolean matches(RuleContext context);

    String describe();
}
