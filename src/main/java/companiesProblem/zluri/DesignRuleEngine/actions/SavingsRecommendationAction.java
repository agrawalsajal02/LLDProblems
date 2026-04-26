package companiesProblem.zluri.DesignRuleEngine.actions;

import companiesProblem.zluri.DesignRuleEngine.model.RuleActionResult;
import companiesProblem.zluri.DesignRuleEngine.model.RuleContext;

public final class SavingsRecommendationAction implements RuleAction {
    private final String recommendation;

    public SavingsRecommendationAction(String recommendation) {
        this.recommendation = recommendation;
    }

    @Override
    public RuleActionResult execute(RuleContext context) {
        return new RuleActionResult(name(), true, "Recommendation: " + recommendation);
    }

    @Override
    public String name() {
        return "SavingsRecommendationAction";
    }
}
