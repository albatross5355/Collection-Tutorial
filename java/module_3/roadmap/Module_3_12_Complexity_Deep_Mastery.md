# Module 3.12 — Collections Complexity & Performance Deep Mastery

> **Goal:** Master the time and space complexity of Java Collections, understand average vs worst-case behavior, amortized analysis, memory overhead, cache locality, allocation and GC effects, concurrency costs, and make production-level collection choices based on workload rather than Big-O alone.

---

# Mastery Cycle

For every topic, complete:

1. [ ] What is it?
2. [ ] Why does Java have it?
3. [ ] Syntax and API
4. [ ] Basic example
5. [ ] Internal working
6. [ ] Memory / runtime behavior
7. [ ] Edge cases
8. [ ] Common mistakes
9. [ ] Performance implications
10. [ ] Production use cases
11. [ ] Interview questions
12. [ ] Coding exercises
13. [ ] Advanced follow-ups

## Completion Standard

> **Explain → Implement → Explain internals → Handle edge cases → Discuss trade-offs → Debug it → Use it in a production scenario**

---

# 3.12.1 Complexity Fundamentals

## What Is Time Complexity?

Time complexity describes how an operation's computational work grows as input size `N` increases.

Common classes:

```text
O(1)
O(log N)
O(N)
O(N log N)
O(N²)
```

Understand that Big-O describes growth rate, not literal execution time.

---

# 3.12.2 Space Complexity

Space complexity describes additional memory requirements as input size grows.

Distinguish:

```text
input space
+
auxiliary space
+
object overhead
+
temporary allocations
+
internal capacity
```

For Java collections, also understand:

```text
references
+
objects/nodes
+
arrays
+
load-factor slack
+
metadata
```

---

# 3.12.3 Big-O vs Amortized Complexity

Some operations are occasionally expensive but cheap on average over a long sequence.

Example:

```text
ArrayList.add()
```

Typical:

```text
O(1) amortized
```

but resizing can require:

```text
O(N)
```

Do not confuse:

```text
worst-case single operation
```

with:

```text
amortized sequence cost
```

---

# 3.12.4 Average vs Worst Case

For hash-based structures:

```text
average expected:
O(1)
```

but performance depends on:

```text
hash distribution
+
collisions
+
implementation
+
adversarial inputs
```

For modern Java HashMap, collision bins can treeify under appropriate conditions, improving certain collision-heavy lookup behavior.

Never state:

```text
HashMap = always O(1)
```

as an absolute guarantee.

---

# 3.12.5 Complexity Table — Major Operations

| Collection | Access/Search | Insert | Remove | Notes |
|---|---:|---:|---:|---|
| ArrayList | O(1) index / O(N) search | O(1) amortized at end | O(N) arbitrary | Excellent locality |
| LinkedList | O(N) index/search | O(1) with node position | O(1) with node position | Finding position can be O(N) |
| HashMap | O(1) expected | O(1) expected | O(1) expected | Collision dependent |
| TreeMap | O(log N) | O(log N) | O(log N) | Ordered keys |
| HashSet | O(1) expected | O(1) expected | O(1) expected | Backed by hash table |
| TreeSet | O(log N) | O(log N) | O(log N) | Sorted ordering |
| ArrayDeque | O(N) search | O(1) amortized at ends | O(1) at ends | Excellent queue/deque choice |
| ConcurrentHashMap | O(1) expected | O(1) expected | O(1) expected | Concurrent access |
| synchronized Map | Usually backing-map dependent | Backing-map dependent | Backing-map dependent | Synchronization adds contention |

These are high-level typical complexities, not universal guarantees for every operation or implementation detail.

---

# 3.12.6 ArrayList Complexity

## Core Operations

```text
get(index)       O(1)
set(index)       O(1)
add(end)         O(1) amortized
add(index)       O(N)
remove(index)    O(N)
contains(value)  O(N)
indexOf(value)   O(N)
```

---

# 3.12.7 Why ArrayList `get()` Is O(1)

ArrayList stores elements in an array-like structure.

Conceptually:

```text
[index 0] [index 1] [index 2] [index 3]
    ↓         ↓         ↓         ↓
    A         B         C         D
```

