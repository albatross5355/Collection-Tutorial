# Module 1.2 — Language Fundamentals

## Deep Mastery Guide

---

# Mastery Cycle

For **every topic**, follow this complete mastery cycle:

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

> **Explain it → Implement it → Explain its internals → Handle edge cases → Discuss trade-offs → Debug it → Use it in a production scenario**

---

# 1.2.1 Variables & Declarations

## 1. What is it?

### Definition

A variable is a named storage location/reference used by a Java program to hold a value.

### Purpose

Variables allow programs to:

- Store data
- Modify state
- Pass values to methods
- Maintain object references
- Represent temporary computation results

### Core Terminology

- [ ] Variable
- [ ] Declaration
- [ ] Initialization
- [ ] Assignment
- [ ] Local variable
- [ ] Instance variable
- [ ] Static/class variable
- [ ] Parameter
- [ ] Reference variable
- [ ] Primitive variable
- [ ] Scope
- [ ] Lifetime

## 2. Why does Java have it?

### Problem it solves

Programs need a way to represent changing and persistent state.

### Design Motivation

Java distinguishes:

```text
Primitive value
      vs.
Object reference
```

and:

```text
Local state
Instance state
Class-level state
```

### Alternatives

- Constants
- Literals
- Fields
- Collections
- Records
- Immutable objects

## 3. Syntax and API

```java
int age;
int count = 10;

String name = "John";

final int MAX = 100;
```

### Important Keywords

- `var`
- `final`
- `static`

### Common Operations

```java
int x = 10;
x = 20;
x++;
x += 5;
```

## 4. Basic Example

```java
public class Main {
    public static void main(String[] args) {
        int age = 25;
        String name = "Alex";

        System.out.println(name);
        System.out.println(age);
    }
}
```

### Modify

- [ ] Change `age`
- [ ] Add another variable
- [ ] Perform arithmetic
- [ ] Make a variable `final`
- [ ] Try using an uninitialized local variable

## 5. Internal Working

Understand:

```text
Declaration
    ↓
Compiler type checking
    ↓
Bytecode generation
    ↓
JVM execution
    ↓
Value/reference stored in runtime location
```

Understand the difference between:

```java
int x = 10;
```

and:

```java
User user = new User();
```

The first stores a primitive value.

The second involves an object and a reference.

### Specification / Source

- Java Language Specification — Variables
- JVM Specification — Local Variables
- JVM bytecode local-variable instructions

## 6. Memory / Runtime Behavior

Understand:

- [ ] Local variables
- [ ] Method parameters
- [ ] Instance fields
- [ ] Static fields
- [ ] Primitive values
- [ ] Object references
- [ ] Heap objects
- [ ] Stack frames
- [ ] Class metadata

### Important Caveat

Do **not** memorize:

> "All primitives are always on the stack."

That is an oversimplification.

Understand variables according to their **language semantics and JVM runtime representation**, rather than assuming a simplistic stack/heap rule.

## 7. Edge Cases

- [ ] Uninitialized local variables
- [ ] `null` references
- [ ] Shadowing
- [ ] Variable scope
- [ ] `final` variables
- [ ] Effectively final variables
- [ ] Integer overflow
- [ ] Floating-point precision
- [ ] Variable lifetime

## 8. Common Mistakes

- [ ] Confusing declaration with initialization
- [ ] Assuming Java passes variables by reference
- [ ] Assuming primitive variables and object references behave identically
- [ ] Accidentally shadowing fields
- [ ] Using mutable static variables unnecessarily
- [ ] Ignoring overflow

## 9. Performance Implications

- [ ] Primitive vs object allocation
- [ ] Boxing/unboxing
- [ ] Object allocation
- [ ] Escape analysis
- [ ] Local variable access
- [ ] Field access
- [ ] Static field access

## 10. Production Use Cases

Variables are fundamental to virtually every Java application.

Understand their role in:

- Request processing
- DTOs
- Domain objects
- Configuration
- Counters
- Caches
- State management

Avoid:

- Unnecessary mutable global state
- Mutable static state
- Excessive object creation

## 11. Interview Questions

### Basic

- [ ] What is a variable?
- [ ] Declaration vs initialization?

### Intermediate

- [ ] Local variable vs instance variable?
- [ ] What happens when a local variable is not initialized?

### Advanced

- [ ] Where are variables stored?
- [ ] What is variable shadowing?

