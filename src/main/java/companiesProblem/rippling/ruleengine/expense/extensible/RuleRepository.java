package companiesProblem.rippling.ruleengine.expense.extensible;

import java.util.List;
import java.util.Optional;

public interface RuleRepository {
    void save(RuleDefinition ruleDefinition);

    Optional<RuleDefinition> findById(String ruleId);

    List<RuleDefinition> findAll();
}
