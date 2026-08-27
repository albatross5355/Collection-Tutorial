# Java Fundamentals & OOP --- 9-Module Mastery Question Bank

## Purpose

This question bank is the assessment layer for Modules **1.1--1.9** of
the Java Fundamentals & OOP Deep Mastery syllabus.

For every topic, the questions are intentionally organized as:

1.  **Basic --- What is it?**
2.  **Intermediate --- How/why does it work?**
3.  **Advanced --- Internals and difficult cases**
4.  **Conceptual --- Reasoning and misconceptions**
5.  **Theory --- Language/JVM/design rules**
6.  **Implementation --- Write/build it**
7.  **Traps --- Predict/fix common mistakes**
8.  **Debugging --- Diagnose failures**
9.  **Performance --- Runtime/resource implications**
10. **Production/Design --- Real engineering decisions**
11. **Senior/Interview --- Defend a decision**
12. **Practical assessment --- Demonstrate mastery**

The source syllabus uses the mastery cycle **Explain → Implement →
Internals → Edge Cases → Trade-offs → Debug → Production**, so this
document deliberately tests beyond definitions.

------------------------------------------------------------------------

# MODULE 1.1 --- JAVA PLATFORM FUNDAMENTALS

## 1.1.1 JDK

### Basic

1.  What is the JDK?
2.  Why is the JDK needed for Java development?
3.  What major tools are included in a modern JDK?
4.  What is `javac`?
5.  What is the `java` launcher?
6.  What are `jar`, `javadoc`, `jdb`, `jcmd`, `jstack`, `jmap`, `jps`,
    `jstat`, `jfr`, `jlink`, and `jpackage` used for?

### Intermediate

7.  JDK vs JRE vs JVM?
8.  Does the JDK contain a JVM?
9.  Which tools are development tools and which are diagnostic/runtime
    tools?
10. Why can multiple JDK versions be installed on one machine?
11. What are `JAVA_HOME` and `PATH` used for?
12. Why can `java -version` and `javac -version` disagree?

### Advanced

13. What happens internally when `javac` is invoked?
14. How does a JDK distribution relate to the Java specification?
15. What is OpenJDK?
16. Why do different JDK vendors exist?
17. What does `jlink` create?
18. Why can a custom runtime image be smaller than a full JDK?

### Conceptual / Theory

19. Is the JVM platform independent?
20. Is Java source code directly executed by the operating system?
21. Why is "JDK = compiler + JVM" an incomplete model?
22. Why is a separate traditional JRE less central in modern Java
    distributions?

### Implementation

23. Install two JDK versions and switch between them.
24. Compile and run a Java program manually.
25. Package it into a JAR.
26. Generate Javadocs.
27. Inspect a running JVM with JDK diagnostic tools.

### Traps

28. A machine can run an already compiled Java application without a
    development JDK. Explain.
29. `JAVA_HOME` points to JDK 17 while `PATH` finds JDK 21. What can
    happen?
30. Why is "JDK vendor = different Java language" incorrect?

### Debugging

31. A build says `javac` is version 17 but CI executes Java 11.
    Diagnose.
32. A production JVM has high CPU. Which JDK tools would you start with?
33. A Java process is stuck. How would `jstack` help?

### Performance

34. What development/runtime overhead should be considered when
    selecting tools?
35. Why might a custom runtime image help container size and startup
    characteristics?

### Production / Design

36. How would you standardize JDK versions across developers, CI and
    production?
37. Would you ship a full JDK or a runtime image in production? What
    factors decide?
38. How would you choose a JDK distribution for an enterprise system?

### Senior

39. Design a Java runtime strategy for services using two different LTS
    releases.
40. Explain how you would safely upgrade a large fleet of Java services.

------------------------------------------------------------------------

## 1.1.2 JRE

1.  What is the JRE?
2.  What was the historical purpose of the JRE?
3.  How does JRE relate to the JVM?
4.  Why doesn't a runtime environment need all development tools?
5.  Why has the traditional standalone JRE model changed?
6.  What is a minimal Java runtime?
7.  How can `jlink` be related to a runtime image?
8.  What are the trade-offs of shipping a minimal runtime?
9.  Can a Java application execute without a traditional standalone JRE
    installation?
10. What problems can arise from an incomplete custom runtime?
11. How would you diagnose a missing runtime module?
12. Design a container runtime for a Java service and justify what you
    include.

------------------------------------------------------------------------

## 1.1.3 JVM

### Basic

1.  What is the JVM?
2.  What does the JVM execute?
3.  What is bytecode?
4.  Why does Java use a virtual machine?
5.  Is every JVM implementation identical?

### Intermediate

6.  Explain source → compiler → bytecode → JVM → execution.
7.  What is the interpreter?
8.  What is JIT compilation?
9.  Why does a JVM use both interpretation and JIT compilation?
10. What are the major JVM runtime data areas?
11. What is the heap?
12. What is a thread stack?
13. What is Metaspace?
14. What is a class loader?

### Advanced

15. How does the JVM execute bytecode?
16. What is profiling information?
17. What is method inlining?
18. What is escape analysis?
19. What is deoptimization?
20. What are safepoints?
21. What is a stop-the-world pause?
22. How can JIT optimization change runtime behavior without changing
    Java semantics?
23. How does class unloading relate to class loaders?

### Conceptual

24. Why is "Java is interpreted" incomplete?
25. Why is "objects live on the heap and primitives live on the stack"
    an oversimplification?
26. Why can two JVMs execute the same bytecode differently while
    preserving language semantics?
27. Why can JVM configuration affect production behavior?

### Production

28. How would you investigate high CPU?
29. How would you investigate excessive GC?
30. How would you investigate thread contention?
31. How would you investigate `OutOfMemoryError`?
32. What JVM evidence would you collect during an incident?

------------------------------------------------------------------------

## 1.1.4 Java Source Compilation / `javac`

1.  What happens when `javac MyApp.java` is executed?
2.  What is produced by compilation?
3.  What does `-d` do?
4.  What does `-cp` do?
5.  What does `--release` control?
6.  What is source compatibility?
7.  What is binary compatibility?
8.  What is runtime compatibility?
9.  Why can compilation succeed while execution fails?
10. How does `javac` resolve referenced classes?
11. What is annotation processing?
12. Why can `--release` be safer for cross-version compilation?
13. What happens if code compiled for a newer Java release is run on an
    older JVM?
14. Implement a multi-package project using `javac` only.
15. Deliberately produce an incompatible class file and diagnose the
    failure.

------------------------------------------------------------------------

## 1.1.5 Bytecode and `.class` Files

1.  What is bytecode?
2.  What is a `.class` file?
3.  What is the class-file magic number?
4.  What is the constant pool?
5.  What is a class-file version?
6.  What are access flags?
7.  What information do method and field entries contain?
8.  What does `javap -c` show?
9.  What does `javap -v` show?
10. What is `invokevirtual`?
11. What is `invokestatic`?
12. What is `invokespecial`?
13. What happens when class-file version is newer than the JVM?
14. How can bytecode inspection explain overload vs override behavior?
15. Inspect constructors and identify `<init>`.
16. Explain how bytecode reaches the interpreter/JIT.
17. Use `javap` to investigate an unexpected language-level behavior.

------------------------------------------------------------------------

## 1.1.6 JVM Execution Lifecycle

