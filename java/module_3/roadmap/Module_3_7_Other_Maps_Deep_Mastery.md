# Module 3.7 — Other Maps
## LinkedHashMap, TreeMap, WeakHashMap, IdentityHashMap, EnumMap & Immutable Maps

> **Goal:** Master the major specialized `Map` implementations beyond `HashMap` and `ConcurrentHashMap`, including ordering, access-order, LRU design, red-black trees, weak references, identity-based equality, enum-optimized maps, immutable/unmodifiable maps, memory behavior, performance, edge cases, and production trade-offs.

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

# 3.7.1 Map Family Overview

Java provides several `Map` implementations because different applications need different semantics.

```text
                         Map
                          |
        +-----------------+------------------+
        |                 |                  |
     HashMap          SortedMap          ConcurrentMap
        |                 |                  |
        |              TreeMap       ConcurrentHashMap
        |
  +-----+-----------------------------+
  |            |            |         |
LinkedHashMap WeakHashMap IdentityHashMap EnumMap
```

Immutable/unmodifiable maps are a category of map usage rather than one single implementation family:

```text
Map.of(...)
Map.ofEntries(...)
Map.copyOf(...)
Collections.unmodifiableMap(...)
```

---

# 3.7.2 Comparison at a Glance

| Map | Ordering | Equality model | Main structure / behavior | Null support | Typical use |
|---|---|---|---|---|---|
| `LinkedHashMap` | Insertion/access | `equals` / `hashCode` | Hash table + linked list | Allows null key/value | Predictable iteration, LRU |
| `TreeMap` | Sorted by key | Ordering via comparator/natural ordering | Red-black tree | Null depends on ordering/comparator | Sorted/navigable data |
| `WeakHashMap` | No ordering guarantee | Normal key equality | Weak-reference-based entries | Allows null key/value | Metadata tied to object lifetime |
| `IdentityHashMap` | No ordering guarantee | `==` identity | Identity-based hash table | Allows null key/value | Identity-sensitive algorithms |
| `EnumMap` | Enum declaration order | Enum key identity/semantics | Array-like enum-key mapping | Null key prohibited | Enum-keyed state |
| `Map.of` / `Map.copyOf` | No guaranteed order | Normal map semantics | JDK immutable implementations | Null prohibited | Immutable configuration/data |
| `unmodifiableMap` | Depends on backing map | Backing map semantics | View over another map | Depends on backing map | Read-only API view |

---

# 3.7.3 LinkedHashMap — What Is It?

`LinkedHashMap<K,V>` is a `HashMap`-based map that additionally maintains a linked ordering of entries.

It supports two important ordering modes:

```text
insertion-order
access-order
```

Example:

```java
Map<String, Integer> map = new LinkedHashMap<>();

map.put("A", 1);
map.put("B", 2);
map.put("C", 3);

System.out.println(map);
```

Typical output:

```text
{A=1, B=2, C=3}
```

The iteration order is predictable according to the map's configured ordering mode.

---

# 3.7.4 Why LinkedHashMap Exists

`HashMap` does not provide a predictable iteration order.

Sometimes applications need:

```text
hash-table lookup
+
stable iteration order
```

Examples:

- [ ] Preserve insertion order.
- [ ] Produce deterministic output.
- [ ] Build ordered configuration.
- [ ] Implement simple LRU caches.
- [ ] Preserve request/result ordering.
- [ ] Serialize data predictably.

`LinkedHashMap` combines hashing with a linked ordering structure.

---

# 3.7.5 LinkedHashMap Internal Structure

Conceptually:

```text
Hash table
     |
     +---- bucket
     +---- bucket
     +---- bucket
     |
     v
Entries linked in order

HEAD
 ↓
A ↔ B ↔ C ↔ D
             ↑
            TAIL
```

Each entry participates in:

```text
hash-table structure
+
doubly linked ordering list
```

This additional linkage creates memory overhead compared with `HashMap`.

---

# 3.7.6 Insertion-Order Mode

Default construction:

```java
LinkedHashMap<String, Integer> map =
        new LinkedHashMap<>();

map.put("A", 1);
map.put("B", 2);
map.put("C", 3);
```

Iteration:

```text
A
B
C
```

The ordering reflects insertion order.

---

# 3.7.7 What Happens When an Existing Key Is Replaced?

Consider:

```java
map.put("A", 1);
map.put("B", 2);
map.put("C", 3);

map.put("B", 20);
```

In insertion-order mode, replacing the value associated with an existing key does not normally make `B` a newly inserted entry.

Expected ordering:

```text
A
B
C
```

This distinction matters when implementing ordering-sensitive logic.

---

# 3.7.8 Access-Order Mode

Create an access-ordered map:

```java
LinkedHashMap<String, Integer> map =
        new LinkedHashMap<>(16, 0.75f, true);
```

The final `true` means:

```text
accessOrder = true
```

Now accesses can move entries toward the end of the linked list.

Example:

```java
map.put("A", 1);
map.put("B", 2);
map.put("C", 3);

map.get("A");
```

The ordering becomes conceptually:

```text
B
C
A
```

---

# 3.7.9 What Counts as Access?

For access-order maps, operations that access an entry can affect ordering.

Important APIs to investigate:

```text
get
getOrDefault
putIfAbsent
computeIfAbsent
computeIfPresent
compute
merge
```

The exact behavior of each method should be verified against the current JDK documentation.

Do not assume every map operation changes access order.

---

# 3.7.10 Access-Order vs Insertion-Order

| Behavior | Insertion-order | Access-order |
|---|---|---|
| New entry | Moves to end | Moves to end |
| Existing key update | Normally stays in position | Can affect position depending on operation |
| `get()` | Does not reorder | Reorders accessed entry |
| Typical use | Ordered map | LRU |
| Constructor | Default | `accessOrder = true` |

---

# 3.7.11 LinkedHashMap Performance

Expected complexity is generally similar to HashMap for lookup/update operations:

```text
get      → expected O(1)
put      → expected O(1)
remove   → expected O(1)
```

Iteration is efficient because the map can follow its linked ordering rather than scanning every possible hash-table slot.

Additional cost:

```text
memory for links
+
pointer/reference manipulation
+
ordering maintenance
```

---

# 3.7.12 LinkedHashMap Memory Model

Compared with HashMap, entries carry additional linkage:

```text
previous
   ↑
entry
   ↓
next
```

Therefore:

```text
LinkedHashMap
    > memory overhead
       than
HashMap
```

This trade-off is worthwhile when predictable ordering is important.

---

# 3.7.13 LRU Cache

LRU means:

```text
Least Recently Used
```

The least recently used entry is evicted first.

A common Java implementation uses:

```text
LinkedHashMap
+
access-order
+
removeEldestEntry()
```

This is one of the classic uses of LinkedHashMap.

---

# 3.7.14 LRU Cache Internal Model

Suppose capacity = 3:

```text
Most recently used
        ↓
C ↔ A ↔ B
        ↑
     least recently used
```

After accessing `B`:

```text
C ↔ A ↔ B
```

depending on current ordering.

Conceptually, the accessed item moves to the most-recent position.

If a new entry is added beyond capacity:

```text
least recently used
        ↓
evict
```

---

# 3.7.15 Implementing LRU with LinkedHashMap

```java
class LruCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    LruCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(
            Map.Entry<K, V> eldest) {

        return size() > capacity;
    }
}
```

Usage:

```java
LruCache<Integer, String> cache =
        new LruCache<>(3);

cache.put(1, "A");
cache.put(2, "B");
cache.put(3, "C");

cache.get(1);

cache.put(4, "D");
```

The least recently used entry is eligible for removal.

---

# 3.7.16 LRU Cache Complexity

Typical operations:

```text
get  → expected O(1)
put  → expected O(1)
remove → expected O(1)
```

The ordering list allows constant-time movement of an entry once it has been located.

---

# 3.7.17 Is LinkedHashMap LRU Cache Thread-Safe?

No.

This is a critical production point.

```java
LinkedHashMap
```

is not inherently thread-safe.

Concurrent access may require:

```text
external synchronization
```

or a dedicated concurrent caching library/implementation.

---

# 3.7.18 Is LinkedHashMap a Production Cache?

Not automatically.

It lacks many features expected from production cache systems:

```text
TTL
size-based policies beyond simple LRU
concurrent access
refresh
statistics
cache stampede protection
distributed caching
serialization
eviction policies
```

For sophisticated production caching, consider a purpose-built cache.

---

# 3.7.19 LinkedHashMap Edge Cases

- [ ] Existing key inserted again.
- [ ] Existing key accessed.
- [ ] Access-order behavior.
- [ ] `put` in access-order map.
- [ ] `compute` behavior.
- [ ] `merge` behavior.
- [ ] Iterator behavior after access-order changes.
- [ ] Concurrent access.
- [ ] Capacity zero.
- [ ] Negative capacity validation.
- [ ] Null keys.
- [ ] Null values.
- [ ] Mutation during iteration.

---

# 3.7.20 TreeMap — What Is It?

`TreeMap<K,V>` implements `NavigableMap`.

It stores keys in sorted order according to:

```text
natural ordering
```

or:

```text
Comparator<? super K>
```

Example:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();

map.put(30, "C");
map.put(10, "A");
map.put(20, "B");

System.out.println(map);
```

Result:

```text
{10=A, 20=B, 30=C}
```

---

# 3.7.21 Why TreeMap Exists

HashMap provides efficient expected lookup but no sorted-key traversal.

TreeMap provides:

```text
sorted keys
+
range queries
+
nearest-key operations
+
NavigableMap operations
```

Examples:

```java
map.floorKey(25);
map.ceilingKey(25);
map.lowerKey(25);
map.higherKey(25);
```

---

# 3.7.22 TreeMap Internal Structure

TreeMap is implemented using a balanced search tree.

The standard implementation uses a:

```text
Red-Black Tree
```

Conceptually:

```text
             20
           /    \
         10      30
        /  \    /  \
       5   15  25  40
```

The tree maintains ordering while using balancing rules to prevent pathological height.

---

# 3.7.23 Red-Black Tree

A red-black tree is a self-balancing binary search tree.

It maintains properties involving:

```text
red nodes
black nodes
root
paths from nodes to leaves
```

These constraints keep the tree approximately balanced.

Therefore:

```text
search
insert
delete
```

are:

```text
O(log N)
```

in the relevant worst-case complexity model.

---

# 3.7.24 Why Not a Plain BST?

A normal binary search tree can degrade:

```text
10
  \
   20
     \
      30
        \
         40
