# Module 2.3 — Strings
## Deep Mastery Question Bank

> **Goal:** Master Java `String`, immutability, the String Pool, literals, `new String()`, `intern()`, concatenation, `StringBuilder`, `StringBuffer`, Unicode, UTF-8, UTF-16, characters, and Unicode code points.

## Required Flow

**Basic → Intermediate → Advanced → Traps → Design → Debugging Challenges → Production**

Every topic must be understood through:
**What is it? → Why? → Syntax/API → Example → Internals → Runtime/Memory → Edge cases → Mistakes → Performance → Production → Coding → Advanced follow-up**

This question bank is derived from the Module 2.3 Deep Mastery Guide. The guide explicitly covers String fundamentals, immutability, String Pool, literals, `new String()`, `intern()`, compile-time/runtime concatenation, `StringBuilder`, `StringBuffer`, Unicode, UTF-8/UTF-16, code points, compact strings, allocation/GC, debugging, production rules, and final mastery. fileciteturn28file6L1009-L1036
# 2.3.1 String — What Is It?

1. What is `String` in Java?
2. Why is `String` a class rather than a primitive?
3. Is `String` a reference type?
4. What does a `String` represent?
5. Is `String` mutable or immutable?
6. Why is `String` declared `final`?
7. How can a String be created from a literal?
8. How can a String be created with a constructor?
9. Draw the conceptual model `reference → String object → sequence of characters`.
10. Why are string literals handled specially?
11. Why does Java treat strings as fundamental values?
12. Where are Strings commonly used: input, configuration, logging, HTTP, JSON/XML, DB, files, protocols?
13. Why does String immutability enable safe sharing?
14. Why does immutability help with predictable behavior?
15. Why does immutability help with stable hash codes?
16. Why is immutability useful for HashMap keys?
17. Why is immutability useful for pooling?
18. Why does immutability simplify concurrency reasoning?
19. Why can String immutability provide security benefits?
20. Explain why a String reference can change while the String object itself remains unchanged.

---

# 2.3.2 String Immutability

1. Demonstrate that String contents cannot be changed.
2. What happens when `s.concat(" Programming")` is called without assigning the result?
3. Why does the following print `Java`?
```java
String s = "Java";
s.concat(" Programming");
System.out.println(s);
```
4. How do you correctly retain the concatenated result?
5. Does `concat()` mutate the original object?
6. Why does a new String value result from many String operations?
7. How does immutability affect aliases to the same String?
8. Why is immutability important when a String is shared by multiple components?
9. Why can immutable Strings be used safely as HashMap keys?
10. How would mutable keys break hash-based lookup?
11. How does immutability affect caching?
12. How does immutability affect thread-safety reasoning?
13. Why can immutability reduce the need for defensive copying?
14. Identify the difference between changing a String object and reassigning a String reference.

---

# 2.3.3 Basic String API

1. What does `length()` return?
2. What is the difference between `isEmpty()` and `isBlank()`?
3. What does `charAt()` return?
4. What does `substring()` do?
5. What does `indexOf()` return when a match is absent?
6. What does `lastIndexOf()` do?
7. What does `contains()` test?
8. What do `startsWith()` and `endsWith()` test?
9. What is the difference between `equals()` and `equalsIgnoreCase()`?
10. What does `compareTo()` provide?
11. What is the difference between `replace()` and `replaceAll()`?
12. What does `split()` produce?
13. What is the difference between `trim()` and `strip()`?
14. What do `toLowerCase()` and `toUpperCase()` do?
15. What does `repeat()` do?
16. What does `String.join()` do?
17. What does `lines()` provide?
18. What does `formatted()` provide?
19. Which String methods return a new String rather than mutating the original?
20. Which APIs can be sensitive to Unicode or locale semantics?
21. Design a small program demonstrating ten major String APIs.

---

# 2.3.4 String Equality

