# Module 3.1 — Collection Architecture
## Deep Mastery Guide

> **Goal:** Build a complete mental model of the Java Collections Framework hierarchy, contracts, iteration model, ordering semantics, and the distinction between `Collection` and `Map`.

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

# 3.1.1 `Iterable`

`Iterable<T>` represents an object that can provide an `Iterator<T>`.

Core API:

```java
Iterator<T> iterator();
```

It enables enhanced `for` loops:

```java
for (String name : names) {
    System.out.println(name);
}
```

Conceptually, iteration is:

```java
Iterator<String> it = names.iterator();

while (it.hasNext()) {
    String name = it.next();
}
```

### Study

- [ ] Definition and purpose
- [ ] `iterator()`
- [ ] Enhanced `for` loop
- [ ] `forEach()`
- [ ] `spliterator()`
- [ ] Implementing custom `Iterable`
- [ ] Iterable vs Iterator
- [ ] Iterator state and lifecycle

### Internal Understanding

```text
Iterable
   ↓
iterator()
   ↓
Iterator
   ↓
hasNext()
   ↓
next()
```

---

# 3.1.2 `Iterator`

`Iterator<T>` represents a traversal over elements.

Core operations:

```java
boolean hasNext();
T next();
void remove();
```

Modern APIs also include:

```java
forEachRemaining(...)
```

Example:

```java
Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {
    String name = iterator.next();
    System.out.println(name);
}
```

### Study

- [ ] Iterator state
- [ ] `hasNext()`
- [ ] `next()`
- [ ] `remove()`
- [ ] `forEachRemaining()`
- [ ] Iterator invalidation
- [ ] Fail-fast behavior
- [ ] `ConcurrentModificationException`
- [ ] Iterator vs collection modification

### Important

Fail-fast behavior is generally **best-effort** and is not a thread-safety mechanism.

---

# 3.1.3 `Collection`

`Collection<E>` is the primary interface representing a group of individual elements.

It extends:

```java
Iterable<E>
```

Common methods:

```java
add()
addAll()
remove()
removeAll()
contains()
containsAll()
size()
isEmpty()
clear()
iterator()
toArray()
stream()
parallelStream()
removeIf()
```

### Key Concept

`Collection` does not itself promise:

- ordering
- uniqueness
- sorting
- queue semantics

Those properties are defined by specialized subinterfaces.

---

# 3.1.4 `List`

`List<E>` represents an ordered collection.

Typical characteristics:

- [ ] Encounter order
- [ ] Positional/index-based access
- [ ] Duplicate elements generally allowed
- [ ] `get(index)`
- [ ] `set(index, value)`
- [ ] Insertion at an index
- [ ] Search by position/value

Example:

```java
List<String> names =
        new ArrayList<>();

names.add("Alice");
names.add("Bob");
names.add("Alice");
```

The duplicate `"Alice"` is valid.

### Important APIs

```java
get()
set()
add()
remove()
indexOf()
lastIndexOf()
subList()
sort()
```

### Edge Case

`subList()` is generally a **backed view**, not an independent copy.

```java
List<String> original =
        new ArrayList<>(List.of("A", "B", "C"));

List<String> sub =
        original.subList(0, 2);

sub.clear();
```

The original list is affected.

---

# 3.1.5 `Set`

`Set<E>` represents a collection that contains no duplicate elements according to the Set's equality/ordering semantics.

Example:

```java
Set<String> names = new HashSet<>();

names.add("Alice");
names.add("Alice");
```

The logical set contains one `"Alice"`.

### Important Implementations

```text
HashSet
LinkedHashSet
TreeSet
EnumSet
CopyOnWriteArraySet
```

### Critical Distinction

Different implementations determine uniqueness differently.

```text
HashSet
   ↓
hashCode() + equals()

TreeSet
   ↓
Comparator / compareTo()
```

Therefore:

```text
HashSet duplicate semantics
        ≠
TreeSet duplicate semantics
```

in some edge cases.

---

# 3.1.6 `Queue`

`Queue<E>` represents elements waiting for processing.

Typical operations:

```java
offer()
poll()
peek()
```

Exception-oriented alternatives:

```java
add()
remove()
element()
```

### Method Semantics

| Purpose | Exception form | Special-value form |
|---|---|---|
| Insert | `add(e)` | `offer(e)` |
| Remove head | `remove()` | `poll()` |
| Inspect head | `element()` | `peek()` |

