# Module 3.6 — ConcurrentHashMap
## Deep Dive & Deep Mastery Guide

> **Goal:** Master `ConcurrentHashMap` from its public contract through Java 7 segmented architecture, Java 8+ CAS-based design, volatile fields, synchronized bins, concurrent reads/updates, resizing, weakly consistent iteration, null-key/value restrictions, atomic compound operations, memory-model reasoning, performance, and production usage.

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

# 3.6.1 What Is `ConcurrentHashMap`?

`ConcurrentHashMap<K,V>` is a thread-safe implementation of the `ConcurrentMap` interface designed for concurrent access.

It allows multiple threads to:

```text
read
+
insert
+
update
+
remove
```

without requiring callers to synchronize the entire map.

Example:

```java
ConcurrentHashMap<String, Integer> counts =
        new ConcurrentHashMap<>();

counts.put("Java", 1);
counts.merge("Java", 1, Integer::sum);

System.out.println(counts.get("Java"));
```

Output:

```text
2
```

---

# 3.6.2 Why Does Java Have It?

A normal `HashMap` is not safe for concurrent mutation.

A single global lock around a HashMap can be safe but may create excessive contention:

```text
Thread A ─┐
Thread B ─┤
Thread C ─┼── global lock ── HashMap
Thread D ─┘
```

ConcurrentHashMap aims to provide:

```text
thread safety
+
high concurrency
+
non-blocking reads in common cases
+
fine-grained update coordination
```

The implementation has evolved significantly across Java versions.

---

# 3.6.3 Core Characteristics

| Property | ConcurrentHashMap |
|---|---|
| Interface | `ConcurrentMap` / `Map` |
| Thread-safe | Yes |
| Concurrent reads | Yes |
| Concurrent updates | Yes |
| Null keys | Not allowed |
| Null values | Not allowed |
| Ordering | No ordering guarantee |
| Iterator | Weakly consistent |
| Atomic compound APIs | Yes |
| Average lookup | Expected O(1) |
| Main modern design | CAS + synchronized bins |
| Java 7 design | Segmented |
| Java 8+ design | Node table + CAS/synchronized bins |

---

# 3.6.4 HashMap vs ConcurrentHashMap

| Property | HashMap | ConcurrentHashMap |
|---|---|---|
| Thread-safe mutation | No | Yes |
| Concurrent reads | Not safe during concurrent mutation | Supported |
| Null key | One allowed | Not allowed |
| Null values | Allowed | Not allowed |
| Atomic compound operations | Basic Map operations only | `putIfAbsent`, `compute`, `merge`, etc. |
| Iteration | Fail-fast behavior generally | Weakly consistent |
| Synchronization strategy | None | Fine-grained concurrency mechanisms |
| Typical use | Single-threaded / externally synchronized | Concurrent access |

---

# 3.6.5 Java 7 Architecture

Java 7-era ConcurrentHashMap used a segmented architecture.

Conceptually:

```text
ConcurrentHashMap
        |
        +-------------------+
        |                   |
    Segment 0            Segment 1
        |                   |
      table               table
        |                   |
      bins                bins
```

Each segment acted approximately like an independently lockable hash table.

This reduced contention compared with one global lock.

---

# 3.6.6 Java 7 Segments

A simplified model:

```text
key
 ↓
hash
 ↓
segment selection
 ↓
segment lock
 ↓
bucket
 ↓
entry
```

Different keys could map to different segments.

Therefore:

```text
Thread A → Segment 0
Thread B → Segment 1
```

could proceed concurrently.

But if:

```text
Thread A → Segment 0
Thread B → Segment 0
```

they could contend on the same segment.

---

# 3.6.7 Segment-Level Concurrency

Java 7's design provided a configurable notion of concurrency level.

Conceptually:

```text
more segments
    ↓
more independent locking regions
    ↓
potentially more concurrent updates
```

But more segments also meant:

```text
more metadata
+
more complexity
+
memory overhead
```

Modern Java 8+ ConcurrentHashMap no longer uses this segmented architecture.

---

# 3.6.8 Java 8+ Architecture

Java 8 fundamentally redesigned ConcurrentHashMap.

The simplified model is:

```text
ConcurrentHashMap
       |
       ↓
Node[] table
       |
       +-- bin 0
       +-- bin 1
       +-- bin 2
       +-- ...
```

Synchronization is associated with individual bins during contended updates rather than using the old Segment abstraction.

Modern implementations combine:

```text
CAS
+
volatile memory semantics
+
synchronized blocks
+
specialized resize coordination
```

---

# 3.6.9 Modern Mental Model

Think:

```text
                  Node[]
                    |
      +-------------+-------------+
      |             |             |
     bin           bin           bin
      |             |             |
     CAS        synchronized     CAS
```

The actual implementation is more sophisticated, especially during initialization, collisions, and resizing.

---

# 3.6.10 CAS

CAS means:

```text
Compare-And-Set
```

