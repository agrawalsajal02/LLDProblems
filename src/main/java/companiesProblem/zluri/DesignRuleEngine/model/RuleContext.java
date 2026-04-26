package companiesProblem.zluri.DesignRuleEngine.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class RuleContext {
    private final Map<String, Object> attributes;

    public RuleContext(Map<String, Object> attributes) {
        this.attributes = new HashMap<>(attributes);
    }

    public Object get(String key) {
        return attributes.get(key);
    }

    public String getString(String key) {
        Object value = attributes.get(key);
        return value == null ? null : value.toString();
    }

    public Double getNumber(String key) {
        Object value = attributes.get(key);
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return null;
    }

    public Map<String, Object> asMap() {
        return Collections.unmodifiableMap(attributes);
    }
}
