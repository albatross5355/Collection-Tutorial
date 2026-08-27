# Module 1.6 — Java Design Principles
## Deep Mastery Guide

> **Prerequisite:** Modules 1.1–1.5  
> **Goal:** Master the principles used to design maintainable, extensible, testable and production-quality Java code.

---

# Mastery Cycle

For **every topic**, complete:

1. [ ] What is it?
2. [ ] Why does Java have/support it?
3. [ ] Syntax and API
4. [ ] Basic example
5. [ ] Internal working / design mechanics
6. [ ] Memory/runtime behavior where relevant
7. [ ] Edge cases
8. [ ] Common mistakes
9. [ ] Performance implications
10. [ ] Production use cases
11. [ ] Interview questions
12. [ ] Coding exercises
13. [ ] Advanced follow-ups

## Completion Standard

Mark a topic complete only when you can:

> **Explain → Implement → Explain design implications → Handle edge cases → Discuss trade-offs → Refactor bad code → Debug it → Use it in a production scenario**

---

# 1.6.1 SOLID Principles

SOLID is a group of five object-oriented design principles:

```text
S → Single Responsibility Principle
O → Open/Closed Principle
L → Liskov Substitution Principle
I → Interface Segregation Principle
D → Dependency Inversion Principle
```

The goal is not to mechanically apply every principle.

Instead, use them to reason about:

- Change
- Coupling
- Cohesion
- Extensibility
- Testability
- Abstraction
- Dependency direction
- Maintainability

---

# 1.6.2 Single Responsibility Principle — SRP

## 1. What is it?

A class/module should have a focused responsibility and, in the common formulation, **one reason to change**.

Bad:

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

This class has multiple unrelated reasons to change.

Better:

```text
InvoiceCalculator
InvoiceRepository
InvoiceEmailService
InvoicePdfGenerator
```

## 2. Why?

SRP reduces the blast radius of changes.

```text
One responsibility
       ↓
Focused change
       ↓
Fewer unrelated regressions
```

## 3. Syntax / Design Tools

Java does not have an `@SingleResponsibility` annotation.

Use:

- [ ] Classes
- [ ] Interfaces
- [ ] Packages
- [ ] Services
- [ ] Modules
- [ ] Composition

## 4. Basic Example

```java
class InvoiceCalculator {

    Money calculate(Invoice invoice) {
        // calculation
        return invoice.total();
    }
}

class InvoiceRepository {

    void save(Invoice invoice) {
        // persistence
    }
}
```

## 5. Internal Design Reasoning

Ask:

> "What different kinds of changes would force this class to change?"

If the answers are unrelated, the class may have too many responsibilities.

## 6. Edge Cases

SRP does **not** mean:

> One class must contain exactly one method.

A cohesive class can contain multiple operations supporting one responsibility.

Avoid over-fragmentation:

```text
TinyClassA
TinyClassB
TinyClassC
TinyClassD
```

with no meaningful boundaries.

## 7. Common Mistakes

- [ ] Treating SRP as "one method per class"
- [ ] Creating hundreds of meaningless classes
- [ ] Splitting code without meaningful cohesion
- [ ] Confusing technical layers with business responsibilities

## 8. Performance Implications

SRP primarily affects maintainability rather than raw performance.

Too much abstraction can increase complexity, but modern JVM JIT optimizations can eliminate some abstraction overhead.

## 9. Production Use Cases

Useful when:

- Business logic changes frequently
- Teams own different responsibilities
- Testing requires isolated behavior
- Components have different lifecycles

## 10. Interview Questions

### Basic

- [ ] What is SRP?
- [ ] What does "one reason to change" mean?

### Intermediate

- [ ] Does SRP mean one method per class?

### Advanced

- [ ] How do you identify multiple responsibilities?

### Senior

- [ ] When can applying SRP create over-engineering?
- [ ] How would you refactor a 2,000-line service?

## 11. Coding Exercises

- [ ] Refactor a God class.
- [ ] Separate calculation from persistence.
- [ ] Separate notification from business logic.
- [ ] Write tests for each responsibility.

---

# 1.6.3 Open/Closed Principle — OCP

## 1. What is it?

Software entities should generally be:

> **Open for extension, closed for modification.**

The idea is to design stable abstractions so new behavior can be added without repeatedly modifying existing, well-tested code.

## 2. Why?

Without OCP:

```text
Every new payment type
        ↓
Modify giant if/else
        ↓
Regression risk
```

With an abstraction:

```text
PaymentProcessor
       ↓
 ┌─────┼─────┐
Card  UPI   Wallet
```

## 3. Basic Example

```java
interface PaymentProcessor {
    void pay(Money amount);
}

class CardPayment implements PaymentProcessor {
    public void pay(Money amount) {
        // card
    }
}

class UpiPayment implements PaymentProcessor {
    public void pay(Money amount) {
        // UPI
    }
}
```

## 4. Design Mechanics

Common mechanisms:

- [ ] Interfaces
- [ ] Polymorphism
- [ ] Strategy pattern
- [ ] Factory pattern
- [ ] Dependency injection
- [ ] Composition

## 5. Edge Cases

OCP does not mean:

> Never modify existing code.

Some requirements genuinely require changing an existing abstraction.

The goal is to avoid **unnecessary repeated modification** when variation is predictable.

## 6. Common Mistakes

- [ ] Creating an interface for every class
- [ ] Predicting imaginary future requirements
- [ ] Excessive inheritance
- [ ] Excessive abstraction
- [ ] Giant factory classes

## 7. Production Use Cases

- Payment providers
- Notification channels
- Authentication mechanisms
- Pricing strategies
- Export formats
- Storage implementations

## 8. Interview Questions

- [ ] Explain OCP.
- [ ] How does polymorphism support OCP?
- [ ] Strategy pattern and OCP?
- [ ] Can OCP ever be violated intentionally?

## 9. Coding Exercises

- [ ] Refactor payment `if/else`.
- [ ] Add three new payment methods without modifying the core processor.
- [ ] Implement Strategy + Factory.

---

# 1.6.4 Liskov Substitution Principle — LSP

## 1. What is it?

Subtypes should be usable wherever their base type is expected without breaking the correctness expectations of the program.

A classic conceptual example:

```java
class Bird {
    void fly() {
    }
}

class Penguin extends Bird {
    // cannot naturally fly
}
```

The abstraction is wrong if callers expect every `Bird` to fly.

Better:

```text
Bird
 ├── FlyingBird
 └── Penguin
```

## 2. Why?

Inheritance should model valid substitutability, not merely code reuse.

## 3. Key Concepts

Study:

- [ ] Behavioral subtyping
- [ ] Preconditions
- [ ] Postconditions
- [ ] Invariants
- [ ] Contract preservation
- [ ] Exceptions
- [ ] Return behavior

## 4. Basic Example

```java
interface PaymentProcessor {
    Receipt process(Payment payment);
}
```

A valid implementation should honor the behavioral contract expected by callers.

## 5. Common Violations

- [ ] Subclass rejects valid inputs accepted by parent
- [ ] Subclass weakens guarantees
- [ ] Subclass strengthens preconditions
- [ ] Unexpected exceptions
- [ ] Broken invariants
- [ ] Methods that do nothing when behavior is required

## 6. Edge Cases

LSP is not simply:

```text
"extends compiles"
```

A subclass can be syntactically valid but behaviorally invalid.

## 7. Common Mistakes

- [ ] Using inheritance only for code reuse
- [ ] Forcing unrelated types into one hierarchy
- [ ] Throwing `UnsupportedOperationException` from required base behavior
- [ ] Ignoring behavioral contracts

## 8. Production Use Cases

Critical for:

- Framework extension points
- Plugin architectures
- Polymorphic services
- Domain hierarchies
- API abstractions

## 9. Interview Questions

### Basic

- [ ] What is LSP?

### Intermediate

- [ ] Explain the Rectangle/Square problem.
- [ ] Why is behavioral compatibility important?

### Advanced

- [ ] Preconditions vs postconditions in LSP?

### Senior

- [ ] How can an inheritance hierarchy violate LSP while still compiling?
- [ ] When should composition replace inheritance?

## 10. Coding Exercises

- [ ] Fix a broken Bird hierarchy.
- [ ] Refactor Rectangle/Square.
- [ ] Design a valid payment hierarchy.
- [ ] Write contract tests for implementations.

