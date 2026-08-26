# Module 1.4 — Object Initialization
## Deep Mastery Guide

> **Prerequisite:** Module 1.3 — OOP  
> **Goal:** Master exactly how Java initializes classes and objects, including `this`, `super`, constructors, initialization blocks, inheritance ordering, JVM class initialization, edge cases, and production implications.

---

# Mastery Cycle

For **every topic**, complete:

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

> **Explain → Implement → Explain internals → Handle edge cases → Discuss trade-offs → Debug → Use in a production scenario**

---

# 1.4.1 `this`

## 1. What is it?

`this` is the reference to the current object in an instance context.

```java
class User {

    private String name;

    User(String name) {
        this.name = name;
    }
}
```

Here:

```java
this.name
```

refers to the instance field, while:

```java
name
```

refers to the constructor parameter.

### Master

- [ ] `this.field`
- [ ] `this.method()`
- [ ] `this(...)`
- [ ] Passing `this`
- [ ] Returning `this`
- [ ] `this` inside nested contexts
- [ ] Restrictions on `this`

---

## 2. Why does Java have `this`?

It solves several problems:

- Distinguishing fields from parameters
- Accessing the current object
- Constructor chaining
- Fluent APIs
- Passing the current object to another method

### Example

```java
class User {

    private String name;

    User(String name) {
        this.name = name;
    }

    User rename(String name) {
        this.name = name;
        return this;
    }
}
```

---

## 3. Syntax and API

```java
this.name
this.calculate()
this(...)
```

### Constructor Chaining

```java
class User {

    private String name;
    private int age;

    User() {
        this("Unknown", 0);
    }

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

---

## 4. Basic Example

```java
class Account {

    private final String id;

    Account(String id) {
        this.id = id;
    }

    void print() {
        System.out.println(this.id);
    }
}
```

### Exercises

- [ ] Rename a field and parameter to the same name.
- [ ] Use `this` to call another method.
- [ ] Return `this` from a method.
- [ ] Use `this(...)` for constructor chaining.

---

## 5. Internal Working

Understand that `this` is not a magical copy of the object.

Conceptually:

```text
object
  ↑
this reference
```

For an instance method:

```java
account.deposit(100);
```

the current object is conceptually available to the method through `this`.

Study:

- [ ] Instance method invocation
- [ ] Receiver object
- [ ] JVM method invocation
- [ ] Local variable representation
- [ ] Bytecode behavior

---

## 6. Memory / Runtime Behavior

`this` refers to the current object.

It does not create another object.

Understand:

- [ ] Reference semantics
- [ ] Object lifetime
- [ ] Escape of `this`
- [ ] Passing `this` to another object
- [ ] Constructor-time object state

---

## 7. Edge Cases

### Escaping `this`

```java
class Example {

    Example(EventBus bus) {
        bus.register(this);
    }
}
```

The object may become visible before construction has completed.

This can be dangerous.

### Do Not Call Overridable Methods Carelessly

```java
class Parent {

    Parent() {
        print();
    }

    void print() {
        System.out.println("Parent");
    }
}
```

If a subclass overrides `print()`, the subclass method may execute before subclass initialization is complete.

---

## 8. Common Mistakes

- [ ] Confusing `this` with a new object
- [ ] Escaping `this` from a constructor
- [ ] Calling overridable methods from constructors
- [ ] Using `this(...)` incorrectly
- [ ] Forgetting that `this(...)` must be the first constructor invocation

---

## 9. Performance Implications

`this` itself normally has negligible overhead.

The important performance concerns are indirect:

- Object escape
- Allocation
- Constructor publication
- Method dispatch
- JIT optimization

---

## 10. Production Use Cases

Use `this` for:

- Field disambiguation
- Constructor chaining
- Fluent APIs
- Explicit receiver access

Avoid exposing `this` from constructors unless you understand the lifecycle implications.

---

## 11. Interview Questions

### Basic

- [ ] What is `this`?
- [ ] Why is `this` needed?

### Intermediate

- [ ] Difference between `this.field` and a local variable?
- [ ] What is `this(...)`?

### Advanced

- [ ] Can `this` be used in a static method?
- [ ] Why is leaking `this` from a constructor dangerous?

### Senior

- [ ] What problems can constructor-time publication of `this` create in concurrent code?
- [ ] Why should constructors avoid calling overridable methods?

---

# 1.4.2 `super`

## 1. What is it?

`super` refers to the superclass portion/type of the current object.

It is used to:

- Call superclass constructors
- Access superclass methods
- Access superclass fields

---

## 2. Why does Java have it?

It allows subclasses to explicitly interact with inherited behavior.

```java
class Dog extends Animal {

