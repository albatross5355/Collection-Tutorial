# Module 2.3 — Strings
## Deep Mastery Guide

> **Goal:** Master Java `String`, immutability, the String Pool, literals, `new String()`, `intern()`, concatenation, `StringBuilder`, `StringBuffer`, Unicode, UTF-8, UTF-16, characters, and Unicode code points.

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

# 2.3.1 String — What Is It?

`String` represents a sequence of characters.

```java
String name = "Java";
```

Key properties:

- [ ] `String` is a class.
- [ ] Strings are immutable.
- [ ] `String` is `final`.
- [ ] String literals are handled specially by the JVM/runtime.
- [ ] Strings can be created from literals or constructors.
- [ ] Strings are reference types.

Basic model:

```text
String reference
      ↓
String object
      ↓
sequence of characters
```

---

# 2.3.2 Why Does Java Have `String`?

Strings are fundamental for:

- [ ] Text
- [ ] User input
- [ ] Configuration
- [ ] Logging
- [ ] HTTP requests/responses
- [ ] JSON/XML
- [ ] Database values
- [ ] File content
- [ ] Protocol messages

Java makes `String` immutable because immutable text provides useful properties:

- [ ] Safe sharing
- [ ] Predictable behavior
- [ ] Stable hash codes
- [ ] Thread-friendly value semantics
- [ ] String pooling
- [ ] Security benefits
- [ ] Easier reasoning

---

# 2.3.3 String Immutability

Once a `String` object exists, its contents cannot be changed.

```java
String s = "Java";

s.concat(" Programming");

System.out.println(s);
```

Output:

```text
Java
```

Correct:

```java
s = s.concat(" Programming");
```

Conceptually:

```text
"Java"
  ↓ concat
"Java Programming"
```

A new string value is produced; the original `"Java"` is not modified.

---

# 2.3.4 Why Immutability Matters

Immutability enables:

- [ ] Safe sharing
- [ ] String pooling
- [ ] Stable `hashCode()`
- [ ] Safe use as `HashMap` keys
- [ ] Easier concurrency
- [ ] Reduced defensive copying
- [ ] Predictable API behavior

Example:

```java
Map<String, Integer> map = new HashMap<>();

map.put("Java", 1);
```

Because the key cannot mutate after insertion, its hash-based lookup remains stable.

---

# 2.3.5 Basic String API

Important methods:

```java
length()
isEmpty()
isBlank()
charAt()
substring()
indexOf()
lastIndexOf()
contains()
startsWith()
endsWith()
equals()
equalsIgnoreCase()
compareTo()
replace()
replaceAll()
split()
trim()
strip()
toLowerCase()
toUpperCase()
concat()
repeat()
join()
lines()
formatted()
```

Also understand:

```java
chars()
codePoints()
getBytes(...)
getChars(...)
toCharArray()
```

---

# 2.3.6 String Pool

The JVM maintains a pool of interned strings.

A string literal such as:

```java
String a = "Java";
String b = "Java";
```

normally refers to the same interned string value.

Therefore:

```java
System.out.println(a == b);
```

can be:

```text
true
```

But the correct value comparison remains:

```java
a.equals(b)
```

---

# 2.3.7 Why Does the String Pool Exist?

Without pooling, repeatedly creating identical literals could waste memory.

Example:

```java
String a = "hello";
String b = "hello";
String c = "hello";
```

The runtime can reuse the same interned representation.

Conceptually:

```text
"hello"
   ↑
   |
 ┌─┴───────┐
 │ String  │
 └─────────┘
 ↑    ↑
a     b
```

This reduces duplication for interned strings.

---

# 2.3.8 Heap vs String Pool

The String Pool is associated with the JVM's managed runtime memory and should not be thought of as a completely separate memory universe from the heap.

Modern JVMs store interned `String` objects in the Java heap.

Conceptually:

```text
Java Heap
│
├── Ordinary String objects
│
└── Interned String objects
      ↑
      │
   String Pool
```

The pool is a logical concept for canonicalized strings, not a separate primitive memory area like the Java stack.

