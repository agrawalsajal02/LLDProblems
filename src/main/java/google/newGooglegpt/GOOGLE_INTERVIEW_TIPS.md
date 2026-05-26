
# Google Interview Tips

[Back to README](./README.md)

This file separates preparation and process advice from the DSA problem catalog.

## Interview Flow and Communication

- Keep the introduction short. Multiple pasted experiences say Google does not extend the 45-minute interview, so preserve time for follow-ups.
- Start by clarifying the problem and repeating it back in your own words.
- Ask about edge cases, constraints, input shape, and whether variants are allowed. This matters especially for ambiguous questions like unary minus in equation parsing, overlapping rectangle area, lakes with land inside, and transaction sign conventions.
- First discuss brute force briefly, but do not spend too long coding it unless the interviewer explicitly asks.
- Move toward the optimal approach through trade-offs: time, space, data structure choice, and follow-up adaptability.
- Dry run on at least one concrete test case before coding or immediately after coding.
- Keep talking while coding: explain loop invariants, data structure contents, and why the code handles edge cases.
- Leave time for tests. Interviewers repeatedly asked for unit tests in the pasted experiences.

## Coding Quality Expectations

- Clean variable names and readable structure matter.
- Avoid over-engineering with heavy data structures when a simpler one solves the stated constraints.
- Know complexities for common structures: priority queue, balanced tree, hashmap, DSU, BFS/DFS, Dijkstra, Kahn topo, segment tree/Fenwick.
- Practice writing code quickly after the algorithm is agreed. One pasted experience says the expectation felt like “write code in 5 minutes once algorithm is identified.”
- Hints can help you recover, but several candidates felt needing many hints hurt feedback. Keep your reasoning visible so hints look like collaboration, not rescue.

## DSA Topics Called Out in the Notes

- Arrays and strings.
- Linked list.
- Graphs, especially BFS, shortest paths, topological sort, DSU, and grid traversal.
- Trees and binary trees.
- Dynamic programming.
- Greedy and priority queues.
- Segment tree / Fenwick tree for range queries.
- Trie for prefix and filesystem-style problems.
- Heap implementation and heap-based scheduling.
- Matrix problems: 01 matrix, rotten oranges, flood fill, shortest bridge, safest path.
- Parsing and expression evaluation.

## Graph Prep Guidance from the Pasted Notes

- One prep note says BFS is often enough for many interview graph/tree/matrix problems, and Kahn’s algorithm is useful for directed cycle/topological problems.
- Another 2026 post asks whether SCC/Kosaraju/Tarjan/Euler paths are worth deep study close to onsite. Practical take: prioritize BFS/DFS, shortest path, topo, DSU, and grid patterns first. Know what SCC/Euler are and one standard template if time permits, but they appear more niche than the core patterns in the pasted set.
- Useful links from pasted note:
  - [Google shortest path tag](https://leetcode.com/problemset/?companySlugs=google&page=1&topicSlugs=shortest-path)
  - [Google BFS tag](https://leetcode.com/problemset/?companySlugs=google&page=1&topicSlugs=breadth-first-search)

## Google Process Notes from Pasted Experiences

- Recruiters may give 2-8 weeks preparation time depending on situation.
- Feedback labels mentioned: No Hire, Lean Hire, Hire, Strong Hire.
- Strong behavioral / Googlyness can help, but coding rounds still carry high weight.
- Team matching and hiring committee can take weeks or months. Several pasted experiences mention silence or delays.
- Some candidates got extra rounds when one technical round was weak or feedback was missing.
- Team matching can influence final outcome, especially if a hiring manager strongly supports the packet.

## Googlyness / Behavioral Prompts Mentioned

- Tell me about a project you are proud of.
- Tell me about a technical challenge in your current role.
- Tell me about a time you handled conflict with a teammate.
- Tell me about working with a non-performer and how you helped.
- How would you resolve a technical conflict between two junior engineers?
- How do you prioritize work items?
- How would you evaluate technical docs?
- How do you mentor juniors?
- How would you improve culture in your team/org?
- What is a non-negotiable culture trait for you?
- Why Google?
- Tell me about a project that did not go as planned.
- Tell me about adapting to a new or unfamiliar situation.
- How would you build a team?
- If appointed manager of an underperforming team, how would you improve it?
- If planning a team outing where people have different preferences, how would you reach a decision?

## Preparation Resources Mentioned

- LeetCode medium/hard practice, especially company-tagged Google problems.
- LeetCode daily challenges and interview assessments.
- TakeUForward sheets, Sean Prashad patterns, and Google-tagged lists were mentioned by candidates.
- Cracking the Coding Interview was mentioned as a brush-up resource.
- Abdul Bari data structures videos.
- William Fiset graph theory.
- NeetCode, TechDose, Aditya Verma.
- For system design: DDIA, Alex Xu Vol. 1 and 2, System Design Primer, Jordan Has No Life, InfoQ talks, Mikhail Smarshchok videos.

## System Design Tips from Pasted Notes

- Understand CAP theorem intuitively.
- Learn unique ID generators like Snowflake.
- Learn distributed key-value databases under both consistency-leaning and availability-leaning designs.
- Learn distributed locking.
- Learn Saga, event-driven design, API gateway, BFF, circuit breaker, database per service.
- Keep HLD simple first. Do not add CDN, queues, Elasticsearch, or other components unless the requirement needs them.
- Avoid overcommitting requirements not asked by the interviewer.
- Do not ignore LLD: parking lot, elevator, external merge sort, Twitter feed, cache, rate limiter.

## Mindset Notes

- Multiple candidates wrote that feeling underprepared is normal.
- Calm problem clarification is better than silently coding the wrong interpretation.
- One campus candidate specifically regretted not asking clarifying questions due to nervousness.
- Interviewers may be friendly and offer hints; use that interaction to show reasoning.
- It is okay to fail; preparation compounds even when one loop does not work out.
