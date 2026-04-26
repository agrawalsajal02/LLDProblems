package companiesProblem.rippling.kvstore;

import java.util.*;

/**
 * Key-Value store for String keys/values with nested transactions.
 *
 * API:
 *   String get(String key)
 *   void set(String key, String value)
 *   boolean deleteKey(String key)
 *   void begin()
 *   void commit()
 *   void rollback()
 *
 * Design:
 *   - base: committed Map<String,String>
 *   - txStack: stack of Txn layers (top = current)
 *     each Txn has:
 *       writes: Map<String,String>     // staged sets
 *       deletes: Set<String>           // tombstones for deletes
 *
 * Reads walk from top txn → base; tombstone hides lower values.
 * Commit merges top into parent or base; rollback discards top.
 */
public class KeyValueStore {

    /** Committed store */
    private final Map<String, String> base = new HashMap<>();

    /** Open transactions (top = current) */
    private final Deque<Txn> txStack = new ArrayDeque<>();

    /** One transaction layer */
    private static final class Txn {
        final Map<String, String> writes = new HashMap<>();
        final Set<String> deletes = new HashSet<>();
    }

    // --------------- Core KV operations ---------------

    public String get(String key) {
        for (Txn t : txStack) {
            if (t.deletes.contains(key)) return null;                 // hidden by delete in this layer
            if (t.writes.containsKey(key)) return t.writes.get(key);  // staged write in this layer
        }
        return base.get(key);                                         // committed value (or null)
    }

    public void set(String key, String value) {
        if (txStack.isEmpty()) {
            base.put(key, value);                                     // no txn => write to base
        } else {
            Txn t = txStack.peek();
            t.deletes.remove(key);                                    // no longer deleted
            t.writes.put(key, value);                                 // stage write
        }
    }

    /** Deletes key; returns true if key existed in the *visible* snapshot. */
    public boolean deleteKey(String key) {
        boolean existed = get(key) != null;
        if (txStack.isEmpty()) {
            if (existed) base.remove(key);
        } else {
            Txn t = txStack.peek();
            t.writes.remove(key);                                     // cancel any same-txn set
            t.deletes.add(key);                                       // tombstone
        }
        return existed;
    }

    // --------------- Transaction operations ---------------

    /** Start a new (possibly nested) transaction. */
    public void begin() {
        txStack.push(new Txn());
    }

    /**
     * Commit the top transaction.
     * - If there is a parent txn: merge into parent (deletes first, then writes).
     * - Else: apply to base.
     */
    public void commit() {
        if (txStack.isEmpty()) {
            throw new IllegalStateException("No active transaction to commit.");
        }
        Txn top = txStack.pop();

        if (!txStack.isEmpty()) {
            // Merge into parent transaction
            Txn parent = txStack.peek();

            // Apply deletes first (hide any parent writes)
            for (String k : top.deletes) {
                parent.writes.remove(k);
                parent.deletes.add(k);
            }

            // Apply writes next (override any tombstones)
            for (Map.Entry<String, String> e : top.writes.entrySet()) {
                parent.deletes.remove(e.getKey());
                parent.writes.put(e.getKey(), e.getValue());
            }
        } else {
            // Apply to base store
            for (String k : top.deletes) base.remove(k);
            for (Map.Entry<String, String> e : top.writes.entrySet()) base.put(e.getKey(), e.getValue());
        }
    }

    /** Roll back (discard) the top transaction. */
    public void rollback() {
        if (txStack.isEmpty()) {
            throw new IllegalStateException("No active transaction to rollback.");
        }
        txStack.pop(); // discard staged layer
    }

    // -------- (Optional) Helpers useful in interviews/tests --------

    /** Returns the set of currently visible keys (base + staged - tombstoned). */
    public Set<String> keys() {
        Set<String> out = new HashSet<>(base.keySet());
        // Apply each txn from bottom to top: add writes, remove deletes
        List<Txn> layers = new ArrayList<>(txStack);
        Collections.reverse(layers); // bottom -> top
        for (Txn t : layers) {
            out.addAll(t.writes.keySet());
            out.removeAll(t.deletes);
        }
        return out;
    }

    // --------------------- Tiny test harness ---------------------