---

# 2.3.9 String Literals

A literal:

```java
String s = "Java";
```

is a compile-time constant expression in many common cases and refers to an interned string.

Another occurrence:

```java
String t = "Java";
```

uses the same interned value.

This is different from:

```java
String t = new String("Java");
```

which explicitly creates a distinct String object.

---

# 2.3.10 `new String()`

Example:

```java
String a = "Java";
String b = new String("Java");
```

Typically:

```text
a → pooled "Java"

b → separate String object
          ↓
       value "Java"
```

Therefore:

```java
a == b       // false in this example
a.equals(b)  // true
```

Avoid unnecessary:

```java
new String("Java")
```

when a literal is sufficient.

---

# 2.3.11 `String.intern()`

`intern()` returns the canonical representation of a string.

Example:

```java
String a = new String("Java");
String b = a.intern();
```

Now `b` refers to the canonical pooled representation.

Example:

```java
String a = new String("Java");
String b = "Java";

System.out.println(a == b);          // false
System.out.println(a.intern() == b); // true
```

Understand:

```text
ordinary String
      ↓
   intern()
      ↓
canonical pooled String
```

---

# 2.3.12 When Should `intern()` Be Used?

Potentially useful when:

- [ ] A workload contains many repeated strings.
- [ ] Canonical identity is intentionally useful.
- [ ] Memory behavior has been measured.
- [ ] The application has a controlled vocabulary.

Potential risks:

- [ ] Large numbers of unique strings
- [ ] Increased pool management overhead
- [ ] Memory retention
- [ ] Unexpected behavior when used indiscriminately

Do not use `intern()` as a generic performance trick without measurement.

---

# 2.3.13 Compile-Time String Concatenation

Consider:

```java
String s = "Hello" + " World";
```

Both operands are compile-time constants.

The compiler can fold the expression into an equivalent constant string value.

Conceptually:

```text
"Hello" + " World"
       ↓
"Hello World"
```

This can become a single constant/interned string.

---

# 2.3.14 Runtime String Concatenation

Consider:

```java
String name = getName();

String message = "Hello " + name;
```

`name` is not necessarily known at compile time.

The compiler must generate code that constructs the resulting string at runtime.

Modern Java compilers/JDKs may use `invokedynamic` string concatenation machinery.

Conceptually:

```text
"Hello "
    +
name
    ↓
runtime concatenation
    ↓
new String result
```

Do not assume modern Java always translates every `+` expression directly into `StringBuilder`.

---

# 2.3.15 String Concatenation with `+`

Simple concatenation is readable:

```java
String message = "Hello, " + name;
```

For a small number of concatenations, this is generally the preferred style.

Example:

```java
String fullName = firstName + " " + lastName;
```

For repeated concatenation in loops, use an appropriate mutable builder.

---

# 2.3.16 The Loop Concatenation Problem

Avoid:

```java
String result = "";

for (String item : items) {
    result += item;
}
```

Repeated concatenation can create many intermediate string values.

Conceptually:

```text
""
 ↓
"item1"
 ↓
"item1item2"
 ↓
"item1item2item3"
 ↓
...
```

This can create unnecessary allocation and copying.

Prefer:

```java
StringBuilder builder = new StringBuilder();

for (String item : items) {
    builder.append(item);
}

String result = builder.toString();
```

---

# 2.3.17 `StringBuilder`

`StringBuilder` is a mutable sequence of characters intended primarily for single-threaded use.

Example:

```java
StringBuilder builder = new StringBuilder();

builder.append("Hello");
builder.append(" ");
builder.append("Java");

String result = builder.toString();
```

Advantages:

- [ ] Mutable
- [ ] Efficient repeated appends
- [ ] Lower allocation than repeated immutable concatenation in many workloads
- [ ] Not synchronized

---

# 2.3.18 `StringBuilder` Internal Working

Conceptually:

```text
StringBuilder
┌─────────────────────────┐
│ mutable character data  │
│ capacity                │
│ length                  │
└─────────────────────────┘
```

When appending exceeds the current capacity:

```text
old buffer
   ↓
grow capacity
   ↓
copy existing data
   ↓
append new data
```

Therefore capacity management matters in high-volume workloads.

Useful constructor:

```java
new StringBuilder(expectedSize);
```

when a reasonable final size is known.

---

# 2.3.19 `StringBuffer`

`StringBuffer` is a mutable character sequence similar to `StringBuilder`, but its mutating operations are synchronized.

Example:

```java
StringBuffer buffer = new StringBuffer();

buffer.append("Hello");
buffer.append(" Java");
```

Historically important, but in most new application code:

```text
StringBuilder
```

is preferred unless the synchronization semantics are actually required.

---

# 2.3.20 String vs StringBuilder vs StringBuffer

| Feature | String | StringBuilder | StringBuffer |
|---|---|---|---|
| Mutable | No | Yes | Yes |
| Thread synchronization | Immutable/value semantics | No | Synchronized methods |
| Repeated append | Can allocate repeatedly | Efficient | Efficient but synchronization adds overhead |
| Typical use | Text values | Single-threaded construction | Legacy/shared synchronized construction |
| `equals()` value semantics | Yes | Inherits Object behavior | Inherits Object behavior |

Important:

```java
new StringBuilder("Java").equals(new StringBuilder("Java"))
```

does not provide the same value-equality semantics as `String`.

---

# 2.3.21 Choosing Between Them

Use `String` when:

```text
The value represents text
AND
the text does not need in-place mutation
```

Use `StringBuilder` when:

```text
You repeatedly construct/modify text
AND
thread-safe mutation is unnecessary
```

Use `StringBuffer` when:

```text
You specifically require its synchronized legacy API semantics
```

Do not choose `StringBuffer` merely because "thread safety is good." Design ownership and synchronization deliberately.

---

# 2.3.22 Unicode

Unicode is a universal character encoding standard/code-point system.

It defines characters/code points for writing systems around the world.

Examples:

```text
A
é
अ
中
😀
```

Java's `String` represents text using UTF-16 code units internally.

This is crucial:

> Java `char` is not equivalent to "one Unicode character" in all cases.

---

# 2.3.23 UTF-16 and Java `char`

A Java `char` is:

```text
16-bit UTF-16 code unit
```

Many Unicode characters fit into one UTF-16 code unit.

But supplementary characters require a surrogate pair.

Example:

```text
😀
```

may require:

```text
high surrogate + low surrogate
```

Therefore:

```java
"😀".length()
```

is not necessarily `1`.

It is:

```text
2 UTF-16 code units
```

---

# 2.3.24 Character vs Unicode Code Point

Distinguish:

```text
char
↓
UTF-16 code unit
```

from:

```text
Unicode code point
↓
abstract Unicode value
```

For code-point-aware processing:

```java
String text = "😀";

text.codePoints();
```

Useful APIs:

```java
codePointAt(...)
codePointBefore(...)
codePointCount(...)
offsetByCodePoints(...)
Character.charCount(...)
Character.isSupplementaryCodePoint(...)
```

---

# 2.3.25 `char` Pitfall

This code:

```java
for (int i = 0; i < text.length(); i++) {
    char c = text.charAt(i);
}
```

iterates UTF-16 code units, not necessarily Unicode code points.

For Unicode-aware processing:

```java
text.codePoints().forEach(cp -> {
    // process code point
});
```

This distinction matters for:

- [ ] Emoji
- [ ] Mathematical symbols
- [ ] Historic scripts
- [ ] Some supplementary-plane characters
- [ ] Internationalized applications

---

# 2.3.26 UTF-8

UTF-8 is a byte encoding for Unicode.

It is different from Java's internal UTF-16 representation.

Conceptually:

```text
Unicode code points
        ↓
UTF-8
        ↓
bytes
```

Example:

```java
byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
```

Decode:

```java
String text = new String(bytes, StandardCharsets.UTF_8);
```

Always specify the charset when crossing a byte/text boundary.

Prefer:

```java
StandardCharsets.UTF_8
```

over relying on the platform default charset.

---

