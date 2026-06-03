package companiesProblem.rippling.ruleengine.ruleengine;

import java.util.Arrays;
import java.util.List;

// ─── Interface ───────────────────────────────────────────────────────────────

public interface Rule {
    String getName();
    int getPriority();
    boolean evaluate(Context ctx);
    void execute(Context ctx);
}

// ─── Default implementation ──────────────────────────────────────────────────

class DefaultRule implements Rule {
    private final String name;
    private final int priority;
    private final Condition condition;
    private final List<Action> actions;

    DefaultRule(String name, int priority, Condition condition, Action... actions) {
        this.name = name;
        this.priority = priority;
        this.condition = condition;
        this.actions = Arrays.asList(actions);
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getPriority() { return priority; }

    @Override
    public boolean evaluate(Context ctx) {
        return condition.evaluate(ctx);
    }

    @Override
    public void execute(Context ctx) {
        if (condition.evaluate(ctx)) {
            actions.forEach(a -> a.execute(ctx));
        }
    }
}
