package companiesProblem.rippling.ruleengine.ruleengine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Holds all rules. Fires them against a Context in priority order.
 *
 * Two strategies:
 *   fireAll   — run every rule regardless of match
 *   fireFirst — stop after the first match
 */
public class RuleEngine {

    private final List<Rule> rules = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
        rules.sort(Comparator.comparingInt(Rule::getPriority));
    }

    // Run every rule
    public void fireAll(Context ctx) {
        for (Rule rule : rules) {
            System.out.println("Evaluating: " + rule.getName());
            rule.execute(ctx);
        }
    }

    // Stop after first match
    public void fireFirst(Context ctx) {
        for (Rule rule : rules) {
            System.out.println("Evaluating: " + rule.getName());
            if (rule.evaluate(ctx)) {
                rule.execute(ctx);
                return;
            }
        }
    }
}
