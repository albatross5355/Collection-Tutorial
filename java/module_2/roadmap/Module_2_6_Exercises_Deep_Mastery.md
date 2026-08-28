# Module 2.6 — Exercises
## Deep Mastery Practice

> **Goal:** Convert Modules 2.1–2.5 from theoretical knowledge into implementation, debugging, and production-ready skill.

This module deliberately focuses on hands-on exercises covering:

- Primitive types
- Wrapper classes
- Strings
- Immutability
- Object contracts
- `equals()`
- `hashCode()`
- `Comparable`
- `compareTo()`

---

# Exercise Mastery Cycle

For every exercise:

1. [ ] Understand the problem
2. [ ] Predict the output/behavior before running
3. [ ] Implement the solution
4. [ ] Run and verify
5. [ ] Explain why it works
6. [ ] Explain the internal behavior
7. [ ] Test edge cases
8. [ ] Identify common mistakes
9. [ ] Analyze performance
10. [ ] Debug intentionally broken versions
11. [ ] Explain production implications
12. [ ] Answer related interview questions
13. [ ] Complete advanced follow-ups

## Completion Standard

> **Predict → Implement → Explain → Inspect Internals → Debug → Optimize → Handle Edge Cases → Apply in Production**

---

# Exercise 2.6.1 — Implement an Immutable Employee

## Objective

Build a properly immutable `Employee` value object.

---

## Part A — Basic

Create:

```java
Employee
```

with:

```text
id
name
department
salary
```

Requirements:

- [ ] Class should be `final`.
- [ ] Fields should be `private final`.
- [ ] Initialize all fields through the constructor.
- [ ] Do not provide setters.
- [ ] Provide getters/accessors.
- [ ] Validate required fields.

Starter:

```java
public final class Employee {

    private final long id;
    private final String name;
    private final String department;
    private final double salary;

    public Employee(long id,
                    String name,
                    String department,
                    double salary) {

        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // accessors
}
```

---

## Part B — Validation

Add:

- [ ] ID must be positive.
- [ ] Name cannot be null.
- [ ] Department cannot be null.
- [ ] Salary cannot be negative.
- [ ] Decide whether blank strings are allowed.

Questions:

- What exception should invalid input produce?
- Should validation happen in the constructor?
- Why is constructor validation important for immutable objects?

---

## Part C — Mutable Field

Add:

```java
List<String> skills
```

Initially implement it incorrectly:

```java
this.skills = skills;
```

Then demonstrate:

```java
List<String> skills = new ArrayList<>();
skills.add("Java");

Employee employee =
        new Employee(..., skills);

skills.add("Spring");
```

Questions:

- Did the employee change?
- Why?
- Does `final` protect the list?
- What is the difference between reference immutability and object immutability?

---

## Part D — Defensive Copy

Fix the implementation using:

```java
List.copyOf(skills)
```

Verify that:

```java
skills.add("Spring");
```

does not change the employee's internal state.

---

## Part E — Getter Protection

Test whether:

```java
employee.getSkills().add("Docker");
```

can mutate internal state.

If it can, fix the implementation.

---

## Part F — Deep Immutability

Change:

```java
List<String>
```

to:

```java
List<Skill>
```

where:

```java
class Skill {
    private String name;
}
```

Now ask:

> Is `Employee` still deeply immutable?

Explain why or why not.

---

## Part G — Production Version

Create:

```java
public record Employee(
        long id,
        String name,
        String department,
        BigDecimal salary,
        List<String> skills
) {}
```

Then make the record properly defensive.

Requirements:

- [ ] Validate components.
- [ ] Protect the list.
- [ ] Use `BigDecimal` for monetary values.
- [ ] Explain why records do not automatically provide deep immutability.

---

## Advanced Questions

- [ ] Why is `BigDecimal` preferable to `double` for salary?
- [ ] What happens if the list contains mutable objects?
- [ ] What JMM guarantees apply to final fields?
- [ ] Why does immutability simplify concurrent access?
- [ ] Would you use this object as a `HashMap` key?
- [ ] Which fields should define equality?

