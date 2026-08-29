# Module 3.11 — Iteration & Sorting Deep Mastery

> **Goal:** Master Java iteration and sorting from `Iterator`/`ListIterator` fundamentals through fail-fast, weakly consistent and snapshot iteration, `Comparable`/`Comparator`, comparator composition, null handling, stable sorting, `Collections` utilities, and immutable/unmodifiable collection semantics.

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

# 3.11.1 Iterator

## What Is It?

`Iterator<E>` provides a standard way to traverse a collection without exposing its internal representation.

Core methods:

```java
boolean hasNext();
E next();
void remove();
```

Modern Java also provides:

```java
default void remove();
default void forEachRemaining(...);
```

Understand the distinction between:

```text
collection
    ↓
iterator
    ↓
traversal
```

---

# 3.11.2 Basic Iterator Example

```java
List<String> names =
    new ArrayList<>(List.of("A", "B", "C"));

Iterator<String> iterator =
    names.iterator();

while (iterator.hasNext()) {
    String name = iterator.next();
    System.out.println(name);
}
```

---

# 3.11.3 Why Iterator Exists

Without an iterator, client code would need to know the collection's internal structure.

For example:

```text
ArrayList
→ array indexes

LinkedList
→ nodes

HashSet
→ hash table

TreeSet
→ tree
```

Iterator provides a common traversal abstraction.

---

# 3.11.4 Iterator Removal

The iterator can support structural removal:

```java
Iterator<String> iterator =
    names.iterator();

while (iterator.hasNext()) {
    if (iterator.next().equals("B")) {
        iterator.remove();
    }
}
```

Important:

> Use `Iterator.remove()` when removing during iterator traversal where the iterator supports it.

Do not casually modify the collection directly while traversing it.

---

# 3.11.5 Iterator State Machine

Conceptually:

```text
iterator created
      ↓
hasNext()
      ↓
next()
      ↓
element returned
      ↓
remove() optionally
      ↓
next()
```

Understand the state requirements around:

```text
next()
remove()
```

Calling `remove()` illegally can result in:

```text
IllegalStateException
```

depending on iterator state.

---

# 3.11.6 Iterator and Enhanced for Loop

This:

```java
for (String name : names) {
    System.out.println(name);
}
```

is conceptually based on iteration through:

```text
Iterable
    ↓
Iterator
```

For ordinary object collections, understand how the compiler translates enhanced-for traversal conceptually.

---

# 3.11.7 Iterable

`Iterable<T>` exposes:

```java
Iterator<T> iterator();
```

It also supports:

```java
forEach()
spliterator()
```

Modern collection traversal therefore includes:

```text
Iterable
├── Iterator
├── forEach
└── Spliterator
```

`Spliterator` becomes particularly important for Streams and parallel processing.

---

# 3.11.8 ListIterator

`ListIterator<E>` extends the capabilities of `Iterator` for Lists.

It supports:

```java
hasNext()
next()

hasPrevious()
previous()

nextIndex()
previousIndex()

add(E)
set(E)
remove()
```

---

# 3.11.9 ListIterator Example

```java
List<String> names =
    new ArrayList<>(
        List.of("A", "B", "C")
    );

ListIterator<String> iterator =
    names.listIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

while (iterator.hasPrevious()) {
    System.out.println(iterator.previous());
}
```

It supports bidirectional traversal.

---

# 3.11.10 ListIterator Modification

Example:

```java
ListIterator<String> iterator =
    names.listIterator();

while (iterator.hasNext()) {
    String value = iterator.next();

    if (value.equals("B")) {
        iterator.set("Java");
    }
}
```

It can also insert:

```java
iterator.add("New");
```

Understand precisely how `next()`, `previous()`, `set()`, `add()`, and `remove()` interact.

---

# 3.11.11 ListIterator vs Iterator

| Feature | Iterator | ListIterator |
|---|---|---|
| Forward traversal | Yes | Yes |
| Backward traversal | No | Yes |
| Remove | Yes | Yes |
| Add | No | Yes |
| Replace/set | No | Yes |
| List-specific | No | Yes |
| Index information | No | Yes |

---

# 3.11.12 Fail-Fast Behavior

Many standard collection iterators are described as:

```text
fail-fast
```

They may detect structural modification after iterator creation and throw:

```java
ConcurrentModificationException
```

Example:

```java
List<String> list =
    new ArrayList<>(
        List.of("A", "B", "C")
    );

for (String value : list) {
    list.remove(value); // unsafe
}
```

---

# 3.11.13 Fail-Fast Is Best-Effort

Critical concept:

> Fail-fast behavior is generally a debugging aid, not a concurrency-safety guarantee.

Do not design correctness around:

```java
ConcurrentModificationException
```

It is not guaranteed to detect every possible concurrent modification.

---

# 3.11.14 Structural Modification

Understand the difference between:

```text
structural modification
```

and:

```text
non-structural modification
```

For many collections:

```text
add/remove
```

are structural.

Changing an existing object's internal state may not modify the collection structure.

The exact behavior depends on the collection and operation.

---

# 3.11.15 ModCount Concept

Many fail-fast collection implementations maintain an internal modification count conceptually similar to:

```text
modCount
```

An iterator can remember an expected count:

```text
expectedModCount
```

Conceptually:

```text
iterator created
    ↓
expectedModCount = current modification count

next()
    ↓
compare expected vs current
    ↓
different?
    ↓
ConcurrentModificationException
```

This is an implementation technique, not a universal Iterator contract.

---

# 3.11.16 Correct Removal During Iteration

Instead of:

```java
for (String value : list) {
    if (value.equals("B")) {
        list.remove(value);
    }
}
```

use:

```java
Iterator<String> iterator =
    list.iterator();

while (iterator.hasNext()) {
    if (iterator.next().equals("B")) {
        iterator.remove();
    }
}
```

Or, where appropriate:

```java
list.removeIf(value -> value.equals("B"));
```

---

# 3.11.17 Weakly Consistent Iterators

Concurrent collections often provide:

```text
weakly consistent iterators
```

They generally:

```text
do not throw ConcurrentModificationException
+
can operate while updates occur
+
may reflect some concurrent changes
```

Examples include iterators from collections such as:

```text
ConcurrentHashMap
ConcurrentLinkedQueue
```

---

# 3.11.18 Weakly Consistent Is Not Snapshot

Do not confuse:

```text
weakly consistent
```

with:

```text
snapshot
```

Weakly consistent:

```text
may observe some concurrent changes
```

Snapshot:

```text
iterates over a stable captured view
```

---

# 3.11.19 Snapshot Iterators

`CopyOnWriteArrayList` provides snapshot-style iteration.

Conceptually:

```text
T0:
[A, B, C]

iterator created
       ↓
snapshot = [A, B, C]

T1:
list.add(D)

current list:
[A, B, C, D]

iterator:
[A, B, C]
```

This is powerful for:

```text
many readers
+
few writers
```

but mutations are expensive.

---

# 3.11.20 Iterator Comparison

| Iterator type | Concurrent modification | Snapshot | Typical example |
|---|---|---|---|
| Fail-fast | May throw CME | No | `ArrayList` |
| Weakly consistent | Continues | No | `ConcurrentHashMap` |
| Snapshot | Continues | Yes | `CopyOnWriteArrayList` |

Remember:

> "Fail-safe iterator" is a common informal term, but it is not the official Java Collections Framework iterator category.

---

# 3.11.21 Comparable

`Comparable<T>` defines an object's natural ordering.

Example:

```java
class Employee
        implements Comparable<Employee> {

    private final int id;

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(id, other.id);
    }
}
```

Then:

```java
Collections.sort(employees);
```

can use the natural ordering.

---

# 3.11.22 Comparable Contract

The essential relationship is:

```text
compareTo(x) < 0
→ this comes before x

compareTo(x) == 0
→ equivalent in ordering

compareTo(x) > 0
→ this comes after x
```

Understand:

```text
signum
+
antisymmetry-like ordering behavior
+
transitivity
+
consistency
```

---

# 3.11.23 Comparable and equals()

Ideally:

```text
x.compareTo(y) == 0
```

should be consistent with:

```text
x.equals(y)
```

but Java does not require this universally.

Important examples include types whose natural ordering can consider values equivalent while `equals()` distinguishes them.

You must understand the consequences for:

```text
TreeSet
+
TreeMap
+
sorted collections
```

---

# 3.11.24 Comparator

`Comparator<T>` defines an external ordering.

Example:

```java
Comparator<Employee> bySalary =
    Comparator.comparing(Employee::salary);
```

Sort:

```java
employees.sort(bySalary);
```

This allows multiple orderings without changing the domain class.

