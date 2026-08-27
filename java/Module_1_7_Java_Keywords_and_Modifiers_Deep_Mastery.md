# Module 1.7 — Java Keywords & Modifiers
## Deep Mastery Guide

> **Goal:** Master Java access control, class/member modifiers, concurrency-related modifiers, serialization modifiers, native code boundaries, and modern sealed-type modifiers.

---

# Mastery Cycle

For **every keyword/modifier**, complete:

1. [ ] What is it?
2. [ ] Why does Java have it?
3. [ ] Syntax and API
4. [ ] Basic example
5. [ ] Internal working
6. [ ] Memory/runtime behavior
7. [ ] Edge cases
8. [ ] Common mistakes
9. [ ] Performance implications
10. [ ] Production use cases
11. [ ] Interview questions
12. [ ] Coding exercises
13. [ ] Advanced follow-ups

## Completion Standard

Mark a topic complete only when you can:

> **Explain → Implement → Explain compiler/JVM behavior → Handle edge cases → Discuss trade-offs → Debug it → Use it in production**

---

# 1.7.1 Modifier Classification

Java modifiers can be grouped conceptually:

```text
Access Control
├── public
├── protected
├── package-private
└── private

Class / Member Design
├── final
├── static
├── abstract
├── default
├── sealed
├── non-sealed
└── permits

Concurrency / Memory Visibility
├── synchronized
└── volatile

Serialization
└── transient

Native Boundary
└── native

Floating-Point Semantics
└── strictfp
```

Important distinction:

> Some items are technically **keywords**, while others are language modifiers or modifier-related constructs. `package-private` is also not a keyword; it means that no access modifier is declared.

---

# 1.7.2 `public`

## 1. What is it?

`public` makes a top-level type or accessible member available wherever its enclosing type/package/module is accessible.

Examples:

```java
public class User {
    public String getName() {
        return "Alex";
    }
}
```

## 2. Why does Java have it?

It defines the widest ordinary Java access boundary.

Use it when an API is intentionally exposed to consumers.

## 3. Syntax

```java
public class User {
}

public void process() {
}

public static final int MAX = 100;
```

## 4. Basic Example

```java
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }
}
```

Another class can call:

```java
Calculator calculator = new Calculator();

calculator.add(10, 20);
```

## 5. Internal Working

Accessibility is checked by the Java compiler and enforced by the JVM's access-control mechanisms.

With JPMS, public does not automatically mean globally accessible from every module: module readability and package exports also matter.

```text
public
   +
module/package rules
   ↓
Actual accessibility
```

## 6. Edge Cases

- [ ] Public top-level class normally requires a matching source filename.
- [ ] A public member can still be inaccessible through an inaccessible enclosing type.
- [ ] A public package does not necessarily mean a public API.
- [ ] JPMS `exports` affects cross-module access.
- [ ] Reflection has additional access-control rules.

## 7. Common Mistakes

- [ ] Making every field public
- [ ] Treating public as default
- [ ] Exposing implementation details
- [ ] Ignoring module boundaries

## 8. Production Use

Use `public` primarily for deliberate API boundaries.

Prefer:

```java
private field
        ↓
public behavior
```

over public mutable state.

## 9. Interview Questions

- [ ] What does public mean?
- [ ] Can a public class have private methods?
- [ ] Does public guarantee accessibility across Java modules?

---

# 1.7.3 `protected`

## 1. What is it?

`protected` allows access:

1. From classes in the same package.
2. From subclasses in other packages, subject to Java's protected-access rules.

Example:

```java
class BaseService {

    protected void execute() {
    }
}
```

## 2. Why?

It supports controlled inheritance-based extension while also permitting package-level access.

## 3. Critical Cross-Package Rule

A subclass in another package can access inherited protected behavior through the subclass relationship.

Do not simplify the rule to:

> "protected means accessible from any subclass object anywhere."

The exact access rules matter.

## 4. Basic Example

```java
class Animal {

    protected void move() {
        System.out.println("Moving");
    }
}

class Dog extends Animal {

    void run() {
        move();
    }
}
```

## 5. Internal Working

The compiler checks the protected access relationship.

The JVM enforces member access at runtime.

## 6. Edge Cases

- [ ] Same-package access
- [ ] Cross-package subclass access
- [ ] Static protected members
- [ ] Protected constructors
- [ ] Field access through references
- [ ] Inheritance vs composition

## 7. Common Mistakes

- [ ] Using protected as "private for subclasses"
- [ ] Exposing too much inheritance surface
- [ ] Using protected fields instead of protected methods
- [ ] Creating inheritance solely to obtain protected access

