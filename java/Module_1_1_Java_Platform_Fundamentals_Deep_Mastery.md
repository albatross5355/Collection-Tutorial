# Module 1 — Java Fundamentals & OOP
## 1.1 Java Platform Fundamentals — Deep Mastery Guide

## Mastery Cycle

For **every topic**, follow this cycle:

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

### Completion Standard

Mark a topic complete only when you can:

> **Explain it → Implement it → Explain its internals → Handle edge cases → Discuss trade-offs → Debug it → Use it in a production scenario**

---

# 1.1.1 JDK — Java Development Kit

## Mastery Checklist

- [ ] What is JDK?
- [ ] Why does Java have the JDK?
- [ ] JDK components
- [ ] Compiler
- [ ] Runtime
- [ ] Debugging tools
- [ ] Monitoring/diagnostic tools
- [ ] Documentation tools
- [ ] `javac`
- [ ] `java`
- [ ] `javadoc`
- [ ] `jar`
- [ ] `jdb`
- [ ] `jcmd`
- [ ] `jstack`
- [ ] `jmap`
- [ ] `jps`
- [ ] `jstat`
- [ ] `jfr`
- [ ] `jlink`
- [ ] `jpackage`
- [ ] JDK vs JRE vs JVM
- [ ] JDK installation and configuration
- [ ] `JAVA_HOME`
- [ ] `PATH`
- [ ] Multiple JDK versions on one machine

## Internal Understanding

- [ ] Understand what happens when `javac` is invoked.
- [ ] Understand what tools are bundled inside a modern JDK.
- [ ] Understand why a separate JRE distribution is no longer the central installation model in modern Java.
- [ ] Understand JDK/JVM vendor distributions.

## Performance Implications

- [ ] Compiler startup overhead
- [ ] JDK tool selection
- [ ] Development vs production runtime considerations

## Production Use Cases

- [ ] Selecting a JDK distribution
- [ ] Running Java applications in containers
- [ ] Managing multiple Java versions
- [ ] Using JVM diagnostic tools in production

## Interview Questions

- [ ] What is the difference between JDK, JRE and JVM?
- [ ] Does the JDK contain the JVM?
- [ ] What happens when you execute `javac`?
- [ ] Can a machine run Java without a JDK?
- [ ] Why is `JAVA_HOME` used?

## Coding / Hands-On Exercises

- [ ] Install two Java versions.
- [ ] Switch between them.
- [ ] Compile a Java program manually.
- [ ] Inspect the generated `.class`.
- [ ] Run JVM diagnostic commands against a running application.

## Advanced Follow-ups

- [ ] JDK modules
- [ ] `jlink`
- [ ] Custom runtime images
- [ ] JDK distributions
- [ ] OpenJDK
- [ ] JVM implementations

---

# 1.1.2 JRE — Java Runtime Environment

## Mastery Checklist

- [ ] What is JRE?
- [ ] Historical purpose of JRE
- [ ] JRE vs JDK
- [ ] Runtime components
- [ ] JVM relationship
- [ ] Why modern Java distributions changed the traditional JRE model
- [ ] Production runtime requirements

## Interview Questions

- [ ] Is JRE a JVM?
- [ ] Can JRE compile Java code?
- [ ] Does every JDK contain a JVM?
- [ ] Why don't modern Java installations necessarily provide a separate JRE package?

## Advanced Follow-ups

- [ ] Custom runtime images using `jlink`
- [ ] Minimal Java runtime environments
- [ ] Containerized Java runtimes

---

# 1.1.3 JVM — Java Virtual Machine

## Mastery Checklist

- [ ] What is the JVM?
- [ ] Why Java uses a virtual machine
- [ ] JVM specification vs JVM implementation
- [ ] JVM implementations
- [ ] JVM architecture
- [ ] Class loader subsystem
- [ ] Runtime data areas
- [ ] Execution engine
- [ ] Interpreter
- [ ] JIT compiler
- [ ] Garbage collector
- [ ] Native interface
- [ ] Native libraries