---

# 3.11.25 Comparable vs Comparator

| Comparable | Comparator |
|---|---|
| Natural ordering | External ordering |
| Implemented by class | Separate object |
| `compareTo()` | `compare()` |
| Usually one primary ordering | Many possible orderings |
| Couples class to ordering | Keeps ordering external |

---

# 3.11.26 Comparator Factory Methods

Master:

```java
Comparator.comparing(...)
Comparator.comparingInt(...)
Comparator.comparingLong(...)
Comparator.comparingDouble(...)
```

and:

```java
Comparator.naturalOrder()
Comparator.reverseOrder()
Comparator.nullsFirst(...)
Comparator.nullsLast(...)
```

---

# 3.11.27 Comparator Chaining

Example:

```java
Comparator<Employee> comparator =
    Comparator.comparing(Employee::department)
              .thenComparing(Employee::name)
              .thenComparingInt(Employee::id);
```

Ordering:

```text
department
    ↓ tie
name
    ↓ tie
id
```

This is one of the most important modern Java sorting techniques.

---

# 3.11.28 Reversing Comparators

Example:

```java
Comparator<Employee> bySalary =
    Comparator.comparingInt(Employee::salary);

Comparator<Employee> descending =
    bySalary.reversed();
```

Be careful with:

```text
reversing the whole comparator
vs.
reversing only one field in a chain
```

Example:

```java
Comparator<Employee> order =
    Comparator.comparing(Employee::department)
              .thenComparing(
                  Comparator.comparingInt(Employee::salary)
                            .reversed()
              );
```

---

# 3.11.29 Null Handling

Null can cause failures when natural ordering is used.

Example:

```java
Comparator<String> comparator =
    Comparator.naturalOrder();
```

If null values are possible, explicitly decide the policy.

Use:

```java
Comparator.nullsFirst(
    Comparator.naturalOrder()
);
```

or:

```java
Comparator.nullsLast(
    Comparator.naturalOrder()
);
```

---

# 3.11.30 Null Handling in Comparator Chains

Example:

```java
Comparator<Employee> comparator =
    Comparator.comparing(
        Employee::department,
        Comparator.nullsLast(
            Comparator.naturalOrder()
        )
    );
```

Understand where null can occur:

```text
employee itself
+
field returned by key extractor
+
nested property
```

These are different cases.

---

# 3.11.31 Stable Sorting

A stable sort preserves the relative ordering of elements that compare as equal.

Example:

```text
Input:

A(priority=1)
B(priority=2)
C(priority=1)

sort by priority

Output:

A(priority=1)
C(priority=1)
B(priority=2)
```

A and C retain their relative order.

---

# 3.11.32 Why Stable Sorting Matters

Stable sorting is useful for:

```text
multi-level sorting
+
preserving prior ordering
+
UI result ordering
+
deterministic processing
+
data pipelines
```

For example:

```text
sort by secondary key
then stable-sort by primary key
```

can produce multi-level ordering.

Modern Java sorting APIs provide stable object sorting behavior where specified by the relevant API.

---

# 3.11.33 TimSort and Java Object Sorting

For object arrays and List sorting, Java uses optimized stable sorting algorithms in modern implementations.

Understand conceptually:

```text
TimSort
+
runs
+
merge strategy
+
stability
```

Do not assume the exact implementation for every primitive/object sorting API is identical.

Verify against the target JDK/API documentation when implementation details matter.

---

# 3.11.34 Primitive Sorting

Primitive arrays such as:

```java
int[]
long[]
double[]
```

have specialized sorting implementations.

Study why primitive sorting can use different algorithms from object sorting.

Understand:

```text
primitive vs object
+
memory layout
+
boxing avoidance
+
performance
```

---

# 3.11.35 Collections Utility Class

`java.util.Collections` provides utility algorithms and wrappers.

Master:

```java
Collections.sort()
Collections.binarySearch()
Collections.reverse()
Collections.shuffle()
Collections.rotate()
Collections.swap()
Collections.min()
Collections.max()
Collections.frequency()
Collections.disjoint()
```

Also study:

```java
Collections.unmodifiableList()
Collections.unmodifiableSet()
Collections.unmodifiableMap()
```

and synchronized wrappers.

---

# 3.11.36 Collections.sort()

Example:

```java
Collections.sort(names);
```

Modern Java code often uses:

```java
names.sort(Comparator.naturalOrder());
```

Understand the relationship between:

```text
List.sort()
+
Collections.sort()
```

and why the newer instance method is generally the more direct API.

---

# 3.11.37 Collections.binarySearch()

Example:

```java
int index =
    Collections.binarySearch(
        names,
        "Bob"
    );
```

Critical requirement:

> The list must be sorted according to the same ordering used by the search.

Otherwise the result is not meaningful.

Complexity on a random-access list can differ from a sequential-access list.

Understand why:

```text
algorithmic O(log N)
```

does not automatically mean:

```text
O(log N) practical traversal cost
```

for every List implementation.

---

# 3.11.38 Unmodifiable Collections

Example:

```java
List<String> original =
    new ArrayList<>(
        List.of("A", "B")
    );

List<String> view =
    Collections.unmodifiableList(original);
```

The returned collection prevents mutation through that reference.

But:

> It is a view, not an immutable copy.

---

# 3.11.39 Unmodifiable View vs Immutable Collection

Unmodifiable view:

```text
original
   ↓
unmodifiable view
```

If original changes:

```text
view can observe the change
```

Immutable collection:

```text
collection state cannot be modified
```

Examples:

```java
List.of("A", "B");
```

creates an immutable collection.

---

# 3.11.40 Unmodifiable Collection Example

```java
List<String> original =
    new ArrayList<>();

original.add("A");

List<String> view =
    Collections.unmodifiableList(original);

original.add("B");

System.out.println(view);
// [A, B]
```

The view reflects changes made through the original mutable collection.

---

# 3.11.41 `List.of()` and Immutable Collections

Modern Java provides:

```java
List.of()
Set.of()
Map.of()
Map.ofEntries()
```

These produce unmodifiable collections.

Understand:

```text
no structural modification
+
null restrictions
+
compact implementations
+
value semantics appropriate to collection contents
```

Do not assume every immutable-looking object inside them is itself immutable.

---

# 3.11.42 Shallow Immutability

Example:

```java
List<List<String>> data =
    List.of(
        new ArrayList<>(List.of("A"))
    );
```

The outer collection cannot be structurally modified.

But the inner mutable List may still be mutable.

Therefore:

```text
unmodifiable collection
≠
deeply immutable object graph
```

This distinction is critical.

---

# 3.11.43 `List.copyOf()`

Example:

```java
List<String> copy =
    List.copyOf(original);
```

Understand the difference between:

```text
copy
+
unmodifiable view
```

`copyOf()` produces an unmodifiable collection that is independent of later structural changes to the source collection.

However, elements themselves are not deeply copied.

---

# 3.11.44 Immutable/Unmodifiable Map and Set

Study:

```java
Set.of(...)
Map.of(...)
Set.copyOf(...)
Map.copyOf(...)
```

Understand:

```text
duplicate elements
+
duplicate keys
+
null restrictions
+
iteration order
+
copy vs view
```

---

# 3.11.45 Synchronized Wrappers

Also understand:

```java
Collections.synchronizedList(...)
Collections.synchronizedSet(...)
Collections.synchronizedMap(...)
```

These are different from:

```text
ConcurrentHashMap
+
CopyOnWriteArrayList
+
other concurrent collections
```

They generally synchronize operations around a backing collection.

Iteration still requires following the API's synchronization guidance.

---

# 3.11.46 Synchronized vs Concurrent vs Unmodifiable

| Approach | Main purpose |
|---|---|
| Unmodifiable view | Prevent mutation through view |
| Immutable collection | Collection cannot be modified |
| Synchronized wrapper | Basic synchronized access |
| Concurrent collection | Designed for concurrent access/scalability |
| Copy-on-write | Read-heavy concurrency |

Do not substitute one for another without analyzing the requirement.

---

# 3.11.47 Sorting Complexity

Typical conceptual complexities:

```text
Comparison sorting:
O(N log N)

Binary search:
O(log N) comparisons

Linear search:
O(N)
```

But actual cost depends on:

```text
Comparator cost
+
key extraction
+
memory locality
+
object allocation
+
boxing
+
List implementation
```

---

# 3.11.48 Expensive Comparators

This comparator:

```java
Comparator.comparing(
    employee -> expensiveCalculation(employee)
);
```

may perform the expensive calculation repeatedly.

Consider precomputing keys when appropriate.

Study:

```text
decorate-sort-undecorate
+
key extraction
+
memoization
+
allocation trade-offs
```

where relevant.

---

# 3.11.49 Comparator Contract Violations

A comparator should provide consistent ordering.

Bad comparators can cause:

```text
incorrect ordering
+
unexpected sorted collection behavior
+
TreeSet/TreeMap anomalies
+
algorithmic assumptions breaking
```

Never use:

```java
return a - b;
```

blindly for integers because overflow can produce incorrect results.

Prefer:

```java
Integer.compare(a, b);
```

---

# 3.11.50 Comparator and Floating Point

Be careful with:

```text
NaN
+
positive zero
+
negative zero
+
infinity
```

Understand how:

```java
Double.compare()
```

differs from naive subtraction-based comparison.

---

# 3.11.51 Comparable/Comparator and TreeSet

Critical relationship:

```text
TreeSet uniqueness
```

is based on ordering comparison.

If:

```java
compareTo(a, b) == 0
```

the TreeSet can treat them as equivalent even when:

```java
a.equals(b) == false
```

This can lead to apparently "missing" elements.

---

# 3.11.52 Comparable/Comparator and TreeMap

Similarly, TreeMap uses its ordering to identify keys.

Therefore:

```text
compare == 0
```

can mean:

```text
same logical sorted-map key
```

even when `equals()` says otherwise.

This is one of the most important object-contract interactions in the Collections Framework.

---

# 3.11.53 Common Mistakes

- [ ] Modifying a collection directly during ordinary fail-fast iteration.
- [ ] Treating ConcurrentModificationException as a synchronization mechanism.
- [ ] Assuming fail-fast detection is guaranteed.
- [ ] Calling `Iterator.remove()` before `next()`.
- [ ] Assuming every iterator is fail-fast.
- [ ] Confusing weakly consistent and snapshot iteration.
- [ ] Assuming PriorityQueue iteration is sorted.
- [ ] Implementing `compareTo()` with subtraction.
- [ ] Writing inconsistent Comparator logic.
- [ ] Ignoring nulls.
- [ ] Assuming `compareTo() == 0` always means `equals() == true`.
- [ ] Forgetting comparator consistency when using TreeSet/TreeMap.
- [ ] Running binary search on an unsorted list.
- [ ] Assuming binary search is always practically O(log N).
- [ ] Assuming `Collections.unmodifiableList()` creates an immutable copy.
- [ ] Assuming `List.copyOf()` deep-copies elements.
- [ ] Assuming immutable collections make contained objects immutable.
- [ ] Using expensive comparators without considering repeated computation.
- [ ] Ignoring stability requirements.
- [ ] Confusing synchronized wrappers with concurrent collections.

---

# 3.11.54 Edge Cases

## Iterator

- [ ] Empty collection.
- [ ] `next()` without `hasNext()`.
- [ ] `next()` after exhaustion.
- [ ] Illegal `remove()`.
- [ ] Collection modification during traversal.

## ListIterator

- [ ] Forward/backward movement.
- [ ] `set()` state.
- [ ] `add()` state.
- [ ] `remove()` state.
- [ ] Iterator at beginning/end.
- [ ] Concurrent modification.

## Sorting

- [ ] Empty list.
- [ ] One element.
- [ ] Duplicate elements.
- [ ] All equal elements.
- [ ] Already sorted.
- [ ] Reverse sorted.
- [ ] Null values.
- [ ] Comparator ties.
- [ ] Comparator contract violation.
- [ ] Integer overflow.
- [ ] Floating-point special values.

## Immutable Collections

- [ ] Null values.
- [ ] Duplicate Set elements.
- [ ] Duplicate Map keys.
- [ ] Source collection modification.
- [ ] Mutable elements.
- [ ] Nested mutable collections.

---

# 3.11.55 Production Use Cases

## Iterator

- [ ] Generic collection traversal.
- [ ] Safe removal during traversal.
- [ ] Framework APIs.

## ListIterator

- [ ] Bidirectional list editing.
- [ ] In-place list transformation.
- [ ] Cursor-based processing.

## Comparable

- [ ] Domain natural ordering.
- [ ] Value objects.
- [ ] Sorted collections.

## Comparator

- [ ] API result sorting.
- [ ] Multi-field sorting.
- [ ] User-configurable ordering.
- [ ] Dynamic sorting.
- [ ] Database-like result ordering in memory.

## Immutable Collections