## 8. Production Guidance

Prefer private state and protected extension hooks when inheritance is genuinely part of the design.

```java
private State state;

protected void beforeExecute() {
}
```

is generally easier to evolve than:

```java
protected State state;
```

---

# 1.7.4 Package-Private / Default Access

## 1. What is it?

When no access modifier is specified, a top-level type/member has **package-private** access.

```java
class User {
    String name;
}
```

The term "default access" is commonly used, but it should not be confused with an interface `default` method.

## 2. Why?

It allows components inside a package to cooperate without exposing implementation details outside the package.

## 3. Basic Example

```java
class InternalValidator {

    boolean valid(String value) {
        return value != null;
    }
}
```

Only code in the same package can directly access the class.

## 4. Production Use

Excellent for package-level implementation details:

```text
public API
    ↓
package-private implementation
    ↓
private internals
```

## 5. Common Mistakes

- [ ] Assuming package-private means module-private
- [ ] Putting unrelated classes in one package just to access internals
- [ ] Confusing package-private with `default` interface methods

## 6. Interview Questions

- [ ] What happens when no access modifier is specified?
- [ ] Can a package-private top-level class be accessed from another package?
- [ ] Is package-private the same as protected?

---

# 1.7.5 `private`

## 1. What is it?

`private` restricts direct member access to the declaring top-level class.

Example:

```java
class BankAccount {

    private BigDecimal balance;
}
```

## 2. Why?

It supports encapsulation and implementation hiding.

## 3. Basic Example

```java
class BankAccount {

    private BigDecimal balance;

    void deposit(BigDecimal amount) {
        // validate and modify
    }
}
```

## 4. Internal Working

The compiler enforces normal Java source-level access rules.

The JVM also performs access checks.

Reflection and method handles have separate access mechanisms and should not be treated as ordinary source-level access.

## 5. Edge Cases

- [ ] Nested classes
- [ ] Private constructors
- [ ] Private static members
- [ ] Reflection
- [ ] Records
- [ ] Serialization frameworks

## 6. Production Guidance

Prefer private fields:

```java
private final CustomerId id;
```

and expose meaningful behavior:

```java
customer.changeAddress(address);
```

rather than:

```java
customer.setAddress(address);
```

when domain invariants matter.

---

# 1.7.6 Access Modifier Comparison

| Modifier | Same Class | Same Package | Subclass Other Package | Unrelated Other Package |
|---|---:|---:|---:|---:|
| `public` | Yes | Yes | Yes* | Yes* |
| `protected` | Yes | Yes | Yes** | No |
| package-private | Yes | Yes | No*** | No |
| `private` | Yes | No | No | No |

`*` Subject to enclosing type and JPMS/module accessibility.

`**` Cross-package protected access is subject to Java's specific protected-access rules.

`***` A subclass in the same package can access package-private members because it is in the same package; inheritance itself does not grant package-private access across packages.

---

# 1.7.7 `final`

## 1. What is it?

`final` prevents further modification in different contexts.

### Final variable

Cannot be assigned again after definite initialization.

```java
final int max = 100;
```

### Final field

```java
class User {
    private final String id;
}
```

### Final method

Cannot be overridden.

```java
final void validate() {
}
```

### Final class

Cannot be subclassed.

```java
final class Money {
}
```

## 2. Why?

It communicates and enforces constraints.

```text
final class
    ↓
No subclassing

final method
    ↓
No overriding

final reference
    ↓
No reassignment
```

## 3. Critical Distinction

A final reference does **not** make the referenced object immutable.

```java
final List<String> names = new ArrayList<>();

names.add("Alex");     // allowed
names = new ArrayList<>(); // not allowed
```

## 4. Basic Example

```java
final class Configuration {
}

class User {

    private final String id;

    User(String id) {
        this.id = id;
    }
}
```

## 5. Internal / Runtime Behavior

The compiler enforces many final restrictions.

The JVM also uses final-field semantics that are relevant to safe publication and the Java Memory Model.

Understand:

- [ ] Final reference vs object immutability
- [ ] Final field semantics
- [ ] Constant variables
- [ ] JIT optimization opportunities

## 6. Performance

Do not assume:

```text
final = faster
```

The primary value is correctness, API design, and immutability guarantees.

The JVM may optimize based on known invariants, but performance should be measured.

## 7. Edge Cases

- [ ] Final fields initialized in constructors
- [ ] Blank final fields
- [ ] Final references to mutable objects
- [ ] Static final constants
- [ ] Final methods and inheritance
- [ ] Reflection/unsafe mechanisms in legacy or specialized contexts