1.  What happens when `java MyApp` is executed?
2.  What is class loading?
3.  What is linking?
4.  What is verification?
5.  What is preparation?
6.  What is resolution?
7.  What is initialization?
8.  When is `main()` invoked?
9.  Loading vs initialization?
10. Does every class load immediately initialize?
11. What triggers initialization?
12. What is `<clinit>`?
13. What happens if initialization fails?
14. What is `ExceptionInInitializerError`?
15. How does initialization behave when multiple threads trigger it?
16. Predict static initialization output in an inheritance hierarchy.
17. Diagnose a startup failure caused by static initialization.
18. Explain why expensive external I/O in static initialization is
    risky.

------------------------------------------------------------------------

## 1.1.7 Classpath / Module Path / Launcher

1.  What is the classpath?
2.  What can be placed on the classpath?
3.  What is classpath shadowing?
4.  What happens when the same class exists in multiple JARs?
5.  `ClassNotFoundException` vs `NoClassDefFoundError`?
6.  Compile-time classpath vs runtime classpath?
7.  What is the module path?
8.  What is JPMS?
9.  What is `module-info.java`?
10. What do `requires` and `exports` mean?
11. What is the unnamed module?
12. What is an automatic module?
13. Why can a `public` class still be inaccessible across modules?
14. Classpath vs module path: when would you choose each?
15. What does the `java` launcher do?
16. JVM arguments vs application arguments?
17. Analyze `java -Xmx512m -cp app.jar com.example.Main arg1`.
18. Diagnose a production classpath conflict.
19. Migrate a small classpath application to JPMS.
20. Design a dependency-resolution strategy for a large Java
    application.

------------------------------------------------------------------------

## 1.1.8 Java Versioning and LTS

1.  What is an LTS release?
2.  What is a feature release?
3.  What is a preview feature?
4.  What is source compatibility?
5.  What is binary compatibility?
6.  What is runtime compatibility?
7.  Why can newer bytecode fail on an older JVM?
8.  What does `UnsupportedClassVersionError` indicate?
9.  What should be evaluated before moving between LTS releases?
10. How do preview features affect build and deployment?
11. How would you standardize Java versions in CI/CD?
12. Design a safe Java LTS upgrade plan for a production fleet.

------------------------------------------------------------------------

## 1.1.9 Module 1.1 Integrated Assessment

1.  Build a Java application without Maven/Gradle.
2.  Compile it manually.
3.  Package it.
4.  Run it.
5.  Inspect `.class` files with `javap`.
6.  Introduce and diagnose a classpath conflict.
7.  Capture a thread dump.
8.  Capture a JFR recording.
9.  Explain the complete source-to-CPU execution pipeline.
10. Explain how you would diagnose a real production JVM incident.

------------------------------------------------------------------------

# MODULE 1.2 --- LANGUAGE FUNDAMENTALS

## 1.2.1 Variables, Declarations, Scope and Lifetime

### Basic

1.  What is a variable?
2.  Declaration vs initialization vs assignment?
3.  What is a local variable?
4.  What is an instance field?
5.  What is a static field?
6.  What is a parameter?
7.  What is a reference variable?
8.  What is scope?
9.  What is lifetime?

### Intermediate

10. Why must local variables be definitely assigned?
11. Why do fields receive default values?
12. What is variable shadowing?
13. How does `this.x` resolve a shadowed field?
14. Can an object outlive the method that created it?
15. Is variable lifetime the same as object lifetime?

### Advanced / Conceptual

16. Where are local variables represented during JVM execution?
17. Why is "all primitives are on the stack" an unsafe rule?
18. How can reachability differ from lexical scope?
19. How can static references retain objects?
20. Explain aliasing through two references to the same object.

### Implementation / Traps / Production

21. Demonstrate shadowing.
22. Demonstrate definite-assignment compilation errors.
23. Create an accidental static memory-retention example and fix it.
24. Design a stateful class without unnecessary global state.

------------------------------------------------------------------------

## 1.2.2 Primitive Data Types

1.  Name all eight primitive types.
2.  What are their conceptual ranges/sizes?
3.  What is integer overflow?
4.  What is underflow?
5.  What is numeric promotion?
6.  Why does `byte + byte` normally produce `int`?
7.  Why does `long` require appropriate literals?
8.  Why does `float` require care?
9.  What is `char`?
10. What are Unicode code points and UTF-16 code units?
11. Why can a `char` represent only part of some Unicode characters?
12. Why is `double` unsuitable for exact monetary arithmetic?
13. Predict `Integer.MAX_VALUE + 1`.
14. Predict the type of `byte + byte`.
15. Explain `0.1 + 0.2`.
16. Implement demonstrations of overflow and floating-point precision.
17. Choose `int`, `long`, `double`, or a decimal representation for
    production scenarios.

------------------------------------------------------------------------

## 1.2.3 Literals

1.  What is a literal?
2.  Explain decimal, binary, octal and hexadecimal integer literals.
3.  What are floating-point literals?
4.  What are character and String literals?
5.  What are boolean and `null` literals?
6.  What are numeric underscores?
7.  What are the types of `10`, `10L`, `10.0`, and `10.0f`?
8.  What literal rules can cause unexpected compilation errors?
9.  Implement equivalent values in multiple literal forms.
10. Identify unsafe/misleading magic literals in production code.

------------------------------------------------------------------------

## 1.2.4 Operators and Evaluation

1.  What are arithmetic operators?
2.  What are relational operators?
3.  What are logical operators?
4.  What are bitwise operators?
5.  What are shift operators?
6.  What is short-circuit evaluation?
7.  `&` vs `&&`?
8.  `|` vs `||`?
9.  `>>` vs `>>>`?
10. What is operator precedence?
11. What is associativity?
12. Predict complex increment expressions.
13. Explain operand evaluation order.
14. Implement a bitmask permission model.
15. Identify overly clever expressions that should be refactored.
16. Explain performance and readability trade-offs of bit manipulation.

------------------------------------------------------------------------

## 1.2.5 Type Conversion and Casting

1.  What is widening conversion?
2.  What is narrowing conversion?
3.  What is explicit casting?
4.  What is upcasting?
5.  What is downcasting?
6.  Why is widening generally safer?
7.  What happens when a large `long` is narrowed to `int`?
8.  What is numeric promotion?
9.  What is the difference between primitive and reference casting?
10. Does a cast create a new object?
11. Why can downcasting cause `ClassCastException`?
12. Compile-time type vs runtime type?
13. Demonstrate safe and unsafe downcasting.
14. Refactor excessive downcasting into polymorphism.
15. Diagnose a `ClassCastException` at an API boundary.

------------------------------------------------------------------------

## 1.2.6 Control Flow and Loops

1.  What is `if`/`else`?
2.  What is a loop?
3.  `for` vs `while` vs `do-while`?
4.  Normal `for` vs enhanced `for`?
5.  What does `break` do?
6.  What does `continue` do?
7.  What does `return` do inside a loop?
8.  Predict nested-loop output.
9.  Explain labeled `break`/`continue`.
10. Identify unreachable or redundant branches.
11. Refactor deeply nested conditionals.
12. Design guard clauses for production validation.
13. Explain how branching complexity affects testability.

------------------------------------------------------------------------

## 1.2.7 `switch`

1.  What is `switch`?
2.  What is fall-through?
3.  What does `break` do in traditional switch?
4.  What are arrow labels?
5.  What is a switch expression?
6.  What is `yield`?
7.  What is exhaustiveness?
8.  Predict fall-through output.
9.  Compare traditional and modern switch.
10. Explain null handling and modern pattern matching at the appropriate
    Java level.
11. Design an enum-based state switch.
12. Decide when a large switch should become polymorphism/Strategy.
13. Explain the maintenance impact of adding new enum states.

