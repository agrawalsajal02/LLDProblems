# Google DSA Prep: System Design & Data Structures (Categorized)

This document contains all Google-asked Custom Data Structure design, sliding window stream analytics, wildcard map lookups, and multi-threaded Concurrency problems (including `Q87. GetTotalCount` with complete multithreading explanations).

---

## 1. Custom Queue & Cache Design (LRU, PriorityQueue, etc.)

### ## 4) ScoreQueue: addElement(content, score) / getElement() with no-consecutive rule
- **LeetCode Equivalent**: Similar to [LC 767. Reorganize String](https://leetcode.com/problems/reorganize-string/) and [LC 621. Task Scheduler](https://leetcode.com/problems/task-scheduler/)
- **Problem Statement**:
  Design a data structure `ScoreQueue` that supports the following operations:
  1. `addElement(String content, int score)`: Inserts a new element. If the content already exists, update its score.
  2. `getElement()`: Returns and removes the content with the **highest score**. However, you cannot return the **same content consecutively** (1-turn cooldown). After an element is returned, its score is **decremented by 1** and it can be re-inserted (its score can become negative). If no element can be returned (e.g. empty, or only the cooldown element exists), return an empty string `""`.
- **Input / Output**:
  - Methods: `addElement(String content, int score)`, `String getElement()`
- **Constraints**:
  - Up to `10^5` operations. Content strings are unique.
- **Java Starter**:
```java
import java.util.*;

class ScoreQueue {
    public ScoreQueue() {
        
    }

    public void addElement(String content, int score) {
        
    }

    public String getElement() {
        
        return "";
    }
}
```

---

### ## 5) Streaming Logs Sorted by Timestamp (Stable on ties)
- **LeetCode Equivalent**: Similar to [LC 2519. K Keepers](https://leetcode.com/problems/k-keepers/) log buffers.
- **Problem Statement**:
  Logs arrive continuously in random order. Each log contains a timestamp string `"DD-MM-YY HH:MM:SS"` and a message. Maintain a sorted-by-time list. If two logs have identical timestamps, preserve their **arrival order** (stable sort).
- **Input / Output**:
  - Methods: `add(String ts, String msg)`, `List<Log> getSorted()`
- **Constraints**:
  - Up to `10^5` logs. Timestamps are well-formed.
- **Java Starter**:
```java
class LogSorter {
    static class Log {
        String ts;
        String msg;
        long epoch; // parsed epoch timestamp
        long seq;   // arrival order sequence ID
    }

    public void add(String timestamp, String message) {
        
    }

    public java.util.List<Log> getSorted() {
        
        return java.util.Collections.emptyList();
    }
}
```

---

### ## 31) Recent Searches Store
- **LeetCode Equivalent**: [LC 146. LRU Cache](https://leetcode.com/problems/lru-cache/)
- **Problem Statement**:
  Design a search query caching structure:
  - `focus()`: Returns the `N` most recent unique search queries.
  - `search(q)`: Saves query `q` (if already present, moves it to the most recent position), and returns the `N` most recent unique queries.
- **Input / Output**:
  - Constructor: `RecentSearches(int N)`
  - Methods: `List<String> focus()`, `List<String> search(String q)`
- **Constraints**:
  - `1 ≤ N ≤ 100`, up to `10^5` operations.
- **Java Starter**:
```java
class RecentSearches {
    public RecentSearches(int N) {
        
    }

    public java.util.List<String> focus() {
        
        return new java.util.ArrayList<>();
    }

    public java.util.List<String> search(String q) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 76) MK-Average streaming
- **LeetCode Equivalent**: [LC 1825. Finding MK Average](https://leetcode.com/problems/finding-mk-average/)
- **Problem Statement**:
  Maintain a stream of integers. Implement `MKAverage(int m, int k)`:
  - `addElement(int num)`: Inserts a number.
  - `calculateMKAverage()`: Considers the **last m** elements. Exclude the **smallest k** and **largest k** elements from these `m` elements, and return the floor average of the remaining `m - 2*k` elements. If there are fewer than `m` elements in the stream, return `-1`. *(Cross-referenced as #42).*
- **Java Starter**:
```java
class MKAverage {
    public MKAverage(int m, int k) {
        
    }
    public void addElement(int num) {
        
    }
    public int calculateMKAverage() {
        
        return -1;
    }
}
```

---

## 2. Streaming Analytics & Sliding Windows

### ## 19) Stream of Floats: Return Any Triplet within D and Remove
- **LeetCode Equivalent**: Similar to [LC 220. Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/)
- **Problem Statement**:
  Design a data structure that processes a stream of unique floating point numbers. Given a threshold `D`. At any point, if there exist **any three numbers** `{a, b, c}` in memory such that all pairwise distances are `≤ D` (i.e. $|a-b| \le D$, $|b-c| \le D$, $|a-c| \le D$), return them as a triplet list and **remove them from memory**. If multiple exist, return any. If none, return empty.
- **Input / Output**:
  - Method: `List<Double> add(double x)`
- **Constraints**:
  - Up to `10^5` values in stream.
- **Java Starter**:
```java
class TripletStream {
    public TripletStream(double D) {
        
    }

    public java.util.List<Double> add(double x) {
        
        return java.util.Collections.emptyList();
    }
}
```

---

### ## 59) Message Deduplicator (Double-Exclusion Window)
- **LeetCode Equivalent**: [LC 359. Logger Rate Limiter](https://leetcode.com/problems/logger-rate-limiter/) with dual-sided discarding.
- **Problem Statement**:
  Given a stream of messages arriving in order, where each message is represented as `{t, msg}` (integer timestamp, string message). If two identical messages occur within **10 seconds** of each other, **discard both occurrences** from the output. Output the filtered logs in original arrival order. *(Cross-referenced as #88).*
- **Input / Output**:
  - Input: `List<M> in`
  - Output: `List<M>` filtered list
- **Constraints**:
  - `1 ≤ in.size() ≤ 10^5`, timestamps are strictly non-decreasing.
- **Java Starter**:
```java
class Solution {
    static class M { 
        int t; 
        String s; 
    }
    public java.util.List<M> filter(java.util.List<M> in) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

## 3. Hashing, Address Books & Wildcard APIs

### ## 93) Address List Query with Null Wildcards in O(1)
- **LeetCode Equivalent**: [LC 211. Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure/) (Wildcard Map).
- **Problem Statement**:
  You are given a list of tuples representing addresses, e.g. `(street, city, state, zip)`. After building the database, support queries of the same structure `exists(a, b, c, d)` where any of the fields can be the string `"null"`, which acts as a **wildcard** (matches any value). The query `exists` must operate in **O(1) time complexity**.
- **Input / Output**:
  - Methods: `build(List<String[]> addresses)`, `boolean exists(String a, String b, String c, String d)`
- **Constraints**:
  - The number of fields is fixed at 4. Up to `10^4` elements.
- **Java Starter**:
```java
class Solution {
    public void build(java.util.List<String[]> addresses) {
        
    }

    public boolean exists(String a, String b, String c, String d) {
        
        return false;
    }
}
```

---

## 4. Multi-threading & Worker Pools (Concurrency)

### ### Q87. GetTotalCount – Word Count API with Workers
- **LeetCode Equivalent**: Concurrency worker pool system design.
- **Problem Statement**:
  Implement the function `GetTotalCount(int num_docs)` which returns the total number of words across all documents with ID numbers in the range `[0, num_docs)`.
  You are given a remote Web API:
  - `int GetWordCount(int doc_id)`: returns the word count of document `doc_id`. This API call involves a network request and is slow.
  
  To speed up execution, you must run requests **concurrently** using a pool of `K` worker threads. The system must be thread-safe, avoid duplicate requests, and return the aggregated sum when all document sizes have been fetched.
- **Java Thread-Safe Solution**:
  Below is a complete, production-grade concurrent implementation using a Java `ExecutorService` thread pool and atomic counters:
```java
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class WordCounter {
    // Mocking the remote slow API
    interface DocumentAPI {
        int getWordCount(int docId);
    }

    private final DocumentAPI api;
    private final int numWorkers;

    public WordCounter(DocumentAPI api, int numWorkers) {
        this.api = api;
        this.numWorkers = numWorkers;
    }

    public int getTotalCount(int numDocs) throws InterruptedException {
        // Create a fixed thread pool with K workers
        ExecutorService executor = Executors.newFixedThreadPool(numWorkers);
        AtomicInteger totalWords = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(numDocs);

        for (int i = 0; i < numDocs; i++) {
            final int docId = i;
            executor.submit(() -> {
                try {
                    // Slow network request
                    int count = api.getWordCount(docId);
                    totalWords.addAndGet(count);
                } catch (Exception e) {
                    System.err.println("Failed to fetch doc: " + docId);
                } finally {
                    latch.countDown(); // decrement remaining docs
                }
            });
        }

        // Wait until all K worker requests are fully completed
        latch.await();
        executor.shutdown();
        
        return totalWords.get();
    }
}
```
