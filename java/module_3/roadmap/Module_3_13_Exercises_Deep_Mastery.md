# Module 3.13 — Collections Exercises Deep Mastery

> **Goal:** Convert Collections Framework knowledge into implementation skill, internal understanding, debugging ability, performance analysis, and production-level design judgment.

---

# Mastery Cycle

For every exercise, follow the complete cycle:

1. [ ] Understand the problem
2. [ ] Identify the required data structures
3. [ ] Identify expected complexity
4. [ ] Design the solution
5. [ ] Implement a basic version
6. [ ] Test normal cases
7. [ ] Test edge cases
8. [ ] Explain internal working
9. [ ] Analyze memory/runtime behavior
10. [ ] Measure performance
11. [ ] Identify common mistakes
12. [ ] Debug failures
13. [ ] Optimize the implementation
14. [ ] Discuss production trade-offs
15. [ ] Answer interview questions
16. [ ] Implement an advanced version

## Completion Standard

> **Explain → Implement → Explain internals → Handle edge cases → Discuss trade-offs → Debug it → Benchmark it → Use it in a production scenario**

---

# 3.13.1 Exercise 1 — Implement a Simplified HashMap

## Objective

Build a simplified HashMap from scratch to understand:

```text
hashing
    ↓
hash spreading
    ↓
bucket calculation
    ↓
collision handling
    ↓
key comparison
    ↓
insert
    ↓
lookup
    ↓
remove
    ↓
resize
```

---

## Part A — Basic Requirements

Implement:

```java
class SimpleHashMap<K, V> {

    void put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    int size();

    boolean isEmpty();

    void clear();
}
```

---

## Part B — Internal Structure

Start with:

```text
Node<K,V>[]
```

Each node should contain:

```text
hash
key
value
next
```

Conceptually:

```text
table
 ├── bucket 0 → Node → Node
 ├── bucket 1 → null
 ├── bucket 2 → Node
 ├── bucket 3 → Node → Node → Node
 └── ...
```

---

## Part C — Hashing

Implement:

```text
key.hashCode()
        ↓
hash spreading
        ↓
bucket index
```

Understand why hash tables try to distribute keys evenly.

---

## Part D — Collision Handling

Implement separate chaining:

```text
bucket
  ↓
Node → Node → Node
```

Two different keys must be able to occupy the same bucket.

Test:

```text
different keys
same hashCode()
```

---

## Part E — Equality

Correct lookup must use:

```java
hashCode()
```

and then:

```java
equals()
```

Do not rely on `==` for logical key equality.

---

## Part F — Null Keys

Decide and document whether your implementation supports:

```java
put(null, value);
```

If supported, explicitly design how null is hashed and located.

---

## Part G — Resize

Add:

```text
initial capacity
load factor
threshold
resize
```

For example:

```text
capacity = 16
load factor = 0.75
threshold = 12
```

When size exceeds the threshold:

```text
old table
    ↓
larger table
    ↓
redistribute entries
```

---

## Part H — Advanced HashMap Exercise

Add:

- [ ] Configurable initial capacity.
- [ ] Configurable load factor.
- [ ] `putIfAbsent()`.
- [ ] `replace()`.
- [ ] `computeIfAbsent()`.
- [ ] `computeIfPresent()`.
- [ ] `forEach()`.
- [ ] Entry iteration.
- [ ] Fail-fast iterator using `modCount`.
- [ ] Collision statistics.
- [ ] Capacity inspection for debugging.

---

## Part I — HashMap Edge Cases

Test:

- [ ] Empty map.
- [ ] One entry.
- [ ] Duplicate key.
- [ ] Duplicate value.
- [ ] Null key.
- [ ] Null value.
- [ ] Multiple keys with identical hash codes.
- [ ] Mutable key.
- [ ] Resize boundary.
- [ ] Large number of entries.
- [ ] Remove first node in bucket.
- [ ] Remove middle node.
- [ ] Remove last node.
- [ ] Reinsert removed key.

---

## Part J — Complexity

Explain:

```text
put()
get()
remove()
containsKey()
resize()
iteration
```

Expected discussion:

```text
average/expected lookup → O(1)
collision-heavy behavior → depends on collision handling
resize → O(N)
iteration → O(capacity + size) for a hash-table-style implementation
```

---

## Part K — Production Discussion

Answer:

- [ ] Why is HashMap not automatically thread-safe?
- [ ] Why does load factor matter?
- [ ] Why can excessive collisions hurt performance?
- [ ] Why should keys generally be immutable?
- [ ] Why does resizing cause latency spikes?
- [ ] Why is pre-sizing sometimes useful?
- [ ] Why can over-sizing waste memory?

---

# 3.13.2 Exercise 2 — Build an LRU Cache

## Objective

Implement:

```text
Least Recently Used Cache
```

with:

```text
O(1) average get()
O(1) average put()
```

---

## Required Data Structures

Use:

```text
HashMap
+
Doubly Linked List
```

Architecture:

```text
HashMap<K, Node>
        │
        ↓
Node ↔ Node ↔ Node ↔ Node
HEAD                  TAIL
MRU                    LRU
```

---

## Part A — API

Implement:

```java
class LRUCache<K, V> {

    V get(K key);

    void put(K key, V value);

    int size();
}
```

---

## Part B — Behavior

When an item is accessed:

```text
get(key)
   ↓
move node to MRU position
```

When a new item exceeds capacity:

```text
remove LRU
```

---

## Part C — Example

Capacity:

```text
3
```

Operations:

```text
put(A, 1)
put(B, 2)
put(C, 3)
```

State:

```text
C → B → A
```

Access:

```text
get(A)
```

State:

```text
A → C → B
```

Then:

```text
put(D, 4)
```

Evict:

```text
B
```

Final:

```text
D → A → C
```

---

## Part D — Required Operations

Implement:

- [ ] Add new entry.
- [ ] Update existing entry.
- [ ] Get existing entry.
- [ ] Get missing entry.
- [ ] Move accessed entry to MRU.
- [ ] Evict LRU.
- [ ] Remove entry.
- [ ] Clear cache.

---

## Part E — Complexity

Prove:

```text
get()  → O(1) average
put()  → O(1) average
remove → O(1)
```

Explain why using only:

```text
HashMap
```

is insufficient for O(1) LRU eviction ordering.

---

## Part F — Edge Cases

Test:

- [ ] Capacity = 1.
- [ ] Capacity > number of entries.
- [ ] Updating an existing key.
- [ ] Repeated get().
- [ ] Repeated put().
- [ ] Missing key.
- [ ] Duplicate key.
- [ ] Null key if supported.
- [ ] Null value if supported.
- [ ] Empty cache.
- [ ] Clear and reuse.

---

## Part G — Advanced LRU

Implement:

- [ ] Thread-safe LRU cache.
- [ ] TTL support.
- [ ] Maximum memory-based capacity.
- [ ] Cache statistics.
- [ ] Hit count.
- [ ] Miss count.
- [ ] Eviction count.
- [ ] Concurrent access testing.

---

## Part H — Production Discussion

Compare:

```text
custom LRU
vs.
LinkedHashMap access-order
vs.
production caching library
```

Explain when a custom implementation is inappropriate.

---

# 3.13.3 Exercise 3 — Priority Queue Based Problem

## Objective

Master:

```text
Heap
+
PriorityQueue
+
custom Comparator
+
Top-K
+
Kth element
```

---

## Part A — Basic PriorityQueue

Implement a program that processes:

```text
tasks
```

according to priority.

Example:

```text
HIGH
MEDIUM
LOW
```

The highest-priority task must execute first.

---

## Part B — Custom Objects

Create:

```java
class Task {
    String name;
    int priority;
    long createdAt;
}
```

Sort using:

```text
priority descending
+
createdAt ascending
```

Use:

```java
PriorityQueue<Task>
```

---

## Part C — Top-K Problem

Given:

```text
N numbers
```

find:

```text
K largest values
```

using:

```text
Min-Heap of size K
```

Target:

```text
O(N log K)
```

rather than:

```text
O(N log N)
```

---

## Part D — Kth Largest

Implement:

```text
findKthLargest(array, K)
```

using a priority queue.

Analyze:

```text
time
space
```

---

## Part E — Merge K Sorted Lists

Given:

```text
K sorted lists
```

merge them into one sorted list using:

```text
PriorityQueue
```

Target:

```text
O(N log K)
```

---

## Part F — Edge Cases

Test:

- [ ] K = 1.
- [ ] K = N.
- [ ] K > N.
- [ ] Empty input.
- [ ] Duplicate values.
- [ ] Negative values.
- [ ] Already sorted input.
- [ ] Reverse sorted input.
- [ ] Large K.
- [ ] Large N.

---

## Part G — Advanced Priority Queue

Implement a simplified binary heap yourself:

```text
array-backed heap
```

Support:

```text
offer()
peek()
poll()
size()
isEmpty()
```

Understand:

```text
parent
left child
right child
sift-up
sift-down
heapify
```

---

# 3.13.4 Exercise 4 — Compare Collection Performance

## Objective

Move beyond theoretical Big-O and measure actual Java performance.

Compare:

```text
ArrayList
LinkedList
HashMap
TreeMap
HashSet
TreeSet
ArrayDeque
LinkedList
ConcurrentHashMap
synchronizedMap
```

---

## Part A — Operations

Benchmark:

```text
insert
lookup
remove
iteration
contains
random access
```

---

## Part B — Input Sizes

Test:

```text
N = 1,000
N = 10,000
N = 100,000
N = 1,000,000
```

Adjust sizes when memory limits require it.

---

## Part C — Workloads

Test:

### Sequential workload

```text
add everything
then read everything
```

### Random workload

```text
random insert
random lookup
random remove
```

### Read-heavy workload

```text
90% reads
10% writes
```

### Write-heavy workload

```text
90% writes
10% reads
```

### Concurrent workload

```text
1
2
4
8
16
32
threads
```

---

## Part D — Metrics

Measure:

```text
throughput
latency
allocation
memory usage
GC activity
CPU usage
```

For concurrent tests also measure:

```text
contention
scalability
```

---

## Part E — Benchmark Correctly

Use:

```text
JMH — Java Microbenchmark Harness
```

Understand:

- [ ] Warmup iterations.
- [ ] Measurement iterations.
- [ ] Forks.
- [ ] JVM JIT compilation.
- [ ] Dead-code elimination.
- [ ] Constant folding.
- [ ] Garbage collection.
- [ ] Different input sizes.
- [ ] Realistic workloads.

Do not conclude collection performance from a single `System.nanoTime()` loop.

---

## Part F — Performance Questions

Explain:

- [ ] Why can ArrayList beat LinkedList despite different theoretical insertion characteristics?
- [ ] Why does cache locality matter?
- [ ] Why does node allocation increase GC pressure?
- [ ] Why can HashMap outperform TreeMap for ordinary lookup?
- [ ] When is TreeMap worth the extra cost?
- [ ] Why can ConcurrentHashMap scale better than a synchronized wrapper?
- [ ] Why can O(1) still be slow?
- [ ] Why can an O(log N) algorithm sometimes outperform an O(N) algorithm at realistic input sizes?

---

# 3.13.5 Exercise 5 — Debug Mutable-Key HashMap Failure

## Objective

Understand one of the most important HashMap correctness failures.

Create a key:

```java
class EmployeeKey {

    private String employeeId;

    EmployeeKey(String employeeId) {
        this.employeeId = employeeId;
    }

    void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        return employeeId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EmployeeKey other)) {
            return false;
        }

        return employeeId.equals(other.employeeId);
    }
}
```

---

## Part A — Reproduce the Failure

Execute:

```java
EmployeeKey key = new EmployeeKey("E100");

Map<EmployeeKey, String> map = new HashMap<>();

map.put(key, "Alice");

key.setEmployeeId("E200");

System.out.println(map.get(key));
System.out.println(map.containsKey(key));
```

Investigate the result.

---

## Part B — Explain What Happened

Trace:

```text
put()
 ↓
hashCode("E100")
 ↓
bucket selection
 ↓
entry stored
```

Then:

```text
mutate key
 ↓
hashCode("E200")
 ↓
different bucket calculation
 ↓
lookup searches a different location
```

The entry still exists in the table, but ordinary lookup may no longer find it.

---

## Part C — Debugging Exercise

Use:

```text
debugger
+
hashCode()
+
equals()
+
collection inspection
```

Answer:

- [ ] Where was the entry originally stored?
- [ ] What was its original hash?
- [ ] What is the new hash?
- [ ] Why does `get()` fail?
- [ ] Why can iteration still reveal the entry?
- [ ] Why does `remove()` potentially fail?
- [ ] What contract was violated conceptually?

---

## Part D — Fix the Design

Implement an immutable key.

Requirements:

- [ ] Make the class `final` where appropriate.
- [ ] Make identity fields `private final`.
- [ ] Initialize identity fields in the constructor.
- [ ] Remove mutators.
- [ ] Implement `equals()`.
- [ ] Implement `hashCode()`.

Test again.

---

## Part E — Advanced Variations

Investigate:

- [ ] Mutable String-like key.
- [ ] Mutable list used as a key.
- [ ] Mutable object nested inside a key.
- [ ] Key whose `equals()` changes behavior.
- [ ] Key whose `hashCode()` is inconsistent with `equals()`.
- [ ] Key with constant hash code.
- [ ] Expensive hashCode().
- [ ] Expensive equals().

---

# 3.13.6 Integrated Exercise — Build a Mini Collections Library

Combine the previous exercises.

Implement:

```text
SimpleHashMap
SimpleHashSet
SimplePriorityQueue
LRUCache
```

Use only primitive building blocks where appropriate:

```text
array
+
linked nodes
+
hashing
+
heap
```

Avoid using the corresponding Java collection internally for the core implementation.

---

# 3.13.7 Integrated Exercise — Collection Selection Challenge

For each scenario, choose the collection and justify the choice.

## Scenario 1

Need:

```text
fast random access
10 million elements
frequent iteration
```

Choose:

```text
?
```

Explain memory locality and access complexity.

---

## Scenario 2

Need:

```text
FIFO processing
high throughput
```

Choose:

```text
?
```

Compare:

```text
ArrayDeque
vs.
LinkedList
```

---

## Scenario 3

Need:

```text
unique values
fast membership
no ordering requirement
```

Choose:

```text
?
```

---

## Scenario 4

Need:

```text
sorted keys
range queries
```

Choose:

```text
?
```

---

## Scenario 5

Need:

```text
concurrent reads/writes
high thread count
```

Choose:

```text
?
```

Compare:

```text
ConcurrentHashMap
vs.
synchronizedMap
```

---

## Scenario 6

Need:

```text
top 100 values
from millions of records
```

Choose:

```text
?
```

Explain:

```text
O(N log K)
```

---

# 3.13.8 Complexity Verification Exercises

For every implementation, create a table:

| Operation | Expected Complexity | Measured Result | Explanation |
|---|---:|---:|---|
| `put` | | | |
| `get` | | | |
| `remove` | | | |
| `contains` | | | |
| iteration | | | |
| resize | | | |

Explain any difference between theory and measurement.

---

# 3.13.9 Memory Analysis Exercise

For each implementation estimate:

```text
number of objects
+
number of references
+
array capacity
+
node overhead
+
unused capacity
```

Compare:

```text
ArrayList
LinkedList
HashMap
TreeMap
LRU Cache
PriorityQueue
```

Then investigate actual memory usage using appropriate JVM profiling tools.

---

# 3.13.10 Thread-Safety Extension

Make the LRU cache thread-safe.

Compare approaches:

```text
synchronized methods
+
synchronized blocks
+
ReentrantLock
+
ConcurrentHashMap
```

Discuss why replacing the HashMap with ConcurrentHashMap alone does not automatically make the entire LRU algorithm thread-safe.

Analyze the atomicity of:

```text
get()
+
move-to-MRU
+
eviction
```

as a compound operation.

---

# 3.13.11 Testing Requirements

For every exercise create:

### Unit Tests

- [ ] Normal cases.
- [ ] Boundary cases.
- [ ] Empty input.
- [ ] Duplicate values.
- [ ] Null behavior.
- [ ] Invalid arguments.
- [ ] Large inputs.

### Property Tests

Verify invariants.

For HashMap:

```text
put(k,v)
then get(k)
returns v
```

For LRU:

```text
capacity never exceeded
```

For PriorityQueue:

```text
poll() returns according to priority
```

---

# 3.13.12 Interview Questions

## Basic

- [ ] How does HashMap work at a high level?
- [ ] Why are collisions possible?
- [ ] What is an LRU cache?
- [ ] Why does an LRU cache need two data structures?
- [ ] What is a priority queue?
- [ ] What is a heap?
- [ ] What is the complexity of heap insertion?
- [ ] What is the complexity of heap removal?
- [ ] Why is ArrayList usually preferred over LinkedList?

## Intermediate

