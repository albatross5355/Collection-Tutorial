# Spring Framework & Spring Boot Mastery — Comprehensive Guide + Booklist

> Scope intentionally stays within the original 4 modules: **Spring Framework Core, Spring Boot Fundamentals, Web Layer (Spring MVC & WebFlux), and Data Layer**.
>
> The goal is **deep mastery**, not merely knowing annotations.

---

# How to Use This Guide

For every topic, follow this mastery cycle:

1. What is it?
2. Why does Spring provide it?
3. How is it implemented?
4. What happens internally?
5. What is the lifecycle/order?
6. What are the edge cases?
7. What are the common mistakes?
8. What are the performance implications?
9. When should it be used?
10. When should it not be used?
11. How is it debugged?
12. Can you implement a small example?
13. Can you explain it without notes?

---

# Module 1 — Spring Framework Core

## 1.1 IoC & Dependency Injection

### Topics
- [ ] Inversion of Control
- [ ] Dependency Injection
- [ ] Spring IoC Container
- [ ] Dependency graph
- [ ] Bean definition
- [ ] Bean creation
- [ ] Dependency resolution
- [ ] Dependency injection vs manual object creation
- [ ] Loose coupling
- [ ] Dependency inversion vs dependency injection

### Injection Types
- [ ] Constructor injection
- [ ] Setter injection
- [ ] Field injection
- [ ] Constructor vs setter vs field injection
- [ ] Why constructor injection is generally preferred
- [ ] Optional dependencies
- [ ] Multiple constructors
- [ ] `@Autowired`
- [ ] Single-constructor autowiring
- [ ] `@Autowired(required = false)`
- [ ] `ObjectProvider`
- [ ] `Provider`
- [ ] `Optional<T>` injection

### Dependency Resolution
- [ ] Type-based resolution
- [ ] Name-based resolution
- [ ] `@Primary`
- [ ] `@Qualifier`
- [ ] Custom qualifier annotations
- [ ] Multiple beans of the same type
- [ ] Collection injection
- [ ] Map injection
- [ ] Ordered dependencies
- [ ] `@Order`
- [ ] Generic type-based injection
- [ ] `NoUniqueBeanDefinitionException`
- [ ] `NoSuchBeanDefinitionException`

### Circular Dependencies
- [ ] What is a circular dependency?
- [ ] Constructor circular dependency
- [ ] Setter/field circular dependency
- [ ] Why constructor cycles fail
- [ ] `@Lazy`
- [ ] Refactoring circular dependencies
- [ ] Redesigning dependencies
- [ ] Why circular dependencies are generally a design smell

### Best Books / References
**Primary**
- **Spring Start Here — Laurentiu Spilca** — excellent for learning Spring Core, IoC and DI from first principles.

**Deep Dive**
- **Spring in Action — Craig Walls** — broader Spring coverage and practical application.

**Reference**
- **Spring Framework Reference Documentation** — use for exact container behavior and current APIs.

---

# 1.2 Bean Scopes

### Standard Scopes
- [ ] Singleton
- [ ] Prototype
- [ ] Request
- [ ] Session
- [ ] Application
- [ ] WebSocket

### Scope Behavior
- [ ] Singleton instance semantics
- [ ] Singleton per ApplicationContext
- [ ] Prototype bean creation
- [ ] Prototype dependency inside singleton
- [ ] Scoped proxies
- [ ] `@Scope`
- [ ] `proxyMode`
- [ ] Request-scoped dependency inside singleton
- [ ] Session-scoped dependency inside singleton

### Custom Scopes
- [ ] Custom scope concept
- [ ] `Scope` interface
- [ ] Registering custom scopes
- [ ] Scope lifecycle
- [ ] Scope destruction

### Best Books / References
**Primary**
- **Spring Start Here — Laurentiu Spilca**

**Deep Dive**
- **Spring in Action — Craig Walls**

**Reference**
- Spring Framework Reference Documentation — Bean Scopes

---

# 1.3 Bean Lifecycle

### Bean Creation
- [ ] Bean definition registration
- [ ] Bean instantiation
- [ ] Constructor execution
- [ ] Dependency injection
- [ ] Property population
- [ ] Aware callbacks
- [ ] Bean post-processing
- [ ] Initialization
- [ ] Post-initialization processing
- [ ] Bean ready for use

### Aware Interfaces
- [ ] `BeanNameAware`
- [ ] `BeanFactoryAware`
- [ ] `ApplicationContextAware`
- [ ] `EnvironmentAware`
- [ ] `ResourceLoaderAware`
- [ ] When Aware interfaces should/shouldn't be used

### BeanPostProcessor
- [ ] `BeanPostProcessor`
- [ ] `postProcessBeforeInitialization`
- [ ] `postProcessAfterInitialization`
- [ ] Bean wrapping
- [ ] Proxy creation
- [ ] Relationship between BPP and AOP

### Initialization
- [ ] `@PostConstruct`
- [ ] `InitializingBean.afterPropertiesSet()`
- [ ] Custom `init-method`
- [ ] Initialization ordering
- [ ] Choosing between initialization mechanisms

### Destruction
- [ ] `@PreDestroy`
- [ ] `DisposableBean.destroy()`
- [ ] Custom `destroy-method`
- [ ] Singleton destruction
- [ ] Prototype destruction limitation
- [ ] Resource cleanup

### Practical Mastery
- [ ] Draw the complete lifecycle from memory
- [ ] Implement a custom BeanPostProcessor
- [ ] Debug lifecycle ordering
- [ ] Observe initialization and destruction logs

### Best Books / References
**Primary**
- **Spring Start Here — Laurentiu Spilca**

**Deep Dive**
- **Spring in Action — Craig Walls**

**Best Reference**
- Spring Framework Reference Documentation — Container Extension Points / Bean Lifecycle

---

# 1.4 BeanFactory & ApplicationContext

- [ ] `BeanFactory`
- [ ] `ApplicationContext`
- [ ] Differences
- [ ] ApplicationContext features
- [ ] MessageSource
- [ ] Event publishing
- [ ] Resource loading
- [ ] Environment abstraction
- [ ] Why Spring applications normally use ApplicationContext

### Best Books / References
**Primary:** Spring Start Here  
**Deep Dive:** Spring in Action  
**Reference:** Spring Framework Reference Documentation

