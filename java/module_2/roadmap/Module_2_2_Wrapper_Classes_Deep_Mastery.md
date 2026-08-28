# Module 2.2 — Wrapper Classes
## Deep Mastery Guide

> **Goal:** Master Java wrapper classes, autoboxing/unboxing, wrapper caching, null-unboxing failures, equality behavior, memory/allocation implications, performance trade-offs, and production usage.

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

# 2.2.1 Wrapper Classes — Overview

Java provides object representations for the eight primitive types:

| Primitive | Wrapper |
|---|---|
| `byte` | `Byte` |
| `short` | `Short` |
| `int` | `Integer` |
| `long` | `Long` |
| `float` | `Float` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

Wrappers are reference types and are useful when Java APIs require objects, especially generics and collections.

```java
int x = 10;
Integer y = 10;

double a = 10.5;
Double b = 10.5;
```

---

# 2.2.2 Why Wrapper Classes Exist

Primitive types are values, not reference types.

This is invalid:

```java
List<int> numbers;
```

This is valid:

```java
List<Integer> numbers;
```

Wrappers also provide APIs such as:

```java
Integer.parseInt(...)
Integer.valueOf(...)
Integer.compare(...)
Integer.toString(...)
```

They also allow nullable representations:

```java
Integer count = null;
```

whereas:

```java
int count = null; // invalid
```

---

# 2.2.3 `Integer`

Wrapper for `int`.

```java
Integer value = 100;
```

Important APIs:

```java
Integer.valueOf(...)
Integer.parseInt(...)
Integer.compare(...)
Integer.toString(...)
Integer.MIN_VALUE
Integer.MAX_VALUE
Integer.bitCount(...)
Integer.numberOfLeadingZeros(...)
Integer.numberOfTrailingZeros(...)
```

Important distinction:

```java
int x = Integer.parseInt("123");
Integer y = Integer.valueOf("123");
```

`parseInt()` returns `int`; `valueOf()` returns `Integer`.

---

# 2.2.4 `Long`

Wrapper for `long`.

```java
Long value = 100L;
```

Important APIs:

```java
Long.valueOf(...)
Long.parseLong(...)
Long.compare(...)
Long.toUnsignedLong(...)
Long.toUnsignedString(...)
Long.bitCount(...)
```

Constants:

```java
Long.MIN_VALUE
Long.MAX_VALUE
```

Typical uses:

- [ ] Large IDs
- [ ] Epoch values
- [ ] Large counters
- [ ] Nullable database fields

---

# 2.2.5 `Double`

Wrapper for `double`.

```java
Double value = 19.99;
```

Important APIs:

```java
Double.valueOf(...)
Double.parseDouble(...)
Double.isNaN(...)
Double.isInfinite(...)
Double.compare(...)
```

Remember:

> `Double.MIN_VALUE` is the smallest positive non-zero double, not the most negative double.

---

# 2.2.6 `Float`

Wrapper for `float`.

```java
Float value = 10.5f;
```

Important APIs:

```java
Float.valueOf(...)
Float.parseFloat(...)
Float.isNaN(...)
Float.isInfinite(...)
Float.compare(...)
```

---

# 2.2.7 `Short`

Wrapper for `short`.

```java
Short value = 100;
```

Important APIs:

```java
Short.valueOf(...)
Short.parseShort(...)
Short.compare(...)
```

---

# 2.2.8 `Byte`

Wrapper for `byte`.

```java
Byte value = 100;
```

Important APIs:

```java
Byte.valueOf(...)
Byte.parseByte(...)
Byte.compare(...)
```

Particularly relevant to binary protocols, byte streams, network data, and file processing.

---

# 2.2.9 `Character`

Wrapper for `char`.

```java
Character c = 'A';
```

Important APIs:

```java
Character.isLetter(...)
Character.isDigit(...)
Character.isWhitespace(...)
Character.isUpperCase(...)
Character.isLowerCase(...)
Character.toUpperCase(...)
Character.toLowerCase(...)
Character.codePointAt(...)
Character.charCount(...)
```

Important Unicode rule:

> A Java `char` is a UTF-16 code unit, not necessarily an entire Unicode code point.

---

# 2.2.10 `Boolean`

Wrapper for `boolean`.

```java
Boolean enabled = true;
```

Important APIs:

```java
Boolean.valueOf(...)
Boolean.parseBoolean(...)
Boolean.compare(...)
Boolean.logicalAnd(...)
Boolean.logicalOr(...)
Boolean.logicalXor(...)
```

Distinction:

```java
boolean a = Boolean.parseBoolean("true");
Boolean b = Boolean.valueOf("true");
```

---

# 2.2.11 `valueOf()` vs Constructors

Prefer:

```java
Integer.valueOf(100);
```

rather than old constructor-based creation.

Modern wrapper constructors such as `new Integer(...)` are deprecated.

`valueOf()` can reuse cached instances where applicable and avoids expressing an unnecessary explicit allocation.

Golden rule:

> Do not rely on wrapper identity for value comparison.

---

# 2.2.12 Autoboxing

Autoboxing is automatic primitive-to-wrapper conversion.

```java
int primitive = 10;
Integer wrapper = primitive;
```

Conceptually similar to:

```java
Integer wrapper = Integer.valueOf(primitive);
```

Example:

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(10);
```

The primitive value is boxed automatically.

---

# 2.2.13 Unboxing

Unboxing converts a wrapper to its primitive value.

```java
Integer wrapper = 10;
int primitive = wrapper;
```

Conceptually:

```java
int primitive = wrapper.intValue();
```

Example:

```java
Integer count = 10;

if (count > 5) {
    System.out.println("Large");
}
```

The comparison requires unboxing.

---

# 2.2.14 Null Unboxing

Critical trap:

```java
Integer value = null;
int result = value;
```

This throws:

```text
NullPointerException
```

Conceptually:

```java
int result = value.intValue();
```

The same issue can occur with:

```java
Integer
Long
Double
Float
Short
Byte
Character
Boolean
```

Production example:

```java
Integer retryCount = config.getRetryCount();

if (retryCount > 3) {
    ...
}
```

If `retryCount` is null, the comparison can fail.

Choose explicitly whether null means:

```text
missing
unknown
not applicable
default
```

rather than accidentally treating it as zero.

---

# 2.2.15 Autoboxing in Expressions

Consider:

```java
Integer a = 10;
Integer b = 20;

Integer c = a + b;
```

Conceptually:

```text
Integer
   ↓ unbox
int
   ↓
int + int
   ↓
int
   ↓ box
Integer
```

This hidden conversion matters for both correctness and performance.

---

# 2.2.16 Wrapper Caching

Some wrapper values can be cached.

The most important standard guarantee involves certain `Integer` values, especially:

```text
-128 through 127
```

Example:

```java
Integer a = 100;
Integer b = 100;

System.out.println(a == b);
```

This can be true.

For larger values:

```java
Integer a = 1000;
Integer b = 1000;

System.out.println(a == b);
```

identity must not be relied upon.

Correct value comparison:

```java
a.equals(b)
```

or:

```java
Objects.equals(a, b)
```

when null is possible.

---

# 2.2.17 Integer Cache

The Integer class maintains a cache for commonly used values.

The standard language guarantee covers:

```text
-128 to 127
```

Implementations may cache additional values.

Understand:

- [ ] Why caching exists
- [ ] `valueOf()` and caching
- [ ] Identity vs value equality
- [ ] Language guarantees vs implementation details
- [ ] Why business logic must never depend on wrapper identity

---

# 2.2.18 Wrapper Equality

For wrapper references:

```java
a == b
```

tests reference identity.

Whereas:

```java
a.equals(b)
```

tests logical value equality.

For nullable wrappers:

```java
Objects.equals(a, b)
```

is often safest.

Example:

```java
Integer a = 1000;
Integer b = 1000;

System.out.println(a == b);
System.out.println(a.equals(b));
```

The first is an identity comparison; the second is a value comparison.

---

# 2.2.19 Mixed Wrapper/Primitive Comparisons

```java
Integer a = 1000;
int b = 1000;

