# Module 2.5 — Object Contracts
## Deep Mastery Guide

> **Goal:** Master Java's object contracts around `equals()`, `hashCode()`, `Comparable`, and `compareTo()`, including their formal rules, inheritance implications, hash-based collections, sorted collections, edge cases, debugging, performance, and production design.

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

# 2.5.1 Object Contracts — Overview

Java objects frequently participate in:

```text
Equality
   ↓
Hashing
   ↓
HashMap / HashSet

Ordering
   ↓
Comparable / Comparator
   ↓
TreeMap / TreeSet / sorting
```

The central contracts are:

```text
equals()
   ↕
hashCode()

compareTo()
   ↕
natural ordering
```

A class that violates these contracts can appear to work in simple tests while failing in collections, caches, persistence layers, or production systems.

---

# 2.5.2 Why Does Java Have Object Contracts?

Java needs a consistent way to answer:

### Equality

> "Do these two objects represent the same logical value?"

```java
a.equals(b)
```

### Hashing

> "Which hash bucket should this logically equal object belong to?"

```java
a.hashCode()
```

### Ordering

> "How should these two objects be ordered?"

```java
a.compareTo(b)
```

These concepts allow generic algorithms and collections to work without knowing the application's domain.

---

# 2.5.3 `equals()` — What Is It?

`equals()` determines logical equality between objects.

Defined in:

```java
java.lang.Object
```

Signature:

```java
public boolean equals(Object obj)
```

Default `Object.equals()` behaves essentially like identity comparison.

A domain class often overrides it to compare meaningful state.

Example:

```java
public final class UserId {

    private final String value;

    public UserId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UserId other)) {
            return false;
        }

        return value.equals(other.value);
    }
}
```

---

# 2.5.4 Why Override `equals()`?

Without overriding:

```java
new UserId("U100").equals(new UserId("U100"))
```

would normally be false because they are different object instances.

With logical equality:

```text
UserId("U100")
       =
UserId("U100")
```

even though:

```text
object references differ
```

This is essential for:

- [ ] Value objects
- [ ] Collections
- [ ] Testing
- [ ] Caching
- [ ] Domain modeling
- [ ] Deduplication

---

# 2.5.5 The `equals()` Contract

For non-null references `x`, `y`, and `z`, `equals()` should satisfy:

1. [ ] Reflexive
2. [ ] Symmetric
3. [ ] Transitive
4. [ ] Consistent
5. [ ] False when compared with `null`

These rules are not merely style guidelines.

They are required for predictable behavior in Java collections and APIs.

---

# 2.5.6 Reflexive

For any non-null object:

```java
x.equals(x)
```

must return:

```text
true
```

Example:

```java
UserId id = new UserId("U100");

System.out.println(id.equals(id)); // true
```

A broken implementation:

```java
@Override
public boolean equals(Object obj) {
    return false;
}
```

violates reflexivity.

---

# 2.5.7 Symmetric

For any non-null `x` and `y`:

```text
x.equals(y) == y.equals(x)
```

Example:

```text
x.equals(y)
      =
y.equals(x)
```

A common violation occurs when inheritance creates asymmetric equality.

---

# 2.5.8 Symmetry Trap with Inheritance

Suppose:

```java
class Money {
    BigDecimal amount;
}
```

and:

```java
class Voucher extends Money {
    String store;
}
```

If `Money.equals()` compares only `amount`, but `Voucher.equals()` compares both `amount` and `store`, you can get:

```text
money.equals(voucher)  → true
voucher.equals(money)  → false
```

This violates symmetry.

This is one reason value objects often use:

```java
final class
```

or carefully controlled equality semantics.

---

# 2.5.9 Transitive

For non-null `x`, `y`, and `z`:

```text
if x.equals(y)
and y.equals(z)

then x.equals(z)
```

Example:

```text
A = UserId("100")
B = UserId("100")
C = UserId("100")
```

Then:

```text
A = B
B = C
therefore
A = C
```

Equality implementations that normalize values inconsistently can accidentally violate transitivity.

---

# 2.5.10 Consistent

Repeated calls should return the same result as long as relevant object state has not changed.

```java
x.equals(y)
```

should not randomly alternate between:

```text
true
false
true
false
```