---

# 1.5 BeanFactoryPostProcessor & BeanPostProcessor

### BeanFactoryPostProcessor
- [ ] `BeanFactoryPostProcessor`
- [ ] BeanDefinition modification
- [ ] Execution timing
- [ ] `BeanDefinitionRegistryPostProcessor`
- [ ] Framework use cases

### BeanPostProcessor
- [ ] `BeanPostProcessor`
- [ ] Before initialization
- [ ] After initialization
- [ ] Wrapping beans
- [ ] Proxy creation
- [ ] Ordering

### Critical Distinction

```text
BeanFactoryPostProcessor
        ↓
modifies BeanDefinitions

BeanPostProcessor
        ↓
modifies Bean instances
```

### Best Books / References
**Primary:** Spring Start Here  
**Deep Dive:** Spring in Action  
**Reference:** Spring Framework Reference Documentation

---

# 1.6 Configuration & Bean Registration

### Java Configuration
- [ ] `@Configuration`
- [ ] `@Bean`
- [ ] `@ComponentScan`
- [ ] `@Component`
- [ ] `@Service`
- [ ] `@Repository`
- [ ] `@Controller`
- [ ] `@Import`
- [ ] `@ImportResource`

### `@Bean`
- [ ] Bean method
- [ ] Bean naming
- [ ] Bean aliases
- [ ] `@Bean(name = ...)`
- [ ] Bean dependencies
- [ ] Inter-bean method calls
- [ ] Full vs lite `@Configuration`

### Component Scanning
- [ ] Base package scanning
- [ ] Include filters
- [ ] Exclude filters
- [ ] Annotation filters
- [ ] Assignable-type filters
- [ ] Component naming

### Best Books / References
**Primary:** Spring Start Here  
**Practical:** Spring in Action  
**Reference:** Spring Framework Reference Documentation

---

# 1.7 Conditional Configuration

- [ ] `@Conditional`
- [ ] `Condition`
- [ ] `@ConditionalOnClass`
- [ ] `@ConditionalOnMissingClass`
- [ ] `@ConditionalOnBean`
- [ ] `@ConditionalOnMissingBean`
- [ ] `@ConditionalOnProperty`
- [ ] `@ConditionalOnResource`
- [ ] `@ConditionalOnExpression`
- [ ] `@ConditionalOnWebApplication`
- [ ] `@ConditionalOnNotWebApplication`
- [ ] Creating custom conditions
- [ ] Condition evaluation
- [ ] Debugging why a bean was/wasn't created

### Best Books / References
**Primary:** Spring Boot Up & Running — Mark Heckler  
**Deep Dive:** Spring Boot Reference Documentation — Auto-configuration  
**Source Study:** Spring Boot auto-configuration source

---

# 1.8 Bean Selection & Lazy Initialization

- [ ] `@Primary`
- [ ] `@Qualifier`
- [ ] Custom qualifier annotations
- [ ] `@Lazy`
- [ ] `@DependsOn`
- [ ] Eager singleton creation
- [ ] Lazy singleton creation
- [ ] Dependency ordering

### Best Books / References
**Primary:** Spring Start Here  
**Deep Dive:** Spring in Action  
**Reference:** Spring Framework Reference Documentation

---

# 1.9 Spring AOP

## AOP Fundamentals
- [ ] Cross-cutting concerns
- [ ] Aspect
- [ ] Join point
- [ ] Pointcut
- [ ] Advice
- [ ] Target object
- [ ] Proxy
- [ ] Weaving

## Advice Types
- [ ] `@Before`
- [ ] `@After`
- [ ] `@AfterReturning`
- [ ] `@AfterThrowing`
- [ ] `@Around`

## Pointcuts
- [ ] Execution expressions
- [ ] Method matching
- [ ] Package matching
- [ ] Annotation matching
- [ ] Argument matching
- [ ] Combining pointcuts
- [ ] Named pointcuts

## Proxy Mechanisms
- [ ] JDK dynamic proxy
- [ ] Interface-based proxying
- [ ] CGLIB/class-based proxying
- [ ] Proxy selection
- [ ] Proxy limitations
- [ ] Final classes/methods
- [ ] Final methods and proxying

## Self Invocation
- [ ] What self-invocation means
- [ ] Why proxy advice is bypassed
- [ ] Self-invocation with `@Transactional`
- [ ] Refactoring solution
- [ ] Self-injection considerations
- [ ] `AopContext` considerations

## AOP Configuration
- [ ] `@EnableAspectJAutoProxy`
- [ ] `@Aspect`
- [ ] `@Pointcut`
- [ ] `@Order`
- [ ] Aspect ordering
- [ ] Proxy exposure

## Practical AOP
- [ ] Logging aspect
- [ ] Execution-time aspect
- [ ] Audit aspect
- [ ] Exception aspect
- [ ] Understand when AOP is appropriate

### Best Books / References
**Primary:** Spring in Action — AOP chapters  
**Deep Dive:** Spring Start Here — AOP sections  
**Conceptual Reference:** AspectJ Programming Guide / Spring AOP Reference Documentation

---

# 1.10 Spring Expression Language — SpEL

- [ ] SpEL fundamentals
- [ ] Literals
- [ ] Operators
- [ ] Property access
- [ ] Method invocation
- [ ] Collections
- [ ] Maps
- [ ] Bean references
- [ ] Environment/property access
- [ ] `@Value`
- [ ] SpEL in conditions
- [ ] SpEL performance/security considerations

### Best Books / References
**Primary:** Spring in Action  
**Reference:** Spring Expression Language documentation

---

# 1.11 Spring Events

- [ ] Application events
- [ ] `ApplicationEvent`
- [ ] `ApplicationEventPublisher`
- [ ] `@EventListener`
- [ ] Event listener ordering
- [ ] Conditional event listeners
- [ ] Generic events
- [ ] Synchronous events
- [ ] Asynchronous event listeners
- [ ] `@Async`
- [ ] Event exception handling
- [ ] Transaction-aware event concepts

### Best Books / References
**Primary:** Spring in Action  
**Reference:** Spring Framework Events documentation

---

# 1.12 Environment & Resource Abstraction

