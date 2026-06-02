package companiesProblem.rippling.kvstore.transactional;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TransactionalKeyValueStore {

    // baseStore has committed values only.
    private final Map<String, String> baseStore = new HashMap<>();

    // transactions behaves like a stack. Top transaction is the currently active one.
    private final Deque<Transaction> transactions = new ArrayDeque<>();

    public String get(String key) {
        for (Transaction transaction : transactions) {
            if (transaction.deletedKeys.contains(key)) {
                return null;
            }

            if (transaction.updatedValues.containsKey(key)) {
                return transaction.updatedValues.get(key);
            }
        }

        return baseStore.get(key);
    }

    public void set(String key, String value) {
        if (transactions.isEmpty()) {
            baseStore.put(key, value);
            return;
        }

        Transaction currentTransaction = transactions.peek();
        currentTransaction.deletedKeys.remove(key);
        currentTransaction.updatedValues.put(key, value);
    }

    public void deleteKey(String key) {
        if (transactions.isEmpty()) {
            baseStore.remove(key);
            return;
        }

        Transaction currentTransaction = transactions.peek();
        currentTransaction.updatedValues.remove(key);
        currentTransaction.deletedKeys.add(key);
    }

    public void begin() {
        transactions.push(new Transaction());
    }

    public void rollback() {
        if (transactions.isEmpty()) {
            throw new IllegalStateException("No active transaction to rollback");
        }

        transactions.pop();
    }

    public void commit() {
        if (transactions.isEmpty()) {
            throw new IllegalStateException("No active transaction to commit");
        }

        Transaction child = transactions.pop();

        if (transactions.isEmpty()) {
            applyToBaseStore(child);
        } else {
            mergeIntoParentTransaction(child, transactions.peek());
        }
    }

    private void applyToBaseStore(Transaction transaction) {
        for (String deletedKey : transaction.deletedKeys) {
            baseStore.remove(deletedKey);
        }

        for (Map.Entry<String, String> entry : transaction.updatedValues.entrySet()) {
            baseStore.put(entry.getKey(), entry.getValue());
        }
    }

    private void mergeIntoParentTransaction(Transaction child, Transaction parent) {
        for (String deletedKey : child.deletedKeys) {
            parent.updatedValues.remove(deletedKey);
            parent.deletedKeys.add(deletedKey);
        }

        for (Map.Entry<String, String> entry : child.updatedValues.entrySet()) {
            parent.deletedKeys.remove(entry.getKey());
            parent.updatedValues.put(entry.getKey(), entry.getValue());
        }
    }

    private static final class Transaction {
        private final Map<String, String> updatedValues = new HashMap<>();
        private final Set<String> deletedKeys = new HashSet<>();
    }

    public static void main(String[] args) {
        TransactionalKeyValueStore store = new TransactionalKeyValueStore();

        store.set("a", "1");
        System.out.println("a = " + store.get("a"));

        store.begin();
        store.set("a", "2");
        store.set("b", "10");
        System.out.println("inside tx, a = " + store.get("a"));
        store.rollback();
        System.out.println("after rollback, a = " + store.get("a"));
        System.out.println("after rollback, b = " + store.get("b"));

        store.begin();
        store.deleteKey("a");
        System.out.println("inside delete tx, a = " + store.get("a"));
        store.rollback();
        System.out.println("after delete rollback, a = " + store.get("a"));

        store.begin();
        store.set("a", "2");
        store.begin();
        store.set("a", "3");
        store.deleteKey("b");
        store.commit();
        System.out.println("after inner commit, a = " + store.get("a"));
        store.rollback();
        System.out.println("after outer rollback, a = " + store.get("a"));

        store.begin();
        store.set("c", "100");
        store.commit();
        System.out.println("after commit, c = " + store.get("c"));
    }
}