---

# Exercise 2.6.2 — Demonstrate String Pool Behavior

## Objective

Understand:

```text
String literals
String pool
heap objects
new String()
intern()
compile-time concatenation
runtime concatenation
```

---

## Part A — Literal Comparison

Run:

```java
String a = "Java";
String b = "Java";

System.out.println(a == b);
System.out.println(a.equals(b));
```

Before running, predict:

```text
a == b       = ?
a.equals(b)  = ?
```

Explain both results.

---

## Part B — `new String()`

Run:

```java
String a = "Java";
String b = new String("Java");

System.out.println(a == b);
System.out.println(a.equals(b));
```

Explain:

```text
pool reference
vs.
new String object
```

---

## Part C — `intern()`

Run:

```java
String a = "Java";
String b = new String("Java");

String c = b.intern();

System.out.println(a == c);
```

Explain what `intern()` does.

---

## Part D — Compile-Time Concatenation

Run:

```java
String a = "Ja" + "va";
String b = "Java";

System.out.println(a == b);
```

Explain why compile-time constant expressions can behave differently from runtime concatenation.

---

## Part E — Runtime Concatenation

Run:

```java
String x = "Ja";
String a = x + "va";
String b = "Java";

System.out.println(a == b);
System.out.println(a.equals(b));
```

Explain the difference.

---

## Part F — Final Variable

Compare:

```java
final String x = "Ja";
String a = x + "va";
```

against:

```java
String x = "Ja";
String a = x + "va";
```

Investigate how compile-time constants affect concatenation.

---

## Part G — StringBuilder Investigation

Write:

```java
String result = "";

for (int i = 0; i < 10000; i++) {
    result += i;
}
```

Then rewrite using:

```java
StringBuilder
```

Compare:

- [ ] Runtime
- [ ] Allocations
- [ ] Intermediate objects
- [ ] GC pressure

---

## Advanced Questions

- [ ] Where are string literals stored conceptually in modern JVMs?
- [ ] What changed regarding the string pool across older Java versions?
- [ ] What does `intern()` return?
- [ ] When should `intern()` be avoided?
- [ ] How does Java compile string concatenation?
- [ ] How does modern Java optimize concatenation?
- [ ] Why is `String` immutable?
- [ ] Why does immutability make pooling possible?

---

# Exercise 2.6.3 — Demonstrate Wrapper Caching

## Objective

Understand:

```text
primitive
wrapper
autoboxing
unboxing
caching
identity
equals()
null unboxing
```

---

## Part A — Integer Cache

Run:

```java
Integer a = 100;
Integer b = 100;

System.out.println(a == b);
System.out.println(a.equals(b));
```

Predict first.

---

## Part B — Outside Typical Cache Range

Run:

```java
Integer a = 1000;
Integer b = 1000;

System.out.println(a == b);
System.out.println(a.equals(b));
```

Explain why relying on `==` for wrapper value comparison is incorrect.

---

## Part C — Explicit Construction

Compare:

```java
Integer a = Integer.valueOf(100);
Integer b = Integer.valueOf(100);
```

with:

```java
Integer a = new Integer(100);
Integer b = new Integer(100);
```

Note that explicit wrapper constructors have been deprecated and should not be used in modern Java.

---

## Part D — Other Wrapper Types

Investigate caching behavior for:

- [ ] `Byte`
- [ ] `Short`
- [ ] `Integer`
- [ ] `Long`
- [ ] `Character`
- [ ] `Boolean`

Do not assume all wrapper classes have identical caching rules.

---

## Part E — Autoboxing

Explain:

```java
Integer x = 10;
```

conceptually as:

```java
Integer.valueOf(10);
```

Then:

```java
int y = x;
```

conceptually involves unboxing.

---

## Part F — Null Unboxing

Run:

```java
Integer value = null;

int x = value;
```

Tasks:

- [ ] Predict the exception.
- [ ] Explain why it occurs.
- [ ] Identify the implicit operation.
- [ ] Rewrite safely.

---