### Important

Do not assume every Queue is FIFO.

Examples include:

```text
LinkedList
PriorityQueue
BlockingQueue implementations
```

which can have different ordering semantics.

---

# 3.1.7 `Deque`

`Deque<E>` means:

```text
Double-Ended Queue
```

Elements can be inserted and removed from both ends.

Important methods:

```java
addFirst()
addLast()

offerFirst()
offerLast()

removeFirst()
removeLast()

pollFirst()
pollLast()

peekFirst()
peekLast()
```

A Deque can act as a queue:

```java
addLast()
pollFirst()
```

or a stack:

```java
push()
pop()
peek()
```

For modern Java stack implementations, prefer `Deque` over the legacy `Stack` class in normal designs.

---

# 3.1.8 `Map`

`Map<K,V>` represents key-value associations.

Example:

```java
Map<String, Integer> ages =
        new HashMap<>();

ages.put("Alice", 30);
ages.put("Bob", 25);
```

Conceptually:

```text
Alice → 30
Bob   → 25
```

### Critical Rule

`Map` does **not** extend `Collection`.

Why?

```text
Collection
   ↓
individual elements

Map
   ↓
key → value associations
```

A Map provides collection-like views:

```java
keySet()
values()
entrySet()
```

---

# 3.1.9 Map Views

Example:

```java
for (Map.Entry<String, Integer> entry
        : ages.entrySet()) {

    System.out.println(
        entry.getKey() + "=" + entry.getValue()
    );
}
```

Important views:

```java
map.keySet()
map.values()
map.entrySet()
```

These are generally backed views rather than independent snapshots.

Study:

- [ ] Key view
- [ ] Values view
- [ ] Entry view
- [ ] Mutation through views
- [ ] View lifecycle
- [ ] Unsupported operations

---

# 3.1.10 Collection Hierarchy

Master the conceptual hierarchy:

```text
Iterable<E>
     |
     +-- Collection<E>
            |
            +-- List<E>
            |     |
            |     +-- ArrayList
            |     +-- LinkedList
            |     +-- Vector
            |     +-- CopyOnWriteArrayList
            |
            +-- Set<E>
            |     |
            |     +-- HashSet
            |     +-- LinkedHashSet
            |     +-- SortedSet<E>
            |            |
            |            +-- NavigableSet<E>
            |                   |
            |                   +-- TreeSet
            |
            +-- Queue<E>
                  |
                  +-- Deque<E>
                        |
                        +-- ArrayDeque
                        +-- LinkedList
```

Also know specialized/concurrent implementations separately.

---

# 3.1.11 Map Hierarchy

Master the major interface relationships:

```text
Map<K,V>
 |
 +-- SortedMap<K,V>
 |      |
 |      +-- NavigableMap<K,V>
 |             |
 |             +-- TreeMap
 |
 +-- ConcurrentMap<K,V>
 |      |
 |      +-- ConcurrentHashMap
 |
 +-- ConcurrentNavigableMap<K,V>
        |
        +-- ConcurrentSkipListMap
```

Important implementations outside these direct branches include:

```text
HashMap
LinkedHashMap
WeakHashMap
IdentityHashMap
EnumMap
Hashtable
```

Do not confuse implementation relationships with conceptual grouping.

---

# 3.1.12 Sorted and Navigable Collections

## `SortedSet`

Maintains elements according to a defined ordering.

## `NavigableSet`

Extends `SortedSet` with navigation operations such as:

```java
lower()
floor()
ceiling()
higher()
```

and descending/range operations.

Typical implementation:

```java
TreeSet
```

Similarly:

```text
SortedMap
    ↓
NavigableMap
    ↓
TreeMap
```

---

# 3.1.13 `ConcurrentMap`

`ConcurrentMap<K,V>` provides operations useful for concurrent map access.

Important operations include:

```java
putIfAbsent()
remove(key, value)
replace()
compute()
computeIfAbsent()
computeIfPresent()
merge()
```

Typical implementation:

```java
ConcurrentHashMap
```

Concurrency internals should be studied separately in the concurrency and collections-internals modules.

---

# 3.1.14 Interface vs Implementation

This distinction is fundamental.

```java
List<User> users =
        new ArrayList<>();
```

