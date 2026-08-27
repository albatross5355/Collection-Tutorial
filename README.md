# java
Core Java (Deep Mastery)
Language Fundamentals
OOP pillars: Encapsulation, Inheritance, Polymorphism (compile-time vs runtime), Abstraction
SOLID principles with real code examples
Access modifiers, final, static, abstract, default, sealed (Java 17), non-sealed, permits
this vs super, constructor chaining, initialization order (static block ? instance block ? constructor)
Pass-by-value semantics (Java is always pass-by-value)

Data Types & Memory
Primitives vs wrappers, autoboxing/unboxing pitfalls
String pool, String vs StringBuilder vs StringBuffer, intern()
Immutability — how to design immutable classes (final class, final fields, defensive copies)
equals() and hashCode() contract, compareTo() consistency

Collections Framework (must know cold)
ArrayList vs LinkedList vs Vector vs CopyOnWriteArrayList
HashMap internals: buckets, hash, treeify threshold (8), load factor (0.75), resize
ConcurrentHashMap internals (segment locking ? CAS + synchronized bins in Java 8+)
LinkedHashMap, TreeMap, WeakHashMap, IdentityHashMap, EnumMap
HashSet, TreeSet, LinkedHashSet
PriorityQueue, ArrayDeque, BlockingQueue implementations
fail-fast vs fail-safe iterators
Comparable vs Comparator
Exception Handling
Checked vs unchecked, Error vs Exception
try-with-resources, multi-catch, exception chaining
Custom exceptions, when to wrap vs propagate
finally block gotchas (return statements, resource leaks)
Generics
Bounded types, wildcards (? extends, ? super), PECS rule
Type erasure, reifiable types
Generic methods, classes, and constraints

Java 8 to Java 21+ Features (all versions)
Java 8: Lambdas, Streams, Optional, Functional interfaces, Method references, Default/static methods, CompletableFuture, Date/Time API, Nashorn
Java 9: Modules (JPMS), var restrictions, factory methods (List.of), private interface methods
Java 10: var local variables
Java 11 (LTS): HTTP Client, String methods, var in lambdas
Java 14: Switch expressions, NullPointerException helpful messages
Java 15: Text blocks, sealed classes (preview)
Java 16: Records, pattern matching for instanceof
Java 17 (LTS): Sealed classes, enhanced pseudo-random generators
Java 21 (LTS): Virtual threads (Project Loom), pattern matching for switch, record patterns, sequenced collections, structured concurrency (preview)
Java 22-25: Stream gatherers, scoped values, unnamed variables

Concurrency & Multithreading (critical)
Thread lifecycle, Thread vs Runnable vs Callable
synchronized, volatile, Atomic* classes
Java Memory Model (JMM): happens-before, visibility, reordering
wait(), notify(), notifyAll(), spurious wakeups
Locks: ReentrantLock, ReadWriteLock, StampedLock
Executors: ThreadPoolExecutor internals (core/max pool, queue types, rejection policies)
Future, CompletableFuture (chaining, combining, exception handling)
ForkJoinPool, parallel streams, work-stealing
CountDownLatch, CyclicBarrier, Semaphore, Phaser, Exchanger
Deadlock, livelock, starvation — detection and prevention
Virtual threads (Loom) vs platform threads, when to use
Thread-safe design patternss


JVM Internals
Class loading: Bootstrap ? Extension ? Application ? Custom; parent delegation
Memory areas: Heap (Young: Eden/S0/S1, Old), Metaspace, Stack, PC register, Native method stack
Garbage collectors: Serial, Parallel, CMS (deprecated), G1, ZGC, Shenandoah, Epsilon
GC tuning flags: -Xms, -Xmx, -XX:MaxGCPauseMillis, -XX:+UseG1GC
JIT compilation: C1, C2, tiered compilation, escape analysis, inlining
JVM tools: jps, jstack, jmap, jstat, jcmd, jfr, VisualVM, JMC, Eclipse MAT
Heap dump & thread dump analysis
OutOfMemoryError types: Java heap space, Metaspace, GC overhead limit, Direct buffer memory