------------------------------------------------------------------------

## 1.2.8 Methods, Signatures and Overloading

1.  What is a method?
2.  Parameter vs argument?
3.  What is a return type?
4.  What is `void`?
5.  What is a method signature?
6.  What is overloading?
7.  Can methods be overloaded only by return type?
8.  Static vs instance methods?
9.  What is overload resolution?
10. How does Java choose among overloaded methods?
11. How do widening, boxing and varargs affect overload resolution?
12. Overloading vs overriding?
13. Predict which overload is selected when the compile-time type
    differs from runtime type.
14. Inspect overloaded methods with bytecode.
15. Design APIs that avoid confusing overload sets.

------------------------------------------------------------------------

## 1.2.9 Pass-by-Value

1.  Is Java pass-by-value or pass-by-reference?
2.  What exactly is copied for a primitive?
3.  What exactly is copied for an object reference?
4.  Why can a method mutate an object?
5.  Why doesn't reassignment of a parameter replace the caller's
    reference?
6.  Trace a primitive example.
7.  Trace object mutation.
8.  Trace reference reassignment.
9.  Explain aliasing.
10. Debug a misleading "Java passes objects by reference" explanation.
11. Demonstrate all three behaviors in code.
12. Explain how immutability and defensive copying reduce side effects.
13. Design an API around Java's pass-by-value semantics.

------------------------------------------------------------------------

## 1.2.10 Arrays

1.  What is an array?
2.  How are arrays declared and initialized?
3.  What does `length` mean?
4.  Are arrays objects?
5.  What happens on invalid indexing?
6.  What happens when an array reference is null?
7.  What is a multidimensional array?
8.  What is a jagged array?
9.  What is array covariance?
10. Explain `ArrayStoreException`.
11. Why can an `Object[]` reference point to a `String[]`?
12. What happens when a non-String is stored through that reference?
13. `System.arraycopy()` vs `Arrays.copyOf()`?
14. Implement array copying and a jagged array.
15. Explain memory and allocation behavior.
16. How would you safely expose an internal array from a class?

------------------------------------------------------------------------

## 1.2.11 Strings

1.  Is `String` primitive?
2.  Why is String immutable?
3.  What is the String pool?
4.  Literal vs `new String(...)`?
5.  `==` vs `equals()` for String?
6.  What does `intern()` do conceptually?
7.  What is compile-time concatenation?
8.  What is runtime concatenation?
9.  When should `StringBuilder` be used?
10. `StringBuilder` vs `StringBuffer`?
11. Why is immutable String useful as a map key?
12. Predict String identity examples.
13. Explain repeated concatenation inside a loop.
14. Implement a string-building workload.
15. Discuss String pooling and memory-retention trade-offs.

------------------------------------------------------------------------

## 1.2.12 `null`

1.  What does `null` represent?
2.  Can primitives contain null?
3.  What is a null reference?
4.  What causes NPE?
5.  Which operations can trigger NPE?
6.  What is null unboxing?
7.  What does `Objects.requireNonNull()` provide?
8.  Why is null not an object?
9.  Missing vs empty vs null?
10. Debug a production NPE from a stack trace.
11. Design a null contract for a public API.
12. Explain why assertions should not replace mandatory validation.

------------------------------------------------------------------------

## 1.2.13 `final`

1.  What does `final` mean for a local variable?
2.  What does it mean for a parameter?
3.  What does it mean for a field?
4.  What does it mean for a method?
5.  What does it mean for a class?
6.  What is a blank final field?
7.  What is an effectively final variable?
8.  Does final make an object immutable?
9.  Why can a `final List` still be mutated?
10. Why are final fields useful in immutable objects?
11. How does final interact with inheritance?
12. Implement an immutable value object using final fields.
13. Explain final-field initialization and safe design.
14. Diagnose a mistaken "final = immutable" assumption.

------------------------------------------------------------------------

## 1.2.14 `static`

1.  What is a static field?
2.  What is a static method?
3.  What is a static block?
4.  What is a static nested class?
5.  Why can't a static method directly use `this`?
6.  Instance vs class-level state?
7.  What is static initialization?
8.  What is static import?
9.  Can static methods be overloaded?
10. Are static methods overridden or hidden?
11. How can mutable static state create test pollution?
12. How can static state create concurrency problems?
13. How can static state retain memory?
14. Refactor a mutable static dependency.
15. Decide when static state is justified in production.

------------------------------------------------------------------------

## 1.2.15 Initialization Order

1.  What is class initialization?
2.  What is instance initialization?
3.  Static fields vs static blocks?
4.  Instance fields vs instance initializer blocks?
5.  What executes first in a parent/child hierarchy?
6.  Predict exact initialization output.
7.  Explain parent static → child static → parent instance → parent
    constructor → child instance → child constructor.
8.  What are `<clinit>` and `<init>`?
9.  What happens if static initialization fails?
10. What happens when multiple threads trigger initialization?
11. Diagnose a startup bug caused by initialization.
12. Explain why expensive I/O in static initialization is risky.
13. Design a safer lazy initialization approach.

------------------------------------------------------------------------

## 1.2.16 `this` and `super`

1.  What is `this`?
2.  What does `this.field` mean?
3.  What does `this.method()` mean?
4.  What does `this(...)` mean?
5.  What is `super`?
6.  What does `super.field` mean?
7.  What does `super.method()` mean?
8.  What does `super(...)` mean?
9.  Why must constructor chaining occur in the appropriate first
    invocation position?
10. `this()` vs `super()`?
11. Predict field and method behavior when parent and child have same
    names.
12. Explain receiver-object behavior.
13. Explain the constructor dispatch trap.
14. Demonstrate returning `this` for a fluent API.
15. Debug a constructor that calls an overridable method.

------------------------------------------------------------------------

## 1.2.17 Packages and Imports

1.  What is a package?
2.  What is a package declaration?
3.  What is a fully qualified class name?
4.  What does `import` do?
5.  What is static import?
6.  How does package-private access relate to packages?
7.  Can two packages have classes with the same simple name?
8.  How do you resolve ambiguous imports?
9.  How do packages relate to classpath and JPMS?
10. What are split packages?
11. Organize a small application into packages.
12. Decide package-by-feature vs technical-layer organization.
13. Use package-private classes to enforce internal boundaries.

------------------------------------------------------------------------

## 1.2.18 Access Modifiers

1.  Explain private, package-private, protected and public.
2.  Build the four-level access matrix.
3.  What is special about protected across packages?
4.  Why is public not necessarily globally accessible under JPMS?
5.  Why can a public member still be inaccessible through an
    inaccessible enclosing type?
6.  Demonstrate every access level across packages.
7.  Explain protected access using a subclass reference.
8.  Identify when protected becomes a design smell.
9.  Design a public library API with minimal exposure.
10. Explain why package-private can be useful for architecture.

------------------------------------------------------------------------

## 1.2.19 Enums

1.  What is an enum?
2.  What are enum constants?
3.  Can enums have fields?
4.  Can enums have constructors?
5.  Can enums have methods?
6.  What do `values()` and `valueOf()` do?
7.  Why are enum constants identity-based?
8.  Why is `==` appropriate for enum constants?
9.  What are `EnumSet` and `EnumMap`?
10. Why is persisting `ordinal()` dangerous?
11. Can an enum implement an interface?
12. Implement an enum-based state machine.
13. Give enum constants explicit stable business codes.
14. Handle unknown external enum values.
15. Design an enum for a production domain state.

------------------------------------------------------------------------

## 1.2.20 Annotations

1.  What is an annotation?
2.  Why are annotations useful?
3.  Explain `@Override`, `@Deprecated`, and `@SuppressWarnings`.
4.  What is an annotation target?
5.  What is retention?
6.  Source vs class vs runtime retention?
7.  Why would runtime retention be required?
8.  How can annotations interact with reflection?
9.  Implement a basic custom annotation.
10. Use `@Override` to catch a method-signature mistake.
11. Identify annotation overuse.
12. Decide when metadata belongs in an annotation versus ordinary
    code/configuration.

------------------------------------------------------------------------

## 1.2.21 Varargs

1.  What is varargs?
2.  What is its syntax?
3.  What is its effective representation inside the method?
4.  Can a varargs method receive zero arguments?
5.  Can it receive an array explicitly?
6.  What happens with `(String[]) null`?
7.  How can varargs overloads become ambiguous?
8.  What are generic varargs/heap-pollution concerns at the appropriate
    level?
9.  Implement a varargs utility.
10. Compare `sum(int...)` with `sum(int[])`.
11. Benchmark carefully rather than assuming allocation behavior.
12. Decide whether varargs is appropriate for a public API.

------------------------------------------------------------------------

## 1.2.22 Recursion

1.  What is recursion?
2.  What is the base case?
3.  What is the recursive case?
4.  Why does recursion consume stack frames?
5.  What causes `StackOverflowError`?
6.  What is tail recursion?
7.  Does Java guarantee tail-call optimization?
8.  Trace factorial recursively.
9.  Implement recursive tree traversal.
10. Compare recursive and iterative approaches.
11. Decide when recursion is appropriate in production.
12. Protect an API from deeply unbounded recursive input.

------------------------------------------------------------------------

## 1.2.23 Assertions

1.  What is an assertion?
2.  What does `assert condition` mean?
3.  What does `assert condition : message` mean?
4.  How are assertions enabled?
5.  What are `-ea` and `-da`?
6.  Are assertions enabled by default?
7.  Why are assertions not a substitute for production validation?
8.  Distinguish internal invariants from external input validation.
9.  Demonstrate enabled/disabled assertion behavior.
10. Refactor mandatory validation incorrectly implemented with `assert`.

------------------------------------------------------------------------

## 1.2.24 Module 1.2 Integrated Assessment

1.  Build the Order Processing CLI.
2.  Use classes, fields, constructors and methods.
3.  Use enums, arrays, loops, conditions and switch.
4.  Use static and final deliberately.
5.  Use packages and access modifiers.
6.  Add validation and custom exceptions.
7.  Add varargs and basic annotations.
8.  Introduce deliberate null, casting, initialization and pass-by-value
    bugs.
9.  Diagnose every bug.
10. Explain performance and production trade-offs.
11. Refactor the application without over-engineering.

------------------------------------------------------------------------

# MODULE 1.3 --- OBJECT-ORIENTED PROGRAMMING

## 1.3.1 Classes and Objects

1.  What is a class?
2.  What is an object?
3.  Class vs object vs instance?
4.  State, behavior and identity?
5.  What is a reference?
6.  Why does Java use classes and objects?
7.  When is a class appropriate and when is it unnecessary abstraction?
8.  Explain object identity versus reference value.
9.  What happens conceptually when `new` is used?
10. Implement `Person` and `BankAccount`.
11. Explain object allocation and lifecycle.
12. Design a domain object with valid invariants.

## 1.3.2 Encapsulation

1.  What is encapsulation?
2.  Why is `private` alone not the whole concept?
3.  What is information hiding?
4.  Why should objects protect invariants?
5.  Why can unrestricted getters/setters still produce weak
    encapsulation?
6.  Design behavior-oriented APIs.
7.  Prevent mutable state leakage.
8.  Implement validation inside a domain object.
9.  Refactor a data-only class into a behavior-rich object.
10. Discuss encapsulation trade-offs in production.

## 1.3.3 Inheritance

1.  What is inheritance?
2.  What does `extends` mean?
3.  What is an IS-A relationship?
4.  Why does Java restrict a class to one superclass?
5.  What is inherited?
6.  What is not inherited?
7.  How does constructor execution work with inheritance?
8.  What are inheritance coupling risks?
9.  Implement an `Animal` hierarchy.
10. Identify an invalid inheritance relationship.
11. Refactor a deep inheritance tree using composition.
12. Decide when inheritance is appropriate.

## 1.3.4 Polymorphism

1.  What is polymorphism?
2.  What is compile-time polymorphism?
3.  What is runtime polymorphism?
4.  What is dynamic method dispatch?
5.  What is the difference between compile-time and runtime type?
6.  Predict overridden method dispatch.
7.  Explain why fields do not participate in dynamic dispatch in the
    same way methods do.
8.  Explain static method hiding.
9.  Implement runtime polymorphism with interfaces.
10. Debug unexpected dispatch.
11. Discuss performance without making unsupported microbenchmark
    claims.
12. Design a pluggable provider architecture.

## 1.3.5 Overloading vs Overriding

1.  What is overloading?
2.  What is overriding?
3.  When is overload resolution performed?
4.  When is overridden method selection performed?
5.  Can return type alone overload a method?
6.  What are valid overriding rules?
7.  What is a covariant return type?
8.  What happens with private/static/final methods?
9.  Predict mixed overload/override examples.
10. Inspect bytecode for invocation instructions.
11. Refactor confusing overload sets.
12. Explain this distinction in a senior interview.

## 1.3.6 Abstraction, Interfaces and Abstract Classes

1.  What is abstraction?
2.  What is an interface?
3.  What is an abstract class?
4.  Interface vs abstract class?
5.  What are default interface methods?
6.  Can an interface contain state?
7.  What is a functional interface at the appropriate level?
8.  When is an interface unnecessary?
9.  Design a payment interface.
10. Design an abstract base where shared state/behavior is genuinely
    justified.
11. Resolve default-method conflicts.
12. Explain abstraction as a design tool rather than a keyword.

## 1.3.7 Composition vs Inheritance / IS-A vs HAS-A

1.  What is composition?
2.  What is aggregation?
3.  What is HAS-A?
4.  Why is composition often more flexible?
5.  What coupling does inheritance introduce?
6.  When should inheritance be preferred?
7.  When should composition be preferred?
8.  Identify IS-A vs HAS-A relationships.
9.  Implement `Car` + `Engine` composition.
10. Refactor inheritance into composition.
11. Explain the trade-off in a production design review.

## 1.3.8 OOP Production Assessment

1.  Build a pluggable Payment Processing System.
2.  Use interfaces for replaceable providers.
3.  Use composition for external dependencies.
4.  Validate domain invariants.
5.  Prevent mutable state leakage.
6.  Make appropriate objects immutable.
7.  Add failure handling.
8.  Design for future payment providers.
9.  Analyze unnecessary inheritance.
10. Profile object allocation appropriately.
11. Explain every abstraction and relationship.

------------------------------------------------------------------------

# MODULE 1.4 --- OBJECT INITIALIZATION

## 1.4.1 `this`

1.  What is `this`?
2.  Why is `this.field` useful?
3.  What does `this.method()` mean?
4.  What does `this(...)` mean?
5.  Can `this` be used in static context?
6.  How is `this` related to the receiver object?
7.  Can `this` be passed to another method?
8.  Can `this` be returned?
9.  Implement fluent methods returning `this`.
10. Explain `this` conceptually at JVM invocation level.
11. Diagnose a `this`-escape problem during construction.

