package companiesProblem.rippling.ruleengine.expense.extensible;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class InMemoryRuleRepository implements RuleRepository {
    private final Map<String, RuleDefinition> rulesById = new LinkedHashMap<>();

    @Override
    public void save(RuleDefinition ruleDefinition) {
        rulesById.put(ruleDefinition.getRuleId(), ruleDefinition);
    }

    @Override
    public Optional<RuleDefinition> findById(String ruleId) {
        return Optional.ofNullable(rulesById.get(ruleId));
    }

    @Override
    public List<RuleDefinition> findAll() {
        return new ArrayList<>(rulesById.values());
    }
}