    @Override
    void speak() {
        super.speak();
        System.out.println("Woof");
    }
}
```

---

## 3. Syntax

```java
super.field
super.method()
super(...)
```

### Constructor

```java
class Dog extends Animal {

    Dog() {
        super();
    }
}
```

---

## 4. Basic Example

```java
class Parent {

    protected String name = "Parent";

    void print() {
        System.out.println("Parent");
    }
}

class Child extends Parent {

    void test() {
        System.out.println(super.name);
        super.print();
    }
}
```

---

## 5. Internal Working

Understand:

```text
Child object
    ↓
Child execution context
    ↓
super.method()
    ↓
explicit superclass method resolution/invocation
```

`super` does not point to a separate parent object.

It provides a language mechanism for accessing superclass behavior.

---

## 6. Memory / Runtime Behavior

A subclass object does not contain a separate independently allocated "parent object".

Conceptually:

```text
Child object
├── inherited state
└── child state
```

Understand:

- [ ] Object layout
- [ ] Inherited fields
- [ ] Constructor execution
- [ ] Method invocation
- [ ] `invokespecial` for relevant special invocations

---

## 7. Edge Cases

- [ ] No explicit `super()` written
- [ ] Parent has no no-argument constructor
- [ ] Private parent fields
- [ ] Overridden methods
- [ ] Static members
- [ ] Constructor exceptions

---

## 8. Common Mistakes

- [ ] Thinking `super` is a parent object reference
- [ ] Forgetting superclass constructor requirements
- [ ] Trying to access private superclass fields directly
- [ ] Confusing `super.method()` with normal virtual dispatch

---

## 9. Performance Implications

Direct superclass invocation has different dispatch semantics from ordinary virtual invocation.

The practical performance difference is usually not a reason to choose one design over another.

---

## 10. Production Use Cases

- Extending framework classes
- Reusing superclass initialization
- Augmenting parent behavior
- Template Method pattern

---

## 11. Interview Questions

### Basic

- [ ] What is `super`?
- [ ] Why use `super()`?

### Intermediate

- [ ] `this()` vs `super()`?
- [ ] What happens if no `super()` is explicitly written?

### Advanced

- [ ] Why can `super.method()` behave differently from `this.method()`?
- [ ] Can `super` access private fields?

### Senior

- [ ] Why can calling superclass methods from constructors still be dangerous?
- [ ] How does `super` relate to JVM method invocation instructions?

---

# 1.4.3 Constructors

## 1. What is it?

A constructor is a special class member used during object creation and initialization.

```java
class User {

    private final String name;

    User(String name) {
        this.name = name;
    }
}
```

---

## 2. Why does Java have constructors?

Constructors allow objects to begin life in a valid state.

They are useful for:

- Required state
- Validation
- Dependency initialization
- Invariant enforcement

---

## 3. Syntax and Rules

```java
class User {

    User(String name) {
    }
}
```

Important rules:

- [ ] Constructor name matches class name
- [ ] No return type
- [ ] Can be overloaded
- [ ] Can call another constructor
- [ ] Cannot be inherited
- [ ] Cannot be overridden
- [ ] Can have access modifiers

---

## 4. Basic Example

```java
class User {

    private final String name;

    User(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid name");
        }

        this.name = name;
    }
}
```

---

## 5. Internal Working

For:

```java
User user = new User("Alex");
```

conceptually:

```text
Class availability / initialization
        ↓
Object allocation
        ↓