2. Spring Framework Core
IoC & DI: constructor vs setter vs field injection (prefer constructor)
Bean scopes: singleton, prototype, request, session, application, websocket
Bean lifecycle: instantiation ? populate ? BeanNameAware ? BeanFactoryAware ? ApplicationContextAware ? @PostConstruct ? InitializingBean.afterPropertiesSet ? custom init ? ready ? @PreDestroy ? DisposableBean.destroy
BeanFactory vs ApplicationContext
BeanPostProcessor vs BeanFactoryPostProcessor
@Configuration, @Bean, @ComponentScan, @Import, @Conditional*
@Primary, @Qualifier, @Lazy, @DependsOn
AOP: JoinPoint, Pointcut, Advice types (Before, After, AfterReturning, AfterThrowing, Around), proxy types (JDK dynamic vs CGLIB), self-invocation problem
Spring Expression Language (SpEL)
Event publishing (ApplicationEventPublisher, @EventListener, async events)
Resource abstraction, PropertySource, Environment

3. Spring Boot
Fundamentals
Auto-configuration: @EnableAutoConfiguration, spring.factories / AutoConfiguration.imports
Starters and dependency management via BOM
@SpringBootApplication composition (@Configuration + @EnableAutoConfiguration + @ComponentScan)
Configuration: application.yml/properties, profiles (@Profile, spring.profiles.active), @ConfigurationProperties, @Value, relaxed binding
Property sources order (command line ? env ? yml ? defaults)
Externalized config: Config Server, Vault, environment variables
Actuator endpoints: health, info, metrics, prometheus, loggers, env, beans, heapdump, threaddump; custom health indicators
DevTools, LiveReload, restart mechanism
Banner, logging (Logback/Log4j2), log levels via Actuator
Fat JAR structure, spring-boot-maven-plugin, layered JARs


Web Layer
Spring MVC: DispatcherServlet flow, HandlerMapping, HandlerAdapter, ViewResolver
REST: @RestController, @RequestMapping, @GetMapping etc., @PathVariable, @RequestParam, @RequestBody, @ResponseStatus
ResponseEntity, content negotiation, HttpMessageConverter (Jackson)
Exception handling: @ControllerAdvice, @ExceptionHandler, ResponseEntityExceptionHandler, ProblemDetail (RFC 7807)
Validation: @Valid, @Validated, JSR-380 (Jakarta Bean Validation), custom validators, group validation
Filters, Interceptors, HandlerInterceptor — order and use cases
CORS configuration
File upload/download, multipart
HATEOAS, API versioning (URL, header, media-type)
OpenAPI/Swagger (springdoc-openapi)
WebFlux (reactive): Mono, Flux, functional endpoints, backpressure, when to choose reactive vs MVC


Data Layer
JPA/Hibernate: entity lifecycle (transient, managed, detached, removed), first-level cache, second-level cache (EhCache/Hazelcast)
Relationships: @OneToOne, @OneToMany, @ManyToOne, @ManyToMany, unidirectional vs bidirectional, mappedBy, cascade types, orphan removal
Fetch strategies: LAZY vs EAGER, N+1 problem and solutions (JOIN FETCH, @EntityGraph, batch fetching)
Spring Data JPA: JpaRepository, derived queries, @Query (JPQL/native), Specifications, Query by Example, Projections (interface, DTO, dynamic)
Pagination & Sorting, Pageable, Slice vs Page
Transactions: @Transactional propagation (REQUIRED, REQUIRES_NEW, NESTED, MANDATORY, SUPPORTS, NOT_SUPPORTED, NEVER), isolation levels (READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE), rollback rules, self-invocation trap
Optimistic (@Version) vs Pessimistic locking
Flyway / Liquibase for schema migrations
JDBC Template, NamedParameterJdbcTemplate
QueryDSL / jOOQ for type-safe queries
Connection pooling: HikariCP tuning (maxPoolSize, connectionTimeout, idleTimeout, leakDetectionThreshold)
NoSQL: MongoDB (Spring Data Mongo), Redis (Spring Data Redis, Lettuce vs Jedis), Cassandra, Elasticsearch

