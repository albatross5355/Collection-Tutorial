# Module 3.4 — Vector & Stack
## Deep Mastery Guide

> **Goal:** Master Java `Vector` and `Stack`, understand their historical design, synchronization model, legacy APIs, internal array-backed behavior, performance costs, inheritance relationship, and why modern Java code should generally prefer `List`/`ArrayList` and `Deque`/`ArrayDeque`.

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

# 3.4.1 What Is `Vector`?

`Vector<E>` is a legacy, array-backed implementation of the `List<E>` interface.

Conceptually:

```text
Vector
  |
  +-- Object[] elementData
  +-- int elementCount
  +-- int capacityIncrement
```

It predates the modern Collections Framework.

Unlike `ArrayList`, many of its methods are synchronized.

Example:

```java
Vector<String> names = new Vector<>();

names.add("Alice");
names.add("Bob");

System.out.println(names.get(0));
```

---

# 3.4.2 Why Does Java Have `Vector`?

`Vector` was introduced before the Java Collections Framework.

Historically, Java needed a growable array-like collection.

Its design provided:

```text
dynamic array
+
synchronized methods
```

Later, the Collections Framework standardized collection interfaces and introduced `ArrayList`.

Therefore:

```text
Vector
   ↓
legacy dynamic array

ArrayList
   ↓
modern general-purpose dynamic array
```

---

# 3.4.3 Vector's Relationship to the Collections Framework

Modern hierarchy:

```text
Iterable
   ↓
Collection
   ↓
List
   ↓
Vector
```

`Vector` also implements `RandomAccess`, `Cloneable`, and `Serializable`.

Important:

> `Vector` is not outside the Collections Framework. It is a legacy class that was retrofitted to implement the modern collection interfaces.

---

# 3.4.4 Vector vs ArrayList

| Property | Vector | ArrayList |
|---|---|---|
| Backing structure | Dynamic array | Dynamic array |
| Implements List | Yes | Yes |
| Random access | O(1) | O(1) |
| Thread-safe methods | Synchronized legacy design | No |
| Synchronization overhead | Yes | No |
| Typical modern choice | No | Yes |
| Legacy API | Yes | No |
| Capacity growth | Configurable increment or growth strategy | Implementation-dependent growth |
| Null allowed | Yes | Yes |
| Duplicates | Yes | Yes |

The key distinction is synchronization and legacy design.

---

# 3.4.5 Vector Internal Array

Vector stores elements in an array-like structure.

Conceptually:

```text
elementData

+----+----+----+----+----+----+
| A  | B  | C  | D  |    |    |
+----+----+----+----+----+----+
  0    1    2    3
```

Logical size:

```text
4
```

Capacity:

```text
6
```

As with ArrayList:

```text
size != capacity
```

---

# 3.4.6 Size vs Capacity

Example:

```java
Vector<Integer> v = new Vector<>(20);

v.add(10);
v.add(20);
```

Conceptually:

```text
size     = 2
capacity = 20
```

Capacity represents allocated storage.

Size represents the number of actual elements.

---

# 3.4.7 Vector Growth

When the backing array becomes full, Vector must expand its storage.

Conceptually:

```text
Before:

[A][B][C][D]


capacity exhausted

        ↓

allocate larger array

        ↓

[A][B][C][D][ ][ ][ ][ ]
```

Existing references are copied into the new array.

Therefore resizing involves:

```text
new allocation
+
copying references
```

---

# 3.4.8 Capacity Increment

Vector exposes a legacy concept called:

```java
capacityIncrement
```

Example:

```java
Vector<Integer> v =
        new Vector<>(10, 5);
```

This means that when growth is required, the configured increment can influence capacity expansion.

Modern code should generally not rely on Vector-specific growth behavior.

Always verify the implementation for the exact JDK version being studied.

---

# 3.4.9 Vector Constructors

Common constructors include:

```java
Vector()
Vector(int initialCapacity)
Vector(int initialCapacity, int capacityIncrement)
Vector(Collection<? extends E> c)
```

Example:

```java
Vector<String> v =
        new Vector<>(10);
```

---

# 3.4.10 Synchronization

Many Vector methods are synchronized.

Conceptually:

```java
public synchronized boolean add(E e) {
    ...
}
```