### Senior / Production

- [ ] Why is mutable static state dangerous?
- [ ] How can variable/object allocation affect GC performance?

---

# 1.2.2 Primitive Data Types

## 1. What is it?

Java has eight primitive types:

```text
byte
short
int
long
float
double
char
boolean
```

## 2. Why does Java have them?

Primitive types provide:

- Efficient representation
- Predictable semantics
- Arithmetic operations
- Lower overhead than objects

## 3. Syntax and API

```java
byte b = 10;
short s = 100;
int i = 1000;
long l = 100000L;

float f = 10.5f;
double d = 10.5;

char c = 'A';
boolean active = true;
```

### Important Concepts

- [ ] Numeric ranges
- [ ] Signed integers
- [ ] Floating point
- [ ] Unicode
- [ ] Boolean values
- [ ] Numeric promotion

## 4. Basic Example

```java
int a = 10;
int b = 20;

int result = a + b;

System.out.println(result);
```

## 5. Internal Working

Understand:

- JVM primitive representations
- Bytecode instructions
- Numeric promotion
- Conversion rules
- Floating-point representation

## 6. Memory / Runtime Behavior

Know the conceptual sizes:

| Type | Typical representation |
|---|---:|
| `byte` | 8-bit |
| `short` | 16-bit |
| `int` | 32-bit |
| `long` | 64-bit |
| `float` | 32-bit |
| `double` | 64-bit |
| `char` | 16-bit |

Do not confuse Java's language-level guarantees with physical memory layout of every JVM implementation.

## 7. Edge Cases

### Integer Overflow

```java
int x = Integer.MAX_VALUE;
x++;
```

Result wraps according to Java integer arithmetic semantics.

### Floating Point

```java
System.out.println(0.1 + 0.2);
```

Do not expect exact decimal arithmetic.

### Character

```java
char c = '\u0041';
```

Understand UTF-16 and surrogate pairs.

## 8. Common Mistakes

- [ ] Assuming `int` overflow throws an exception
- [ ] Comparing floating-point values using `==`
- [ ] Assuming `char` represents every Unicode code point by itself
- [ ] Ignoring numeric promotion
- [ ] Accidentally narrowing values

## 9. Performance Implications

- Primitive arithmetic is generally efficient.
- Boxing can introduce allocations.
- Floating-point calculations have different CPU characteristics.
- Large numbers may require `long` rather than `int`.

## 10. Production Use Cases

Use primitives when:

- Values cannot be `null`
- Boxing is unnecessary
- High-volume numeric processing is involved

Use wrappers when:

- `null` is meaningful
- Generics require objects
- Framework APIs require object types

## 11. Interview Questions

### Basic

- [ ] What are Java's primitive types?
- [ ] What is the size of `int`?

### Intermediate

- [ ] What is numeric promotion?
- [ ] Why does integer overflow not throw an exception?

### Advanced

- [ ] Why is `char` not equivalent to a Unicode code point?
- [ ] Why is `0.1 + 0.2` problematic?

### Senior

- [ ] When should primitives be preferred over wrappers in performance-sensitive systems?

---

# 1.2.3 Wrapper Classes & Boxing

## Core Types

```text
byte    → Byte
short   → Short
int     → Integer
long    → Long
float   → Float
double  → Double
char    → Character
boolean → Boolean
```

## Mastery

- [ ] Boxing
- [ ] Unboxing
- [ ] Autoboxing
- [ ] Autounboxing
- [ ] Integer cache
- [ ] `equals()`
- [ ] `==`
- [ ] Null unboxing
- [ ] Generic collections

### Example

```java
Integer x = 10;
int y = x;
```

### Dangerous Example

```java
Integer value = null;

int x = value; // NullPointerException
```

### Important Interview Trap

```java
Integer a = 127;
Integer b = 127;

System.out.println(a == b);
```

vs.

```java
Integer a = 1000;
Integer b = 1000;

System.out.println(a == b);
```

Understand **object identity vs value equality** and wrapper caching.

---

# 1.2.4 Literals

Master:

- [ ] Integer literals
- [ ] Long literals
- [ ] Floating-point literals
- [ ] Character literals
- [ ] String literals
- [ ] Boolean literals
- [ ] `null`
- [ ] Binary literals
- [ ] Octal literals
- [ ] Hexadecimal literals
- [ ] Underscores in numeric literals

