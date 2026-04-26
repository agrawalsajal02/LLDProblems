package companiesProblem.rippling.ruleengine;//package rippling.ruleengine;
//
//import java.math.BigDecimal;
//import java.util.*;
//import java.util.function.Function;
//
//
//////:
////Sure — here’s a **clear, complete, interview-style problem statement** for the question you mentioned (including both base and follow-up parts):
////
////        ---
////
////        ## 🧾 Problem Statement — Expense Validation System
////
////        You are asked to design and implement a **rule-based expense validation system**.
////
////        The system receives:
////
////        1. A **list of expenses**, each representing an item charged during a trip.
////        2. A **list of validation rules** that define constraints or restrictions on expenses.
////
////        Your task is to evaluate all the rules against the list of expenses and report **which rules are violated**, along with the **expense(s)** that caused the violation.
////
////        ---
////
////        ### 📘 Expense Structure
////
////        Each expense contains the following fields:
////
////        ```json
////{
////    "expenseId": "1",
////        "itemId": "Item1",
////        "expenseType": "Food",
////        "amountInUsd": 250,
////        "sellerType": "restaurant",
////        "sellerName": "ABC restaurant"
////}
////```
////
////        * **expenseId** – unique identifier of the expense
////* **itemId** – item or service identifier
////* **expenseType** – type/category of expense (e.g., "Food", "Transport", "Entertainment")
////* **amountInUsd** – monetary amount for the expense
////* **sellerType** – type of seller (e.g., "restaurant", "hotel", "cab")
////* **sellerName** – name of the vendor/seller
////
////        You can assume this data is already parsed into an in-memory list of `Expense` objects.
////
////---
////
////        ### 📜 Rule Structure
////
////        Rules define what constraints the expenses must satisfy.
////
////        Examples of rules:
////
////        1. **Trip Total Cap**
////
////        > Total expense on the trip should not exceed $175.
////
////2. **Seller Type Limit**
////
////        > The total expense for seller type `"restaurant"` should not exceed $45.
////
////        3. **Forbidden Expense Type**
////
////        > The `"Entertainment"` expense type should not be charged at all.
////
////        4. **Category-based Cap**
////
////        > The total expense on `"Food"` during a trip should not exceed $120.
////
////---
////
////        ### ⚙️ Function to Implement
////
////        You must implement the following function:
////
////        ```java
////        List<RuleViolation> evaluateRules(List<Rule> rules, List<Expense> expenses)
////```
////
////        #### Requirements:
////
////        * Evaluate all expenses sequentially against all given rules.
////        * Return a list of **violations**, where each violation contains:
////
////        * The violated **rule ID**
////        * The **expense ID** that triggered the violation
////  * A short **message** explaining why it failed.
////
////        If all expenses satisfy all rules, return an empty list.
////
////---
////
////        ### 🧩 Follow-Up Requirements
////
////        Your solution should be:
////
////        1. **Extensible** — New rule types (e.g., per-day cap, per-seller cap, per-category limit) should be easily added without modifying the main evaluator logic.
////        2. **Aggregate-aware** — Some rules depend on cumulative totals (e.g., total food spend).
////        3. **Dynamic** — The rule definitions could come from configuration (e.g., JSON, database) rather than being hardcoded.
////
////        ---
////
////        ### ✅ Example Input
////
////**Expenses**
////
////        | expenseId | expenseType   | amountInUsd | sellerType | sellerName     |
////        | --------- | ------------- | ----------- | ---------- | -------------- |
////        | 1         | Food          | 100         | restaurant | ABC restaurant |
////        | 2         | Transport     | 50          | cab        | XYZ Cabs       |
////        | 3         | Entertainment | 60          | theatre    | CineMax        |
////        | 4         | Food          | 70          | restaurant | PQR Diner      |
////
////        **Rules**
////
////        1. Trip total ≤ 175
////        2. SellerType `"restaurant"` total ≤ 45
////        3. ExpenseType `"Entertainment"` not allowed
////4. ExpenseType `"Food"` total ≤ 120
////
////        ---
////
////        ### 🚨 Expected Output
////
////```
////        Violation{ruleId='TRIP_TOTAL_CAP', expenseId='4', message='Trip total would be 280 > cap 175'}
////        Violation{ruleId='SELLER_RESTAURANT_CAP', expenseId='1', message='Seller type restaurant total would be 100 > cap 45'}
////        Violation{ruleId='NO_ENTERTAINMENT', expenseId='3', message='Forbidden expense type Entertainment'}
////        Violation{ruleId='FOOD_TOTAL_CAP', expenseId='4', message='Expense type Food total would be 170 > cap 120'}
////```
////
////        ---
////
////        ### 💡 Evaluation Criteria
////
////* Correct rule evaluation and aggregation logic
////* Clean object-oriented design (e.g., interfaces like `Rule`)
////* Extensibility to handle new rules without modifying existing code
////* Clear, meaningful output for violations
////
////---
////
////        Would you like me to also show a **UML-style diagram** of how `Expense`, `Rule`, `RuleViolation`, and `RuleEngine` interact? It helps make this design look very strong in interviews.
////
//
///** ======== Domain ======== */
//final class Expense {
//    final String expenseId;
//    final String itemId;
//    final String expenseType; // e.g., "Food", "Entertainment"
//    final BigDecimal amountUsd;
//    final String sellerType;  // e.g., "restaurant"
//    final String sellerName;
//
//    Expense(String expenseId, String itemId, String expenseType,
//            BigDecimal amountUsd, String sellerType, String sellerName) {
//        this.expenseId = Objects.requireNonNull(expenseId);
//        this.itemId = Objects.requireNonNull(itemId);
//        this.expenseType = Objects.requireNonNull(expenseType);
//        this.amountUsd = Objects.requireNonNull(amountUsd);
//        this.sellerType = Objects.requireNonNull(sellerType);
//        this.sellerName = Objects.requireNonNull(sellerName);
//    }
//}
//
///** What we report when a rule is broken. */
//final class RuleViolation {
//    final String ruleId;
//    final String expenseId;
//    final String message;
//
//    RuleViolation(String ruleId, String expenseId, String message) {
//        this.ruleId = ruleId;
//        this.expenseId = expenseId;
//        this.message = message;
//    }
//
//    @Override public String toString() {
//        return "Violation{ruleId='%s', expenseId='%s', message='%s'}"
//                .formatted(ruleId, expenseId, message);
//    }
//}
//
///** ======== Evaluation context (running aggregates) ======== */
//final class RunningTotals {
//    BigDecimal tripTotal = BigDecimal.ZERO;
//    final Map<String, BigDecimal> bySellerType = new HashMap<>();
//    final Map<String, BigDecimal> byExpenseType = new HashMap<>();
//
//    BigDecimal nextTripTotal(BigDecimal add) {
//        return tripTotal.add(add);
//    }
//
//    BigDecimal nextBy(Map<String, BigDecimal> map, String key, BigDecimal add) {
//        return map.getOrDefault(key, BigDecimal.ZERO).add(add);
//    }
//
//    void commit(Expense e) {
//        // NOTE: If business wants to "reject" violating expenses,
//        // you would NOT commit on violation. Here we commit regardless
//        // so subsequent checks still see the full stream.
//        tripTotal = tripTotal.add(e.amountUsd);
//        bySellerType.merge(e.sellerType, e.amountUsd, BigDecimal::add);
//        byExpenseType.merge(e.expenseType, e.amountUsd, BigDecimal::add);
//    }
//}
//
///** ======== Rule SPI ======== */
//interface Rule {
//    String id();
//    String description();
//
//    /**
//     * Called BEFORE committing this expense into the aggregates.
//     * Return empty if OK, or a violation if this expense would break the rule.
//     */
//    Optional<RuleViolation> evaluate(Expense e, RunningTotals totals);
//}
//
///** Total trip cap: "Total expense should not be > X" */
//final class TripTotalCapRule implements Rule {
//    private final String id;
//    private final BigDecimal cap;
//
//    TripTotalCapRule(String id, BigDecimal cap) {
//        this.id = id;
//        this.cap = cap;
//    }
//
//    @Override public String id() { return id; }
//    @Override public String description() {
//        return "Trip total must not exceed " + cap;
//    }
//
//    @Override
//    public Optional<RuleViolation> evaluate(Expense e, RunningTotals totals) {
//        BigDecimal wouldBe = totals.nextTripTotal(e.amountUsd);
//        if (wouldBe.compareTo(cap) > 0) {
//            String msg = "Trip total would be %s > cap %s (expenseId=%s, amount=%s)"
//                    .formatted(wouldBe, cap, e.expenseId, e.amountUsd);
//            return Optional.of(new RuleViolation(id, e.expenseId, msg));
//        }
//        return Optional.empty();
//    }
//}
//
///**
// * Aggregation cap by key (e.g., by sellerType or by expenseType).
// * Example use:
// *   new AggregationCapRule("SELLER_RESTAURANT_CAP", e -> e.sellerType, "restaurant", new BigDecimal("45"))
// *   new AggregationCapRule("FOOD_TOTAL_CAP", e -> e.expenseType, "Food", new BigDecimal("100"))
// */
//final class AggregationCapRule implements Rule {
//    enum Dimension { SELLER_TYPE, EXPENSE_TYPE }
//
//    private final String id;
//    private final Dimension dimension;
//    private final Function<Expense, String> keyFn;
//    private final String keyValue;
//    private final BigDecimal cap;
//
//    AggregationCapRule(String id,
//                       Dimension dimension,
//                       Function<Expense, String> keyFn,
//                       String keyValue,
//                       BigDecimal cap) {
//        this.id = id;
//        this.dimension = dimension;
//        this.keyFn = keyFn;
//        this.keyValue = keyValue;
//        this.cap = cap;
//    }
//
//    @Override public String id() { return id; }
//    @Override public String description() {
//        return "%s '%s' sum must not exceed %s"
//                .formatted(dimension, keyValue, cap);
//    }
//
//    @Override
//    public Optional<RuleViolation> evaluate(Expense e, RunningTotals totals) {
//        String k = keyFn.apply(e);
//        if (!keyValue.equalsIgnoreCase(k)) return Optional.empty(); // not this bucket
//
//        Map<String, BigDecimal> map =
//                (dimension == Dimension.SELLER_TYPE) ? totals.bySellerType : totals.byExpenseType;
//
//        BigDecimal wouldBe = totals.nextBy(map, keyValue, e.amountUsd);
//        if (wouldBe.compareTo(cap) > 0) {
//            String msg = "%s '%s' total would be %s > cap %s (expenseId=%s, amount=%s)"
//                    .formatted(dimension, keyValue, wouldBe, cap, e.expenseId, e.amountUsd);
//            return Optional.of(new RuleViolation(id, e.expenseId, msg));
//        }
//        return Optional.empty();
//    }
//}
//
///** Forbids charging any expense of a given type ("Entertainment expense type should not be charged"). */
//final class ForbiddenExpenseTypeRule implements Rule {
//    private final String id;
//    private final String forbiddenType;
//
//    ForbiddenExpenseTypeRule(String id, String forbiddenType) {
//        this.id = id;
//        this.forbiddenType = forbiddenType;
//    }
//
//    @Override public String id() { return id; }
//    @Override public String description() {
//        return "Expense type '" + forbiddenType + "' is not allowed";
//    }
//
//    @Override
//    public Optional<RuleViolation> evaluate(Expense e, RunningTotals totals) {
//        if (forbiddenType.equalsIgnoreCase(e.expenseType)) {
//            String msg = "Forbidden expense type '%s' (expenseId=%s, amount=%s)"
//                    .formatted(e.expenseType, e.expenseId, e.amountUsd);
//            return Optional.of(new RuleViolation(id, e.expenseId, msg));
//        }
//        return Optional.empty();
//    }
//}
//
///** ======== Evaluator ======== */
//final class RuleEngine {
//
//    /**
//     * Evaluate rules against the expense stream.
//     * Returns all violations (possibly multiple per expense).
//     *
//     * NOTE on semantics: we "commit" each expense into the running totals
//     * regardless of violations. If you want "reject on violation", remove the commit
//     * for violating rules or short-circuit once a violation is found.
//     */
//    static List<RuleViolation> evaluateRules(List<Rule> rules, List<Expense> expenses) {
//        Objects.requireNonNull(rules);
//        Objects.requireNonNull(expenses);
//
//        List<RuleViolation> out = new ArrayList<>();
//        RunningTotals totals = new RunningTotals();
//
//        for (Expense e : expenses) {
//            for (Rule r : rules) {
//                r.evaluate(e, totals).ifPresent(out::add);
//            }
//            totals.commit(e);
//        }
//        return out;
//    }
//}
//
///** ======== Demo / Example ======== */
//public class ExpensesValidationDemo {
//    public static void main(String[] args) {
//        // Sample expenses (same shape as your example)
//        List<Expense> expenses = List.of(
//                new Expense("1", "Item1", "Food",          new BigDecimal("100"), "restaurant", "ABC restaurant"),
//                new Expense("2", "Item2", "Transport",     new BigDecimal("50"),  "cab",        "XYZ Cabs"),
//                new Expense("3", "Item3", "Entertainment", new BigDecimal("60"),  "theatre",    "CineMax"),
//                new Expense("4", "Item4", "Food",          new BigDecimal("70"),  "restaurant", "PQR Diner")
//        );
//
//        // Rules:
//        // 1) Trip total must not exceed 175
//        Rule tripCap = new TripTotalCapRule("TRIP_TOTAL_CAP", new BigDecimal("175"));
//
//        // 2) Seller type 'restaurant' must not exceed 45 total
//        Rule restaurantCap = new AggregationCapRule(
//                "SELLER_RESTAURANT_CAP",
//                AggregationCapRule.Dimension.SELLER_TYPE,
//                e -> e.sellerType,
//                "restaurant",
//                new BigDecimal("45")
//        );
//
//        // 3) Expense type 'Entertainment' is forbidden
//        Rule forbidEntertainment = new ForbiddenExpenseTypeRule("NO_ENTERTAINMENT", "Entertainment");
//
//        // (Optional) Example: cap Food category to 120 on trip
//        Rule foodCap = new AggregationCapRule(
//                "FOOD_TOTAL_CAP",
//                AggregationCapRule.Dimension.EXPENSE_TYPE,
//                e -> e.expenseType,
//                "Food",
//                new BigDecimal("120")
//        );
//
//        List<Rule> rules = List.of(tripCap, restaurantCap, forbidEntertainment, foodCap);
//
//        List<RuleViolation> violations = RuleEngine.evaluateRules(rules, expenses);
//        if (violations.isEmpty()) {
//            System.out.println("All expenses satisfy all rules.");
//        } else {
//            violations.forEach(System.out::println);
//        }
//    }
//}