4. Microservices Architecture
Principles & Patterns
12-Factor App
Domain-Driven Design (DDD): bounded contexts, aggregates, entities, value objects, domain events, ubiquitous language
CQRS and Event Sourcing
Saga pattern: choreography vs orchestration
Outbox pattern, transactional messaging
API Gateway pattern (Spring Cloud Gateway, Zuul)
BFF (Backend for Frontend)
Strangler Fig for monolith decomposition
Sidecar, Ambassador, Anti-corruption layer
Bulkhead, Circuit Breaker, Retry, Rate Limiter, Time Limiter — Resilience4j
Idempotency, exactly-once vs at-least-once vs at-most-once
Distributed transactions: 2PC (avoid), Saga, TCC

Spring Cloud Ecosystem
Service Discovery: Eureka, Consul, Kubernetes-native
Client-side load balancing: Spring Cloud LoadBalancer (Ribbon deprecated)
API Gateway: Spring Cloud Gateway (routes, predicates, filters, rate limiting)
Config Server: Git-backed, encryption, refresh scope, Spring Cloud Bus
Circuit Breaker: Resilience4j (replaces Hystrix)
Distributed Tracing: Micrometer Tracing + Zipkin/Jaeger (Sleuth deprecated)
OpenFeign declarative REST client
Spring Cloud Stream (Kafka/RabbitMQ abstraction)
Spring Cloud Contract for consumer-driven contract testing

Inter-Service Communication
Sync: REST, gRPC (Protocol Buffers), GraphQL
Async: Kafka, RabbitMQ, ActiveMQ, AWS SQS/SNS
Kafka deep dive: topics, partitions, consumer groups, offsets, rebalancing, exactly-once semantics, transactions, Streams, Connect, Schema Registry (Avro/Protobuf)
RabbitMQ: exchanges (direct, topic, fanout, headers), queues, DLQ, TTL

5. Security
Spring Security architecture: SecurityFilterChain, AuthenticationManager, AuthenticationProvider, UserDetailsService
Authentication: form login, basic auth, JWT, OAuth2, OIDC
Authorization: @PreAuthorize, @PostAuthorize, @Secured, method security, URL-based
JWT: structure (header.payload.signature), signing (HS256, RS256), refresh tokens, blacklisting/rotation
OAuth2 flows: Authorization Code (+ PKCE), Client Credentials, Password (deprecated), Implicit (deprecated), Device Code
Spring Authorization Server (replaces Spring Security OAuth)
Keycloak / Okta / Auth0 integration
CSRF, CORS, XSS, SQL Injection, SSRF — how Spring mitigates
Password hashing: BCrypt, Argon2, PBKDF2
Secrets management: HashiCorp Vault, AWS Secrets Manager, Azure Key Vault
mTLS between services
Rate limiting, API keys
OWASP Top 10 — know each one

6. Testing (Non-Negotiable at 5 Years)
JUnit 5 (Jupiter): lifecycle, extensions, parameterized, nested, dynamic tests
Mockito: @Mock, @InjectMocks, @Spy, argument captors, when/thenReturn, verify, mockStatic, mockConstruction
AssertJ / Hamcrest for fluent assertions
Spring Boot Test: @SpringBootTest, @WebMvcTest, @DataJpaTest, @RestClientTest, @JsonTest
MockMvc vs WebTestClient vs TestRestTemplate
Testcontainers: Postgres, Kafka, Redis, LocalStack — integration testing with real dependencies
WireMock for HTTP stubbing
Contract testing: Spring Cloud Contract, Pact
Performance testing: JMeter, Gatling, k6
Mutation testing: PIT
Coverage: JaCoCo (line, branch, method)
TDD, BDD (Cucumber)
Test pyramid: unit > integration > E2E


7. Databases
SQL
Deep SQL: joins (inner, left, right, full, cross, self), subqueries, CTEs, window functions (ROW_NUMBER, RANK, LEAD/LAG), stored procedures, triggers, views
Indexing: B-tree, hash, composite, covering, partial; when indexes hurt
Query plans (EXPLAIN ANALYZE), query optimization
Normalization (1NF–3NF, BCNF) vs denormalization trade-offs
ACID properties, isolation levels, phantom/dirty/non-repeatable reads
Sharding, partitioning, replication, read replicas
Postgres/MySQL specifics
NoSQL
Document (MongoDB), Key-value (Redis, DynamoDB), Column (Cassandra), Graph (Neo4j)
CAP theorem, PACELC
Eventual consistency, tunable consistency

