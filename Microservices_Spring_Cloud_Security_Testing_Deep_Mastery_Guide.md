# Microservices Architecture, Spring Cloud, Security & Testing — Comprehensive Mastery Guide

> Scope is intentionally limited to the **6 modules provided**. No new major modules are added.
>
> The goal is to turn the syllabus into a senior-level mastery guide by making the existing topics more granular, connecting them to internals and production behavior, and adding the best books/references for each module and major submodule.

---

# How to Use This Guide

For every topic, follow this mastery cycle:

1. **Understand** — What is it and why does it exist?
2. **Design** — When should it be used and when should it not?
3. **Implement** — Build a working example.
4. **Understand internals** — Know the protocol, lifecycle, state transitions, or framework mechanism.
5. **Failure handling** — Understand timeouts, retries, partial failures, duplicates, and recovery.
6. **Performance** — Understand throughput, latency, resource usage, and scaling.
7. **Security** — Understand trust boundaries and attack surfaces.
8. **Observability** — Know what metrics, logs, and traces reveal problems.
9. **Test** — Unit, integration, contract, and failure testing where appropriate.
10. **Production** — Be able to diagnose and explain a real-world failure.

---

# Module 1 — Microservices Architecture & Design Patterns

# 1.1 Microservices Foundations

- [ ] What is a microservice?
- [ ] Characteristics of a microservice
- [ ] Microservices vs monolith
- [ ] Modular monolith vs microservices
- [ ] Distributed-system trade-offs
- [ ] Independent deployment
- [ ] Independent scaling
- [ ] Independent ownership
- [ ] Service boundaries
- [ ] Network as a failure boundary
- [ ] Data ownership
- [ ] Operational complexity
- [ ] Distributed transactions
- [ ] Eventual consistency
- [ ] CAP theorem
- [ ] PACELC concepts
- [ ] Availability vs consistency trade-offs
- [ ] Synchronous vs asynchronous architecture
- [ ] Coupling and cohesion
- [ ] Failure domains

### Mastery Exercise
- [ ] Take a monolithic application and identify candidate service boundaries
- [ ] Explain why some boundaries should NOT become separate services

### Best Books
**Primary:** *Building Microservices, 2nd Edition* — Sam Newman

**Deep Dive:** *Microservices Patterns* — Chris Richardson

**Architecture Reference:** *Fundamentals of Software Architecture* — Mark Richards & Neal Ford

---

# 1.2 Twelve-Factor & Cloud-Native Principles

- [ ] Codebase
- [ ] Dependencies
- [ ] Config
- [ ] Backing services
- [ ] Build/release/run separation
- [ ] Stateless processes
- [ ] Port binding
- [ ] Concurrency
- [ ] Disposability
- [ ] Dev/prod parity
- [ ] Logs
- [ ] Admin processes
- [ ] Applying 12-factor principles to Spring Boot
- [ ] Externalized configuration
- [ ] Container-friendly applications
- [ ] Graceful startup/shutdown
- [ ] Stateless service design

### Best References
**Primary:** *The Twelve-Factor App* — Adam Wiggins / official methodology

**Practical:** *Building Microservices, 2nd Edition* — Sam Newman

---

# 1.3 Monolith to Microservices

- [ ] Why organizations migrate
- [ ] When NOT to migrate
- [ ] Identifying bounded contexts
- [ ] Identifying seams in a monolith
- [ ] Strangler Fig Pattern
- [ ] Incremental migration
- [ ] Routing old vs new functionality
- [ ] Data migration challenges
- [ ] Dual-write risks
- [ ] Anti-Corruption Layer
- [ ] Legacy integration
- [ ] Parallel running
- [ ] Migration rollback strategy
- [ ] Observability during migration
- [ ] Measuring migration success

### Strangler Fig — Step-by-Step
- [ ] Identify functionality
- [ ] Define new boundary
- [ ] Introduce routing/facade
- [ ] Build new service
- [ ] Redirect traffic
- [ ] Migrate data where necessary
- [ ] Observe and validate
- [ ] Remove legacy path

### Best Books
**Primary:** *Building Microservices, 2nd Edition* — Sam Newman

**Pattern Reference:** *Microservices Patterns* — Chris Richardson

---

# 1.4 Sidecar, Ambassador & BFF

## Sidecar
- [ ] Sidecar pattern
- [ ] Separate process/container
- [ ] Shared lifecycle
- [ ] Networking responsibilities
- [ ] Observability responsibilities
- [ ] Advantages and disadvantages

## Ambassador
- [ ] Ambassador pattern
- [ ] External-service communication
- [ ] Proxy responsibilities
- [ ] Retry/connection management

## Backend for Frontend
- [ ] BFF concept
- [ ] One backend per client experience
- [ ] Mobile BFF
- [ ] Web BFF
- [ ] Aggregation
- [ ] Client-specific APIs
- [ ] BFF vs API Gateway
- [ ] When BFF becomes a bottleneck

### Best Book
**Building Microservices, 2nd Edition — Sam Newman**

---

# 1.5 Domain-Driven Design — Strategic Design

- [ ] Domain vs subdomain
- [ ] Core domain
- [ ] Supporting subdomain
- [ ] Generic subdomain
- [ ] Ubiquitous Language
- [ ] Bounded Context
- [ ] Context boundaries
- [ ] Context Mapping
- [ ] Partnership
- [ ] Shared Kernel
- [ ] Customer/Supplier
- [ ] Conformist
- [ ] Anti-Corruption Layer
- [ ] Open Host Service
- [ ] Published Language
- [ ] Separate Ways
- [ ] Mapping bounded contexts to microservices

### Best Book
**Domain-Driven Design: Tackling Complexity in the Heart of Software — Eric Evans**

### Practical Microservices Companion
**Microservices Patterns — Chris Richardson**

---

# 1.6 DDD — Tactical Design

- [ ] Entity
- [ ] Identity
- [ ] Value Object
- [ ] Aggregate
- [ ] Aggregate Root
- [ ] Aggregate boundaries
- [ ] Invariants
- [ ] Domain Service
- [ ] Domain Repository
- [ ] Application Service
- [ ] Domain Event
- [ ] Integration Event
- [ ] Factories
- [ ] Domain logic vs application logic
- [ ] Transaction boundary around aggregates
- [ ] Aggregate size and performance trade-offs

### Critical Concepts
- [ ] Why aggregates define consistency boundaries
- [ ] Why one transaction should generally not span unrelated aggregates
- [ ] Why entities should protect invariants
- [ ] Domain event vs integration event

### Best Book
**Domain-Driven Design — Eric Evans**

### Practical Companion
**Implementing Domain-Driven Design — Vaughn Vernon**

---

# 1.7 Database-per-Service & Distributed Data

- [ ] Database-per-service
- [ ] Private database ownership
- [ ] Shared database anti-pattern
- [ ] Cross-service queries
- [ ] Data duplication
- [ ] Materialized views
- [ ] Data synchronization
- [ ] Eventual consistency
- [ ] Read models
- [ ] Data ownership boundaries
- [ ] Distributed reporting
- [ ] Data migration

### Best Books
**Primary:** *Microservices Patterns* — Chris Richardson

**Architecture:** *Building Microservices, 2nd Edition* — Sam Newman

---

# 1.8 CQRS

- [ ] CQRS concept
- [ ] Command model
- [ ] Query model
- [ ] Separate read/write models
- [ ] CQRS without event sourcing
- [ ] CQRS with event sourcing
- [ ] Read-model projections
- [ ] Eventual consistency
- [ ] Rebuilding read models
- [ ] Synchronization
- [ ] CQRS complexity
- [ ] When CQRS is inappropriate

