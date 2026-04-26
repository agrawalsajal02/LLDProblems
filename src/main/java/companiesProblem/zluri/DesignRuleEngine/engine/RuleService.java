package companiesProblem.zluri.DesignRuleEngine.engine;

import companiesProblem.zluri.DesignRuleEngine.model.Rule;
import java.util.List;

public final class RuleService {
    private final RuleRepository repository;

    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }

    public void createRule(Rule rule) {
        repository.create(rule);
    }

    public Rule getRule(String ruleId) {
        return repository.getById(ruleId);
    }

    public List<Rule> getAllRules() {
        return repository.getAll();
    }

    public void updateRule(Rule rule) {
        repository.update(rule);
    }

    public void deleteRule(String ruleId) {
        repository.delete(ruleId);
    }

    public void enableRule(String ruleId) {
        Rule rule = repository.getById(ruleId);
        if (rule != null) {
            rule.enable();
        }
    }

    public void disableRule(String ruleId) {
        Rule rule = repository.getById(ruleId);
        if (rule != null) {
            rule.disable();
        }
    }
}