It is a lock-free atomic primitive supported by modern CPUs and exposed by Java concurrency mechanisms.

Conceptually:

```text
expected value
      +
new value
      ↓
CAS
      ↓
change only if current == expected
```

Example mental model:

```text
current = null

CAS(null, newNode)

if current is still null:
    install newNode
else:
    retry / take another path
```

This can allow uncontended insertion into an empty bin without acquiring a conventional monitor.

---

# 3.6.11 Why CAS Is Useful

Suppose multiple threads attempt to insert into an empty bucket:

```text
Thread A ─┐
Thread B ─┼── bin
Thread C ─┘
```

CAS allows one thread to win atomically.

Conceptually:

```text
A: CAS(null, nodeA) → success
B: CAS(null, nodeB) → failure
C: CAS(null, nodeC) → failure
```

The losing threads follow the implementation's retry/collision path.

---

# 3.6.12 Volatile Fields

ConcurrentHashMap relies on volatile memory semantics and low-level atomic operations for safe coordination.

Important internal state includes table references and node/value visibility.

Conceptually:

```text
Thread A writes
       ↓
volatile / atomic publication
       ↓
Thread B observes
```

This is a Java Memory Model issue, not merely a syntax feature.

You should understand:

```text
visibility
+
ordering
+
atomicity
```

separately.

---

# 3.6.13 Volatile Does Not Mean Everything Is Atomic

This distinction is critical.

A volatile variable provides visibility and ordering guarantees appropriate to volatile access.

It does not automatically make arbitrary multi-step operations atomic.

For example:

```java
map.get(key);
map.put(key, newValue);
```

is not automatically one atomic operation.

Use the appropriate atomic map API when the operation must be performed as one logical update.

---

# 3.6.14 Synchronized Bins

For contended updates to an existing bin, modern ConcurrentHashMap can synchronize on the relevant bin/node.

Conceptually:

```java
synchronized (bin) {
    // modify collision chain/tree
}
```

This is very different from:

```text
synchronized entire map
```

Only the relevant portion is coordinated.

This enables greater concurrency.

---

# 3.6.15 Why Synchronized Bins Work

Suppose:

```text
bin 5 → A → B
bin 10 → C → D
```

Thread A modifies bin 5:

```text
lock bin 5
```

Thread B modifies bin 10:

```text
lock bin 10
```

The operations can proceed independently.

The exact locking object and implementation details must be studied from the target OpenJDK source.

---

# 3.6.16 Concurrent Reads

A major goal of ConcurrentHashMap is highly concurrent retrieval.

Typical:

```java
map.get(key);
```

does not require the caller to acquire a global lock.

This makes it especially suitable for read-heavy workloads.

Examples:

```text
configuration cache
lookup table
session metadata
counters
in-memory indexes
```

---

# 3.6.17 Concurrent Updates

Updates are coordinated at a finer granularity than a single global lock.

Conceptually:

```text
empty bin
   ↓
CAS

occupied bin
   ↓
synchronized bin update

large collision bin
   ↓
tree-bin mechanisms
```

The exact path depends on:

```text
table state
+
bin state
+
hash
+
collision structure
+
resize state
```

---

# 3.6.18 `put()`

Conceptual flow:

```text
put(key, value)
       ↓
compute hash
       ↓
initialize table if needed
       ↓
calculate bin
       ↓
empty?
  |       |
 yes      no
  |       |
 CAS    inspect bin
          |
       synchronize /
       update / tree
```

If the map needs resizing:

```text
resize coordination
```

is triggered.

---

# 3.6.19 `get()`

Conceptual flow:

```text
get(key)
   ↓
compute hash
   ↓
locate table
   ↓
calculate bin
   ↓
inspect node
   ↓
compare hash/key
   ↓
return value
```

The read path is deliberately optimized for concurrency.

---

# 3.6.20 Why Null Keys Are Not Allowed

ConcurrentHashMap does not allow:

```java
map.put(null, value);
```

This throws:

```text
NullPointerException
```

Why?

A major reason is semantic ambiguity for concurrent retrieval.

Consider:

```java
V value = map.get(key);
```

If null values were allowed:

```text
null
```

could mean:

```text
key absent
```

or:

```text
key present with null value
```

ConcurrentMap APIs such as:

```java
putIfAbsent
compute
merge
```

benefit from a clear distinction:

```text
null return/value
=
absence or special computation semantics
```

---

# 3.6.21 Why Null Values Are Not Allowed

The same ambiguity applies to values.

With:

```java
map.get(key)
```

ConcurrentHashMap can use:

```text
null
```

as an unambiguous indication that there is no mapping for the key.

Therefore:

```text
null key → prohibited
null value → prohibited
```

This is a deliberate API design decision.

---

# 3.6.22 Testing Null Behavior

```java
ConcurrentHashMap<String, String> map =
        new ConcurrentHashMap<>();

map.put(null, "A");
```

Expected:

```text
NullPointerException
```

And:

```java
map.put("A", null);
```

also fails.

