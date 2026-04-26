package companiesProblem.zluri.DesignRuleEngine.engine;

import companiesProblem.zluri.DesignRuleEngine.model.Rule;
import java.util.List;

public interface RuleRepository {
    void create(Rule rule);

    Rule getById(String ruleId);

    List<Rule> getAll();

    void update(Rule rule);

    void delete(String ruleId);
}