without a relevant state change.

External mutable state should not unexpectedly influence equality.

---

# 2.5.11 Null Comparison

For any non-null `x`:

```java
x.equals(null)
```

must return:

```text
false
```

It should not throw `NullPointerException`.

Example:

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }

    if (!(obj instanceof UserId other)) {
        return false;
    }

    return value.equals(other.value);
}
```

`instanceof` naturally handles null by returning false.

---

# 2.5.12 `==` vs `equals()`

For object references:

```java
a == b
```

checks:

```text
same object identity?
```

Whereas:

```java
a.equals(b)
```

checks:

```text
same logical value?
```

Example:

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a == b);       // false
System.out.println(a.equals(b));  // true
```

Do not use `==` when logical equality is required.

---

# 2.5.13 `equals()` Basic Implementation Pattern

A common modern implementation:

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }

    if (!(obj instanceof UserId other)) {
        return false;
    }

    return Objects.equals(value, other.value);
}
```

Use:

```java
Objects.equals(a, b)
```

when fields can be null.

---

# 2.5.14 `hashCode()` — What Is It?

`hashCode()` returns an integer hash value representing an object's equality-relevant state.

Signature:

```java
public int hashCode()
```

It is defined by `Object`.

Hash-based collections use it to locate candidate buckets efficiently.

Typical flow:

```text
object
  ↓
hashCode()
  ↓
hash spreading / processing
  ↓
bucket index
  ↓
candidate entries
  ↓
equals()
```

---

# 2.5.15 `hashCode()` Contract

The key rule is:

> If two objects are equal according to `equals()`, they must have the same `hashCode()`.

Formally:

```text
x.equals(y) == true
        ↓
x.hashCode() == y.hashCode()
```

The reverse is NOT required:

```text
same hashCode
    ≠
objects must be equal
```

Hash collisions are allowed.

---

# 2.5.16 Hash Collision

Different objects can have the same hash:

```text
A.hashCode() = 42
B.hashCode() = 42
```

while:

```text
A.equals(B) = false
```

This is a collision.

Hash-based collections handle collisions by comparing candidate keys using equality.

Therefore:

```text
hashCode()
    ↓
fast candidate location
    ↓
equals()
    ↓
final equality decision
```

---

# 2.5.17 Why `equals()` and `hashCode()` Must Agree

Suppose:

```java
class UserId {
    String value;

    @Override
    public boolean equals(Object o) {
        ...
    }

    // hashCode() NOT overridden
}
```

Then two logically equal IDs may have different hash codes.

A `HashMap` can place them into different buckets.

Result:

```text
put(key1, value)
       ↓
bucket A

get(key2)
       ↓
different hash
       ↓
bucket B
       ↓
lookup fails
```

This is a classic production bug.

---

# 2.5.18 `HashMap` Lookup Flow

Conceptually:

```text
map.get(key)
      ↓
key.hashCode()
      ↓
hash transformation/spreading
      ↓
bucket selection
      ↓
candidate node(s)
      ↓
identity and equality checks
      ↓
matching key
      ↓
value
```

Therefore both methods matter.

---

# 2.5.19 `HashSet` and Object Contracts

`HashSet` is backed by hash-based machinery.

When adding:

```java
set.add(object);
```

the collection uses hash/equality semantics to determine whether an equivalent element already exists.

Conceptually:

```text
add(x)
 ↓
hashCode()
 ↓
bucket
 ↓
equals()
 ↓
duplicate?
 ↓
store / reject
```

Incorrect equality/hash-code implementations can therefore cause:

- [ ] Duplicate logical values
- [ ] Failed `contains()`
- [ ] Failed `remove()`
- [ ] Unexpected set size

---

# 2.5.20 Mutable Hash Keys — Major Production Trap

Consider:

```java
class User {
    String id;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        ...
    }
}
```

Then:

```java
User user = new User("U100");

Map<User, String> map = new HashMap<>();
map.put(user, "Alice");

user.id = "U200";
```

The object's hash code may now change.

The map still stores the entry according to the old bucket location.

Then:

```java
map.get(user);
```

may fail.

Golden rule:

> Do not mutate equality/hash-code state while an object is being used as a key in a hash-based collection.

---

# 2.5.21 Immutable Keys

Excellent map key:

```java
public record UserId(String value) {
}
```

or:

```java
public final class UserId {
    private final String value;
}
```

Benefits:

```text
stable state
    ↓
