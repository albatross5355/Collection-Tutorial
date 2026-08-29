# Module 3.9 — Queues & Deques Deep Mastery

> **Goal:** Master Java queue abstractions and implementations from FIFO/LIFO fundamentals through `Deque`, `ArrayDeque`, `PriorityQueue`, heap mechanics, blocking queues, producer-consumer design, concurrency, memory behavior, performance, edge cases, production trade-offs, debugging, and interview-level reasoning.

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

# 3.9.1 Queue Fundamentals

## What Is a Queue?

A `Queue<E>` is designed primarily for holding elements before processing.

The common conceptual discipline is:

```text
FIFO
First In → First Out
```

Example:

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer("A");
queue.offer("B");
queue.offer("C");

System.out.println(queue.poll()); // A
System.out.println(queue.poll()); // B
```

---

# 3.9.2 Queue API

Master both exception-returning and special-value-returning methods.

| Operation | Throws exception | Returns special value |
|---|---|---|
| Insert | `add(e)` | `offer(e)` |
| Remove | `remove()` | `poll()` |
| Examine | `element()` | `peek()` |

Understand why both styles exist.

Example:

```java
queue.offer("Java");

String value = queue.poll();

String next = queue.peek();
```

For empty queues:

```text
poll()  → null
peek()  → null
```

whereas:

```text
remove()
element()
```

throw when no element is available.

---

# 3.9.3 Queue Is an Interface

`Queue` does not dictate one internal data structure.

Possible implementations include:

```text
ArrayDeque
LinkedList
PriorityQueue
ArrayBlockingQueue
LinkedBlockingQueue
PriorityBlockingQueue
DelayQueue
SynchronousQueue
```

Therefore:

> Queue behavior and queue implementation are separate concepts.

---

# 3.9.4 FIFO Is Not Universal

Do not assume every Queue implementation is FIFO.

Examples:

```text
ArrayDeque
→ insertion/removal at ends

PriorityQueue
→ priority ordering

DelayQueue
→ delayed availability

SynchronousQueue
→ direct handoff
```

The Queue abstraction is broader than simple FIFO processing.

---

# 3.9.5 Deque

`Deque<E>` means:

```text
Double-Ended Queue
```

It supports insertion and removal at both ends.

Conceptually:

```text
HEAD
 ↓
[A] [B] [C]
          ↑
         TAIL
```

Operations:

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

---

# 3.9.6 Deque as Queue

A Deque can operate as a FIFO queue:

```java
Deque<String> deque = new ArrayDeque<>();

deque.addLast("A");
deque.addLast("B");

System.out.println(deque.removeFirst());
```

Flow:

```text
addLast
   ↓
[A] [B]
   ↓
removeFirst
   ↓
A
```

---

# 3.9.7 Deque as Stack

A Deque can also operate as a LIFO stack:

```java
Deque<String> stack = new ArrayDeque<>();

stack.push("A");
stack.push("B");

System.out.println(stack.pop()); // B
```

Therefore:

```text
Deque
 ├── FIFO queue
 └── LIFO stack
```

This is why `Deque` is generally preferred over the legacy `Stack` class for stack behavior.

---

# 3.9.8 ArrayDeque

## What Is It?

`ArrayDeque` is a resizable-array implementation of `Deque`.

Example:

```java
Deque<Integer> deque =
    new ArrayDeque<>();

deque.addFirst(10);
deque.addLast(20);
```

It supports efficient operations at both ends.

---

# 3.9.9 ArrayDeque Internal Structure

Conceptually:

```text
circular/resizable array

       HEAD
        ↓
[ ][ ][A][B][C][ ][ ]
             ↑
            TAIL
```

The implementation uses an array with head/tail positioning and wraps around the underlying storage.

The exact resizing and indexing strategy should be verified against the target JDK source.

---

# 3.9.10 ArrayDeque Circular Buffer Concept

Instead of physically shifting every element when removing from the front:

```text
remove first
```

the implementation can advance its logical head.

Conceptually:

```text
Before:

HEAD
 ↓
[A][B][C][D]
          ↑
         TAIL

After removing A:

   HEAD
    ↓