## 8. Production Use Cases

- Immutable objects
- Constants
- Stable dependencies
- Preventing subclassing
- Thread-safe publication patterns

## 9. Interview Questions

- [ ] final variable vs final reference?
- [ ] final vs immutable?
- [ ] Why are final fields important for concurrency?
- [ ] Can a final object be mutated?

---

# 1.7.8 `static`

## 1. What is it?

`static` associates a field, method, initializer, or nested type with the class rather than with individual object instances.

## 2. Why?

Some state and behavior logically belong to the type itself.

## 3. Basic Example

```java
class MathUtil {

    static int add(int a, int b) {
        return a + b;
    }
}
```

Call:

```java
MathUtil.add(10, 20);
```

## 4. Static Fields

```java
class Counter {

    static int count;
}
```

The field is associated with the class rather than each instance.

## 5. Static Initialization

```java
class Config {

    static {
        System.out.println("Class initialization");
    }
}
```

Static initialization occurs as part of class initialization when required by JVM execution.

## 6. Internal Working

Conceptually:

```text
Class loaded
    ↓
Linked
    ↓
Initialized when required
    ↓
Static state initialized
```

Do not equate class loading with class initialization. They are related but distinct JVM lifecycle concepts.

## 7. Memory / Runtime Behavior

Understand:

- [ ] Class-level state
- [ ] Class initialization
- [ ] Class loader association
- [ ] Static references preventing GC of referenced objects while the defining class remains live
- [ ] Thread-safety of mutable static state

## 8. Common Mistakes

- [ ] Global mutable state
- [ ] Huge static caches
- [ ] Static dependency containers
- [ ] Assuming static automatically means thread-safe
- [ ] Static initialization with side effects

## 9. Production Use Cases

Good:

```java
MathUtil.add(...)
Constants.MAX_RETRIES
```

Risky:

```java
static Map<String, Object> globalState;
```

without lifecycle, concurrency, and memory planning.

## 10. Interview Questions

- [ ] Static vs instance members?
- [ ] When is static initialization executed?
- [ ] Can static methods access instance fields directly?
- [ ] Is static state thread-safe?

---

# 1.7.9 `abstract`

## 1. What is it?

`abstract` indicates incomplete class/method design intended for extension or implementation.

### Abstract class

```java
abstract class Animal {
}
```

### Abstract method

```java
abstract void move();
```

## 2. Why?

It allows a base type to define common structure/contract while leaving some behavior to subclasses.

## 3. Basic Example

```java
abstract class PaymentProcessor {

    abstract void process();

    void validate() {
        System.out.println("Validating");
    }
}
```

## 4. Internal Working

Abstract methods do not provide a normal implementation for invocation through an abstract declaration.

Concrete subclasses must implement required abstract methods unless they are also abstract.

## 5. Edge Cases

- [ ] Abstract class cannot be instantiated directly.
- [ ] Abstract class can have constructors.
- [ ] Abstract class can have fields and concrete methods.
- [ ] Abstract methods cannot be private.
- [ ] Abstract methods cannot be final.
- [ ] An abstract class can implement an interface without implementing all methods.

## 6. Common Mistakes

- [ ] Using abstract classes when composition is better
- [ ] Creating deep hierarchies
- [ ] Abstracting before identifying stable variation

## 7. Production Use Cases

- Framework extension points
- Template Method pattern
- Shared behavior plus required subclass behavior

---

# 1.7.10 `default`

## 1. What is it?

In an interface, `default` defines an instance method with a provided implementation.

```java
interface Logger {

    default void log(String message) {
        System.out.println(message);
    }
}
```

## 2. Why?

Default methods allow interfaces to evolve by adding behavior without immediately breaking every existing implementation.

## 3. Basic Example

```java
interface Vehicle {

    default void start() {
        System.out.println("Starting");
    }
}
```

## 4. Internal Working

Default methods participate in interface method resolution and inheritance.

If multiple interfaces provide conflicting defaults, the implementing class may need to resolve the conflict.

## 5. Diamond Problem

```java
interface A {
    default void run() {}
}

interface B {
    default void run() {}
}

class C implements A, B {

    @Override
    public void run() {
        A.super.run();
    }
}
```

## 6. Edge Cases

- [ ] Conflicting defaults
- [ ] Class methods take precedence over interface defaults.
- [ ] More-specific interface rules
- [ ] Explicit resolution
- [ ] Default methods cannot be used to provide state

## 7. Common Mistakes