8. DevOps & Cloud
Build & CI/CD
Maven (lifecycle, phases, plugins, profiles, BOM, multi-module) and Gradle basics
Git: rebase vs merge, cherry-pick, reflog, bisect, GitFlow/trunk-based
CI/CD: Jenkins, GitHub Actions, GitLab CI, Azure DevOps, ArgoCD (GitOps)
Artifact repositories: Nexus, Artifactory
Containers & Orchestration
Docker: Dockerfile best practices (multi-stage, layer caching, non-root, distroless), .dockerignore, image scanning
Docker Compose
Kubernetes: Pods, Deployments, Services (ClusterIP/NodePort/LoadBalancer), Ingress, ConfigMap, Secret, PVC, StatefulSet, DaemonSet, Job, CronJob, HPA, VPA, RBAC, namespaces, network policies
Helm charts, Kustomize
Service Mesh: Istio, Linkerd (mTLS, traffic shaping, observability)

Cloud (know at least one deeply)
AWS: EC2, ECS/EKS, Lambda, S3, RDS, DynamoDB, SQS/SNS, API Gateway, CloudWatch, IAM, VPC, Route53
Azure: AKS, App Service, Functions, Service Bus, Cosmos DB, Key Vault, Application Insights
GCP: GKE, Cloud Run, Pub/Sub, BigQuery
Observability
Metrics: Micrometer + Prometheus + Grafana
Logging: SLF4J + Logback/Log4j2, structured logging (JSON), MDC for correlation IDs, ELK/EFK stack (Elasticsearch, Logstash/Fluentd, Kibana), Loki
Tracing: OpenTelemetry, Jaeger, Zipkin
APM: Datadog, New Relic, Dynatrace, AppDynamics
Health checks: liveness, readiness, startup probes

9. System Design & Architecture (senior-level expectation)
Scalability: horizontal vs vertical, stateless services
Load balancing: L4 vs L7, algorithms (round-robin, least-connections, IP-hash, consistent hashing)
Caching strategies: cache-aside, read-through, write-through, write-behind, refresh-ahead; cache invalidation; Redis patterns (session store, distributed lock via Redlock, pub/sub, rate limiter)
CDN, edge caching
Message queues vs event streams
Rate limiting algorithms: token bucket, leaky bucket, fixed/sliding window
Consistent hashing, sharding strategies
Database scaling: read replicas, sharding, federation, materialized views
CAP theorem, BASE vs ACID
Idempotency keys, deduplication
Designing APIs: REST maturity (Richardson), pagination, filtering, versioning, error responses, HATEOAS
Real-world designs: URL shortener, rate limiter, notification system, chat, feed, payment system, ride-sharing

10. Design Patterns
GoF Patterns (know all 23, master these)
Creational: Singleton (thread-safe, enum, Bill Pugh), Factory, Abstract Factory, Builder, Prototype
Structural: Adapter, Decorator, Proxy, Facade, Composite, Bridge, Flyweight
Behavioral: Strategy, Observer, Command, Template Method, Chain of Responsibility, State, Iterator, Mediator, Memento, Visitor
Enterprise & Spring Patterns
Repository, Service, DTO, Mapper (MapStruct)
Specification pattern
Circuit breaker, Retry, Bulkhead
CQRS, Event Sourcing, Saga, Outbox
Anti-patterns to Avoid
God class, anemic domain model, distributed monolith, chatty microservices, shared database across services

11. Data Structures & Algorithms
Even senior devs get asked:

Arrays, LinkedList, Stack, Queue, Deque, HashMap/HashSet, TreeMap/TreeSet, Heap/PriorityQueue, Trie, Graph, Tree (BST, AVL, Red-Black, B-tree)
Complexity: Big-O for common operations
Algorithms: sorting (merge, quick, heap), searching (binary), BFS/DFS, Dijkstra, dynamic programming basics, sliding window, two pointers
Design problems: LRU cache, LFU cache, rate limiter