### Best References
**Primary:** *Microservices Patterns* — Chris Richardson

**DDD Deep Dive:** *Implementing Domain-Driven Design* — Vaughn Vernon

---

# 1.9 Event Sourcing

- [ ] Event store
- [ ] Events as source of truth
- [ ] Event append
- [ ] Event sequence
- [ ] State reconstruction
- [ ] Snapshots
- [ ] Event replay
- [ ] Event versioning
- [ ] Schema evolution
- [ ] Immutable events
- [ ] Projection rebuilding
- [ ] Eventual consistency
- [ ] Event sourcing vs traditional persistence
- [ ] Operational complexity
- [ ] When not to use event sourcing

### Best References
**Primary:** *Microservices Patterns* — Chris Richardson

**Deep Dive:** *Versioning in an Event Sourced System* concepts and EventStoreDB documentation

---

# 1.10 Saga Pattern

- [ ] Distributed transaction problem
- [ ] Saga concept
- [ ] Local transactions
- [ ] Saga steps
- [ ] Compensating transactions
- [ ] Choreography
- [ ] Orchestration
- [ ] Choreography trade-offs
- [ ] Orchestrator responsibilities
- [ ] Failure handling
- [ ] Compensation failures
- [ ] Idempotency
- [ ] Timeouts
- [ ] Observability
- [ ] Saga state management

### Best Book
**Microservices Patterns — Chris Richardson**

---

# 1.11 Transactional Outbox

- [ ] Dual-write problem
- [ ] Transactional Outbox
- [ ] Outbox table
- [ ] Atomic database transaction
- [ ] Event publisher
- [ ] Polling publisher
- [ ] Change Data Capture
- [ ] Debezium
- [ ] Ordering
- [ ] Duplicate delivery
- [ ] Idempotent consumers
- [ ] Outbox cleanup
- [ ] Event schema management

### Best References
**Primary:** *Microservices Patterns* — Chris Richardson

**CDC:** Debezium documentation

---

# 1.12 Distributed Transaction Strategies

- [ ] Why distributed transactions are difficult
- [ ] Two-Phase Commit
- [ ] Coordinator
- [ ] Prepare phase
- [ ] Commit phase
- [ ] Blocking behavior
- [ ] Failure scenarios
- [ ] Why 2PC is often avoided in microservices
- [ ] TCC
- [ ] Try
- [ ] Confirm
- [ ] Cancel
- [ ] Saga
- [ ] Saga vs TCC
- [ ] Choosing between approaches

### Best Reference
**Microservices Patterns — Chris Richardson**

---

# 1.13 Resilience Patterns

## Bulkhead
- [ ] Bulkhead concept
- [ ] Thread-pool isolation
- [ ] Semaphore isolation
- [ ] Resource partitioning
- [ ] Failure containment
- [ ] Capacity planning

## Circuit Breaker
- [ ] Closed
- [ ] Open
- [ ] Half-open
- [ ] Failure thresholds
- [ ] Sliding windows
- [ ] Recovery testing
- [ ] Fallbacks
- [ ] Circuit breaker anti-patterns

## Rate Limiter
- [ ] Rate limiting
- [ ] Fixed-window concepts
- [ ] Token bucket concepts
- [ ] Concurrency limits
- [ ] Distributed rate limiting

## Time Limiter
- [ ] Request timeout
- [ ] Connection timeout
- [ ] Read timeout
- [ ] Overall operation timeout
- [ ] Timeout budgeting

## Retry
- [ ] Retryable vs non-retryable errors
- [ ] Exponential backoff
- [ ] Jitter
- [ ] Maximum attempts
- [ ] Retry storms
- [ ] Retry amplification
- [ ] Retry + timeout interaction
- [ ] Retry + circuit breaker interaction

### Best Books
**Primary:** *Release It!, 2nd Edition* — Michael T. Nygard

**Microservices:** *Microservices Patterns* — Chris Richardson

---

# 1.14 Idempotency & Message Delivery Semantics

- [ ] Idempotent API
- [ ] Idempotency key
- [ ] Duplicate requests
- [ ] Idempotency storage
- [ ] Idempotent consumers
- [ ] At-most-once
- [ ] At-least-once
- [ ] Exactly-once
- [ ] Practical meaning of "exactly once"
- [ ] Deduplication
- [ ] Ordering
- [ ] Retry + duplicate delivery
- [ ] Poison messages

### Best References
**Primary:** *Microservices Patterns* — Chris Richardson

**Messaging:** *Enterprise Integration Patterns* — Gregor Hohpe & Bobby Woolf

---

# Module 2 — Inter-Service Communication & Event-Driven Architecture

# 2.1 Synchronous Communication

- [ ] Synchronous vs asynchronous communication
- [ ] REST
- [ ] Resource-oriented API design
- [ ] HTTP methods
- [ ] Status codes
- [ ] Timeouts
- [ ] Connection pooling
- [ ] Keep-alive
- [ ] HTTP/2
- [ ] HTTP/2 multiplexing
- [ ] HTTP/3
- [ ] QUIC concepts
- [ ] Backward compatibility
- [ ] API evolution
- [ ] Client failure handling

### Best Books
**Primary:** *Building Microservices, 2nd Edition* — Sam Newman

**HTTP Reference:** *HTTP: The Definitive Guide* — David Gourley & Brian Totty

---

# 2.2 gRPC & Protocol Buffers

## Protocol Buffers
- [ ] `.proto`
- [ ] Messages
- [ ] Fields
- [ ] Field numbers
- [ ] Scalar types
- [ ] Enums
- [ ] Nested messages
- [ ] Repeated fields
- [ ] `oneof`
- [ ] Maps
- [ ] Optional fields
- [ ] Schema evolution
- [ ] Backward compatibility

## gRPC
- [ ] Unary RPC
- [ ] Server streaming
- [ ] Client streaming
- [ ] Bidirectional streaming
- [ ] Channels
- [ ] Stubs
- [ ] Deadlines
- [ ] Metadata
- [ ] Status codes
- [ ] Interceptors
- [ ] Error handling
- [ ] Load balancing concepts
- [ ] gRPC vs REST

### Best References
**Primary:** *gRPC Up & Running* — Kasun Indrasiri & Danesh Gamage

**Protocol Reference:** Protocol Buffers documentation

---

# 2.3 GraphQL

- [ ] GraphQL fundamentals
- [ ] Schema
- [ ] Types
- [ ] Queries
- [ ] Mutations
- [ ] Subscriptions
- [ ] Resolvers
- [ ] Arguments
- [ ] Variables
- [ ] Fragments
- [ ] Directives
- [ ] Schema evolution
- [ ] N+1 problem
- [ ] DataLoader concepts
- [ ] GraphQL vs REST
- [ ] GraphQL in microservices

### Best Book
**Learning GraphQL — Eve Porcello & Alex Banks**

---

# 2.4 Kafka Architecture

- [ ] Kafka purpose
- [ ] Topics
- [ ] Partitions
- [ ] Brokers
- [ ] Replication
- [ ] Leaders/followers
- [ ] In-sync replicas
- [ ] Consumer groups
- [ ] Cluster metadata
- [ ] KRaft architecture
- [ ] Controllers
- [ ] Controller quorum
- [ ] Partition leadership
- [ ] Log segments
- [ ] Offsets
- [ ] Retention
- [ ] Compaction

### Best Books
**Primary:** *Kafka: The Definitive Guide, 2nd Edition* — Gwen Shapira, Todd Palino, Rajini Sivaram & Krit Petty