Verify exact exceptions and behavior against the current JDK API documentation.

---

# 3.6.23 Atomic Compound Operations

One of the most important ConcurrentHashMap features is atomic compound operations.

Examples:

```java
putIfAbsent()
computeIfAbsent()
computeIfPresent()
compute()
merge()
replace()
replaceAll()
remove(key, value)
replace(key, oldValue, newValue)
```

These allow common read-modify-write operations to be expressed safely.

---

# 3.6.24 Broken Compound Operation

This is unsafe as a logical atomic update:

```java
Integer count = map.get(key);

if (count == null) {
    map.put(key, 1);
} else {
    map.put(key, count + 1);
}
```

Two threads can both observe:

```text
count = 0
```

and both write:

```text
1
```

One increment is lost.

---

# 3.6.25 Correct Atomic Update

Use:

```java
map.merge(
    key,
    1,
    Integer::sum
);
```

This expresses the intended operation atomically according to the ConcurrentMap contract.

For counters, `LongAdder` combined with `computeIfAbsent` is another important pattern under high contention:

```java
ConcurrentHashMap<String, LongAdder> counters =
        new ConcurrentHashMap<>();

counters
    .computeIfAbsent("Java", k -> new LongAdder())
    .increment();
```

---

# 3.6.26 `computeIfAbsent`

Example:

```java
map.computeIfAbsent(
    "users",
    key -> new ArrayList<>()
);
```

This is useful for building grouped data structures.

Example:

```java
ConcurrentHashMap<String, Set<String>> groups =
        new ConcurrentHashMap<>();
```

Be careful:

> The map's atomic operation does not automatically make the mutable object stored as the value thread-safe.

For example:

```java
ConcurrentHashMap<String, ArrayList<String>>
```

does not make each `ArrayList` safe for concurrent mutation.

---

# 3.6.27 Atomicity Boundary

This is a senior-level concept.

ConcurrentHashMap can atomically coordinate:

```java
map.compute(key, ...)
```

but if the mapping function modifies unrelated shared state:

```java
map.compute(key, k -> {
    sharedObject.modify();
    return value;
});
```

the safety of `sharedObject` is a separate concern.

Atomicity belongs to the operation covered by the map's contract, not automatically to every side effect inside the callback.

---

# 3.6.28 Mapping Function Caveats

Be careful with:

```java
compute()
computeIfAbsent()
computeIfPresent()
merge()
```

Mapping functions should generally be:

```text
short
+
side-effect conscious
+
non-blocking where practical
```

Avoid designs where the mapping function:

```text
waits on external locks
+
performs slow I/O
+
recursively modifies the same map
+
creates lock-ordering hazards
```

Always consult the current API documentation for restrictions and guarantees.

---

# 3.6.29 Resizing

ConcurrentHashMap must resize while other threads may still be accessing it.

This is significantly more complex than ordinary HashMap resizing.

Conceptually:

```text
old table
   ↓
resize
   ↓
new table
```

while:

```text
Thread A → reading
Thread B → inserting
Thread C → removing
Thread D → helping resize
```

may all be active.

---

# 3.6.30 Cooperative Resizing

Modern ConcurrentHashMap can coordinate multiple threads in transferring bins during resize.

Conceptually:

```text
Thread A
   ↓
transfer some bins

Thread B
   ↓
transfer more bins

Thread C
   ↓
help transfer
```

This distributes resize work rather than requiring one thread to perform the entire transfer alone.

---

# 3.6.31 `sizeCtl`

Modern ConcurrentHashMap uses internal control state commonly represented by:

```text
sizeCtl
```

It participates in table initialization, resizing thresholds, and resize coordination.

Its exact encoding is implementation-specific and can represent different states.

Study it directly from the OpenJDK source rather than memorizing only one simplified interpretation.

---

# 3.6.32 Forwarding Nodes

During resizing, modern ConcurrentHashMap uses special forwarding nodes.

Conceptually:

```text
old bin
   ↓
ForwardingNode
   ↓
new table
```

A reader encountering such a node can follow the migration to the new table.

This allows concurrent operations to continue while resizing progresses.

---

# 3.6.33 Why Resize Is Hard

Normal HashMap:

```text
resize
 ↓
move entries
```

ConcurrentHashMap:

```text
resize
 ↓
other threads still operate
 ↓
coordinate transfer
 ↓
readers may encounter forwarding state
 ↓
updates may help transfer
 ↓
new table becomes active
```

This is one of the most valuable internal topics in advanced Java concurrency.

---

# 3.6.34 Weakly Consistent Iteration

ConcurrentHashMap iterators are weakly consistent.

They:

```text
do not throw ConcurrentModificationException
```

merely because another thread modifies the map during iteration.

An iterator may reflect:

```text
some updates
```

but not necessarily all updates that happen after iteration begins.

Therefore:

> Do not interpret an iteration as a globally frozen snapshot.

---

# 3.6.35 Weakly Consistent Example

Suppose:

```java
for (Map.Entry<String, Integer> e :
        map.entrySet()) {

    // another thread modifies map
}
```

The iteration can continue safely.

Depending on timing, it may:

```text
see an entry
not see a newly added entry
observe an updated value
observe an older value
```

The exact behavior depends on the operation and timing.

Do not rely on a particular interleaving.

---

# 3.6.36 Fail-Fast vs Weakly Consistent

| Behavior | Typical HashMap Iterator | ConcurrentHashMap Iterator |
|---|---|---|
| Concurrent structural modification detection | Generally fail-fast | No fail-fast requirement |
| `ConcurrentModificationException` for normal concurrent modification | Possible | Not expected from ordinary concurrent modification |
| Snapshot | No | No |
| Safe concurrent traversal | No | Yes |
| Consistent global view | No | No |

Important:

```text
weakly consistent ≠ snapshot
```

---

# 3.6.37 Why Weak Consistency Is Useful

A fully consistent snapshot can be expensive.

ConcurrentHashMap instead provides a traversal model that allows:

```text
ongoing updates
+
ongoing reads
+
ongoing iteration
```

without requiring a global stop-the-world map lock.

This is valuable for:

```text
monitoring
statistics
caches
registries
live configuration
```

---

# 3.6.38 `size()` Under Concurrency

A concurrent map's size-related operations must be understood in the context of concurrent updates.

If other threads are changing the map, the result can represent the state at a particular moment rather than a stable application-wide snapshot.

For algorithms that require an exact stable count, do not assume:

```java
map.size()
```

provides a transactional snapshot across subsequent operations.

---

# 3.6.39 `mappingCount()`

ConcurrentHashMap also provides:

```java
mappingCount()
```

which returns a `long`.

This is useful because map sizes can exceed the range of `int`.

Understand why both:

```java
size()
```

and:

```java
mappingCount()
```

exist.

---

# 3.6.40 Concurrency Level Constructor Parameter

Older Java versions exposed constructors involving:

```java
concurrencyLevel
```

This was meaningful in the segmented design.

In modern Java 8+ implementations, the old segment-based model no longer applies.

Do not interpret `concurrencyLevel` as:

```text
number of locks
```

in the modern implementation.

Study the current JDK documentation/source for how constructor compatibility is handled.

---

# 3.6.41 Memory Visibility

ConcurrentHashMap provides the necessary synchronization and memory semantics for its supported operations.

For example:

```text
Thread A
put(key, object)
      ↓
publication through CHM
      ↓
Thread B
get(key)
```

The map's concurrency guarantees allow the retrieved mapping to be safely observed according to the API/JMM contract.

However:

> Publishing an object safely does not make all subsequent mutations of that object thread-safe.

---

# 3.6.42 Safe Publication vs Object Mutability

Example:

```java
ConcurrentHashMap<String, User> map =
        new ConcurrentHashMap<>();
```

Putting:

```java
map.put("A", user);
```

does not make:

```java
user
```

internally immutable or thread-safe.

If another thread concurrently changes fields inside `user`, that is a separate concurrency problem.

This distinction is essential:

```text
thread-safe container
≠
thread-safe contained object
```

---

# 3.6.43 `ConcurrentHashMap` and Immutable Values

A particularly strong production pattern is:

```text
ConcurrentHashMap
        +
immutable value objects
```

This reduces shared-state mutation.

Example:

```text
key → immutable configuration
key → immutable snapshot
key → immutable metadata
```

The map coordinates concurrent association changes while the values themselves remain safely immutable.

---

# 3.6.44 ConcurrentHashMap and Tree Bins

Like modern HashMap, ConcurrentHashMap can use tree-bin structures for heavily colliding bins.

The concurrency implementation must additionally coordinate tree-bin updates safely.

Therefore the mental model is:

```text
bin
 |
 +-- normal node chain
 |
 +-- tree bin
```

plus:

```text
CAS
+
synchronization
+
memory visibility
```

---

# 3.6.45 Hash Collision Behavior

A poor hash function can still create:

```text
many keys
   ↓
same bin
```

ConcurrentHashMap mitigates collision chains using tree bins where appropriate, but a bad key design still hurts:

```text
CPU
+
memory
+
contention
```

Collision resistance remains important.

---

# 3.6.46 Contention

Even with fine-grained concurrency, contention can occur.

Examples:

```text
many threads
   ↓
same hot key
```

or:

```text
many colliding keys
   ↓
same bin
```

Then:

```text
CAS retries
+
bin synchronization
+
CPU contention
```

can become significant.

This is why:

```text
"ConcurrentHashMap is concurrent"
```

does not mean:

```text
"all workloads scale linearly."
```

---

# 3.6.47 Hot-Key Problem

Suppose:

```text
99% of requests
      ↓
same map key
```

Even a highly concurrent map can experience contention around that logical workload.

For high-contention counters, alternatives such as:

```java
LongAdder
```

can provide better scalability than repeatedly updating a single boxed numeric value.

---

