# Data Structures & Algorithms — Senior Developer Mastery
## Patterns, Sub-Patterns, Question Bank & Progressive Practice Guide

> Scope: the **6 modules provided** are retained and refined. The main additions are finer-grained patterns, sub-patterns, implementation drills, and a progressive question bank from **Basic → Intermediate → Advanced → Senior/Expert**.
>
> Primary language assumed: **Java**, while the algorithmic ideas are language-independent.

---

# How to Use This DSA Guide

For every pattern:

1. Learn the invariant.
2. Implement it from scratch.
3. Solve a basic recognition problem.
4. Solve an intermediate variation.
5. Solve an advanced problem.
6. Explain time and space complexity.
7. Identify edge cases.
8. Explain why a naive approach fails.
9. Optimize the solution.
10. Re-implement without looking at notes.

## Difficulty Levels

- **B1–B3:** Basic
- **I1–I3:** Intermediate
- **A1–A3:** Advanced
- **S1–S3:** Senior/Expert

---

# Module 1 — Complexity Analysis & Big-O

# 1.1 Asymptotic Analysis

## Patterns

- [ ] Big-O — upper-bound growth
- [ ] Big-Omega — lower-bound growth
- [ ] Big-Theta — tight-bound growth
- [ ] Best case
- [ ] Average case
- [ ] Worst case
- [ ] Input-size dependence
- [ ] Dominant terms
- [ ] Constant-factor reasoning
- [ ] Nested-loop analysis
- [ ] Sequential-loop analysis
- [ ] Conditional complexity
- [ ] Recursive complexity
- [ ] Logarithmic growth
- [ ] Polynomial growth
- [ ] Exponential growth

## Sub-Patterns

### Loop Analysis
- [ ] Single loop
- [ ] Nested loops
- [ ] Dependent nested loops
- [ ] Triangular loops
- [ ] Logarithmic loops
- [ ] Loop with multiplication/division
- [ ] Multiple independent loops

### Recursion Analysis
- [ ] Single recursive call
- [ ] Multiple recursive calls
- [ ] Linear recursion
- [ ] Binary recursion
- [ ] Divide-and-conquer recurrence
- [ ] Recursion tree
- [ ] Recurrence substitution

## Questions

### Basic

- [ ] B1 — Find complexity of a single loop.
- [ ] B2 — Find complexity of two sequential loops.
- [ ] B3 — Find complexity of nested loops.
- [ ] B4 — Analyze a loop that doubles `i`.
- [ ] B5 — Analyze a loop that halves `n`.
- [ ] B6 — Rank O(1), O(log n), O(n), O(n log n), O(n²), O(2ⁿ).
- [ ] B7 — Determine whether two loops are O(n) or O(n²).
- [ ] B8 — Analyze a simple recursive function.

### Intermediate

- [ ] I1 — Analyze triangular nested loops.
- [ ] I2 — Analyze dependent loops such as `j < i`.
- [ ] I3 — Analyze recursive binary search.
- [ ] I4 — Analyze merge sort.
- [ ] I5 — Analyze quicksort best/average/worst cases.
- [ ] I6 — Determine complexity of tree traversal.
- [ ] I7 — Analyze BFS/DFS complexity using V and E.

### Advanced

- [ ] A1 — Derive complexity from a recurrence.
- [ ] A2 — Compare multiple recurrence solutions.
- [ ] A3 — Identify hidden O(n²) behavior in apparently linear code.
- [ ] A4 — Analyze nested data-structure operations.
- [ ] A5 — Analyze amortized operations in dynamic arrays and hash tables.

### Senior

- [ ] S1 — Explain why asymptotic complexity does not directly predict wall-clock latency.
- [ ] S2 — Analyze cache locality and memory-access costs.
- [ ] S3 — Compare two algorithms with different complexity but different constants/cache behavior.

---

# 1.2 Space Complexity

## Patterns

- [ ] Auxiliary space
- [ ] Input space
- [ ] Output space
- [ ] In-place algorithms
- [ ] Recursion stack
- [ ] Heap allocation
- [ ] Temporary arrays
- [ ] Hash-map auxiliary storage

## Questions

- [ ] B1 — Determine space complexity of iterative array traversal.
- [ ] B2 — Determine recursion-stack space.
- [ ] I1 — Compare recursive vs iterative tree traversal.
- [ ] I2 — Analyze merge sort auxiliary space.
- [ ] I3 — Analyze BFS vs DFS memory.
- [ ] A1 — Optimize an algorithm from O(n) auxiliary space to O(1).
- [ ] S1 — Explain when using extra memory improves production performance.

---

# 1.3 Amortized Analysis

## Patterns

- [ ] Aggregate method
- [ ] Accounting method
- [ ] Potential method
- [ ] Dynamic-array resizing
- [ ] Hash-map resizing
- [ ] Stack with occasional expensive operation

## Questions

- [ ] B1 — Why is dynamic-array append considered amortized O(1)?
- [ ] I1 — Derive the total cost of repeated array resizing.
- [ ] I2 — Explain hash-table rehashing amortization.
- [ ] A1 — Analyze a custom dynamic array.
- [ ] A2 — Compare resize factors such as 1.5x vs 2x.
- [ ] S1 — Explain amortized complexity vs worst-case latency in a production service.

---

# 1.4 Master Theorem

## Patterns

- [ ] Divide-and-conquer recurrence
- [ ] `T(n) = aT(n/b) + f(n)`
- [ ] Case 1
- [ ] Case 2
- [ ] Case 3
- [ ] Recursion-tree intuition
- [ ] Merge sort analysis
- [ ] Binary-search-style recurrences

## Questions

- [ ] B1 — Analyze binary search.
- [ ] I1 — Analyze merge sort.
- [ ] I2 — Analyze divide-and-conquer maximum search.
- [ ] A1 — Solve non-trivial Master Theorem recurrences.
- [ ] A2 — Recognize when Master Theorem does not directly apply.
- [ ] S1 — Solve recurrences using recursion-tree/substitution methods when necessary.

---

# Module 2 — Fundamental Data Structures

# 2.1 Arrays

## Patterns

