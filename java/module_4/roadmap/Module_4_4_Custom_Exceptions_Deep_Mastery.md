# Module 4.4 — Custom Exceptions Deep Mastery

> **Goal:** Master custom checked and unchecked exceptions, naming, messages, causes, wrapping, exception translation, propagation decisions, API contracts, debugging, performance, and production exception architecture.

---

# Mastery Cycle

For every topic:

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

# 4.4.1 Custom Exceptions

## What Is It?

A custom exception is an application-defined exception type that represents a meaningful failure category specific to a domain, API, library, or architectural layer.

Examples:

```text
InvalidOrderException
CustomerNotFoundException
PaymentProcessingException
ConfigurationException
InventoryUnavailableException
```

A custom exception should communicate a meaningful semantic category rather than merely rename an existing exception.

---

# 4.4.2 Why Create Custom Exceptions?

Custom exceptions are useful when callers need to distinguish a meaningful application-level failure.

They can provide:

```text
domain meaning
+
abstraction
+
structured handling
+
stable API contracts
+
clear logging/diagnostics
```

Example:

```text
SQLException
    ↓
CustomerRepositoryException
```

The service does not need to understand database-specific implementation details.

---

# 4.4.3 Custom Checked Exceptions

## Basic Syntax

```java
public class InvalidCustomerException extends Exception {

    public InvalidCustomerException(String message) {
        super(message);
    }
}
```

Usage:

```java
void validateCustomer(Customer customer)
        throws InvalidCustomerException {

    if (customer == null) {
        throw new InvalidCustomerException("Customer is required");
    }
}
```

Because it extends `Exception` directly, it is checked.

---

# 4.4.4 Custom Unchecked Exceptions

## Basic Syntax

```java
public class InvalidOrderException
        extends RuntimeException {

    public InvalidOrderException(String message) {
        super(message);
    }
}
```

Usage:

```java
if (order.getItems().isEmpty()) {
    throw new InvalidOrderException(
        "Order must contain at least one item"
    );
}
```

No catch-or-declare requirement exists.

---

# 4.4.5 Checked vs Unchecked Custom Exceptions

| Decision | Checked | Unchecked |
|---|---|---|
| Base type | `Exception` | `RuntimeException` |
| Compiler enforcement | Yes | No |
| Caller must acknowledge | Yes | No |
| Typical use | Explicit failure contract | Invalid state/arguments/domain contract |
| API verbosity | Higher | Lower |
| Propagation burden | Higher | Lower |

Do not choose checked merely because the failure "can happen."

Choose based on the API's semantic contract and whether forcing callers to acknowledge the failure is valuable.

---

# 4.4.6 When Should You Create a Custom Exception?

Good reason:

```text
caller needs to distinguish the condition
```

For example:

```java
catch (CustomerNotFoundException e) {
    return notFound();
}
```

Less useful:

```java
class MyNullPointerException extends RuntimeException {
}
```

if it adds no meaningful semantic value.

---

# 4.4.7 Exception Naming

Use clear names ending in:

```text
Exception
```

Examples:

```text
PaymentException
InvalidPaymentException
PaymentTimeoutException
CustomerNotFoundException
ConfigurationException
```

Avoid vague names:

```text
ProblemException
SomethingWentWrongException
GeneralFailureException
MyException
```

The name should communicate the failure category.

---

# 4.4.8 Naming by Domain Meaning

Prefer:

```text
InsufficientBalanceException
```

over:

```text
BusinessException
```

Prefer:

```text
DuplicateCustomerException
```

over:

```text
ValidationException
```

when callers need to distinguish the exact condition.

Avoid creating dozens of classes when a smaller hierarchy communicates the domain adequately.

---

# 4.4.9 Exception Hierarchy Design

Example:

```text
ApplicationException
│
├── CustomerException
│   ├── CustomerNotFoundException
│   └── DuplicateCustomerException
│
├── PaymentException
│   ├── PaymentDeclinedException
│   └── PaymentTimeoutException
│
└── InventoryException
    └── InventoryUnavailableException
```