Index calculation directly identifies the reference location.

No traversal is required.

---

# 3.12.8 ArrayList Insert Complexity

Appending:

```java
list.add(value);
```

is typically:

```text
O(1) amortized
```

Insertion in the middle:

```java
list.add(index, value);
```

requires shifting elements:

```text
[A][B][C][D]
    ↓ insert X
[A][X][B][C][D]
```

Complexity:

```text
O(N)
```

---

# 3.12.9 ArrayList Remove Complexity

Removing from the end:

```text
O(1) typical
```

Removing from the beginning or middle:

```text
O(N)
```

because subsequent references may need to shift.

---

# 3.12.10 ArrayList Space Complexity

For N elements:

```text
O(N)
```

But actual memory includes:

```text
backing array capacity
+
N references
+
element objects
```

Unused capacity contributes memory overhead.

---

# 3.12.11 ArrayList Cache Locality

Array-backed structures often have excellent spatial locality:

```text
reference  reference  reference  reference
   ↓          ↓          ↓          ↓
 contiguous memory
```

This can make ArrayList substantially faster than LinkedList in real workloads even when both appear similar in certain Big-O analyses.

---

# 3.12.12 LinkedList Complexity

Typical:

```text
get(index)       O(N)
set(index)       O(N)
contains         O(N)
addFirst         O(1)
addLast          O(1)
removeFirst      O(1)
removeLast       O(1)
```

If you already have a `ListIterator` positioned at the required location, insertion/removal can be O(1) after reaching that location.

---

# 3.12.13 Why LinkedList `get()` Is O(N)

Conceptually:

```text
HEAD
 ↓
Node → Node → Node → Node
                         ↑
                        TAIL
```

To access an arbitrary index, traversal is required.

The implementation can choose traversal direction based on the index, but the worst-case remains O(N).

---

# 3.12.14 LinkedList Space Complexity

LinkedList requires:

```text
O(N)
```

space but with greater per-element overhead than an array-backed List.

Each node conceptually stores:

```text
element reference
previous reference
next reference
```

plus object/header/alignment overhead.

---

# 3.12.15 ArrayList vs LinkedList

| Characteristic | ArrayList | LinkedList |
|---|---|---|
| Random access | O(1) | O(N) |
| Append | O(1) amortized | O(1) |
| Middle insertion | O(N) | O(N) to locate + O(1) once positioned |
| Memory locality | Excellent | Poorer |
| Per-element overhead | Lower | Higher |
| Allocation | Backing array growth | Many node objects |
| Typical general-purpose choice | Usually preferred | Specialized use cases |

---

# 3.12.16 Why LinkedList Is Often Slower

Even where LinkedList has theoretically attractive insertion/removal properties, real performance can be worse because of:

```text
pointer chasing
+
poor cache locality
+
node allocations
+
GC pressure
+
larger memory footprint
```

Big-O alone is insufficient.

---

# 3.12.17 HashMap Complexity

Typical expected complexity:

```text
get()          O(1)
put()          O(1)
remove()       O(1)
containsKey()  O(1)
```

But analyze:

```text
hash computation
+
bucket lookup
+
collision handling
+
equals()
+
resizing
```

---

# 3.12.18 HashMap Worst-Case Reasoning

Collision-heavy behavior can degrade.

Modern implementations can treeify sufficiently large collision bins under the required conditions.

Therefore learn both:

```text
typical expected lookup
```

and:

```text
collision-heavy behavior
```

rather than memorizing one number.

---

# 3.12.19 HashMap Resize Cost

A resize can require significant work:

```text
old table
    ↓
allocate larger table
    ↓
transfer bins
    ↓
new table
```

A resize can be O(N) for the affected table.

Amortization makes ordinary insertion efficient over long sequences.

---

# 3.12.20 HashMap Space Complexity

For N mappings:

```text
O(N)
```

Actual memory includes:

```text
table array
+
Node objects
+
key objects
+
value objects
+
tree-node overhead where applicable
+
unused capacity
```

HashMap can therefore consume substantially more memory than a compact array-based structure.

---

