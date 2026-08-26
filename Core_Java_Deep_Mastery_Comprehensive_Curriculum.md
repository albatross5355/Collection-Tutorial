# Core Java (Deep Mastery) & Advanced Java — Comprehensive Curriculum

## Goal

A comprehensive **Core Java → Advanced Java → Production Java** roadmap designed for deep understanding rather than surface-level topic coverage.

For every topic, use this mastery cycle:

1. What is it?
2. Why does Java have it?
3. Syntax and API
4. Basic example
5. Internal working
6. Memory/runtime behavior
7. Edge cases
8. Common mistakes
9. Performance implications
10. Production use cases
11. Interview questions
12. Coding exercises
13. Advanced follow-ups

---

# Module 1 — Java Fundamentals & OOP

## 1.1 Java Platform Fundamentals
- [ ] JDK, JRE, JVM
- [ ] Java source code compilation
- [ ] `javac`
- [ ] Bytecode
- [ ] `.class` files
- [ ] JVM execution lifecycle
- [ ] Classpath
- [ ] Module path
- [ ] Java launcher
- [ ] Java versioning and LTS releases

## 1.2 Language Fundamentals
- [ ] Variables
- [ ] Constants
- [ ] Literals
- [ ] Primitive data types
- [ ] Reference types
- [ ] Scope
- [ ] Lifetime
- [ ] Operators
- [ ] Type casting
- [ ] Widening and narrowing conversion
- [ ] Control flow
- [ ] Loops
- [ ] `break`
- [ ] `continue`
- [ ] `switch`
- [ ] Methods
- [ ] Method signatures
- [ ] Varargs

## 1.3 OOP
- [ ] Classes and objects
- [ ] Encapsulation
- [ ] Inheritance
- [ ] Polymorphism
- [ ] Compile-time polymorphism
- [ ] Runtime polymorphism
- [ ] Method overloading
- [ ] Method overriding
- [ ] Dynamic method dispatch
- [ ] Abstraction
- [ ] Interfaces
- [ ] Abstract classes
- [ ] Composition vs inheritance
- [ ] "Is-a" vs "Has-a"

## 1.4 Object Initialization
- [ ] `this`
- [ ] `super`
- [ ] Constructors
- [ ] Constructor overloading
- [ ] Constructor chaining
- [ ] Static initialization
- [ ] Instance initialization blocks
- [ ] Constructor execution order
- [ ] Parent-to-child initialization order
- [ ] Static block → instance initialization → constructor

## 1.5 Object Class
- [ ] `Object`
- [ ] `equals()`
- [ ] `hashCode()`
- [ ] `toString()`
- [ ] `getClass()`
- [ ] `clone()`
- [ ] `finalize()` history/deprecation/removal context

## 1.6 Java Design Principles
- [ ] SOLID
- [ ] Single Responsibility Principle
- [ ] Open/Closed Principle
- [ ] Liskov Substitution Principle
- [ ] Interface Segregation Principle
- [ ] Dependency Inversion Principle
- [ ] Composition over inheritance
- [ ] Coupling
- [ ] Cohesion
- [ ] DRY
- [ ] KISS
- [ ] YAGNI
- [ ] Law of Demeter
- [ ] Tell Don't Ask

## 1.7 Keywords & Modifiers
- [ ] `public`
- [ ] `protected`
- [ ] package-private/default
- [ ] `private`
- [ ] `final`
- [ ] `static`
- [ ] `abstract`
- [ ] `default`
- [ ] `synchronized`
- [ ] `volatile`
- [ ] `transient`
- [ ] `native`
- [ ] `strictfp`
- [ ] `sealed`
- [ ] `non-sealed`
- [ ] `permits`

## 1.8 Other Language Features
- [ ] Nested classes
- [ ] Static nested classes
- [ ] Inner classes
- [ ] Local classes
- [ ] Anonymous classes
- [ ] Enums
- [ ] Annotations
- [ ] Varargs
- [ ] Pass-by-value
- [ ] Java reference semantics
- [ ] `==` vs `equals()`

## 1.9 Exercises
- [ ] Build an immutable value object
- [ ] Implement inheritance and runtime polymorphism
- [ ] Demonstrate constructor chaining
- [ ] Implement each SOLID principle with real code
- [ ] Debug a pass-by-value example

---

# Module 2 — Types, Strings & Immutability

## 2.1 Primitive Types
- [ ] `byte`
- [ ] `short`
- [ ] `int`
- [ ] `long`
- [ ] `float`
- [ ] `double`
- [ ] `char`
- [ ] `boolean`
- [ ] Numeric ranges
- [ ] Overflow
- [ ] Underflow
- [ ] Integer promotion
- [ ] Floating-point precision

## 2.2 Wrapper Classes
- [ ] `Integer`
- [ ] `Long`
- [ ] `Double`
- [ ] `Float`
- [ ] `Short`
- [ ] `Byte`
- [ ] `Character`
- [ ] `Boolean`
- [ ] Autoboxing
- [ ] Unboxing
- [ ] Wrapper caching
- [ ] `Integer` cache
- [ ] Null unboxing
- [ ] Performance implications

## 2.3 Strings
- [ ] String immutability
- [ ] String pool
- [ ] Heap vs string pool
- [ ] String literals
- [ ] `new String()`
- [ ] `String.intern()`
- [ ] String concatenation
- [ ] Compile-time concatenation
- [ ] Runtime concatenation
- [ ] `StringBuilder`
- [ ] `StringBuffer`
- [ ] `String` vs `StringBuilder` vs `StringBuffer`
- [ ] Unicode
- [ ] UTF-8
- [ ] Character/code-point concepts

## 2.4 Immutability
- [ ] What makes an object immutable
- [ ] Final class
- [ ] Final fields
- [ ] Constructor initialization
- [ ] Defensive copying
- [ ] Mutable field hazards
- [ ] Immutable collections
- [ ] Records and immutability limitations
- [ ] Thread-safety benefits

## 2.5 Object Contracts
- [ ] `equals()` contract
- [ ] Reflexive
- [ ] Symmetric
- [ ] Transitive
- [ ] Consistent
- [ ] Null comparison
- [ ] `hashCode()` contract
- [ ] Hash-based collections
- [ ] `Comparable`
- [ ] `compareTo()`
- [ ] Consistency between `equals()` and `compareTo()`

## 2.6 Exercises
- [ ] Implement an immutable `Employee`
- [ ] Demonstrate String pool behavior
- [ ] Demonstrate wrapper caching
- [ ] Implement correct `equals/hashCode`
- [ ] Debug a broken `compareTo()`

---

# Module 3 — Collections Framework

## 3.1 Collection Architecture
- [ ] `Iterable`
- [ ] `Iterator`
- [ ] `Collection`
- [ ] `List`
- [ ] `Set`
- [ ] `Queue`
- [ ] `Deque`
- [ ] `Map`
- [ ] Collection hierarchy
- [ ] Map hierarchy

## 3.2 ArrayList
- [ ] Internal array
- [ ] Size vs capacity
- [ ] Growth mechanism
- [ ] Resizing
- [ ] Random access
- [ ] Insert/delete complexity
- [ ] Memory overhead
- [ ] Capacity management

## 3.3 LinkedList
- [ ] Node structure
- [ ] Doubly linked list
- [ ] Traversal
- [ ] Insertion/deletion
- [ ] Random access cost
- [ ] Why LinkedList is often slower in practice

## 3.4 Vector & Stack
- [ ] Vector
- [ ] Synchronization overhead
- [ ] Legacy APIs
- [ ] Stack
- [ ] Why `Deque` is preferred

## 3.5 HashMap — Deep Dive
- [ ] Hashing
- [ ] Hash spreading
- [ ] Bucket calculation
- [ ] Capacity
- [ ] Default capacity
- [ ] Load factor
- [ ] Threshold
- [ ] Collisions
- [ ] Node structure
- [ ] Linked-list bins
- [ ] Tree bins
- [ ] Treeification threshold
- [ ] Untreeification
- [ ] Minimum treeify capacity
- [ ] Resize
- [ ] Rehashing
- [ ] Power-of-two capacity
- [ ] Mutable keys
- [ ] `equals/hashCode`
- [ ] Iteration behavior
- [ ] Java 7 vs Java 8+ differences
- [ ] Complexity
- [ ] Hash collision considerations

## 3.6 ConcurrentHashMap
- [ ] Java 7 segmented architecture
- [ ] Java 8+ architecture
- [ ] CAS
- [ ] Volatile fields
- [ ] Synchronized bins
- [ ] Concurrent reads
- [ ] Concurrent updates
- [ ] Resizing
- [ ] Weakly consistent iteration
- [ ] Why null keys/values are not allowed
- [ ] Atomic compound operations

## 3.7 Other Maps
- [ ] LinkedHashMap
- [ ] Access-order
- [ ] Insertion-order
- [ ] LRU cache implementation
- [ ] TreeMap
- [ ] Red-black tree
- [ ] WeakHashMap
- [ ] IdentityHashMap
- [ ] EnumMap
- [ ] Immutable maps

## 3.8 Sets
- [ ] HashSet
- [ ] LinkedHashSet
- [ ] TreeSet
- [ ] EnumSet
- [ ] Ordering behavior
- [ ] Uniqueness semantics

## 3.9 Queues
- [ ] Queue
- [ ] Deque
- [ ] ArrayDeque
- [ ] PriorityQueue
- [ ] Heap concepts
- [ ] BlockingQueue
- [ ] ArrayBlockingQueue
- [ ] LinkedBlockingQueue
- [ ] PriorityBlockingQueue
- [ ] DelayQueue
- [ ] SynchronousQueue

## 3.10 Concurrent Collections
- [ ] CopyOnWriteArrayList
- [ ] CopyOnWriteArraySet
- [ ] ConcurrentLinkedQueue
- [ ] ConcurrentLinkedDeque
- [ ] ConcurrentHashMap

## 3.11 Iteration & Sorting
- [ ] Iterator
- [ ] ListIterator
- [ ] Fail-fast behavior
- [ ] Weakly consistent iterators
- [ ] Snapshot iterators
- [ ] Comparable
- [ ] Comparator
- [ ] Comparator chaining
- [ ] Null handling
- [ ] Stable sorting
- [ ] Collections utility class
- [ ] Immutable/unmodifiable collections

