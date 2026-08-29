# Module 3.5 — HashMap
## Deep Dive & Deep Mastery Guide

> **Goal:** Master Java `HashMap` from the `Map` contract through hashing, hash spreading, bucket calculation, collisions, node/tree bins, capacity, load factor, threshold, resizing, mutable-key hazards, iteration behavior, Java 7 vs Java 8+ differences, complexity, performance, and OpenJDK internals.

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

# 3.5.1 What Is `HashMap`?

`HashMap<K, V>` is a hash-table-based implementation of the `Map` interface.

It stores mappings:

```text
key → value
```

Example:

```java
Map<String, Integer> scores =
        new HashMap<>();

scores.put("Alice", 95);
scores.put("Bob", 88);

System.out.println(scores.get("Alice"));
```

Conceptually:

```text
HashMap
   |
   ↓
bucket array
   |
   +-- bucket 0
   +-- bucket 1
   +-- bucket 2
   +-- ...
```

Each bucket can contain one or more entries.

---

# 3.5.2 Why Does Java Have `HashMap`?

Searching a list requires traversal:

```text
O(n)
```

A hash table attempts to locate a key directly using its hash.

Conceptually:

```text
key
 ↓
hash
 ↓
bucket
 ↓
entry
 ↓
value
```

Under favorable conditions, lookup is approximately:

```text
O(1)
```

This makes HashMap useful for:

- [ ] Fast key-based lookup.
- [ ] Caching.
- [ ] Indexing.
- [ ] Frequency counting.
- [ ] Deduplication.
- [ ] In-memory associations.

---

# 3.5.3 Core Characteristics

| Property | HashMap |
|---|---|
| Interface | `Map` |
| Key/value storage | Yes |
| Average `get` | O(1) |
| Average `put` | O(1) |
| Worst-case lookup | Can degrade; tree bins mitigate collision chains |
| Ordering | No guaranteed iteration order |
| Null key | One |
| Null values | Multiple |
| Duplicate keys | No |
| Duplicate values | Yes |
| Thread-safe | No |
| Backing structure | Array + node/tree bins |
| Resize | Yes |

Do not confuse:

```text
average expected complexity
```

with:

```text
guaranteed constant-time behavior
```

---

# 3.5.4 Basic HashMap Mental Model

Think:

```text
              HashMap
                 |
                 ↓
        +----------------+
        | bucket array   |
        +----------------+
         |  |  |  |  |
         ↓  ↓  ↓  ↓  ↓
        [] [A] [] [B] [C]
              |       |
              ↓       ↓
             node    node
                       |
                       ↓
                    collision
```

The array provides the initial bucket location.

The nodes represent entries within buckets.

---

# 3.5.5 Hashing

Hashing converts a key into an integer hash value.

Java keys expose:

```java
int hashCode()
```

Example:

```java
String key = "Java";

int hash = key.hashCode();
```

Conceptually:

```text
key
 ↓
hashCode()
 ↓
int hash
```

But HashMap does not simply use the raw hashCode directly.

---

# 3.5.6 Hash Spreading

Modern HashMap performs an additional transformation to spread information from the high bits into the lower bits.

Conceptually, OpenJDK has historically used a transformation similar to:

```java
h ^ (h >>> 16)
```

The exact implementation should always be checked against the target JDK source.

Why?

Because bucket selection uses low-order bits.

If only low bits contain useful variation, poor hash distributions can create collisions.

Hash spreading attempts to improve distribution.

---

# 3.5.7 HashMap Hash Pipeline

You should memorize the conceptual pipeline:

```text
key
 ↓
key.hashCode()
 ↓
hash spreading
 ↓
bucket calculation
 ↓
bucket
 ↓
compare hash
 ↓
compare equals()
 ↓
entry/value
```

This is one of the most important HashMap internal models.

---

# 3.5.8 Bucket Calculation

For a table whose capacity is a power of two, HashMap can calculate a bucket using a bit mask.

Conceptually:

```text
index = (n - 1) & hash
```

where:

```text
n = table length
```

Example:

```text
capacity = 16

index = (16 - 1) & hash
       = 15 & hash
```

This is much cheaper than a general modulo operation.

---

# 3.5.9 Why Power-of-Two Capacity?

HashMap maintains table capacity as a power of two.

Examples:

```text
1
2
4
8
16
32
64
128
...
```

This allows:

```java
(n - 1) & hash
```

to efficiently select a bucket.

It also makes resizing and bucket redistribution efficient.

---

# 3.5.10 Default Capacity

The commonly documented default initial capacity is:

```text
16
```

with the default load factor:

```text
0.75
```

Important:

> Modern HashMap implementations can defer actual table allocation until the first insertion, so "default capacity 16" should not be interpreted as "a 16-slot array is always allocated at construction."

Verify exact behavior against the JDK version being studied.

---

# 3.5.11 Load Factor

The default load factor is:

```text
0.75
```

Conceptually:

```text
load factor =
number of entries / table capacity
```

A higher load factor:

```text
less memory
+
more collisions
```

A lower load factor:

```text
more memory
+
potentially fewer collisions
```

Therefore load factor is a memory/performance trade-off.

---

# 3.5.12 Threshold

HashMap uses a threshold to determine when resizing should occur.

Conceptually:

```text
threshold ≈ capacity × loadFactor
```

For:

```text
capacity = 16
load factor = 0.75
```

the threshold is approximately:

```text
12
```

When the number of mappings exceeds the relevant threshold, resizing may occur.

---

# 3.5.13 Capacity vs Size vs Threshold

These three concepts must be distinguished.

```text
size
 ↓
number of mappings

capacity
 ↓
number of buckets

threshold
 ↓
resize trigger based on capacity/load factor
```

Example:

```text
capacity = 16
size     = 10
threshold = 12
```

No resize is required merely because the map contains ten entries.

---

# 3.5.14 Creating a HashMap with Initial Capacity

Example:

```java
Map<String, Integer> map =
        new HashMap<>(1000);
```

The requested capacity is processed according to HashMap's internal capacity rules.

Do not assume:

```text
requested capacity = exact table length
```

The implementation may round it to a suitable power of two.

---

# 3.5.15 Collisions

A collision occurs when multiple keys map to the same bucket.

Conceptually:

```text
Key A → bucket 5
Key B → bucket 5
Key C → bucket 5
```

The bucket therefore needs a structure containing multiple entries.

Historically this was primarily a linked chain.

Modern HashMap can transform sufficiently large collision bins into tree bins.

---

# 3.5.16 Node Structure

Conceptually, modern HashMap nodes resemble:

```java
static class Node<K,V>
        implements Map.Entry<K,V> {

    final int hash;
    final K key;
    V value;
    Node<K,V> next;
}
```

The exact declaration and implementation should be verified against the target OpenJDK version.

Mental model:

```text
Node
 |
 +-- hash
 +-- key
 +-- value
 +-- next
```

---

# 3.5.17 Linked-List Bins

A bucket can contain a linked chain:

```text
bucket 5
   |
   ↓
[Node A] → [Node B] → [Node C]
```

Lookup proceeds through the chain:

```text
candidate hash
     ↓
compare hash
     ↓
compare key
     ↓
next
     ↓
next
```

If the chain becomes long, lookup can degrade.

---

# 3.5.18 Why `hashCode()` Alone Is Not Enough

Two different keys can have the same hash:

```java
key1.hashCode() == key2.hashCode()
```

That does not imply:

```java
key1.equals(key2)
```

HashMap therefore needs both:

```text
hash
+
equals()
```

to identify the actual key.

Conceptually:

```text
same hash
   ↓
collision
   ↓
equals() distinguishes keys
```

---

# 3.5.19 HashMap Lookup

For:

```java
map.get(key);
```

the conceptual process is:

```text
1. Compute key hash.
2. Spread hash.
3. Calculate bucket.
4. Inspect bucket.
5. Compare candidate hash values.
6. Compare keys using equals().
7. Return associated value.
```

You should be able to explain every step.

---

# 3.5.20 `put()` Internal Flow

For:

```java
map.put(key, value);
```

conceptually:

```text
key
 ↓
hash
 ↓
spread
 ↓
bucket index
 ↓
empty bucket?
   |
   +-- yes → insert node
   |
   +-- no → collision
              ↓
          compare key
              |
       +------+------+
       |             |
     same key     different key
       |             |
   replace value    continue chain/tree
```

If the map grows beyond the threshold:

```text
resize
```

may occur.

---

# 3.5.21 Tree Bins

Modern Java HashMap can convert a heavily populated bucket into a tree structure.

Conceptually:

```text
bucket
  |
  ↓
       root
      /    \
   node    node
   /  \      \
 node node   node
```

The tree is based on a red-black-tree structure in modern OpenJDK implementations.

The purpose is to avoid pathological long linked chains under heavy collisions.

---

# 3.5.22 Treeification

Treeification means converting a collision bin from a linked-list representation to a tree representation.

Conceptually:

```text
Linked list:

A → B → C → D → E → F → G → H


Tree bin:

        D
       / \
      B   F
     / \ / \
    A  C E  G
             \
              H
```