Default initialization of object fields
        ↓
Constructor chain
        ↓
Instance initialization
        ↓
Constructor body
        ↓
Reference returned
```

The exact JVM/class-initialization details should be studied separately from the source-level constructor syntax.

---

## 6. Memory / Runtime Behavior

Before constructor code executes, object fields receive their default values.

Examples:

```text
int      → 0
boolean  → false
reference → null
```

Then explicit initialization takes place.

Master:

- [ ] Default field values
- [ ] Instance field initializers
- [ ] Instance initializer blocks
- [ ] Constructor body
- [ ] Final field assignment
- [ ] Object publication

---

## 7. Edge Cases

- [ ] Constructor throws exception
- [ ] Recursive constructor chaining
- [ ] Parent constructor failure
- [ ] Final field initialization
- [ ] Private constructor
- [ ] Constructor visibility
- [ ] Constructor inheritance misconception
- [ ] Constructor invoking overridable method

---

## 8. Common Mistakes

- [ ] Giving a constructor a return type
- [ ] Assuming constructors are inherited
- [ ] Forgetting superclass constructor requirements
- [ ] Putting complex business logic into constructors
- [ ] Starting threads from constructors
- [ ] Registering `this` before construction completes

---

## 9. Performance Implications

Constructors contribute to object allocation cost.

Watch:

- [ ] Excessive allocations
- [ ] Expensive I/O during construction
- [ ] Database calls in constructors
- [ ] Network calls in constructors
- [ ] Heavy computation during object creation

---

## 10. Production Use Cases

Good constructor responsibilities:

- Establish invariants
- Validate required arguments
- Assign dependencies
- Initialize object state

Avoid:

```text
Constructor
   ↓
Network call
   ↓
Database call
   ↓
Thread creation
```

unless there is a very deliberate lifecycle design.

---

## 11. Interview Questions

### Basic

- [ ] What is a constructor?
- [ ] Does a constructor have a return type?

### Intermediate

- [ ] Are constructors inherited?
- [ ] Can constructors be overloaded?

### Advanced

- [ ] What happens before constructor execution?
- [ ] What are default field values?

### Senior

- [ ] Why is heavy I/O inside constructors usually a design smell?
- [ ] Why is leaking `this` during construction dangerous?

---

# 1.4.4 Constructor Overloading

## What is it?

A class can have multiple constructors with different parameter lists.

```java
class User {

    User() {
    }

    User(String name) {
    }

    User(String name, int age) {
    }
}
```

---

## Why?

It supports different valid initialization paths.

However, too many constructors can become difficult to maintain.

---

## Best Practice

Prefer constructor chaining:

```java
class User {

    User() {
        this("Unknown", 0);
    }

    User(String name) {
        this(name, 0);
    }

    User(String name, int age) {
        // canonical initialization
    }
}
```

This gives one primary initialization path.

---

## Edge Cases

- [ ] Ambiguous overloads
- [ ] `null`
- [ ] Boxing/unboxing
- [ ] Varargs
- [ ] Widening conversion
- [ ] Constructor visibility

---

# 1.4.5 Constructor Chaining

## Same Class

Use:

```java
this(...)
```

Example:

```java
class Product {

    private final String name;
    private final double price;

    Product() {
        this("Unknown", 0);
    }

    Product(String name) {
        this(name, 0);
    }

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
```

---

## Parent Class

Use:

```java
super(...)
```

Example:

```java
class Animal {

    Animal(String name) {
    }
}

class Dog extends Animal {

    Dog(String name) {
        super(name);
    }
}
```

---

## Critical Rule

A constructor may begin with either:

```java
this(...)
```

or:

```java
super(...)
```

but not both.

And the explicit constructor invocation must be the first statement.

---

## Edge Cases

- [ ] Constructor chaining cycles
- [ ] Missing parent no-arg constructor
- [ ] Checked exceptions
- [ ] Access restrictions
- [ ] Recursive constructor invocation

Example of invalid conceptual cycle:

```java
A() {
    this(1);
}

A(int x) {
    this();
}
```

This results in a constructor invocation cycle.

---

# 1.4.6 Static Initialization

## 1. What is it?

Static initialization initializes class-level state.

Examples:

```java
class Config {