System.out.println(a == b);
```

Here `a` is unboxed and the values are compared.

This differs from:

```java
Integer a = 1000;
Integer b = 1000;

a == b
```

which compares references.

This is a classic interview and production-debugging trap.

---

# 2.2.20 Wrapper Immutability

Wrappers represent immutable values.

```java
Integer x = 10;
x = 20;
```

The original object was not mutated. The variable is simply associated with another value/reference.

This allows wrapper instances to be safely shared as values, subject to normal reference-publication and concurrency rules.

---

# 2.2.21 Wrapper Internals

Conceptually:

```text
Integer object
┌───────────────┐
│ int value     │
└───────────────┘
```

Important methods:

```java
intValue()
longValue()
doubleValue()
compareTo()
equals()
hashCode()
toString()
```

For:

```java
Integer x = 42;
int value = x.intValue();
```

the wrapper's represented primitive value is extracted.

---

# 2.2.22 Memory Behavior

Compare:

```java
int value = 42;
```

with:

```java
Integer value = 42;
```

A wrapper representation can involve:

```text
reference
   ↓
Integer object
   ↓
primitive value
```

while a primitive is stored directly in its relevant storage context.

Arrays demonstrate the difference:

```java
int[] a;
```

stores primitive values.

```java
Integer[] b;
```

stores references to wrapper objects.

This can affect:

- [ ] Memory consumption
- [ ] Allocation
- [ ] Cache locality
- [ ] GC pressure
- [ ] Throughput

---

# 2.2.23 Wrapper Allocation

Do not assume every boxing operation creates a new object.

Conceptually:

```text
primitive
   ↓
boxing
   ↓
cached wrapper OR newly created wrapper
```

Caching and runtime optimizations can avoid some allocations.

For hot paths, measure rather than guessing.

---

# 2.2.24 Performance Implications

Wrapper-heavy code can introduce:

- [ ] Object allocation
- [ ] Garbage collection pressure
- [ ] Reference indirection
- [ ] Poorer locality
- [ ] Unboxing operations
- [ ] Null checks
- [ ] Increased memory footprint

But wrappers are appropriate when:

- [ ] Nullability matters
- [ ] Generics require reference types
- [ ] Framework APIs require objects
- [ ] Object semantics are required

Do not replace every wrapper with a primitive without considering domain semantics.

---

# 2.2.25 Boxing in Loops

Potentially inefficient:

```java
Long total = 0L;

for (long value : values) {
    total += value;
}
```

Conceptually each `+=` may involve:

```text
Long
 ↓ unbox
long
 ↓ arithmetic
long
 ↓ box
Long
```

Prefer:

```java
long total = 0L;

for (long value : values) {
    total += value;
}
```

when nullable/object semantics are unnecessary.

---

# 2.2.26 Wrapper Types in Collections

Collections require reference types:

```java
List<Integer> numbers = new ArrayList<>();
```

Adding:

```java
numbers.add(10);
```

boxes the value.

Retrieving:

```java
int x = numbers.get(0);
```

unboxes it.

Conceptual flow:

```text
primitive
 ↓
box
 ↓
collection
 ↓
wrapper
 ↓
unbox
 ↓
primitive
```

For very large numerical workloads, consider primitive arrays or primitive-specialized collections where appropriate.

---

# 2.2.27 Nullability and API Design

Wrappers can represent:

```text
value
```

or:

```text
null
```

This is useful when:

```text
zero != missing
```

Examples:

- [ ] Nullable database columns
- [ ] Optional API fields
- [ ] Optional configuration
- [ ] Partial update requests

But nullable wrappers require deliberate null handling.

---

# 2.2.28 Database and API Boundary

Suppose a database column contains:

```text
NULL
```

Using:

```java
Integer retryCount;
```

can preserve:

```text
null
```

versus:

```text
0
```

If the domain requires this distinction, converting immediately to primitive `int` may lose important information.

At boundaries, explicitly define:

```text
missing
zero
unknown
not applicable
```

---

# 2.2.29 Parsing vs Boxing

Examples:

```java
int x = Integer.parseInt("123");
Integer y = Integer.valueOf("123");
```

Likewise for:

```text
Long
Double
Float
Short
Byte
Boolean
```

Understand whether an API returns:

```text
primitive
```

or:

```text
wrapper
```

because that changes nullability and boxing behavior.

---

# 2.2.30 Edge Cases

## Null

```java
Integer value = null;
int x = value;
```

Throws `NullPointerException`.

## Cache Boundary

Test:

```java
Integer a = 127;
Integer b = 127;