```

This becomes effectively:

```text
linked list
```

with:

```text
O(N)
```

search.

A red-black tree limits height and maintains:

```text
O(log N)
```

operations.

---

# 3.7.25 TreeMap Comparator

Example:

```java
TreeMap<String, Integer> map =
        new TreeMap<>(Comparator.reverseOrder());
```

Now keys are ordered in reverse.

The comparator defines the map's ordering semantics.

---

# 3.7.26 Comparator Must Be Consistent

A comparator used by TreeMap should define a consistent ordering.

If it treats two distinct keys as equivalent:

```text
compare(k1, k2) == 0
```

TreeMap may treat them as the same map key for ordering purposes.

This is different from simply checking:

```java
k1.equals(k2)
```

This is a major source of TreeMap bugs.

---

# 3.7.27 `compareTo()` vs `equals()`

Example:

```java
BigDecimal("1.0")
BigDecimal("1.00")
```

Their `compareTo()` values can indicate equivalence while `equals()` can differ.

A TreeMap uses ordering to determine key placement.

Therefore:

> Always understand the consistency relationship between `compareTo()`/Comparator and `equals()` when using sorted maps.

---

# 3.7.28 TreeMap Complexity

| Operation | Complexity |
|---|---:|
| `get` | O(log N) |
| `put` | O(log N) |
| `remove` | O(log N) |
| `firstKey` | O(log N) / implementation path |
| `lastKey` | O(log N) / implementation path |
| `floorKey` | O(log N) |
| `ceilingKey` | O(log N) |
| Range view | Typically O(log N) to locate boundaries + iteration |

TreeMap sacrifices hash-table expected O(1) lookup for sorted/navigable behavior.

---

# 3.7.29 TreeMap Navigation

Master:

```java
firstKey()
lastKey()

lowerKey()
floorKey()
ceilingKey()
higherKey()

firstEntry()
lastEntry()

pollFirstEntry()
pollLastEntry()

headMap()
tailMap()
subMap()
descendingMap()
```

These are central to `NavigableMap`.

---

# 3.7.30 Range Queries

Example:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
map.put(40, "D");

Map<Integer, String> range =
        map.subMap(20, true, 40, false);
```

This gives a sorted range view.

Understand the difference between:

```text
view
vs.
copy
```

A map view can remain connected to the original map.

---

# 3.7.31 TreeMap Null Keys

TreeMap generally does not permit null keys when natural ordering is used because natural comparison cannot order null.

A custom comparator may potentially define a null ordering.

However:

> Do not design production code around null-key tricks unless the comparator contract clearly supports them and the semantics are intentional.

Null values are allowed.

---

# 3.7.32 TreeMap Edge Cases

- [ ] Duplicate keys.
- [ ] Custom Comparator.
- [ ] Comparator inconsistent with equals.
- [ ] Null keys.
- [ ] Null values.
- [ ] Mutable keys.
- [ ] Key mutation after insertion.
- [ ] Range-view mutation.
- [ ] Descending views.
- [ ] Concurrent access.
- [ ] Iterator modification.
- [ ] Comparator exceptions.

---

# 3.7.33 WeakHashMap — What Is It?

`WeakHashMap<K,V>` stores keys using weak references.

Conceptually:

```text
Map
 |
 +-- weak reference to key
 |
 +-- value
```

If a key is no longer strongly reachable elsewhere, it can become eligible for garbage collection.

The corresponding map entry can then disappear.

---

# 3.7.34 Why WeakHashMap Exists

It is useful when map entries should not keep their keys alive indefinitely.

Typical conceptual use:

```text
object
  ↓
metadata
  ↓
WeakHashMap
```

The metadata should disappear when the object is otherwise unreachable.

Possible use cases:

- [ ] Metadata associated with object lifetimes.
- [ ] Temporary object-associated state.
- [ ] Certain canonicalization/auxiliary mappings.
- [ ] Memory-sensitive associations.

---

# 3.7.35 Weak Reference Concept

Normal reference:

```text
Map → Key
```

keeps the key strongly reachable.

Weak reference:

```text
Map → WeakReference → Key
```

does not provide the same strong reachability.

Conceptually:

```text
Strong reference exists
       ↓
key remains reachable

No strong reference
       ↓
key may be collected
       ↓
entry may disappear
```

GC behavior is not deterministic in timing.

---

# 3.7.36 WeakHashMap Is Not "A Map That Automatically Expires"

This is a common misconception.

WeakHashMap is based on:

```text
reachability
+
garbage collection
```

not:

```text
TTL
+
clock
```

If you need:

```text
expire after 5 minutes
```

use TTL-based cache mechanisms instead.

---

# 3.7.37 WeakHashMap and Garbage Collection

The entry may disappear after the key becomes weakly reachable and GC processing occurs.

Do not assume:

```text
key unreachable
↓
entry disappears immediately
```

Garbage collection timing is nondeterministic.

---

# 3.7.38 WeakHashMap Values

A subtle issue:

```text
weak key
+
strong value
```

If the value strongly references the key, the key may remain reachable through the value graph.

Conceptually:

```text
WeakHashMap
   |
 weak key
   |
 value ───────→ key
```

The value can indirectly keep the key alive.

Therefore, designing WeakHashMap value objects requires reachability analysis.

---

# 3.7.39 WeakHashMap Edge Cases

- [ ] Key becomes unreachable.
- [ ] GC does not run immediately.
- [ ] Value references key.
- [ ] Strong reference accidentally retained elsewhere.
- [ ] Null key.
- [ ] Null value.
- [ ] Iteration while GC occurs.
- [ ] Entry disappearance.
- [ ] Identity vs equality confusion.
- [ ] Using mutable keys.
- [ ] Using WeakHashMap as a cache.

---

# 3.7.40 IdentityHashMap — What Is It?

`IdentityHashMap<K,V>` intentionally violates the normal `Map` equality model by comparing keys using reference identity:

```java
k1 == k2
```

rather than:

```java
k1.equals(k2)
```

This makes it suitable for algorithms where object identity matters.

---

# 3.7.41 Normal Map Equality vs IdentityHashMap

Normal hash maps conceptually use:

```text
hashCode()
+
equals()
```

IdentityHashMap uses:

```text
object identity
+
identity hash semantics
```

Therefore:

```java
new String("A")
new String("A")
```

are normally equal by `equals()` but are different objects.

IdentityHashMap can store both as separate keys.

---

# 3.7.42 IdentityHashMap Example

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a.equals(b)); // true
System.out.println(a == b);     // false

IdentityHashMap<String, Integer> map =
        new IdentityHashMap<>();

map.put(a, 1);
map.put(b, 2);
```

The map can contain both mappings.

---

# 3.7.43 Why IdentityHashMap Exists

Useful for:

- [ ] Object graph traversal.
- [ ] Graph algorithms where node identity matters.
- [ ] Serialization/cycle tracking.
- [ ] Deep-copy algorithms.
- [ ] Identity-sensitive metadata.
- [ ] Framework internals.

It should not be used simply because:

```text
"equals is inconvenient."
```

The identity semantics must be intentional.

---

# 3.7.44 IdentityHashMap Internal Idea

IdentityHashMap uses an array-oriented hash table with identity-based key comparison.

Conceptually:

```text
identityHashCode(key)
        ↓
table location
        ↓
key == storedKey
```

This is fundamentally different from:

```text
hashCode()
+
equals()
```

---

# 3.7.45 IdentityHashMap and Mutable Keys

Identity-based lookup is unaffected by changes to:

```java
key.hashCode()
key.equals(...)
```

because the map is based on identity.

This can be useful for identity graphs.

But identity semantics can also surprise developers expecting ordinary Map behavior.

---

# 3.7.46 IdentityHashMap Contract Caveat

IdentityHashMap intentionally violates the general-purpose Map contract's conceptual equality expectations in order to provide identity semantics.

Therefore:

> Use it only when identity-based semantics are explicitly required.

---

# 3.7.47 IdentityHashMap Edge Cases

- [ ] Two equal but distinct objects.
- [ ] Same object inserted twice.
- [ ] Null key.
- [ ] Mutable key.
- [ ] `equals()` vs `==`.
- [ ] `hashCode()` vs `System.identityHashCode()`.
- [ ] Map equality interactions.
- [ ] Serialization/cycle detection.
- [ ] Accidental use where value equality was intended.

---

# 3.7.48 EnumMap — What Is It?

`EnumMap<K extends Enum<K>, V>` is a specialized Map implementation for enum keys.

Example:

```java
enum Status {
    NEW, PROCESSING, COMPLETE
}

EnumMap<Status, String> map =
        new EnumMap<>(Status.class);

map.put(Status.NEW, "Created");
map.put(Status.PROCESSING, "Running");
map.put(Status.COMPLETE, "Finished");
```

---

# 3.7.49 Why EnumMap Exists

Enums have a fixed, known universe of keys.

Instead of a general-purpose hash table, EnumMap can exploit:

```text
enum ordinal
+
known key universe
```

This provides efficient and compact storage.

---

# 3.7.50 EnumMap Internal Model

Conceptually:

```text
enum:
NEW        ordinal 0
PROCESSING ordinal 1
COMPLETE   ordinal 2

array:
[ value0 ][ value1 ][ value2 ]
```

The actual implementation uses an array-backed representation associated with enum constants.

This is why EnumMap can be highly efficient.

---

# 3.7.51 EnumMap Ordering

EnumMap iteration follows the natural order of enum constants:

```java
enum Status {
    NEW,
    PROCESSING,
    COMPLETE
}
```

Iteration follows:

```text
NEW
PROCESSING
COMPLETE
```

rather than arbitrary hash ordering.

---

# 3.7.52 EnumMap Null Behavior

EnumMap does not permit:

```text
null key
```

because keys must be enum constants.

It can support null values.

This differs from:

```text
ConcurrentHashMap
```

which prohibits both null keys and null values.

---

# 3.7.53 EnumMap Performance

For enum keys, EnumMap can provide excellent performance.

Conceptually:

```text
key
 ↓
ordinal
 ↓
array index
 ↓