## 3.12 Complexity
- [ ] Time complexity of major collections
- [ ] Space complexity
- [ ] ArrayList vs LinkedList
- [ ] HashMap vs TreeMap
- [ ] HashSet vs TreeSet
- [ ] ArrayDeque vs LinkedList
- [ ] ConcurrentHashMap vs synchronized Map

## 3.13 Exercises
- [ ] Implement a simplified HashMap
- [ ] Build an LRU cache
- [ ] Build a priority queue based problem
- [ ] Compare collection performance
- [ ] Debug mutable-key HashMap failure

---

# Module 4 — Exceptions & Error Handling

## 4.1 Hierarchy
- [ ] `Throwable`
- [ ] `Error`
- [ ] `Exception`
- [ ] RuntimeException
- [ ] Checked exceptions
- [ ] Unchecked exceptions

## 4.2 Handling
- [ ] `try`
- [ ] `catch`
- [ ] `finally`
- [ ] `throw`
- [ ] `throws`
- [ ] Exception propagation
- [ ] Stack traces
- [ ] Multi-catch
- [ ] Nested exceptions
- [ ] Exception chaining

## 4.3 Try-With-Resources
- [ ] `AutoCloseable`
- [ ] `Closeable`
- [ ] Resource closing order
- [ ] Suppressed exceptions
- [ ] Resource leak prevention

## 4.4 Custom Exceptions
- [ ] Creating custom checked exceptions
- [ ] Creating custom unchecked exceptions
- [ ] Exception naming
- [ ] Exception messages
- [ ] Exception wrapping
- [ ] Exception translation
- [ ] Propagate vs wrap

## 4.5 Gotchas
- [ ] `finally` overriding return
- [ ] `finally` overriding exceptions
- [ ] Swallowing exceptions
- [ ] Catching overly broad exceptions
- [ ] Using exceptions for normal control flow

## 4.6 Exercises
- [ ] Build custom exception hierarchy
- [ ] Implement resource-safe code
- [ ] Debug exception chaining
- [ ] Refactor bad exception handling

---

# Module 5 — Generics

## 5.1 Fundamentals
- [ ] Generic classes
- [ ] Generic interfaces
- [ ] Generic methods
- [ ] Type parameters
- [ ] Multiple type parameters
- [ ] Generic inheritance

## 5.2 Bounds
- [ ] Upper bounds
- [ ] Multiple bounds
- [ ] Lower bounds
- [ ] Bounded type parameters

## 5.3 Wildcards
- [ ] Unbounded wildcard
- [ ] `? extends`
- [ ] `? super`
- [ ] PECS
- [ ] Producer Extends
- [ ] Consumer Super

## 5.4 Type System Internals
- [ ] Type erasure
- [ ] Reifiable types
- [ ] Non-reifiable types
- [ ] Raw types
- [ ] Heap pollution
- [ ] Bridge methods
- [ ] Generic arrays
- [ ] Generic exceptions limitations
- [ ] Type inference
- [ ] Target typing
- [ ] `Class<T>`

## 5.5 Exercises
- [ ] Generic repository
- [ ] Generic utility methods
- [ ] PECS examples
- [ ] Debug heap pollution
- [ ] Explain type erasure at bytecode level

---

# Module 6 — Functional Java, Lambdas & Streams

## 6.1 Functional Interfaces
- [ ] Functional interface concept
- [ ] `@FunctionalInterface`
- [ ] Predicate
- [ ] Function
- [ ] Consumer
- [ ] Supplier
- [ ] UnaryOperator
- [ ] BinaryOperator
- [ ] BiFunction
- [ ] BiConsumer
- [ ] BiPredicate

## 6.2 Lambdas
- [ ] Lambda syntax
- [ ] Target typing
- [ ] Variable capture
- [ ] Effectively-final variables
- [ ] Lambda object/runtime behavior
- [ ] Method references
- [ ] Constructor references

## 6.3 Functional Composition
- [ ] `and`
- [ ] `or`
- [ ] `negate`
- [ ] `compose`
- [ ] `andThen`

## 6.4 Streams
- [ ] Stream creation
- [ ] Intermediate operations
- [ ] Terminal operations
- [ ] Lazy evaluation
- [ ] Pipeline execution
- [ ] `filter`
- [ ] `map`
- [ ] `flatMap`
- [ ] `distinct`
- [ ] `sorted`
- [ ] `peek`
- [ ] `limit`
- [ ] `skip`
- [ ] `takeWhile`
- [ ] `dropWhile`
- [ ] Primitive streams
- [ ] `mapToInt`
- [ ] `mapToLong`
- [ ] `mapToDouble`
- [ ] `reduce`
- [ ] `collect`
- [ ] `groupingBy`
- [ ] `partitioningBy`
- [ ] `joining`
- [ ] `toMap`
- [ ] Custom collectors
- [ ] Stateful operations
- [ ] Side effects
- [ ] Stream reuse limitations

## 6.5 Parallel Streams
- [ ] Parallel stream architecture
- [ ] ForkJoinPool relationship
- [ ] Ordering
- [ ] Thread safety
- [ ] Performance trade-offs
- [ ] When not to use parallel streams

