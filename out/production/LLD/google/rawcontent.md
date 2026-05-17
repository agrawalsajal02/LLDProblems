```
Validate if the equation is syntactically correct.

Valid operators: +, -, a-z, (, )
Test cases:
Valid - a + x = b + (c + a)
Invalid - a + x = (ending with =; doesn't have RHS)
Invalid - a + -x = a + b (- in -x is a unary operator)

Question:
How to handle spaces

Equation   := Expression '=' Expression
Expression := Term (('+' | '-') Term)*
Term       := Variable | '(' Expression ')'
Variable   := [a-z]

class Solution {
    public boolean isValidEquation(String s) {
        Parser parser = new Parser(s);
        boolean ok = parser.parseEquation();
        return ok && parser.atEnd();
    }

    static class Parser {
        char[] ch;
        int i;

        Parser(String s) {
            ch = s.replaceAll("\\s+", "").toCharArray(); // strip spaces
            i = 0;
        }

        // Equation := Expression '=' Expression
        boolean parseEquation() {
            if (!parseExpression()) return false;
            if (!peek('=')) return false;
            i++; // consume '='
            return parseExpression();
        }

        // Expression := Term (('+' | '-') Term)*
        boolean parseExpression() {
            if (!parseTerm()) return false;
            while (peek('+') || peek('-')) {
                i++; // consume operator
                if (!parseTerm()) return false;
            }
            return true;
        }

        // Term := Variable | '(' Expression ')'
        boolean parseTerm() {
            if (peekVar()) {
                i++;
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

        boolean peek(char c) {
            return i < ch.length && ch[i] == c;
        }

        boolean peekVar() {
            return i < ch.length && (ch[i] >= 'a' && ch[i] <= 'z');
        }

        boolean atEnd() {
            return i == ch.length;
        }
    }

    // --- driver ---
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isValidEquation("a + x = b + (c + a)")); // true
        System.out.println(sol.isValidEquation("a + x ="));             // false
        System.out.println(sol.isValidEquation("a + -x = a + b"));      // false
        System.out.println(sol.isValidEquation("(a+b) = (c)"));         // true
        System.out.println(sol.isValidEquation("=a+b"));                // false
    }
}

```