### Environment
- [ ] `Environment`
- [ ] `PropertySource`
- [ ] Profiles
- [ ] Active profiles
- [ ] Default profiles
- [ ] Property resolution

### Resources
- [ ] `Resource`
- [ ] `ResourceLoader`
- [ ] Classpath resources
- [ ] File resources
- [ ] URL resources
- [ ] `classpath:`
- [ ] `file:`
- [ ] Resource abstraction

### Best Books / References
**Primary:** Spring Start Here  
**Reference:** Spring Framework Reference Documentation

---

# Module 2 — Spring Boot Fundamentals

# 2.1 Spring Boot Architecture

- [ ] What Spring Boot solves
- [ ] Spring Framework vs Spring Boot
- [ ] Convention over configuration
- [ ] Opinionated defaults
- [ ] Starters
- [ ] Auto-configuration
- [ ] Embedded server
- [ ] Production-ready features

### Best Books
**Primary:** Spring Boot Up & Running — Mark Heckler  
**Alternative:** Spring in Action — Spring Boot chapters

---

# 2.2 `@SpringBootApplication`

- [ ] `@SpringBootApplication`
- [ ] `@Configuration`
- [ ] `@EnableAutoConfiguration`
- [ ] `@ComponentScan`
- [ ] How annotations combine
- [ ] Main application class
- [ ] `SpringApplication.run()`

### Best Books / References
**Primary:** Spring Boot Up & Running  
**Reference:** Spring Boot Reference Documentation

---

# 2.3 Auto-Configuration

- [ ] Auto-configuration concept
- [ ] Auto-configuration classes
- [ ] `AutoConfiguration.imports`
- [ ] Historical `spring.factories`
- [ ] Conditional configuration
- [ ] `@ConditionalOnClass`
- [ ] `@ConditionalOnMissingBean`
- [ ] `@ConditionalOnProperty`
- [ ] `@ConditionalOnBean`
- [ ] `@ConditionalOnWebApplication`
- [ ] Auto-configuration ordering
- [ ] `@AutoConfigureBefore`
- [ ] `@AutoConfigureAfter`
- [ ] Excluding auto-configuration
- [ ] `@SpringBootApplication(exclude = ...)`
- [ ] Auto-configuration report
- [ ] Condition evaluation
- [ ] Debugging why configuration was applied/not applied

### Best Books / References
**Primary:** Spring Boot Up & Running  
**Deep Dive:** Spring Boot Reference Documentation  
**Advanced:** Read relevant Spring Boot auto-configuration source code

---

# 2.4 Application Startup & Lifecycle

- [ ] `SpringApplication`
- [ ] Environment preparation
- [ ] ApplicationContext creation
- [ ] Bean registration
- [ ] Bean instantiation
- [ ] Embedded server startup
- [ ] Application events
- [ ] `ApplicationStartingEvent`
- [ ] `ApplicationEnvironmentPreparedEvent`
- [ ] `ApplicationContextInitializedEvent`
- [ ] `ApplicationPreparedEvent`
- [ ] `ApplicationStartedEvent`
- [ ] `ApplicationReadyEvent`
- [ ] `ApplicationFailedEvent`

### Startup Hooks
- [ ] `CommandLineRunner`
- [ ] `ApplicationRunner`
- [ ] Difference between them
- [ ] Runner ordering
- [ ] `@Order`

### Failure Handling
- [ ] Failure analyzers
- [ ] Startup diagnostics
- [ ] Common startup failures
- [ ] Condition evaluation debugging

### Best Books / References
**Primary:** Spring Boot Up & Running  
**Reference:** Spring Boot Reference Documentation

---

# 2.5 Dependency & Build Management

- [ ] Spring Boot starters
- [ ] Starter dependencies
- [ ] Dependency management
- [ ] BOM
- [ ] Maven dependency management
- [ ] Gradle dependency management
- [ ] Version alignment
- [ ] Dependency conflicts
- [ ] Transitive dependencies
- [ ] Dependency tree analysis

### Best Books / References
**Primary:** Spring Boot Up & Running  
**Reference:** Spring Boot Maven/Gradle plugin documentation

---

# 2.6 Executable & Layered JARs

- [ ] Spring Boot fat JAR
- [ ] JAR structure
- [ ] `BOOT-INF/classes`
- [ ] `BOOT-INF/lib`
- [ ] Boot loader
- [ ] `spring-boot-maven-plugin`
- [ ] Executable JAR
- [ ] Layered JARs
- [ ] Why layers matter for containers
- [ ] Docker image optimization

### Best References
**Primary:** Spring Boot Reference Documentation  
**Supplement:** Spring Boot Maven Plugin documentation

---

# 2.7 AOT & Native Image

- [ ] Spring AOT
- [ ] Runtime reflection limitations
- [ ] Build-time processing
- [ ] GraalVM Native Image
- [ ] Native executable
- [ ] Startup-time benefits
- [ ] Memory considerations
- [ ] Native-image limitations
- [ ] Runtime hints
- [ ] JVM vs native deployment trade-offs

### Best References
**Primary:** Spring Boot Native Image documentation  
**Supplement:** GraalVM Native Image documentation

---

# 2.8 Configuration Management

## Configuration Files
- [ ] `application.properties`
- [ ] `application.yml`
- [ ] YAML structure
- [ ] Property placeholders
- [ ] Default values
- [ ] Profile-specific files

## Profiles
- [ ] `@Profile`
- [ ] Active profiles
- [ ] Default profiles
- [ ] Profile groups
- [ ] Profile-specific configuration
- [ ] Profile activation

## `@ConfigurationProperties`
- [ ] Type-safe configuration
- [ ] Prefix
- [ ] Binding
- [ ] Nested configuration
- [ ] Validation
- [ ] Immutable configuration
- [ ] Constructor binding concepts
- [ ] Relaxed binding

## `@Value`
- [ ] Property injection
- [ ] Default values
- [ ] SpEL integration
- [ ] `@Value` vs `@ConfigurationProperties`

## Property Sources
- [ ] Command-line arguments
- [ ] Environment variables
- [ ] System properties
- [ ] Application configuration
- [ ] Profile-specific configuration
- [ ] Property precedence
- [ ] External configuration

## External Configuration
- [ ] Spring Cloud Config Server
- [ ] HashiCorp Vault
- [ ] External configuration concepts