## 6.6 Optional
- [ ] `Optional`
- [ ] `of`
- [ ] `ofNullable`
- [ ] `empty`
- [ ] `map`
- [ ] `flatMap`
- [ ] `filter`
- [ ] `orElse`
- [ ] `orElseGet`
- [ ] `orElseThrow`
- [ ] Optional anti-patterns
- [ ] Optional in API design

## 6.7 Exercises
- [ ] Stream-based data transformation
- [ ] Grouping/partitioning problems
- [ ] Custom Collector
- [ ] Optional-based API
- [ ] Parallel stream benchmark

---

# Module 7 — Java 8 to Java 25 Evolution

> Mark every feature as **Final**, **Preview**, or **Incubator** where applicable.

## Java 8
- [ ] Lambdas
- [ ] Functional interfaces
- [ ] Streams
- [ ] Optional
- [ ] Method references
- [ ] Default/static interface methods
- [ ] CompletableFuture
- [ ] Date/Time API

## Java 9
- [ ] JPMS/modules
- [ ] Module descriptors
- [ ] `requires`
- [ ] `exports`
- [ ] `opens`
- [ ] Private interface methods
- [ ] Collection factory methods

## Java 10
- [ ] `var`
- [ ] Local variable type inference

## Java 11
- [ ] HTTP Client
- [ ] String utility methods
- [ ] `var` in lambda parameters
- [ ] LTS concepts

## Java 12–13
- [ ] Switch expression evolution
- [ ] Text blocks evolution

## Java 14–16
- [ ] Switch expressions
- [ ] Helpful NullPointerException messages
- [ ] Records
- [ ] Pattern matching for `instanceof`
- [ ] Text blocks

## Java 17
- [ ] Sealed classes
- [ ] Enhanced pseudo-random generators
- [ ] Strong encapsulation
- [ ] LTS

## Java 18–20
- [ ] UTF-8 default
- [ ] Simple web server
- [ ] Virtual threads evolution
- [ ] Pattern matching evolution
- [ ] Record patterns evolution
- [ ] Foreign Function & Memory evolution
- [ ] Structured concurrency evolution

## Java 21
- [ ] Virtual threads
- [ ] Pattern matching for switch
- [ ] Record patterns
- [ ] Sequenced collections
- [ ] Structured concurrency status
- [ ] Scoped values status
- [ ] String templates status/history

## Java 22–25
- [ ] Stream Gatherers
- [ ] Scoped Values
- [ ] Unnamed variables/patterns
- [ ] Foreign Function & Memory API
- [ ] Continued virtual-thread improvements
- [ ] Continued pattern matching improvements
- [ ] New collection/stream APIs
- [ ] Java 25 LTS

---

# Module 8 — Concurrency & Multithreading

## 8.1 Thread Fundamentals
- [ ] Process vs thread
- [ ] Thread lifecycle
- [ ] Thread states
- [ ] `Thread`
- [ ] `Runnable`
- [ ] `Callable`
- [ ] `Future`

## 8.2 Java Memory Model
- [ ] Visibility
- [ ] Atomicity
- [ ] Ordering
- [ ] Happens-before
- [ ] Reordering
- [ ] Data races
- [ ] Safe publication
- [ ] Final-field semantics

## 8.3 Synchronization
- [ ] `synchronized`
- [ ] Intrinsic locks
- [ ] Object monitors
- [ ] Class locks
- [ ] Method vs block synchronization
- [ ] `volatile`
- [ ] Atomic classes
- [ ] CAS
- [ ] ABA problem

## 8.4 Coordination
- [ ] `wait`
- [ ] `notify`
- [ ] `notifyAll`
- [ ] Spurious wakeups
- [ ] Condition predicates

## 8.5 Locks
- [ ] ReentrantLock
- [ ] Fair vs unfair locks
- [ ] `tryLock`
- [ ] Interruptible lock acquisition
- [ ] ReadWriteLock
- [ ] StampedLock
- [ ] Optimistic reads

## 8.6 Synchronizers
- [ ] CountDownLatch
- [ ] CyclicBarrier
- [ ] Semaphore
- [ ] Phaser
- [ ] Exchanger

## 8.7 Executors
- [ ] Executor
- [ ] ExecutorService
- [ ] ScheduledExecutorService
- [ ] ThreadPoolExecutor
- [ ] Core pool size
- [ ] Maximum pool size
- [ ] Keep-alive time
- [ ] Queue types
- [ ] Rejection policies
- [ ] ThreadFactory
- [ ] Shutdown
- [ ] `shutdownNow`

## 8.8 CompletableFuture
- [ ] `supplyAsync`
- [ ] `runAsync`
- [ ] `thenApply`
- [ ] `thenCompose`
- [ ] `thenCombine`
- [ ] `allOf`
- [ ] `anyOf`
- [ ] Exception handling
- [ ] Timeouts
- [ ] Cancellation
- [ ] Custom executors

## 8.9 ForkJoinPool
- [ ] Work stealing
- [ ] Fork/join tasks
- [ ] Common pool
- [ ] Parallel streams

## 8.10 Concurrency Problems
- [ ] Race condition
- [ ] Deadlock
- [ ] Livelock
- [ ] Starvation
- [ ] Lock contention
- [ ] Thread exhaustion
- [ ] Detection
- [ ] Prevention

