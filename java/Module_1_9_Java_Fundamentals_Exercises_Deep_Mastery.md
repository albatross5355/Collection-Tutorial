# Module 1.9 — Java Fundamentals Exercises
## Deep Mastery Exercise Set

> **Goal:** Convert the concepts from Modules 1.1–1.8 into implementation skill. These exercises are intentionally arranged from foundational implementation to production-style design and debugging.

---

# Mastery Cycle

For **every exercise**, complete:

1. [ ] Understand the requirement
2. [ ] Design before coding
3. [ ] Implement the solution
4. [ ] Run the code
5. [ ] Test normal cases
6. [ ] Test edge cases
7. [ ] Explain the implementation
8. [ ] Explain relevant JVM/runtime behavior
9. [ ] Analyze performance
10. [ ] Refactor
11. [ ] Debug intentionally introduced bugs
12. [ ] Explain production trade-offs
13. [ ] Answer interview questions

## Exercise Completion Standard

Do not mark an exercise complete merely because the code works.

You should be able to:

> **Explain → Implement → Test → Debug → Explain internals → Handle edge cases → Refactor → Discuss trade-offs → Apply in production**

---

# 1.9.1 Exercise — Build an Immutable Value Object

## Objective

Build a genuinely immutable Java value object.

Suggested domain:

```text
Money
```

Represent:

```text
amount
currency
```

Example:

```java
Money money = new Money(
        new BigDecimal("100.00"),
        "INR"
);
```

## Requirements

- [ ] Make the class `final`, unless you can justify another design.
- [ ] Make state private.
- [ ] Make fields final where appropriate.
- [ ] Validate constructor arguments.
- [ ] Do not expose mutable internal state.
- [ ] Implement `equals()`.
- [ ] Implement `hashCode()`.
- [ ] Implement `toString()`.
- [ ] Ensure logically equal values compare equally.
- [ ] Ensure equal objects have equal hash codes.
- [ ] Handle null appropriately.
- [ ] Do not provide mutating setters.

## Advanced Requirement

If using a mutable field such as:

```java
Date
List
Map
```

apply defensive copying.

Demonstrate why:

```java
Money money = ...;
externalMutableObject.mutate();
```

cannot unexpectedly mutate the value object's state.

## Tests

- [ ] Same values → `equals()` is true.
- [ ] Different values → `equals()` is false.
- [ ] Equal values → same hash code.
- [ ] Attempted mutation cannot change object state.
- [ ] Null inputs behave according to the documented contract.
- [ ] Object works correctly as a `HashMap` key.
- [ ] Object works correctly in a `HashSet`.

## Advanced Follow-ups

- [ ] Convert the implementation to a Java `record`.
- [ ] Compare the record with the traditional immutable class.
- [ ] Decide whether the domain should use a record.
- [ ] Investigate record equality/hashCode semantics.
- [ ] Add currency validation.
- [ ] Implement arithmetic operations without mutation.

Example:

```java
Money total = price.add(tax);
```

rather than:

```java
price.setAmount(...);
```

## Interview Questions

- [ ] What makes a Java object immutable?
- [ ] Why make an immutable class final?
- [ ] Why are final fields useful?
- [ ] What is defensive copying?
- [ ] Immutable object vs unmodifiable collection?
- [ ] Why are immutable objects useful in concurrent systems?
- [ ] When is a record appropriate?

---

# 1.9.2 Exercise — Implement Inheritance and Runtime Polymorphism

## Objective

Build a small hierarchy that demonstrates genuine runtime polymorphism.

Suggested domain:

```text
Payment
├── CardPayment
├── UpiPayment
└── BankTransferPayment
```

## Requirements

Create a common abstraction:

```java
abstract class Payment {
    abstract void process();
}
```

Implement:

```java
class CardPayment extends Payment {
    @Override
    void process() {
        System.out.println("Card payment");
    }
}
```

and additional implementations.

## Demonstrate Dynamic Dispatch

Write:

```java
Payment payment;

payment = new CardPayment();
payment.process();

payment = new UpiPayment();
payment.process();
```