A hierarchy is useful when you need:

```java
catch (PaymentException e) {
    ...
}
```

while retaining more specific subclasses.

Do not create hierarchies merely for organization.

---

# 4.4.10 Custom Exception Constructors

A production-friendly exception commonly supports:

```java
public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentException(Throwable cause) {
        super(cause);
    }
}
```

Understand:

```text
message
+
cause
+
stack trace
+
suppressed exceptions
```

---

# 4.4.11 Exception Messages

A good message should explain:

```text
what failed
+
relevant safe context
+
useful diagnostic information
```

Example:

```java
throw new PaymentException(
    "Payment processing failed for order " + orderId
);
```

Be careful about sensitive data.

Never include secrets such as:

```text
passwords
+
API keys
+
access tokens
+
private cryptographic material
```

in exception messages.

---

# 4.4.12 Good vs Bad Messages

Bad:

```text
"Error"
```

Bad:

```text
"Something went wrong"
```

Better:

```text
"Payment authorization failed for order ORD-123"
```

Better still when appropriate:

```text
"Payment authorization failed for order ORD-123: provider timeout"
```

Only include identifiers and provider details that are safe and useful.

---

# 4.4.13 Exception Messages Are for Diagnostics

Do not make business logic depend on:

```java
if (e.getMessage().equals("Customer not found")) {
    ...
}
```

Messages are not stable programmatic contracts.

Use:

```text
exception type
+
structured error code
+
typed fields
```

when programmatic decisions are required.

---

# 4.4.14 Structured Error Information

Instead of encoding everything into a message:

```java
class PaymentException extends RuntimeException {

    private final String errorCode;

    public PaymentException(
            String errorCode,
            String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
```

This separates:

```text
machine-readable information
```

from:

```text
human-readable diagnostics
```

---

# 4.4.15 Exception Wrapping

Wrapping means creating a higher-level exception while preserving the original exception as its cause.

Example:

```java
try {
    repository.save(customer);
} catch (SQLException e) {
    throw new CustomerPersistenceException(
        "Unable to save customer",
        e
    );
}
```

Structure:

```text
CustomerPersistenceException
          ↓
        cause
          ↓
      SQLException
```

---

# 4.4.16 Why Wrap Exceptions?

Wrapping is useful when the lower-level exception leaks implementation details.

Example:

```text
JDBC
 ↓
SQLException
 ↓
Repository
 ↓
CustomerRepositoryException
```

The repository abstraction can hide the database implementation.

---

# 4.4.17 Exception Translation

Exception translation means converting an exception from one abstraction layer into another appropriate abstraction.

Example:

```text
SQLException
      ↓
RepositoryException
      ↓
ServiceException
      ↓
API error response
```

Translation should add semantic value.

Do not blindly wrap every exception at every layer.

---

# 4.4.18 Propagate vs Wrap

This is a major design decision.

## Propagate

Use when the exception already represents the correct abstraction.

```java
void service() throws CustomerNotFoundException {
    repository.findCustomer();
}
```

If the exception is already meaningful to the caller, unnecessary wrapping adds noise.

---

## Wrap

Wrap when:

```text
lower-level abstraction
        ↓
should not leak upward
```

Example:

```java
catch (SQLException e) {
    throw new CustomerRepositoryException(
        "Unable to load customer",
        e
    );
}
```

---

# 4.4.19 Decision Framework

Ask:

```text
Is the current exception meaningful to my caller?
        ↓
       yes
        ↓
   propagate

       no
        ↓
Does the abstraction need to change?
        ↓
       yes
        ↓
wrap + preserve cause
```

---

# 4.4.20 Preserve the Cause

Bad:

```java
catch (SQLException e) {
    throw new CustomerException("Database failure");
}
```

Good:

```java
catch (SQLException e) {
    throw new CustomerException(
        "Database failure",
        e
    );
}
```

The second version preserves:

```text
original exception
+
stack trace
+
root-cause chain
```

---