- [ ] Configuration.
- [ ] Constants.
- [ ] Safe API return values.
- [ ] Shared read-only data.
- [ ] Thread-safe publication.

## Concurrent Iteration

- [ ] Listener registries.
- [ ] Concurrent maps.
- [ ] Event processing.
- [ ] Read-heavy configuration.

---

# 3.11.56 Production Scenario — API Sorting

Requirement:

```text
GET /employees?sort=department,name,-salary
```

Design:

```text
parse requested fields
      ↓
validate allowed fields
      ↓
build Comparator chain
      ↓
sort
```

Consider:

```text
null handling
+
field allow-list
+
stable ordering
+
pagination
+
performance
+
large result sets
```

Never allow arbitrary reflection/expression evaluation merely to support dynamic sorting.

---

# 3.11.57 Production Scenario — Immutable API Response

Instead of exposing:

```java
return internalMutableList;
```

consider:

```java
return List.copyOf(internalList);
```

This prevents callers from modifying the returned collection structurally.

Explain why this is different from:

```java
Collections.unmodifiableList(internalList)
```

---

# 3.11.58 Production Scenario — Concurrent Registry

Requirement:

```text
many reads
+
rare writes
```

Potential choice:

```java
CopyOnWriteArrayList<Handler>
```

Iteration is snapshot-style.

Explain:

```text
why it works
+
why writes are expensive
+
why it can simplify concurrent traversal
```

---

# 3.11.59 Coding Exercises

## Basic

- [ ] Iterate an ArrayList using Iterator.
- [ ] Remove selected elements using Iterator.remove().
- [ ] Traverse a List using ListIterator.
- [ ] Modify elements using ListIterator.set().
- [ ] Insert elements using ListIterator.add().
- [ ] Implement Comparable for Employee.
- [ ] Sort Employees using natural ordering.
- [ ] Sort Employees using Comparator.

## Intermediate

- [ ] Build a comparator chain for Employee.
- [ ] Add null handling.
- [ ] Implement ascending/descending sort options.
- [ ] Implement stable multi-field sorting.
- [ ] Demonstrate fail-fast behavior.
- [ ] Demonstrate weakly consistent iteration.
- [ ] Demonstrate snapshot iteration.
- [ ] Compare unmodifiable view vs copy.
- [ ] Implement binary search with a Comparator.

## Advanced

- [ ] Implement a custom Iterator.
- [ ] Implement a custom ListIterator.
- [ ] Implement a sorted collection using a custom Comparator.
- [ ] Build a dynamic comparator builder.
- [ ] Implement stable multi-key sorting.
- [ ] Benchmark expensive vs precomputed comparator keys.
- [ ] Create a custom immutable collection wrapper.
- [ ] Demonstrate TreeSet behavior when compareTo disagrees with equals.

## Production-Style

- [ ] Implement dynamic API sorting safely.
- [ ] Implement immutable service responses.
- [ ] Design a concurrent listener traversal mechanism.
- [ ] Build a production-style multi-field sorting component.
- [ ] Add null policies and deterministic tie-breakers.
- [ ] Add tests for comparator contracts.
- [ ] Diagnose a sorting regression caused by an expensive comparator.

---

# 3.11.60 Debugging Exercise — Broken Comparator

Broken:

```java
Comparator<Integer> comparator =
    (a, b) -> a - b;
```

Test:

```text
Integer.MAX_VALUE
Integer.MIN_VALUE
```

Explain the overflow problem.

Fix:

```java
Integer.compare(a, b);
```

---

# 3.11.61 Debugging Exercise — TreeSet Duplicate

Create:

```java
class Employee {
    int id;
    String name;
}
```

Use a Comparator that compares only:

```text
name
```

Insert:

```text
Employee(1, "Alex")
Employee(2, "Alex")
```

Observe the TreeSet size.

Explain:

```text
Comparator equality
vs.
equals()
```

Then add a tie-breaker:

```text
name
→ id
```

---

# 3.11.62 Debugging Exercise — Unmodifiable View

Create:

```java
List<String> original =
    new ArrayList<>(List.of("A"));

List<String> view =
    Collections.unmodifiableList(original);
```

Then:

```java
original.add("B");
```

Observe the view.

Compare with:

```java
List<String> copy =
    List.copyOf(original);
```

Explain:

```text
view
vs.
independent unmodifiable copy
```

---