Integer c = 128;
Integer d = 128;
```

Understand identity behavior and why identity is not a value comparison.

## Mixed Comparison

```java
Integer a = 1000;
int b = 1000;

System.out.println(a == b);
```

## Wrapper Arithmetic

```java
Integer x = 1;
Integer y = 2;

Integer z = x + y;
```

Explain every boxing/unboxing step.

## Boolean

```java
Boolean active = null;

if (active) {
}
```

This can throw due to unboxing.

---

# 2.2.31 Common Mistakes

- [ ] Comparing wrappers with `==`.
- [ ] Assuming caching applies to every value.
- [ ] Assuming every boxing operation allocates.
- [ ] Unboxing a potentially null wrapper.
- [ ] Using wrapper objects unnecessarily in hot loops.
- [ ] Forgetting that collections require wrappers.
- [ ] Ignoring boxing inside streams/lambdas.
- [ ] Assuming wrappers are mutable.
- [ ] Confusing `parseXxx()` and `valueOf()`.
- [ ] Using `Double` without understanding floating-point precision.
- [ ] Treating identity as value equality.
- [ ] Using nullable wrappers without defining null semantics.
- [ ] Assuming primitive and wrapper arrays have equivalent memory layouts.

---

# 2.2.32 Debugging Challenges

## Challenge 1 — Integer Cache

Predict:

```java
Integer a = 100;
Integer b = 100;

System.out.println(a == b);
```

Then:

```java
Integer a = 1000;
Integer b = 1000;

System.out.println(a == b);
```

Explain the difference and give the correct comparison.

## Challenge 2 — Null Unboxing

```java
Integer count = null;

if (count > 0) {
    System.out.println("positive");
}
```

Tasks:

- [ ] Identify the failure.
- [ ] Explain the hidden unboxing.
- [ ] Fix it safely.
- [ ] Decide whether null should mean zero or unknown.

## Challenge 3 — Hidden Boxing

```java
Integer total = 0;

for (int i = 0; i < 1_000_000; i++) {
    total += i;
}
```

Tasks:

- [ ] Explain hidden conversions.
- [ ] Identify allocation/performance risks.
- [ ] Rewrite using `int`.
- [ ] Explain when the wrapper version could still be justified.

## Challenge 4 — Equality

```java
Integer a = 1000;
Integer b = 1000;

System.out.println(a == b);
System.out.println(a.equals(b));
```

Explain both operations.

## Challenge 5 — Mixed Types

```java
Integer a = 1000;
int b = 1000;

System.out.println(a == b);
```

Explain why this differs from wrapper-vs-wrapper comparison.

## Challenge 6 — Boolean

```java
Boolean active = null;

