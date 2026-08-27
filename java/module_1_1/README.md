
Worked for 11s

Yes. I understand what you want: not just another list of interview questions, but a complete question framework for every subtopic in Modules 1.1–1.9, where each topic is tested from multiple angles:

1. Basic — what it is


2. Intermediate — how/why it works


3. Advanced — internals and difficult cases


4. Conceptual — reasoning/traps


5. Theory — definitions, rules, specifications


6. Implementation — write/modify/debug code


7. Major traps — predict output / identify bugs


8. Production & design — real-world decisions


9. Performance/runtime — where relevant


10. Senior/interview — trade-offs and architecture



This matches the mastery philosophy already present in your files: Explain → Implement → Internals → Edge cases → Trade-offs → Debug → Production. 

Important: I would structure it differently from your existing modules

Your current documents already contain explanations, exercises and interview questions. For example, OOP explicitly expects you to distinguish overloading/overriding, dynamic dispatch, field hiding, static method hiding, composition/inheritance, etc. 

So the question bank should become the assessment layer on top of the syllabus, rather than repeating the notes.


---

Question Framework I will use

For each individual subtopic, the question set will look like this:

A. Basic — What is it?

Usually 3–5 questions

Example — this

What is this in Java?

Why is this required?

What does this.field refer to?

Can this be used inside a static method?

Does this create a new object?


B. Intermediate — How does it work?

Usually 3–5 questions

How does this distinguish an instance field from a constructor parameter?

What is the difference between this() and this?

What restrictions apply to this()?

How does this behave when an instance method is invoked?

Why can this be passed to another method?


C. Advanced — Internals

Usually 3–6 questions

What does the JVM conceptually pass as the receiver of an instance method?

How is this represented in an instance method's local-variable context?

What happens when this escapes during construction?

How does constructor-time publication interact with object initialization?

How can JIT optimization affect the physical representation of object references?


This follows the source's emphasis on receiver objects, JVM invocation and bytecode. 

D. Conceptual / Tricky

Usually 3–6 questions

These are the questions that expose whether you actually understand Java.

For example:

class A {
    A() {
        print();
    }

    void print() {
        System.out.println("A");
    }
}

class B extends A {
    private int x = 10;

    @Override
    void print() {
        System.out.println(x);
    }
}

Questions:

What gets printed?

Why?

Has B.x been initialized when print() executes?

Why is calling overridable methods from constructors dangerous?

How would you redesign this?


Your source explicitly identifies this as a constructor dispatch trap. 

E. Theory

Questions covering exact Java rules:

What does the JLS specify?

What is the formal rule?

What is compiler-enforced?

What is JVM-enforced?

What is language semantics versus implementation detail?


This is particularly important for topics such as:

overload resolution

overriding

access control

class initialization

equality

pass-by-value

volatile

synchronized

sealed classes

annotations

varargs


F. Implementation

Not just "write a program."

Questions will include:

Implement

> Implement constructor chaining using this().



Modify

> Modify the implementation so all constructors delegate to one canonical constructor.



Debug

> The following constructor hierarchy produces unexpected output. Find and fix the problem.



Refactor

> Refactor this design to remove duplicated initialization.



Design

> Design a class where mandatory invariants can never be violated.



Your Module 1.4 already specifically asks for constructor chaining experiments and this() vs super(). 


---

G. Major Traps

This will be a separate category, because Java has many interview traps.

Examples:

JVM

JDK vs JRE vs JVM

"Java is platform independent" — what exactly is platform independent?

Is JVM itself platform independent?

.java → .class → machine code

class loading vs class initialization

ClassNotFoundException vs NoClassDefFoundError

newer bytecode on older JVM

stack/heap oversimplifications


The source itself specifically calls out these areas. 

OOP

overload vs override

static method hiding

field hiding

private methods

final methods

covariant returns

interface default conflicts

compile-time vs runtime polymorphism


Object

== vs equals()

equals() without hashCode()

mutable HashMap keys

shallow vs deep clone

getClass() vs instanceof

proxy classes

array equality


Language

integer overflow

floating-point precision

autoboxing

wrapper caching

String pool

null

var

varargs overload ambiguity

recursion stack overflow


Initialization

static block order

instance block order

parent vs child initialization

constructor chaining

this() vs super()

initialization failure

circular initialization

constructor calling overridable method

this escape



---

H. Production / Design Questions

This is where the question bank becomes senior-level rather than exam-level.

