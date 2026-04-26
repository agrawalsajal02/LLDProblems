package companiesProblem.emergent;

final class ClaudeDegradedState implements RouterState {
    @Override
    public LLMType chooseTarget(LLMRouter router) {
        int random = router.nextRandomPercent();
        if (random < 5) {
            return LLMType.CLAUDE;
        }
        return LLMType.OPENAI;
    }

    @Override
    public void onResult(LLMRouter router, LLMType provider, Status status) {
        if (provider == LLMType.CLAUDE && status == Status.SUCCESS) {
            router.transitionTo(new ClaudeHealthyState());
            return;
        }

        if (provider == LLMType.OPENAI && status == Status.FAILURE) {
            router.transitionTo(new BothDegradedState());
        }
    }

    @Override
    public String name() {
        return "CLAUDE_DEGRADED";
    }
}