1. What does `==` mean when comparing two String references?
2. What does `equals()` mean for Strings?
3. Why is `==` generally wrong for comparing String content?
4. Why can two String literals compare `true` using `==`?
5. Why can two equal-content Strings compare `false` using `==`?
6. Predict the result:
```java
String a = "Java";
String b = "Java";
System.out.println(a == b);
System.out.println(a.equals(b));
```
7. Predict the result when one String is created with `new String("Java")`.
8. Explain identity versus logical equality for Strings.
9. How would you safely compare two potentially null Strings?
10. Why is String equality particularly important in production code?
11. How can incorrect identity comparison create intermittent-looking bugs?
12. Create a debugging exercise where `==` passes for literals but fails for runtime Strings.

---

# 2.3.5 String Pool

1. What is the String Pool?
2. Why does Java maintain canonical representations for string literals?
3. Where are string literals handled?
4. What happens when the same literal appears multiple times?
5. Explain conceptually why `String a = "Java"; String b = "Java";` may share a canonical object.
6. How is String Pool behavior different from ordinary object creation?
7. Explain heap versus pooled/canonical String references without oversimplifying JVM implementation details.
8. Why does pooling work especially well with immutable Strings?
9. How does String immutability make sharing pooled values safe?
10. Why should application logic never depend on pooled identity?
11. How can String Pooling reduce duplicate immutable String values?
12. What are the potential memory implications of large numbers of distinct Strings?
13. How would you investigate String Pool behavior experimentally?
14. Write a program that demonstrates literal sharing.

---

# 2.3.6 `new String()`

1. What does `new String("Java")` explicitly request?
2. Why can `new String("Java")` produce a distinct String object?
3. Compare `String a = "Java"` and `String b = new String("Java")`.
4. Predict `a == b` and `a.equals(b)`.
5. Why is unnecessary `new String("...")` generally discouraged?
6. Can `new String()` be justified in a real design?
7. Explain the conceptual relationship between the constructor argument and the newly created String object.
8. Why does `new String()` not mean the String becomes mutable?
9. Create a program showing literal identity, constructor identity, and logical equality.
10. Identify the trap in code that assumes equal content implies equal references.

---

# 2.3.7 `intern()`

1. What does `String.intern()` do?
2. What is meant by a canonical String representation?
3. Predict:
```java
String a = new String("Java");
String b = a.intern();
```
4. Why can `a.intern() == "Java"` be true?
5. Explain the conceptual flow `ordinary String → intern() → canonical pooled String`.
6. When can interning be useful?
7. What workload characteristics make interning potentially useful?
8. Why should interning not be treated as a generic performance trick?
9. What are the risks of interning many unique Strings?
10. How can interning contribute to memory retention?
11. Why should memory behavior be measured before introducing `intern()`?
12. Design a controlled-vocabulary use case for `intern()`.
13. Design a workload where `intern()` would be a poor choice.
14. How would you benchmark the effect of interning?

---

# 2.3.8 Compile-Time String Concatenation

1. What is compile-time String concatenation?
2. Why can `"Hello" + " World"` be folded by the compiler?
3. What is the conceptual result of compile-time constant concatenation?
4. Why can the resulting constant become an interned/canonical String?
5. How is compile-time concatenation different from runtime concatenation?
6. Predict the identity behavior of two equivalent compile-time constants.
7. How can constant expressions affect bytecode?
8. How would you inspect compile-time concatenation using `javap`?
9. Why should you not assume all `+` expressions are compile-time constants?
10. Create examples that clearly separate compile-time and runtime concatenation.

---

# 2.3.9 Runtime String Concatenation

1. When does String concatenation happen at runtime?
2. Analyze `String name = getName(); String message = "Hello " + name;`.
3. Why cannot the compiler necessarily know the final String value?
4. What is the conceptual runtime concatenation flow?
5. What does modern Java's `invokedynamic` string-concatenation machinery change?
6. Why is the statement '`+` always becomes StringBuilder' an oversimplification?
7. How would you inspect runtime concatenation bytecode?
8. How can runtime concatenation produce temporary String data?
9. How should modern Java code balance readability and performance?
10. When is ordinary `+` the preferred choice?

---

# 2.3.10 Loop Concatenation

1. Why can repeated `result += item` in a loop be inefficient?
2. Explain the sequence of intermediate String values created conceptually.
3. Why does String immutability contribute to repeated-result construction?
4. What are the memory implications of repeated concatenation?
5. What are the GC implications?
6. When is loop concatenation acceptable?
7. When should `StringBuilder` be used?
8. Rewrite a loop using `StringBuilder`.
9. How would you benchmark loop concatenation versus a builder?
10. Why should you measure before assuming a concatenation pattern is a bottleneck?