# 4.4.21 Double-Wrapping

Avoid meaningless chains:

```text
ExceptionA
 ↓
ExceptionB
 ↓
ExceptionC
 ↓
ExceptionD
```

when every layer simply adds:

```text
"operation failed"
```

Instead, translate at meaningful boundaries.

Good:

```text
database implementation
        ↓
repository abstraction
        ↓
service/domain abstraction
        ↓
external API representation
```

---

# 4.4.22 Propagate Unchanged

Propagation is often best when the exception already belongs to the appropriate abstraction.

Example:

```java
Customer findCustomer(long id)
        throws CustomerNotFoundException {

    return repository.find(id)
        .orElseThrow(
            () -> new CustomerNotFoundException(
                "Customer not found: " + id
            )
        );
}
```

There is no value in wrapping it as:

```text
ServiceException
```

if the existing type is already the intended contract.

---

# 4.4.23 Custom Exception with Cause

Recommended pattern:

```java
public class PaymentException
        extends RuntimeException {

    public PaymentException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}
```

Usage:

```java
catch (TimeoutException e) {
    throw new PaymentException(
        "Payment provider timed out",
        e
    );
}
```

---

# 4.4.24 Custom Checked Exception with Cause

```java
public class ConfigurationException
        extends Exception {

    public ConfigurationException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}
```

Usage:

```java
try {
    loadConfiguration();
} catch (IOException e) {
    throw new ConfigurationException(
        "Unable to load configuration",
        e
    );
}
```

---

# 4.4.25 Custom Exception and Serialization

Exceptions extend `Throwable`, which implements `Serializable`.

Understand:

```text
Exception
    ↓
Throwable
    ↓
Serializable
```

For exceptions that may be serialized, understand compatibility concerns and fields added to the class.

Do not add arbitrary mutable state without considering serialization/API compatibility requirements.

---

# 4.4.26 `serialVersionUID`

If your custom exception is intended to be serialized, understand:

```java
private static final long serialVersionUID = 1L;
```

Modern distributed systems generally should not rely on Java native serialization merely because exceptions are serializable.

For production APIs, prefer explicit wire formats such as:

```text
JSON
+
Protobuf
+
Avro
```

where appropriate.

---

# 4.4.27 Custom Exception and Thread Safety

Exception objects are usually created, thrown, and discarded rather than shared.

Avoid mutable shared state in exception classes.

Prefer:

```java
final fields
+
immutable diagnostic data
```

when additional state is needed.

---

# 4.4.28 Memory / Runtime Behavior

A custom exception is still a Throwable object.

It can involve:

```text
heap allocation
+
stack-trace capture
+
message
+
cause
+
suppressed exceptions
```

The custom class itself does not fundamentally change Java's exception dispatch model.

---

# 4.4.29 Performance Implications

Potential costs include:

```text
object allocation
+
stack trace generation
+
GC
+
logging
+
serialization
```

Do not use custom exceptions as a high-frequency ordinary control-flow mechanism.

Measure when exception-heavy behavior is suspected to be a performance issue.

---

# 4.4.30 Production Use Cases

Custom exceptions are especially useful for:

```text
domain validation
+
business rule failures
+
repository abstraction
+
external service failures
+
configuration failures
+
authorization/domain decisions
+
API error mapping
```

Example:

```text
PaymentDeclinedException
PaymentTimeoutException
PaymentProviderUnavailableException
```

can allow different retry/API-handling strategies.

---

# 4.4.31 Retryability

A useful exception design may distinguish:

```text
retryable
vs.
non-retryable
```

Examples:

```text
PaymentTimeoutException
        ↓
possibly retryable

InvalidPaymentDetailsException
        ↓
usually non-retryable
```

Do not infer retryability solely from exception names.

The actual operation and failure semantics matter.

---

# 4.4.32 Exception Types and HTTP APIs

A backend might map:

```text
CustomerNotFoundException
        ↓
404

InvalidOrderException
        ↓
400

PaymentDeclinedException
        ↓
appropriate domain/API response

Unexpected internal exception
        ↓
500
```