Observe that the implementation selected at runtime depends on the actual object.

## Concepts to Demonstrate

- [ ] Upcasting
- [ ] Downcasting
- [ ] Method overriding
- [ ] Runtime polymorphism
- [ ] Dynamic method dispatch
- [ ] `@Override`
- [ ] Abstract classes
- [ ] Interfaces
- [ ] `final` methods
- [ ] `final` classes

## Advanced Exercise

Replace the abstract class with:

```java
interface PaymentProcessor {
    void process();
}
```

Then compare:

```text
Abstract class
vs
Interface
```

Discuss:

- [ ] Shared state
- [ ] Shared implementation
- [ ] Multiple inheritance of type
- [ ] Coupling
- [ ] Extensibility
- [ ] Composition

## Edge Cases

- [ ] Override with incompatible return type.
- [ ] Attempt invalid method overriding.
- [ ] Test private methods.
- [ ] Test static methods and method hiding.
- [ ] Test final methods.
- [ ] Test covariant return types.
- [ ] Test overloaded vs overridden methods.

## Critical Challenge

Create:

```java
void process(Payment payment)
```

and pass different implementations.

Explain why the method does not need:

```java
if (payment instanceof CardPayment) ...
```

for ordinary polymorphic behavior.

## Interview Questions

- [ ] Compile-time vs runtime polymorphism?
- [ ] Overloading vs overriding?
- [ ] What is dynamic method dispatch?
- [ ] What happens during an overridden method call?
- [ ] Can static methods be overridden?
- [ ] Can private methods be overridden?
- [ ] What is method hiding?
- [ ] What is covariant return type?

---

# 1.9.3 Exercise — Demonstrate Constructor Chaining

## Objective

Understand constructor execution and initialization order.

Build:

```text
Parent
  ↓
Child
```

Example:

```java
class Parent {

    Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {

    Child() {
        System.out.println("Child constructor");
    }
}
```

Run:

```java
new Child();
```

Observe:

```text
Parent constructor
Child constructor
```

## Part A — `this()`

Create overloaded constructors:

```java
class User {

    private final String name;
    private final int age;

    User() {
        this("Unknown", 0);
    }

    User(String name) {
        this(name, 0);
    }

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Demonstrate:

- [ ] Constructor overloading
- [ ] `this()`
- [ ] Constructor chaining
- [ ] Single initialization path

## Part B — `super()`

Create:

```java
class Animal {

    Animal(String name) {
        System.out.println(name);
    }
}

class Dog extends Animal {

    Dog(String name) {
        super(name);
    }
}
```

Demonstrate:

- [ ] `super()`
- [ ] Parent constructor invocation
- [ ] Constructor argument forwarding

## Part C — Initialization Blocks

Add:

```java
static {
    System.out.println("Static block");
}

{
    System.out.println("Instance block");
}
```

Then add constructors.

Determine the execution order.

Expected conceptual sequence for first class initialization followed by instance creation:

```text
Static initialization
        ↓
Object construction begins
        ↓
Parent initialization
        ↓
Parent constructor
        ↓
Child instance initialization
        ↓
Child constructor
```

Be precise about where each initialization block belongs.

## Part D — Multi-Level Hierarchy

Create:

```text
GrandParent
     ↓
Parent
     ↓
Child
```

Add:

- [ ] Static blocks
- [ ] Instance initialization blocks
- [ ] Constructors
- [ ] Field initializers

Predict the output **before running the program**.

## Advanced Follow-ups

- [ ] Explain why `this()` must be the first constructor statement.
- [ ] Explain why explicit `super()` must be first when used.
- [ ] Explain implicit `super()`.
- [ ] Investigate constructor invocation with bytecode.
- [ ] Explain class initialization vs object initialization.
- [ ] Explain what happens if static initialization fails.
- [ ] Explain why calling overridable methods from constructors is dangerous.

## Interview Questions

- [ ] `this()` vs `super()`?
- [ ] What is constructor chaining?
- [ ] What happens if no `super()` is written?
- [ ] What is the initialization order in inheritance?
- [ ] Static block vs instance block?
- [ ] Why should constructors avoid calling overridable methods?

---

# 1.9.4 Exercise — Implement SRP

## Objective

Identify and fix a class containing multiple unrelated responsibilities.

Start with:

```java
class InvoiceService {