# 3.6.48 `AtomicInteger` vs `LongAdder`

For a concurrent counter:

```java
ConcurrentHashMap<String, AtomicInteger>
```

may work.

But under very high contention:

```java
ConcurrentHashMap<String, LongAdder>
```

can be a better pattern.

Reason:

```text
LongAdder
 ↓
striped/cell-based accumulation
 ↓
less direct contention on one atomic variable
```

This is workload-dependent and should be benchmarked.

---

# 3.6.49 Common Mistakes

- [ ] Using HashMap for concurrent mutation.
- [ ] Assuming ConcurrentHashMap makes values thread-safe.
- [ ] Assuming `volatile` makes compound operations atomic.
- [ ] Performing `get()` followed by `put()` when atomicity is required.
- [ ] Ignoring `computeIfAbsent`, `merge`, and `putIfAbsent`.
- [ ] Assuming weakly consistent iteration is a snapshot.
- [ ] Assuming iteration sees every concurrent update.
- [ ] Expecting fail-fast behavior.
- [ ] Using null keys.
- [ ] Using null values.
- [ ] Performing expensive I/O inside mapping functions.
- [ ] Introducing lock-ordering hazards inside callbacks.
- [ ] Assuming all concurrent workloads scale linearly.
- [ ] Assuming Java 7 segment internals still describe modern CHM.
- [ ] Treating constructor `concurrencyLevel` as modern segment count.
- [ ] Ignoring hot-key contention.
- [ ] Ignoring poor hash distribution.
- [ ] Assuming safe publication makes mutable values thread-safe.

---

# 3.6.50 Edge Cases

Investigate:

- [ ] Empty map.
- [ ] Null key.
- [ ] Null value.
- [ ] Duplicate key updates.
- [ ] Concurrent insertion.
- [ ] Concurrent removal.
- [ ] Concurrent replacement.
- [ ] Concurrent iteration.
- [ ] Resize while iterating.
- [ ] Resize while updating.
- [ ] Highly colliding keys.
- [ ] Tree bins.
- [ ] `computeIfAbsent`.
- [ ] Recursive map modification.
- [ ] Exceptions inside mapping functions.
- [ ] Multiple threads targeting one key.
- [ ] Large maps.
- [ ] High contention.
- [ ] Hot-key workloads.

---

# 3.6.51 Debugging Challenge — Lost Update

Start with:

```java
ConcurrentHashMap<String, Integer> map =
        new ConcurrentHashMap<>();

map.put("count", 0);
```

Write many threads executing:

```java
Integer value = map.get("count");
map.put("count", value + 1);
```

Tasks:

- [ ] Predict the result.
- [ ] Explain lost updates.
- [ ] Replace with `merge`.
- [ ] Compare results.
- [ ] Explain why thread-safe individual operations do not imply atomic compound workflows.

---

# 3.6.52 Debugging Challenge — Atomic `compute`

Implement:

```java
map.compute(
    key,
    (k, oldValue) -> ...
);
```

Tasks:

- [ ] Make a thread-safe counter.
- [ ] Run thousands of concurrent updates.
- [ ] Verify correctness.
- [ ] Introduce a slow mapping function.
- [ ] Observe contention.
- [ ] Explain the production implications.

---

# 3.6.53 Debugging Challenge — Weakly Consistent Iterator

Create:

```text
Thread A → iterate
Thread B → continuously add/remove
```

Tasks:

- [ ] Confirm that iteration can continue.
- [ ] Determine whether newly added entries are always visible.
- [ ] Determine whether removed entries are always absent.
- [ ] Explain why the iterator is not a snapshot.
- [ ] Explain why this behavior is useful.

---

# 3.6.54 Debugging Challenge — Null Semantics

Test:

```java
map.put(null, value);
map.put(key, null);
```

Then compare with:

```java
HashMap
```

Tasks:

- [ ] Record the behavior.
- [ ] Explain why CHM rejects null.
- [ ] Explain how this simplifies `get()` semantics.
- [ ] Identify migration issues when replacing HashMap with CHM.

---

# 3.6.55 Coding Exercises

## Basic

- [ ] Create a ConcurrentHashMap.
- [ ] Insert entries.
- [ ] Retrieve entries.
- [ ] Remove entries.
- [ ] Iterate entries.
- [ ] Test `containsKey`.
- [ ] Test `containsValue`.
- [ ] Demonstrate null restrictions.
- [ ] Use `putIfAbsent`.
- [ ] Use `replace`.

## Intermediate

- [ ] Implement a concurrent word counter.
- [ ] Implement a concurrent frequency map using `merge`.
- [ ] Group values using `computeIfAbsent`.
- [ ] Implement a concurrent cache.
- [ ] Implement a concurrent registry.
- [ ] Implement an atomic state transition using `compute`.

## Advanced

