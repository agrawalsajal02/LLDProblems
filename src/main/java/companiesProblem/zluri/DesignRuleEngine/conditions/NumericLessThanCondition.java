package companiesProblem.zluri.DesignRuleEngine.conditions;

import companiesProblem.zluri.DesignRuleEngine.model.RuleContext;

public final class NumericLessThanCondition implements RuleCondition {
    private final String fieldName;
    private final double threshold;

    public NumericLessThanCondition(String fieldName, double threshold) {
        this.fieldName = fieldName;
        this.threshold = threshold;
    }

    @Override
    public boolean matches(RuleContext context) {
        Double actual = context.getNumber(fieldName);
        return actual != null && actual < threshold;
    }

    @Override
    public String describe() {
        return fieldName + " < " + threshold;
    }
}