# 3.12.21 TreeMap Complexity

TreeMap is based on an ordered tree structure.

Typical:

```text
get()       O(log N)
put()       O(log N)
remove()    O(log N)
containsKey O(log N)
```

Advantages:

```text
sorted keys
+
range-oriented navigation
+
ordered traversal
```

---

# 3.12.22 TreeMap Memory

TreeMap nodes require references for:

```text
key
value
left
right
parent
```

plus tree metadata/object overhead.

Therefore:

```text
O(N)
```

space with significant per-entry overhead.

---

# 3.12.23 HashMap vs TreeMap

| Characteristic | HashMap | TreeMap |
|---|---|---|
| Typical lookup | O(1) expected | O(log N) |
| Ordering | No sorted-key ordering | Sorted |
| Range queries | Limited | Excellent |
| Memory | Hash table + nodes | Tree nodes |
| Main cost | Hashing/collisions | Comparisons/tree traversal |
| Use when | Fast key lookup | Ordered/range operations |

---

# 3.12.24 HashSet Complexity

Typical expected:

```text
add()       O(1)
remove()    O(1)
contains()  O(1)
```

HashSet is implemented using a hash-based Map structure.

Therefore its complexity and memory behavior are closely related to HashMap.

---

# 3.12.25 TreeSet Complexity

Typical:

```text
add()       O(log N)
remove()    O(log N)
contains()  O(log N)
```

It provides sorted Set semantics.

---

# 3.12.26 HashSet vs TreeSet

| Characteristic | HashSet | TreeSet |
|---|---|---|
| Lookup | O(1) expected | O(log N) |
| Insert | O(1) expected | O(log N) |
| Remove | O(1) expected | O(log N) |
| Ordering | No sorted order | Sorted |
| Range operations | Weak | Strong |
| Comparator required | No | Yes, unless natural ordering |

Choose TreeSet when ordering is part of the requirement, not merely because it "feels organized."

---

# 3.12.27 ArrayDeque Complexity

Typical:

```text
addFirst()   O(1) amortized
addLast()    O(1) amortized
pollFirst()  O(1)
pollLast()   O(1)
peekFirst()  O(1)
peekLast()   O(1)
```

Search operations such as:

```text
contains()
remove(Object)
```

are O(N).

---

# 3.12.28 ArrayDeque vs LinkedList

Both support Deque operations.

But ArrayDeque generally benefits from:

```text
contiguous backing storage
+
better cache locality
+
fewer node allocations
+
lower per-element overhead
```

LinkedList can still be appropriate when its specific List/Deque behavior fits the workload, but it should not be selected merely because "linked lists have O(1) insertion."

---

# 3.12.29 ConcurrentHashMap Complexity

Typical expected:

```text
get()          O(1)
put()          O(1)
remove()       O(1)
containsKey()  O(1)
```

Concurrency introduces additional factors:

```text
CAS
+
bin synchronization
+
contention
+
resizing
+
memory barriers
```

Therefore the asymptotic complexity does not capture scalability.

---

# 3.12.30 ConcurrentHashMap vs Synchronized Map

Compare:

```java
Collections.synchronizedMap(
    new HashMap<>()
);
```

with:

```java
new ConcurrentHashMap<>();
```

A synchronized wrapper typically serializes access around the backing collection's operations.

ConcurrentHashMap uses a more scalable concurrent architecture.

---

# 3.12.31 Concurrency Complexity vs Throughput

Two implementations can have identical:

```text
O(1)
```

complexity while having radically different:

```text
throughput
+
latency
+
contention
+
scalability
```

For concurrent collections, always analyze:

```text
thread count
+
read/write ratio
+
key distribution
+
contention hotspots
+
allocation
```

---

# 3.12.32 Synchronized Map Costs

Potential costs include:

```text
lock acquisition
+
lock contention
+
context switching
+
serialized access
```

Under low contention, the difference may be small.

Under high contention, a synchronized global access pattern can become a scalability bottleneck.

---

# 3.12.33 Complexity Is Not Performance

Always distinguish:

```text
algorithmic complexity
```

from:

```text
real machine performance
```

Real performance is affected by:

```text
CPU cache
+
branch prediction
+
pointer chasing
+
allocation
+
GC
+
locking
+
CAS retries
+
memory bandwidth
+
CPU frequency
```

---

# 3.12.34 Cache Locality Example

Compare:

```text
ArrayList:

[A][B][C][D][E]
 ↓  ↓  ↓  ↓  ↓
contiguous references
```

with:

```text
LinkedList:

Node A → Node B → Node C → Node D
  ↘       ↘        ↘
different memory locations
```

The first can often exploit CPU cache locality more effectively.

---

# 3.12.35 Allocation Behavior

ArrayList:

```text
few backing-array allocations
+
occasional resize
```

LinkedList:

```text
one node allocation per element
```

This difference can affect:

```text
GC frequency
+
allocation rate
+
latency
```

---

# 3.12.36 Boxing and Collection Complexity

Java generic collections store references.

Therefore:

```java
List<Integer>
```

stores references to Integer objects rather than primitive `int` values directly.

This introduces:

```text
boxing
+
object allocation where values are not cached/reused
+
memory overhead
+
GC considerations
```

Algorithmically both may still be:

```text
O(N)
```

but their practical performance can differ significantly.

---

# 3.12.37 Comparator Cost

Tree-based collections perform comparisons.

Therefore complexity such as:

```text
O(log N)
```

assumes comparator work is reasonably bounded.

If:

```java
compare(a, b)
```

is expensive, total runtime includes that cost.

Study:

```text
O(log N × comparatorCost)
```

conceptually.

---

# 3.12.38 Hash Function Cost

Hash-based collections depend on:

```text
hashCode()
```

If hashing is expensive:

```text
Map operation cost
=
hash computation
+
lookup
+
equality checks
```

Do not assume `hashCode()` is always trivial.

---

# 3.12.39 `equals()` Cost

Collision resolution can require multiple equality checks.

If `equals()` is expensive:

```text
collision-heavy lookup
```

can become costly.

Therefore good key design matters.

---

# 3.12.40 Mutable Keys

Never casually mutate fields that participate in:

```text
hashCode()
+
equals()
```

after inserting an object into a HashMap/HashSet.

Otherwise:

```text
object
 ↓
bucket determined using old hash
 ↓
key changes
 ↓
lookup uses new hash
 ↓
entry may become unreachable through normal lookup
```

This is a correctness and debugging issue, not merely a performance issue.

---

# 3.12.41 Capacity Planning

Pre-sizing can reduce resizing.

Example:

```java
Map<String, User> users =
    new HashMap<>(expectedCapacity);
```

But blindly allocating huge capacity can waste memory.

Understand:

```text
expected size
+
load factor
+
threshold
+
resize cost
+
memory footprint
```

---

# 3.12.42 Complexity of Major Collection Operations

## List

- [ ] `ArrayList.get()` → O(1)
- [ ] `ArrayList.add(end)` → O(1) amortized
- [ ] `ArrayList.add(index)` → O(N)
- [ ] `ArrayList.remove(index)` → O(N)
- [ ] `ArrayList.contains()` → O(N)
- [ ] `LinkedList.get()` → O(N)
- [ ] `LinkedList.addFirst()` → O(1)
- [ ] `LinkedList.removeFirst()` → O(1)

## Set

- [ ] `HashSet.contains()` → O(1) expected
- [ ] `HashSet.add()` → O(1) expected
- [ ] `TreeSet.contains()` → O(log N)
- [ ] `TreeSet.add()` → O(log N)

## Map

- [ ] `HashMap.get()` → O(1) expected
- [ ] `HashMap.put()` → O(1) expected
- [ ] `TreeMap.get()` → O(log N)
- [ ] `TreeMap.put()` → O(log N)
- [ ] `ConcurrentHashMap.get()` → O(1) expected
- [ ] `ConcurrentHashMap.put()` → O(1) expected

## Queue / Deque

