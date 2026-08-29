# Module 3.3 — LinkedList
## Deep Mastery Guide

> **Goal:** Master Java `LinkedList` from its `List`/`Deque` contracts to doubly linked node internals, traversal, insertion/deletion, random-access costs, memory behavior, cache locality, practical performance, and production trade-offs.

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

# 3.3.1 What Is `LinkedList`?

`LinkedList<E>` is a doubly linked list implementation of both:

```java
List<E>
Deque<E>
```

It can therefore be used as a:

- List
- Queue
- Deque
- Stack-like structure

Example:

```java
List<String> names = new LinkedList<>();
```

or:

```java
Deque<String> deque = new LinkedList<>();
```

For normal stack/queue use cases, compare it with `ArrayDeque`.

---

# 3.3.2 Why Does Java Have `LinkedList`?

A linked list provides a different storage model from an array-backed list.

```text
ArrayList

[A][B][C][D]
```

versus:

```text
LinkedList

[A] ↔ [B] ↔ [C] ↔ [D]
```

Each node maintains links to neighboring nodes.

This makes local insertion/removal efficient **when the relevant node or iterator position is already known**, while indexed access requires traversal.

---

# 3.3.3 Core Characteristics

| Property | LinkedList |
|---|---|
| Backing structure | Doubly linked nodes |
| Implements | `List`, `Deque` |
| Random access | Slow |
| `get(index)` | O(n) |
| Add at ends | O(1) |
| Remove at ends | O(1) |
| Search | O(n) |
| Middle insertion | O(n) to locate position |
| Middle deletion by index | O(n) to locate node |
| Per-element node overhead | High |
| Cache locality | Generally poor |
| Thread-safe | No |
| Duplicates | Allowed |
| Null elements | Allowed |

The complexity of an operation must be interpreted together with how its target position is obtained.

---

# 3.3.4 Node Structure

The fundamental structure is a doubly linked node:

```text
Node<E>

+---------+---------+---------+
|  prev   | element |  next   |
+---------+---------+---------+
```

A list looks conceptually like:

```text
null
  ↑
 [A] ↔ [B] ↔ [C] ↔ [D]
                              ↓
                             null
```

Each node knows:

- previous node
- current element
- next node

---

# 3.3.5 Why Doubly Linked?

A singly linked list has:

```text
next
```

A doubly linked list has:

```text
prev
next
```

The additional `prev` reference allows efficient traversal and unlinking from both directions.

Trade-off:

```text
more flexibility
      +
more memory per node
```

---

# 3.3.6 Java's Internal Node Model

Conceptually, the OpenJDK implementation uses a private node structure similar to:

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
}
```

The exact source should be checked against the Java/OpenJDK version being studied.

The list maintains references to:

```text
first
last
size
```

Mental model:

```text
LinkedList
   |
   +-- size
   +-- first
   +-- last
          |
          ↓
       Node ↔ Node ↔ Node
```

---

# 3.3.7 First and Last Nodes

Conceptually:

```text
first
  ↓
[A] ↔ [B] ↔ [C] ↔ [D]
                          ↑
                         last
```

The first node has:

```text
prev = null
```

The last node has:

```text
next = null
```

This enables efficient operations at both ends.

---

# 3.3.8 Adding at the End

```java
list.add("D");
```

Conceptually:

```text
Before:

[A] ↔ [B] ↔ [C]

After:

[A] ↔ [B] ↔ [C] ↔ [D]
```

Because the list maintains `last`, adding at the end is typically:

```text
O(1)
```

---

# 3.3.9 Adding at the Beginning

```java
list.addFirst("A");
```

Conceptually:

```text
Before:

[B] ↔ [C] ↔ [D]

After:

[A] ↔ [B] ↔ [C] ↔ [D]
```

Only a small number of links must be changed.

Typical complexity:

```text
O(1)
```

---

# 3.3.10 Removing the Last Node

```java
list.removeLast();
```

Conceptually:

```text
Before:

[A] ↔ [B] ↔ [C] ↔ [D]

After:

[A] ↔ [B] ↔ [C]
```

Typical complexity:

```text
O(1)
```

---

# 3.3.11 Removing the First Node

```java
list.removeFirst();
```

Conceptually:

```text
Before:

[A] ↔ [B] ↔ [C]

After:

[B] ↔ [C]
```

Typical complexity:

```text
O(1)
```

---

# 3.3.12 Local Insertion

Suppose the node for `B` is already known.

```text
[A] ↔ [B] ↔ [C]
```

Insert `X` after `B`:

```text
[A] ↔ [B] ↔ [X] ↔ [C]
```

Only neighboring links need adjustment.

The local link manipulation is:

```text
O(1)
```

---

# 3.3.13 The Critical Qualification

A common misconception is:

> "LinkedList insertion is O(1)."

That is incomplete.

For:

```java
list.add(index, value);
```

the implementation must first locate the position.

Conceptually:

```text
locate position → O(n)
insert locally  → O(1)

overall         → O(n)
```

If a node/iterator position is already available:

```text
insert/unlink → O(1)
```

This distinction is essential for interviews and production performance reasoning.

---

# 3.3.14 Indexed Access

```java
list.get(500_000);
```

A linked list cannot directly jump to index 500,000.

It must traverse nodes:

```text
first
 ↓
node 0
 ↓
node 1
 ↓
node 2
 ↓
...
 ↓
node 500000
```

Therefore:

```text
get(index) → O(n)
```

---

# 3.3.15 Bidirectional Traversal Optimization

Java's LinkedList can select the closer end when locating an index.

Conceptually:

```text
index near beginning
      ↓
traverse from first

index near end
      ↓
traverse from last
```

The number of traversed links is approximately bounded by:

```text
min(index, size - index - 1)
```

The asymptotic complexity remains:

```text
O(n)
```

but this reduces unnecessary traversal.

---

# 3.3.16 Random Access Cost

Compare:

```java
arrayList.get(index);
```

with:

```java
linkedList.get(index);
```

### ArrayList

```text
index
 ↓
array slot
 ↓
element

O(1)
```

### LinkedList

```text
index
 ↓
traverse nodes
 ↓
target node
 ↓
element

O(n)
```

This is the fundamental reason ArrayList is generally preferred for indexed List access.

---

# 3.3.17 Why LinkedList Can Be Slower in Practice

The theoretical complexity story is not enough.

LinkedList has poor memory locality.

Conceptually:

```text
Node A → memory location 1000
Node B → memory location 800000
Node C → memory location 25000
Node D → memory location 900000
```

Nodes can be distributed throughout the heap.

An ArrayList is backed by an array:

```text
[A][B][C][D][E][F]
```

which generally offers much better spatial locality.

---

# 3.3.18 CPU Cache Locality

Modern CPUs use multiple cache levels:

```text
CPU
 ↓
L1
 ↓
L2
 ↓
L3
 ↓
RAM
```

ArrayList's contiguous backing storage can provide good spatial locality:

```text
[element][element][element][element]
```

LinkedList traversal involves following references:

```text
[node] → another node → another node
```

This pointer chasing can result in more cache misses and less effective hardware prefetching.

Therefore:

> Two O(n) algorithms can have dramatically different real-world performance.

---

# 3.3.19 Pointer Chasing

Linked-list traversal is commonly described as:

```text
pointer chasing
```

The CPU performs approximately:

```text
load node
 ↓
read next pointer
 ↓
load next node
 ↓
read next pointer
 ↓
repeat
```

The address of the next node depends on the current node.

This is less cache-friendly than predictable sequential array access.

---

# 3.3.20 Memory Overhead

For each element, LinkedList requires a node containing:

```text
element reference
+
prev reference
+
next reference
```

plus object-header/alignment overhead.

Conceptually:

```text
ArrayList:

[ref][ref][ref][ref]


LinkedList:

[node]
  ├── prev
  ├── element
  └── next

[node]
  ├── prev
  ├── element
  └── next
```

Thus LinkedList can consume significantly more memory per element.

---

# 3.3.21 GC Implications

Each element requires a separate node object.

A large LinkedList can therefore create:

```text
many node objects
+
many references
+
more allocation activity
+
more GC bookkeeping
```

The actual impact depends on:

- JVM
- heap configuration
- object lifetime
- workload
- garbage collector
- element types

Do not assume a fixed GC penalty without measurement.

---

# 3.3.22 LinkedList and Object References

For:

```java
LinkedList<Employee> employees;
```

the node stores a reference:

```text
Node
  |
  +-- Employee reference
             |
             ↓
       Employee object
```

The Employee object is separate from the Node.

This distinction matters for memory estimation.

---

# 3.3.23 LinkedList as a Deque

One important feature is its `Deque` behavior.

```java
Deque<String> deque = new LinkedList<>();

