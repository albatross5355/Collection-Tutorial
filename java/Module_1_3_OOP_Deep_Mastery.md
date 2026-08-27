# Module 1.3 — Object-Oriented Programming (OOP)
## Deep Mastery Guide

> **Prerequisite:** Module 1.2 — Language Fundamentals  
> **Goal:** Move from knowing Java syntax to being able to design, implement, debug, optimize, and explain object-oriented Java code at a senior/production level.

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

# 1.3.1 Classes and Objects

## 1. What is it?

### Class

A class is a Java type that defines a structure and behavior for objects.

A class can contain:

- [ ] Fields
- [ ] Methods
- [ ] Constructors
- [ ] Initializer blocks
- [ ] Nested classes/interfaces
- [ ] Static members
- [ ] Instance members

### Object

An object is a runtime instance of a class.

```java
class User {
    String name;

    void greet() {
        System.out.println("Hello " + name);
    }
}

User user = new User();
user.name = "Alex";
user.greet();
```

### Core Terminology

- [ ] Class
- [ ] Object
- [ ] Instance
- [ ] Field
- [ ] Method
- [ ] Constructor
- [ ] State
- [ ] Behavior
- [ ] Identity
- [ ] Reference
- [ ] Instance member
- [ ] Static member

---

## 2. Why does Java have classes and objects?

### Problems they solve

- Encapsulating state and behavior
- Modeling domain concepts
- Reusing behavior
- Controlling access
- Supporting polymorphism
- Creating abstractions

### Design Motivation

Java's object model allows:

```text
State + Behavior + Identity
           ↓
         Object
```

### Alternatives

- Primitive types
- Records
- Enums
- Static utility classes
- Functional programming
- Data-oriented designs

Understand **when a class is appropriate and when it is unnecessary abstraction**.

---

## 3. Syntax and API

```java
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello " + name);
    }
}
```

Create an object:

```java
User user = new User("Alex");
```

### Important Keywords

- `class`
- `new`
- `this`
- `static`
- `final`
- `abstract`
- `extends`
- `implements`

---

## 4. Basic Example

```java
class BankAccount {

    private double balance;

    BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    void deposit(double amount) {
        balance += amount;
    }

    double getBalance() {
        return balance;
    }
}
```

Usage:

```java
BankAccount account = new BankAccount(1000);

account.deposit(500);

System.out.println(account.getBalance());
```

### Modify

- [ ] Add withdrawal.
- [ ] Add validation.
- [ ] Add account ID.
- [ ] Make account ID immutable.
- [ ] Add multiple account objects.
- [ ] Compare object identity.

---

## 5. Internal Working

Understand:

```text
new BankAccount(...)
        ↓
Class initialization if required
        ↓
Memory allocated for object
        ↓
Fields initialized
        ↓
Constructor executes
        ↓
Reference returned
```

Understand the distinction between:

```java
BankAccount account;
```

and:

```java
BankAccount account = new BankAccount();
```

The first declares a reference variable.

The second creates an object and assigns its reference.

### Advanced

- [ ] Object header concept
- [ ] Instance fields
- [ ] Class metadata
- [ ] Method metadata
- [ ] Constructor invocation
- [ ] Reference representation
- [ ] JVM object allocation
- [ ] Escape analysis
- [ ] Scalar replacement

---

## 6. Memory / Runtime Behavior

Understand conceptually:

```text
Stack Frame
   |
   | account reference
   ↓
Heap
   |
   +---- BankAccount object
          |
          +---- balance
```

But do not reduce JVM memory behavior to the simplistic rule:

> "Objects are always on the heap and references are always on the stack."

JIT optimizations can change physical runtime representation.

Master:

- [ ] Object allocation
- [ ] Reference variables
- [ ] Instance fields
- [ ] Class metadata
- [ ] Object lifetime
- [ ] Garbage collection
- [ ] Escape analysis
- [ ] Thread-local allocation concepts

