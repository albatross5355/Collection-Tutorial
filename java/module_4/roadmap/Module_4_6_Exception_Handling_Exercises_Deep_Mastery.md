# Module 4.6 — Exception Handling Exercises Deep Mastery

> **Goal:** Convert exception-handling knowledge into implementation, debugging, refactoring, and production-level design ability.

---

# Mastery Cycle

For every exercise, work through:

1. What is it?
2. Why does Java have it?
3. Syntax and API
4. Basic example
5. Internal working
6. Memory / runtime behavior
7. Edge cases
8. Common mistakes
9. Performance implications
10. Production use cases
11. Interview questions
12. Coding exercises
13. Advanced follow-ups

## Completion Standard

Mark an exercise complete only when you can:

- [ ] Explain it
- [ ] Implement it
- [ ] Explain its internals
- [ ] Handle edge cases
- [ ] Discuss trade-offs
- [ ] Debug it
- [ ] Use it in a production scenario

---

# 4.6.1 Build a Custom Exception Hierarchy

## 1. What is it?

A custom exception hierarchy is a structured set of application-specific exception classes representing related failure categories.

Example:

```text
ApplicationException
├── CustomerException
│   ├── CustomerNotFoundException
│   └── DuplicateCustomerException
├── PaymentException
│   ├── PaymentDeclinedException
│   └── PaymentTimeoutException
└── InventoryException
    └── InventoryUnavailableException
```

## 2. Why does Java have it?

Java's inheritance model allows related exception types to share common handling while preserving specific failure information.

It solves:

```text
generic error handling
+
specific error handling
+
domain-level abstraction
```

## 3. Syntax and API

```java
class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

Subclass:

```java
class CustomerNotFoundException extends ApplicationException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
```

## 4. Basic Example

```java
class PaymentException extends RuntimeException {
    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}

