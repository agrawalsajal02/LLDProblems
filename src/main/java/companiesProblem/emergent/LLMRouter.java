package companiesProblem.emergent;

import java.util.concurrent.ThreadLocalRandom;

public final class LLMRouter {
    private RouterState currentState;

    public LLMRouter() {
        this.currentState = new ClaudeHealthyState();
    }

    public LLMType routeRequest() {
        return currentState.chooseTarget(this);
    }

    public void recordResult(LLMType provider, Status status) {
        if (provider == null || provider == LLMType.NONE || status == null) {
            return;
        }
        currentState.onResult(this, provider, status);
    }

    public String currentStateName() {
        return currentState.name();
    }

    void transitionTo(RouterState nextState) {
        this.currentState = nextState;
    }

    int nextRandomPercent() {
        return ThreadLocalRandom.current().nextInt(100);
    }
}