- [ ] `ArrayDeque.addFirst()` → O(1) amortized
- [ ] `ArrayDeque.addLast()` → O(1) amortized
- [ ] `ArrayDeque.pollFirst()` → O(1)
- [ ] `ArrayDeque.pollLast()` → O(1)
- [ ] `PriorityQueue.offer()` → O(log N)
- [ ] `PriorityQueue.poll()` → O(log N)
- [ ] `PriorityQueue.peek()` → O(1)

---

# 3.12.43 Space Complexity Comparison

| Structure | Space | Major overhead |
|---|---:|---|
| ArrayList | O(N) | Backing array capacity |
| LinkedList | O(N) | Node objects + links |
| HashMap | O(N) | Table + entries |
| TreeMap | O(N) | Tree nodes + links |
| HashSet | O(N) | Backing HashMap |
| TreeSet | O(N) | Tree nodes |
| ArrayDeque | O(N) | Backing array |
| ConcurrentHashMap | O(N) | Table + nodes + concurrency metadata |

Remember:

```text
O(N)
```

does not mean equal memory usage.

---

# 3.12.44 Memory Efficiency Ranking — General Intuition

For many simple reference-element workloads:

```text
array-backed structures
    ↓
often more memory-efficient

node/tree/hash structures
    ↓
more metadata per element
```

But exact memory consumption depends on:

```text
JDK
+
JVM
+
object layout
+
compressed references
+
element types
+
capacity
```

Use measurement tools rather than relying on intuition for production sizing.

---

# 3.12.45 Production Collection Selection

## Need random indexed access?

```text
ArrayList
```

## Need frequent queue/deque operations?

```text
ArrayDeque
```

## Need expected O(1) key lookup?

```text
HashMap
```

## Need sorted keys/range operations?

```text
TreeMap
```

## Need unique values with fast expected membership?

```text
HashSet
```

## Need sorted unique values?

```text
TreeSet
```

## Need highly concurrent key/value access?

```text
ConcurrentHashMap
```

## Need simple synchronized access to an existing Map?

```text
Collections.synchronizedMap()
```

but analyze contention and iteration requirements.

---

# 3.12.46 Decision Matrix

| Requirement | Preferred starting point |
|---|---|
| Indexed list access | ArrayList |
| General-purpose List | ArrayList |
| FIFO queue | ArrayDeque |
| LIFO stack | ArrayDeque |
| Priority ordering | PriorityQueue |
| Expected fast Map lookup | HashMap |
| Sorted Map | TreeMap |
| Expected fast Set membership | HashSet |
| Sorted Set | TreeSet |
| Concurrent Map | ConcurrentHashMap |
| Read-heavy copy-on-write List | CopyOnWriteArrayList |
| Bounded blocking queue | ArrayBlockingQueue |

This is a starting point, not a substitute for workload analysis.

---

# 3.12.47 Common Mistakes

- [ ] Memorizing Big-O without understanding implementation.
- [ ] Saying HashMap is always O(1).
- [ ] Saying LinkedList insertion is always O(1).
- [ ] Ignoring the cost of finding a LinkedList position.
- [ ] Ignoring cache locality.
- [ ] Ignoring allocation and GC.
- [ ] Ignoring unused collection capacity.
- [ ] Ignoring comparator cost.
- [ ] Ignoring hashCode/equals cost.
- [ ] Using TreeMap when ordering is unnecessary.
- [ ] Using LinkedList as a default queue.
- [ ] Using HashSet when sorted output is required.
- [ ] Assuming synchronized Map scales like ConcurrentHashMap.
- [ ] Treating O(1) as "instant."
- [ ] Ignoring contention in concurrent collections.
- [ ] Ignoring resizing.
- [ ] Blindly pre-sizing collections.
- [ ] Mutating HashMap keys after insertion.
- [ ] Using unbounded structures without memory analysis.

---

# 3.12.48 Edge Cases

- [ ] Empty collection.
- [ ] One element.
- [ ] Very large N.
- [ ] Duplicate values.
- [ ] Hash collisions.
- [ ] Poor hash function.
- [ ] Expensive equals().
- [ ] Expensive comparator.
- [ ] Collection resize.
- [ ] High load factor.
- [ ] Low load factor.
- [ ] Mutable keys.
- [ ] Concurrent mutation.
- [ ] High thread contention.
- [ ] Memory pressure.
- [ ] GC pressure.
- [ ] Null handling.
- [ ] Ordered vs unordered semantics.