**Deep Dive:** *Kafka Streams in Action, 2nd Edition* — Bill Bejeck

**Reference:** Apache Kafka documentation

---

# 2.5 Kafka Producer Internals

- [ ] Producer architecture
- [ ] Serializer
- [ ] Partitioner
- [ ] Record accumulator
- [ ] Batching
- [ ] Compression
- [ ] `acks=0`
- [ ] `acks=1`
- [ ] `acks=all`
- [ ] Retries
- [ ] Delivery timeout
- [ ] Request timeout
- [ ] Idempotent producer
- [ ] Producer IDs
- [ ] Sequence numbers
- [ ] Custom partitioners
- [ ] Ordering guarantees

### Best Reference
**Kafka: The Definitive Guide, 2nd Edition**

---

# 2.6 Kafka Consumer Internals

- [ ] Consumer groups
- [ ] Partitions assigned to consumers
- [ ] Offset
- [ ] Committed offset
- [ ] Current position
- [ ] Auto commit
- [ ] Manual commit
- [ ] `commitSync`
- [ ] `commitAsync`
- [ ] Poll loop
- [ ] `max.poll.interval.ms`
- [ ] `max.poll.records`
- [ ] Session timeout
- [ ] Heartbeats
- [ ] Consumer lag
- [ ] Poison messages
- [ ] Retry/DLQ strategies

### Best Reference
**Kafka: The Definitive Guide, 2nd Edition**

---

# 2.7 Kafka Rebalancing

- [ ] Why rebalancing occurs
- [ ] Consumer group coordination
- [ ] Eager rebalancing
- [ ] Cooperative rebalancing
- [ ] Sticky assignment
- [ ] CooperativeStickyAssignor
- [ ] Partition movement
- [ ] Rebalance impact
- [ ] Rebalance listeners
- [ ] Static membership concepts

### Best Reference
**Kafka: The Definitive Guide, 2nd Edition**

---

# 2.8 Kafka Transactions & Exactly-Once Semantics

- [ ] Producer transactions
- [ ] Transactional ID
- [ ] Atomic writes
- [ ] Read committed
- [ ] Read uncommitted
- [ ] Idempotent producers
- [ ] EOS
- [ ] Kafka Streams exactly-once processing
- [ ] Failure scenarios
- [ ] Practical meaning of exactly-once

### Best Reference
**Kafka: The Definitive Guide, 2nd Edition**

---

# 2.9 Kafka Streams

- [ ] Kafka Streams architecture
- [ ] Stateless operations
- [ ] Stateful operations
- [ ] KStream
- [ ] KTable
- [ ] GlobalKTable
- [ ] Filtering
- [ ] Mapping
- [ ] Grouping
- [ ] Aggregation
- [ ] Joins
- [ ] Windowing
- [ ] Tumbling windows
- [ ] Hopping windows
- [ ] Session windows
- [ ] State stores
- [ ] Interactive queries
- [ ] Exactly-once processing

### Best Book
**Kafka Streams in Action, 2nd Edition — Bill Bejeck**

---

# 2.10 Kafka Connect

- [ ] Kafka Connect architecture
- [ ] Source connectors
- [ ] Sink connectors
- [ ] Workers
- [ ] Tasks
- [ ] Standalone mode
- [ ] Distributed mode
- [ ] Connector configuration
- [ ] Offset management
- [ ] Error handling
- [ ] Dead Letter Queue
- [ ] SMTs

### Best References
**Kafka: The Definitive Guide, 2nd Edition**

**Reference:** Apache Kafka Connect documentation

---

# 2.11 Schema Registry & Serialization

- [ ] Why schemas matter
- [ ] Avro
- [ ] Protobuf
- [ ] JSON Schema concepts
- [ ] Schema Registry
- [ ] Subject
- [ ] Schema versions
- [ ] Compatibility modes
- [ ] Backward compatibility
- [ ] Forward compatibility
- [ ] Full compatibility
- [ ] Schema evolution
- [ ] Breaking changes

### Best References
**Primary:** *Kafka: The Definitive Guide, 2nd Edition*

**Schema Reference:** Confluent Schema Registry documentation

---

# 2.12 RabbitMQ

- [ ] RabbitMQ architecture
- [ ] Producer
- [ ] Consumer
- [ ] Exchange
- [ ] Queue
- [ ] Binding
- [ ] Direct exchange
- [ ] Topic exchange
- [ ] Fanout exchange
- [ ] Headers exchange
- [ ] Routing key
- [ ] Acknowledgment
- [ ] Negative acknowledgment
- [ ] Prefetch
- [ ] Consumer QoS
- [ ] Dead Letter Exchange
- [ ] DLQ
- [ ] TTL
- [ ] Alternate exchanges
- [ ] Retry patterns
- [ ] Ordering
- [ ] RabbitMQ vs Kafka

### Best Book
**RabbitMQ in Depth — Gavin M. Roy**

### Reference
RabbitMQ documentation

---

# 2.13 Cloud Messaging & ActiveMQ

## AWS SQS
- [ ] Standard queues
- [ ] FIFO queues
- [ ] Visibility timeout
- [ ] Long polling
- [ ] Dead-letter queues
- [ ] Message deduplication
- [ ] Message groups

## AWS SNS
- [ ] Publish/subscribe
- [ ] Topics
- [ ] Subscriptions
- [ ] Fanout

## ActiveMQ
- [ ] Broker architecture
- [ ] Queues
- [ ] Topics
- [ ] JMS concepts
- [ ] Acknowledgment
- [ ] Transactions

### Best References
- AWS SQS/SNS documentation
- ActiveMQ documentation

---

# Module 3 — Spring Cloud Ecosystem

# 3.1 Service Discovery

- [ ] Why service discovery exists
- [ ] Client-side discovery
- [ ] Server-side discovery
- [ ] Service registration
- [ ] Service lookup
- [ ] Health status
- [ ] Heartbeats
- [ ] Eureka
- [ ] Consul
- [ ] Kubernetes Service
- [ ] Kubernetes DNS
- [ ] Discovery trade-offs
- [ ] When Kubernetes-native discovery eliminates the need for Eureka

### Best Books / References
**Primary:** *Spring Microservices in Action, 2nd Edition* — John Carnell

**Reference:** Spring Cloud documentation

---

# 3.2 Spring Cloud LoadBalancer

- [ ] Client-side load balancing
- [ ] Spring Cloud LoadBalancer
- [ ] Service instances
- [ ] Instance selection
- [ ] Round-robin
- [ ] Custom load-balancing strategies
- [ ] Health-aware selection
- [ ] Ribbon history
- [ ] Ribbon migration
- [ ] Load balancing vs API gateway routing

### Best References
**Primary:** Spring Cloud documentation

**Supplement:** Spring Microservices in Action, 2nd Edition

---

# 3.3 OpenFeign

- [ ] Declarative HTTP client
- [ ] `@FeignClient`
- [ ] Interface-based API
- [ ] Request mapping
- [ ] Path/query parameters
- [ ] Request headers
- [ ] Custom configuration
- [ ] Encoders
- [ ] Decoders
- [ ] ErrorDecoder
- [ ] Interceptors
- [ ] Timeouts
- [ ] Retry behavior
- [ ] LoadBalancer integration
- [ ] Circuit breaker integration
- [ ] Feign logging
- [ ] Feign vs RestClient/WebClient

### Best References
**Primary:** Spring Cloud OpenFeign documentation

**Practical:** Spring Microservices in Action, 2nd Edition

---