The exact implementation should be checked against the target OpenJDK version.

This means operations may involve monitor synchronization.

---

# 3.4.11 Synchronization Overhead

Consider:

```java
vector.add(value);
```

The operation may involve synchronization even when:

```text
only one thread
```

is accessing the Vector.

That can introduce unnecessary overhead compared with an unsynchronized `ArrayList` when thread safety is not required.

The actual cost depends on:

- JVM
- JIT optimization
- contention
- operation frequency
- workload

Do not assume synchronization always causes a large penalty; measure when performance matters.

---

# 3.4.12 Thread Safety Does Not Mean Atomic Workflows

This is extremely important.

Suppose:

```java
if (!vector.contains(value)) {
    vector.add(value);
}
```

Even if individual methods are synchronized, the complete operation:

```text
contains
+
add
```

is not automatically atomic.

Another thread could modify the collection between the two operations.

For compound operations, external synchronization or a suitable concurrent collection may be required.

---

# 3.4.13 External Synchronization

A caller may synchronize a sequence:

```java
synchronized (vector) {
    if (!vector.contains(value)) {
        vector.add(value);
    }
}
```

This illustrates an important principle:

> Thread-safe individual methods do not automatically make arbitrary multi-step workflows thread-safe.

---

# 3.4.14 Enumeration

Vector historically provides:

```java
Enumeration<E>
```

Example:

```java
Enumeration<String> e =
        vector.elements();

while (e.hasMoreElements()) {
    System.out.println(e.nextElement());
}
```

`Enumeration` predates:

```text
Iterator
```

and the modern Collections Framework.

For modern APIs, `Iterator` is generally preferred.

---

# 3.4.15 Vector-Specific Legacy Methods

Vector exposes methods such as:

```java
addElement()
elementAt()
copyInto()
firstElement()
lastElement()
insertElementAt()
removeElement()
removeElementAt()
removeAllElements()
elements()
```

Many have modern equivalents:

```text
add()
get()
remove()
clear()
iterator()
```

Example:

```java
vector.addElement("Java");
```

versus:

```java
vector.add("Java");
```

Prefer the modern collection API when writing new code.

---

# 3.4.16 Why Legacy APIs Matter

You should understand these APIs because they may appear in:

```text
legacy enterprise applications
old libraries
maintenance work
interview questions
migration projects
```

Knowing legacy Java is different from choosing it for new development.

---

# 3.4.17 Vector Random Access

Vector is array-backed.

Therefore:

```java
vector.get(index);
```

provides:

```text
O(1)
```

random access, assuming normal array indexing behavior.

This is similar to ArrayList.

---

# 3.4.18 Vector Insertion and Deletion

Appending at the end is typically:

```text
O(1) amortized
```

because growth is occasional.

Insertion in the middle requires shifting references:

```text
Before:

[A][B][C][D]

insert X at index 1

After:

[A][X][B][C][D]
```

References after the insertion point must move.

Typical complexity:

```text
O(n)
```

Removal from the middle similarly requires shifting.

---

# 3.4.19 Vector Memory Behavior

Vector uses a backing array.

Therefore its memory model resembles ArrayList:

```text
Vector object
    |
    ↓
Object[] array
    |
    +-- reference
    +-- reference
    +-- reference
```

It does not allocate a separate node object per element.

This generally gives better locality than a linked-node structure.

---

# 3.4.20 Vector and Garbage Collection

The backing array contains references to elements.

When elements are removed, the implementation must ensure obsolete references are no longer retained unnecessarily.

This matters because:

```text
retained reference
      ↓
reachable object
      ↓
cannot be collected
```

Study how removal operations clear unused array slots.

---

# 3.4.21 Vector `ensureCapacity`

Vector provides:

```java
ensureCapacity(int minCapacity)
```

This allows callers to request enough backing storage ahead of time.

Example:

```java
vector.ensureCapacity(10_000);
```

Potential benefit:

```text
fewer future reallocations
+
fewer reference copies
```

But premature capacity allocation can waste memory.

---

# 3.4.22 Vector `trimToSize`

Vector also provides:

```java
trimToSize()
```

Conceptually:

```text
capacity
   ↓
reduce toward current size
```

This can reduce unused backing-array capacity.

