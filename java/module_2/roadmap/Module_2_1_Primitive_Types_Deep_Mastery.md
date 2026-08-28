# Module 2.1 — Primitive Types
## Deep Mastery Guide

> **Goal:** Master Java's eight primitive types, their ranges and representations, numeric promotion, overflow/underflow, floating-point behavior, memory/runtime implications, and production trade-offs.

---

# Mastery Cycle

For **every topic**, complete:

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

# 2.1.1 Primitive Types — Overview

Java has **eight primitive types**:

```text
Integral
├── byte
├── short
├── int
└── long

Floating-point
├── float
└── double

Character
└── char

Boolean
└── boolean
```

| Primitive | Category | Size | Typical Use |
|---|---|---:|---|
| `byte` | Integral | 8 bits | Binary data, byte streams |
| `short` | Integral | 16 bits | Specialized compact numeric data |
| `int` | Integral | 32 bits | Default integer arithmetic |
| `long` | Integral | 64 bits | Large integers, timestamps, counters |
| `float` | Floating point | 32 bits | Lower-precision numerical data |
| `double` | Floating point | 64 bits | General floating-point calculations |
| `char` | Character | 16 bits | UTF-16 code units |
| `boolean` | Boolean | Language-defined representation | Logical true/false |

> Numeric primitive widths and value semantics are specified by Java. Do not assume every JVM physically stores every primitive in exactly the source-level width in every context. `boolean` representation is especially implementation-dependent.

---

# 2.1.2 `byte`

## What is it?

A signed 8-bit two's-complement integer.

```text
-128 to 127
```

Formula:

```text
-2^7 to 2^7 - 1
```

## Syntax

```java
byte value = 100;
```

## Basic Example

```java
byte temperature = 25;
System.out.println(temperature);
```

## Overflow

```java
byte value = 127;
value++;
System.out.println(value); // -128
```

## Integer Promotion

```java
byte a = 10;
byte b = 20;

int result = a + b;
```

`byte + byte` produces an `int` expression because of numeric promotion.

## Production Use

Best suited to:

- Binary data
- Network/file buffers
- Protocol payloads
- Specialized compact representations

Do not choose `byte` merely because a business value happens to be small.

---

# 2.1.3 `short`

## What is it?

A signed 16-bit two's-complement integer.

```text
-32,768 to 32,767
```

Formula:

```text
-2^15 to 2^15 - 1
```

## Example

```java
short port = 8080;
```

## Promotion

```java
short a = 10;
short b = 20;

int result = a + b;
```

## Overflow

```java
short value = 32767;
value++;
System.out.println(value); // -32768
```

## Production Use

Less common in normal application code. Consider it for:

- Binary formats
- Interoperability
- Specialized memory-sensitive structures

---

# 2.1.4 `int`

## What is it?

A signed 32-bit two's-complement integer.

```text
-2,147,483,648 to 2,147,483,647
```

Formula:

```text
-2^31 to 2^31 - 1
```

## Example

```java
int count = 100;
```

## Constants

```java
Integer.MIN_VALUE
Integer.MAX_VALUE
```

## Overflow

```java
int max = Integer.MAX_VALUE;
int result = max + 1;

System.out.println(result); // -2147483648
```

## Production Use

Typical for:

- Counters
- Array indexes
- Loop variables
- Collection sizes
- Ordinary integer calculations

---

# 2.1.5 `long`

## What is it?

A signed 64-bit two's-complement integer.

```text
-9,223,372,036,854,775,808
to
 9,223,372,036,854,775,807
```

Formula:

```text
-2^63 to 2^63 - 1
```

## Syntax

Use `L` for long literals when needed:

```java
long population = 8_000_000_000L;
```

## Important Trap

This performs `int` arithmetic first:

```java
long value = 2_000_000_000 + 2_000_000_000;
```

Prefer:

```java
long value = 2_000_000_000L + 2_000_000_000L;
```

## Production Use

Common for:

- Large counters
- IDs
- Epoch timestamps
- File sizes
- Sequence numbers
- Byte counts

---

# 2.1.6 `float`

## What is it?

A 32-bit IEEE 754 binary floating-point type with roughly 6–7 decimal digits of precision.

## Syntax

Floating-point literals default to `double`, so:

```java
float x = 10.5f;
```

## Special Values