- [ ] Contiguous memory
- [ ] Random access
- [ ] Sequential traversal
- [ ] Insert/delete shifting
- [ ] Dynamic arrays
- [ ] Prefix sums
- [ ] Difference arrays
- [ ] Two-dimensional arrays
- [ ] Matrix traversal

## Sub-Patterns

### Prefix Sum

- [ ] 1D prefix sum
- [ ] Range sum
- [ ] 2D prefix sum
- [ ] Difference array
- [ ] Range update

### Array Manipulation

- [ ] Reverse
- [ ] Rotate
- [ ] Partition
- [ ] Remove duplicates
- [ ] Merge sorted arrays
- [ ] In-place transformation

## Questions

### Basic

- [ ] B1 — Find maximum/minimum.
- [ ] B2 — Reverse an array.
- [ ] B3 — Remove duplicates from sorted array.
- [ ] B4 — Move zeroes.
- [ ] B5 — Find second-largest element.
- [ ] B6 — Rotate array.

### Intermediate

- [ ] I1 — Prefix-sum range queries.
- [ ] I2 — Maximum subarray.
- [ ] I3 — Merge two sorted arrays.
- [ ] I4 — Product of array except self.
- [ ] I5 — Find missing/repeated values.
- [ ] I6 — Merge intervals.
- [ ] I7 — Rotate matrix.

### Advanced

- [ ] A1 — Difference-array range updates.
- [ ] A2 — Maximum subarray with constrained variants.
- [ ] A3 — Trapping rain water.
- [ ] A4 — Median of two sorted arrays.
- [ ] A5 — In-place matrix transformations.

---

# 2.2 Strings

## Patterns

- [ ] String immutability
- [ ] Character frequency
- [ ] StringBuilder
- [ ] Two-pointer string processing
- [ ] Sliding window
- [ ] String hashing
- [ ] Prefix matching
- [ ] Palindrome processing

## Questions

### Basic

- [ ] B1 — Reverse a string.
- [ ] B2 — Check palindrome.
- [ ] B3 — Count character frequencies.
- [ ] B4 — Check anagram.
- [ ] B5 — Remove duplicate characters.

### Intermediate

- [ ] I1 — Longest substring without repeating characters.
- [ ] I2 — Valid palindrome with ignored characters.
- [ ] I3 — Group anagrams.
- [ ] I4 — Longest common prefix.
- [ ] I5 — String compression.
- [ ] I6 — Minimum window substring.

### Advanced

- [ ] A1 — Longest palindromic substring.
- [ ] A2 — KMP pattern matching.
- [ ] A3 — Rabin-Karp hashing.
- [ ] A4 — Z-algorithm.
- [ ] A5 — Trie-based prefix matching.

---

# 2.3 Linked Lists

## Patterns

- [ ] Singly linked list
- [ ] Doubly linked list
- [ ] Circular linked list
- [ ] Sentinel/dummy node
- [ ] Fast/slow pointers
- [ ] Two-pointer gap
- [ ] In-place reversal
- [ ] Recursive reversal
- [ ] Cycle detection
- [ ] Cycle entry
- [ ] Merge lists

## Questions

### Basic

- [ ] B1 — Traverse linked list.
- [ ] B2 — Insert at head.
- [ ] B3 — Insert at tail.
- [ ] B4 — Delete a node.
- [ ] B5 — Find middle node.
- [ ] B6 — Reverse linked list.

### Intermediate

- [ ] I1 — Detect cycle.
- [ ] I2 — Find cycle entry.
- [ ] I3 — Find kth node from end.
- [ ] I4 — Merge two sorted lists.
- [ ] I5 — Remove nth node from end.
- [ ] I6 — Check palindrome linked list.
- [ ] I7 — Add two numbers represented by linked lists.

### Advanced

- [ ] A1 — Reverse nodes in groups of K.
- [ ] A2 — Sort a linked list.
- [ ] A3 — Copy list with random pointer.
- [ ] A4 — Flatten multilevel linked list.
- [ ] A5 — Reorder linked list.
- [ ] S1 — Design an intrusive linked-list structure for O(1) removal.

---

# 2.4 Stack

## Patterns

- [ ] Array-backed stack
- [ ] Linked-list stack
- [ ] LIFO
- [ ] Monotonic increasing stack
- [ ] Monotonic decreasing stack
- [ ] Expression evaluation
- [ ] Parentheses matching
- [ ] Undo/redo

## Questions

- [ ] B1 — Implement stack.
- [ ] B2 — Valid parentheses.
- [ ] B3 — Min stack.
- [ ] I1 — Evaluate postfix expression.
- [ ] I2 — Infix to postfix.
- [ ] I3 — Next greater element.
- [ ] I4 — Daily temperatures.
- [ ] A1 — Largest rectangle in histogram.
- [ ] A2 — Maximal rectangle.
- [ ] A3 — Stock span.
- [ ] S1 — Identify monotonic-stack opportunities from problem constraints.

---

# 2.5 Queue & Deque

## Patterns

- [ ] FIFO
- [ ] Circular queue
- [ ] Array deque
- [ ] Linked queue
- [ ] Monotonic deque
- [ ] BFS queue
- [ ] Sliding-window maximum

## Questions

- [ ] B1 — Implement queue.
- [ ] B2 — Implement circular queue.
- [ ] B3 — Implement deque.
- [ ] I1 — Sliding-window maximum.
- [ ] I2 — First negative number in every window.
- [ ] I3 — Shortest path in an unweighted graph using BFS.
- [ ] A1 — Constrained sliding-window optimization using monotonic deque.

---

# Module 3 — Hash-Based & Tree-Based Structures

# 3.1 Hashing

## Patterns

- [ ] Hash function
- [ ] Bucket
- [ ] Collision
- [ ] Separate chaining
- [ ] Open addressing
- [ ] Linear probing
- [ ] Quadratic probing
- [ ] Double hashing
- [ ] Load factor
- [ ] Rehashing
- [ ] Hash distribution
- [ ] Mutable-key danger

## Questions

### Basic

- [ ] B1 — Implement a simple hash map.
- [ ] B2 — Implement a hash set.
- [ ] B3 — Count frequencies.
- [ ] B4 — Find duplicate values.
- [ ] B5 — Two Sum.