- [ ] Treating default methods as abstract-class replacement
- [ ] Putting too much stateful behavior in interfaces
- [ ] Ignoring method conflict rules

## 8. Interview Questions

- [ ] Why were default methods introduced?
- [ ] What happens when two interfaces have the same default method?
- [ ] Which wins: class method or interface default?

---

# 1.7.11 `synchronized`

## 1. What is it?

`synchronized` provides monitor-based mutual exclusion and establishes important Java Memory Model visibility guarantees.

It can apply to:

- Instance methods
- Static methods
- Blocks

## 2. Why?

It provides a built-in mechanism for coordinating concurrent access to shared state.

## 3. Basic Example

```java
class Counter {

    private int value;

    public synchronized void increment() {
        value++;
    }

    public synchronized int get() {
        return value;
    }
}
```

## 4. Synchronized Block

```java
synchronized (lock) {
    value++;
}
```

## 5. Instance vs Static

Instance method:

```java
public synchronized void increment() {}
```

is conceptually synchronized on:

```java
this
```

Static synchronized method:

```java
public static synchronized void process() {}
```

is synchronized on the `Class` object associated with the class.

## 6. Internal Working

Conceptually:

```text
Thread
  ↓
Acquire monitor
  ↓
Enter critical section
  ↓
Modify/read shared state
  ↓
Release monitor
```

Synchronization also establishes happens-before relationships around monitor unlock/lock operations.

## 7. Memory / Runtime Behavior

Understand:

- [ ] Monitor
- [ ] Mutual exclusion
- [ ] Visibility
- [ ] Happens-before
- [ ] Lock contention
- [ ] Thread blocking
- [ ] JVM lock implementation
- [ ] Lock optimization history

## 8. Edge Cases

- [ ] Reentrant synchronization
- [ ] Exceptions release the monitor
- [ ] Deadlocks
- [ ] Lock contention
- [ ] Synchronizing on publicly accessible objects
- [ ] Synchronizing on boxed/string interned objects
- [ ] Nested locks

## 9. Common Mistakes

Bad:

```java
synchronized ("LOCK") {
}
```

The same interned string may be used elsewhere.

Prefer a private lock:

```java
private final Object lock = new Object();
```

or appropriate concurrency abstractions.

## 10. Performance

Contention can reduce throughput.

Do not remove synchronization merely because it has a cost; first determine whether synchronization is required for correctness.

## 11. Production Use Cases

- Shared mutable state
- Legacy thread-safe classes
- Small critical sections
- Simple monitor-based coordination

For complex concurrency, also study:

- `ReentrantLock`
- Atomics
- Concurrent collections
- Executors
- Virtual threads

## 12. Interview Questions

- [ ] What does synchronized guarantee?
- [ ] Instance vs static synchronized?
- [ ] What is a monitor?
- [ ] Is synchronized reentrant?
- [ ] Does synchronized provide visibility?

---

# 1.7.12 `volatile`

## 1. What is it?

`volatile` tells the Java Memory Model that accesses to a field have special visibility and ordering semantics across threads.

Example:

```java
private volatile boolean running = true;
```

## 2. Why?

It is useful when threads need to observe updates to shared state without using a monitor for that field.

## 3. Basic Example

```java
class Worker {

    private volatile boolean running = true;

    void stop() {
        running = false;
    }

    void work() {
        while (running) {
            // work
        }
    }
}
```

## 4. Internal Working

Conceptually:

```text
Thread A writes volatile field
        ↓
Visibility/order guarantees
        ↓
Thread B reads volatile field
        ↓
Can observe the write according to JMM rules
```

## 5. Critical Limitation

`volatile` does **not** make compound operations atomic.

This is not safe:

```java
volatile int count;

count++;
```

Because:

```text
read
+
add
+
write
```

is multiple operations.

Use:

```java
AtomicInteger
```

or synchronization where appropriate.

## 6. Memory / Runtime Behavior

Study:

- [ ] Happens-before
- [ ] Visibility
- [ ] Reordering
- [ ] Memory barriers / fences conceptually
- [ ] Atomic reads/writes
- [ ] Cache coherence vs Java Memory Model

Do not reduce volatile to:

> "It forces the CPU cache to update."

That is an oversimplification.

## 7. Edge Cases

- [ ] Compound operations
- [ ] Multiple related variables
- [ ] Invariants spanning multiple fields
- [ ] Publication
- [ ] Double-checked locking
- [ ] Reference fields

## 8. Production Use Cases

Appropriate for:

- Stop flags
- State flags
- Configuration visibility
- Certain safe-publication patterns
- Lock-free algorithms when designed correctly