For example, instead of only:

> What is private?



you'll get:

> You are designing a public Java library. Which members should be public, protected, package-private and private, and why?



And:

> A service has 15 mutable static fields shared by multiple requests. What problems can this create in production?



The syllabus itself explicitly emphasizes deliberate API boundaries and avoiding unnecessary public state. 

For OOP:

> When would you choose:

interface

vs

abstract class

vs

composition?



Your source already gives the decision framework: shared contract → interface, shared state/behavior → abstract class, replaceable dependency → composition, true subtype → inheritance. 


---

I. Production Debugging Questions

I'll also include questions like:

> A production HashMap lookup suddenly returns null for a key that was previously inserted. What would you investigate?



Expected areas:

equals()

hashCode()

mutable fields

key mutation

hash distribution

collection behavior


This is directly aligned with your Object module's production debugging requirements. 

Similarly:

> A long-running application shows unexpected memory retention. An anonymous callback was registered with a long-lived component. What reference chain would you investigate?



This connects nested/inner classes, callbacks, GC roots and lifecycle.

Your Module 1.8 specifically identifies inner classes, anonymous callbacks, static fields, caches and ThreadLocal as memory-retention investigation areas. 


---

J. Scenario-Based Questions

These will be particularly important.

Instead of:

> What is SRP?



you'll get:

> You have a 2,000-line OrderService that performs validation, pricing, persistence, email, PDF generation and auditing. Identify the responsibilities and redesign it.



Then:

Which principle is violated?

How would you split it?

Where could over-engineering occur?

What dependencies should be injected?

How would you test the new design?

What would you keep together because of cohesion?


That aligns with your SRP material and its explicit God-class refactoring exercise. 


---

K. Output/Prediction Questions

I would make these a major part of the bank.

For example:

class Parent {
    static int x = init("Parent");

    int y = init("Parent instance");

    Parent() {
        System.out.println("Parent constructor");
    }

    static int init(String s) {
        System.out.println(s);
        return 1;
    }
}

class Child extends Parent {
    static int z = init("Child");

    int w = init("Child instance");

    Child() {
        System.out.println("Child constructor");
    }
}

Question:

> What is the exact output when new Child() is executed?



Then progressively harder versions involving:

static initialization

instance initialization

inheritance

constructor chaining

this()

super()

overridden methods

static methods

field hiding


Your Module 1.4 specifically requires being able to predict initialization output before execution. 


---

L. Design Questions

For every suitable topic, I'll include questions such as:

Encapsulation

> Should a domain object expose a mutable List through a getter?



Immutability

> Why would you make a value object immutable?



Inheritance

> Why might inheritance create stronger coupling than composition?



Equality

> What fields should participate in equality for a persistence entity?



Enum

> Why is persisting ordinal() dangerous?



Nested classes

> When should a nested class be static instead of an inner class?



Annotations

> When should an annotation have RUNTIME retention?



Varargs

> Would you use varargs on a hot-path API? What allocation implications should you consider?



Assertions

> Why should assertions not be used for mandatory production validation?



The latter is explicitly covered in Module 1.2. 


---

How I would cover the 9 modules

Module 1.1 — Java Platform Fundamentals

Every subtopic such as:

JDK

JRE

JVM

JVM architecture

bytecode

.class

class loading

linking

initialization

classpath

module path

JPMS

Java versioning

LTS

preview features

compatibility

JVM arguments

application arguments

JIT

interpreter

GC

runtime memory areas

diagnostic tools

javap

jcmd

jstack

jmap

JFR

jlink

JDK distributions


will get the full question pattern.


---

Module 1.2 — Language Fundamentals

All subtopics, including:

variables/declarations

primitives

literals

operators

type conversion

casting

var

final

strings

String pool

wrappers

autoboxing/unboxing

arrays

control flow

loops

methods

parameters

return values

recursion

scope

lifetime

packages

imports

access modifiers

enums

annotations

varargs

assertions

integrated project


Your file has 26 numbered sections, including packages/imports, access modifiers, enums, annotations, varargs, recursion and assertions. 


---

Module 1.3 — OOP

Every subtopic:

classes

objects

state

behavior

identity

references

encapsulation

constructors

inheritance

extends

polymorphism

overloading

overriding

dynamic dispatch

field hiding

static method hiding

abstraction

interfaces

abstract classes

default methods

IS-A

HAS-A

composition

inheritance trade-offs