# 3.4 Spring Cloud Gateway

## Architecture
- [ ] Gateway role
- [ ] Route
- [ ] Predicate
- [ ] Filter
- [ ] Gateway handler chain
- [ ] Route matching

## Predicates
- [ ] Path
- [ ] Host
- [ ] Method
- [ ] Header
- [ ] Query
- [ ] Remote address
- [ ] Time-based predicates

## Filters
- [ ] Gateway filter factories
- [ ] Global filters
- [ ] Pre/post filter behavior
- [ ] Filter ordering
- [ ] Request mutation
- [ ] Response mutation
- [ ] Header manipulation
- [ ] Custom filters

## Resilience & Rate Limiting
- [ ] Redis RequestRateLimiter
- [ ] Key resolver
- [ ] Token bucket concepts
- [ ] Gateway circuit breaker
- [ ] Timeout
- [ ] Retry
- [ ] Fallback routing

### Best References
**Primary:** Spring Cloud Gateway Reference Documentation

**Practical:** Spring Microservices in Action, 2nd Edition

---

# 3.5 Spring Cloud Config

- [ ] Config Server
- [ ] Config Client
- [ ] Git backend
- [ ] Native backend
- [ ] Vault backend
- [ ] Environment-specific configuration
- [ ] Configuration repositories
- [ ] Encryption/decryption
- [ ] Symmetric keys
- [ ] Asymmetric keys
- [ ] Key management
- [ ] Configuration refresh
- [ ] `@RefreshScope`
- [ ] Refresh lifecycle

### Best References
**Primary:** Spring Cloud Config documentation

**Practical:** Spring Microservices in Action, 2nd Edition

---

# 3.6 Spring Cloud Bus

- [ ] Why distributed refresh is difficult
- [ ] Spring Cloud Bus
- [ ] Event propagation
- [ ] Refresh events
- [ ] Kafka/RabbitMQ binder
- [ ] Distributed configuration refresh
- [ ] Refresh ordering
- [ ] Failure handling

### Best Reference
Spring Cloud Bus documentation

---

# 3.7 Resilience4j

- [ ] CircuitBreaker
- [ ] RateLimiter
- [ ] Retry
- [ ] Bulkhead
- [ ] TimeLimiter
- [ ] Annotation-based integration
- [ ] Programmatic configuration
- [ ] Sliding window
- [ ] Failure thresholds
- [ ] Wait duration
- [ ] Retry backoff
- [ ] Jitter
- [ ] Fallbacks
- [ ] Event publishers
- [ ] Metrics
- [ ] Combining resilience patterns
- [ ] Avoiding retry storms

### Best References
**Primary:** *Release It!, 2nd Edition* — Michael T. Nygard

**Implementation:** Resilience4j documentation

---

# 3.8 Spring Cloud Stream

- [ ] Binder abstraction
- [ ] Kafka binder
- [ ] RabbitMQ binder
- [ ] Functional programming model
- [ ] Supplier
- [ ] Consumer
- [ ] Function
- [ ] Bindings
- [ ] Consumer groups
- [ ] Partitions
- [ ] Error handling
- [ ] Retry
- [ ] DLQ concepts
- [ ] Serialization
- [ ] Schema evolution
- [ ] Kafka vs RabbitMQ binder behavior

### Best References
**Primary:** Spring Cloud Stream documentation

**Messaging Companion:** *Enterprise Integration Patterns* — Gregor Hohpe & Bobby Woolf

---

# 3.9 Spring Cloud Contract

- [ ] Contract testing concept
- [ ] Consumer-driven contracts
- [ ] Contract definition
- [ ] Provider verification
- [ ] Generated tests
- [ ] Stub generation
- [ ] Breaking API changes
- [ ] Contract versioning
- [ ] Contract vs integration testing

### Best References
**Primary:** Spring Cloud Contract documentation

**Alternative:** Pact documentation

---

# Module 4 — Enterprise Security & Identity Management

# 4.1 Spring Security Architecture

- [ ] Servlet security architecture
- [ ] `DelegatingFilterProxy`
- [ ] `FilterChainProxy`
- [ ] `SecurityFilterChain`
- [ ] Security filters
- [ ] Filter ordering
- [ ] Authentication flow
- [ ] Authorization flow
- [ ] Exception translation
- [ ] Security context lifecycle

### Best Books
**Primary:** *Spring Security in Action, 2nd Edition* — Laurentiu Spilca

**Reference:** Spring Security Reference Documentation

---

# 4.2 Authentication Architecture

- [ ] `Authentication`
- [ ] `AuthenticationManager`
- [ ] `AuthenticationProvider`
- [ ] `UserDetailsService`
- [ ] `UserDetails`
- [ ] `PasswordEncoder`
- [ ] Authentication success
- [ ] Authentication failure
- [ ] Anonymous authentication
- [ ] Remember-me concepts
- [ ] Multiple AuthenticationProviders

### Best Book
**Spring Security in Action, 2nd Edition — Laurentiu Spilca**

---

# 4.3 Security Context

- [ ] `SecurityContext`
- [ ] `SecurityContextHolder`
- [ ] ThreadLocal strategy
- [ ] Security context propagation
- [ ] Authentication availability
- [ ] Async execution considerations
- [ ] Context cleanup
- [ ] Stateless vs stateful security context

### Best References
**Primary:** Spring Security in Action  
**Reference:** Spring Security Reference Documentation

---

# 4.4 Authorization

- [ ] URL authorization
- [ ] `authorizeHttpRequests`
- [ ] Request matchers
- [ ] Permit-all
- [ ] Authenticated
- [ ] Role-based authorization
- [ ] Authority-based authorization
- [ ] Method security
- [ ] `@PreAuthorize`
- [ ] `@PostAuthorize`
- [ ] `@Secured`
- [ ] `@RolesAllowed`
- [ ] SpEL authorization
- [ ] Custom authorization logic

### RBAC vs ABAC
- [ ] RBAC
- [ ] ABAC
- [ ] Role explosion
- [ ] Attribute-based decisions
- [ ] Policy evaluation
- [ ] Choosing RBAC vs ABAC

### Best Book
**Spring Security in Action, 2nd Edition**

---

# 4.5 Authentication Models

- [ ] Form login
- [ ] HTTP Basic
- [ ] Session-based authentication
- [ ] Stateless JWT
- [ ] OAuth 2.0
- [ ] OpenID Connect
- [ ] Resource Server
- [ ] OAuth2 Client
- [ ] Authorization Server

### Best Book
**Spring Security in Action, 2nd Edition**

---

# 4.6 JWT Deep Dive

## Token Anatomy
- [ ] Header
- [ ] Payload
- [ ] Signature
- [ ] Registered claims
- [ ] `iss`
- [ ] `sub`
- [ ] `aud`
- [ ] `exp`
- [ ] `nbf`
- [ ] `iat`
- [ ] `jti`

## Algorithms
- [ ] HS256
- [ ] RS256
- [ ] Elliptic-curve algorithms
- [ ] Symmetric vs asymmetric signing
- [ ] Key rotation
- [ ] JWKS concepts
- [ ] Algorithm confusion risks

## Token Lifecycle
- [ ] Access token
- [ ] Refresh token
- [ ] Refresh token rotation
- [ ] Token expiration
- [ ] Revocation
- [ ] Blacklisting
- [ ] Redis-based revocation
- [ ] Token theft considerations
- [ ] Secure token storage

### Best References
**Primary:** *OAuth 2 in Action* — Justin Richer & Antonio Sanso

**Spring:** Spring Security OAuth2 Resource Server documentation