The variable depends on:

```text
List contract
```

while the object uses:

```text
ArrayList implementation
```

Likewise:

```java
Map<String, User> users =
        new HashMap<>();
```

### Why This Matters

The interface defines:

```text
what behavior is available
```

The implementation determines much of:

```text
how it is achieved
memory layout
performance
ordering details
concurrency behavior
```

---

# 3.1.15 Programming to Interfaces

Prefer:

```java
List<User>
Set<User>
Queue<Task>
Deque<Task>
Map<String, User>
```

over unnecessarily exposing:

```java
ArrayList<User>
HashSet<User>
HashMap<String, User>
```

This improves implementation flexibility.

But do not choose an overly generic interface if callers genuinely require implementation-specific semantics.

---

# 3.1.16 Memory / Runtime Behavior

The interface itself does not determine object layout.

Examples:

```text
List
 ↓
ArrayList
 ↓
backing array
```

versus:

```text
List
 ↓
LinkedList
 ↓
node objects + links
```

and:

```text
Map
 ↓
HashMap
 ↓
table + entries/tree bins
```

Therefore, when analyzing performance, identify the actual implementation.

---

# 3.1.17 Complexity Awareness

Typical expectations:

| Structure | Operation | Typical complexity |
|---|---|---:|
| `ArrayList` | `get(index)` | O(1) |
| `ArrayList` | append | Amortized O(1) |
| `LinkedList` | indexed access | O(n) |
| `HashSet` | `contains()` | Expected O(1) |
| `TreeSet` | `contains()` | O(log n) |
| `HashMap` | `get()` | Expected O(1) |
| `TreeMap` | `get()` | O(log n) |
| `ArrayDeque` | end operations | O(1) amortized |

These are general expectations, not absolute guarantees under every pathological condition.

---

# 3.1.18 `Iterable` vs `Iterator`

Memorize this distinction:

### Iterable

> "I can give you an iterator."

```java
Iterable<T>
```

### Iterator

> "I am currently traversing elements."

```java
Iterator<T>
```

Mental model:

```text
Iterable
   ↓
iterator()
   ↓
Iterator
   ↓
hasNext()
   ↓
next()
```

---

# 3.1.19 `Collection` vs `Map`

### Collection

Represents individual elements:

```text
E
E
E
```

Examples:

```java
List<E>
Set<E>
Queue<E>
```

### Map

Represents associations:

```text
K → V
K → V
K → V
```

Examples:

```java
Map<K,V>
```

A Map can expose:

```text
keys
values
entries
```

but is not itself a Collection.

---

# 3.1.20 List vs Set vs Queue vs Deque vs Map

| Interface | Primary purpose | Duplicates | Ordering |
|---|---|---|---|
| `List` | Ordered sequence | Usually yes | Encounter/positional |
| `Set` | Unique elements | No | Implementation-dependent |
| `Queue` | Processing queue | Usually yes | Implementation-dependent |
| `Deque` | Double-ended processing | Usually yes | Both ends |
| `Map` | Key-value lookup | Keys unique | Implementation-dependent |

---

# 3.1.21 Collection Default Methods

Modern Collection APIs contain default and stream-related methods.

Study:

```java
removeIf()
stream()
parallelStream()
forEach()
spliterator()
```

Understand why default interface methods allowed Java to evolve collection APIs while maintaining compatibility with existing implementations.

---

# 3.1.22 `Spliterator`

`Spliterator` supports traversal and partitioning.

Important methods:

```java
tryAdvance()
forEachRemaining()
trySplit()
characteristics()
estimateSize()
```

It is particularly important for:

```text
Streams
Parallel streams
Collection traversal
Work partitioning
```

Study characteristics such as:

```text
ORDERED
DISTINCT
SORTED
SIZED
NONNULL
IMMUTABLE
CONCURRENT
SUBSIZED
```

---

# 3.1.23 Collection Views

Some APIs expose views instead of copies.

Examples:

```java
map.keySet()
map.values()
map.entrySet()
list.subList(...)
```

Mental model:

```text
Original collection
        ↑
        |
      View
```

Mutation of the original may be visible through the view.

This is different from:

```text
Original collection
        ↓ copy
Independent collection
```

---

# 3.1.24 Null Semantics

Do not assume all collection implementations have the same null policy.

Examples:

```text
ArrayList
    → permits null

HashMap
    → permits null key/value

ConcurrentHashMap
    → does not permit null keys/values
```

Tree-based collections can also depend on their ordering/comparator rules.

Always check the implementation's API contract.

---

# 3.1.25 Immutable / Unmodifiable Collections

Modern Java provides:

```java
List.of(...)
Set.of(...)
Map.of(...)
List.copyOf(...)
Set.copyOf(...)
Map.copyOf(...)
```

Understand:

```text
unmodifiable structure
        ≠
deeply immutable object graph
```

If elements themselves are mutable, they can still change.

Also distinguish:

```java
Collections.unmodifiableList(original)
```

from:

```java
List.copyOf(original)
```

because one is a view while the other is an independent unmodifiable representation according to its contract.

---

# 3.1.26 Common Mistakes

- [ ] Thinking `Map` extends `Collection`.
- [ ] Confusing `Iterable` with `Iterator`.
- [ ] Assuming every Set is sorted.
- [ ] Assuming every Queue is FIFO.
- [ ] Assuming every List is array-backed.
- [ ] Returning implementation types unnecessarily.
- [ ] Using raw collections.
- [ ] Modifying a collection directly during iteration.
- [ ] Treating fail-fast as thread-safety.
- [ ] Treating views as copies.
- [ ] Assuming all implementations allow null.
- [ ] Assuming HashSet and TreeSet use identical duplicate semantics.
- [ ] Assuming `Map` views are independent copies.
- [ ] Ignoring implementation-specific performance.

---

# 3.1.27 Edge Cases

Investigate:

- [ ] Empty collection
- [ ] Single-element collection
- [ ] Duplicate elements
- [ ] Null elements
- [ ] Null keys
- [ ] Null values
- [ ] Concurrent modification
- [ ] Unsupported optional operations
- [ ] Immutable collections
- [ ] Unmodifiable views
- [ ] Backed views
- [ ] Comparator inconsistent with equals
- [ ] Very large collections
- [ ] Memory pressure
- [ ] Generic type safety

---

# 3.1.28 Debugging Challenges

## Challenge 1 — Iterable vs Iterator

Analyze:

```java
Iterator<String> iterator = names.iterator();

for (String name : iterator) {
    System.out.println(name);
}
```

Tasks:

- [ ] Determine whether it compiles.
- [ ] Explain why.
- [ ] Explain the roles of Iterable and Iterator.
- [ ] Design a custom type that can be used in enhanced `for`.

---

## Challenge 2 — Map Is Not Collection

Try:

```java
Collection<String> collection =
        new HashMap<>();
```

Tasks:

- [ ] Explain the compiler error.
- [ ] Demonstrate `keySet()`.
- [ ] Demonstrate `values()`.
- [ ] Demonstrate `entrySet()`.

---

## Challenge 3 — Backed `subList()`

```java
List<String> list =
        new ArrayList<>(List.of("A", "B", "C"));

List<String> sub =
        list.subList(0, 2);

sub.clear();
```

Tasks:

- [ ] Predict the original list.
- [ ] Explain the relationship between the lists.
- [ ] Create an independent copy.

---

## Challenge 4 — Direct Modification During Iteration

Debug:

```java
for (String value : list) {
    if (value.isBlank()) {
        list.remove(value);
    }
}
```

Fix it using:

```java
Iterator
```

Then compare it with:

```java
list.removeIf(String::isBlank);
```

---

## Challenge 5 — Wrong API Abstraction

Given:

```java
public ArrayList<User> getUsers()
```

Tasks:

- [ ] Refactor to an appropriate interface.
- [ ] Explain why.
- [ ] Identify cases where returning a concrete implementation could be justified.

---

# 3.1.29 Coding Exercises

## Basic

- [ ] Create an `ArrayList` through `List`.
- [ ] Create a `HashSet` through `Set`.
- [ ] Create a `HashMap` through `Map`.
- [ ] Iterate using enhanced `for`.
- [ ] Iterate using `Iterator`.
- [ ] Iterate through `Map.entrySet()`.
- [ ] Use a Queue.
- [ ] Use a Deque.
- [ ] Implement a custom Iterable.

## Intermediate

