# Module 3.2 — ArrayList
## Deep Mastery Guide

> **Goal:** Master `ArrayList` from API usage to backing-array internals, capacity management, resizing, memory behavior, complexity, performance, debugging, and production design.

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

# 3.2.1 What Is `ArrayList`?

`ArrayList<E>` is a resizable-array implementation of the `List<E>` interface.

It provides:

- [ ] Ordered elements
- [ ] Index-based access
- [ ] Duplicate elements
- [ ] Dynamic size
- [ ] Fast random access
- [ ] Amortized constant-time append

Basic declaration:

```java
List<String> names = new ArrayList<>();
```

Implementation-specific declaration:

```java
ArrayList<String> names = new ArrayList<>();
```

Prefer the interface when implementation-specific methods are unnecessary.

---

# 3.2.2 Why Does Java Have `ArrayList`?

A normal Java array has fixed length:

```java
String[] names = new String[10];
```

Once created, its length cannot change.

`ArrayList` provides:

```text
dynamic size
      +
array-like random access
      +
List interface
```

Conceptually:

```text
Fixed Array
    ↓
fixed capacity

ArrayList
    ↓
resizable backing array
```

---

# 3.2.3 Core Characteristics

Understand these properties:

| Property | `ArrayList` |
|---|---|
| Backing structure | Resizable array |
| Random access | Fast |
| Index access | O(1) |
| Append | Amortized O(1) |
| Insert middle | O(n) |
| Delete middle | O(n) |
| Search | O(n) |
| Duplicates | Allowed |
| Null elements | Allowed |
| Ordering | Encounter order |
| Thread-safe | No |
| Generic | Yes |

---

# 3.2.4 Internal Array

The key internal concept is the backing array.

Conceptually:

```text
ArrayList
   |
   +-- elementData
          |
          +-- [A]
          +-- [B]
          +-- [C]
          +-- null
          +-- null
          +-- null
```

The array's physical length represents:

```text
capacity
```

while the number of actual logical elements represents:

```text
size
```

These are different concepts.

---

# 3.2.5 Size vs Capacity

This distinction is essential.

Example:

```java
ArrayList<String> list =
        new ArrayList<>(10);

list.add("A");
list.add("B");
```

Conceptually:

```text
size     = 2
capacity = 10
```

The list has two logical elements but room for ten element references before another resize is needed.

### Mastery Questions

- [ ] What does `size()` return?
- [ ] Is capacity exposed directly by the `List` interface?
- [ ] Why can capacity be larger than size?
- [ ] Why does unused capacity exist?
- [ ] What happens when size reaches capacity?

---

# 3.2.6 Size

```java
list.size();
```

returns the number of elements currently stored.

Example:

```java
List<String> list = new ArrayList<>();

list.add("A");
list.add("B");

System.out.println(list.size());
```

Output:

```text
2
```

It does not return the backing-array length.

---

# 3.2.7 Capacity

Capacity represents how many elements the current backing storage can accommodate before growth is required.

Important:

> Capacity is an implementation detail of `ArrayList`; it is not part of the general `List` contract.

You should not write code that depends on a particular capacity-growth formula as if it were guaranteed by the `List` interface.

---

# 3.2.8 Constructors

Common constructors:

```java
new ArrayList<>()
```

and:

```java
new ArrayList<>(initialCapacity)
```

and:

```java
new ArrayList<>(existingCollection)
```

Example:

```java
ArrayList<String> names =
        new ArrayList<>(1000);
```

This can be useful when a reasonable final size is already known.

---

# 3.2.9 Initial Capacity

Example:

```java
List<Employee> employees =
        new ArrayList<>(10_000);
```

If approximately 10,000 elements are expected, initial capacity can reduce repeated growth operations.

But do not blindly allocate huge capacities.

Trade-off:

```text
larger initial capacity
       ↓
fewer resizes

but

       ↓
more memory reserved immediately
```

---

# 3.2.10 Growth Mechanism

When the backing array becomes full, `ArrayList` must allocate a larger array and copy existing element references.

Conceptually:

```text
Before:

[A][B][C][D]

capacity = 4
size     = 4
```

Add another element:

```text
new larger array

[A][B][C][D][E][ ][ ]
```

The old array is eventually eligible for garbage collection if no other reference exists.