### Intermediate

- [ ] I1 — Group anagrams.
- [ ] I2 — Longest consecutive sequence.
- [ ] I3 — Subarray sum equals K.
- [ ] I4 — First unique character.
- [ ] I5 — Intersection of arrays.

### Advanced

- [ ] A1 — Implement separate chaining.
- [ ] A2 — Implement open addressing.
- [ ] A3 — Design a frequency tracker.
- [ ] A4 — Design a constant-time membership structure.
- [ ] S1 — Explain why a poor hash function can turn expected O(1) into O(n).

---

# 3.2 Binary Trees

## Patterns

- [ ] Recursive DFS
- [ ] Iterative DFS
- [ ] BFS/level order
- [ ] Preorder
- [ ] Inorder
- [ ] Postorder
- [ ] Height/depth
- [ ] Diameter
- [ ] Balanced-tree checking
- [ ] Path-sum patterns
- [ ] Tree serialization

## Questions

### Basic

- [ ] B1 — Preorder traversal.
- [ ] B2 — Inorder traversal.
- [ ] B3 — Postorder traversal.
- [ ] B4 — Level-order traversal.
- [ ] B5 — Maximum depth.
- [ ] B6 — Count nodes.
- [ ] B7 — Search for a value.

### Intermediate

- [ ] I1 — Check balanced binary tree.
- [ ] I2 — Diameter of binary tree.
- [ ] I3 — Invert binary tree.
- [ ] I4 — Level-order traversal variations.
- [ ] I5 — Path sum.
- [ ] I6 — Right-side view.
- [ ] I7 — Lowest Common Ancestor.

### Advanced

- [ ] A1 — Serialize/deserialize binary tree.
- [ ] A2 — Construct tree from preorder + inorder.
- [ ] A3 — Construct tree from inorder + postorder.
- [ ] A4 — Maximum path sum.
- [ ] A5 — Vertical traversal.
- [ ] A6 — Boundary traversal.
- [ ] S1 — Design a memory-conscious tree representation.

---

# 3.3 Binary Search Trees

## Patterns

- [ ] BST invariant
- [ ] Search
- [ ] Insert
- [ ] Delete
- [ ] Inorder sorted property
- [ ] Validate BST
- [ ] Predecessor
- [ ] Successor
- [ ] Kth smallest/largest
- [ ] LCA

## Questions

- [ ] B1 — Search in BST.
- [ ] B2 — Insert into BST.
- [ ] B3 — Validate BST.
- [ ] I1 — Delete node.
- [ ] I2 — Find kth smallest.
- [ ] I3 — Find predecessor/successor.
- [ ] I4 — Lowest common ancestor.
- [ ] A1 — Convert sorted array to balanced BST.
- [ ] A2 — Recover corrupted BST.
- [ ] A3 — Serialize/deserialize BST.
- [ ] S1 — Explain why an unbalanced BST can degrade to O(n).

---

# 3.4 AVL Trees

## Patterns

- [ ] Balance factor
- [ ] Height maintenance
- [ ] LL rotation
- [ ] RR rotation
- [ ] LR rotation
- [ ] RL rotation
- [ ] Insertion
- [ ] Deletion
- [ ] Rebalancing

## Questions

- [ ] I1 — Calculate balance factors.
- [ ] I2 — Perform LL rotation.
- [ ] I3 — Perform RR rotation.
- [ ] A1 — Implement AVL insertion.
- [ ] A2 — Implement AVL deletion.
- [ ] A3 — Trace multiple rotations.
- [ ] S1 — Compare AVL and Red-Black trees for lookup/update workloads.

---

# 3.5 Red-Black Trees

## Patterns

- [ ] Node colors
- [ ] Root property
- [ ] Red-node property
- [ ] Black-height
- [ ] Rotations
- [ ] Recoloring
- [ ] Insertion fix-up
- [ ] Deletion fix-up
- [ ] Java TreeMap/TreeSet relationship

## Questions

- [ ] I1 — Identify Red-Black invariant violations.
- [ ] I2 — Trace insertion fix-up.
- [ ] A1 — Implement insertion.
- [ ] A2 — Explain deletion fix-up.
- [ ] S1 — Explain why Java's TreeMap uses a Red-Black tree rather than an AVL tree.

---

# 3.6 B-Trees & B+ Trees

## Patterns

- [ ] Multi-way search tree
- [ ] Node capacity
- [ ] Splitting
- [ ] Merging
- [ ] Height minimization
- [ ] Disk/page locality
- [ ] B+ tree leaf chaining
- [ ] Range queries
- [ ] Database indexes

## Questions

- [ ] I1 — Explain why binary trees are poor for disk-based indexing.
- [ ] I2 — Trace B-tree insertion.
- [ ] I3 — Trace node splitting.
- [ ] A1 — Compare B-tree vs B+ tree.
- [ ] S1 — Explain why database indexes prefer page-oriented trees.

---

# 3.7 Trie

## Patterns

- [ ] Prefix tree
- [ ] Character nodes
- [ ] End-of-word marker
- [ ] Insert
- [ ] Search
- [ ] Prefix search
- [ ] Delete
- [ ] Autocomplete
- [ ] Compressed trie concepts

## Questions

- [ ] B1 — Implement Trie.
- [ ] I1 — Search prefix.
- [ ] I2 — Autocomplete.
- [ ] I3 — Word dictionary.
- [ ] A1 — Word search using Trie + DFS.
- [ ] A2 — Replace words using Trie.
- [ ] S1 — Optimize Trie memory usage.

---

# 3.8 Segment Tree

## Patterns

- [ ] Build
- [ ] Range query
- [ ] Point update
- [ ] Range update
- [ ] Lazy propagation
- [ ] Min query
- [ ] Max query
- [ ] Sum query

## Questions

- [ ] I1 — Range sum query.
- [ ] I2 — Range minimum query.
- [ ] A1 — Implement segment tree.
- [ ] A2 — Implement lazy propagation.
- [ ] A3 — Range update + range query.
- [ ] S1 — Decide between Segment Tree, Fenwick Tree and prefix sums.

---

# 3.9 Fenwick Tree / Binary Indexed Tree

