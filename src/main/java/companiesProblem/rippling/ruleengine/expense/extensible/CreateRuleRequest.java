package companiesProblem.rippling.ruleengine.expense.extensible;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CreateRuleRequest {
    private final String name;
    private final RuleType type;
    private final int priority;
    private final Map<String, String> params;

    public CreateRuleRequest(String name, RuleType type, int priority, Map<String, String> params) {
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.params = new HashMap<>(params);
    }

    public String getName() {
        return name;
    }

    public RuleType getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public Map<String, String> getParams() {
        return Collections.unmodifiableMap(params);
    }
}
