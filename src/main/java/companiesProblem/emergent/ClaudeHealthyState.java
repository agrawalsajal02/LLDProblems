package companiesProblem.emergent;

final class ClaudeHealthyState implements RouterState {
    @Override
    public LLMType chooseTarget(LLMRouter router) {
        return LLMType.CLAUDE;
    }

    @Override
    public void onResult(LLMRouter router, LLMType provider, Status status) {
        if (provider == LLMType.CLAUDE && status == Status.FAILURE) {
            router.transitionTo(new ClaudeDegradedState());
        }
    }

    @Override
    public String name() {
        return "CLAUDE_HEALTHY";
    }
}
