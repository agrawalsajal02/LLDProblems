package companiesProblem.zluri.DesignRuleEngine.engine;

import companiesProblem.zluri.DesignRuleEngine.model.Rule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class InMemoryRuleRepository implements RuleRepository {
    private final Map<String, Rule> rulesById = new HashMap<>();

    @Override
    public void create(Rule rule) {
        rulesById.put(rule.getRuleId(), rule);
    }

    @Override
    public Rule getById(String ruleId) {
        return rulesById.get(ruleId);
    }

    @Override
    public List<Rule> getAll() {
        return new ArrayList<>(rulesById.values());
    }

    @Override
    public void update(Rule rule) {
        rulesById.put(rule.getRuleId(), rule);
    }

    @Override
    public void delete(String ruleId) {
        rulesById.remove(ruleId);
    }
}
