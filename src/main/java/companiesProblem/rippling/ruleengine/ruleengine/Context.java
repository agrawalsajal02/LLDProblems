package companiesProblem.rippling.ruleengine.ruleengine;

import java.util.HashMap;
import java.util.Map;

/**
 * Shared data bag passed through the entire rule evaluation.
 * Rules READ from it, Actions WRITE to it.
 */
public class Context {

    private final Map<String, Object> data = new HashMap<>();

    public void set(String key, Object value) {
        data.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) data.get(key);
    }

    public boolean has(String key) {
        return data.containsKey(key);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
