package companiesProblem.emergent;

interface RouterState {
    LLMType chooseTarget(LLMRouter router);

    void onResult(LLMRouter router, LLMType provider, Status status);

    String name();
}