**Standards:** RFC 7519 / JWT specification

---

# 4.7 OAuth 2.0

- [ ] OAuth roles
- [ ] Resource Owner
- [ ] Client
- [ ] Authorization Server
- [ ] Resource Server
- [ ] Authorization Code flow
- [ ] PKCE
- [ ] Client Credentials
- [ ] Refresh tokens
- [ ] Access tokens
- [ ] Scopes
- [ ] Consent
- [ ] Redirect URI
- [ ] State parameter
- [ ] Token endpoint
- [ ] Authorization endpoint
- [ ] OAuth threat model

### Best Book
**OAuth 2 in Action — Justin Richer & Antonio Sanso**

---

# 4.8 OpenID Connect

- [ ] OIDC vs OAuth 2.0
- [ ] ID Token
- [ ] UserInfo endpoint
- [ ] Discovery
- [ ] JWKS
- [ ] Claims
- [ ] Nonce
- [ ] Authorization Code + PKCE
- [ ] Enterprise SSO concepts

### Best References
**Primary:** *OAuth 2 in Action*

**Reference:** OpenID Connect Core specification

---

# 4.9 Spring Authorization Server

- [ ] Authorization Server architecture
- [ ] Registered clients
- [ ] Client authentication
- [ ] Authorization endpoint
- [ ] Token endpoint
- [ ] Consent
- [ ] JWT signing
- [ ] JWK source
- [ ] OIDC support
- [ ] Custom claims
- [ ] Custom grant concepts
- [ ] Token customization

### Best References
**Primary:** Spring Authorization Server documentation

**Security Concepts:** *OAuth 2 in Action*

---

# 4.10 Enterprise Identity Providers

- [ ] Keycloak
- [ ] Okta
- [ ] Auth0
- [ ] Identity Provider vs Authorization Server
- [ ] OIDC discovery
- [ ] JWKS
- [ ] Realm/tenant concepts
- [ ] Client registration
- [ ] Roles and scopes
- [ ] Enterprise SSO integration
- [ ] Resource Server integration

### Best References
- Keycloak documentation
- Okta developer documentation
- Auth0 documentation
- Spring Security OAuth2 documentation

---

# 4.11 Application Security & OWASP

## CSRF
- [ ] What CSRF is
- [ ] Stateful applications
- [ ] Stateless APIs
- [ ] Cookie-based authentication
- [ ] CSRF tokens
- [ ] SameSite cookies
- [ ] When disabling CSRF is appropriate

## CORS
- [ ] Same-origin policy
- [ ] Preflight
- [ ] Allowed origins
- [ ] Allowed methods
- [ ] Allowed headers
- [ ] Credentials
- [ ] Security implications

## Common Attacks
- [ ] XSS
- [ ] SQL Injection
- [ ] SSRF
- [ ] Broken Object Level Authorization
- [ ] Broken Authentication
- [ ] Security misconfiguration
- [ ] Injection
- [ ] Sensitive data exposure
- [ ] Insecure direct object access concepts

## Password Hashing
- [ ] BCrypt
- [ ] Argon2
- [ ] PBKDF2
- [ ] Salt
- [ ] Work factor
- [ ] Password verification
- [ ] Password upgrade strategy

### Best References
**Primary:** *Spring Security in Action, 2nd Edition*

**Security Reference:** OWASP Top 10

**Web Security:** OWASP Web Security Testing Guide

---

# 4.12 Secrets Management

- [ ] Why secrets should not be in source control
- [ ] Environment variables
- [ ] HashiCorp Vault
- [ ] AWS Secrets Manager
- [ ] Azure Key Vault
- [ ] Secret rotation
- [ ] Dynamic secrets
- [ ] Application integration
- [ ] Secret exposure prevention
- [ ] Configuration vs secrets

### Best References
- HashiCorp Vault documentation
- AWS Secrets Manager documentation
- Azure Key Vault documentation

---

# 4.13 Zero Trust & mTLS

- [ ] Zero Trust principles
- [ ] Never trust, always verify
- [ ] Service identity
- [ ] Workload identity
- [ ] Mutual TLS
- [ ] Client certificates
- [ ] Server certificates
- [ ] Certificate authorities
- [ ] Certificate rotation
- [ ] Trust stores
- [ ] Service-to-service authentication
- [ ] mTLS vs JWT
- [ ] Combining mTLS and application authorization

### Best References
**Primary:** *Zero Trust Networks* — Evan Gilman & Doug Barth

**Security:** *OAuth 2 in Action*

**Reference:** SPIFFE/SPIRE documentation for workload identity concepts

---

# Module 5 — Observability, Distributed Tracing & SRE

# 5.1 Distributed Tracing

- [ ] Why distributed tracing is needed
- [ ] Trace
- [ ] Span
- [ ] Parent/child spans
- [ ] Trace ID
- [ ] Span ID
- [ ] Sampling
- [ ] W3C Trace Context
- [ ] `traceparent`
- [ ] `tracestate`
- [ ] Context propagation
- [ ] HTTP propagation
- [ ] Messaging propagation
- [ ] Async context propagation
- [ ] Trace/log correlation

### Best References
**Primary:** *Distributed Systems Observability* — Cindy Sridharan

**Modern Standard:** OpenTelemetry documentation

---

# 5.2 Micrometer Tracing

- [ ] Micrometer Tracing
- [ ] Spring Boot integration
- [ ] Brave
- [ ] OpenTelemetry
- [ ] Zipkin
- [ ] Jaeger
- [ ] Spring Cloud Sleuth history/migration
- [ ] Trace context propagation
- [ ] Span customization
- [ ] Sampling

### Best References
- Micrometer Tracing documentation
- OpenTelemetry documentation
- Spring Boot observability documentation

---

# 5.3 MDC & Correlation IDs

- [ ] MDC
- [ ] Correlation ID
- [ ] Trace ID
- [ ] Span ID
- [ ] Logging context
- [ ] Thread-local behavior
- [ ] Async propagation
- [ ] Reactive context vs ThreadLocal
- [ ] Structured logging

### Best Reference
**Distributed Systems Observability — Cindy Sridharan**

---

# 5.4 Metrics & Micrometer

- [ ] Metrics fundamentals
- [ ] Counter
- [ ] Gauge
- [ ] Timer
- [ ] Distribution summary
- [ ] Histograms
- [ ] Percentiles
- [ ] Tags
- [ ] Cardinality
- [ ] Custom metrics
- [ ] JVM metrics
- [ ] HTTP metrics
- [ ] Database metrics
- [ ] Kafka metrics

### Best References
**Primary:** Micrometer documentation

**Observability:** *Distributed Systems Observability*

---

# 5.5 Prometheus

- [ ] Pull model
- [ ] Scraping
- [ ] Targets
- [ ] Labels
- [ ] Time-series data
- [ ] PromQL
- [ ] Counters
- [ ] Gauges
- [ ] Histograms
- [ ] Recording rules
- [ ] Alerting rules
- [ ] High-cardinality problems

### Best Reference
**Prometheus documentation**

---

# 5.6 Grafana

- [ ] Dashboard fundamentals
- [ ] Panels
- [ ] Variables
- [ ] Prometheus data source
- [ ] Queries
- [ ] Alerting
- [ ] Dashboard design
- [ ] Service-level dashboards
- [ ] Golden signals dashboards

### Best Reference
**Grafana documentation**

---

# 5.7 Centralized Logging