---

# 3.2.11 Resizing Process

Conceptually:

```text
add(element)
     ↓
Is there available capacity?
     |
   yes → store element
     |
    no
     ↓
calculate larger capacity
     ↓
allocate new array
     ↓
copy references
     ↓
replace backing array
     ↓
store new element
```

This makes an individual resize expensive, but repeated append remains amortized O(1).

---

# 3.2.12 Modern OpenJDK Growth

Current OpenJDK `ArrayList` implementations commonly grow the backing array by approximately 50% when expansion is needed.

Conceptually:

```text
old capacity
      ↓
old + old/2
      ↓
new capacity
```

For example:

```text
10
15
22
33
...
```

The exact implementation includes overflow and maximum-array-size handling.

### Important

Do not treat the exact growth formula as a permanent Java language guarantee.

It is an implementation detail and should be verified against the Java/OpenJDK version being studied.

---

# 3.2.13 Why Growth Is Not O(n) for Every `add()`

Suppose we append:

```text
A
B
C
D
...
```

Most additions simply place an element into an existing empty slot.

Occasionally:

```text
resize
+
copy
```

occurs.

Therefore:

```text
Individual worst-case add → O(n)
Typical append             → O(1)
Amortized append            → O(1)
```

This distinction is extremely important.

---

# 3.2.14 Amortized Analysis

Suppose capacity grows geometrically.

Although some individual insertions cost O(n), the expensive operations occur increasingly less often.

Across a large sequence of appends:

```text
total work
-----------
number of operations
```

remains O(1) amortized per append.

Study:

- [ ] Aggregate analysis
- [ ] Geometric growth
- [ ] Resize frequency
- [ ] Copy cost
- [ ] Amortized O(1)

---

# 3.2.15 Random Access

`ArrayList` provides fast indexed access:

```java
String value = list.get(500);
```

Conceptually:

```text
backing array
     ↓
elementData[500]
```

Therefore:

```text
get(index) → O(1)
set(index, value) → O(1)
```

subject to normal bounds checking.

---

# 3.2.16 Why Random Access Is Fast

An array stores references at predictable offsets.

Conceptually:

```text
base address
     +
index × reference-size
     ↓
target slot
```

The JVM performs the appropriate array access and bounds checking.

This is fundamentally different from traversing a linked list.

---

# 3.2.17 Index Bounds

Given:

```java
List<String> list =
        new ArrayList<>(List.of("A", "B", "C"));
```

valid indices are:

```text
0
1
2
```

This is invalid:

```java
list.get(3);
```

and:

```java
list.get(-1);
```

typically results in:

```text
IndexOutOfBoundsException
```

---

# 3.2.18 Insert at End

Example:

```java
list.add("D");
```

Usually:

```text
O(1) amortized
```

If resizing occurs:

```text
O(n)
```

for that individual operation.

---

# 3.2.19 Insert at Beginning

Example:

```java
list.add(0, "X");
```

Existing references must be shifted:

```text
Before:

[A][B][C][D]

After:

[X][A][B][C][D]
```

Therefore:

```text
O(n)
```

---

# 3.2.20 Insert in the Middle

Example:

```java
list.add(2, "X");
```

Conceptually:

```text
Before:

[A][B][C][D]

After:

[A][B][X][C][D]
```

Elements after the insertion point must move.

Complexity:

```text
O(n)
```

relative to the number of shifted elements.

---

# 3.2.21 Delete from End

Example:

```java
list.remove(list.size() - 1);
```

Usually:

```text
O(1)
```

Only the final slot needs to be cleared.

---

# 3.2.22 Delete from Beginning

Example:

```java
list.remove(0);
```

Conceptually:

```text
Before:

[A][B][C][D]

After:

[B][C][D][ ]
```

References must shift left.

Complexity:

```text
O(n)
```

---

# 3.2.23 Delete from Middle

Example:

```java
list.remove(2);
```

Elements after the removed index are shifted.

Complexity:

```text
O(n)
```

---

# 3.2.24 Search Complexity

Methods such as:

```java
contains()
indexOf()
lastIndexOf()
```

generally perform linear scanning.

Typical complexity:

```text
O(n)
```

Example:

```java
list.contains("Alice");
```

