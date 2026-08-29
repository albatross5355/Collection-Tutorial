# Module 3.8 — Sets
## HashSet, LinkedHashSet, TreeSet, EnumSet, Ordering & Uniqueness

> **Goal:** Master Java Set implementations from the `Set` contract through hashing, ordering, uniqueness semantics, tree-based ordering, enum-specialized storage, equality/hash-code behavior, internal structures, memory/runtime behavior, edge cases, performance, production use cases, debugging, and interview-level trade-offs.

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

# 3.8.1 Set Fundamentals

A `Set<E>` represents a collection whose contract does not permit duplicate elements.

```java
Set<String> languages = new HashSet<>();

languages.add("Java");
languages.add("Spring");
languages.add("Java");

System.out.println(languages.size()); // 2
```

The definition of "duplicate" depends on the implementation:

| Set | Uniqueness / ordering basis |
|---|---|
| `HashSet` | `hashCode()` + `equals()` |
| `LinkedHashSet` | `hashCode()` + `equals()` + insertion ordering |
| `TreeSet` | `compareTo()` / `Comparator` |
| `EnumSet` | Enum constants |

---

# 3.8.2 Why Java Has Sets

Sets are optimized for requirements such as:

- [ ] Membership testing.
- [ ] Duplicate elimination.
- [ ] Unique IDs.
- [ ] Unique permissions.
- [ ] Visited-node tracking.
- [ ] Deduplication.
- [ ] Set union/intersection/difference.

Typical operations:

```text
add
contains
remove
```

---

# 3.8.3 Set Implementations

| Implementation | Ordering | Internal structure | Typical complexity |
|---|---|---|---|
| `HashSet` | No guaranteed order | HashMap-backed | Expected O(1) |
| `LinkedHashSet` | Insertion order | Hash-based + linked ordering | Expected O(1) |
| `TreeSet` | Sorted order | Red-black tree / TreeMap-backed | O(log N) |
| `EnumSet` | Enum declaration order | Specialized enum representation | Very efficient |

---

# 3.8.4 HashSet

## What Is It?

`HashSet` is the general-purpose hash-based Set implementation.

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
```

Only two unique elements remain.

## Internal Working

Conceptually:

```text
HashSet
   |
   v
HashMap<E, Object>
   |
   +-- key   = element
   +-- value = shared dummy object
```

A simplified conceptual implementation is:

```java
private transient HashMap<E, Object> map;
private static final Object PRESENT = new Object();
```

The current JDK source should be consulted for exact implementation details.

## `add()`

Conceptually:

```text
set.add(element)
      |
      v
HashMap.put(element, PRESENT)
      |
      v
hash
      |
      v
bucket
      |
      v
equals comparison
      |
   +--+--+
   |     |
 new   existing
   |       |
 insert   reject
```

Return value:

```java
true  // Set changed
false // Element already existed
```

---

# 3.8.5 HashSet Uniqueness

HashSet depends on the `equals()` / `hashCode()` contract.

If:

```java
a.equals(b)
```

is true, then:

```java
a.hashCode() == b.hashCode()
```

must also be true.

Otherwise, logically equal objects may occupy different buckets and behave incorrectly.

---

# 3.8.6 Mutable Elements in HashSet

Avoid changing fields involved in:

```text
equals()
+
hashCode()
```

after insertion.

Example:

```java
Set<User> users = new HashSet<>();

User user = new User("alice");
users.add(user);

user.setUsername("bob");
```

Now:

```java
users.contains(user)
```

may unexpectedly return `false`.

Reason:

```text
old hash
  ↓
old bucket

mutation
  ↓
new hash

lookup
  ↓