- [ ] Structured logging
- [ ] JSON logs
- [ ] Logstash Logback Encoder
- [ ] Correlation IDs
- [ ] Trace IDs
- [ ] Log levels
- [ ] Log aggregation
- [ ] ELK
- [ ] Elasticsearch
- [ ] Logstash
- [ ] Kibana
- [ ] Grafana Loki
- [ ] ELK vs Loki
- [ ] Log retention
- [ ] Searchability
- [ ] Sensitive-data handling

### Best References
**Primary:** *Distributed Systems Observability*

**Implementation:** Elastic documentation / Grafana Loki documentation

---

# 5.8 Health & Kubernetes Probes

- [ ] Spring Boot Actuator health
- [ ] Liveness
- [ ] Readiness
- [ ] Startup probes concepts
- [ ] Kubernetes health model
- [ ] `/actuator/health/liveness`
- [ ] `/actuator/health/readiness`
- [ ] Failure behavior
- [ ] Dependency health vs application health
- [ ] Avoiding cascading failures through health checks

### Best References
**Primary:** Spring Boot Actuator documentation

**Platform:** Kubernetes documentation

---

# 5.9 SRE-Oriented Observability Concepts

- [ ] Golden signals
- [ ] Latency
- [ ] Traffic
- [ ] Errors
- [ ] Saturation
- [ ] SLIs
- [ ] SLOs
- [ ] Error budgets
- [ ] Alert quality
- [ ] Symptom vs cause
- [ ] Actionable alerts
- [ ] Incident diagnosis

### Best Book
**Site Reliability Engineering — Betsy Beyer et al.**

### Supplement
**The Site Reliability Workbook — Betsy Beyer et al.**

---

# Module 6 — Testing & Quality Engineering

# 6.1 Testing Strategy

- [ ] Testing pyramid
- [ ] Unit tests
- [ ] Integration tests
- [ ] Contract tests
- [ ] End-to-end tests
- [ ] Test isolation
- [ ] Test determinism
- [ ] Test reliability
- [ ] Test execution speed
- [ ] What should/shouldn't be mocked

### Best Book
**Unit Testing: Principles, Practices, and Patterns — Vladimir Khorikov**

---

# 6.2 JUnit 5

- [ ] JUnit Jupiter
- [ ] Test lifecycle
- [ ] `@BeforeEach`
- [ ] `@AfterEach`
- [ ] `@BeforeAll`
- [ ] `@AfterAll`
- [ ] Nested tests
- [ ] Dynamic tests
- [ ] Assertions
- [ ] Exception assertions
- [ ] Timeout assertions
- [ ] Test tagging
- [ ] Test ordering considerations
- [ ] Parameterized tests
- [ ] `@ValueSource`
- [ ] `@CsvSource`
- [ ] `@MethodSource`
- [ ] `@ArgumentsSource`

### Best Book
**JUnit 5 User Guide** — official documentation

### Practical Companion
**Unit Testing — Vladimir Khorikov**

---

# 6.3 Mockito

- [ ] `@Mock`
- [ ] `@InjectMocks`
- [ ] `@Spy`
- [ ] `@Captor`
- [ ] Stubbing
- [ ] `when`
- [ ] `thenReturn`
- [ ] `thenThrow`
- [ ] `doReturn`
- [ ] Verification
- [ ] Invocation count
- [ ] Invocation order
- [ ] `ArgumentCaptor`
- [ ] Argument matchers
- [ ] Strict stubbing
- [ ] Mocking static methods
- [ ] Mocking constructors
- [ ] Partial mocking
- [ ] Mockito pitfalls
- [ ] Over-mocking
- [ ] Testing implementation details

### Best References
**Primary:** Mockito documentation

**Testing Principles:** *Unit Testing — Vladimir Khorikov*

---

# 6.4 AssertJ & Hamcrest

## AssertJ
- [ ] Fluent assertions
- [ ] Chaining
- [ ] Collection assertions
- [ ] Exception assertions
- [ ] Recursive comparison
- [ ] Custom assertions

## Hamcrest
- [ ] Matchers
- [ ] Composable matchers
- [ ] AssertJ vs Hamcrest

### Best Reference
AssertJ documentation

---

# 6.5 Spring Boot Test Architecture

## Full Context
- [ ] `@SpringBootTest`
- [ ] WebEnvironment options
- [ ] Full ApplicationContext
- [ ] Test configuration
- [ ] Context caching
- [ ] Test profiles
- [ ] Test properties

## Web Slice
- [ ] `@WebMvcTest`
- [ ] Controller testing
- [ ] MockMvc
- [ ] Mocking service dependencies

## Persistence Slice
- [ ] `@DataJpaTest`
- [ ] Repository testing
- [ ] Transaction behavior
- [ ] Embedded databases
- [ ] Testcontainers database testing

## Client/Serialization
- [ ] `@RestClientTest`
- [ ] `@JsonTest`

### Best References
**Primary:** Spring Boot Testing documentation

**Practical:** *Spring in Action*

---

# 6.6 HTTP Testing

## MockMvc
- [ ] Request building
- [ ] Request parameters
- [ ] Headers
- [ ] JSON bodies
- [ ] Authentication test concepts
- [ ] Response assertions
- [ ] Exception assertions

## WebTestClient
- [ ] Reactive testing
- [ ] MVC compatibility use cases
- [ ] WebFlux testing

## TestRestTemplate
- [ ] Real HTTP testing
- [ ] Embedded server
- [ ] Integration testing

## Comparison
- [ ] MockMvc vs WebTestClient
- [ ] WebTestClient vs TestRestTemplate
- [ ] In-process vs real HTTP

### Best References
Spring Boot Testing documentation

---

# 6.7 Testcontainers

- [ ] Why real dependencies matter
- [ ] Container lifecycle
- [ ] PostgreSQL
- [ ] MySQL
- [ ] Redis
- [ ] Kafka
- [ ] Reusable containers
- [ ] Container networking
- [ ] Wait strategies
- [ ] Dynamic configuration
- [ ] `@DynamicPropertySource`
- [ ] Test isolation
- [ ] CI execution
- [ ] Container startup performance

### Best Book / Reference
**Primary:** Testcontainers documentation

**Testing Principles:** *Unit Testing — Vladimir Khorikov*

---

# 6.8 LocalStack

- [ ] Why cloud-service emulation is useful
- [ ] AWS S3
- [ ] SQS
- [ ] DynamoDB
- [ ] Local integration tests
- [ ] Test environment configuration
- [ ] LocalStack limitations vs real AWS

### Best Reference
LocalStack documentation

---

# 6.9 WireMock

- [ ] HTTP stubbing
- [ ] Request matching
- [ ] Response stubbing
- [ ] Fault simulation
- [ ] Delays
- [ ] Stateful scenarios
- [ ] Verification
- [ ] Resetting state
- [ ] Testing timeouts
- [ ] Testing retries
- [ ] Testing downstream failures

### Best Reference
WireMock documentation

---

# 6.10 Contract Testing

## Consumer-Driven Contracts
- [ ] Why integration tests alone are insufficient
- [ ] Consumer expectations
- [ ] Provider verification
- [ ] Contract versioning
- [ ] Breaking changes
- [ ] Contract repository

## Pact
- [ ] Consumer tests
- [ ] Provider verification
- [ ] Pact Broker
- [ ] Contract lifecycle

## Spring Cloud Contract
- [ ] Contract definitions
- [ ] Stub generation
- [ ] Provider verification
- [ ] Spring integration

## Comparison
- [ ] Pact vs Spring Cloud Contract
- [ ] Contract vs integration testing
- [ ] Contract vs E2E

### Best References
**Pact:** Pact documentation

**Spring:** Spring Cloud Contract documentation