- [ ] Implement HashMap from scratch.
- [ ] Explain collision handling.
- [ ] Explain HashMap resizing.
- [ ] Design an O(1) LRU cache.
- [ ] Explain why HashMap alone cannot implement O(1) LRU eviction.
- [ ] Find K largest elements using a heap.
- [ ] Merge K sorted lists.
- [ ] Compare HashMap and TreeMap.
- [ ] Compare ArrayDeque and LinkedList.

## Advanced

- [ ] Design a simplified HashMap with resizing.
- [ ] Explain how poor hash distribution affects performance.
- [ ] Explain why mutable keys are dangerous.
- [ ] Explain how to make an LRU cache thread-safe.
- [ ] Explain why ConcurrentHashMap does not automatically make a compound algorithm atomic.
- [ ] Design an LFU cache as a follow-up.
- [ ] Benchmark collection implementations using JMH.
- [ ] Explain why benchmark results can be misleading.

## Senior / Production

- [ ] How would you select a collection for a high-throughput service?
- [ ] How would you diagnose unexpectedly high HashMap latency?
- [ ] How would you investigate memory overhead from millions of collection entries?
- [ ] How would you diagnose GC pressure caused by LinkedList?
- [ ] How would you design a production-grade LRU cache?
- [ ] When would you avoid implementing your own cache?
- [ ] How would you benchmark a collection change before deploying it?
- [ ] How would you detect a mutable-key bug in production?
- [ ] How would you prove a collection is responsible for a latency regression?
- [ ] How would you design collection behavior under concurrent access?

---

# 3.13.13 Advanced Follow-Ups

After completing all exercises, study:

- [ ] OpenJDK `HashMap` source.
- [ ] OpenJDK `LinkedHashMap` source.
- [ ] OpenJDK `PriorityQueue` source.
- [ ] OpenJDK `ArrayDeque` source.
- [ ] OpenJDK `ConcurrentHashMap` source.
- [ ] HashMap tree bins.
- [ ] Red-black trees.
- [ ] Heapify algorithms.
- [ ] Amortized dynamic-array growth.
- [ ] CPU cache locality.
- [ ] Object allocation.
- [ ] Garbage collection effects.
- [ ] JIT optimizations.
- [ ] Escape analysis.
- [ ] Concurrent algorithms.
- [ ] CAS and contention.
- [ ] Lock granularity.
- [ ] False sharing.
- [ ] JMH benchmarking.
- [ ] Production profiling.

---

# 3.13.14 Final Mastery Gate

## Simplified HashMap

- [ ] Explain hashing.
- [ ] Implement bucket lookup.
- [ ] Implement collision handling.
- [ ] Implement `put()`.
- [ ] Implement `get()`.
- [ ] Implement `remove()`.
- [ ] Implement resizing.
- [ ] Explain load factor.
- [ ] Explain complexity.
- [ ] Handle edge cases.
- [ ] Debug collision issues.

## LRU Cache

- [ ] Explain LRU semantics.
- [ ] Implement HashMap + doubly linked list.
- [ ] Achieve O(1) average get.
- [ ] Achieve O(1) average put.
- [ ] Implement eviction.
- [ ] Handle capacity edge cases.
- [ ] Make a version thread-safe.
- [ ] Explain production trade-offs.

## Priority Queue

- [ ] Explain heap structure.
- [ ] Use Java PriorityQueue.
- [ ] Implement a heap.
- [ ] Solve Top-K.
- [ ] Solve Kth-largest.
- [ ] Merge K sorted lists.
- [ ] Analyze O(N log K).

## Performance Comparison

- [ ] Compare major collections.
- [ ] Benchmark using JMH.
- [ ] Explain cache locality.
- [ ] Explain allocation.
- [ ] Explain GC.
- [ ] Explain contention.
- [ ] Interpret benchmark results.

## Mutable-Key Debugging

- [ ] Reproduce the failure.
- [ ] Explain hash bucket behavior.
- [ ] Inspect the key with a debugger.
- [ ] Explain equals/hashCode interaction.
- [ ] Fix the design using immutability.
- [ ] Explain the production risk.

---

# Final Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] DEBUGGED
- [ ] BENCHMARKED
- [ ] INTERNALS UNDERSTOOD
- [ ] COMPLEXITY MASTERED
- [ ] MEMORY BEHAVIOR UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PRODUCTION TRADE-OFFS UNDERSTOOD
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
