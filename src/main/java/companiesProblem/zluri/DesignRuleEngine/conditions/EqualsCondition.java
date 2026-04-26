package companiesProblem.zluri.DesignRuleEngine.conditions;

import companiesProblem.zluri.DesignRuleEngine.model.RuleContext;

public final class EqualsCondition implements RuleCondition {
    private final String fieldName;
    private final String expectedValue;

    public EqualsCondition(String fieldName, String expectedValue) {
        this.fieldName = fieldName;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean matches(RuleContext context) {
        String actual = context.getString(fieldName);
        return expectedValue.equals(actual);
    }

    @Override
    public String describe() {
        return fieldName + " == " + expectedValue;
    }
}