### Best Books / References
**Primary:** Spring Boot Up & Running  
**Deep Dive:** Spring in Action  
**Reference:** Spring Boot Externalized Configuration documentation

---

# 2.9 Actuator & Observability

## Actuator
- [ ] Spring Boot Actuator
- [ ] Endpoint exposure
- [ ] Management port
- [ ] Endpoint configuration

## Endpoints
- [ ] `health`
- [ ] `info`
- [ ] `metrics`
- [ ] `prometheus`
- [ ] `loggers`
- [ ] `env`
- [ ] `beans`
- [ ] `heapdump`
- [ ] `threaddump`
- [ ] `mappings`
- [ ] `conditions`

## Customization
- [ ] Custom health indicator
- [ ] Custom actuator endpoint
- [ ] Health groups
- [ ] Liveness
- [ ] Readiness
- [ ] Application availability

## Metrics
- [ ] Micrometer
- [ ] Counters
- [ ] Gauges
- [ ] Timers
- [ ] Distribution summaries
- [ ] Custom metrics
- [ ] Prometheus integration

## Tracing
- [ ] Distributed tracing
- [ ] Micrometer Tracing
- [ ] Brave
- [ ] OpenTelemetry
- [ ] Trace ID
- [ ] Span ID
- [ ] W3C trace context
- [ ] Context propagation
- [ ] Trace/log correlation

### Best Books / References
**Primary:** Spring Boot Up & Running  
**Deep Dive:** Spring Boot Reference Documentation — Actuator  
**Observability Reference:** Micrometer documentation  
**Tracing Reference:** Micrometer Tracing documentation

---

# 2.10 Developer Experience

- [ ] Spring Boot DevTools
- [ ] Automatic restart
- [ ] LiveReload
- [ ] Restart classloader concept
- [ ] DevTools limitations
- [ ] Logging configuration
- [ ] Logback
- [ ] Log4j2
- [ ] Log levels
- [ ] Runtime log-level changes with Actuator
- [ ] Custom startup banner
- [ ] Startup diagnostics

### Best Books / References
**Primary:** Spring Boot Up & Running  
**Reference:** Spring Boot Developer Tools documentation

---

# Module 3 — Web Layer: Spring MVC & WebFlux

# 3.1 Spring MVC Architecture

- [ ] Servlet architecture
- [ ] DispatcherServlet
- [ ] Front Controller pattern
- [ ] Request lifecycle
- [ ] HandlerMapping
- [ ] HandlerAdapter
- [ ] Controller invocation
- [ ] Argument resolution
- [ ] Return-value handling
- [ ] HttpMessageConverter
- [ ] ViewResolver
- [ ] ExceptionResolver
- [ ] Complete DispatcherServlet flow

### Request Flow

```text
HTTP Request
     ↓
DispatcherServlet
     ↓
HandlerMapping
     ↓
HandlerAdapter
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
Response processing
     ↓
HttpMessageConverter / ViewResolver
     ↓
HTTP Response
```

### Best Books
**Primary:** Spring in Action  
**Deep Dive:** Spring Start Here  
**Reference:** Spring Framework Web MVC documentation

---

# 3.2 REST API Development

## Controllers
- [ ] `@Controller`
- [ ] `@RestController`
- [ ] `@RequestMapping`
- [ ] `@GetMapping`
- [ ] `@PostMapping`
- [ ] `@PutMapping`
- [ ] `@PatchMapping`
- [ ] `@DeleteMapping`

## Request Binding
- [ ] `@PathVariable`
- [ ] `@RequestParam`
- [ ] `@RequestBody`
- [ ] `@RequestHeader`
- [ ] `@CookieValue`
- [ ] `@RequestPart`
- [ ] Optional parameters
- [ ] Default values
- [ ] Complex object binding

## Responses
- [ ] `ResponseEntity`
- [ ] `@ResponseStatus`
- [ ] HTTP status codes
- [ ] Response headers
- [ ] Response body
- [ ] Empty responses

### Best Books
**Primary:** Spring in Action  
**Practical:** Spring Boot Up & Running  
**Reference:** Spring MVC documentation

---

# 3.3 HTTP Message Conversion & Jackson

- [ ] `HttpMessageConverter`
- [ ] JSON conversion
- [ ] Jackson integration
- [ ] Serialization
- [ ] Deserialization
- [ ] ObjectMapper
- [ ] Jackson modules
- [ ] Naming strategies
- [ ] Date/time serialization
- [ ] Null handling
- [ ] Custom serializers
- [ ] Custom deserializers
- [ ] `@JsonProperty`
- [ ] `@JsonIgnore`
- [ ] `@JsonInclude`
- [ ] Cyclic object graphs
- [ ] Jackson configuration in Spring Boot

### Best References
**Primary:** Jackson documentation  
**Spring Integration:** Spring Framework Web MVC documentation  
**Practical:** Spring in Action

---

# 3.4 Content Negotiation

- [ ] Content negotiation
- [ ] `Accept`
- [ ] `Content-Type`
- [ ] Media types
- [ ] JSON
- [ ] XML concepts
- [ ] Path strategy
- [ ] Parameter strategy
- [ ] Header strategy
- [ ] Message converter selection

### Best References
**Primary:** Spring Framework Web MVC documentation  
**Supplement:** Spring in Action

---

# 3.5 Exception Handling

- [ ] Exception propagation through MVC
- [ ] `@ExceptionHandler`
- [ ] `@ControllerAdvice`
- [ ] `@RestControllerAdvice`
- [ ] Global exception handling
- [ ] Local vs global handlers
- [ ] `ResponseEntityExceptionHandler`
- [ ] Overriding framework handlers
- [ ] `ResponseStatusException`
- [ ] ProblemDetail
- [ ] RFC 7807
- [ ] Consistent error response structure
- [ ] Validation error responses

### Best Books / References
**Primary:** Spring in Action  
**Reference:** Spring Framework Web MVC documentation

---

# 3.6 Validation

- [ ] Jakarta Bean Validation
- [ ] `@Valid`
- [ ] `@Validated`
- [ ] Constraint annotations
- [ ] `@NotNull`
- [ ] `@NotBlank`
- [ ] `@Size`
- [ ] `@Min`
- [ ] `@Max`
- [ ] `@Pattern`
- [ ] Method validation
- [ ] Custom validators
- [ ] `ConstraintValidator`
- [ ] Validation groups
- [ ] Nested-object validation
- [ ] Validation error handling

