# Day 9: Focused 4-Question Set

**Date:** Wednesday, June 3, 2026

**Focus:** Streaming and heap day: MK mean, triplets, timeout detection, stable logs.

## Checklist

- [ ] Q42. Sliding Window Mean of Last N Excluding Largest K (Heap, Priority Queue, Streaming / Streaming - Window Statistics)
- [ ] Q19. Stream of Floats/Integers: Return Any Triplet within Threshold `D` and Remove (Heap, Priority Queue, Streaming / Streaming - Triplets)
- [ ] Q36. RPC Timeout Detection (Earliest Time) (Heap, Priority Queue, Streaming / Streaming - Dedup / Logs)
- [ ] Q5. Streaming Logs Sorted by Timestamp (Stable Ties) (Heap, Priority Queue, Streaming / Streaming - Dedup / Logs)

## How to Use Today

- Spend 5 minutes clarifying the prompt and constraints before solving.
- Write brute force in words, then the optimized approach and complexity.
- Code only after the approach is stable.
- Dry run one provided test case and add two edge cases.
- At the end, write a 3-line recap: pattern, key data structure, mistake to avoid.

---

## Q42. Sliding Window Mean of Last N Excluding Largest K

**Topic:** Heap, Priority Queue, Streaming

**Type:** Streaming - Window Statistics

**Original topic file:** [HEAP_PRIORITY_QUEUE_STREAMING.md](../HEAP_PRIORITY_QUEUE_STREAMING.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q19. Stream of Floats/Integers: Return Any Triplet within Threshold `D` and Remove

**Topic:** Heap, Priority Queue, Streaming

**Type:** Streaming - Triplets

**Original topic file:** [HEAP_PRIORITY_QUEUE_STREAMING.md](../HEAP_PRIORITY_QUEUE_STREAMING.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q36. RPC Timeout Detection (Earliest Time)

**Topic:** Heap, Priority Queue, Streaming

**Type:** Streaming - Dedup / Logs

**Original topic file:** [HEAP_PRIORITY_QUEUE_STREAMING.md](../HEAP_PRIORITY_QUEUE_STREAMING.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:

---

## Q5. Streaming Logs Sorted by Timestamp (Stable Ties)

**Topic:** Heap, Priority Queue, Streaming

**Type:** Streaming - Dedup / Logs

**Original topic file:** [HEAP_PRIORITY_QUEUE_STREAMING.md](../HEAP_PRIORITY_QUEUE_STREAMING.md)

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

### Practice Notes

- Brute force:
- Optimized idea:
- Complexity:
- Edge cases:
- Final recap:
