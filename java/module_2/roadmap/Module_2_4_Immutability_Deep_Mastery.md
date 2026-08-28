# Module 2.4 — Immutability
## Deep Mastery Guide

> **Goal:** Master immutable object design in Java, including final classes, final fields, constructor initialization, defensive copying, mutable-field hazards, immutable collections, records and their limitations, memory/runtime behavior, and the thread-safety benefits of immutability.

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

# 2.4.1 What Is Immutability?

An object is immutable when its observable state cannot be changed after construction.

Example:

```java
public final class UserId {

    private final String value;

    public UserId(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
```

After construction:

```java
UserId id = new UserId("U100");
```

there is no operation that changes the object's `value`.

Important:

> `final` and immutability are related, but `final` alone does not make an object immutable.

---

# 2.4.2 Why Does Java Need Immutable Objects?

Immutability provides:

- [ ] Predictable state
- [ ] Easier reasoning
- [ ] Safer sharing
- [ ] Thread-safety benefits
- [ ] Stable map/set keys
- [ ] Easier caching
- [ ] Safer concurrent programming
- [ ] Reduced synchronization requirements
- [ ] Better defensive API design

Example:

```java
Map<UserId, User> cache = new HashMap<>();
```

If the key's state cannot change, its equality/hash-code behavior can remain stable.

---

# 2.4.3 Characteristics of a Truly Immutable Class

A strong immutable-class design normally follows these rules:

- [ ] Class cannot be subclassed, usually by making it `final`.
- [ ] State fields are `private`.
- [ ] State fields are `final`.
- [ ] State is initialized completely during construction.
- [ ] No setters or other mutating methods.
- [ ] Mutable inputs are defensively copied.
- [ ] Mutable internal state is never exposed directly.
- [ ] Methods do not mutate internal state.
- [ ] Subclass-controlled mutation is prevented.
- [ ] Object invariants are established before publication.

Typical structure:

```text
final class
    ↓
private final fields
    ↓
constructor establishes valid state
    ↓
defensive copies
    ↓
no mutators
    ↓
safe accessors
```

---

# 2.4.4 Final Class

Use:

```java
public final class Money {
    ...
}
```

A final class cannot be subclassed.

Why this helps:

```text
Immutable class
      ↓
No subclass
      ↓
No subclass-defined mutable state/behavior
      ↓
Easier immutability guarantee
```

Without `final`, a subclass could introduce mutable state or override methods in ways that violate assumptions.

Example:

```java
public class ImmutableBase {
    private final int value;

    public ImmutableBase(int value) {
        this.value = value;
    }
}
```

A subclass could add:

```java
class MutableChild extends ImmutableBase {
    private int counter;
}
```

The base portion may remain immutable, but the overall child object is not necessarily immutable.

---

# 2.4.5 Is `final` Always Required?

No.

An immutable class does not mathematically require the class itself to be `final`, but preventing subclassing is a common and robust design technique.

Alternatives include:

- [ ] Carefully designed inheritance
- [ ] Sealed hierarchies
- [ ] Private constructors/factory-controlled implementations
- [ ] Package/API design that prevents unsafe extension

For ordinary value objects, `final` is usually the simplest choice.

---

# 2.4.6 Final Fields

Example:

```java
private final String name;
private final int age;
```

A final field can be assigned only during its permitted initialization phase.

For instance fields, typical initialization occurs:

```text
field initializer
OR
instance initialization
OR
constructor
```

After construction, the reference cannot be reassigned.

Important:

> `final` prevents reassignment of the field. It does not automatically make the referenced object immutable.

---

# 2.4.7 The Final Reference Trap

This is not necessarily immutable:

```java
private final List<String> names;
```

The reference cannot change:

```java
names = anotherList; // illegal after initialization
```

But the object can still mutate:

```java
names.add("Alice");
```

Conceptually:

```text
final reference
      ↓
┌───────────────┐
│ mutable List  │
└───────────────┘
      ↑
 reference cannot change
 object can change
```

Therefore:

```text
final reference ≠ immutable object
```

---

# 2.4.8 Constructor Initialization

Immutable state should be completely established during construction.

Example:

```java
public final class Account {

    private final String id;
    private final String owner;

    public Account(String id, String owner) {
        this.id = Objects.requireNonNull(id);
        this.owner = Objects.requireNonNull(owner);
    }
}
```

Benefits:

- [ ] Object invariants established early.
- [ ] No partially configured public object.
- [ ] No setter-based mutation.
- [ ] Easier reasoning.
- [ ] Safer publication.

---

# 2.4.9 Constructor Validation

Validate immutable object state during construction.

```java
public Money(BigDecimal amount) {
    if (amount == null) {
        throw new NullPointerException("amount");
    }

    if (amount.signum() < 0) {
        throw new IllegalArgumentException("amount cannot be negative");
    }

    this.amount = amount;
}
```

After construction:

```text
valid state
+
immutable state
=
stable invariant
```

This is especially valuable for domain/value objects.

---

# 2.4.10 Defensive Copying

Defensive copying protects an immutable object from mutable objects supplied by callers.

Bad:

```java
public final class User {

    private final List<String> roles;

    public User(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }
}
```

The caller can mutate:

```java
roles.add("ADMIN");
```

and change the supposedly immutable object.

---

# 2.4.11 Defensive Copy in the Constructor

Better:

```java
public User(List<String> roles) {
    this.roles = new ArrayList<>(roles);
}
```

Now:

```text
caller list
     ↓ copy
internal list
```

Mutating the caller's list does not mutate the internal list.

But the getter must also be protected.

---

# 2.4.12 Defensive Copy on Output

Bad:

```java
public List<String> getRoles() {
    return roles;
}
```

Better:

```java
public List<String> getRoles() {
    return new ArrayList<>(roles);
}
```

Or, when appropriate:

```java
public List<String> getRoles() {
    return List.copyOf(roles);
}
```

The correct choice depends on the required API semantics and performance.

---

# 2.4.13 Defensive Copying — Full Example

```java
public final class User {

    private final String username;
    private final List<String> roles;

    public User(String username, List<String> roles) {
        this.username = Objects.requireNonNull(username);
        this.roles = List.copyOf(roles);
    }

    public String username() {
        return username;
    }

    public List<String> roles() {
        return roles;
    }
}
```

Now:

```text
Input list
   ↓
List.copyOf()
   ↓
immutable internal list
   ↓
safe exposure
```

---

# 2.4.14 Mutable Field Hazards

Common mutable fields:

- [ ] `ArrayList`
- [ ] `HashMap`
- [ ] `HashSet`
- [ ] Arrays
- [ ] `Date`
- [ ] Mutable custom objects
- [ ] `StringBuilder`
- [ ] Buffers
- [ ] Mutable framework objects

A final field containing one of these does not automatically make the enclosing object immutable.

---

# 2.4.15 Arrays Are a Classic Trap

Bad:

```java
public final class User {

    private final byte[] token;

    public User(byte[] token) {
        this.token = token;
    }

    public byte[] getToken() {
        return token;
    }
}
```

The caller can modify:

```java
token[0] = 99;
```

and change internal state.

Correct:

```java
this.token = token.clone();
```

and:

```java
return token.clone();
```

---

# 2.4.16 Nested Mutable Objects

Consider:

```java
final class Address {
    String city;
}
```

and:

```java
final class User {
    private final Address address;
}
```

Even if `User` is final and `address` is final:

```java
user.address().city = "Delhi";
```

can change the state.

For deep immutability:

```text
Immutable User
      ↓
Immutable Address
      ↓
Immutable City representation
```

Immutability may need to be transitive through the reachable object graph.

---

# 2.4.17 Shallow vs Deep Immutability

## Shallow Immutability

The object's direct fields cannot be reassigned.

But referenced objects may still mutate.