Trade-off:

```text
less retained memory
vs.
possible future resizing cost
```

Do not call it indiscriminately in hot paths.

---

# 3.4.23 Vector Synchronization and Iteration

Even though Vector methods are synchronized, iteration requires careful reasoning.

Example:

```java
for (String s : vector) {
    ...
}
```

The iterator itself does not turn an entire traversal into one atomic synchronized operation.

If concurrent mutation matters, the iteration protocol and external synchronization strategy must be considered.

---

# 3.4.24 Stack

`Stack<E>` is a legacy LIFO collection.

It extends:

```java
Vector<E>
```

Hierarchy:

```text
Object
  ↓
Vector
  ↓
Stack
```

Therefore Stack inherits Vector's synchronization and array-backed behavior.

Example:

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);

System.out.println(stack.pop());
```

Output:

```text
20
```

---

# 3.4.25 LIFO Semantics

Stack follows:

```text
Last In
First Out
```

Example:

```text
push A
push B
push C

top
 ↓
 C
 B
 A
```

Then:

```java
pop()
```

returns:

```text
C
```

---

# 3.4.26 Stack API

Important methods:

```java
push(E item)
pop()
peek()
empty()
search(Object o)
```

Example:

```java
Stack<String> stack =
        new Stack<>();

stack.push("A");
stack.push("B");

String top = stack.peek();
String value = stack.pop();
```

---

# 3.4.27 Stack Empty Behavior

Calling:

```java
stack.pop();
```

on an empty Stack throws:

```text
EmptyStackException
```

By contrast, modern Deque APIs provide both exception-based and non-throwing forms.

For example:

```java
deque.pop();
```

versus:

```java
deque.poll();
```

This distinction should be understood.

---

# 3.4.28 The Major Design Problem with Stack

Stack inherits from Vector.

That means it exposes many operations that do not represent pure stack semantics.

For example:

```java
stack.add(0, value);
stack.remove(0);
stack.get(3);
stack.insertElementAt(value, 0);
```

A true stack abstraction should ideally expose only operations relevant to:

```text
push
pop
peek
```

Inheritance therefore weakens the abstraction.

---

# 3.4.29 Why Deque Is Preferred

Modern Java generally recommends:

```java
Deque<E>
```

for stack behavior.

Typical implementation:

```java
Deque<Integer> stack =
        new ArrayDeque<>();
```

Then:

```java
stack.push(10);
stack.push(20);

System.out.println(stack.pop());
```

This gives the desired LIFO abstraction without using the legacy Stack class.

---

# 3.4.30 Stack vs Deque

| Factor | Stack | ArrayDeque as Deque |
|---|---|---|
| API age | Legacy | Modern |
| LIFO support | Yes | Yes |
| Underlying structure | Vector/dynamic array | Resizable array |
| Synchronization | Legacy synchronized methods | Not thread-safe |
| Pure stack abstraction | Weaker | Better |
| Typical modern choice | No | Yes |
| Null elements | Allowed | Not allowed |
| Random-access List API | Exposed through inheritance | Not exposed |

---

# 3.4.31 Why `ArrayDeque` Is Usually Faster for Stack Work

ArrayDeque is designed specifically as a deque.

It avoids:

```text
Vector's legacy synchronization
+
Stack's inheritance baggage
```

It uses an array-based circular-buffer-style design.

This gives efficient operations at both ends with low per-element overhead and good locality.

---

# 3.4.32 Important Null Difference

Vector and Stack can contain:

```java
null
```

ArrayDeque does not permit null elements.

Therefore:

```java
stack.push(null);
```

with Stack is possible, while:

```java
deque.push(null);
```

with ArrayDeque throws `NullPointerException`.

This is an important migration edge case.

---

# 3.4.33 Stack `search`

Stack provides:

```java
search(Object o)
```

which returns a 1-based position measured from the top.

Example:

```text
top
 C ← 1
 B ← 2
 A ← 3
