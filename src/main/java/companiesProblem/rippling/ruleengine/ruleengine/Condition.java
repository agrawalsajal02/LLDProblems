package companiesProblem.rippling.ruleengine.ruleengine;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// ─── Interface ───────────────────────────────────────────────────────────────

public interface Condition {
    boolean evaluate(Context ctx);
}

// ─── Leaf conditions ─────────────────────────────────────────────────────────

class EqualsCondition implements Condition {
    private final String key;
    private final Object expected;

    EqualsCondition(String key, Object expected) {
        this.key = key;
        this.expected = expected;
    }

    @Override
    public boolean evaluate(Context ctx) {
        return Objects.equals(ctx.get(key), expected);
    }
}

class GreaterThanCondition implements Condition {
    private final String key;
    private final double threshold;

    GreaterThanCondition(String key, double threshold) {
        this.key = key;
        this.threshold = threshold;
    }

    @Override
    public boolean evaluate(Context ctx) {
        Number val = ctx.get(key);
        return val != null && val.doubleValue() > threshold;
    }
}

// ─── Composite conditions ────────────────────────────────────────────────────

class AndCondition implements Condition {
    private final List<Condition> conditions;

    AndCondition(Condition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public boolean evaluate(Context ctx) {
        return conditions.stream().allMatch(c -> c.evaluate(ctx));
    }
}

class OrCondition implements Condition {
    private final List<Condition> conditions;

    OrCondition(Condition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public boolean evaluate(Context ctx) {
        return conditions.stream().anyMatch(c -> c.evaluate(ctx));
    }
}
