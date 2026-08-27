
# Java Spring Boot ? Senior Developer / Tech Lead / Solution Architect Roadmap

> **Audience:** Java Spring Boot developer with 5+ years of experience.
> **Goal:** Progress to Senior Developer, Technical Lead, or Solution Architect.
> **Approach:** Practical, industry-focused, aligned with current enterprise standards.

## How to Use This Roadmap

Each topic is tagged with a **mastery level** and an **estimated time to master** (assuming ~8–10 focused hours/week on top of a day job):

| Level | Meaning | Signal you've reached it |
|-------|---------|--------------------------|
| ? **Beginner Refresh** | Re-solidify fundamentals you already use daily | Can explain it clearly to a junior |
| ? **Intermediate** | Apply confidently in production without hand-holding | Ship features & debug independently |
| ? **Advanced** | Design solutions, weigh trade-offs, tune for scale | Lead a component / review others' work |
| ? **Expert** | Set standards, make architecture decisions | Own cross-team technical direction |

**Legend:** ? = estimated time to master · ? = hands-on practice recommendation

---

## Table of Contents

1. [Core Java Mastery](#1-core-java-mastery)
   - 1.1 Language Foundations & OOP · 1.2 Collections Framework · 1.3 Generics & Type System · 1.4 Functional Programming & Streams · 1.5 Concurrency Fundamentals · 1.6 Executors & Async · 1.7 JVM Internals · 1.8 Memory & Garbage Collection · 1.9 Design Patterns · 1.10 Modern Java (21+)
2. [Spring Ecosystem](#2-spring-ecosystem)
   - 2.1 Spring Core & IoC · 2.2 Spring Boot & Auto-Config · 2.3 Spring MVC · 2.4 Spring Data JPA · 2.5 Spring AOP · 2.6 Spring Security · 2.7 Spring Batch · 2.8 Spring Cloud · 2.9 WebFlux & Reactive · 2.10 Platform Engineering
3. [Microservices Architecture](#3-microservices-architecture)
   - 3.1 Decomposition & DDD · 3.2 Service Communication · 3.3 Service Discovery & Gateway · 3.4 Resilience Patterns · 3.5 Distributed Data & Transactions · 3.6 Event-Driven & CQRS · 3.7 Contracts & Versioning
4. [Database Expertise](#4-database-expertise)
   - 4.1 Transactions & Isolation · 4.2 SQL Optimization · 4.3 Indexing · 4.4 PostgreSQL Deep Dive · 4.5 Caching with Redis · 4.6 Partitioning & Sharding · 4.7 Oracle · 4.8 NoSQL & MongoDB · 4.9 Polyglot Persistence
5. [API Development](#5-api-development)
   - 5.1 REST Design · 5.2 API Documentation (OpenAPI) · 5.3 Versioning & Evolution · 5.4 AuthN & AuthZ · 5.5 OAuth2 / OIDC / JWT · 5.6 API Security Hardening · 5.7 Rate Limiting & Throttling
6. [Messaging & Integration](#6-messaging--integration)
   - 6.1 Messaging Fundamentals · 6.2 Kafka · 6.3 RabbitMQ · 6.4 ActiveMQ / JMS · 6.5 Kafka Streams & Schema Registry · 6.6 Reliable Async Processing · 6.7 Enterprise Integration Patterns
7. [Cloud & DevOps](#7-cloud--devops)
   - 7.1 Containerization (Docker) · 7.2 CI/CD Pipelines · 7.3 Kubernetes · 7.4 Helm & Packaging · 7.5 Infrastructure as Code · 7.6 Cloud Platforms (Azure/AWS) · 7.7 GitOps & Progressive Delivery
8. [System Design](#8-system-design)
   - 8.1 High-Level Design · 8.2 Low-Level Design · 8.3 Scalability · 8.4 Availability & Reliability · 8.5 Fault Tolerance · 8.6 Consistency & CAP · 8.7 Load Balancing · 8.8 Caching Strategies · 8.9 Architecture Governance
9. [Testing](#9-testing)
   - 9.1 Unit Testing · 9.2 Mocking · 9.3 Integration & Slice Testing · 9.4 TestContainers · 9.5 Contract Testing · 9.6 Performance Testing · 9.7 Test Strategy & Quality
10. [Security](#10-security)
    - 10.1 OWASP Top 10 · 10.2 Secure Coding · 10.3 Authentication Mechanisms · 10.4 Cryptography & Key Management · 10.5 Auditing, Compliance & Threat Modeling
11. [Observability](#11-observability)
    - 11.1 Logging · 11.2 Log Aggregation (ELK) · 11.3 Metrics (Prometheus/Grafana) · 11.4 Distributed Tracing (OpenTelemetry) · 11.5 Unified Observability & SLOs
12. [Leadership Skills](#12-leadership-skills)
    - 12.1 Code Reviews · 12.2 Technical Writing · 12.3 Agile & Delivery · 12.4 Mentoring · 12.5 Estimation · 12.6 Stakeholder Management · 12.7 Technical Vision
13. [Real-World Projects](#13-real-world-projects)
14. [Interview Preparation](#14-interview-preparation)
15. [6-Month Learning Plan](#15-6-month-learning-plan)

---

## 1. Core Java Mastery

> The differentiator between a mid-level and senior engineer is depth here. You must reason about *how* the JVM behaves under load, not just *what* the API does.

### 1.1 Language Foundations & OOP ? (? 1 week)
- **OOP applied** — inheritance vs composition, static vs dynamic dispatch, Liskov substitution in practice, abstraction boundaries.
- **Object contracts** — `equals`/`hashCode` invariants, `Comparable` vs `Comparator`, `toString`, `clone` pitfalls.
- **Immutability** — `final` semantics, defensive copies, immutable value objects, builders for immutables.
- **Enums done right** — stateful enums, strategy-enums, `EnumMap`/`EnumSet`.
- ? Refactor a mutable domain class into an immutable value object with a builder and correct equality.

### 1.2 Collections Framework ? (? 1 week)
- **List/Set/Map internals** — `ArrayList` vs `LinkedList`, `HashMap` (buckets, load factor, treeification at 8, resize/rehash), `LinkedHashMap`, `TreeMap` (red-black tree).
- **Concurrent collections** — `ConcurrentHashMap` (bucket-level locking/CAS), `CopyOnWriteArrayList`, `BlockingQueue` family.
- **Complexity & selection** — Big-O per operation, iteration order guarantees, fail-fast vs fail-safe iterators.
- ? Benchmark `HashMap` vs `TreeMap` vs `ConcurrentHashMap` under mixed read/write load with JMH.

### 1.3 Generics & Type System ? (? 1 week)
- **Generics** — bounded types, wildcards (`? extends` / `? super`), PECS rule, type erasure and its consequences.
- **Reflection & annotations** — runtime metadata, custom annotations, annotation processors (overview).
- ? Build a small type-safe generic repository interface and a custom validation annotation.

### 1.4 Functional Programming & Streams ? (? 1–2 weeks)
- **Functional core** — lambdas, method references, functional interfaces, default/static interface methods.
- **Stream API** — intermediate vs terminal ops, lazy evaluation, `Collectors`, grouping/partitioning, `flatMap`, primitive streams.
- **Optional** — correct use, avoiding `Optional` anti-patterns, chaining with `map`/`flatMap`.
- **Parallel streams** — when they help vs hurt, spliterators, common pool pitfalls.
- ? Rewrite a legacy imperative data-processing class using Streams; benchmark before/after.

### 1.5 Concurrency Fundamentals ? (? 2 weeks)
- **Memory model** — `happens-before`, `volatile`, visibility vs atomicity, false sharing.
- **Synchronization** — `synchronized`, intrinsic locks, `ReentrantLock`, `ReadWriteLock`, `StampedLock`.
- **Coordination primitives** — `Atomic*` / CAS, `CountDownLatch`, `Semaphore`, `CyclicBarrier`, `Phaser`.
- **Concurrency hazards** — deadlock, livelock, starvation, race conditions; detection & avoidance.
- ? Build a bounded producer-consumer pipeline with a `BlockingQueue`; then reproduce and fix a deadlock.

### 1.6 Executors & Asynchronous Programming ? (? 1–2 weeks)
- **Executor framework** — `ThreadPoolExecutor` internals (core/max pool, queue types, rejection policies), `ScheduledExecutorService`, correct pool sizing (CPU vs IO bound).
- **CompletableFuture** — composition (`thenApply`, `thenCompose`, `thenCombine`), exception handling (`exceptionally`, `handle`), custom executors, avoiding blocking pitfalls.
- **ForkJoinPool** — work-stealing, `RecursiveTask`, common pool considerations.
- ? Rebuild the producer-consumer pipeline with `CompletableFuture` + a custom, monitored thread pool.

### 1.7 JVM Internals ? (? 2 weeks)
- **Class loading** — bootstrap/platform/application loaders, delegation model, custom classloaders.
- **Execution engine** — bytecode, interpreter, JIT (C1/C2, tiered compilation), escape analysis, method inlining.
- **Runtime data areas** — method area, heap, stacks, PC registers, native memory.
- ? Disassemble a method with `javap`; observe JIT decisions with `-XX:+PrintCompilation`.

### 1.8 Memory Management & Garbage Collection ? (? 2 weeks)
- **Heap layout** — young/old gen, Eden/Survivor, metaspace, TLABs, off-heap memory.
- **References & leaks** — strong/`Weak`/`Soft`/`Phantom` references, leaks vs legitimate retention, `ThreadLocal` leaks.
- **Collectors** — Serial/Parallel/G1 (default)/ZGC/Shenandoah; pause-time goals, tuning flags (`-Xmx`, `-XX:MaxGCPauseMillis`).
- **Diagnostics** — reading GC logs, heap dumps, allocation profiling.
- ? Profile a memory leak with **VisualVM / JFR / async-profiler**; interpret a heap dump in **Eclipse MAT**.

### 1.9 Design Patterns ? (? 1–2 weeks)
- **GoF patterns** — Strategy, Factory/Abstract Factory, Builder, Observer, Decorator, Proxy, Template Method, Adapter.
- **Enterprise patterns** — Repository, Unit of Work, DTO, Facade, Dependency Injection.
- **Recognize them in Spring** — proxy (AOP), factory (bean factories), template (`*Template` classes), strategy (pluggable beans).
- ? Refactor a `switch`-heavy service into Strategy + Factory; identify three patterns already in your codebase.

### 1.10 Modern Java (21+) ? (? ongoing)
- **Project Loom** — virtual threads, structured concurrency, scoped values; when they replace reactive stacks.
- **Language evolution** — records, sealed classes, pattern matching for `switch`, record patterns, sequenced collections, text blocks.
- **Standards ownership** — team-wide concurrency guidelines and JVM tuning baselines for production services.
- ? Migrate a thread-pool-bound service to virtual threads; measure throughput and tail-latency changes.

---

## 2. Spring Ecosystem

### 2.1 Spring Core & IoC ? (? 3–4 days)
- **Container** — `ApplicationContext` vs `BeanFactory`, bean lifecycle, `BeanPostProcessor`/`BeanFactoryPostProcessor`.
- **Dependency injection** — constructor vs field vs setter, `@Component`/`@Bean`, qualifiers, `@Primary`, circular dependency resolution.
- **Bean scopes** — singleton, prototype, request/session, scoped proxies.
- ? Trace a bean's lifecycle with a custom `BeanPostProcessor` logging each phase.

### 2.2 Spring Boot & Auto-Configuration ? (? 3–4 days)
- **Auto-configuration** — starters, `@EnableAutoConfiguration`, `spring.factories`/`AutoConfiguration.imports`, condition evaluation report.
- **Configuration** — `application.yml` profiles, `@ConfigurationProperties`, relaxed binding, config precedence.
- **Actuator** — health, info, metrics endpoints, custom health indicators.
- ? Inspect the auto-config report (`--debug`) and disable/override one auto-configuration.

### 2.3 Spring MVC ? (? 1 week)
- **Request lifecycle** — DispatcherServlet flow, handler mapping/adapters, `@RestController`, content negotiation.
- **Validation & errors** — `@Valid` + Bean Validation, `@ControllerAdvice`, `ResponseEntityExceptionHandler`, RFC 7807.
- **Filters vs interceptors** — ordering, use cases, `HandlerInterceptor`.
- ? Build a controller with layered exception handling and a consistent error contract.

### 2.4 Spring Data JPA ? (? 1–2 weeks)
- **Repositories** — derived queries, JPQL, `@Query`, native queries, pagination & sorting, projections.
- **Performance** — N+1 problem, fetch strategies, `@EntityGraph`, batch fetching, DTO projections.
- **Lifecycle & mapping** — entity states, `@Transactional` propagation/isolation, optimistic vs pessimistic locking, auditing.
- ? Diagnose an N+1 with SQL logging and fix it with an `@EntityGraph` or fetch join.

### 2.5 Spring AOP ? (? 3–4 days)
- **Proxying** — JDK dynamic proxy vs CGLIB, self-invocation limitation.
- **Pointcuts & advice** — `@Before`/`@After`/`@Around`, execution/annotation pointcuts, advice ordering.
- **Use cases** — logging, metrics, auditing, declarative transactions.
- ? Write an `@Around` aspect that records method timing and publishes a metric.

### 2.6 Spring Security ? (? 1 week)
- **Architecture** — filter chain, `SecurityFilterChain`, authentication vs authorization.
- **AuthN** — `UserDetailsService`, password encoders, authentication providers.
- **AuthZ** — method security (`@PreAuthorize`), URL security, roles vs authorities.
- **Web protections** — CSRF, CORS, security headers.
- ? Build a secured CRUD service with method-level authorization and a custom authentication provider.

### 2.7 Spring Batch ? (? 1–2 weeks)
- **Job model** — jobs, steps, `JobRepository`, `JobLauncher`, execution context.
- **Chunk vs tasklet** — readers/processors/writers, skip/retry policies, restartability.
- **Scaling** — multi-threaded steps, partitioning, remote chunking.
- ? Build a restartable, partitioned job that ingests a large file into a database.

### 2.8 Spring Cloud ? (? 1–2 weeks)
- **Config** — Config Server, refresh scope, encrypted properties.
- **Edge & routing** — Spring Cloud Gateway, LoadBalancer, OpenFeign clients.
- **Observability glue** — Micrometer Tracing, context propagation.
- ? Stand up a Config Server + Gateway routing to two Feign-backed services.

### 2.9 Spring WebFlux & Reactive ? (? 2 weeks)
- **Reactive core** — `Mono`/`Flux`, cold vs hot publishers, operators, backpressure.
- **Reactive stack** — WebClient, R2DBC, functional endpoints, schedulers, blocking-call detection.
- **When to use** — comparison with MVC and virtual threads.
- ? Build a reactive gateway aggregating 3 downstream services with WebClient + resilience.

### 2.10 Platform Engineering & Custom Starters ? (? ongoing)
- **Extensibility** — custom auto-configuration, `@Conditional` beans, custom starters.
- **Standards** — organization-wide parent/BOM, shared library conventions.
- **Decisions** — WebFlux vs virtual threads vs MVC per workload, with evidence.
- ? Create an internal "platform starter" enforcing logging, security, and observability defaults.

---

## 3. Microservices Architecture

### 3.1 Decomposition & Domain-Driven Design ? (? 2 weeks)
- **Boundaries** — bounded contexts, aggregates, entities vs value objects, ubiquitous language.
- **Context mapping** — anti-corruption layer, shared kernel, customer/supplier, avoiding distributed monoliths.
- **Service granularity** — business-capability alignment, single-writer principle, right-sizing services.
- ? Run an event-storming session to derive bounded contexts for a real domain.

### 3.2 Service Communication ? (? 1 week)
- **Sync vs async** — REST/gRPC vs messaging, trade-offs, request coupling.
- **Contracts** — DTOs, payload design, error propagation across services.
- **Timeouts & deadlines** — end-to-end budget, propagating cancellation.
- ? Compare a REST and an async-messaging version of the same inter-service call.

### 3.3 Service Discovery & API Gateway ? (? 1 week)
- **Discovery** — client-side (Eureka) vs server-side; DNS-based discovery in Kubernetes.
- **Gateway** — routing, filters, cross-cutting auth, request aggregation (Spring Cloud Gateway).
- ? Route two services through a gateway with centralized auth and request logging.

### 3.4 Resilience Patterns ? (? 1 week)
- **Resilience4j** — circuit breaker, retry, rate limiter, bulkhead, time limiter.
- **Fallbacks** — graceful degradation, cached responses, default values.
- **Idempotency** — idempotency keys, safe retries.
- ? Add Resilience4j circuit breakers + retries to inter-service calls and simulate downstream failures.

### 3.5 Distributed Data & Transactions ? (? 2 weeks)
- **Why 2PC fails at scale** — locking, coordinator failure, latency.
- **Saga pattern** — orchestration vs choreography, compensating transactions.
- **Outbox pattern** — reliable event publishing, transactional outbox + relay.
- ? Implement an order flow with the **outbox pattern + Saga** across two services; enforce idempotent consumers.

### 3.6 Event-Driven Architecture & CQRS ? (? 2 weeks)
- **EDA** — event sourcing, event-carried state transfer, event vs command semantics.
- **CQRS** — command/query separation, read models, projections, consistency trade-offs.
- **Eventual consistency** — read-your-writes, handling stale reads.
- ? Build a read-optimized projection updated from a domain event stream.

### 3.7 Contracts, Versioning & Governance ? (? ongoing)
- **Contract-first** — schema/API contracts as source of truth, backward/forward compatibility.
- **Versioning** — non-breaking evolution, deprecation policy across teams.
- **Standards** — service templates, ownership, golden paths.
- ? Define a versioning + backward-compatibility standard and enforce it in CI.

---

## 4. Database Expertise

### 4.1 Transactions & Isolation ? (? 4–5 days)
- **ACID & isolation** — read committed, repeatable read, serializable; lost updates, dirty/phantom/non-repeatable reads.
- **Spring `@Transactional`** — propagation, rollback rules, read-only, self-invocation pitfall.
- **Locking** — optimistic vs pessimistic, deadlock avoidance.
- ? Reproduce a lost update, then prevent it with the right isolation level or locking.

### 4.2 SQL Optimization & Query Tuning ? (? 1 week)
- **Execution plans** — `EXPLAIN`/`EXPLAIN ANALYZE`, cost model, cardinality estimates.
- **Join strategies** — nested loop, hash join, merge join; avoiding full scans.
- **Query shape** — predicate pushdown, sargable predicates, avoiding N+1 at the DB.
- ? Take a slow endpoint, analyze the query plan, and prove the latency drop after tuning.

### 4.3 Indexing ? (? 4–5 days)
- **Index types** — B-tree vs hash vs GIN/GiST; when each applies.
- **Design** — composite column order, selectivity, covering/index-only scans.
- **Costs** — write amplification, index bloat, when indexes hurt.
- ? Add the right composite index to remove a full scan and verify with the plan.

### 4.4 PostgreSQL Deep Dive ? (? 1 week)
- **MVCC & VACUUM** — tuple visibility, bloat, autovacuum tuning.
- **Connection pooling** — PgBouncer/HikariCP sizing.
- **Advanced types** — JSONB, arrays, full-text search basics.
- ? Tune autovacuum and pooling for a write-heavy table and measure the effect.

### 4.5 Caching with Redis ? (? 4–5 days)
- **Patterns** — cache-aside, write-through, write-behind, TTL/eviction policies.
- **Failure modes** — cache stampede, thundering herd, hot keys; mitigation.
- **Data structures** — strings, hashes, sorted sets, counters.
- ? Add cache-aside to a hot read path with stampede protection and measure hit ratio.

### 4.6 Partitioning & Sharding ? (? 1 week)
- **Partitioning** — range/list/hash, partition pruning, local vs global indexes.
- **Sharding** — shard keys, resharding, cross-shard queries.
- ? Design a partitioned schema for a high-write table and load-test it.

### 4.7 Oracle (Legacy Enterprise) ? (? 4–5 days)
- **Tuning** — hints, AWR reports, bind variables, sequences vs identity.
- **PL/SQL basics** — packages, procedures for legacy integration.
- ? Read an AWR report and identify the top SQL by elapsed time.

### 4.8 NoSQL & MongoDB ? (? 1 week)
- **Document modeling** — embedding vs referencing, schema design for access patterns.
- **Operations** — aggregation pipeline, indexes, replica sets, sharding.
- **Distributed caching** — topologies, invalidation, consistency vs freshness.
- ? Model a domain in MongoDB optimized for its top 3 queries.

### 4.9 Polyglot Persistence & Data Lifecycle ? (? ongoing)
- **Store selection** — SQL vs document vs KV vs search per bounded context.
- **Lifecycle** — archival, retention, multi-region replication strategy.
- ? Write an ADR choosing the datastore for a new bounded context with trade-offs.

---

## 5. API Development

### 5.1 REST Design ? (? 4–5 days)
- **Resource modeling** — nouns over verbs, correct HTTP verbs/status codes, HATEOAS awareness.
- **Collection semantics** — pagination, filtering, sorting, sparse fieldsets.
- **Reliability** — idempotency keys, safe retries, conditional requests (ETags).
- ? Design a resource-oriented API with pagination and idempotent writes.

### 5.2 API Documentation (OpenAPI) ? (? 3–4 days)
- **Spec** — spec-first vs code-first (springdoc), schema components, examples.
- **Contract as docs** — generated clients, mock servers, linting the spec.
- ? Publish an OpenAPI-documented API with consistent error responses (RFC 7807).

### 5.3 Versioning & Evolution ? (? 2–3 days)
- **Strategies** — URI vs header vs media-type versioning; trade-offs.
- **Compatibility** — additive changes, deprecation policy, sunset headers.
- ? Evolve an endpoint with a non-breaking change and a documented deprecation path.

### 5.4 Authentication & Authorization ? (? 3–4 days)
- **Models** — session vs token, RBAC vs ABAC, scopes vs roles.
- **Enforcement points** — gateway vs service, defense in depth.
- ? Model access with both RBAC and ABAC and compare enforcement.

### 5.5 OAuth2 / OIDC / JWT ? (? 1 week)
- **OAuth2/OIDC flows** — authorization code + PKCE, client credentials, scopes, tokens.
- **JWT** — structure, signing (HS256 vs RS256), validation, refresh tokens, revocation.
- **Resource server** — Spring Security config, JWK sets, audience/issuer checks.
- ? Secure a service as an OAuth2 resource server validating JWTs against a JWK set.

### 5.6 API Security Hardening ? (? 3–4 days)
- **Input/output** — validation, output encoding, mass-assignment protection.
- **Transport** — TLS, HSTS, secure headers.
- ? Add input validation and mass-assignment protection to a write endpoint.

### 5.7 Rate Limiting & Throttling ? (? 2–3 days)
- **Algorithms** — token bucket, leaky bucket, fixed/sliding window.
- **Enforcement** — per-client quotas, gateway-level limits, distributed counters (Redis).
- ? Enforce per-client rate limits at the gateway backed by Redis.

---

## 6. Messaging & Integration

### 6.1 Messaging Fundamentals ? (? 2–3 days)
- **Models** — queue vs topic, pub/sub vs point-to-point, push vs pull.
- **Delivery semantics** — at-most / at-least / exactly-once, ordering guarantees.
- **Reliability primitives** — acks, redelivery, dead-letter queues, idempotent consumers.
- ? Diagram the delivery guarantees of a producer?broker?consumer flow.

### 6.2 Kafka ? (? 1 week)
- **Topology** — topics, partitions, offsets, consumer groups, rebalancing.
- **Ordering & keys** — partition keys, ordering guarantees, hot partitions.
- **Producer/consumer tuning** — acks, idempotent producer, batching, `max.poll`, commit strategies.
- ? Build an async order processor with Kafka; handle retries and a dead-letter topic.

### 6.3 RabbitMQ ? (? 3–4 days)
- **Routing** — exchanges (direct/topic/fanout/headers), queues, bindings.
- **Reliability** — ack/nack, publisher confirms, DLQ, TTL, priority queues.
- ? Implement a topic-exchange routing scheme with a DLQ for poison messages.

### 6.4 ActiveMQ / JMS ? (? 2–3 days)
- **JMS model** — queues vs topics, sessions, message types, transactions.
- **Legacy integration** — enterprise messaging, bridging to modern brokers.
- ? Wire a JMS producer/consumer for a legacy-style integration.

### 6.5 Kafka Streams & Schema Registry ? (? 1 week)
- **Stateful processing** — KStream/KTable, windowing, joins, state stores.
- **Exactly-once** — transactions, processing guarantees.
- **Schemas** — Avro/Protobuf, Schema Registry, compatibility rules & evolution.
- ? Implement a streaming aggregation with Kafka Streams + schema registry and evolve a schema safely.

### 6.6 Reliable Async Processing ? (? 3–4 days)
- **Backpressure & lag** — consumer-lag monitoring, flow control.
- **Failure handling** — poison-message handling, retry topics, DLQ replay.
- ? Add consumer-lag alerting and a retry-topic strategy to a consumer.

### 6.7 Enterprise Integration Patterns ? (? 3–4 days)
- **EIP catalog** — message router, aggregator, splitter, content enricher, claim check.
- **Frameworks** — Spring Integration / Apache Camel.
- ? Build a splitter?enricher?aggregator pipeline with Spring Integration or Camel.

---

## 7. Cloud & DevOps

### 7.1 Containerization (Docker) ? (? 1 week)
- **Images** — multi-stage builds, layer caching, distroless/JRE base images, image size & CVE surface.
- **Runtime** — resource limits, JVM container awareness, `docker-compose` for local stacks.
- ? Containerize a Spring Boot app with a multi-stage build and a minimal base image.

### 7.2 CI/CD Pipelines ? (? 1 week)
- **Pipeline stages** — build/test/scan/deploy, artifact promotion, environments.
- **Tooling** — Jenkins / GitHub Actions, matrix builds, caching, reusable workflows.
- **Secrets** — secret stores, OIDC-based cloud auth, least privilege.
- ? Build a full GitHub Actions pipeline: test ? scan ? build image ? deploy.

### 7.3 Kubernetes ? (? 2 weeks)
- **Workloads** — pods, deployments, ReplicaSets, StatefulSets, Jobs/CronJobs.
- **Networking & config** — services, ingress, ConfigMaps/Secrets.
- **Ops** — probes, resource requests/limits, HPA, rolling vs blue-green vs canary.
- ? Deploy a service with probes, an HPA, and a canary rollout.

### 7.4 Helm & Packaging ? (? 3–4 days)
- **Charts** — templating, values, `_helpers.tpl`, releases, upgrades/rollbacks.
- **Distribution** — chart repositories, versioning, dependencies.
- ? Package a service as a Helm chart with environment-specific values.

### 7.5 Infrastructure as Code (Terraform) ? (? 1 week)
- **Core** — providers, resources, variables, outputs, `plan`/`apply`.
- **State** — remote backends, locking, workspaces, drift detection.
- **Structure** — modules, composition, environment layering.
- ? Provision a cluster + managed DB with reusable Terraform modules and remote state.

### 7.6 Cloud Platforms (Azure & AWS) ? (? 1–2 weeks)
- **Compute** — AKS/EKS, App Service/ECS, serverless options.
- **Managed services** — databases, messaging (Event Hubs/SQS/SNS), object storage.
- **Security** — IAM, secrets (Key Vault / Secrets Manager), networking basics.
- ? Deploy a service to a managed Kubernetes cluster using platform secrets and a managed DB.

### 7.7 GitOps & Progressive Delivery ? (? ongoing)
- **GitOps** — ArgoCD/Flux, declarative deployments, drift reconciliation.
- **Progressive delivery** — canary/blue-green automation, feature flags, rollback policy.
- **Trade-offs** — cost/reliability, multi-region DR standards.
- ? Set up GitOps-driven deployment with automated canary analysis.

---

## 8. System Design

> This section most directly determines whether you're seen as a Tech Lead / Architect.

### 8.1 High-Level Design (HLD) ? (? 2 weeks)
- **Decomposition** — component/service boundaries, data flow, API contracts.
- **Technology choices** — justification, build-vs-buy, constraints & assumptions.
- ? Produce an HLD diagram + component responsibilities for a mid-size system.

### 8.2 Low-Level Design (LLD) ? (? 1–2 weeks)
- **Modeling** — class and sequence diagrams, interface design, SOLID.
- **Patterns in design** — applying GoF/enterprise patterns to a concrete problem.
- ? Do the LLD (classes + sequences) for one component from your HLD.

### 8.3 Scalability ? (? 1 week)
- **Scaling axes** — vertical vs horizontal, stateless services, sharding, read replicas.
- **Bottlenecks** — identifying and removing chokepoints, back-pressure.
- ? Take a single-node design and produce a horizontally scalable version.

### 8.4 Availability & Reliability ? (? 4–5 days)
- **Redundancy** — failover, health checks, multi-AZ/region.
- **SLAs/SLOs/SLIs** — the "nines", error budgets.
- ? Define SLOs and an error budget policy for a service.

### 8.5 Fault Tolerance ? (? 4–5 days)
- **Patterns** — retries with backoff+jitter, timeouts, bulkheads, circuit breakers.
- **Degradation** — graceful degradation, load shedding, fallback paths.
- ? Add layered fault-tolerance to a critical path and test with fault injection.

### 8.6 Consistency & CAP ? (? 4–5 days)
- **Theory** — CAP, PACELC, CP vs AP trade-offs.
- **Models** — strong/eventual/causal consistency, quorum reads/writes.
- ? Justify a consistency model choice for a chosen scenario with an ADR.

### 8.7 Load Balancing ? (? 2–3 days)
- **Layers** — L4 vs L7, ingress, service mesh basics.
- **Algorithms** — round robin, least connections, consistent hashing, sticky sessions.
- ? Compare load-balancing algorithms for a stateful vs stateless workload.

### 8.8 Caching Strategies ? (? 2–3 days)
- **Layers** — client/CDN/gateway/app/DB caches.
- **Policies** — cache-aside vs read-through vs write-behind, invalidation, TTL.
- ? Design a multi-layer caching strategy and its invalidation plan.

### 8.9 Architecture Governance ? (? ongoing)
- **Decision records** — lead design reviews, produce ADRs and reference architectures.
- **Estimation** — capacity planning and back-of-the-envelope estimation as a habit.
- ? Design 3 systems end-to-end (URL shortener, rate limiter, notification system); write ADRs; then design one to 10M+ users (newsfeed, ride-sharing dispatch, or payment ledger).

---

## 9. Testing

### 9.1 Unit Testing (JUnit 5) ? (? 3–4 days)
- **Lifecycle & structure** — `@BeforeEach`/`@AfterEach`, nested tests, tags, assertions.
- **Data-driven** — parameterized tests, dynamic tests, extensions.
- ? Convert a set of repetitive tests into parameterized tests.

### 9.2 Mocking (Mockito) ? (? 3–4 days)
- **Doubles** — mocks vs spies vs stubs, `@InjectMocks`, argument captors.
- **Behavior verification** — verifying interactions, avoiding over-mocking.
- ? Add interaction-based tests for a service with mocked collaborators.

### 9.3 Integration & Slice Testing ? (? 4–5 days)
- **Full context** — `@SpringBootTest`, test config, profiles.
- **Slices** — `@WebMvcTest`, `@DataJpaTest`, `MockMvc`/`WebTestClient`.
- ? Cover a controller with a `@WebMvcTest` and a repository with `@DataJpaTest`.

### 9.4 TestContainers ? (? 3–4 days)
- **Real dependencies** — Postgres/Kafka/Redis in tests, lifecycle, reusable containers.
- **CI integration** — startup cost, parallelization, Docker-in-CI.
- ? Convert brittle mocked DB tests to TestContainers-based integration tests.

### 9.5 Contract Testing ? (? 3–4 days)
- **Consumer-driven** — Spring Cloud Contract / Pact, provider verification.
- **Pipeline** — publishing/verifying contracts in CI.
- ? Add consumer-driven contract tests between two services in CI.

### 9.6 Performance Testing ? (? 3–4 days)
- **Tools** — JMeter / Gatling / k6, scripting realistic scenarios.
- **Test types** — load vs stress vs soak; defining SLIs and pass/fail thresholds.
- ? Add a Gatling/k6 load test to CI with latency and error-rate gates.

### 9.7 Test Strategy & Quality ? (? ongoing)
- **Test pyramid** — balance across layers, avoiding ice-cream-cone anti-pattern.
- **Reliability** — flaky-test elimination, mutation testing (PIT), coverage as a signal not a target.
- ? Introduce mutation testing and hunt down two flaky tests.

---

## 10. Security

### 10.1 OWASP Top 10 ? (? 4–5 days)
- **Top risks** — injection, broken auth, XSS, SSRF, insecure deserialization, security misconfiguration.
- **Spring mitigations** — parameterized queries, output encoding, safe deserialization, secure defaults.
- ? Map each OWASP risk to a concrete mitigation in one of your services.

### 10.2 Secure Coding Practices ? (? 3–4 days)
- **Input/trust boundaries** — validation, output encoding, least privilege, safe defaults.
- **Supply chain** — dependency scanning (OWASP Dependency-Check, Snyk), SBOMs.
- ? Run a dependency + SAST scan and remediate the top findings.

### 10.3 Authentication Mechanisms ? (? 3–4 days)
- **Protocols** — OIDC, mTLS, API keys, MFA concepts.
- **Session/token security** — secure cookies, token lifetime, revocation.
- ? Add mTLS between two internal services.

### 10.4 Cryptography & Key Management ? (? 3–4 days)
- **Primitives** — symmetric vs asymmetric, TLS handshake, hashing (bcrypt/argon2).
- **Data protection** — encryption at rest, KMS/Key Vault, secrets rotation.
- ? Encrypt a sensitive field at rest with a KMS-managed key and rotate it.

### 10.5 Auditing, Compliance & Threat Modeling ? (? 3–4 days)
- **Auditing** — audit logging, tamper evidence, log integrity.
- **Compliance** — PCI-DSS/GDPR awareness.
- **Threat modeling** — STRIDE, attack trees, mitigation backlog.
- ? Threat-model one service with STRIDE and produce a mitigation backlog.

---

## 11. Observability

### 11.1 Logging ? (? 2–3 days)
- **Structured logging** — JSON logs, log levels, MDC, avoiding sensitive-data leakage.
- **Correlation** — correlation/trace IDs propagated across services.
- ? Add structured logging with correlation IDs across two services.

### 11.2 Log Aggregation (ELK) ? (? 3–4 days)
- **Pipeline** — Elasticsearch, Logstash/Beats, Kibana; shipping and indexing.
- **Analysis** — dashboards, saved searches, retention.
- ? Ship logs to ELK and build a dashboard for error triage.

### 11.3 Metrics (Prometheus & Grafana) ? (? 4–5 days)
- **Instrumentation** — Micrometer, counters/gauges/timers, labels/cardinality.
- **Methodologies** — RED/USE methods, dashboards, alerting rules.
- ? Expose custom metrics and alert on p99 latency and error rate.

### 11.4 Distributed Tracing (OpenTelemetry) ? (? 3–4 days)
- **Tracing model** — spans, context propagation, baggage, sampling.
- **Exporters** — Jaeger/Tempo, OTLP collector.
- ? Instrument a multi-service flow with OpenTelemetry and trace a request end-to-end.

### 11.5 Unified Observability & SLOs ? (? ongoing)
- **Three pillars** — logs + metrics + traces correlated by trace ID.
- **SLOs & alerting** — symptom-based alerts, error budgets, on-call hygiene.
- ? Build a Grafana dashboard correlating traces, metrics, and logs for one flow.

---

## 12. Leadership Skills

> Technical depth gets you to Senior. These skills get you to Lead and Architect.

### 12.1 Code Reviews ? (ongoing)
- **Review focus** — design & maintainability over syntax, correctness, security.
- **Process** — constructive feedback, review SLAs, when to pair instead.
- ? Write a team code-review checklist and apply it to five PRs.

### 12.2 Technical Writing ? (ongoing)
- **Documents** — ADRs, design docs, runbooks, READMEs.
- **Diagrams** — C4 model, sequence/flow diagrams.
- ? Write an ADR and a one-page design doc for a real decision.

### 12.3 Agile & Delivery ? (ongoing)
- **Practices** — refinement, story slicing, definition of done, retrospectives.
- **Tech debt** — making it visible and prioritized.
- ? Slice a large story into thin vertical increments with a clear DoD.

### 12.4 Mentoring ? (ongoing)
- **Growth** — growth plans, pairing, delegation with safety nets.
- **Feedback** — SBI framework, radical candor, coaching vs directing.
- ? Mentor a junior through a feature end-to-end.

### 12.5 Estimation ? (ongoing)
- **Techniques** — decomposition, uncertainty ranges, spikes.
- **Communicating risk** — avoiding false precision, surfacing assumptions.
- ? Estimate an epic with ranges and track actuals vs estimates.

### 12.6 Stakeholder Management ? (ongoing)
- **Translation** — tech to business value, managing expectations.
- **Influence** — saying "no" with alternatives, influence without authority.
- ? Present a technical trade-off to non-technical stakeholders and drive a decision.

### 12.7 Technical Vision & Standards ? (ongoing)
- **Direction** — set technical vision & standards, drive cross-team alignment.
- **Organization** — build consensus, own hiring and tech brand.
- ? Run a design review and publish a reference architecture the team adopts.

---

## 13. Real-World Projects

> Build these to consolidate the roadmap. Each is résumé- and interview-grade. Put them on GitHub with READMEs, diagrams, and ADRs.

### Project 1 — E-Commerce Order Platform (Microservices + Saga)
- **Architecture:** API Gateway ? services (Catalog, Cart, Order, Payment, Inventory, Notification). Async communication via Kafka. Saga orchestrator coordinates Order?Payment?Inventory with compensating transactions. Outbox pattern guarantees event delivery. CQRS on the Order read model.
- **Tech Stack:** Spring Boot, Spring Cloud Gateway, Kafka, Resilience4j, PostgreSQL, Redis, Docker, Kubernetes, Helm.
- **Database Design:** Per-service DB (database-per-service). Orders (RDBMS, partitioned by date), Catalog (Postgres + Redis cache), read-optimized order projection.
- **Deployment:** Kubernetes with Helm, HPA on Order service, canary releases, secrets via Key Vault/Secrets Manager.
- **Security:** OAuth2/OIDC at gateway, JWT propagation, rate limiting, TLS everywhere, PCI-aware payment isolation.

### Project 2 — Real-Time Analytics / Event Streaming Pipeline
- **Architecture:** Ingestion API ? Kafka ? Kafka Streams (windowed aggregations) ? materialized views in Postgres/Elasticsearch ? dashboard API. Schema Registry enforces compatibility.
- **Tech Stack:** Spring Boot, Kafka + Kafka Streams, Avro + Schema Registry, Elasticsearch, WebFlux, Grafana.
- **Database Design:** Elasticsearch for search/aggregation, Postgres for aggregates, Redis for hot counters.
- **Deployment:** Kubernetes, StatefulSets for Kafka, autoscaled consumers, consumer-lag alerting.
- **Security:** SASL/TLS on Kafka, ACLs per topic, PII masking in streams.

### Project 3 — Multi-Tenant SaaS Platform
- **Architecture:** Tenant-aware services, gateway routing per tenant, config-driven feature flags, per-tenant isolation (schema-per-tenant or row-level security).
- **Tech Stack:** Spring Boot, Spring Security, Spring Data JPA, Postgres (RLS), Redis, Terraform, AWS/Azure.
- **Database Design:** Schema-per-tenant vs shared-schema + tenant_id (discuss trade-offs); connection routing.
- **Deployment:** IaC with Terraform, blue-green deploys, per-tenant observability.
- **Security:** Strict tenant isolation, RBAC/ABAC, audit logging, encryption at rest, secrets rotation.

### Project 4 — Reactive API Aggregation Gateway (BFF)
- **Architecture:** Backend-for-Frontend aggregating multiple downstream services non-blockingly with WebFlux + WebClient; circuit breakers, response caching, request coalescing.
- **Tech Stack:** Spring WebFlux, Reactor, Resilience4j, Redis, OpenTelemetry, Docker.
- **Database Design:** Redis for response cache; no primary datastore (stateless aggregator).
- **Deployment:** Kubernetes, HPA on latency/CPU, canary.
- **Security:** OAuth2 resource server, per-route rate limits, request validation.

### Project 5 — Batch + Reporting System (Spring Batch)
- **Architecture:** Scheduled Spring Batch jobs (partitioned) ingest large files ? transform ? load to warehouse; failure restartability; reporting API on top.
- **Tech Stack:** Spring Batch, Spring Boot, Postgres/Oracle, Quartz, Docker, Jenkins/GitHub Actions.
- **Database Design:** Staging + partitioned fact tables, indexed reporting views, archival strategy.
- **Deployment:** Kubernetes CronJobs or scheduled pods, resource-limited batch pods.
- **Security:** Encrypted file transfer, data masking, least-privilege DB accounts, audit trail.

---

## 14. Interview Preparation

> Rather than memorizing answers, master the **themes**. Below are the high-yield topic clusters that generate the "Top N" question banks — study the concept, then drill questions from each cluster.

### Core Java — 100-question coverage map
1. OOP & language: inheritance vs composition, `equals`/`hashCode` contract, `final`, `static`, immutability, enums.
2. Collections: `HashMap` internals, fail-fast vs fail-safe, `Comparable` vs `Comparator`, concurrent collections.
3. Concurrency: thread lifecycle, `synchronized` vs `Lock`, `volatile`, `wait`/`notify`, executors, `CompletableFuture`, deadlock/livelock.
4. JVM/memory: class loading, GC, memory leaks, `String` pool, stack vs heap.
5. Java 8–21: streams, `Optional`, records, sealed classes, virtual threads.
6. Exceptions, generics (type erasure, wildcards), serialization, I/O & NIO.
7. Design patterns & SOLID applied in Java.

### Spring Boot — 100-question coverage map
1. IoC/DI, bean scopes & lifecycle, `@Autowired` resolution, circular dependencies.
2. Auto-configuration, starters, `@Conditional`, profiles, externalized config.
3. Spring MVC request flow, exception handling, validation, filters vs interceptors.
4. Spring Data JPA: repositories, N+1, transactions, propagation, isolation, `@Transactional` self-invocation pitfall.
5. Spring Security: filter chain, OAuth2/JWT, method security.
6. Actuator, Micrometer, testing slices, `@SpringBootTest`.
7. AOP, caching, scheduling, async, WebFlux vs MVC.

### Microservices — 50-question coverage map
1. Decomposition, DDD bounded contexts, service granularity.
2. Communication: sync vs async, REST vs messaging, service discovery.
3. Resilience: circuit breaker, retry, bulkhead, timeout, idempotency.
4. Data: database-per-service, Saga, outbox, CQRS, event sourcing.
5. Gateway, config, distributed tracing, distributed transactions, consistency.
6. Deployment, versioning, observability, security.

### System Design — 50-question coverage map
1. Fundamentals: scalability, availability, CAP/PACELC, consistency models.
2. Building blocks: load balancers, caching, CDN, message queues, databases (SQL/NoSQL), sharding, replication.
3. Estimation & capacity planning.
4. Classic designs: URL shortener, rate limiter, chat/notification, newsfeed, ride-sharing, payment system, distributed cache, search autocomplete.
5. Trade-off articulation & ADR writing.

**Practice cadence:** 3–5 questions/day per active cluster, spaced repetition, and mock interviews (peer or platform-based) weekly.

---

## 15. 6-Month Learning Plan

> Assumes ~8–10 focused hours/week. Adjust to your baseline — skip refreshers you already own. Each month ends with a **shippable artifact**.

### Month 1 — Core Java & Spring Foundations (Deepen)
| Week | Milestones |
|------|-----------|
| 1 | JVM internals, memory model, GC fundamentals; read GC logs. |
| 2 | Concurrency: executors, `CompletableFuture`, locks; build producer-consumer pipeline. |
| 3 | Collections internals + Java 17/21 features (records, sealed, pattern matching, virtual threads). |
| 4 | Spring Core/Boot/AOP refresh; build secured CRUD service with AOP audit aspect. |

**Goal:** Rock-solid fundamentals. **Artifact:** Concurrency demo + profiled memory-leak write-up.

### Month 2 — Data, APIs & Testing
| Week | Milestones |
|------|-----------|
| 1 | SQL tuning, indexing, `EXPLAIN ANALYZE`; optimize a slow endpoint. |
| 2 | Spring Data JPA deep dive (N+1, fetch, transactions) + Redis caching. |
| 3 | REST best practices, OpenAPI, versioning, error contracts. |
| 4 | JUnit 5, Mockito, TestContainers integration tests. |

**Goal:** Production-grade data & API skills. **Artifact:** Documented, tested, cached REST service.

### Month 3 — Microservices & Messaging
| Week | Milestones |
|------|-----------|
| 1 | Service discovery, gateway, Feign; Resilience4j circuit breakers. |
| 2 | Kafka fundamentals; async order processor with DLQ. |
| 3 | Saga + outbox pattern across two services; idempotent consumers. |
| 4 | DDD & CQRS; event-storm a domain and model bounded contexts. |

**Goal:** Design resilient distributed systems. **Artifact:** Project 1 (E-Commerce Saga) MVP.

### Month 4 — Cloud, DevOps & Observability
| Week | Milestones |
|------|-----------|
| 1 | Docker multi-stage builds + GitHub Actions CI pipeline. |
| 2 | Kubernetes core objects + Helm chart; deploy a service. |
| 3 | Terraform IaC to provision cluster + managed DB. |
| 4 | Prometheus/Grafana + OpenTelemetry tracing across services. |

**Goal:** Ship & operate services. **Artifact:** Fully deployed, observable service via Helm + Terraform.

### Month 5 — System Design & Security
| Week | Milestones |
|------|-----------|
| 1 | System design fundamentals; design URL shortener + rate limiter with ADRs. |
| 2 | Design newsfeed/notification system; capacity estimation practice. |
| 3 | OWASP Top 10, OAuth2/OIDC/JWT hardening; secure a resource server. |
| 4 | Threat modeling (STRIDE), encryption & secrets management, dependency scanning. |

**Goal:** Think like an architect; secure by design. **Artifact:** 3 system-design docs + hardened, threat-modeled service.

### Month 6 — Leadership, Advanced Topics & Interview Prep
| Week | Milestones |
|------|-----------|
| 1 | Spring WebFlux/reactive + BFF aggregation (Project 4). |
| 2 | Spring Batch reporting system (Project 5); performance/contract testing. |
| 3 | Leadership: run a design review, write ADRs, mentor a peer, practice estimation. |
| 4 | Interview drills: Core Java, Spring, Microservices, System Design; mock interviews. |

**Goal:** Lead technically & interview at target level. **Artifact:** 2 more portfolio projects + interview-ready across all clusters.

### Recommended Certifications
- **Cloud:** AWS Certified Solutions Architect – Associate **or** Microsoft Certified: Azure Solutions Architect Expert (AZ-305).
- **Kubernetes:** CKA (Certified Kubernetes Administrator) or CKAD (Developer).
- **Spring:** VMware Spring Professional / Spring Certified Professional.
- **Java:** Oracle Certified Professional (OCP) Java SE 17/21 — optional if you already have deep experience.
- **Terraform:** HashiCorp Certified: Terraform Associate.
- **Security (optional):** CompTIA Security+ for foundational security literacy.

> **Priority order for Architect track:** 1 Cloud architect cert ? CKA/CKAD ? Terraform Associate ? Spring Professional.

---

## Final Advice

1. **Depth beats breadth for seniority** — pick one area (e.g., distributed systems + Kafka) and go truly deep; be "the person" for it.
2. **Build in public** — GitHub, blog posts, internal tech talks. Visibility compounds.
3. **Write ADRs habitually** — articulating trade-offs is the core skill of an architect.
4. **Read production incidents & post-mortems** — real systems teach fault tolerance better than tutorials.
5. **Grow people, not just code** — Tech Lead and Architect roles are measured by team leverage, not lines written.

*Track progress by ticking off levels per topic. Revisit this roadmap monthly and adjust based on your target role and company's tech stack.*