[final_google_list](https://www.notion.so/final_google_list-25299cb551208190aecde5aa9b3dd0e0?pvs=21)

```markdown

# Google Interview Questions — Detailed Specs + Java Starters (MASTER)

> Format for every item:
> - **Problem** (clear statement)
> - **Input / Output**
> - **Constraints** (reasonable interview bounds; tune as needed)
> - **Examples** (≥2)
> - **Java Starter (LeetCode style)** — **no solution body**, just the method signature/shape

---

## 1) Collatz Steps
**Problem.** Given `n > 0`, repeatedly apply: if `n` is even `n = n/2` else `n = 3*n+1`, count steps until `n == 1`.

**Input / Output.**
- Input: `int n`
- Output: `int steps` to reach 1

**Constraints.**
- `1 ≤ n ≤ 10^12` (use 64-bit), worst-case steps unknown

**Examples.**
- `n = 1 → 0`
- `n = 6 → 8` (6→3→10→5→16→8→4→2→1)

**Java Starter.**
```java
class Solution {
    public int collatzSteps(long n) {

        return 0;
    }
}
```

---

## 2) Paint Fence with Horizontal/Vertical Strokes (Min Strokes)
**Problem.** You have `n` planks with heights `h[i]`. In one vertical stroke you can paint an entire single plank; in one horizontal stroke you can pick a segment `[l..r]` and reduce all heights in it by 1. Find **minimum strokes** to paint all to zero.

**Input / Output.**
- Input: `int[] heights`
- Output: `int` min strokes

**Constraints.**
- `1 ≤ n ≤ 10^5`, `0 ≤ heights[i] ≤ 10^9`

**Examples.**
- `[1,1,1] → 1`
- `[3,1,3] → 3`
- `[4,1,3,2] → 4`

**Java Starter.**
```java
class Solution {
    public int minStrokes(int[] heights) {

        return 0;
    }
}
```

---

## 3) Decode String with k[encoded] (nested)
**Problem.** Decode strings like `"3[a2[c]]" → "accaccacc"`. `k` is positive integer, nesting allowed.

**Input / Output.**
- Input: `String s`
- Output: decoded `String`

**Constraints.**
- `1 ≤ |s| ≤ 10^5`

**Examples.**
- `"3[a]2[bc]" → "aaabcbc"`
- `"2[abc]3[cd]ef" → "abcabccdcdcdef"`

**Java Starter.**
```java
class Solution {
    public String decodeString(String s) {

        return "";
    }
}
```

---

## 4) ScoreQueue: addElement(content, score) / getElement() with no-consecutive rule
**Problem.** Maintain elements `(content:String, score:int)`. `getElement()` returns **highest-score content**, but cannot return the **same content consecutively** (1-turn cooldown). After being returned, its score **decrements by 1** (can go negative). If no elements, return empty string.

**Input / Output.**
- Methods:
    - `void addElement(String content, int score)`
    - `String getElement()`

**Constraints.**
- Up to `10^5` operations; content unique at insertion

**Examples.**
- See prompt’s example sequence.

**Java Starter.**
```java
import java.util.*;

class ScoreQueue {
    public ScoreQueue() {}

    public void addElement(String content, int score) {

    }

    public String getElement() {

        return "";
    }
}
```

---

## 5) Streaming Logs Sorted by Timestamp (Stable on ties)
**Problem.** Logs arrive in any order; each has timestamp `"DD-MM-YY HH:MM:SS"` and message. Maintain **sorted-by-time** list; on tie, preserve **arrival order**.

**Input / Output.**
- Methods e.g. `add(String ts, String msg)`, `List<Log> getSorted()`
- Output stable-sorted logs

**Constraints.**
- Up to `10^5` logs

**Examples.**
- Given sequence in prompt → sorted order as shown.

**Java Starter.**
```java
class LogSorter {
    static class Log {
        String ts;
        String msg;
        long epoch; // parsed
        long seq;   // arrival order
    }

    public void add(String timestamp, String message) {

    }

    public java.util.List<Log> getSorted() {

        return java.util.Collections.emptyList();
    }
}
```

---

## 6) 3D Lattice: Torch/Wire Power Propagation
**Problem.** Nodes in a 3D lattice are either torch (value 16) or wire (0). Power propagates from torches to adjacent wires, losing 1 each step (but multiple torches can feed). Compute final graph values after propagation stabilizes.

**Input / Output.**
- Input: Graph representation (nodes, adjacency, initial values)
- Output: Final values per node

**Constraints.**
- Up to `N ≤ 10^5` nodes, sparse adjacency

**Examples.**
- See sample: `16->0->0` becomes `15->14->13` unless another 16 nearby.

**Java Starter.**
```java
class Solution {
    static class Node {
        String id;
        int value; // 16 for torch, 0 for wire initially
        java.util.List<String> neighbors;
    }
    public java.util.Map<String,Integer> propagate(java.util.Map<String, Node> graph) {

        return new java.util.HashMap<>();
    }
}
```

---

## 7) Assign Questions to Volunteers by Tags (Max Matching)
**Problem.** Each question has tags; each volunteer has tags. Assign **at most one** question to each volunteer, **at most one** volunteer to each question, match if they share any tag. Maximize #assigned.

**Input / Output.**
- Input: list of `Question{id,tags}`, list of `Volunteer{id,tags}`
- Output: matching pairs

**Constraints.**
- Up to `10^4` nodes total

**Examples.**
- Provided example → A→4, B→2, C→3, 1 unassigned

**Java Starter.**
```java
class Solution {
    static class Item {
        String id;
        java.util.Set<String> tags;
    }
    public java.util.List<String[]> assign(java.util.List<Item> questions, java.util.List<Item> volunteers) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 8) Reduce Selected File Paths to Minimal Set
**Problem.** Given all files and a selected subset, compress selection: if **all items in a directory** are selected, replace them by the directory path (one level up), recursively.

**Input / Output.**
- Input: `List<String> allFiles`, `List<String> selected`
- Output: `List<String> minimalSelection`

**Constraints.**
- Paths with `/`, no symlinks; up to `10^5` files

**Examples.**
- From prompt: `/a/d` and `/a/b/p.txt`

**Java Starter.**
```java
class Solution {
    public java.util.List<String> minimizeSelection(java.util.List<String> all, java.util.List<String> sel) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 9) Shortest Missing Byte Sequence (a–f) / General Bytes
**Problem.** Given byte stream limited to `{'a'..'f'}` (or general 0..255), find the **shortest string** over alphabet that **does not occur** as a substring.

**Input / Output.**
- Input: `String s` (or byte array), alphabet size `Σ`
- Output: missing substring

**Constraints.**
- `|s| ≤ 4GB` (memory considerations), return any valid

**Examples.**
- `"abcdefacbeddefd" → "aa"`

**Java Starter.**
```java
class Solution {
    public String shortestMissingSubstring(String s) {

        return "";
    }
}
```

---

## 10) Find Incompatible Pair of Unit Tests (Using TestRunner)
**Problem.** `testRunner(subset)` returns **false** if any pair in subset fails together; true otherwise. All single tests pass. Find **at least one failing pair**. Optimize when `testRunner` costs `O(n)` per run.

**Input / Output.**
- Input: `int n`, black-box `testRunner`
- Output: one bad pair `(i,j)`

**Constraints.**
- `n ≤ 10^5`, `testRunner` monotone

**Examples.**
- Multiple bad pairs possible; return any.

**Java Starter.**
```java
class Solution {
    interface Runner { boolean run(java.util.List<Integer> tests); }
    public int[] findBadPair(int n, Runner runner) {

        return new int[]{-1,-1};
    }
}
```

---

## 11) Validate Equation Syntax (+, -, variables, parentheses, '=')
**Problem.** Validate if equation is syntactically correct: letters a–z, operators `+,-`, parentheses, exactly one `=`, both sides valid expressions, unary minus allowed (like `-x`).

**Input / Output.**
- Input: `String expr`
- Output: `boolean`

**Constraints.**
- `1 ≤ |expr| ≤ 10^5`

**Examples.**
- `"a + x = b + (c + a)" → true`
- `"a + x =" → false`
- `"a + -x = a + b"` → true (unary '-')

**Java Starter.**
```java
class Solution {
    public boolean isValidEquation(String s) {

        return false;
    }
}
```

---

## 12) Expression to Target with Operators & Parentheses
**Problem.** Given digits/nums and operator set `+ - * / ( )`, generate **all expressions** that evaluate to target. (Variant of LC 282; may include parentheses.)

**Input / Output.**
- Input: `int[] nums`, `int target`
- Output: `List<String> expressions`

**Constraints.**
- `n ≤ 10`, beware overflow

**Examples.**
- `nums=[2,3,4], target=20 → "(2+3)*4"`

**Java Starter.**
```java
class Solution {
    public java.util.List<String> addOperators(int[] nums, int target) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 13) Recover Coin Denominations from Ways DP
**Problem.** Given `dp[0..T]` where `dp[x]` is **#ways** to make sum `x` using unknown positive coin set (unbounded), reconstruct **a possible coins set** consistent with dp.

**Input / Output.**
- Input: `int T`, `int[] dp` of length `T+1`
- Output: `List<Integer> coins`

**Constraints.**
- `dp[0]=1`, `dp[T]>0`

**Examples.**
- Example provided: `target=10`, dp: `[1,0,1,0,1,1,2,1,2,1,3] → coins=[2,5,6]`

**Java Starter.**
```java
class Solution {
    public java.util.List<Integer> recoverCoins(int[] dp) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 14) Valid Brackets with **Unique Pairing Constraint**
**Problem.** Given `s` of `()[]{}`, valid if normal bracket rules hold **and** each open bracket has **exactly one** valid closing (no ambiguity).

**Input / Output.**
- Input: `String s`
- Output: `boolean`

**Constraints.**
- `1 ≤ |s| ≤ 10^5`

**Examples.**
- `"{[()()]}" → true`
- `"[(])" → false`
- `"{[(])}" → false` (ambiguity)

**Java Starter.**
```java
class Solution {
    public boolean isValidUnique(String s) {

        return false;
    }
}
```

---

## 15) Duplicate Groups by Any Shared Property (3 props)
**Problem.** Each element has `id` and 3 properties. Two elements are in the same **duplicate group** if they share **any** property (transitively). Return all groups.

**Input / Output.**
- Input: `List<Element{id,p1,p2,p3}>`
- Output: `List<Set<String>> groups`

**Constraints.**
- `n ≤ 10^5`

**Examples.**
- Case 1 → `{{id1, id2}, {id3}}`
- Case 2 → `{{id1,id2,id3}}`

**Java Starter.**
```java
class Solution {
    static class E { String id, p1, p2, p3; }
    public java.util.List<java.util.Set<String>> duplicateGroups(java.util.List<E> items) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 16) Valid Tournament Draw
**Problem.** Given initial bracket order `[1..N]`, where best always wins, a draw is **valid** if in **each round** matches pair best-vs-worst, second-best-vs-second-worst, etc. Check validity across rounds.

**Input / Output.**
- Input: `int[] draw` (length power of 2)
- Output: `boolean`

**Constraints.**
- `N ≤ 2^16`

**Examples.**
- `[1,8,6,2,7,3,4,5] → valid` etc.

**Java Starter.**
```java
class Solution {
    public boolean isValidDraw(int[] draw) {

        return false;
    }
}
```

---

## 17) Assign Apartments to People
**Problem.** Each person prefers roommates or not, apartments have `numRooms`. Assign people to apartments (room per person) maximizing satisfaction (or just any valid mapping).

**Input / Output.**
- Input: `List<Apartment{aptNumber,numRooms}>`, `List<Person{name,wantsHousemates}>`
- Output: `Map<Integer, List<String>> apt->people`

**Constraints.**
- `n ≤ 10^4`

**Examples.**
- Custom small cases.

**Java Starter.**
```java
class Solution {
    static class Apartment { int aptNumber, numRooms; }
    static class Person { String name; boolean wantsHousemates; }
    public java.util.Map<Integer, java.util.List<String>> assign(java.util.List<Apartment> apts, java.util.List<Person> people) {

        return new java.util.HashMap<>();
    }
}
```

---

## 18) Max Score Jumps: score = (destIdx - srcIdx) * value[dest]
**Problem.** From index 0 to n-1, you can jump from `j` to `i>j` and earn `(i-j)*a[i]`. Find maximum total score to reach the last index (multiple jumps allowed).

**Input / Output.**
- Input: `int[] a`
- Output: `long` max score

**Constraints.**
- `1 ≤ n ≤ 2e5`, `|a[i]| ≤ 1e9`

**Examples.**
- `[3,12,9,10] → 32`
- `[1,2,3,4,5] → 20`

**Java Starter.**
```java
class Solution {
    public long maxJumpScore(int[] a) {

        return 0L;
    }
}
```

---

## 19) Stream of Floats: Return Any Triplet within D and Remove
**Problem.** Given unique float stream and threshold `D`, when possible return **any 3 values** `{a,b,c}` with all pairwise distances `≤ D`, and remove them from memory. Continue processing.

**Input / Output.**
- Methods: `init(double D)`, `List<Double> add(double x)` returns triplet or empty

**Constraints.**
- Up to `1e5` values

**Examples.**
- Stream `1,10,7,-2,8,...` with `D=5` → when `8` arrives, returns `7,8,10`

**Java Starter.**
```java
class TripletStream {
    public TripletStream(double D) {}

    public java.util.List<Double> add(double x) {

        return java.util.Collections.emptyList();
    }
}
```

---

## 20) Smallest Positive Not Formable (with +,-,*,/,(), reorder subset)
**Problem.** Given distinct numbers, form expressions using subset and `+ - * / ( )` exactly once each number at most once. Find **smallest positive** not representable.

**Input / Output.**
- Input: `int[] nums`
- Output: `int` smallest positive not formable

**Constraints.**
- `n ≤ 8` typical

**Examples.**
- `[1,2] → 4`

**Java Starter.**
```java
class Solution {
    public int smallestNotFormable(int[] nums) {

        return 0;
    }
}
```

---

## 21) Versioned JSON Storage — Design Diff API
**Problem.** Design an API to store all versions of a JSON object efficiently (diffs). Provide functions to write new version and fetch any version.

**Input / Output.**
- Methods: `put(JSONObject v)`, `get(int version)`

**Constraints.**
- Large objects; minimize storage

**Java Starter.**
```java
class JsonStore {
    public void put(String json) {

    }
    public String get(int version) {

        return "";
    }
}
```

---

## 22) Tile Grid with Shapes (Rect, Square, L-shape) — Fit Exactly?
**Problem.** Given `m×n` grid with holes to fill and a multiset of shapes (rotations/flips allowed), decide if they can tile grid without gaps/overlaps.

**Input / Output.**
- Input: grid size, list of shapes
- Output: `boolean`

**Constraints.**
- Small backtracking instance

**Java Starter.**
```java
class Solution {
    public boolean canTile(int m, int n, java.util.List<char[][]> shapes) {

        return false;
    }
}
```

---

## 23) Neighborhood Reassignment (capacities preserved, sorted, no duplicates inside)
**Problem.** Given list of neighborhoods (lists of house numbers), **redistribute** across same neighborhood sizes so each is sorted ascending and no duplicate in the **same** neighborhood.

**Input / Output.**
- Input: `List<List<Integer>> blocks`
- Output: `List<List<Integer>> result`

**Constraints.**
- Sum sizes ≤ `2e5`

**Examples.**
- Example in prompt

**Java Starter.**
```java
class Solution {
    public java.util.List<java.util.List<Integer>> rearrange(java.util.List<java.util.List<Integer>> blocks) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 24) Generate Substrings to Reconstruct String (unique list)
**Problem.** Given `S`, generate list `L` of substrings such that **concatenating in order** reproduces `S`, and **if a substring is new**, add to `L`. Return `L`.

**Input / Output.**
- Input: `String s`
- Output: `List<String> parts`

**Constraints.**
- `1 ≤ |s| ≤ 1e5`

**Examples.**
- `"GOOOOOOGLE" → ["G","O","OO","OOO","GL","E"]`

**Java Starter.**
```java
class Solution {
    public java.util.List<String> decompose(String s) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 25) Max Subarray Sum (Kadane) + Indices with nums[i]==nums[j]
**Problem.** Find max subarray sum. Follow-up: find indices `[i,j]` achieving max sum with constraint `nums[i]==nums[j]`.

**Input / Output.**
- Input: `int[] nums`
- Output: `int maxSum`, follow-up: `int[] ij`

**Java Starter.**
```java
class Solution {
    public int maxSubArray(int[] nums) {

        return 0;
    }
    public int[] maxSumEqualEnds(int[] nums) {

        return new int[]{-1,-1};
    }
}
```

---

## 26) Bank Serve Maximum Contiguously after Start
**Problem.** Initial funds `X`; array transactions `R[i]` (deposit negative? or positive?), once you choose a start index, you must process sequentially until a withdrawal exceeds funds. You may **skip** any prefix to pick start. Maximize #served.

**Input / Output.**
- Input: `int X`, `int[] R`
- Output: `int` max served

**Constraints.**
- `n ≤ 1e5`

**Examples.**
- From prompt.

**Java Starter.**
```java
class Solution {
    public int maxServed(int X, int[] tx) {

        return 0;
    }
}
```

---

## 27) Frog Min Steps with Variable Left/Right Bounds
**Problem.** Frog at `start` wants to reach `dest`; on each step choose to move left up to `l` or right up to `r` (0..l and 0..r). Find min steps.

**Input / Output.**
- Input: `int start, int dest, int l, int r`
- Output: `int` min steps or `-1` if impossible

**Java Starter.**
```java
class Solution {
    public int minFrogSteps(int start, int dest, int l, int r) {

        return -1;
    }
}
```

---

## 28) Odd/Even Jump Game with Updates (potentially infinite)
**Problem.** Array `A`, start at `S`. Move 1: **odd** → jump to **first left index** with value `A[cur]+1`. Move 2: **even** → jump to **first right index** with value `A[cur]+1`. After each jump, set previous position `+= X`. Stop when no jump possible; detect infinite loop.

**Input / Output.**
- Input: `int[] A`, `int X`, `int S`
- Output: `int` final index or `-1` if infinite

**Java Starter.**
```java
class Solution {
    public int oddEvenJumpGame(int[] A, int X, int S) {

        return -1;
    }
}
```

---

## 29) Circles Grouping (Overlap Connectivity)
**Problem.** Circles given by centers/radii. A **group** is connected if any overlap (touching counts). Determine if **all circles** are in one group.

**Input / Output.**
- Input: `double[][] circles`
- Output: `boolean`

**Constraints.**
- `n ≤ 1e5`

**Java Starter.**
```java
class Solution {
    public boolean singleCircleGroup(double[][] circles) {

        return false;
    }
}
```

---

## 30) Remove Leaves in Rounds (skip freshly created leaves until next round)
**Problem.** For a rooted tree, remove all leaves; newly formed leaves **cannot** be removed **in the same round** unless no other nodes remain. Output removal order by rounds.

**Input / Output.**
- Input: tree
- Output: `List<List<Integer>>` per round or flat order

**Java Starter.**
```java
class Solution {
    static class Node {
        int val;
        java.util.List<Node> children;
    }
    public java.util.List<java.util.List<Integer>> removeLeavesRounds(Node root) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 31) Recent Searches Store (Top N on focus, save on search)
**Problem.** DS that stores recent searches. `focus()` returns **N most recent**. `search(q)` saves query and returns N most recent.

**Input / Output.**
- Methods per above

**Java Starter.**
```java
class RecentSearches {
    public RecentSearches(int N) {}

    public java.util.List<String> focus() {

        return new java.util.ArrayList<>();
    }

    public java.util.List<String> search(String q) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 32) Min-Cost Path
**Problem.** Find a path with minimum cost in a grid/graph (generic prompt—clarify weights).

**Input / Output.**
- Input: weighted graph
- Output: min cost and path

**Java Starter.**
```java
class Solution {
    public int minPathCost(int[][] grid) {

        return 0;
    }
}
```

---

## 33) Percent-Variable Substitution (nested, with %% escaping)
**Problem.** Given map of variables, replace `%VAR%` in string with values recursively. `%%` → single `%`. Detect cycles.

**Input / Output.**
- Input: `String s`, `Map<String,String> env`
- Output: expanded `String`

**Java Starter.**
```java
class Solution {
    public String expand(String s, java.util.Map<String,String> env) {

        return "";
    }
}
```

---

## 34) Active Users per Time [0,N)
**Problem.** Given sessions `[start,end]` inclusive, and `N`, compute for each time `t ∈ [0, N)` number of active users.

**Input / Output.**
- Input: `int[][] sessions`, `int N`
- Output: `int[] counts` length `N`

**Java Starter.**
```java
class Solution {
    public int[] activeCounts(int[][] sessions, int N) {

        return new int[N];
    }
}
```

---

## 35) Seat Students to Minimize Adjacent Same Exam
**Problem.** Reorder students (list of exam ids) to minimize adjacent equal exams. Output one valid arrangement.

**Input / Output.**
- Input: `int[] exams`
- Output: `int[] arrangement`

**Java Starter.**
```java
class Solution {
    public int[] arrangeExams(int[] exams) {

        return new int[0];
    }
}
```

---

## 36) RPC Timeout Detection (earliest time)
**Problem.** Stream of logs `{id, time, type(start/end)}`. Given timeout `T`, report the earliest time at which some request has definitely timed out.

**Input / Output.**
- Input: logs, `T`
- Output: list of `(id, timeDetected)`

**Java Starter.**
```java
class Solution {
    static class Log { int id; int time; boolean isStart; }
    public java.util.List<int[]> timeouts(java.util.List<Log> logs, int T) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 37) Pick 5 Strings with All 25 Unique Letters
**Problem.** Given list of strings (length 5), pick 5 strings whose 25 letters are all distinct (no repeats).

**Input / Output.**
- Input: `List<String> words`
- Output: `List<String> picked` length 5 or empty

**Java Starter.**
```java
class Solution {
    public java.util.List<String> pickFive(java.util.List<String> words) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 38) Sort Odds, Keep Evens in Place
**Problem.** Sort only odd numbers, keeping even numbers at their indices.

**Input / Output.**
- Input: `int[] arr`
- Output: modified array

**Java Starter.**
```java
class Solution {
    public int[] sortOddsKeepEvens(int[] arr) {

        return arr;
    }
}
```

---

## 39) Consultant Schedule across Two Cities with Travel
**Problem.** Arrays `A[i], B[i]`: earnings if working in A or B on day `i`. Travel day earns 0. Choose schedule string `S` of 'A','B','T' maximizing total. Start in either city.

**Input / Output.**
- Input: `int[] A, int[] B`
- Output: `String S`

**Java Starter.**
```java
class Solution {
    public String bestSchedule(int[] A, int[] B) {

        return "";
    }
}
```

---

## 40) Managers with Salary < Avg of All Reports
**Problem.** Given org edges and salaries, count managers whose salary is **less than** the average of their direct+indirect employees.

**Input / Output.**
- Input: `edges`, `salary map`
- Output: `int count`

**Java Starter.**
```java
class Solution {
    public int countUnderpaid(java.util.Map<String, java.util.List<String>> reports, java.util.Map<String,Integer> salary) {

        return 0;
    }
}
```

---

## 41) Robot Grid Color → Direction Lookup to Reach Star
**Problem.** Grid with colors 0..7 and star -1. Build a mapping color→direction so robot from (0,0) reaches star by following directions based on current tile color.

**Input / Output.**
- Input: `int[][] grid`
- Output: `Map<Integer,Character>` mapping to 'U','D','L','R'

**Java Starter.**
```java
class Solution {
    public java.util.Map<Integer, Character> findLookup(int[][] grid) {

        return new java.util.HashMap<>();
    }
}
```

---

## 42) Sliding Window Mean of Last N Excluding Largest K
**Problem.** Stream of ints, maintain mean of last `N` elements **excluding largest K** in the window.

**Input / Output.**
- Methods: `add(num)`, `double mean()`

**Java Starter.**
```java
class MKMean {
    public MKMean(int N, int K) {}

    public void add(int num) {}

    public double mean() { return 0.0; }
}
```

---

## 43) LIS with Increasing Differences
**Problem.** Find LIS where the **differences** between consecutive elements form a **strictly increasing** sequence.

**Input / Output.**
- Input: `int[] nums`
- Output: `int length`

**Java Starter.**
```java
class Solution {
    public int lisIncreasingDiffs(int[] nums) {

        return 0;
    }
}
```

---

## 44) Dynamic Friend Connectivity with Add/Remove — Earliest All Connected
**Problem.** Stream of logs: addFriend(A,B) or removeFriend(A,B) with timestamps. Find earliest timestamp all users become connected.

**Input / Output.**
- Input: events sorted by time
- Output: earliest time or -1

**Java Starter.**
```java
class Solution {
    static class Event { int t; String type; int a, b; }
    public int earliestAllConnected(int n, java.util.List<Event> events) {

        return -1;
    }
}
```

---

## 45) Sparse Bit Array via Range Query (Find All Ones)
*(See #74 for starter; same spec.)*

---

## 46) On-call Rotation Timeline (merge intervals into labeled segments)
**Problem.** Given (name, start, end) intervals, output **non-overlapping** timeline segments with **list of on-call people** in each segment.

**Input / Output.**
- Input: `List<Interval{name,s,e}>`
- Output: `List<Segment{start,end, names[]}>`

**Java Starter.**
```java
class Solution {
    static class Interval { String name; int s, e; }
    static class Segment { int s, e; java.util.List<String> names; }
    public java.util.List<Segment> rotation(java.util.List<Interval> in) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 47) Is Coast? (Ocean-connected land)
**Problem.** Grid with 'X' land and '.' water. A '.' is **ocean** if connected to boundary '.' at least on one side (unbounded). A **coast** is any 'X' directly adjacent to **ocean**. Given `(r,c)`, return if it's a coast.

**Input / Output.**
- Input: `char[][] grid`, `int r,c`
- Output: `boolean`

**Java Starter.**
```java
class Solution {
    public boolean isCoast(char[][] grid, int r, int c) {

        return false;
    }
}
```

---

## 48) Highest Water Tower Cell Reaching Two Towns via Non-Increasing Paths
**Problem.** Given `heights` grid and two town coordinates, find the **highest elevation cell** from which water can flow (non-increasing, 4-dir) to **both towns**. Return coordinates.

**Input / Output.**
- Input: `int[][] heights`, `int[] town1`, `int[] town2`
- Output: `int[] bestCell` or `{-1,-1}`

**Java Starter.**
```java
class Solution {
    public int[] bestWaterTower(int[][] heights, int[] town1, int[] town2) {

        return new int[]{-1,-1};
    }
}
```

---

## 49) Array to Zero with Range Queries (subtract 1 from any subsequence within [l,r])
**Problem.** Given array `arr` and queries `[l,r]`, per query you may subtract 1 from any **subsequence** of indices in `[l,r]`. After all queries, can you make all zeros?

**Input / Output.**
- Input: `int[] arr`, `int[][] queries`
- Output: `boolean`

**Java Starter.**
```java
class Solution {
    public boolean canZero(int[] arr, int[][] queries) {

        return false;
    }
}
```

---

## 50) Common Free Time of All Persons
**Problem.** Given blocks `{personId,start,end}` and `totalTime`, return intervals where **all persons** are free.

**Input / Output.**
- Input: `List<Block> blocks`, `int totalTime`
- Output: `List<int[]> intervals`

**Java Starter.**
```java
class Solution {
    static class Block { int personId, start, end; }
    public java.util.List<int[]> allFree(java.util.List<Block> blocks, int totalTime) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 51) Flip 1-bit-per-pixel Image Horizontally In-Place
**Problem.** Image stored as `byte[]`, width `w` bits (multiple of 8), height `h`. Flip horizontally (reverse bits per row) **in place**.

**Input / Output.**
- Input: `byte[] image`, `int wBits`, `int h`
- Output: (mutates input)

**Java Starter.**
```java
class Solution {
    public void flipHorizontally(byte[] image, int wBits, int h) {

    }
}
```

---

## 52) Equal-Area Vertical Line across Overlapping Rectangles
*(See #80 for spec; Java starter variant below).*

**Java Starter.**
```java
class Solution {
    public double splitEqualArea(int[][] rects) { // rect: [x1,y1,x2,y2]

        return 0.0;
    }
}
```

---

## 53) Cat & Mouse Safest Path with Blockers (O(n^2) expected)
**Problem.** Grid with mouse, cat, food; cells with water(1) blocked for mouse (cat can traverse water). Find the **maximum safeness** path or minimal maximum risk so mouse reaches food avoiding cat proximity, with blockers. (Variant of safest path with constraints.)

**Input / Output.**
- Input: `int[][] grid`, `int[] mouse`, `int[] cat`, `int[] food`
- Output: `int` safeness or distance per spec

**Java Starter.**
```java
class Solution {
    public int findDistance(int[][] grid, int[] mouse, int[] cat, int[] food) {

        return -1;
    }
}
```

---

## 54) Count Words with Prefix (Binary Search on Sorted List)
**Problem.** Given sorted array of strings and a prefix, count how many start with the prefix.

**Input / Output.**
- Input: `String[] arr`, `String prefix`
- Output: `int` count

**Java Starter.**
```java
class Solution {
    public int countWithPrefix(String[] arr, String prefix) {

        return 0;
    }
}
```

---

## 55) Shortest Path to Favorite Cities (Dijkstra from A)
**Problem.** Weighted graph (cities/roads). From city A, given favorites `[F1..Fn]`, determine which favorite is reached **fastest** and the time.

**Input / Output.**
- Input: graph, source A, favorites
- Output: `(city, time)`

**Java Starter.**
```java
class Solution {
    public String fastestFavorite(java.util.Map<String, java.util.List<String[]>> graph, String A, java.util.Set<String> favs) {

        return "";
    }
}
```

---

## 56) Variable Resolution with `#a#`-style references
**Problem.** Given variable map where values may reference other vars via `#var#`, expand string fully.

**Input / Output.**
- Input: `Map<String,String> vars`, `String input`
- Output: expanded `String`

**Java Starter.**
```java
class Solution {
    public String resolve(java.util.Map<String,String> vars, String input) {

        return "";
    }
}
```

---

## 57) Max Possible Transactions (Stop when can't withdraw)
**Problem.** Given initial total `T` and an array where **positive = withdraw**, **negative = deposit**. You can process transactions in any order, but once you hit a withdraw > balance you must stop. Maximize number processed.

**Input / Output.**
- Input: `int T, int[] tx`
- Output: `int` max count

**Java Starter.**
```java
class Solution {
    public int maxTransactions(int T, int[] tx) {

        return 0;
    }
}
```

---

## 58) Favorites-First Iterator (No duplicates)
**Problem.** Given `photos[]` and `favorites[]` (no dups), iterate favorites first (in given order), then remaining photos (original order), without duplicates.

**Input / Output.**
- Input: arrays
- Output: iterator or list

**Java Starter.**
```java
class Solution {
    public java.util.List<String> favoriteFirst(String[] photos, String[] favs) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 59) Message Deduplicator (within 10s both sides discarded)
**Problem.** From stream `(t, msg)`, discard duplicates within a 10s window **both occurrences**. Output valid messages in input order.

**Input / Output.**
- Input: stream
- Output: filtered list

**Java Starter.**
```java
class Solution {
    static class M { int t; String s; }
    public java.util.List<M> filter(java.util.List<M> in) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 60) Combination Lock Count within Tolerance (two combos)
**Problem.** 3-digit lock, digits wrap `[0..numOptions-1]`. Two valid combos (user,bypass). A trial opens if each digit is within `tolerance` (circular) of **either** combo. Count distinct combinations that open.

**Input / Output.**
- Input: `int[] user, int[] bypass, int tolerance, int numOptions`
- Output: `int` count

**Java Starter.**
```java
class Solution {
    public int countNumberCombinations(int[] user, int[] bypass, int tolerance, int numOptions) {

        return 0;
    }
}
```

---

## 61) Rope-like Substring from Internal Node
**Problem.** Tree: internal nodes have `len`, leaves have text. Given internal node and `(start,length)` within its concatenation, return substring.

**Input / Output.**
- Input: rope-like structure, index, length
- Output: `String`

**Java Starter.**
```java
class Solution {
    static class Node { boolean leaf; int len; String text; Node left,right; }
    public String findSubstring(Node node, int start, int length) {

        return "";
    }
}
```

---

## 62) Prefix Count via Two Binary Searches
*(See #54)*

---

## 63) Subsequence in A allowing ≤1 change in B (not first char)
**Problem.** Check if `B` is a subsequence of `A` after changing **at most one** character in `B` **excluding its first character**. Return **starting index (1-based)** where it occurs, else `-1`.

**Input / Output.**
- Input: `String A, String B`
- Output: `int` index1based or -1

**Java Starter.**
```java
class Solution {
    public int findSubsequenceWithOneChange(String A, String B) {

        return -1;
    }
}
```

---

## 64) Partition Equal Subset Sum with Negatives
**Problem.** Given `nums` (can be negative), determine if partition into two subsets with equal sum is possible. Follow-ups: return one such partition; return all.

**Input / Output.**
- Input: `int[] nums`
- Output: `boolean` / variant returns lists

**Java Starter.**
```java
class Solution {
    public boolean canPartition(int[] nums) {

        return false;
    }
}
```

---

## 65) Burgers Grid: Shortest Path Visiting All Burgers then End
**Problem.** Grid with `S`,`E`,`B`,`O`. Must visit all `B` before reaching `E`. Min path length (4-dir moves).

**Input / Output.**
- Input: `char[][] grid`
- Output: `int` shortest distance

**Java Starter.**
```java
class Solution {
    public int shortestPathEatAll(char[][] grid) {

        return -1;
    }
}
```

---

## 66) IP Range → Country Lookup
**Problem.** Given disjoint/sorted IP ranges `[start,end] → country`. For a query IPv4 string, return its country (or “UNKNOWN”).

**Input / Output.**
- Input: ranges list, `String ip`
- Output: `String country`

**Java Starter.**
```java
class Solution {
    static class Range { long start, end; String country; }
    public String findCountry(java.util.List<Range> ranges, String ip) {

        return "";
    }
}
```

---

## 67) Sum of Squares to X (any list) + shortest list
**Problem.** Given `x`, return **any** multiset whose squares sum to `x`. Follow-up: **shortest** list.

**Input / Output.**
- Input: `int x`
- Output: `List<Integer>`

**Java Starter.**
```java
class Solution {
    public java.util.List<Integer> sumSquares(int x) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 68) Palindromic Triples Count
**Problem.** Given string `S`, count triples of pal substrings `(i1,j1),(i2,j2),(i3,j3)` with `i1≤j1<i2≤j2<i3≤j3`.

**Input / Output.**
- Input: `String s`
- Output: `long` count

**Java Starter.**
```java
class Solution {
    public long countPalTriples(String s) {

        return 0L;
    }
}
```

---

## 69) Subsets with LCM divisible by k
**Problem.** Count subsets whose LCM is divisible by `k`.

**Input / Output.**
- Input: `int[] arr`, `int k`
- Output: `long` count

**Java Starter.**
```java
class Solution {
    public long countSubsetsLCMDivK(int[] arr, int k) {

        return 0L;
    }
}
```

---

## 70) Binary Tree → String Representation
**Problem.** Given `Node(char, left, right)`, return preorder-like string (exact format per prompt’s examples).

**Input / Output.**
- Input: root
- Output: `String`

**Java Starter.**
```java
class Solution {
    static class Node { char val; Node left,right; }
    public String serialize(Node root) {

        return "";
    }
}
```

---

## 71) Function Evaluator: add, sub, mul, div, pow (floats)
**Problem.** Evaluate expressions like `mul(2e3, sub(4,2))`. No overflow/div0 cases.

**Input / Output.**
- Input: `String expr`
- Output: `double`

**Java Starter.**
```java
class Solution {
    public double eval(String s) {

        return 0.0;
    }
}
```

---

## 72) Robot Sort with One Empty Slot
**Problem.** Array `a1..an,_` with one empty slot. Robot can move one box to empty slot per operation. Sort ascending with `_` at either end. Minimize moves.

**Input / Output.**
- Input: `int[] arr` plus empty position
- Output: sequence of moves or count

**Java Starter.**
```java
class Solution {
    public int minMovesToSortWithHole(int[] arr) {

        return 0;
    }
}
```

---

## 73) Org Relations: manager/peer/is_manager (online updates)
**Problem.** Support `manager a b`, `peer a b`, `is_manager a b` queries online.

**Input / Output.**
- Output: boolean for `is_manager`

**Java Starter.**
```java
class Org {
    public void manager(String a, String b) {}

    public void peer(String a, String b) {}

    public boolean isManager(String a, String b) { return false; }
}
```

---

## 74) LIS diff = 1 (again)
*(Handled above as #1 / #82 variants)*

---

## 75) Musical Notes Sequences to Sum 12 with Transitions
**Problem.** Notes ∈ {1,2,3}, valid transitions given (map). Find **all sequences** summing to 12 with valid transitions including wrap last→first.

**Input / Output.**
- Input: transitions, target=12
- Output: `List<List<Integer>>`

**Java Starter.**
```java
class Solution {
    public java.util.List<java.util.List<Integer>> musicSequences(java.util.Map<Integer, java.util.List<Integer>> nexts) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 76) MK-Average (m,k) streaming (reference)
**Problem.** Maintain MKAverage over stream: take last m, drop smallest k and largest k, return floor average.

**Java Starter.**
```java
class MKAverage {
    public MKAverage(int m, int k) {}
    public void addElement(int num) {}
    public int calculateMKAverage() { return -1; }
}
```

---

## 77) Weighted Random from Stream on Demand
**Problem.** Stream of non-negative integers; on seeing `0`, output a random number from previously seen (non-zero) with probability proportional to its frequency.

**Input / Output.**
- Input: stream
- Output: numbers at zeros

**Java Starter.**
```java
class WeightedStreamPicker {
    public void add(int x) {}
    public Integer pickOnZero() { return null; }
}
```

---

## 78) Distinct Decimal Values of Subsequences (binary string)
**Problem.** Given binary string `s`, consider all **non-empty subsequences**, interpret as **binary numbers**, count **distinct decimal values** modulo `1e9+7`.

**Input / Output.**
- Input: `String s`
- Output: `int` mod

**Java Starter.**
```java
class Solution {
    public int distinctSubsequenceValues(String s) {

        return 0;
    }
}
```

---

## 79) Remove All Parentheses from Formula
**Problem.** Given letters and `+,-,()` remove parentheses by applying signs: e.g., `a-(b+c) → a-b-c`, `a-(a-b) → b` (assumes like terms?). Return simplified string with only `+/-` and letters, no parentheses.

**Input / Output.**
- Input: `String s`
- Output: `String`

**Java Starter.**
```java
class Solution {
    public String removeParentheses(String s) {

        return "";
    }
}
```

---

## 80) Array Differences (A−B and B−A) with Duplicates & Order-Preserving
**Problem.** Given arrays possibly with duplicates, output two arrays: elements in A not consumed by B (and vice versa). Second variant: **preserve order** of appearance.

**Input / Output.**
- Input: `int[] A, int[] B`
- Output: `int[][] diffs`

**Java Starter.**
```java
class Solution {
    public int[][] multisetDiff(int[] A, int[] B) {

        return new int[][]{};
    }
    public int[][] multisetDiffPreserveOrder(int[] A, int[] B) {

        return new int[][]{};
    }
}
```

---

## 81) OA: Maximize F(B) with OR and XOR
**Problem.** Given array `A` and `K`, choose subsequence `B` of length `2K` to maximize `F(B) = (OR of first K) XOR (OR of last K)`.

**Input / Output.**
- Input: `int[] A`, `int K`
- Output: `int` max value

**Java Starter.**
```java
class Solution {
    public int maximizeF(int[] A, int K) {

        return 0;
    }
}
```

---

## 82) Electricity Network over Roads Only (connect all cities)
**Problem.** Grid: 1=road, 0=forbidden, -1=city. Connect all cities through **roads only** with minimal build (edges). Output chosen road cells.

**Input / Output.**
- Input: `int[][] grid`
- Output: list of cells / grid

**Java Starter.**
```java
class Solution {
    public java.util.List<int[]> connectCities(int[][] grid) {

        return new java.util.ArrayList<>();
    }
}
```

---

## 83) Cover Holes with 1×n and m×1 Planks (min planks)
**Problem.** Grid `m×n` with holes; cover all holes using vertical or horizontal planks spanning contiguous holes; minimize number of planks.

**Input / Output.**
- Input: `int[][] holes`
- Output: `int` min planks

**Java Starter.**
```java
class Solution {
    public int minPlanks(int[][] grid) {

        return 0;
    }
}
```

---

## 84) Rectangle Area (Union of Two)
**Problem.** Given two axis-aligned rectangles, return total area covered.

**Input / Output.**
- Input: rectangle coords
- Output: `int` area

**Java Starter.**
```java
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2,
                           int bx1, int by1, int bx2, int by2) {

        return 0;
    }
}
```

---

## 85) Graph Travel Max Weight within Time (A→…→A ≤ 24)
**Problem.** Weighted undirected graph, start at A, return to A within 24 time units, maximize sum of node weights collected once.

**Input / Output.**
- Input: graph, node weights
- Output: `int maxWeight, int timeUsed`

**Java Starter.**
```java
class Solution {
    public int[] maxWeightWithinTime(java.util.Map<String, java.util.List<String[]>> graph,
                                     java.util.Map<String,Integer> weight,
                                     String start, int limit) {

        return new int[]{0,0};
    }
}
```

---

## 86) Flip Image II (bit-level) — already (#51)

---

## 87) Vertical Equal Area Line — already (#52)

---

## 88) Google Logger Rate Limiter Variant (within ±10s) — see #59

---

## 89) Range Query Sparse Bit Array — see #74/#45

---

## 90) Greedy Offset Commit Order
**Problem.** Given order of processed offsets, output commit order: when offset `X` can be committed (0 or all <X committed/ready), commit; else `-1`.

**Input / Output.**
- Input: `int[] processed`
- Output: `int[] commitOrder`

**Java Starter.**
```java
class Solution {
    public int[] greedyCommit(int[] processed) {

        return new int[processed.length];
    }
}
```

---

## 91) Largest k-digit Number from Array (preserve order)
**Problem.** Pick `k` elements in order to maximize the resulting number (concatenate digits).

**Input / Output.**
- Input: `int[] arr, int k`
- Output: `String` largest number

**Java Starter.**
```java
class Solution {
    public String largestNumberK(int[] arr, int k) {

        return "";
    }
}
```

---

## 92) Phone Screen: Overlap Intervals? & Min Cars for Bookings
**Problem.**
1) Given two intervals, check overlap.
2) Given many intervals, assign minimal cars (resources) to cover all bookings.

**Java Starter.**
```java
class Solution {
    public boolean overlap(int s1, int e1, int s2, int e2) { return false; }
    public int minCars(int[][] intervals) { return 0; }
}
```

---

## 93) Address List Query with Null Wildcards in O(1)
**Problem.** Tuples like `(a,b,c,d)`; query may contain `"null"` for any field and should answer existence in O(1).

**Input / Output.**
- Input: list, then queries
- Output: boolean per query

**Java Starter.**
```java
class Solution {
    public void build(java.util.List<String[]> addresses) {}

    public boolean exists(String a, String b, String c, String d) { return false; }
}
```

---

## 94) Bomb Pair Detonation to Maximize Sum (non-overlapping ranges)
**Problem.** bombs[i] radius at i removes `[i-r,i+r]`. Choose **pairs** (i,j) that don’t interfere, maximize sum radii.

**Input / Output.**
- Input: `int[] bombs`
- Output: `int` max

**Java Starter.**
```java
class Solution {
    public int maxDetonationSum(int[] bombs) {

        return 0;
    }
}
```

---

## 95) File System Entity Size by ID
**Problem.** Given FS map EntityID→Entity{type,name,children,size}, compute total size for given entity (dirs sum of children).

**Input / Output.**
- Input: `Map<Integer,Entity>`, `int id`
- Output: `long size`

**Java Starter.**
```java
class Solution {
    static class Entity {
        int id; String type; String name;
        java.util.List<Integer> children; long size;
    }
    public long computeSize(java.util.Map<Integer,Entity> fs, int id) {

        return 0L;
    }
}
```

---

## 96) Duplicates by 3 Properties — see #15

---

## 97) Equal-Area Vertical Line — see #52/#80

---

## 98) Song Shuffler (not in last K) — see #81

---

**Note:** For items that appear multiple times in your notes (e.g., Song Shuffler, Equal-Area Line, LIS diff=1), I provided one canonical spec + starter and cross-referenced repeats to keep this sheet usable.

---

## Want me to split this into categories (Arrays, Graphs, Strings, DP, Design) and auto-add a clickable Table of Contents at the top?
Say the word, and I’ll refactor this master into **TOC + sections** so you can jump fast during prep.

---
### Q87. GetTotalCount – Word Count API with Workers

**Problem:**  
Implement the function `GetTotalCount(num_docs: int)` which returns the total number of words in documents with ID numbers in the range [0, num_docs).  
You are given a Web API:  
`https://www.count-words.com/document/{docId}?worker_id={id}`
- A worker can handle only one request at a time.
- If another request is sent while it is busy, it fails.

**Follow-up:**  
`GetTotalCount(num_docs: int, num_workers: int)` where `num_workers` is the number of worker IDs available [0..num_workers).

**Input / Output:**
- Input: `num_docs = 5`
- Output: sum of words across docIds [0..4].

**Constraints:**
- num_docs ≥ 0
- worker_id ≥ 0
- API latency may vary (large docs can take long).

**Examples:**
- `GetTotalCount(5)` → total words in docs 0–4
- `GetTotalCount(5, 3)` → same but with workers [0,1,2]

**Java Starter:**
```java
class Solution {
    public int getTotalCount(int numDocs) {
        // TODO: sequentially call API and aggregate
    }

    public int getTotalCount(int numDocs, int numWorkers) {
        // TODO: parallelize with worker pool
    }
}
```

---
### Q88. Car Pooling (LeetCode 1094)

**Problem:**  
You are given `capacity` and a list of trips. Each trip has `[numPassengers, from, to]`.  
Check if it is possible to pick up and drop all passengers.

**Constraints:**
- 1 ≤ trips.length ≤ 1000
- 1 ≤ numPassengers ≤ 100
- 0 ≤ from < to ≤ 1000
- 1 ≤ capacity ≤ 10^5

**Examples:**
- Input: trips = [[2,1,5],[3,3,7]], capacity = 4 → Output: false
- Input: trips = [[2,1,5],[3,3,7]], capacity = 5 → Output: true

**Java Starter:**
```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        // TODO: simulate passenger changes
    }
}
```

---
### Q89. Sequence Reconstruction (LeetCode 444)

**Problem:**  
Given `nums` (a permutation of [1..n]) and subsequences `sequences`, check if `nums` is the *only* shortest supersequence that includes all sequences.

**Constraints:**
- 1 ≤ n ≤ 10^4
- sequences[i].length ≥ 1

**Examples:**
- nums = [1,2,3], sequences = [[1,2],[1,3]] → false (two supersequences possible)
- nums = [1,2,3], sequences = [[1,2],[1,3],[1,2,3]] → true

**Java Starter:**
```java
class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        // TODO: use topological ordering
    }
}
```

---
### Q90. Longest Duplicate Substring (LeetCode 1044)

**Problem:**  
Given a string `s`, return the longest duplicated substring (appears ≥2 times).  
If none exists, return `""`.

**Constraints:**
- 2 ≤ s.length ≤ 3 * 10^4
- s consists of lowercase English letters.

**Examples:**
- Input: "banana" → Output: "ana"
- Input: "abcd" → Output: ""

**Java Starter:**
```java
class Solution {
    public String longestDupSubstring(String s) {
        // TODO: binary search + rolling hash / suffix array
    }
}
```

---
### Q91. Restaurant Waitlist APIs

**Problem:**  
Implement APIs for restaurant table allocation:
- `joinWaitlist(groupSize)`
- `leaveWaitlist(groupId)`
- `allocateTable(size)` → allocate to first group of exact size if possible, else nearest smaller.

**Constraints:**
- Multiple groups can join/leave.
- Tables allocated in order of arrival.

**Example:**
- Groups: [5, 3, 2]
- allocateTable(3) → assigns group of size 3.
- allocateTable(4) → assigns group of size 2.

**Java Starter:**
```java
class WaitlistSystem {
    public void joinWaitlist(int groupSize) {
        // TODO
    }
    public void leaveWaitlist(int groupId) {
        // TODO
    }
    public int allocateTable(int tableSize) {
        // TODO: return allocated groupId
    }
}
```

---
### Q92. Chat System APIs

**Problem:**  
Implement chat APIs:
- `userWithMostChats()` → return userId with max messages.
- `deleteUser(userId)` → remove user and all their chats.

**Input / Output:**  
Messages are tuples `(From, To, Message)`.

**Constraints:**
- User deletion must remove chats globally.

**Example:**
- Chats: [(A,B,"hi"), (B,C,"yo"), (A,C,"hello")]
- userWithMostChats() → A or B
- deleteUser(B) → remove all chats with B.

**Java Starter:**
```java
class ChatSystem {
    public String userWithMostChats() {
        // TODO
    }
    public void deleteUser(String userId) {
        // TODO
    }
}
```

---
### Q93. IP Range to City Mapping

**Problem:**  
You are given mappings: `(IP_Start, IP_End, City)`.  
Given an IP address, return its city.

**Constraints:**
- IPs stored in ranges.
- No overlaps.

**Examples:**
- Input: (10–20 → "NY"), query=15 → "NY"
- query=30 → not found

**Java Starter:**
```java
class Solution {
    public String findCity(List<int[]> ipRanges, int queryIp) {
        // TODO: binary search over ranges
    }
}
```

---
### Q94. Stream Triplets within Threshold

**Problem:**  
Given an endless stream of integers and a threshold `d`, find triplets such that:  
`max - min ≤ d`.  
Once used, numbers can’t be reused.

**Examples:**
- d=2, stream=[10,5,7,6,8,3,4,1,2]
- Output: [[5,6,7],[1,2,3]]

**Java Starter:**
```java
class Solution {
    public List<List<Integer>> findTriplets(int[] stream, int d) {
        // TODO
    }
}
```

---
### Q95. Shortest Path with Obstacles

**Problem:**  
Given a directed graph `n` nodes, edges, start=A, target=B, and forbidden nodes.  
Find shortest path (min edges) avoiding forbidden nodes. Return -1 if impossible.

**Java Starter:**
```java
class Solution {
    public int shortestPath(int n, int[][] edges, int start, int target, Set<Integer> forbidden) {
        // TODO: BFS ignoring forbidden
    }
}
```

---
### Q96. Merge Two Screenshots

**Problem:**  
Given two screenshots of size h×w, merge into one without overlapping repeated rows.

**Examples:**  
Screen1:  
[1 2 3]  
[4 4 5]  
[6 7 8]  
[9 10 11]

Screen2:  
[6 7 8]  
[9 10 11]  
[13 14 15]  
[16 17 19]

Output:  
[1 2 3]  
[4 4 5]  
[6 7 8]  
[9 10 11]  
[13 14 15]  
[16 17 19]

**Java Starter:**
```java
class Solution {
    public int[][] mergeScreenshots(int[][] screen1, int[][] screen2) {
        // TODO: detect overlap and merge
    }
}
```

```