If the element is near the beginning, the search may terminate early.

Worst case:

```text
O(n)
```

---

# 3.2.25 `remove(Object)` vs `remove(int)`

This is a common Java trap.

Given:

```java
ArrayList<Integer> values =
        new ArrayList<>(List.of(10, 20, 30));
```

This:

```java
values.remove(1);
```

means:

```text
remove element at index 1
```

not:

```text
remove Integer value 1
```

To remove the value:

```java
values.remove(Integer.valueOf(1));
```

---

# 3.2.26 Memory Layout

An `ArrayList` object contains object state including a reference to its backing array.

Conceptually:

```text
ArrayList object
     |
     +-- backing array reference
              |
              +-- reference slots
              +-- reference slots
              +-- reference slots
```

For reference types, the backing array contains references to objects, not the objects themselves.

Example:

```java
List<Employee> employees;
```

does not place complete Employee objects inline inside the backing array.

Conceptually:

```text
ArrayList
   |
   +-- [ref] → Employee object
   +-- [ref] → Employee object
   +-- [ref] → Employee object
```

---

# 3.2.27 Memory Overhead

`ArrayList` has several layers of memory overhead:

```text
ArrayList object
+
backing array object
+
unused capacity
+
referenced element objects
```

For object elements:

```text
element references
    ≠
element objects
```

This distinction matters when estimating memory.

---

# 3.2.28 Null Slots

After removing an element, the implementation clears the obsolete reference.

Conceptually:

```text
Before:

[A][B][C]

remove B

[A][C][null]
```

Clearing the reference matters because otherwise the removed object could remain strongly reachable through the backing array and unnecessarily remain alive.

This is an important memory-management detail.

---

# 3.2.29 Capacity Management

`ArrayList` supports capacity-management methods through its concrete API, including:

```java
ensureCapacity(int)
trimToSize()
```

### `ensureCapacity()`

Useful when you can reasonably predict future growth:

```java
list.ensureCapacity(10_000);
```

This can reduce repeated resizing.

### `trimToSize()`

Requests that the backing storage be adjusted toward the current size.

Use carefully because future growth may then require expansion again.

---

# 3.2.30 `ensureCapacity()` Trade-off

Good scenario:

```text
Known large batch
↓
approximately 1,000,000 elements
↓
ensure capacity
↓
fewer growth operations
```

Bad scenario:

```text
ensureCapacity(1_000_000_000)
```

without a realistic need.

Possible result:

```text
large memory reservation
```

and potentially allocation failure.

---

# 3.2.31 `trimToSize()` Trade-off

Suppose:

```text
size     = 10
capacity = 1,000,000
```

Trimming can reduce unused storage.

But if the list soon grows again:

```text
10
 ↓
trim
 ↓
capacity ≈ 10
 ↓
add thousands of elements
 ↓
multiple growth operations
```

Therefore trimming is not automatically an optimization.

---

# 3.2.32 `clear()` vs `trimToSize()`

Important distinction:

```java
list.clear();
```

removes logical elements.

It does not necessarily reduce the backing-array capacity.

Conceptually:

```text
Before:

size = 100
capacity = 1000

clear()

size = 0
capacity ≈ 1000
```

If storage reduction is required, capacity management is a separate concern.

---

# 3.2.33 `ArrayList` and Garbage Collection

When elements are removed:

```text
logical element removed
       ↓
backing-array reference cleared
       ↓
object may become unreachable
       ↓
GC may reclaim it later
```

Important:

> `remove()` does not immediately invoke garbage collection.

It only removes the strong reference maintained by the list.

---

# 3.2.34 Thread Safety

`ArrayList` is not synchronized.

This means concurrent unsynchronized mutation is unsafe.

Example:

```text
Thread A → add()
Thread B → remove()
```

without appropriate synchronization can cause race conditions.

For concurrent designs, evaluate:

```text
Collections.synchronizedList()
CopyOnWriteArrayList
external locking
concurrent queues
other specialized structures
```

Do not automatically replace every ArrayList with a concurrent collection.

---

# 3.2.35 Fail-Fast Iterators

`ArrayList` iterators commonly detect structural modification and may throw:

```text
ConcurrentModificationException
```

Example:

```java
for (String name : list) {
    list.add("X");
}
```