## 1.4.2 `super`

1.  What is `super`?
2.  What does `super.field` mean?
3.  What does `super.method()` mean?
4.  What does `super(...)` mean?
5.  Why is `super()` related to superclass construction?
6.  How does `super.method()` differ from normal virtual dispatch?
7.  Implement explicit parent constructor invocation.
8.  Debug a parent/child initialization problem.

## 1.4.3 Constructors

1.  What is a constructor?
2.  How does its declaration differ from a method?
3.  What is constructor overloading?
4.  Can constructors be inherited?
5.  Can constructors be overridden?
6.  What is constructor chaining?
7.  Why should constructors establish invariants?
8.  What is the danger of too many overloaded constructors?
9.  Implement overloaded constructors.
10. Refactor duplicated constructor logic.

## 1.4.4 Initialization Blocks and Order

1.  What is a static initialization block?
2.  What is an instance initialization block?
3.  What executes first: static field, static block, instance field,
    instance block, constructor?
4.  How does inheritance change the order?
5.  Predict exact output for a parent/child hierarchy.
6.  Explain class initialization vs object initialization.
7.  Explain `<clinit>` and `<init>`.
8.  What happens when static initialization fails?
9.  How does initialization concurrency work conceptually?
10. Why can initialization order create production bugs?
11. Debug an initialization-order failure.
12. Design classes that minimize hidden initialization behavior.

------------------------------------------------------------------------

# MODULE 1.5 --- `java.lang.Object`

## 1.5.1 `Object`

1.  Why is `Object` the root superclass?
2.  Which important methods come from `Object`?
3.  What is object identity?
4.  What does `getClass()` provide?
5.  How do Object methods relate to equality, hashing and diagnostics?
6.  Why is `wait()`/`notify()` primarily a concurrency topic?
7.  Explain runtime class metadata.
8.  Implement a useful `toString()`.

## 1.5.2 `equals()`

1.  What is `equals()`?
2.  What is logical equality?
3.  Explain reflexivity.
4.  Explain symmetry.
5.  Explain transitivity.
6.  Explain consistency.
7.  What should happen with null?
8.  `==` vs `equals()`?
9.  Implement `equals()` correctly.
10. Test the equality contract.
11. Debug broken symmetry caused by inheritance.
12. Decide identity vs value equality for a domain object.

## 1.5.3 `hashCode()`

1.  What is `hashCode()`?
2.  What is its contract?
3.  If two objects are equal, what must be true about their hash codes?
4.  Does equal hash code imply equality?
5.  How do HashMap/HashSet conceptually use hashCode and equals?
6.  Why must equals and hashCode be implemented together?
7.  Implement both.
8.  Debug a collection failure caused by incorrect hashing.
9.  Why are mutable keys dangerous?
10. Design stable keys for production collections.

## 1.5.4 `toString()`

1.  What is `toString()` used for?
2.  Why is it valuable for diagnostics?
3.  What should a production `toString()` avoid exposing?
4.  Should passwords and tokens be included?
5.  Should huge collections be dumped?
6.  Implement a safe domain `toString()`.
7.  Debug a log containing sensitive information.
8.  Design useful diagnostic output without leaking secrets.

## 1.5.5 `getClass()`

1.  What does `getClass()` return?
2.  What is the runtime class?
3.  `getClass()` vs `instanceof`?
4.  How can runtime type information help debugging?
5.  How can proxy classes affect runtime type observations?
6.  Implement runtime-type inspection.
7.  Decide whether exact-class equality or `instanceof` is appropriate.

## 1.5.6 `clone()` / `Cloneable`

1.  What is `clone()`?
2.  What is `Cloneable`?
3.  Why is cloning historically awkward?
4.  What is shallow copying?
5.  What is deep copying?
6.  Why doesn't `Cloneable` itself define a clone operation?
7.  Implement a controlled copy operation.
8.  Compare cloning with constructors, factories and copy constructors.
9.  Decide whether cloning is appropriate in production.

## 1.5.7 `finalize()` History and Modern Resource Management

1.  What was finalization?
2.  Why was it deprecated/removed from normal modern use?
3.  What is object resurrection?
4.  Why is finalization unreliable?
5.  Why is GC not a resource-management mechanism?
6.  What should replace finalizer-based cleanup?
7.  When might `Cleaner` be justified?
8.  Migrate a legacy finalizer design to explicit resource management.

## 1.5.8 Object Contracts + Collections

1.  Explain the conceptual HashMap lookup flow: `hashCode()` → candidate
    bucket → `equals()`.
2.  Implement Employee equality/hashCode.
3.  Add two logically equal employees to a HashSet and predict the size.
4.  Break hashCode deliberately and diagnose the result.
5.  Mutate a key after insertion and explain lookup failure.
6.  Analyze equality under inheritance.
7.  Explain `instanceof` vs `getClass()` equality strategies.
8.  Explain why composition can simplify equality design.

------------------------------------------------------------------------

# MODULE 1.6 --- JAVA DESIGN PRINCIPLES

## 1.6.1 SOLID

1.  What does SOLID stand for?
2.  Why does SOLID exist?
3.  Is every SOLID principle mandatory in every class?
4.  How do coupling, cohesion, change and testability relate to SOLID?
5.  Identify which principle a bad design violates.
6.  Refactor without creating meaningless abstractions.
7.  Explain when deliberately not applying a principle is reasonable.

## 1.6.2 SRP

1.  What is Single Responsibility Principle?
2.  What is "one reason to change"?
3.  Why is SRP not "one method per class"?
4.  Identify multiple responsibilities in a God class.
5.  Refactor `InvoiceService` into cohesive components.
6.  How can over-fragmentation violate the spirit of SRP?
7.  How does SRP affect testing and regression risk?
8.  Apply SRP to a production service.

## 1.6.3 OCP

1.  What is Open/Closed Principle?
2.  What does open for extension mean?
3.  What does closed for modification mean?
4.  Why can interfaces and Strategy support OCP?
5.  Identify a switch-based design that violates OCP.
6.  Refactor payment providers using an abstraction.
7.  When can OCP lead to premature abstraction?
8.  Balance OCP against YAGNI.

## 1.6.4 LSP

1.  What is Liskov Substitution Principle?
2.  What does substitutability mean?
3.  How can inheritance violate LSP?
4.  Identify a subtype that strengthens preconditions.
5.  Identify a subtype that weakens postconditions.
6.  Explain the classic invalid inheritance examples conceptually.
7.  Refactor a broken hierarchy using composition.
8.  Give a production example of an LSP violation.
9.  Explain LSP without reducing it to "child must be usable as parent"
    alone.

## 1.6.5 ISP

1.  What is Interface Segregation Principle?
2.  Why are fat interfaces problematic?
3.  How can role-specific interfaces help?
4.  Identify an interface forcing clients to implement irrelevant
    methods.
5.  Split a large interface.
6.  When can excessive interface splitting become harmful?
7.  Design interfaces for a payment/notification system.

## 1.6.6 DIP

1.  What is Dependency Inversion Principle?
2.  High-level vs low-level modules?
3.  What does depending on abstractions mean?
4.  DIP vs dependency injection?
5.  Why is DIP useful for testing?
6.  Refactor direct database construction behind an abstraction.
7.  Explain dependency direction.
8.  Design a production service using constructor injection.

## 1.6.7 Composition over Inheritance