Do not expose:

```text
stack traces
+
database exception messages
+
internal implementation details
```

to clients.

---

# 4.4.33 Error Code vs Exception Type

Use exception type for:

```text
internal programmatic classification
```

Use explicit error codes for:

```text
stable external contracts
```

Example:

```text
Exception:
PaymentDeclinedException

External error code:
PAYMENT_DECLINED
```

This prevents clients from depending on Java class names.

---

# 4.4.34 Exception Hierarchy Example

```text
ApplicationException
│
├── CustomerException
│   ├── CustomerNotFoundException
│   └── DuplicateCustomerException
│
├── PaymentException
│   ├── PaymentDeclinedException
│   ├── PaymentTimeoutException
│   └── PaymentProviderUnavailableException
│
└── InventoryException
    └── InventoryUnavailableException
```

Discuss:

```text
Which classes need separate handling?
Which should share handling?
Which should be HTTP-mapped?
Which should be retryable?
```

---

# 4.4.35 Common Mistakes

- [ ] Creating custom exceptions without semantic value.
- [ ] Using vague names.
- [ ] Calling every exception `BusinessException`.
- [ ] Creating excessive exception hierarchies.
- [ ] Losing the original cause.
- [ ] Wrapping at every layer.
- [ ] Propagating low-level implementation details.
- [ ] Using exception messages as machine-readable codes.
- [ ] Including secrets in exception messages.
- [ ] Including excessive sensitive data in logs.
- [ ] Making exception state mutable unnecessarily.
- [ ] Using exceptions for ordinary control flow.
- [ ] Choosing checked exceptions solely by habit.
- [ ] Choosing unchecked exceptions solely to avoid boilerplate.
- [ ] Returning HTTP status directly from domain exception classes.
- [ ] Coupling domain exceptions tightly to a transport protocol.
- [ ] Treating all failures as retryable.

---

# 4.4.36 Edge Cases

- [ ] Custom exception with no message.
- [ ] Custom exception with a cause.
- [ ] Null cause.
- [ ] Nested cause chain.
- [ ] Multiple suppressed exceptions.
- [ ] Checked custom exception crossing several layers.
- [ ] Unchecked custom exception crossing several layers.
- [ ] Exception wrapping an Error.
- [ ] Exception during exception construction.
- [ ] Exception message containing sensitive information.
- [ ] Duplicate exception translation.
- [ ] Multiple exception subclasses mapping to the same external error.
- [ ] One exception type requiring different retry policies depending on context.
- [ ] Serialization compatibility.
- [ ] Exception handling across asynchronous boundaries.

---

# 4.4.37 Coding Exercises

## Basic

- [ ] Create a custom checked exception.
- [ ] Create a custom unchecked exception.
- [ ] Add message constructors.
- [ ] Add cause constructors.
- [ ] Throw and catch the custom exceptions.
- [ ] Create a meaningful exception hierarchy.

## Intermediate

- [ ] Build `CustomerNotFoundException`.
- [ ] Build `InvalidOrderException`.
- [ ] Build `PaymentException` with subclasses.
- [ ] Add structured error codes.
- [ ] Preserve causes.
- [ ] Demonstrate propagate vs wrap.
- [ ] Demonstrate exception translation.

## Advanced

- [ ] Build repository → service exception translation.
- [ ] Hide JDBC exceptions behind repository exceptions.
- [ ] Design retryable and non-retryable exception types.
- [ ] Build centralized API exception mapping.
- [ ] Preserve root causes across multiple layers.
- [ ] Build a domain exception hierarchy.
- [ ] Benchmark exception-heavy code with JMH.

## Production-Style

- [ ] Design an exception hierarchy for an e-commerce backend.
- [ ] Map domain exceptions to API error codes.
- [ ] Prevent internal details from leaking to clients.
- [ ] Add safe structured diagnostic context.
- [ ] Design retry classification for downstream failures.
- [ ] Implement repository exception translation.
- [ ] Test exception behavior under dependency failures.