---

# 1.6.5 Interface Segregation Principle — ISP

## 1. What is it?

Clients should not be forced to depend on methods they do not need.

Bad:

```java
interface Machine {
    void print();
    void scan();
    void fax();
}
```

A simple printer may not support scanning or faxing.

Better:

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

## 2. Why?

Smaller focused interfaces reduce coupling.

## 3. Java Tools

- [ ] Interfaces
- [ ] Default methods
- [ ] Composition
- [ ] Dependency injection

## 4. Internal Design Effect

```text
Large interface
      ↓
Many unnecessary dependencies
      ↓
More coupling

Focused interfaces
      ↓
Only required dependencies
      ↓
Lower coupling
```

## 5. Edge Cases

Interfaces should not become artificially tiny.

The correct size depends on:

- Client needs
- Cohesion
- Change patterns
- Domain boundaries

## 6. Common Mistakes

- [ ] One-method interface for everything
- [ ] Giant interfaces
- [ ] Splitting cohesive methods unnecessarily
- [ ] Using interfaces purely for mocking

## 7. Production Use Cases

- Client-specific APIs
- Infrastructure adapters
- Plugin interfaces
- Repository abstractions
- External provider integrations

## 8. Interview Questions

- [ ] What is ISP?
- [ ] Why are fat interfaces problematic?
- [ ] How does ISP reduce coupling?

## 9. Coding Exercises

- [ ] Refactor a fat interface.
- [ ] Create role-specific interfaces.
- [ ] Build a printer/scanner example.
- [ ] Apply ISP to a payment provider API.

---

# 1.6.6 Dependency Inversion Principle — DIP

## 1. What is it?

High-level policy should not depend directly on low-level implementation details.

Both should depend on abstractions.

Abstractions should not depend on details; details should depend on abstractions.

## 2. Why?

It separates:

```text
Business policy
       ↓
Abstraction
       ↑
Infrastructure
```

instead of:

```text
Business logic
      ↓
Concrete database class
```

## 3. Basic Example

Bad:

```java
class OrderService {

    private final MySqlOrderRepository repository =
            new MySqlOrderRepository();
}
```

Better:

```java
interface OrderRepository {
    void save(Order order);
}

class OrderService {

    private final OrderRepository repository;

    OrderService(OrderRepository repository) {
        this.repository = repository;
    }
}
```

Then:

```java
new OrderService(new MySqlOrderRepository());
```

or a different implementation.

## 4. Important Distinction

DIP is a **design principle**.

Dependency Injection (DI) is a **technique** that can help implement DIP.

```text
DIP
 ↓
Design principle

DI
 ↓
Implementation technique
```

Spring uses dependency injection heavily.

## 5. Java Tools

- [ ] Interfaces
- [ ] Constructor injection
- [ ] Factory methods
- [ ] Dependency injection frameworks
- [ ] Composition

## 6. Edge Cases

DIP does not mean:

> Everything must have an interface.

Abstractions should be introduced where they provide useful architectural flexibility.

## 7. Common Mistakes

- [ ] Interface explosion
- [ ] Abstracting every class
- [ ] Field injection
- [ ] Dependency locator abuse
- [ ] Confusing DIP with DI

## 8. Production Use Cases

- Database repositories
- Payment gateways
- Message brokers
- External APIs
- File storage
- Cloud providers
- Notification systems

## 9. Interview Questions

### Basic

- [ ] What is DIP?

### Intermediate

- [ ] DIP vs dependency injection?

### Advanced

- [ ] Why does constructor injection support DIP?

### Senior

- [ ] Does every concrete class need an interface?
- [ ] How does DIP improve testing and architecture?

## 10. Coding Exercises

- [ ] Refactor a concrete dependency.
- [ ] Add constructor injection.
- [ ] Swap database implementations.
- [ ] Build a payment provider abstraction.

---

# 1.6.7 Composition Over Inheritance

## 1. What is it?

Prefer combining objects to achieve behavior rather than using inheritance when the relationship is not a genuine subtype relationship.

Inheritance:

```text
Car extends Vehicle
```

Composition:

```text
Car
 ├── Engine
 ├── Transmission
 └── Navigation
```

## 2. Why?

Composition usually provides:

- Lower coupling
- Greater flexibility
- Better testability
- Runtime behavior replacement
- Fewer fragile base-class problems

## 3. Basic Example

```java
class PaymentService {

    private final PaymentProcessor processor;

    PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }
}
```

Behavior is composed rather than inherited.

## 4. When Inheritance Is Appropriate

Use inheritance when:

- [ ] True IS-A relationship exists
- [ ] LSP is satisfied
- [ ] Shared contract is stable
- [ ] Polymorphic substitution is meaningful

## 5. When Composition Is Better

Use composition when:

- [ ] Behavior changes independently
- [ ] Multiple behaviors need combining
- [ ] Runtime replacement is useful
- [ ] Reuse is the main reason for inheritance

## 6. Common Mistakes

- [ ] Inheritance solely for code reuse
- [ ] Deep inheritance trees
- [ ] Fragile base class
- [ ] Overriding too many methods
- [ ] Tight coupling to superclass implementation

## 7. Production Use Cases

- Strategy pattern
- Decorator pattern
- Service composition
- Adapter pattern
- Dependency injection

## 8. Interview Questions

- [ ] Composition vs inheritance?
- [ ] When should inheritance be used?
- [ ] What is the fragile base class problem?

---

# 1.6.8 Coupling

## 1. What is it?

Coupling describes how strongly components depend on one another.

```text
High coupling
    ↓
Changes spread easily

Low coupling
    ↓
Changes remain localized
```

## 2. Types to Study

- [ ] Tight coupling
- [ ] Loose coupling
- [ ] Content coupling
- [ ] Common coupling
- [ ] Control coupling
- [ ] Data coupling
- [ ] Temporal coupling
- [ ] Coupling through abstractions

## 3. Java Examples

High:

```java
class Service {
    private final MySqlRepository repository =
            new MySqlRepository();
}
```

Lower:

```java
class Service {
    private final Repository repository;

    Service(Repository repository) {
        this.repository = repository;
    }
}
```

## 4. Edge Cases

Low coupling does not mean zero dependencies.

A system with zero dependencies cannot do useful work.

The goal is **appropriate dependency direction and manageable change coupling**.

## 5. Production Use Cases

Evaluate coupling across:

```text
Classes
Packages
Modules
Services
Databases
External systems
Teams
```

## 6. Interview Questions

- [ ] What is coupling?
- [ ] How does dependency inversion reduce coupling?
- [ ] Can low coupling ever hurt readability?

---

# 1.6.9 Cohesion

## 1. What is it?

Cohesion measures how strongly the responsibilities within a component belong together.

```text
High cohesion
    ↓
Related responsibilities

Low cohesion
    ↓
Unrelated responsibilities
```

## 2. Example

Good:

```java
class InvoiceCalculator {
    calculateSubtotal();
    calculateTax();
    calculateTotal();
}
```

These operations are strongly related.

Bad:

```java
class UtilityManager {
    calculateTax();
    sendEmail();
    resizeImage();
    connectToDatabase();
}
```

## 3. Why?

High cohesion makes code:

- Easier to understand
- Easier to test
- Easier to modify
- Easier to reuse

## 4. Common Mistakes

- [ ] Giant utility classes
- [ ] "Manager" classes containing unrelated behavior
- [ ] Over-fragmentation

## 5. Interview Questions

- [ ] Coupling vs cohesion?
- [ ] Why aim for high cohesion and low coupling?

---

# 1.6.10 DRY — Don't Repeat Yourself

## 1. What is it?

DRY means avoiding duplicated **knowledge or business rules**, not merely avoiding identical lines of code.

Bad:

```java
if (age >= 18) {
    ...
}
```

repeated across many unrelated places.

Better:

```java
class EligibilityPolicy {

    boolean isEligible(int age) {
        return age >= 18;
    }
}
```

## 2. Important Distinction

Not every duplicated line should immediately be abstracted.

```text
Code duplication
    ≠
Knowledge duplication
```

Two pieces of code may look similar but represent different concepts.

## 3. Common Mistakes