## 9. Common Mistakes

- [ ] Using volatile for counters
- [ ] Using volatile for multi-field invariants
- [ ] Assuming volatile means thread-safe
- [ ] Treating it as a replacement for all synchronization

## 10. Interview Questions

- [ ] volatile vs synchronized?
- [ ] Why is `count++` not safe with volatile?
- [ ] What is happens-before?
- [ ] Does volatile provide atomicity?

---

# 1.7.13 `transient`

## 1. What is it?

`transient` marks a field to be skipped by Java's default serialization mechanism.

```java
class User implements Serializable {

    private String username;

    private transient String password;
}
```

## 2. Why?

Some fields should not be serialized.

Examples:

- Sensitive derived state
- Caches
- Runtime-only state
- Resources that cannot be serialized

## 3. Basic Example

```java
class Session implements Serializable {

    private String userId;

    private transient Object runtimeConnection;
}
```

## 4. Internal Working

With Java's built-in serialization:

```text
Object
  ↓
ObjectOutputStream
  ↓
Serializable fields
  ↓
transient fields skipped
```

On deserialization, skipped fields receive default values unless custom deserialization restores them.

## 5. Edge Cases

- [ ] `static` fields are not serialized as per-object state.
- [ ] Transient fields are initialized to defaults during normal deserialization unless restored explicitly.
- [ ] Sensitive data may still exist elsewhere.
- [ ] Custom `writeObject()` / `readObject()` can alter serialization behavior.

## 6. Common Mistakes

- [ ] Assuming transient means encrypted
- [ ] Assuming transient securely erases memory
- [ ] Forgetting to reconstruct transient derived state
- [ ] Using Java serialization for untrusted data

## 7. Production Guidance

Java native serialization has substantial security and maintenance concerns.

For modern applications, prefer explicit formats and APIs such as:

- JSON
- Protobuf
- Avro

depending on the architecture.

---

# 1.7.14 `native`

## 1. What is it?

`native` declares a method whose implementation is provided outside Java, historically through mechanisms such as JNI.

Example:

```java
public native void executeNative();
```

There is no Java method body.

## 2. Why?

It provides a bridge to:

- Operating-system APIs
- Existing native libraries
- Hardware-specific functionality
- Performance-sensitive native components
- Platform integrations

## 3. Internal Working

Conceptually:

```text
Java call
   ↓
JNI/native boundary
   ↓
Native implementation
   ↓
OS / native library
```

## 4. Runtime Behavior

Native calls cross the JVM/native boundary and can introduce:

- [ ] Marshaling costs
- [ ] Native memory
- [ ] Native crashes
- [ ] Platform dependencies
- [ ] Resource-management complexity

## 5. Common Mistakes

- [ ] Assuming native is automatically faster
- [ ] Ignoring native memory
- [ ] Ignoring platform compatibility
- [ ] Poor native resource lifecycle management

## 6. Production Use Cases

Use only when there is a strong reason.

Many Java applications can avoid direct JNI by using existing Java APIs.

---

# 1.7.15 `strictfp`

## 1. What is it?

`strictfp` historically requested strict IEEE-style floating-point evaluation semantics.

Example from older Java code:

```java
strictfp class Calculator {
}
```

## 2. Why?

It was introduced to make floating-point calculations more predictable across platforms.

## 3. Modern Java Context

Starting with Java 17, floating-point expressions are consistently strict by default under the modern Java language/runtime model.

Therefore, `strictfp` is effectively obsolete for new Java code.

Modern compilers may issue a warning when it is used.

## 4. Production Guidance

For modern Java:

> Understand `strictfp` for legacy code and interviews, but do not add it to new code unless maintaining a compatibility-sensitive codebase where its presence is intentional.

## 5. Important Distinction

Floating-point determinism does not mean:

```text
double = exact decimal arithmetic
```

For money, use appropriate decimal representations such as:

```java
BigDecimal
```

when exact decimal semantics are required.

## 6. Interview Questions

- [ ] Why did strictfp exist?
- [ ] What changed in Java 17?
- [ ] Should strictfp be used in new Java code?

---

# 1.7.16 `sealed`

## 1. What is it?

A `sealed` class/interface restricts which types may directly extend/implement it.

```java
public sealed interface Payment
        permits CardPayment, UpiPayment {
}
```

## 2. Why?

It allows controlled inheritance and closed hierarchies.

Useful for:

- Domain models
- Algebraic-data-type-like designs
- Exhaustive pattern matching
- Framework APIs

## 3. Basic Example