[B][C][D]
```

This avoids O(N) front-shifting.

---

# 3.9.11 ArrayDeque Performance

Typical:

```text
addFirst       O(1) amortized
addLast        O(1) amortized
removeFirst    O(1)
removeLast     O(1)
peekFirst      O(1)
peekLast       O(1)
```

Occasional resizing can require O(N) work.

Overall behavior is efficient because resizing is amortized.

---

# 3.9.12 ArrayDeque Restrictions

`ArrayDeque` does not permit:

```text
null elements
```

This allows:

```text
null
```

to be used unambiguously as an empty-result signal for operations such as:

```java
poll()
peek()
```

---

# 3.9.13 ArrayDeque vs LinkedList

Both can implement `Deque`.

Prefer `ArrayDeque` for typical queue/deque workloads because it generally provides:

```text
better cache locality
+
less per-element node overhead
+
fewer object allocations
```

`LinkedList` has node-based storage and generally higher memory overhead.

---

# 3.9.14 PriorityQueue

## What Is It?

`PriorityQueue<E>` is a Queue where the head is determined by priority rather than insertion order.

Example:

```java
PriorityQueue<Integer> pq =
    new PriorityQueue<>();

pq.offer(30);
pq.offer(10);
pq.offer(20);

System.out.println(pq.peek()); // 10
```

By default, the smallest element has highest priority.

---

# 3.9.15 PriorityQueue Comparator

Custom ordering:

```java
PriorityQueue<Integer> pq =
    new PriorityQueue<>(Comparator.reverseOrder());
```

Now:

```text
largest element
```

has highest priority.

For objects:

```java
PriorityQueue<Task> tasks =
    new PriorityQueue<>(
        Comparator.comparingInt(Task::priority)
    );
```

---

# 3.9.16 PriorityQueue Internal Structure

PriorityQueue is based on a:

```text
binary heap
```

Typically represented in an array.

Conceptually:

```text
        10
       /       20    30
    /     40  50
```

Array representation:

```text
[10, 20, 30, 40, 50]
```

---

# 3.9.17 Heap Concepts

A min-heap satisfies:

```text
parent <= children
```

A max-heap satisfies:

```text
parent >= children
```

Important:

> A heap is not a fully sorted array.

Only the heap-order property is guaranteed.

---

# 3.9.18 Heap Array Relationships

For a zero-based binary heap, commonly:

```text
parent(i) = (i - 1) / 2

left(i)   = 2i + 1

right(i)  = 2i + 2
```

These formulas are fundamental for implementing heaps manually.

---

# 3.9.19 PriorityQueue `offer()`

Conceptually:

```text
insert at end
    ↓
new element
    ↓
sift up
    ↓
compare with parent
    ↓
swap/move until heap property restored
```

Complexity:

```text
O(log N)
```

---

# 3.9.20 PriorityQueue `poll()`

Conceptually:

```text
remove root
    ↓
move last element to root
    ↓
sift down
    ↓
restore heap property
```

Complexity:

```text
O(log N)
```

---

# 3.9.21 PriorityQueue `peek()`

`peek()` reads the root of the heap.

Typical complexity:

```text
O(1)
```

The root represents the highest-priority element according to the queue's ordering.

---

# 3.9.22 PriorityQueue `remove(Object)`

This is different from removing the head.

Arbitrary removal generally requires locating the element first.

Typical complexity:

```text
O(N)
```

followed by heap repair.

Do not assume every PriorityQueue removal is O(log N).

---

# 3.9.23 PriorityQueue Iteration

A critical interview point:

```java
for (Integer x : pq) {
    System.out.println(x);
}
```

does not guarantee sorted-order traversal.

If sorted removal is required:

```java
while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}
```

The queue guarantees its head according to priority, not a sorted iterator.

---

# 3.9.24 PriorityQueue Null and Ordering

PriorityQueue does not permit null elements.

Elements must be mutually comparable according to:

```text
natural ordering
```

or:

```text
Comparator
```

If ordering is inconsistent or not mutually compatible, runtime failures can occur.

---

# 3.9.25 PriorityQueue Common Uses

- [ ] Top-K problems.
- [ ] Kth-largest / Kth-smallest.
- [ ] Scheduling.
- [ ] Dijkstra's algorithm.
- [ ] Event simulation.
- [ ] Merge K sorted lists.
- [ ] Task prioritization.
- [ ] Best-first search.

---

# 3.9.26 BlockingQueue

`BlockingQueue<E>` is designed for concurrent producer-consumer scenarios.

Key behavior:

```text
producer
   ↓
queue
   ↓