if (active) {
    System.out.println("active");
}
```

Identify the implicit operation and fix it.

---

# 2.2.33 Coding Exercises

## Basic

- [ ] Create all eight wrapper types.
- [ ] Demonstrate `valueOf()`.
- [ ] Demonstrate `parseXxx()`.
- [ ] Demonstrate `xxxValue()` methods.
- [ ] Demonstrate wrapper constants.
- [ ] Demonstrate `equals()`.
- [ ] Demonstrate `compareTo()`.
- [ ] Demonstrate autoboxing.
- [ ] Demonstrate unboxing.

## Intermediate

- [ ] Demonstrate Integer caching.
- [ ] Build an identity-vs-value equality program.
- [ ] Demonstrate null unboxing.
- [ ] Demonstrate mixed primitive/wrapper comparisons.
- [ ] Trace boxing/unboxing in arithmetic.
- [ ] Compare primitive and wrapper arrays.
- [ ] Build nullable configuration using wrappers.
- [ ] Convert strings into primitive and wrapper values.

## Advanced

- [ ] Inspect bytecode generated for boxing/unboxing.
- [ ] Benchmark boxing-heavy vs primitive code with JMH.
- [ ] Measure allocation and GC pressure.
- [ ] Benchmark `List<Integer>` against a primitive-specialized representation.
- [ ] Build a null-safe numeric conversion utility.
- [ ] Investigate Integer cache implementation in OpenJDK.
- [ ] Use JFR/profiling to identify boxing allocations.
- [ ] Analyze boxing introduced by streams and lambdas.

## Production-Style

Build a:

```text
Configuration + Metrics Processing Service
```

Requirements:

- [ ] Read optional numeric configuration.
- [ ] Preserve null vs zero semantics.
- [ ] Process large numbers of metrics.
- [ ] Avoid unnecessary boxing in hot paths.
- [ ] Use wrappers at nullable boundaries.
- [ ] Use primitives internally where appropriate.
- [ ] Handle invalid numeric input.
- [ ] Measure allocation rate.
- [ ] Document primitive/wrapper design decisions.

---

# 2.2.34 Interview Questions

## Basic

- [ ] What are wrapper classes?
- [ ] Why does Java need wrapper classes?
- [ ] Name all eight wrappers.
- [ ] What is autoboxing?
- [ ] What is unboxing?
- [ ] Difference between `parseInt()` and `valueOf()`?
- [ ] Why can't `List<int>` be used?

## Intermediate

- [ ] What happens during autoboxing?
- [ ] What happens during unboxing?
- [ ] What is wrapper caching?
- [ ] What is the Integer cache?
- [ ] Why should wrappers normally be compared with `equals()`?
- [ ] What happens when a null wrapper is unboxed?
- [ ] Why can `Integer a = 100; Integer b = 100; a == b` be true?
- [ ] Why can `Integer a = 1000; Integer b = 1000; a == b` be false?

## Advanced

- [ ] Explain boxing/unboxing in `Integer c = a + b`.
- [ ] Explain mixed wrapper/primitive equality.
- [ ] Explain Integer cache guarantees vs implementation-specific caching.
- [ ] Explain wrapper memory overhead.
- [ ] Explain how boxing increases GC pressure.
- [ ] Explain why wrapper-heavy collections can be slower.
- [ ] Explain how nullability affects API design.
- [ ] Explain wrapper immutability.

## Senior / Production

- [ ] When should `Integer` be used instead of `int`?
- [ ] How would you model nullable database numeric fields?
- [ ] How would you eliminate accidental boxing from a hot path?
- [ ] How would you detect boxing allocations?
- [ ] Why can wrapper counters create allocation pressure?
- [ ] How can boxing affect cache locality?
- [ ] When is `List<Integer>` appropriate despite boxing?
- [ ] How would you design a high-throughput numerical service?
- [ ] How would you prevent null-unboxing bugs across API boundaries?
- [ ] Which wrapper-related bugs have the highest production impact?

---

# 2.2.35 Advanced Follow-ups

## Compiler / Bytecode

Study:

```text
Source
  ↓
Autoboxing
  ↓
Compiler inserts valueOf(...)
  ↓
Bytecode
```

and:

```text
Wrapper
  ↓
Unboxing
  ↓
intValue()/longValue()/...
  ↓