```text
Object
 ├── final field → mutable object
 └── final field → mutable object
```

## Deep Immutability

The entire relevant reachable state cannot be mutated through the object.

```text
Immutable object
      ↓
immutable fields
      ↓
immutable referenced objects
      ↓
immutable nested state
```

Deep immutability is a stronger property.

---

# 2.4.18 Immutable Collections

Modern Java provides factory methods:

```java
List.of(...)
Set.of(...)
Map.of(...)
```

These create unmodifiable collection instances.

Example:

```java
List<String> roles = List.of("USER", "ADMIN");
```

This fails:

```java
roles.add("AUDITOR");
```

with an `UnsupportedOperationException`.

---

# 2.4.19 `List.copyOf()`

Example:

```java
List<String> immutableRoles = List.copyOf(roles);
```

This creates an unmodifiable list view/value representation according to the API contract and prevents structural modification through the returned list.

Important:

> Unmodifiable does not necessarily mean deeply immutable.

If elements themselves are mutable:

```java
List<MutableObject>
```

the objects inside may still change.

---

# 2.4.20 Immutable Collection vs Mutable Elements

Example:

```java
List<StringBuilder> values =
        List.of(new StringBuilder("Java"));
```

The list cannot be structurally modified:

```java
values.add(...); // fails
```

But:

```java
values.get(0).append("!");
```

can mutate the contained `StringBuilder`.

Therefore:

```text
immutable collection
        ≠
deeply immutable object graph
```

---

# 2.4.21 `Collections.unmodifiableList()`

Example:

```java
List<String> original = new ArrayList<>();
List<String> view = Collections.unmodifiableList(original);
```

The returned list cannot be modified through `view`.

But:

```java
original.add("Java");
```

changes what `view` observes.

Conceptually:

```text
original mutable list
        ↑
        │
unmodifiable view
```

This differs from making a defensive copy.

---

# 2.4.22 Unmodifiable View vs Defensive Copy

### View

```java
Collections.unmodifiableList(original)
```

The underlying collection can still change elsewhere.

### Copy

```java
List.copyOf(original)
```

creates an independent unmodifiable representation according to its contract.

Choose based on whether you need:

```text
live view
```

or:

```text
stable snapshot/value
```

---

# 2.4.23 Records and Immutability

Records are concise data carriers:

```java
public record User(String name, int age) {
}
```

They provide:

- [ ] Final component fields
- [ ] Accessor methods
- [ ] Constructor
- [ ] `equals()`
- [ ] `hashCode()`
- [ ] `toString()`

But:

> A record is not automatically deeply immutable.

---

# 2.4.24 Record Mutable Field Trap

Example:

```java
public record User(List<String> roles) {
}
```

The record component reference is final, but:

```java
roles.add("ADMIN");
```

can still mutate the list if the caller supplied a mutable list.

Therefore this is stronger:

```java
public record User(List<String> roles) {

    public User {
        roles = List.copyOf(roles);
    }
}
```

Now the record protects the collection structurally.

---

# 2.4.25 Record with Array Trap

This is not deeply immutable:

```java
public record Data(byte[] bytes) {
}
```

The array can still be modified.

A defensive copy can be made in the compact constructor:

```java
public record Data(byte[] bytes) {

    public Data {
        bytes = bytes.clone();
    }

    @Override
    public byte[] bytes() {
        return bytes.clone();
    }
}
```

This protects both input and output.

---

# 2.4.26 Records vs Traditional Immutable Classes

Traditional class:

```java
public final class User {

    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }
}
```

Record:

```java
public record User(String name, int age) {
}
```

Records reduce boilerplate, but they do not automatically solve:

- [ ] Defensive copying
- [ ] Deep immutability
- [ ] Validation
- [ ] Mutable nested state

---

# 2.4.27 Thread-Safety Benefits

Immutable objects are naturally easier to share across threads.

Example:

```java
public final class Configuration {

    private final String host;
    private final int port;

    public Configuration(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String host() {
        return host;
    }

    public int port() {
        return port;
    }
}
```

Once safely constructed and published, there is no state mutation for competing threads to coordinate.

This can eliminate the need for locks around object state.

---

# 2.4.28 Immutability and the Java Memory Model

Final fields have special initialization visibility guarantees under the Java Memory Model when construction is performed correctly.

The key idea:

```text
constructor completes
       ↓
final field initialization semantics
       ↓
other threads can safely observe properly initialized final fields
```

However:

> Final-field semantics do not make unsafe publication generally safe, nor do they make referenced mutable objects immutable.

Correct object publication still matters.

---

# 2.4.29 Safe Publication

Good publication mechanisms include:

- [ ] Constructing before publishing
- [ ] Static initialization
- [ ] Volatile references
- [ ] Locks/synchronization
- [ ] Concurrent collections
- [ ] Proper executor/concurrency mechanisms

Immutability reduces synchronization requirements but does not remove all concurrency design concerns.

---

# 2.4.30 Immutable Objects as Map Keys

Immutable objects make excellent keys.

Example:

```java
public record UserId(String value) {
}
```

Then:

```java
Map<UserId, String> users = new HashMap<>();

users.put(new UserId("U100"), "Alice");
```

Because the key's logical state does not change, its hash-code contract remains stable.

Mutable key example:

```text
insert key
   ↓
mutate key
   ↓
hashCode changes
   ↓
lookup may fail
```

This is a major production hazard.

---

# 2.4.31 Immutability and Caching

Immutable objects work well with caching because their state does not change after insertion.

Examples:

- [ ] Configuration
- [ ] Value objects
- [ ] Parsed metadata
- [ ] Request descriptors
- [ ] Domain identifiers
- [ ] Small reusable objects

But immutable objects can still consume memory, and excessive creation should still be measured.

---

# 2.4.32 Immutability and Hash Codes

If an object's fields participate in:

```java
equals()
hashCode()
```

those fields should not change while the object is being used as a hash-based collection key.

Immutability provides a strong guarantee:

```text
object state stable
       ↓
equals stable
       ↓
hashCode stable
       ↓
HashMap/HashSet behavior predictable
```

---

# 2.4.33 Immutability and Sharing

Immutable values can safely be shared:

```text
Thread A ─┐
          ├──> Immutable object
Thread B ─┤
          │
Thread C ─┘
```

No thread can mutate the shared state.

With mutable objects:

```text
Thread A ─┐
Thread B ─┼──> shared mutable object
Thread C ─┘
             ↓
          synchronization
          coordination
          race conditions
```

Immutability shifts the design toward safer sharing.

---

# 2.4.34 Performance Implications

Immutability can improve performance through:

- [ ] Safe sharing
- [ ] Reduced synchronization
- [ ] Easier caching
- [ ] Stable hash codes
- [ ] Potential reuse

But it can also introduce costs:

- [ ] Defensive-copy allocation
- [ ] New objects for changed values
- [ ] Copying large collections
- [ ] Array copying
- [ ] Increased temporary allocation

Example:

```java
List<String> copy = List.copyOf(largeList);
```

may have meaningful cost for large data.

The correct design depends on workload.

---

# 2.4.35 Immutability vs Mutability

| Property | Immutable | Mutable |
|---|---|---|
| State changes | No | Yes |
| Thread sharing | Easier | Requires coordination |
| Defensive copying | Often on boundaries | Usually less |
| Caching | Easier | More difficult |
| HashMap key | Excellent | Dangerous if key state changes |
| Object replacement | New value/object | In-place mutation |
| Large data updates | Can be expensive | Often cheaper |
| Reasoning | Easier | More complex |

---

# 2.4.36 Common Mistakes