consumer
```

Operations can wait when:

```text
queue is full
```

or:

```text
queue is empty
```

depending on the operation.

---

# 3.9.27 BlockingQueue API

Master four operation styles:

| Operation | Throws | Special value | Blocks | Times out |
|---|---|---|---|---|
| Insert | `add` | `offer` | `put` | `offer(timeout)` |
| Remove | `remove` | `poll` | `take` | `poll(timeout)` |
| Examine | `element` | `peek` | — | — |

Critical methods:

```java
put()
take()

offer()
poll()

offer(e, timeout, unit)
poll(timeout, unit)
```

---

# 3.9.28 Producer-Consumer Pattern

Producer:

```java
queue.put(item);
```

Consumer:

```java
Item item = queue.take();
```

Conceptually:

```text
Producer
   |
   | put()
   v
+---------+
| Queue   |
+---------+
   |
   | take()
   v
Consumer
```

This avoids manually implementing:

```text
wait()
notify()
```

for many producer-consumer scenarios.

---

# 3.9.29 ArrayBlockingQueue

`ArrayBlockingQueue` is a bounded blocking queue backed by an array.

Example:

```java
BlockingQueue<Integer> queue =
    new ArrayBlockingQueue<>(100);
```

Capacity is fixed.

---

# 3.9.30 ArrayBlockingQueue Internal Model

Conceptually:

```text
fixed array
+
head
+
tail
+
count
+
concurrency control
```

It is useful when you need explicit bounded capacity.

Bounded queues are important for:

```text
backpressure
+
memory protection
+
load shedding
```

---

# 3.9.31 ArrayBlockingQueue Fairness

`ArrayBlockingQueue` can optionally be constructed with fairness:

```java
new ArrayBlockingQueue<>(100, true);
```

Fairness can influence waiting-thread ordering.

Trade-off:

```text
fairness
vs.
throughput
```

Do not enable fairness automatically without understanding the workload.

---

# 3.9.32 ArrayBlockingQueue Use Cases

- [ ] Bounded worker queues.
- [ ] Producer-consumer pipelines.
- [ ] Backpressure.
- [ ] Resource-limited systems.
- [ ] Preventing unlimited memory growth.

---

# 3.9.33 LinkedBlockingQueue

`LinkedBlockingQueue` is a potentially bounded blocking queue based on linked nodes.

Example:

```java
BlockingQueue<Task> queue =
    new LinkedBlockingQueue<>(1000);
```

It can be:

```text
bounded
```

or, if constructed without a capacity, effectively limited by implementation/integer capacity rather than being a practically safe unlimited queue.

Production code should generally choose capacity deliberately.

---

# 3.9.34 LinkedBlockingQueue Internal Model

Conceptually:

```text
head → Node → Node → Node → tail

+
producer-side locking
+
consumer-side locking
+
count
```

Its design can allow greater concurrency between producers and consumers than a single-lock design, subject to implementation details.

---

# 3.9.35 LinkedBlockingQueue Trade-offs

Advantages:

```text
linked growth
+
optional capacity
+
producer/consumer concurrency
```

Costs:

```text
per-node object allocation
+
pointer chasing
+
GC overhead
+
poorer cache locality
```

Compare it with ArrayBlockingQueue based on actual workload.

---

# 3.9.36 PriorityBlockingQueue

`PriorityBlockingQueue` combines:

```text
PriorityQueue
+
blocking behavior
```

Example:

```java
BlockingQueue<Task> queue =
    new PriorityBlockingQueue<>();
```

The head is the highest-priority element.

---

# 3.9.37 PriorityBlockingQueue Characteristics

Important:

> `PriorityBlockingQueue` is unbounded in its normal design.

Therefore:

```text
put()
```

does not block merely because the queue is "full."

This makes it fundamentally different from:

```text
ArrayBlockingQueue
```

for backpressure.

---

# 3.9.38 PriorityBlockingQueue Ordering

The head follows priority.

However:

```text
iteration
```

does not provide globally sorted traversal.

As with PriorityQueue:

```text
poll()
```

repeatedly to retrieve elements according to priority.

---

# 3.9.39 PriorityBlockingQueue Use Cases

- [ ] Concurrent task scheduling.
- [ ] Priority-based worker queues.
- [ ] Event processing.
- [ ] Best-first processing.
- [ ] Multi-priority work systems.

Be careful about:

```text
starvation
```

when low-priority work is continuously delayed by high-priority work.

---

# 3.9.40 DelayQueue

`DelayQueue<E extends Delayed>` is a blocking queue where an element becomes available only after its delay expires.

Conceptually:

```text
insert task
    ↓