The actual tree shape is implementation-dependent.

---

# 3.5.23 Treeification Threshold

A key HashMap constant is:

```text
TREEIFY_THRESHOLD = 8
```

This means treeification is considered when a bin reaches a sufficiently large population.

But:

> A bin reaching eight entries does not mean HashMap will always immediately create a tree.

Table capacity is also considered.

---

# 3.5.24 Minimum Treeify Capacity

Another critical constant is conceptually:

```text
MIN_TREEIFY_CAPACITY = 64
```

If the table is too small, HashMap generally prefers resizing rather than treeifying.

The reasoning is:

```text
small table
+
collision-heavy bucket
        ↓
increase table capacity
        ↓
better distribution
```

rather than immediately allocating tree nodes.

---

# 3.5.25 Treeification Decision

Conceptually:

```text
bin becomes large
       |
       ↓
capacity >= minimum treeify capacity?
       |
   +---+---+
   |       |
  yes      no
   |       |
treeify   resize
```

This is an important interview concept.

---

# 3.5.26 Untreeification

A tree bin can later become a linked-list bin if the number of entries drops sufficiently.

This process is called:

```text
untreeification
```

The relevant threshold is lower than the treeification threshold.

Historically, the constant is commonly:

```text
UNTREEIFY_THRESHOLD = 6
```

The exact behavior and constants should be verified against the JDK version being studied.

The reason for the lower threshold is to avoid constantly converting:

```text
list ↔ tree
```

around the boundary.

---

# 3.5.27 Why Tree Bins Exist

Without treeification, a malicious or pathological key distribution could create:

```text
A → B → C → D → E → F → ... → N
```

causing lookup to approach:

```text
O(n)
```

within that bucket.

Tree bins improve the theoretical collision-bin lookup behavior toward logarithmic behavior under appropriate conditions.

But this does not make bad hash functions harmless.

---

# 3.5.28 HashMap Complexity

Typical expected complexity:

| Operation | Expected |
|---|---:|
| `get` | O(1) |
| `put` | O(1) |
| `remove` | O(1) |
| `containsKey` | O(1) |
| Iteration | O(capacity + size) conceptually |

Heavy collision scenarios can change the behavior.

Tree bins mitigate long collision chains, but they introduce additional structure and overhead.

---

# 3.5.29 Iteration Complexity

HashMap iteration should not simply be described as:

```text
O(size)
```

Conceptually, iteration examines the table and its entries:

```text
table capacity
+
number of mappings
```

Therefore excessive initial capacity can affect iteration cost.

This is an important tuning trade-off.

---

# 3.5.30 Resize

When the map grows beyond the relevant threshold:

```text
resize
```

occurs.

Conceptually:

```text
capacity = 16
        ↓
capacity = 32
```

The backing table grows.

Entries must be redistributed into the new table.

---

# 3.5.31 Why Resize Is Expensive

Resize requires:

```text
allocate new table
+
move/redistribute entries
```

This creates a temporary performance spike.

However, resizing is not performed for every insertion.

Therefore average insertion complexity remains approximately:

```text
O(1) amortized / expected
```

---

# 3.5.32 HashMap Resize and Power-of-Two Doubling

Modern HashMap typically doubles the table capacity:

```text
16 → 32 → 64 → 128 → ...
```

This has an important optimization.

Because the capacity doubles, an existing entry's new bucket position can often be determined using an additional high-order bit rather than recomputing an arbitrary modulo operation.

Conceptually:

```text
old index
   |
   +-- stays at old index
   |
   +-- moves by old capacity
```

This is a major internal detail to understand.

---

# 3.5.33 Resize Redistribution

Suppose:

```text
old capacity = 16
new capacity = 32
```

For a node, the new position is related to:

```text
old index
```

and:

```text
old capacity bit
```

Conceptually:

```text
newIndex = oldIndex
```

or:

```text
newIndex = oldIndex + oldCapacity
```

depending on the relevant hash bit.

This avoids a full recomputation based on arbitrary modulo arithmetic.

---

# 3.5.34 Rehashing Terminology

Many explanations use:

```text
rehashing
```

for the resize process.

Be precise.

A HashMap resize changes table capacity and redistributes nodes.

Modern HashMap does not necessarily recompute each key's original `hashCode()`.

The stored/spread hash and power-of-two table structure allow efficient redistribution.

---

# 3.5.35 Mutable Keys — The Major Hazard

Never casually use a mutable object as a HashMap key.

Example:

```java
class UserKey {
    String id;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        ...
    }
}
```

Then:

```java
UserKey key = new UserKey("A");

map.put(key, "value");

key.id = "B";
```

Now the key's hash can change.

The entry remains physically stored in the bucket determined by the old hash.

Lookup using the mutated key may fail.

---

# 3.5.36 Mutable-Key Failure Model

Before mutation:

```text
key "A"
 ↓
hash H1
 ↓
bucket 5
 ↓
entry
```

After mutation:

```text
key "B"
 ↓
hash H2
 ↓
bucket 12
```

But the entry may still physically exist in:

```text
bucket 5
```

Therefore:

```java
map.get(key)
```

can return:

```text
null
```

even though the entry appears to exist during iteration.

---

# 3.5.37 Correct Key Design

Good HashMap keys should generally be:

```text
immutable
+
stable hashCode
+
stable equals semantics
```

Good examples often include:

```text
String
Integer
Long
UUID
records with immutable components
well-designed value objects
```

Records help with value-based equality but do not automatically make every component deeply immutable.

---

# 3.5.38 `equals()` and `hashCode()` Contract

For HashMap:

```text
if a.equals(b)
```

then:

```text
a.hashCode() == b.hashCode()
```

must hold.

The reverse is not required:

```text
same hash
≠
equal objects
```

That is exactly how collisions can occur.

---

# 3.5.39 Broken `hashCode()` Example

Suppose:

```java
@Override
public boolean equals(Object o) {
    return this.id == ((User) o).id;
}
```

but:

```java
@Override
public int hashCode() {
    return name.hashCode();
}
```

Two logically equal objects could produce different hash codes.

HashMap behavior becomes incorrect.

The contract must be respected.

---

# 3.5.40 Iteration Behavior

HashMap does not guarantee insertion order.

Example:

```java
Map<Integer, String> map =
        new HashMap<>();

map.put(1, "A");
map.put(2, "B");
map.put(3, "C");
```

Do not build business logic around:

```text
observed iteration order
```

The order can change because of:

- [ ] Resizing.
- [ ] Hash distribution.
- [ ] JDK implementation changes.
- [ ] Key hash behavior.
- [ ] Treeification.
- [ ] Removal/reinsertion patterns.

If order matters, use an appropriate ordered map such as `LinkedHashMap` or `TreeMap`.

---

# 3.5.41 Why HashMap Order Can Change After Resize

Suppose:

```text
capacity = 16
```

and then:

```text
capacity = 32
```

Entries may move to different buckets.

Since iteration follows the internal table structure, the visible iteration order can change.

Therefore:

> Never treat HashMap iteration order as stable API behavior.

---

# 3.5.42 Null Key

HashMap allows one:

```java
null
```

key.

Example:

```java
map.put(null, "value");
```

It can also contain multiple null values:

```java
map.put("A", null);
map.put("B", null);
```

This differs from some other Map implementations.

---

# 3.5.43 Null Key Internals

The null key has special handling because:

```text
null.hashCode()
```

cannot be called.

Conceptually, HashMap handles a null key as a special hash/bucket case.

Do not infer that every null value receives special hashing behavior.

Only the key requires special treatment.

---

# 3.5.44 HashMap and Thread Safety

HashMap is not thread-safe.

Concurrent unsynchronized access involving mutation can produce incorrect behavior.

For concurrent map requirements, evaluate:

```java
ConcurrentHashMap
```

rather than assuming:

```text
HashMap + multiple threads = safe
```

---

# 3.5.45 `Collections.synchronizedMap`

You can wrap a map:

```java
Map<K,V> map =
    Collections.synchronizedMap(
        new HashMap<>()
    );
```

This provides synchronized access through the wrapper, but iteration and compound operations still require careful synchronization according to the API contract.

It is not simply equivalent to `ConcurrentHashMap`.

---

# 3.5.46 Java 7 vs Java 8+ — Major Difference

One of the most important historical HashMap changes is collision handling.

### Java 7-era model

Primarily:

```text
bucket
 ↓
linked list
```

### Java 8+ model

Can use:

```text
bucket
 ↓
linked list
```

or:

```text
bucket
 ↓
red-black tree
```

when collision bins become sufficiently large and the table is large enough.

This was introduced to improve resilience against pathological collision patterns.

---

# 3.5.47 Java 7 Resize and Concurrency History

Older HashMap implementations also had a notorious interaction with unsynchronized concurrent resizing.

In some historical Java 7 implementations, concurrent mutation during resize could contribute to corrupted bucket chains and pathological traversal behavior.

The key lesson is:

> HashMap has never been a concurrent collection.

Do not use historical implementation details as a reason to use HashMap concurrently today.

