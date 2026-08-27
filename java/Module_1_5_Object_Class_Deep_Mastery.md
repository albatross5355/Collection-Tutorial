# Module 1.5 — Object Class
## Deep Mastery Guide

> **Prerequisite:** Module 1.4 — Object Initialization  
> **Goal:** Master `java.lang.Object`, Java's root class, and deeply understand `equals()`, `hashCode()`, `toString()`, `getClass()`, `clone()`, and the history/deprecation/removal of `finalize()`.

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

> **Explain → Implement → Explain internals → Handle edge cases → Discuss trade-offs → Debug → Use in a production scenario**

---

# 1.5.1 `java.lang.Object`

## 1. What is it?

`java.lang.Object` is the root superclass of Java's ordinary class hierarchy.

If a class does not explicitly extend another class:

```java
class User {
}
```

it implicitly extends `Object`.

Conceptually:

```text
Object
  ↑
 User
```

Important methods inherited by ordinary classes:

```text
equals()
hashCode()
toString()
getClass()
clone()
finalize()   ← legacy; deprecated for removal and removed from normal modern use
wait()
notify()
notifyAll()
```

`wait()` / `notify()` belong primarily to Java concurrency and should be studied deeply in the concurrency module.

## 2. Why does Java have it?

`Object` provides a common root and common object-level operations for:

- Identity
- Equality
- Hashing
- String representation
- Runtime type inspection
- Legacy cloning
- Legacy finalization
- Monitor synchronization

## 3. Syntax and API

```java
Object value = new Object();

value.equals(other);
value.hashCode();
value.toString();
value.getClass();
```

Legacy:

```java
value.clone();
value.finalize();
```

Do not use `finalize()` in new code.

## 4. Basic Example

```java
class User {

    private final String name;

    User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "'}";
    }
}

public class Main {

    public static void main(String[] args) {
        User user = new User("Alex");

        System.out.println(user);
        System.out.println(user.getClass());
        System.out.println(user.hashCode());
    }
}
```

## 5. Internal Working

Understand:

```text
Object
 ├── equals()
 ├── hashCode()
 ├── toString()
 ├── getClass()
 ├── clone()
 └── finalize() [legacy]
```

Study:

- [ ] Method dispatch
- [ ] Object identity
- [ ] Runtime class metadata
- [ ] Hashing
- [ ] Reflection
- [ ] JVM/runtime implementation where applicable

## 6. Memory / Runtime Behavior

Understand:

- [ ] Object identity
- [ ] Object references
- [ ] Runtime class metadata
- [ ] Object lifetime
- [ ] Garbage collection
- [ ] Object copying
- [ ] Runtime type information

Do not assume every `Object` method is implemented purely as ordinary Java code; some have JVM/runtime-level behavior.

## 7. Edge Cases

- [ ] `null`
- [ ] Inheritance
- [ ] Mutable objects
- [ ] Arrays
- [ ] Proxy objects
- [ ] Shallow cloning
- [ ] Non-cloneable objects
- [ ] Legacy finalization

## 8. Common Mistakes

- [ ] Treating `==` and `equals()` as identical
- [ ] Overriding `equals()` without `hashCode()`
- [ ] Using mutable objects as hash keys
- [ ] Using `toString()` as serialization
- [ ] Assuming `clone()` is a deep copy
- [ ] Using `finalize()` for cleanup

---

# 1.5.2 `equals()`

## 1. What is it?

`equals()` determines logical equality according to a class's equality contract.

The implementation inherited from `Object` is identity-based.

```java
a.equals(b)
```

does not automatically mean "same field values."

## 2. Why does Java have it?

Two different objects can represent the same logical value:

```java
User a = new User("Alex");
User b = new User("Alex");
```

They may be:

```text
Different objects
        +
Same logical value
```

## 3. Syntax and API

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }

    if (!(obj instanceof User other)) {
        return false;
    }

    return Objects.equals(id, other.id);
}
```

## 4. Basic Example

```java
import java.util.Objects;

class User {

    private final String id;

    User(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof User other)) {
            return false;
        }

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
```

## 5. Internal Working

Understand:

```text
a.equals(b)
    ↓
Selected equals implementation
    ↓
Compare relevant state
    ↓
true / false
```

## 6. Memory / Runtime Behavior

`equals()` does not inherently allocate memory, but an implementation may allocate temporary objects or traverse large object graphs.

Study:

- [ ] Reference identity
- [ ] Object state
- [ ] Aliasing
- [ ] Immutable equality fields
- [ ] Equality cost

## 7. Edge Cases

- [ ] `this == other`
- [ ] `null`
- [ ] Different classes
- [ ] Subclasses
- [ ] Mutable state
- [ ] Inheritance
- [ ] Proxy objects

## 8. The Equality Contract

### Reflexive

```text
x.equals(x) == true
```

### Symmetric

```text
x.equals(y) == y.equals(x)
```

### Transitive

```text
x == y
y == z
⇒ x == z
```

in terms of `equals()`.

### Consistent

Repeated calls should remain consistent while relevant state is unchanged.

### Non-null

```java
x.equals(null)
```

should return `false`.

## 9. Inheritance Trap

Equality can become difficult with:

```text
Money
  ↑
Voucher
```

Study:

- [ ] `instanceof` equality
- [ ] `getClass()` equality
- [ ] Symmetry
- [ ] Transitivity
- [ ] `canEqual()` pattern
- [ ] Composition
- [ ] Value objects
- [ ] Records

Equality is easiest to reason about when value-like classes are immutable and often final.

## 10. Common Mistakes

- [ ] Using `==`
- [ ] Ignoring `null`
- [ ] Violating symmetry
- [ ] Violating transitivity
- [ ] Using mutable state carelessly
- [ ] Overriding `equals()` without `hashCode()`

## 11. Performance Implications

Potential costs:

- Large collection comparisons
- Deep object graphs
- Repeated string comparisons
- Expensive nested equality

## 12. Production Use Cases

- Collections
- Caching
- Deduplication
- Domain value objects
- DTO comparison
- Tests
- Business rules

## 13. Interview Questions

### Basic

- [ ] `==` vs `equals()`?
- [ ] What does `Object.equals()` do?

### Intermediate

- [ ] What is the equality contract?
- [ ] Why should `equals(null)` return false?

### Advanced

- [ ] `instanceof` vs `getClass()` in equality?
- [ ] Why can inheritance break equality?

### Senior / Production

- [ ] How would you design equality for an entity?
- [ ] How can bad equality break collections?

---

# 1.5.3 `hashCode()`

## 1. What is it?

`hashCode()` returns an integer hash value used heavily by hash-based data structures.

Important collections:

```text
HashMap
HashSet
ConcurrentHashMap
```

## 2. Why does Java have it?

It helps hash-based structures efficiently locate candidate entries.

Conceptually:

```text
key
 ↓
hashCode()
 ↓
hash transformation
 ↓
bucket
 ↓
equals()
```

## 3. Critical Contract

If:

```java
a.equals(b)
```

is `true`, then:

```java
a.hashCode() == b.hashCode()
```

must be true.

But:

```text
same hash
   ≠
equal objects
```

Collisions are valid.

## 4. Basic Example

```java
@Override
public int hashCode() {
    return Objects.hash(id, name);
}
```

## 5. Internal Working

For a hash-based collection:

```text
hashCode()
    ↓
bucket selection
    ↓
collision handling
    ↓
equals()
```

Study HashMap internals separately in the Collections module.

## 6. Memory / Runtime Behavior

Potential concerns:

- [ ] Hash computation cost
- [ ] Temporary allocation
- [ ] Cached hash
- [ ] Mutable key state

## 7. Edge Cases

- [ ] Hash collisions
- [ ] Mutable keys
- [ ] Null fields
- [ ] Hash changes after insertion
- [ ] Integer overflow in hash calculation

### Critical Example

```java
Map<User, String> map = new HashMap<>();

User user = new User("1");

map.put(user, "value");

// If a field used by hashCode changes,
// lookup may fail.
```

## 8. Common Mistakes

- [ ] Overriding `equals()` but not `hashCode()`
- [ ] Mutable HashMap keys
- [ ] Assuming hashes are unique
- [ ] Assuming collisions indicate a bug
- [ ] Writing unnecessarily expensive hashes

## 9. Performance Implications

Poor hash distribution can cause:

```text
More collisions
    ↓
More comparisons
    ↓