wait until delay expires
    ↓
task becomes eligible
    ↓
take()
```

---

# 3.9.41 DelayQueue Requirements

Elements must implement:

```java
Delayed
```

Example structure:

```java
class DelayedTask implements Delayed {

    private final long deadline;

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(
            deadline - System.nanoTime(),
            TimeUnit.NANOSECONDS
        );
    }

    @Override
    public int compareTo(Delayed other) {
        return Long.compare(
            getDelay(TimeUnit.NANOSECONDS),
            other.getDelay(TimeUnit.NANOSECONDS)
        );
    }
}
```

Use a monotonic time source such as `System.nanoTime()` for elapsed-time calculations.

---

# 3.9.42 DelayQueue Internal Concept

It is priority-based:

```text
earliest expiration
        ↓
       head
        ↓
available when delay <= 0
```

Conceptually:

```text
Task A → 2 sec
Task B → 5 sec
Task C → 10 sec

        ↓

A becomes available first
```

---

# 3.9.43 DelayQueue Use Cases

- [ ] Scheduled expiration.
- [ ] Retry scheduling.
- [ ] Temporary object expiration.
- [ ] Time-based caches.
- [ ] Delayed jobs.
- [ ] Lease expiration.

It is not a general replacement for a full-featured scheduled executor.

---

# 3.9.44 SynchronousQueue

`SynchronousQueue` is fundamentally different.

It has:

```text
no meaningful internal capacity for storing elements
```

A producer hands an element directly to a consumer.

Conceptually:

```text
Producer
   |
   | put(A)
   v
[ HANDOFF ]
   |
   | take()
   v
Consumer
```

There is no ordinary buffered queue of waiting elements.

---

# 3.9.45 SynchronousQueue Semantics

If:

```java
queue.put(task);
```

is called while no consumer is ready, the producer waits.

When a consumer performs:

```java
queue.take();
```

the handoff occurs.

This is useful for:

```text
direct task handoff
+
thread-pool work submission
+
rendezvous-style coordination
```

---

# 3.9.46 SynchronousQueue Fairness

It can be constructed with fairness:

```java
new SynchronousQueue<>(true);
```

Fairness affects the underlying handoff ordering.

As always:

```text
fairness
vs.
throughput
```

is a trade-off.

---

# 3.9.47 BlockingQueue Comparison

| Implementation | Capacity | Ordering | Main structure | Key use |
|---|---|---|---|---|
| `ArrayBlockingQueue` | Bounded | FIFO | Array | Backpressure |
| `LinkedBlockingQueue` | Bounded/large | FIFO | Linked nodes | Producer-consumer |
| `PriorityBlockingQueue` | Unbounded | Priority | Heap | Priority tasks |
| `DelayQueue` | Unbounded | Delay/priority | Heap-like | Delayed work |
| `SynchronousQueue` | No buffered capacity | Handoff | Transfer mechanism | Direct handoff |

---

# 3.9.48 Queue Selection Decision Tree

```text
Need ordinary non-blocking FIFO?
        ↓
    ArrayDeque

Need double-ended operations?
        ↓
    ArrayDeque

Need priority ordering?
        ↓
    PriorityQueue

Need bounded producer-consumer queue?
        ↓
    ArrayBlockingQueue

Need linked blocking FIFO?
        ↓
    LinkedBlockingQueue

Need concurrent priority queue?
        ↓
    PriorityBlockingQueue

Need delayed availability?
        ↓
    DelayQueue

Need direct producer-consumer handoff?
        ↓
    SynchronousQueue
```

---

# 3.9.49 Queue vs Stack

Modern Java preference:

```text
Stack behavior
    ↓
Deque
    ↓
ArrayDeque
```

Avoid defaulting to the legacy:

```java
Stack
```

for new designs.

---

# 3.9.50 Queue vs PriorityQueue

FIFO:

```text
A
B
C

poll → A
```

PriorityQueue:

```text
30
10
20

poll → 10
```

The insertion order is not the primary ordering rule in PriorityQueue.

---

# 3.9.51 Queue vs BlockingQueue

`Queue`:

```text
non-blocking abstraction
```

`BlockingQueue`:

```text
concurrency-aware
+
blocking operations
+
producer-consumer coordination
```

Do not add manual synchronization around a BlockingQueue without a concrete reason.

---

# 3.9.52 Backpressure

A bounded BlockingQueue can provide a form of backpressure:

```text
Producer
   ↓