## Patterns

- [ ] Prefix sum
- [ ] Point update
- [ ] Binary lifting concept
- [ ] Coordinate compression
- [ ] Range-query patterns

## Questions

- [ ] I1 — Implement Fenwick tree.
- [ ] I2 — Dynamic prefix sums.
- [ ] A1 — Inversion counting.
- [ ] A2 — Coordinate compression + Fenwick tree.
- [ ] S1 — Compare Fenwick Tree vs Segment Tree.

---

# 3.10 Heaps & Priority Queues

## Patterns

- [ ] Min heap
- [ ] Max heap
- [ ] Complete binary tree
- [ ] Array representation
- [ ] Sift up
- [ ] Sift down
- [ ] Heapify
- [ ] Build heap O(n)
- [ ] Heap sort
- [ ] Top-K

## Questions

### Basic

- [ ] B1 — Implement min heap.
- [ ] B2 — Implement max heap.
- [ ] B3 — Find kth largest.
- [ ] B4 — Find kth smallest.

### Intermediate

- [ ] I1 — Top K frequent elements.
- [ ] I2 — K closest points.
- [ ] I3 — Merge K sorted lists.
- [ ] I4 — Kth largest in stream.
- [ ] I5 — Running median.

### Advanced

- [ ] A1 — Median from data stream using two heaps.
- [ ] A2 — Sliding-window median.
- [ ] A3 — Task scheduler using priority queue.
- [ ] S1 — Explain why heapify is O(n), not O(n log n).

---

# Module 4 — Graph Theory

# 4.1 Graph Representation

## Patterns

- [ ] Adjacency matrix
- [ ] Adjacency list
- [ ] Edge list
- [ ] Weighted graph
- [ ] Directed graph
- [ ] Undirected graph
- [ ] Multigraph concepts

## Questions

- [ ] B1 — Convert edge list to adjacency list.
- [ ] B2 — Build adjacency matrix.
- [ ] I1 — Compare memory complexity.
- [ ] I2 — Choose representation for sparse graph.
- [ ] A1 — Design graph representation for millions of edges.

---

# 4.2 BFS

## Patterns

- [ ] Queue
- [ ] Visited set
- [ ] Level traversal
- [ ] Multi-source BFS
- [ ] Shortest path in unweighted graph
- [ ] State-space BFS

## Questions

### Basic

- [ ] B1 — BFS traversal.
- [ ] B2 — Number of connected nodes.
- [ ] B3 — Level order.

### Intermediate

- [ ] I1 — Shortest path in unweighted graph.
- [ ] I2 — Number of islands.
- [ ] I3 — Rotting oranges.
- [ ] I4 — Bipartite graph.
- [ ] I5 — Multi-source BFS.

### Advanced

- [ ] A1 — Word ladder.
- [ ] A2 — Shortest transformation sequence.
- [ ] A3 — State-space BFS.
- [ ] S1 — Design BFS for a graph too large to fit in memory.

---

# 4.3 DFS

## Patterns

- [ ] Recursive DFS
- [ ] Iterative DFS
- [ ] Connected components
- [ ] Cycle detection
- [ ] Path existence
- [ ] Backtracking relationship

## Questions

- [ ] B1 — DFS traversal.
- [ ] B2 — Count connected components.
- [ ] I1 — Detect cycle in undirected graph.
- [ ] I2 — Detect cycle in directed graph.
- [ ] I3 — Number of islands.
- [ ] I4 — Flood fill.
- [ ] A1 — Strongly connected components.
- [ ] A2 — Articulation points concepts.
- [ ] A3 — Bridges concepts.

---

# 4.4 Dijkstra

## Patterns

- [ ] Greedy relaxation
- [ ] Priority queue
- [ ] Distance array
- [ ] Stale priority-queue entries
- [ ] Non-negative edge weights
- [ ] Path reconstruction

## Questions

- [ ] I1 — Basic shortest path.
- [ ] I2 — Network delay.
- [ ] I3 — Cheapest route with constraints.
- [ ] A1 — Dijkstra with custom state.
- [ ] A2 — Multi-source variants.
- [ ] S1 — Explain why Dijkstra does not work with negative edges.

---

# 4.5 Bellman-Ford

## Patterns

- [ ] Relaxation
- [ ] V−1 iterations
- [ ] Negative-edge support
- [ ] Negative-cycle detection
- [ ] Early stopping

## Questions

- [ ] I1 — Shortest path with negative edges.
- [ ] A1 — Detect negative cycle.
- [ ] A2 — Compare Bellman-Ford and Dijkstra.
- [ ] S1 — Explain why Bellman-Ford is slower but more general.

---

# 4.6 Topological Sort

## Patterns

- [ ] DAG
- [ ] Kahn's algorithm
- [ ] Indegree
- [ ] Queue
- [ ] DFS topological ordering
- [ ] Cycle detection
- [ ] Dependency resolution

## Questions

- [ ] B1 — Topological ordering.
- [ ] I1 — Course schedule.
- [ ] I2 — Detect dependency cycle.
- [ ] I3 — Build dependency order.
- [ ] A1 — Alien dictionary.
- [ ] S1 — Design a build-system dependency resolver.

---

# 4.7 Minimum Spanning Tree

## Prim

- [ ] Priority queue
- [ ] Growing tree
- [ ] Cut property

## Kruskal

- [ ] Sort edges
- [ ] Union-Find
- [ ] Cycle avoidance

## Questions

- [ ] I1 — Implement Prim.
- [ ] I2 — Implement Kruskal.
- [ ] A1 — Minimum cost to connect points.
- [ ] A2 — Compare Prim and Kruskal.
- [ ] S1 — Select an MST algorithm for sparse vs dense graphs.

---

# 4.8 Union-Find / DSU

## Patterns

- [ ] Parent array
- [ ] Rank
- [ ] Size
- [ ] Path compression
- [ ] Union by rank
- [ ] Union by size
- [ ] Connected components
- [ ] Cycle detection

## Questions

- [ ] B1 — Implement DSU.
- [ ] I1 — Detect cycle.
- [ ] I2 — Number of connected components.
- [ ] I3 — Redundant connection.
- [ ] A1 — Dynamic connectivity.
- [ ] A2 — Kruskal MST.
- [ ] S1 — Explain inverse-Ackermann complexity intuitively.