- [ ] Premature abstraction
- [ ] Giant utility methods
- [ ] Shared abstraction for unrelated concepts
- [ ] "DRYing" code that should evolve independently

## 4. Production Use Cases

Apply DRY especially to:

- Business rules
- Validation rules
- Security policies
- Pricing formulas
- Configuration conventions

## 5. Interview Questions

- [ ] What does DRY actually mean?
- [ ] Can DRY lead to over-abstraction?
- [ ] DRY vs code duplication?

---

# 1.6.11 KISS — Keep It Simple

## 1. What is it?

Prefer the simplest design that correctly solves the problem.

Example:

Bad:

```text
Factory
 → AbstractFactory
 → StrategyFactory
 → Builder
 → Registry
 → Reflection
```

for a problem that requires one simple class.

Better:

```java
class TaxCalculator {
    BigDecimal calculate(Order order) {
        ...
    }
}
```

## 2. Why?

Complexity creates:

- More bugs
- More cognitive load
- More maintenance
- Harder debugging

## 3. Common Mistakes

- [ ] Over-engineering
- [ ] Unnecessary patterns
- [ ] Excessive abstractions
- [ ] Clever code
- [ ] Premature optimization

## 4. Production Rule

Ask:

> "What is the simplest design that satisfies today's actual requirements?"

---

# 1.6.12 YAGNI — You Aren't Gonna Need It

## 1. What is it?

Do not implement speculative functionality before there is a real requirement.

Bad:

```text
Current requirement:
CSV export

Implementation:
CSV + XML + JSON + YAML + PDF + Excel + GraphQL
```

If only CSV is required, build CSV first.

## 2. Why?

Unused functionality creates:

- Code
- Tests
- APIs
- Maintenance
- Security surface
- Cognitive overhead

## 3. Important Balance

YAGNI does not mean:

> Ignore architecture.

You should still create reasonable extension points when justified by known requirements.

## 4. Common Mistakes

- [ ] Speculative abstractions
- [ ] Future-proofing everything
- [ ] Building unused features
- [ ] Premature generalization

## 5. Interview Questions

- [ ] What is YAGNI?
- [ ] YAGNI vs extensible architecture?
- [ ] Can YAGNI conflict with OCP?

---

# 1.6.13 Law of Demeter

## 1. What is it?

A method should generally minimize knowledge of and navigation through unrelated object structures.

Common shorthand:

> Talk to your immediate friends, not strangers.

Bad:

```java
order.getCustomer()
     .getAddress()
     .getCity()
     .getCountry()
     .getCode();
```

This is often called a train-wreck or message chain.

Better:

```java
order.getShippingCountryCode();
```

with the relevant responsibility owned by the appropriate abstraction.

## 2. Why?

Long navigation chains create coupling to object structure.

```text
Order
 ↓
Customer
 ↓
Address
 ↓
Country
 ↓
Code
```

Changing the internal structure can break many callers.

## 3. Important Nuance

Not every method chain violates the Law of Demeter.

For example:

```java
stream.filter(...).map(...).toList();
```

is a fluent API designed for chaining.

The real concern is inappropriate knowledge of another object's internal structure.

## 4. Common Mistakes

- [ ] Blindly banning method chaining
- [ ] Creating dozens of forwarding methods
- [ ] Confusing fluent APIs with structural coupling

## 5. Production Use Cases

Important in:

- Domain models
- Service boundaries
- API design
- Encapsulation
- DTO/domain transformations

## 6. Interview Questions

- [ ] What is the Law of Demeter?
- [ ] Why are long getter chains problematic?
- [ ] Are all method chains violations?

---

# 1.6.14 Tell, Don't Ask

## 1. What is it?

Instead of asking an object for its internal state and then deciding what it should do, tell the object to perform the behavior it owns.

Ask:

```java
if (account.getBalance() >= amount) {
    account.setBalance(account.getBalance() - amount);
}
```

Better:

```java
account.withdraw(amount);
```

The object protects its invariant.

## 2. Why?

It promotes:

- Encapsulation
- High cohesion
- Reduced coupling
- Domain behavior
- Invariant protection

## 3. Basic Example

Bad:

```java
class OrderService {

    void cancel(Order order) {
        if (order.getStatus() == Status.PENDING) {
            order.setStatus(Status.CANCELLED);
        }
    }
}
```