---

## 7. Edge Cases

- [ ] `null` references
- [ ] Multiple references to the same object
- [ ] Object aliasing
- [ ] Constructor failure
- [ ] Recursive object relationships
- [ ] Mutable shared objects
- [ ] Object equality
- [ ] Object identity
- [ ] Circular references

---

## 8. Common Mistakes

- [ ] Confusing object with reference
- [ ] Comparing objects with `==` when value equality is required
- [ ] Exposing mutable fields directly
- [ ] Creating unnecessary classes
- [ ] Making everything `static`
- [ ] Creating classes with too many responsibilities

---

## 9. Performance Implications

- Object allocation
- Allocation rate
- GC pressure
- Object size
- Cache locality
- Escape analysis
- Boxing

---

## 10. Production Use Cases

Classes are used to represent:

- Domain entities
- Services
- DTOs
- Configuration
- Clients
- Repositories
- Controllers
- Infrastructure components

Avoid:

- God classes
- Excessive inheritance
- Mutable shared state
- Unnecessary object graphs

---

## 11. Interview Questions

### Basic

- [ ] What is a class?
- [ ] What is an object?
- [ ] Class vs object?

### Intermediate

- [ ] What happens when `new` is executed?
- [ ] What is object identity?

### Advanced

- [ ] What happens if object construction throws an exception?
- [ ] Can two references point to the same object?

### Senior / Production

- [ ] How does excessive object allocation affect GC?
- [ ] How would you reduce allocation pressure in a high-throughput service?

---

# 1.3.2 Encapsulation

## 1. What is it?

Encapsulation means controlling access to an object's internal state and exposing a well-defined API for interacting with it.

```java
class BankAccount {

    private double balance;

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
```

---

## 2. Why does Java have it?

Encapsulation protects invariants.

Without encapsulation:

```java
account.balance = -100000;
```

With encapsulation:

```java
account.withdraw(100);
```

The object controls whether the operation is valid.

### Design Motivation

- [ ] Information hiding
- [ ] Invariant protection
- [ ] API stability
- [ ] Reduced coupling
- [ ] Maintainability

### Alternatives

- Public mutable fields
- Immutable data structures
- Records
- Functional data transformations

---

## 3. Syntax and API

Important mechanisms:

- [ ] `private`
- [ ] `protected`
- [ ] package-private
- [ ] `public`
- [ ] Getters/setters
- [ ] Domain methods
- [ ] Defensive copies

### Prefer Behavior Over Blind Getters/Setters

Instead of:

```java
account.setBalance(account.getBalance() + amount);
```

Prefer:

```java
account.deposit(amount);
```

---

## 4. Basic Example

```java
class Temperature {

    private double celsius;

    public void setCelsius(double celsius) {
        if (celsius < -273.15) {
            throw new IllegalArgumentException("Below absolute zero");
        }

        this.celsius = celsius;
    }

    public double getCelsius() {
        return celsius;
    }
}
```

---

## 5. Internal Working

Encapsulation is primarily a **language-level access-control mechanism**.

The compiler checks access rules.

At runtime, JVM access checks also exist.

Understand:

- [ ] Access flags
- [ ] Compiler access checking
- [ ] JVM access checking
- [ ] Reflection
- [ ] `MethodHandles`

---

## 6. Memory / Runtime Behavior

Encapsulation itself does not create special memory.

The object still contains its fields.

The key effect is on **who can access and mutate that state**.

---

## 7. Edge Cases

- [ ] Mutable getter results
- [ ] Defensive copying
- [ ] Collections returned from getters
- [ ] Reflection
- [ ] Package-private access
- [ ] `protected` behavior across packages

Example problem:

```java
class User {
    private final List<String> roles;

    public List<String> getRoles() {
        return roles;
    }
}
```

The caller can mutate internal state.

---

## 8. Common Mistakes

