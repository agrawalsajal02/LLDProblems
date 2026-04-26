package companiesProblem.zluri.DesignRuleEngine;

import companiesProblem.zluri.DesignRuleEngine.actions.NotificationAction;
import companiesProblem.zluri.DesignRuleEngine.actions.SavingsRecommendationAction;
import companiesProblem.zluri.DesignRuleEngine.conditions.AndCondition;
import companiesProblem.zluri.DesignRuleEngine.conditions.EqualsCondition;
import companiesProblem.zluri.DesignRuleEngine.conditions.NumericLessThanCondition;
import companiesProblem.zluri.DesignRuleEngine.engine.InMemoryRuleRepository;
import companiesProblem.zluri.DesignRuleEngine.engine.RuleExecutionEngine;
import companiesProblem.zluri.DesignRuleEngine.engine.RuleService;
import companiesProblem.zluri.DesignRuleEngine.model.Rule;
import companiesProblem.zluri.DesignRuleEngine.model.RuleContext;
import companiesProblem.zluri.DesignRuleEngine.model.RuleEvaluationResult;
import companiesProblem.zluri.DesignRuleEngine.model.RuleExecutionMode;
import companiesProblem.zluri.DesignRuleEngine.monitoring.InMemoryAuditLogger;
import companiesProblem.zluri.DesignRuleEngine.monitoring.InMemoryMetricsCollector;
import companiesProblem.zluri.DesignRuleEngine.testing.RuleTester;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main {
    // https://docs.google.com/document/d/1dppU_tHrvkYuQjWER39w57RdRhN2ockP0vTLnElfOCU/edit?tab=t.0
    public static void main(String[] args) {
        InMemoryRuleRepository repository = new InMemoryRuleRepository();
        RuleService ruleService = new RuleService(repository);
        InMemoryAuditLogger auditLogger = new InMemoryAuditLogger();
        InMemoryMetricsCollector metricsCollector = new InMemoryMetricsCollector();
        RuleExecutionEngine engine = new RuleExecutionEngine(repository, auditLogger, metricsCollector);

        Rule engineeringSavingsRule = new Rule(
            "rule-1",
            "Engineering Active Users Under Budget",
            1,
            RuleExecutionMode.REAL_TIME,
            new AndCondition(
                Arrays.asList(
                    new EqualsCondition("department", "Engineering"),
                    new EqualsCondition("user_status", "active"),
                    new NumericLessThanCondition("cost", 200)
                )
            ),
            Arrays.asList(
                new NotificationAction("User qualifies for optimization review"),
                new SavingsRecommendationAction("Review rightsizing options")
            ),
            Collections.emptyList()
        );

        Rule idleSpendRule = new Rule(
            "rule-2",
            "High Idle Monthly Spend",
            2,
            RuleExecutionMode.SCHEDULED,
            new companiesProblem.zluri.DesignRuleEngine.conditions.NumericGreaterThanCondition("monthly_idle_spend", 100),
            Arrays.asList(new NotificationAction("Idle spend crossed threshold")),
            Collections.singletonList("rule-1")
        );

        ruleService.createRule(engineeringSavingsRule);
        ruleService.createRule(idleSpendRule);

        Map<String, Object> data = new HashMap<>();
        data.put("department", "Engineering");
        data.put("user_status", "active");
        data.put("cost", 100);
        data.put("monthly_idle_spend", 150);

        RuleContext context = new RuleContext(data);

        List<RuleEvaluationResult> realTimeResults = engine.evaluate(context, RuleExecutionMode.REAL_TIME);
        System.out.println("Real-time results: " + realTimeResults.size());

        List<RuleEvaluationResult> scheduledResults = engine.evaluate(context, RuleExecutionMode.SCHEDULED);
        System.out.println("Scheduled results: " + scheduledResults.size());

        RuleTester tester = new RuleTester(engine);
        List<List<RuleEvaluationResult>> replayResults = tester.replayHistoricalData(
            Arrays.asList(context),
            RuleExecutionMode.REAL_TIME
        );
        System.out.println("Historical replay batches: " + replayResults.size());
        System.out.println("Audit logs captured: " + auditLogger.getLogs().size());
        System.out.println("Metrics tracked: " + metricsCollector.getMetricsByRuleId().size());
    }
}