different bucket
```

Prefer immutable elements when possible.

---

# 3.8.7 HashSet Ordering

HashSet provides:

```text
no guaranteed iteration order
```

Never depend on an observed order.

If insertion order matters:

```text
LinkedHashSet
```

If sorted order matters:

```text
TreeSet
```

---

# 3.8.8 HashSet Performance

Expected average:

```text
add       O(1)
contains  O(1)
remove    O(1)
```

Actual performance depends on:

```text
hash distribution
+
collisions
+
table size
+
memory locality
+
GC
```

Memory includes:

```text
table
+
nodes
+
references
+
backing HashMap structure
```

---

# 3.8.9 LinkedHashSet

## What Is It?

`LinkedHashSet` is a Set that maintains predictable insertion order while retaining hash-based membership.

```java
Set<String> set = new LinkedHashSet<>();

set.add("C");
set.add("A");
set.add("B");

System.out.println(set);
```

Typical:

```text
[C, A, B]
```

---

# 3.8.10 LinkedHashSet Internal Model

Conceptually:

```text
Hash-based structure
       +
linked ordering

HEAD
 ↓
C ↔ A ↔ B
         ↑
        TAIL
```

The implementation uses LinkedHashMap-style ordering machinery.

This adds memory overhead compared with HashSet.

---

# 3.8.11 LinkedHashSet Ordering

Its important ordering guarantee is:

```text
insertion order
```

If:

```text
C
A
B
```

are inserted, iteration follows:

```text
C
A
B
```

Adding an existing element does not create a duplicate.

Do not confuse LinkedHashSet with an access-order LRU structure.

---

# 3.8.12 LinkedHashSet vs LinkedHashMap

Both preserve insertion ordering, but they represent different abstractions:

```text
LinkedHashSet
→ unique elements

LinkedHashMap
→ key/value associations
```

For LRU caches, use:

```text
LinkedHashMap with access-order
```

rather than LinkedHashSet.

---

# 3.8.13 LinkedHashSet Performance

Typical:

```text
add       O(1) expected
contains  O(1) expected
remove    O(1) expected
```

Trade-off:

```text
predictable ordering
+
additional memory/link maintenance
```

---

# 3.8.14 TreeSet

## What Is It?

`TreeSet` is a `NavigableSet` that maintains elements in sorted order.

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

---

# 3.8.15 TreeSet Internal Working

Conceptually:

```text
TreeSet
   |
   v
TreeMap
   |
   v
Red-Black Tree
```

The Set elements behave as the keys of the underlying sorted map structure.

---

# 3.8.16 Red-Black Tree

A red-black tree is a self-balancing binary search tree.

Its balancing rules prevent the tree from degenerating into a linked list.

Therefore:

```text
add       O(log N)
contains  O(log N)
remove    O(log N)
```

This enables:

```text
sorted iteration
+
nearest-element queries
+
range queries
```

---

# 3.8.17 TreeSet Uniqueness

This is a critical distinction.

TreeSet uses:

```text
compareTo()
```

or:

```text
Comparator.compare()
```

to determine ordering.

If:

```java
compare(a, b) == 0
```

TreeSet treats them as the same element for Set purposes.

Therefore:

```text
compare(a,b) == 0
```

can occur even when:

```java
!a.equals(b)
```

---

# 3.8.18 Comparator-Based Uniqueness Trap

Example:

```java
TreeSet<String> set =
    new TreeSet<>(
        Comparator.comparingInt(String::length)
    );

set.add("Java");
set.add("Code");
```

Both have length 4.

The comparator returns:

```text
0
```

so one may be treated as a duplicate.

Fix with a secondary comparison if full-value uniqueness is required:

```java
Comparator<String> comparator =
    Comparator.comparingInt(String::length)
              .thenComparing(Comparator.naturalOrder());
```

---

# 3.8.19 TreeSet Navigation

Master:

```java
first()
last()

lower()
floor()
ceiling()
higher()

pollFirst()
pollLast()

headSet()
tailSet()
subSet()

descendingSet()
```

Example:

```java
TreeSet<Integer> set =
        new TreeSet<>(List.of(10, 20, 30, 40));

set.floor(25);   // 20
set.ceiling(25); // 30
```

---

# 3.8.20 TreeSet Range Queries

Example:

```java
NavigableSet<Integer> range =
    set.subSet(20, true, 40, false);