# 3.11.63 Debugging Exercise — Concurrent Iteration

Compare:

```text
ArrayList
CopyOnWriteArrayList
ConcurrentHashMap
```

Modify each during iteration.

Record:

```text
exception?
snapshot?
weak consistency?
live updates?
```

Explain the underlying collection contract.

---

# 3.11.64 OpenJDK Source Investigation

Inspect the current target JDK implementation for:

## ArrayList Iterator

- [ ] Iterator state.
- [ ] Expected modification count.
- [ ] `next()`.
- [ ] `remove()`.
- [ ] Concurrent modification checks.

## LinkedList ListIterator

- [ ] Node cursor.
- [ ] Previous/next traversal.
- [ ] `add()`.
- [ ] `set()`.
- [ ] `remove()`.

## CopyOnWriteArrayList

- [ ] Snapshot array.
- [ ] Iterator construction.
- [ ] Why iterator mutation is unsupported.

## ConcurrentHashMap

- [ ] Weakly consistent traversal.
- [ ] Traverser state.
- [ ] Resizing interaction.

## Sorting

Study the current target JDK implementation and documentation for:

```text
List.sort()
Arrays.sort()
Collections.sort()
```

Understand where:

```text
object sorting
+
primitive sorting
```

use different strategies.

Do not rely on a historical implementation when making claims about a specific modern JDK.

---

# 3.11.65 JLS / Java API Specification Investigation

Study official API contracts for:

- [ ] `Iterator`.
- [ ] `ListIterator`.
- [ ] `Iterable`.
- [ ] `Comparable`.
- [ ] `Comparator`.
- [ ] `Collections`.
- [ ] `List.sort()`.
- [ ] `Arrays.sort()`.
- [ ] `List.of()`.
- [ ] `List.copyOf()`.
- [ ] `Set.of()`.
- [ ] `Map.of()`.
- [ ] `Collections.unmodifiable*()`.

Understand the difference between:

```text
specified behavior
```

and:

```text
current implementation detail
```

---

# 3.11.66 Advanced Internal Questions

- [ ] How does Iterator abstract collection traversal?
- [ ] How does enhanced-for use Iterable/Iterator?
- [ ] What state does an Iterator maintain?
- [ ] What is fail-fast behavior?
- [ ] Why is fail-fast best-effort?
- [ ] How does modCount-style detection work?
- [ ] What is structural modification?
- [ ] Why is Iterator.remove() special?
- [ ] How does ListIterator maintain its cursor?
- [ ] How does weakly consistent iteration work?
- [ ] How does snapshot iteration work?
- [ ] Why is CopyOnWriteArrayList suitable for snapshot traversal?
- [ ] What is natural ordering?
- [ ] What is the Comparable contract?
- [ ] What is the Comparator contract?
- [ ] Why can Comparator inconsistency break sorted collections?
- [ ] Why can TreeSet treat unequal objects as duplicates?
- [ ] How does comparator chaining work?
- [ ] How should nulls be handled?
- [ ] What makes a sorting algorithm stable?
- [ ] Why does object sorting differ from primitive sorting?
- [ ] What does Collections.binarySearch() require?
- [ ] Why isn't binary search always practically O(log N)?
- [ ] What is the difference between an unmodifiable view and an immutable copy?
- [ ] Why doesn't `List.copyOf()` provide deep immutability?

---

# 3.11.67 Interview Questions

## Basic

- [ ] What is Iterator?
- [ ] What is ListIterator?
- [ ] Difference between Iterator and ListIterator?
- [ ] What is fail-fast behavior?
- [ ] What is Comparable?
- [ ] What is Comparator?
- [ ] Difference between Comparable and Comparator?
- [ ] What is stable sorting?
- [ ] What is an unmodifiable collection?
- [ ] What does `Collections.unmodifiableList()` do?

## Intermediate

- [ ] Why does ConcurrentModificationException occur?
- [ ] Is fail-fast guaranteed?
- [ ] How do you safely remove elements during iteration?
- [ ] What is weakly consistent iteration?
- [ ] What is snapshot iteration?
- [ ] Compare fail-fast, weakly consistent and snapshot iterators.
- [ ] How does Comparator chaining work?
- [ ] How do you handle nulls in a Comparator?
- [ ] Why should `Integer.compare()` be preferred over subtraction?
- [ ] What is the difference between `List.copyOf()` and `Collections.unmodifiableList()`?
- [ ] What does `Collections.binarySearch()` require?