Better:

```java
class Order {

    void cancel() {
        if (status != Status.PENDING) {
            throw new IllegalStateException("Cannot cancel");
        }

        status = Status.CANCELLED;
    }
}
```

Then:

```java
order.cancel();
```

## 4. Important Nuance

Not every getter is bad.

Getters are appropriate for:

- DTOs
- Read models
- Serialization
- Query APIs
- Legitimate observation

The principle is about **where behavior and invariants should live**.

## 5. Common Mistakes

- [ ] Making all state private but still putting all behavior elsewhere
- [ ] Treating getters as inherently bad
- [ ] Creating an anemic domain model
- [ ] Moving every operation into entities regardless of architecture

## 6. Production Use Cases

Particularly useful for:

- Domain models
- Financial operations
- State transitions
- Business invariants
- Authorization decisions

## 7. Interview Questions

- [ ] What is Tell, Don't Ask?
- [ ] How does it relate to encapsulation?
- [ ] Is using getters always a violation?

---

# 1.6.15 How the Principles Work Together

These principles are not independent checkboxes.

A useful relationship:

```text
High Cohesion
      +
Low Coupling
      ↓
Maintainable Components
      ↓
SOLID
      ↓
Better Change Isolation
```

Another useful chain:

```text
Composition
    ↓
Dependency Inversion
    ↓
Dependency Injection
    ↓
Lower Coupling
    ↓
Better Testing
```

And:

```text
Tell, Don't Ask
      ↓
Encapsulation
      ↓
Higher Cohesion
      ↓
Lower Coupling
```

---

# 1.6.16 Principle Conflicts and Trade-offs

A senior developer should know that principles can conflict.

## OCP vs YAGNI

```text
OCP → Prepare for meaningful variation
YAGNI → Don't build speculative variation
```

Solution:

> Design around known variation, not imaginary requirements.

## DRY vs KISS

Sometimes duplicating a small amount of code is simpler than creating a complex abstraction.

## SRP vs Too Many Classes

Splitting responsibilities too aggressively can create a distributed mess.

## Composition vs Simplicity

Composition is powerful, but unnecessary indirection can make simple code harder to understand.

## Low Coupling vs Readability

An abstraction can reduce coupling while increasing conceptual complexity.

---

# 1.6.17 Code Smells That Suggest Design Problems

Learn to recognize:

- [ ] God classes
- [ ] Long methods
- [ ] Long parameter lists
- [ ] Feature envy
- [ ] Shotgun surgery
- [ ] Divergent change
- [ ] Refused bequest
- [ ] Switch statements over type
- [ ] Deep inheritance trees
- [ ] Fat interfaces
- [ ] Primitive obsession
- [ ] Data clumps
- [ ] Train-wreck method chains
- [ ] Anemic domain models
- [ ] Excessive getters/setters
- [ ] Global mutable state

Map each smell to possible principles.

---

# 1.6.18 Refactoring Exercise — Before

Start with:

```java
class OrderManager {

    void process(Order order) {

        if (order.getCustomer().getType() == CustomerType.PREMIUM) {
            // discount
        }

        if (order.getPayment().getType() == PaymentType.CARD) {
            // card processing
        }

        if (order.getStatus() == Status.PENDING) {
            // status change
        }

        // save
        // email
        // audit
    }
}
```

Identify:

- [ ] SRP violations
- [ ] OCP violations
- [ ] Coupling
- [ ] Low cohesion
- [ ] Law of Demeter violations
- [ ] Tell, Don't Ask violations
- [ ] Potential DIP violation

Then refactor.

---

# 1.6.19 Refactoring Exercise — After

Target architecture:

```text
OrderService
    ↓
Order
    ↓
Business behavior

OrderRepository
    ↓
Persistence

PaymentProcessor
    ↓
Payment implementations

NotificationService
    ↓
Notification implementations
```

Use:

- [ ] SRP
- [ ] OCP
- [ ] DIP
- [ ] Composition
- [ ] Tell, Don't Ask
- [ ] Law of Demeter
- [ ] High cohesion
- [ ] Low coupling

Do not introduce abstractions that are not justified.