value
```

This avoids the general-purpose hashing machinery required by HashMap.

---

# 3.7.54 EnumMap Use Cases

Excellent for:

- [ ] State-to-handler mappings.
- [ ] Enum-to-configuration mappings.
- [ ] Enum-to-strategy mappings.
- [ ] State machines.
- [ ] Feature configuration.
- [ ] Permission/state tables.
- [ ] Protocol state mappings.

Example:

```java
EnumMap<Status, Runnable> handlers =
        new EnumMap<>(Status.class);
```

---

# 3.7.55 EnumMap vs HashMap

| Property | EnumMap | HashMap |
|---|---|---|
| Keys | Enum only | Any object |
| Storage model | Enum-specialized | General hash table |
| Ordering | Enum declaration order | No guaranteed ordering |
| Null key | No | Yes |
| Null value | Yes | Yes |
| Typical performance | Excellent for enum keys | General-purpose |
| Best use | Enum-keyed map | General mapping |

---

# 3.7.56 Immutable Maps

Java provides several ways to expose maps that callers cannot mutate through the returned reference.

Common APIs:

```java
Map.of(...)
Map.ofEntries(...)
Map.copyOf(...)
Collections.unmodifiableMap(...)
```

These are not all semantically identical.

---

# 3.7.57 `Map.of()`

Example:

```java
Map<String, Integer> map =
        Map.of(
            "A", 1,
            "B", 2
        );
```

The returned map is immutable.

Attempting:

```java
map.put("C", 3);
```

throws:

```text
UnsupportedOperationException
```

---

# 3.7.58 `Map.ofEntries()`

Useful when creating larger immutable maps:

```java
Map<String, Integer> map =
        Map.ofEntries(
            Map.entry("A", 1),
            Map.entry("B", 2),
            Map.entry("C", 3)
        );
```

The map cannot be structurally modified.

---

# 3.7.59 `Map.copyOf()`

Example:

```java
Map<String, Integer> source =
        new HashMap<>();

source.put("A", 1);

Map<String, Integer> immutable =
        Map.copyOf(source);
```

Later:

```java
source.put("B", 2);
```

does not make the returned map a live mutable view of `source`.

Understand the distinction between:

```text
copy
vs.
unmodifiable view
```

---

# 3.7.60 `Collections.unmodifiableMap()`

Example:

```java
Map<String, Integer> source =
        new HashMap<>();

Map<String, Integer> view =
        Collections.unmodifiableMap(source);
```

The returned object is a read-only view.

But:

```java
source.put("A", 1);
```

can still change what is visible through:

```java
view
```

Therefore:

```text
unmodifiable view
≠
immutable snapshot
```

---

# 3.7.61 Immutable vs Unmodifiable

This distinction is critical.

## Unmodifiable View

```text
source map
   ↓
unmodifiable view
```

Source can still mutate.

## Immutable Map

```text
source
   ↓
immutable representation
```

The returned map itself cannot be structurally changed.

This distinction should be part of your production API design knowledge.

---

# 3.7.62 Immutable Map Null Restrictions

Factory maps such as:

```java
Map.of()
Map.ofEntries()
Map.copyOf()
```

do not allow null keys or null values.

This is an important migration consideration.

---

# 3.7.63 Immutable Map and Thread Safety

Immutable data structures are naturally easier to share between threads because their state cannot be changed after construction.

Conceptually:

```text
construct once
     ↓
publish safely
     ↓
many readers
```

This reduces synchronization requirements.

However:

> Immutability of the map does not automatically make mutable objects stored inside it immutable.

---

# 3.7.64 Nested Mutability

Example:

```java
Map<String, List<String>> map =
        Map.of("users", new ArrayList<>());
```

The map cannot be structurally changed through its API, but the list can still be modified:

```java
map.get("users").add("Alice");
```

Therefore:

```text
immutable map
≠
deeply immutable object graph
```

Deep immutability requires immutable values/collections throughout the graph.

---

# 3.7.65 Immutable Map Performance

Immutable maps can provide:

```text
low mutation overhead
+
safe sharing
+
predictable state
+
reduced synchronization
```

But construction may require:

```text
copying
+
allocation
```

Use them especially for:

```text
configuration
constants
lookup tables
API return values
read-only state
```

---

# 3.7.66 Map Selection Decision Tree

Ask:

```text
Need general hash lookup?
        |
       yes
        ↓
     HashMap