## Part G — Wrapper Performance

Compare:

```java
int sum = 0;

for (int i = 0; i < 10_000_000; i++) {
    sum += i;
}
```

against a deliberately boxing-heavy implementation.

Measure:

- [ ] Execution time
- [ ] Allocation
- [ ] GC
- [ ] Memory usage

---

## Advanced Questions

- [ ] Why does Java cache some wrapper values?
- [ ] Is wrapper caching guaranteed for every arbitrary integer?
- [ ] Why is `==` dangerous for wrappers?
- [ ] What is the difference between identity and value equality?
- [ ] How can autoboxing silently create allocations?
- [ ] Why can wrapper-heavy collections have memory overhead?
- [ ] Why does `HashMap<Integer, ...>` differ from `int[]` in memory behavior?

---

# Exercise 2.6.4 — Implement Correct `equals()` / `hashCode()`

## Objective

Implement and validate the complete object equality contract.

Create:

```java
public final class EmployeeId {

    private final String value;

    public EmployeeId(String value) {
        this.value = value;
    }
}
```

---

## Part A — Implement `equals()`

Requirements:

- [ ] Same reference → true.
- [ ] Null → false.
- [ ] Wrong type → false.
- [ ] Same logical ID → true.
- [ ] Different ID → false.

---

## Part B — Implement `hashCode()`

Ensure:

```text
a.equals(b)
        ↓
a.hashCode() == b.hashCode()
```

Use:

```java
Objects.hash(...)
```

or an appropriate equivalent.

---

## Part C — Contract Tests

Create tests for:

### Reflexivity

```java
assertTrue(a.equals(a));
```

### Symmetry

```text
a.equals(b) == b.equals(a)
```

### Transitivity

```text
a.equals(b)
b.equals(c)
a.equals(c)
```

### Consistency

Repeated calls produce the same result while relevant state remains unchanged.

### Null

```java
assertFalse(a.equals(null));
```

---

## Part D — HashSet

Run:

```java
Set<EmployeeId> ids = new HashSet<>();

ids.add(new EmployeeId("E100"));
ids.add(new EmployeeId("E100"));
```

Expected logical behavior:

```text
size = 1
```

Verify it.

---

## Part E — HashMap

Run:

```java
Map<EmployeeId, String> employees = new HashMap<>();

employees.put(new EmployeeId("E100"), "Alice");

System.out.println(
        employees.get(new EmployeeId("E100"))
);
```

This should work only if the object contract is correct.

---

## Part F — Deliberately Break `hashCode()`

Remove `hashCode()`.

Run the same test.

Observe and explain the behavior.

---

## Part G — Mutable Key Debugging

Create:

```java
class MutableEmployeeKey {

    String id;

    // equals/hashCode based on id
}
```

Then:

```java
MutableEmployeeKey key =
        new MutableEmployeeKey("E100");

Map<MutableEmployeeKey, String> map =
        new HashMap<>();

map.put(key, "Alice");

key.id = "E200";

System.out.println(map.get(key));
```

Tasks:

- [ ] Predict behavior.
- [ ] Explain why it can fail.
- [ ] Explain bucket selection.
- [ ] Explain why immutability solves the problem.

---

## Advanced Questions

- [ ] Why is same hash code not sufficient for equality?
- [ ] Why does HashMap need both hashCode and equals?
- [ ] What happens during a collision?
- [ ] Why are immutable keys preferred?
- [ ] Which Employee fields should define identity?
- [ ] How does entity equality differ from value-object equality?

---

# Exercise 2.6.5 — Debug a Broken `compareTo()`

## Objective

Find and fix incorrect natural ordering implementations.

---

## Part A — Broken Subtraction

Given:

```java
public int compareTo(Employee other) {
    return this.age - other.age;
}
```

Identify the problem.

Construct values that can cause integer overflow.

---

## Part B — Fix It

Replace with:

```java
return Integer.compare(this.age, other.age);
```

Explain why this is safer.

---

## Part C — Return-Value Mistake

Find the problem with:

```java
if (employee.compareTo(other) == -1) {
    ...
}
```

Correct it:

```java
if (employee.compareTo(other) < 0) {
    ...
}
```

Explain why `compareTo()` does not promise exactly `-1`, `0`, or `1`.

---

## Part D — Multi-Field Ordering

Create ordering:

```text
department
    ↓
salary
    ↓
name
```

Implement using:

```java
Comparator
```

with:

```java
thenComparing(...)
```

---

## Part E — TreeSet

Create:

```java
Set<Employee> employees = new TreeSet<>();
```

Investigate what happens when:

```java
compareTo() == 0
```

but:

```java
equals() == false
```

Explain the consequence.

---

## Part F — BigDecimal Trap

Run:

```java
BigDecimal a = new BigDecimal("1.0");
BigDecimal b = new BigDecimal("1.00");

System.out.println(a.equals(b));
System.out.println(a.compareTo(b));
```

Then compare:

```java
HashSet<BigDecimal>
TreeSet<BigDecimal>
```

Explain why their behavior differs.

---

## Part G — Broken Transitivity

Create a deliberately broken comparator that produces inconsistent ordering.

Use:

```java
List.sort(...)
TreeSet
```

and observe the consequences.

Tasks:

- [ ] Identify the violation.
- [ ] Explain why ordering must be transitive.
- [ ] Repair the comparator.

---

# Exercise 2.6.6 — Integrated Object-Contract Challenge

Build:

```text
Employee
EmployeeId
Department
Salary
```

Requirements:

```text
EmployeeId
    ↓
immutable value object

Employee
    ↓
immutable domain object

Salary
    ↓
BigDecimal

Department
    ↓
well-defined equality

Employee
    ↓
Comparable<Employee>
```

Implement:

- [ ] `equals()`
- [ ] `hashCode()`
- [ ] `compareTo()`
- [ ] Multiple `Comparator`s
- [ ] Defensive copying
- [ ] Constructor validation

---

# Exercise 2.6.7 — Collection Behavior Lab

Create the same logical employees and insert them into:

```java
HashSet<Employee>
TreeSet<Employee>
HashMap<EmployeeId, Employee>
TreeMap<EmployeeId, Employee>
```

Record:

- [ ] What determines uniqueness?
- [ ] What determines lookup?
- [ ] What determines ordering?
- [ ] What happens when equality and ordering disagree?
- [ ] What happens after key mutation?
- [ ] What happens with null?
- [ ] What happens with duplicate logical values?

Create a comparison table from your observations.

---

# Exercise 2.6.8 — Primitive and Wrapper Edge-Case Lab

Test:

```java
int
Integer
long
Long
double
Double
boolean
Boolean
```

Investigate:

- [ ] Overflow
- [ ] Underflow
- [ ] Boxing
- [ ] Unboxing
- [ ] Cache behavior
- [ ] Null unboxing
- [ ] `==`
- [ ] `equals()`
- [ ] `compareTo()`
- [ ] Special floating-point values

Include:

```java
Double.NaN
Double.POSITIVE_INFINITY
Double.NEGATIVE_INFINITY
+0.0
-0.0
```

---

# Exercise 2.6.9 — String + Wrapper + Equality Challenge

Predict the result before running:

```java
String a = "100";
String b = new String("100");

Integer x = 100;
Integer y = 100;

System.out.println(a == b);
System.out.println(a.equals(b));

System.out.println(x == y);
System.out.println(x.equals(y));
```

Then explain:

```text
String pool
    vs.
Integer cache
    vs.
Object identity
    vs.
logical equality
```

---

# Exercise 2.6.10 — Production Value Object

Design a:

```text
Money
```

value object.

Requirements:

- [ ] Immutable.
- [ ] `BigDecimal` amount.
- [ ] Currency code.
- [ ] Constructor validation.
- [ ] Correct `equals()`.
- [ ] Correct `hashCode()`.
- [ ] Natural ordering only if domain semantics justify it.
- [ ] No floating-point money representation.
- [ ] Safe as a HashMap key.
- [ ] Safe across threads.
- [ ] Clear null policy.
- [ ] Tests for scale behavior.