### Best References
**Primary:** Hibernate Validator documentation  
**Spring Integration:** Spring Framework validation documentation  
**Practical:** Spring in Action

---

# 3.7 Filters & Interceptors

## Filters
- [ ] Servlet Filter
- [ ] Filter chain
- [ ] Filter ordering
- [ ] Request/response wrapping
- [ ] OncePerRequestFilter concepts

## Interceptors
- [ ] `HandlerInterceptor`
- [ ] `preHandle`
- [ ] `postHandle`
- [ ] `afterCompletion`
- [ ] Interceptor ordering
- [ ] Filters vs interceptors
- [ ] Choosing the correct mechanism

### Best References
**Primary:** Spring Framework Web MVC documentation  
**Practical:** Spring in Action

---

# 3.8 CORS

- [ ] Same-origin concept
- [ ] CORS
- [ ] Preflight request
- [ ] OPTIONS
- [ ] `@CrossOrigin`
- [ ] Global CORS configuration
- [ ] Controller-level CORS
- [ ] Allowed origins
- [ ] Allowed methods
- [ ] Allowed headers
- [ ] Credentials
- [ ] Common CORS mistakes

### Best References
**Primary:** Spring Framework Web MVC documentation  
**Supplement:** MDN CORS documentation

---

# 3.9 Advanced REST

## File Handling
- [ ] Multipart requests
- [ ] `MultipartFile`
- [ ] File upload
- [ ] Multiple file upload
- [ ] File download
- [ ] Streaming responses
- [ ] Large file handling

## API Versioning
- [ ] URL path versioning
- [ ] Query parameter versioning
- [ ] Header versioning
- [ ] Media-type versioning
- [ ] Versioning trade-offs

## HATEOAS
- [ ] HATEOAS
- [ ] Hypermedia concepts
- [ ] Spring HATEOAS
- [ ] Resource representation
- [ ] Links
- [ ] When HATEOAS is useful

## API Documentation
- [ ] OpenAPI 3
- [ ] Swagger
- [ ] springdoc-openapi
- [ ] API metadata
- [ ] Request/response schemas
- [ ] Error documentation
- [ ] Versioned API documentation

### Best Books / References
**Primary:** Spring in Action  
**OpenAPI:** OpenAPI Specification + springdoc-openapi documentation  
**HATEOAS:** Spring HATEOAS reference documentation

---

# 3.10 Spring WebFlux

## Reactive Fundamentals
- [ ] Reactive programming
- [ ] Reactive Streams
- [ ] Publisher
- [ ] Subscriber
- [ ] Subscription
- [ ] Backpressure

## Reactor
- [ ] `Mono`
- [ ] `Flux`
- [ ] Operators
- [ ] Mapping
- [ ] Filtering
- [ ] Combining publishers
- [ ] Error handling
- [ ] Retry
- [ ] Timeout
- [ ] Scheduling

## WebFlux Models
- [ ] Annotated controllers
- [ ] Functional endpoints
- [ ] `RouterFunction`
- [ ] `HandlerFunction`

## Execution Model
- [ ] Event loop
- [ ] Non-blocking I/O
- [ ] Blocking vs non-blocking
- [ ] Scheduler concepts
- [ ] Blocking calls in WebFlux
- [ ] Thread usage

## MVC vs WebFlux
- [ ] Thread-per-request model
- [ ] Event-loop model
- [ ] Blocking workloads
- [ ] Non-blocking workloads
- [ ] Throughput
- [ ] Latency
- [ ] Complexity
- [ ] Operational considerations
- [ ] Decision matrix

### Best Books / References
**Primary:** Reactive Spring — Josh Long  
**Deep Dive:** Project Reactor Reference Guide  
**Spring Reference:** Spring WebFlux documentation

---

# Module 4 — Data Layer

# 4.1 JPA & Hibernate Fundamentals

- [ ] ORM concept
- [ ] JPA specification
- [ ] Hibernate implementation
- [ ] Entity
- [ ] EntityManager
- [ ] Persistence Context
- [ ] Entity lifecycle

## Entity States
- [ ] Transient
- [ ] Managed/Persistent
- [ ] Detached
- [ ] Removed
- [ ] `persist`
- [ ] `merge`
- [ ] `remove`
- [ ] `refresh`
- [ ] `detach`

## Persistence Context
- [ ] First-level cache
- [ ] Dirty checking
- [ ] Automatic flush
- [ ] Flush modes
- [ ] Write-behind behavior
- [ ] Entity identity

## Second-Level Cache
- [ ] Second-level cache
- [ ] Hibernate cache architecture
- [ ] Ehcache concepts
- [ ] Hazelcast concepts
- [ ] Cache regions
- [ ] Query cache concepts
- [ ] When second-level cache helps
- [ ] Cache invalidation concerns

### Best Books
**Primary:** Java Persistence with Spring Data and Hibernate — Catalin Tudose  
**Deep Dive:** High-Performance Java Persistence — Vlad Mihalcea  
**Reference:** Hibernate ORM documentation + Jakarta Persistence specification

---

# 4.2 Entity Mapping

## Basic Mapping
- [ ] `@Entity`
- [ ] `@Table`
- [ ] `@Id`
- [ ] ID generation
- [ ] `@GeneratedValue`
- [ ] `@Column`
- [ ] `@Enumerated`
- [ ] Java time types
- [ ] `@Transient`

## Relationships
- [ ] `@OneToOne`
- [ ] `@OneToMany`
- [ ] `@ManyToOne`
- [ ] `@ManyToMany`
- [ ] Foreign keys
- [ ] Join columns
- [ ] Join tables
- [ ] Unidirectional relationships
- [ ] Bidirectional relationships
- [ ] Owning side
- [ ] `mappedBy`

## Relationship Design
- [ ] Cascade types
- [ ] `PERSIST`
- [ ] `MERGE`
- [ ] `REMOVE`
- [ ] `REFRESH`
- [ ] `DETACH`
- [ ] `ALL`
- [ ] Orphan removal
- [ ] Cascade vs orphan removal
- [ ] Bidirectional synchronization
- [ ] Common relationship mapping mistakes