- [ ] Assuming `final` makes an object immutable.
- [ ] Returning internal mutable collections.
- [ ] Storing caller-owned mutable collections directly.
- [ ] Forgetting array defensive copies.
- [ ] Assuming records are deeply immutable.
- [ ] Confusing unmodifiable views with immutable snapshots.
- [ ] Assuming immutable collections make their elements immutable.
- [ ] Allowing mutable subclasses.
- [ ] Using mutable objects inside immutable value objects.
- [ ] Publishing partially initialized objects.
- [ ] Ignoring nested mutable state.
- [ ] Overusing defensive copies for enormous objects without measuring.
- [ ] Assuming immutability automatically solves all concurrency problems.

---

# 2.4.37 Edge Cases

## Null Mutable Input

```java
public User(List<String> roles) {
    this.roles = List.copyOf(roles);
}
```

What happens if:

```java
roles == null
```

Decide whether to:

```text
reject null
```

or:

```text
normalize null to empty
```

Do not leave this accidental.

---

## Empty Collections

```java
List.of()
List.copyOf(Collections.emptyList())
```

Understand their semantics and whether your API should return an empty collection instead of null.

---

## Mutable Elements

```java
List<MutableObject>
```

The collection can be unmodifiable while the elements remain mutable.

---

## Arrays

```java
byte[] data
```

Always consider defensive copies when arrays are part of immutable state.

---

## Subclassing

An apparently immutable superclass may be extended by a subclass that introduces mutable state.

Consider:

```java
final
```

or a carefully designed sealed hierarchy where appropriate.

---

# 2.4.38 Debugging Challenges

## Challenge 1 — Final Does Not Mean Immutable

```java
public final class User {

    private final List<String> roles;

    public User(List<String> roles) {
        this.roles = roles;
    }

    public List<String> roles() {
        return roles;
    }
}
```

Tasks:

- [ ] Identify the immutability violation.
- [ ] Show how a caller can mutate it.
- [ ] Fix the constructor.
- [ ] Fix the accessor.
- [ ] Explain whether `List.copyOf()` is appropriate.

---

## Challenge 2 — Array Exposure

```java
public final class Token {

    private final byte[] value;

    public Token(byte[] value) {
        this.value = value;
    }

    public byte[] value() {
        return value;
    }
}
```

Tasks:

- [ ] Demonstrate external mutation.
- [ ] Fix constructor exposure.
- [ ] Fix getter exposure.
- [ ] Explain why `final` does not help.

---

## Challenge 3 — Record Immutability

```java
record User(List<String> roles) {}
```

Tasks:

- [ ] Demonstrate mutation.
- [ ] Explain why the record is not deeply immutable.
- [ ] Add a compact constructor.
- [ ] Make the component safely exposed.

---

## Challenge 4 — Unmodifiable View

```java
List<String> source = new ArrayList<>();
List<String> view = Collections.unmodifiableList(source);

source.add("Java");

System.out.println(view);
```

Tasks:

- [ ] Predict the result.
- [ ] Explain why the view changes.
- [ ] Replace it with a snapshot when appropriate.

---

## Challenge 5 — Mutable HashMap Key

Create a mutable key:

```java
class Key {
    String value;
}
```

Use it in:

```java
HashMap<Key, String>
```

Then mutate `value`.

Tasks:

- [ ] Demonstrate lookup failure.
- [ ] Explain the hash-code problem.
- [ ] Redesign the key as an immutable value object.

---

# 2.4.39 Coding Exercises

## Basic

- [ ] Build a simple immutable `Person`.
- [ ] Use private final fields.
- [ ] Remove setters.
- [ ] Validate constructor input.
- [ ] Make the class final.
- [ ] Implement safe getters.
- [ ] Add `equals()` and `hashCode()`.

## Intermediate

- [ ] Build an immutable class containing a `List`.
- [ ] Add defensive copying.
- [ ] Build an immutable class containing a `Map`.
- [ ] Build an immutable class containing an array.
- [ ] Compare `Collections.unmodifiableList()` with `List.copyOf()`.
- [ ] Create a record with immutable collection components.
- [ ] Create a record containing an array and protect it.
- [ ] Use an immutable object as a `HashMap` key.