deque.addFirst("A");
deque.addLast("B");

String first = deque.removeFirst();
String last = deque.removeLast();
```

This provides efficient operations at both ends.

However, for a general-purpose deque, compare it with `ArrayDeque`, which usually has lower per-element overhead and better locality.

---

# 3.3.24 Queue Operations

As a Queue:

```java
Queue<String> queue = new LinkedList<>();

queue.offer("A");
queue.offer("B");

String value = queue.poll();
```

If the requirement is simply queue/deque behavior, evaluate:

```text
LinkedList
vs
ArrayDeque
```

Do not choose LinkedList automatically.

---

# 3.3.25 Stack-Like Operations

A LinkedList can support Deque stack operations:

```java
Deque<Integer> stack = new LinkedList<>();

stack.push(10);
stack.push(20);

System.out.println(stack.pop());
```

For normal stack requirements, prefer the `Deque` abstraction and usually evaluate `ArrayDeque` first.

---

# 3.3.26 `RandomAccess`

Unlike ArrayList, LinkedList does not implement:

```java
RandomAccess
```

This communicates that indexed access is not expected to be fast.

Generic algorithms can use this information when choosing traversal strategies.

---

# 3.3.27 Search Complexity

Operations such as:

```java
contains()
indexOf()
lastIndexOf()
```

require traversal.

Typical complexity:

```text
O(n)
```

The actual work depends on where the matching element is found.

---

# 3.3.28 Iteration

Sequential iteration follows node links:

```text
first
 ↓
next
 ↓
next
 ↓
next
```

This is why:

```java
for (String value : linkedList) {
    // ...
}
```

is preferable to repeated indexed lookup.

Do not write:

```java
for (int i = 0; i < linkedList.size(); i++) {
    linkedList.get(i);
}
```

for large lists unless you have a specific reason.

---

# 3.3.29 The Classic O(n²) Trap

Consider:

```java
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}
```

For a LinkedList, each `get(i)` can require traversal.

Across all iterations, the total work can become:

```text
O(n²)
```

Instead:

```java
for (String value : list) {
    System.out.println(value);
}
```

performs sequential traversal:

```text
O(n)
```

---

# 3.3.30 `ListIterator`

LinkedList supports `ListIterator`:

```java
ListIterator<String> iterator =
        list.listIterator();
```

It supports:

```java
hasNext()
next()

hasPrevious()
previous()
```

and:

```java
add()
remove()
set()
```

Once the iterator is positioned appropriately, local list modifications can be efficient.

---

# 3.3.31 `ListIterator` vs Index Operations

Compare:

```java
list.add(index, value);
```

with an iterator positioned at the desired location:

```java
ListIterator<String> iterator =
        list.listIterator(index);

iterator.add(value);
```

The initial positioning can require traversal.

After the iterator has a relevant position, repeated local operations can avoid repeated indexed searches.

This distinction matters in workloads involving sequential traversal plus modification.

---

# 3.3.32 Structural Modification

Structural operations include:

```text
add
remove
clear
```

Iterators track structural modifications on a best-effort basis and may throw:

```text
ConcurrentModificationException
```

for unexpected modifications.

Fail-fast behavior is not synchronization.

---

# 3.3.33 Null and Duplicate Elements

LinkedList permits:

```java
null
```

and duplicates.

Example:

```java
list.add(null);
list.add("A");
list.add("A");
```

This follows normal List semantics.

---

# 3.3.34 `remove(int)` vs `remove(Object)`

For:

```java
LinkedList<Integer> values =
        new LinkedList<>(List.of(10, 20, 30));