---

# 1.6.20 Production Design Exercise

Design a payment platform supporting:

```text
Credit Card
UPI
Wallet
Bank Transfer
```

Requirements:

- [ ] New payment types can be added cleanly.
- [ ] Payment rules remain encapsulated.
- [ ] Core service does not depend on concrete providers.
- [ ] Provider integrations can be mocked.
- [ ] Business rules are not duplicated.
- [ ] No speculative payment types.
- [ ] No deep inheritance hierarchy.
- [ ] Interfaces remain focused.
- [ ] Components have clear responsibilities.

Explicitly document where you apply:

```text
SRP
OCP
LSP
ISP
DIP
Composition
Coupling
Cohesion
DRY
KISS
YAGNI
Law of Demeter
Tell, Don't Ask
```

---

# 1.6.21 Advanced Coding Exercises

## Basic

- [ ] Identify SRP violations.
- [ ] Refactor a God class.
- [ ] Reduce coupling.
- [ ] Increase cohesion.
- [ ] Replace a simple inheritance hierarchy with composition.

## Intermediate

- [ ] Implement Strategy for payment methods.
- [ ] Apply DIP using constructor injection.
- [ ] Split a fat interface.
- [ ] Refactor a long getter chain.
- [ ] Move business behavior into the object that owns the invariant.

## Advanced

- [ ] Refactor a 1,000-line service.
- [ ] Design an extensible notification framework.
- [ ] Design multiple storage implementations.
- [ ] Write contract tests for LSP.
- [ ] Design focused interfaces for multiple clients.
- [ ] Refactor a distributed domain model without creating an anemic model.

## Production-Style

Build:

```text
Order Management System
        ↓
Order
 ├── Pricing
 ├── Payment
 ├── Inventory
 ├── Shipping
 └── Notification
```

Requirements:

- [ ] Apply all five SOLID principles where justified.
- [ ] Use composition appropriately.
- [ ] Keep coupling low.
- [ ] Keep cohesion high.
- [ ] Remove duplicated business knowledge.
- [ ] Avoid speculative features.
- [ ] Keep the simplest viable architecture.
- [ ] Protect domain invariants.
- [ ] Avoid inappropriate object-graph navigation.
- [ ] Write unit and integration tests.
- [ ] Document major trade-offs.

---

# 1.6.22 Interview Question Bank

## Basic

- [ ] What is SOLID?
- [ ] Explain SRP.
- [ ] Explain OCP.
- [ ] Explain LSP.
- [ ] Explain ISP.
- [ ] Explain DIP.
- [ ] Composition vs inheritance?
- [ ] What is coupling?
- [ ] What is cohesion?
- [ ] What is DRY?
- [ ] What is KISS?
- [ ] What is YAGNI?

## Intermediate

- [ ] How does SRP improve maintainability?
- [ ] How does polymorphism support OCP?
- [ ] Explain the Rectangle/Square LSP problem.
- [ ] What is a fat interface?
- [ ] DIP vs dependency injection?
- [ ] Why is composition often preferred?
- [ ] High cohesion vs low coupling?
- [ ] Can DRY cause over-engineering?
- [ ] Can YAGNI conflict with OCP?
- [ ] What is the Law of Demeter?
- [ ] What is Tell, Don't Ask?

## Advanced

- [ ] Give a real production example of each SOLID principle.
- [ ] How would you identify an SRP violation?
- [ ] How do you recognize an OCP violation?
- [ ] How do preconditions and postconditions relate to LSP?
- [ ] When should an interface be split?
- [ ] Does every dependency require an interface?
- [ ] How can composition create excessive indirection?
- [ ] How do DRY and KISS sometimes conflict?
- [ ] How do you avoid speculative abstractions?
- [ ] Are method chains always Law of Demeter violations?

## Senior / Production

- [ ] Refactor a God service while preserving behavior.
- [ ] Design an extensible payment architecture.
- [ ] Explain where SOLID should NOT be applied.
- [ ] How do you balance flexibility against simplicity?
- [ ] How do you prevent an architecture from becoming over-engineered?
- [ ] How can excessive abstraction increase operational/debugging complexity?
- [ ] How would you evaluate a design during a code review?
- [ ] How do coupling and cohesion apply across microservices, not just classes?
- [ ] How can a shared database create architectural coupling?
- [ ] How do you distinguish useful abstraction from premature abstraction?