1.  What does the principle mean?
2.  Why can composition reduce coupling?
3.  When is inheritance still appropriate?
4.  Refactor a deep hierarchy.
5.  Explain runtime configurability benefits.
6.  Discuss memory/runtime implications without oversimplifying.
7.  Design a pluggable behavior using composition.

## 1.6.8 Coupling and Cohesion

1.  What is coupling?
2.  What is cohesion?
3.  Why do we generally want lower unnecessary coupling and higher
    meaningful cohesion?
4.  Identify tight coupling in code.
5.  Identify a class with weak cohesion.
6.  Refactor dependencies.
7.  Explain how package boundaries affect coupling.
8.  Discuss coupling as a production maintenance cost.

## 1.6.9 DRY / KISS / YAGNI

### DRY

1.  What does DRY mean?
2.  When is duplication harmful?
3.  When should similar code not be immediately abstracted?
4.  Explain accidental coupling caused by premature deduplication.

### KISS

5.  What does KISS mean?
6.  How can abstraction make a simple problem harder?
7.  Simplify an over-engineered design.

### YAGNI

8.  What does YAGNI mean?
9.  Why is speculative extensibility risky?
10. Balance YAGNI with OCP.
11. Decide whether an abstraction should be introduced now or later.

## 1.6.10 Law of Demeter

1.  What is the Law of Demeter?
2.  Why can long navigation chains indicate coupling?
3.  Identify a violation such as `a.getB().getC().getD()`.
4.  Refactor using behavior or a facade.
5.  Discuss cases where navigation is harmless versus architecturally
    dangerous.

## 1.6.11 Tell, Don't Ask

1.  What does Tell, Don't Ask mean?
2.  Why can pulling state out and making decisions elsewhere weaken
    encapsulation?
3.  Refactor a domain model where services inspect too much internal
    state.
4.  Distinguish appropriate queries from inappropriate externalized
    business logic.
5.  Apply the principle to an order/payment domain.

## 1.6.12 Design Mastery

1.  Refactor a 2,000-line God class.
2.  Identify all relevant SOLID violations.
3.  Avoid over-engineering.
4.  Introduce abstractions only where change pressure justifies them.
5.  Explain coupling/cohesion before and after.
6.  Write tests around the new boundaries.
7.  Defend the final design in a senior-level review.

------------------------------------------------------------------------

# MODULE 1.7 --- JAVA KEYWORDS & MODIFIERS

## 1.7.1 Access Modifiers

1.  Explain public/protected/package-private/private.
2.  Test all four across packages.
3.  Explain protected across package boundaries.
4.  Explain public under JPMS.
5.  Design API exposure deliberately.
6.  Identify overexposed implementation details.

## 1.7.2 `final`

1.  What does final mean for fields, methods and classes?
2.  Final reference vs immutable object?
3.  What are blank final fields?
4.  What is effectively final?
5.  How can final support immutable design?
6.  Explain final-field semantics at the appropriate JMM level.
7.  Implement and test final fields.
8.  Diagnose a false immutability assumption.

## 1.7.3 `static`

1.  What is static state/behavior?
2.  Static vs instance members?
3.  Can static methods be overridden?
4.  Explain static method hiding.
5.  Explain static initialization.
6.  Explain static nested classes.
7.  Diagnose mutable static state and test pollution.
8.  Design a safer alternative to global state.

## 1.7.4 `abstract` and `default`

1.  What is an abstract class?
2.  What is an abstract method?
3.  What is a default interface method?
4.  Why can't an abstract method be implemented as ordinary behavior in
    the same declaration?
5.  Which modifier combinations are illegal?
6.  How are default-method conflicts resolved?
7.  Decide interface vs abstract class.
8.  Design a clean polymorphic contract.

## 1.7.5 `synchronized`

1.  What does synchronized do?
2.  What is a monitor?
3.  What does synchronized method locking mean?
4.  What object is locked by an instance synchronized method?
5.  What object is locked by a static synchronized method?
6.  What is monitor ownership?
7.  What is happens-before with synchronized?
8.  What problems does synchronized solve?
9.  What does synchronized not solve automatically?
10. Diagnose a deadlock scenario.
11. Decide when higher-level concurrency utilities are preferable.

## 1.7.6 `volatile`

1.  What does volatile provide?
2.  What is visibility?
3.  What ordering guarantees are relevant?
4.  Why doesn't volatile make `count++` atomic?
5.  Volatile vs synchronized?
6.  Explain happens-before through volatile.
7.  Implement a safe visibility example.
8.  Diagnose a visibility bug.
9.  Decide whether volatile is appropriate for a production
    flag/counter.

## 1.7.7 `transient`

1.  What does transient mean?
2.  How does it affect normal Java serialization?
3.  Why are sensitive/runtime-only fields candidates for transient?
4.  What value does a transient field receive after normal
    deserialization?
5.  What is the security risk of serialization?
6.  Implement a serializable object with transient state.
7.  Design serialization boundaries carefully.

## 1.7.8 `native`

1.  What does native mean?
2.  What is JNI?
3.  Why would Java call native code?
4.  What are native-boundary risks?
5.  What performance costs can crossing the boundary introduce?
6.  How would you diagnose a native crash?
7.  When should native code be avoided?
8.  Design a safe boundary around a native dependency.

## 1.7.9 `strictfp`

1.  What did strictfp historically control?
2.  Why is it largely obsolete in modern Java?
3.  What floating-point portability problem did it address?
4.  Why should you understand its history even when rarely writing it?
5.  What production code should replace reliance on obsolete
    assumptions?

## 1.7.10 Sealed Types

1.  What is a sealed class/interface?
2.  What does `permits` do?
3.  What is `non-sealed`?
4.  What does `final` mean in a sealed hierarchy?
5.  What rules apply to direct subclasses?
6.  Explain a closed hierarchy.
7.  Implement a sealed payment hierarchy.
8.  Predict which subclass declarations compile.
9.  Explain how sealed types interact with pattern
    matching/exhaustiveness.
10. Decide sealed vs open inheritance in API design.

## 1.7.11 Modifier Interaction / Production

1.  Which modifier combinations are illegal?
2.  Why can't an abstract class be final?
3.  Why can't an abstract method be final?
4.  Why can't an abstract method be private?
5.  Why isn't static synchronized equivalent to instance synchronized?
6.  Why doesn't volatile replace atomic operations?
7.  Design a modifier strategy for a public Java library.
8.  Explain how modifiers communicate API and concurrency intent.

------------------------------------------------------------------------

# MODULE 1.8 --- OTHER JAVA LANGUAGE FEATURES

## 1.8.1 Nested Classes Overview

1.  What are nested classes?
2.  What are the four major nested-type categories?
3.  Static nested vs inner class?
4.  Local class vs anonymous class?
5.  Why group a type inside another type?
6.  What are memory/reference implications?

## 1.8.2 Static Nested Classes

1.  What is a static nested class?
2.  Does it require an outer instance?
3.  Does it implicitly hold an outer reference?
4.  Can it access private members of the enclosing class?
5.  Why is it useful for builders?
6.  Implement a static nested Builder.
7.  Explain why static nested classes can avoid accidental outer-object
    retention.

## 1.8.3 Inner Classes

1.  What is an inner member class?
2.  Does it require an enclosing object?
3.  How does it access outer instance state?
4.  What implicit relationship exists with the outer instance?
5.  How can that relationship affect memory retention?
6.  Implement an inner class.
7.  Diagnose an accidental long-lived outer reference.
8.  Decide static nested vs inner in production.

## 1.8.4 Local Classes