Use the correct concurrent abstraction.

---

# 3.5.48 Java 8+ Treeification

Modern collision handling:

```text
collision chain
      ↓
bin becomes sufficiently large
      ↓
capacity sufficient?
      |
      +-- yes → treeify
      |
      +-- no  → resize
```

This makes the implementation more robust against poor key distributions and adversarial collision patterns.

---

# 3.5.49 Hash Collision Considerations

Collisions are normal.

A good hash function does not guarantee:

```text
one key → one unique hash
```

because the hash space is finite.

Instead, the goal is good distribution.

For many keys:

```text
keys
 ↓
hash values
 ↓
well-distributed buckets
```

Good distribution reduces chain/tree population.

---

# 3.5.50 Collision Attacks

If an attacker can control keys and deliberately cause many collisions, hash tables can become vulnerable to algorithmic complexity attacks.

Modern tree bins reduce the worst impact for applicable collision patterns.

However:

```text
treeification ≠ complete security solution
```

Application-level limits, input validation, framework protections, and appropriate data structures still matter.

---

# 3.5.51 HashMap Memory Model

Conceptually:

```text
HashMap object
      |
      +-- table reference
             |
             ↓
       Node[] table
        |   |   |
        ↓   ↓   ↓
       node    node
        |       |
        ↓       ↓
      key/value key/value
```

Memory is consumed by:

```text
HashMap object
+
bucket array
+
Node objects
+
keys
+
values
+
tree-node overhead where applicable
```

---

# 3.5.52 Memory Overhead

HashMap does not simply store:

```text
key + value
```

It also requires:

```text
bucket array
+
entry/node objects
+
hash metadata
+
references
+
object headers/alignment
```

Therefore a HashMap can consume substantially more memory than a compact primitive-oriented structure.

This matters in large in-memory datasets.

---

# 3.5.53 Initial Capacity Trade-Off

Too small:

```text
frequent resizing
+
reference movement
+
temporary allocation
```

Too large:

```text
more memory
+
potentially more iteration work
```

The ideal initial capacity depends on:

```text
expected number of entries
+
load factor
+
workload
+
memory constraints
```

---

# 3.5.54 Pre-Sizing

If you know approximately how many mappings will be inserted, pre-sizing can reduce resizing.

Example:

```java
Map<String, User> users =
        new HashMap<>(10_000);
```

But remember:

```text
constructor argument
≠
necessarily exact backing-table length
```

The implementation rounds/adjusts capacity according to its internal rules.

---

# 3.5.55 HashMap vs Hashtable

Historical comparison:

| Property | HashMap | Hashtable |
|---|---|---|
| Modern collection | Yes | Legacy |
| Null key | One | No |
| Null values | Yes | No |
| Synchronization | No | Synchronized legacy design |
| Preferred modern map | Yes | Generally no |

If thread safety is required, do not automatically choose Hashtable.

Evaluate `ConcurrentHashMap` or an appropriate synchronization strategy.

---

# 3.5.56 HashMap vs LinkedHashMap

```text
HashMap
 ↓
no ordering guarantee
```

```text
LinkedHashMap
 ↓
predictable iteration order
```

If you need:

```text
insertion order
```

or suitable access-order behavior:

```java
LinkedHashMap
```

may be more appropriate.

---

# 3.5.57 HashMap vs TreeMap

```text
HashMap
 ↓
hash-based lookup
expected O(1)
```

```text
TreeMap
 ↓
sorted tree
O(log n)
```

Use TreeMap when sorted/navigable key semantics matter.

Do not choose HashMap when the primary requirement is ordered key traversal.

---

# 3.5.58 Common Mistakes

- [ ] Assuming HashMap guarantees insertion order.
- [ ] Assuming `hashCode()` uniquely identifies an object.
- [ ] Implementing `equals()` without a compatible `hashCode()`.
- [ ] Using mutable keys.
- [ ] Assuming O(1) means guaranteed constant time.
- [ ] Ignoring collisions.
- [ ] Assuming treeification happens at exactly eight entries regardless of capacity.
- [ ] Forgetting the minimum treeify capacity.
- [ ] Confusing size, capacity, and threshold.
- [ ] Assuming requested initial capacity is exact table length.
- [ ] Assuming resize recomputes every original `hashCode()`.
- [ ] Using HashMap concurrently without synchronization.
- [ ] Assuming synchronized wrappers make compound operations automatically atomic.
- [ ] Over-sizing every HashMap unnecessarily.
- [ ] Under-sizing a known large map.
- [ ] Treating implementation details as permanent API guarantees.
- [ ] Assuming Java 7 and Java 8+ HashMap internals are identical.