Investigate:

```java
new BigDecimal("10.0")
new BigDecimal("10.00")
```

Decide whether your domain considers them equal.

Document the decision.

---

# Exercise 2.6.11 — Production Debugging Scenario

You receive this incident:

```text
A service stores Employee objects in a HashSet.

Duplicate employees appear.

Another service reports that:
employees.contains(employee)
returns false even though the employee was previously added.
```

Investigate systematically:

```text
1. equals()
      ↓
2. hashCode()
      ↓
3. mutable equality fields
      ↓
4. object mutation
      ↓
5. inheritance
      ↓
6. null behavior
      ↓
7. hash distribution
```

Produce:

- [ ] Root cause.
- [ ] Minimal reproduction.
- [ ] Fixed implementation.
- [ ] Regression test.
- [ ] Production prevention strategy.

---

# Exercise 2.6.12 — Interview Drill

## Basic

- [ ] Why is String immutable?
- [ ] What is the String pool?
- [ ] What is autoboxing?
- [ ] What is wrapper caching?
- [ ] What is immutability?
- [ ] What is `equals()`?
- [ ] What is `hashCode()`?
- [ ] What is `Comparable`?

## Intermediate

- [ ] Why is `==` dangerous with wrappers?
- [ ] Why doesn't `final` make a List immutable?
- [ ] Why must arrays be defensively copied?
- [ ] Why must `equals()` and `hashCode()` agree?
- [ ] What happens if a HashMap key is mutated?
- [ ] Why does `compareTo()` return an integer?
- [ ] Difference between Comparable and Comparator?
- [ ] Why can TreeSet and HashSet behave differently?

## Advanced

- [ ] Explain String pool internals.
- [ ] Explain compile-time vs runtime concatenation.
- [ ] Explain wrapper cache behavior.
- [ ] Explain final-field semantics.
- [ ] Explain the complete HashMap lookup process.
- [ ] Explain equality contract violations.
- [ ] Explain `BigDecimal.equals()` vs `compareTo()`.
- [ ] Explain ordering-based uniqueness in TreeSet.

## Senior / Production

- [ ] Design an immutable value object.
- [ ] Define entity equality.
- [ ] Define value-object equality.
- [ ] Diagnose a broken HashMap lookup.
- [ ] Diagnose duplicate objects in a HashSet.
- [ ] Design safe natural ordering.
- [ ] Decide when natural ordering should not be implemented.
- [ ] Explain the performance impact of boxing.
- [ ] Explain defensive-copy trade-offs.
- [ ] Design object contracts for a high-throughput service.

---

# Exercise 2.6.13 — Advanced Follow-ups

## OpenJDK Investigation

Inspect relevant implementations for:

- [ ] `String`
- [ ] `Integer.valueOf()`
- [ ] `Integer.IntegerCache`
- [ ] `Object.equals()`
- [ ] `Object.hashCode()`
- [ ] `HashMap`
- [ ] `HashSet`
- [ ] `TreeMap`
- [ ] `TreeSet`
- [ ] `StringBuilder`

Questions:

- How is the String pool represented?
- How does Integer caching work?
- How does HashMap calculate a bucket?
- How does HashMap resolve collisions?
- How does TreeMap navigate its tree?
- How do sorted collections use comparison?

---

# Exercise 2.6.14 — Performance Lab

Benchmark:

### Test A

```text
primitive int
```

### Test B

```text
Integer
```

### Test C

```text
String concatenation
```

### Test D

```text
StringBuilder
```

### Test E

```text
HashMap with good hash distribution
```

### Test F

```text
HashMap with intentionally poor hash distribution
```

Measure:

- [ ] Throughput
- [ ] Allocation rate
- [ ] Heap usage
- [ ] GC activity
- [ ] CPU usage
- [ ] Latency

Use a proper benchmark methodology rather than relying solely on `System.nanoTime()` around a single operation.

---

# Exercise 2.6.15 — Final Integrated Challenge