```

Understand:

```text
view
vs.
copy
```

Many sorted collection range methods return views connected to the original collection.

---

# 3.8.21 TreeSet Comparator and Comparable

Without a comparator:

```java
TreeSet<Employee> employees =
    new TreeSet<>();
```

the elements need compatible natural ordering.

With a comparator:

```java
TreeSet<Employee> employees =
    new TreeSet<>(Comparator.comparing(Employee::getId));
```

The comparator becomes part of the Set's ordering and uniqueness semantics.

---

# 3.8.22 TreeSet Mutable Elements

If the fields used for ordering are changed after insertion:

```text
old ordering
   ↓
mutation
   ↓
new ordering
```

the tree can no longer be relied upon to behave according to its intended ordering.

Avoid mutable ordering keys.

---

# 3.8.23 EnumSet

## What Is It?

`EnumSet<E extends Enum<E>>` is a specialized Set implementation for enum constants.

```java
enum Permission {
    READ,
    WRITE,
    DELETE
}

EnumSet<Permission> permissions =
    EnumSet.of(
        Permission.READ,
        Permission.WRITE
    );
```

---

# 3.8.24 Why EnumSet Is Special

Enums have:

```text
fixed universe
+
known constants
+
ordinal positions
```

EnumSet can exploit this structure.

Conceptually:

```text
READ   → bit 0
WRITE  → bit 1
DELETE → bit 2
```

Membership can therefore be represented very compactly.

---

# 3.8.25 EnumSet Internal Implementations

Current JDK implementations include specialized forms such as:

```text
RegularEnumSet
JumboEnumSet
```

Conceptually:

```text
small enum universe
      ↓
machine-word bit representation

large enum universe
      ↓
multiple-word representation
```

Study the target JDK source for exact thresholds and implementation details.

---

# 3.8.26 EnumSet Ordering

EnumSet iteration follows enum declaration order.

```java
enum State {
    NEW,
    RUNNING,
    COMPLETE
}
```

Iteration follows:

```text
NEW
RUNNING
COMPLETE
```

---

# 3.8.27 EnumSet Factory Methods

Master:

```java
EnumSet.noneOf(...)
EnumSet.allOf(...)
EnumSet.of(...)
EnumSet.range(...)
EnumSet.complementOf(...)
EnumSet.copyOf(...)
```

Example:

```java
EnumSet<State> active =
    EnumSet.of(State.NEW, State.RUNNING);
```

---

# 3.8.28 EnumSet Set Operations

EnumSet is excellent for:

```text
union
intersection
difference
complement
```

Example:

```java
EnumSet<Permission> common =
    EnumSet.copyOf(first);

common.retainAll(second);
```

---

# 3.8.29 EnumSet Null Behavior

EnumSet does not permit:

```text
null
```

as an element because all elements must be enum constants.

---

# 3.8.30 Set Ordering Comparison

| Set | Ordering |
|---|---|
| HashSet | No guaranteed order |
| LinkedHashSet | Insertion order |
| TreeSet | Sorted order |
| EnumSet | Enum declaration order |

This distinction should be immediately clear when selecting an implementation.

---

# 3.8.31 Uniqueness Comparison

| Set | How uniqueness is determined |
|---|---|
| HashSet | `hashCode()` + `equals()` |
| LinkedHashSet | `hashCode()` + `equals()` |
| TreeSet | `compareTo()` / `Comparator` |
| EnumSet | Enum constants |

---

# 3.8.32 HashSet vs LinkedHashSet vs TreeSet vs EnumSet

| Feature | HashSet | LinkedHashSet | TreeSet | EnumSet |
|---|---|---|---|---|
| Unique elements | Yes | Yes | Yes | Yes |
| Order | None guaranteed | Insertion | Sorted | Declaration |
| Average add | O(1) | O(1) | O(log N) | Very efficient |
| Average contains | O(1) | O(1) | O(log N) | Very efficient |
| Null element | Yes | Yes | Usually no with natural ordering | No |
| Custom Comparator | No | No | Yes | No |
| Enum-only | No | No | No | Yes |
| Range queries | No | No | Yes | No |

---

# 3.8.33 Set Operations

Master:

```java
addAll()       // union-like
retainAll()    // intersection-like
removeAll()    // difference-like
containsAll()  // subset-like check
```

Example:

```java
Set<Integer> a =
    new HashSet<>(Set.of(1, 2, 3));