## Advanced

- [ ] Build a deeply immutable object graph.
- [ ] Design an immutable money/value object.
- [ ] Design an immutable configuration object.
- [ ] Implement immutable pagination parameters.
- [ ] Build an immutable request context.
- [ ] Benchmark defensive copies for large collections.
- [ ] Analyze object allocation caused by immutable transformations.
- [ ] Investigate final-field semantics in the Java Memory Model.

## Production-Style

Build an:

```text
Immutable Configuration System
```

Requirements:

- [ ] Configuration is immutable after startup.
- [ ] Validate all values during construction.
- [ ] Support nested configuration.
- [ ] Protect mutable collections.
- [ ] Support safe sharing across threads.
- [ ] Provide immutable snapshots.
- [ ] Use records where appropriate.
- [ ] Prevent accidental mutable references.
- [ ] Add tests proving external mutation cannot alter configuration.
- [ ] Document memory/copying trade-offs.

---

# 2.4.40 Interview Questions

## Basic

- [ ] What is immutability?
- [ ] Why is immutability useful?
- [ ] How do you create an immutable class?
- [ ] Why are fields usually private and final?
- [ ] Why is the class often final?
- [ ] What is defensive copying?
- [ ] Why should immutable objects not have setters?

## Intermediate

- [ ] Does final make an object immutable?
- [ ] Why is `final List<String>` still mutable?
- [ ] Why must arrays be defensively copied?
- [ ] Difference between unmodifiable and immutable collections?
- [ ] Difference between `Collections.unmodifiableList()` and `List.copyOf()`?
- [ ] Are Java records immutable?
- [ ] Why are immutable objects good map keys?
- [ ] Can an immutable object contain mutable objects?

## Advanced

- [ ] Explain shallow vs deep immutability.
- [ ] Explain defensive copying on input and output.
- [ ] Explain record immutability limitations.
- [ ] Explain final-field semantics under the Java Memory Model.
- [ ] Explain safe publication.
- [ ] Explain why immutability simplifies concurrency.
- [ ] Explain mutable nested state.
- [ ] Explain the performance costs of defensive copying.

## Senior / Production

- [ ] How would you design an immutable domain object?
- [ ] When should a mutable object be preferred?
- [ ] How would you make a large configuration object immutable?
- [ ] How would you prevent mutable state leaking through a REST API model?
- [ ] How would you detect accidental mutability in code review?
- [ ] How would you balance defensive-copy cost against safety?
- [ ] How does immutability affect GC pressure?
- [ ] How would you design immutable objects for high-concurrency systems?
- [ ] When are records appropriate for immutable DTOs?
- [ ] How would you guarantee deep immutability across a complex object graph?

---

# 2.4.41 Advanced Follow-ups

## Java Memory Model

Study:

```text
constructor
    ↓
final field initialization
    ↓
object publication
    ↓
other threads
```

Understand:

- [ ] Final-field semantics
- [ ] Safe publication
- [ ] Happens-before relationships
- [ ] Data races
- [ ] Mutable references inside final fields

---

# 2.4.42 Defensive Copying Strategy

For every mutable input ask:

```text
Can the caller mutate it?
        ↓
Yes
        ↓
Copy / normalize / reject
```

For every mutable output ask:

```text
Can the caller mutate internal state?
        ↓
Yes
        ↓
Return copy / immutable representation
```

For arrays:

```java
input.clone()
```

For collections:

```java
List.copyOf(...)
Set.copyOf(...)
Map.copyOf(...)
```

when their semantics fit the requirement.

---

# 2.4.43 Immutable Object Graph

For deep immutability:

```text
Root immutable object
        ↓
immutable field
        ↓
immutable nested object
        ↓
immutable collection
        ↓
immutable collection elements
```

Audit the entire reachable state graph.

Do not stop at:

```java
private final
```

---

# 2.4.44 Production Architecture