Examples:

```java
int decimal = 100;
int binary = 0b1010;
int hex = 0xFF;
int readable = 1_000_000;

long value = 100L;
double price = 10.5;
```

---

# 1.2.5 Type Conversion & Casting

## Mastery

- [ ] Widening conversion
- [ ] Narrowing conversion
- [ ] Explicit casting
- [ ] Numeric promotion
- [ ] Reference casting
- [ ] Upcasting
- [ ] Downcasting
- [ ] `ClassCastException`

### Example

```java
int x = 10;
long y = x;
```

Widening.

```java
long x = 10;
int y = (int) x;
```

Narrowing.

### Dangerous

```java
long x = 10_000_000_000L;
int y = (int) x;
```

Understand information loss.

---

# 1.2.6 Operators

Master all operator categories:

## Arithmetic

```text
+
-
*
/
%
```

## Unary

```text
+
-
++
--
!
~
```

## Relational

```text
<
>
<=
>=
==
!=
```

## Logical

```text
&&
||
!
```

## Bitwise

```text
&
|
^
~
```

## Shift

```text
<<
>>
>>>
```

## Assignment

```text
=
+=
-=
*=
/=
%=
&=
|=
^=
<<=
>>=
>>>=
```

## Conditional

```java
condition ? value1 : value2
```

### Mastery

- [ ] Operator precedence
- [ ] Associativity
- [ ] Short-circuit evaluation
- [ ] Integer arithmetic
- [ ] Bit manipulation
- [ ] Overflow

---

# 1.2.7 Control Flow

Master:

- [ ] `if`
- [ ] `else`
- [ ] Nested conditions
- [ ] `switch`
- [ ] `for`
- [ ] Enhanced `for`
- [ ] `while`
- [ ] `do-while`
- [ ] `break`
- [ ] `continue`
- [ ] `return`

### Example

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        continue;
    }

    System.out.println(i);
}
```

---

# 1.2.8 Switch

Master both traditional and modern switch.

### Traditional

```java
switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    default:
        System.out.println("Unknown");
}
```

### Modern

```java
String result = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    default -> "Unknown";
};
```

Master:

- [ ] Fall-through
- [ ] `break`
- [ ] Arrow labels
- [ ] Switch expressions
- [ ] `yield`
- [ ] Exhaustiveness
- [ ] `null` behavior
- [ ] Pattern matching for switch

---

# 1.2.9 Methods

## Mastery

- [ ] Method declaration
- [ ] Parameters
- [ ] Return types
- [ ] `void`
- [ ] Method invocation
- [ ] Overloading
- [ ] Varargs
- [ ] Recursion
- [ ] Static methods
- [ ] Instance methods
- [ ] Access modifiers

### Example

```java
static int add(int a, int b) {
    return a + b;
}
```

### Deep Topics

- [ ] Stack frames
- [ ] Parameter passing
- [ ] Return values
- [ ] Method dispatch
- [ ] Overloading resolution
- [ ] Runtime polymorphism

---

# 1.2.10 Java Pass-by-Value

This is a **critical mastery topic**.

Java is always pass-by-value.

### Primitive

```java
void change(int x) {
    x = 100;
}
```

The caller's variable does not change.

### Object Reference

```java
void change(User user) {
    user.name = "Bob";
}
```

The object's state can change.

But:

```java
void change(User user) {
    user = new User();
}
```

The caller's reference does not change.

### Mental Model

```text
Caller
  |
  | copy of value
  ↓
Method parameter
```

For objects:

```text
caller reference
       |
       +------> Object
       |
   copied reference
       |
       +------> same Object
```

Master this until you can explain it without saying:

> "Java passes objects by reference."

That statement is incorrect.

---

# 1.2.11 Scope & Lifetime

Master:

- [ ] Block scope
- [ ] Method scope
- [ ] Parameter scope
- [ ] Class scope
- [ ] Instance field lifetime
- [ ] Static field lifetime
- [ ] Object lifetime

Example:

```java
if (true) {
    int x = 10;
}

// x is unavailable here
```

---

# 1.2.12 Arrays

## Mastery

- [ ] Array declaration
- [ ] Array initialization
- [ ] Indexing
- [ ] Length
- [ ] Multidimensional arrays
- [ ] Jagged arrays
- [ ] Array covariance
- [ ] `ArrayIndexOutOfBoundsException`
- [ ] `NullPointerException`
- [ ] `Arrays` utility class
- [ ] Array copying

### Example

```java
int[] numbers = {10, 20, 30};