```java
Float.POSITIVE_INFINITY
Float.NEGATIVE_INFINITY
Float.NaN
Float.MAX_VALUE
Float.MIN_VALUE
```

Important:

> `Float.MIN_VALUE` is the smallest positive non-zero float, not the most negative float.

## Precision

```java
float value = 0.1f;
```

The stored value is generally an approximation.

## Production Use

Use deliberately where its precision/range is acceptable, such as some graphics or numerical workloads.

---

# 2.1.7 `double`

## What is it?

A 64-bit IEEE 754 binary floating-point type with roughly 15–17 decimal digits of precision.

## Example

```java
double price = 19.99;
double ratio = 0.75;
```

## NaN

```java
double x = Double.NaN;

System.out.println(x == x); // false
System.out.println(Double.isNaN(x)); // true
```

## Infinity

```java
double x = 1.0 / 0.0;
System.out.println(x); // Infinity
```

## Production Use

Useful for:

- Scientific calculations
- Measurements
- Statistics
- Approximate numerical calculations

Do not automatically use it for exact monetary calculations.

---

# 2.1.8 `char`

## What is it?

`char` is a 16-bit unsigned UTF-16 **code unit**.

Range:

```text
0 to 65,535
```

## Example

```java
char c = 'A';
```

## Numeric Conversion

```java
char c = 'A';
int value = c;

System.out.println(value); // 65
```

## Unicode

A `char` is not necessarily a complete Unicode code point.

Some characters outside the Basic Multilingual Plane require a surrogate pair:

```text
high surrogate + low surrogate
```

Study:

```java
Character.codePointAt(...)
Character.charCount(...)
String.codePoints()
```

## Production Importance

This matters for:

- Internationalization
- Emoji
- Non-BMP Unicode
- Text processing
- Security-sensitive text handling

Do not assume:

```text
1 char == 1 user-visible character
```

---

# 2.1.9 `boolean`

## What is it?

A primitive logical type with two values:

```text
true
false
```

## Example

```java
boolean active = true;
```

## Operators

```java
!
&&
||
^
```

## Short-Circuit Evaluation

```java
if (user != null && user.isActive()) {
}
```

The second operand is not evaluated if the first is false.

Likewise:

```java
if (conditionA || conditionB) {
}
```

does not evaluate the second operand when the first is true.

## Important Rule

Java does not treat numbers as booleans:

```java
if (1) { } // invalid
```

## Production Use

Use for binary state. For multiple mutually exclusive states, consider an enum instead of several independent booleans.

---

# 2.1.10 Numeric Ranges

## Integral Types

| Type | Bits | Minimum | Maximum |
|---|---:|---:|---:|
| `byte` | 8 | -128 | 127 |
| `short` | 16 | -32,768 | 32,767 |
| `int` | 32 | -2³¹ | 2³¹ - 1 |
| `long` | 64 | -2⁶³ | 2⁶³ - 1 |

## Floating Point

Floating-point values include:

```text
negative finite values
zero
positive finite values
negative infinity
positive infinity
NaN
```

Distinguish:

```text
smallest positive value
```

from:

```text
most negative value
```

For example:

```java
Float.MIN_VALUE
```

is positive.

---

# 2.1.11 Overflow

## Integer Overflow

Occurs when an integer result exceeds the representable range.

```java
int x = Integer.MAX_VALUE;
int y = x + 1;
```

The result wraps according to Java's fixed-width integer semantics.

## Detecting Overflow

Use exact arithmetic methods:

```java
Math.addExact(...)
Math.subtractExact(...)
Math.multiplyExact(...)
Math.incrementExact(...)
Math.decrementExact(...)
Math.toIntExact(...)
Math.negateExact(...)
```

Example:

```java
int result = Math.addExact(Integer.MAX_VALUE, 1);
```

This throws `ArithmeticException`.

## Production Risks

Overflow can cause:

- Incorrect counters
- Incorrect financial calculations
- Incorrect pagination
- Incorrect capacity calculations
- Timestamp bugs
- Security issues

---

# 2.1.12 Underflow

## Integer Underflow

Going below the minimum representable integer:

```java
int x = Integer.MIN_VALUE;
x--;
```

Result:

```text
Integer.MAX_VALUE
```

## Floating-Point Underflow