---

# 4.4.38 Debugging Exercise — Lost Cause

Broken:

```java
try {
    repository.save(order);
} catch (SQLException e) {
    throw new OrderPersistenceException(
        "Unable to save order"
    );
}
```

Fix:

```java
throw new OrderPersistenceException(
    "Unable to save order",
    e
);
```

Explain:

```text
What information was lost?
How does the cause improve debugging?
```

---

# 4.4.39 Debugging Exercise — Over-Wrapping

Given:

```text
SQLException
 ↓
RepositoryException
 ↓
ServiceException
 ↓
ApplicationException
 ↓
ControllerException
```

Determine:

```text
Which layers add useful abstraction?
Which layers merely add noise?
Where should translation occur?
Which causes should be preserved?
```

---

# 4.4.40 Debugging Exercise — Message Dependency

Broken:

```java
if ("Customer not found".equals(e.getMessage())) {
    return notFound();
}
```

Redesign using:

```text
exception type
+
structured error code
```

Explain why messages should not be treated as stable programmatic contracts.

---

# 4.4.41 Debugging Exercise — Sensitive Message

Broken:

```java
throw new PaymentException(
    "Payment failed. Card number: " + cardNumber
);
```

Identify the production security/observability problem.

Redesign the diagnostic message without exposing sensitive information.

---

# 4.4.42 Debugging Exercise — Retry Classification

Given:

```text
PaymentException
```

determine whether the following are likely retryable:

```text
invalid card number
provider timeout
provider temporarily unavailable
duplicate payment request
authorization declined
```

Design a better exception model.

---

# 4.4.43 Interview Questions

## Basic

- [ ] Why create a custom exception?
- [ ] How do you create a checked custom exception?
- [ ] How do you create an unchecked custom exception?
- [ ] What should a custom exception be named?
- [ ] How do you pass a message?
- [ ] How do you pass a cause?
- [ ] What is exception wrapping?
- [ ] What is exception translation?
- [ ] Difference between propagate and wrap?

## Intermediate

- [ ] When should you use checked custom exceptions?
- [ ] When should you use unchecked custom exceptions?
- [ ] Why preserve the original cause?
- [ ] Should every exception have a custom class?
- [ ] Why should messages not be used as programmatic error codes?
- [ ] How should custom exception hierarchies be designed?
- [ ] What makes a good exception message?
- [ ] How do suppressed exceptions relate to custom exceptions?
- [ ] Can custom exceptions contain additional fields?
- [ ] Should exception fields be mutable?

## Advanced

- [ ] Design a custom exception hierarchy for a backend.
- [ ] Explain exception translation across repository/service/controller layers.
- [ ] Explain when propagation is better than wrapping.
- [ ] Explain how to preserve root causes.
- [ ] Explain over-wrapping and abstraction leakage.
- [ ] Design retryable vs non-retryable exception types.
- [ ] Design machine-readable error codes.
- [ ] Explain custom exception performance.
- [ ] Explain exception behavior across asynchronous boundaries.
- [ ] Explain custom exception serialization concerns.

## Senior / Production

- [ ] How would you design exceptions for a large microservice?
- [ ] How would you prevent database-specific exceptions from leaking into the domain?
- [ ] How would you map domain exceptions to REST errors?
- [ ] How would you prevent sensitive data from entering exception logs?
- [ ] How would you classify downstream failures for retries?
- [ ] How would you avoid duplicate logging?
- [ ] How would you preserve diagnostic context across service boundaries?
- [ ] How would you design an exception hierarchy that remains stable as the system grows?
- [ ] When would you deliberately propagate an infrastructure exception?
- [ ] How would you test exception contracts?

---

# 4.4.44 Advanced Follow-ups