stable equals()
    ↓
stable hashCode()
    ↓
safe HashMap/HashSet usage
```

---

# 2.5.22 Implementing `hashCode()`

Example:

```java
@Override
public int hashCode() {
    return Objects.hash(value);
}
```

For multiple fields:

```java
@Override
public int hashCode() {
    return Objects.hash(firstName, lastName, age);
}
```

The fields used should correspond to the equality definition.

Important:

> If `equals()` ignores a field, `hashCode()` should normally ignore that field too.

---

# 2.5.23 Equality Field Selection

Suppose:

```java
class User {
    String id;
    String name;
    Instant createdAt;
}
```

If identity is defined by:

```text
id
```

then equality should normally use:

```text
id
```

rather than:

```text
id + name + createdAt
```

The question is:

> What does this class mean by "same object"?

Equality is a domain-design decision, not merely an IDE-generated method.

---

# 2.5.24 `Comparable`

`Comparable<T>` defines a type's natural ordering.

Interface:

```java
public interface Comparable<T> {
    int compareTo(T other);
}
```

Example:

```java
public final class UserId implements Comparable<UserId> {

    private final String value;

    @Override
    public int compareTo(UserId other) {
        return value.compareTo(other.value);
    }
}
```

Then:

```java
id1.compareTo(id2)
```

determines ordering.

---

# 2.5.25 `compareTo()` Return Value

`compareTo()` returns:

```text
negative → this < other
zero     → this == other in ordering
positive → this > other
```

Do NOT depend on the exact positive/negative number.

This is enough:

```java
if (a.compareTo(b) < 0) {
    ...
}
```

Avoid:

```java
if (a.compareTo(b) == -1)
```

because implementations are not required to return exactly `-1`.

---

# 2.5.26 Natural Ordering

Examples:

```java
Integer
String
LocalDate
BigDecimal
```

implement `Comparable`.

For example:

```java
LocalDate a = LocalDate.of(2026, 1, 1);
LocalDate b = LocalDate.of(2027, 1, 1);

a.compareTo(b);
```

The result describes their natural chronological ordering.

---

# 2.5.27 `Comparable` vs `Comparator`

`Comparable`:

```text
type defines its natural ordering
```

`Comparator`:

```text
external object defines an ordering
```

Example:

```java
users.sort(Comparator.comparing(User::name));
```

A class can have one natural ordering but many possible application-specific orderings.

---

# 2.5.28 Why `compareTo()` Exists

Ordering is needed for:

- [ ] Sorting
- [ ] `TreeSet`
- [ ] `TreeMap`
- [ ] Min/max operations
- [ ] Priority queues
- [ ] Range queries
- [ ] Ordered algorithms

Example:

```java
List<Integer> values = ...
Collections.sort(values);
```

The natural ordering can be used.

---

# 2.5.29 `compareTo()` Contract

Natural ordering should be:

- [ ] Antisymmetric in sign
- [ ] Transitive
- [ ] Consistent
- [ ] Comparable to the ordering semantics of the type

If:

```text
x.compareTo(y) < 0
```

then normally:

```text
y.compareTo(x) > 0
```

If:

```text
x.compareTo(y) == 0
```

then both objects are equivalent according to natural ordering.

---

# 2.5.30 Compare-to Consistency with Equals

Ideally:

```text
x.compareTo(y) == 0
        ↕
x.equals(y) == true
```

This is called consistency between natural ordering and equality.

But Java does not require every `Comparable` implementation to have this property.

This distinction is extremely important.

---

# 2.5.31 The `BigDecimal` Example

A famous example:

```java
BigDecimal a = new BigDecimal("1.0");
BigDecimal b = new BigDecimal("1.00");
```

They can be:

```java
a.equals(b)       // false
a.compareTo(b)    // 0
```

Why?

`equals()` considers scale.

`compareTo()` considers numerical value for ordering.

Therefore:

```text
equals()       → different representation
compareTo()    → same numerical ordering
```

---

# 2.5.32 Why This Matters for `TreeSet`

`TreeSet` uses ordering to determine uniqueness.

Example:

```java
Set<BigDecimal> set = new TreeSet<>();