## 8.11 Modern Concurrency
- [ ] Platform threads
- [ ] Virtual threads
- [ ] Thread-per-request model
- [ ] Blocking I/O with virtual threads
- [ ] Pinning considerations
- [ ] Virtual-thread limitations
- [ ] Structured concurrency
- [ ] Scoped values

## 8.12 Exercises
- [ ] Thread-safe counter
- [ ] Producer/consumer
- [ ] Bounded buffer
- [ ] Rate limiter
- [ ] Thread-safe cache
- [ ] Deadlock demonstration
- [ ] Deadlock prevention
- [ ] CompletableFuture pipeline
- [ ] Virtual-thread benchmark

---

# Module 9 — JVM Internals

## 9.1 JVM Architecture
- [ ] Class loader subsystem
- [ ] Runtime data areas
- [ ] Execution engine
- [ ] JNI
- [ ] Native libraries

## 9.2 Class Loading
- [ ] Bootstrap ClassLoader
- [ ] Platform ClassLoader
- [ ] Application ClassLoader
- [ ] Custom ClassLoader
- [ ] Parent delegation
- [ ] Loading
- [ ] Linking
- [ ] Verification
- [ ] Preparation
- [ ] Resolution
- [ ] Initialization
- [ ] Class initialization triggers

## 9.3 Runtime Memory
- [ ] Heap
- [ ] Thread stack
- [ ] PC register
- [ ] Native method stack
- [ ] Metaspace
- [ ] Code cache
- [ ] Direct memory

## 9.4 Heap
- [ ] Eden
- [ ] Survivor spaces
- [ ] Old generation
- [ ] Object promotion
- [ ] Allocation
- [ ] TLAB concepts

## 9.5 Garbage Collection
- [ ] Reachability
- [ ] GC roots
- [ ] Minor/young collection
- [ ] Major/old collection concepts
- [ ] Full GC
- [ ] Serial GC
- [ ] Parallel GC
- [ ] G1 GC
- [ ] ZGC
- [ ] Shenandoah
- [ ] Collector selection

## 9.6 JVM Tuning
- [ ] `-Xms`
- [ ] `-Xmx`
- [ ] `-Xss`
- [ ] `-XX:MaxGCPauseMillis`
- [ ] `-XX:+UseG1GC`
- [ ] GC logging
- [ ] Heap sizing
- [ ] Pause-time goals
- [ ] Throughput vs latency

## 9.7 JIT
- [ ] Interpreter
- [ ] C1
- [ ] C2
- [ ] Tiered compilation
- [ ] JIT compilation
- [ ] Inlining
- [ ] Escape analysis
- [ ] Scalar replacement
- [ ] Deoptimization
- [ ] Code cache

## 9.8 Diagnostics
- [ ] `jps`
- [ ] `jstack`
- [ ] `jmap`
- [ ] `jstat`
- [ ] `jcmd`
- [ ] JFR
- [ ] JMC
- [ ] VisualVM
- [ ] Eclipse MAT

## 9.9 Troubleshooting
- [ ] Heap dump
- [ ] Thread dump
- [ ] GC analysis
- [ ] CPU profiling
- [ ] Memory profiling
- [ ] OOM: Java heap space
- [ ] OOM: Metaspace
- [ ] OOM: Direct buffer memory
- [ ] Unable to create native thread
- [ ] StackOverflowError

## 9.10 Exercises
- [ ] Analyze a thread dump
- [ ] Analyze a heap dump
- [ ] Diagnose high CPU
- [ ] Diagnose GC pauses
- [ ] Diagnose OOM
- [ ] Compare GC collectors

---

# Module 10 — Advanced OOP & Design Patterns

## 10.1 Advanced OOP
- [ ] Composition over inheritance
- [ ] Dependency inversion
- [ ] Coupling/cohesion
- [ ] Immutable design
- [ ] Value objects
- [ ] Domain objects
- [ ] Interface-driven design
- [ ] API design

## 10.2 Creational Patterns
- [ ] Singleton
  - [ ] Eager
  - [ ] Lazy
  - [ ] Double-checked locking
  - [ ] Bill Pugh
  - [ ] Enum
- [ ] Factory Method
- [ ] Abstract Factory
- [ ] Builder
- [ ] Prototype

## 10.3 Structural Patterns
- [ ] Adapter
- [ ] Bridge
- [ ] Composite
- [ ] Decorator
- [ ] Facade
- [ ] Flyweight
- [ ] Proxy
- [ ] Static proxy
- [ ] Dynamic proxy

## 10.4 Behavioral Patterns
- [ ] Chain of Responsibility
- [ ] Command
- [ ] Iterator
- [ ] Mediator
- [ ] Memento
- [ ] Observer
- [ ] State
- [ ] Strategy
- [ ] Template Method
- [ ] Visitor

## 10.5 Pattern Mastery
For every pattern:
- [ ] Problem
- [ ] Naive solution
- [ ] Pattern solution
- [ ] Implementation
- [ ] Trade-offs
- [ ] Thread-safety considerations
- [ ] Performance
- [ ] Real-world Java usage
- [ ] Anti-patterns

---

# Module 11 — I/O, NIO & Serialization