```java
sealed interface Shape
        permits Circle, Rectangle {
}

final class Circle implements Shape {
}

final class Rectangle implements Shape {
}
```

## 4. Internal Working

The compiler validates the permitted hierarchy.

The permitted direct subtype must declare an appropriate modifier:

```text
final
sealed
non-sealed
```

## 5. Edge Cases

- [ ] Direct subclasses must be permitted.
- [ ] Permitted subclasses must be in the required package/module relationship.
- [ ] A permitted subtype must declare `final`, `sealed`, or `non-sealed`.
- [ ] Sealed hierarchies interact strongly with pattern matching.
- [ ] Indirect subclasses depend on the modifiers chosen by intermediate types.

## 6. Production Use Cases

Useful when the domain intentionally has a closed set of direct variants:

```text
Payment
 ├── Card
 ├── UPI
 └── BankTransfer
```

It can make invalid implementations impossible at compile time.

---

# 1.7.17 `non-sealed`

## 1. What is it?

`non-sealed` explicitly reopens inheritance below a sealed type.

```java
sealed interface Payment
        permits CardPayment {
}

non-sealed class CardPayment implements Payment {
}
```

Now other classes may extend `CardPayment`.

## 2. Why?

It allows a controlled hierarchy to contain an extensible branch.

```text
Payment
   ↓ sealed
CardPayment
   ↓ non-sealed
AnyCardSubclass
```

## 3. Basic Example

```java
sealed interface Animal
        permits Mammal {
}

non-sealed class Mammal implements Animal {
}

class Dog extends Mammal {
}
```

## 4. Common Mistake

Thinking `non-sealed` means:

> "The whole hierarchy is open."

It only removes the sealing restriction from that particular subtype.

---

# 1.7.18 `permits`

## 1. What is it?

`permits` declares the direct subclasses/subinterfaces allowed for a sealed type.

```java
sealed interface Result
        permits Success, Failure {
}
```

## 2. Why?

It makes the allowed direct type hierarchy explicit and compiler-checkable.

## 3. Basic Example

```java
sealed interface Result
        permits Success, Failure {
}

final class Success implements Result {
}

final class Failure implements Result {
}
```

## 4. Edge Cases

- [ ] Direct subtype omitted from permits list
- [ ] Invalid subtype modifier
- [ ] Package/module restrictions
- [ ] Nested permitted classes
- [ ] Pattern matching exhaustiveness

## 5. Modern Java Connection

Sealed types work especially well with pattern matching:

```java
static String describe(Result result) {
    return switch (result) {
        case Success s -> "success";
        case Failure f -> "failure";
    };
}
```

The compiler can reason about the closed hierarchy.

---

# 1.7.19 Sealed Type Modifier Rules

Master this matrix:

| Base Type | Direct Subtype Declaration |
|---|---|
| `sealed` | Must use `final`, `sealed`, or `non-sealed` |
| `final` | Cannot be extended |
| `non-sealed` | Can be extended normally |
| `sealed` | Must define its permitted direct subtypes |

Example:

```java
sealed interface Payment
        permits Card, Upi {
}

final class Card implements Payment {
}

non-sealed class Upi implements Payment {
}
```

Then:

```java
class PhonePeUpi extends Upi {
}
```

is allowed.

---

# 1.7.20 Modifier Interaction Matrix

Study which modifiers can legally appear together.

Examples:

```text
final class
abstract class        ← illegal combination
final method
abstract method       ← illegal combination
static method
abstract static method ← generally illegal
private method
abstract private method ← illegal
```

Important combinations:

- [ ] `public final class`
- [ ] `public abstract class`
- [ ] `private static final`
- [ ] `protected final`
- [ ] `public synchronized`
- [ ] `private synchronized`
- [ ] `static synchronized`
- [ ] `public volatile`
- [ ] `private transient`
- [ ] `public native`
- [ ] `sealed interface`
- [ ] `non-sealed class`
- [ ] `permits`

Learn the compiler rules rather than memorizing random combinations.

---

# 1.7.21 Modifier Decision Guide

## Need API visibility?

```text
public
```

## Need package-only implementation?

```text
package-private
```

## Need inheritance extension?

```text
protected / abstract
```

only when inheritance is intentional.

## Need encapsulation?

```text
private
```

## Need prevent reassignment/extension/override?

```text
final
```

depending on target.

## Need type-level state/behavior?

```text
static
```

## Need incomplete polymorphic contract?

```text
abstract
```

## Need interface-provided behavior?

```text
default
```

## Need monitor synchronization?

```text
synchronized
```

## Need cross-thread visibility/order for a field?