set.add(new BigDecimal("1.0"));
set.add(new BigDecimal("1.00"));
```

The set can treat them as the same ordered element because:

```java
compareTo() == 0
```

even though:

```java
equals() == false
```

This can surprise developers expecting `Set` to universally use `equals()`.

Golden rule:

> Sorted collections use ordering semantics, not necessarily `equals()` semantics, to determine element equivalence.

---

# 2.5.33 `TreeMap` and Comparator Equality

Similarly:

```java
TreeMap<Key, Value>
```

uses natural ordering or a supplied `Comparator`.

If:

```java
compare(key1, key2) == 0
```

the map treats them as the same ordered key.

This can differ from:

```java
key1.equals(key2)
```

Therefore the choice of ordering is part of the collection's semantics.

---

# 2.5.34 Bad `compareTo()` Implementation

Avoid:

```java
return this.age - other.age;
```

Why?

Integer overflow can produce incorrect results.

Example:

```text
Integer.MAX_VALUE - (-1)
```

can overflow.

Prefer:

```java
return Integer.compare(this.age, other.age);
```

For long:

```java
return Long.compare(this.id, other.id);
```

---

# 2.5.35 Comparing Multiple Fields

Example:

```java
@Override
public int compareTo(User other) {
    int result = this.lastName.compareTo(other.lastName);

    if (result != 0) {
        return result;
    }

    return this.firstName.compareTo(other.firstName);
}
```

Modern style:

```java
return Comparator
        .comparing(User::lastName)
        .thenComparing(User::firstName)
        .compare(this, other);
```

Be careful that the ordering matches the intended domain semantics.

---

# 2.5.36 Null Ordering

Decide whether null values are allowed.

Using:

```java
Comparator.nullsFirst(...)
Comparator.nullsLast(...)
```

can explicitly define ordering.

Example:

```java
Comparator<String> comparator =
        Comparator.nullsFirst(String::compareTo);
```

Do not allow accidental null behavior to define your domain ordering.

---

# 2.5.37 Floating-Point Edge Cases

Be careful with:

```java
Double
Float
```

and equality/ordering.

Special values include:

```text
NaN
+0.0
-0.0
+Infinity
-Infinity
```

Understand:

- [ ] `Double.equals()`
- [ ] `Double.compare()`
- [ ] primitive `==`
- [ ] NaN behavior
- [ ] signed zero behavior

Do not design numerical equality without understanding these cases.

---

# 2.5.38 Array Equality

Arrays do not override `Object.equals()` for element-wise equality.

This:

```java
int[] a = {1, 2};
int[] b = {1, 2};