```

Then:

```java
stack.search("B");
```

returns:

```text
2
```

This is another example of Stack exposing operations beyond the minimal LIFO abstraction.

---

# 3.4.34 Stack Performance

Because Stack is Vector-based:

```text
push → typically O(1) amortized
pop  → O(1)
peek → O(1)
```

But synchronization and legacy design can make it less attractive than ArrayDeque for modern single-threaded or externally coordinated stack workloads.

---

# 3.4.35 Concurrency Consideration

If you need a thread-safe stack/deque, simply replacing Stack with ArrayDeque is not equivalent.

ArrayDeque is:

```text
not thread-safe
```

For concurrent producer/consumer workloads, evaluate appropriate concurrent abstractions such as:

```text
BlockingDeque
ConcurrentLinkedDeque
```

depending on requirements.

The correct choice depends on:

```text
blocking?
non-blocking?
multiple producers?
multiple consumers?
ordering?
backpressure?
```

---

# 3.4.36 Common Mistakes

- [ ] Choosing Vector for new code merely because it is synchronized.
- [ ] Assuming synchronized methods make compound workflows atomic.
- [ ] Choosing Stack for new LIFO code.
- [ ] Ignoring Stack's inheritance from Vector.
- [ ] Assuming Stack exposes only stack operations.
- [ ] Treating `Enumeration` as the modern iteration API.
- [ ] Assuming Vector synchronization is free.
- [ ] Assuming synchronization automatically solves all concurrency issues.
- [ ] Replacing Stack with ArrayDeque without checking null handling.
- [ ] Using ArrayDeque when blocking semantics are required.
- [ ] Confusing `size` with capacity.
- [ ] Ignoring resize costs.
- [ ] Calling `trimToSize()` indiscriminately.
- [ ] Assuming legacy APIs are deprecated simply because they are old.

---

# 3.4.37 Edge Cases

Investigate:

- [ ] Empty Vector.
- [ ] Empty Stack.
- [ ] `pop()` on empty Stack.
- [ ] `peek()` on empty Stack.
- [ ] Null elements.
- [ ] Duplicate elements.
- [ ] Vector capacity exhaustion.
- [ ] Explicit `ensureCapacity`.
- [ ] `trimToSize`.
- [ ] Concurrent mutation.
- [ ] Compound operations.
- [ ] Iteration while another thread modifies the collection.
- [ ] Migration from Stack to ArrayDeque.
- [ ] Migration code that relies on null values.
- [ ] Legacy code using Enumeration.

---

# 3.4.38 Debugging Challenge — Synchronization

Create:

```java
Vector<Integer> vector =
        new Vector<>();
```

Run a high-frequency workload.

Compare it with:

```java
ArrayList<Integer> list =
        new ArrayList<>();
```

Tasks:

- [ ] Measure throughput.
- [ ] Explain synchronization.
- [ ] Explain contention.
- [ ] Explain why single-threaded and highly contended workloads can behave differently.
- [ ] Inspect JIT-generated behavior only after establishing a reliable benchmark.

---

# 3.4.39 Debugging Challenge — Compound Operation

Consider:

```java
if (!vector.contains(value)) {
    vector.add(value);
}
```

Tasks:

- [ ] Explain why synchronized methods do not make the entire operation atomic.
- [ ] Create a race condition with multiple threads.
- [ ] Fix it using appropriate synchronization.
- [ ] Compare with a concurrent collection where appropriate.
- [ ] Explain the trade-offs.

---

# 3.4.40 Debugging Challenge — Stack Migration

Replace:

```java
Stack<Integer> stack =
        new Stack<>();
```

with:

```java
Deque<Integer> stack =
        new ArrayDeque<>();