    public static void main(String[] args) {
        KeyValueStore kv = new KeyValueStore();

        // Base operations
        kv.set("a", "1");
        assert "1".equals(kv.get("a"));
        assert kv.deleteKey("a");
        assert kv.get("a") == null;

        // One-level transaction: commit
        kv.begin();
        kv.set("x", "10");
        kv.set("y", "20");
        assert "10".equals(kv.get("x"));
        assert "20".equals(kv.get("y"));
        kv.commit();
        assert "10".equals(kv.get("x"));
        assert "20".equals(kv.get("y"));

        // One-level transaction: rollback (delete edge case)
        kv.begin();
        assert kv.deleteKey("y");          // delete during txn
        assert kv.get("y") == null;        // hidden now
        kv.rollback();                     // should not delete from base
        assert "20".equals(kv.get("y"));   // visible again

        // Nested: child override parent delete
        kv.set("m", "1");                  // base: m=1
        kv.begin();                        // T1
        kv.deleteKey("m");                 // T1 tombstone
        assert kv.get("m") == null;
        kv.begin();                        // T2
        kv.set("m", "2");                  // override delete in child
        assert "2".equals(kv.get("m"));
        kv.commit();                       // merge T2 -> T1 (tombstone cleared in T1)
        assert "2".equals(kv.get("m"));
        kv.commit();                       // T1 -> base
        assert "2".equals(kv.get("m"));

        // Nested: commit inner, rollback outer (both staged should vanish)
        kv.begin();                        // T1
        kv.set("p", "A");
        kv.begin();                        // T2
        kv.set("p", "B");
        kv.set("q", "C");
        kv.commit();                       // T2 -> T1
        assert "B".equals(kv.get("p")) && "C".equals(kv.get("q"));
        kv.rollback();                     // drop T1 altogether
        assert kv.get("p") == null && kv.get("q") == null;

        // Error cases
        boolean ok = false;
        try { kv.commit(); } catch (IllegalStateException e) { ok = true; }
        assert ok;
        ok = false;
        try { kv.rollback(); } catch (IllegalStateException e) { ok = true; }
        assert ok;

        System.out.println("All tests passed ✅  Visible keys: " + kv.keys());
    }
}


//
//Sure 👍 — here’s how you can **restate the full problem statement** exactly the way it’s asked in interviews like Rippling, Google, or Meta (starting from the basic version → follow-ups).
//
//        ---
//
//        ## 🧩 Problem: Design a Key-Value Store
//
//Implement an **in-memory Key-Value Store** that supports the following operations:
//
//        ```java
//String get(String key);
//void set(String key, String value);
//boolean deleteKey(String key);
//```
//
//        * `set(key, value)` — Stores the given key–value pair.
//        * `get(key)` — Returns the value associated with the key, or `null` if not found.
//        * `deleteKey(key)` — Deletes the given key from the store and returns `true` if the key existed, `false` otherwise.
//
//### Example
//
//```
//set("a", "10")
//get("a")        → "10"
//deleteKey("a")  → true
//get("a")        → null
//        ```
//
//        ---
//
//        ## 🔁 Follow-up 1 — Add Transaction Support
//
//Extend the Key-Value store to support **transactions**, with the following additional operations:
//
//        ```java
//void begin();      // Start a new transaction
//void commit();     // Commit all operations in the current transaction
//void rollback();   // Discard all operations in the current transaction
//```
//
//        ### Rules
//
//* During a transaction, all operations (`set`, `get`, `deleteKey`) should be applied to the **temporary transaction state**, not directly to the global store.
//        * On `commit()`, all changes in the transaction should be applied to the global store.
//        * On `rollback()`, all changes in the transaction should be **reverted** — the global store must remain unchanged.
//
//        ### Example (single transaction)
//
//```
//set("a", "10")
//begin()
//set("a", "20")
//get("a")        → "20"
//rollback()
//get("a")        → "10"
//        ```
//
//        ---
//
//        ## 🔁 Follow-up 2 — Support **Nested Transactions**
//
//Enhance your implementation to support **nested transactions**, meaning you can start a new transaction inside another one.
//
//Rules:
//
//        * Each `begin()` starts a new transaction layer.
//        * `commit()` should merge the top transaction into its parent.
//        * `rollback()` should discard only the current transaction layer.
//* The global store should only change when the **outermost transaction** commits.
//
//### Example (nested)
//
//```
//set("x", "1")
//
//begin()             // T1
//set("x", "2")
//begin()           // T2
//set("x", "3")
//commit()          // T2 merged into T1
//get("x")          → "3"
//rollback()          // T1 rolled back
//get("x")            → "1"
//        ```
//
//        ---
//
//        ## ✅ Expected Behavior Summary
//
//| Operation      | Description                                                |
//        | -------------- | ---------------------------------------------------------- |
//        | `set(k, v)`    | Store key–value in current transaction (or base if no txn) |
//        | `get(k)`       | Return latest visible value from top-most active layer     |
//        | `deleteKey(k)` | Mark key as deleted (tombstone) in current transaction     |
//        | `begin()`      | Start a new transaction layer                              |
//        | `commit()`     | Merge top layer into parent or apply to base store         |
//        | `rollback()`   | Discard the current transaction layer                      |
//
//        ---
//
//        ## 🧠 Edge Cases to Handle
//
//1. Deleting a key inside a transaction **must not affect the global store** if you roll back.
//2. If you `delete` a key and then `set` it again in the same transaction, the delete should be overridden.
//        3. Nested commits and rollbacks should behave consistently:
//
//        * Inner commit merges into parent.
//   * Outer rollback discards both parent and merged child changes.
//4. Committing when no transaction is active → throw exception.
//5. Rolling back when no transaction is active → throw exception.
//
//---
//
//Would you like me to also include a **sample input/output trace** (like test commands + expected output table) so you can show it directly in an interview demo?
