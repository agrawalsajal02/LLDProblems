package companiesProblem.rippling.ruleengine.expense;

public final class RuleViolation {
    private final String ruleName;
    private final String targetId;
    private final String message;

    public RuleViolation(String ruleName, String targetId, String message) {
        this.ruleName = ruleName;
        this.targetId = targetId;
        this.message = message;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getTargetId() {
        return targetId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return ruleName + ": " + message;
    }
}