```

Test:

```text
push
pop
peek
empty
null
search
iteration
```

Identify which behaviors differ.

---

# 3.4.41 Coding Exercises

## Basic

- [ ] Create a Vector.
- [ ] Add elements.
- [ ] Access by index.
- [ ] Remove an element.
- [ ] Check size.
- [ ] Check capacity.
- [ ] Call `ensureCapacity`.
- [ ] Call `trimToSize`.
- [ ] Iterate using Iterator.
- [ ] Iterate using Enumeration.

## Intermediate

- [ ] Implement a stack using Vector.
- [ ] Implement a stack using ArrayDeque.
- [ ] Compare `push`, `pop`, and `peek`.
- [ ] Implement balanced-parentheses checking.
- [ ] Implement expression evaluation using a stack.
- [ ] Implement undo/redo behavior using two stacks.

## Advanced

Build a benchmark comparing:

```text
Stack
ArrayDeque
LinkedList
```

for:

```text
push
pop
peek
large workloads
```

Then analyze:

- [ ] Throughput.
- [ ] Latency.
- [ ] Allocation.
- [ ] Memory.
- [ ] Synchronization overhead.
- [ ] Cache behavior.

---

# 3.4.42 Production Exercise

Design a production component requiring LIFO behavior.

Evaluate:

```text
Stack
ArrayDeque
ConcurrentLinkedDeque
BlockingDeque
```

Answer:

```text
1. Is the component single-threaded?
2. Is blocking required?
3. Are multiple producers possible?
4. Are multiple consumers possible?
5. Is null a valid value?
6. Is strict LIFO required?
7. What latency is expected?
8. What throughput is expected?
9. Is external synchronization acceptable?
```

Choose the implementation based on requirements rather than familiarity.

---

# 3.4.43 Performance Comparison

| Operation | Vector | Stack | ArrayDeque |
|---|---:|---:|---:|
| Random access | O(1) | O(1) | Not List-based |
| Add at end | O(1) amortized | O(1) amortized | O(1) amortized |
| Remove end | O(1) | O(1) | O(1) |
| Stack push | N/A | O(1) amortized | O(1) amortized |
| Stack pop | N/A | O(1) | O(1) |
| Synchronization | Legacy synchronized methods | Inherited | No |
| LIFO abstraction | No | Yes, but broad API | Yes |
| Typical modern stack choice | No | No | Yes |

Exact constants and implementation details should be benchmarked on the target JDK.

---

# 3.4.44 Production Decision Matrix

## Use `ArrayList` when:

```text
general-purpose List
+
random access
+
no intrinsic synchronization required
```

## Use `Vector` when:

```text
maintaining legacy code
+
existing API compatibility requires Vector
```

Do not choose it merely because:

```text
"it is synchronized."
```

## Use `ArrayDeque` when:

```text
LIFO stack
+
deque
+
single-threaded or externally coordinated access
```

## Consider `ConcurrentLinkedDeque` when:

```text
non-blocking concurrent deque behavior
```

## Consider `BlockingDeque` when:

```text
blocking producer/consumer semantics
```

---

# 3.4.45 Senior-Level Trade-Offs

The correct question is not:

> "Which collection is fastest?"

Instead ask:

```text
What abstraction do I need?
        ↓
What concurrency model do I need?
        ↓
What operations dominate?
        ↓
What memory behavior is acceptable?
        ↓
What compatibility constraints exist?
        ↓
Which implementation best matches the workload?
```

This is the correct production decision process.

---

# 3.4.46 Interview Questions

## Basic

- [ ] What is Vector?
- [ ] Is Vector array-backed?
- [ ] Is Vector part of the Collections Framework?
- [ ] Is Vector synchronized?
- [ ] What is Stack?
- [ ] What does Stack extend?
- [ ] What does LIFO mean?
- [ ] What is `Deque`?
- [ ] What is `ArrayDeque`?

## Intermediate

- [ ] Vector vs ArrayList?
- [ ] Why is Vector considered legacy?
- [ ] What is synchronization overhead?
- [ ] Why doesn't synchronized Vector make compound operations atomic?
- [ ] What is the difference between `Enumeration` and `Iterator`?
- [ ] Why is Stack considered a legacy collection?
- [ ] Why does Stack expose List operations?
- [ ] Why is Deque preferred for stack behavior?

## Advanced

- [ ] Explain Vector's internal array and growth behavior.
- [ ] Explain capacity vs size.
- [ ] Explain `capacityIncrement`.
- [ ] Explain Vector synchronization at the method level.
- [ ] Explain why Stack's inheritance weakens the abstraction.
- [ ] Compare Stack and ArrayDeque internals.
- [ ] Why can ArrayDeque outperform Stack?
- [ ] What are the null-handling differences?

## Senior / Production

- [ ] Would you use Vector in a new application?
- [ ] When would maintaining Vector be justified?
- [ ] Would synchronization alone justify choosing Vector?
- [ ] How would you migrate Stack to Deque safely?
- [ ] What if the old Stack-based code relies on null values?
- [ ] What if multiple threads access the replacement deque?
- [ ] When should BlockingDeque be preferred?
- [ ] How would you benchmark Stack vs ArrayDeque?
- [ ] How would you identify synchronization as a production bottleneck?
- [ ] How do API abstraction and inheritance affect collection design?

---

# 3.4.47 Advanced OpenJDK Follow-up

Inspect the OpenJDK source for:

```text
java.util.Vector
java.util.Stack
java.util.ArrayDeque
```

For Vector, study:

- [ ] Internal backing array.
- [ ] Size tracking.
- [ ] Capacity handling.
- [ ] Capacity increment.
- [ ] Growth logic.
- [ ] Element shifting.
- [ ] Synchronization.
- [ ] `ensureCapacity`.
- [ ] `trimToSize`.
- [ ] Iterator.
- [ ] Enumeration.
- [ ] Spliterator.

For Stack, study:

- [ ] Inheritance from Vector.
- [ ] `push`.
- [ ] `pop`.
- [ ] `peek`.
- [ ] `empty`.
- [ ] `search`.

For ArrayDeque, study:

- [ ] Circular-array design.
- [ ] Head/tail management.
- [ ] Growth.
- [ ] Push/pop.
- [ ] Null prohibition.
- [ ] Iterator behavior.

Always verify source against the exact JDK version being studied.

---

# 3.4.48 Internal Mental Model

## Vector

```text
Vector
  |
  +-- size
  +-- capacity
  +-- Object[] elementData
                 |
                 ↓
        +---+---+---+---+---+
        | A | B | C | D |   |
        +---+---+---+---+---+