    static int value = 10;

    static {
        value = 20;
    }
}
```

---

## Why?

Static initialization is useful for class-level setup.

Examples:

- Static constants
- Static lookup tables
- Class-level configuration
- Derived static state

---

## Internal Working

Understand the distinction:

```text
Class loading
    ↓
Linking
    ↓
Initialization
    ↓
<clinit>
```

At initialization, Java executes static field initializers and static initializer blocks according to textual order within the class, with superclass/interface initialization rules applying as specified by the JLS.

### Important

Do not equate:

> Class loading = class initialization.

They are different phases.

---

## Example

```java
class Demo {

    static int x = print("static field");

    static {
        print("static block");
    }

    static int print(String message) {
        System.out.println(message);
        return 10;
    }
}
```

---

## Runtime Behavior

A class is initialized when required by JVM/JLS initialization triggers.

Typical trigger:

```java
Demo demo = new Demo();
```

But not every use of a class necessarily triggers initialization.

Study:

- [ ] Active use
- [ ] Static field access
- [ ] Static method invocation
- [ ] Object creation
- [ ] Compile-time constants
- [ ] Class initialization lock

---

## Edge Cases

### Compile-Time Constants

```java
static final int VALUE = 10;
```

A compile-time constant can behave differently from an ordinary static field with respect to class initialization.

### Initialization Failure

If static initialization throws an exception, class initialization can fail and later use can result in initialization-related errors.

Master:

- [ ] `ExceptionInInitializerError`
- [ ] `NoClassDefFoundError`
- [ ] Initialization failure behavior

---

## Production Use Cases

Use static initialization carefully for:

- Immutable lookup tables
- Constants
- Lightweight class-level setup

Avoid:

- Network calls
- Database calls
- Slow I/O
- Complex startup logic

inside static initialization.

---

# 1.4.7 Instance Initialization Blocks

## What is it?

An instance initializer block runs as part of instance initialization.

```java
class Example {

    private int value;

    {
        value = 10;
        System.out.println("Instance block");
    }

    Example() {
        System.out.println("Constructor");
    }
}
```

---

## Why?

Historically useful for shared initialization logic among constructors.

Modern Java often prefers:

- Field initializers
- Constructor chaining
- Helper methods
- Factories
- Builders

---

## Ordering

Within a class, instance field initializers and instance initializer blocks execute in textual order during instance initialization.

Example:

```java
class Demo {

    int a = print("field a");

    {
        print("block 1");
    }

    int b = print("field b");

    {
        print("block 2");
    }

    Demo() {
        print("constructor");
    }

    int print(String text) {
        System.out.println(text);
        return 1;
    }
}
```

Expected order:

```text
field a
block 1
field b
block 2
constructor
```

---

## Common Mistakes

- [ ] Treating initializer blocks as static blocks
- [ ] Forgetting textual order
- [ ] Putting complex business logic in initializer blocks
- [ ] Using initializer blocks when constructor chaining is clearer

---

# 1.4.8 Constructor Execution Order

This is one of the most important Java interview and debugging topics.

Consider:

```java
class Parent {

    static {
        System.out.println("Parent static");
    }

    {
        System.out.println("Parent instance");
    }

    Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {

    static {
        System.out.println("Child static");
    }

    {
        System.out.println("Child instance");
    }

    Child() {
        System.out.println("Child constructor");
    }
}
```

When:

```java
new Child();
```

is executed for the first relevant use, the initialization sequence is conceptually:

```text
Parent class initialization
        ↓
Child class initialization
        ↓
Parent object initialization
        ↓
Parent constructor
        ↓
Child object initialization
        ↓
Child constructor
```

More precisely at the source level:

```text
Parent static field initializers / static blocks
        ↓
Child static field initializers / static blocks
        ↓
Parent instance field initializers / instance blocks
        ↓
Parent constructor
        ↓
Child instance field initializers / instance blocks
        ↓
Child constructor
```

---

# 1.4.9 Parent-to-Child Initialization Order

## Full Example

```java
class Parent {