---

# Module 5 — Algorithmic Paradigms & Patterns

# 5.1 Binary Search

## Patterns

- [ ] Exact search
- [ ] Lower bound
- [ ] Upper bound
- [ ] First occurrence
- [ ] Last occurrence
- [ ] Search rotated array
- [ ] Search with duplicates
- [ ] Binary search on answer

## Questions

### Basic

- [ ] B1 — Binary search.
- [ ] B2 — First occurrence.
- [ ] B3 — Last occurrence.
- [ ] B4 — Lower bound.
- [ ] B5 — Upper bound.

### Intermediate

- [ ] I1 — Search rotated sorted array.
- [ ] I2 — Find minimum in rotated array.
- [ ] I3 — Search insert position.
- [ ] I4 — Find peak element.

### Advanced

- [ ] A1 — Binary search on answer.
- [ ] A2 — Minimum capacity to ship packages.
- [ ] A3 — Allocate books.
- [ ] A4 — Aggressive cows / maximum minimum distance.
- [ ] S1 — Recognize hidden monotonicity in optimization problems.

---

# 5.2 Sorting

## Merge Sort

- [ ] Divide
- [ ] Merge
- [ ] Stability
- [ ] O(n log n)
- [ ] Auxiliary space

## Quick Sort

- [ ] Partition
- [ ] Pivot
- [ ] Lomuto
- [ ] Hoare
- [ ] Randomized pivot
- [ ] Worst case
- [ ] Stack depth

## Heap Sort

- [ ] Heap construction
- [ ] Repeated extraction
- [ ] In-place sorting

## Counting Sort

- [ ] Bounded integer domain
- [ ] Frequency array
- [ ] Stable variant

## Radix Sort

- [ ] Digit processing
- [ ] Stable inner sort
- [ ] Integer/string variants

## Questions

- [ ] B1 — Implement merge sort.
- [ ] B2 — Implement quicksort.
- [ ] I1 — Sort colors.
- [ ] I2 — Merge intervals.
- [ ] I3 — Count inversions.
- [ ] A1 — Implement stable sorting.
- [ ] A2 — Sort huge bounded integer data.
- [ ] S1 — Select a sorting algorithm based on data distribution, memory, stability and worst-case requirements.

---

# 5.3 Two Pointers

## Sub-Patterns

### Opposite Direction

- [ ] Sorted two-sum
- [ ] Palindrome
- [ ] Container with most water
- [ ] Partitioning

### Same Direction

- [ ] Fast/slow
- [ ] Remove duplicates
- [ ] In-place filtering
- [ ] Sliding boundary

## Questions

- [ ] B1 — Two Sum on sorted array.
- [ ] B2 — Remove duplicates.
- [ ] I1 — 3Sum.
- [ ] I2 — Container with most water.
- [ ] I3 — Move zeroes.
- [ ] A1 — 4Sum.
- [ ] A2 — Trapping rain water.
- [ ] S1 — Recognize when sorting enables a two-pointer solution.

---

# 5.4 Sliding Window

## Fixed Window

- [ ] Window initialization
- [ ] Add right
- [ ] Remove left
- [ ] Maintain invariant

## Variable Window

- [ ] Expand
- [ ] Check constraint
- [ ] Shrink
- [ ] Record answer

## Advanced

- [ ] Frequency map
- [ ] Character counts
- [ ] Monotonic deque
- [ ] At-most-K constraints
- [ ] Exactly-K via difference of at-most-K

## Questions

### Basic

- [ ] B1 — Maximum sum subarray of size K.
- [ ] B2 — Average of every window.
- [ ] B3 — Maximum element in fixed window.

### Intermediate

- [ ] I1 — Longest substring without repetition.
- [ ] I2 — Longest substring with at most K distinct characters.
- [ ] I3 — Minimum window substring.
- [ ] I4 — Permutation in string.

### Advanced

- [ ] A1 — Sliding-window maximum using deque.
- [ ] A2 — Subarrays with exactly K distinct values.
- [ ] A3 — Minimum window with weighted constraints.
- [ ] S1 — Identify whether a window condition is monotonic enough to shrink greedily.

---

# 5.5 Dynamic Programming

## DP Recognition

- [ ] Overlapping subproblems
- [ ] Optimal substructure
- [ ] State definition
- [ ] Transition
- [ ] Base case
- [ ] Iteration order
- [ ] Answer extraction
- [ ] Space optimization

## DP Sub-Patterns

### 1D DP

- [ ] Fibonacci
- [ ] Climbing stairs
- [ ] House robber
- [ ] Min cost climbing stairs
- [ ] Coin change

### 2D/Grid DP

- [ ] Grid paths
- [ ] Minimum path sum
- [ ] Obstacles
- [ ] LCS
- [ ] Edit distance

### Knapsack

- [ ] 0/1 knapsack
- [ ] Unbounded knapsack
- [ ] Subset sum
- [ ] Partition equal subset
- [ ] Target sum

### Sequence DP

- [ ] LIS
- [ ] LCS
- [ ] Edit distance
- [ ] Palindromic subsequences

### Interval DP

- [ ] Matrix-chain multiplication
- [ ] Burst balloons concepts
- [ ] Optimal interval splitting

### State-Machine DP

- [ ] Buy/sell stock
- [ ] Cooldown
- [ ] Transaction limits

## Questions

### Basic

- [ ] B1 — Fibonacci.
- [ ] B2 — Climbing stairs.
- [ ] B3 — House robber.
- [ ] B4 — Min cost climbing stairs.

### Intermediate

- [ ] I1 — Coin change.
- [ ] I2 — Unique paths.
- [ ] I3 — Minimum path sum.
- [ ] I4 — 0/1 knapsack.
- [ ] I5 — Partition equal subset sum.
- [ ] I6 — Longest increasing subsequence.

### Advanced

- [ ] A1 — LCS.
- [ ] A2 — Edit distance.
- [ ] A3 — Longest palindromic subsequence.
- [ ] A4 — Burst balloons.
- [ ] A5 — Stock trading state-machine DP.
- [ ] A6 — Interval DP.