A floating-point value can become too small for normal representation.

It may become:

```text
subnormal
```

and eventually:

```text
0.0
```

Study:

- [ ] Normal values
- [ ] Subnormal values
- [ ] Gradual underflow
- [ ] IEEE 754 rounding
- [ ] Precision loss

---

# 2.1.13 Integer Promotion

This is a critical Java language rule.

## Basic Rule

In many numeric operations, `byte`, `short`, and `char` are promoted to `int`.

```java
byte a = 10;
byte b = 20;

var result = a + b;
```

`result` is an `int`.

## `char`

```java
char a = 'A';
char b = 1;

int result = a + b;
```

## Wider Types

```java
int i = 10;
long l = 20;

var result = i + l; // long
```

```java
long l = 10;
float f = 20;

var result = l + f; // float
```

```java
float f = 10;
double d = 20;

var result = f + d; // double
```

Mental model:

```text
byte
short
char
   ↓
 int
   ↓
 long
   ↓
 float
   ↓
 double
```

Learn the precise JLS rules rather than relying only on this simplified ladder.

---

# 2.1.14 Compound Assignment and Narrowing

This compiles:

```java
byte x = 10;
x += 20;
```

But this does not:

```java
byte x = 10;
x = x + 20;
```

because:

```java
x + 20
```

is an `int` expression.

Compound assignment includes an implicit conversion back to the left-hand type.

## Dangerous Example

```java
byte x = 127;
x += 1;

System.out.println(x); // -128
```

The implicit conversion can lose information.

---

# 2.1.15 Floating-Point Precision

## Core Problem

Most decimal fractions cannot be represented exactly in binary floating point.

```java
double x = 0.1;
```

stores an approximation.

## Famous Example

```java
double result = 0.1 + 0.2;

System.out.println(result == 0.3);
```

Typically:

```text
false
```

## Why?

Binary floating point represents fractions based on powers of two:

```text
1/2
1/4
1/8
1/16
...
```

But:

```text
0.1 = 1/10
```

does not have a finite binary representation.

## Comparing Floating-Point Values

Do not blindly use:

```java
a == b
```

for approximate numerical calculations.

Depending on the domain, consider:

```java
Math.abs(a - b) < epsilon
```

or more appropriate absolute/relative/ULP-based comparisons.

A single universal epsilon is not correct for every numerical problem.

---

# 2.1.16 Floating-Point Special Values

Master:

```text
NaN
+Infinity
-Infinity
+0.0
-0.0
subnormal values
```

## NaN

```java
double x = Double.NaN;

x == x       // false
x < x        // false
x > x        // false
```

Use:

```java
Double.isNaN(x)
```

## Infinity

```java
Double.isInfinite(x)
```

## Negative Zero

Floating point distinguishes:

```text
+0.0
-0.0
```

This can affect certain numerical operations.

---

# 2.1.17 Floating Point vs Money

Do not assume:

```java
double amount = 0.1;
```

provides exact decimal arithmetic.

For domains requiring exact decimal semantics, consider:

```java
BigDecimal
```

or integer minor units such as:

```java
long cents = 1099;
```

The correct design depends on:

- [ ] Currency
- [ ] Range
- [ ] Rounding rules
- [ ] Tax calculations
- [ ] Serialization
- [ ] Regulatory requirements

---

# 2.1.18 Primitive Literals

## Decimal

```java
int x = 100;
```

## Binary

```java
int x = 0b1010;
```

## Octal

```java
int x = 012;
```

Be careful: a leading zero indicates octal syntax.

## Hexadecimal

```java
int x = 0xFF;
```

## Underscores

```java
int population = 1_400_000_000;
long distance = 3_000_000_000L;
```

Study where underscores are and are not legal around:

- [ ] Prefixes
- [ ] Decimal points
- [ ] Exponents
- [ ] Type suffixes

---

# 2.1.19 Type Casting

## Widening

```java
int x = 100;
long y = x;
```

## Narrowing

```java
long x = 1000;
int y = (int) x;
```

Narrowing can lose information.

## Floating to Integer

```java
double x = 10.9;
int y = (int) x;
```

The fractional part is discarded toward zero.

## Production Rule

A cast does not mean "safe conversion."

It means:

> Perform the language-defined conversion, potentially losing information.

---

# 2.1.20 Memory / Runtime Behavior