Primitive operation
```

Inspect bytecode with:

```bash
javap -c -p ClassName
```

Investigate:

- [ ] `Integer.valueOf`
- [ ] `Integer.intValue`
- [ ] `Long.valueOf`
- [ ] `Long.longValue`
- [ ] Boxing inside loops
- [ ] Boxing inside lambdas
- [ ] Boxing inside streams

---

# 2.2.36 Wrapper Caching Deep Dive

Study:

```text
Integer
Long
Short
Byte
Character
Boolean
```

Understand:

- [ ] Which wrappers provide caching
- [ ] What the Java language guarantees
- [ ] What is implementation-dependent
- [ ] Why caching exists
- [ ] Why identity should not be used for value comparison
- [ ] How `valueOf()` interacts with caching

Core principle:

> Caching is not a substitute for value equality.

---

# 2.2.37 Performance Investigation

Create three implementations:

### Version A — Primitive

```java
long total = 0;
```

### Version B — Wrapper

```java
Long total = 0L;
```

### Version C — Atomic

```java
AtomicLong total = new AtomicLong();
```

Measure:

- [ ] Throughput
- [ ] Allocation rate
- [ ] Heap usage
- [ ] GC activity
- [ ] CPU
- [ ] Multithreaded contention

Use:

```text
JMH
JFR
VisualVM
JMC
GC logs
```

Do not conclude based only on theoretical overhead.

---

# 2.2.38 Production Design Rules

Use a primitive when:

```text
A value is mandatory
AND
null has no semantic meaning
AND
primitive representation is appropriate
```

Use a wrapper when:

```text
null is meaningful
OR
a generic/reference type is required
OR
a framework/API requires an object
OR
object semantics are intentionally needed
```

At boundaries:

```text
Input / API / DB
       ↓
Nullable wrapper where required
       ↓
Validation / normalization
       ↓
Primitive domain representation where appropriate
```

In hot paths:

```text
Prefer primitives where semantically valid
        ↓
Measure boxing
        ↓
Inspect allocation
        ↓
Optimize only when justified
```

---

# 2.2.39 Production Review Checklist

Before choosing a wrapper, ask:

1. [ ] Does null have a meaningful domain interpretation?
2. [ ] Does the API require a reference type?
3. [ ] Does a generic collection require the wrapper?
4. [ ] Is this code in a performance-sensitive path?
5. [ ] Could boxing happen repeatedly?
6. [ ] Could unboxing encounter null?
7. [ ] Does equality use value semantics?
8. [ ] Does caching affect any incorrect identity assumptions?
9. [ ] Is the value crossing a DB/API boundary?
10. [ ] Is the wrapper choice documented when non-obvious?

---

# 2.2.40 Final Mastery Gate

## Wrapper Fundamentals

- [ ] Explain all eight wrapper classes.
- [ ] Explain why wrappers exist.
- [ ] Explain primitive vs wrapper representation.
- [ ] Explain wrapper immutability.
- [ ] Explain important wrapper APIs.

## Autoboxing / Unboxing

- [ ] Explain autoboxing.
- [ ] Explain unboxing.
- [ ] Predict hidden boxing/unboxing.
- [ ] Explain boxing inside arithmetic.
- [ ] Explain boxing inside collections.
- [ ] Explain boxing inside loops.
- [ ] Explain boxing in streams/lambdas.

## Caching

- [ ] Explain wrapper caching.
- [ ] Explain Integer cache.
- [ ] Explain cache boundaries.
- [ ] Explain identity vs equality.
- [ ] Never depend on wrapper identity for value comparison.

## Null

- [ ] Explain null unboxing.
- [ ] Debug a NullPointerException caused by unboxing.
- [ ] Design null-safe wrapper APIs.
- [ ] Distinguish null from zero.
- [ ] Handle nullable Boolean safely.

## Performance

- [ ] Explain allocation overhead.
- [ ] Explain GC implications.
- [ ] Explain memory locality.
- [ ] Identify accidental boxing.
- [ ] Measure boxing with JMH/JFR.
- [ ] Explain when wrapper overhead matters.

## Production

- [ ] Choose primitive vs wrapper appropriately.
- [ ] Design nullable API/database boundaries.
- [ ] Prevent null-unboxing failures.
- [ ] Avoid unnecessary boxing in hot paths.
- [ ] Explain trade-offs to a senior engineering audience.
- [ ] Debug a real boxing/caching issue.

## Interview

- [ ] Answer basic questions.
- [ ] Answer intermediate questions.
- [ ] Answer advanced questions.
- [ ] Answer senior/production questions.
- [ ] Explain Integer caching precisely.
- [ ] Explain autoboxing from source code to bytecode.
- [ ] Explain wrapper performance implications.

---

# Final Module Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