    void calculateTotal() {
    }

    void saveToDatabase() {
    }

    void sendEmail() {
    }

    void generatePdf() {
    }
}
```

## Tasks

- [ ] Identify each responsibility.
- [ ] Identify reasons to change.
- [ ] Refactor the class.
- [ ] Create cohesive components.

Possible result:

```text
InvoiceCalculator
InvoiceRepository
InvoiceNotificationService
InvoicePdfGenerator
```

## Advanced

Write tests for each component separately.

Explain:

- [ ] Why SRP improves testing.
- [ ] Why SRP is not "one method per class."
- [ ] How over-fragmentation can become a problem.

---

# 1.9.5 Exercise — Implement OCP

## Objective

Refactor a payment system that contains a growing conditional.

Initial code:

```java
class PaymentService {

    void pay(String type) {

        if (type.equals("CARD")) {
            // process card
        } else if (type.equals("UPI")) {
            // process UPI
        } else if (type.equals("WALLET")) {
            // process wallet
        }
    }
}
```

## Tasks

- [ ] Identify the extension problem.
- [ ] Create `PaymentProcessor`.
- [ ] Implement Card processor.
- [ ] Implement UPI processor.
- [ ] Implement Wallet processor.
- [ ] Refactor the service to depend on the abstraction.
- [ ] Add a new payment type without modifying core processing logic.

## Advanced

Implement:

```text
Strategy
+
Factory
+
Dependency Injection
```

Explain which part supports OCP and why.

---

# 1.9.6 Exercise — Implement LSP

## Objective

Create a hierarchy and prove that its implementations are behaviorally substitutable.

## Part A — Broken Design

Start with:

```java
class Bird {

    void fly() {
    }
}

class Penguin extends Bird {

    @Override
    void fly() {
        throw new UnsupportedOperationException();
    }
}
```

Identify the design problem.

## Part B — Refactor

Create:

```text
Bird
├── FlyingBird
│      ├── Eagle
│      └── Sparrow
└── Penguin
```

or use focused interfaces.

## Contract Testing

Define expectations for:

```java
Bird bird
```

Then verify every valid subtype satisfies the contract.

## Advanced

Study:

- [ ] Preconditions
- [ ] Postconditions
- [ ] Invariants
- [ ] Behavioral subtyping

---

# 1.9.7 Exercise — Implement ISP

## Objective

Refactor a fat interface.

Initial:

```java
interface Machine {

    void print();

    void scan();

    void fax();
}
```

A simple printer should not be forced to implement scanning and faxing.

## Refactor

Create:

```java
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

interface Fax {
    void fax();
}
```

## Tasks

- [ ] Implement a printer.
- [ ] Implement a multifunction machine.
- [ ] Verify clients depend only on required interfaces.
- [ ] Explain why this reduces coupling.

## Advanced

Design interfaces for:

```text
PaymentProvider
PaymentRefundProvider
PaymentStatusProvider
```

Determine whether splitting them improves the client design.

---

# 1.9.8 Exercise — Implement DIP

## Objective

Remove direct dependency on infrastructure.

Bad:

```java
class OrderService {

    private final MySqlOrderRepository repository =
            new MySqlOrderRepository();
}
```

## Refactor

Create:

```java
interface OrderRepository {
    void save(Order order);
}
```

Then:

```java
class OrderService {

    private final OrderRepository repository;

    OrderService(OrderRepository repository) {
        this.repository = repository;
    }
}
```

Implement:

```text
MySqlOrderRepository
InMemoryOrderRepository
```

## Tasks

- [ ] Use constructor injection.
- [ ] Unit test using the in-memory implementation.
- [ ] Explain DIP.
- [ ] Explain dependency injection.
- [ ] Explain why DIP and DI are not the same thing.
- [ ] Explain why an interface should not automatically be created for every class.

---

# 1.9.9 Exercise — Composition Over Inheritance

## Objective

Replace inappropriate inheritance with composition.

Initial design:

```java
class Vehicle {
    void drive() {
    }
}