Slower operations
```

Master:

- [ ] Distribution
- [ ] Collision rate
- [ ] Hash calculation cost
- [ ] Resizing
- [ ] Hash spreading

## 10. Production Use Cases

- HashMap keys
- HashSet elements
- Caches
- Deduplication
- Partitioning concepts

## 11. Interview Questions

### Basic

- [ ] Why override `hashCode()` with `equals()`?

### Intermediate

- [ ] Can two unequal objects have the same hash code?

### Advanced

- [ ] Why are mutable keys dangerous?

### Senior

- [ ] How can poor hash distribution affect production latency?
- [ ] How would you design a hash for a domain object?

---

# 1.5.4 `toString()`

## 1. What is it?

`toString()` provides a string representation of an object.

The default `Object.toString()` produces an implementation-defined identity-oriented representation based on the runtime class name and hash-code-related information.

Do not treat its exact formatting as a stable API contract.

## 2. Why does Java have it?

Useful for:

- Debugging
- Logging
- Diagnostics
- Developer-facing output

## 3. Basic Example

```java
@Override
public String toString() {
    return "User{id='" + id + "', name='" + name + "'}";
}
```

## 4. Internal Working

Conceptually:

```text
System.out.println(object)
        ↓
String conversion
        ↓
object.toString()
```

## 5. Memory / Runtime Behavior

String creation can allocate memory.

Avoid expensive `toString()` implementations that:

- Traverse huge graphs
- Trigger database access
- Perform network calls
- Serialize massive collections

## 6. Edge Cases

- [ ] Sensitive fields
- [ ] Circular references
- [ ] Large collections
- [ ] Null fields
- [ ] Lazy-loaded relationships
- [ ] Logging overhead

## 7. Common Mistakes

Never casually expose:

- Passwords
- Access tokens
- API keys
- Secrets

Also avoid:

- [ ] Database access
- [ ] Network access
- [ ] Huge object graphs
- [ ] Treating toString as serialization

## 8. Production Use Cases

Use for:

- Debugging
- Logging
- Diagnostics

Do not use it as a stable:

- API format
- Database format
- Security identity
- Serialization protocol

## 9. Interview Questions

- [ ] Why override `toString()`?
- [ ] What does default `Object.toString()` represent?
- [ ] Why should secrets never be included?

---

# 1.5.5 `getClass()`

## 1. What is it?

`getClass()` returns the runtime `Class<?>` object representing the actual runtime class of the object.

```java
Object value = "Hello";

System.out.println(value.getClass());
```

## 2. Why does Java have it?

It supports:

- Reflection
- Runtime type inspection
- Framework infrastructure
- Diagnostics
- Dynamic behavior

## 3. `getClass()` vs `ClassName.class`

```java
user.getClass()
```

asks:

> What is the runtime class of this object?

```java
User.class
```

asks:

> What `Class` object represents the `User` type?

## 4. Basic Example

```java
Object value = "Hello";

Class<?> type = value.getClass();

System.out.println(type.getName());
System.out.println(type.getSimpleName());
```

## 5. Internal Working

Conceptually:

```text
Object
  ↓
Runtime class metadata
  ↓
Class<?> object
```

## 6. Memory / Runtime Behavior

The `Class<?>` object is associated with runtime class metadata and its defining class loader.

Study:

- [ ] Class objects
- [ ] Class loaders
- [ ] Runtime metadata
- [ ] Reflection

## 7. Edge Cases

### Polymorphism

```java
Animal animal = new Dog();

