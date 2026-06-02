# Design, Data Structures, LLD

[Back to README](./README.md)

## Design - API / Data Structure

### 31. Recent Searches Store / Top N Most Recent Searches

**Level / source tag:** Onsite / SWE3

_Links:_ [LC 146 LRU Cache](https://leetcode.com/problems/lru-cache/), [LC 642 Design Search Autocomplete System](https://leetcode.com/problems/design-search-autocomplete-system/)

**Problem.** Design a data structure that stores recent searches.

APIs:
- `focus()` returns `N` most recent searches.
- `search(q)` saves query and returns `N` most recent.

**Variants.**
- Top N most recently searched items like LRU cache.
- Some notes mention autocomplete-like “on typing, save and return top N matches.”

**Approach.**
- If exact recent unique queries: `LinkedHashMap` / doubly linked list + hash map.
- If prefix matching: trie + per-node top list or filter recent list based on constraints.

### 130. Address List Query with Null Wildcards in O(1)

**Level / source tag:** Google Interview

_Links:_ not provided in pasted notes.

**Problem.** Given address tuples like `(a,b,c,d)`, query may contain `"null"` for any field and should answer existence in `O(1)`.

**Approach.**
- For every address, precompute all `2^4` wildcard patterns and store in a hash set.
- Query maps null fields to wildcard token and checks set.

## Design - Cache / Queue

### 108. LFU-like Cache with Even-Score Eviction

**Level / source tag:** 2026 L4 Onsite

_Links:_ [2026 Google L4 reject post](https://leetcode.com/discuss/post/8265899/google-l4-interview-reject-by-anonymous_-xsc3/)

**Problem.** Similar to LFU cache, but key-value values include `[Content: String, Score: int]`. On access, score increases by 1. Eviction follows standard cache priority but may evict only values whose score is even.

**Approach.**
- Clarify exact “standard pattern” priority: LFU frequency, LRU tiebreak?
- Maintain frequency buckets plus an index/set of even-score candidates.

### 117. LRU Cache with Expiration

**Level / source tag:** 2026 Onsite 2

_Links:_ [LC 146 LRU Cache](https://leetcode.com/problems/lru-cache/), [2026 Google L4 interview post](https://leetcode.com/discuss/post/7685567/google-interview-l4-by-anonymous_user-6s1j/)

**Problem.** Implement standard LRU cache:
- `get(key) -> value or -1`
- `put(key,value)`

Extend with time-based expiration. Each entry expires after fixed time window, e.g. 5 minutes.

**API.**

```cpp
class LRUCache {
public:
    LRUCache(int capacity, int expirationTime);
    int get(int key);
    void put(int key, int value);
};
```

**Approach.**
- Standard: hashmap + doubly linked list.
- Expiration: lazy delete on `get`/`put`; if linked list is ordered by insertion/expiry, expired prefix can be cleaned.
- Discuss recurring cleanup job trade-offs.

## Design - Iterator / Generator

### 57. Favorites-First Iterator (No Duplicates)

**Level / source tag:** Google Onsite L4 / L5

_Links:_ not provided in pasted notes.

**Problem.** Given `photos[]` and `favorites[]` with no duplicates, iterate favorites first in favorite order, then remaining photos in original order, without duplicates.

**Input / Output.**
- Input: arrays/lists.
- Output: iterator or list.

**Optimization follow-up.**
- If both arrays are sorted, use merge-like logic / sets depending on required output order.

### 92. Song Shuffler with No Repeat in Last K

**Level / source tag:** Onsite 2022 / 2024

_Links:_ [Related LC 384 Shuffle an Array](https://leetcode.com/problems/shuffle-an-array/)

**Problem.** Design a shuffler where each `playNext()` returns a random song not played in the last `K` turns. All eligible songs must have equal probability.

**Example.**
- songs `[A,B,C,D]`, `k=2`
- Valid sequence: `C -> A -> B -> C ...`

**Approach.**
- Maintain an array of eligible songs and recent queue.
- To sample uniformly in `O(1)`, keep inactive recent songs swapped out of eligible range.

### 103. String Validator as Tree Iterator

**Level / source tag:** Prep list

_Links:_ not provided in pasted notes.

**Problem.** Given a tree where each node is a character, you can move only to children. With a stream of input characters, check whether the character is a child of the current node; move to it if yes, otherwise throw `InvalidStringException`. If at a leaf, move back to root. Implement as iterator class with `hasNext()` and `next()`.

**Approach.**
- Maintain current node pointer.
- `next(char c)` validates child transition.
- On leaf after consuming char, reset to root.

### 116. URL Shortener Unique String Generator with Frequency Threshold

**Level / source tag:** 2026 Onsite 1

_Links:_ [2026 Google L4 interview post](https://leetcode.com/discuss/post/7685567/google-interview-l4-by-anonymous_user-6s1j/)

**Problem.** Design a class generating unique lowercase strings for URL shortening.

Rules:
1. Strings use only `a-z`.
2. Threshold `T` is provided.
3. Frequency of any character in a generated string must be `<= T`.
4. Every API call returns a unique string.
5. Optimize for shorter strings first: all valid length-1 before length-2, etc.

**API.**

```cpp
class URLShortener {
public:
    URLShortener(int threshold);
    string getNext();
};
```

**Example.**
- Threshold `2`: length 1 outputs `a,b,c,...,z`; length 2 then `aa,ab,...,zz`; invalid strings like `aaa` at length 3 are skipped.

**Approach.**
- Enumerate strings like base-26 counter by length.
- Skip invalid candidates whose char frequency exceeds `T`.
- This guarantees uniqueness and shorter-first order.

## Design - Chat / Waitlist / Product

### 86. Restaurant Waitlist APIs

**Level / source tag:** Phone Screen L4 / Q91

_Links:_ not provided in pasted notes.

**Problem.** Implement restaurant table allocation:
- `joinWaitlist(groupSize)`
- `leaveWaitlist(groupId)`
- `allocateTable(size)` -> allocate to first group of exact size if possible, else nearest smaller / first arrived group `<= tableSize` depending on variant.

**Constraints.**
- Multiple groups can join/leave.
- Tables allocated in order of arrival.

**Example.**
- Groups: `[5,3,2]`
- `allocateTable(3)` assigns group size `3`.
- `allocateTable(4)` assigns group size `2`.

**Approach.**
- Queue per group size, plus `groupId -> node`.
- TreeMap from size to queue for nearest `<= tableSize`.

### 87. Chat System APIs

**Level / source tag:** Q92

_Links:_ not provided in pasted notes.

**Problem.** Implement chat APIs:
- `userWithMostChats()` returns userId with max messages.
- `deleteUser(userId)` removes user and all their chats.

Messages are tuples `(From, To, Message)`.

**Example.**
- Chats: `(A,B,"hi")`, `(B,C,"yo")`, `(A,C,"hello")`
- `userWithMostChats() -> A or B`
- `deleteUser(B)` removes all chats with B.

**Approach.**
- Store messages by ID.
- Map user -> set of message IDs.
- Maintain counts in heap/tree with lazy deletion or update-capable structure.