A useful architecture is:

```text
External Input
      ↓
Validation
      ↓
Normalization
      ↓
Immutable Domain Object
      ↓
Shared Across Services/Threads
      ↓
No mutation
```

For configuration:

```text
Configuration files
        ↓
Binding
        ↓
Validation
        ↓
Immutable configuration
        ↓
Application components
```

This reduces accidental state changes throughout the application.

---

# 2.4.45 Performance Investigation

Build two versions of a data model:

### Version A — Mutable

```java
object.setValue(...)
```

### Version B — Immutable

```java
object = object.withValue(...)
```

Measure:

- [ ] Allocation rate
- [ ] GC activity
- [ ] Throughput
- [ ] CPU
- [ ] Memory usage
- [ ] Thread contention
- [ ] Code complexity

Do not assume mutable or immutable is universally faster.

---

# 2.4.46 Production Review Checklist

Before approving an immutable class:

1. [ ] Is the class final or otherwise extension-safe?
2. [ ] Are fields private?
3. [ ] Are fields final?
4. [ ] Is construction complete and validated?
5. [ ] Are mutable inputs copied?
6. [ ] Are mutable outputs protected?
7. [ ] Are arrays cloned?
8. [ ] Are nested objects immutable?
9. [ ] Are collection elements immutable when required?
10. [ ] Are records being mistaken for deep immutability?
11. [ ] Are unmodifiable views being confused with snapshots?
12. [ ] Is safe publication considered?
13. [ ] Are `equals()`/`hashCode()` fields stable?
14. [ ] Are copying costs acceptable?
15. [ ] Has the design been tested against mutation attempts?

---

# 2.4.47 Final Mastery Gate

## Fundamentals

- [ ] Explain immutability.
- [ ] Explain why Java benefits from immutable objects.
- [ ] Explain final classes.
- [ ] Explain final fields.
- [ ] Explain constructor initialization.
- [ ] Explain immutable invariants.

## Defensive Copying

- [ ] Explain defensive copying.
- [ ] Protect mutable constructor arguments.
- [ ] Protect mutable return values.
- [ ] Protect arrays.
- [ ] Protect collections.
- [ ] Protect nested mutable objects.

## Collections

- [ ] Explain immutable/unmodifiable collection semantics.
- [ ] Explain `List.of()`.
- [ ] Explain `List.copyOf()`.
- [ ] Explain `Collections.unmodifiableList()`.
- [ ] Distinguish view vs snapshot.
- [ ] Explain shallow vs deep immutability.

## Records

- [ ] Explain record-generated state.
- [ ] Explain why records are not automatically deeply immutable.
- [ ] Protect mutable record components.
- [ ] Use compact constructors for normalization/validation.

## Concurrency

- [ ] Explain why immutable objects are easier to share.
- [ ] Explain final-field semantics.
- [ ] Explain safe publication.
- [ ] Explain why immutability reduces synchronization requirements.
- [ ] Explain why immutability does not solve every concurrency problem.

## Performance

- [ ] Explain defensive-copy costs.
- [ ] Explain allocation implications.
- [ ] Explain GC implications.
- [ ] Explain immutable transformation costs.
- [ ] Measure rather than assume.

## Production

- [ ] Design an immutable value object.
- [ ] Design immutable configuration.
- [ ] Design immutable API models.
- [ ] Prevent mutable state leakage.
- [ ] Use immutable objects safely across threads.
- [ ] Use immutable objects as map/set keys.
- [ ] Explain trade-offs to senior engineers.

## Interview

- [ ] Answer basic questions.
- [ ] Answer intermediate questions.
- [ ] Answer advanced questions.
- [ ] Answer senior/production questions.
- [ ] Explain why final does not mean immutable.
- [ ] Explain records' immutability limitations.
- [ ] Explain defensive copying.
- [ ] Explain shallow vs deep immutability.
- [ ] Explain immutability and the Java Memory Model.

---

# Final Module Status

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