    static int pStatic = print("Parent static field");

    static {
        print("Parent static block");
    }

    int pInstance = print("Parent instance field");

    {
        print("Parent instance block");
    }

    Parent() {
        print("Parent constructor");
    }

    static int print(String message) {
        System.out.println(message);
        return 1;
    }
}

class Child extends Parent {

    static int cStatic = print("Child static field");

    static {
        print("Child static block");
    }

    int cInstance = print("Child instance field");

    {
        print("Child instance block");
    }

    Child() {
        print("Child constructor");
    }
}
```

When the class hierarchy is initialized and a `Child` object is created, the output follows the initialization rules:

```text
Parent static field
Parent static block
Child static field
Child static block

Parent instance field
Parent instance block
Parent constructor

Child instance field
Child instance block
Child constructor
```

The exact output should always be verified by tracing the JLS rules rather than memorizing a slogan.

---

# 1.4.10 The Initialization Rule

A useful mental model:

```text
CLASS INITIALIZATION
--------------------

Parent static initialization
        ↓
Child static initialization


OBJECT INITIALIZATION
---------------------

Parent instance initialization
        ↓
Parent constructor
        ↓
Child instance initialization
        ↓
Child constructor
```

Within each class:

```text
Instance field initializers
        +
Instance initializer blocks
        ↓
Constructor body
```

And static members follow their textual initialization order.

---

# 1.4.11 `static block → instance initialization → constructor`

This shorthand is useful but incomplete.

A more accurate model is:

```text
First relevant class initialization
        ↓
Superclass static initialization
        ↓
Subclass static initialization
        ↓
Object creation
        ↓
Superclass instance initialization
        ↓
Superclass constructor
        ↓
Subclass instance initialization
        ↓
Subclass constructor
```

### Important

Do not blindly memorize:

```text
static → instance → constructor
```

because inheritance adds superclass/subclass ordering and static initialization has its own class-initialization rules.

---

# 1.4.12 Initialization Order — Multiple Objects

Consider:

```java
class Demo {

    static {
        System.out.println("static");
    }

    {
        System.out.println("instance");
    }

    Demo() {
        System.out.println("constructor");
    }
}
```

Then:

```java
new Demo();
new Demo();
```

Output:

```text
static
instance
constructor
instance
constructor
```

Why?

Because:

- Static initialization happens once per class initialization.
- Instance initialization happens once per object.

---

# 1.4.13 `static` vs Instance Initialization

| Feature | Static Initialization | Instance Initialization |
|---|---|---|
| Associated with | Class | Object |
| Runs | Once per class initialization | Once per object |
| Static fields | Yes | No |
| Instance fields | No | Yes |
| Static block | Yes | No |
| Instance block | No | Yes |
| Constructor | No | Yes |
| Typical use | Class-level state | Object state |

---

# 1.4.14 Field Initialization Order

Understand:

```java
class Demo {

    int a = 10;

    int b = a + 10;

    Demo() {
        System.out.println(a);
        System.out.println(b);
    }
}
```

Initialization occurs in declaration order.

This matters:

```java
int a = b; // problematic depending on declaration/definite assignment rules
int b = 10;
```

Master:

- [ ] Textual order
- [ ] Forward references
- [ ] Static forward references
- [ ] Instance initialization rules
- [ ] Definite assignment

---

# 1.4.15 Final Fields During Initialization

Master:

```java
class User {

    private final String id;

    User(String id) {
        this.id = id;
    }
}
```

Understand:

- [ ] Blank final fields
- [ ] Constructor assignment
- [ ] Initialization blocks
- [ ] Definite assignment
- [ ] Final field semantics
- [ ] Safe publication implications

### Production

Final fields are especially useful for immutable objects and correctly constructed state.

---

# 1.4.16 Constructor + Inheritance Trap

## Dangerous Example

```java
class Parent {

    Parent() {
        print();
    }

    void print() {
        System.out.println("Parent");
    }
}

class Child extends Parent {