## Local Variables

Primitive locals are represented through JVM local-variable slots as part of method execution.

Do not oversimplify this as "all primitives live on the stack."

## Fields

```java
class Counter {
    int count;
}
```

The primitive field is part of the object's state.

## Static Fields

```java
class Config {
    static int maxRetries = 3;
}
```

The field belongs to the class rather than each instance.

## Primitive Arrays

```java
int[] numbers = {1, 2, 3};
```

Conceptually, the array stores primitive values directly.

This differs from:

```java
Integer[] numbers;
```

which stores references to wrapper objects.

## JVM Optimization

JIT compilation, escape analysis, object layout, and other JVM implementation details can affect physical representation.

Do not build production reasoning on the simplistic rule:

```text
primitive = stack
object = heap
```

---

# 2.1.21 Primitive Values and Threads

Primitive fields can still have concurrency problems.

```java
class Counter {

    int count;

    void increment() {
        count++;
    }
}
```

`count++` is not automatically atomic.

It is effectively a read-modify-write sequence.

Depending on requirements, consider:

```java
AtomicInteger
synchronized
Lock
LongAdder
```

or another concurrency design.

---

# 2.1.22 Common Mistakes

- [ ] Assuming integer overflow throws an exception.
- [ ] Forgetting `byte`, `short`, and `char` promotion.
- [ ] Assuming `byte + byte` returns `byte`.
- [ ] Forgetting `L` for large long literals.
- [ ] Forgetting `f` for float literals.
- [ ] Using `double` for exact money.
- [ ] Thinking `Float.MIN_VALUE` is the most negative float.
- [ ] Treating `char` as a complete Unicode character.
- [ ] Assuming one `char` equals one visible character.
- [ ] Comparing floating-point values blindly with `==`.
- [ ] Ignoring NaN.
- [ ] Ignoring signed zero.
- [ ] Assuming casts are lossless.
- [ ] Assuming primitives make operations thread-safe.
- [ ] Assuming primitives are always physically stored on the JVM stack.

---

# 2.1.23 Performance Implications

## Primitive vs Wrapper

Primitive values can avoid object allocation and object-reference overhead.

Compare:

```java
int[] values;
```

with:

```java
Integer[] values;
```

The wrapper representation can involve object references and wrapper objects.

## Memory Locality

Primitive arrays can provide compact, contiguous storage and favorable cache behavior.

Important for:

- [ ] Numerical processing
- [ ] Large datasets
- [ ] High-throughput algorithms
- [ ] Performance-sensitive services

## CPU

Modern JVMs can optimize primitive arithmetic aggressively.

Do not assume a particular source-level operation maps to one machine instruction.

Measure real workloads when performance matters.

---

# 2.1.24 Production Use Cases

| Type | Appropriate Examples |
|---|---|
| `byte` | Binary/network/file data |
| `short` | Specialized binary formats |
| `int` | Counters, indexes, normal integers |
| `long` | IDs, timestamps, large counters |
| `float` | Specialized lower-precision numerical workloads |
| `double` | Scientific/statistical/approximate calculations |
| `char` | UTF-16 code-unit operations |
| `boolean` | Binary state and conditions |

Always choose based on:

```text
Range
Precision
Concurrency
Memory
Interoperability
Persistence
Serialization
Domain requirements
```

---

# 2.1.25 Debugging Challenges

## Challenge 1 — Overflow

```java
int total = 2_000_000_000;
total += 2_000_000_000;

System.out.println(total);
```

Tasks:

- [ ] Predict the result.
- [ ] Explain it.
- [ ] Fix it with `long`.
- [ ] Fix it with `Math.addExact()`.

## Challenge 2 — Integer Promotion

```java
byte a = 100;
byte b = 27;

byte result = (byte) (a + b);
```

Tasks:

- [ ] Explain why the cast is required.
- [ ] Determine whether information is lost.
- [ ] Remove the cast and explain the compiler error.

## Challenge 3 — Long Literal

Explain:

```java
long value = 3_000_000_000;
```

Then fix it.

## Challenge 4 — Floating Point

```java
double total = 0.1 + 0.2;

System.out.println(total);
System.out.println(total == 0.3);
```

Explain the result.

## Challenge 5 — NaN

```java
double x = Double.NaN;

System.out.println(x == x);
```