## Runtime Architecture

```text
Java Source
     ↓
javac
     ↓
Bytecode
     ↓
.class
     ↓
Class Loader
     ↓
Bytecode Verification
     ↓
Runtime Data Areas
     ↓
Interpreter / JIT
     ↓
Machine Code
     ↓
CPU
```

## Memory / Runtime Areas

- [ ] Heap
- [ ] Java stacks
- [ ] PC register
- [ ] Native method stack
- [ ] Metaspace
- [ ] Code cache
- [ ] Thread-local runtime structures

## Performance Implications

- [ ] Interpreter startup
- [ ] JIT compilation
- [ ] Warm-up
- [ ] Tiered compilation
- [ ] Garbage collection
- [ ] Allocation
- [ ] Escape analysis

## Production Use Cases

- [ ] JVM memory configuration
- [ ] GC selection
- [ ] JVM monitoring
- [ ] Thread dumps
- [ ] Heap dumps
- [ ] Java Flight Recorder (JFR)

## Interview Questions

- [ ] Is JVM platform-independent?
- [ ] Is JVM the same thing as Java?
- [ ] What happens inside JVM when a Java program starts?
- [ ] What is JIT?
- [ ] Why doesn't Java directly execute `.java` files?
- [ ] Where are objects stored?
- [ ] Where are local variables stored?
- [ ] What is Metaspace?

## Advanced Follow-ups

- [ ] JVM Specification
- [ ] Class loading
- [ ] Bytecode verification
- [ ] JIT
- [ ] C1/C2
- [ ] GC algorithms
- [ ] JVM tuning

---

# 1.1.4 Java Source Code Compilation

## Mastery Checklist

- [ ] `.java` source file
- [ ] Compilation process
- [ ] Lexical analysis
- [ ] Parsing
- [ ] Semantic analysis
- [ ] Type checking
- [ ] Symbol resolution
- [ ] Bytecode generation
- [ ] Compilation errors
- [ ] Annotation processing
- [ ] Compiler options

## Basic Example

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
```

Compile:

```bash
javac Hello.java
```

Run:

```bash
java Hello
```

## Internal Compilation Flow

```text
Hello.java
    ↓
javac
    ↓
Parsing
    ↓
Type checking
    ↓
Symbol resolution
    ↓
Bytecode generation
    ↓