1.  What is a local class?
2.  Where can it be declared?
3.  What can it capture?
4.  What does effectively final mean for captured locals?
5.  When is a local class useful?
6.  Compare local classes with lambdas/anonymous classes.
7.  Implement a local class for a narrow algorithmic helper.

## 1.8.5 Anonymous Classes

1.  What is an anonymous class?
2.  When are anonymous classes useful?
3.  How do they differ from named classes?
4.  How do they differ from lambdas?
5.  Can anonymous classes retain outer references?
6.  Implement a callback using an anonymous class.
7.  Diagnose lifecycle/memory-retention problems.
8.  Decide whether a lambda, anonymous class or named class is clearer.

## 1.8.6 Enums

1.  What makes enum constants unique?
2.  Why is enum identity stable within the enum type?
3.  Why should ordinal not represent business meaning?
4.  How do EnumSet and EnumMap fit enum-heavy designs?
5.  Design an enum with explicit stable business codes.

## 1.8.7 Annotations

1.  What is an annotation?
2.  What are targets and retention?
3.  How do runtime annotations relate to reflection?
4.  What are built-in annotations?
5.  Implement a runtime `@Audited` annotation.
6.  Read it through reflection.
7.  Explain when runtime metadata is worth the cost/complexity.

## 1.8.8 Varargs

1.  What is the array relationship?
2.  What happens for zero, one and many arguments?
3.  What happens when a null array is explicitly passed?
4.  How can overloaded varargs become ambiguous?
5.  Are varargs necessarily allocation-free?
6.  Compare varargs and arrays in a benchmark.
7.  Explain generic varargs risks.

## 1.8.9 Pass-by-Value and Reference Semantics

1.  What is copied when a reference is passed?
2.  Why can mutation be observed by the caller?
3.  Why can't parameter reassignment replace the caller's reference?
4.  Reference copying vs object copying?
5.  Does assignment clone an object?
6.  Demonstrate all cases.
7.  Explain aliasing and defensive copying.
8.  Design an API that minimizes accidental mutation.

## 1.8.10 `==` vs `equals()`

1.  What does `==` mean for primitives?
2.  What does `==` mean for references?
3.  What does `equals()` represent?
4.  Why is `==` wrong for String content?
5.  Why can wrapper `==` produce surprising results?
6.  What is identity vs logical equality?
7.  What is `System.identityHashCode()`?
8.  Implement equality correctly.
9.  Diagnose a collection bug caused by equality.
10. Decide identity vs value semantics in a domain model.

## 1.8.11 Equality Contract

1.  Explain reflexive, symmetric, transitive, consistent and non-null
    behavior.
2.  Explain the equals/hashCode relationship.
3.  Why doesn't equal hashCode imply equality?
4.  Why are mutable equality fields dangerous?
5.  Why can inheritance complicate equality?
6.  Compare `instanceof` and `getClass()` approaches.
7.  Implement and test the contract.
8.  Diagnose broken symmetry/transitivity.
9.  Explain the production impact on HashMap, HashSet and
    ConcurrentHashMap.

## 1.8.12 Production Design

Build an Order domain containing:

1.  `OrderStatus` enum.
2.  Stable business status codes.
3.  Immutable `Order`.
4.  Static nested Builder.
5.  Meaningful equals/hashCode.
6.  Identity vs equality demonstrations.
7.  Runtime `@Audited` annotation.
8.  Reflection-based annotation reading.
9.  A varargs utility.
10. Pass-by-value demonstrations.
11. Memory-retention analysis.
12. Production-level API design justification.

------------------------------------------------------------------------

# MODULE 1.9 --- INTEGRATED JAVA FUNDAMENTALS EXERCISES

## 1.9.1 Immutable Value Object

### Basic

1.  What makes an object immutable?
2.  Why use final fields?
3.  Why initialize state in the constructor?
4.  Why avoid setters?

### Intermediate

5.  How do defensive copies protect immutability?
6.  What happens if an immutable object contains a mutable field?
7.  Why is final reference not the same as immutability?
8.  Implement immutable `Money` or `Employee`.

### Advanced

9.  Implement equals/hashCode.
10. Handle mutable collection fields.
11. Explain safe sharing across threads.
12. Explain object identity vs value equality.

### Production

13. Design an immutable domain value object for a concurrent service.
14. Explain allocation, sharing, GC and defensive-copy trade-offs.

------------------------------------------------------------------------

## 1.9.2 Inheritance and Runtime Polymorphism

1.  Implement a parent/child hierarchy.
2.  Add an overridden method.
3.  Demonstrate runtime dispatch.
4.  Demonstrate compile-time overloading.
5.  Demonstrate field hiding.
6.  Demonstrate static method hiding.
7.  Debug an unexpected dispatch result.
8.  Explain dynamic dispatch.
9.  Refactor a poor hierarchy.
10. Decide whether composition is better.

------------------------------------------------------------------------

## 1.9.3 Constructor Chaining

1.  Implement overloaded constructors.
2.  Use `this()`.
3.  Use `super()`.
4.  Centralize initialization.
5.  Predict exact initialization output.
6.  Demonstrate parent-to-child ordering.
7.  Add static and instance blocks.
8.  Inspect generated bytecode.
9.  Diagnose constructor dispatch hazards.

------------------------------------------------------------------------

## 1.9.4 SOLID Implementation

### SRP

1.  Implement a God-class refactoring.
2.  Identify separate reasons to change.

### OCP

3.  Implement extensible payment providers.
4.  Add a provider without modifying core processing logic.

### LSP

5.  Create a valid subtype hierarchy.
6.  Create and repair an invalid substitution relationship.

### ISP

7.  Split a fat interface.
8.  Design client-specific interfaces.

### DIP

9.  Replace concrete dependencies with abstractions.
10. Inject dependencies through constructors.

### Cross-principle

11. Explain why the final design is cohesive.
12. Identify where applying another abstraction would be unnecessary.
13. Balance SOLID against KISS and YAGNI.

------------------------------------------------------------------------

## 1.9.5 Pass-by-Value Debugging

1.  Debug primitive reassignment.
2.  Debug object mutation.
3.  Debug reference reassignment.
4.  Draw the reference/value model.
5.  Explain aliasing.
6.  Refactor to immutable state where appropriate.
7.  Explain defensive-copy decisions.

------------------------------------------------------------------------

## 1.9.6 Performance Exercises

1.  Compare mutable and immutable objects.
2.  Discuss allocation.
3.  Discuss GC.
4.  Discuss thread safety.
5.  Benchmark varargs vs array carefully.
6.  Investigate interface/virtual/final/static calls.
7.  Use JMH or a profiler where appropriate.
8.  Do not infer production performance from one naive `nanoTime()`
    test.
9.  Explain escape analysis and inlining at the appropriate level.

------------------------------------------------------------------------

## 1.9.7 Interview Challenge

### Basic

1.  What makes a class immutable?
2.  What is runtime polymorphism?
3.  What is constructor chaining?
4.  What is SRP?
5.  What is pass-by-value?

### Intermediate

6.  Why does object mutation survive a method call?
7.  Why does reference reassignment not affect the caller?
8.  Why should equals and hashCode be implemented together?
9.  Composition vs inheritance?
10. What is OCP?

### Advanced

11. Explain dynamic dispatch.
12. Explain initialization order.
13. Explain LSP with a production example.
14. Explain DIP vs DI.
15. Explain immutable objects and concurrency.
16. Explain mutable-key HashMap failures.
17. Explain overload vs override.

### Senior / Production