### Senior

- [ ] S1 — Convert recursive memoized DP to iterative DP.
- [ ] S2 — Optimize 2D DP to O(n) memory.
- [ ] S3 — Explain why a proposed greedy solution fails and DP is required.
- [ ] S4 — Identify the smallest sufficient state.
- [ ] S5 — Detect when DP is actually unnecessary.

---

# 5.6 Greedy

## Patterns

- [ ] Greedy choice property
- [ ] Local optimum
- [ ] Exchange argument
- [ ] Sorting-based greedy
- [ ] Interval scheduling
- [ ] Activity selection
- [ ] Jump game
- [ ] Gas station
- [ ] Huffman coding
- [ ] Minimum platforms concepts

## Questions

- [ ] B1 — Assign cookies.
- [ ] I1 — Activity selection.
- [ ] I2 — Jump Game.
- [ ] I3 — Gas Station.
- [ ] I4 — Merge/choose intervals.
- [ ] A1 — Minimum number of arrows.
- [ ] A2 — Huffman coding.
- [ ] A3 — Weighted scheduling concepts.
- [ ] S1 — Prove the greedy choice using an exchange argument.

---

# 5.7 Backtracking

## Template

```text
backtrack(state):
    if complete:
        record answer
        return

    for choice in choices:
        if valid(choice):
            choose
            backtrack(next state)
            undo choice
```

## Patterns

- [ ] Permutations
- [ ] Combinations
- [ ] Subsets
- [ ] Duplicate handling
- [ ] Constraint checking
- [ ] Pruning
- [ ] Decision tree
- [ ] Bitmask optimization
- [ ] Constraint ordering

## Questions

### Basic

- [ ] B1 — Generate subsets.
- [ ] B2 — Generate permutations.
- [ ] B3 — Generate combinations.

### Intermediate

- [ ] I1 — Combination Sum.
- [ ] I2 — Letter combinations.
- [ ] I3 — Subsets with duplicates.
- [ ] I4 — Permutations with duplicates.

### Advanced

- [ ] A1 — N-Queens.
- [ ] A2 — Sudoku solver.
- [ ] A3 — Word Search.
- [ ] A4 — Partition into K subsets.
- [ ] S1 — Optimize backtracking with pruning and constraint ordering.

---

# Module 6 — Custom Data Structure Design

# 6.1 LRU Cache

## Required Structures

```text
HashMap<Key, Node>
        +
Doubly Linked List
```

## Required Operations

- [ ] get — O(1)
- [ ] put — O(1)
- [ ] remove
- [ ] move-to-front
- [ ] evict-from-tail

## Questions

- [ ] B1 — Implement basic LRU.
- [ ] I1 — Handle capacity.
- [ ] I2 — Handle updates.
- [ ] I3 — Add expiration.
- [ ] A1 — Thread-safe LRU.
- [ ] S1 — Explain synchronization strategy and contention.

---

# 6.2 LFU Cache

## Required Structures

```text
key → node
frequency → doubly linked list
minimum frequency
```

## Patterns

- [ ] Frequency tracking
- [ ] Recency tie-break
- [ ] O(1) get
- [ ] O(1) put
- [ ] Eviction
- [ ] Frequency-list cleanup

## Questions

- [ ] I1 — Implement LFU.
- [ ] A1 — O(1) LFU implementation.
- [ ] A2 — Correct tie-breaking.
- [ ] S1 — Compare LFU vs LRU workloads.
- [ ] S2 — Design concurrent LFU.

---

# 6.3 Rate Limiter

## Token Bucket

- [ ] Token generation
- [ ] Refill rate
- [ ] Capacity
- [ ] Burst handling
- [ ] Atomic updates

## Sliding Window

- [ ] Timestamp queue
- [ ] Counter
- [ ] Window expiration
- [ ] Distributed state

## Questions

- [ ] B1 — In-memory token bucket.
- [ ] I1 — Per-user limiter.
- [ ] I2 — Sliding-window limiter.
- [ ] A1 — Redis-backed limiter.
- [ ] A2 — Distributed atomic implementation.
- [ ] S1 — Design a rate limiter for multiple application instances.

---

# 6.4 Insert/Delete/GetRandom O(1)

## Structures

```text
HashMap<Value, Index>
        +
ArrayList<Value>
```

## Patterns

- [ ] Random access
- [ ] Swap-with-last deletion
- [ ] Index maintenance
- [ ] Duplicate handling

## Questions

- [ ] B1 — Implement unique values.
- [ ] I1 — Delete in O(1).
- [ ] A1 — Support duplicates.
- [ ] S1 — Explain why array deletion normally costs O(n) and how swap-with-last fixes it.

---

# 6.5 Time-Based Key-Value Store

## Structures

```text
Map<Key, Sorted Timestamped Values>
```

## Patterns

- [ ] Timestamp ordering
- [ ] Binary search
- [ ] Floor lookup
- [ ] Multiple versions
- [ ] Memory management

## Questions

- [ ] B1 — Basic set/get.
- [ ] I1 — Get latest value <= timestamp.
- [ ] I2 — Binary search optimization.
- [ ] A1 — Multiple keys and large history.
- [ ] S1 — Design memory/retention policies.

---

# 6.6 In-Memory File System

## Structures

```text
Trie / Tree
   +
HashMap<String, Node>
```

## Patterns

- [ ] Directory node
- [ ] File node
- [ ] Path parsing
- [ ] Directory creation
- [ ] File content
- [ ] Listing
- [ ] Prefix traversal

## Questions

- [ ] I1 — Implement directory creation.
- [ ] I2 — Implement file creation.
- [ ] I3 — Read/write file.
- [ ] I4 — List directory.
- [ ] A1 — Support large files.
- [ ] A2 — Add permissions metadata.
- [ ] S1 — Design thread-safe concurrent access.

---

# 6.7 Thread-Safe Blocking Queue

## Core Structures

- [ ] Circular array or linked queue
- [ ] Lock
- [ ] Condition
- [ ] `notEmpty`
- [ ] `notFull`

## Patterns