System.out.println(numbers[0]);
System.out.println(numbers.length);
```

### Internal Understanding

```text
Reference
   ↓
Array object
   ↓
Elements
```

Master:

- [ ] Heap allocation
- [ ] Array object metadata
- [ ] Bounds checking
- [ ] Reference arrays
- [ ] Primitive arrays
- [ ] GC behavior

---

# 1.2.13 Strings — Language Foundation

Although Strings deserve a later dedicated module, Language Fundamentals should establish:

- [ ] String literals
- [ ] `String`
- [ ] Immutability
- [ ] Concatenation
- [ ] `==` vs `equals()`
- [ ] String pool basics
- [ ] `StringBuilder`
- [ ] Escape sequences

### Example

```java
String a = "hello";
String b = "hello";

System.out.println(a == b);
System.out.println(a.equals(b));
```

Understand why the two comparisons are different.

---

# 1.2.14 `null`

Master:

- [ ] Meaning of `null`
- [ ] Null references
- [ ] `NullPointerException`
- [ ] Null checks
- [ ] `Objects.requireNonNull()`
- [ ] Null-safe APIs
- [ ] `Optional` as a later abstraction

### Example

```java
String name = null;

System.out.println(name.length());
```

Understand exactly why this fails.

### Advanced

- [ ] Helpful NPE messages
- [ ] Nullness contracts
- [ ] Framework-generated nulls
- [ ] Defensive validation

---

# 1.2.15 `final`

Master all uses:

```java
final int x = 10;
```

```java
final class User {
}
```

```java
final void process() {
}
```

Understand:

- [ ] Final local variables
- [ ] Final parameters
- [ ] Final fields
- [ ] Final methods
- [ ] Final classes
- [ ] Immutability vs finality
- [ ] Blank final fields
- [ ] Effectively final variables

### Critical Distinction

`final` does **not** automatically make an object immutable.

```java
final List<String> list = new ArrayList<>();
list.add("A"); // allowed
```

The reference cannot be reassigned, but the object can mutate.

---

# 1.2.16 `static`

Master:

- [ ] Static fields
- [ ] Static methods
- [ ] Static blocks
- [ ] Static nested classes
- [ ] Class-level state
- [ ] Initialization
- [ ] Static import

Understand:

```text
Class
 ↓
static member
 ↓
shared by instances
```

### Production Risks

- [ ] Mutable static state
- [ ] Memory retention
- [ ] Test pollution
- [ ] Concurrency problems
- [ ] Global state

---

# 1.2.17 Initialization Order

Master the exact order:

```text
Class loading
    ↓
Static fields / static blocks
    ↓
Superclass initialization
    ↓
Subclass initialization
    ↓
Object allocation
    ↓
Instance field initializers
    ↓
Instance initializer blocks
    ↓
Constructor
```

For inheritance, study the exact superclass/subclass ordering.

### Example

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
```

Then build a subclass and predict the output before running it.

---

# 1.2.18 `this` and `super`

Master:

- [ ] `this`
- [ ] `this.field`
- [ ] `this.method()`
- [ ] `this(...)`
- [ ] `super.field`
- [ ] `super.method()`
- [ ] `super(...)`
- [ ] Constructor chaining
- [ ] Parent constructor invocation

### Critical Rule

`this(...)` and `super(...)` constructor calls must appear as the appropriate first constructor invocation.

---

# 1.2.19 Packages & Imports

Master:

- [ ] Package declaration
- [ ] Fully qualified class names
- [ ] `import`
- [ ] Static import
- [ ] Package visibility
- [ ] Naming conventions
- [ ] Package structure
- [ ] Classpath relationship
- [ ] Module relationship

Example:

```java
package com.example.service;

import java.util.List;
```

Understand:

```text
Package
    ↓
Namespace organization
    ↓
Access control
    ↓
Classpath/module resolution
```

---

# 1.2.20 Access Modifiers

Master:

| Modifier | Same Class | Same Package | Subclass | Everywhere |
|---|---|---|---|---|
| `private` | ✓ | ✗ | ✗ | ✗ |
| default | ✓ | ✓ | package-dependent | ✗ |
| `protected` | ✓ | ✓ | ✓ | ✗ |
| `public` | ✓ | ✓ | ✓ | ✓ |