# 2.3.27 UTF-8 vs UTF-16

| Concept | UTF-8 | UTF-16 |
|---|---|---|
| Unit | Byte sequence | 16-bit code units |
| Common in | Files, HTTP, JSON, network protocols | Java String representation |
| ASCII-compatible | Yes | No, not in the UTF-8 sense |
| Supplementary Unicode | Variable bytes | Surrogate pair |
| Java `String` internal representation | No | Conceptually UTF-16 code units, with compact representations possible in modern JDK implementations |

Important:

> Encoding and Java's in-memory String representation are separate concerns.

---

# 2.3.28 Compact Strings

Modern JDK implementations can use a compact representation for strings when all characters fit within a suitable one-byte encoding representation.

Conceptually:

```text
String
 ├── byte[] value
 └── coder
```

for modern OpenJDK implementations.

This is an implementation detail, not a reason to write code that depends on a particular internal layout.

It can reduce memory usage for strings dominated by Latin-1 characters.

---

# 2.3.29 String Memory Behavior

Understand these layers:

```text
String reference
      ↓
String object
      ↓
internal representation
      ↓
characters/code units
```

Potentially relevant memory sources:

- [ ] String object
- [ ] Backing storage
- [ ] Duplicate strings
- [ ] Temporary concatenation results
- [ ] Interned strings
- [ ] StringBuilder buffers

Large text workloads can therefore become allocation-heavy.

---

# 2.3.30 GC Implications

Strings can create significant GC pressure when applications repeatedly generate temporary strings.

Examples:

```java
String result = a + b + c;
```

or:

```java
for (...) {
    result += value;
}
```

Potential effects:

- [ ] More allocations
- [ ] Higher young-generation allocation rate
- [ ] More GC activity
- [ ] Increased CPU consumption
- [ ] Higher latency under heavy load

Use profiling rather than assuming every concatenation is problematic.

---

# 2.3.31 `StringBuilder` Capacity and Growth

Example:

```java
StringBuilder builder = new StringBuilder(1000);
```

This provides an initial capacity.

If the builder exceeds capacity, it grows its internal storage.

Study:

- [ ] Initial capacity
- [ ] Growth strategy
- [ ] Reallocation
- [ ] Copying
- [ ] Final `toString()`
- [ ] Memory retention during construction

Pre-sizing can help when the final size is reasonably predictable.

---

# 2.3.32 `StringBuilder.toString()`

At the end:

```java
String result = builder.toString();
```

produces the final immutable string.

Conceptually:

```text
mutable builder
      ↓
toString()
      ↓
immutable String
```

This is an important boundary:

```text
construction phase → mutation allowed
final value        → immutable String
```

---

# 2.3.33 `StringBuffer` Synchronization

`StringBuffer` synchronizes many operations.

This provides thread-safety characteristics for individual operations, but do not assume that this automatically makes a multi-operation workflow atomic.

Example:

```java
buffer.append("A");
buffer.append("B");
```

Synchronization of individual methods is not equivalent to a transaction across an arbitrary sequence of operations.

Understand the difference between:

```text
thread-safe operation
```

and:

```text
thread-safe compound action
```

---

# 2.3.34 Common String Mistakes

- [ ] Assuming `String` is mutable.
- [ ] Using `==` instead of `equals()`.
- [ ] Assuming all strings with equal content have the same identity.
- [ ] Creating `new String("...")` unnecessarily.
- [ ] Calling `intern()` indiscriminately.
- [ ] Repeated `+=` concatenation inside large loops.
- [ ] Assuming every `+` becomes `StringBuilder`.
- [ ] Ignoring runtime concatenation costs.
- [ ] Treating `char` as a complete Unicode character.
- [ ] Confusing UTF-8 with UTF-16.
- [ ] Using the default charset for byte/string conversion.
- [ ] Assuming `StringBuffer` makes arbitrary compound operations atomic.
- [ ] Ignoring allocation behavior in text-heavy services.
- [ ] Using regex APIs when simple `String` methods are sufficient.

---

# 2.3.35 Edge Cases

## Empty String