- [ ] Build a concurrent metrics registry.
- [ ] Compare `merge` with `AtomicInteger`.
- [ ] Compare `AtomicInteger` with `LongAdder`.
- [ ] Create a high-contention benchmark.
- [ ] Create a collision-heavy benchmark.
- [ ] Observe behavior during resizing.
- [ ] Build a concurrent memoization cache.
- [ ] Analyze mapping-function contention.

---

# 3.6.56 Production Exercise — Concurrent Cache

Design:

```text
ConcurrentHashMap<K,V>
```

for an in-memory cache.

Consider:

```text
1. Thread safety
2. Cache misses
3. Atomic initialization
4. Expiration
5. Memory limits
6. Eviction
7. Hot keys
8. Failure handling
9. Metrics
10. Stampede prevention
```

Important:

> ConcurrentHashMap itself is not a complete cache implementation.

You still need to design:

```text
TTL
+
eviction
+
capacity
+
refresh
+
failure behavior
```

if those features are required.

---

# 3.6.57 Production Use Cases

ConcurrentHashMap is useful for:

- [ ] Concurrent registries.
- [ ] In-memory indexes.
- [ ] Shared configuration.
- [ ] Metrics aggregation.
- [ ] Memoization.
- [ ] Request metadata.
- [ ] Session-related metadata.
- [ ] Connection registries.
- [ ] Feature-flag state.
- [ ] Concurrent caches when cache-specific eviction is not required.

---

# 3.6.58 When Not to Use ConcurrentHashMap

Do not automatically use it whenever multiple threads exist.

Consider alternatives when you need:

```text
ordering
```

or:

```text
blocking semantics
```

or:

```text
specialized caching
```

or:

```text
sorted/navigable access
```

or:

```text
transactional multi-key updates
```

A ConcurrentHashMap does not provide arbitrary multi-operation transactions.

---

# 3.6.59 Multi-Key Atomicity

This is an important limitation.

Suppose you need:

```text
update key A
+
update key B
```

as one atomic transaction.

ConcurrentHashMap does not provide general transactional multi-key atomicity.

You may need:

```text
external synchronization
+
higher-level locking
+
transactional data structure
+
different architecture
```

depending on requirements.

---

# 3.6.60 Production Trade-Offs

ConcurrentHashMap gives:

```text
high concurrent access
+
fine-grained coordination
+
efficient reads
+
atomic map-level compound operations
```

but costs:

```text
implementation complexity
+
memory overhead
+
contention under hot workloads
+
no null keys/values
+
no stable ordering
+
no general transaction semantics
```

---

# 3.6.61 Java 7 vs Java 8+ Comparison

| Topic | Java 7-era CHM | Java 8+ CHM |
|---|---|---|
| Main architecture | Segments | Node table |
| Locking | Segment-based | Bin-level coordination |
| CAS | Used in concurrency internals, but design differs | Central design component |
| Volatile/atomic state | Yes | Yes |
| Reads | Highly concurrent | Highly concurrent |
| Updates | Segment locking | CAS + synchronized bins |
| Resize | Concurrent mechanisms | Cooperative transfer |
| Tree bins | No modern Java 8-style tree bins | Yes |
| Concurrency level | Architecturally meaningful | No longer maps to segments |
| Design | Segmented hash table | Fine-grained bin coordination |

Exact implementation details vary by JDK version.

---

# 3.6.62 Internal Mental Model

## Java 7

```text
ConcurrentHashMap
       |
       +---- Segment 0 ---- table
       |
       +---- Segment 1 ---- table
       |
       +---- Segment 2 ---- table
       |
       +---- Segment N ---- table
```

## Java 8+

```text
ConcurrentHashMap
       |
       ↓
    Node[]
       |
   +---+---+---+
   |   |   |   |
  bin bin bin bin
   |   |   |   |
  CAS lock CAS lock
```

During resize:

```text
old table
    |
    ↓
ForwardingNode
    |
    ↓
new table
```

---

# 3.6.63 Deep Performance Mental Model

Do not reduce ConcurrentHashMap to:

```text
"HashMap but thread-safe."
```

Use:

```text
hash quality
+
bucket distribution
+
CAS contention
+
bin synchronization
+
hot keys
+
resize work
+
memory visibility
+
cache locality
+
allocation
+
GC
+
iteration workload
+
mapping-function cost
=
real ConcurrentHashMap performance
```

---

# 3.6.64 JMM Follow-Up

You should be able to explain:

```text
volatile
   ↓
visibility + ordering

CAS
   ↓
atomic conditional update

synchronized
   ↓
mutual exclusion + memory synchronization

ConcurrentHashMap
   ↓
combines these mechanisms
```

Then distinguish:

```text
visibility
vs.
atomicity
vs.
ordering
vs.
mutual exclusion
```

This is essential senior-level Java concurrency knowledge.

---

# 3.6.65 Advanced OpenJDK Follow-Up

Inspect the current OpenJDK source for:

```text
java.util.concurrent.ConcurrentHashMap
```

Study:

- [ ] `Node<K,V>`
- [ ] `TreeNode<K,V>`
- [ ] `TreeBin<K,V>`
- [ ] `ForwardingNode`
- [ ] `ReservationNode`
- [ ] `table`
- [ ] `nextTable`
- [ ] `sizeCtl`
- [ ] `transferIndex`
- [ ] `baseCount`
- [ ] `CounterCell`
- [ ] hash spreading
- [ ] bin selection
- [ ] CAS insertion
- [ ] synchronized bin updates
- [ ] tree bins
- [ ] resize
- [ ] cooperative transfer
- [ ] iterator
- [ ] spliterator
- [ ] `computeIfAbsent`
- [ ] `compute`
- [ ] `merge`
- [ ] `putIfAbsent`

Trace:

```text
put()
 ↓
hash()
 ↓
table initialization
 ↓
bin calculation
 ↓
CAS / collision path
 ↓
synchronized bin
 ↓
treeification / resize
```

And:

```text
get()
 ↓
hash()
 ↓
table
 ↓
bin
 ↓
node/tree lookup
 ↓
value
```

---

# 3.6.66 Advanced Source Investigation

Answer these questions from the OpenJDK source:

- [ ] Why is the table lazily initialized?
- [ ] How does CAS install the first node in an empty bin?
- [ ] Why is the first node of a bin important for synchronization?
- [ ] How does a tree bin coordinate concurrent access?
- [ ] What does `sizeCtl` represent in different states?
- [ ] How is resize work divided among threads?
- [ ] What is `transferIndex` used for?
- [ ] What does a forwarding node tell a reader?
- [ ] How are counts maintained under concurrency?
- [ ] Why does ConcurrentHashMap use counter cells?
- [ ] How does `computeIfAbsent` avoid duplicate initialization?
- [ ] What restrictions apply to mapping functions?

---

# 3.6.67 Interview Questions

## Basic

- [ ] What is ConcurrentHashMap?
- [ ] Why is it needed?
- [ ] Is ConcurrentHashMap thread-safe?
- [ ] Does it allow null keys?
- [ ] Does it allow null values?
- [ ] What is the difference between HashMap and ConcurrentHashMap?
- [ ] What is CAS?
- [ ] What is weakly consistent iteration?

## Intermediate

- [ ] Explain Java 7 ConcurrentHashMap architecture.
- [ ] What are segments?
- [ ] Explain Java 8+ ConcurrentHashMap architecture.
- [ ] Why was the segment design replaced?
- [ ] How are concurrent reads supported?
- [ ] How are concurrent updates coordinated?
- [ ] What are synchronized bins?
- [ ] Why are null keys/values prohibited?
- [ ] What is `putIfAbsent`?
- [ ] What is `computeIfAbsent`?
- [ ] What is `merge`?

## Advanced

- [ ] Explain CAS-based insertion.
- [ ] Explain volatile fields in CHM.
- [ ] Explain bin-level synchronization.
- [ ] Explain cooperative resizing.
- [ ] Explain forwarding nodes.
- [ ] Explain `sizeCtl`.
- [ ] Explain weakly consistent iterators.
- [ ] Explain why `get()` followed by `put()` is not an atomic update.
- [ ] Explain tree bins in ConcurrentHashMap.
- [ ] Explain Java 7 vs Java 8+ internals.

## Senior / Production

- [ ] When would you use ConcurrentHashMap over synchronizedMap?
- [ ] When would you use ConcurrentHashMap over HashMap + external locking?
- [ ] When would you use LongAdder with ConcurrentHashMap?
- [ ] How would you handle a hot key?
- [ ] How would you design a concurrent cache?
- [ ] How would you diagnose CHM contention?
- [ ] How would you reason about memory visibility?
- [ ] How would you safely store mutable objects as values?
- [ ] How would you implement a multi-key atomic update?
- [ ] What happens when resize occurs under heavy concurrent traffic?
- [ ] Why doesn't ConcurrentHashMap provide transactional semantics?
- [ ] How would you benchmark CHM against synchronizedMap?

---

# 3.6.68 Benchmarking Lab

Use JMH for serious measurements.

Compare:

```text
HashMap
Collections.synchronizedMap(new HashMap<>())
ConcurrentHashMap
```

Workloads:

```text
read-heavy
write-heavy
mixed read/write
single key
many keys
high collision
large map
small map
```

Measure:

- [ ] Throughput.
- [ ] Latency.
- [ ] Allocation.
- [ ] GC.
- [ ] CPU utilization.
- [ ] Contention.
- [ ] Scaling with thread count.

Test:

```text
1 thread
2 threads
4 threads
8 threads
16 threads
32 threads
```

Then explain why scaling is not necessarily linear.

---

# 3.6.69 Advanced Benchmark — Counter Designs

Compare:

```java
ConcurrentHashMap<String, Integer>
```

with:

```java
ConcurrentHashMap<String, AtomicInteger>
```

and:

```java
ConcurrentHashMap<String, LongAdder>
```

Test:

```text
low contention
medium contention
high contention
single hot key
many independent keys
```