---

# 3.5.59 Edge Cases

Investigate:

- [ ] Empty HashMap.
- [ ] One entry.
- [ ] Null key.
- [ ] Null values.
- [ ] Duplicate key insertion.
- [ ] Mutable key after insertion.
- [ ] Broken equals/hashCode.
- [ ] Deliberate collisions.
- [ ] Treeification threshold.
- [ ] Minimum treeify capacity.
- [ ] Untreeification.
- [ ] Resize.
- [ ] Iteration after resize.
- [ ] Remove and reinsert.
- [ ] Concurrent mutation.
- [ ] Very large capacity.
- [ ] Memory pressure.

---

# 3.5.60 Debugging Challenge — Mutable Key

Create a mutable key:

```java
class UserKey {
    String id;

    UserKey(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserKey other)) {
            return false;
        }
        return id.equals(other.id);
    }
}
```

Then:

```java
UserKey key = new UserKey("A");

Map<UserKey, String> map =
        new HashMap<>();

map.put(key, "value");

key.id = "B";

System.out.println(map.get(key));
System.out.println(map.containsKey(key));
System.out.println(map.size());
```

Tasks:

- [ ] Predict the output.
- [ ] Explain why lookup can fail.
- [ ] Iterate through the map.
- [ ] Locate the apparently "missing" entry.
- [ ] Fix the design.
- [ ] Explain why immutable keys solve the problem.

---

# 3.5.61 Debugging Challenge — Broken `hashCode()`

Create two logically equal objects:

```text
A.equals(B) == true
```

but deliberately give them different hash codes.

Insert one into a HashMap.

Then:

```java
map.get(B);
```

Tasks:

- [ ] Explain the result.
- [ ] Trace bucket selection.
- [ ] Explain why `equals()` alone is insufficient.
- [ ] Correct the implementation.

---

# 3.5.62 Debugging Challenge — Collision

Create a key class whose `hashCode()` always returns:

```java
42
```

Insert thousands of keys.

Tasks:

- [ ] Observe performance.
- [ ] Explain collision behavior.
- [ ] Explain linked bins.
- [ ] Investigate treeification.
- [ ] Determine the table capacity at treeification.
- [ ] Inspect the OpenJDK source.
- [ ] Compare with a well-distributed hash function.

---

# 3.5.63 Coding Exercises

## Basic

- [ ] Create a HashMap.
- [ ] Insert mappings.
- [ ] Retrieve values.
- [ ] Update a value.
- [ ] Remove a key.
- [ ] Check `containsKey`.
- [ ] Check `containsValue`.
- [ ] Iterate through `entrySet`.
- [ ] Iterate through `keySet`.
- [ ] Iterate through `values`.
- [ ] Use a null key.
- [ ] Use null values.

## Intermediate

- [ ] Build a word-frequency counter.
- [ ] Build a character-frequency counter.
- [ ] Find duplicate elements using HashMap.
- [ ] Implement two-sum using HashMap.
- [ ] Group objects by a property.
- [ ] Build a simple in-memory index.
- [ ] Implement memoization using HashMap.

## Advanced

- [ ] Implement a simplified HashMap from scratch.
- [ ] Implement bucket indexing.
- [ ] Implement collision chaining.
- [ ] Implement resize.
- [ ] Implement load-factor threshold.
- [ ] Implement mutable-key failure demonstration.
- [ ] Implement collision-heavy workloads.
- [ ] Implement tree-bin behavior conceptually.
- [ ] Compare custom implementation with JDK HashMap.

---

# 3.5.64 Implement a Simplified HashMap

Build:

```text
MyHashMap<K,V>
```

Requirements:

```java
V put(K key, V value);
V get(Object key);
V remove(Object key);

boolean containsKey(Object key);
int size();

void clear();
```

Internally implement:

```text
Node<K,V>[]
```

with:

```text
hash
key
value
next
```

Then add:

- [ ] Power-of-two capacity.
- [ ] Bucket calculation.
- [ ] Load factor.
- [ ] Threshold.
- [ ] Resize.
- [ ] Null key support.
- [ ] Correct equals/hashCode behavior.
- [ ] Iterator.

Do not copy the JDK source mechanically. Reconstruct the concepts yourself.

---

# 3.5.65 Production Performance Lab

Benchmark:

```text
HashMap
LinkedHashMap
TreeMap
```

for:

```text
insert
lookup
remove
iteration
```

Measure:

- [ ] Throughput.
- [ ] Latency.
- [ ] Allocation.
- [ ] Heap usage.
- [ ] GC.
- [ ] CPU.
- [ ] Collision behavior.