- [ ] Making every field public
- [ ] Creating getters/setters for everything
- [ ] Returning mutable internal collections
- [ ] Using inheritance to bypass encapsulation
- [ ] Treating encapsulation as merely "private fields + getters"

---

## 9. Performance Implications

- Method calls may be inlined by the JIT.
- Good encapsulation usually does not imply meaningful runtime overhead.
- Defensive copying can have allocation costs.

---

## 10. Production Use Cases

Essential for:

- Domain models
- Financial transactions
- Security-sensitive state
- Configuration
- API boundaries
- Thread-safe objects

---

## 11. Interview Questions

### Basic

- [ ] What is encapsulation?
- [ ] Why use private fields?

### Intermediate

- [ ] Encapsulation vs abstraction?
- [ ] Why are getters/setters not always good encapsulation?

### Advanced

- [ ] How can a getter break encapsulation?
- [ ] What is defensive copying?

### Senior

- [ ] How would you design an immutable, encapsulated domain object?
- [ ] How does encapsulation reduce coupling?

---

# 1.3.3 Inheritance

## 1. What is it?

Inheritance allows a class to derive from another class.

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}
```

---

## 2. Why does Java have it?

Inheritance provides:

- Reuse
- Specialization
- Polymorphism
- Hierarchical modeling

### Important Warning

Inheritance is not primarily a code-reuse mechanism.

It creates a strong relationship between parent and child types.

---

## 3. Syntax

```java
class Child extends Parent {
}
```

Java supports:

- [ ] Single class inheritance
- [ ] Multilevel inheritance
- [ ] Hierarchical inheritance

Java does **not** support multiple inheritance of classes.

Interfaces provide a form of multiple type inheritance.

---

## 4. Basic Example

```java
class Vehicle {
    void start() {
        System.out.println("Vehicle starting");
    }
}

class Car extends Vehicle {
    void drive() {
        System.out.println("Car driving");
    }
}
```

---

## 5. Internal Working

Understand:

```text
Car object
   ↓
Car class metadata
   ↓
Superclass relationship
   ↓
Inherited members
```

Understand:

- [ ] `extends`
- [ ] `super`
- [ ] Constructor chaining
- [ ] Method overriding
- [ ] Runtime method dispatch
- [ ] Class hierarchy

---

## 6. Memory / Runtime Behavior

A subclass object contains the state required by its superclass plus its own instance state.

Conceptually:

```text
Car object
├── Vehicle state
└── Car state
```

Do not assume that inheritance means the object contains a separate Java object for the parent.

---

## 7. Edge Cases

- [ ] Constructor ordering
- [ ] Private members are not inherited as accessible members
- [ ] Final classes
- [ ] Final methods
- [ ] Protected members
- [ ] Overridden methods
- [ ] Field hiding
- [ ] Static method hiding

---

## 8. Common Mistakes

- [ ] Using inheritance only for code reuse
- [ ] Deep inheritance trees
- [ ] Violating Liskov Substitution
- [ ] Calling overridable methods from constructors
- [ ] Confusing field hiding with method overriding

---

## 9. Performance Implications

- Virtual dispatch can have runtime cost, although JIT optimization can often inline calls.
- Deep hierarchies increase complexity more than they necessarily increase runtime cost.
- Poor inheritance design can create difficult optimization and maintenance problems.

---

## 10. Production Use Cases

Good use cases:

- Framework extension points
- Stable type hierarchies
- Domain taxonomies where substitutability is real
- Template Method pattern

Avoid inheritance when composition models the relationship better.

---

## 11. Interview Questions

### Basic

- [ ] What is inheritance?
- [ ] Does Java support multiple inheritance?

### Intermediate

- [ ] What is constructor chaining?
- [ ] What does `super` do?

### Advanced

- [ ] Are private members inherited?
- [ ] What is field hiding?
- [ ] What is method overriding?

### Senior

- [ ] Why is composition often preferred over inheritance?
- [ ] What inheritance problems can violate Liskov Substitution?

---

# 1.3.4 Polymorphism

## 1. What is it?

Polymorphism means that the same interface/type can represent different concrete implementations and that behavior can vary according to the actual object.

```java
Animal animal = new Dog();
animal.speak();
```

The reference type is `Animal`.

The actual object is `Dog`.

---

## 2. Why does Java have it?

Polymorphism enables:

- Extensibility
- Loose coupling
- Programming to abstractions
- Dependency inversion
- Plugin architectures
- Strategy implementations

---

## 3. Types of Polymorphism

### Compile-Time

Usually associated with:

- Method overloading
- Generic type resolution
- Compile-time type selection

### Runtime

Usually associated with:

- Method overriding
- Dynamic method dispatch
- Interface dispatch
- Abstract type references

---

# 1.3.5 Compile-Time Polymorphism — Method Overloading

## Syntax

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

The compiler chooses the applicable overload.

---

## Internal Working

Understand:

```text
Source code
    ↓
