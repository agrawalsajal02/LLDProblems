package companiesProblem.zluri.DesignRuleEngine.model;

public final class RuleActionResult {
    private final String actionName;
    private final boolean success;
    private final String message;

    public RuleActionResult(String actionName, boolean success, String message) {
        this.actionName = actionName;
        this.success = success;
        this.message = message;
    }

    public String getActionName() {
        return actionName;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