---

# 2.3.11 StringBuilder

1. What is `StringBuilder`?
2. Why is StringBuilder mutable while String is immutable?
3. When is StringBuilder appropriate?
4. How does its mutable buffer work conceptually?
5. What does `append()` do?
6. What does `insert()` do?
7. What does `delete()` do?
8. What does `setLength()` do?
9. What does `capacity()` represent?
10. What happens when capacity is exceeded?
11. Why can repeated capacity growth cause copying/reallocation?
12. Why can pre-sizing improve performance?
13. When is pre-sizing not worth the complexity?
14. What does `toString()` produce?
15. Why is `toString()` an important mutable-to-immutable boundary?
16. Compare String construction with StringBuilder construction.
17. Why is StringBuilder generally preferred for repeated single-threaded assembly?

---

# 2.3.12 StringBuffer

1. What is `StringBuffer`?
2. How does it differ from StringBuilder?
3. Why does StringBuffer synchronize many operations?
4. Does synchronization of individual methods make a multi-operation workflow atomic?
5. Distinguish a thread-safe operation from a thread-safe compound action.
6. When might StringBuffer be justified?
7. Why is StringBuffer often unnecessary in modern single-threaded code?
8. Compare StringBuilder and StringBuffer in a shared mutable state scenario.
9. How would you review code that uses StringBuffer everywhere?
10. Design a test showing why individual synchronization does not automatically make a sequence transactional.

---

# 2.3.13 Unicode Fundamentals

1. What is Unicode?
2. What problem does Unicode solve?
3. What is a Unicode code point?
4. What is a code unit?
5. Why are code points and code units different concepts?
6. What is a UTF-16 code unit?
7. Why can a single Unicode code point require two UTF-16 code units?
8. What is a surrogate pair?
9. Why is Java `char` not necessarily a complete Unicode character?
10. Why can `charAt()` be misleading for user-visible characters?
11. Why is `codePoints()` useful?
12. How can emoji expose incorrect assumptions about `char`?
13. Write a Unicode-safe traversal using code points.
14. How would you test a String containing supplementary characters?
15. Why is 'character count' ambiguous in Unicode?

---

# 2.3.14 UTF-8 vs UTF-16

1. What is UTF-8?
2. What is UTF-16?
3. What is the basic storage unit of UTF-8?
4. What is the basic storage unit of UTF-16?
5. Why is UTF-8 common for files, HTTP, JSON, and network protocols?
6. Why is UTF-16 relevant to Java String representation?
7. Is Java String's in-memory representation identical to UTF-8 encoding?
8. Why are encoding and in-memory representation separate concerns?
9. How does UTF-8 represent ASCII characters?
10. How does UTF-16 represent supplementary code points?
11. Compare UTF-8 and UTF-16 for supplementary Unicode characters.
12. Why must byte boundaries explicitly specify a charset?
13. Why is `StandardCharsets.UTF_8` preferable to a platform default?
14. Design a UTF-8 encode/decode test.
15. Design a network boundary that explicitly uses UTF-8.

---

# 2.3.15 Compact Strings

1. What are compact strings?
2. Why can modern JDK implementations use a compact representation?
3. What is the conceptual `byte[] value + coder` representation mentioned in the guide?
4. Why can compact strings reduce memory for Latin-1-heavy workloads?
5. Is compact-string layout a Java language guarantee?
6. Why should production code avoid depending on internal String layout?
7. How would you investigate compact-string behavior?
8. How can implementation changes affect memory without changing application source code?
9. Why should JVM implementation observations be separated from Java language semantics?

---

# 2.3.16 String Memory and GC

1. What memory components can contribute to String-heavy workloads?
2. How can duplicate Strings increase memory consumption?
3. How can temporary concatenation results increase allocation?
4. How can interned Strings affect memory retention?
5. How can StringBuilder buffers affect memory?
6. Why can repeated temporary Strings increase young-generation allocation?
7. How can excessive allocation increase GC activity?
8. How can GC pressure increase CPU consumption?
9. How can GC pressure affect latency?
10. Why should profiling be used instead of assuming every String operation is expensive?
11. How would you diagnose excessive String allocation in production?
12. Which tools from the guide can help: JMH, JFR, JMC, VisualVM?
13. Design a profiling experiment for a text-heavy service.