- [ ] Implement a custom collection wrapper.
- [ ] Implement a custom Iterator.
- [ ] Implement an iterator for a tree.
- [ ] Compare List and Set duplicate behavior.
- [ ] Compare Queue and Deque operations.
- [ ] Demonstrate iterator removal.
- [ ] Demonstrate `subList()` view behavior.
- [ ] Demonstrate Map collection views.
- [ ] Demonstrate immutable collection behavior.
- [ ] Build an API that depends only on collection interfaces.

## Advanced

- [ ] Implement a custom `Collection`.
- [ ] Implement a custom `Iterator`.
- [ ] Implement a custom `Spliterator`.
- [ ] Analyze Spliterator characteristics.
- [ ] Build a collection wrapper exposing only required operations.
- [ ] Benchmark List implementations.
- [ ] Measure memory differences between collection implementations.
- [ ] Create tests for interface-contract assumptions.

## Production-Style

Design an:

```text
Employee Repository API
```

Possible API:

```java
List<Employee> findAll();

Optional<Employee> findById(EmployeeId id);

Set<EmployeeId> findIds();

Queue<Employee> pendingEmployees();

Map<EmployeeId, Employee> findByIds(...);
```

Requirements:

- [ ] Choose appropriate interfaces.
- [ ] Hide unnecessary implementation details.
- [ ] Document ordering.
- [ ] Document duplicate semantics.
- [ ] Document null behavior.
- [ ] Document mutability.
- [ ] Document thread-safety.
- [ ] Test against interface contracts.
- [ ] Avoid leaking mutable internal collections.

---

# 3.1.30 Interview Questions

## Basic

- [ ] What is the Java Collections Framework?
- [ ] What is `Iterable`?
- [ ] What is `Iterator`?
- [ ] What is `Collection`?
- [ ] What is `List`?
- [ ] What is `Set`?
- [ ] What is `Queue`?
- [ ] What is `Deque`?
- [ ] What is `Map`?
- [ ] Why doesn't Map extend Collection?

## Intermediate

- [ ] Difference between Iterable and Iterator?
- [ ] How does enhanced `for` work?
- [ ] What is fail-fast behavior?
- [ ] How does HashSet determine duplicates?
- [ ] Is every Set unordered?
- [ ] Is every Queue FIFO?
- [ ] Difference between Queue and Deque?
- [ ] Difference between `add()` and `offer()`?
- [ ] Difference between `remove()` and `poll()`?
- [ ] Difference between `element()` and `peek()`?
- [ ] Why program to interfaces?

## Advanced

- [ ] Explain the Collection hierarchy.
- [ ] Explain the Map hierarchy.
- [ ] Explain SortedMap vs NavigableMap.
- [ ] Explain SortedSet vs NavigableSet.
- [ ] Explain ConcurrentMap.
- [ ] Explain collection views.
- [ ] Explain `subList()` behavior.
- [ ] Explain Iterator state.
- [ ] Explain Spliterator.
- [ ] Explain fail-fast limitations.
- [ ] Explain interface contracts vs implementation behavior.

## Senior / Production

- [ ] How do you choose between List, Set, Queue, Deque, and Map?
- [ ] How would you design a collection-returning API?
- [ ] When should an API return an immutable collection?
- [ ] How would you document ordering guarantees?
- [ ] How would you document null behavior?
- [ ] How would you prevent callers from depending on implementation details?
- [ ] How does collection abstraction support good API design?
- [ ] How would you diagnose `ConcurrentModificationException`?
- [ ] How would you select a collection for millions of objects?
- [ ] How would you reason about memory locality and allocation?

---

# 3.1.31 Advanced Follow-ups

## OpenJDK Source

Inspect:

```text
java.lang.Iterable
java.util.Iterator
java.util.Collection
java.util.List
java.util.Set
java.util.Queue
java.util.Deque
java.util.Map
```

Then inspect representative implementations:

```text
ArrayList
LinkedList
HashSet
LinkedHashSet
HashMap
LinkedHashMap
TreeSet
TreeMap
ArrayDeque
```

Study:

- [ ] Interface contracts
- [ ] Default methods
- [ ] Iterator implementations
- [ ] Modification tracking
- [ ] Backing structures
- [ ] Spliterator implementations
- [ ] Complexity
- [ ] Memory behavior

---

# 3.1.32 Specification Study

Understand the distinction:

```text
Java language rules
        ↓
JLS

Library behavior/contracts
        ↓
Java API Specification

Actual implementation
        ↓
OpenJDK source
```

Do not treat an OpenJDK implementation detail as a universal language guarantee.

---

# 3.1.33 Performance Implications

Interface choice defines semantics; implementation choice determines much of the runtime behavior.

For:

```java
List<User> users;
```

you must still identify whether the implementation is:

```text
ArrayList
LinkedList
CopyOnWriteArrayList
```

Analyze:

- [ ] Access pattern
- [ ] Insertion pattern
- [ ] Removal pattern
- [ ] Search frequency
- [ ] Iteration frequency
- [ ] Memory locality
- [ ] Allocation behavior
- [ ] Boxing
- [ ] Cache locality
- [ ] Concurrency requirements

---

# 3.1.34 Production Use Cases

## List

Use when:

```text
order matters
duplicates matter
sequence/index semantics matter
```

Examples:

- API response items
- Search results
- Ordered records

## Set

Use when:

```text
uniqueness matters
```

Examples:

- Permissions
- Tags
- Feature flags
- Unique IDs

## Queue

Use when:

```text
items await processing
```

Examples:

- Background tasks
- Work queues
- Processing pipelines

## Deque

Use when:

```text
both ends matter
```

Examples:

- Stack behavior
- Sliding-window algorithms
- BFS/DFS
- Double-ended scheduling

## Map

Use when:

```text
key → value lookup
```

Examples:

- ID → Entity
- Code → Configuration
- Username → Account
- Cache key → Cached value

---

# 3.1.35 Production Trade-offs

Before choosing a collection, ask:

```text
1. Are duplicates allowed?
2. Does order matter?
3. Is sorting required?
4. Is lookup by key required?
5. What operations dominate?
6. What is the expected size?
7. Is random access required?
8. Is concurrent access required?
9. Is null allowed?
10. Should the collection be mutable?
11. Is deterministic iteration order required?
12. Does memory footprint matter?
13. Do callers need a live view or snapshot?
```

---

# 3.1.36 Final Mastery Gate

## Iterable

- [ ] Explain `Iterable`.
- [ ] Explain `iterator()`.
- [ ] Explain enhanced `for`.
- [ ] Implement a custom Iterable.
- [ ] Explain iteration state.

## Iterator

- [ ] Explain Iterator.
- [ ] Implement an Iterator.
- [ ] Use `hasNext()` and `next()`.
- [ ] Understand `remove()`.
- [ ] Explain fail-fast behavior.
- [ ] Debug modification during iteration.

## Collection

- [ ] Explain Collection.
- [ ] Explain common operations.
- [ ] Explain its relationship with Iterable.
- [ ] Understand optional operations.
- [ ] Understand views and mutability.

## List

- [ ] Explain List semantics.
- [ ] Explain ordering.
- [ ] Explain duplicates.
- [ ] Explain positional access.
- [ ] Explain `subList()`.

## Set

- [ ] Explain Set semantics.
- [ ] Explain uniqueness.
- [ ] Distinguish HashSet/LinkedHashSet/TreeSet.
- [ ] Understand equality vs ordering semantics.

## Queue

- [ ] Explain Queue.
- [ ] Explain head semantics.
- [ ] Understand `offer/poll/peek`.
- [ ] Understand `add/remove/element`.
- [ ] Understand that Queue does not universally mean FIFO.

## Deque

- [ ] Explain double-ended operations.
- [ ] Use Deque as a queue.
- [ ] Use Deque as a stack.
- [ ] Understand ArrayDeque conceptually.

## Map

- [ ] Explain Map.
- [ ] Explain why Map is not Collection.
- [ ] Explain key/value/entry views.
- [ ] Explain Map hierarchy.
- [ ] Explain SortedMap/NavigableMap.
- [ ] Explain ConcurrentMap conceptually.

## Architecture

- [ ] Draw Collection hierarchy.
- [ ] Draw Map hierarchy.
- [ ] Distinguish interface from implementation.
- [ ] Choose the correct collection abstraction.
- [ ] Explain ordering/uniqueness semantics.
- [ ] Explain view vs copy.
- [ ] Discuss performance and memory trade-offs.
- [ ] Debug collection behavior.
- [ ] Use collections correctly in production code.

---

# Final Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PERFORMANCE UNDERSTOOD
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