interface vs abstract class

LSP

production OOP architecture


The production section will include the complete payment-processing design because your source explicitly uses it as the integrated OOP project. 


---

Module 1.4 — Object Initialization

This will be especially heavy on output prediction and traps:

this

super

this()

super()

constructors

constructor overloading

constructor chaining

static fields

static blocks

instance fields

instance blocks

initialization order

parent-child initialization

class initialization

class loading

<init>

<clinit>

final fields

initialization failure

initialization concurrency

this escape

constructor dispatch trap

bytecode inspection


Your source explicitly goes down to invokespecial, invokestatic, <init>, <clinit> and JVM initialization locking. 


---

Module 1.5 — Object

Heavy focus on contracts and production bugs:

Object

object identity

equals()

equality contract

hashCode()

hash collisions

toString()

getClass()

clone()

Cloneable

shallow copy

deep copy

finalize()

Cleaner

wait()

notify()

notifyAll()

arrays and equality

records and generated methods

proxy-related equality/type issues

immutable keys


The source specifically expects debugging mutable-key failures and collection bugs. 


---

Module 1.6 — Design Principles

Every SOLID principle gets:

definition questions

identification questions

bad-design diagnosis

refactoring questions

implementation

counterexamples

over-engineering questions

production scenarios

architecture questions


Including:

SRP

OCP

LSP

ISP

DIP

cohesion

coupling

abstraction

dependency direction

composition

Strategy

Factory

dependency injection

ports/adapters


The important point is that the bank will not teach SOLID as five memorized definitions. Your source explicitly says the goal is to reason about change, coupling, cohesion, extensibility, testability and dependency direction. 


---

Module 1.7 — Keywords & Modifiers

Every modifier:

public

protected

package-private

private

final

static

abstract

default

sealed

non-sealed

permits

synchronized

volatile

transient

native

strictfp


gets the same Basic → Intermediate → Advanced → Conceptual → Implementation → Trap → Production structure.

The source already classifies these into access control, design, concurrency/memory visibility, serialization, native boundary and floating-point semantics. 


---

Module 1.8 — Other Java Features

Especially strong coverage of:

nested classes

static nested classes

inner classes

local classes

anonymous classes

lambdas comparison

enums

enum internals

enum identity

EnumSet

EnumMap

annotations

annotation targets

retention

runtime annotations

varargs

generic varargs

heap pollution

pass-by-value

reference semantics

aliasing

mutation vs reassignment

==

equals()

String pooling

wrapper caching

identity vs logical equality

memory retention


The source's final mastery gate explicitly includes all of these. 


---

Module 1.9 — Implementation/Challenge Questions

This module should not just be normal theory questions.

It should become the practical assessment:

Basic

Build an immutable value object.

Build a Person.

Build a BankAccount.


Intermediate

Implement inheritance.

Implement runtime polymorphism.

Implement constructor chaining.

Implement composition.


Advanced

Implement a pluggable payment system.

Implement Strategy.

Implement notification providers.

Implement storage abstraction.

Diagnose equality bugs.

Diagnose reference/aliasing bugs.


Production

Design immutable domain objects.

Design provider replacement.

Prevent mutable-state leakage.

Validate domain invariants.

Profile object allocation.

Refactor unnecessary inheritance.

Explain architectural trade-offs.


This matches the progression already specified in Module 1.9. 


---

Final structure

So the final question bank will effectively be:

MODULE
│
├── Topic
│   │
│   ├── Basic Questions
│   ├── Intermediate Questions
│   ├── Advanced Questions
│   ├── Conceptual Questions
│   ├── Theory Questions
│   ├── Implementation Questions
│   ├── Output / Prediction Questions
│   ├── Major Traps
│   ├── Debugging Questions
│   ├── Performance Questions
│   ├── Production Questions
│   ├── Design Questions
│   └── Senior / Interview Questions
│
└── Module Mastery Test

And importantly, the difficulty will genuinely increase:

> Basic: Can you define it?
↓
Intermediate: Can you use it?
↓
Advanced: Can you explain what happens internally?
↓
Conceptual: Can you reason about unfamiliar code?
↓
Implementation: Can you build it?
↓
Debugging: Can you find why it broke?
↓
Production: Can you make the right engineering decision?
↓
Senior: Can you defend that decision against alternatives?



That will give you a much stronger Java mastery + interview + production readiness question bank than simply adding more questions to the existing modules.