This behavior is fail-fast on a best-effort basis.

It is not a concurrency-control mechanism.

---

# 3.2.36 Structural Modification

Structural modification generally means changing the collection's structure, such as:

```text
add
remove
clear
```

Changing an existing object's internal state is not necessarily a structural modification of the list.

This distinction matters when understanding iterator behavior.

---

# 3.2.37 `ArrayList` and `RandomAccess`

`ArrayList` implements:

```java
RandomAccess
```

This marker interface indicates that indexed access is expected to be fast.

It can allow generic algorithms to choose different strategies depending on whether a List supports efficient random access.

---

# 3.2.38 `ArrayList` vs Array

### Array

```java
String[] array = new String[100];
```

Properties:

- fixed length
- low abstraction overhead
- direct array operations
- supports primitives directly

### ArrayList

```java
List<String> list = new ArrayList<>();
```

Properties:

- dynamic size
- List API
- generic type
- object/reference semantics
- capacity management
- collection utilities

---

# 3.2.39 `ArrayList` vs `LinkedList`

| Property | ArrayList | LinkedList |
|---|---:|---:|
| Random access | O(1) | O(n) |
| Append | Amortized O(1) | O(1) at end |
| Middle insertion | O(n) | O(n) traversal, then local link changes |
| Memory locality | Good | Poorer |
| Per-element node overhead | No | Yes |
| Cache friendliness | Generally better | Generally worse |
| Typical general-purpose choice | Often preferred | Specialized use cases |

Do not conclude that LinkedList is faster merely because node insertion itself can be O(1). Finding the insertion point can dominate.

---

# 3.2.40 Common Mistakes

- [ ] Confusing size with capacity.
- [ ] Assuming `add()` is always O(1).
- [ ] Ignoring resize costs.
- [ ] Repeatedly inserting at index 0 in a large ArrayList.
- [ ] Repeatedly deleting from the beginning.
- [ ] Assuming `clear()` releases all backing storage.
- [ ] Calling `trimToSize()` unnecessarily.
- [ ] Using `ensureCapacity()` with unrealistic values.
- [ ] Assuming ArrayList is thread-safe.
- [ ] Modifying the list during iteration.
- [ ] Confusing `remove(int)` with `remove(Object)`.
- [ ] Assuming the backing array contains complete objects.
- [ ] Treating implementation-specific capacity behavior as a Java language guarantee.
- [ ] Choosing LinkedList solely because insertion is theoretically O(1).

---

# 3.2.41 Edge Cases

Investigate:

- [ ] Empty ArrayList.
- [ ] Single element.
- [ ] Null elements.
- [ ] Duplicate elements.
- [ ] Index `-1`.
- [ ] Index `size()`.
- [ ] Integer overflow in huge capacity calculations.
- [ ] Very large lists.
- [ ] Out-of-memory conditions.
- [ ] Concurrent modification.
- [ ] Iterator invalidation.
- [ ] `subList()` behavior.
- [ ] `clear()` and retained capacity.
- [ ] `trimToSize()` followed by growth.
- [ ] `ensureCapacity()` with large values.

---

# 3.2.42 Debugging Challenges

## Challenge 1 — Size vs Capacity

Create a list with:

```java
new ArrayList<>(1000)
```

Add:

```text
10 elements
```

Determine:

```text
logical size
backing capacity
```

Explain why these values differ.

---

## Challenge 2 — Resize Detection

Create an experiment that adds thousands of elements.

Observe when the backing array changes using debugging tools or OpenJDK source inspection.

Do not rely on a fixed growth sequence as a language guarantee.

---

## Challenge 3 — Remove Trap

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(List.of(1, 2, 3, 4));