---

# 3.12.49 Performance Implications

For every collection, evaluate:

```text
CPU
+
memory
+
allocation
+
GC
+
cache locality
+
branch behavior
+
contention
+
resizing
+
comparator/hash cost
```

Do not optimize solely from asymptotic notation.

---

# 3.12.50 Benchmarking Rules

Use a proper benchmark framework such as:

```text
JMH — Java Microbenchmark Harness
```

Avoid concluding collection performance from:

```java
System.nanoTime()
```

around one small loop.

A valid benchmark should consider:

- [ ] Warmup.
- [ ] JVM JIT compilation.
- [ ] Forks.
- [ ] Dead-code elimination.
- [ ] Constant folding.
- [ ] Allocation.
- [ ] Garbage collection.
- [ ] Different input sizes.
- [ ] Different thread counts.

---

# 3.12.51 Coding Exercises

## Basic

- [ ] Measure ArrayList indexed access.
- [ ] Measure LinkedList indexed access.
- [ ] Compare HashMap and TreeMap lookup.
- [ ] Compare HashSet and TreeSet membership.
- [ ] Compare ArrayDeque and LinkedList queue operations.
- [ ] Calculate Big-O for common collection operations.

## Intermediate

- [ ] Implement a simple dynamic array.
- [ ] Implement a simple linked list.
- [ ] Implement a simple hash table.
- [ ] Implement a binary search tree.
- [ ] Implement a circular deque.
- [ ] Measure resizing behavior.
- [ ] Calculate amortized cost of dynamic-array insertion.

## Advanced

- [ ] Implement HashMap-style buckets.
- [ ] Implement collision chaining.
- [ ] Implement a binary heap.
- [ ] Implement a red-black-tree conceptually.
- [ ] Compare cache locality of arrays vs nodes.
- [ ] Benchmark different collection implementations using JMH.
- [ ] Measure allocation and GC differences.

## Production-Style

- [ ] Select collections for a high-throughput API.
- [ ] Design a memory-efficient in-memory cache.
- [ ] Design a concurrent request registry.
- [ ] Optimize a slow collection-heavy service.
- [ ] Diagnose GC caused by collection allocations.
- [ ] Analyze a production latency spike caused by collection contention.
- [ ] Design collection capacity based on expected workload.

---

# 3.12.52 Comparison Exercise — ArrayList vs LinkedList

Create workloads:

```text
append N
prepend N
random get N
middle insert N
middle remove N
contains N
iterate N
```

Run with:

```text
N = 1,000
N = 10,000
N = 100,000
N = 1,000,000
```

Record:

```text
runtime
+
allocation
+
GC
+
memory
```

Explain why observed results may differ from a simplistic Big-O prediction.

---

# 3.12.53 Comparison Exercise — HashMap vs TreeMap

Benchmark:

```text
put
get
remove
containsKey
iteration
```

Test:

```text
random keys
+
sequential keys
+
large keys
+
different N
```

Explain:

```text
O(1) expected
vs.
O(log N)
```

and why HashMap may still have different behavior under collision-heavy workloads.

---

# 3.12.54 Comparison Exercise — HashSet vs TreeSet

Measure:

```text
add
contains
remove
iteration
```

Then determine when:

```text
TreeSet's ordering
```

is worth its additional computational cost.

---

# 3.12.55 Comparison Exercise — ArrayDeque vs LinkedList

Build:

```text
FIFO queue
LIFO stack
double-ended queue
```

Measure:

```text
addFirst
addLast
removeFirst
removeLast
peekFirst
peekLast
iteration
```

Explain the impact of:

```text
cache locality
+
node allocation
+
memory overhead
```

---

# 3.12.56 Comparison Exercise — ConcurrentHashMap vs Synchronized Map

Create:

```text
1 thread
2 threads
4 threads
8 threads
16 threads
32 threads
```

Run:

```text
read-heavy
write-heavy
mixed
high-contention
low-contention
```

Measure:

```text
throughput
+
latency
+
CPU
+
contention
```