Set<Integer> b =
    Set.of(2, 3, 4);

a.retainAll(b);

System.out.println(a); // [2, 3]
```

Remember:

> Many Set operations mutate the receiving collection.

---

# 3.8.34 Set Equality

Set equality is membership-based rather than order-based.

Conceptually:

```text
{A, B, C}
```

and:

```text
{C, A, B}
```

contain the same members and can be equal as Sets.

This differs from Lists, where order is part of equality.

---

# 3.8.35 Mutable-Key / Mutable-Element Hazards

For HashSet:

```text
equals/hashCode changes
→ lookup corruption
```

For TreeSet:

```text
ordering changes
→ tree ordering assumptions break
```

General rule:

> Elements whose equality or ordering participates in Set membership should preferably be immutable while stored in the Set.

---

# 3.8.36 Common Mistakes

- [ ] Assuming HashSet has stable order.
- [ ] Assuming LinkedHashSet supports access-order.
- [ ] Using LinkedHashSet as an LRU cache.
- [ ] Forgetting equals/hashCode for HashSet elements.
- [ ] Mutating HashSet elements after insertion.
- [ ] Assuming TreeSet uses equals() for uniqueness.
- [ ] Using a Comparator that returns zero for distinct objects unintentionally.
- [ ] Mutating TreeSet ordering fields.
- [ ] Using TreeSet when sorting is unnecessary.
- [ ] Using HashSet when insertion order matters.
- [ ] Ignoring EnumSet for enum-only data.
- [ ] Assuming EnumSet allows null.
- [ ] Mutating the receiving Set with `retainAll()` accidentally.
- [ ] Assuming Set equality depends on iteration order.
- [ ] Assuming all Set implementations have the same uniqueness semantics.

---

# 3.8.37 Edge Cases

## HashSet

- [ ] Duplicate equal objects.
- [ ] Different objects with same hash.
- [ ] Mutable hash fields.
- [ ] Null.
- [ ] Heavy collisions.
- [ ] Resize.
- [ ] Iteration during mutation.

## LinkedHashSet

- [ ] Duplicate insertion.
- [ ] Re-inserting existing element.
- [ ] Insertion order.
- [ ] Null.
- [ ] Memory overhead.
- [ ] Concurrent access.

## TreeSet

- [ ] Comparator returns zero.
- [ ] Comparable inconsistent with equals.
- [ ] Null.
- [ ] Mutable ordering fields.
- [ ] Range views.
- [ ] Descending views.
- [ ] Custom Comparator.

## EnumSet

- [ ] Empty set.
- [ ] All constants.
- [ ] Complement.
- [ ] Range.
- [ ] Null.
- [ ] Large enum universe.
- [ ] Declaration order.

---

# 3.8.38 Coding Exercises

## Basic

- [ ] Remove duplicates from a List using HashSet.
- [ ] Check membership using HashSet.
- [ ] Count unique values.
- [ ] Preserve insertion order using LinkedHashSet.
- [ ] Sort unique values using TreeSet.
- [ ] Create an EnumSet.
- [ ] Iterate over all four implementations.

## Intermediate

- [ ] Find union of two Sets.
- [ ] Find intersection.
- [ ] Find difference.
- [ ] Find symmetric difference.
- [ ] Detect duplicates in a List.
- [ ] Build a unique username registry.
- [ ] Build a sorted unique timestamp Set.
- [ ] Implement enum-based permissions using EnumSet.

## Advanced

- [ ] Build a duplicate-detection pipeline.
- [ ] Build a unique event registry.
- [ ] Build a permission engine using EnumSet.
- [ ] Build a sorted active-user Set using TreeSet.
- [ ] Demonstrate comparator-based uniqueness.
- [ ] Demonstrate mutable-element corruption.
- [ ] Benchmark all four implementations.
- [ ] Implement a concurrent Set using `ConcurrentHashMap.newKeySet()`.

## Production-Style

- [ ] Design unique request-ID tracking.
- [ ] Design ordered unique event processing.
- [ ] Design sorted unique timestamps.
- [ ] Design an enum-based permission model.
- [ ] Design a concurrent membership registry.
- [ ] Design memory limits for a growing Set.

---

# 3.8.39 Production Scenario — Unique Request IDs

Requirement:

```text
Track request IDs
Reject duplicates
Fast membership checks
```

Choose:

```text
HashSet
```

Then analyze:

```text
memory growth
+
expiration
+
concurrency
+
distributed deployment
+
persistence
```

Important:

> A local HashSet does not provide distributed uniqueness.

---

# 3.8.40 Production Scenario — Ordered Unique Events

Requirements:

```text
unique events
+
insertion order
+
fast membership
```

Choose:

```text
LinkedHashSet
```

Then discuss:

```text
memory overhead
+
thread safety
+
capacity
+
eviction
+
persistence
```

---

# 3.8.41 Production Scenario — Sorted Unique Timestamps

Requirements:

```text
unique
+
sorted
+
nearest timestamp
+
range queries
```

Choose:

```java
TreeSet<Instant>
```

Use:

```java
floor()
ceiling()
subSet()
```

Discuss:

```text
duplicate timestamps
+
time precision
+
time zones
+
concurrency
```

---

# 3.8.42 Production Scenario — Permission Engine

Create:

```java
enum Permission {
    READ,
    WRITE,
    DELETE,
    ADMIN
}
```

Use:

```java
EnumSet<Permission>
```

Implement:

- [ ] Grant.
- [ ] Revoke.
- [ ] Check.
- [ ] Check multiple permissions.
- [ ] Compute common permissions.
- [ ] Compute missing permissions.
- [ ] Role-to-permission mapping.

Explain why EnumSet is preferable to HashSet.

---

# 3.8.43 Concurrent Sets

These Set implementations are not inherently thread-safe.

For concurrent membership:

```java
Set<String> set =
    ConcurrentHashMap.newKeySet();
