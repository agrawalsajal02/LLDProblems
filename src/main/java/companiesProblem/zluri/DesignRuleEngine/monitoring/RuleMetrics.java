package companiesProblem.zluri.DesignRuleEngine.monitoring;

public final class RuleMetrics {
    private int totalExecutions;
    private int matches;
    private int failures;

    public void recordExecution(boolean matched, boolean success) {
        totalExecutions++;
        if (matched) {
            matches++;
        }
        if (!success) {
            failures++;
        }
    }

    public int getTotalExecutions() {
        return totalExecutions;
    }

    public int getMatches() {
        return matches;
    }

    public int getFailures() {
        return failures;
    }
}