bounded queue
   ↓
Consumer
```

When the queue reaches capacity:

```text
put()
```

can block, or:

```java
offer()
```

can fail/return according to the chosen overload.

This prevents unlimited buffering.

---

# 3.9.53 Backpressure Trade-offs

Too large:

```text
memory growth
+
high latency
+
stale work
```

Too small:

```text
producer blocking
+
lower throughput
```

Good queue sizing depends on:

```text
arrival rate
+
service rate
+
burst size
+
latency requirements
+
memory budget
+
failure behavior
```

---

# 3.9.54 Poison Pill Pattern

A common shutdown technique:

```java
queue.put(POISON_PILL);
```

Consumer:

```java
while (true) {
    Task task = queue.take();

    if (task == POISON_PILL) {
        break;
    }

    process(task);
}
```

Understand its limitations in:

```text
multiple consumers
+
multiple producers
+
restarts
+
distributed systems
```

---

# 3.9.55 Common Queue Mistakes

- [ ] Assuming every Queue is FIFO.
- [ ] Using `remove()` when `poll()` is safer.
- [ ] Ignoring the difference between `add()` and `offer()`.
- [ ] Ignoring the difference between `take()` and `poll()`.
- [ ] Using `LinkedList` when ArrayDeque is more appropriate.
- [ ] Assuming PriorityQueue iteration is sorted.
- [ ] Assuming PriorityQueue arbitrary removal is O(log N).
- [ ] Using an unbounded queue where backpressure is required.
- [ ] Using PriorityBlockingQueue expecting bounded behavior.
- [ ] Assuming DelayQueue is a general scheduler.
- [ ] Treating SynchronousQueue as a normal buffer.
- [ ] Ignoring fairness/throughput trade-offs.
- [ ] Holding references indefinitely in queues.
- [ ] Forgetting shutdown behavior.
- [ ] Allowing unbounded backlog in production.

---

# 3.9.56 Edge Cases

## Queue

- [ ] Empty queue.
- [ ] `peek()` vs `element()`.
- [ ] `poll()` vs `remove()`.
- [ ] Duplicate elements.
- [ ] Null behavior.

## ArrayDeque

- [ ] Empty deque.
- [ ] First/last operations.
- [ ] Resizing.
- [ ] Wrap-around.
- [ ] Null.
- [ ] Concurrent access.

## PriorityQueue

- [ ] Duplicate priorities.
- [ ] Comparator returning zero.
- [ ] Mutable priority fields.
- [ ] Null.
- [ ] Iterator ordering.
- [ ] Arbitrary removal.
- [ ] Heap resizing.

## BlockingQueue

- [ ] Empty queue.
- [ ] Full queue.
- [ ] Interrupted threads.
- [ ] Timeout expiration.
- [ ] Shutdown.
- [ ] Producer failure.
- [ ] Consumer failure.

## DelayQueue

- [ ] Zero delay.
- [ ] Negative delay.
- [ ] Multiple equal deadlines.
- [ ] Incorrect `compareTo()`.
- [ ] Clock calculations.
- [ ] Cancellation.

## SynchronousQueue

- [ ] Producer without consumer.
- [ ] Consumer without producer.
- [ ] Interrupted handoff.
- [ ] Fair vs non-fair behavior.
- [ ] Multiple producers/consumers.

---

# 3.9.57 Thread Interruption

Blocking methods such as:

```java
put()
take()
poll(timeout)
offer(timeout)
```

can throw:

```java
InterruptedException
```

Understand:

```text
interruption
+
cancellation
+
shutdown
```

Do not blindly swallow interruption:

```java
catch (InterruptedException e) {
    // ignore
}
```

A production design should preserve or deliberately handle the interruption signal.

---

# 3.9.58 Performance Comparison

Study:

| Operation | ArrayDeque | PriorityQueue | ArrayBlockingQueue | LinkedBlockingQueue |
|---|---:|---:|---:|---:|
| Add/offer | O(1) amortized | O(log N) | O(1) | O(1) typical |
| Remove/poll | O(1) | O(log N) | O(1) | O(1) typical |
| Peek | O(1) | O(1) | O(1) | O(1) |
| Ordering | End-based | Priority | FIFO | FIFO |
| Main cost | Resize | Heap maintenance | Synchronization | Node allocation |

Actual throughput depends heavily on contention, workload, CPU cache behavior, allocation, and queue size.

---

# 3.9.59 Production Use Cases

## ArrayDeque

- [ ] BFS queues.
- [ ] DFS stacks.
- [ ] Sliding-window algorithms.
- [ ] Local task processing.
- [ ] Parsing.

## PriorityQueue

- [ ] Top-K.
- [ ] Dijkstra.
- [ ] Scheduling.
- [ ] Event simulation.
- [ ] Best-first search.

## ArrayBlockingQueue

- [ ] Bounded worker pools.
- [ ] Backpressure.
- [ ] Resource-limited pipelines.

## LinkedBlockingQueue

- [ ] Producer-consumer pipelines.
- [ ] Work queues.
- [ ] Thread pools where a linked queue is appropriate.

## PriorityBlockingQueue

- [ ] Concurrent priority scheduling.
- [ ] Priority work processing.

## DelayQueue

- [ ] Delayed retries.
- [ ] Expiration.
- [ ] Lease management.

## SynchronousQueue

- [ ] Direct task handoff.
- [ ] Rendezvous coordination.
- [ ] Certain executor designs.

---

# 3.9.60 Production Design — Worker Queue

Requirement:

```text
multiple producers
+
multiple consumers
+
bounded memory
+
backpressure
```

Possible choice:

```text
ArrayBlockingQueue
```

Analyze:

```text
capacity
+
fairness
+
shutdown
+
rejection
+
timeouts
+
monitoring
```

---

# 3.9.61 Production Design — Priority Worker System

Requirement:

```text
multiple workers
+
higher priority tasks first
```

Possible choice:

```text
PriorityBlockingQueue
```

But analyze:

```text
unbounded growth
+
starvation
+
priority inversion
+
fairness
+
memory
```

A bounded priority queue may require a different architecture because PriorityBlockingQueue itself is not bounded.

---

# 3.9.62 Production Design — Delayed Retry Queue

Requirement:

```text
retry task after delay
```

Possible choice:

```text
DelayQueue
```

But also compare against:

```text
ScheduledExecutorService
+
external message broker
+
persistent scheduler
```

depending on:

```text
durability
+
process restart
+
horizontal scaling
+
delivery guarantees
```

---

# 3.9.63 Coding Exercises

## Basic

- [ ] Implement FIFO processing using Queue.
- [ ] Implement stack behavior using Deque.
- [ ] Implement queue behavior using ArrayDeque.
- [ ] Reverse elements using a Deque.
- [ ] Implement min-priority processing using PriorityQueue.

## Intermediate

- [ ] Implement BFS using ArrayDeque.
- [ ] Implement DFS using ArrayDeque.
- [ ] Solve Top-K using PriorityQueue.
- [ ] Merge K sorted arrays using PriorityQueue.
- [ ] Implement a task scheduler using PriorityQueue.
- [ ] Build a bounded producer-consumer pipeline using ArrayBlockingQueue.

## Advanced

- [ ] Implement a binary min-heap from scratch.
- [ ] Implement a binary max-heap from scratch.
- [ ] Implement heapify.
- [ ] Implement heap sort.
- [ ] Implement a custom priority task.
- [ ] Implement delayed tasks using DelayQueue.
- [ ] Build a multi-producer/multi-consumer pipeline.
- [ ] Compare ArrayBlockingQueue and LinkedBlockingQueue.

## Production-Style

- [ ] Design a bounded worker queue.
- [ ] Design a priority-based job processor.
- [ ] Design a delayed retry system.
- [ ] Design graceful shutdown for consumers.
- [ ] Design queue monitoring and backlog alerts.
- [ ] Design overload/backpressure handling.
- [ ] Design a direct-handoff worker model using SynchronousQueue.

---

# 3.9.64 Heap Exercise

Implement:

```java
class MinHeap {