Build:

```text
Employee Directory
```

Architecture:

```text
EmployeeId
     ↓
Immutable Value Object
     ↓
Employee
     ↓
Immutable Domain Object
     ↓
HashMap<EmployeeId, Employee>
     ↓
Sorted Employee Views
     ↓
Comparators
```

Requirements:

### EmployeeId

- [ ] Immutable.
- [ ] Correct equality.
- [ ] Correct hash code.
- [ ] Safe as HashMap key.

### Employee

- [ ] Immutable.
- [ ] Defensive copying.
- [ ] Constructor validation.
- [ ] Correct equality semantics.
- [ ] Stable hash code.

### Sorting

Support:

```text
by employee ID
by name
by salary
by department + name
```

### Collection Operations

Implement:

```text
add
findById
remove
findByDepartment
sortBySalary
topNBySalary
```

### Testing

Test:

- [ ] Duplicate IDs.
- [ ] Null IDs.
- [ ] Mutable input collections.
- [ ] Duplicate employees.
- [ ] HashMap lookup.
- [ ] TreeSet behavior.
- [ ] Comparator consistency.
- [ ] Salary edge cases.
- [ ] Concurrent reads.

---

# 2.6.16 Debugging Mastery Checklist

You should be able to diagnose:

- [ ] `HashMap.get()` unexpectedly returns null.
- [ ] `HashSet` contains duplicates.
- [ ] `HashSet.remove()` unexpectedly fails.
- [ ] `TreeSet` loses apparently distinct values.
- [ ] `TreeMap` replaces an apparently different key.
- [ ] Wrapper `==` behaves unexpectedly.
- [ ] String `==` behaves unexpectedly.
- [ ] An immutable object changes unexpectedly.
- [ ] A getter exposes mutable internal state.
- [ ] A comparator produces inconsistent ordering.
- [ ] `compareTo()` behaves incorrectly for extreme integers.
- [ ] A service has excessive boxing/allocation.

---

# 2.6.17 Mastery Gate

## Primitive Types

- [ ] Implement primitive operations.
- [ ] Explain numeric ranges.
- [ ] Debug overflow.
- [ ] Explain integer promotion.
- [ ] Explain floating-point precision.

## Wrappers

- [ ] Explain autoboxing.
- [ ] Explain unboxing.
- [ ] Explain wrapper caching.
- [ ] Debug null unboxing.
- [ ] Explain boxing performance.
- [ ] Correctly use `equals()` instead of `==`.

## Strings

- [ ] Explain String immutability.
- [ ] Explain the String pool.
- [ ] Explain literals.
- [ ] Explain `new String()`.
- [ ] Explain `intern()`.
- [ ] Explain compile-time concatenation.
- [ ] Explain runtime concatenation.
- [ ] Use StringBuilder appropriately.
- [ ] Explain Unicode/code points at a practical level.

## Immutability

- [ ] Build an immutable Employee.
- [ ] Use final fields correctly.
- [ ] Protect mutable fields.
- [ ] Implement defensive copying.
- [ ] Explain records' limitations.
- [ ] Explain thread-safety benefits.
- [ ] Design deep immutability when required.

## Object Contracts

- [ ] Implement `equals()`.
- [ ] Implement `hashCode()`.
- [ ] Prove contract compliance with tests.
- [ ] Explain HashMap behavior.
- [ ] Explain HashSet behavior.
- [ ] Debug mutable-key failures.
- [ ] Implement Comparable.
- [ ] Implement safe `compareTo()`.
- [ ] Use Comparator correctly.
- [ ] Explain TreeMap/TreeSet semantics.
- [ ] Explain equality/order consistency.

---

# Final Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] BASIC EXERCISES COMPLETE
- [ ] INTERMEDIATE EXERCISES COMPLETE
- [ ] ADVANCED EXERCISES COMPLETE
- [ ] DEBUGGING CHALLENGES COMPLETE
- [ ] PERFORMANCE LAB COMPLETE
- [ ] PRODUCTION PROJECT COMPLETE
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
