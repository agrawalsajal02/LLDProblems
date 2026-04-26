package companiesProblem.emergent;

final class BothDegradedState implements RouterState {
    @Override
    public LLMType chooseTarget(LLMRouter router) {
        int random = router.nextRandomPercent();
        if (random < 5) {
            return LLMType.OPENAI;
        }
        if (random < 10) {
            return LLMType.CLAUDE;
        }
        return LLMType.NONE;
    }

    @Override
    public void onResult(LLMRouter router, LLMType provider, Status status) {
        if (provider == LLMType.CLAUDE && status == Status.SUCCESS) {
            router.transitionTo(new ClaudeHealthyState());
            return;
        }

        if (provider == LLMType.OPENAI && status == Status.SUCCESS) {
            router.transitionTo(new ClaudeDegradedState());
        }
    }

    @Override
    public String name() {
        return "BOTH_DEGRADED";
    }
}
