package companiesProblem.zluri.DesignRuleEngine.engine;

import companiesProblem.zluri.DesignRuleEngine.actions.RuleAction;
import companiesProblem.zluri.DesignRuleEngine.model.Rule;
import companiesProblem.zluri.DesignRuleEngine.model.RuleActionResult;
import companiesProblem.zluri.DesignRuleEngine.model.RuleContext;
import companiesProblem.zluri.DesignRuleEngine.model.RuleEvaluationResult;
import companiesProblem.zluri.DesignRuleEngine.model.RuleExecutionMode;
import companiesProblem.zluri.DesignRuleEngine.model.RuleStatus;
import companiesProblem.zluri.DesignRuleEngine.monitoring.AuditLogger;
import companiesProblem.zluri.DesignRuleEngine.monitoring.MetricsCollector;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class RuleExecutionEngine {
    private final RuleRepository repository;
    private final AuditLogger auditLogger;
    private final MetricsCollector metricsCollector;

    public RuleExecutionEngine(
        RuleRepository repository,
        AuditLogger auditLogger,
        MetricsCollector metricsCollector
    ) {
        this.repository = repository;
        this.auditLogger = auditLogger;
        this.metricsCollector = metricsCollector;
    }

    public List<RuleEvaluationResult> evaluate(RuleContext context, RuleExecutionMode mode) {
        List<Rule> enabledRules = new ArrayList<>();
        for (Rule rule : repository.getAll()) {
            if (rule.getStatus() == RuleStatus.ENABLED && rule.getExecutionMode() == mode) {
                enabledRules.add(rule);
            }
        }

        List<Rule> orderedRules = orderRules(enabledRules);
        List<RuleEvaluationResult> results = new ArrayList<>();

        for (Rule rule : orderedRules) {
            RuleEvaluationResult result = evaluateSingleRule(rule, context);
            auditLogger.log(result);
            metricsCollector.record(result);
            results.add(result);
        }
        return results;
    }

    private RuleEvaluationResult evaluateSingleRule(Rule rule, RuleContext context) {
        try {
            boolean matched = rule.getCondition().matches(context);
            List<RuleActionResult> actionResults = new ArrayList<>();

            if (matched) {
                for (RuleAction action : rule.getActions()) {
                    actionResults.add(action.execute(context));
                }
            }

            return new RuleEvaluationResult(rule.getRuleId(), matched, true, null, actionResults);
        } catch (Exception exception) {
            return new RuleEvaluationResult(
                rule.getRuleId(),
                false,
                false,
                exception.getMessage(),
                new ArrayList<>()
            );
        }
    }

    private List<Rule> orderRules(List<Rule> rules) {
        Map<String, Rule> byId = new HashMap<>();
        for (Rule rule : rules) {
            byId.put(rule.getRuleId(), rule);
        }

        List<Rule> ordered = new ArrayList<>();
        Set<String> visiting = new HashSet<>();
        Set<String> visited = new HashSet<>();

        rules.sort(Comparator.comparingInt(Rule::getPriority));
        for (Rule rule : rules) {
            dfs(rule, byId, visiting, visited, ordered);
        }
        return ordered;
    }

    private void dfs(
        Rule rule,
        Map<String, Rule> byId,
        Set<String> visiting,
        Set<String> visited,
        List<Rule> ordered
    ) {
        if (visited.contains(rule.getRuleId())) {
            return;
        }
        if (visiting.contains(rule.getRuleId())) {
            throw new IllegalStateException("Cycle found in rule dependencies for rule " + rule.getRuleId());
        }

        visiting.add(rule.getRuleId());
        for (String dependencyId : rule.getDependsOnRuleIds()) {
            Rule dependency = byId.get(dependencyId);
            if (dependency != null) {
                dfs(dependency, byId, visiting, visited, ordered);
            }
        }
        visiting.remove(rule.getRuleId());
        visited.add(rule.getRuleId());
        ordered.add(rule);
    }
}