## 11.1 Classic I/O
- [ ] InputStream
- [ ] OutputStream
- [ ] Reader
- [ ] Writer
- [ ] FileInputStream
- [ ] FileOutputStream
- [ ] BufferedInputStream
- [ ] BufferedOutputStream
- [ ] BufferedReader
- [ ] BufferedWriter
- [ ] DataInputStream
- [ ] DataOutputStream
- [ ] Performance implications

## 11.2 NIO
- [ ] Buffer
- [ ] ByteBuffer
- [ ] Channel
- [ ] FileChannel
- [ ] Selector
- [ ] Charset
- [ ] Blocking vs non-blocking I/O

## 11.3 NIO.2
- [ ] Path
- [ ] Paths
- [ ] Files
- [ ] FileSystem
- [ ] DirectoryStream
- [ ] File attributes
- [ ] Symbolic links
- [ ] WatchService
- [ ] File traversal
- [ ] Atomic file operations

## 11.4 Serialization
- [ ] Serializable
- [ ] Externalizable
- [ ] `serialVersionUID`
- [ ] `transient`
- [ ] `readObject`
- [ ] `writeObject`
- [ ] `readResolve`
- [ ] `writeReplace`
- [ ] Serialization compatibility
- [ ] Serialization vulnerabilities
- [ ] Why native Java serialization is often avoided

## 11.5 Exercises
- [ ] File processing utility
- [ ] NIO directory watcher
- [ ] File copy benchmark
- [ ] Custom serialization
- [ ] Diagnose serialization compatibility issue

---

# Module 12 — Reflection, Annotations & Metaprogramming

## 12.1 Reflection
- [ ] `Class`
- [ ] Fields
- [ ] Methods
- [ ] Constructors
- [ ] Modifiers
- [ ] Generic type reflection
- [ ] Dynamic invocation
- [ ] Access checks
- [ ] `setAccessible`
- [ ] Reflection performance
- [ ] Encapsulation implications

## 12.2 Annotations
- [ ] Built-in annotations
- [ ] Custom annotations
- [ ] `@Target`
- [ ] `@Retention`
- [ ] `@Documented`
- [ ] `@Inherited`
- [ ] `@Repeatable`
- [ ] Runtime annotation processing
- [ ] Compile-time annotation processing

## 12.3 Method Handles
- [ ] MethodHandles
- [ ] MethodHandle lookup
- [ ] MethodHandle invocation
- [ ] VarHandle
- [ ] Comparison with reflection

## 12.4 Dynamic Proxies
- [ ] `Proxy`
- [ ] InvocationHandler
- [ ] Proxy generation
- [ ] JDK dynamic proxies
- [ ] Proxy limitations
- [ ] Relationship with AOP

## 12.5 Exercises
- [ ] Build a mini dependency injector
- [ ] Build custom annotation processor
- [ ] Implement dynamic proxy logging
- [ ] Compare reflection and MethodHandles

---

# Module 13 — JDBC & Database Connectivity

## 13.1 JDBC Architecture
- [ ] JDBC API
- [ ] Driver
- [ ] DriverManager
- [ ] DataSource
- [ ] Connection
- [ ] Statement
- [ ] PreparedStatement
- [ ] CallableStatement
- [ ] ResultSet

## 13.2 ResultSet
- [ ] Forward-only
- [ ] Scrollable
- [ ] Read-only
- [ ] Updatable
- [ ] ResultSetMetaData
- [ ] DatabaseMetaData

## 13.3 SQL Safety
- [ ] SQL injection
- [ ] PreparedStatement
- [ ] Parameter binding
- [ ] Input validation

## 13.4 Transactions
- [ ] ACID
- [ ] Auto-commit
- [ ] Commit
- [ ] Rollback
- [ ] Savepoints
- [ ] Isolation levels
- [ ] Dirty read
- [ ] Non-repeatable read
- [ ] Phantom read
- [ ] Lost update

## 13.5 Performance
- [ ] Batch processing
- [ ] Generated keys
- [ ] Prepared statement reuse
- [ ] Connection pooling
- [ ] HikariCP concepts
- [ ] Pool sizing
- [ ] Connection leak detection
- [ ] Timeout configuration

## 13.6 Exercises
- [ ] JDBC CRUD application
- [ ] Transaction example
- [ ] Batch insert benchmark
- [ ] Connection pool configuration
- [ ] SQL injection demonstration and fix

---

# Module 14 — Networking & HTTP

## 14.1 Networking Fundamentals
- [ ] IP address
- [ ] Port
- [ ] DNS
- [ ] TCP
- [ ] UDP
- [ ] TCP handshake
- [ ] Connection lifecycle
- [ ] Socket
- [ ] ServerSocket
- [ ] DatagramSocket
- [ ] InetAddress

## 14.2 HTTP
- [ ] HTTP request
- [ ] HTTP response
- [ ] Methods
- [ ] Headers
- [ ] Status codes
- [ ] Cookies
- [ ] Content types
- [ ] HTTP/1.1
- [ ] HTTP/2 concepts
- [ ] HTTPS
- [ ] Keep-alive
- [ ] Connection pooling
- [ ] Timeouts

## 14.3 Java HTTP Client
- [ ] HttpClient
- [ ] HttpRequest
- [ ] HttpResponse
- [ ] Synchronous requests
- [ ] Asynchronous requests
- [ ] CompletableFuture integration
- [ ] Redirect handling
- [ ] Timeout handling
- [ ] HTTP versions