18. Refactor a God class without over-engineering.
19. Design a payment abstraction supporting future providers.
20. Explain when not to use inheritance.
21. Explain when not to apply DRY.
22. Balance OCP against YAGNI.
23. Diagnose an equality bug in production.
24. Design immutable domain objects for concurrent services.
25. Explain reference-driven memory retention.

------------------------------------------------------------------------

# FINAL 9-MODULE MASTER ASSESSMENT

## Stage 1 --- Fundamentals

For each module, answer without notes:

-   What is it?
-   Why does it exist?
-   What problem does it solve?
-   What are its major rules?
-   What are its major APIs/keywords?

## Stage 2 --- Implementation

For each module:

-   Write a working example from memory.
-   Modify an existing implementation.
-   Add validation.
-   Add tests.
-   Refactor the design.

## Stage 3 --- Internals

Be able to explain, where relevant:

-   Compiler behavior.
-   Bytecode.
-   JVM execution.
-   Class loading.
-   Initialization.
-   Dispatch.
-   Object/reference behavior.
-   Memory/runtime implications.
-   JMM/concurrency mechanics for modifier-related topics.

## Stage 4 --- Traps

For every topic, be able to:

-   Predict output.
-   Identify compile-time failure.
-   Identify runtime failure.
-   Explain why the behavior occurs.
-   Distinguish common myths from actual Java rules.

## Stage 5 --- Debugging

For each major area, diagnose deliberately broken examples involving:

-   Null.
-   Casting.
-   Equality.
-   Hashing.
-   Initialization.
-   Constructor chaining.
-   Dispatch.
-   Access control.
-   Static state.
-   Varargs.
-   Reference mutation.
-   Memory retention.
-   Concurrency modifiers.

## Stage 6 --- Performance

Be able to discuss:

-   Allocation.
-   GC pressure.
-   Boxing.
-   String construction.
-   Varargs.
-   Dispatch.
-   Static state.
-   Object sharing.
-   Escape analysis.
-   Inlining.
-   Locking/visibility where relevant.

**Do not make performance claims without measurement when measurement is
appropriate.**

## Stage 7 --- Production Design

For every major concept, answer:

> **Would I use this in production? When? Why? What alternative would I
> consider? What failure mode does it introduce?**

Examples:

-   Interface vs abstract class vs composition.
-   Static state vs injected dependency.
-   Array vs collection.
-   Primitive vs wrapper.
-   `==` vs `equals()`.
-   `final` vs actual immutability.
-   Inheritance vs composition.
-   Traditional switch vs polymorphism.
-   Recursion vs iteration.
-   Assertion vs validation.
-   Classpath vs module path.
-   Full JDK vs custom runtime.

## Stage 8 --- Senior Interview

For every topic, be able to move from:

> **definition → example → internal mechanism → edge case → trap →
> debugging → performance → production trade-off**

without relying on memorized one-line answers.

------------------------------------------------------------------------

# MASTER COMPLETION CHECKLIST

## Module 1.1

-   [ ] JDK/JRE/JVM
-   [ ] Compilation
-   [ ] `javac`
-   [ ] Bytecode
-   [ ] `.class`
-   [ ] JVM lifecycle
-   [ ] Classpath
-   [ ] Module path
-   [ ] Launcher
-   [ ] Versioning/LTS
-   [ ] JVM diagnostics
-   [ ] Production runtime design

## Module 1.2

-   [ ] Variables
-   [ ] Primitives
-   [ ] Literals
-   [ ] Operators
-   [ ] Conversion/casting
-   [ ] Control flow
-   [ ] Switch
-   [ ] Methods
-   [ ] Pass-by-value
-   [ ] Scope/lifetime
-   [ ] Arrays
-   [ ] Strings
-   [ ] Null
-   [ ] Final
-   [ ] Static
-   [ ] Initialization
-   [ ] This/super
-   [ ] Packages/imports
-   [ ] Access modifiers
-   [ ] Enums
-   [ ] Annotations
-   [ ] Varargs
-   [ ] Recursion
-   [ ] Assertions

## Module 1.3

-   [ ] Classes/objects
-   [ ] Encapsulation
-   [ ] Inheritance
-   [ ] Polymorphism
-   [ ] Overloading
-   [ ] Overriding
-   [ ] Dynamic dispatch
-   [ ] Abstraction
-   [ ] Interfaces
-   [ ] Abstract classes
-   [ ] Composition
-   [ ] IS-A/HAS-A
-   [ ] Production OOP design

## Module 1.4

-   [ ] `this`
-   [ ] `super`
-   [ ] Constructors
-   [ ] Constructor overloading
-   [ ] Constructor chaining
-   [ ] Static initialization
-   [ ] Instance initialization
-   [ ] Initialization order
-   [ ] JVM initialization internals
-   [ ] Initialization traps

## Module 1.5

-   [ ] Object
-   [ ] equals
-   [ ] hashCode
-   [ ] toString
-   [ ] getClass
-   [ ] clone
-   [ ] finalize history
-   [ ] Equality contracts
-   [ ] Mutable-key failures
-   [ ] Collection interactions

## Module 1.6

-   [ ] SOLID
-   [ ] SRP
-   [ ] OCP
-   [ ] LSP
-   [ ] ISP
-   [ ] DIP
-   [ ] Composition over inheritance
-   [ ] Coupling
-   [ ] Cohesion
-   [ ] DRY
-   [ ] KISS
-   [ ] YAGNI
-   [ ] Law of Demeter
-   [ ] Tell Don't Ask
-   [ ] Refactoring
-   [ ] Production architecture

## Module 1.7

-   [ ] public
-   [ ] protected
-   [ ] package-private
-   [ ] private
-   [ ] final
-   [ ] static
-   [ ] abstract
-   [ ] default
-   [ ] synchronized
-   [ ] volatile
-   [ ] transient
-   [ ] native
-   [ ] strictfp
-   [ ] sealed
-   [ ] non-sealed
-   [ ] permits
-   [ ] Modifier interactions
-   [ ] JMM implications
-   [ ] Production decisions

## Module 1.8

-   [ ] Nested classes
-   [ ] Static nested classes
-   [ ] Inner classes
-   [ ] Local classes
-   [ ] Anonymous classes
-   [ ] Enums
-   [ ] Annotations
-   [ ] Varargs
-   [ ] Pass-by-value
-   [ ] Reference semantics
-   [ ] == vs equals
-   [ ] Equality contract
-   [ ] Identity
-   [ ] Memory retention
-   [ ] Production design

## Module 1.9

-   [ ] Immutable value object
-   [ ] Inheritance
-   [ ] Runtime polymorphism
-   [ ] Constructor chaining
-   [ ] SOLID implementations
-   [ ] Pass-by-value debugging
-   [ ] Performance experiments
-   [ ] Production-style projects
-   [ ] Senior interview challenges

------------------------------------------------------------------------

# DEEP MASTERY DEFINITION

Do **not** mark a topic complete merely because you can answer:

> "What is it?"

Mark it complete only when you can:

> **Explain → Implement → Explain internals → Predict behavior → Handle
> edge cases → Recognize traps → Debug failures → Discuss performance →
> Make production/design decisions → Defend those decisions in a senior
> interview.**

## Final status

-   [ ] NOT STARTED
-   [ ] LEARNING
-   [ ] IMPLEMENTED
-   [ ] INTERNALS UNDERSTOOD
-   [ ] EDGE CASES MASTERED
-   [ ] PRODUCTION READY
-   [ ] INTERVIEW READY
-   [ ] DEEP MASTERY COMPLETE