---

# 2.3.17 StringBuilder Capacity and Growth

1. What is initial StringBuilder capacity?
2. Why might you provide an expected size to the constructor?
3. What happens when appended content exceeds capacity?
4. What is reallocation?
5. Why can copying during growth cost CPU?
6. How can pre-sizing reduce growth-related work?
7. What trade-off exists when the predicted size is too large?
8. Why should capacity be chosen based on reasonable estimates rather than arbitrary huge values?
9. How would you benchmark different initial capacities?
10. How would you inspect heap usage while varying capacity?
11. Explain memory retention during construction.

---

# 2.3.18 StringBuilder.toString()

1. What happens when `builder.toString()` is called?
2. Why is the result an immutable String?
3. Explain the boundary `mutable construction → immutable final value`.
4. Does calling `toString()` make the builder immutable?
5. Can the builder still be modified after `toString()`?
6. Why is the final String a useful API boundary?
7. How would you design a method that internally uses StringBuilder but returns String?
8. Why should callers generally receive the final immutable String rather than the mutable builder?

---

# 2.3.19 Basic Consolidation

1. Explain String in one minute.
2. Explain String immutability in one minute.
3. Explain String Pool in one minute.
4. Explain `new String()` versus a literal.
5. Explain `intern()` in one minute.
6. Explain `==` versus `equals()` for Strings.
7. Explain compile-time versus runtime concatenation.
8. Explain StringBuilder versus StringBuffer.
9. Explain Unicode code point versus Java char.
10. Explain UTF-8 versus UTF-16.
11. Write a program demonstrating all ten concepts.

---

# 2.3.20 Intermediate Concept Questions

1. Predict the result of literal-vs-literal equality.
2. Predict the result of literal-vs-constructor equality.
3. Predict the result of `intern()` followed by `==`.
4. Determine whether a concatenation expression is compile-time or runtime.
5. Trace intermediate values in loop concatenation.
6. Choose String or StringBuilder for a given workload.
7. Choose StringBuilder or StringBuffer for a given concurrency requirement.
8. Determine whether a Unicode sample requires surrogate-pair handling.
9. Choose the correct charset for a byte conversion.
10. Identify where allocation may occur in a String-heavy method.

---

# 2.3.21 Advanced Internal Questions

1. Explain String immutability in relation to hashing and pooling.
2. Explain how modern runtime concatenation can use invokedynamic.
3. Explain why '`+` always becomes StringBuilder' is incomplete.
4. Explain the conceptual String memory layers.
5. Explain compact strings as an implementation detail.
6. Explain StringBuilder capacity growth and copying.
7. Explain StringBuffer synchronization limitations.
8. Explain why UTF-8 encoding is distinct from Java's in-memory String representation.
9. Explain why `char` is a UTF-16 code unit rather than necessarily a code point.
10. Explain why code-point processing is required for supplementary characters.

---

# 2.3.22 Traps and Common Mistakes

1. Why is `==` a trap for String content comparison?
2. Why can a String-literal `==` test accidentally hide a bug?
3. Why is `new String("Java")` often unnecessary?
4. Why is `intern()` dangerous when applied indiscriminately?
5. Why is repeated `+=` in a loop a potential performance trap?
6. Why is assuming every `+` becomes StringBuilder a trap?
7. Why is assuming StringBuffer makes arbitrary workflows atomic a trap?
8. Why is treating `char` as a complete Unicode character a trap?
9. Why is relying on platform-default charset a production trap?
10. Why is assuming every String operation creates a fresh large object an oversimplification?
11. Why is depending on compact-string internals a trap?
12. Why is over-sizing every StringBuilder a trap?
13. Why is optimizing String code without measurement a trap?

---

# 2.3.23 Design Questions