    void add(int value);

    int peek();

    int poll();

    int size();

    void heapify(int[] values);
}
```

You must explain:

```text
parent index
+
left child index
+
right child index
+
sift up
+
sift down
+
heapify
```

---

# 3.9.65 Debugging Exercise — PriorityQueue

Create:

```java
class Task {
    int priority;
    String name;
}
```

Insert tasks into:

```java
PriorityQueue<Task>
```

Then:

- [ ] Change a task's priority after insertion.
- [ ] Observe behavior.
- [ ] Explain why mutable priority is dangerous.
- [ ] Fix the design using immutable priority.
- [ ] Add a tie-breaker comparator.

---

# 3.9.66 Debugging Exercise — BlockingQueue Shutdown

Build:

```text
2 producers
+
3 consumers
+
ArrayBlockingQueue
```

Then implement:

- [ ] Graceful shutdown.
- [ ] Interruption handling.
- [ ] Consumer termination.
- [ ] Producer termination.
- [ ] Poison-pill approach.
- [ ] Timeout-based shutdown.

Explain why each approach behaves differently.

---

# 3.9.67 OpenJDK Source Investigation

Inspect the target JDK source for:

```text
Queue
Deque
ArrayDeque
PriorityQueue
BlockingQueue
ArrayBlockingQueue
LinkedBlockingQueue
PriorityBlockingQueue
DelayQueue
SynchronousQueue
```

Study:

## ArrayDeque

- [ ] Array representation.
- [ ] Head/tail.
- [ ] Wrap-around.
- [ ] Growth.
- [ ] Add/remove operations.
- [ ] Iterator.

## PriorityQueue

- [ ] Backing array.
- [ ] Heap representation.
- [ ] Sift-up.
- [ ] Sift-down.
- [ ] Comparator.
- [ ] Resize.
- [ ] Removal.

## ArrayBlockingQueue

- [ ] Fixed array.
- [ ] Locks.
- [ ] Conditions.
- [ ] Put/take coordination.
- [ ] Fairness.

## LinkedBlockingQueue

- [ ] Node structure.
- [ ] Producer/consumer synchronization.
- [ ] Count.
- [ ] Put/take operations.

## PriorityBlockingQueue

- [ ] Heap.
- [ ] Locking.
- [ ] Condition.
- [ ] Blocking behavior.
- [ ] Unbounded capacity.

## DelayQueue

- [ ] Priority structure.
- [ ] Delayed ordering.
- [ ] Leader/follower optimization.
- [ ] Blocking behavior.

## SynchronousQueue

- [ ] Transfer architecture.
- [ ] Fair vs non-fair mode.
- [ ] Producer/consumer matching.
- [ ] Blocking behavior.

---

# 3.9.68 Advanced Internal Questions

- [ ] Why is ArrayDeque faster than LinkedList for many queue workloads?
- [ ] How does ArrayDeque wrap around its array?
- [ ] When does ArrayDeque resize?
- [ ] Why is PriorityQueue not sorted internally?
- [ ] Explain heap invariants.
- [ ] Explain sift-up and sift-down.
- [ ] Why is heapify O(N)?
- [ ] Why is PriorityQueue `peek()` O(1)?
- [ ] Why can arbitrary PriorityQueue removal be O(N)?
- [ ] How does ArrayBlockingQueue coordinate producers and consumers?
- [ ] Why can LinkedBlockingQueue use separate producer/consumer synchronization?
- [ ] Why is PriorityBlockingQueue not bounded?
- [ ] How does DelayQueue determine availability?
- [ ] Why does DelayQueue use monotonic time calculations?
- [ ] How does SynchronousQueue perform direct handoff?
- [ ] How does fairness change queue behavior?

---

# 3.9.69 Interview Questions

## Basic

- [ ] What is a Queue?
- [ ] What is FIFO?
- [ ] What is a Deque?
- [ ] Difference between Queue and Deque?
- [ ] What is ArrayDeque?
- [ ] Why is Deque preferred over Stack?
- [ ] What is PriorityQueue?
- [ ] What is a heap?
- [ ] What is BlockingQueue?
- [ ] What is ArrayBlockingQueue?
- [ ] What is LinkedBlockingQueue?
- [ ] What is PriorityBlockingQueue?
- [ ] What is DelayQueue?
- [ ] What is SynchronousQueue?

## Intermediate

- [ ] `add()` vs `offer()`?
- [ ] `remove()` vs `poll()`?
- [ ] `element()` vs `peek()`?
- [ ] How can Deque implement a stack?
- [ ] Why does ArrayDeque not allow null?
- [ ] How does PriorityQueue maintain priority?
- [ ] Is PriorityQueue iteration sorted?
- [ ] What is the complexity of PriorityQueue operations?
- [ ] What is the difference between bounded and unbounded queues?
- [ ] How does BlockingQueue help producer-consumer systems?

## Advanced

- [ ] Explain ArrayDeque internals.
- [ ] Explain PriorityQueue heap internals.
- [ ] Explain heapify O(N).
- [ ] Explain ArrayBlockingQueue synchronization.
- [ ] Explain LinkedBlockingQueue's architecture.
- [ ] Explain PriorityBlockingQueue.
- [ ] Explain DelayQueue.
- [ ] Explain SynchronousQueue.
- [ ] Explain fairness in blocking queues.
- [ ] Explain interruption during blocking operations.

## Senior / Production

- [ ] How would you choose between ArrayBlockingQueue and LinkedBlockingQueue?
- [ ] How would you design queue backpressure?
- [ ] How would you prevent unbounded memory growth?
- [ ] How would you handle priority starvation?
- [ ] How would you implement graceful queue shutdown?
- [ ] How would you debug a growing queue backlog?
- [ ] How would you determine the correct queue capacity?
- [ ] How would you choose DelayQueue vs ScheduledExecutorService?
- [ ] When is SynchronousQueue appropriate?
- [ ] How would you monitor queue latency and depth?
- [ ] How would you handle consumer failures?
- [ ] How would you design a queue across multiple application instances?

---

# 3.9.70 Final Mastery Gate

## Queue

- [ ] Explain Queue.
- [ ] Explain FIFO.
- [ ] Explain Queue operation pairs.
- [ ] Explain empty-queue behavior.
- [ ] Explain different Queue semantics.

## Deque

- [ ] Explain double-ended operations.
- [ ] Implement FIFO using Deque.
- [ ] Implement LIFO using Deque.
- [ ] Explain why ArrayDeque is preferred over Stack.

## ArrayDeque

- [ ] Explain internal array.
- [ ] Explain head/tail.
- [ ] Explain circular behavior.
- [ ] Explain resizing.
- [ ] Explain amortized complexity.
- [ ] Handle null restriction.
- [ ] Compare against LinkedList.

## PriorityQueue

- [ ] Explain priority semantics.
- [ ] Explain min-heap.
- [ ] Explain max-heap.
- [ ] Explain array representation.
- [ ] Explain sift-up.
- [ ] Explain sift-down.
- [ ] Explain heapify.
- [ ] Explain complexity.
- [ ] Handle comparator edge cases.
- [ ] Explain iteration behavior.

## BlockingQueue

- [ ] Explain producer-consumer architecture.
- [ ] Explain blocking methods.
- [ ] Explain timed methods.
- [ ] Handle interruption.
- [ ] Explain backpressure.
- [ ] Explain bounded vs unbounded queues.

## ArrayBlockingQueue

- [ ] Explain fixed capacity.
- [ ] Explain array storage.
- [ ] Explain synchronization.
- [ ] Explain fairness.
- [ ] Use it for backpressure.

## LinkedBlockingQueue

- [ ] Explain linked nodes.
- [ ] Explain capacity.
- [ ] Explain producer/consumer coordination.
- [ ] Explain allocation overhead.
- [ ] Compare with ArrayBlockingQueue.

## PriorityBlockingQueue

- [ ] Explain heap + blocking.
- [ ] Explain priority ordering.
- [ ] Explain unbounded behavior.
- [ ] Handle starvation.
- [ ] Explain why it is unsuitable as a bounded backpressure mechanism by itself.

## DelayQueue

- [ ] Explain delayed availability.
- [ ] Implement `Delayed`.
- [ ] Explain ordering.
- [ ] Handle time calculations.
- [ ] Compare with scheduled executors.

## SynchronousQueue

- [ ] Explain direct handoff.
- [ ] Explain lack of buffered capacity.
- [ ] Explain producer/consumer matching.
- [ ] Explain fairness.
- [ ] Use it in an appropriate production scenario.

## Performance

- [ ] Compare ArrayDeque.
- [ ] Compare PriorityQueue.
- [ ] Compare ArrayBlockingQueue.
- [ ] Compare LinkedBlockingQueue.
- [ ] Understand heap complexity.
- [ ] Understand allocation and cache effects.
- [ ] Benchmark realistic workloads.

## Production

- [ ] Design a worker queue.
- [ ] Design backpressure.
- [ ] Design priority scheduling.
- [ ] Design delayed retries.
- [ ] Design graceful shutdown.
- [ ] Design queue monitoring.
- [ ] Debug backlog growth.
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
- [ ] CONCURRENCY MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