```

Compare:

```text
HashSet
Collections.synchronizedSet(...)
ConcurrentHashMap.newKeySet()
```

Study:

```text
locking
+
iteration semantics
+
concurrency
+
throughput
```

Important:

```text
thread-safe collection
≠
thread-safe elements
```

---

# 3.8.44 Immutable Sets

Java provides:

```java
Set.of(...)
Set.copyOf(...)
Collections.unmodifiableSet(...)
```

Example:

```java
Set<String> languages =
    Set.of("Java", "Python", "C++");
```

Understand:

```text
immutable Set
vs.
unmodifiable view
```

and:

```text
shallow immutability
vs.
deep immutability
```

---

# 3.8.45 Stream Interaction

Compare:

```java
set
```

with:

```java
stream.distinct()
```

Example:

```java
List<String> unique =
    values.stream()
          .distinct()
          .toList();
```

Study:

```text
ordering
+
state
+
memory
+
parallel streams
```

Do not assume `distinct()` has identical behavior to manually constructing every possible Set implementation.

---

# 3.8.46 Set vs List

Use a Set when requirements emphasize:

```text
uniqueness
+
membership
```

Use a List when requirements emphasize:

```text
sequence
+
position
+
duplicates
```

For repeated membership checks over large collections, understand why:

```text
List.contains()
```

may be much more expensive than:

```text
HashSet.contains()
```

---

# 3.8.47 Performance Lab

Benchmark:

```text
HashSet
LinkedHashSet
TreeSet
EnumSet
```

Operations:

```text
add
contains
remove
iteration
```

Dataset sizes:

```text
10
1,000
100,000
1,000,000
```

Measure:

- [ ] Throughput.
- [ ] Latency.
- [ ] Memory.
- [ ] Allocation.
- [ ] CPU.
- [ ] GC.

Explain:

```text
O(1)
vs.
O(log N)
vs.
specialized enum representation
```

while considering real-world constant factors and memory locality.

---

# 3.8.48 OpenJDK Source Investigation

Inspect the current OpenJDK source for:

```text
HashSet
LinkedHashSet
TreeSet
EnumSet
```

## HashSet

Study:

- [ ] Backing HashMap.
- [ ] `PRESENT`.
- [ ] `add`.
- [ ] `remove`.
- [ ] `contains`.
- [ ] Iterator.
- [ ] Serialization.

## LinkedHashSet

Study:

- [ ] Relationship with HashSet.
- [ ] Linked ordering.
- [ ] Insertion-order maintenance.
- [ ] Entry linkage.

## TreeSet

Study:

- [ ] Backing TreeMap.
- [ ] Comparator.
- [ ] Natural ordering.
- [ ] Navigation.
- [ ] Range views.
- [ ] Red-black tree relationship.

## EnumSet

Study:

- [ ] Abstract EnumSet design.
- [ ] `RegularEnumSet`.
- [ ] `JumboEnumSet`.
- [ ] Enum universe.
- [ ] Bit representation.
- [ ] `add`.
- [ ] `remove`.
- [ ] `contains`.
- [ ] `complementOf`.
- [ ] Iteration.

---

# 3.8.49 Advanced Internal Questions

- [ ] Why does HashSet use HashMap?
- [ ] Why is the dummy value shared?
- [ ] How does HashSet detect duplicates?
- [ ] How does LinkedHashSet maintain insertion order?
- [ ] Why does LinkedHashSet not provide access-order?
- [ ] Why does TreeSet use TreeMap?
- [ ] How does TreeSet determine uniqueness?
- [ ] What happens when Comparator returns zero?
- [ ] Why can TreeSet contain fewer elements than expected?
- [ ] How does EnumSet represent membership?
- [ ] When is RegularEnumSet used?
- [ ] When is JumboEnumSet used?
- [ ] Why can EnumSet be memory-efficient?
- [ ] Why does EnumSet iterate in declaration order?

---

# 3.8.50 Interview Questions

## Basic

- [ ] What is a Set?
- [ ] How is Set different from List?
- [ ] What is HashSet?
- [ ] What is LinkedHashSet?
- [ ] What is TreeSet?
- [ ] What is EnumSet?
- [ ] Does HashSet preserve order?
- [ ] Does LinkedHashSet preserve order?
- [ ] Does TreeSet preserve order?
- [ ] What is EnumSet's ordering?

## Intermediate

- [ ] How does HashSet guarantee uniqueness?
- [ ] Why must equals and hashCode be consistent?
- [ ] How does HashSet use HashMap?
- [ ] How does LinkedHashSet maintain insertion order?
- [ ] How does TreeSet maintain sorted order?
- [ ] What data structure does TreeSet use?
- [ ] What happens when TreeSet's comparator returns zero?
- [ ] Why can TreeSet consider two non-equal objects duplicates?
- [ ] Why is EnumSet efficient?
- [ ] When should EnumSet be used?

## Advanced

- [ ] Explain HashSet internals.
- [ ] Explain LinkedHashSet internals.
- [ ] Explain TreeSet and TreeMap relationship.
- [ ] Explain red-black tree balancing.
- [ ] Explain EnumSet representation.
- [ ] Explain RegularEnumSet vs JumboEnumSet.
- [ ] Explain mutable-element corruption in HashSet.
- [ ] Explain mutable-ordering corruption in TreeSet.
- [ ] Explain comparator/equals inconsistency.
- [ ] Compare all four implementations.

## Senior / Production

- [ ] How would you choose a Set implementation?
- [ ] How would you design a concurrent Set?
- [ ] When would `ConcurrentHashMap.newKeySet()` be preferable?
- [ ] How would you prevent duplicate processing in a distributed system?
- [ ] How would you handle a Set that grows indefinitely?
- [ ] How would you implement ordered uniqueness?
- [ ] How would you implement sorted uniqueness with range queries?
- [ ] How would you represent permissions efficiently?
- [ ] How would you debug HashSet.contains() unexpectedly returning false?
- [ ] How would you debug missing TreeSet elements?
- [ ] How would you benchmark Set implementations?
- [ ] What happens if a HashSet element's hashCode changes?
- [ ] What happens if a TreeSet element's ordering changes?

---

# 3.8.51 Final Mastery Gate

## Set Fundamentals

- [ ] Explain the Set contract.
- [ ] Explain uniqueness.
- [ ] Explain Set vs List.
- [ ] Explain Set vs Map.
- [ ] Explain union/intersection/difference.
- [ ] Explain Set equality.

## HashSet

- [ ] Explain HashSet.
- [ ] Explain HashMap backing.
- [ ] Explain `PRESENT`.
- [ ] Explain hashing.
- [ ] Explain equals/hashCode.
- [ ] Explain collisions.
- [ ] Explain resizing.
- [ ] Handle mutable elements.
- [ ] Explain ordering limitations.
- [ ] Explain expected complexity.

## LinkedHashSet

- [ ] Explain insertion order.
- [ ] Explain internal links.
- [ ] Explain duplicate insertion.
- [ ] Explain memory overhead.
- [ ] Explain why it is not access-ordered.
- [ ] Explain production use cases.

## TreeSet

- [ ] Explain TreeSet.
- [ ] Explain TreeMap backing.
- [ ] Explain red-black trees.
- [ ] Explain balancing.
- [ ] Explain Comparator.
- [ ] Explain Comparable.
- [ ] Explain comparator-based uniqueness.
- [ ] Handle mutable ordering fields.
- [ ] Use navigation methods.
- [ ] Explain O(log N).

## EnumSet

- [ ] Explain enum specialization.
- [ ] Explain enum universe.
- [ ] Explain bit representation.
- [ ] Explain RegularEnumSet.
- [ ] Explain JumboEnumSet.
- [ ] Explain declaration-order iteration.
- [ ] Use factory methods.
- [ ] Perform set operations.
- [ ] Explain null restrictions.

## Ordering

- [ ] Explain HashSet's lack of ordering guarantee.
- [ ] Explain LinkedHashSet insertion order.
- [ ] Explain TreeSet sorted order.
- [ ] Explain EnumSet declaration order.
- [ ] Choose the correct implementation from requirements.

## Uniqueness

- [ ] Explain HashSet equality semantics.
- [ ] Explain TreeSet ordering semantics.
- [ ] Explain EnumSet uniqueness.
- [ ] Explain comparator returning zero.
- [ ] Handle equals/hashCode violations.
- [ ] Handle mutable elements.

## Performance

- [ ] Compare HashSet.
- [ ] Compare LinkedHashSet.
- [ ] Compare TreeSet.
- [ ] Compare EnumSet.
- [ ] Understand memory overhead.
- [ ] Benchmark realistic workloads.
- [ ] Explain Big-O vs real-world performance.

## Concurrency

- [ ] Explain lack of inherent thread safety.
- [ ] Explain synchronized Set wrappers.
- [ ] Explain `ConcurrentHashMap.newKeySet()`.
- [ ] Explain concurrent iteration semantics.
- [ ] Distinguish container safety from element safety.

## Production

- [ ] Design unique-ID tracking.
- [ ] Design ordered uniqueness.
- [ ] Design sorted uniqueness.
- [ ] Design enum-based permissions.
- [ ] Design a concurrent Set.
- [ ] Handle memory growth.
- [ ] Debug equality/hash issues.
- [ ] Debug comparator issues.
- [ ] Explain trade-offs.

---

# Final Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PERFORMANCE UNDERSTOOD
- [ ] ORDERING MASTERED
- [ ] UNIQUENESS SEMANTICS MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