Explain why.

## Challenge 6 — Character Promotion

```java
char c = 'A';

System.out.println(c + 1);
```

Determine the result type and output.

## Challenge 7 — Compound Assignment

Explain:

```java
byte x = 1;
x += 2;
```

versus:

```java
byte x = 1;
x = x + 2;
```

---

# 2.1.26 Coding Exercises

## Basic

- [ ] Print the range of every integral primitive.
- [ ] Print floating-point constants and special values.
- [ ] Demonstrate byte overflow.
- [ ] Demonstrate short overflow.
- [ ] Demonstrate int overflow.
- [ ] Demonstrate long overflow.
- [ ] Demonstrate integer underflow.
- [ ] Demonstrate floating-point underflow.
- [ ] Demonstrate char-to-int conversion.
- [ ] Demonstrate boolean short-circuiting.

## Intermediate

- [ ] Build a numeric-promotion demonstration.
- [ ] Demonstrate widening conversions.
- [ ] Demonstrate narrowing conversions and information loss.
- [ ] Demonstrate NaN and infinity.
- [ ] Demonstrate positive and negative zero.
- [ ] Demonstrate Unicode surrogate pairs.
- [ ] Demonstrate `int[]` vs `Integer[]`.
- [ ] Implement safe arithmetic using `Math.*Exact()`.

## Advanced

- [ ] Build an integer overflow detector.
- [ ] Implement safe `long` to `int` conversion.
- [ ] Implement absolute and relative floating-point comparisons.
- [ ] Build fixed-point money using minor currency units.
- [ ] Compare fixed-point, `double`, and `BigDecimal`.
- [ ] Benchmark primitive arrays vs wrapper arrays using JMH.
- [ ] Inspect bytecode for primitive arithmetic.
- [ ] Investigate JIT optimizations involving primitive values.

## Production-Style

Build a:

```text
High-Throughput Metrics Counter
```

Requirements:

- [ ] Track request count.
- [ ] Track error count.
- [ ] Track latency.
- [ ] Prevent counter overflow.
- [ ] Make updates concurrency-safe.
- [ ] Compare `long`, `AtomicLong`, and `LongAdder`.
- [ ] Benchmark under contention.
- [ ] Explain memory and throughput trade-offs.

---

# 2.1.27 Interview Questions

## Basic

- [ ] What are Java's eight primitive types?
- [ ] What is the range of byte?
- [ ] What is the range of int?
- [ ] What is the range of long?
- [ ] What is the difference between float and double?
- [ ] What is the range of char?
- [ ] What values can boolean hold?
- [ ] What is integer overflow?

## Intermediate

- [ ] Why does `byte + byte` produce int?
- [ ] What is integer promotion?
- [ ] Why can a long calculation unexpectedly overflow as int?
- [ ] Why is `f` required for float literals?
- [ ] Why is `L` used for long literals?
- [ ] What is widening vs narrowing conversion?
- [ ] Why does compound assignment behave differently?
- [ ] Why can floating-point equality be problematic?

## Advanced

- [ ] Explain binary numeric promotion.
- [ ] Explain integer overflow semantics.
- [ ] Explain IEEE 754 at a high level.
- [ ] Why does `0.1 + 0.2` not exactly equal `0.3`?
- [ ] What is NaN?
- [ ] Why is NaN not equal to itself?
- [ ] What is floating-point underflow?
- [ ] What is a subnormal number?
- [ ] What does char actually represent?
- [ ] Why can one Unicode code point require two chars?

## Senior / Production

- [ ] When should you use int vs long?
- [ ] When should you use primitive types vs wrappers?
- [ ] When is double inappropriate?
- [ ] How would you design safe monetary arithmetic?
- [ ] How can integer overflow become a production/security issue?
- [ ] How would you detect overflow in a high-throughput service?
- [ ] Why is `count++` unsafe under concurrent access?
- [ ] How would you choose AtomicLong vs LongAdder?
- [ ] How can primitive-array layout affect cache behavior?
- [ ] What JVM memory assumptions about primitives are unsafe?

---

# 2.1.28 Advanced Follow-ups

## Java Language Specification

Study:

- [ ] Primitive types
- [ ] Numeric types
- [ ] Numeric literals
- [ ] Numeric promotion
- [ ] Widening conversion
- [ ] Narrowing conversion
- [ ] Assignment conversion
- [ ] Compound assignment
- [ ] Equality operators
- [ ] Floating-point semantics

## JVM

Study:

```text
Java source
    ↓
javac
    ↓
Bytecode
    ↓
JVM
    ↓
Interpreter / JIT
    ↓
Machine code
```

Investigate:

- [ ] JVM local-variable slots
- [ ] Primitive fields
- [ ] Primitive arrays
- [ ] Integer arithmetic bytecodes
- [ ] Floating-point bytecodes
- [ ] JIT compilation
- [ ] Escape analysis
- [ ] Register allocation

## IEEE 754

Study:

- [ ] Sign
- [ ] Exponent
- [ ] Significand
- [ ] Normalized values
- [ ] Subnormal values
- [ ] NaN
- [ ] Infinity
- [ ] Signed zero
- [ ] Rounding
- [ ] Error propagation

---

# 2.1.29 OpenJDK / Specification Investigation

Study the current Java specification and APIs:

- [ ] JLS — Primitive Types
- [ ] JLS — Numeric Types
- [ ] JLS — Numeric Promotion
- [ ] JLS — Conversions and Contexts
- [ ] JVM Specification — Primitive Types and Values
- [ ] `java.lang.Math`
- [ ] `java.lang.StrictMath`
- [ ] `java.lang.Integer`
- [ ] `java.lang.Long`
- [ ] `java.lang.Float`
- [ ] `java.lang.Double`
- [ ] `java.lang.Character`

For implementation-level questions, inspect the relevant OpenJDK source and generated bytecode.

---

# 2.1.30 Production Review Checklist

Before selecting a primitive type, ask:

```text
1. What is the maximum possible value?
2. What is the minimum possible value?
3. Can the value grow over time?
4. Can arithmetic overflow?
5. Is exact arithmetic required?
6. Is decimal precision required?
7. Is the value accessed concurrently?
8. Does memory footprint matter?
9. Is interoperability involved?
10. Does the database use a different range?
11. Is serialization involved?
12. Are negative values valid?
13. Is the value a code/unit rather than a mathematical quantity?
14. Will the value cross an API boundary?
```

---

# 2.1.31 Final Mastery Gate

## Primitive Fundamentals

- [ ] Explain all eight primitive types.
- [ ] State integral ranges from memory.
- [ ] Explain signed two's-complement integers.
- [ ] Explain floating-point representation at a high level.
- [ ] Explain char as a UTF-16 code unit.
- [ ] Explain boolean semantics.

## Numeric Operations

- [ ] Explain integer promotion.
- [ ] Explain binary numeric promotion.
- [ ] Explain compound assignment conversion.
- [ ] Explain widening conversions.
- [ ] Explain narrowing conversions.
- [ ] Predict expression result types.

## Overflow / Underflow

- [ ] Demonstrate integer overflow.
- [ ] Demonstrate integer underflow.
- [ ] Detect overflow with `Math.*Exact()`.
- [ ] Explain floating-point underflow.
- [ ] Explain subnormal values.

## Floating Point

- [ ] Explain decimal/binary representation.
- [ ] Explain `0.1 + 0.2`.
- [ ] Explain NaN.
- [ ] Explain infinity.
- [ ] Explain signed zero.
- [ ] Compare floating-point values appropriately.
- [ ] Explain when `BigDecimal` or fixed-point representation is preferable.

## Memory / Runtime

- [ ] Explain primitive fields.
- [ ] Explain primitive arrays.
- [ ] Explain JVM local-variable slots.
- [ ] Avoid the "all primitives live on stack" misconception.
- [ ] Explain primitive concurrency limitations.

## Production

- [ ] Select the correct primitive for a real requirement.
- [ ] Identify overflow risks.
- [ ] Identify precision risks.
- [ ] Identify concurrency risks.
- [ ] Explain memory/performance trade-offs.
- [ ] Debug primitive arithmetic bugs.
- [ ] Benchmark when performance matters.

## Interview

- [ ] Answer basic questions.
- [ ] Answer intermediate questions.
- [ ] Answer advanced questions.
- [ ] Answer senior/production questions.
- [ ] Explain numeric promotion from first principles.
- [ ] Explain floating-point precision from first principles.

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