**Testing Strategy:** *Building Microservices, 2nd Edition*

---

# 6.11 Code Coverage

- [ ] JaCoCo
- [ ] Line coverage
- [ ] Branch coverage
- [ ] Method coverage
- [ ] Instruction coverage
- [ ] Coverage thresholds
- [ ] Coverage reports
- [ ] Why high coverage does not guarantee good tests
- [ ] Meaningful coverage vs coverage chasing

### Best References
**Primary:** JaCoCo documentation

**Testing Quality:** *Unit Testing — Vladimir Khorikov*

---

# 6.12 Mutation Testing

- [ ] Mutation testing concept
- [ ] Mutants
- [ ] Mutation operators
- [ ] Surviving mutants
- [ ] Killed mutants
- [ ] Mutation score
- [ ] PIT
- [ ] Why mutation testing measures test strength
- [ ] Mutation testing limitations
- [ ] CI integration

### Best References
**Primary:** PIT Mutation Testing documentation

**Testing Principles:** *Unit Testing — Vladimir Khorikov*

---

# 6.13 Performance & Load Testing

## JMeter
- [ ] Test plans
- [ ] Thread groups
- [ ] Requests
- [ ] Assertions
- [ ] Listeners
- [ ] Distributed load testing concepts

## Gatling
- [ ] Scenario
- [ ] Simulation
- [ ] Virtual users
- [ ] Assertions
- [ ] Reports

## k6
- [ ] JavaScript-based load tests
- [ ] Virtual users
- [ ] Thresholds
- [ ] Scenarios
- [ ] Checks
- [ ] CI integration

## Performance Methodology
- [ ] Load testing
- [ ] Stress testing
- [ ] Spike testing
- [ ] Soak testing
- [ ] Capacity testing
- [ ] Baseline
- [ ] Throughput
- [ ] Latency
- [ ] p50
- [ ] p95
- [ ] p99
- [ ] Error rate
- [ ] Resource utilization
- [ ] Bottleneck identification

### Best References
**Primary:** *Performance Testing Guidance for Web Applications* concepts + tool documentation

**Architecture:** *Release It!, 2nd Edition* — Michael T. Nygard

---

# 6.14 TDD & BDD

## TDD
- [ ] Red
- [ ] Green
- [ ] Refactor
- [ ] Test-first development
- [ ] Small increments
- [ ] Unit-level design feedback
- [ ] TDD limitations
- [ ] TDD vs test-after development

## BDD
- [ ] Behavior specification
- [ ] Given/When/Then
- [ ] Cucumber
- [ ] Feature files
- [ ] Step definitions
- [ ] Business-readable scenarios
- [ ] BDD vs TDD

### Best Books
**Primary:** *Test-Driven Development: By Example* — Kent Beck

**Practical Testing:** *Unit Testing — Vladimir Khorikov*

---

# 6.15 Test Pyramid Enforcement

- [ ] Unit test majority
- [ ] Integration tests for real boundaries
- [ ] Contract tests for service compatibility
- [ ] Limited E2E tests
- [ ] Test execution time
- [ ] Flaky test management
- [ ] Test ownership
- [ ] CI pipeline integration
- [ ] Test quality gates
- [ ] Coverage gates
- [ ] Mutation testing gates where appropriate

### Target Model

```text
             E2E
            /   \
       Contract  \
          /       \
   Integration     \
        /           \
       /   Unit      \
      /_______________\
```

The exact ratio should depend on the architecture; the principle is to keep the fastest, most isolated tests as the largest layer.

---

# Critical Architecture Flows to Master

## Synchronous Microservice Call

```text
Client Service
     ↓
HTTP/gRPC Client
     ↓
Service Discovery / DNS
     ↓
Load Balancer
     ↓
Target Service
     ↓
Business Logic
     ↓
Database
     ↓
Response
```

Understand where each of these can fail:

- [ ] DNS failure
- [ ] Connection timeout
- [ ] Read timeout
- [ ] Target overload
- [ ] Database latency
- [ ] Network partition
- [ ] Retry amplification
- [ ] Circuit breaker activation

---

# Event-Driven Flow

```text
Producer
   ↓
Serializer
   ↓
Broker
   ↓
Topic
   ↓
Partition
   ↓
Consumer Group
   ↓
Consumer
   ↓
Business Logic
   ↓
Database
```

Be able to explain:

- [ ] Ordering
- [ ] Partition assignment
- [ ] Consumer offsets
- [ ] Rebalancing
- [ ] Duplicate messages
- [ ] Retry
- [ ] DLQ
- [ ] Idempotency
- [ ] Schema evolution

---

# Saga Flow

```text
Service A
   ↓
Local Transaction
   ↓
Event
   ↓
Service B
   ↓
Local Transaction
   ↓
Event
   ↓
Service C
   ↓
Failure
   ↓
Compensating Action
```

Understand:

- [ ] Choreography
- [ ] Orchestration
- [ ] Compensation
- [ ] Partial failure
- [ ] Idempotency
- [ ] Observability

---

# OAuth2 / OIDC Flow

```text
User
 ↓
Client
 ↓
Authorization Server
 ↓
Authorization Code
 ↓
Client
 ↓
Token Endpoint
 ↓
Access Token
 ↓
Resource Server
 ↓
Protected Resource
```

For PKCE:

```text
Code Verifier
     ↓
Code Challenge
     ↓
Authorization Request
     ↓
Authorization Code
     ↓
Token Request + Verifier
     ↓
Access Token
```

Be able to explain:

- [ ] OAuth vs OIDC
- [ ] Access token vs ID token
- [ ] Authorization code
- [ ] PKCE
- [ ] Client credentials
- [ ] Refresh tokens
- [ ] JWT validation
- [ ] JWKS
- [ ] Token expiration
- [ ] Token rotation

---

# Distributed Tracing Flow

```text
Request
  ↓
Service A
  ├── Span A
  ↓
Service B
  ├── Span B
  ↓
Service C
  ├── Span C
  ↓
Database
```

Understand:

- [ ] Trace ID
- [ ] Span ID
- [ ] Parent-child relationship
- [ ] `traceparent`
- [ ] Context propagation
- [ ] Async propagation
- [ ] Sampling
- [ ] Trace/log correlation

---

# Testing Strategy Flow

```text
Fast Feedback
     ↓
Unit Tests
     ↓
Integration Tests
     ↓
Contract Tests
     ↓
E2E Tests
     ↓
Production Monitoring
```

Use the smallest test capable of proving the behavior.

---

# Highest-Priority Deep-Mastery Areas

## Tier 1 — Must Master

- [ ] Microservice boundaries
- [ ] Monolith decomposition
- [ ] DDD bounded contexts
- [ ] Database-per-service
- [ ] Saga
- [ ] Transactional Outbox
- [ ] Idempotency
- [ ] Circuit breaker
- [ ] Retry + timeout + jitter
- [ ] Kafka fundamentals
- [ ] Kafka producer/consumer internals
- [ ] Consumer groups
- [ ] Rebalancing
- [ ] Spring Cloud Gateway
- [ ] Resilience4j
- [ ] Spring Security filter chain
- [ ] Authentication/Authorization
- [ ] JWT
- [ ] OAuth2/OIDC
- [ ] Spring Security Resource Server
- [ ] Distributed tracing
- [ ] Metrics
- [ ] JUnit 5
- [ ] Mockito
- [ ] Spring Boot testing
- [ ] Testcontainers
- [ ] Contract testing

## Tier 2 — Strong Senior-Level Knowledge