## 14.4 Exercises
- [ ] TCP echo server
- [ ] UDP client/server
- [ ] HTTP client
- [ ] Async HTTP client
- [ ] Timeout/retry handling

---

# Module 15 — Java Security

## 15.1 Cryptographic Fundamentals
- [ ] Encoding vs encryption vs hashing
- [ ] Hash functions
- [ ] SHA-256
- [ ] HMAC
- [ ] Symmetric encryption
- [ ] AES
- [ ] Asymmetric encryption
- [ ] RSA
- [ ] Key pairs
- [ ] Digital signatures
- [ ] SecureRandom

## 15.2 JCA/JCE
- [ ] MessageDigest
- [ ] Cipher
- [ ] Mac
- [ ] Signature
- [ ] KeyGenerator
- [ ] KeyPairGenerator
- [ ] SecureRandom

## 15.3 Key Management
- [ ] KeyStore
- [ ] TrustStore
- [ ] Certificates
- [ ] X.509
- [ ] Certificate chain
- [ ] Public/private key management

## 15.4 TLS
- [ ] TLS fundamentals
- [ ] SSLContext
- [ ] TLS handshake
- [ ] Certificate validation
- [ ] Trust managers
- [ ] Key managers
- [ ] HTTPS configuration

## 15.5 Security Practices
- [ ] Password hashing concepts
- [ ] Random token generation
- [ ] Secret management
- [ ] Avoiding weak algorithms
- [ ] Avoiding hard-coded secrets
- [ ] Common cryptographic mistakes

---

# Module 16 — Modern Java Application Architecture

## 16.1 Dependency Injection
- [ ] Dependency inversion
- [ ] Constructor injection
- [ ] Setter injection
- [ ] Field injection
- [ ] Dependency lifecycle
- [ ] Composition roots

## 16.2 Layered Architecture
- [ ] Controller layer
- [ ] Service layer
- [ ] Repository layer
- [ ] Database
- [ ] Separation of concerns
- [ ] Transaction boundaries

## 16.3 DTO & Domain Design
- [ ] Entity vs DTO
- [ ] Request DTO
- [ ] Response DTO
- [ ] Mapping
- [ ] Value objects
- [ ] Records
- [ ] Immutable DTOs

## 16.4 Configuration
- [ ] Externalized configuration
- [ ] Environment variables
- [ ] Profiles
- [ ] Configuration validation
- [ ] Secrets
- [ ] Configuration precedence

## 16.5 Logging
- [ ] Logging levels
- [ ] SLF4J concepts
- [ ] Logback concepts
- [ ] Structured logging
- [ ] Correlation IDs
- [ ] Sensitive-data logging
- [ ] Logging performance

## 16.6 Validation & Defensive APIs
- [ ] Input validation
- [ ] Boundary validation
- [ ] Fail-fast design
- [ ] Null handling
- [ ] Error responses
- [ ] Timeouts
- [ ] Idempotency
- [ ] Retries

---

# Module 17 — Testing & Code Quality

## 17.1 Testing Fundamentals
- [ ] Unit testing
- [ ] Integration testing
- [ ] End-to-end testing
- [ ] Test isolation
- [ ] Test pyramid
- [ ] Deterministic tests

## 17.2 Test Doubles
- [ ] Stub
- [ ] Mock
- [ ] Spy
- [ ] Fake
- [ ] Dummy

## 17.3 Test Design
- [ ] Arrange/Act/Assert
- [ ] Assertions
- [ ] Parameterized tests
- [ ] Exception testing
- [ ] Edge-case testing
- [ ] Boundary testing
- [ ] Concurrency testing

## 17.4 Code Quality
- [ ] Clean code
- [ ] Code smells
- [ ] Refactoring
- [ ] Static analysis
- [ ] Code coverage
- [ ] Maintainability
- [ ] Readability
- [ ] API design
- [ ] Backward compatibility

## 17.5 Exercises
- [ ] Test a service layer
- [ ] Mock external dependencies
- [ ] Write parameterized tests
- [ ] Refactor legacy Java code
- [ ] Increase coverage without meaningless tests

---

# Module 18 — Production Java & Troubleshooting

## 18.1 Memory Leaks
- [ ] Static collection leaks
- [ ] ThreadLocal leaks
- [ ] Unclosed resources
- [ ] Database connection leaks
- [ ] Listener/callback leaks
- [ ] Cache growth
- [ ] ClassLoader leaks
- [ ] Object retention

## 18.2 Performance Problems
- [ ] High CPU
- [ ] High memory
- [ ] GC pressure
- [ ] Long GC pauses
- [ ] Lock contention
- [ ] Thread pool exhaustion
- [ ] Connection pool exhaustion
- [ ] Slow I/O
- [ ] Excessive object allocation

## 18.3 Production Failure Scenarios
- [ ] OutOfMemoryError
- [ ] StackOverflowError
- [ ] Deadlock
- [ ] High CPU
- [ ] Slow API
- [ ] GC storm
- [ ] Thread starvation
- [ ] Connection exhaustion
- [ ] File descriptor/resource exhaustion