numbers.remove(1);
```

Tasks:

- [ ] Predict the result.
- [ ] Explain overload resolution.
- [ ] Remove the value `1` instead.

---

## Challenge 4 — Performance Trap

Compare:

```java
for (int i = 0; i < n; i++) {
    list.add(0, i);
}
```

with:

```java
for (int i = 0; i < n; i++) {
    list.add(i);
}
```

Explain the complexity difference.

---

## Challenge 5 — Retained Capacity

Create:

```text
large ArrayList
↓
add millions of elements
↓
clear()
```

Investigate:

- [ ] Logical size.
- [ ] Capacity.
- [ ] Heap usage.
- [ ] Whether the backing array itself remains allocated.
- [ ] Whether `trimToSize()` changes the situation.

---

# 3.2.43 Coding Exercises

## Basic

- [ ] Create an ArrayList.
- [ ] Add elements.
- [ ] Retrieve elements by index.
- [ ] Replace an element.
- [ ] Remove by index.
- [ ] Remove by value.
- [ ] Search with `contains()`.
- [ ] Iterate using `for`.
- [ ] Iterate using `Iterator`.
- [ ] Clear the list.

## Intermediate

- [ ] Implement a dynamic-array class from scratch.
- [ ] Maintain separate `size` and `capacity`.
- [ ] Implement automatic growth.
- [ ] Implement `get()`.
- [ ] Implement `set()`.
- [ ] Implement `add()`.
- [ ] Implement `add(index, value)`.
- [ ] Implement `remove(index)`.
- [ ] Implement `ensureCapacity()`.
- [ ] Implement `trimToSize()`.
- [ ] Add bounds checking.
- [ ] Write unit tests for all operations.

## Advanced

Implement:

```text
MyArrayList<E>
```

with:

```java
E get(int index);
int size();
boolean isEmpty();
boolean add(E element);
void add(int index, E element);
E remove(int index);
boolean remove(Object value);
E set(int index, E element);
void clear();
void ensureCapacity(int capacity);
```

Then add:

- [ ] Iterator.
- [ ] Fail-fast modification detection.
- [ ] Generic type safety.
- [ ] Capacity-growth strategy.
- [ ] Overflow handling.
- [ ] Efficient bulk operations.

---

# 3.2.44 Performance Lab

Benchmark:

### Test A

Append:

```java
list.add(value);
```

### Test B

Insert at beginning:

```java
list.add(0, value);
```

### Test C

Remove from end.

### Test D

Remove from beginning.

### Test E

Pre-sized ArrayList:

```java
new ArrayList<>(expectedSize)
```

### Test F

Default-capacity growth.

Measure:

- [ ] Throughput.
- [ ] Latency.
- [ ] Allocation.
- [ ] GC activity.
- [ ] Heap usage.
- [ ] Number of resize operations.
- [ ] Impact of initial capacity.

Use a proper benchmarking framework such as JMH for serious measurements rather than drawing production conclusions from a single `System.nanoTime()` loop.

---

# 3.2.45 Production Use Cases

`ArrayList` is often an excellent default when:

```text
random access is important
iteration is frequent
append is common
middle insertion/removal is uncommon
```

Examples:

- API response collections.
- Database query results.
- In-memory configuration lists.
- Ordered processing results.
- DTO collections.
- Batch-processing buffers.
- Read-heavy collections.

---

# 3.2.46 When Not to Prefer ArrayList

Consider another structure when:

```text
frequent insertion/removal at the front
frequent two-ended operations
priority ordering
key-based lookup
concurrent specialized access
```

Possible alternatives:

```text
Deque
HashMap
PriorityQueue
ConcurrentHashMap
CopyOnWriteArrayList
ArrayDeque
```

Choose based on required semantics rather than theoretical complexity alone.

---

# 3.2.47 Production Design Questions

Before choosing ArrayList:

```text
1. How large can the list become?
2. Is random access required?
3. How frequently is it modified?
4. Where are insertions performed?
5. Where are removals performed?
6. Are duplicates meaningful?
7. Is ordering meaningful?
8. Is concurrent mutation required?
9. Can the size be estimated?
10. Does memory footprint matter?
11. Should callers receive a mutable or immutable view?
12. Will the collection be retained for a long lifetime?
```

---

# 3.2.48 Interview Questions

## Basic

- [ ] What is ArrayList?
- [ ] How does ArrayList differ from an array?
- [ ] Does ArrayList allow duplicates?
- [ ] Does ArrayList allow null?
- [ ] Is ArrayList thread-safe?
- [ ] What is random access?
- [ ] What is the complexity of `get()`?

## Intermediate

- [ ] Difference between size and capacity?
- [ ] How does ArrayList resize?
- [ ] Why is append amortized O(1)?
- [ ] Why is insertion at index 0 O(n)?
- [ ] Why is removal from the middle O(n)?
- [ ] What is `ensureCapacity()`?
- [ ] What is `trimToSize()`?
- [ ] What does `RandomAccess` mean?
- [ ] Difference between `remove(int)` and `remove(Object)`?

## Advanced

- [ ] Explain ArrayList internals.
- [ ] Explain backing-array growth.
- [ ] Explain amortized analysis.
- [ ] Explain memory overhead.
- [ ] Explain why references are cleared after removal.
- [ ] Explain iterator fail-fast behavior.
- [ ] Explain `subList()` and backing relationships.
- [ ] Explain ArrayList vs LinkedList from a hardware/cache perspective.

## Senior / Production

- [ ] How would you choose initial capacity?
- [ ] When can pre-sizing improve production performance?
- [ ] When can `trimToSize()` hurt?
- [ ] How would you diagnose excessive ArrayList memory retention?
- [ ] How would you process millions of objects efficiently?
- [ ] When would you replace ArrayList?
- [ ] How would you make List access safe across threads?
- [ ] How would you benchmark ArrayList behavior correctly?
- [ ] How would you estimate the memory cost of a large ArrayList?
- [ ] How would you design a custom dynamic array?

---

# 3.2.49 Advanced OpenJDK Follow-up

Inspect the current OpenJDK implementation of:

```text
java.util.ArrayList
```

Focus on:

- [ ] Backing-array field.
- [ ] Default construction.
- [ ] Explicit initial capacity.
- [ ] `add()`.
- [ ] `grow()`.
- [ ] `get()`.
- [ ] `set()`.
- [ ] `remove()`.
- [ ] `fastRemove()`.
- [ ] `clear()`.
- [ ] `ensureCapacity()`.
- [ ] `trimToSize()`.
- [ ] Iterator.
- [ ] Spliterator.
- [ ] Modification tracking.
- [ ] Bulk operations.

Then compare the implementation across Java versions if you want historical understanding.

---

# 3.2.50 Internal Mental Model

You should be able to draw:

```text
ArrayList
    |
    +-- size
    |
    +-- backing array reference
             |
             +-- [element reference]
             +-- [element reference]
             +-- [element reference]
             +-- [unused slot]
             +-- [unused slot]