a.equals(b);
```

does not perform element-wise comparison.

Use:

```java
Arrays.equals(a, b);
```

For nested arrays:

```java
Arrays.deepEquals(...)
```

Likewise:

```java
Arrays.hashCode(...)
Arrays.deepHashCode(...)
```

are relevant when arrays participate in equality/hash calculations.

---

# 2.5.39 Collection Equality

Many standard collections define logical equality based on their contents.

For example:

```java
List.of("A", "B").equals(List.of("A", "B"))
```

is true.

But equality semantics differ across collection types.

Understand:

- [ ] List equality
- [ ] Set equality
- [ ] Map equality
- [ ] Ordering-sensitive equality
- [ ] Ordering-insensitive equality

---

# 2.5.40 `Objects.equals()`

Useful for null-safe field comparison:

```java
Objects.equals(a, b)
```

Equivalent conceptual logic:

```text
both null → true
one null   → false
both non-null → a.equals(b)
```

This avoids:

```java
if (a != null && a.equals(b))
```

when concise null-safe comparison is desired.

---

# 2.5.41 Equality and Inheritance

Inheritance complicates equality.

Questions to answer:

- [ ] Should subclass and superclass instances be equal?
- [ ] Is equality based on exact class?
- [ ] Is equality based on `instanceof`?
- [ ] Can subclass state affect equality?
- [ ] Can symmetry break?
- [ ] Can transitivity break?

Common patterns include:

```java
getClass() == other.getClass()
```

or:

```java
instanceof
```

Neither is universally correct.

Choose based on the domain model.

---

# 2.5.42 `getClass()` vs `instanceof` in `equals()`

Strict type equality:

```java
if (getClass() != obj.getClass()) {
    return false;
}
```

allows equality only between the exact same runtime class.

Polymorphic equality:

```java
if (!(obj instanceof User other)) {
    return false;
}
```

can allow equality across compatible types.

The choice has inheritance consequences.

---

# 2.5.43 Common Mistakes

- [ ] Overriding `equals()` without `hashCode()`.
- [ ] Comparing objects with `==`.
- [ ] Using mutable fields in hash-code identity.
- [ ] Mutating map keys after insertion.
- [ ] Using `compareTo() == -1`.
- [ ] Implementing `compareTo()` with subtraction.
- [ ] Assuming `compareTo() == 0` always means `equals() == true`.
- [ ] Ignoring `BigDecimal` semantics.
- [ ] Assuming `TreeSet` always uses `equals()`.
- [ ] Forgetting null behavior.
- [ ] Creating asymmetric equality through inheritance.
- [ ] Including different fields in `equals()` and `hashCode()`.
- [ ] Using arrays with `equals()` expecting content equality.
- [ ] Ignoring floating-point special values.
- [ ] Treating hash collisions as equality.

---

# 2.5.44 Edge Cases

## Null

```java
object.equals(null)
```

must return false for a non-null object.

---

## Same Reference

```java
object.equals(object)
```

must be true.

---

## Different Instances, Same Value

```java
new UserId("100")
new UserId("100")
```

should be equal if `UserId` is a value object.

---

## Mutable Hash State

Insert an object into:

```java
HashMap
HashSet
```

then mutate an equality-relevant field.

Predict lookup/removal behavior.

---

## Hash Collision

Create two unequal objects with the same hash code.

Verify:

```text
same hash
≠
same object
```

---

## BigDecimal

Test:

```java
new BigDecimal("1.0")
new BigDecimal("1.00")
```

with:

```java
equals()
compareTo()
HashSet
TreeSet
```

---

## Floating Point

Test:

```java
Double.NaN
+0.0
-0.0
```

with:

```java
==
equals()
compareTo()
```

---

# 2.5.45 Debugging Challenges

## Challenge 1 — Missing `hashCode()`

Create:

```java
class UserId {
    String value;

    @Override
    public boolean equals(Object o) {
        ...
    }
}
```

Then:

```java
Set<UserId> set = new HashSet<>();

set.add(new UserId("U100"));
set.add(new UserId("U100"));
```

Tasks:

- [ ] Predict the set size.
- [ ] Explain the failure.
- [ ] Add `hashCode()`.
- [ ] Verify the result.

---

## Challenge 2 — Mutable Key

```java
Map<User, String> map = new HashMap<>();

User user = new User("U100");

map.put(user, "Alice");

user.setId("U200");

System.out.println(map.get(user));
```

Tasks:

- [ ] Predict the result.
- [ ] Explain bucket placement.
- [ ] Explain why lookup can fail.
- [ ] Redesign the key as immutable.

---

## Challenge 3 — BigDecimal

```java
BigDecimal a = new BigDecimal("1.0");
BigDecimal b = new BigDecimal("1.00");
```

Test:

```java
a.equals(b)
a.compareTo(b)
```

Then compare:

```java
HashSet
TreeSet
```

behavior.

---

## Challenge 4 — Bad `compareTo()`

```java
@Override
public int compareTo(User other) {
    return this.age - other.age;
}
```

Tasks:

- [ ] Find the overflow problem.
- [ ] Construct a failing input.
- [ ] Replace with `Integer.compare()`.

---

## Challenge 5 — Symmetry

Create a superclass/subclass equality implementation that produces:

```text
a.equals(b) == true
b.equals(a) == false
```

Tasks:

- [ ] Identify the violation.
- [ ] Explain why inheritance caused it.
- [ ] Redesign the equality strategy.

---

## Challenge 6 — TreeSet Surprise

```java
Set<BigDecimal> values = new TreeSet<>();

values.add(new BigDecimal("1.0"));
values.add(new BigDecimal("1.00"));