```text
Producer
   ↓
put()
   ↓
wait if full
   ↓
signal consumers

Consumer
   ↓
take()
   ↓
wait if empty
   ↓
signal producers
```

## Questions

- [ ] I1 — Implement basic blocking queue.
- [ ] I2 — Add capacity.
- [ ] A1 — Use `ReentrantLock` + `Condition`.
- [ ] A2 — Handle interruption.
- [ ] A3 — Avoid lost notifications.
- [ ] S1 — Explain fairness vs throughput.
- [ ] S2 — Compare intrinsic locking vs `ReentrantLock`.
- [ ] S3 — Analyze contention and false sharing considerations.

---

# Cross-Module DSA Patterns You Must Master

These patterns repeatedly appear across interview and production problems.

## Pattern 1 — Frequency Map

Used for:

- [ ] Anagrams
- [ ] Duplicate detection
- [ ] Counting
- [ ] Sliding windows
- [ ] Top-K
- [ ] Deduplication

---

## Pattern 2 — Fast & Slow Pointers

Used for:

- [ ] Cycle detection
- [ ] Middle node
- [ ] Linked-list partitioning
- [ ] Duplicate-number problems

---

## Pattern 3 — Two Pointers

Used for:

- [ ] Sorted arrays
- [ ] Pair sums
- [ ] 3Sum
- [ ] Palindromes
- [ ] In-place filtering

---

## Pattern 4 — Sliding Window

Used for:

- [ ] Substrings
- [ ] Subarrays
- [ ] Frequency constraints
- [ ] Fixed-size windows
- [ ] Variable-size windows

---

## Pattern 5 — Prefix Sum

Used for:

- [ ] Range queries
- [ ] Subarray sums
- [ ] Difference arrays
- [ ] 2D matrix queries

---

## Pattern 6 — Monotonic Stack

Used for:

- [ ] Next greater element
- [ ] Next smaller element
- [ ] Histogram
- [ ] Stock span
- [ ] Temperature problems

---

## Pattern 7 — Monotonic Queue

Used for:

- [ ] Sliding maximum
- [ ] Sliding minimum
- [ ] Window optimization

---

## Pattern 8 — Binary Search on Answer

Recognition:

- [ ] Answer lies in ordered numeric range
- [ ] Feasibility can be checked
- [ ] Feasibility is monotonic

Template:

```text
low = minimum possible answer
high = maximum possible answer

while low <= high:
    mid = ...
    if feasible(mid):
        move toward better answer
    else:
        move away
```

---

## Pattern 9 — DFS + Backtracking

Used for:

- [ ] Permutations
- [ ] Combinations
- [ ] Subsets
- [ ] Sudoku
- [ ] N-Queens
- [ ] Path exploration

---

## Pattern 10 — BFS

Used for:

- [ ] Shortest unweighted path
- [ ] Level traversal
- [ ] Multi-source propagation
- [ ] State transitions

---

## Pattern 11 — DFS + Memoization

Used for:

- [ ] DAG DP
- [ ] Grid paths
- [ ] State-space optimization
- [ ] Recursive DP

---

## Pattern 12 — Heap / Top-K

Used for:

- [ ] Kth largest
- [ ] Kth smallest
- [ ] Top K frequency
- [ ] Merge K sorted streams
- [ ] Running median

---

## Pattern 13 — Union-Find

Used for:

- [ ] Connectivity
- [ ] Cycle detection
- [ ] MST
- [ ] Dynamic connectivity

---

## Pattern 14 — Trie

Used for:

- [ ] Prefix search
- [ ] Autocomplete
- [ ] Dictionary matching
- [ ] Word search

---

## Pattern 15 — Interval Problems

Sub-patterns:

- [ ] Sort by start
- [ ] Sort by end
- [ ] Merge intervals
- [ ] Detect overlap
- [ ] Sweep line
- [ ] Difference events
- [ ] Priority queue for active intervals

Questions:

- [ ] B1 — Merge intervals.
- [ ] I1 — Meeting rooms.
- [ ] I2 — Meeting rooms II.
- [ ] I3 — Insert interval.
- [ ] A1 — Minimum rooms for dynamic events.
- [ ] A2 — Sweep-line interval aggregation.

---

## Pattern 16 — Prefix/Suffix Precomputation

Used for:

- [ ] Product except self
- [ ] Range calculations
- [ ] Left/right maxima
- [ ] Trapping rain water

---

## Pattern 17 — State Compression

- [ ] Bitmask
- [ ] Small-state encoding
- [ ] Boolean-state compression
- [ ] DP bitmasking

Questions:

- [ ] I1 — Subset representation.
- [ ] A1 — Traveling Salesman DP concepts.
- [ ] A2 — Assignment-state DP.

---

# DSA Recognition Cheat Sheet

| If you see... | Think... |
|---|---|
| Sorted data + search | Binary Search |
| Sorted array + pair/triple | Two Pointers |
| Contiguous subarray/substring | Sliding Window |
| Range sum | Prefix Sum |
| Range update | Difference Array |
| Next greater/smaller | Monotonic Stack |
| Sliding max/min | Monotonic Deque |
| Top K | Heap |
| Repeated connectivity | Union-Find |
| Prefix matching | Trie |
| Shortest unweighted path | BFS |
| Weighted non-negative shortest path | Dijkstra |
| Negative edges | Bellman-Ford |
| Dependencies | Topological Sort |
| Connect all nodes cheaply | MST |
| Overlapping subproblems | DP |
| All combinations/permutations | Backtracking |
| Ordered dynamic range query | Segment Tree/Fenwick |
| Need O(1) random + delete | HashMap + ArrayList |
| Need O(1) recency cache | HashMap + Doubly Linked List |

---

# Senior-Level Implementation Requirements

You should be able to implement from scratch without library assistance:

## Data Structures

- [ ] Dynamic Array
- [ ] Singly Linked List
- [ ] Doubly Linked List
- [ ] Stack
- [ ] Queue
- [ ] Deque
- [ ] HashMap
- [ ] HashSet
- [ ] Binary Tree
- [ ] BST
- [ ] AVL Tree
- [ ] Heap
- [ ] Trie
- [ ] Union-Find
- [ ] Segment Tree
- [ ] Fenwick Tree
- [ ] Graph representation
- [ ] LRU Cache
- [ ] LFU Cache
- [ ] Blocking Queue

