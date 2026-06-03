package companiesProblem.rippling.ruleengine.expense.extensible;

import companiesProblem.rippling.ruleengine.expense.Rule;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class RuleService {
    private final RuleRepository ruleRepository;
    private final RuleFactory ruleFactory;

    public RuleService(RuleRepository ruleRepository, RuleFactory ruleFactory) {
        this.ruleRepository = ruleRepository;
        this.ruleFactory = ruleFactory;
    }

    public RuleDefinition createRule(CreateRuleRequest request) {
        RuleDefinition definition = new RuleDefinition(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getType(),
                true,
                request.getPriority(),
                request.getParams());

        ruleFactory.createRule(definition);
        ruleRepository.save(definition);
        return definition;
    }

    public List<RuleDefinition> listRules() {
        return ruleRepository.findAll();
    }

    public List<Rule> getEnabledRules() {
        return ruleRepository.findAll().stream()
                .filter(RuleDefinition::isEnabled)
                .sorted(Comparator.comparingInt(RuleDefinition::getPriority))
                .map(ruleFactory::createRule)
                .collect(Collectors.toList());
    }
}