- [ ] CQRS
- [ ] Event sourcing
- [ ] TCC
- [ ] gRPC
- [ ] GraphQL
- [ ] Kafka Streams
- [ ] Kafka Connect
- [ ] Schema Registry
- [ ] RabbitMQ
- [ ] Spring Cloud Config
- [ ] Spring Cloud Bus
- [ ] OpenFeign
- [ ] Spring Cloud Stream
- [ ] Spring Cloud Contract
- [ ] Keycloak/Okta/Auth0 integration
- [ ] mTLS
- [ ] Prometheus
- [ ] Grafana
- [ ] Centralized logging
- [ ] WireMock
- [ ] Mutation testing
- [ ] Performance/load testing

## Tier 3 — Advanced / Specialist

- [ ] Event sourcing architecture at scale
- [ ] Kafka transaction internals
- [ ] Kafka Streams state stores
- [ ] Schema evolution strategy
- [ ] Custom Spring Cloud Gateway filters
- [ ] Custom Resilience4j configuration
- [ ] Custom OAuth2 authorization behavior
- [ ] Token customization
- [ ] mTLS certificate lifecycle
- [ ] Reactive context propagation
- [ ] High-cardinality observability
- [ ] Distributed test environments
- [ ] Advanced performance testing

---

# Best Books — Module Summary

| Module | Best Primary Book | Best Deep-Dive / Reference |
|---|---|---|
| Module 1 — Microservices Architecture & Design | **Building Microservices, 2nd Ed. — Sam Newman** | **Microservices Patterns — Chris Richardson** |
| Module 1 — DDD | **Domain-Driven Design — Eric Evans** | **Implementing Domain-Driven Design — Vaughn Vernon** |
| Module 1 — Resilience | **Release It!, 2nd Ed. — Michael T. Nygard** | Microservices Patterns |
| Module 2 — Messaging/Kafka | **Kafka: The Definitive Guide, 2nd Ed.** | **Kafka Streams in Action, 2nd Ed.** |
| Module 2 — Messaging Patterns | **Enterprise Integration Patterns — Hohpe & Woolf** | Kafka/RabbitMQ documentation |
| Module 2 — gRPC | **gRPC Up & Running** | Protocol Buffers documentation |
| Module 2 — GraphQL | **Learning GraphQL** | GraphQL specification |
| Module 3 — Spring Cloud | **Spring Microservices in Action, 2nd Ed.** | Spring Cloud documentation |
| Module 3 — Gateway | Spring Cloud Gateway Reference | Spring Microservices in Action |
| Module 3 — Resilience | **Release It!, 2nd Ed.** | Resilience4j documentation |
| Module 4 — Spring Security | **Spring Security in Action, 2nd Ed.** | Spring Security Reference |
| Module 4 — OAuth/OIDC | **OAuth 2 in Action** | OAuth/OIDC specifications |
| Module 4 — Identity | Spring Security in Action | Keycloak/Okta/Auth0 documentation |
| Module 4 — Zero Trust | **Zero Trust Networks** | SPIFFE/SPIRE documentation |
| Module 5 — Observability | **Distributed Systems Observability** | OpenTelemetry + Micrometer docs |
| Module 5 — SRE | **Site Reliability Engineering** | **The Site Reliability Workbook** |
| Module 6 — Testing | **Unit Testing — Vladimir Khorikov** | JUnit/Mockito/Spring Test docs |
| Module 6 — TDD | **Test-Driven Development: By Example** | Unit Testing |
| Module 6 — Contract Testing | Pact / Spring Cloud Contract docs | Building Microservices |
| Module 6 — Performance Testing | Tool documentation | Release It! |

---

# Recommended Core Book Collection

You do **not** need to read every book cover-to-cover.

If you want a compact senior-level library, prioritize these:

## 1. Building Microservices — Sam Newman
Your main architecture book.

Use for:
- Service decomposition
- Migration
- Communication
- Deployment boundaries
- Distributed-system trade-offs

## 2. Microservices Patterns — Chris Richardson
Your main microservices pattern reference.

Use for:
- Saga
- CQRS
- Event sourcing
- Outbox
- API gateway
- Database-per-service
- Distributed transactions

## 3. Domain-Driven Design — Eric Evans
Your DDD foundation.

## 4. Release It! — Michael T. Nygard
Your resilience/failure-management book.

## 5. Kafka: The Definitive Guide, 2nd Edition
Your Kafka foundation.

## 6. Spring Microservices in Action, 2nd Edition
Your Spring Cloud-oriented practical book.

## 7. Spring Security in Action, 2nd Edition
Your Spring Security foundation.

## 8. OAuth 2 in Action
Your OAuth/OIDC conceptual deep dive.

## 9. Distributed Systems Observability
Your observability foundation.

## 10. Site Reliability Engineering
Your SRE foundation.

## 11. Unit Testing — Vladimir Khorikov
Your testing-design foundation.

## 12. Test-Driven Development: By Example — Kent Beck
Your TDD foundation.

---

# Recommended Study Order

A practical order for this entire syllabus:

```text
Microservices Fundamentals
        ↓
DDD + Service Boundaries
        ↓
Distributed Data
        ↓
Saga + Outbox + Idempotency
        ↓
Resilience Patterns
        ↓
REST + gRPC
        ↓
Kafka
        ↓
RabbitMQ / Cloud Messaging
        ↓
Spring Cloud Discovery
        ↓
Spring Cloud Gateway
        ↓
OpenFeign
        ↓
Config + Cloud Bus
        ↓
Resilience4j
        ↓
Spring Cloud Stream
        ↓
Spring Security Architecture
        ↓
JWT
        ↓
OAuth2 + OIDC
        ↓
Enterprise Identity Providers
        ↓
mTLS + Zero Trust
        ↓
Distributed Tracing
        ↓
Metrics + Prometheus + Grafana
        ↓
Centralized Logging
        ↓
JUnit + Mockito
        ↓
Spring Boot Test Slices
        ↓
Testcontainers
        ↓
WireMock
        ↓
Contract Testing
        ↓
Mutation Testing
        ↓
Load / Performance Testing
```

---

# Final Mastery Standard

Do not mark a topic complete simply because you can configure it.

For example, don't consider Kafka complete because you can create a producer:

```java
kafkaTemplate.send("orders", order);
```

You should be able to explain:

```text
Producer
   ↓
Serializer
   ↓
Partitioner
   ↓
Record Accumulator
   ↓
Batch
   ↓
Broker
   ↓
Partition
   ↓
Replication
   ↓
Consumer Group
   ↓
Consumer
   ↓
Offset
```

And answer:

- [ ] What happens when a broker fails?
- [ ] What happens when a consumer crashes?
- [ ] Why does a rebalance occur?
- [ ] What happens to an uncommitted message?
- [ ] How do retries affect ordering?
- [ ] How do duplicates occur?
- [ ] How do you make the consumer idempotent?
- [ ] What does `acks=all` guarantee?
- [ ] What does idempotent producer actually solve?
- [ ] What does Kafka EOS actually guarantee?

Apply the same standard to:

- [ ] Microservice decomposition
- [ ] DDD
- [ ] Saga
- [ ] Outbox
- [ ] Circuit breakers
- [ ] Kafka
- [ ] Spring Cloud Gateway
- [ ] Resilience4j
- [ ] Spring Security
- [ ] OAuth2/OIDC
- [ ] JWT
- [ ] Observability
- [ ] Testing

The target is:

> **Understand → Design → Implement → Inspect Internals → Handle Failure → Observe → Test → Optimize → Explain the Trade-offs → Operate in Production**