## 18.4 Diagnostic Workflow
- [ ] Identify symptom
- [ ] Collect metrics
- [ ] Check logs
- [ ] Capture thread dump
- [ ] Capture heap dump
- [ ] Inspect GC behavior
- [ ] Profile CPU
- [ ] Profile memory
- [ ] Identify root cause
- [ ] Apply fix
- [ ] Validate fix
- [ ] Prevent recurrence

## 18.5 Defensive Programming
- [ ] Fail-fast validation
- [ ] Defensive copying
- [ ] Immutability
- [ ] Resource management
- [ ] Timeouts
- [ ] Retries
- [ ] Circuit-breaking concepts
- [ ] Idempotency
- [ ] Safe concurrency
- [ ] Avoid deep cloning pitfalls

## 18.6 Production Exercises
- [ ] Diagnose memory leak
- [ ] Diagnose deadlock
- [ ] Diagnose high CPU
- [ ] Diagnose thread starvation
- [ ] Diagnose GC pause
- [ ] Diagnose connection pool exhaustion
- [ ] Diagnose slow database access
- [ ] Diagnose slow HTTP dependency
- [ ] Build a production troubleshooting runbook

---

# Mastery Levels

For every topic, progress through these levels.

## Level 1 — Concept
- [ ] Define it
- [ ] Explain why it exists
- [ ] Know the basic API/syntax

## Level 2 — Implementation
- [ ] Write code without copying
- [ ] Explain the code line by line
- [ ] Handle normal edge cases

## Level 3 — Internals
- [ ] Explain how it works internally
- [ ] Understand memory/runtime behavior
- [ ] Understand relevant JVM behavior

## Level 4 — Performance
- [ ] Know time complexity
- [ ] Know space complexity
- [ ] Identify bottlenecks
- [ ] Understand trade-offs

## Level 5 — Production
- [ ] Know when to use it
- [ ] Know when not to use it
- [ ] Recognize failure modes
- [ ] Debug real-world problems

## Level 6 — Interview
- [ ] Explain without notes
- [ ] Answer "why" questions
- [ ] Answer comparison questions
- [ ] Solve coding problems
- [ ] Explain internals on a whiteboard

## Level 7 — Design
- [ ] Combine multiple concepts
- [ ] Design maintainable solutions
- [ ] Evaluate trade-offs
- [ ] Review/refactor other people's code

---

# Recommended Priority

## Tier 1 — Must Master
- [ ] OOP
- [ ] Collections
- [ ] `equals/hashCode`
- [ ] Generics
- [ ] Exceptions
- [ ] Lambdas
- [ ] Streams
- [ ] Concurrency
- [ ] JVM basics
- [ ] JDBC
- [ ] Design principles
- [ ] Common design patterns

## Tier 2 — Strong Professional Knowledge
- [ ] Advanced JVM
- [ ] NIO
- [ ] Reflection
- [ ] Annotations
- [ ] Networking
- [ ] CompletableFuture
- [ ] Virtual threads
- [ ] Security
- [ ] Testing
- [ ] Production troubleshooting

## Tier 3 — Specialist/Advanced
- [ ] ClassLoader internals
- [ ] JIT internals
- [ ] GC tuning
- [ ] MethodHandles
- [ ] VarHandle
- [ ] Custom annotation processors
- [ ] Advanced NIO selectors
- [ ] Advanced JVM profiling
- [ ] JVM performance engineering

---

# Final Outcome

After completing this curriculum, you should be able to:

- [ ] Write clean, idiomatic modern Java
- [ ] Explain Java OOP deeply
- [ ] Select the right collection for a problem
- [ ] Explain HashMap and ConcurrentHashMap internals
- [ ] Design thread-safe code
- [ ] Explain the Java Memory Model
- [ ] Use CompletableFuture and modern concurrency correctly
- [ ] Understand virtual threads
- [ ] Explain JVM memory and garbage collection
- [ ] Analyze heap dumps and thread dumps
- [ ] Understand JIT compilation at a practical level
- [ ] Apply SOLID and GoF patterns appropriately
- [ ] Write robust JDBC code
- [ ] Build efficient HTTP clients
- [ ] Understand Java security fundamentals
- [ ] Use reflection and annotations appropriately
- [ ] Write testable production-quality Java
- [ ] Diagnose common production Java failures
- [ ] Explain Java internals confidently in interviews
- [ ] Build a strong foundation for Spring/Spring Boot and backend development

---

# Suggested Study Order

```text
Java Fundamentals
       ↓
OOP + SOLID
       ↓
Types + Strings + Immutability
       ↓
Collections
       ↓
Exceptions
       ↓
Generics
       ↓
Lambdas + Streams + Optional
       ↓
Java 8–25 Features
       ↓
Concurrency
       ↓
JVM Internals
       ↓
Design Patterns
       ↓
I/O + NIO
       ↓
Reflection + Annotations
       ↓
JDBC
       ↓
Networking + HTTP
       ↓
Security
       ↓
Application Architecture
       ↓
Testing
       ↓
Production Troubleshooting
```

# Rule for Deep Mastery

**Do not mark a topic complete merely because you have read it.**

Mark it complete only when you can:

> **Explain it → Implement it → Explain its internals → Handle edge cases → Discuss trade-offs → Debug it → Use it in a production scenario.**