Use JMH for serious microbenchmarking.

---

# 3.5.66 Collision Benchmark

Compare:

```text
well-distributed hashCode()
```

against:

```text
constant hashCode()
```

Test:

```text
100
1,000
10,000
100,000
```

keys.

Observe:

```text
lookup latency
insert latency
memory
treeification
```

Explain why the results differ.

---

# 3.5.67 Resize Benchmark

Compare:

```java
new HashMap<>();
```

with a suitably pre-sized HashMap for a known workload.

Example:

```text
1 million insertions
```

Measure:

```text
allocation
GC
throughput
latency
```

Explain the trade-off between:

```text
pre-sizing
vs.
memory reservation
```

---

# 3.5.68 Production Use Cases

HashMap is commonly appropriate for:

- [ ] In-memory lookup tables.
- [ ] Request-scoped indexes.
- [ ] Frequency counters.
- [ ] Deduplication.
- [ ] Configuration mappings.
- [ ] Caches where concurrency is handled elsewhere.
- [ ] Object-to-object association.
- [ ] Fast membership/lookup structures.

Examples:

```text
userId → User
productId → Product
code → configuration
word → frequency
```

---

# 3.5.69 When Not to Use HashMap

Do not use HashMap when the primary requirement is:

```text
sorted keys
```

Use an ordered/navigable map where appropriate.

Do not use HashMap when:

```text
concurrent mutation
```

requires stronger thread-safety guarantees.

Do not use it when:

```text
stable insertion order
```

is required without explicitly choosing an ordered implementation.

Do not use it as a cache without considering:

```text
eviction
memory limits
TTL
concurrency
observability
```

---

# 3.5.70 Production Trade-Offs

HashMap provides:

```text
fast expected lookup
```

at the cost of:

```text
extra memory
+
hash computation
+
collision handling
+
resizing
+
no ordering guarantee
+
non-thread-safe mutation
```

A senior developer should understand the entire trade-off rather than simply saying:

```text
HashMap = O(1)
```

---

# 3.5.71 Interview Questions

## Basic

- [ ] What is HashMap?
- [ ] How does HashMap work?
- [ ] What is hashing?
- [ ] What is a collision?
- [ ] What is the default initial capacity?
- [ ] What is the default load factor?
- [ ] What is a bucket?
- [ ] Does HashMap allow null keys?
- [ ] Does HashMap allow duplicate keys?
- [ ] Is HashMap thread-safe?

## Intermediate

- [ ] Explain the HashMap lookup process.
- [ ] Explain hash spreading.
- [ ] Explain bucket calculation.
- [ ] Why is capacity a power of two?
- [ ] What is threshold?
- [ ] What causes resize?
- [ ] Explain linked-list bins.
- [ ] Explain `equals()` and `hashCode()`.
- [ ] Why are mutable keys dangerous?
- [ ] Does HashMap preserve insertion order?

## Advanced

- [ ] Explain treeification.
- [ ] What is the treeification threshold?
- [ ] What is the minimum treeify capacity?
- [ ] What is untreeification?
- [ ] Why does HashMap resize instead of treeifying when capacity is too small?
- [ ] Explain resize redistribution.
- [ ] Why can resize avoid recomputing a full bucket index?
- [ ] Explain HashMap memory overhead.
- [ ] Why can HashMap iteration depend on capacity?
- [ ] Explain Java 7 vs Java 8+ collision handling.

## Senior / Production

- [ ] How would you choose initial capacity?
- [ ] How would you diagnose excessive HashMap memory usage?
- [ ] How would you detect a pathological hash distribution?
- [ ] How can mutable keys cause production bugs?
- [ ] Why can a HashMap lookup fail even though the entry appears during iteration?
- [ ] When would you choose LinkedHashMap instead?
- [ ] When would you choose TreeMap instead?
- [ ] When would you choose ConcurrentHashMap?
- [ ] How would you benchmark HashMap?
- [ ] How would you investigate HashMap performance in production?
- [ ] How do collision attacks affect hash-based data structures?
- [ ] Why doesn't treeification make a poor hashCode implementation acceptable?

---

# 3.5.72 Advanced OpenJDK Follow-Up

Inspect the current OpenJDK source for:

```text
java.util.HashMap
```

Study:

- [ ] `Node<K,V>`
- [ ] `TreeNode<K,V>`
- [ ] `table`
- [ ] `size`
- [ ] `threshold`
- [ ] `loadFactor`
- [ ] `TREEIFY_THRESHOLD`
- [ ] `UNTREEIFY_THRESHOLD`
- [ ] `MIN_TREEIFY_CAPACITY`
- [ ] hash spreading
- [ ] bucket calculation
- [ ] `putVal`
- [ ] `getNode`
- [ ] `resize`
- [ ] `treeifyBin`
- [ ] `removeNode`
- [ ] `TreeNode` operations
- [ ] iterators
- [ ] spliterator

Trace:

```text
put()
 ↓
putVal()
 ↓
hash()
 ↓
bucket selection
 ↓
collision handling
 ↓
treeification / resize
```

and:

```text
get()
 ↓
getNode()
 ↓
hash comparison
 ↓
equals comparison
 ↓
value
```

---

# 3.5.73 JLS / API Contract Follow-Up

HashMap itself is primarily an API/library implementation topic rather than a Java-language syntax feature.

Study:

```text
Map contract
 ↓
equals/hashCode contract
 ↓
HashMap implementation
 ↓
OpenJDK source
```

Understand what is:

```text
API contract
```

versus:

```text
implementation detail
```

Do not treat internal constants as permanent language guarantees.

---

# 3.5.74 Java 7 vs Java 8+ Mental Model

## Java 7-era simplified model

```text
HashMap
  ↓
table
  ↓
linked-list bins
```

## Java 8+ simplified model

```text
HashMap
  ↓
table
  ↓
+----------------+
| empty          |
| linked list    |
| tree bin       |
| linked list    |
| ...            |
+----------------+
```

The major conceptual improvement is:

```text
long collision chain
        ↓
potential tree bin
```

combined with improved resize behavior.

---

# 3.5.75 Deep Performance Mental Model

Do not stop at:

```text
HashMap.get() = O(1)
```

Use:

```text
hashCode quality
        +
hash spreading
        +
bucket distribution
        +
collision frequency
        +
table capacity
        +
load factor
        +
treeification
        +
memory locality
        +
allocation
        +
GC
        +
CPU cache behavior
        =
real HashMap performance
```

---

# 3.5.76 Final Mastery Gate

## Fundamentals

- [ ] Explain HashMap.
- [ ] Explain the Map abstraction.
- [ ] Explain hashing.
- [ ] Explain collisions.
- [ ] Explain buckets.
- [ ] Explain nodes.
- [ ] Explain key/value mapping.

## Hashing

- [ ] Explain `hashCode()`.
- [ ] Explain hash spreading.
- [ ] Explain bucket calculation.
- [ ] Explain power-of-two capacity.
- [ ] Explain why collisions occur.

## Capacity

- [ ] Explain default capacity.
- [ ] Explain size.
- [ ] Explain capacity.
- [ ] Explain load factor.
- [ ] Explain threshold.
- [ ] Explain resize.
- [ ] Explain redistribution.
- [ ] Explain pre-sizing.

## Collision Handling

- [ ] Explain linked-list bins.
- [ ] Explain tree bins.
- [ ] Explain treeification threshold.
- [ ] Explain minimum treeify capacity.
- [ ] Explain untreeification.
- [ ] Explain collision complexity.

## Keys

- [ ] Explain equals/hashCode contract.
- [ ] Explain mutable-key failure.
- [ ] Design stable keys.
- [ ] Debug broken key implementations.

## Iteration

- [ ] Explain lack of ordering guarantee.
- [ ] Explain why resize can change observed order.
- [ ] Explain capacity impact on iteration.

## Historical Internals

- [ ] Explain Java 7-era collision handling.
- [ ] Explain Java 8+ tree bins.
- [ ] Explain major resize implementation changes.
- [ ] Distinguish historical behavior from current guarantees.

## Performance

- [ ] Explain expected O(1).
- [ ] Explain collision degradation.
- [ ] Explain memory overhead.
- [ ] Explain resizing cost.
- [ ] Explain cache/allocation effects.
- [ ] Benchmark realistic workloads.

## Production

- [ ] Select suitable initial capacity.
- [ ] Avoid mutable keys.
- [ ] Select HashMap vs LinkedHashMap vs TreeMap.
- [ ] Select HashMap vs ConcurrentHashMap.
- [ ] Diagnose collision and memory issues.
- [ ] Explain trade-offs in an architecture review.

## Implementation

- [ ] Implement a simplified HashMap.
- [ ] Implement buckets.
- [ ] Implement collision chaining.
- [ ] Implement lookup.
- [ ] Implement insertion.
- [ ] Implement removal.
- [ ] Implement resizing.
- [ ] Implement load factor/threshold.
- [ ] Implement iteration.
- [ ] Explain treeification conceptually.

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