class ElectricCar extends Vehicle {
}
```

Now imagine the system needs interchangeable behaviors:

```text
Engine
Transmission
Navigation
Payment
```

Build:

```java
class Car {

    private final Engine engine;
    private final Navigation navigation;

    Car(Engine engine, Navigation navigation) {
        this.engine = engine;
        this.navigation = navigation;
    }
}
```

## Tasks

- [ ] Identify behavior that varies independently.
- [ ] Extract interfaces where justified.
- [ ] Inject implementations.
- [ ] Replace inheritance with composition.
- [ ] Explain the trade-off.

## Advanced

Implement the Strategy pattern using composition.

---

# 1.9.10 Exercise — Coupling and Cohesion

## Objective

Measure design quality by examining relationships between responsibilities and dependencies.

Start with:

```java
class ApplicationManager {

    void processPayment() {}
    void sendEmail() {}
    void generateReport() {}
    void connectDatabase() {}
    void resizeImage() {}
}
```

## Tasks

Identify:

- [ ] Low cohesion
- [ ] High coupling
- [ ] Multiple responsibilities
- [ ] Possible God class

Refactor into cohesive components.

Then document:

```text
Before:
High coupling
Low cohesion

After:
Lower coupling
Higher cohesion
```

## Advanced

Create a dependency graph.

```text
A → B
B → C
A → D
```

Ask:

> If C changes, how many components might require modification?

---

# 1.9.11 Exercise — DRY Without Over-Abstraction

## Objective

Distinguish duplicated code from duplicated business knowledge.

Create two implementations that contain similar-looking validation.

```java
if (amount.compareTo(BigDecimal.ZERO) <= 0) {
    throw new IllegalArgumentException();
}
```

Determine whether they represent the same business rule.

## Tasks

- [ ] Extract genuine shared knowledge.
- [ ] Leave coincidental duplication alone.
- [ ] Compare readability before and after.
- [ ] Explain how DRY can create over-abstraction.

---

# 1.9.12 Exercise — KISS and YAGNI

## Scenario

Requirement:

> Add CSV export to an application.

Bad design:

```text
ExportFactory
 ↓
ExportStrategy
 ↓
ExportProvider
 ↓
FormatRegistry
 ↓
CSV/XML/JSON/PDF/Excel implementations
```

when only CSV is required.

## Tasks

- [ ] Implement the simplest valid solution.
- [ ] Identify speculative abstractions.
- [ ] Remove unnecessary future-proofing.
- [ ] Explain where OCP is still useful.
- [ ] Explain why YAGNI does not mean poor architecture.

---

# 1.9.13 Exercise — Law of Demeter

## Objective

Refactor excessive object-graph navigation.

Bad:

```java
order.getCustomer()
     .getAddress()
     .getCountry()
     .getCode();
```

## Tasks

- [ ] Identify structural coupling.
- [ ] Move appropriate behavior to the correct abstraction.
- [ ] Create:

```java
order.getShippingCountryCode();
```

if that behavior genuinely belongs to `Order`.

## Important Challenge

Determine whether these are equivalent violations:

```java
order.getCustomer().getAddress().getCity();
```

and:

```java
stream.map(...).filter(...).toList();
```

Explain why method chaining itself is not the problem.

---

# 1.9.14 Exercise — Tell, Don't Ask

## Objective

Move behavior toward the object that owns the state and invariant.

Bad:

```java
if (account.getBalance().compareTo(amount) >= 0) {
    account.setBalance(
        account.getBalance().subtract(amount)
    );
}
```

Refactor:

```java
account.withdraw(amount);
```

## Requirements

The `Account` must:

- [ ] Validate amount.
- [ ] Check balance.
- [ ] Maintain invariants.
- [ ] Update its state.
- [ ] Report failure appropriately.

## Advanced

Create:

```text
Order
Payment
Account
InventoryItem
```

and move appropriate domain behavior into each object.

---

# 1.9.15 Exercise — Debug a Pass-by-Value Example

## Objective

Prove experimentally that Java is always pass-by-value.

Start with:

```java
class User {

