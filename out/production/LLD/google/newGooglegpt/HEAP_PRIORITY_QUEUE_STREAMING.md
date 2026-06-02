# Heap, Priority Queue, Streaming

[Back to README](./README.md)

## Heap - Max/Min Selection

### 35. Seat Students to Minimize Adjacent Same Exam

**Level / source tag:** Onsite set

_Links:_ [LC 767 Reorganize String](https://leetcode.com/problems/reorganize-string/), [LC 358 Rearrange String k Distance Apart](https://leetcode.com/problems/rearrange-string-k-distance-apart/)

**Problem.** Reorder students represented by exam IDs to minimize adjacent equal exams. Output one valid arrangement.

**Input / Output.**
- Input: `int[] exams`
- Output: `int[] arrangement`

**Approach.**
- Same as reorganize string: repeatedly pick most frequent exam not equal to previous.
- Max-heap by remaining count.

### 121. Furthest Building with Bricks and Ropes

**Level / source tag:** Google Onsite Round 2

_Links:_ [LC 1642 Furthest Building You Can Reach](https://leetcode.com/problems/furthest-building-you-can-reach/)

**Problem.** Given building heights, start at first building with `b` bricks and `r` ropes. Moving to a higher next building requires either bricks equal to height difference or one rope. Find maximum distance reachable.

**Approach.**
- Use min-heap for climbs assigned to ropes; when ropes exceeded, pay smallest climb with bricks.

## Heap - Cooldown / Scheduler

### 4. ScoreQueue / Ads: Highest Score with No Consecutive Same Content

**Level / source tag:** L4 / Onsite

_Links:_ [LC 621 Task Scheduler](https://leetcode.com/problems/task-scheduler/), [LC 358 Rearrange String k Distance Apart](https://leetcode.com/problems/rearrange-string-k-distance-apart/), [Google L4 Bangalore 2026 post](https://leetcode.com/discuss/post/8276966/google-l4-banglore-by-anonymous_user-97gh/)

**Problem.** Maintain elements `(content: String, score: int)`.

APIs:
- `addElement(content, score)`
- `getElement()`

`getElement()` returns highest-score content, but cannot return the same content consecutively (1-turn cooldown). After being returned, its score decrements by 1 and stays in the pool. If no elements exist, return empty string.

**Constraints.**
- Up to `10^5` operations.
- Pasted master says content is unique at insertion.

**Examples from notes.**
- `apple(5), banana(3), cherry(5) -> apple -> cherry -> apple -> cherry -> banana ...`

**Follow-up from 2026 L4 post.**
- Introduce a cooldown/gap before the same ad can appear again while keeping operations close to `O(1)`.

**Related patterns.**
- Greedy heap with temporary holdout.
- Task scheduler / k-distance cooldown.

### 120. Single-Threaded CPU and Two-Core Follow-up

**Level / source tag:** Google Onsite Round 1

_Links:_ [LC 1834 Single-Threaded CPU](https://leetcode.com/problems/single-threaded-cpu/)

**Problem.** Given tasks with `id`, `arrivalTime`, `executionTime`. At any time, from pending tasks pick the minimum execution time first on a single-core processor. Return order of execution.

**Follow-up.**
- Modify for two-core processor.

**Approach.**
- Sort tasks by arrival time.
- Min-heap by processing time then id.
- For multiple cores, maintain machine availability heap.

## Streaming - Window Statistics

### 42. Sliding Window Mean of Last N Excluding Largest K

**Level / source tag:** Onsite / Screening

_Links:_ [LC 1825 Finding MK Average](https://leetcode.com/problems/finding-mk-average/)

**Problem.** Stream of ints; maintain mean of last `N` elements excluding the largest `K` values in the window.

**Input / Output.**
- APIs: `add(num)`, `mean()`.

**Example from notes.**
- `N=5, K=2` on stream `[20,2,-2,0,10,1,5,-2,0]` -> mean over lower part after excluding top 2.

**Approach.**
- Queue for last N.
- Two multisets / heaps with lazy deletion:
  - `large`: largest K values
  - `rest`: included values whose sum is tracked.

## Streaming - Dedup / Logs

### 5. Streaming Logs Sorted by Timestamp (Stable Ties)

**Level / source tag:** L4 R2

_Links:_ [LC 635 Design Log Storage System](https://leetcode.com/problems/design-log-storage-system/)

**Problem.** Logs arrive in any order. Each log has timestamp `"DD-MM-YY HH:MM:SS"` and a message. Maintain sorted-by-time logs. If timestamps tie, preserve arrival order.

**Input / Output.**
- Methods: `add(String ts, String msg)`, `List<Log> getSorted()`
- Output stable-sorted logs.

**Constraints.**
- Up to `10^5` logs.

**Implementation notes.**
- Parse timestamp into epoch-like comparable value.
- Store arrival sequence number for stable tie-breaking.
- Use ordered structure or append + stable sort depending on call pattern.

### 36. RPC Timeout Detection (Earliest Time)

**Level / source tag:** Onsite set

_Links:_ not provided in pasted notes.

**Problem.** Stream of logs `{id, time, type(start/end)}`. Given timeout `T`, report the earliest time at which some request has definitely timed out.

**Input / Output.**
- Input: logs and `T`.
- Output: list of `(id, timeDetected)`.

**Example note.**
- Pasted note mentions output `{1,6}` because at time 6 request 1 exceeded `T` without an end.

**Approach.**
- Min-heap keyed by `startTime + T`.
- Sweep logs in timestamp order.
- Before processing each log time, pop expired starts that have no matching end.

### 58. Message Deduplicator within 10 Seconds (Both Sides Discarded)

**Level / source tag:** Google Onsite L4/L5

_Links:_ not provided in pasted notes.

**Problem.** From stream `(t, msg)`, discard duplicates within a 10-second window. Both occurrences are discarded. Output valid messages in input order.

**Input / Output.**
- Input: stream of timestamped messages.
- Output: filtered list.

**Notes.**
- Also referenced as Google Logger Rate Limiter variant within `±10s`.

**Approach.**
- Maintain map from message to recent timestamps/items.
- Need delayed output if a future duplicate can invalidate a current message.

## Streaming - Random / Sampling

### 73. Weighted Random from Stream on Demand

**Level / source tag:** Google Screening

_Links:_ [LC 528 Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight/)

**Problem.** Stream of non-negative integers. When seeing `0`, output a random number from previously seen non-zero values with probability proportional to frequency.

**Input / Output.**
- Input: stream.
- Output: sampled numbers at zeros.

**Approach.**
- Maintain map value -> count and total count.
- For pick: random integer in `[1,total]`, prefix over counts.
- Optimize with Fenwick tree or dynamic alias structure if many distinct values and picks.

## Streaming - Triplets

### 19. Stream of Floats/Integers: Return Any Triplet within Threshold `D` and Remove

**Level / source tag:** Unspecified

_Links:_ not provided in pasted notes.

**Problem.** Given a stream of unique floats or integers and threshold `D`, whenever possible return any three values `{a,b,c}` with all pairwise distances `<= D`, then remove them from memory. Continue processing.

**Input / Output.**
- Constructor: `TripletStream(double D)`
- `add(x)` returns a triplet or empty list.

**Constraints.**
- Up to `1e5` values in prompt variants.

**Examples.**
- Stream `1,10,7,-2,8,...`, `D = 5` -> when `8` arrives, returns `7,8,10`.
- `d=2`, stream `[10,5,7,6,8,3,4,1,2]` -> output can include `[[5,6,7],[1,2,3]]`.
- Stream `[1.0, 2.0, 8.0, 12.0, 3.0]`, `d = 3` -> `[1.0, 2.0, 3.0]`.

**Approach.**
- Balanced BST / `TreeMap`.
- A valid triplet exists if three values fit in a window of length `D`.
- On each insert, inspect neighbors around `x` or maintain buckets.

## Streaming - Workers / Async

### 82. GetTotalCount: Word Count API with Workers

**Level / source tag:** Q87 / Onsite scheduling API

_Links:_ not provided in pasted notes.

**Problem.** Implement `GetTotalCount(num_docs)` returning total number of words in documents with IDs `[0, num_docs)`.

API:

```text
https://www.count-words.com/document/{docId}?worker_id={id}
```

Rules:
- A worker can handle only one request at a time.
- If another request is sent while it is busy, it fails.

**Follow-up.**
- `GetTotalCount(num_docs, num_workers)` with worker IDs `[0..num_workers)`.

**Examples.**
- `GetTotalCount(5)` -> total words in docs `0..4`.
- `GetTotalCount(5,3)` -> same but with workers `0,1,2`.

**Constraints.**
- `num_docs >= 0`.
- API latency may vary.

**Approach.**
- Sequential variant uses one worker.
- Parallel variant uses worker pool / executor; assign next document only when worker completes.
