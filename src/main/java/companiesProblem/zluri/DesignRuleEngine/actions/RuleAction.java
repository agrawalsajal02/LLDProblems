package companiesProblem.zluri.DesignRuleEngine.actions;

import companiesProblem.zluri.DesignRuleEngine.model.RuleActionResult;
import companiesProblem.zluri.DesignRuleEngine.model.RuleContext;

public interface RuleAction {
    RuleActionResult execute(RuleContext context);

    String name();
}
