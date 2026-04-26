package companiesProblem.zluri.DesignRuleEngine.testing;

import companiesProblem.zluri.DesignRuleEngine.engine.RuleExecutionEngine;
import companiesProblem.zluri.DesignRuleEngine.model.RuleContext;
import companiesProblem.zluri.DesignRuleEngine.model.RuleEvaluationResult;
import companiesProblem.zluri.DesignRuleEngine.model.RuleExecutionMode;
import java.util.ArrayList;
import java.util.List;

public final class RuleTester {
    private final RuleExecutionEngine engine;

    public RuleTester(RuleExecutionEngine engine) {
        this.engine = engine;
    }

    public List<List<RuleEvaluationResult>> replayHistoricalData(
        List<RuleContext> historicalContexts,
        RuleExecutionMode mode
    ) {
        List<List<RuleEvaluationResult>> allResults = new ArrayList<>();
        for (RuleContext context : historicalContexts) {
            allResults.add(engine.evaluate(context, mode));
        }
        return allResults;
    }
}
