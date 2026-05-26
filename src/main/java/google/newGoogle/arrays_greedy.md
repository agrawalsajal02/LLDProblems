# Google DSA Prep: Arrays, Greedy & Math Simulation (Categorized)

This document contains all Google-asked Array, Greedy, Sweep-line, Interval, Binary Search, and mathematical Simulation problems. They are sorted into logical sections from interval boundaries and sweep-lines, to greedy resource schedules, binary searches, and cyclic sorts.

---

## 1. Sweep Line & Interval Merging

### ## 34) Active Users per Time [0, N)
- **LeetCode Equivalent**: Sweep Line. Similar to [LC 253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) and [LC 1094. Car Pooling](https://leetcode.com/problems/car-pooling/)
- **Problem Statement**:
  Given a list of user active sessions, where each session is represented as `[start, end]` (inclusive). Given an integer `N`, compute for each time unit `t ∈ [0, N)` the number of active users online at that specific time. Return an array of size `N`.
- **Input / Output**:
  - Input: `int[][] sessions`, `int N`
  - Output: `int[]` of length `N` containing active counts
- **Constraints**:
  - `1 ≤ sessions.length ≤ 10^5`, `1 ≤ N ≤ 10^5`
  - `0 ≤ start ≤ end < N`
- **Examples**:
  - `sessions = [[0, 2], [1, 3]], N = 5`
    - Time 0: user 1 is active. (Count = 1)
    - Time 1: user 1 and user 2 active. (Count = 2)
    - Time 2: user 1 and user 2 active. (Count = 2)
    - Time 3: user 2 is active. (Count = 1)
    - Time 4: no active users. (Count = 0)
    - Output: `[1, 2, 2, 1, 0]`
- **Java Starter**:
```java
class Solution {
    public int[] activeCounts(int[][] sessions, int N) {
        
        return new int[N];
    }
}
```

---

### ## 46) On-call Rotation Timeline (Interval Partitioning)
- **LeetCode Equivalent**: Similar to [LC 56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)
- **Problem Statement**:
  You are given a list of on-call intervals for multiple engineers, represented as `{name, start, end}`. The intervals can overlap. Output a timeline of non-overlapping time segments with the exact **list of on-call engineers** active in each segment. The timeline must be sorted ascending.
- **Input / Output**:
  - Input: `List<Interval> intervals`
  - Output: `List<Segment>` containing non-overlapping intervals with list of names
- **Constraints**:
  - `1 ≤ intervals.size() ≤ 10^5`
- **Java Starter**:
```java
class Solution {
    static class Interval { 
        String name; 
        int s, e; 
    }
    static class Segment { 
        int s, e; 
        java.util.List<String> names; 
    }
    public java.util.List<Segment> rotation(java.util.List<Interval> in) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 50) Common Free Time of All Persons
- **LeetCode Equivalent**: [LC 759. Employee Free Time](https://leetcode.com/problems/employee-free-time/)
- **Problem Statement**:
  You are given a list of occupied time blocks across multiple individuals, where each block is represented as `{personId, start, end}`. Given a total schedule time bounds `[0, totalTime]`. Find and return the list of time intervals during which **every person** is simultaneously free.
- **Input / Output**:
  - Input: `List<Block> blocks`, `int totalTime`
  - Output: `List<int[]>` representing the free time intervals
- **Constraints**:
  - `1 ≤ blocks.size() ≤ 10^5`
- **Java Starter**:
```java
class Solution {
    static class Block { 
        int personId, start, end; 
    }
    public java.util.List<int[]> allFree(java.util.List<Block> blocks, int totalTime) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 52) Equal-Area Vertical Line across Overlapping Rectangles
- **LeetCode Equivalent**: Sweep Line with Binary Search. Similar to [LC 223. Rectangle Area](https://leetcode.com/problems/rectangle-area/)
- **Problem Statement**:
  Given a list of axis-aligned rectangles, where each rectangle is represented as `[x1, y1, x2, y2]`. The rectangles can overlap. Find a vertical line `x = C` that divides the total union area of the rectangles into **two equal parts**. Return the coordinate `C`. *(This is cross-referenced as #80, #87, #97).*
- **Input / Output**:
  - Input: `int[][] rects`
  - Output: `double` C coordinate
- **Constraints**:
  - `1 ≤ rects.length ≤ 10^4`
- **Java Starter**:
```java
class Solution {
    public double splitEqualArea(int[][] rects) {
        
        return 0.0;
    }
}
```

---

### ## 84) Rectangle Area (Union of Two)
- **LeetCode Equivalent**: [LC 223. Rectangle Area](https://leetcode.com/problems/rectangle-area/)
- **Problem Statement**:
  Given the coordinates of two axis-aligned rectangles, return the **total area** covered by the union of these two rectangles (i.e. sum of areas minus their overlap).
- **Input / Output**:
  - Input: `int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2`
  - Output: `int` total union area
- **Constraints**:
  - `-10^4 ≤ coords ≤ 10^4`
- **Java Starter**:
```java
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2,
                           int bx1, int by1, int bx2, int by2) {
        
        return 0;
    }
}
```

---

## 2. Greedy Partitioning, Heap & Rearrangement

### ## 17) Assign Apartments to People
- **LeetCode Equivalent**: Greedy matching. Similar to job assignment / interval packing.
- **Problem Statement**:
  You are given a list of apartments, each with a number of rooms (`numRooms`), and a list of people. Each person has a name and a boolean preference indicating whether they want housemates (`wantsHousemates`). Assign people to apartments such that a room is allocated per person, roommate preferences are fully respected (if they do not want housemates, they must be assigned to a 1-room apartment alone; if they do, they can share a multi-room apartment). Maximize satisfaction and return the assignments.
- **Input / Output**:
  - Input: `List<Apartment> apts`, `List<Person> people`
  - Output: `Map<Integer, List<String>>` mapping apartment number to names of people assigned
- **Constraints**:
  - `1 ≤ apts.size(), people.size() ≤ 10^4`
- **Java Starter**:
```java
class Solution {
    static class Apartment { 
        int aptNumber, numRooms; 
    }
    static class Person { 
        String name; 
        boolean wantsHousemates; 
    }
    public java.util.Map<Integer, java.util.List<String>> assign(java.util.List<Apartment> apts, java.util.List<Person> people) {
        
        return new java.util.HashMap<>();
    }
}
```

---

### ## 23) Neighborhood Reassignment (Sorted Capacities)
- **LeetCode Equivalent**: Greedy Array sorting. Similar to [LC 1054. Distant Barcodes](https://leetcode.com/problems/distant-barcodes/)
- **Problem Statement**:
  You are given a list of blocks, where each block is a list of house numbers. You want to redistribute the houses across the blocks such that:
  1. The sizes (capacities) of the blocks are preserved.
  2. Each block's house numbers are sorted in ascending order.
  3. There are **no duplicate house numbers** in the same block.
  Return the rearranged blocks.
- **Input / Output**:
  - Input: `List<List<Integer>> blocks`
  - Output: `List<List<Integer>>` rearranged blocks
- **Constraints**:
  - Sum of block sizes `≤ 2 * 10^5`
- **Java Starter**:
```java
class Solution {
    public java.util.List<java.util.List<Integer>> rearrange(java.util.List<java.util.List<Integer>> blocks) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 26) Bank Serve Maximum Contiguously after Start
- **LeetCode Equivalent**: Sliding Window. Similar to [LC 862. Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/)
- **Problem Statement**:
  A bank starts with an initial fund `X`. You are given an array of transaction requests `tx`, where a positive value is a deposit and a negative value is a withdrawal. You can choose any start index `i` to begin processing. Once you choose `i`, you must process transactions sequentially `tx[i], tx[i+1], tx[i+2]...` until a withdrawal is requested that exceeds the bank's current funds. Find the start index that maximizes the **number of contiguously served transactions** and return that count.
- **Input / Output**:
  - Input: `int X`, `int[] tx`
  - Output: `int` max contiguously served
- **Constraints**:
  - `1 ≤ tx.length ≤ 10^5`
  - `-10^9 ≤ tx[i], X ≤ 10^9`
- **Java Starter**:
```java
class Solution {
    public int maxServed(int X, int[] tx) {
        
        return 0;
    }
}
```

---

### ## 35) Seat Students to Minimize Adjacent Same Exam
- **LeetCode Equivalent**: Greedy Max-Heap. Similar to [LC 767. Reorganize String](https://leetcode.com/problems/reorganize-string/)
- **Problem Statement**:
  You are given an array of student exam IDs representing the exam code each student is taking. Reorder the students in a row such that the number of adjacent students taking the **same exam** is minimized. Return one such valid arrangement.
- **Input / Output**:
  - Input: `int[] exams`
  - Output: `int[]` reordered exam row
- **Constraints**:
  - `1 ≤ exams.length ≤ 10^5`
- **Java Starter**:
```java
class Solution {
    public int[] arrangeExams(int[] exams) {
        
        return new int[0];
    }
}
```

---

### ## 49) Array to Zero with Range Queries
- **LeetCode Equivalent**: Greedy interval schedule. Similar to [LC 1094. Car Pooling](https://leetcode.com/problems/car-pooling/)
- **Problem Statement**:
  Given an array `arr` and a set of queries `[l, r]`. For each query, you are allowed to select any **subsequence** of indices within `[l, r]` and subtract 1 from the values at those indices. Determine if it is possible to reduce all elements of `arr` to exactly `0` after processing all queries.
- **Input / Output**:
  - Input: `int[] arr`, `int[][] queries`
  - Output: `boolean` (true if possible, false otherwise)
- **Constraints**:
  - `1 ≤ arr.length, queries.length ≤ 10^5`
- **Java Starter**:
```java
class Solution {
    public boolean canZero(int[] arr, int[][] queries) {
        
        return false;
    }
}
```

---

### ## 57) Max Possible Transactions (Stop on Limit)
- **LeetCode Equivalent**: Greedy with Max-Heap. Similar to [LC 630. Course Schedule III](https://leetcode.com/problems/course-schedule-iii/)
- **Problem Statement**:
  Given an initial total balance `T` and an array of transactions `tx` (positive represents a withdrawal, negative represents a deposit). You can choose to process the transactions **in any order**. However, if you attempt to process a withdrawal that exceeds your current balance, you must stop immediately. Maximize the **total number** of transactions you can successfully process.
- **Input / Output**:
  - Input: `int T`, `int[] tx`
  - Output: `int` max count processed
- **Constraints**:
  - `1 ≤ tx.length ≤ 10^5`
- **Java Starter**:
```java
class Solution {
    public int maxTransactions(int T, int[] tx) {
        
        return 0;
    }
}
```

---

## 3. Binary Search & Divide and Conquer

### ## 10) Find Incompatible Pair of Unit Tests (Using TestRunner)
- **LeetCode Equivalent**: Interactive Binary Search / Divide & Conquer.
- **Problem Statement**:
  You are debugging a test suite of `n` unit tests. All tests pass when run individually. However, there is at least one pair of tests `(i, j)` that are **incompatible** and fail when run in the same subset. You are given a black-box function `testRunner(subset)` which returns `false` if any incompatible pair exists in the subset, and `true` otherwise. Find and return **at least one** incompatible pair `(i, j)`. Maximize the efficiency (call `testRunner` at most $O(\log n)$ times per step).
- **Input / Output**:
  - Input: `int n`, `Runner runner` (black-box)
  - Output: `int[]` of length 2 representing the bad pair `{i, j}`
- **Constraints**:
  - `2 ≤ n ≤ 10^5`, testRunner takes $O(k)$ time for a subset of size $k$.
- **Java Starter**:
```java
class Solution {
    interface Runner { 
        boolean run(java.util.List<Integer> tests); 
    }
    public int[] findBadPair(int n, Runner runner) {
        
        return new int[]{-1, -1};
    }
}
```

---

### ## 45) Sparse Bit Array via Range Query (Find All Ones)
- **LeetCode Equivalent**: Interactive Binary Search. Similar to [LC 278. First Bad Version](https://leetcode.com/problems/first-bad-version/)
- **Problem Statement**:
  You are given a very large, sparse bit array containing mostly `0`s and a few `1`s. You cannot inspect indices directly. Instead, you have a function `queryRange(l, r)` that returns `true` if there is at least one `1` in the range `[l, r]`, and `false` otherwise. Design an algorithm to find the coordinates of **all** indices containing `1` using the minimum number of range queries. *(Cross-referenced as #74, #89).*
- **Input / Output**:
  - Input: `int n` (size), `RangeQuery query`
  - Output: `List<Integer>` indices of all `1`s
- **Java Starter**:
```java
class Solution {
    interface RangeQuery { 
        boolean hasOne(int l, int r); 
    }
    public java.util.List<Integer> findOnes(int n, RangeQuery query) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 54) Count Words with Prefix (Binary Search on Sorted List)
- **LeetCode Equivalent**: Binary Search bounds. Similar to [LC 34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
- **Problem Statement**:
  Given a sorted array of strings `arr` and a prefix string `prefix`. Count how many words in `arr` start with the given `prefix` using Binary Search (do not do a linear scan, which takes $O(N)$). *(Cross-referenced as #62).*
- **Input / Output**:
  - Input: `String[] arr` (sorted), `String prefix`
  - Output: `int` count
- **Constraints**:
  - `1 ≤ arr.length ≤ 10^5`, string lengths `≤ 100`
- **Examples**:
  - `arr = ["apple", "applet", "apricot", "banana"], prefix = "app"`
    - Words starting with `"app"` are `"apple"` and `"applet"`.
    - Output: `2`
- **Java Starter**:
```java
class Solution {
    public int countWithPrefix(String[] arr, String prefix) {
        
        return 0;
    }
}
```

---

### ## 66) IP Range → Country Lookup
- **LeetCode Equivalent**: Binary Search on Intervals. Similar to [LC 34. Find First and Last Position](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
- **Problem Statement**:
  You are given a list of disjoint, sorted IP address ranges `[start, end] → country`, where IPs are converted to 32-bit unsigned integers. For a query IPv4 string (e.g. `"192.168.1.1"`), convert it to its integer equivalent and return the associated country name. If the IP does not fall in any range, return `"UNKNOWN"`.
- **Input / Output**:
  - Input: `List<Range> ranges`, `String ip`
  - Output: `String` country
- **Constraints**:
  - `1 ≤ ranges.size() ≤ 10^5`
- **Java Starter**:
```java
class Solution {
    static class Range { 
        long start, end; 
        String country; 
    }
    public String findCountry(java.util.List<Range> ranges, String ip) {
        
        return "";
    }
}
```

---

## 4. Math Simulation, Cyclic Sort & Permutations

### ## 1) Collatz Steps
- **LeetCode Equivalent**: Simulation / Math. Similar to [LC 1387. Sort Integers by The Power Value](https://leetcode.com/problems/sort-integers-by-the-power-value/)
- **Problem Statement**:
  Given a positive integer `n`, repeatedly apply the following rule:
  - If `n` is even: `n = n / 2`
  - If `n` is odd: `n = 3 * n + 1`
  Count and return the number of steps required to reach `1`.
- **Input / Output**:
  - Input: `long n` (64-bit integer)
  - Output: `int` steps
- **Constraints**:
  - `1 ≤ n ≤ 10^12`
- **Examples**:
  - `n = 1` → `0`
  - `n = 6` → `8` (path: 6 → 3 → 10 → 5 → 16 → 8 → 4 → 2 → 1)
- **Java Starter**:
```java
class Solution {
    public int collatzSteps(long n) {
        
        return 0;
    }
}
```

---

### ## 16) Valid Tournament Draw
- **LeetCode Equivalent**: Recursion / Divide & Conquer.
- **Problem Statement**:
  You are checking the validity of a tournament bracket order `[1..N]` (where `N` is a power of 2). In this tournament, the better-ranked player (smaller number) always wins. A draw order is **valid** if, in **every round**, the match-ups pair the best player against the worst player, the second-best against the second-worst, and so on. Check if the initial draw order is valid.
- **Input / Output**:
  - Input: `int[] draw`
  - Output: `boolean`
- **Constraints**:
  - `N ≤ 2^16` (N is power of 2)
- **Examples**:
  - `draw = [1, 8, 6, 2, 7, 3, 4, 5]` → `true`
- **Java Starter**:
```java
class Solution {
    public boolean isValidDraw(int[] draw) {
        
        return false;
    }
}
```

---

### ## 28) Odd/Even Jump Game with Updates
- **LeetCode Equivalent**: Dynamic Simulation. Extended version of [LC 975. Odd Even Jump](https://leetcode.com/problems/odd-even-jump/)
- **Problem Statement**:
  You have an array `A` and start at index `S`.
  - **Odd numbered move**: Jump to the **first index to the left** with value exactly equal to `A[cur] + 1`.
  - **Even numbered move**: Jump to the **first index to the right** with value exactly equal to `A[cur] + 1`.
  After making a jump, the element at the previous position is incremented: `A[prev] += X`. Stop when no valid jump is possible or an infinite loop is detected (return `-1` if infinite). Return the final index reached.
- **Input / Output**:
  - Input: `int[] A`, `int X`, `int S`
  - Output: `int` final index, or `-1` if infinite
- **Constraints**:
  - `1 ≤ A.length ≤ 10^5`
- **Java Starter**:
```java
class Solution {
    public int oddEvenJumpGame(int[] A, int X, int S) {
        
        return -1;
    }
}
```

---

### ## 51) Flip 1-bit-per-pixel Image Horizontally In-Place
- **LeetCode Equivalent**: Bit Manipulation. Similar to [LC 190. Reverse Bits](https://leetcode.com/problems/reverse-bits/)
- **Problem Statement**:
  An image is stored as a 1D byte array `image`, where each bit represents one pixel (1-bit-per-pixel). Given the width of the image in bits `wBits` (guaranteed to be a multiple of 8) and the height `h`. Flip the image horizontally (reverse the bits in each row) **in place**. *(Cross-referenced as #86).*
- **Input / Output**:
  - Input: `byte[] image`, `int wBits`, `int h`
  - Output: void (mutates the input array)
- **Constraints**:
  - `wBits` is a multiple of 8.
- **Java Starter**:
```java
class Solution {
    public void flipHorizontally(byte[] image, int wBits, int h) {
        
    }
}
```

---

### ## 72) Robot Sort with One Empty Slot
- **LeetCode Equivalent**: Cycle Decomposition. Similar to [LC 765. Couples Holding Hands](https://leetcode.com/problems/couples-holding-hands/)
- **Problem Statement**:
  An array of size `n` contains elements `1..n-1` and exactly one empty slot `_`. A robot can move any number from its current slot to the empty slot `_` in one operation. Find the minimum number of operations required to sort the array in ascending order (with the empty slot `_` positioned at either end).
- **Input / Output**:
  - Input: `int[] arr`
  - Output: `int` minimum moves
- **Constraints**:
  - `1 ≤ arr.length ≤ 1000`
- **Java Starter**:
```java
class Solution {
    public int minMovesToSortWithHole(int[] arr) {
        
        return 0;
    }
}
```

---

### ## 91) Largest k-digit Number from Array (preserve order)
- **LeetCode Equivalent**: Monotonic Stack. Similar to [LC 402. Remove K Digits](https://leetcode.com/problems/remove-k-digits/) and [LC 321. Create Maximum Number](https://leetcode.com/problems/create-maximum-number/)
- **Problem Statement**:
  Given an integer array `arr` and an integer `k`. Pick exactly `k` elements from `arr` such that their relative order is preserved, and concatenating these elements forms the **largest possible number**. Return this number as a string.
- **Input / Output**:
  - Input: `int[] arr`, `int k`
  - Output: `String` representing the largest number
- **Constraints**:
  - `1 ≤ k ≤ arr.length ≤ 10^5`
  - `0 ≤ arr[i] ≤ 9`
- **Java Starter**:
```java
class Solution {
    public String largestNumberK(int[] arr, int k) {
        
        return "";
    }
}
```