```

this:

```java
values.remove(1);
```

means:

```text
remove element at index 1
```

while:

```java
values.remove(Integer.valueOf(1));
```

means:

```text
remove the value 1
```

This is a classic overload trap.

---

# 3.3.35 Common Mistakes

- [ ] Assuming LinkedList insertion is always O(1).
- [ ] Ignoring the cost of locating an index.
- [ ] Using LinkedList for frequent random access.
- [ ] Assuming LinkedList is faster than ArrayList because insertion is O(1).
- [ ] Ignoring node memory overhead.
- [ ] Ignoring CPU cache locality.
- [ ] Using repeated `get(i)` in a loop.
- [ ] Using LinkedList as the default queue without comparing ArrayDeque.
- [ ] Assuming fail-fast means thread-safe.
- [ ] Modifying the list incorrectly during iteration.
- [ ] Confusing `remove(int)` with `remove(Object)`.
- [ ] Treating Big-O as the only performance metric.
- [ ] Ignoring allocation and GC overhead.

---

# 3.3.36 Edge Cases

Investigate:

- [ ] Empty list.
- [ ] Single-node list.
- [ ] Two-node list.
- [ ] Null elements.
- [ ] Duplicate elements.
- [ ] Insert at index 0.
- [ ] Insert at index size.
- [ ] Remove first.
- [ ] Remove last.
- [ ] Invalid index.
- [ ] Iterator modification.
- [ ] ListIterator behavior.
- [ ] Very large lists.
- [ ] Memory pressure from many nodes.
- [ ] Concurrent access.

---

# 3.3.37 Debugging Challenge — Random Access

Create a LinkedList containing one million values.

Compare:

```java
for (int i = 0; i < list.size(); i++) {
    list.get(i);
}
```

against:

```java
for (Integer value : list) {
}
```

Tasks:

- [ ] Predict complexity.
- [ ] Benchmark both.
- [ ] Explain pointer chasing.
- [ ] Explain cache locality.
- [ ] Explain why the indexed loop can become O(n²).

---

# 3.3.38 Debugging Challenge — Insertion

Compare:

```java
list.add(0, value);
```

with:

```java
list.add(value);
```

Tasks:

- [ ] Explain why end operations can be efficient.
- [ ] Compare with ArrayList.
- [ ] Explain the theoretical linked-list advantage.
- [ ] Explain why that advantage does not automatically imply better real-world performance.

---

# 3.3.39 Debugging Challenge — Queue Choice

Implement the same workload using:

```text
LinkedList
ArrayDeque
```

Measure:

```text
offer
poll
iteration
memory
allocation
```

Explain why an array-backed deque can outperform a linked structure even when both provide efficient end operations.

---

# 3.3.40 Coding Exercises

## Basic

- [ ] Create a LinkedList.
- [ ] Add elements.
- [ ] Add at beginning.
- [ ] Add at end.
- [ ] Remove first.
- [ ] Remove last.
- [ ] Retrieve an element.
- [ ] Search for an element.
- [ ] Iterate using enhanced `for`.
- [ ] Iterate using Iterator.
- [ ] Iterate using ListIterator.

## Intermediate

- [ ] Implement a singly linked list.
- [ ] Implement a doubly linked list.
- [ ] Add at head.
- [ ] Add at tail.
- [ ] Insert at index.
- [ ] Remove at index.
- [ ] Find a node.
- [ ] Reverse a linked list.
- [ ] Detect a cycle.
- [ ] Find the middle node.
- [ ] Merge two sorted linked lists.

## Advanced

Implement:

```text
MyLinkedList<E>
```

with:

```java
int size();
boolean isEmpty();

void addFirst(E value);
void addLast(E value);

E get(int index);

void add(int index, E value);
E remove(int index);

E removeFirst();
E removeLast();

boolean contains(Object value);