Compiler
    ↓
Overload resolution
    ↓
Selected method signature
    ↓
Bytecode
```

The runtime generally does not decide which overloaded method is meant based on the object's runtime type.

---

## Edge Cases

Master overload resolution involving:

- [ ] Exact match
- [ ] Widening
- [ ] Boxing
- [ ] Unboxing
- [ ] Varargs
- [ ] `null`
- [ ] Ambiguous overloads
- [ ] Reference types

### Example

```java
void test(Object value) {}
void test(String value) {}

test(null);
```

Understand why `String` is selected.

Then:

```java
void test(String value) {}
void test(Integer value) {}

test(null);
```

This is ambiguous.

---

## Common Mistakes

- [ ] Calling overloading runtime polymorphism
- [ ] Assuming return type alone can overload a method
- [ ] Ignoring boxing/widening/varargs rules

---

## Interview Questions

- [ ] What is method overloading?
- [ ] Can methods overload only by return type?
- [ ] Is overloading compile-time or runtime polymorphism?
- [ ] How does Java choose between overloaded methods?

---

# 1.3.6 Runtime Polymorphism — Method Overriding

## Basic Example

```java
class Animal {
    void speak() {
        System.out.println("Animal");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Dog");
    }
}
```

```java
Animal animal = new Dog();
animal.speak();
```

Output:

```text
Dog
```

---

## Internal Working

The important distinction:

```text
Reference type
Animal
   ↓
Compiler checks whether speak() is valid

Actual object
Dog
   ↓
Runtime selects Dog.speak()
```

This is **dynamic method dispatch**.

---

# 1.3.7 Dynamic Method Dispatch

## Mastery

- [ ] Virtual method invocation
- [ ] Runtime receiver type
- [ ] Method overriding
- [ ] Interface dispatch
- [ ] `invokevirtual`
- [ ] `invokeinterface`
- [ ] Static methods
- [ ] Private methods
- [ ] Final methods

### Conceptual Flow

```text
Animal animal = new Dog();

animal.speak();
     ↓
Compiler
     ↓
Is speak() valid on Animal?
     ↓
Yes
     ↓
Runtime
     ↓
Actual object = Dog
     ↓
Dog.speak()
```

---

## Important Exceptions

Not every method call is dynamically dispatched in the same way.

Study separately:

- [ ] Static methods
- [ ] Private methods
- [ ] Final methods
- [ ] Constructors
- [ ] `super.method()`

### Field Access

Fields are not polymorphic like overridden instance methods.

```java
class Parent {
    int value = 1;
}

class Child extends Parent {
    int value = 2;
}

Parent p = new Child();