animal.getClass();
```

returns the runtime class:

```text
Dog
```

not `Animal`.

### Framework Proxies

Frameworks can generate proxy objects, so:

```java
object.getClass()
```

may return a proxy class.

This becomes particularly important with Spring and ORM frameworks.

## 8. Common Mistakes

- [ ] Confusing runtime and compile-time type
- [ ] Using `getClass()` when `instanceof` is appropriate
- [ ] Assuming proxies always have the business class
- [ ] Treating class names as permanent identifiers

## 9. Performance

`getClass()` itself is generally inexpensive.

Reflection performed afterward can have additional cost.

## 10. Interview Questions

### Basic

- [ ] What does `getClass()` return?

### Intermediate

- [ ] `getClass()` vs `.class`?

### Advanced

- [ ] What happens with a polymorphic reference?

### Senior

- [ ] Why can proxies complicate exact class checks?
- [ ] When should `instanceof` be preferred?

---

# 1.5.6 `clone()`

## 1. What is it?

`clone()` is a legacy object-copying mechanism.

When supported correctly, `Object.clone()` performs a field-by-field copy.

It is normally a **shallow copy**.

## 2. Why does Java have it?

It was part of Java's original object model.

However, `Cloneable` and `Object.clone()` have significant design limitations.

Modern Java often prefers:

- Copy constructors
- Static factories
- Explicit copy methods
- Builders

## 3. Syntax

`Object.clone()` is protected:

```java
protected native Object clone() throws CloneNotSupportedException;
```

A legacy clone implementation may look like:

```java
class User implements Cloneable {

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
```

## 4. Basic Example

```java
class User implements Cloneable {

    String name;