    String name;

    User(String name) {
        this.name = name;
    }
}
```

## Example A — Primitive

```java
static void change(int value) {
    value = 100;
}

int x = 10;

change(x);

System.out.println(x);
```

Predict the result before running it.

## Example B — Object Mutation

```java
static void change(User user) {
    user.name = "Bob";
}

User user = new User("Alice");

change(user);

System.out.println(user.name);
```

Predict the result.

## Example C — Reference Reassignment

```java
static void change(User user) {
    user = new User("Bob");
}

User user = new User("Alice");

change(user);

System.out.println(user.name);
```

Predict the result.

## Example D — Both Mutation and Reassignment

```java
static void change(User user) {

    user.name = "Bob";

    user = new User("Charlie");
    user.name = "David";
}
```

Predict:

```text
Caller user.name = ?
```

## Required Explanation

Draw:

```text
Caller
  |
  | copied reference value
  ↓
Parameter
  |
  ↓
Same object
```

Then explain why:

```java
user = new User("Charlie");
```

does not change the caller's variable.

## Advanced

Repeat the experiment with:

- [ ] Arrays
- [ ] Lists
- [ ] Maps
- [ ] Immutable Strings
- [ ] Records
- [ ] `final` references

---

# 1.9.16 Combined Exercise — Production-Style Order Domain

Build:

```text
Order
├── OrderId
├── Customer
├── OrderItem
├── OrderStatus
└── Money
```

## Requirements

### Value Objects

Implement:

```text
OrderId
Money
```

as immutable value objects.

- [ ] Encapsulation
- [ ] Immutability
- [ ] Equality
- [ ] Hashing

### OOP

Implement:

```text
PaymentProcessor
├── CardPayment
├── UpiPayment
└── BankTransferPayment
```

Demonstrate runtime polymorphism.

### Initialization

Use:

- [ ] Constructor overloading
- [ ] `this()`
- [ ] `super()`
- [ ] Constructor chaining

### SOLID

Explicitly document:

- [ ] SRP
- [ ] OCP
- [ ] LSP
- [ ] ISP
- [ ] DIP

### Composition

Use composition for:

```text
Order
 ├── PricingPolicy
 ├── PaymentProcessor
 └── NotificationService
```

where justified.

### Equality

Verify:

```text
OrderId equality
Money equality
HashMap behavior
HashSet behavior
```

### Debugging

Add and debug:

- [ ] Pass-by-value bug
- [ ] Incorrect equals/hashCode
- [ ] Null handling bug
- [ ] Constructor initialization bug
- [ ] Polymorphism bug

---

# 1.9.17 Debugging Challenges

Intentionally introduce each bug and diagnose it.

## Challenge 1 — Equality Bug

```java
class User {