### Best Books
**Primary:** Java Persistence with Spring Data and Hibernate  
**Deep Dive:** High-Performance Java Persistence  
**Reference:** Hibernate ORM documentation

---

# 4.3 Fetching & Performance

- [ ] LAZY fetching
- [ ] EAGER fetching
- [ ] Default fetch behavior
- [ ] Proxy concepts
- [ ] LazyInitializationException
- [ ] N+1 query problem
- [ ] Detecting N+1
- [ ] `JOIN FETCH`
- [ ] `@EntityGraph`
- [ ] Batch fetching
- [ ] Hibernate batch size
- [ ] Fetch joins and pagination limitations
- [ ] DTO projections for performance
- [ ] SQL logging
- [ ] Hibernate statistics
- [ ] Query count analysis

### Best Book
**High-Performance Java Persistence — Vlad Mihalcea**

This is the **most important deep-dive book in the entire Data Layer**.

---

# 4.4 Spring Data JPA

## Repository Architecture
- [ ] Repository abstraction
- [ ] `Repository`
- [ ] `CrudRepository`
- [ ] `PagingAndSortingRepository`
- [ ] `JpaRepository`
- [ ] Repository implementation generation
- [ ] Query method parsing

## Derived Queries
- [ ] Method-name parsing
- [ ] Equality
- [ ] Greater/less than
- [ ] Like
- [ ] In
- [ ] Between
- [ ] IsNull
- [ ] Ordering
- [ ] Nested property queries

## Custom Queries
- [ ] `@Query`
- [ ] JPQL
- [ ] Native SQL
- [ ] Named parameters
- [ ] Positional parameters
- [ ] Update/delete queries
- [ ] `@Modifying`

## Advanced Querying
- [ ] Specifications
- [ ] Criteria API
- [ ] Query by Example
- [ ] QueryDSL
- [ ] jOOQ
- [ ] Choosing between approaches

### Best Books / References
**Primary:** Java Persistence with Spring Data and Hibernate  
**Spring Data Reference:** Spring Data JPA documentation  
**Deep Dive:** High-Performance Java Persistence

---

# 4.5 Projections

- [ ] Interface-based projections
- [ ] DTO projections
- [ ] Constructor projections
- [ ] Dynamic projections
- [ ] Closed projections
- [ ] Open projections
- [ ] Projection performance
- [ ] Entity vs DTO query selection

### Best References
**Primary:** Spring Data JPA Reference Documentation  
**Deep Dive:** High-Performance Java Persistence

---

# 4.6 Pagination & Sorting

- [ ] `Pageable`
- [ ] `Page`
- [ ] `Slice`
- [ ] `Sort`
- [ ] Offset pagination
- [ ] Count query
- [ ] Page vs Slice
- [ ] Large dataset pagination
- [ ] Keyset/cursor pagination concepts
- [ ] Pagination with fetch joins

### Best References
**Primary:** Spring Data JPA documentation  
**Deep Dive:** High-Performance Java Persistence

---

# 4.7 Auditing

- [ ] JPA auditing
- [ ] `@CreatedDate`
- [ ] `@LastModifiedDate`
- [ ] `@CreatedBy`
- [ ] `@LastModifiedBy`
- [ ] Auditing entity listener
- [ ] `AuditorAware`
- [ ] Immutable audit fields

### Best References
**Primary:** Spring Data JPA documentation  
**Practical:** Java Persistence with Spring Data and Hibernate

---

# 4.8 Transaction Management

## Fundamentals
- [ ] What is a transaction?
- [ ] ACID
- [ ] Transaction boundaries
- [ ] Transaction manager
- [ ] Declarative transactions
- [ ] Programmatic transactions
- [ ] `@Transactional`
- [ ] `TransactionTemplate`

## Propagation
- [ ] `REQUIRED`
- [ ] `REQUIRES_NEW`
- [ ] `NESTED`
- [ ] `MANDATORY`
- [ ] `SUPPORTS`
- [ ] `NOT_SUPPORTED`
- [ ] `NEVER`

## Isolation
- [ ] `READ_UNCOMMITTED`
- [ ] `READ_COMMITTED`
- [ ] `REPEATABLE_READ`
- [ ] `SERIALIZABLE`
- [ ] Dirty reads
- [ ] Non-repeatable reads
- [ ] Phantom reads
- [ ] Database-specific behavior

## Rollback
- [ ] Default rollback rules
- [ ] Runtime exceptions
- [ ] Checked exceptions
- [ ] `rollbackFor`
- [ ] `noRollbackFor`
- [ ] Rollback behavior

## Transaction Internals
- [ ] Transaction interceptor
- [ ] Proxy-based transaction management
- [ ] Transaction synchronization
- [ ] Persistence context and transaction relationship
- [ ] Flush behavior
- [ ] Self-invocation problem
- [ ] Why `@Transactional` can silently fail
- [ ] Refactoring self-invocation

### Best Books
**Primary:** Spring in Action — transaction chapters  
**Deep Dive:** High-Performance Java Persistence  
**Reference:** Spring Framework Transaction Management documentation

---

# 4.9 Concurrency & Locking

## Optimistic Locking
- [ ] `@Version`
- [ ] Version column
- [ ] OptimisticLockException
- [ ] Retry strategies
- [ ] When optimistic locking is appropriate

## Pessimistic Locking
- [ ] Pessimistic read
- [ ] Pessimistic write
- [ ] Database row locks
- [ ] Lock timeout
- [ ] Deadlock considerations
- [ ] Optimistic vs pessimistic locking

### Best Book
**High-Performance Java Persistence — Vlad Mihalcea**

### Reference
- Spring Data JPA documentation
- Hibernate ORM documentation

---

# 4.10 Database Migrations

## Flyway
- [ ] Migration concept
- [ ] Versioned migrations
- [ ] Naming conventions
- [ ] Migration ordering
- [ ] Schema history
- [ ] Repeatable migrations

## Liquibase
- [ ] Changelog
- [ ] Changesets
- [ ] Rollback
- [ ] Database-independent migrations
- [ ] Flyway vs Liquibase

## Spring Integration
- [ ] Migration startup
- [ ] Migration configuration
- [ ] Production migration practices

