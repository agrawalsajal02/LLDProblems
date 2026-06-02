# Transactional Key Value Store

## Intuition

Basic KV store me `get`, `set`, `deleteKey` simple map se ho jata hai.

Transaction add karne ke baad main challenge delete ka hota hai:

Transaction ke andar agar key delete hui and rollback hua, to original committed value wapas visible honi chahiye. Isliye transaction me direct base store se delete nahi karte.

Solution:

- `baseStore`: committed data.
- `transactions`: stack of transaction layers.
- Har transaction layer me:
  - `updatedValues`: current transaction me set ki gayi values.
  - `deletedKeys`: tombstone set, yani key deleted dikhani hai.

Nested transactions ke liye stack natural fit hai:

- `begin`: new layer push.
- `rollback`: top layer discard.
- `commit`: top layer parent me merge, ya base store me apply.

## Classes

- `TransactionalKeyValueStore`: main API class.
- `Transaction`: one transaction layer, containing writes and deletes.

## Functions

- `get(String key)`: top transaction se base tak search karta hai. Tombstone mila to null. Time: O(T), where T = transaction depth.
- `set(String key, String value)`: active transaction hai to top layer me set, warna base store me set. Time: O(1) average.
- `deleteKey(String key)`: active transaction hai to tombstone add, warna base store se delete. Time: O(1) average.
- `begin()`: new transaction start. Time: O(1).
- `rollback()`: current transaction discard. Time: O(1).
- `commit()`: current transaction parent/base me apply. Time: O(K), where K = keys changed in current transaction.
- `applyToBaseStore(...)`: outermost commit ko base store me apply karta hai. Time: O(K).
- `mergeIntoParentTransaction(...)`: nested commit ko parent transaction me merge karta hai. Time: O(K).

## Complexity

- `set`: O(1)
- `deleteKey`: O(1)
- `begin`: O(1)
- `rollback`: O(1)
- `commit`: O(number of keys changed in transaction)
- `get`: O(transaction depth), because top se base tak check karna padta hai