System.out.println(p.value);
```

Understand why the result differs from method overriding.

---

## Performance

- Virtual calls can be optimized by the JIT.
- Monomorphic call sites are especially easy to optimize.
- Polymorphic/megamorphic call sites can be more challenging.
- Do not prematurely avoid polymorphism for performance reasons.

---

# 1.3.8 Method Overriding

## Mastery

- [ ] Same method signature
- [ ] Compatible return type
- [ ] Visibility rules
- [ ] Checked exception rules
- [ ] `@Override`
- [ ] Covariant returns
- [ ] Final methods
- [ ] Static method hiding
- [ ] Private methods
- [ ] Constructor behavior

### Example

```java
class Parent {
    Number getValue() {
        return 10;
    }
}

class Child extends Parent {
    @Override
    Integer getValue() {
        return 20;
    }
}
```

This demonstrates a covariant return type.

---

## Edge Cases

- [ ] Cannot reduce visibility
- [ ] Cannot override `final`
- [ ] Static methods are hidden, not overridden
- [ ] Private methods are not overridden
- [ ] Constructors cannot be overridden
- [ ] Checked exception restrictions

---

# 1.3.9 Abstraction

## 1. What is it?

Abstraction exposes essential behavior while hiding unnecessary implementation details.

Example:

```java
interface PaymentProcessor {
    void process(Payment payment);
}
```

The caller needs to know **what** the processor does, not necessarily **how** it does it.

---

## 2. Why does Java have it?

Abstraction reduces coupling.

It allows:

```text
Caller
  ↓
Abstraction
  ↓
Implementation
```

instead of:

```text
Caller
  ↓
Concrete implementation details
```

---

## 3. Mechanisms

Java provides abstraction through:

- [ ] Interfaces
- [ ] Abstract classes
- [ ] Encapsulation
- [ ] Access modifiers

---

## 4. Production Example

```java
interface PaymentProcessor {
    void process(double amount);
}

class StripePaymentProcessor implements PaymentProcessor {
    @Override
    public void process(double amount) {
        // Stripe integration
    }
}

class PaymentService {

    private final PaymentProcessor processor;

    PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }

    void pay(double amount) {
        processor.process(amount);
    }
}
```

The service depends on an abstraction rather than a concrete payment provider.

---

# 1.3.10 Interfaces

## What is it?

An interface defines a contract/type that classes can implement.

```java
interface Flyable {
    void fly();
}
```

```java
class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Flying");
    }
}
```

---

## Modern Interface Features

Master:

- [ ] Abstract methods
- [ ] `default` methods
- [ ] `static` methods
- [ ] Private interface methods
- [ ] Functional interfaces
- [ ] Multiple interface inheritance

### Example

```java
interface Logger {

    void log(String message);

    default void info(String message) {
        log("INFO: " + message);
    }
}
```

---

## Internal Working

Understand:

- [ ] Interface metadata
- [ ] `implements`
- [ ] Interface method dispatch
- [ ] `invokeinterface`
- [ ] Default method resolution
- [ ] Multiple interface inheritance
- [ ] Diamond/default-method conflicts

---

## Edge Cases

### Default Method Conflict

```java
interface A {
    default void test() {}
}

interface B {
    default void test() {}
}
```

A class implementing both must resolve the conflict.

---

## Production Use Cases

Interfaces are heavily used for:

- Dependency injection
- Plugin systems
- Strategy pattern
- Ports and adapters
- Testing seams
- Framework extension points

---

## Common Mistakes

- [ ] Creating interfaces for every class without a real abstraction
- [ ] Treating interfaces as merely "collections of methods"
- [ ] Excessive abstraction
- [ ] Ignoring default method conflicts

---

# 1.3.11 Abstract Classes

## What is it?

An abstract class is a class that cannot be instantiated directly and can contain both abstract and concrete behavior.

```java
abstract class Animal {

    abstract void speak();