Hello.class
```

## Coding / Hands-On

- [ ] Compile manually.
- [ ] Intentionally introduce syntax errors.
- [ ] Introduce type errors.
- [ ] Compare compiler diagnostics.
- [ ] Compile with `-d`.
- [ ] Compile with `-classpath`.
- [ ] Compile with `--module-path`.

## Interview Questions

- [ ] Is Java compiled or interpreted?
- [ ] Why is the answer not simply "compiled"?
- [ ] What does `javac` generate?
- [ ] Does `javac` generate machine code?
- [ ] Can bytecode execute directly on a CPU?

## Performance / Production

- [ ] Understand compiler startup overhead.
- [ ] Understand build-time vs runtime work.
- [ ] Understand how Maven/Gradle invoke Java compilation.
- [ ] Understand compiler configuration and generated bytecode compatibility.

---

# 1.1.5 `javac`

## Mastery Checklist

- [ ] Purpose
- [ ] Basic syntax
- [ ] Source paths
- [ ] Destination directory
- [ ] Classpath
- [ ] Module path
- [ ] Encoding
- [ ] Debug information
- [ ] Annotation processors
- [ ] Compiler warnings
- [ ] Incremental compilation concepts

## Important Commands

```bash
javac Hello.java
```

```bash
javac -d out src/com/example/Hello.java
```

```bash
javac -classpath lib/* -d out src/com/example/*.java
```

## Important Options

- [ ] `-d`
- [ ] `-cp` / `-classpath`
- [ ] `--module-path`
- [ ] `-source`
- [ ] `-target`
- [ ] `--release`
- [ ] `-parameters`
- [ ] `-g`
- [ ] `-Xlint`

## Edge Cases

- [ ] Wrong source version
- [ ] Wrong target version
- [ ] Missing dependency
- [ ] Duplicate classes
- [ ] Annotation processor failures
- [ ] Classpath/module-path mistakes

## Production / Build Relevance

- [ ] Understand how Maven/Gradle ultimately compile Java code.
- [ ] Understand how compiler flags affect generated bytecode.
- [ ] Understand source/target compatibility.

---

# 1.1.6 Bytecode

## Mastery Checklist

- [ ] What is bytecode?
- [ ] Why Java uses bytecode
- [ ] JVM instruction set
- [ ] Platform independence
- [ ] Bytecode verification
- [ ] `.class` format
- [ ] Constant pool
- [ ] Fields
- [ ] Methods
- [ ] Attributes
- [ ] Method bytecode
- [ ] Stack-based execution model

## Tools

- [ ] `javap`
- [ ] `javap -c`
- [ ] `javap -v`
- [ ] `javap -p`

## Hands-On Example

```java
int add(int a, int b) {
    return a + b;
}
```

Compile:

```bash
javac Example.java
```

Inspect:

```bash
javap -c Example
```

## Advanced Bytecode Topics

- [ ] JVM operand stack
- [ ] Local variable table
- [ ] Constant pool
- [ ] Method descriptors
- [ ] Bytecode instructions
- [ ] `invokestatic`
- [ ] `invokevirtual`
- [ ] `invokeinterface`
- [ ] `invokespecial`
- [ ] `invokedynamic`

## Interview Questions

- [ ] Why is Java bytecode stack-based?
- [ ] What is contained inside a `.class` file?
- [ ] What does the JVM execute?
- [ ] Can bytecode be inspected?
- [ ] Can different JVM implementations execute the same bytecode?

## Production / Performance

- [ ] Understand bytecode compatibility.
- [ ] Understand how bytecode feeds interpretation and JIT compilation.
- [ ] Understand why inspecting bytecode can help debug language/runtime behavior.

---

# 1.1.7 `.class` Files

## Mastery Checklist

- [ ] Class-file structure
- [ ] Magic number
- [ ] Version
- [ ] Constant pool
- [ ] Access flags
- [ ] Class information
- [ ] Superclass
- [ ] Interfaces
- [ ] Fields
- [ ] Methods
- [ ] Attributes

## Hands-On

- [ ] Generate `.class`.
- [ ] Inspect with `javap`.
- [ ] Compare class files generated by different Java releases.
- [ ] Understand class-file version compatibility.

## Edge Cases

- [ ] Running newer bytecode on an older JVM
- [ ] `UnsupportedClassVersionError`
- [ ] Duplicate classes
- [ ] Classpath conflicts
- [ ] Different versions of the same class

## Interview Questions

- [ ] What is the magic number of a Java class file?
- [ ] What is the class-file version?
- [ ] What is the constant pool?
- [ ] What happens when class-file versions are incompatible?

---

# 1.1.8 JVM Execution Lifecycle

## Complete Flow

```text
java Application
       ↓
Java Launcher
       ↓
Locate main class
       ↓
Class Loading
       ↓
Linking
       ├── Verification
       ├── Preparation
       └── Resolution
       ↓
Initialization
       ↓
main()
       ↓
Interpretation
       ↓
JIT Compilation
       ↓
Optimized Machine Code
       ↓
Garbage Collection / Runtime
       ↓
Application Exit
```

## Mastery Checklist

- [ ] Launching
- [ ] Class loading
- [ ] Linking
- [ ] Verification
- [ ] Preparation
- [ ] Resolution
- [ ] Initialization
- [ ] Method invocation
- [ ] JIT compilation
- [ ] Garbage collection
- [ ] Shutdown

## Interview Questions

- [ ] When is a class initialized?
- [ ] What is the difference between loading, linking and initialization?
- [ ] When does static initialization happen?
- [ ] When does JIT compilation happen?
- [ ] What happens when `main()` finishes?
- [ ] What causes JVM shutdown?
- [ ] What are shutdown hooks?

## Production / Debugging

- [ ] Understand startup failures.
- [ ] Understand class initialization failures.
- [ ] Understand JVM startup options.
- [ ] Understand graceful shutdown and shutdown hooks.

---

# 1.1.9 Classpath

## Mastery Checklist

- [ ] What is classpath?
- [ ] Why classpath exists
- [ ] Compile-time classpath
- [ ] Runtime classpath
- [ ] Directory entries
- [ ] JAR entries
- [ ] Wildcards
- [ ] Relative vs absolute paths
- [ ] `-cp`
- [ ] `CLASSPATH`
- [ ] Classpath ordering
- [ ] Class conflicts

## Internal Understanding

```text
Classpath
    ↓
Class Loader
    ↓
Search locations
    ↓
.class / JAR
    ↓
Load class
```

## Edge Cases

- [ ] Same class in two JARs
- [ ] Wrong JAR version
- [ ] `ClassNotFoundException`
- [ ] `NoClassDefFoundError`
- [ ] Dependency conflicts
- [ ] Classpath shadowing

## Production Use Cases

- [ ] Dependency conflicts
- [ ] Fat JARs
- [ ] Containerized applications
- [ ] Runtime classpath debugging

## Interview Questions

- [ ] What is the classpath?
- [ ] How does the JVM find a class?
- [ ] Compile-time vs runtime classpath?
- [ ] What happens if the same class exists in multiple JARs?
- [ ] Why can changing JAR order change application behavior?

---

# 1.1.10 Module Path

## Mastery Checklist

- [ ] Why JPMS was introduced
- [ ] Classpath vs module path
- [ ] Named modules
- [ ] `module-info.java`
- [ ] `requires`
- [ ] `exports`
- [ ] `opens`
- [ ] `uses`
- [ ] `provides`
- [ ] `requires transitive`
- [ ] Automatic modules
- [ ] Unnamed module
- [ ] Module readability

## Basic Example

```java
module com.example.app {
    requires java.sql;

    exports com.example.api;
}
```

## Internal Understanding

```text
Classpath
    ↓
Mostly package/JAR based

Module Path
    ↓
Module-aware resolution
    ↓
Explicit dependencies
    ↓
Strong encapsulation
```

## Edge Cases

- [ ] Split packages
- [ ] Automatic modules
- [ ] Unnamed module
- [ ] Illegal reflective access
- [ ] `exports` vs `opens`
- [ ] Module resolution failures

## Production Use Cases

- [ ] Strong encapsulation
- [ ] Smaller runtime images
- [ ] `jlink`
- [ ] Large enterprise applications
- [ ] Framework compatibility

## Interview Questions

- [ ] Classpath vs module path?
- [ ] What is JPMS?
- [ ] What does `requires` do?
- [ ] What does `exports` do?
- [ ] Difference between `exports` and `opens`?
- [ ] What is an automatic module?
- [ ] What is the unnamed module?

---

# 1.1.11 Java Launcher

## Mastery Checklist

- [ ] `java` command
- [ ] Main class
- [ ] Classpath
- [ ] Module execution
- [ ] JAR execution
- [ ] JVM options
- [ ] Application arguments
- [ ] System properties
- [ ] Environment variables

## Commands

```bash
java Hello
```

```bash
java -cp app.jar com.example.Main
```

```bash
java -jar application.jar
```

```bash
java --module-path mods -m com.example.app/com.example.Main
```

## JVM vs Application Arguments

```bash
java -Xmx512m -Denv=prod Main arg1 arg2
```

Understand:

```text
-Xmx512m
→ JVM option

-Denv=prod
→ JVM system property

arg1 arg2
→ Application arguments
```

## Production Use Cases

- [ ] JVM startup configuration
- [ ] Heap sizing
- [ ] GC configuration
- [ ] System properties
- [ ] Container runtime configuration
- [ ] Environment-specific configuration

## Interview Questions

- [ ] Difference between JVM options and application arguments?
- [ ] How do you pass a system property?
- [ ] How do you run an executable JAR?
- [ ] How do you run a modular application?

---

# 1.1.12 Java Versioning

## Mastery Checklist

- [ ] Java release cadence
- [ ] Feature releases
- [ ] LTS releases
- [ ] Java 8
- [ ] Java 11
- [ ] Java 17
- [ ] Java 21
- [ ] Java 25
- [ ] Preview features
- [ ] Incubator features
- [ ] Source compatibility
- [ ] Binary compatibility
- [ ] Runtime compatibility

## Critical Distinction

```text
Source compatibility
        ≠
Binary compatibility
        ≠
Runtime compatibility
```

## Interview Questions

- [ ] What is an LTS release?
- [ ] Why do enterprises prefer LTS versions?
- [ ] Can Java 21 run Java 17 bytecode?
- [ ] Can an older JVM run newer bytecode?
- [ ] What is a preview feature?
- [ ] What is an incubator feature?
- [ ] What happens when Java class-file versions are incompatible?

## Production Use Cases

- [ ] Selecting an LTS release
- [ ] Upgrade strategy
- [ ] Compatibility testing
- [ ] Dependency compatibility
- [ ] Container base images
- [ ] JVM vendor selection

## Advanced Follow-ups

- [ ] Java release compatibility
- [ ] LTS upgrade planning
- [ ] Preview feature lifecycle
- [ ] JEP lifecycle
- [ ] JVM vendor differences
- [ ] Runtime image compatibility

---

# 1.1.13 Integrated Hands-On Project

Build a tiny Java application **without Maven or Gradle**.

```text
project/
├── src/
│   └── com/example/
│       ├── Main.java
│       └── User.java
├── lib/
└── out/
```

## Exercises

- [ ] Compile with `javac`.
- [ ] Specify `-d out`.
- [ ] Run using `java`.
- [ ] Add an external JAR.
- [ ] Configure classpath.
- [ ] Inspect `.class` files with `javap`.
- [ ] Add a `module-info.java`.
- [ ] Run using the module path.
- [ ] Pass JVM options.
- [ ] Pass application arguments.
- [ ] Add a system property.
- [ ] Generate a thread dump.
- [ ] Inspect JVM information with `jcmd`.

## Production Simulation

- [ ] Create a classpath conflict.
- [ ] Diagnose `ClassNotFoundException`.
- [ ] Diagnose `NoClassDefFoundError`.
- [ ] Run incompatible bytecode and diagnose `UnsupportedClassVersionError`.
- [ ] Package the application as a JAR.
- [ ] Run it in a container.
- [ ] Inspect its JVM process.
- [ ] Capture a thread dump.
- [ ] Capture a JFR recording.

---

# 1.1.14 Final Interview Set

You should be able to answer these without memorization:

1. What exactly happens when you run `java MyApp`?
2. What exactly happens when you run `javac MyApp.java`?
3. What is bytecode?
4. Why doesn't Java compile directly to native machine code?
5. What is the difference between JDK, JRE and JVM?
6. What is contained in a `.class` file?
7. What happens during class loading?
8. What is linking?
9. What is class initialization?
10. What is the classpath?
11. What is the module path?
12. Classpath vs module path?
13. What is the difference between `ClassNotFoundException` and `NoClassDefFoundError`?
14. What does `javap` do?
15. What does the JIT compiler do?
16. When does JIT compilation happen?
17. What is an LTS release?
18. What is a preview feature?
19. What happens if a newer `.class` file runs on an older JVM?
20. What is the difference between source, binary and runtime compatibility?

---

# 1.1.15 Mastery Completion Checklist

Do not mark **Java Platform Fundamentals** complete until you can:

- [ ] Explain JDK/JRE/JVM without notes.
- [ ] Compile Java manually with `javac`.
- [ ] Run Java manually with `java`.
- [ ] Explain the complete source → bytecode → JVM pipeline.
- [ ] Inspect bytecode using `javap`.
- [ ] Explain `.class` structure at a high level.
- [ ] Explain JVM execution lifecycle.
- [ ] Debug classpath problems.
- [ ] Explain classpath vs module path.
- [ ] Build and run a basic JPMS application.
- [ ] Explain JVM vs application arguments.
- [ ] Explain Java version/LTS compatibility.
- [ ] Diagnose `ClassNotFoundException`.
- [ ] Diagnose `NoClassDefFoundError`.
- [ ] Diagnose `UnsupportedClassVersionError`.
- [ ] Explain JIT at a high level.
- [ ] Explain where JVM memory/runtime structures fit.
- [ ] Use basic JVM diagnostic tools.
- [ ] Discuss production implications of Java version selection.

## Mastery Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE

---

# Reusable Topic Mastery Template

Use this template for every future topic in the Java syllabus.

## Topic: __________________

### 1. What is it?

- [ ] Definition
- [ ] Purpose
- [ ] Core terminology

### 2. Why does Java have it?

- [ ] Problem it solves
- [ ] Design motivation
- [ ] Alternatives

### 3. Syntax and API

- [ ] Syntax
- [ ] Important APIs
- [ ] Important keywords
- [ ] Common methods

### 4. Basic Example

- [ ] Minimal implementation
- [ ] Run the example
- [ ] Modify the example

### 5. Internal Working

- [ ] JVM/compiler behavior
- [ ] Runtime flow
- [ ] Relevant implementation details
- [ ] OpenJDK source where useful

### 6. Memory / Runtime Behavior

- [ ] Heap behavior
- [ ] Stack behavior
- [ ] Object/reference behavior
- [ ] Class metadata
- [ ] GC implications
- [ ] Thread behavior

### 7. Edge Cases

- [ ] Boundary conditions
- [ ] Null behavior
- [ ] Exceptions
- [ ] Concurrency issues
- [ ] Version compatibility
- [ ] Resource limitations

### 8. Common Mistakes

- [ ] Misconceptions
- [ ] Incorrect API usage
- [ ] Performance mistakes
- [ ] Production mistakes

### 9. Performance Implications

- [ ] Time complexity
- [ ] Space complexity
- [ ] Allocation behavior
- [ ] CPU implications
- [ ] I/O implications
- [ ] Concurrency implications

### 10. Production Use Cases

- [ ] Where it is used
- [ ] When it should be used
- [ ] When it should not be used
- [ ] Production trade-offs

### 11. Interview Questions

#### Basic

- [ ] __________________
- [ ] __________________

#### Intermediate

- [ ] __________________
- [ ] __________________

#### Advanced

- [ ] __________________
- [ ] __________________

#### Senior / Production

- [ ] __________________
- [ ] __________________

### 12. Coding Exercises

#### Basic

- [ ] __________________

#### Intermediate

- [ ] __________________

#### Advanced

- [ ] __________________

#### Production-style

- [ ] __________________

### 13. Advanced Follow-ups

- [ ] Internal implementation
- [ ] JVM behavior
- [ ] Performance tuning
- [ ] Concurrency implications
- [ ] Production architecture
- [ ] Related Java APIs
- [ ] OpenJDK source
- [ ] JLS/JVM specification

### Final Mastery Gate

- [ ] Explain it
- [ ] Implement it
- [ ] Explain its internals
- [ ] Handle edge cases
- [ ] Discuss trade-offs
- [ ] Debug it
- [ ] Use it in a production scenario

### Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