    User(String name) {
        this.name = name;
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
```

## 5. Internal Working

Conceptually:

```text
Original object
      ↓
clone()
      ↓
New object
      ↓
Field-by-field copy
```

For reference fields:

```text
Original
   |
   +----> Address

Clone
   |
   +----> SAME Address
```

Thus the default clone mechanism is shallow.

## 6. Memory / Runtime Behavior

For:

```java
class Person implements Cloneable {
    String name;
    Address address;
}
```

the clone contains copied field values, but the `address` reference can point to the same object.

Master:

- [ ] Object allocation
- [ ] Field copying
- [ ] Reference aliasing
- [ ] Nested mutable objects
- [ ] GC implications

## 7. Edge Cases

- [ ] `CloneNotSupportedException`
- [ ] Shallow copy
- [ ] Deep copy
- [ ] Mutable nested objects
- [ ] Inheritance
- [ ] Final fields
- [ ] Arrays
- [ ] Cloneable marker interface

### Arrays

```java
int[] original = {1, 2, 3};
int[] copy = original.clone();
```

Primitive elements are copied.

For reference arrays, references are copied.

## 8. Common Mistakes

- [ ] Assuming clone means deep copy
- [ ] Blindly implementing `Cloneable`
- [ ] Ignoring nested mutable state
- [ ] Assuming all objects are cloneable
- [ ] Using cloning as the default copying mechanism

## 9. Performance Implications

Shallow cloning can be efficient for simple objects, but deep copying large object graphs can be expensive.

Measure before choosing a copying strategy.

## 10. Production Use Cases

Prefer:

```java
new User(existingUser)
```

or:

```java
User.copyOf(existingUser)
```

when explicit copy semantics are clearer.

## 11. Interview Questions

### Basic

- [ ] What is `clone()`?
- [ ] What is `Cloneable`?

### Intermediate

- [ ] Shallow vs deep copy?
- [ ] Why can `clone()` throw `CloneNotSupportedException`?

### Advanced

- [ ] Why is `Cloneable` considered problematic?
- [ ] How are reference fields handled?

### Senior

- [ ] Why choose a copy constructor over clone?
- [ ] How would you safely copy a complex object graph?

---

# 1.5.7 `finalize()` — History, Deprecation & Removal

## 1. What was it?

`finalize()` was a legacy mechanism intended to allow cleanup before an object was reclaimed.

Historically:

```java
@Override
protected void finalize() throws Throwable {
    // cleanup
}
```

## 2. Why did Java have it?

The original idea was:

```text
Object becomes unreachable
        ↓
GC identifies it
        ↓
Finalization may occur
        ↓
finalize()
        ↓
Object can eventually be reclaimed
```

This model proved unreliable.

## 3. Why was it deprecated?

Finalization was deprecated for removal in **Java 9**.

Major problems included:

- [ ] Unpredictable timing
- [ ] No deterministic cleanup
- [ ] GC overhead
- [ ] Performance problems
- [ ] Object resurrection
- [ ] Security risks
- [ ] Resource exhaustion
- [ ] Difficult reasoning and debugging

## 4. Removal Context

**JEP 421**, delivered with JDK 18, formally deprecated finalization for removal and provided the migration path away from it.

The transition continued in later JDK releases, including runtime controls that allowed finalization to be disabled.

For modern Java development:

> **Do not design new code around `finalize()`.**

Study the exact current JDK behavior and release notes when maintaining legacy applications.

## 5. Correct Alternatives

### Try-with-resources

```java
try (var input = new FileInputStream("data.txt")) {
    // use resource
}
```

### AutoCloseable

```java
class Resource implements AutoCloseable {

    @Override
    public void close() {
        // deterministic cleanup
    }
}
```

### Cleaner

Java also provides:

```java
java.lang.ref.Cleaner
```

for specialized fallback cleanup scenarios.

However:

> `Cleaner` is not a substitute for deterministic resource management.

Prefer explicit ownership and `close()`.

## 6. Why Finalization Was Dangerous

### Unpredictable timing

You cannot depend on prompt execution after an object becomes unreachable.

### Resource exhaustion

If finalization is delayed, resources such as:

- File descriptors
- Native resources
- Sockets
- Other scarce handles

may remain unavailable for too long.

### Resurrection

Legacy finalizers could make an otherwise unreachable object reachable again.

This made object lifetime reasoning much harder.

## 7. Production Rule

Never write new resource cleanup like:

```java
@Override
protected void finalize() {
    closeDatabaseConnection();
}
```

Prefer:

```java
try (DatabaseResource resource = ...) {
    // work
}
```

## 8. Interview Questions

### Basic

- [ ] What was `finalize()`?
- [ ] Should it be used in modern Java?

### Intermediate

- [ ] Why was finalization deprecated?

### Advanced

- [ ] What is object resurrection?
- [ ] Why is finalization unreliable?

### Senior / Production

- [ ] How would you migrate a legacy finalizer?
- [ ] Why is GC not a resource-management mechanism?
- [ ] When might `Cleaner` be justified?

---

# 1.5.8 `equals()` + `hashCode()` + Collections

This combination is critical.

For:

```java
Set<User> users = new HashSet<>();
```

the conceptual process is:

```text
hashCode()
    ↓
candidate bucket
    ↓
equals()
    ↓
actual equality
```

## Exercise

Create:

```java
class Employee {
    private final long id;
    private final String name;
}
```

Implement:

- [ ] `equals()`
- [ ] `hashCode()`
- [ ] `toString()`

Then:

```java
Set<Employee> employees = new HashSet<>();

employees.add(new Employee(1, "Alex"));
employees.add(new Employee(1, "Alex"));
```

Determine:

- [ ] Number of elements
- [ ] Why duplicates are detected
- [ ] Which methods are invoked
- [ ] What happens if `hashCode()` is incorrect

---

# 1.5.9 Mutable Key Failure Experiment

Create an object whose equality and hash code depend on a mutable field.

Then:

```java
Map<User, String> map = new HashMap<>();

User user = new User("A");

map.put(user, "value");

user.setId("B");

System.out.println(map.get(user));
```

Explain:

```text
Inserted using hash(A)
        ↓
Key mutated
        ↓
Lookup uses hash(B)
        ↓
Different bucket may be searched
        ↓
Entry may not be found
```

Master why mutable keys are dangerous.

---

# 1.5.10 Equality and Inheritance Experiment

Create:

```text
Money
  ↓
Voucher
```

Implement equality using:

```java
instanceof
```

Then using:

```java
getClass()
```

Compare:

- [ ] Symmetry
- [ ] Transitivity
- [ ] Substitutability
- [ ] Value semantics
- [ ] Inheritance complexity

Then consider redesigning the model using composition.

---

# 1.5.11 `toString()` Production Exercise

Create a domain object containing:

- [ ] ID
- [ ] Name
- [ ] Password
- [ ] Token
- [ ] Large collection

Implement `toString()` safely.

Ensure:

- [ ] Password is excluded.
- [ ] Token is excluded.
- [ ] Huge collection is not dumped.
- [ ] Useful diagnostic information remains.

---

# 1.5.12 Object Identity vs Equality

Master:

```text
Identity
   ↓
Same object?
   ↓
==
```

versus:

```text
Logical equality
   ↓
Equivalent according to domain contract?
   ↓
equals()
```

Example:

```java
String a = new String("hello");
String b = new String("hello");

System.out.println(a == b);
System.out.println(a.equals(b));
```

Explain why the results differ.

---

# 1.5.13 Records and Object Methods

Modern Java records automatically provide implementations of:

- [ ] `equals()`
- [ ] `hashCode()`
- [ ] `toString()`

Example:

```java
record User(String id, String name) {
}
```

Understand why records are useful for value-oriented data carriers.

---

# 1.5.14 Arrays and Object Methods

Arrays are objects, but their equality behavior is special.

```java
int[] a = {1, 2};
int[] b = {1, 2};
```

Understand:

```java
a.equals(b)
```

does not perform element-wise equality.

Use:

```java
Arrays.equals(a, b);
```

For nested arrays:

```java
Arrays.deepEquals(...)
```

Also study:

```java
Arrays.hashCode(...)
Arrays.deepHashCode(...)
Arrays.toString(...)
Arrays.deepToString(...)
```

---

# 1.5.15 Production Design Guidelines

## `equals()`

Use when logical equality matters.

Prefer equality based on stable state.

## `hashCode()`

Keep it consistent with `equals()`.

Prefer immutable keys.

## `toString()`

Keep it:

- Useful
- Small
- Safe
- Non-sensitive
- Free of side effects

## `getClass()`

Use for runtime type inspection and reflection.

Do not use it as a substitute for good polymorphic design.

## `clone()`

Prefer explicit copy APIs.

## `finalize()`

Do not use it.

Prefer:

- `AutoCloseable`
- try-with-resources
- Explicit lifecycle management
- Specialized `Cleaner` only when justified

---

# 1.5.16 Performance Checklist

Analyze:

- [ ] Cost of `equals()`
- [ ] Cost of `hashCode()`
- [ ] Hash distribution
- [ ] Object allocation during copying
- [ ] `toString()` allocation
- [ ] Reflection after `getClass()`
- [ ] Large object graphs
- [ ] Mutable keys
- [ ] Finalization overhead in legacy systems

---

# 1.5.17 Production Debugging Checklist

## Unexpected collection behavior

```text
1. Check equals()
2. Check hashCode()
3. Check key mutability
4. Check object state
5. Check hash distribution
6. Inspect collection contents
```

## Unexpected logging cost

```text
1. Inspect toString()
2. Check object graph size
3. Check logging level
4. Check serialization
5. Check allocations
```

## Resource cleanup problems

```text
1. Search for finalize()
2. Identify resource ownership
3. Introduce AutoCloseable
4. Use try-with-resources
5. Verify deterministic close behavior
```

---

# 1.5.18 Coding Exercises

## Basic

- [ ] Create a class and inspect inherited Object methods.
- [ ] Override `toString()`.
- [ ] Compare objects with `==`.
- [ ] Compare objects with `equals()`.
- [ ] Inspect `getClass()`.
- [ ] Inspect hash codes.

## Intermediate

- [ ] Implement `equals()` and `hashCode()`.
- [ ] Store custom objects in `HashSet`.
- [ ] Use custom objects as `HashMap` keys.
- [ ] Demonstrate hash collisions.
- [ ] Demonstrate mutable-key failure.
- [ ] Implement safe `toString()`.

## Advanced

- [ ] Design equality for an inheritance hierarchy.
- [ ] Compare `instanceof` vs `getClass()` equality.
- [ ] Implement a shallow-copy API.
- [ ] Implement a deep-copy API.
- [ ] Compare clone vs copy constructor.
- [ ] Analyze array equality.
- [ ] Inspect record-generated Object methods.

## Production-Style

Build:

```text
Product Catalog
      ↓
Product
      ↓
SKU / Price / Category
      ↓
HashMap + HashSet
```

Requirements:

- [ ] Immutable product identity
- [ ] Correct `equals()`
- [ ] Correct `hashCode()`
- [ ] Safe `toString()`
- [ ] Product as HashMap key
- [ ] Deliberate mutable-key failure experiment
- [ ] Record-based DTO
- [ ] Entity equality vs value-object equality
- [ ] `AutoCloseable` resource
- [ ] Explicitly explain why finalization is not used

---

# 1.5.19 Master Interview Question Bank

## Basic

- [ ] What is `Object`?
- [ ] What methods does `Object` provide?
- [ ] What is `equals()`?
- [ ] What is `hashCode()`?
- [ ] What is `toString()`?
- [ ] What does `getClass()` return?
- [ ] What is `clone()`?
- [ ] What was `finalize()`?

## Intermediate

- [ ] `==` vs `equals()`?
- [ ] What is the `equals()` contract?
- [ ] Why must equal objects have equal hash codes?
- [ ] Can unequal objects have the same hash code?
- [ ] Why are mutable HashMap keys dangerous?
- [ ] Shallow vs deep cloning?
- [ ] `getClass()` vs `.class`?

## Advanced

- [ ] Why can inheritance make `equals()` difficult?
- [ ] `instanceof` vs `getClass()` in equality?
- [ ] How does HashMap use `hashCode()` and `equals()`?
- [ ] Why is `Cloneable` considered problematic?
- [ ] Why was `finalize()` deprecated?
- [ ] What is object resurrection?
- [ ] How do records implement Object methods?

## Senior / Production

- [ ] How would you design equality for a persistence entity?
- [ ] Why are immutable keys preferred?
- [ ] How can poor hash distribution affect production latency?
- [ ] How would you diagnose a HashMap lookup failure?
- [ ] How would you design a safe `toString()` for production logs?
- [ ] How can framework proxies affect `getClass()` checks?
- [ ] How would you migrate legacy finalization-based cleanup?
- [ ] When would you use `Cleaner`, and why is explicit cleanup preferred?
- [ ] When should identity be used instead of logical equality?
- [ ] How would you model equality across an inheritance hierarchy?

---

# 1.5.20 Advanced Follow-ups

## JVM / Runtime

- [ ] Object identity
- [ ] Object headers
- [ ] Runtime class metadata
- [ ] Method dispatch
- [ ] Object copying
- [ ] GC lifecycle
- [ ] Finalization architecture

## Collections

- [ ] HashMap internals
- [ ] HashSet internals
- [ ] Hash collision handling
- [ ] Mutable keys
- [ ] Tree-based collections and comparison

## Reflection

- [ ] `Class<?>`
- [ ] Runtime type inspection
- [ ] Reflection
- [ ] Method handles
- [ ] Dynamic proxies

## Modern Java

- [ ] Records
- [ ] Pattern matching
- [ ] `instanceof`
- [ ] Immutable value objects
- [ ] `AutoCloseable`
- [ ] `Cleaner`

## Specifications / References

Study:

- [ ] Java API specification — `Object`
- [ ] Java API specification — `Class`
- [ ] Java API specification — `Cloneable`
- [ ] Java API specification — `AutoCloseable`
- [ ] JLS — Classes and objects
- [ ] JLS — Equality and reference types
- [ ] JVM Specification — Object creation
- [ ] JVM Specification — Method invocation
- [ ] JEP 421 — Deprecate Finalization for Removal
- [ ] OpenJDK implementation where useful

---

# 1.5.21 Final Mastery Gate

Do **not** mark Module 1.5 complete until you can:

- [ ] Explain `java.lang.Object`.
- [ ] Explain its role in Java's class hierarchy.
- [ ] Explain object identity.
- [ ] Explain `equals()`.
- [ ] Implement `equals()` correctly.
- [ ] Explain every part of the equality contract.
- [ ] Explain inheritance-related equality problems.
- [ ] Explain `hashCode()`.
- [ ] Explain the `equals()` / `hashCode()` relationship.
- [ ] Explain hash collisions.
- [ ] Debug mutable-key failures.
- [ ] Implement safe `toString()`.
- [ ] Explain `getClass()`.
- [ ] Explain runtime class vs compile-time type.
- [ ] Explain `clone()`.
- [ ] Explain shallow vs deep copying.
- [ ] Explain why `Cloneable` is problematic.
- [ ] Explain the history of `finalize()`.
- [ ] Explain why finalization was deprecated for removal.
- [ ] Explain deterministic resource management.
- [ ] Use try-with-resources correctly.
- [ ] Explain `Cleaner` and its limitations.
- [ ] Explain records' generated Object methods.
- [ ] Explain array equality differences.
- [ ] Debug Object-method-related collection bugs.
- [ ] Discuss performance implications.
- [ ] Discuss production trade-offs.
- [ ] Answer basic → senior interview questions.
- [ ] Complete basic → production coding exercises.
- [ ] Explain relevant JVM/runtime behavior.

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