Explain the results using:

```text
CAS contention
+
false sharing considerations
+
striped counters
+
allocation
+
cache coherence
```

---

# 3.6.70 Production Scenario — Concurrent Registry

Design:

```text
serviceId → ServiceMetadata
```

Requirements:

```text
many reads
occasional registration
updates
removals
monitoring iteration
```

Decide:

```text
HashMap + lock
ConcurrentHashMap
CopyOnWriteArrayList
other structure
```

Explain your decision.

---

# 3.6.71 Production Scenario — Memoization

Implement:

```java
computeIfAbsent()
```

for expensive object initialization.

Consider:

```text
1. Duplicate computation
2. Mapping-function cost
3. Failure
4. Exceptions
5. Recursive computation
6. Memory growth
7. Eviction
8. Cache stampede
```

Do not assume:

```text
ConcurrentHashMap = complete cache solution
```

---

# 3.6.72 Production Scenario — Metrics

Build:

```text
metricName → LongAdder
```

using:

```java
ConcurrentHashMap<String, LongAdder>
```

Then implement:

```text
increment
read
reset
snapshot
```

Discuss:

```text
high write concurrency
+
eventual snapshot semantics
+
memory growth
+
metric cardinality
```

---

# 3.6.73 Production Scenario — Mutable Values

Store:

```java
ConcurrentHashMap<String, UserSession>
```

where `UserSession` is mutable.

Create concurrent readers/writers.

Then redesign using:

```text
immutable UserSession
+
replace-on-update
```

Compare:

```text
shared mutation
vs.
immutable replacement
```

Explain which design is easier to reason about and why.

---

# 3.6.74 Final Mastery Gate

## Fundamentals

- [ ] Explain ConcurrentHashMap.
- [ ] Explain why HashMap is insufficient for concurrent mutation.
- [ ] Explain the ConcurrentMap abstraction.
- [ ] Explain core CHM APIs.
- [ ] Explain thread safety.

## Java 7 Internals

- [ ] Explain segmented architecture.
- [ ] Explain Segment.
- [ ] Explain segment-level locking.
- [ ] Explain concurrency level.
- [ ] Explain limitations of the segment model.

## Java 8+ Internals

- [ ] Explain Node[] table.
- [ ] Explain CAS.
- [ ] Explain volatile state.
- [ ] Explain synchronized bins.
- [ ] Explain tree bins.
- [ ] Explain fine-grained update coordination.
- [ ] Explain why global locking is avoided.

## Reads & Updates

- [ ] Explain concurrent reads.
- [ ] Explain concurrent updates.
- [ ] Explain `put`.
- [ ] Explain `get`.
- [ ] Explain `putIfAbsent`.
- [ ] Explain `computeIfAbsent`.
- [ ] Explain `compute`.
- [ ] Explain `merge`.
- [ ] Explain atomicity boundaries.

## Resizing

- [ ] Explain resize.
- [ ] Explain cooperative transfer.
- [ ] Explain forwarding nodes.
- [ ] Explain `sizeCtl`.
- [ ] Explain `transferIndex`.
- [ ] Explain concurrent resize/read interaction.

## Iteration

- [ ] Explain weakly consistent iteration.
- [ ] Explain why it is not fail-fast.
- [ ] Explain why it is not a snapshot.
- [ ] Explain what concurrent iteration can and cannot guarantee.

## Null Semantics

- [ ] Explain why null keys are prohibited.
- [ ] Explain why null values are prohibited.
- [ ] Explain how this simplifies `get()` semantics.
- [ ] Handle HashMap → CHM migration edge cases.

## JMM

- [ ] Explain visibility.
- [ ] Explain volatile semantics.
- [ ] Explain CAS.
- [ ] Explain atomicity.
- [ ] Explain synchronized memory semantics.
- [ ] Explain safe publication.
- [ ] Explain why thread-safe containers do not make contained objects thread-safe.

## Performance

- [ ] Explain contention.
- [ ] Explain hot-key behavior.
- [ ] Explain collision effects.
- [ ] Explain resizing costs.
- [ ] Compare `AtomicInteger` and `LongAdder`.
- [ ] Benchmark under different thread counts.

## Production

- [ ] Design a concurrent registry.
- [ ] Design concurrent counters.
- [ ] Design safe memoization.
- [ ] Design a concurrent cache.
- [ ] Handle mutable values correctly.
- [ ] Diagnose contention.
- [ ] Choose CHM vs synchronizedMap vs external locking.
- [ ] Explain multi-key atomicity limitations.

## Implementation

- [ ] Implement a simplified concurrent hash table.
- [ ] Implement CAS-based insertion conceptually.
- [ ] Implement synchronized-bin updates conceptually.
- [ ] Implement weakly consistent iteration conceptually.
- [ ] Explain concurrent resize conceptually.
- [ ] Explain the OpenJDK implementation from source.

---

# Final Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PERFORMANCE UNDERSTOOD
- [ ] CONCURRENCY MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