```java
String s = "";
```

Understand:

```java
s.isEmpty()
s.isBlank()
s.length()
```

## Null

```java
String s = null;

s.length();
```

throws `NullPointerException`.

Distinguish:

```text
null
""
" "
```

They are different states.

## Unicode

```java
String emoji = "😀";

System.out.println(emoji.length());
System.out.println(emoji.codePointCount(0, emoji.length()));
```

Explain the difference.

## Identity

```java
String a = "Java";
String b = new String("Java");

System.out.println(a == b);
System.out.println(a.equals(b));
```

## Interning

```java
String a = new String("Java");
String b = a.intern();
String c = "Java";
```

Explain all identity relationships.

---

# 2.3.36 Debugging Challenges

## Challenge 1 — Immutability

```java
String s = "Java";
s.concat(" Programming");

System.out.println(s);
```

Explain the result and fix it.

---

## Challenge 2 — Pool vs Heap

```java
String a = "Java";
String b = "Java";
String c = new String("Java");

System.out.println(a == b);
System.out.println(a == c);
System.out.println(a.equals(c));
```

Explain every result.

---

## Challenge 3 — `intern()`

```java
String a = new String("Java");
String b = a.intern();
String c = "Java";

System.out.println(a == b);
System.out.println(b == c);
```

Explain why.

---

## Challenge 4 — Loop Concatenation

```java
String result = "";

for (int i = 0; i < 100_000; i++) {
    result += i;
}
```

Tasks:

- [ ] Explain the allocation behavior.
- [ ] Explain why it can be inefficient.
- [ ] Rewrite using `StringBuilder`.
- [ ] Benchmark both.

---

## Challenge 5 — Unicode

```java
String s = "😀";

System.out.println(s.length());
System.out.println(s.codePointCount(0, s.length()));
```

Explain why the results differ.

---

## Challenge 6 — UTF-8

```java
String text = "नमस्ते 😀";

byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
String restored = new String(bytes, StandardCharsets.UTF_8);
```

Tasks:

- [ ] Explain encoding.
- [ ] Explain decoding.
- [ ] Explain why byte length and `String.length()` can differ.
- [ ] Test with ASCII and non-ASCII text.

---

# 2.3.37 Coding Exercises

## Basic

- [ ] Create strings using literals.
- [ ] Create strings using `new String()`.
- [ ] Demonstrate immutability.
- [ ] Demonstrate `equals()` vs `==`.
- [ ] Use common String methods.
- [ ] Extract substrings.
- [ ] Search within strings.
- [ ] Convert strings to character arrays.
- [ ] Convert strings to UTF-8 bytes.

## Intermediate

- [ ] Demonstrate String Pool behavior.
- [ ] Demonstrate `intern()`.
- [ ] Compare compile-time and runtime concatenation.
- [ ] Rewrite loop concatenation using `StringBuilder`.
- [ ] Compare `String`, `StringBuilder`, and `StringBuffer`.
- [ ] Build a CSV generator with `StringBuilder`.
- [ ] Build a log-message formatter.
- [ ] Implement UTF-8 encode/decode tests.
- [ ] Process Unicode code points correctly.

## Advanced

- [ ] Inspect bytecode for compile-time concatenation.
- [ ] Inspect bytecode for runtime concatenation.
- [ ] Investigate modern string concatenation using `invokedynamic`.
- [ ] Inspect OpenJDK String implementation.
- [ ] Investigate compact strings.
- [ ] Benchmark `String` concatenation vs `StringBuilder`.
- [ ] Benchmark different StringBuilder initial capacities.
- [ ] Profile temporary string allocation using JFR.
- [ ] Build Unicode-safe string processing utilities.

## Production-Style

Build a:

```text
High-Throughput Text Processing Service
```

Requirements:

- [ ] Process large text inputs.
- [ ] Support UTF-8.
- [ ] Correctly handle supplementary Unicode code points.
- [ ] Avoid unnecessary intermediate strings.
- [ ] Use `StringBuilder` where appropriate.
- [ ] Define input/output charset explicitly.
- [ ] Handle null and blank values deliberately.
- [ ] Measure allocation rate.
- [ ] Profile CPU and GC behavior.
- [ ] Document string-processing trade-offs.

