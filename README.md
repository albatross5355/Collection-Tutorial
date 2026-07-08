# Collection-Tuto
Core Java (Deep Mastery)
Language Fundamentals
OOP pillars: Encapsulation, Inheritance, Polymorphism (compile-time vs runtime), Abstraction
SOLID principles with real code examples
Access modifiers, final, static, abstract, default, sealed (Java 17), non-sealed, permits
this vs super, constructor chaining, initialization order (static block ? instance block ? constructor)
Pass-by-value semantics (Java is always pass-by-value)

Data Types & Memory
Primitives vs wrappers, autoboxing/unboxing pitfalls
String pool, String vs StringBuilder vs StringBuffer, intern()
Immutability — how to design immutable classes (final class, final fields, defensive copies)
equals() and hashCode() contract, compareTo() consistency

Collections Framework (must know cold)
ArrayList vs LinkedList vs Vector vs CopyOnWriteArrayList
HashMap internals: buckets, hash, treeify threshold (8), load factor (0.75), resize
ConcurrentHashMap internals (segment locking ? CAS + synchronized bins in Java 8+)
LinkedHashMap, TreeMap, WeakHashMap, IdentityHashMap, EnumMap
HashSet, TreeSet, LinkedHashSet
PriorityQueue, ArrayDeque, BlockingQueue implementations
fail-fast vs fail-safe iterators
Comparable vs Comparator
Exception Handling
Checked vs unchecked, Error vs Exception
try-with-resources, multi-catch, exception chaining
Custom exceptions, when to wrap vs propagate
finally block gotchas (return statements, resource leaks)
Generics
Bounded types, wildcards (? extends, ? super), PECS rule
Type erasure, reifiable types
Generic methods, classes, and constraints

Java 8 to Java 21+ Features (all versions)
Java 8: Lambdas, Streams, Optional, Functional interfaces, Method references, Default/static methods, CompletableFuture, Date/Time API, Nashorn
Java 9: Modules (JPMS), var restrictions, factory methods (List.of), private interface methods
Java 10: var local variables
Java 11 (LTS): HTTP Client, String methods, var in lambdas
Java 14: Switch expressions, NullPointerException helpful messages
Java 15: Text blocks, sealed classes (preview)
Java 16: Records, pattern matching for instanceof
Java 17 (LTS): Sealed classes, enhanced pseudo-random generators
Java 21 (LTS): Virtual threads (Project Loom), pattern matching for switch, record patterns, sequenced collections, structured concurrency (preview)
Java 22-25: Stream gatherers, scoped values, unnamed variables

Concurrency & Multithreading (critical)
Thread lifecycle, Thread vs Runnable vs Callable
synchronized, volatile, Atomic* classes
Java Memory Model (JMM): happens-before, visibility, reordering
wait(), notify(), notifyAll(), spurious wakeups
Locks: ReentrantLock, ReadWriteLock, StampedLock
Executors: ThreadPoolExecutor internals (core/max pool, queue types, rejection policies)
Future, CompletableFuture (chaining, combining, exception handling)
ForkJoinPool, parallel streams, work-stealing
CountDownLatch, CyclicBarrier, Semaphore, Phaser, Exchanger
Deadlock, livelock, starvation — detection and prevention
Virtual threads (Loom) vs platform threads, when to use
Thread-safe design patternss


JVM Internals
Class loading: Bootstrap ? Extension ? Application ? Custom; parent delegation
Memory areas: Heap (Young: Eden/S0/S1, Old), Metaspace, Stack, PC register, Native method stack
Garbage collectors: Serial, Parallel, CMS (deprecated), G1, ZGC, Shenandoah, Epsilon
GC tuning flags: -Xms, -Xmx, -XX:MaxGCPauseMillis, -XX:+UseG1GC
JIT compilation: C1, C2, tiered compilation, escape analysis, inlining
JVM tools: jps, jstack, jmap, jstat, jcmd, jfr, VisualVM, JMC, Eclipse MAT
Heap dump & thread dump analysis
OutOfMemoryError types: Java heap space, Metaspace, GC overhead limit, Direct buffer memory