```

## Stack

```text
Stack
  ↓
Vector
  ↓
Object[] elementData

Top
 ↓
 C
 B
 A
```

## Modern Stack

```text
Deque
  ↓
ArrayDeque

Top
 ↓
 C
 B
 A
```

---

# 3.4.49 Deep Performance Mental Model

Do not reduce the decision to:

```text
Vector = synchronized
Stack = old
ArrayDeque = new
```

Use:

```text
API abstraction
        +
operation complexity
        +
synchronization
        +
contention
        +
memory layout
        +
cache locality
        +
allocation
        +
GC
        +
concurrency semantics
        +
compatibility requirements
        =
correct implementation choice
```

---

# 3.4.50 Final Mastery Gate

## Vector

- [ ] Explain Vector.
- [ ] Explain its historical purpose.
- [ ] Explain its relationship with List.
- [ ] Explain its array-backed internals.
- [ ] Explain size vs capacity.
- [ ] Explain growth.
- [ ] Explain capacity increment.
- [ ] Explain synchronization.
- [ ] Explain synchronization overhead.
- [ ] Explain compound-operation races.
- [ ] Explain legacy APIs.
- [ ] Explain Enumeration.
- [ ] Explain `ensureCapacity`.
- [ ] Explain `trimToSize`.

## Stack

- [ ] Explain Stack.
- [ ] Explain LIFO.
- [ ] Explain Stack's inheritance from Vector.
- [ ] Explain `push`.
- [ ] Explain `pop`.
- [ ] Explain `peek`.
- [ ] Explain `search`.
- [ ] Explain `EmptyStackException`.
- [ ] Explain why inheritance exposes inappropriate operations.
- [ ] Explain why Stack is a legacy choice.

## Deque

- [ ] Explain the Deque abstraction.
- [ ] Implement stack behavior using Deque.
- [ ] Explain ArrayDeque.
- [ ] Explain ArrayDeque vs Stack.
- [ ] Explain null differences.
- [ ] Explain concurrency alternatives.
- [ ] Choose between ArrayDeque, ConcurrentLinkedDeque, and BlockingDeque based on requirements.

## Performance

- [ ] Explain synchronization overhead.
- [ ] Explain contention.
- [ ] Explain array-backed memory behavior.
- [ ] Explain resizing.
- [ ] Explain cache locality.
- [ ] Benchmark Stack vs ArrayDeque.

## Production

- [ ] Know when to maintain Vector/Stack.
- [ ] Know when not to introduce them.
- [ ] Migrate legacy code safely.
- [ ] Evaluate concurrency requirements.
- [ ] Select the correct modern abstraction.
- [ ] Explain trade-offs in an architecture review.

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