1. Design a value object that uses String fields safely.
2. Design a high-throughput text formatter.
3. Design a CSV generator using StringBuilder.
4. Design a log-message formatter.
5. Design a UTF-8 network protocol boundary.
6. Design an API that clearly distinguishes null, empty, and blank text.
7. Design a Unicode-safe user-name processing component.
8. Design a controlled-vocabulary system where interning might be justified.
9. Design a configuration parser with explicit charset handling.
10. Design a service that minimizes unnecessary intermediate Strings.
11. Decide when StringBuilder is unnecessary despite its performance reputation.
12. Decide when StringBuffer's synchronized semantics are actually required.
13. Design a production review checklist for String-heavy code.

---

# 2.3.24 Debugging Challenges

1. Debug code that uses `==` to compare a literal with runtime input.
2. Debug code where `new String()` causes unexpected identity behavior.
3. Debug incorrect assumptions about `intern()`.
4. Debug a loop that repeatedly concatenates Strings.
5. Debug a StringBuilder whose capacity repeatedly grows.
6. Debug a StringBuffer-based compound operation that is incorrectly assumed atomic.
7. Debug code that counts Unicode characters using `charAt()`/`length()` incorrectly.
8. Debug UTF-8 corruption caused by inconsistent charsets.
9. Debug a null/empty/blank validation bug.
10. Debug excessive String allocation using profiling evidence.
11. Debug a latency regression caused by temporary String creation.
12. Debug a text processor that breaks on emoji or supplementary Unicode code points.

---

# 2.3.25 Coding Challenges

1. Create a String API demonstration program.
2. Demonstrate String immutability.
3. Demonstrate String Pool behavior.
4. Demonstrate `new String()` identity.
5. Demonstrate `intern()`.
6. Compare compile-time and runtime concatenation.
7. Rewrite loop concatenation using StringBuilder.
8. Compare String, StringBuilder, and StringBuffer.
9. Build a CSV generator with StringBuilder.
10. Build a log-message formatter.
11. Implement UTF-8 encode/decode tests.
12. Process Unicode code points correctly.
13. Inspect bytecode for compile-time concatenation.
14. Inspect bytecode for runtime concatenation.
15. Investigate invokedynamic string concatenation.
16. Inspect OpenJDK String implementation.
17. Investigate compact strings.
18. Benchmark String concatenation versus StringBuilder.
19. Benchmark multiple StringBuilder initial capacities.
20. Profile temporary String allocation with JFR.
21. Build a Unicode-safe String processing utility.

---

# 2.3.26 Production Scenarios

1. Build a High-Throughput Text Processing Service.
2. Process large text inputs without unnecessary intermediate Strings.
3. Support UTF-8 explicitly.
4. Correctly process supplementary Unicode code points.
5. Use StringBuilder where repeated mutable assembly is required.
6. Define input/output charset explicitly.
7. Handle null, empty, and blank values deliberately.
8. Measure allocation rate.
9. Profile CPU and GC behavior.
10. Document String-processing trade-offs.
11. Diagnose excessive String allocation in a production service.
12. Decide whether interning is justified using measured workload characteristics.
13. Review whether `==` is used anywhere for String content.
14. Review whether `new String("...")` is actually necessary.
15. Review whether a StringBuilder capacity is reasonable.
16. Review whether StringBuffer has a real synchronization requirement.
17. Review whether security-sensitive strings receive appropriate handling.

---

# 2.3.27 Interview — Basic

1. What is String?
2. Why is String immutable?
3. What is the String Pool?
4. What is a String literal?
5. What is the difference between String and `new String()`?
6. What does `intern()` do?
7. Why is String final?
8. What is StringBuilder?
9. What is StringBuffer?
10. What is the difference between StringBuilder and StringBuffer?

---

# 2.3.28 Interview — Intermediate

1. Explain String Pool behavior.
2. Explain heap versus String Pool.
3. Explain compile-time concatenation.
4. Explain runtime concatenation.
5. Why can `==` return true for two literals?
6. Why can `==` return false for equal-content Strings?
7. Why can StringBuilder be faster for repeated concatenation?
8. What happens when StringBuilder capacity is exceeded?
9. When is intern useful?
10. Why can indiscriminate interning be dangerous?

---

# 2.3.29 Interview — Advanced