System.out.println(values.size());
```

Explain why the size may differ from what `HashSet` would produce.

---

# 2.5.46 Coding Exercises

## Basic

- [ ] Implement `equals()` for a simple value object.
- [ ] Implement matching `hashCode()`.
- [ ] Test reflexivity.
- [ ] Test symmetry.
- [ ] Test transitivity.
- [ ] Test consistency.
- [ ] Test null comparison.
- [ ] Compare identity vs logical equality.

## Intermediate

- [ ] Create an immutable `UserId`.
- [ ] Use it as a `HashMap` key.
- [ ] Use it in a `HashSet`.
- [ ] Create intentional hash collisions.
- [ ] Implement `Comparable`.
- [ ] Sort objects using natural ordering.
- [ ] Implement multi-field `compareTo()`.
- [ ] Create equivalent `Comparator` logic.
- [ ] Compare `HashSet` and `TreeSet` behavior.

## Advanced

- [ ] Demonstrate mutable-key corruption.
- [ ] Build equality tests for an inheritance hierarchy.
- [ ] Compare `instanceof` vs `getClass()` equality.
- [ ] Analyze `BigDecimal` equality/ordering behavior.
- [ ] Test floating-point equality edge cases.
- [ ] Implement array-aware equality.
- [ ] Build property-based tests for equality contracts.
- [ ] Benchmark different hash functions for a domain object.
- [ ] Inspect `HashMap` lookup behavior with collisions.

## Production-Style

Build an:

```text
Immutable Domain Identity and Pricing Model
```

Requirements:

- [ ] `ProductId` value object.
- [ ] `Money` value object.
- [ ] Stable `equals()`/`hashCode()`.
- [ ] Natural ordering where meaningful.
- [ ] Explicit alternative `Comparator`s.
- [ ] Safe use as `HashMap`/`HashSet` keys.
- [ ] Correct behavior in `TreeMap`/`TreeSet`.
- [ ] Tests for all object contracts.
- [ ] Tests for null and boundary values.
- [ ] Documentation explaining equality semantics.

---

# 2.5.47 Interview Questions

## Basic

- [ ] What is `equals()`?
- [ ] What is `hashCode()`?
- [ ] Why override both?
- [ ] What is reflexivity?
- [ ] What is symmetry?
- [ ] What is transitivity?
- [ ] What does consistency mean?
- [ ] What should `equals(null)` return?
- [ ] What does `compareTo()` return?

## Intermediate

- [ ] What happens if `equals()` is overridden but `hashCode()` is not?
- [ ] Can two unequal objects have the same hash code?
- [ ] Why shouldn't `==` be used for object value comparison?
- [ ] Why shouldn't `compareTo()` use subtraction?
- [ ] What is natural ordering?
- [ ] Difference between `Comparable` and `Comparator`?
- [ ] What happens when a mutable key is changed after insertion into a `HashMap`?
- [ ] Why can `TreeSet` behave differently from `HashSet`?

## Advanced

- [ ] Explain the complete `HashMap` equality lookup flow.
- [ ] Explain symmetry problems caused by inheritance.
- [ ] Explain `instanceof` vs `getClass()` in `equals()`.
- [ ] Explain consistency between `equals()` and `compareTo()`.
- [ ] Explain the `BigDecimal` equality/ordering difference.
- [ ] Explain hash collisions.
- [ ] Explain why hash-code equality does not imply object equality.
- [ ] Explain mutable key corruption.
- [ ] Explain array equality and hashing.

## Senior / Production

- [ ] How would you define equality for a domain entity?
- [ ] How does equality differ between an entity and a value object?
- [ ] Which fields should participate in `equals()` and `hashCode()`?
- [ ] How would you design immutable map keys?
- [ ] How would you review an equality implementation?
- [ ] How would you test the equality contract systematically?
- [ ] How would you diagnose a production `HashMap.get()` returning null for an apparently present key?
- [ ] How would you design ordering for a domain object with multiple valid sort orders?
- [ ] When should natural ordering intentionally differ from equality?
- [ ] How would you handle equality in an inheritance hierarchy?

---

# 2.5.48 Advanced Follow-ups

## Object Contract Relationship

Master this chain:

```text
equals()
   ↓
logical equality
   ↓
hashCode()
   ↓