```text
volatile
```

## Need to exclude a field from Java serialization?

```text
transient
```

## Need native implementation?

```text
native
```

## Maintaining legacy floating-point semantics?

```text
strictfp
```

## Need a closed hierarchy?

```text
sealed + permits
```

## Need to reopen one branch?

```text
non-sealed
```

---

# 1.7.22 Deep Edge-Case Exercises

## Access Control

- [ ] Create four packages and test every access modifier.
- [ ] Test protected access across packages.
- [ ] Test nested classes.
- [ ] Test private constructors.
- [ ] Test package-private top-level classes.
- [ ] Add JPMS modules and test `exports`.

## `final`

- [ ] Create a final field.
- [ ] Create a final reference to a mutable list.
- [ ] Mutate the list.
- [ ] Attempt reference reassignment.
- [ ] Compare final with immutable.

## `static`

- [ ] Create static and instance fields.
- [ ] Observe initialization order.
- [ ] Create multiple class loaders if studying advanced JVM behavior.
- [ ] Demonstrate static-state lifecycle.

## `synchronized`

- [ ] Build an unsafe counter.
- [ ] Fix it using synchronized.
- [ ] Create intentional lock contention.
- [ ] Demonstrate deadlock with two locks.
- [ ] Replace the design with `ReentrantLock`.

## `volatile`

- [ ] Demonstrate a visibility flag.
- [ ] Demonstrate why volatile does not make `count++` atomic.
- [ ] Replace it with `AtomicInteger`.
- [ ] Compare with synchronized.

## `transient`

- [ ] Serialize an object.
- [ ] Mark a field transient.
- [ ] Deserialize it.
- [ ] Observe the default value.
- [ ] Implement custom `readObject()`.

## Sealed Types

- [ ] Build a sealed payment hierarchy.
- [ ] Add final subclasses.
- [ ] Add a non-sealed branch.
- [ ] Extend the non-sealed branch.
- [ ] Use pattern matching for switch.
- [ ] Add a new permitted subtype and observe compilation consequences.

---

# 1.7.23 Production Design Exercise

Build:

```text
Payment
   ↓ sealed
 ┌───────────────┬───────────────┐
CardPayment      UpiPayment      CashPayment
```

Requirements:

- [ ] Use `sealed`.
- [ ] Use `permits`.
- [ ] Make appropriate variants `final`.
- [ ] Make one branch `non-sealed` if justified.
- [ ] Keep state private.
- [ ] Use `final` fields where appropriate.
- [ ] Use interfaces for stable contracts.
- [ ] Use package-private implementation classes where appropriate.
- [ ] Avoid unnecessary `protected` state.
- [ ] Add a thread-safe payment counter.
- [ ] Explain whether `synchronized`, `volatile`, or atomics are appropriate.
- [ ] Add a transient runtime-only field.
- [ ] Document every modifier choice.

---

# 1.7.24 Interview Question Bank

## Basic

- [ ] Explain all four access levels.
- [ ] What is package-private?
- [ ] What does final mean?
- [ ] What does static mean?
- [ ] What is an abstract class?
- [ ] What is a default interface method?
- [ ] What does synchronized do?
- [ ] What does volatile do?
- [ ] What does transient do?
- [ ] What does sealed mean?

## Intermediate

- [ ] protected vs package-private?
- [ ] final reference vs immutable object?
- [ ] static vs instance state?
- [ ] abstract class vs interface?
- [ ] default method vs abstract method?
- [ ] synchronized vs volatile?
- [ ] Why doesn't volatile make `count++` atomic?
- [ ] Why are mutable static fields dangerous?
- [ ] Why are transient fields restored to defaults during normal Java deserialization?

## Advanced

- [ ] Explain Java's protected access rules across packages.
- [ ] Explain final-field semantics under the JMM.
- [ ] Explain class initialization vs class loading.
- [ ] Explain monitor ownership.
- [ ] Explain happens-before with synchronized.
- [ ] Explain happens-before with volatile.
- [ ] Explain default-method conflict resolution.
- [ ] Explain sealed hierarchy rules.
- [ ] Explain `final` vs `sealed`.
- [ ] Explain `non-sealed` with an example.
- [ ] Explain why `strictfp` is obsolete in modern Java.
- [ ] Explain native method boundaries.

## Senior / Production

