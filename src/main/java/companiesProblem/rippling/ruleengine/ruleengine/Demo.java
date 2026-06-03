package companiesProblem.rippling.ruleengine.ruleengine;


/**
 * Interview demo: Loan eligibility rule engine.
 * Shows how all 5 classes wire together in ~20 lines.
 */
public class Demo {

    public static void main(String[] args) {

        // 1. Build rules
        Rule creditRule = new DefaultRule(
            "Credit Check", 1,
            new GreaterThanCondition("creditScore", 650),
            new SetValueAction("creditOk", true),
            new LogAction("Credit check passed")
        );

        Rule incomeRule = new DefaultRule(
            "Income Check", 2,
            new AndCondition(
                new GreaterThanCondition("income", 30000),
                new EqualsCondition("employed", true)
            ),
            new SetValueAction("incomeOk", true),
            new LogAction("Income check passed")
        );

        Rule approvalRule = new DefaultRule(
            "Final Approval", 3,
            new AndCondition(
                new EqualsCondition("creditOk", true),
                new EqualsCondition("incomeOk", true)
            ),
            new SetValueAction("status", "APPROVED"),
            new LogAction("Loan APPROVED")
        );

        // 2. Register in engine
        RuleEngine engine = new RuleEngine();
        engine.addRule(creditRule);
        engine.addRule(incomeRule);
        engine.addRule(approvalRule);

        // 3. Build context (input facts)
        Context ctx = new Context();
        ctx.set("creditScore", 720.0);
        ctx.set("income", 55000.0);
        ctx.set("employed", true);

        // 4. Fire
        System.out.println("=== fireAll ===");
        engine.fireAll(ctx);

        System.out.println("\nResult: " + ctx.get("status"));
    }
}