## Algorithms

- [ ] Binary Search
- [ ] Merge Sort
- [ ] Quick Sort
- [ ] Heap Sort
- [ ] Counting Sort
- [ ] Radix Sort
- [ ] BFS
- [ ] DFS
- [ ] Dijkstra
- [ ] Bellman-Ford
- [ ] Topological Sort
- [ ] Prim
- [ ] Kruskal
- [ ] Backtracking
- [ ] Core DP patterns
- [ ] Greedy algorithms

---

# Recommended Question Progression

## Stage 1 — Foundation

Solve approximately:

- [ ] 20 array problems
- [ ] 15 string problems
- [ ] 15 linked-list problems
- [ ] 10 stack/queue problems
- [ ] 15 hashing problems
- [ ] 15 tree problems

Goal:

> Recognize basic data structures and implement their standard operations quickly.

---

# Stage 2 — Core Interview Patterns

Solve:

- [ ] 15 binary-search problems
- [ ] 15 two-pointer problems
- [ ] 15 sliding-window problems
- [ ] 10 monotonic-stack problems
- [ ] 10 heap problems
- [ ] 15 BFS/DFS problems
- [ ] 10 Union-Find problems

Goal:

> Recognize the pattern before writing code.

---

# Stage 3 — Advanced Algorithms

Solve:

- [ ] 15 graph problems
- [ ] 20 DP problems
- [ ] 10 greedy problems
- [ ] 15 backtracking problems
- [ ] 10 advanced tree problems
- [ ] 10 range-query problems

Goal:

> Handle unfamiliar variations instead of memorizing solutions.

---

# Stage 4 — Senior DSA

Implement:

- [ ] LRU
- [ ] LFU
- [ ] Rate limiter
- [ ] Blocking queue
- [ ] Time-based KV store
- [ ] In-memory filesystem
- [ ] Randomized O(1) structure

Then answer:

- [ ] What is the invariant?
- [ ] Why is each operation O(1)/O(log n)?
- [ ] What happens under concurrency?
- [ ] What happens with malformed input?
- [ ] What is the memory overhead?
- [ ] What changes at 10x scale?

---

# Final Mastery Checklist

## Complexity

- [ ] Derive time complexity without guessing.
- [ ] Derive auxiliary space.
- [ ] Explain amortized complexity.
- [ ] Solve common recurrences.

## Data Structures

- [ ] Know the invariant.
- [ ] Know implementation internals.
- [ ] Know operation complexity.
- [ ] Know memory trade-offs.
- [ ] Know when not to use the structure.

## Algorithms

- [ ] Recognize patterns.
- [ ] Write brute force first.
- [ ] Identify bottleneck.
- [ ] Optimize systematically.
- [ ] Prove correctness.
- [ ] Analyze complexity.
- [ ] Test edge cases.

## Senior Level

- [ ] Implement core structures from scratch.
- [ ] Design custom structures by combining primitives.
- [ ] Explain trade-offs.
- [ ] Handle concurrency where applicable.
- [ ] Handle large input.
- [ ] Avoid unnecessary asymptotic optimization.
- [ ] Consider memory locality.
- [ ] Consider allocation/GC overhead in Java.
- [ ] Know when a standard library implementation is preferable.

---

# Suggested Reference Books

## 1. Primary DSA Book

### *Introduction to Algorithms (CLRS)*
**Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein**

Best for:

- Complexity
- Sorting
- Trees
- Graphs
- Dynamic programming
- Greedy algorithms
- Formal algorithm analysis

Use as the **theory/reference book**, not necessarily as the first book for every coding problem.

---

## 2. Practical Interview DSA

### *Cracking the Coding Interview*
**Gayle Laakmann McDowell**

Best for:

- Interview patterns
- Problem-solving process
- Arrays
- Linked lists
- Trees
- Graphs
- Recursion
- Dynamic programming

---

## 3. Java-Specific Data Structures

### *Algorithms, 4th Edition*
**Robert Sedgewick & Kevin Wayne**

Best for:

- Algorithms
- Data structures
- Sorting
- Searching
- Graph algorithms
- Java implementations

---

## 4. Advanced Algorithms

### *The Algorithm Design Manual*
**Steven S. Skiena**

Best for:

- Algorithmic thinking
- Problem classification
- Graph algorithms
- Greedy techniques
- Dynamic programming
- Practical problem solving

---

# Recommended Book by Module

| Module | Primary Book | Supplement |
|---|---|---|
| 1. Complexity | **CLRS** | The Algorithm Design Manual |
| 2. Fundamental Structures | **Algorithms, 4th Edition** | Cracking the Coding Interview |
| 3. Hashing & Trees | **Algorithms, 4th Edition** | CLRS |
| 4. Graph Theory | **CLRS** | The Algorithm Design Manual |
| 5. Algorithmic Patterns | **The Algorithm Design Manual** | CLRS |
| 6. Custom Data Structures | **Cracking the Coding Interview** | Algorithms, 4th Edition |

---

# Best Learning Combination

Do not try to read every book cover-to-cover.

Use:

```text
CLRS
  ↓
Theory + correctness + complexity

Algorithms, 4th Edition
  ↓
Java implementations + practical algorithms

The Algorithm Design Manual
  ↓
Problem recognition + algorithm selection

Cracking the Coding Interview
  ↓
Interview-oriented problem practice
```

Then use a problem platform for volume practice.

---

# Final Senior DSA Standard

You have reached senior-level DSA mastery when you can take an unfamiliar problem and:

```text
Understand requirements
        ↓
Identify constraints
        ↓
Estimate brute-force complexity
        ↓
Find the bottleneck
        ↓
Recognize a pattern
        ↓
Select data structure
        ↓
Develop optimized algorithm
        ↓
Prove correctness
        ↓
Analyze time + space
        ↓
Test edge cases
        ↓
Implement cleanly
        ↓
Explain trade-offs
```

The goal is **not** to memorize hundreds of solutions.

The goal is to recognize that many apparently different problems reduce to a relatively small number of reusable patterns.
