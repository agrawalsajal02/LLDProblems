package companiesProblem.rippling.ruleengine.ruleengine;

// ─── Interface ───────────────────────────────────────────────────────────────

public interface Action {
    void execute(Context ctx);
}

// ─── Concrete actions ────────────────────────────────────────────────────────

class SetValueAction implements Action {
    private final String key;
    private final Object value;

    SetValueAction(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void execute(Context ctx) {
        ctx.set(key, value);
    }
}

class LogAction implements Action {
    private final String message;

    LogAction(String message) {
        this.message = message;
    }

    @Override
    public void execute(Context ctx) {
        System.out.println("[LOG] " + message + " | ctx=" + ctx);
    }
}