HashMap / HashSet
```

And:

```text
Comparable
   ↓
compareTo()
   ↓
natural ordering
   ↓
TreeMap / TreeSet / sorting
```

---

# 2.5.49 HashMap / HashSet Internals

Study the conceptual lookup path:

```text
Key
 ↓
hashCode()
 ↓
hash spreading
 ↓
bucket index
 ↓
candidate entries
 ↓
identity / equals()
 ↓
match
```

Be able to explain:

- [ ] Why hashCode is needed.
- [ ] Why equals is still needed.
- [ ] What a collision is.
- [ ] Why mutable keys are dangerous.
- [ ] Why bad hash distribution hurts performance.
- [ ] Why equal objects must have equal hashes.

---

# 2.5.50 TreeMap / TreeSet Internals

Study:

```text
Key
 ↓
Comparator / compareTo()
 ↓
ordering decision
 ↓
tree navigation
 ↓
equivalent ordered key?
```

Understand:

- [ ] Natural ordering.
- [ ] Custom comparators.
- [ ] `compareTo() == 0`.
- [ ] Ordering-based uniqueness.
- [ ] Why inconsistent ordering can surprise users.
- [ ] Why `TreeSet` and `HashSet` can have different notions of duplicate.

---

# 2.5.51 Equality and Domain Modeling

Before implementing `equals()`, answer:

```text
What makes two instances "the same"?
```

### Entity

Identity may be:

```text
database ID
business identifier
```

### Value Object

Equality usually depends on:

```text
all value-defining components
```

Examples:

```text
Money
Coordinates
EmailAddress
UserId
DateRange
```

This distinction is essential for enterprise application design.

---

# 2.5.52 Performance Implications

`equals()` and `hashCode()` can be called frequently.

Potential concerns:

- [ ] Expensive field traversal
- [ ] Large collections as equality state
- [ ] Deep object graphs
- [ ] Poor hash distribution
- [ ] Repeated hashing
- [ ] Excessive string/array processing
- [ ] Allocation inside equality/hash methods

Avoid allocating unnecessarily inside:

```java
equals()
hashCode()
compareTo()
```

These methods can execute on hot paths.

---

# 2.5.53 Hash Distribution

A good hash function should distribute common inputs reasonably across buckets.

Poor distribution can cause:

```text
many keys
   ↓
few buckets
   ↓
many collisions
   ↓
more equality checks
   ↓
slower operations
```

For custom classes:

```java
return Objects.hash(field1, field2);
```

is often a good baseline.

For specialized high-performance structures, hash design may require deeper analysis.

---

# 2.5.54 Performance of `compareTo()`

Ordering functions can also become hot paths.

Avoid:

```text
database access
network calls
heavy computation
object allocation
```

inside:

```java
compareTo()
Comparator.compare()
```

A comparator should normally be:

```text
deterministic
fast
side-effect free
```

---

# 2.5.55 Production Review Checklist

Before approving `equals()`/`hashCode()`:

1. [ ] What defines logical identity?
2. [ ] Is `equals()` reflexive?
3. [ ] Is it symmetric?
4. [ ] Is it transitive?
5. [ ] Is it consistent?
6. [ ] Does non-null vs null return false?
7. [ ] Does `hashCode()` use the same equality state?
8. [ ] Are equality fields immutable?
9. [ ] Can the object be used safely as a map/set key?
10. [ ] Are inheritance semantics deliberate?
11. [ ] Are arrays compared correctly?
12. [ ] Are floating-point edge cases considered?
13. [ ] Is performance acceptable?

Before approving `compareTo()`:

1. [ ] Is the ordering clearly defined?
2. [ ] Is it transitive?
3. [ ] Is sign symmetry respected?
4. [ ] Is overflow avoided?
5. [ ] Is null behavior explicit?
6. [ ] Is it deterministic?
7. [ ] Is `compareTo() == 0` consistent with equality when intended?
8. [ ] Are multiple ordering requirements handled with `Comparator`s?
9. [ ] Does it avoid expensive side effects?
10. [ ] Is TreeMap/TreeSet behavior understood?

---

# 2.5.56 Production Debugging Workflow

If:

```java
map.get(key)
```

unexpectedly returns null:

```text
1. Check key equality
       ↓
