Below is the consolidated segregation from **all 10 chunks**.

## DSA Questions

### Arrays / Two Pointers / Binary Search

* **Question:** Given a sorted array, reorder elements based on sorted order of their squares.

  * **Details:** Example input `[-3,-2,-1,0,1,2,3,4]`, output `[0,-1,1,-2,2,-3,3,4]`. Expected optimized `O(n)` two-pointer / merge-style solution.
  * **Source:** [Uber | SDE2](https://leetcode.com/discuss/post/7619138/uber-sde2-by-anonymous_user-1g2h/), [Uber SDE-2 Interview Experience](https://leetcode.com/discuss/post/7302014/uber-sde-2-interview-experience-by-anony-6fmj/)

* **Question:** Find the `k-th` smallest square in a sorted array in less than `O(k)`.

  * **Details:** Follow-up to square-sorted array problem. Example `[-3,-2,-1,0,1,2,3,4], k=3`, output `1`. Binary-search optimization was expected.
  * **Source:** [Uber interview sde1](https://leetcode.com/discuss/post/7345883/uber-interview-sde1-by-anonymous_user-y70t/), [Uber | SDE2](https://leetcode.com/discuss/post/7619138/uber-sde2-by-anonymous_user-1g2h/)

* **Question:** Split Array Largest Sum.

  * **Details:** Initially tried DP, interviewer nudged toward binary search on answer.
  * **Source:** [Uber SDE2 Chances](https://leetcode.com/discuss/post/6947609/uber-sde2-chances-by-anonymous_user-h4vb/)

* **Question:** Capacity to Ship Packages Within D Days.

  * **Details:** Classic binary search on answer.
  * **Source:** [Jivox / Davinci Commerce - Senior Software Engineer](https://leetcode.com/discuss/post/8067747/jivox-davinci-commerce-senior-software-e-tlk4/)

* **Question:** Given a number `x` and an array, determine if all elements can be made equal to some `y` by adding/subtracting values up to `x`.

  * **Details:** First version had `x = 1`; follow-up had `x >= 1`.
  * **Source:** [Uber L4 - SDE2 Android Interview experience](https://leetcode.com/discuss/post/7370037/uber-l4-sde2-android-interview-experienc-2kha/)

* **Question:** Find maximum length `L` such that every subarray of length `L` has sum strictly less than `k`.

  * **Details:** `1 <= n <= 2 * 10^5`, `1 <= a[i] <= 10^9`, `1 <= k <= 10^18`. Example `a=[3,1,2,4], k=8`, output `3`.
  * **Source:** [Uber OA 15th June 2025](https://leetcode.com/discuss/post/6846683/uber-oa-15th-june-2025-by-anonymous_user-voqx/)

* **Question:** Maximum Points You Can Obtain from Cards.

  * **Details:** Sliding window / prefix-sum style problem.
  * **Source:** [Uber | Software Engineer II Bangalore](https://leetcode.com/discuss/post/7300207/uber-software-engineer-ii-se-2-bangalore-wyl8/), [Uber | L4 | Bangalore | Offer](https://leetcode.com/discuss/post/6770968/uber-l4-bangalore-offer-by-10399vk-c73t/)

* **Question:** Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit.

  * **Details:** Asked in driver-cab domain. Expected final `O(n)` solution using sliding window + two deques for min/max. Interviewer asked brute force, optimized versions, base cases, TC/SC.
  * **Source:** [Uber SDE-2 Interview Experience L4 Bangalore](https://leetcode.com/discuss/post/7182332/uber-sde-2-interview-experience-l4-banga-fd1d/), [Gave 100+ interviews](https://leetcode.com/discuss/post/7347409/gave-100-interviews-top-10-questions-tha-o8rr/)

---

### Strings / Palindrome / Trie

* **Question:** Given strings `s` and `t`, find minimum times `s` must be appended to itself so `t` becomes a subsequence.

  * **Details:** Lowercase English chars only. Example `s=boy`, `t=oyb`, answer `2` because `"boyboy"` contains `oyb`.
  * **Source:** [Uber | Software Engineer II Bangalore](https://leetcode.com/discuss/post/7300207/uber-software-engineer-ii-se-2-bangalore-wyl8/)

* **Question:** Find the closest palindrome.

  * **Details:** LeetCode 564 / Find Closest Palindrome. Edge cases and follow-ups discussed.
  * **Source:** [Uber SDE-2 Interview Experience](https://leetcode.com/discuss/post/7302014/uber-sde-2-interview-experience-by-anony-6fmj/), [Uber L4 Interview Experience US](https://leetcode.com/discuss/post/7217970/uber-sde2-interview-experience-us-offer-hfj64/), [Uber SDE-II Interview Experience](https://leetcode.com/discuss/post/6827210/uber-sde-ii-interview-experience-march-2-oj2b/)

* **Question:** Find the next greater palindrome number.

  * **Details:** Expected mirror + carry propagation approach.
  * **Source:** [Uber L5A SSE interview experience](https://leetcode.com/discuss/post/7043175/uber-l5a-sse-interview-experience-by-nik-qpdr/)

* **Question:** Minimum number of characters/elements to append at end of string to make it palindrome.

  * **Details:** Asked in Uber Android phone screen.
  * **Source:** [Uber L4 SDE2 Android Interview experience](https://leetcode.com/discuss/post/7370037/uber-l4-sde2-android-interview-experienc-2kha/)

* **Question:** Alien Dictionary.

  * **Details:** Topological sort. Asked multiple times. Need handle invalid prefix, graph construction, indegree, ordering, cycles, TC/SC.
  * **Source:** [Uber SDE 2 Interview Experience](https://leetcode.com/discuss/post/7327020/uber-sde-2-interview-experience-by-anony-hb8r/), [Uber L4 Interview Experience Ongoing](https://leetcode.com/discuss/post/7196883/uber-l4-interview-experience-ongoing-by-xj5u8/), [Uber L4 Interview Experience US](https://leetcode.com/discuss/post/7217970/uber-sde2-interview-experience-us-offer-hfj64/), [Uber L4 Interview Process](https://leetcode.com/discuss/post/5833928/uber-l4-interview-process-by-anonymous_u-gysb/), [Uber SSE Bangalore L5A](https://leetcode.com/discuss/post/6628212/uber-sse-bangalore-l5-a-by-anonymous_use-0h6x/)

* **Question:** String + map problem, then solve with Trie.

  * **Details:** Brute force → optimized with map and prefix window → Trie discussion.
  * **Source:** [Uber Off-Campus 2025 for SDE 1](https://leetcode.com/discuss/post/6892974/uber-off-campus-2025-for-sde-1-by-anonym-br54/)

* **Question:** Given a string with lowercase letters and hashes, find longest safest path.

  * **Details:** `#` is blocker. Follow-up: allow one `#` to be considered safe.
  * **Source:** [Microsoft Interview Experience SDE1](https://leetcode.com/discuss/post/7361123/microsoft-interview-experience-sde-1-l60-ev95/)

---

### Graphs / BFS / DFS / Shortest Path

* **Question:** Word Ladder / minimum transformation path.

  * **Details:** Candidate used DFS + DP but feedback was negative; shortest path expectation is BFS.
  * **Source:** [Is DFS not optimal solution?](https://leetcode.com/discuss/post/8098497/is-dfs-not-optimal-solution-by-logic_pio-tamv/)

* **Question:** Cheapest Flights Within K Stops.

  * **Details:** Graph shortest path with bounded stops.
  * **Source:** [Uber | Software Engineer II Bangalore](https://leetcode.com/discuss/post/7300207/uber-software-engineer-ii-se-2-bangalore-wyl8/), [Uber | L4 | Bangalore | Offer](https://leetcode.com/discuss/post/6770968/uber-l4-bangalore-offer-by-10399vk-c73t/)

* **Question:** Reconstruct Itinerary.

  * **Details:** Asked in 1-hour elimination round.
  * **Source:** [Uber | Software Engineer 2 Interview Experience](https://leetcode.com/discuss/post/6943563/uber-software-engineer-2-interview-exper-gpty/)

* **Question:** Currency conversion between `from` and `to`.

  * **Details:** Given currencies and conversion rates, compute conversion rate. Follow-up: if multiple paths exist, return highest-value path. Standard Dijkstra does not directly work due to multiplication; Bellman-Ford / log transform reasoning needed.
  * **Source:** [Uber SDE2 Chances](https://leetcode.com/discuss/post/6947609/uber-sde2-chances-by-anonymous_user-h4vb/)

* **Question:** Hard graph currency exchange problem.

  * **Details:** LC hard style graph/currency exchange; candidate passed all test cases near end.
  * **Source:** [Uber SDE-2](https://leetcode.com/discuss/post/5745602/uber-sde-2-by-anonymous_user-5vqx/)

* **Question:** Minimum edge reversal in graph.

  * **Details:** Asked in OA. Similar to Minimum Edge Reversals So Every Node Is Reachable.
  * **Source:** [Uber SDE II 6 Rounds](https://leetcode.com/discuss/post/8018831/uber-interview-experience-sde-ii-6-round-gnl6/), [Uber interview experience OA and phone screen](https://leetcode.com/discuss/post/7578312/uber-interview-experience-oa-and-phone-s-z9hr/)

* **Question:** Given an undirected tree, find minimum time to burn entire tree starting from any node.

  * **Details:** Fire spreads to adjacent nodes in 1 time unit. Need choose optimal starting node → tree radius/center.
  * **Source:** [Uber interview experience OA and phone screen](https://leetcode.com/discuss/post/7578312/uber-interview-experience-oa-and-phone-s-z9hr/)

* **Question:** Find path between two nodes in an undirected tree.

  * **Details:** Build adjacency list. Use BFS/DFS. Example path from `1` to `6` is `[1,2,4,6]`.
  * **Source:** [Uber OA - US Mid / Entry Level](https://leetcode.com/discuss/post/7564386/uber-oa-us-mid-entry-level-by-anonymous_-32b8/)

* **Question:** Given grid with arrows, find minimum cost from top-left to bottom-right.

  * **Details:** Following arrow direction costs `0`; any other direction costs `1`. Model as 0-1 weighted graph. Candidate used Dijkstra; 0-1 BFS also relevant.
  * **Source:** [Uber L5A SSE interview experience](https://leetcode.com/discuss/post/7043175/uber-l5a-sse-interview-experience-by-nik-qpdr/)

* **Question:** 0-1 BFS problem with Dijkstra follow-up.

  * **Details:** Working code expected with test cases.
  * **Source:** [Uber L5A / Coupang L5 SSE India](https://leetcode.com/discuss/post/6840488/uber-l5a-coupang-l5-senior-software-engi-5uyv/)

* **Question:** Bus Routes-like BFS problem.

  * **Details:** Exact statement not remembered, but similar to Bus Routes.
  * **Source:** [Uber L4 Interview Experience Ongoing](https://leetcode.com/discuss/post/7196883/uber-l4-interview-experience-ongoing-by-xj5u8/)

* **Question:** Find smallest distance between nodes in graph, not Dijkstra.

  * **Details:** Graph-based shortest distance problem; follow-up coded within 45 minutes.
  * **Source:** [Uber SDE-II Interview Experience March 25](https://leetcode.com/discuss/post/6827210/uber-sde-ii-interview-experience-march-2-oj2b/)

---

### Union Find / DSU

* **Question:** Earliest timestamp when all users become connected.

  * **Details:** Logs like `<timestamp> UserA shared ride with UserB`; logs sorted by timestamp. Return earliest timestamp when all users connected.
  * **Source:** [Uber SDE I Interview Experience Feb 2026](https://leetcode.com/discuss/post/7647484/uber-sde-i-interview-experience-feb-2026-snr8/), [Uber BPS interview](https://leetcode.com/discuss/post/7479216/uber-bps-interview-any-chance-of-clearin-g1rt/)

* **Question:** Earliest timestamp with connection removals.

  * **Details:** Follow-up adds `<timestamp> UserA cancel ride with UserB`, which removes edge.
  * **Source:** [Uber SDE I Interview Experience Feb 2026](https://leetcode.com/discuss/post/7647484/uber-sde-i-interview-experience-feb-2026-snr8/)

* **Question:** Given cab-sharing logs, return how many cabs are currently running.

  * **Details:** Logs format `UserA-Shared-UserB-timestamp`. Need count current connected components/cabs.
  * **Source:** [Uber SDE-2 Interview Experience L4 Bangalore](https://leetcode.com/discuss/post/7182332/uber-sde-2-interview-experience-l4-banga-fd1d/)

* **Question:** Given cab-sharing logs, return timestamp when all users get into one cab.

  * **Details:** DSU with path compression, union by rank/size expected. Interviewer asked DSU from scratch.
  * **Source:** [Uber SDE-2 Interview Experience L4 Bangalore](https://leetcode.com/discuss/post/7182332/uber-sde-2-interview-experience-l4-banga-fd1d/)

* **Question:** Number of Islands II.

  * **Details:** Dynamic island count using DSU.
  * **Source:** [Uber SDE-2 Interview Experience India](https://leetcode.com/discuss/post/6878990/uber-sde-2-interview-experience-india-by-h7wd/)

* **Question:** Making A Large Island.

  * **Details:** DSU/grid component sizing.
  * **Source:** [Uber L4 Interview experience](https://leetcode.com/discuss/post/7584609/uber-l4-interview-experience-by-anonymou-iufp/)

* **Question:** Uber City Network Construction.

  * **Details:** Given hubs `(x_i,y_i)`, connect all hubs with min total cost where edge cost is `min(|x_i-x_j|, |y_i-y_j|)`. `n <= 10^5`. Expected: sort by x/y, create adjacent edges only, Kruskal + DSU.
  * **Source:** [Uber SWE I Online Assessment Jan 2026](https://leetcode.com/discuss/post/7650054/uber-swe-i-online-assessment-jan-2026-by-f05e/)

* **Question:** Uber Zone Clusters.

  * **Details:** Zones connected if `gcd(S_i,S_j)>1`; return cluster size per zone. Use SPF + DSU over prime factors.
  * **Source:** [Uber SWE I Online Assessment Jan 2026](https://leetcode.com/discuss/post/7650054/uber-swe-i-online-assessment-jan-2026-by-f05e/)

---

### Trees / BST / N-ary Tree

* **Question:** Boundary traversal of binary tree clockwise.

  * **Details:** Return borders from right, bottom, left. Example output `[1,3,6,5,4,2]`.
  * **Source:** [Uber coding screening-round 1](https://leetcode.com/discuss/post/7572080/uber-coding-screening-round-1-by-mehdimk-m3m6/)

* **Question:** Given N-ary tree, print nodes seen while walking from bottom-left to bottom-right in an arc via root.

  * **Details:** Need level-wise leftmost/rightmost around root. Example output `12,5,4,7,8`; edge case adds `11` on both sides. BFS level-wise approach.
  * **Source:** [Uber SDE 2 DSA Round India](https://leetcode.com/discuss/post/6314790/uber-sde-2-dsa-round-india-by-anonymous_-2we5/)

* **Question:** Validate Binary Search Tree.

  * **Details:** Asked in Uber SDE-2 screening.
  * **Source:** [Uber SDE-2](https://leetcode.com/discuss/post/5745602/uber-sde-2-by-anonymous_user-5vqx/)

* **Question:** Kth Smallest Element in BST / LC 230-like problem.

  * **Details:** Asked in LLD slot but was another DS round with medium follow-up.
  * **Source:** [Uber L4 Interview Experience US](https://leetcode.com/discuss/post/7217970/uber-sde2-interview-experience-us-offer-hfj64/)

* **Question:** Longest Path With Different Adjacent Characters.

  * **Details:** Tree DFS/DP.
  * **Source:** [Uber SDE-2 Interview Experience India](https://leetcode.com/discuss/post/6878990/uber-sde-2-interview-experience-india-by-h7wd/)

* **Question:** Optimal Binary Search Tree variation.

  * **Details:** DP on trees / optimal BST. Memoized solution accepted in one post; optimized approach asked in another.
  * **Source:** [Interview Experience SDE2 Uber Nov 2025](https://leetcode.com/discuss/post/7672800/interview-experience-sde2-uber-nov-2025-v0b3r/), [Uber Software Engineer 2 Interview Experience](https://leetcode.com/discuss/post/6943563/uber-software-engineer-2-interview-exper-gpty/)

---

### Dynamic Programming / Game Theory

* **Question:** Dungeon Game-like problem.

  * **Details:** Focus on DP optimization, edge cases, clean code.
  * **Source:** [Uber SDE II 6 Rounds](https://leetcode.com/discuss/post/8018831/uber-interview-experience-sde-ii-6-round-gnl6/)

* **Question:** Optimal Strategy for a Game variation.

  * **Details:** Two players pick only corner values; maximize own value assuming opponent plays optimally. Memoization expected.
  * **Source:** [Interview Experience SDE2 Uber Nov 2025](https://leetcode.com/discuss/post/7672800/interview-experience-sde2-uber-nov-2025-v0b3r/)

* **Question:** Modified Rock–Paper–Scissors circular table.

  * **Details:** String of `R/P/S`, circular adjacency. Minimum changes so no adjacent engineers choose same option. `3 <= n <= 2*10^5`. Examples: `PRSSP -> 2`, `RRRRRRR -> 4`, `RSPRPSPRS -> 0`.
  * **Source:** [Uber SDE-1 Game Theory + Multi-dimensional DP](https://leetcode.com/discuss/post/7684685/uber-sde-1-interview-exp-by-anonymous_us-zcbu/)

* **Question:** IPL Auction Game.

  * **Details:** Even number of players in row, each has score. Two selectors alternate; each picks first or last. Opponent optimal. Return max score first player can get.
  * **Source:** [SDE-2 Frontend Interview Uber](https://leetcode.com/discuss/post/6970499/sde-2-frontend-interview-uber-by-anonymo-kfq0/)

* **Question:** 2 Keys Keyboard.

  * **Details:** Asked in technical phone screen.
  * **Source:** [Uber SDE-2 Reject](https://leetcode.com/discuss/post/7033960/uber-sde-2-oa-by-debmalyapan53-u6sm/)

---

### Matrix / Grid / Simulation

* **Question:** Robot blocker-distance grid.

  * **Details:** Grid has `O` robot, `E` empty, `X` blocker. Query `[left, top, bottom, right]` gives exact distance to blocker/boundary. Return robot coordinates satisfying all exact distances.
  * **Source:** [Uber SSE Screening Round Reject](https://leetcode.com/discuss/post/7332236/uber-sse-screening-round-reject-by-anony-9r42/)

* **Question:** Bubble Explosion Simulation.

  * **Details:** 2D grid with colors or `0`. Bubble explodes if at least two adjacent same-colored bubbles. Explosions propagate to adjacent same color. Apply gravity once. Return resulting grid.
  * **Source:** [Uber SDE-2 Reject](https://leetcode.com/discuss/post/7033960/uber-sde-2-oa-by-debmalyapan53-u6sm/)

* **Question:** Minimum swaps to create `y x y` matrix of `1`s inside an `n x n` binary matrix.

  * **Details:** Swaps need not be adjacent.
  * **Source:** [Uber L4 Interview Process](https://leetcode.com/discuss/post/5833928/uber-l4-interview-process-by-anonymous_u-gysb/)

* **Question:** UBERBOT wall-climbing robot valid routes.

  * **Details:** Grid `n x m`, cells `X` usable and `#` empty. Start bottom row, end top row. Move upward/horizontally only, at least one node per level, up to two nodes per level, Euclidean jump distance `<= w`, count routes modulo `998244353`. `n,m <= 2000`.
  * **Source:** [Uber OA 15th June 2025](https://leetcode.com/discuss/post/6846683/uber-oa-15th-june-2025-by-anonymous_user-voqx/)

---

### Greedy / Heap / Stack / Intervals

* **Question:** Biological Hazards — count valid contiguous intervals.

  * **Details:** `n` chemicals `1..n`, arrays `poisonous[]`, `allergic[]`. Pair cannot coexist in same interval. Count intervals `[L,R]` with no forbidden pair. `n,m <= 2*10^5`. Expected sliding window / two pointers.
  * **Source:** [Uber SDE II 6 Rounds](https://leetcode.com/discuss/post/8018831/uber-interview-experience-sde-ii-6-round-gnl6/)

* **Question:** Uber Shuttle / Exam Room style seating.

  * **Details:** `n` seats `0..n-1`. `board()` chooses seat maximizing distance from nearest rider; tie lowest index. Empty shuttle first seat `0`. `dropOff(p)` frees seat.
  * **Source:** [Uber SDE2 Screening Round BPS](https://leetcode.com/discuss/post/7606367/uber-sde2-screening-round-bps-by-vivek_s-0935/)

* **Question:** Chef Maria / Bomb Defusal scheduling.

  * **Details:** Each task has duration `X_i`, deadline `Y_i`. One at a time. Return min total time if all meet deadlines else `-1`. `N <= 10^5` or `2*10^5` depending post.
  * **Source:** [Uber Online Assessment SDE1 June 2025](https://leetcode.com/discuss/post/6851435/uber-online-assessment-sde-1-june-2025-b-m1eh/), [Uber OA 15th June 2025](https://leetcode.com/discuss/post/6846683/uber-oa-15th-june-2025-by-anonymous_user-voqx/)

* **Question:** Ride batching / Uber Pool grouping.

  * **Details:** Rides `[ride_id, pickup_location, pickup_time]`. Same location and max time span <= 10 mins. Max 3 per batch. Unique assignment. Output sorted IDs per batch; batches ordered by earliest original index. `N <= 10000`.
  * **Source:** [Uber Online Assessment SDE1 June 2025](https://leetcode.com/discuss/post/6851435/uber-online-assessment-sde-1-june-2025-b-m1eh/)

* **Question:** Uber Circular Shuttle Optimization.

  * **Details:** Tree with passenger nodes. Shuttle starts anywhere, collects passengers within radius 2 for free, moves along edge cost 1, must return to start. Min cost. `n <= 3*10^4`.
  * **Source:** [Uber Online Assessment SDE1 June 2025](https://leetcode.com/discuss/post/6851435/uber-online-assessment-sde-1-june-2025-b-m1eh/)

* **Question:** Video Stitching.

  * **Source:** [Gave 100+ interviews](https://leetcode.com/discuss/post/7347409/gave-100-interviews-top-10-questions-tha-o8rr/)

* **Question:** Maximum Number of Events That Can Be Attended.

  * **Source:** [Gave 100+ interviews](https://leetcode.com/discuss/post/7347409/gave-100-interviews-top-10-questions-tha-o8rr/)

* **Question:** Furthest Building You Can Reach.

  * **Source:** [Gave 100+ interviews](https://leetcode.com/discuss/post/7347409/gave-100-interviews-top-10-questions-tha-o8rr/), [Chances of Clearing Uber OA](https://leetcode.com/discuss/post/7368960/chances-of-clearing-the-uber-oa-by-anony-tv5h/)

* **Question:** Maximum Profit in Job Scheduling.

  * **Source:** [Gave 100+ interviews](https://leetcode.com/discuss/post/7347409/gave-100-interviews-top-10-questions-tha-o8rr/)

* **Question:** Maximum Width Ramp.

  * **Source:** [Gave 100+ interviews](https://leetcode.com/discuss/post/7347409/gave-100-interviews-top-10-questions-tha-o8rr/)

* **Question:** Minimum Insertions to Balance Parentheses String.

  * **Source:** [Gave 100+ interviews](https://leetcode.com/discuss/post/7347409/gave-100-interviews-top-10-questions-tha-o8rr/)

* **Question:** Longest Happy String.

  * **Source:** [Gave 100+ interviews](https://leetcode.com/discuss/post/7347409/gave-100-interviews-top-10-questions-tha-o8rr/)

* **Question:** Remove Invalid Parentheses.

  * **Source:** [Gave 100+ interviews](https://leetcode.com/discuss/post/7347409/gave-100-interviews-top-10-questions-tha-o8rr/)

* **Question:** Find Building Where Alice and Bob Can Meet.

  * **Source:** [Gave 100+ interviews](https://leetcode.com/discuss/post/7347409/gave-100-interviews-top-10-questions-tha-o8rr/)

---

### Number Theory / Math

* **Question:** Water and Jug Problem.

  * **Source:** [Uber SSE Technical Phone Round](https://leetcode.com/discuss/post/7313588/uber-sse-technical-phone-round-by-get_si-w5km/)

* **Question:** Minimum Jumps to Reach End via Prime Teleportation.

  * **Source:** [Chances of Clearing Uber OA](https://leetcode.com/discuss/post/7368960/chances-of-clearing-the-uber-oa-by-anony-tv5h/)

* **Question:** Count primes in range `[L,R]` using Segmented Sieve.

  * **Details:** `1 <= L <= R <= 10^12`, `R-L <= 10^6`. Use primes up to `sqrt(R)`, segment array, handle `L=1`.
  * **Source:** [Mastering Segmented Sieve and SPF](https://leetcode.com/discuss/post/7332274/mastering-segmented-sieve-and-spf-smalle-fp7n/)

* **Question:** Prime factorization queries using Smallest Prime Factor sieve.

  * **Details:** `N <= 10^6`, `Q <= 10^5`; build SPF in `O(n log log n)`, query in `O(log n)`.
  * **Source:** [Mastering Segmented Sieve and SPF](https://leetcode.com/discuss/post/7332274/mastering-segmented-sieve-and-spf-smalle-fp7n/)

* **Question:** Minimum time for cars on x-axis to meet.

  * **Details:** Each car has position and max speed, can move left/right. Find minimum time for all to meet at common point. Example positions `[1,3,7]`, speeds `[2,1,1]`, answer `2`.
  * **Source:** [Uber interview Experience](https://leetcode.com/discuss/post/6801527/uber-interview-experience-by-kamal14-hwfp/)

---

### Linked List / Data Structure Design

* **Question:** Reverse every `k` nodes in linked list; if remaining nodes `< k`, leave unchanged.

  * **Details:** Example `A->B->C->D->E`, `k=2`, output `B->A->D->C->E`.
  * **Source:** [Uber L4 Interview Experience Ongoing](https://leetcode.com/discuss/post/7196883/uber-l4-interview-experience-ongoing-by-xj5u8/)

* **Question:** Detect first node of cycle in linked list.

  * **Source:** [Jivox / Davinci Commerce](https://leetcode.com/discuss/post/8067747/jivox-davinci-commerce-senior-software-e-tlk4/)

* **Question:** First Unique Number.

  * **Details:** Expected `O(1)` using DLL + map.
  * **Source:** [Uber SDE-2 Interview Experience](https://leetcode.com/discuss/post/7302014/uber-sde-2-interview-experience-by-anony-6fmj/), [Uber SDE2 Chances](https://leetcode.com/discuss/post/6947609/uber-sde2-chances-by-anonymous_user-h4vb/)

* **Question:** LRU Cache.

  * **Source:** [BitGo Developer interview experience](https://leetcode.com/discuss/post/8102938/bitgo-developer-interview-experience-by-vhzs5/)

---

## LLD / Machine Coding Questions

### File System

* **Question:** Design in-memory File System with `mkdir`, `pwd`, `cd`.

  * **Details:** `mkdir(dirname)` creates directory in current dir. `pwd()` returns absolute current path. `cd(path)` supports absolute `/...` and relative paths. Wildcard `*` can match `.`, `..`, or any child directory. Linux-like behavior expected.
  * **Source:** [Uber SDE I Interview Experience Feb 2026](https://leetcode.com/discuss/post/7647484/uber-sde-i-interview-experience-feb-2026-snr8/), [Uber Software Engineer II Bangalore](https://leetcode.com/discuss/post/7300207/uber-software-engineer-ii-se-2-bangalore-wyl8/)

* **Question:** Implement Linux File System commands.

  * **Details:** `mkdir`, `cd`, `ls`, `pwd`. Expected clean code, N-ary tree/folder hierarchy, error handling, debugging edge cases.
  * **Source:** [Interview Experience SDE2 Uber Nov 2025](https://leetcode.com/discuss/post/7672800/interview-experience-sde2-uber-nov-2025-v0b3r/)

* **Question:** Design File System with wildcard search support.

  * **Details:** Clean class design, extensibility, efficient wildcard `*` search.
  * **Source:** [Uber SDE II 6 Rounds](https://leetcode.com/discuss/post/8018831/uber-interview-experience-sde-ii-6-round-gnl6/)

---

### Parking / Reservation / Scheduling

* **Question:** Design Parking Lot system.

  * **Details:** Multiple levels, each level has rows/spots. Vehicles: motorcycle and car. Motorcycle can park anywhere; car only car spot. Implement `park`, `unpark`, `search`.
  * **Source:** [Uber L4 Bangalore Offer](https://leetcode.com/discuss/post/6770968/uber-l4-bangalore-offer-by-10399vk-c73t/)

* **Question:** Design Parking System.

  * **Details:** Candidate explained and coded complete thing; exact specs not provided.
  * **Source:** [Uber SDE-2](https://leetcode.com/discuss/post/5745602/uber-sde-2-by-anonymous_user-5vqx/)

* **Question:** Meeting Scheduler / Conference Room API.

  * **Details:** Predefined rooms. `scheduleMeeting(startTime,endTime)` returns available room and reserves it, else throws error. Unix timestamps. Expected complexity `log(meetings) + O(rooms)`.
  * **Source:** [Uber SDE 3 Phone Screen](https://leetcode.com/discuss/post/7332704/uber-sde-3-phone-screen-by-anonymous_use-ghxr/)

* **Question:** Meeting Scheduler with `n` rooms.

  * **Details:** Booking requests with start/end. Allocate any available room. Focus: classes, interactions, design patterns.
  * **Source:** [Uber SDE-2 Interview Experience India](https://leetcode.com/discuss/post/6878990/uber-sde-2-interview-experience-india-by-h7wd/)

* **Question:** Train-Platform Management System.

  * **Details:** Assign trains to platforms; query train at platform at time; query platform for train at time. Runnable Java code expected. Classes: `Train`, `Platform`, `Scheduler`, `ScheduleManager`. Min-heap strategy and extensibility discussed.
  * **Source:** [Uber SDE-2 Interview Experience L4 Bangalore](https://leetcode.com/discuss/post/7182332/uber-sde-2-interview-experience-l4-banga-fd1d/)

* **Question:** Job Scheduler-like machine coding.

  * **Details:** Interviewer expected LLD + HLD. Asked push vs pull flows. Candidate wrote structures and explained patterns.
  * **Source:** [Uber L4 Interview Experience Ongoing](https://leetcode.com/discuss/post/7196883/uber-l4-interview-experience-ongoing-by-xj5u8/)

---

### Cache / Counter / Queue

* **Question:** Design Cache with `O(1)` operations.

  * **Details:** Operations unspecified, all required `O(1)`.
  * **Source:** [Uber L4 Interview Process](https://leetcode.com/discuss/post/5833928/uber-l4-interview-process-by-anonymous_u-gysb/)

* **Question:** Low-level design of Cache.

  * **Details:** OOD + SOLID + Strategy pattern for eviction. LRU eviction coded and run.
  * **Source:** [Microsoft Interview Experience SDE1](https://leetcode.com/discuss/post/7361123/microsoft-interview-experience-sde-1-l60-ev95/)

* **Question:** Expiry Counter.

  * **Details:** Initialize counter with expiry `t` seconds. Methods: `put_element(k)`, `get_element_count(k)`, `get_total_elements()`. Discussed PQ+hashmap approach and timestamp-list + binary search. Production concern: discard stale timestamps.
  * **Source:** [Uber L5A SSE interview experience](https://leetcode.com/discuss/post/7043175/uber-l5a-sse-interview-experience-by-nik-qpdr/)

* **Question:** Task queue with controlled concurrency in TypeScript.

  * **Details:** Execute up to `K` tasks in parallel; queue extra tasks. Unique task ID, success/error callbacks, custom executor for logging/retries/rate limiting.
  * **Source:** [Uber Frontend L4](https://leetcode.com/discuss/post/7279221/uber-interview-experience-l4-frontend-ub-tcxu/)

* **Question:** Async queue with concurrency limit.

  * **Details:** Queue signature includes `ProcessorFn`, `OnCompleteFn`, `concurrency`. Supports `push`, `unshift`, `drain`, `error`. Tasks can be added anytime.
  * **Source:** [SDE-2 Frontend Interview Uber](https://leetcode.com/discuss/post/6970499/sde-2-frontend-interview-uber-by-anonymo-kfq0/)

---

### Inventory / Pricing / Subscription / Trading

* **Question:** Uber Eats Pricing Engine.

  * **Details:** Calculate pricing considering item prices, upgrades, and taxes. Full working solution expected; many follow-ups possible.
  * **Source:** [Uber SDE-2 Interview Experience Offer](https://leetcode.com/discuss/post/7682158/uber-sde-2-interview-experience-offer-re-3u58/)

* **Question:** Car Inventory Management + Reservation Management.

  * **Details:** Code classes and services for inventory and reservation.
  * **Source:** [Uber SDE-2 Backend Interview Experience](https://leetcode.com/discuss/post/7302014/uber-sde-2-interview-experience-by-anony-6fmj/)

* **Question:** Subscription Management System.

  * **Details:** Follow-ups: concurrency while renewing, upgrade/downgrade, isolation levels. Extra features: auto collection, price variants.
  * **Source:** [Jivox / Davinci Commerce](https://leetcode.com/discuss/post/8067747/jivox-davinci-commerce-senior-software-e-tlk4/)

* **Question:** Stock Trading Platform.

  * **Details:** Working code expected.
  * **Source:** [Uber SSE Bangalore L5A](https://leetcode.com/discuss/post/6628212/uber-sse-bangalore-l5-a-by-anonymous_use-0h6x/)

* **Question:** Design ATM functionalities.

  * **Details:** Interfaces/classes provided; driver class not completed due to time.
  * **Source:** [Uber L5A / Coupang L5 SSE India](https://leetcode.com/discuss/post/6840488/uber-l5a-coupang-l5-senior-software-engi-5uyv/)

---

### Games / UI / Frontend Machine Coding

* **Question:** Build Connect Four game.

  * **Details:** Expected board, players, move validation, disc drop, win detection, draw/end-state.
  * **Source:** [Uber SDE2 Fullstack Interview Experience](https://leetcode.com/discuss/post/7506720/uber-sde-2-fullstack-interview-experienc-o7ff/)

* **Question:** Voting Poll UI component.

  * **Details:** Four options as buttons. Click increments count and updates percentage distribution. Each option has colored progress bar, smooth real-time updates. Optional total votes and labels.
  * **Source:** [Uber Frontend L4](https://leetcode.com/discuss/post/7279221/uber-interview-experience-l4-frontend-ub-tcxu/)

* **Question:** Modal component.

  * **Details:** Primary/secondary buttons, optional close icon, priority level, close lower-priority/open modals when higher-priority modal opens.
  * **Source:** [Uber Frontend L4](https://leetcode.com/discuss/post/7279221/uber-interview-experience-l4-frontend-ub-tcxu/)

* **Question:** Memory game.

  * **Details:** Initially discussed React, interviewer asked vanilla JS with optimization.
  * **Source:** [Uber SDE-2 Frontend](https://leetcode.com/discuss/post/6820731/uber-sde-2-frontend-by-anonymous_user-rvuq/)

* **Question:** Notes app edit/delete.

  * **Details:** Prewritten Android app opened in Android Studio; implement edit/delete and review existing code.
  * **Source:** [Uber L4 SDE2 Android Interview experience](https://leetcode.com/discuss/post/7370037/uber-l4-sde2-android-interview-experienc-2kha/)

* **Question:** C-shaped squares UI.

  * **Details:** Render 3 C-shape squares, 25px, black border, white background, 10px gap. Click turns green. Once all 3 green, unwind in reverse click order, one per second.
  * **Source:** [SDE-2 Frontend Interview Uber](https://leetcode.com/discuss/post/6970499/sde-2-frontend-interview-uber-by-anonymo-kfq0/)

* **Question:** Jira-like drag-and-drop in React.

  * **Details:** MDN docs allowed.
  * **Source:** [Uber L5A Frontend Bangalore](https://leetcode.com/discuss/post/7228490/tc-l5a-uber-bangalore-by-anonymous_user-gemb/)

* **Question:** Implement custom Promise.

  * **Source:** [Uber Frontend](https://leetcode.com/discuss/post/7255282/uber-frontend-by-anonymous_user-aui4/)

* **Question:** JS memoization utility for callback-based async function.

  * **Details:** Cache async callback responses.
  * **Source:** [Uber L5A Frontend Bangalore](https://leetcode.com/discuss/post/7228490/tc-l5a-uber-bangalore-by-anonymous_user-gemb/)

* **Question:** Method chaining + promises queue.

  * **Details:** Example:
    `new UberDriver().pick("TestUser").pick("Rahul").drive(2).drop("Rahul").drive(4).drop("TestUser").rest(10)`
    Output should happen with delays. Follow-up: user priority behavior.
  * **Source:** [Uber SDE-2 Frontend](https://leetcode.com/discuss/post/6820731/uber-sde-2-frontend-by-anonymous_user-rvuq/)

---

### Pub-Sub / Concurrency / Backend LLD

* **Question:** Pub-Sub system using Java threads.

  * **Details:** Simulate multiple publishers and subscribers with Java threads. Concurrency knowledge expected.
  * **Source:** [Uber SDE-II Interview Experience March 25](https://leetcode.com/discuss/post/6827210/uber-sde-ii-interview-experience-march-2-oj2b/)

* **Question:** Referral-system question using HashMap/unordered_set.

  * **Details:** Optimized working code expected.
  * **Source:** [Uber SSE Bangalore L5A](https://leetcode.com/discuss/post/6628212/uber-sse-bangalore-l5-a-by-anonymous_use-0h6x/)

* **Question:** Employee-manager hierarchy APIs.

  * **Details:** Given edges `[manager, employee]`. Implement:

    1. Total direct+indirect employees under manager in `O(1)`.
    2. Change manager of employee, including reportees.
    3. Add employee edge.
       Candidate had to define input format / main function.
  * **Source:** [Uber SSE Technical Phone Round](https://leetcode.com/discuss/post/7313588/uber-sse-technical-phone-round-by-get_si-w5km/)

---

## HLD / System Design Questions

### Uber / Maps / Location / Geo

* **Question:** Driver Heatmap.

  * **Details:** Show Uber driver heatmap in internal dashboard. Near real-time last 20 mins + after 24 hrs bucketized hourly. Input TPS around `500,000`. Kafka location events → geohash → Kafka partition by geohash → Flink tumbling 1-min counts → Redis. Hourly aggregates to Postgres. Discussed Redis mappings, DB choice, scalability, fault tolerance.
  * **Source:** [Uber L5A SSE interview experience](https://leetcode.com/discuss/post/7043175/uber-l5a-sse-interview-experience-by-nik-qpdr/), [Uber L4 Interview experience](https://leetcode.com/discuss/post/7584609/uber-l4-interview-experience-by-anonymou-iufp/), [Uber SDE-2 Offer](https://leetcode.com/discuss/post/7682158/uber-sde-2-interview-experience-offer-re-3u58/)

* **Question:** Restaurant Recommendation System based on location.

  * **Details:** Discussed latency reduction, regional optimizations, caching, sharding, geohashing, quad trees, DB indexing, trade-offs.
  * **Source:** [Uber L4 Interview Experience US](https://leetcode.com/discuss/post/7217970/uber-sde2-interview-experience-us-offer-hfj64/)

* **Question:** Proximity Service.

  * **Details:** Hard design/bar raiser prompt.
  * **Source:** [Uber L5A / Coupang L5 SSE India](https://leetcode.com/discuss/post/6840488/uber-l5a-coupang-l5-senior-software-engi-5uyv/)

---

### E-commerce / Uber Eats / Trending

* **Question:** Uber Eats Homepage.

  * **Details:** Location-based search with geohashing/geo-indexing; homepage/search APIs; schema for restaurants, dishes, orders; SQL vs NoSQL justification; caching; metrics: most ordered restaurant, most ordered dish, orders per restaurant.
  * **Source:** [Uber SDE-2 Interview Experience L4 Bangalore](https://leetcode.com/discuss/post/7182332/uber-sde-2-interview-experience-l4-banga-fd1d/)

* **Question:** Uber Eats homepage for train passengers.

  * **Details:** Input `PNR`, output food ordering based on station where delivery should happen. Candidate covered FR, NFR, capacity/scale, entities, HLD, DB/cache, deep dives.
  * **Source:** [Uber L4 Interview Experience Ongoing](https://leetcode.com/discuss/post/7196883/uber-l4-interview-experience-ongoing-by-xj5u8/)

* **Question:** E-commerce browsing system that highlights top merchandise.

  * **Details:** Scope excludes purchase. Process events: product viewed, added to cart, wishlisted, purchased. Questions: where store data? prioritize consistency or availability? Suggested Kafka, MongoDB product catalog, Redis leaderboard/cache top-K. Follow-up: example data stored in MongoDB.
  * **Source:** [Uber Software Engineer II Bangalore](https://leetcode.com/discuss/post/7300207/uber-software-engineer-ii-se-2-bangalore-wyl8/)

* **Question:** Backend system to fetch trending items.

  * **Details:** Event ingestion, ranking/aggregation, storage, APIs, caching, freshness implied.
  * **Source:** [Uber SDE2 Fullstack Interview Experience](https://leetcode.com/discuss/post/7506720/uber-sde-2-fullstack-interview-experienc-o7ff/)

* **Question:** E-commerce platform.

  * **Details:** Service design, database choices, scalability, consistency.
  * **Source:** [Uber SDE-II Interview Experience March 25](https://leetcode.com/discuss/post/6827210/uber-sde-ii-interview-experience-march-2-oj2b/)

* **Question:** E-commerce system.

  * **Source:** [Uber L5A / Coupang L5 SSE India](https://leetcode.com/discuss/post/6840488/uber-l5a-coupang-l5-senior-software-engi-5uyv/)

---

### Stock / Finance

* **Question:** Stock price alerting system.

  * **Details:** Notify users when stock price changes by configured delta.
  * **Source:** [Uber SDE-2 Interview Experience Backend](https://leetcode.com/discuss/post/7302014/uber-sde-2-interview-experience-by-anony-6fmj/), [Uber SDE2 Chances](https://leetcode.com/discuss/post/6947609/uber-sde2-chances-by-anonymous_user-h4vb/)

* **Question:** Push notification when stock jumps/drops >5% from closing price.

  * **Details:** Users subscribe to stock; trigger push when threshold crossed.
  * **Source:** [Uber L4 Bangalore Offer](https://leetcode.com/discuss/post/6770968/uber-l4-bangalore-offer-by-10399vk-c73t/)

* **Question:** Stock Broker System.

  * **Source:** [Uber L4 Interview Process](https://leetcode.com/discuss/post/5833928/uber-l4-interview-process-by-anonymous_u-gysb/), [Uber L4 Chances](https://leetcode.com/discuss/post/6368345/uber-l4-chances-by-anonymous_user-4le1/)

* **Question:** Stock Trading Platform.

  * **Details:** Asked as LLD in one post but concept is stock/trading system.
  * **Source:** [Uber SSE Bangalore L5A](https://leetcode.com/discuss/post/6628212/uber-sse-bangalore-l5-a-by-anonymous_use-0h6x/)

---

### Messaging / Chat / Notification

* **Question:** Chat application like Facebook Messenger.

  * **Details:** Architecture, server-side storage, APIs, components, data models. Core P0: 1:1 conversation, group conversation. Also offline users, delivery/read receipts, message history, group management add/remove user.
  * **Source:** [Uber System Design Round](https://leetcode.com/discuss/post/7291521/uber-system-design-round-by-anonymous_us-2yi2/)

* **Question:** Messaging app.

  * **Details:** Candidate mistakenly thought LLD; interviewer hinted real-world project and DB schema. Follow-ups on schema.
  * **Source:** [Uber Off-Campus 2025 for SDE1](https://leetcode.com/discuss/post/6892974/uber-off-campus-2025-for-sde-1-by-anonym-br54/)

* **Question:** Notification System.

  * **Details:** Basic flow, functional requirements, HLD diagram requested; follow-ups unspecified.
  * **Source:** [Jivox / Davinci Commerce](https://leetcode.com/discuss/post/8067747/jivox-davinci-commerce-senior-software-e-tlk4/)

---

### Monitoring / Analytics / Counting

* **Question:** Real-time Monitoring System.

  * **Details:** Discussed push vs pull, service mesh/Istio, time-series DB, caching, 5/10 min aggregation windows, dashboard design.
  * **Source:** [Uber SDE II 6 Rounds](https://leetcode.com/discuss/post/8018831/uber-interview-experience-sde-ii-6-round-gnl6/)

* **Question:** YouTube view count system.

  * **Details:** Design YouTube-style view-count system.
  * **Source:** [Uber SSE Bangalore L5A](https://leetcode.com/discuss/post/6628212/uber-sse-bangalore-l5-a-by-anonymous_use-0h6x/)

---

### Calendar / Frontend System Design / SDK

* **Question:** Google Calendar / calendar app.

  * **Details:** Auth signup/login/logout; create/edit/delete/accept event invites; event has title, time, date, participants, description; month/week/day views and seamless switching. In one frontend round, grilled on GraphQL paginated data for week/month views and caching with GraphQL.
  * **Source:** [Uber Frontend L4](https://leetcode.com/discuss/post/7279221/uber-interview-experience-l4-frontend-ub-tcxu/), [Uber SDE-2 Frontend](https://leetcode.com/discuss/post/6820731/uber-sde-2-frontend-by-anonymous_user-rvuq/), [SDE-2 Frontend Interview Uber](https://leetcode.com/discuss/post/6970499/sde-2-frontend-interview-uber-by-anonymo-kfq0/)

* **Question:** Android SDK with search bar and places list.

  * **Details:** SDK has search bar; while typing, display list of places.
  * **Source:** [Uber L4 SDE2 Android Interview experience](https://leetcode.com/discuss/post/7370037/uber-l4-sde2-android-interview-experienc-2kha/)

* **Question:** Config-driven UI widget builder.

  * **Details:** Design builder and explain client integration.
  * **Source:** [Uber L5A Frontend Bangalore](https://leetcode.com/discuss/post/7228490/tc-l5a-uber-bangalore-by-anonymous_user-gemb/)

---

### Generic Platform / Infra / Backend

* **Question:** API Gateway.

  * **Source:** [Oracle Health IC3 SMTS interview](https://leetcode.com/discuss/post/8103132/oracle-health-ic3-smts-interview-experie-jfrm/)

* **Question:** URL Shortener.

  * **Details:** Scale estimation mattered; unrealistic numbers were called out.
  * **Source:** [Oracle Health IC3 SMTS interview](https://leetcode.com/discuss/post/8103132/oracle-health-ic3-smts-interview-experie-jfrm/)

* **Question:** File Exporter System.

  * **Details:** Focus on Factory Design Pattern.
  * **Source:** [Oracle Health IC3 SMTS interview](https://leetcode.com/discuss/post/8103132/oracle-health-ic3-smts-interview-experie-jfrm/)

* **Question:** Wallet System.

  * **Details:** Heavy on calculations and edge cases.
  * **Source:** [BitGo Developer interview](https://leetcode.com/discuss/post/8102938/bitgo-developer-interview-experience-by-vhzs5/)

* **Question:** DB and Kafka eventual consistency.

  * **Details:** Expected answer hinted as CDC; transactional outbox also relevant.
  * **Source:** [BitGo Developer interview](https://leetcode.com/discuss/post/8102938/bitgo-developer-interview-experience-by-vhzs5/)

* **Question:** Webhook system.

  * **Details:** Candidate discussed optimizations and communication but design was not strongest.
  * **Source:** [Uber SDE-2](https://leetcode.com/discuss/post/5745602/uber-sde-2-by-anonymous_user-5vqx/)

* **Question:** Splitwise.

  * **Details:** Graph edge optimization/minimizing transactions, DB schema, concurrent expense entries, scaling large groups, global consistency.
  * **Source:** [Interview Experience SDE2 Uber Nov 2025](https://leetcode.com/discuss/post/7672800/interview-experience-sde2-uber-nov-2025-v0b3r/)

* **Question:** Bus Booking System.

  * **Source:** [Uber SDE-2 Interview Experience India](https://leetcode.com/discuss/post/6878990/uber-sde-2-interview-experience-india-by-h7wd/)

* **Question:** Instagram Feed.

  * **Source:** [Uber L4 Offer Chances](https://leetcode.com/discuss/post/7160420/uber-l4-offer-chances-by-anonymous_user-pyh6/)

---

### ML / AI System Design

* **Question:** How is evaluation done for scalable ML / Gemini-style systems?

  * **Source:** [Google Hiring Manager Round Gemini Applications](https://leetcode.com/discuss/post/7936329/google-hiring-manager-round-gemini-appli-mb5k/)

* **Question:** Design a multi-task system that handles varying domains.

  * **Details:** Limited resources, business pressure, access to new Gemini model. Discuss golden datasets, prompt enhancements vs fine-tuning, one-model limitations.
  * **Source:** [Google Hiring Manager Round Gemini Applications](https://leetcode.com/discuss/post/7936329/google-hiring-manager-round-gemini-appli-mb5k/)

* **Question:** How would you improve the model over time?

  * **Details:** Golden datasets + stakeholder feedback.
  * **Source:** [Google Hiring Manager Round Gemini Applications](https://leetcode.com/discuss/post/7936329/google-hiring-manager-round-gemini-appli-mb5k/)

* **Question:** What would you do if no additional data is available?

  * **Details:** Expected: agentic evaluation on golden datasets to iterate faster.
  * **Source:** [Google Hiring Manager Round Gemini Applications](https://leetcode.com/discuss/post/7936329/google-hiring-manager-round-gemini-appli-mb5k/)

---

## Managerial / Behavioral / Collaboration Questions

### Project Deep Dive / Ownership

* **Question:** Walk me through one project / previous project in detail.

  * **Details:** Asked across HM/TLM rounds. Expected components, services, decisions, impact, trade-offs, challenges.
  * **Source:** [Google Gemini HM](https://leetcode.com/discuss/post/7936329/google-hiring-manager-round-gemini-appli-mb5k/), [Uber SDE2 Chances](https://leetcode.com/discuss/post/6947609/uber-sde2-chances-by-anonymous_user-h4vb/), [Uber SDE-II March 25](https://leetcode.com/discuss/post/6827210/uber-sde-ii-interview-experience-march-2-oj2b/), [Uber SSE Bangalore L5A](https://leetcode.com/discuss/post/6628212/uber-sse-bangalore-l5-a-by-anonymous_use-0h6x/)

* **Question:** Walk through a complex/impactful project you worked on or led.

  * **Details:** Use virtual whiteboard. Cover every technical and non-technical challenge, contributions, impact.
  * **Source:** [Uber L5A SSE interview experience](https://leetcode.com/discuss/post/7043175/uber-l5a-sse-interview-experience-by-nik-qpdr/)

* **Question:** What good coding practices did you follow? What was your role and impact?

  * **Source:** [Uber SDE-2 Interview Experience L4 Bangalore](https://leetcode.com/discuss/post/7182332/uber-sde-2-interview-experience-l4-banga-fd1d/)

* **Question:** Tell me about ownership.

  * **Source:** [Google SDEIII L4 Interview](https://leetcode.com/discuss/post/8096071/google-sdeiii-interview-experience-by-an-67tm/), [Uber SDE-II March 25](https://leetcode.com/discuss/post/6827210/uber-sde-ii-interview-experience-march-2-oj2b/)

---

### Conflict / Feedback / Collaboration

* **Question:** Tell me about a conflict with co-workers and how you solved it.

  * **Source:** [Uber L4 Bangalore Offer](https://leetcode.com/discuss/post/6770968/uber-l4-bangalore-offer-by-10399vk-c73t/), [Uber SDE-1 Off-Campus](https://leetcode.com/discuss/post/6892974/uber-off-campus-2025-for-sde-1-by-anonym-br54/), [Uber SDE-2 Interview Experience India](https://leetcode.com/discuss/post/6878990/uber-sde-2-interview-experience-india-by-h7wd/)

* **Question:** Tell me about a time you had disagreement.

  * **Source:** [Google SDEIII L4 Interview](https://leetcode.com/discuss/post/8096071/google-sdeiii-interview-experience-by-an-67tm/)

* **Question:** Tell me about a time you received critical feedback from your manager and how you handled it.

  * **Source:** [Uber L4 Bangalore Offer](https://leetcode.com/discuss/post/6770968/uber-l4-bangalore-offer-by-10399vk-c73t/)

* **Question:** Tell me about a time you worked with someone outside your team.

  * **Source:** [Uber L4 Bangalore Offer](https://leetcode.com/discuss/post/6770968/uber-l4-bangalore-offer-by-10399vk-c73t/)

* **Question:** How do you collaborate with your team and prove you are a team player?

  * **Source:** [Uber L4 Interview Experience US](https://leetcode.com/discuss/post/7217970/uber-sde2-interview-experience-us-offer-hfj64/)

* **Question:** Conflict resolution and team scenarios.

  * **Source:** [BitGo Developer interview](https://leetcode.com/discuss/post/8102938/bitgo-developer-interview-experience-by-vhzs5/)

---

### Challenges / Mistakes / Innovation

* **Question:** Tell me about a time you made a mistake.

  * **Source:** [Google SDEIII L4 Interview](https://leetcode.com/discuss/post/8096071/google-sdeiii-interview-experience-by-an-67tm/)

* **Question:** Describe challenges you faced at work.

  * **Source:** [Uber SDE-2 Interview Experience India](https://leetcode.com/discuss/post/6878990/uber-sde-2-interview-experience-india-by-h7wd/), [Uber SDE-II March 25](https://leetcode.com/discuss/post/6827210/uber-sde-ii-interview-experience-march-2-oj2b/)

* **Question:** Tell me about a time you proposed a unique way of solving a problem.

  * **Source:** [Uber SDE-2 Interview Experience India](https://leetcode.com/discuss/post/6878990/uber-sde-2-interview-experience-india-by-h7wd/)

* **Question:** How do you debug issues?

  * **Details:** Asked in HM practical-thinking round.
  * **Source:** [Uber SDE II 6 Rounds](https://leetcode.com/discuss/post/8018831/uber-interview-experience-sde-ii-6-round-gnl6/)

* **Question:** How do you ensure feature quality?

  * **Details:** Testing, monitoring, rollout quality.
  * **Source:** [Uber SDE II 6 Rounds](https://leetcode.com/discuss/post/8018831/uber-interview-experience-sde-ii-6-round-gnl6/)

---

### Stress / Motivation / Mentorship

* **Question:** How do you handle overburn in your team, and how do you stay afloat during stressful times?

  * **Source:** [Uber L4 Interview Experience US](https://leetcode.com/discuss/post/7217970/uber-sde2-interview-experience-us-offer-hfj64/)

* **Question:** Why do you want to leave your current role / switch?

  * **Source:** [Uber L5A SSE interview experience](https://leetcode.com/discuss/post/7043175/uber-l5a-sse-interview-experience-by-nik-qpdr/), [Uber SDE-II March 25](https://leetcode.com/discuss/post/6827210/uber-sde-ii-interview-experience-march-2-oj2b/)

* **Question:** What type of work are you looking for?

  * **Source:** [Uber L5A SSE interview experience](https://leetcode.com/discuss/post/7043175/uber-l5a-sse-interview-experience-by-nik-qpdr/)

* **Question:** Discuss mentorship experience.

  * **Details:** Lack of mentorship was negative HM feedback in one post.
  * **Source:** [Uber L4 Bangalore Offer](https://leetcode.com/discuss/post/6770968/uber-l4-bangalore-offer-by-10399vk-c73t/)

---

### Behavioral Format / Misc

* **Question:** Amazon Leadership Principle questions.

  * **Details:** 4 thought-based LP questions in Amazon intern OA; exact questions not listed.
  * **Source:** [Amazon SDE Intern OA](https://leetcode.com/discuss/post/8099432/amazon-sde-intern-oa-by-jackiiee-l75j/)

* **Question:** How do you use AI tools in daily work?

  * **Source:** [Uber SDE II 6 Rounds](https://leetcode.com/discuss/post/8018831/uber-interview-experience-sde-ii-6-round-gnl6/)

* **Question:** Plan a team outing where people have different preferences and are not aligned.

  * **Details:** Situational behavioral question: align team and reach decision.
  * **Source:** [Google SDEIII L4 Interview](https://leetcode.com/discuss/post/8096071/google-sdeiii-interview-experience-by-an-67tm/)

* **Question:** General leadership and collaboration questions related to past experience.

  * **Source:** [Uber Frontend L4](https://leetcode.com/discuss/post/7279221/uber-interview-experience-l4-frontend-ub-tcxu/), [SDE-2 Frontend Interview Uber](https://leetcode.com/discuss/post/6970499/sde-2-frontend-interview-uber-by-anonymo-kfq0/)

* **Question:** HM round on HLD and programming-language depth.

  * **Details:** Candidate expected behavioral, but round was technical around HLD and language internals.
  * **Source:** [Uber L4 Chances](https://leetcode.com/discuss/post/6368345/uber-l4-chances-by-anonymous_user-4le1/)