Iterator<E> iterator();
ListIterator<E> listIterator();
```

Requirements:

- [ ] Maintain `first`.
- [ ] Maintain `last`.
- [ ] Maintain `size`.
- [ ] Maintain `prev` and `next`.
- [ ] Optimize traversal direction.
- [ ] Correctly update endpoints.
- [ ] Handle empty list.
- [ ] Handle single-node list.
- [ ] Handle insertion/removal at boundaries.
- [ ] Handle invalid indexes.
- [ ] Implement iterator behavior.

---

# 3.3.41 Production Performance Lab

Benchmark realistic workloads.

## Workload A — Sequential Iteration

```text
1 million elements
iterate all
```

Compare:

```text
ArrayList
LinkedList
```

## Workload B — Random Access

```text
1 million random get(index)
```

Compare:

```text
ArrayList
LinkedList
```

## Workload C — End Operations

```text
addFirst/removeFirst
addLast/removeLast
```

Compare:

```text
LinkedList
ArrayDeque
ArrayList
```

## Workload D — Memory

Compare approximate memory consumption for large collections.

Measure:

- [ ] Heap usage.
- [ ] Allocation rate.
- [ ] GC activity.
- [ ] Throughput.
- [ ] Latency.
- [ ] CPU usage.

Use JMH for serious microbenchmarking.

---

# 3.3.42 Performance Summary

| Operation | LinkedList |
|---|---:|
| `get(index)` | O(n) |
| `set(index)` | O(n) |
| `addFirst()` | O(1) |
| `addLast()` | O(1) |
| `removeFirst()` | O(1) |
| `removeLast()` | O(1) |
| Search | O(n) |
| Insert at known node | O(1) |
| Delete at known node | O(1) |
| Insert by index | O(n) overall |
| Delete by index | O(n) overall |
| Sequential iterator traversal | O(n) |

Remember:

> Big-O describes growth; it does not capture cache locality, allocation cost, object headers, GC effects, CPU behavior, or constant factors.

---

# 3.3.43 LinkedList vs ArrayList

| Factor | ArrayList | LinkedList |
|---|---|---|
| Random access | Excellent | Poor |
| Sequential iteration | Usually excellent | Often slower |
| Memory overhead | Lower | Higher |
| Cache locality | Good | Poorer |
| Append | Amortized O(1) | O(1) |
| Add first | O(n) | O(1) |
| Remove first | O(n) | O(1) |
| Middle insertion | O(n) | O(n) to locate |
| Middle removal by index | O(n) | O(n) to locate |
| Node allocation | No per-element node | Yes |
| Typical general-purpose List choice | Yes | Specialized |

---

# 3.3.44 Why ArrayList Is Often the Better Default

For general List usage, ArrayList is frequently preferred because it provides:

```text
O(1) random access
+
excellent iteration locality
+
lower per-element memory overhead
+
fewer objects
+
good CPU-cache behavior
```

LinkedList should be selected when its specific structural or Deque characteristics match the workload.

---

# 3.3.45 When LinkedList Can Be Appropriate

Potential scenarios:

- [ ] Frequent operations at both ends.
- [ ] Existing node/iterator positions are maintained.
- [ ] ListIterator-based local insertion/removal is central.
- [ ] Deque semantics are required and LinkedList is acceptable.

Even then, compare `ArrayDeque` where applicable.

---

# 3.3.46 When Not to Use LinkedList

Avoid choosing it merely because:

```text
"Linked lists have O(1) insertion."
```

That ignores traversal cost.

For:

```text
random access
large sequential scans
memory-sensitive workloads
cache-sensitive workloads
general-purpose Lists
```

ArrayList is often stronger.

For:

```text
queue/deque
```

also evaluate:

```text
ArrayDeque
```

---

# 3.3.47 Production Design Questions

Before choosing LinkedList:

```text
1. Do I need indexed access?
2. Do I need frequent operations at the beginning?
3. Do I need frequent operations at the end?
4. Do I already hold node/iterator positions?
5. Is sequential iteration dominant?
6. Is memory overhead important?
7. Is cache locality important?
8. Would ArrayDeque satisfy the same requirement?
9. Would ArrayList be simpler and faster?
10. Is concurrency required?
11. What is the expected collection size?
12. What is the actual workload?
```

---

# 3.3.48 Interview Questions

## Basic

- [ ] What is LinkedList?
- [ ] Is Java LinkedList singly or doubly linked?
- [ ] What interfaces does LinkedList implement?
- [ ] What is a node?
- [ ] What is the complexity of `get(index)`?
- [ ] Does LinkedList allow duplicates?
- [ ] Does LinkedList allow null?
- [ ] Is LinkedList thread-safe?

## Intermediate

- [ ] Explain the internal node structure.
- [ ] Why is insertion at the end O(1)?
- [ ] Why is insertion at an arbitrary index O(n)?
- [ ] Why is random access O(n)?
- [ ] Why does LinkedList traverse from the closer end?
- [ ] What is `RandomAccess`?
- [ ] Why can LinkedList be used as a Deque?
- [ ] Difference between `removeFirst()` and `remove(index)`?

## Advanced

- [ ] Why is LinkedList often slower than ArrayList?
- [ ] Explain cache locality.
- [ ] Explain pointer chasing.
- [ ] Explain memory overhead.
- [ ] Explain GC implications.
- [ ] Why doesn't O(1) insertion automatically make LinkedList faster?
- [ ] Explain the O(n²) indexed-loop trap.
- [ ] How does ListIterator change insertion/removal analysis?

## Senior / Production

- [ ] When would you actually choose LinkedList?
- [ ] Why might ArrayDeque be better than LinkedList for a deque?
- [ ] How would you benchmark LinkedList vs ArrayList?
- [ ] How would you estimate LinkedList memory usage?
- [ ] How can CPU cache behavior change expected performance?
- [ ] How would you diagnose a LinkedList-related performance problem?
- [ ] What workload makes LinkedList theoretically attractive but practically disappointing?
- [ ] How would you design a node-based structure for a production workload?

---

# 3.3.49 Advanced OpenJDK Follow-up

Inspect the current OpenJDK implementation of:

```text
java.util.LinkedList
```

Study:

- [ ] `Node<E>`
- [ ] `first`
- [ ] `last`
- [ ] `size`
- [ ] `linkFirst()`
- [ ] `linkLast()`
- [ ] `linkBefore()`
- [ ] `unlink()`
- [ ] `unlinkFirst()`
- [ ] `unlinkLast()`
- [ ] `node(index)`
- [ ] `get()`
- [ ] `add()`
- [ ] `remove()`
- [ ] `ListIterator`
- [ ] Iterator modification tracking
- [ ] Spliterator implementation

Pay special attention to:

```text
node(index)
```

and how it selects traversal from the beginning or end.

---

# 3.3.50 Internal Mental Model

You should be able to draw:

```text
LinkedList
    |
    +-- size
    |
    +-- first
    |     |
    |     ↓
    |   Node
    |     |
    |     +-- prev → null
    |     +-- item → element
    |     +-- next ─────────┐
    |                       ↓
    |                     Node
    |                       |
    |                       +-- prev
    |                       +-- item
    |                       +-- next
    |
    +-- last