```

For append:

```text
add()
 ↓
size < capacity?
 ├── yes → store reference
 └── no
       ↓
     grow
       ↓
allocate larger array
       ↓
copy references
       ↓
replace backing array
       ↓
store reference
```

---

# 3.2.51 Final Mastery Gate

## Fundamentals

- [ ] Explain ArrayList.
- [ ] Explain why it exists.
- [ ] Explain its List contract.
- [ ] Explain random access.
- [ ] Explain ordering and duplicates.

## Internals

- [ ] Explain backing array.
- [ ] Explain size.
- [ ] Explain capacity.
- [ ] Explain growth.
- [ ] Explain resizing.
- [ ] Explain reference copying.
- [ ] Explain amortized complexity.
- [ ] Explain nulling removed references.

## Complexity

- [ ] `get()` → O(1)
- [ ] `set()` → O(1)
- [ ] Append → amortized O(1)
- [ ] Resize → O(n)
- [ ] Beginning insertion → O(n)
- [ ] Middle insertion → O(n)
- [ ] End removal → O(1)
- [ ] Beginning removal → O(n)
- [ ] Search → O(n)

## Memory

- [ ] Explain object overhead.
- [ ] Explain backing-array overhead.
- [ ] Explain unused capacity.
- [ ] Explain references vs objects.
- [ ] Explain GC implications.
- [ ] Explain `clear()` vs capacity.
- [ ] Explain `trimToSize()` trade-offs.

## Production

- [ ] Choose appropriate initial capacity.
- [ ] Recognize when ArrayList is appropriate.
- [ ] Recognize when another collection is better.
- [ ] Diagnose memory retention.
- [ ] Diagnose excessive resizing.
- [ ] Benchmark correctly.
- [ ] Discuss thread-safety requirements.

## Implementation

- [ ] Implement a dynamic array.
- [ ] Implement growth.
- [ ] Implement bounds checking.
- [ ] Implement insertion/removal.
- [ ] Implement an iterator.
- [ ] Implement fail-fast detection.
- [ ] Handle large capacities safely.

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