Explain why the results change with workload and thread count.

---

# 3.12.57 Production Debugging Exercise

A service has:

```text
high GC
+
high memory
+
latency spikes
```

Its code uses:

```java
LinkedList<Event> events;
```

and repeatedly creates/removes millions of objects.

Investigate:

```text
node allocation
+
GC
+
cache locality
+
collection choice
```

Test whether an array-backed structure improves the workload.

---

# 3.12.58 Production Debugging Exercise — HashMap

Symptoms:

```text
lookup latency unexpectedly high
```

Investigate:

```text
hashCode()
+
equals()
+
collision distribution
+
key mutability
+
resize behavior
+
capacity
```

Do not immediately blame HashMap itself.

---

# 3.12.59 Production Debugging Exercise — Concurrent Map

Symptoms:

```text
CPU high
+
throughput drops as thread count increases
```

Investigate:

```text
hot keys
+
contention
+
CAS retries
+
bin synchronization
+
mapping functions
+
expensive compute operations
```

Explain why asymptotic O(1) does not guarantee linear scalability.

---

# 3.12.60 OpenJDK Source Investigation

Inspect the target JDK implementation of:

- [ ] `ArrayList`
- [ ] `LinkedList`
- [ ] `HashMap`
- [ ] `TreeMap`
- [ ] `HashSet`
- [ ] `TreeSet`
- [ ] `ArrayDeque`
- [ ] `ConcurrentHashMap`

Study:

```text
data structure
+
fields
+
growth
+
resizing
+
iteration
+
removal
+
hashing
+
tree balancing
+
concurrency
```

Focus on implementation details only after understanding the public API contract.

---

# 3.12.61 Complexity Investigation

For each structure, answer:

```text
What is the operation's average/expected complexity?
What is its worst-case complexity?
What causes the worst case?
Is the operation amortized?
What memory is allocated?
What hidden work occurs?
```

Create a personal complexity matrix covering:

```text
get
put
add
remove
contains
iteration
resize
sort
```

---

# 3.12.62 Advanced Follow-Ups

- [ ] Amortized analysis of dynamic arrays.
- [ ] Hash-table load factor mathematics.
- [ ] Hash collision behavior.
- [ ] Treeification in HashMap.
- [ ] Red-black tree complexity.
- [ ] CPU cache effects.
- [ ] Pointer chasing.
- [ ] Object header overhead.
- [ ] Compressed ordinary object pointers.
- [ ] Garbage-collection impact.
- [ ] Allocation rate.
- [ ] Escape analysis implications.
- [ ] JIT optimization.
- [ ] Lock contention.
- [ ] CAS retry loops.
- [ ] Memory barriers.
- [ ] False sharing concepts.
- [ ] Benchmark methodology with JMH.

---

# 3.12.63 Interview Questions

## Basic

- [ ] What is Big-O?
- [ ] What is space complexity?
- [ ] What is amortized complexity?
- [ ] Complexity of ArrayList get?
- [ ] Complexity of LinkedList get?
- [ ] Complexity of HashMap get?
- [ ] Complexity of TreeMap get?
- [ ] Complexity of HashSet contains?
- [ ] Complexity of TreeSet contains?
- [ ] Complexity of ArrayDeque operations?

## Intermediate

- [ ] Why is ArrayList append O(1) amortized?
- [ ] Why is ArrayList insertion in the middle O(N)?
- [ ] Why is LinkedList often slower despite O(1) node insertion?
- [ ] Why is HashMap lookup expected O(1)?
- [ ] What can make HashMap slower?
- [ ] Why is TreeMap O(log N)?
- [ ] Why is TreeSet O(log N)?
- [ ] Why is ArrayDeque usually preferred over LinkedList for queues?
- [ ] Why does HashMap resize?
- [ ] What is the space complexity of each major collection?

## Advanced

- [ ] Explain amortized analysis of ArrayList growth.
- [ ] Explain HashMap collision complexity.
- [ ] Explain treeification and its effect on complexity.
- [ ] Explain why Big-O does not predict actual latency.
- [ ] Explain cache locality.
- [ ] Explain pointer chasing.
- [ ] Explain collection allocation overhead.
- [ ] Explain comparator cost in TreeMap.
- [ ] Explain hashCode/equals cost in HashMap.
- [ ] Explain ConcurrentHashMap scalability.
- [ ] Explain synchronized Map contention.