1. Explain String immutability and hash-based collections.
2. Explain runtime concatenation in modern Java.
3. Why is '`+` always becomes StringBuilder' an oversimplification?
4. Explain Java `char` versus Unicode code point.
5. Explain UTF-8 versus UTF-16.
6. Explain surrogate pairs.
7. Explain compact strings.
8. Explain StringBuilder memory growth.
9. Explain StringBuffer synchronization.
10. Explain why byte encoding and String in-memory representation are separate concerns.

---

# 2.3.30 Interview — Senior / Production

1. How would you diagnose excessive String allocation?
2. How would you optimize a text-heavy service?
3. When would you consider intern?
4. How would you safely process user text containing emoji?
5. How would you design a UTF-8 network protocol boundary?
6. Why should a service explicitly specify UTF-8?
7. How can String handling increase GC pressure?
8. How would you detect String-related latency in production?
9. When is StringBuilder unnecessary?
10. When can StringBuffer be justified?

---

# 2.3.31 Advanced Follow-Ups

1. Inspect bytecode for compile-time concatenation.
2. Inspect bytecode for runtime concatenation.
3. Investigate invokedynamic string concatenation.
4. Inspect OpenJDK String implementation.
5. Investigate compact strings.
6. Benchmark concatenation strategies with JMH.
7. Benchmark different StringBuilder initial capacities.
8. Profile temporary String allocation using JFR.
9. Build Unicode-safe String utilities.
10. Explain which observations are language-level guarantees versus implementation details.

---

# 2.3.32 Production Review Checklist

1. Is String immutability understood?
2. Is `==` avoided for value comparison?
3. Is `new String("...")` actually necessary?
4. Is `intern()` justified by measured requirements?
5. Is repeated concatenation optimized where necessary?
6. Is StringBuilder used appropriately?
7. Is Unicode handled correctly?
8. Is `char` being incorrectly treated as a code point?
9. Is UTF-8 explicitly specified at byte boundaries?
10. Have allocation and GC effects been measured?
11. Are null/empty/blank semantics explicit?
12. Are security-sensitive Strings handled appropriately?

---

# 2.3.33 Final Mastery Gate

1. Can you explain what String is?
2. Can you explain String immutability and why it matters?
3. Can you explain major String APIs?
4. Can you explain String equality?
5. Can you explain String Pool and literals?
6. Can you correctly explain heap versus pool?
7. Can you explain `new String()`?
8. Can you explain `intern()`?
9. Can you explain compile-time versus runtime concatenation?
10. Can you identify inefficient loop concatenation?
11. Can you explain when StringBuilder is appropriate?
12. Can you explain StringBuilder's mutable buffer?
13. Can you explain capacity and growth?
14. Can you explain `toString()`?
15. Can you explain StringBuffer and synchronization trade-offs?
16. Can you explain Unicode, UTF-8, UTF-16, code points, code units, and surrogate pairs?
17. Can you explain Java char limitations?
18. Can you use `codePoints()` correctly?
19. Can you explain String allocation and temporary Strings?
20. Can you benchmark and profile String-heavy workloads?
21. Can you handle Unicode correctly in production?
22. Can you specify UTF-8 explicitly at byte boundaries?
23. Can you diagnose String allocation problems?
24. Can you explain all major trade-offs to a senior engineer?

---

# 2.3.34 Final Integrated Challenge

Build a **High-Throughput Text Processing Service**.

1. Accept large text input.
2. Define UTF-8 explicitly at byte boundaries.
3. Correctly process supplementary Unicode code points.
4. Demonstrate why `char` is not always a complete character.
5. Avoid incorrect String identity comparisons.
6. Define null, empty, and blank semantics.
7. Use `StringBuilder` for repeated mutable assembly where appropriate.
8. Avoid unnecessary intermediate Strings.
9. Choose a reasonable initial StringBuilder capacity.
10. Return immutable final Strings.
11. Decide whether StringBuffer has any justified use.
12. Evaluate whether interning is justified.
13. Measure allocation rate.
14. Profile CPU and GC behavior.
15. Inspect relevant bytecode.
16. Create intentionally broken versions for identity, Unicode, charset, concatenation, and allocation bugs.
17. Debug and explain every failure.
18. Document the production design decisions.
19. Produce benchmark results comparing at least two construction strategies.
20. Explain all trade-offs to a senior engineer.

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
