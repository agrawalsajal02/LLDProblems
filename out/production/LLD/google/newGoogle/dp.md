# Google DSA Prep: Dynamic Programming (Categorized)

This document contains all Google-asked Dynamic Programming (DP) problems. They are ordered from subarray/Kadane optimizations to knapsack/reverse DP coin change, Longest Increasing Subsequence (LIS) variants, state-machine schedulers, and advanced palindromic/subsequence string matchings.

---

## 1. Maximum Subarrays & 1D/2D Kadane Variants

### ## 25) Max Subarray Sum (Kadane) + Indices with nums[i]==nums[j]
- **LeetCode Equivalent**: Extended version of [LC 53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)
- **Problem Statement**:
  1. Find the maximum sum of a contiguous subarray (standard Kadane's algorithm).
  2. *Follow-up*: Find the starting index `i` and ending index `j` that achieve the maximum sum under the constraint that the elements at both ends are equal (`nums[i] == nums[j]`).
- **Input / Output**:
  - `maxSubArray` Input: `int[] nums`, Output: `int` (max sum)
  - `maxSumEqualEnds` Input: `int[] nums`, Output: `int[]` of size 2 representing `{i, j}`
- **Constraints**:
  - `1 ≤ nums.length ≤ 10^5`
  - `-10^4 ≤ nums[i] ≤ 10^4`
- **Examples**:
  - `nums = [3, -1, 4, 3]`
    - `maxSubArray` → `9` (subarray `[3, -1, 4, 3]`)
    - `maxSumEqualEnds` → `[0, 3]` (since `nums[0] == nums[3] == 3`, sum = 9)
  - `nums = [1, -5, 2, -1, 2, -5, 1]`
    - `maxSumEqualEnds` → `[2, 4]` (since `nums[2] == nums[4] == 2`, sum = 3)
- **Java Starter**:
```java
class Solution {
    public int maxSubArray(int[] nums) {
        
        return 0;
    }
    public int[] maxSumEqualEnds(int[] nums) {
        
        return new int[]{-1, -1};
    }
}
```

---

## 2. Coin Change, Knapsack & Reverse DP

### ## 13) Recover Coin Denominations from Ways DP (Reverse DP)
- **LeetCode Equivalent**: Reverse of [LC 518. Coin Change II](https://leetcode.com/problems/coin-change-ii/)
- **Problem Statement**:
  You are given an integer array `dp` of size `T+1` where `dp[x]` represents the number of ways to make sum `x` using an unknown, infinite set of positive coin denominations (order of coin choices does not matter). Reconstruct and return **a possible set of coin denominations** consistent with the given `dp` array.
- **Input / Output**:
  - Input: `int[] dp`
  - Output: `List<Integer>` representing the coin denominations in ascending order
- **Constraints**:
  - `1 ≤ T ≤ 5000`
  - `dp[0] = 1`, `dp[x] ≥ 0`
- **Examples**:
  - `T = 10`, `dp = [1, 0, 1, 0, 1, 1, 2, 1, 2, 1, 3]`
    - Output: `[2, 5, 6]` (we can verify: sum 6 has 2 ways: `2+2+2`, `6`. Sum 10 has 3 ways: `2+2+2+2+2`, `5+5`, `2+2+6`)
- **Java Starter**:
```java
class Solution {
    public java.util.List<Integer> recoverCoins(int[] dp) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 67) Sum of Squares to X (any list) + shortest list
- **LeetCode Equivalent**: [LC 279. Perfect Squares](https://leetcode.com/problems/perfect-squares/)
- **Problem Statement**:
  Given a positive integer `x`:
  1. Return **any** multiset of integers whose squares sum up to `x`.
  2. *Follow-up*: Return the **shortest** possible list of integers (which corresponds to the minimum number of perfect squares).
- **Input / Output**:
  - Input: `int x`
  - Output: `List<Integer>` containing the elements
- **Constraints**:
  - `1 ≤ x ≤ 10^5`
- **Examples**:
  - `x = 12`
    - `sumSquares` (Any) → `[2, 2, 2]` ($2^2+2^2+2^2 = 12$)
    - `sumSquares` (Shortest) → `[2, 2, 2]` (minimum squares is 3)
  - `x = 13`
    - `sumSquares` (Shortest) → `[2, 3]` ($2^2 + 3^2 = 13$, length 2)
- **Java Starter**:
```java
class Solution {
    public java.util.List<Integer> sumSquares(int x) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

### ## 64) Partition Equal Subset Sum with Negatives
- **LeetCode Equivalent**: [LC 416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) with negative numbers.
- **Problem Statement**:
  Given an array of integers `nums` that can contain **negative values**, determine if it can be partitioned into two subsets such that the sum of elements in both subsets is equal.
- **Input / Output**:
  - Input: `int[] nums`
  - Output: `boolean`
- **Constraints**:
  - `1 ≤ nums.length ≤ 100`
  - `-100 ≤ nums[i] ≤ 100`
- **Examples**:
  - `nums = [1, 5, 11, 5]` → `true` (subsets `[1, 5, 5]` and `[11]`, both sum to 11)
  - `nums = [2, -2, 4, -4]` → `true` (subsets `[2, -4]` and `[-2, 4]`, both sum to -2)
- **Java Starter**:
```java
class Solution {
    public boolean canPartition(int[] nums) {
        
        return false;
    }
}
```

---

### ## 69) Subsets with LCM divisible by k
- **LeetCode Equivalent**: Subsequences math DP. Similar to [LC 2507. Smallest Value After Replacing With Sum of Prime Factors](https://leetcode.com/problems/smallest-value-after-replacing-with-sum-of-prime-factors/) and divisible subset calculations.
- **Problem Statement**:
  Given an array of positive integers `arr` and a target divisor `k`. Count the number of non-empty subsets of `arr` whose Least Common Multiple (LCM) is **strictly divisible** by `k`. Return the count modulo `10^9 + 7`.
- **Input / Output**:
  - Input: `int[] arr`, `int k`
  - Output: `long` subset count mod `10^9 + 7`
- **Constraints**:
  - `1 ≤ arr.length ≤ 1000`
  - `1 ≤ arr[i], k ≤ 10^6`
- **Java Starter**:
```java
class Solution {
    public long countSubsetsLCMDivK(int[] arr, int k) {
        
        return 0L;
    }
}
```

---

## 3. LIS & Subsequence Optimization DP

### ## 18) Max Score Jumps: score = (destIdx - srcIdx) * value[dest]
- **LeetCode Equivalent**: Similar to [LC 1696. Jump Game VI](https://leetcode.com/problems/jump-game-vi/) optimized DP.
- **Problem Statement**:
  You are standing at index 0 of an array `a`. You want to reach the last index `n-1` by making a series of jumps. Jumping from index `j` to index `i` (where `i > j`) earns you a score of `(i - j) * a[i]`. Compute the **maximum possible score** you can accumulate by making any number of jumps to reach the final index.
- **Input / Output**:
  - Input: `int[] a`
  - Output: `long` maximum total score
- **Constraints**:
  - `2 ≤ n ≤ 2 * 10^5`
  - `-10^9 ≤ a[i] ≤ 10^9` (score can be large, use 64-bit integers)
- **Examples**:
  - `a = [3, 12, 9, 10]`
    - Jump 0 → 1: score = `(1-0)*12 = 12`
    - Jump 1 → 3: score = `(3-1)*10 = 20`
    - Total score = 32.
    - Output: `32`
  - `a = [1, 2, 3, 4, 5]`
    - Jump 0 → 4: score = `(4-0)*5 = 20`
    - Output: `20`
- **Java Starter**:
```java
class Solution {
    public long maxJumpScore(int[] a) {
        
        return 0L;
    }
}
```

---

### ## 43) LIS with Increasing Differences
- **LeetCode Equivalent**: Subsequence variant of [LC 300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)
- **Problem Statement**:
  Find the length of the longest increasing subsequence in an array `nums` such that the **difference** between consecutive elements in the chosen subsequence forms a **strictly increasing** sequence.
- **Input / Output**:
  - Input: `int[] nums`
  - Output: `int` max length of valid subsequence
- **Constraints**:
  - `1 ≤ nums.length ≤ 1000`
  - `-10^6 ≤ nums[i] ≤ 10^6`
- **Examples**:
  - `nums = [1, 3, 4, 7, 11]`
    - Subsequence: `[1, 3, 7, 11]` is invalid because differences are `[2, 4, 4]` (not strictly increasing).
    - Subsequence: `[1, 3, 7]` has differences `[2, 4]` (strictly increasing).
    - Output: `3`
- **Java Starter**:
```java
class Solution {
    public int lisIncreasingDiffs(int[] nums) {
        
        return 0;
    }
}
```

---

### ## 74) LIS diff = 1 (Cross-Referenced)
- **LeetCode Equivalent**: [LC 1218. Longest Arithmetic Subsequence of Given Difference](https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/)
- **Problem Statement**:
  Find the length of the longest arithmetic subsequence in `nums` where the difference between consecutive elements is exactly `1`.
- **Java Starter**:
```java
class Solution {
    public int longestSubsequence(int[] arr) {
        
        return 0;
    }
}
```

---

## 4. State Machine DP & Travel Scheduling

### ## 39) Consultant Schedule across Two Cities with Travel
- **LeetCode Equivalent**: State Machine DP. Similar to [LC 309. Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)
- **Problem Statement**:
  A consultant works across two cities, A and B. You are given two arrays `A` and `B` of length `n`, representing the earnings on day `i` in City A or City B. The consultant can start in either city on Day 0. On any day, they can choose to travel to the other city. A travel day earns `0` revenue. Find the optimal sequence of actions ('A', 'B', 'T' where 'T' represents travel) that **maximizes total earnings** across all `n` days.
- **Input / Output**:
  - Input: `int[] A`, `int[] B`
  - Output: `String` of length `n` containing characters 'A', 'B', 'T'
- **Constraints**:
  - `1 ≤ n ≤ 10^5`, `0 ≤ A[i], B[i] ≤ 10^6`
- **Examples**:
  - `A = [100, 1, 1], B = [1, 1, 100]`
    - Optimal: Work day 0 in A ('A'), Travel day 1 ('T'), Work day 2 in B ('B').
    - Total = 100 + 0 + 100 = 200.
    - Output: `"ATB"`
- **Java Starter**:
```java
class Solution {
    public String bestSchedule(int[] A, int[] B) {
        
        return "";
    }
}
```

---

### ## 75) Musical Notes Sequences to Sum 12 with Transitions
- **LeetCode Equivalent**: Dynamic Path Count on Graph. Similar to DFS backtracking / cycle counting.
- **Problem Statement**:
  You are creating a short song of musical notes where notes can have lengths `1`, `2`, or `3` beats. You are given a transition map `nexts` indicating which note lengths can follow a given note length (e.g. note `2` can only be followed by `1` or `3`). You want to find **all valid sequences of notes** that sum up to exactly `12` beats, under the constraint that the transition from the **last note back to the first note** must also be valid (circular song).
- **Input / Output**:
  - Input: `Map<Integer, List<Integer>> nexts`
  - Output: `List<List<Integer>>` containing all valid song loops
- **Constraints**:
  - Song length sum = 12. Note values are in `{1, 2, 3}`.
- **Java Starter**:
```java
class Solution {
    public java.util.List<java.util.List<Integer>> musicSequences(java.util.Map<Integer, java.util.List<Integer>> nexts) {
        
        return new java.util.ArrayList<>();
    }
}
```

---

## 5. String Subsequences & Palindromic DP

### ## 68) Palindromic Triples Count
- **LeetCode Equivalent**: Similar to [LC 647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/) combined with segment partition.
- **Problem Statement**:
  Given a string `S`. Count the number of unique triples of palindromic substrings `(t1, t2, t3)` where each substring is represented by interval bounds `(i, j)` and the intervals are strictly ordered and non-overlapping: `i1 ≤ j1 < i2 ≤ j2 < i3 ≤ j3`.
- **Input / Output**:
  - Input: `String s`
  - Output: `long` count of palindromic triples
- **Constraints**:
  - `3 ≤ s.length() ≤ 2000`
- **Examples**:
  - `s = "aaaa"`
    - Substring choices `(0,0)`, `(1,1)`, `(2,3)` -> `"a"`, `"a"`, `"aa"` (all palindromes)
    - Output: count of valid index triplets
- **Java Starter**:
```java
class Solution {
    public long countPalTriples(String s) {
        
        return 0L;
    }
}
```

---

### ## 78) Distinct Decimal Values of Subsequences (Binary String)
- **LeetCode Equivalent**: [LC 940. Distinct Subsequences II](https://leetcode.com/problems/distinct-subsequences-ii/) binary interpretation.
- **Problem Statement**:
  Given a binary string `s` representing a sequence of bits. Consider all non-empty subsequences of `s`, interpret each subsequence as a **binary number**, and count how many **distinct decimal values** can be represented by these subsequences. Return the result modulo `10^9 + 7`.
- **Input / Output**:
  - Input: `String s`
  - Output: `int` count modulo `10^9 + 7`
- **Constraints**:
  - `1 ≤ s.length() ≤ 10^5`
- **Examples**:
  - `s = "101"`
    - Subsequences: `"1"`, `"0"`, `"1"`, `"10"`, `"01"`, `"11"`, `"101"`.
    - Distinct binary representations: `"1"` (1), `"0"` (0), `"10"` (2), `"01"` (1 - duplicate decimal), `"11"` (3), `"101"` (5).
    - Distinct decimal values: {0, 1, 2, 3, 5} (5 total values).
    - Output: `5`
- **Java Starter**:
```java
class Solution {
    public int distinctSubsequenceValues(String s) {
        
        return 0;
    }
}
```

---

### ## 81) OA: Maximize F(B) with OR and XOR
- **LeetCode Equivalent**: Range OR / Prefix-Suffix DP optimization. Similar to [LC 1310. XOR Queries of a Subarray](https://leetcode.com/problems/xor-queries-of-a-subarray/)
- **Problem Statement**:
  Given an integer array `A` and a positive integer `K`. You must choose a subsequence `B` of length exactly `2 * K` from `A`. The score of subsequence `B` is defined as:
  $$F(B) = (B_0 \mid B_1 \mid \dots \mid B_{K-1}) \oplus (B_K \mid B_{K+1} \mid \dots \mid B_{2K-1})$$
  (The bitwise OR of the first `K` elements XORed with the bitwise OR of the last `K` elements). Compute the **maximum possible score** $F(B)$ you can achieve.
- **Input / Output**:
  - Input: `int[] A`, `int K`
  - Output: `int` maximum score
- **Constraints**:
  - `2 * K ≤ A.length ≤ 10^5`
  - `0 ≤ A[i] ≤ 10^9`
- **Java Starter**:
```java
class Solution {
    public int maximizeF(int[] A, int K) {
        
        return 0;
    }
}
```