---

# 2.3.38 Interview Questions

## Basic

- [ ] Why is String immutable?
- [ ] What is the String Pool?
- [ ] What is a String literal?
- [ ] Difference between `String` and `new String()`?
- [ ] What does `intern()` do?
- [ ] Why is `String` final?
- [ ] Difference between `String` and `StringBuilder`?
- [ ] Difference between `StringBuilder` and `StringBuffer`?

## Intermediate

- [ ] Explain String Pool behavior.
- [ ] Explain heap vs String Pool.
- [ ] Explain compile-time concatenation.
- [ ] Explain runtime concatenation.
- [ ] Why can `==` return true for two string literals?
- [ ] Why can `==` return false for strings with equal content?
- [ ] Why is `StringBuilder` faster for repeated concatenation?
- [ ] What happens when StringBuilder capacity is exceeded?
- [ ] What is `intern()` used for?
- [ ] Why can indiscriminate interning be dangerous?

## Advanced

- [ ] Explain String immutability and hash-based collections.
- [ ] Explain runtime concatenation in modern Java.
- [ ] Explain why "`+` always becomes StringBuilder" is an oversimplification.
- [ ] Explain Java `char` vs Unicode code point.
- [ ] Explain UTF-8 vs UTF-16.
- [ ] Explain surrogate pairs.
- [ ] Explain compact strings.
- [ ] Explain StringBuilder memory growth.
- [ ] Explain StringBuffer synchronization.

## Senior / Production

- [ ] How would you diagnose excessive string allocation?
- [ ] How would you optimize a text-heavy service?
- [ ] When would you consider `intern()`?
- [ ] How would you safely process user text containing emoji?
- [ ] How would you design a UTF-8 network protocol boundary?
- [ ] Why should a service explicitly specify UTF-8?
- [ ] How can string handling increase GC pressure?
- [ ] How would you detect string-related latency in production?
- [ ] When is `StringBuilder` unnecessary?
- [ ] When can `StringBuffer` be justified?

---

# 2.3.39 Advanced Follow-ups

## Compiler / Bytecode

Inspect:

```bash
javap -c -p ClassName
```

Compare:

```java
String s = "A" + "B";
```

with:

```java
String a = getA();
String s = a + "B";
```

Investigate:

- [ ] Constant folding
- [ ] Runtime concatenation
- [ ] `invokedynamic`
- [ ] Generated bytecode
- [ ] JDK-version differences

---

# 2.3.40 OpenJDK Deep Dive

Study the modern OpenJDK implementation of:

```text
java.lang.String
java.lang.AbstractStringBuilder
java.lang.StringBuilder
java.lang.StringBuffer
```

Investigate:

- [ ] Internal value representation
- [ ] Compact strings
- [ ] Coder information
- [ ] Hash-code caching
- [ ] String concatenation machinery
- [ ] Builder growth
- [ ] `intern()`

Do not confuse implementation details with Java language guarantees.

---

# 2.3.41 Unicode Deep Mastery

You should be able to explain:

```text
Unicode
   ↓
Code point
   ↓
UTF-16 code units
   ↓
Java char / String processing
```

and:

```text
Unicode
   ↓
UTF-8 encoding
   ↓
Bytes
   ↓
Network / file / protocol
```

Master:

- [ ] Code point
- [ ] Code unit
- [ ] Surrogate pair
- [ ] UTF-8
- [ ] UTF-16
- [ ] `char`
- [ ] `String.length()`
- [ ] `codePointCount()`
- [ ] `codePoints()`
- [ ] `Character` APIs

---

# 2.3.42 Performance Investigation

Create benchmarks for:

### Test A

```java
String result = "";

for (...) {
    result += value;
}
```

### Test B

```java
StringBuilder builder = new StringBuilder();

for (...) {
    builder.append(value);
}

String result = builder.toString();
```

### Test C

```java
StringBuilder builder = new StringBuilder(expectedSize);
```