## Senior / Production

- [ ] How would you choose between ArrayList and LinkedList in production?
- [ ] How would you choose between HashMap and TreeMap?
- [ ] How would you choose between HashSet and TreeSet?
- [ ] How would you choose between ArrayDeque and LinkedList?
- [ ] How would you choose ConcurrentHashMap vs synchronizedMap?
- [ ] How would you investigate a collection-induced latency spike?
- [ ] How would you investigate collection-induced GC pressure?
- [ ] How would you capacity-plan a HashMap?
- [ ] How would you benchmark collection choices correctly?
- [ ] Why can O(1) operations still become bottlenecks?
- [ ] How does CPU cache behavior affect collection choice?
- [ ] How would you prove that a collection is the performance bottleneck?

---

# 3.12.64 Final Mastery Gate

## Complexity Fundamentals

- [ ] Explain Big-O.
- [ ] Explain Big-Omega.
- [ ] Explain Big-Theta.
- [ ] Explain space complexity.
- [ ] Explain amortized complexity.
- [ ] Distinguish average/expected from worst case.
- [ ] Understand implementation-dependent behavior.

## ArrayList vs LinkedList

- [ ] Explain internal structures.
- [ ] Explain operation complexity.
- [ ] Explain amortized append.
- [ ] Explain resizing.
- [ ] Explain memory overhead.
- [ ] Explain cache locality.
- [ ] Explain why LinkedList is often slower.
- [ ] Choose correctly for production.

## HashMap vs TreeMap

- [ ] Explain hash-table lookup.
- [ ] Explain expected O(1).
- [ ] Explain collision effects.
- [ ] Explain resize cost.
- [ ] Explain TreeMap O(log N).
- [ ] Explain ordered traversal.
- [ ] Explain range operations.
- [ ] Compare memory.
- [ ] Choose correctly.

## HashSet vs TreeSet

- [ ] Explain uniqueness.
- [ ] Explain expected O(1) HashSet operations.
- [ ] Explain O(log N) TreeSet operations.
- [ ] Explain ordering.
- [ ] Explain comparator/natural ordering.
- [ ] Choose correctly.

## ArrayDeque vs LinkedList

- [ ] Explain deque operations.
- [ ] Explain amortized array growth.
- [ ] Explain node allocation.
- [ ] Explain cache locality.
- [ ] Compare memory.
- [ ] Choose correctly.

## ConcurrentHashMap vs Synchronized Map

- [ ] Explain synchronization differences.
- [ ] Explain concurrency.
- [ ] Explain contention.
- [ ] Explain atomic compound operations.
- [ ] Explain iteration semantics.
- [ ] Explain scalability.
- [ ] Benchmark both.
- [ ] Choose correctly.

## Performance

- [ ] Explain CPU cache effects.
- [ ] Explain pointer chasing.
- [ ] Explain object allocation.
- [ ] Explain GC effects.
- [ ] Explain comparator/hash costs.
- [ ] Explain resizing.
- [ ] Explain contention.
- [ ] Use JMH correctly.

## Production

- [ ] Diagnose collection bottlenecks.
- [ ] Diagnose memory pressure.
- [ ] Diagnose GC pressure.
- [ ] Diagnose contention.
- [ ] Capacity-plan collections.
- [ ] Benchmark realistic workloads.
- [ ] Justify collection choices with evidence.

---

# Final Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] COMPLEXITY MASTERED
- [ ] SPACE COMPLEXITY MASTERED
- [ ] AMORTIZED ANALYSIS MASTERED
- [ ] INTERNALS UNDERSTOOD
- [ ] MEMORY BEHAVIOR UNDERSTOOD
- [ ] CACHE / CPU BEHAVIOR UNDERSTOOD
- [ ] CONCURRENCY PERFORMANCE UNDERSTOOD
- [ ] BENCHMARKING MASTERED
- [ ] EDGE CASES MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