- [ ] How would you design public vs package-private APIs in a large codebase?
- [ ] When is protected a design smell?
- [ ] When does static state become an architectural problem?
- [ ] How do final fields help immutable objects?
- [ ] How would you debug a visibility bug involving volatile?
- [ ] When should synchronized be replaced with higher-level concurrency utilities?
- [ ] How would you prevent serialization of sensitive/runtime state?
- [ ] When are sealed types better than open inheritance?
- [ ] How can sealed types improve API evolution and pattern matching?
- [ ] How do JPMS boundaries interact with public classes?
- [ ] How can class loaders affect static state?

---

# 1.7.25 Advanced Follow-ups

Study after completing the basic modifier material:

## Access & Modules

- [ ] Java Platform Module System
- [ ] `exports`
- [ ] `opens`
- [ ] `requires`
- [ ] Reflection and modules
- [ ] Class loaders

## Concurrency

- [ ] Java Memory Model
- [ ] Happens-before
- [ ] Safe publication
- [ ] Final-field semantics
- [ ] Monitor implementation
- [ ] VarHandle
- [ ] Atomic classes
- [ ] Lock implementations

## Object Design

- [ ] Immutability
- [ ] Encapsulation
- [ ] Composition
- [ ] API design
- [ ] Inheritance
- [ ] Sealed hierarchies

## Serialization

- [ ] Serializable
- [ ] Object streams
- [ ] `readObject`
- [ ] `writeObject`
- [ ] `serialVersionUID`
- [ ] Serialization security

## JVM

- [ ] Class loading
- [ ] Linking
- [ ] Initialization
- [ ] Runtime constant pool
- [ ] Method invocation
- [ ] JVM access checks
- [ ] Native method invocation
- [ ] JNI

---

# 1.7.26 Performance Checklist

For modifier-related performance reasoning:

- [ ] Do not assume `final` automatically improves performance.
- [ ] Do not assume `static` is always faster.
- [ ] Measure synchronization contention before optimizing.
- [ ] Understand volatile memory-ordering costs.
- [ ] Avoid unnecessary global static state.
- [ ] Understand native boundary overhead.
- [ ] Avoid expensive static initialization.
- [ ] Consider class-loader lifecycle for static caches.
- [ ] Understand serialization allocation and I/O costs.

---

# 1.7.27 Production Debugging Checklist

## Access Error

```text
1. Check declared modifier
2. Check package
3. Check inheritance relationship
4. Check enclosing type
5. Check module readability/exports
6. Check reflection access
```

## Concurrency Bug

```text
1. Identify shared state
2. Check atomicity
3. Check visibility
4. Check ordering
5. Check happens-before
6. Check synchronized/volatile/atomic usage
7. Check lock ordering
8. Check deadlocks
```

## Serialization Bug

```text
1. Check Serializable
2. Check transient fields
3. Check serialVersionUID
4. Check custom read/write methods
5. Check post-deserialization invariants
```

## Class Initialization Bug

```text
1. Identify class
2. Check static fields
3. Check static blocks
4. Check initialization triggers
5. Check initialization dependencies
6. Check class-loader boundaries
```

---

# 1.7.28 Final Mastery Gate

Do **not** mark Module 1.7 complete until you can:

- [ ] Explain `public`.
- [ ] Explain `protected` precisely.
- [ ] Explain package-private access.
- [ ] Explain `private`.
- [ ] Compare all access levels.
- [ ] Explain `final` for variables, fields, methods, and classes.
- [ ] Explain final reference vs immutable object.
- [ ] Explain `static` fields, methods, nested types, and initialization.
- [ ] Explain class loading vs initialization.
- [ ] Explain `abstract` classes and methods.
- [ ] Explain `default` interface methods.
- [ ] Resolve default-method conflicts.
- [ ] Explain `synchronized`.
- [ ] Explain monitors.
- [ ] Explain synchronization's happens-before relationship.
- [ ] Explain `volatile`.
- [ ] Explain why volatile does not provide compound-operation atomicity.
- [ ] Explain `transient`.
- [ ] Explain Java serialization behavior for transient/static state.
- [ ] Explain `native` and the Java/native boundary.
- [ ] Explain `strictfp` historically and its modern Java status.
- [ ] Explain sealed classes/interfaces.
- [ ] Explain `permits`.
- [ ] Explain `non-sealed`.
- [ ] Build a sealed hierarchy.
- [ ] Use sealed types with pattern matching.
- [ ] Explain modifier combinations that are illegal.
- [ ] Debug access-control problems.
- [ ] Debug visibility/concurrency problems.
- [ ] Debug class initialization issues.
- [ ] Discuss performance implications.
- [ ] Discuss production trade-offs.
- [ ] Answer basic → senior interview questions.
- [ ] Complete basic → production exercises.

---

# Final Mastery Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