Deeply understand the special behavior of `protected` across packages.

---

# 1.2.21 Enums

Master:

- [ ] Enum declaration
- [ ] Enum constants
- [ ] Fields
- [ ] Constructors
- [ ] Methods
- [ ] `values()`
- [ ] `valueOf()`
- [ ] Enum identity
- [ ] EnumSet
- [ ] EnumMap
- [ ] Enum serialization

### Basic

```java
enum Status {
    NEW,
    PROCESSING,
    COMPLETED
}
```

### Advanced

```java
enum Status {
    NEW("New"),
    COMPLETED("Completed");

    private final String label;

    Status(String label) {
        this.label = label;
    }
}
```

---

# 1.2.22 Annotations — Language-Level Foundation

Master the basics:

- [ ] What annotations are
- [ ] Annotation syntax
- [ ] Built-in annotations
- [ ] `@Override`
- [ ] `@Deprecated`
- [ ] `@SuppressWarnings`
- [ ] Annotation targets
- [ ] Retention basics

Detailed annotation processing belongs in the advanced reflection/annotations module.

---

# 1.2.23 Varargs

Master:

```java
void print(String... values) {
}
```

Understand internally:

```text
varargs
   ↓
array
```

### Edge Cases

```java
method();
method("A");
method("A", "B");
method((String[]) null);
```

Understand ambiguity and overload resolution.

---

# 1.2.24 Recursion

Master:

- [ ] Base case
- [ ] Recursive case
- [ ] Stack frames
- [ ] Stack overflow
- [ ] Tail recursion concept
- [ ] Recursive tree traversal
- [ ] Recursive algorithms
- [ ] Iterative alternatives

### Example

```java
static int factorial(int n) {
    if (n <= 1) {
        return 1;
    }

    return n * factorial(n - 1);
}
```

### Production

Do not use recursion blindly for deeply unbounded input.

---

# 1.2.25 Assertions

Master:

```java
assert condition;
```

```java
assert condition : message;
```

Understand:

- [ ] Why assertions exist
- [ ] How to enable them
- [ ] Why they are not validation
- [ ] `-ea`
- [ ] `-da`

Example:

```bash
java -ea Main
```

### Production Warning

Do not rely on assertions for mandatory production validation because assertions may be disabled.

---

# 1.2.26 Integrated Language Fundamentals Project

Build a small **Order Processing CLI application** using only core Java.

```text
Order
 ├── id
 ├── customer
 ├── items
 ├── status
 └── total
```

Implement:

- [ ] Classes
- [ ] Constructors
- [ ] Fields
- [ ] Methods
- [ ] `this`
- [ ] `super`
- [ ] Encapsulation
- [ ] Enums
- [ ] Arrays
- [ ] Loops
- [ ] Conditions
- [ ] Switch
- [ ] Exceptions
- [ ] Static members
- [ ] Final fields
- [ ] Packages
- [ ] Access modifiers
- [ ] Varargs
- [ ] Basic annotations
- [ ] Input validation

Then:

- [ ] Add inheritance.
- [ ] Add polymorphism.
- [ ] Add interfaces.
- [ ] Add custom exceptions.
- [ ] Add unit tests later.
- [ ] Profile object allocation later.

---

# 1.2.27 Advanced Follow-ups

## JVM

- [ ] Class loading
- [ ] Bytecode
- [ ] Stack frames
- [ ] JIT
- [ ] Escape analysis
- [ ] Garbage collection

## Language Specification

- [ ] JLS type system
- [ ] Conversion contexts
- [ ] Method invocation conversion
- [ ] Overload resolution
- [ ] Initialization rules
- [ ] Definite assignment

## OpenJDK

- [ ] `javac`
- [ ] `java.lang`
- [ ] `java.util`
- [ ] HotSpot implementation
- [ ] Wrapper caches

## Performance

- [ ] Allocation
- [ ] Boxing
- [ ] Escape analysis
- [ ] Method inlining
- [ ] Branch behavior
- [ ] GC pressure

## Concurrency

- [ ] Shared mutable state
- [ ] Static state
- [ ] Object publication
- [ ] Thread safety
- [ ] Immutable objects

---

# 1.2.28 Master Interview Question Bank

## Basic