class PaymentTimeoutException extends PaymentException {
    public PaymentTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

## 5. Internal Working

Exception matching uses the runtime type of the thrown object.

```java
catch (PaymentException e)
```

can catch:

```text
PaymentException
PaymentTimeoutException
PaymentDeclinedException
```

because subclasses are instances of their parent type.

## 6. Memory / Runtime Behavior

Every thrown exception is an object. Depending on the implementation and runtime path, exception creation may involve:

```text
heap allocation
+
stack-trace capture
+
message storage
+
cause reference
```

The hierarchy itself does not fundamentally change exception dispatch.

## 7. Edge Cases

- [ ] Catch parent before child.
- [ ] Catching parent can make a later child catch unreachable.
- [ ] Preserve causes.
- [ ] Avoid excessively deep hierarchies.
- [ ] Decide checked vs unchecked at the architecture level.
- [ ] Avoid exposing infrastructure exceptions unnecessarily.

## 8. Common Mistakes

- [ ] One generic `BusinessException` for everything.
- [ ] Dozens of meaningless subclasses.
- [ ] Losing the original cause.
- [ ] Coupling domain exceptions directly to HTTP.
- [ ] Catching a superclass too early.

## 9. Performance Implications

Exception construction and stack-trace capture can be more expensive than normal object creation. The main goal is semantic clarity, not micro-optimizing the hierarchy.

## 10. Production Use Cases

Useful for:

```text
domain failures
+
repository abstraction
+
external service failures
+
API error mapping
+
retry classification
```

## 11. Interview Questions

**Basic**
- How do you create a custom exception?
- Why use an exception hierarchy?

**Intermediate**
- Why can a parent catch block catch child exceptions?
- Why must specific catch blocks precede general ones?

**Advanced**
- How would you design a payment exception hierarchy?
- Checked or unchecked: how would you decide?

**Senior / Production**
- How should exception hierarchies map to API error contracts?
- How would you classify retryable failures?

## 12. Coding Exercises

**Basic**
- [ ] Create `ApplicationException`.
- [ ] Create two subclasses.

**Intermediate**
- [ ] Build Customer and Payment exception branches.

**Advanced**
- [ ] Add causes and error codes.

**Production-style**
- [ ] Design an exception hierarchy for an e-commerce service.

## 13. Advanced Follow-ups

- [ ] Throwable inheritance.
- [ ] Checked exception rules.
- [ ] Cause chains.
- [ ] Suppressed exceptions.
- [ ] API error mapping.
- [ ] Retry classification.
- [ ] Spring exception translation.

---

# 4.6.2 Implement Resource-Safe Code

## 1. What is it?

Resource-safe code guarantees that resources such as:

```text
files
+
streams
+
sockets
+
JDBC connections
```

are released correctly, including when failures occur.

## 2. Why does Java have it?

External resources are not safely managed by ordinary garbage collection alone.

Java provides:

```text
AutoCloseable
+
Closeable
+
try-with-resources
```

for deterministic cleanup.

## 3. Syntax and API

```java
try (BufferedReader reader =
         Files.newBufferedReader(path)) {

    process(reader);

}
```

Multiple resources:

```java
try (
    Connection connection = dataSource.getConnection();
    PreparedStatement ps = connection.prepareStatement(sql);
    ResultSet rs = ps.executeQuery()
) {
    ...
}
```

## 4. Basic Example

```java
try (BufferedReader reader =
         Files.newBufferedReader(Path.of("data.txt"))) {

    String line;

    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}
```

## 5. Internal Working

Resources close automatically when the try block completes.

Multiple resources close in reverse declaration order:

```text
ResultSet
    ↓
PreparedStatement
    ↓
Connection
```

If body and cleanup failures occur, try-with-resources preserves the primary exception and records cleanup failures as suppressed exceptions.

## 6. Memory / Runtime Behavior

GC manages Java object memory, but resource release is a separate concern.

```text
Java object
    ↓
external resource
```

Examples:

```text
InputStream → file descriptor
Connection → pooled/database resource
Socket → network resource
```

## 7. Edge Cases

- [ ] Resource acquisition fails.
- [ ] Partial acquisition occurs.
- [ ] Body throws.
- [ ] Close throws.
- [ ] Multiple closes throw.
- [ ] Body and close both throw.
- [ ] Connection pooling changes physical-close semantics.
- [ ] Custom resource has special close behavior.

## 8. Common Mistakes

- [ ] Relying on GC for cleanup.
- [ ] Forgetting a resource.
- [ ] Incorrect manual finally cleanup.
- [ ] Ignoring suppressed exceptions.
- [ ] Using resources after close.
- [ ] Misunderstanding pooled `Connection.close()`.

## 9. Performance Implications

Resource leaks can cause:

```text
file descriptor exhaustion
+
connection pool exhaustion
+
socket exhaustion
+
memory/resource pressure
```

Cleanup itself may involve expensive I/O or network operations.

## 10. Production Use Cases

Use try-with-resources in:

```text
repositories
+
file processors
+
network clients
+
batch jobs
+
data import/export
```

## 11. Interview Questions

**Basic**
- What is try-with-resources?
- What is AutoCloseable?

**Intermediate**
- What is the resource closing order?
- What is a suppressed exception?

**Advanced**
- What happens when resource acquisition partially fails?
- How does try-with-resources differ from finally?

**Senior / Production**
- How would you diagnose connection-pool exhaustion?
- How would you design a resource-safe repository?

## 12. Coding Exercises

**Basic**
- [ ] Create and close a custom AutoCloseable.

**Intermediate**
- [ ] Manage three dependent resources.

**Advanced**
- [ ] Simulate body and close failures.

**Production-style**
- [ ] Build a resource-safe JDBC repository.

## 13. Advanced Follow-ups

- [ ] JLS try-with-resources semantics.
- [ ] Suppressed exceptions.
- [ ] Bytecode generated by javac.
- [ ] JDBC lifecycle.
- [ ] Connection pooling.
- [ ] File descriptor management.

---

# 4.6.3 Debug Exception Chaining

## 1. What is it?

Exception chaining preserves the relationship between a high-level exception and the lower-level cause that triggered it.

```text
ServiceException
      ↓ cause
RepositoryException
      ↓ cause
SQLException
```

## 2. Why does Java have it?

It allows abstraction boundaries without losing diagnostic information.

The upper layer can expose:

```text
meaningful abstraction
```

while retaining:

```text
root cause
```

## 3. Syntax and API

```java
throw new ServiceException(
    "Unable to process order",
    cause
);
```

Inspect:

```java
e.getCause()
```

## 4. Basic Example

```java
try {
    repository.save(order);
} catch (SQLException e) {
    throw new OrderPersistenceException(
        "Unable to save order",
        e
    );
}
```

## 5. Internal Working

Each exception can contain a reference to another Throwable as its cause.

```text
exception
   ↓
cause
   ↓
cause
   ↓
root cause
```

The stack trace can display the chain for diagnostics.

## 6. Memory / Runtime Behavior

Cause references keep referenced exceptions reachable while the outer exception remains reachable.

A deep chain can therefore retain multiple Throwable objects and their diagnostic data.

## 7. Edge Cases

- [ ] Null cause.
- [ ] Cause chain several levels deep.
- [ ] Self-causation restrictions.
- [ ] Suppressed exceptions in addition to causes.
- [ ] Cause preserved but message changed.
- [ ] Re-wrapping the same exception repeatedly.

## 8. Common Mistakes

- [ ] Dropping the original cause.
- [ ] Logging only the outer message.
- [ ] Wrapping at every layer.
- [ ] Creating meaningless exception chains.
- [ ] Confusing cause with suppressed exception.

## 9. Performance Implications

Long chains increase diagnostic object retention and logging output. Exception construction and stack traces can also have measurable cost.

## 10. Production Use Cases

Exception chaining is valuable across:

```text
database
→ repository
→ service
→ API
```

and:

```text
HTTP client
→ integration layer
→ domain service
```

## 11. Interview Questions

**Basic**
- What is exception chaining?
- What does `getCause()` return?

**Intermediate**
- Why preserve the cause?
- Difference between cause and suppressed exception?

**Advanced**
- How do you translate `SQLException` without losing diagnostics?
- How can over-wrapping hurt debugging?

**Senior / Production**
- Where should exception translation happen in layered architecture?
- How do you preserve diagnostic information across service boundaries?

## 12. Coding Exercises

**Basic**
- [ ] Create an exception with a cause.

**Intermediate**
- [ ] Build a three-level cause chain.

**Advanced**
- [ ] Debug a deliberately broken chain.

**Production-style**
- [ ] Implement repository-to-service exception translation.

## 13. Advanced Follow-ups

- [ ] `Throwable.initCause()`.
- [ ] Cause constructors.
- [ ] Suppressed exceptions.
- [ ] Stack traces.
- [ ] Exception translation.
- [ ] Structured logging.

---

# 4.6.4 Refactor Bad Exception Handling

## 1. What is it?

Refactoring bad exception handling means identifying unsafe patterns and replacing them with clear, recoverable, observable, and abstraction-appropriate handling.

Target problems:

```text
empty catches
+
broad catches
+
lost causes
+
bad finally blocks
+
exception-driven control flow
+
duplicate logging
```

## 2. Why does Java have it?

Java gives developers flexible exception mechanisms, but flexibility can produce unreliable code if failure semantics are not deliberately designed.

## 3. Syntax and API

Bad:

```java
try {
    process();
} catch (Exception e) {
}
```

Better:

```java
try {
    process();
} catch (SpecificException e) {
    throw new ProcessingException(
        "Processing failed",
        e
    );
}
```

## 4. Basic Example

### Bad

```java
try {
    save();
} catch (Exception e) {
    System.out.println("error");
}
```

Problems:

```text
broad catch
+
poor logging
+
possible swallowed failure
+
no recovery
+
no cause translation
```

### Refactored

```java
try {
    save();
} catch (SQLException e) {
    throw new PersistenceException(
        "Unable to save entity",
        e
    );
}
```

## 5. Internal Working

Analyze each handler according to:

```text
What can be thrown?
        ↓
What can this layer recover from?
        ↓
What should be propagated?
        ↓
What should be translated?
        ↓
What should be logged?
```

## 6. Memory / Runtime Behavior

Poor handling can cause:

```text
repeated exception creation
+
large log volumes
+
retained diagnostic objects
+
unnecessary retries
```

Swallowed failures may instead allow corrupted or incomplete application state to continue.

## 7. Edge Cases

- [ ] `InterruptedException`.
- [ ] `Error` vs `Exception`.
- [ ] Retryable vs non-retryable failures.
- [ ] Cleanup failures.
- [ ] Partial database operations.
- [ ] Transaction rollback.
- [ ] Async exception propagation.
- [ ] Duplicate logging.
- [ ] Sensitive exception data.

## 8. Common Mistakes

- [ ] `catch (Exception e) {}`.
- [ ] `catch (Throwable t)`.
- [ ] `return null` after failure.
- [ ] Logging and continuing without recovery.
- [ ] Throwing from `finally` and losing the primary exception.
- [ ] Logging the same exception at every layer.
- [ ] Replacing the cause with only a message.
- [ ] Catching exceptions the layer cannot handle.
- [ ] Treating every exception as retryable.

## 9. Performance Implications

Poor exception handling can produce:

```text
logging storms
+
retry storms
+
high allocation
+
GC pressure
+
increased latency
+
resource exhaustion
```

Optimize based on measured production behavior.

## 10. Production Use Cases

Refactor exception handling in:

```text
REST controllers
+
service layers
+
repositories
+
message consumers
+
scheduled jobs
+
external API integrations
```

## 11. Interview Questions

**Basic**
- Why should empty catch blocks be avoided?
- Why catch specific exceptions?

**Intermediate**
- What is exception translation?
- Why should causes be preserved?

**Advanced**
- How should exceptions be handled across layers?
- Why is catching `Throwable` usually dangerous?

**Senior / Production**
- How would you redesign exception handling in a microservice?
- How would you prevent retry storms and duplicate logging?
- How would you handle failures from downstream services?

## 12. Coding Exercises

**Basic**
- [ ] Refactor an empty catch block.

**Intermediate**
- [ ] Replace a broad catch with specific handlers.

**Advanced**
- [ ] Preserve causes and introduce domain exceptions.

**Production-style**
- [ ] Refactor a deliberately broken service containing:
  - [ ] swallowed exceptions
  - [ ] broad catches
  - [ ] lost causes
  - [ ] bad finally returns
  - [ ] exception-driven control flow
  - [ ] duplicate logging

## 13. Advanced Follow-ups

- [ ] Global exception handling.
- [ ] Spring exception translation.
- [ ] REST error contracts.
- [ ] Retry and circuit-breaker classification.
- [ ] Transaction rollback semantics.
- [ ] Async exception handling.
- [ ] Structured logging.
- [ ] Observability.
- [ ] Error budgets.
- [ ] Distributed failure handling.

---

# 4.6.5 Integrated Capstone Exercise

Build a small Java backend simulation containing:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database-like resource
```

Requirements:

- [ ] Create a custom exception hierarchy.
- [ ] Use checked and unchecked exceptions deliberately.
- [ ] Use try-with-resources.
- [ ] Implement a custom `AutoCloseable`.
- [ ] Preserve exception causes.
- [ ] Generate suppressed exceptions.
- [ ] Refactor broad catches.
- [ ] Remove swallowed exceptions.
- [ ] Avoid `return` from `finally`.
- [ ] Avoid exception-driven normal control flow.
- [ ] Add retryable/non-retryable classification.
- [ ] Produce safe diagnostic messages.
- [ ] Prevent sensitive information from being logged.
- [ ] Add unit tests for failure paths.
- [ ] Debug failures using stack traces and causes.

---

# 4.6.6 Final Mastery Gate

## Custom Exception Hierarchy

- [ ] Build a meaningful hierarchy.
- [ ] Choose checked vs unchecked deliberately.
- [ ] Preserve causes.
- [ ] Avoid unnecessary subclasses.

## Resource Safety

- [ ] Use try-with-resources.
- [ ] Understand closing order.
- [ ] Handle acquisition failures.
- [ ] Handle close failures.
- [ ] Understand suppressed exceptions.
- [ ] Prevent resource leaks.

## Exception Chaining

- [ ] Build cause chains.
- [ ] Preserve root causes.
- [ ] Distinguish cause from suppressed exception.
- [ ] Translate exceptions at proper abstraction boundaries.
- [ ] Debug nested exceptions.

## Exception Refactoring

- [ ] Remove swallowed exceptions.
- [ ] Replace overly broad catches.
- [ ] Remove dangerous `finally` returns.
- [ ] Preserve causes.
- [ ] Avoid duplicate logging.
- [ ] Avoid exceptions for normal control flow.
- [ ] Classify retryable failures.
- [ ] Protect sensitive diagnostic information.

## Production Readiness

- [ ] Design exception boundaries.
- [ ] Handle failures safely.
- [ ] Preserve observability.
- [ ] Prevent resource leaks.
- [ ] Avoid retry storms.
- [ ] Maintain stable external error contracts.
- [ ] Debug production-style failure scenarios.

---

# Final Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] DEBUGGED
- [ ] INTERNALS UNDERSTOOD
- [ ] CUSTOM EXCEPTION HIERARCHY MASTERED
- [ ] RESOURCE-SAFE CODE MASTERED
- [ ] EXCEPTION CHAINING MASTERED
- [ ] EXCEPTION REFACTORING MASTERED
- [ ] EDGE CASES MASTERED
- [ ] PERFORMANCE UNDERSTOOD
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