### Best References
**Flyway:** Flyway documentation  
**Liquibase:** Liquibase documentation  
**Spring Integration:** Spring Boot database initialization documentation

---

# 4.11 Spring JDBC

- [ ] `JdbcTemplate`
- [ ] `NamedParameterJdbcTemplate`
- [ ] RowMapper
- [ ] ResultSetExtractor
- [ ] Prepared statements
- [ ] Batch operations
- [ ] Exception translation
- [ ] Transaction integration
- [ ] JDBC vs JPA
- [ ] When JDBC is preferable

### Best References
**Primary:** Spring Framework JDBC documentation  
**Practical:** Spring in Action  
**Database Performance:** High-Performance Java Persistence

---

# 4.12 QueryDSL & jOOQ

## QueryDSL
- [ ] QueryDSL fundamentals
- [ ] Type-safe query construction
- [ ] QueryDSL with Spring Data
- [ ] Query composition
- [ ] Dynamic queries

## jOOQ
- [ ] jOOQ fundamentals
- [ ] Generated schema classes
- [ ] SQL-first approach
- [ ] Type-safe SQL
- [ ] Query construction

## Comparison
- [ ] QueryDSL vs Specifications
- [ ] QueryDSL vs JPQL
- [ ] jOOQ vs JPA
- [ ] SQL-first vs ORM-first
- [ ] Choosing the right approach

### Best References
**QueryDSL:** QueryDSL reference/documentation  
**jOOQ:** The jOOQ User Manual  
**ORM comparison:** High-Performance Java Persistence

---

# 4.13 Connection Pooling — HikariCP

## Fundamentals
- [ ] Why connection pooling exists
- [ ] Connection lifecycle
- [ ] Pool initialization
- [ ] Connection acquisition
- [ ] Connection return

## Configuration
- [ ] `maximumPoolSize`
- [ ] `minimumIdle`
- [ ] `connectionTimeout`
- [ ] `idleTimeout`
- [ ] `maxLifetime`
- [ ] `leakDetectionThreshold`

## Production Behavior
- [ ] Pool sizing
- [ ] Connection starvation
- [ ] Connection leaks
- [ ] Database connection limits
- [ ] Pool vs database capacity
- [ ] Pool exhaustion diagnosis
- [ ] Slow connection acquisition

### Best References
**Primary:** HikariCP documentation  
**Deep Dive:** High-Performance Java Persistence  
**Spring Integration:** Spring Boot DataSource configuration documentation

---

# 4.14 NoSQL Integration

## MongoDB
- [ ] Spring Data MongoDB
- [ ] Documents
- [ ] Collections
- [ ] Mongo repositories
- [ ] MongoTemplate
- [ ] Querying
- [ ] Indexing concepts
- [ ] Mongo transactions concepts

### Best References
**Primary:** Spring Data MongoDB documentation  
**Database Reference:** MongoDB documentation

---

## Redis
- [ ] Spring Data Redis
- [ ] RedisTemplate
- [ ] Repository abstraction
- [ ] Lettuce
- [ ] Jedis
- [ ] Serialization
- [ ] TTL
- [ ] Redis data structures
- [ ] Connection management

### Best References
**Primary:** Spring Data Redis documentation  
**Client Reference:** Lettuce documentation / Jedis documentation  
**Database Reference:** Redis documentation

---

## Cassandra
- [ ] Spring Data Cassandra
- [ ] Partitioning concepts
- [ ] Query-driven data modeling
- [ ] Repository abstraction
- [ ] Cassandra-specific access patterns

### Best References
**Primary:** Spring Data Cassandra documentation  
**Database Reference:** Apache Cassandra documentation

---

## Elasticsearch
- [ ] Spring Data Elasticsearch
- [ ] Documents
- [ ] Indexes
- [ ] Repositories
- [ ] Search concepts
- [ ] Mapping
- [ ] Query concepts

### Best References
**Primary:** Spring Data Elasticsearch documentation  
**Database Reference:** Elasticsearch documentation

---

# Critical Spring Flows to Master

These should eventually be explainable **without notes**.

---

## Bean Creation Flow

```text
Bean Definition
      ↓
Instantiation
      ↓
Dependency Injection
      ↓
Aware Callbacks
      ↓
BeanPostProcessor
      ↓
@PostConstruct
      ↓
InitializingBean
      ↓
Custom init-method
      ↓
BeanPostProcessor
      ↓
Proxy / Ready Bean
```

---

## Spring Boot Startup Flow

```text
SpringApplication.run()
        ↓
Environment
        ↓
ApplicationContext
        ↓
Auto-Configuration
        ↓
Component Scanning
        ↓
Bean Definitions
        ↓
Bean Creation
        ↓
Embedded Server
        ↓
ApplicationStartedEvent
        ↓
Runners
        ↓
ApplicationReadyEvent
```

---

## Spring MVC Flow

```text
HTTP Request
      ↓
DispatcherServlet
      ↓
HandlerMapping
      ↓
HandlerAdapter
      ↓
Controller
      ↓
Service
      ↓
Repository
      ↓
Response
      ↓
HttpMessageConverter / ViewResolver
      ↓
HTTP Response
```

---

## Spring AOP Flow

```text
Client
  ↓
Proxy
  ↓
Advice
  ↓
Target Method
  ↓
Advice
  ↓
Response
```

---

## `@Transactional` Flow

```text
Caller
  ↓
Spring Proxy
  ↓
Transaction Interceptor
  ↓
Begin Transaction
  ↓
Target Method
  ↓
Commit / Rollback
```

---

## JPA Flow

```text
Repository
    ↓
EntityManager
    ↓
Persistence Context
    ↓
Hibernate
    ↓
JDBC
    ↓
Connection Pool
    ↓
Database
```

---

## HikariCP Flow

```text
Application
     ↓
DataSource
     ↓
HikariCP
     ↓
Connection Pool
     ↓
Database Connection
     ↓
Database
```

---

# Highest-Priority Deep-Mastery Areas

If time is limited, prioritize these.

## Tier 1 — Must Master