```

Traversal:

```text
index near start
       ↓
first → next → next → target

index near end
       ↓
last → prev → prev → target
```

---

# 3.3.51 Deep Performance Mental Model

Do not stop at:

```text
LinkedList insertion = O(1)
```

Use:

```text
Algorithmic complexity
        +
Memory allocation
        +
Object overhead
        +
Cache locality
        +
Pointer chasing
        +
Branch behavior
        +
GC pressure
        +
CPU architecture
        =
Real performance
```

This is the level of reasoning expected in senior Java performance discussions.

---

# 3.3.52 Final Mastery Gate

## Fundamentals

- [ ] Explain LinkedList.
- [ ] Explain why it exists.
- [ ] Explain List semantics.
- [ ] Explain Deque semantics.
- [ ] Explain node-based storage.

## Internals

- [ ] Explain doubly linked nodes.
- [ ] Explain `prev`.
- [ ] Explain `next`.
- [ ] Explain `first`.
- [ ] Explain `last`.
- [ ] Explain `size`.
- [ ] Explain linking.
- [ ] Explain unlinking.
- [ ] Explain traversal direction.

## Complexity

- [ ] `get(index)` → O(n)
- [ ] `set(index)` → O(n)
- [ ] End insertion → O(1)
- [ ] End removal → O(1)
- [ ] Beginning insertion → O(1)
- [ ] Beginning removal → O(1)
- [ ] Search → O(n)
- [ ] Known-node insertion → O(1)
- [ ] Known-node removal → O(1)
- [ ] Indexed insertion/removal → O(n) overall

## Memory

- [ ] Explain node overhead.
- [ ] Explain references.
- [ ] Explain object headers conceptually.
- [ ] Explain cache locality.
- [ ] Explain pointer chasing.
- [ ] Explain allocation pressure.
- [ ] Explain GC implications.

## Performance

- [ ] Explain why ArrayList is often faster.
- [ ] Explain why Big-O alone is insufficient.
- [ ] Identify O(n²) indexed traversal.
- [ ] Compare LinkedList with ArrayDeque.
- [ ] Benchmark with an appropriate methodology.

## Production

- [ ] Choose LinkedList only for a justified workload.
- [ ] Explain when not to use it.
- [ ] Diagnose performance problems.
- [ ] Discuss memory trade-offs.
- [ ] Discuss concurrency limitations.
- [ ] Select the correct abstraction (`List`/`Deque`/`Queue`).

## Implementation

- [ ] Implement a doubly linked list.
- [ ] Implement node linking.
- [ ] Implement node unlinking.
- [ ] Maintain first/last correctly.
- [ ] Handle empty and single-node cases.
- [ ] Implement bidirectional traversal.
- [ ] Implement Iterator.
- [ ] Implement ListIterator.

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