```

Need predictable insertion/access ordering?

```text
yes → LinkedHashMap
```

Need sorted/navigable keys?

```text
yes → TreeMap
```

Need object-lifetime-sensitive weak keys?

```text
yes → WeakHashMap
```

Need identity (`==`) semantics?

```text
yes → IdentityHashMap
```

Need enum keys?

```text
yes → EnumMap
```

Need concurrent access?

```text
yes → ConcurrentHashMap
```

Need immutable/read-only map?

```text
yes → Map.of / Map.copyOf / unmodifiableMap
```

---

# 3.7.67 Choosing Between LinkedHashMap and TreeMap

Use:

```text
LinkedHashMap
```

when you need:

```text
hash lookup
+
insertion/access order
```

Use:

```text
TreeMap
```

when you need:

```text
sorted order
+
range queries
+
floor/ceiling navigation
```

Do not choose TreeMap merely because:

```text
"it maintains order."
```

Its order is sorted order, not insertion order.

---

# 3.7.68 Choosing Between WeakHashMap and Normal Map

Use WeakHashMap only when:

```text
key lifetime
```

should influence entry lifetime.

Do not use it as a generic memory-saving map.

Ask:

```text
Should this mapping disappear because the key
is no longer strongly reachable?
```

If the answer is no, WeakHashMap is probably the wrong abstraction.

---

# 3.7.69 Choosing IdentityHashMap

Use IdentityHashMap when:

```text
object identity
```

is part of the algorithm.

Typical question:

```text
"Do I care whether these are literally the same object?"
```

If no:

```text
use HashMap / another equality-based map
```

---

# 3.7.70 Choosing EnumMap

If:

```java
K extends Enum<K>
```

then always consider:

```java
EnumMap
```

before automatically using HashMap.

The specialized implementation communicates intent and can provide excellent efficiency.

---

# 3.7.71 Common Mistakes

- [ ] Assuming LinkedHashMap is automatically thread-safe.
- [ ] Confusing insertion-order and access-order.
- [ ] Implementing LRU without enabling access-order.
- [ ] Treating LinkedHashMap as a complete production cache.
- [ ] Assuming TreeMap uses hashing.
- [ ] Forgetting TreeMap is O(log N).
- [ ] Using a comparator inconsistent with equals without understanding the consequences.
- [ ] Mutating TreeMap keys after insertion.
- [ ] Using WeakHashMap as a TTL cache.
- [ ] Assuming WeakHashMap removes entries immediately after a key becomes unreachable.
- [ ] Allowing values in WeakHashMap to strongly retain their keys.
- [ ] Using IdentityHashMap when value equality is intended.
- [ ] Forgetting IdentityHashMap uses `==`.
- [ ] Ignoring EnumMap for enum-keyed data.
- [ ] Assuming `Map.of()` allows null.
- [ ] Confusing `Map.copyOf()` with `unmodifiableMap()`.
- [ ] Assuming an immutable map is deeply immutable.
- [ ] Assuming unmodifiable means immutable.
- [ ] Assuming immutable maps preserve insertion order unless documented otherwise.

---

# 3.7.72 Edge-Case Lab

Implement tests for:

## LinkedHashMap

- [ ] Duplicate insertion.
- [ ] Access-order.
- [ ] `get()` reordering.
- [ ] `put()` behavior.
- [ ] LRU eviction.
- [ ] Null key/value.
- [ ] Concurrent access.

## TreeMap

- [ ] Natural ordering.
- [ ] Reverse comparator.
- [ ] Comparator inconsistent with equals.
- [ ] Null key.
- [ ] Null value.
- [ ] Mutable key.
- [ ] Range views.
- [ ] Descending views.

## WeakHashMap

- [ ] Strong key reference.
- [ ] Remove strong reference.
- [ ] Trigger GC pressure.
- [ ] Observe eventual entry removal.
- [ ] Value retains key.
- [ ] Null key/value.

## IdentityHashMap

- [ ] Equal but distinct objects.
- [ ] Same object inserted twice.
- [ ] Null key.
- [ ] Mutable objects.
- [ ] Compare `equals()` vs `==`.

## EnumMap

- [ ] Enum ordering.
- [ ] Missing enum value.
- [ ] Null key.
- [ ] Null value.
- [ ] Compare with HashMap.

## Immutable Maps

- [ ] `Map.of`.
- [ ] `Map.ofEntries`.
- [ ] `Map.copyOf`.
- [ ] `Collections.unmodifiableMap`.
- [ ] Mutate backing map.
- [ ] Attempt mutation through returned map.
- [ ] Null keys/values.
- [ ] Mutable nested values.

---

# 3.7.73 Coding Exercises

## Basic

- [ ] Build an insertion-ordered LinkedHashMap.
- [ ] Build an access-ordered LinkedHashMap.
- [ ] Traverse a TreeMap using all major navigation methods.
- [ ] Build a TreeMap with a custom Comparator.
- [ ] Build a WeakHashMap demonstration.
- [ ] Demonstrate IdentityHashMap with equal objects.
- [ ] Build an EnumMap.
- [ ] Build immutable maps using `Map.of`.

## Intermediate

- [ ] Implement an LRU cache using LinkedHashMap.
- [ ] Implement reverse-sorted TreeMap.
- [ ] Implement a range-query service using TreeMap.
- [ ] Build object metadata using WeakHashMap.
- [ ] Implement an identity-based graph traversal.
- [ ] Implement enum-to-strategy dispatch using EnumMap.
- [ ] Compare `Map.copyOf()` with `Collections.unmodifiableMap()`.

## Advanced

- [ ] Implement an LRU cache with metrics.
- [ ] Implement an LRU cache with maximum capacity and hit/miss statistics.
- [ ] Design a concurrent wrapper around an LRU map.
- [ ] Implement a simplified red-black tree.
- [ ] Implement an identity-based deep-copy tracker.
- [ ] Build a state machine using EnumMap.
- [ ] Design an immutable configuration registry.
- [ ] Benchmark HashMap vs LinkedHashMap vs TreeMap vs EnumMap.

## Production-Style

- [ ] Design an in-memory LRU cache.
- [ ] Design a sorted event scheduler using TreeMap.
- [ ] Design object-lifetime metadata using WeakHashMap.
- [ ] Design an enum-driven command/handler registry.
- [ ] Design a read-only configuration API using immutable maps.
- [ ] Migrate a HashMap to the correct specialized implementation and justify the decision.

---

# 3.7.74 Production Scenario — LRU Cache

Requirements:

```text
maximum 10,000 entries
fast lookup
evict least recently used
predictable eviction
```

Design using:

```text
LinkedHashMap
+
access-order
+
removeEldestEntry
```

Then analyze:

```text
thread safety
+
memory
+
eviction cost
+
cache hit rate
+
hot keys
+
metrics
+
TTL requirements
```

Explain why the implementation may be insufficient for a high-scale production cache.

---

# 3.7.75 Production Scenario — Sorted Event Scheduler

Requirements:

```text
event timestamp → events
```

Need:

```text
earliest event
next event after timestamp
range of events
remove earliest
```

Consider:

```java
TreeMap<Instant, List<Event>>
```

Explain why:

```text
HashMap
```

is not the natural choice.

---

# 3.7.76 Production Scenario — Enum State Machine

Given:

```java
enum OrderState {
    CREATED,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
```

Build:

```java
EnumMap<OrderState, Handler>
```

Requirements:

- [ ] One handler per state.
- [ ] Validate missing handlers.
- [ ] Execute correct handler.
- [ ] Maintain enum declaration ordering.
- [ ] Explain why EnumMap is appropriate.

---

# 3.7.77 Production Scenario — Weak Metadata

Suppose a framework associates metadata with application objects:

```text
Object → Metadata
```

Requirements:

```text
metadata must not keep objects alive
```

Evaluate:

```text
HashMap
WeakHashMap
IdentityHashMap
```

Explain the correct choice and identify the reachability hazards.

---

# 3.7.78 Production Scenario — Immutable Configuration

Create:

```java
Map<String, String>
```

that should be safely shared across many threads.

Compare:

```text
HashMap
Collections.unmodifiableMap
Map.copyOf
Map.of
```

Discuss:

```text
mutability
+
backing map changes
+
null behavior
+
thread sharing
+
nested mutable values
```

---

# 3.7.79 Performance Comparison

Benchmark:

```text
HashMap
LinkedHashMap
TreeMap
EnumMap
```

Measure:

- [ ] `get`.
- [ ] `put`.
- [ ] `remove`.
- [ ] Iteration.
- [ ] Memory footprint.
- [ ] Allocation.
- [ ] CPU.
- [ ] Large-map behavior.

Use realistic workloads.

Do not conclude:

```text
"O(1) is always faster than O(log N)"
```

without considering:

```text
constant factors
+
cache locality
+
memory layout
+
workload
+
iteration behavior
```

---

# 3.7.80 Deep Internal Investigation

Inspect current OpenJDK implementations for:

```text
LinkedHashMap
TreeMap
WeakHashMap
IdentityHashMap
EnumMap
```

Study:

## LinkedHashMap

- [ ] Entry/link structure.
- [ ] Before/after links.
- [ ] Insertion-order behavior.
- [ ] Access-order behavior.
- [ ] `afterNodeAccess`.
- [ ] `afterNodeInsertion`.
- [ ] `removeEldestEntry`.

## TreeMap

- [ ] Entry/node structure.
- [ ] Red/black state.
- [ ] Rotations.
- [ ] Recoloring.
- [ ] Insert balancing.
- [ ] Delete balancing.
- [ ] Comparator usage.
- [ ] Navigable operations.

## WeakHashMap

- [ ] WeakReference.
- [ ] ReferenceQueue.
- [ ] Stale-entry cleanup.
- [ ] Expunging stale entries.
- [ ] GC interaction.

## IdentityHashMap

- [ ] Identity comparison.
- [ ] Identity hash code.
- [ ] Array representation.
- [ ] Collision handling.
- [ ] Deletion behavior.

## EnumMap

- [ ] Enum universe.
- [ ] Ordinal indexing.
- [ ] Array-backed storage.
- [ ] Null handling.
- [ ] Iteration order.

---

# 3.7.81 Advanced Follow-Ups

- [ ] HashMap vs LinkedHashMap source comparison.
- [ ] LinkedHashMap access-order implementation.
- [ ] LRU cache eviction mechanics.
- [ ] TreeMap red-black rotations.
- [ ] TreeMap comparator semantics.
- [ ] WeakReference and ReferenceQueue.
- [ ] GC reachability levels.
- [ ] Identity hash code behavior.
- [ ] Enum ordinal and enum universe.
- [ ] EnumMap memory efficiency.
- [ ] Immutable collection implementation strategies.
- [ ] Unmodifiable view vs immutable copy.
- [ ] Concurrent access to specialized maps.
- [ ] Cache implementation alternatives.
- [ ] Memory profiling of map implementations.

---

# 3.7.82 Interview Questions

## Basic

- [ ] What is LinkedHashMap?
- [ ] How is LinkedHashMap different from HashMap?
- [ ] What is insertion order?
- [ ] What is access order?
- [ ] What is TreeMap?
- [ ] What data structure does TreeMap use?
- [ ] What is a red-black tree?
- [ ] What is WeakHashMap?
- [ ] What is IdentityHashMap?
- [ ] What is EnumMap?
- [ ] How do you create an immutable map?

## Intermediate

- [ ] How does LinkedHashMap maintain order?
- [ ] How do you implement LRU using LinkedHashMap?
- [ ] What does `removeEldestEntry()` do?
- [ ] Why is LinkedHashMap not thread-safe?
- [ ] Why does TreeMap have O(log N) operations?
- [ ] What happens when a TreeMap comparator returns zero?
- [ ] How does WeakHashMap interact with GC?
- [ ] Why can WeakHashMap entries disappear?
- [ ] Why does IdentityHashMap use `==`?
- [ ] When should EnumMap be preferred?
- [ ] Difference between `Map.copyOf()` and `unmodifiableMap()`?

## Advanced

- [ ] Explain LinkedHashMap internal links.
- [ ] Explain access-order mechanics.
- [ ] Explain LRU eviction.
- [ ] Explain TreeMap red-black balancing.
- [ ] Explain TreeMap rotations.
- [ ] Explain WeakReference and ReferenceQueue.
- [ ] Explain WeakHashMap stale-entry cleanup.
- [ ] Explain IdentityHashMap's identity hash behavior.
- [ ] Explain EnumMap's array-backed representation.
- [ ] Explain immutable map implementation trade-offs.

## Senior / Production

- [ ] How would you design a thread-safe LRU cache?
- [ ] Why might LinkedHashMap be unsuitable for a high-throughput cache?
- [ ] When would TreeMap outperform a sorted list?
- [ ] How would you design a time-based scheduler with TreeMap?
- [ ] How would you diagnose a WeakHashMap that is not releasing expected keys?
- [ ] When is IdentityHashMap appropriate in framework code?
- [ ] Why can EnumMap outperform HashMap for enum keys?
- [ ] How would you safely publish immutable maps?
- [ ] What does "immutable map" mean when values are mutable?
- [ ] How would you choose among HashMap, LinkedHashMap, TreeMap, WeakHashMap, IdentityHashMap, EnumMap, and ConcurrentHashMap?

---

# 3.7.83 Mastery Matrix

| Implementation | Primary Skill | Internal Concept | Key Production Concern |
|---|---|---|---|
| LinkedHashMap | Ordering | Hash table + linked list | Memory / concurrency |
| TreeMap | Sorted access | Red-black tree | O(log N), comparator correctness |
| WeakHashMap | Reachability | WeakReference + ReferenceQueue | GC-dependent lifecycle |
| IdentityHashMap | Object identity | Identity comparison | Easy semantic misuse |
| EnumMap | Enum specialization | Array/ordinal mapping | Enum-only keys |
| Immutable maps | Safe sharing | Immutable representation/view | Shallow vs deep immutability |

---

# 3.7.84 Final Mastery Gate

## LinkedHashMap

- [ ] Explain LinkedHashMap.
- [ ] Explain insertion-order.
- [ ] Explain access-order.
- [ ] Explain internal linked structure.
- [ ] Explain access-order updates.
- [ ] Implement LRU.
- [ ] Explain `removeEldestEntry`.
- [ ] Explain memory overhead.
- [ ] Explain thread-safety limitations.

## TreeMap

- [ ] Explain TreeMap.
- [ ] Explain red-black trees.
- [ ] Explain balancing.
- [ ] Explain rotations.
- [ ] Explain comparator semantics.
- [ ] Explain `NavigableMap`.
- [ ] Implement range queries.
- [ ] Handle comparator/equals inconsistencies.
- [ ] Explain O(log N) behavior.

## WeakHashMap

- [ ] Explain weak references.
- [ ] Explain key reachability.
- [ ] Explain GC interaction.
- [ ] Explain ReferenceQueue.
- [ ] Explain stale-entry cleanup.
- [ ] Handle values that reference keys.
- [ ] Explain why it is not a TTL cache.

## IdentityHashMap

- [ ] Explain identity semantics.
- [ ] Explain `==` vs `equals`.
- [ ] Explain identity hash behavior.
- [ ] Demonstrate equal-but-distinct keys.
- [ ] Use it for identity-sensitive algorithms.
- [ ] Explain why ordinary business logic usually should not use it.

## EnumMap

- [ ] Explain enum specialization.
- [ ] Explain ordinal-based storage.
- [ ] Explain ordering.
- [ ] Handle null keys.
- [ ] Build an enum-driven state machine.
- [ ] Compare with HashMap.

## Immutable Maps

- [ ] Explain `Map.of`.
- [ ] Explain `Map.ofEntries`.
- [ ] Explain `Map.copyOf`.
- [ ] Explain `Collections.unmodifiableMap`.
- [ ] Explain view vs copy.
- [ ] Handle null restrictions.
- [ ] Explain shallow vs deep immutability.
- [ ] Safely share immutable configuration.

## Production

- [ ] Choose the correct map for a requirement.
- [ ] Explain trade-offs.
- [ ] Benchmark alternatives.
- [ ] Debug ordering problems.
- [ ] Debug comparator problems.
- [ ] Debug GC/reachability problems.
- [ ] Debug identity/equality problems.
- [ ] Design an LRU cache.
- [ ] Design a sorted scheduler.
- [ ] Design an enum-based registry.
- [ ] Design an immutable configuration API.

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