- [ ] IoC and DI
- [ ] Bean lifecycle
- [ ] BeanPostProcessor
- [ ] BeanFactoryPostProcessor
- [ ] `@Configuration` / `@Bean`
- [ ] `@ComponentScan`
- [ ] `@Primary` / `@Qualifier`
- [ ] Spring AOP
- [ ] JDK proxies vs CGLIB
- [ ] Self-invocation
- [ ] Spring Boot auto-configuration
- [ ] Configuration properties
- [ ] DispatcherServlet
- [ ] REST controllers
- [ ] Exception handling
- [ ] Validation
- [ ] JPA persistence context
- [ ] Entity lifecycle
- [ ] Relationships
- [ ] Lazy/Eager loading
- [ ] N+1 problem
- [ ] Spring Data JPA
- [ ] `@Transactional`
- [ ] Propagation
- [ ] Isolation
- [ ] Optimistic/pessimistic locking
- [ ] HikariCP

## Tier 2 — Strong Professional Knowledge

- [ ] SpEL
- [ ] Spring events
- [ ] Resource abstraction
- [ ] Actuator
- [ ] Micrometer
- [ ] Distributed tracing
- [ ] Jackson internals
- [ ] Filters vs interceptors
- [ ] CORS
- [ ] API versioning
- [ ] HATEOAS
- [ ] OpenAPI
- [ ] WebFlux
- [ ] Reactor
- [ ] Flyway/Liquibase
- [ ] Spring JDBC
- [ ] QueryDSL
- [ ] jOOQ
- [ ] MongoDB
- [ ] Redis
- [ ] Cassandra
- [ ] Elasticsearch

## Tier 3 — Advanced / Framework-Internals

- [ ] Custom BeanPostProcessor
- [ ] Custom BeanFactoryPostProcessor
- [ ] Custom scopes
- [ ] Custom conditions
- [ ] Auto-configuration source-code analysis
- [ ] Proxy internals
- [ ] AOP advisor chain
- [ ] Bean lifecycle debugging
- [ ] Spring Boot startup events
- [ ] AOT processing
- [ ] Native image constraints
- [ ] Reactor scheduling/backpressure
- [ ] Hibernate dirty checking
- [ ] Hibernate persistence-context internals
- [ ] Query execution analysis
- [ ] Connection-pool internals

---

# Recommended Book Collection

If you want a **compact but powerful library**, these are the most useful books to prioritize.

## 1. Spring Core

### `Spring Start Here — Laurentiu Spilca`
Best for:
- IoC
- DI
- Beans
- Configuration
- AOP fundamentals
- Spring fundamentals

**Use as your first Spring book.**

---

## 2. Spring Framework + Practical Development

### `Spring in Action — Craig Walls`
Best for:
- Spring Core
- Spring Boot
- MVC
- REST
- AOP
- Data access
- Transactions

**Use as the broad reference book alongside Spring Start Here.**

---

## 3. Spring Boot

### `Spring Boot Up & Running — Mark Heckler`
Best for:
- Boot architecture
- Auto-configuration
- Configuration
- Actuator
- Application startup
- Production-oriented Boot concepts

**Use as the primary Spring Boot book.**

---

## 4. JPA / Hibernate

### `Java Persistence with Spring Data and Hibernate — Catalin Tudose`
Best for:
- JPA
- Hibernate
- Spring Data
- Entity mapping
- Repositories
- Transactions
- Persistence context

---

## 5. Hibernate Performance

### `High-Performance Java Persistence — Vlad Mihalcea`
Best for:
- Hibernate internals
- SQL generated by Hibernate
- Fetching
- N+1
- Batching
- Transactions
- Locking
- Database performance
- Connection pooling

**This is the most important advanced Data Layer book.**

---

## 6. Reactive Spring

### `Reactive Spring — Josh Long`
Best for:
- WebFlux
- Reactor
- Reactive applications
- Reactive streams
- Spring reactive architecture

Supplement with the **Project Reactor Reference Guide**.

---

# Best Book by Module

| Module | Best Primary Book | Best Deep-Dive Reference |
|---|---|---|
| Module 1 — Spring Core | **Spring Start Here** | Spring Framework Reference |
| Module 2 — Spring Boot | **Spring Boot Up & Running** | Spring Boot Reference |
| Module 3 — MVC | **Spring in Action** | Spring Framework Web MVC Reference |
| Module 3 — WebFlux | **Reactive Spring** | Project Reactor Reference |
| Module 4 — JPA/Hibernate | **Java Persistence with Spring Data and Hibernate** | **High-Performance Java Persistence** |
| Module 4 — Transactions | **Spring in Action** | **High-Performance Java Persistence** |
| Module 4 — Spring Data JPA | **Java Persistence with Spring Data and Hibernate** | Spring Data JPA Reference |
| Module 4 — JDBC | **Spring in Action** | Spring JDBC Reference |
| Module 4 — NoSQL | Spring Data documentation | Native database documentation |

---

# Recommended Reading Order

Don't read all books sequentially from cover to cover.

Use this order:

```text
Spring Start Here
       ↓
Spring in Action
       ↓
Spring Boot Up & Running
       ↓
Spring MVC + REST
       ↓
Java Persistence with Spring Data and Hibernate
       ↓
High-Performance Java Persistence
       ↓
Reactive Spring
       ↓
Spring Framework / Boot Reference Documentation
       ↓
Source-code study
```

---

# Final Mastery Standard

Do **not** mark a Spring topic complete merely because you know the annotation.

For example, don't mark `@Transactional` complete because you know:

```java
@Transactional
public void save() {
}
```

Mark it complete only when you can explain:

```text
@Transactional
     ↓
Spring proxy
     ↓
Transaction interceptor
     ↓
Transaction manager
     ↓
Connection / EntityManager
     ↓
Transaction begins
     ↓
Target method executes
     ↓
Commit or rollback
```

And you can explain:

- [ ] Why self-invocation breaks it
- [ ] Why checked exceptions may not roll back by default
- [ ] How propagation works
- [ ] How isolation works
- [ ] How persistence context interacts with it
- [ ] What happens when multiple transactional methods call each other
- [ ] How to diagnose transaction problems in production

Apply the same standard to **IoC, AOP, Boot auto-configuration, MVC, WebFlux, JPA, Hibernate, Spring Data and HikariCP**.

The target is:

> **Understand → Implement → Inspect Internals → Debug → Optimize → Explain → Use in Production**