## Advanced

- [ ] Explain Iterator internals.
- [ ] Explain ListIterator cursor behavior.
- [ ] Explain modCount/expectedModCount.
- [ ] Explain why fail-fast is best-effort.
- [ ] Explain ConcurrentHashMap iterator semantics.
- [ ] Explain CopyOnWriteArrayList iterator semantics.
- [ ] Explain Comparable's ordering contract.
- [ ] Explain Comparator contract requirements.
- [ ] Explain TreeSet behavior when compareTo disagrees with equals.
- [ ] Explain stable sorting.
- [ ] Explain comparator performance.
- [ ] Explain binary search behavior on different List implementations.

## Senior / Production

- [ ] How would you design safe dynamic API sorting?
- [ ] How would you prevent comparator-based security/performance issues?
- [ ] How would you choose between immutable copy and unmodifiable view?
- [ ] How would you expose collections safely from an API?
- [ ] How would you handle concurrent iteration over a listener registry?
- [ ] How would you diagnose a ConcurrentModificationException?
- [ ] How would you diagnose a sorting performance regression?
- [ ] How would you guarantee deterministic ordering for equal keys?
- [ ] How would you design a comparator for nullable nested fields?
- [ ] How would you handle sorting millions of records efficiently?
- [ ] When should sorting happen in the database rather than Java?
- [ ] How would you test a comparator for correctness?

---

# 3.11.68 Final Mastery Gate

## Iterator

- [ ] Explain Iterator.
- [ ] Implement Iterator traversal.
- [ ] Remove safely using Iterator.
- [ ] Explain iterator state.
- [ ] Handle exhaustion.
- [ ] Explain fail-fast behavior.

## ListIterator

- [ ] Explain bidirectional traversal.
- [ ] Implement forward/backward traversal.
- [ ] Use `set()`.
- [ ] Use `add()`.
- [ ] Use `remove()`.
- [ ] Explain cursor state.

## Iteration Semantics

- [ ] Explain fail-fast.
- [ ] Explain best-effort detection.
- [ ] Explain weakly consistent iterators.
- [ ] Explain snapshot iterators.
- [ ] Compare all three.
- [ ] Identify the appropriate collection for each behavior.

## Comparable

- [ ] Implement natural ordering.
- [ ] Explain compareTo contract.
- [ ] Explain consistency with equals.
- [ ] Handle integer overflow.
- [ ] Handle special numeric values.

## Comparator

- [ ] Implement Comparator.
- [ ] Chain comparators.
- [ ] Reverse ordering.
- [ ] Handle nulls.
- [ ] Add deterministic tie-breakers.
- [ ] Explain comparator contract.
- [ ] Analyze comparator cost.

## Sorting

- [ ] Explain stable sorting.
- [ ] Understand object vs primitive sorting.
- [ ] Explain O(N log N) comparison sorting.
- [ ] Understand comparator overhead.
- [ ] Use `Collections.sort()` / `List.sort()`.
- [ ] Use binary search correctly.

## Collections Utilities

- [ ] Use sorting utilities.
- [ ] Use search utilities.
- [ ] Use reverse/shuffle/rotate.
- [ ] Understand synchronized wrappers.
- [ ] Understand unmodifiable wrappers.

## Immutable / Unmodifiable Collections

- [ ] Explain immutable collection.
- [ ] Explain unmodifiable view.
- [ ] Explain `List.of()`.
- [ ] Explain `List.copyOf()`.
- [ ] Explain shallow vs deep immutability.
- [ ] Handle mutable elements.

## Production

- [ ] Build safe dynamic sorting.
- [ ] Design deterministic ordering.
- [ ] Expose collections safely.
- [ ] Choose correct iteration semantics.
- [ ] Debug concurrent modification.
- [ ] Benchmark sorting.
- [ ] Explain trade-offs.

---

# Final Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] INTERNALS UNDERSTOOD
- [ ] ITERATION SEMANTICS MASTERED
- [ ] COMPARABLE MASTERED
- [ ] COMPARATOR MASTERED
- [ ] SORTING MASTERED
- [ ] NULL HANDLING MASTERED
- [ ] IMMUTABILITY / UNMODIFIABLE SEMANTICS MASTERED
- [ ] EDGE CASES MASTERED
- [ ] PERFORMANCE UNDERSTOOD
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