    void eat() {
        System.out.println("Eating");
    }
}
```

---

## Why?

Use when related subclasses share:

- State
- Implementation
- Protected behavior
- Common invariants
- A common conceptual base

---

## Mastery

- [ ] Abstract methods
- [ ] Concrete methods
- [ ] Constructors
- [ ] Fields
- [ ] Static members
- [ ] Final methods
- [ ] Protected methods
- [ ] Abstract class references

---

## Interface vs Abstract Class

| Feature | Interface | Abstract Class |
|---|---|---|
| Multiple inheritance | Yes, interfaces | No |
| Instance fields | No ordinary instance state | Yes |
| Constructors | No | Yes |
| Concrete methods | Yes | Yes |
| Abstract methods | Yes | Yes |
| State sharing | Limited | Strong |
| Best for | Contract/capability | Shared base behavior |

Do not memorize the table alone. Learn the **design decision** behind each choice.

---

# 1.3.12 Composition vs Inheritance

## Composition

Composition means an object contains or delegates to another object.

```java
class Car {

    private final Engine engine;

    Car(Engine engine) {
        this.engine = engine;
    }

    void start() {
        engine.start();
    }
}
```

This represents:

```text
Car
 ↓
HAS-A
 ↓
Engine
```

---

## Inheritance

```java
class Dog extends Animal {
}
```

Represents:

```text
Dog
 ↓
IS-A
 ↓
Animal
```

---

## Why Composition Is Often Preferred

Composition provides:

- Lower coupling
- Runtime flexibility
- Easier testing
- Better separation of responsibilities
- Easier replacement of dependencies

Inheritance provides:

- Type substitution
- Shared implementation
- Polymorphism
- Framework extension

---

## Decision Framework

Ask:

### Is it genuinely an "IS-A" relationship?

If yes, inheritance may be appropriate.

### Does one object use another object?

Prefer composition.

### Do you need runtime replacement?

Composition is usually better.

### Do subclasses need to obey a parent contract?

Inheritance may be appropriate.

---

# 1.3.13 "Is-A" vs "Has-A"

## IS-A

Usually represented by inheritance or interface implementation.

```java
class Dog extends Animal {
}
```

Dog **is an** Animal.

```java
class StripePaymentProcessor implements PaymentProcessor {
}
```

StripePaymentProcessor **is a** PaymentProcessor.

---

## HAS-A

Usually represented by composition.

```java
class Car {
    private Engine engine;
}
```

Car **has an** Engine.

---

## Critical Edge Case

Do not model relationships solely based on English grammar.

The real question is:

> **Does the subtype satisfy the behavioral contract of the supertype?**

This connects directly to the **Liskov Substitution Principle**.

---

# 1.3.14 OOP Design Decision Matrix

| Requirement | Prefer |
|---|---|
| Shared contract | Interface |
| Shared state + behavior | Abstract class |
| Replaceable dependency | Composition |
| True subtype relationship | Inheritance |
| Multiple capabilities | Interfaces |
| Runtime strategy replacement | Composition + interface |
| Common implementation across related types | Abstract class |
| Independent reusable component | Composition |

---

# 1.3.15 Integrated OOP Project

Build a **Payment Processing System**.

## Domain

```text
Payment
 ├── id
 ├── amount
 └── status

PaymentProcessor
 ├── process()
 └── refund()

Implementations
 ├── CardPaymentProcessor
 ├── UpiPaymentProcessor
 └── BankTransferProcessor
```

## Requirements

- [ ] Create classes.
- [ ] Encapsulate fields.
- [ ] Create constructors.
- [ ] Use interfaces.
- [ ] Use an abstract class where justified.
- [ ] Demonstrate inheritance.
- [ ] Demonstrate method overloading.
- [ ] Demonstrate method overriding.
- [ ] Demonstrate runtime polymorphism.
- [ ] Demonstrate dynamic method dispatch.
- [ ] Demonstrate composition.
- [ ] Explain every "IS-A" and "HAS-A" relationship.
- [ ] Avoid unnecessary inheritance.
- [ ] Use constructor injection for dependencies.

### Example Architecture

```text
PaymentService
      |
      ↓