- [ ] Throwable inheritance.
- [ ] Checked-exception compiler rules.
- [ ] Exception stack traces.
- [ ] Exception cause chains.
- [ ] Suppressed exceptions.
- [ ] Custom exception serialization.
- [ ] Exception object allocation.
- [ ] JVM exception dispatch.
- [ ] Exception handling in `CompletableFuture`.
- [ ] Exception handling in virtual threads.
- [ ] Spring exception translation.
- [ ] Spring `@Repository` exception translation.
- [ ] REST exception mapping.
- [ ] Global exception handlers.
- [ ] Retry/circuit-breaker exception classification.
- [ ] Distributed error propagation.
- [ ] Error codes and external contracts.
- [ ] Observability and structured exception logging.
- [ ] JLS exception checking.
- [ ] OpenJDK Throwable implementation.

---

# 4.4.45 Specification / Source Investigation

Study:

```text
Throwable
Exception
RuntimeException
```

Study JLS topics:

```text
throw
throws
try/catch
exception checking
```

Inspect OpenJDK where useful for:

```text
Throwable constructors
+
cause handling
+
suppressed exceptions
+
stack traces
```

Inspect framework source where relevant to understand:

```text
exception translation
+
global exception handling
+
persistence exception abstraction
```

Always distinguish:

```text
Java language/API guarantees
```

from:

```text
framework implementation details
```

---

# 4.4.46 Final Mastery Gate

## Custom Checked Exceptions

- [ ] Create them correctly.
- [ ] Understand compiler enforcement.
- [ ] Design appropriate constructors.
- [ ] Propagate them correctly.
- [ ] Decide when they are justified.

## Custom Unchecked Exceptions

- [ ] Create them correctly.
- [ ] Understand RuntimeException semantics.
- [ ] Use them for meaningful domain/API contracts.
- [ ] Avoid using them merely to hide design problems.

## Naming

- [ ] Use meaningful names.
- [ ] Use `Exception` suffix.
- [ ] Avoid vague generic names.
- [ ] Avoid unnecessary class proliferation.

## Messages

- [ ] Write useful diagnostic messages.
- [ ] Include safe context.
- [ ] Avoid secrets/sensitive information.
- [ ] Never use messages as stable machine-readable contracts.

## Wrapping

- [ ] Wrap at meaningful abstraction boundaries.
- [ ] Preserve causes.
- [ ] Avoid double-wrapping.
- [ ] Understand cause chains.

## Translation

- [ ] Hide inappropriate implementation details.
- [ ] Expose meaningful domain failures.
- [ ] Preserve root causes.
- [ ] Map errors appropriately at external boundaries.

## Propagate vs Wrap

- [ ] Know when to propagate unchanged.
- [ ] Know when to wrap.
- [ ] Explain the abstraction boundary.
- [ ] Avoid unnecessary translation.

## Debugging

- [ ] Trace custom exception causes.
- [ ] Identify lost causes.
- [ ] Identify over-wrapping.
- [ ] Diagnose message misuse.
- [ ] Diagnose sensitive logging.

## Performance

- [ ] Understand allocation.
- [ ] Understand stack-trace costs.
- [ ] Understand logging costs.
- [ ] Avoid exceptions for ordinary control flow.
- [ ] Benchmark when necessary.

## Production

- [ ] Design a maintainable exception hierarchy.
- [ ] Define retryable/non-retryable semantics.
- [ ] Map errors to stable external contracts.
- [ ] Protect sensitive data.
- [ ] Preserve diagnostic context.
- [ ] Avoid implementation leakage.

---

# Final Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] DEBUGGED
- [ ] INTERNALS UNDERSTOOD
- [ ] CUSTOM CHECKED EXCEPTIONS MASTERED
- [ ] CUSTOM UNCHECKED EXCEPTIONS MASTERED
- [ ] EXCEPTION NAMING MASTERED
- [ ] EXCEPTION MESSAGES MASTERED
- [ ] EXCEPTION WRAPPING MASTERED
- [ ] EXCEPTION TRANSLATION MASTERED
- [ ] PROPAGATE VS WRAP MASTERED
- [ ] EDGE CASES MASTERED
- [ ] PERFORMANCE UNDERSTOOD
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