Measure:

- [ ] Throughput
- [ ] Allocation rate
- [ ] Heap usage
- [ ] GC activity
- [ ] CPU time
- [ ] Result correctness

Use:

```text
JMH
JFR
JMC
VisualVM
```

---

# 2.3.43 Production Design Rules

## Prefer `String`

For:

```text
immutable text values
API fields
IDs represented as text
configuration
messages
keys
```

## Prefer `StringBuilder`

For:

```text
repeated text construction
large loops
single-threaded mutable assembly
```

## Prefer `StringBuffer`

Only when:

```text
its synchronized legacy semantics are specifically required
```

## Charset Rule

Whenever converting between text and bytes:

```java
text.getBytes(StandardCharsets.UTF_8)
```

and:

```java
new String(bytes, StandardCharsets.UTF_8)
```

Do not rely on the platform default charset.

---

# 2.3.44 Production Review Checklist

Before shipping string-heavy code:

1. [ ] Is `String` immutability understood?
2. [ ] Is `==` avoided for value comparison?
3. [ ] Is `new String("...")` actually necessary?
4. [ ] Is `intern()` justified by measured requirements?
5. [ ] Is repeated concatenation optimized where necessary?
6. [ ] Is `StringBuilder` used appropriately?
7. [ ] Is Unicode handled correctly?
8. [ ] Is `char` being incorrectly treated as a code point?
9. [ ] Is UTF-8 explicitly specified at byte boundaries?
10. [ ] Have allocation and GC effects been measured?
11. [ ] Are null/empty/blank semantics explicit?
12. [ ] Are security-sensitive strings handled appropriately?

---

# 2.3.45 Final Mastery Gate

## String Fundamentals

- [ ] Explain what `String` is.
- [ ] Explain String immutability.
- [ ] Explain why immutability matters.
- [ ] Explain major String APIs.
- [ ] Explain String equality.

## String Pool

- [ ] Explain String Pool.
- [ ] Explain string literals.
- [ ] Explain heap vs pool correctly.
- [ ] Explain `new String()`.
- [ ] Explain `intern()`.
- [ ] Explain identity vs equality.

## Concatenation

- [ ] Explain compile-time concatenation.
- [ ] Explain runtime concatenation.
- [ ] Explain modern Java concatenation.
- [ ] Identify inefficient loop concatenation.
- [ ] Explain when `StringBuilder` is appropriate.

## Builders

- [ ] Explain StringBuilder.
- [ ] Explain its mutable buffer.
- [ ] Explain capacity and growth.
- [ ] Explain `toString()`.
- [ ] Explain StringBuffer.
- [ ] Explain synchronization trade-offs.

## Unicode

- [ ] Explain Unicode.
- [ ] Explain UTF-8.
- [ ] Explain UTF-16.
- [ ] Explain code points.
- [ ] Explain code units.
- [ ] Explain surrogate pairs.
- [ ] Explain `char` limitations.
- [ ] Use `codePoints()` correctly.

## Performance

- [ ] Explain string allocation.
- [ ] Explain temporary strings.
- [ ] Explain GC implications.
- [ ] Benchmark concatenation strategies.
- [ ] Profile string-heavy workloads.
- [ ] Explain StringBuilder capacity trade-offs.

## Production

- [ ] Design efficient text processing.
- [ ] Handle Unicode correctly.
- [ ] Use explicit UTF-8 boundaries.
- [ ] Prevent incorrect identity comparisons.
- [ ] Make null/empty/blank semantics explicit.
- [ ] Diagnose string allocation problems.
- [ ] Explain trade-offs to senior engineers.

## Interview

- [ ] Answer basic questions.
- [ ] Answer intermediate questions.
- [ ] Answer advanced questions.
- [ ] Answer senior/production questions.
- [ ] Explain String Pool precisely.
- [ ] Explain `intern()`.
- [ ] Explain compile-time vs runtime concatenation.
- [ ] Explain Java `char` vs Unicode code point.
- [ ] Explain UTF-8 vs UTF-16.

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