    String id;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User user
                && id.equals(user.id);
    }
}
```

Remove `hashCode()`.

Then:

```java
Set<User> users = new HashSet<>();
```

Investigate the behavior.

## Challenge 2 — Mutable HashMap Key

Use a mutable field in `hashCode()`.

Insert the object into a `HashMap`, mutate the field, then attempt lookup.

Explain the result.

## Challenge 3 — Polymorphism

Create an overload and an override with similar signatures.

Determine which method is selected at compile time and which implementation is selected at runtime.

## Challenge 4 — Constructor Dispatch

Call an overridable method from a superclass constructor.

Observe what happens when the subclass has not finished initializing its fields.

Explain why this is dangerous.

## Challenge 5 — Initialization Order

Create:

```text
static field
static block
instance field
instance block
constructor
```

across a parent/child hierarchy.

Predict output before execution.

---

# 1.9.18 Performance Exercises

## Exercise A — Immutable Objects

Compare:

```text
Mutable object
vs
Immutable object
```

Discuss:

- [ ] Allocation
- [ ] Garbage collection
- [ ] Thread safety
- [ ] Sharing
- [ ] Defensive copying

## Exercise B — Varargs

Benchmark:

```java
sum(int... values)
```

against:

```java
sum(int[] values)
```

in a high-iteration loop.

Do not assume the result before measuring.

## Exercise C — Polymorphism

Investigate:

```text
Interface call
Virtual method call
Final method
Static method
```

using a profiler or JMH where appropriate.

Do not make performance claims from a simple `System.nanoTime()` microbenchmark.

---

# 1.9.19 Interview Challenge Set

## Basic

- [ ] What makes a class immutable?
- [ ] What is runtime polymorphism?
- [ ] What is constructor chaining?
- [ ] What is SRP?
- [ ] What is pass-by-value?

## Intermediate

- [ ] Why does object mutation survive a method call?
- [ ] Why does reference reassignment not affect the caller?
- [ ] Why should equals and hashCode be implemented together?
- [ ] Composition vs inheritance?
- [ ] What is OCP?

## Advanced

- [ ] Explain dynamic dispatch.
- [ ] Explain constructor initialization order.
- [ ] Explain LSP using a production example.
- [ ] Explain DIP vs DI.
- [ ] Explain why immutable objects are useful for concurrency.
- [ ] Explain how mutable keys can break HashMap lookups.
- [ ] Explain why overloaded methods are resolved differently from overridden methods.

## Senior / Production

- [ ] Refactor a God class using SOLID without over-engineering.
- [ ] Design a payment abstraction supporting new providers.
- [ ] Explain when not to use inheritance.
- [ ] Explain when DRY should not be applied.
- [ ] Balance OCP against YAGNI.
- [ ] Diagnose a production bug caused by incorrect equality.
- [ ] Explain how object references affect memory retention.
- [ ] Design immutable domain objects for concurrent services.

---

# 1.9.20 Final Exercise Mastery Gate

Do not mark Module 1.9 complete until you can:

## Immutable Value Object

- [ ] Implement one from scratch.
- [ ] Explain immutability.
- [ ] Implement equals/hashCode.
- [ ] Protect mutable state.
- [ ] Explain production benefits.

## OOP

- [ ] Implement inheritance.
- [ ] Implement interfaces.
- [ ] Demonstrate runtime polymorphism.
- [ ] Explain dynamic dispatch.
- [ ] Distinguish overloading from overriding.

## Initialization

- [ ] Implement constructor overloading.
- [ ] Implement `this()` chaining.
- [ ] Implement `super()` chaining.
- [ ] Predict initialization order.
- [ ] Explain parent-to-child initialization.

## SOLID

- [ ] Implement SRP.
- [ ] Implement OCP.
- [ ] Implement LSP.
- [ ] Implement ISP.
- [ ] Implement DIP.
- [ ] Explain when each principle should and should not be applied.

## Design Principles

- [ ] Apply composition over inheritance.
- [ ] Identify coupling.
- [ ] Improve cohesion.
- [ ] Apply DRY appropriately.
- [ ] Apply KISS.
- [ ] Apply YAGNI.
- [ ] Apply Law of Demeter.
- [ ] Apply Tell, Don't Ask.

## Pass-by-Value

- [ ] Explain primitive pass-by-value.
- [ ] Explain reference-value copying.
- [ ] Demonstrate object mutation.
- [ ] Demonstrate reference reassignment.
- [ ] Explain arrays and collections.
- [ ] Explain why Java does not pass objects by reference.

## Debugging

- [ ] Debug equality errors.
- [ ] Debug hash-based collection errors.
- [ ] Debug constructor-order problems.
- [ ] Debug polymorphism issues.
- [ ] Debug reference/aliasing problems.

## Production

- [ ] Complete the Order Domain project.
- [ ] Write unit tests.
- [ ] Handle edge cases.
- [ ] Explain performance implications.
- [ ] Explain design trade-offs.
- [ ] Perform a code review of your own solution.
- [ ] Answer senior-level interview questions.

---

# Final Module Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] DEBUGGED
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
