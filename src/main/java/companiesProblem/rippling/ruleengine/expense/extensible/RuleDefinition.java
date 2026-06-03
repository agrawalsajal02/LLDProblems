package companiesProblem.rippling.ruleengine.expense.extensible;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class RuleDefinition {
    private final String ruleId;
    private final String name;
    private final RuleType type;
    private final boolean enabled;
    private final int priority;
    private final Map<String, String> params;

    public RuleDefinition(String ruleId, String name, RuleType type, boolean enabled,
            int priority, Map<String, String> params) {
        this.ruleId = ruleId;
        this.name = name;
        this.type = type;
        this.enabled = enabled;
        this.priority = priority;
        this.params = new HashMap<>(params);
    }

    public String getRuleId() {
        return ruleId;
    }

    public String getName() {
        return name;
    }

    public RuleType getType() {
        return type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getPriority() {
        return priority;
    }

    public Map<String, String> getParams() {
        return Collections.unmodifiableMap(params);
    }
}