- [ ] What are Java primitive types?
- [ ] What is the difference between declaration and initialization?
- [ ] What is a method?
- [ ] What is an array?
- [ ] What is `null`?
- [ ] What is `final`?
- [ ] What is `static`?
- [ ] What are access modifiers?
- [ ] What is an enum?
- [ ] What is a package?

## Intermediate

- [ ] Primitive vs wrapper?
- [ ] `==` vs `equals()`?
- [ ] Widening vs narrowing conversion?
- [ ] Why does integer overflow happen?
- [ ] Why does `0.1 + 0.2` not necessarily equal `0.3`?
- [ ] What is autoboxing?
- [ ] What is variable shadowing?
- [ ] What is method overloading?
- [ ] What is constructor chaining?
- [ ] What is the difference between `this()` and `super()`?

## Advanced

- [ ] Is Java pass-by-value or pass-by-reference?
- [ ] Where are local variables stored?
- [ ] Where are objects stored?
- [ ] Is `final` enough to make an object immutable?
- [ ] How does method overloading resolution work?
- [ ] What happens during class initialization?
- [ ] Why can a `final List` still be modified?
- [ ] Why can a `char` not represent every Unicode code point?
- [ ] How does array covariance work?
- [ ] Why can arrays throw `ArrayStoreException`?

## Senior / Production

- [ ] When should primitives be preferred over wrappers?
- [ ] How can boxing create GC pressure?
- [ ] Why is mutable static state dangerous?
- [ ] How can class initialization cause production startup failures?
- [ ] How would you diagnose unexpected object allocation?
- [ ] How would you debug a `ClassCastException` caused by an API boundary?
- [ ] What language-level behavior can affect performance?
- [ ] When should recursion be replaced with iteration?
- [ ] How can initialization order create subtle production bugs?
- [ ] How would you design a Java API to minimize null-related failures?

---

# 1.2.29 Coding Exercises

## Basic

- [ ] Calculator using primitive types.
- [ ] Grade calculator using `if`/`switch`.
- [ ] Number reversal.
- [ ] Prime-number checker.
- [ ] Factorial.
- [ ] Fibonacci.
- [ ] Array minimum/maximum.
- [ ] String character counter.

## Intermediate

- [ ] Implement an immutable configuration object.
- [ ] Implement an enum-based state machine.
- [ ] Implement overloaded methods.
- [ ] Implement a class hierarchy.
- [ ] Implement constructor chaining.
- [ ] Implement a custom validation framework using basic Java.
- [ ] Implement a menu-driven CLI application.

## Advanced

- [ ] Implement a custom dynamic array.
- [ ] Implement an enum-based command processor.
- [ ] Implement a recursive tree traversal.
- [ ] Implement a type-safe generic utility.
- [ ] Demonstrate array covariance.
- [ ] Demonstrate boxing/unboxing performance differences.
- [ ] Investigate bytecode generated for overloaded methods.

## Production-Style

- [ ] Build an Order Processing CLI.
- [ ] Add validation and custom exceptions.
- [ ] Add logging.
- [ ] Add configuration.
- [ ] Separate packages by responsibility.
- [ ] Add immutable domain objects.
- [ ] Add graceful error handling.
- [ ] Profile the application.
- [ ] Diagnose deliberate bugs involving `null`, casting and initialization.

---

# 1.2.30 Final Mastery Gate

Do **not** mark Module 1.2 complete until you can:

- [ ] Explain primitive types deeply.
- [ ] Explain wrappers and boxing.
- [ ] Explain numeric conversion.
- [ ] Explain operator behavior.
- [ ] Explain control flow.
- [ ] Explain modern switch.
- [ ] Explain methods and overload resolution.
- [ ] Explain Java's pass-by-value semantics.
- [ ] Explain scope and lifetime.
- [ ] Explain arrays internally.
- [ ] Explain Strings at a foundational level.
- [ ] Explain `null`.
- [ ] Explain `final`.
- [ ] Explain `static`.
- [ ] Explain initialization order.
- [ ] Explain `this` and `super`.
- [ ] Explain packages and imports.
- [ ] Explain access modifiers.
- [ ] Explain enums.
- [ ] Explain varargs.
- [ ] Explain recursion and stack behavior.
- [ ] Explain assertions.
- [ ] Debug common language-level failures.
- [ ] Discuss performance implications.
- [ ] Discuss production trade-offs.
- [ ] Implement the concepts without copying solutions.
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
