package companiesProblem.zluri.DesignRuleEngine.actions;

import companiesProblem.zluri.DesignRuleEngine.model.RuleActionResult;
import companiesProblem.zluri.DesignRuleEngine.model.RuleContext;

public final class NotificationAction implements RuleAction {
    private final String messageTemplate;

    public NotificationAction(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @Override
    public RuleActionResult execute(RuleContext context) {
        return new RuleActionResult(
            name(),
            true,
            "Notification sent: " + messageTemplate + " context=" + context.asMap()
        );
    }

    @Override
    public String name() {
        return "NotificationAction";
    }
}