---

# 1.6.23 Advanced Follow-ups

Study these after mastering the fundamentals:

- [ ] Design by Contract
- [ ] Behavioral subtyping
- [ ] Dependency Injection
- [ ] Inversion of Control
- [ ] Hexagonal Architecture
- [ ] Clean Architecture
- [ ] Domain-Driven Design
- [ ] Rich vs Anemic Domain Models
- [ ] Functional Core / Imperative Shell
- [ ] Immutability
- [ ] Value Objects
- [ ] Package design
- [ ] Module boundaries
- [ ] API design
- [ ] Refactoring patterns
- [ ] Code smells
- [ ] Architecture fitness functions

---

# 1.6.24 Java-Specific Connections

Connect the principles to Java features:

```text
Interfaces
    ↓
ISP / DIP / OCP

Inheritance
    ↓
LSP

Polymorphism
    ↓
OCP / LSP

Constructor Injection
    ↓
DIP

Records / Immutability
    ↓
Cohesion / Value Objects

Packages / Modules
    ↓
Coupling boundaries

Exceptions
    ↓
Behavioral contracts

Generics
    ↓
Abstraction boundaries

Streams / Lambdas
    ↓
Composition / KISS / readability
```

---

# 1.6.25 Production Review Checklist

Before approving a design, ask:

## Responsibility

- [ ] Does each component have a clear purpose?
- [ ] Are unrelated responsibilities separated?

## Extensibility

- [ ] Where is behavior expected to vary?
- [ ] Is extension easier than repeatedly modifying stable code?

## Substitutability

- [ ] Can implementations genuinely substitute for their abstractions?
- [ ] Are contracts preserved?

## Interfaces

- [ ] Are clients forced to depend on unused operations?

## Dependencies

- [ ] Does business logic depend on infrastructure details?
- [ ] Can dependencies be replaced for tests?

## Coupling

- [ ] How many components must change for one requirement?
- [ ] Are implementation details leaking?

## Cohesion

- [ ] Do methods and fields belong together?

## Duplication

- [ ] Is business knowledge duplicated?

## Simplicity

- [ ] Is the architecture more complex than the requirement?

## Future Work

- [ ] Are we implementing real requirements or imaginary ones?

## Encapsulation

- [ ] Does an object own the behavior and invariants associated with its state?

---

# 1.6.26 Final Mastery Gate

Do **not** mark Module 1.6 complete until you can:

- [ ] Explain all five SOLID principles.
- [ ] Implement SRP in real code.
- [ ] Identify multiple responsibilities.
- [ ] Apply OCP using polymorphism/composition.
- [ ] Explain when OCP should not be forced.
- [ ] Explain LSP as behavioral substitutability.
- [ ] Identify LSP violations.
- [ ] Design focused interfaces using ISP.
- [ ] Explain DIP independently from DI.
- [ ] Implement DIP with constructor injection.
- [ ] Explain composition over inheritance.
- [ ] Identify inappropriate inheritance.
- [ ] Explain coupling.
- [ ] Explain cohesion.
- [ ] Design for high cohesion and manageable coupling.
- [ ] Apply DRY without creating premature abstractions.
- [ ] Apply KISS to avoid unnecessary complexity.
- [ ] Apply YAGNI without ignoring legitimate architecture.
- [ ] Explain the Law of Demeter.
- [ ] Distinguish harmful object-graph navigation from legitimate fluent APIs.
- [ ] Apply Tell, Don't Ask to domain behavior.
- [ ] Recognize when getters are appropriate.
- [ ] Refactor a God class.
- [ ] Refactor a fat interface.
- [ ] Refactor a deep inheritance hierarchy.
- [ ] Design an extensible payment system.
- [ ] Explain principle trade-offs.
- [ ] Review production code using these principles.
- [ ] Discuss when **not** to apply a principle.
- [ ] Answer basic → senior interview questions.
- [ ] Complete basic → production coding exercises.

---

# Final Mastery Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] INTERNALS / DESIGN MECHANICS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
