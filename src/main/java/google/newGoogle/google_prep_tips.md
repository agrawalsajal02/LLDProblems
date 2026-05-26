# Google Interview Prep Guide: Key Strategies & DSA Patterns

This guide summarizes high-yield design patterns, structural tips, and strategic advice drawn directly from these 98 Google-asked problems. It is designed to help you organize your thought processes and code structures during live interviews.

---

## 1. Navigating Google's Ambiguity & Constraints

> [!IMPORTANT]
> **Rule #1: Google interviewers deliberately leave questions open-ended.** 
> Always ask clarifying questions *before* writing any code.

### Critical Clarifications to Raise
1. **Input Scale**: Ask "What are the bounds on $N$?" If $N \le 15$, think backtracking/DFS. If $N \le 10^5$, you need $O(N)$ or $O(N \log N)$ (e.g. greedy, sorting, tree maps).
2. **Memory Bounds**: For streaming problems (e.g., Shortest Missing Substring, Triplet Stream), clarify if the entire stream can fit in memory. If not, suggest a sliding window or min-heap boundary.
3. **Corner Cases**:
   - Empty or null inputs.
   - Negative values (critical in Dynamic Programming or subset sum problems, as shown in #64).
   - Character sets (e.g., lowercase English `a-z` vs. complete 256-byte ASCII arrays in #9).

---

## 2. Structural Design Patterns to Master

### A. The Recursive Descent Parser Pattern
*Used in: #11 (Validate Equation), #71 (Function Evaluator), #79 (Remove Parentheses)*
Google frequently asks parser-based questions. Implementing these using stacks can get messy. Instead, write a clean **Recursive Descent Parser** class:

```java
class FormulaParser {
    private final char[] ch;
    private int i = 0;

    public FormulaParser(String s) {
        // Strip spaces first for clean tokenizing
        this.ch = s.replaceAll("\\s+", "").toCharArray();
    }

    // Grammar: Expr := Term (('+' | '-') Term)*
    public boolean parseExpression() {
        if (!parseTerm()) return false;
        while (peek('+') || peek('-')) {
            i++; // consume operator
            if (!parseTerm()) return false;
        }
        return true;
    }

    private boolean parseTerm() {
        if (peekVar()) {
            i++; // consume variable [a-z]
            return true;
        } else if (peek('(')) {
            i++; // consume '('
            if (!parseExpression()) return false;
            if (!peek(')')) return false;
            i++; // consume ')'
            return true;
        }
        return false;
    }

    private boolean peek(char c) { return i < ch.length && ch[i] == c; }
    private boolean peekVar() { return i < ch.length && ch[i] >= 'a' && ch[i] <= 'z'; }
}
```
*Pro-Tip: Defining your grammar explicitly in your comments before writing the parser will gain you massive points for design and communication.*

---

### B. The Sweep Line & Interval Pattern
*Used in: #34 (Active Users), #46 (On-call Timeline), #50 (Common Free Time), #52 (Equal Area Split), #84 (Rectangle Area)*
When elements have a `[start, end]` duration, a **Sweep Line** (or coordinate compression) approach is almost always optimal.
- **Classic Algorithm**:
  1. Map intervals to start/end events: `{time, type (+1 for start, -1 for end)}`.
  2. Sort events by `time` ascending. On a tie, sort by event type to handle overlapping boundaries.
  3. Maintain a running active counter as you loop through the events.

---

### C. Connected Components & Disjoint Set Union (DSU)
*Used in: #15 (Duplicate Groups), #29 (Circles), #44 (Dynamic Friend Connectivity), #73 (Org Relations)*
If the problem involves grouping nodes by transitive or shared properties, DSU is the standard $O(\alpha(N))$ choice.
- **DSU Structure**:
  - Implement a standard `find(x)` with **path compression**.
  - Implement `union(x, y)` with **union by rank/size**.
  - To answer "earliest time all connected" (#44), maintain a `componentsCount` variable initialized to $N$ and decrement it each time a successful union merges two disjoint trees. When `componentsCount == 1`, return the current timestamp.

---

### D. Concurrency & Thread-Safety Patterns
*Used in: Q87 (Word Count API with Workers)*
Google system design/concurrency coding rounds test your knowledge of basic synchronization primitives.
- **Thread Pool Management**: Use Java's `Executors.newFixedThreadPool(K)` to limit the concurrent load on external APIs.
- **CountDownLatch**: Essential for waiting until asynchronous tasks finish executing before returning results.
- **Atomic Primitives**: Use `AtomicInteger` or `AtomicLong` for accumulators to prevent race conditions without heavy lock overheads.
- **ConcurrentHashMap**: Use for thread-safe caching or de-duplication buffers.

---

## 3. Communication Checklist during Coding Rounds

1. **Dry-Run first**: Walk through the code with the simplest example (e.g. $N=1, N=2$) using text comments to trace variable states. Do not start coding immediately.
2. **State Complexity**: Clearly state the **Time Complexity** and **Space Complexity** using Big-O notation. Explain if there is a trade-off (e.g. using more memory to achieve $O(1)$ query times in #93).
3. **Refactor**: Keep methods small and modular. In Java, use static nested classes (like `Node` or `Parser`) to package data structures cleanly.