PaymentProcessor
      |
      +---- CardPaymentProcessor
      |
      +---- UpiPaymentProcessor
      |
      +---- BankTransferProcessor
```

---

# 1.3.16 Advanced OOP Debugging Exercises

Create deliberate bugs and diagnose them.

- [ ] Field hiding bug
- [ ] Static method hiding bug
- [ ] Incorrect override
- [ ] Missing `@Override`
- [ ] Constructor calling overridable method
- [ ] Null polymorphic reference
- [ ] Invalid downcast
- [ ] Default-method conflict
- [ ] Broken encapsulation
- [ ] Mutable internal collection exposure
- [ ] Incorrect inheritance hierarchy
- [ ] God class
- [ ] Excessive inheritance

---

# 1.3.17 Performance & JVM Follow-ups

After understanding OOP behavior, study:

- [ ] Object allocation
- [ ] Object headers
- [ ] References
- [ ] Escape analysis
- [ ] Scalar replacement
- [ ] JIT inlining
- [ ] Virtual calls
- [ ] Interface calls
- [ ] Monomorphic call sites
- [ ] Polymorphic call sites
- [ ] Megamorphic call sites
- [ ] Devirtualization
- [ ] GC impact of object graphs

### Important Principle

Do not sacrifice good object-oriented design merely because a method is virtual.

First measure.

Then optimize the actual bottleneck.

---

# 1.3.18 Advanced Follow-ups

## Internal Implementation

- [ ] Object layout
- [ ] Class metadata
- [ ] Method tables
- [ ] Virtual dispatch
- [ ] Interface dispatch
- [ ] Bytecode invocation instructions
- [ ] JIT devirtualization

## JVM Behavior

- [ ] `invokevirtual`
- [ ] `invokeinterface`
- [ ] `invokespecial`
- [ ] `invokestatic`
- [ ] Class initialization
- [ ] JIT compilation

## Performance Tuning

- [ ] Allocation profiling
- [ ] JFR
- [ ] Async-profiler
- [ ] GC analysis
- [ ] JIT compilation logs

## Concurrency

- [ ] Immutable objects
- [ ] Shared mutable state
- [ ] Thread-safe domain objects
- [ ] Safe publication
- [ ] Synchronization around object state

## Production Architecture

- [ ] Dependency inversion
- [ ] Ports and adapters
- [ ] Strategy pattern
- [ ] Repository abstraction
- [ ] Service abstraction
- [ ] DTO vs domain object
- [ ] Entity vs value object

## Specifications

- [ ] JLS — Classes
- [ ] JLS — Objects
- [ ] JLS — Inheritance
- [ ] JLS — Interfaces
- [ ] JLS — Method invocation
- [ ] JVM Specification — Object model
- [ ] JVM Specification — Method invocation

---

# 1.3.19 Master Interview Question Bank

## Basic

- [ ] What is a class?
- [ ] What is an object?
- [ ] What is encapsulation?
- [ ] What is inheritance?
- [ ] What is polymorphism?
- [ ] What is abstraction?
- [ ] What is an interface?
- [ ] What is an abstract class?
- [ ] What is method overloading?
- [ ] What is method overriding?

## Intermediate

- [ ] Overloading vs overriding?
- [ ] Interface vs abstract class?
- [ ] Composition vs inheritance?
- [ ] What is an IS-A relationship?
- [ ] What is a HAS-A relationship?
- [ ] What is constructor chaining?
- [ ] What is dynamic method dispatch?
- [ ] Can Java support multiple inheritance?
- [ ] Why does Java not support multiple class inheritance?

## Advanced

- [ ] How does runtime polymorphism work?
- [ ] What is the difference between field hiding and method overriding?
- [ ] Are static methods overridden?
- [ ] Are private methods overridden?
- [ ] Why can a final method not be overridden?
- [ ] How are overloaded methods resolved?
- [ ] What happens when two interfaces provide conflicting default methods?
- [ ] What bytecode instructions are associated with method invocation?
- [ ] How does the JIT optimize virtual calls?

## Senior / Production

- [ ] When should you prefer composition over inheritance?
- [ ] How can inheritance violate Liskov Substitution?
- [ ] When is an interface unnecessary?
- [ ] How would you design a pluggable payment system?
- [ ] How would you avoid a deep inheritance hierarchy?
- [ ] How can excessive object allocation affect a high-throughput Java service?
- [ ] How does polymorphism affect performance in theory and in practice?
- [ ] How would you debug unexpected dispatch behavior?
- [ ] How would you design immutable domain objects?
- [ ] How would you decide between interface, abstract class and composition in a production system?

---

# 1.3.20 Coding Exercises

## Basic

- [ ] Create a `Person` class.
- [ ] Create a `BankAccount` class.
- [ ] Implement encapsulated fields.
- [ ] Implement constructors.
- [ ] Implement getter/setter validation.
- [ ] Create an `Animal` hierarchy.
- [ ] Demonstrate method overloading.
- [ ] Demonstrate method overriding.

## Intermediate

- [ ] Build a shape hierarchy.
- [ ] Implement `Shape` as an interface.
- [ ] Implement `Circle`, `Rectangle`, and `Triangle`.
- [ ] Demonstrate runtime polymorphism.
- [ ] Implement an abstract `PaymentProcessor`.
- [ ] Demonstrate constructor chaining.
- [ ] Demonstrate default interface methods.
- [ ] Demonstrate composition using `Car` + `Engine`.

## Advanced

- [ ] Build a pluggable payment system.
- [ ] Build a notification abstraction with Email/SMS/Push implementations.
- [ ] Build a storage abstraction with in-memory and file implementations.
- [ ] Implement a Strategy pattern using interfaces.
- [ ] Demonstrate field hiding vs method overriding.
- [ ] Investigate bytecode for overloaded and overridden methods.
- [ ] Create an example that produces an interface default-method conflict and resolve it.

## Production-Style

- [ ] Build the complete Payment Processing System.
- [ ] Use interfaces for replaceable providers.
- [ ] Use composition for external dependencies.
- [ ] Validate all domain invariants.
- [ ] Prevent mutable state leakage.
- [ ] Make appropriate objects immutable.
- [ ] Add failure handling.
- [ ] Design the API for future payment providers.
- [ ] Profile object allocation.
- [ ] Analyze the architecture for unnecessary inheritance.
- [ ] Explain every abstraction and relationship in the design.

---

# 1.3.21 Final Mastery Gate

Do **not** mark Module 1.3 complete until you can:

- [ ] Explain classes and objects deeply.
- [ ] Explain object identity vs reference.
- [ ] Explain encapsulation beyond private fields/getters.
- [ ] Implement inheritance correctly.
- [ ] Explain inheritance trade-offs.
- [ ] Explain polymorphism.
- [ ] Explain compile-time polymorphism.
- [ ] Explain runtime polymorphism.
- [ ] Explain method overloading.
- [ ] Explain method overriding.
- [ ] Explain dynamic method dispatch.
- [ ] Explain field hiding vs method overriding.
- [ ] Explain static method hiding.
- [ ] Explain abstraction.
- [ ] Design interfaces correctly.
- [ ] Design abstract classes correctly.
- [ ] Explain default interface methods.
- [ ] Explain composition vs inheritance.
- [ ] Correctly identify IS-A vs HAS-A.
- [ ] Explain Liskov Substitution implications.
- [ ] Debug polymorphic dispatch problems.
- [ ] Explain relevant JVM/runtime behavior.
- [ ] Discuss performance implications.
- [ ] Discuss production trade-offs.
- [ ] Build a production-style OOP system.
- [ ] Answer basic, intermediate, advanced and senior interview questions.

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
