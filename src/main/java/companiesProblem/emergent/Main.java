package companiesProblem.emergent;

public final class Main {
    public static void main(String[] args) {
        LLMRouter router = new LLMRouter();

        simulate(router, Status.SUCCESS); // Claude healthy, stays Claude
        simulate(router, Status.FAILURE); // Claude fails, move to ClaudeDegraded

        // In degraded mode, suppose OpenAI fails too.
        router.recordResult(LLMType.OPENAI, Status.FAILURE);
        System.out.println("State after OpenAI failure: " + router.currentStateName());

        // In both degraded, if Claude recovers, go back to 100% Claude.
        router.recordResult(LLMType.CLAUDE, Status.SUCCESS);
        System.out.println("State after Claude recovery: " + router.currentStateName());
    }

    private static void simulate(LLMRouter router, Status simulatedStatus) {
        LLMType target = router.routeRequest();
        System.out.println("Current state: " + router.currentStateName() + ", routed to: " + target);

        if (target == LLMType.NONE) {
            System.out.println("Request dropped because both providers are degraded.");
            return;
        }

        router.recordResult(target, simulatedStatus);
        System.out.println("After result " + simulatedStatus + ", state is: " + router.currentStateName());
    }
}