2. Check hashCode
       ↓
3. Check whether key mutated
       ↓
4. Check class/type equality
       ↓
5. Check hash collisions/distribution
       ↓
6. Check whether a different key instance is logically equal
       ↓
7. Reproduce with HashMap/HashSet test
```

If:

```java
TreeSet
```

unexpectedly removes/deduplicates values:

```text
1. Inspect compareTo()
       ↓
2. Inspect Comparator
       ↓
3. Check compare() == 0
       ↓
4. Compare against equals()
       ↓
5. Check whether ordering is consistent with intended uniqueness
```

---

# 2.5.57 Testing the Equality Contract

For a class `UserId`, write explicit tests:

### Reflexive

```java
assertTrue(a.equals(a));
```

### Symmetric

```java
assertEquals(a.equals(b), b.equals(a));
```

### Transitive

```java
assertTrue(a.equals(b));
assertTrue(b.equals(c));
assertTrue(a.equals(c));
```

### Consistent

Repeatedly call:

```java
a.equals(b)
```

without changing relevant state.

### Null

```java
assertFalse(a.equals(null));
```

### Hash contract

```java
if (a.equals(b)) {
    assertEquals(a.hashCode(), b.hashCode());
}
```

---

# 2.5.58 Testing Comparable

For objects `a`, `b`, `c`, test:

```text
a < b
b < c
therefore a < c
```

Also test sign reversal:

```text
sign(a.compareTo(b))
=
-sign(b.compareTo(a))
```

When appropriate, test:

```text
compareTo() == 0
```

against:

```text
equals()
```

and explicitly document exceptions such as `BigDecimal`.

---

# 2.5.59 Final Mastery Gate

## `equals()`

- [ ] Explain logical equality.
- [ ] Explain identity vs equality.
- [ ] Implement `equals()`.
- [ ] Explain reflexivity.
- [ ] Explain symmetry.
- [ ] Explain transitivity.
- [ ] Explain consistency.
- [ ] Handle null correctly.
- [ ] Handle inheritance deliberately.
- [ ] Handle arrays correctly.

## `hashCode()`

- [ ] Explain the hash-code contract.
- [ ] Implement matching `hashCode()`.
- [ ] Explain collisions.
- [ ] Explain HashMap lookup.
- [ ] Explain HashSet uniqueness.
- [ ] Debug a missing-hashCode bug.
- [ ] Debug a mutable-key bug.
- [ ] Explain hash distribution.

## Comparable

- [ ] Explain natural ordering.
- [ ] Implement `Comparable`.
- [ ] Implement `compareTo()`.
- [ ] Explain negative/zero/positive semantics.
- [ ] Avoid subtraction overflow.
- [ ] Handle multiple fields.
- [ ] Understand null ordering.

## Equality vs Ordering

- [ ] Explain `equals()` vs `compareTo()`.
- [ ] Explain consistency between them.
- [ ] Explain why consistency is recommended but not universal.
- [ ] Explain the `BigDecimal` example.
- [ ] Explain TreeSet/TreeMap semantics.
- [ ] Explain HashSet/HashMap semantics.

## Performance

- [ ] Explain hash distribution.
- [ ] Explain equality cost.
- [ ] Explain comparison cost.
- [ ] Avoid allocation in hot contract methods.
- [ ] Measure behavior where necessary.

## Production

- [ ] Design a correct immutable value object.
- [ ] Safely use it as a HashMap key.
- [ ] Safely use it in HashSet.
- [ ] Define natural ordering where appropriate.
- [ ] Provide alternative Comparators.
- [ ] Diagnose collection lookup failures.
- [ ] Diagnose TreeSet/TreeMap surprises.
- [ ] Explain entity vs value-object equality.

## Interview

- [ ] Answer basic questions.
- [ ] Answer intermediate questions.
- [ ] Answer advanced questions.
- [ ] Answer senior/production questions.
- [ ] Explain every `equals()` contract rule.
- [ ] Explain the `hashCode()` contract.
- [ ] Explain mutable-key corruption.
- [ ] Explain Comparable/Comparator.
- [ ] Explain `BigDecimal` equality vs ordering.
- [ ] Explain HashMap vs TreeMap equality semantics.

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