    private String value = "initialized";

    @Override
    void print() {
        System.out.println(value);
    }
}
```

The parent constructor executes before the child's instance initialization.

Therefore the overridden method may observe the child in a partially initialized state.

### Rule

> Avoid calling overridable methods from constructors.

This is a major production-quality principle.

---

# 1.4.17 Constructor Exceptions

If a constructor throws:

```java
class User {

    User() {
        throw new RuntimeException();
    }
}
```

the object is not successfully returned to the caller.

Master:

- [ ] Partially initialized object
- [ ] Cleanup
- [ ] Resource ownership
- [ ] Exception propagation
- [ ] Constructor failure

### Production Rule

Avoid acquiring resources in constructors unless ownership and cleanup semantics are very clear.

Prefer explicit lifecycle management or dependency injection.

---

# 1.4.18 Initialization and Concurrency

Class initialization has strong JVM synchronization semantics.

Master:

- [ ] Class initialization lock
- [ ] Thread-safe class initialization
- [ ] Static initialization races
- [ ] Safe publication
- [ ] Singleton initialization
- [ ] Initialization-on-demand holder idiom

### Example

```java
class Singleton {

    private Singleton() {
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
```

Understand why class initialization makes this pattern useful.

---

# 1.4.19 Initialization and Memory Model

Deep-dive:

- [ ] Final field semantics
- [ ] Constructor completion
- [ ] Safe publication
- [ ] Happens-before
- [ ] Static initialization visibility
- [ ] Escaping `this`

Critical distinction:

```text
Object correctly constructed
        ≠
Object safely published to every thread
```

Study the Java Memory Model separately as part of concurrency.

---

# 1.4.20 Performance Implications

Analyze:

- [ ] Constructor cost
- [ ] Static startup cost
- [ ] Instance initializer cost
- [ ] Allocation cost
- [ ] Object graph creation
- [ ] Class initialization cost
- [ ] Lazy vs eager initialization
- [ ] JIT optimization
- [ ] Escape analysis
- [ ] GC impact

### Production Principle

Do not put expensive operations into:

```text
static initializer
constructor
instance initializer
```

unless startup/lifecycle semantics require them.

---

# 1.4.21 Production Use Cases

## Good Uses

### Static initialization

- Immutable lookup tables
- Constants
- Lightweight class-level configuration

### Constructors

- Required dependencies
- Validation
- Invariant establishment
- Immutable state initialization

### Constructor chaining

- Centralized initialization logic

### `this`

- Field disambiguation
- Fluent APIs
- Constructor delegation

### `super`

- Parent initialization
- Extending parent behavior

---

## Avoid

- [ ] Network calls in constructors
- [ ] Database calls in constructors
- [ ] Starting threads in constructors
- [ ] Registering `this` before construction completes
- [ ] Complex static initialization
- [ ] Hidden initialization side effects
- [ ] Overusing initializer blocks

---

# 1.4.22 Common Initialization Anti-Patterns

## Anti-Pattern 1 — Heavy Constructor

```text
new Service()
   ↓
Database connection
   ↓
HTTP request
   ↓
File loading
```

Problem:

- Slow construction
- Difficult testing
- Hidden side effects
- Failure complexity

---

## Anti-Pattern 2 — Static Initialization Doing I/O

```java
static {
    loadFromRemoteServer();
}
```

Problems:

- Startup failures
- Slow class initialization
- Difficult diagnostics
- Unexpected initialization triggers

---

## Anti-Pattern 3 — Leaking `this`

```java
class Service {

    Service(Registry registry) {
        registry.register(this);
    }
}
```

Potentially exposes an incompletely initialized object.

---

## Anti-Pattern 4 — Calling Overridable Methods

```java
Parent() {
    initialize();
}
```

A subclass override can execute before subclass initialization completes.

---

# 1.4.23 Debugging Initialization Order

When debugging unexpected output, create a trace:

```java
static void trace(String message) {
    System.out.println(
        Thread.currentThread().getName() + " : " + message
    );
}
```

Then trace:

- [ ] Static fields
- [ ] Static blocks
- [ ] Instance fields
- [ ] Instance blocks
- [ ] Constructors
- [ ] Parent constructor
- [ ] Child constructor

---

# 1.4.24 Integrated Initialization Exercise

Create:

```text
Parent
   ↓
Vehicle
   ↓
Car
```

Each class must contain:

- [ ] Static field initializer
- [ ] Static block
- [ ] Instance field initializer
- [ ] Instance block
- [ ] Constructor
- [ ] Overridden method

Then create:

```java
new Car();
new Car();
```

Before running:

1. [ ] Predict the exact output.
2. [ ] Explain every line.
3. [ ] Run the program.
4. [ ] Compare prediction with output.
5. [ ] Explain why static initialization does not repeat.
6. [ ] Explain why instance initialization repeats.
7. [ ] Add another subclass.
8. [ ] Repeat the exercise.

---

# 1.4.25 Advanced Coding Exercises

## Basic

- [ ] Create a class with constructor and `this`.
- [ ] Create overloaded constructors.
- [ ] Implement constructor chaining.
- [ ] Use `super()` explicitly.
- [ ] Add static and instance initialization blocks.

## Intermediate

- [ ] Predict initialization order for a three-level hierarchy.
- [ ] Demonstrate multiple object creation.
- [ ] Demonstrate static initialization failure.
- [ ] Demonstrate constructor exception handling.
- [ ] Demonstrate field initialization order.

## Advanced

- [ ] Demonstrate `this` escaping from a constructor.
- [ ] Demonstrate calling an overridable method from a constructor.
- [ ] Demonstrate parent-to-child initialization order.
- [ ] Investigate class initialization with multiple threads.
- [ ] Implement the initialization-on-demand holder singleton.

## Production-Style

Build a configuration system with:

```text
Application
    ↓
Configuration
    ↓
DatabaseConfig
    ↓
CacheConfig
```

Requirements:

- [ ] Immutable configuration state.
- [ ] Constructor validation.
- [ ] Constructor chaining where useful.
- [ ] No heavy I/O in constructors.
- [ ] Safe initialization.
- [ ] Thread-safe singleton if appropriate.
- [ ] Clear initialization lifecycle.
- [ ] Tests for initialization failures.
- [ ] Tests for multiple instances.
- [ ] Startup performance measurement.

---

# 1.4.26 Master Interview Question Bank

## Basic

- [ ] What is `this`?
- [ ] What is `super`?
- [ ] What is a constructor?
- [ ] Can constructors be overloaded?
- [ ] Can constructors be overridden?
- [ ] What is constructor chaining?
- [ ] What is a static block?
- [ ] What is an instance initialization block?

## Intermediate

- [ ] `this()` vs `super()`?
- [ ] Why must `this()` or `super()` appear first?
- [ ] What happens if you don't explicitly call `super()`?
- [ ] When does a static block execute?
- [ ] When does an instance initialization block execute?
- [ ] How many times does a static block run?
- [ ] How many times does an instance block run?

## Advanced

- [ ] Explain the complete parent-child initialization order.
- [ ] What happens before a constructor body executes?
- [ ] What is the difference between class loading and class initialization?
- [ ] What is `<clinit>`?
- [ ] What happens if static initialization fails?
- [ ] Why is calling an overridable method from a constructor dangerous?
- [ ] Why is leaking `this` from a constructor dangerous?
- [ ] What are default values of instance fields before explicit initialization?

## Senior / Production

- [ ] How does class initialization behave with multiple threads?
- [ ] How does the JVM ensure class initialization safety?
- [ ] How would you diagnose a production startup failure caused by static initialization?
- [ ] Why should constructors generally avoid I/O?
- [ ] How can constructor design affect object allocation and GC?
- [ ] How do final fields relate to safe publication?
- [ ] How would you design lazy initialization safely?
- [ ] Compare eager initialization, lazy initialization and initialization-on-demand holder.
- [ ] How would you debug a partially initialized object?
- [ ] What initialization behavior can cause unexpected startup latency?

---

# 1.4.27 Deep-Dive Experiments

Perform each experiment without looking at the answer first.

## Experiment 1 — Basic Order

```java
class Demo {

    static {
        System.out.println("static");
    }

    {
        System.out.println("instance");
    }

    Demo() {
        System.out.println("constructor");
    }
}
```

Predict:

```java
new Demo();
new Demo();
```

---

## Experiment 2 — Parent and Child

Create:

```text
Parent
Child
```

with:

- Static fields
- Static blocks
- Instance fields
- Instance blocks
- Constructors

Predict the output.

---

## Experiment 3 — Constructor Chaining

Create three constructors:

```java
A()
A(int)
A(int, int)
```

Make every constructor delegate to one canonical constructor.

---

## Experiment 4 — `this()` vs `super()`

Create a parent-child hierarchy and trace constructor calls.

---

## Experiment 5 — Static Initialization Failure

Create a static initializer that throws.

Observe:

- First failure
- Subsequent class use
- Exception type
- JVM behavior

---

## Experiment 6 — Constructor Dispatch Trap

Create a parent constructor that calls an overridable method.

Observe the child state.

Explain why it is dangerous.

---

## Experiment 7 — `this` Escape

Register `this` with another object during construction.

Then inspect whether the receiver can observe incomplete state.

---

## Experiment 8 — Multi-Threaded Initialization

Trigger class initialization from multiple threads.

Investigate:

- [ ] Whether initialization occurs once
- [ ] Which thread performs initialization
- [ ] What other threads observe
- [ ] What happens if initialization fails

---

# 1.4.28 OpenJDK / JVM Deep Dive

After mastering source-level behavior, investigate:

- [ ] `javac` constructor bytecode
- [ ] `invokespecial`
- [ ] `invokestatic`
- [ ] `<init>`
- [ ] `<clinit>`
- [ ] Class initialization
- [ ] JVM initialization lock
- [ ] Object allocation
- [ ] Field initialization
- [ ] JIT compilation

Useful commands:

```bash
javap -c -p YourClass
```

```bash
javap -v YourClass
```

Use them to inspect constructor and static initialization bytecode.

---

# 1.4.29 Specifications to Study

## Java Language Specification

Study the relevant sections covering:

- [ ] Class initialization
- [ ] Object creation
- [ ] Constructors
- [ ] Constructor invocation
- [ ] Initialization of fields
- [ ] Instance initialization
- [ ] Static initialization
- [ ] `this`
- [ ] `super`

## JVM Specification

Study:

- [ ] Object creation
- [ ] Class initialization
- [ ] Method invocation
- [ ] `<init>`
- [ ] `<clinit>`
- [ ] JVM runtime data areas

---

# 1.4.30 Final Mastery Gate

Do **not** mark Module 1.4 complete until you can:

- [ ] Explain `this`.
- [ ] Explain `super`.
- [ ] Explain constructors.
- [ ] Explain constructor overloading.
- [ ] Implement constructor chaining.
- [ ] Explain `this(...)`.
- [ ] Explain `super(...)`.
- [ ] Explain static initialization.
- [ ] Explain instance initialization blocks.
- [ ] Explain field initialization order.
- [ ] Explain constructor execution order.
- [ ] Explain parent-to-child initialization order.
- [ ] Explain the relationship between static initialization, instance initialization and constructors.
- [ ] Explain why static initialization happens once.
- [ ] Explain why instance initialization happens per object.
- [ ] Explain class initialization vs class loading.
- [ ] Explain `<init>` and `<clinit>` at a high level.
- [ ] Explain initialization failure.
- [ ] Explain `this` escaping.
- [ ] Explain constructor dispatch traps.
- [ ] Explain final-field initialization.
- [ ] Explain initialization under concurrency.
- [ ] Debug complex initialization-order problems.
- [ ] Predict initialization output before executing code.
- [ ] Inspect constructor bytecode with `javap`.
- [ ] Discuss performance implications.
- [ ] Discuss production trade-offs.
- [ ] Design safe initialization in a production application.
- [ ] Answer basic → senior interview questions.

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
