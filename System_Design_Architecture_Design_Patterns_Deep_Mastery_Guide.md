# System Design, Architecture & Design Patterns — Comprehensive Mastery Guide

> Scope: only the **10 modules provided** have been refined. No new major modules are added.
>
> The refinement adds finer-grained concepts, internals, trade-offs, failure modes, production considerations, interview practice criteria, and recommended books/references for each module.

---

# How to Use This Guide

For every topic, aim to master:

1. **Concept** — What is it?
2. **Internals** — How does it work?
3. **Implementation** — Can you build it?
4. **Trade-offs** — When should/shouldn't you use it?
5. **Scale** — What changes at 10x/100x traffic?
6. **Failure modes** — What happens when components fail?
7. **Consistency** — What guarantees are required?
8. **Performance** — What determines latency and throughput?
9. **Operations** — How do you observe and troubleshoot it?
10. **Communication** — Can you clearly explain and draw the architecture?

---

# Module 1 — Core System Design & Scalability Principles

## 1.1 Scalability Fundamentals

- [ ] Scalability vs performance
- [ ] Vertical scaling
- [ ] Horizontal scaling
- [ ] Scale-up vs scale-out
- [ ] Stateless services
- [ ] Stateful services
- [ ] Session externalization
- [ ] Load distribution
- [ ] Capacity planning
- [ ] Bottleneck identification
- [ ] Resource saturation
- [ ] Throughput vs latency
- [ ] Average latency vs percentile latency
- [ ] p50
- [ ] p95
- [ ] p99
- [ ] p99.9

### Mastery

- [ ] Estimate requests per second
- [ ] Estimate storage growth
- [ ] Estimate network bandwidth
- [ ] Estimate memory requirements
- [ ] Estimate database throughput
- [ ] Identify the first bottleneck
- [ ] Explain what changes when traffic increases 10x

### Best Books

**Primary:** *Designing Data-Intensive Applications* — Martin Kleppmann

**System Design:** *System Design Interview — An Insider's Guide* — Alex Xu

---

# 1.2 Stateless Service Design

- [ ] Stateless request processing
- [ ] Externalized session state
- [ ] Redis-backed sessions
- [ ] Database-backed sessions
- [ ] JWT-based stateless authentication concepts
- [ ] Idempotent APIs
- [ ] Horizontal scaling
- [ ] Request affinity
- [ ] Sticky sessions
- [ ] Why sticky sessions reduce scalability
- [ ] When stateful services are unavoidable

---

# 1.3 High Availability

## Active-Active

- [ ] Multiple active instances
- [ ] Traffic distribution
- [ ] Multi-AZ deployment
- [ ] Multi-region concepts
- [ ] Data consistency implications
- [ ] Failure detection
- [ ] Failover

## Active-Passive

- [ ] Primary instance
- [ ] Standby instance
- [ ] Health checks
- [ ] Failover
- [ ] Promotion
- [ ] Recovery time
- [ ] Data synchronization

## Availability

- [ ] Availability percentage
- [ ] 99%
- [ ] 99.9%
- [ ] 99.99%
- [ ] 99.999%
- [ ] Downtime budgets
- [ ] Availability vs cost

### Mastery

- [ ] Design for single-node failure
- [ ] Design for AZ failure
- [ ] Understand regional failure implications
- [ ] Identify remaining SPOFs

### Best Reference

*Site Reliability Engineering* — Google

---

# 1.4 Disaster Recovery

- [ ] Disaster recovery vs high availability
- [ ] RTO
- [ ] RPO
- [ ] Backup
- [ ] Restore
- [ ] Replication
- [ ] Cross-region replication
- [ ] Failover
- [ ] Failback
- [ ] Disaster recovery testing
- [ ] Backup verification
- [ ] Cold standby
- [ ] Warm standby
- [ ] Hot standby
- [ ] Active-active disaster recovery

### Mastery

Be able to design:

```text
Primary Region
      ↓
Replication
      ↓
Secondary Region
      ↓
Failover
      ↓
Traffic Redirection
```

### Best Books

**Primary:** *Site Reliability Engineering*

**Architecture:** *Designing Data-Intensive Applications*

---

# 1.5 Single Points of Failure

- [ ] Identify SPOFs
- [ ] Single database
- [ ] Single load balancer
- [ ] Single availability zone
- [ ] Single message broker
- [ ] Single cache
- [ ] Single DNS dependency
- [ ] Single authentication provider
- [ ] Single-region dependency
- [ ] Eliminate SPOF
- [ ] Replication
- [ ] Redundancy
- [ ] Failover

### Mastery

For every architecture ask:

> "If this component dies right now, does the system continue operating?"

---

# 1.6 CAP Theorem

- [ ] Consistency
- [ ] Availability
- [ ] Partition tolerance
- [ ] Network partitions
- [ ] CP systems
- [ ] AP systems
- [ ] CAP misconceptions
- [ ] Practical database examples
- [ ] CAP vs normal-operation trade-offs

### Best Book

**Designing Data-Intensive Applications**

---

# 1.7 BASE vs ACID

## BASE

- [ ] Basically Available
- [ ] Soft state
- [ ] Eventual consistency
- [ ] Availability-oriented systems
- [ ] Distributed data

## ACID

- [ ] Atomicity
- [ ] Consistency
- [ ] Isolation
- [ ] Durability
- [ ] Transaction boundaries

## Trade-offs

- [ ] Strong vs eventual consistency
- [ ] Latency
- [ ] Availability
- [ ] Complexity
- [ ] Business requirements

---

# Module 2 — Load Balancing, Hashing & Rate Limiting

# 2.1 Layer 4 Load Balancing

- [ ] TCP load balancing
- [ ] UDP load balancing
- [ ] Connection-based routing
- [ ] Source IP
- [ ] Destination IP
- [ ] Source/destination port
- [ ] NAT concepts
- [ ] TLS passthrough concepts
- [ ] Performance characteristics

---

# 2.2 Layer 7 Load Balancing

- [ ] HTTP-aware routing
- [ ] Host-based routing
- [ ] Path-based routing
- [ ] Header-based routing
- [ ] Cookie-based routing
- [ ] TLS termination
- [ ] HTTP/2 considerations
- [ ] HTTP/3 considerations
- [ ] Content-aware routing
- [ ] Authentication integration

## L4 vs L7

| Aspect | L4 | L7 |
|---|---|---|
| Layer | Transport | Application |
| Protocol awareness | TCP/UDP | HTTP/etc. |
| Routing | Connection/network information | Request information |
| Overhead | Lower | Higher |
| Routing flexibility | Lower | Higher |

### Best Book

*Designing Data-Intensive Applications*

---

# 2.3 Load-Balancing Algorithms

- [ ] Round robin
- [ ] Weighted round robin
- [ ] Least connections
- [ ] Weighted least connections
- [ ] IP hash
- [ ] Consistent hashing
- [ ] Random
- [ ] Latency-aware routing
- [ ] Health-aware routing
- [ ] Connection draining
- [ ] Slow start
- [ ] Session affinity

### Trade-offs

- [ ] Even distribution
- [ ] Uneven request cost
- [ ] Stateful sessions
- [ ] Node capacity differences
- [ ] Failure handling

---

# 2.4 Global Server Load Balancing

- [ ] DNS-based routing
- [ ] Geo-routing
- [ ] Latency-based routing
- [ ] Health checks
- [ ] Failover
- [ ] Anycast concepts
- [ ] Multi-region routing
- [ ] DNS TTL implications
- [ ] DNS caching during failover

---

# 2.5 Consistent Hashing

- [ ] Hash ring
- [ ] Hash function
- [ ] Nodes on ring
- [ ] Key placement
- [ ] Node addition
- [ ] Node removal
- [ ] Minimal remapping
- [ ] Virtual nodes
- [ ] Load distribution
- [ ] Hot keys
- [ ] Replication on a hash ring

### Mastery

Be able to implement:

```text
Hash(key)
   ↓
Locate position on ring
   ↓
Move clockwise
   ↓
Select node
```

### Best Books

**Primary:** *Designing Data-Intensive Applications*

**Distributed Systems:** *System Design Interview — Alex Xu*

---

# 2.6 Sharding

## Hash-Based

- [ ] Hash key
- [ ] Even distribution
- [ ] Hot-key issues
- [ ] Resharding

## Range-Based

- [ ] Key ranges
- [ ] Range scans
- [ ] Hot ranges
- [ ] Splitting ranges

## Directory-Based

- [ ] Lookup service
- [ ] Shard mapping
- [ ] Metadata management
- [ ] Failure of routing metadata

## Advanced

- [ ] Shard key selection
- [ ] Cross-shard queries
- [ ] Cross-shard transactions
- [ ] Rebalancing
- [ ] Hot shards
- [ ] Global IDs

### Best Book

*Designing Data-Intensive Applications*

---

# 2.7 Token Bucket

- [ ] Token generation
- [ ] Bucket capacity
- [ ] Refill rate
- [ ] Request cost
- [ ] Burst handling
- [ ] Sustained rate
- [ ] Distributed implementation
- [ ] Redis implementation
- [ ] Clock considerations

### Example

```text
Tokens
  ↓
Request arrives
  ↓
Token available?
  ├── Yes → Consume token → Allow
  └── No  → Reject / delay
```

---

# 2.8 Leaky Bucket

- [ ] Fixed processing rate
- [ ] Queue
- [ ] Burst smoothing
- [ ] Queue overflow
- [ ] Latency trade-off
- [ ] Token bucket vs leaky bucket

---

# 2.9 Window-Based Rate Limiting

- [ ] Fixed window
- [ ] Sliding window log
- [ ] Sliding window counter
- [ ] Boundary burst problem
- [ ] Memory requirements
- [ ] Accuracy
- [ ] Distributed rate limiting
- [ ] Per-user limits
- [ ] Per-IP limits
- [ ] Per-API-key limits
- [ ] Global limits

### Best Reference

*System Design Interview — Alex Xu*

---

# Module 3 — Caching, CDNs & Content Delivery

# 3.1 Why Caching Exists

- [ ] Reduce latency
- [ ] Reduce database load
- [ ] Improve throughput
- [ ] Reduce network cost
- [ ] Hot data
- [ ] Cache hit ratio
- [ ] Cache miss
- [ ] Cache stampede
- [ ] Cache penetration
- [ ] Cache avalanche

---

# 3.2 Cache-Aside

```text
Application
    ↓
Check Cache
    ↓
Hit ─────────→ Return
    ↓ Miss
Database
    ↓
Populate Cache
    ↓
Return
```

- [ ] Read path
- [ ] Write path
- [ ] TTL
- [ ] Invalidation
- [ ] Race conditions
- [ ] Cache stampede

---

# 3.3 Read-Through

- [ ] Cache owns data loading
- [ ] Cache miss handling
- [ ] Application simplification
- [ ] Infrastructure complexity

---

# 3.4 Write-Through

- [ ] Write cache
- [ ] Synchronous database write
- [ ] Consistency
- [ ] Write latency

---

# 3.5 Write-Behind / Write-Back

- [ ] Cache accepts write
- [ ] Asynchronous persistence
- [ ] Higher write throughput
- [ ] Data-loss risk
- [ ] Ordering
- [ ] Failure recovery

---

# 3.6 Refresh-Ahead

- [ ] Predict hot keys
- [ ] Refresh before expiry
- [ ] Reduce cache misses
- [ ] Background refresh
- [ ] Refresh cost

---

# 3.7 Cache Invalidation

- [ ] TTL
- [ ] Explicit invalidation
- [ ] Event-based invalidation
- [ ] Versioned keys
- [ ] Active expiration
- [ ] Passive/lazy expiration
- [ ] LRU
- [ ] LFU
- [ ] FIFO concepts
- [ ] Cache consistency
- [ ] Stale data

### Critical Principle

> Cache invalidation is a data-consistency problem, not merely a performance feature.

### Best Books

**Primary:** *Designing Data-Intensive Applications*

**Practical:** *Redis in Action*

---

# 3.8 Cache Stampede & Avalanche

- [ ] Many keys expire simultaneously
- [ ] Thundering herd
- [ ] Request coalescing
- [ ] Locking
- [ ] Randomized TTL
- [ ] Background refresh
- [ ] Graceful degradation

---

# 3.9 Redis Patterns

## Session Store

- [ ] Session key design
- [ ] TTL
- [ ] Session expiration
- [ ] High availability
- [ ] Serialization
- [ ] Session invalidation

## Distributed Locks

- [ ] Lock acquisition
- [ ] Lock expiration
- [ ] Unique lock token
- [ ] Ownership
- [ ] Safe release
- [ ] Failure scenarios
- [ ] Redlock algorithm
- [ ] Criticism and trade-offs
- [ ] When a database/consensus system is preferable

## Pub/Sub

- [ ] Publisher
- [ ] Subscriber
- [ ] Channels
- [ ] Fanout
- [ ] Delivery semantics
- [ ] Message loss on disconnected subscribers
- [ ] Pub/Sub vs Redis Streams

## Rate Limiting

- [ ] Counters
- [ ] TTL
- [ ] Lua/scripted atomic operations
- [ ] Distributed limits
- [ ] Hot keys

### Best Book

*Redis in Action — Josiah L. Carlson*

---

# 3.10 CDN

- [ ] Origin server
- [ ] Edge location
- [ ] Cache key
- [ ] Cache hit
- [ ] Cache miss
- [ ] TTL
- [ ] Cache-Control
- [ ] ETag
- [ ] Conditional requests
- [ ] Push vs pull concepts
- [ ] Purging
- [ ] Cache invalidation
- [ ] Origin shielding
- [ ] Geographic distribution

---

# 3.11 Edge Computing

- [ ] Edge execution
- [ ] Request interception
- [ ] Edge caching
- [ ] Geographic routing
- [ ] Edge functions
- [ ] Cloudflare Workers concepts
- [ ] Latency reduction
- [ ] Cold-start considerations
- [ ] State limitations

### Best References

Cloudflare documentation + CDN provider documentation

---

# Module 4 — Database Scaling & Distributed Data

# 4.1 Read Replicas

- [ ] Primary
- [ ] Replica
- [ ] Replication
- [ ] Replication lag
- [ ] Read-after-write consistency
- [ ] Sticky reads
- [ ] Routing reads to primary
- [ ] Replica health
- [ ] Replica failure
- [ ] Scaling read traffic

---

# 4.2 Database Federation

- [ ] Functional partitioning
- [ ] Splitting databases by business capability
- [ ] Independent scaling
- [ ] Failure isolation
- [ ] Cross-database queries
- [ ] Cross-database transactions
- [ ] Data ownership

---

# 4.3 Materialized Views

- [ ] Precomputed results
- [ ] Refresh
- [ ] Full refresh
- [ ] Incremental refresh concepts
- [ ] Staleness
- [ ] Aggregation workloads
- [ ] Reporting
- [ ] Read performance vs freshness

---

# 4.4 Search Architecture

- [ ] Database search vs dedicated search
- [ ] Inverted index
- [ ] Terms
- [ ] Postings lists
- [ ] Tokenization
- [ ] Normalization
- [ ] Stemming
- [ ] Stop words
- [ ] Analyzers
- [ ] Index mappings
- [ ] Search relevance
- [ ] Scoring
- [ ] Fuzzy matching
- [ ] Prefix search
- [ ] Phrase search
- [ ] Filtering
- [ ] Aggregations
- [ ] Shards
- [ ] Replicas
- [ ] Refresh
- [ ] Segment concepts

### Elasticsearch Architecture

- [ ] Cluster
- [ ] Node
- [ ] Index
- [ ] Shard
- [ ] Replica
- [ ] Primary shard
- [ ] Inverted index
- [ ] Lucene concepts
- [ ] Segment
- [ ] Merge

### Best Books

**Primary:** *Elasticsearch: The Definitive Guide* — Clinton Gormley & Zachary Tong

**Distributed Search:** *Designing Data-Intensive Applications*

---

# Module 5 — Asynchronous Processing & Messaging

# 5.1 Message Queues

- [ ] Producer
- [ ] Consumer
- [ ] Queue
- [ ] Message
- [ ] Acknowledgment
- [ ] Visibility timeout
- [ ] Retry
- [ ] Dead-letter queue
- [ ] Ordering
- [ ] Backpressure
- [ ] Consumer scaling

---

# 5.2 Event Streams

- [ ] Event
- [ ] Topic
- [ ] Partition
- [ ] Offset
- [ ] Consumer group
- [ ] Replay
- [ ] Retention
- [ ] Ordering per partition
- [ ] Stream processing

---

# 5.3 RabbitMQ vs Kafka

| Area | RabbitMQ | Kafka |
|---|---|---|
| Core model | Message broker | Distributed event log |
| Consumption | Queue/ack based | Offset based |
| Replay | Limited/architecture dependent | Core capability |
| Ordering | Queue-level concepts | Partition-level |
| Typical use | Task/message routing | Event streaming |
| Routing | Exchanges/bindings | Topics/partitions |

### Mastery

Do not memorize "Kafka is faster."

Instead understand:

- [ ] Workload shape
- [ ] Ordering requirements
- [ ] Replay requirements
- [ ] Consumer model
- [ ] Retention
- [ ] Throughput
- [ ] Routing requirements
- [ ] Operational complexity

### Best Book

**Designing Event-Driven Systems — Ben Stopford**

### Architecture

*Designing Data-Intensive Applications*

---

# 5.4 Pub/Sub vs Point-to-Point

## Point-to-Point

```text
Producer → Queue → Consumer
```

- [ ] One consumer processes a message
- [ ] Work distribution
- [ ] Task processing

## Pub/Sub

```text
             → Consumer A
Publisher → Topic → Consumer B
             → Consumer C
```

- [ ] Fanout
- [ ] Independent consumers
- [ ] Event distribution

---

# 5.5 Idempotency

- [ ] Idempotent operation
- [ ] Idempotency key
- [ ] Request deduplication
- [ ] Consumer deduplication
- [ ] Unique constraints
- [ ] Idempotency table
- [ ] Redis-based deduplication
- [ ] Message IDs
- [ ] Exactly-once business effect

### Example

```text
Request
  ↓
Idempotency Key
  ↓
Already processed?
 ├── Yes → Return previous result
 └── No  → Process + persist key
```

---

# 5.6 Exactly-Once Semantics

- [ ] At-most-once
- [ ] At-least-once
- [ ] Exactly-once processing
- [ ] Exactly-once delivery vs exactly-once effect
- [ ] Retries
- [ ] Duplicate messages
- [ ] Deduplication
- [ ] Transactional processing
- [ ] Kafka EOS concepts
- [ ] Database + message broker consistency

### Critical Principle

> In distributed systems, "exactly once" must always be examined at the level of the actual business effect.

### Best Books

**Primary:** *Designing Data-Intensive Applications*

**Event-driven:** *Designing Event-Driven Systems*

---

# Module 6 — API Design & Communication Protocols

# 6.1 REST Fundamentals

- [ ] Resources
- [ ] URIs
- [ ] HTTP methods
- [ ] GET
- [ ] POST
- [ ] PUT
- [ ] PATCH
- [ ] DELETE
- [ ] HTTP status codes
- [ ] Idempotency
- [ ] Safe methods
- [ ] Statelessness
- [ ] Content negotiation

---

# 6.2 Richardson Maturity Model

- [ ] Level 0 — HTTP as transport
- [ ] Level 1 — Resources
- [ ] Level 2 — HTTP verbs/status codes
- [ ] Level 3 — Hypermedia

### Mastery

Understand that the maturity model is descriptive, not a mandatory checklist for every production API.

---

# 6.3 Pagination

## Offset-Based

```text
?page=10&size=50
```

- [ ] Simple implementation
- [ ] Random page access
- [ ] Large-offset performance
- [ ] Data shifting problem

## Cursor-Based

```text
?cursor=eyJpZCI6...
```

- [ ] Stable ordering
- [ ] Cursor encoding
- [ ] Next/previous cursor
- [ ] Efficient large datasets
- [ ] Mutation consistency

## Keyset Pagination

- [ ] Seek method
- [ ] Indexed ordering
- [ ] Stable cursor
- [ ] Performance benefits

### Best Reference

*API Design Patterns — JJ Geewax*

---

# 6.4 Filtering, Sorting & Searching

- [ ] Query parameters
- [ ] Field filtering
- [ ] Multi-field filtering
- [ ] Sorting
- [ ] Search
- [ ] Range filters
- [ ] Date filters
- [ ] Validation
- [ ] Maximum page size
- [ ] Query complexity limits
- [ ] Stable ordering

---

# 6.5 API Versioning

- [ ] URI versioning
- [ ] Query parameter versioning
- [ ] Header versioning
- [ ] Media-type versioning
- [ ] Backward compatibility
- [ ] Deprecation
- [ ] Sunset
- [ ] Consumer migration
- [ ] Database compatibility

### Best Book

*API Design Patterns — JJ Geewax*

---

# 6.6 Standardized Errors

- [ ] HTTP status
- [ ] Error type
- [ ] Error title
- [ ] Error detail
- [ ] Instance
- [ ] Validation errors
- [ ] Correlation ID
- [ ] RFC 7807 Problem Details
- [ ] Stable error contracts

---

# 6.7 HATEOAS

- [ ] Hypermedia
- [ ] Links
- [ ] Actions
- [ ] State transitions
- [ ] Discoverability
- [ ] When HATEOAS is useful
- [ ] When simpler APIs are preferable

---

# 6.8 TCP vs UDP

## TCP
- [ ] Connection-oriented
- [ ] Reliability
- [ ] Ordering
- [ ] Flow control
- [ ] Congestion control
- [ ] Retransmission
- [ ] Three-way handshake

## UDP
- [ ] Connectionless
- [ ] No built-in delivery guarantee
- [ ] Lower protocol overhead
- [ ] Application-managed reliability
- [ ] Real-time workloads

### Best Book

*Computer Networking: A Top-Down Approach* — Kurose & Ross

---

# 6.9 Long Polling

- [ ] Request held open
- [ ] Server response when data arrives
- [ ] Client reconnect
- [ ] Connection limits
- [ ] Latency
- [ ] Scalability

---

# 6.10 WebSockets

- [ ] HTTP upgrade
- [ ] Persistent connection
- [ ] Bidirectional communication
- [ ] Connection lifecycle
- [ ] Heartbeats
- [ ] Reconnection
- [ ] Connection scaling
- [ ] Load balancing
- [ ] Sticky routing considerations
- [ ] Horizontal scaling with pub/sub

---

# 6.11 Server-Sent Events

- [ ] HTTP-based
- [ ] Server → client
- [ ] Persistent connection
- [ ] Event stream
- [ ] Reconnection
- [ ] Last-event ID
- [ ] Browser support
- [ ] SSE vs WebSocket

---

# 6.12 WebRTC

- [ ] Peer-to-peer model
- [ ] Signaling
- [ ] SDP
- [ ] ICE
- [ ] STUN
- [ ] TURN
- [ ] NAT traversal
- [ ] Media/data channels
- [ ] WebRTC vs WebSockets

### Best Reference

*WebRTC for the Curious* — WebRTC community/open reference

---

# Module 7 — Real-World System Design Interviews

# 7.1 Universal Interview Framework

For every design question:

```text
1. Clarify requirements
        ↓
2. Functional requirements
        ↓
3. Non-functional requirements
        ↓
4. Capacity estimation
        ↓
5. API design
        ↓
6. Data model
        ↓
7. High-level architecture
        ↓
8. Deep dive into bottlenecks
        ↓
9. Failure handling
        ↓
10. Scaling
        ↓
11. Consistency
        ↓
12. Observability
        ↓
13. Security considerations
        ↓
14. Trade-offs
```

---

# 7.2 URL Shortener

- [ ] Requirements
- [ ] Short-code generation
- [ ] Base62
- [ ] Collision handling
- [ ] Read-heavy workload
- [ ] Cache
- [ ] Database
- [ ] Analytics
- [ ] Expiration
- [ ] Custom aliases
- [ ] Hot URLs
- [ ] Horizontal scaling

### Deep Dive
- [ ] ID generation
- [ ] Cache strategy
- [ ] Database partitioning
- [ ] Redirect latency

### Best Book

*System Design Interview — Alex Xu*

---

# 7.3 Distributed Rate Limiter

- [ ] Requirements
- [ ] Per-user limits
- [ ] Per-IP limits
- [ ] Global limits
- [ ] Token bucket
- [ ] Redis
- [ ] Atomic operations
- [ ] Lua/script concepts
- [ ] Distributed consistency
- [ ] Failure behavior
- [ ] Clock considerations

---

# 7.4 Notification System

- [ ] Push notifications
- [ ] Email
- [ ] SMS
- [ ] User preferences
- [ ] Priority
- [ ] Scheduling
- [ ] Retry
- [ ] DLQ
- [ ] Provider failover
- [ ] Idempotency
- [ ] Rate limits
- [ ] Fanout
- [ ] Delivery status

---

# 7.5 Chat System

- [ ] WebSockets
- [ ] Connection management
- [ ] Presence
- [ ] Message ordering
- [ ] Message persistence
- [ ] Delivery acknowledgments
- [ ] Read receipts
- [ ] Offline messages
- [ ] Fanout
- [ ] Multi-device synchronization
- [ ] Media storage
- [ ] Group chat
- [ ] Horizontal scaling

### Deep Dive
- [ ] Connection routing
- [ ] Pub/sub
- [ ] Message persistence
- [ ] Ordering guarantees

---

# 7.6 News Feed

- [ ] Fanout-on-write
- [ ] Fanout-on-read
- [ ] Hybrid fanout
- [ ] Celebrity users
- [ ] Ranking
- [ ] Timeline cache
- [ ] Pagination
- [ ] Event processing
- [ ] Consistency
- [ ] Feed freshness

---

# 7.7 Payment Gateway

- [ ] Idempotency
- [ ] Payment states
- [ ] Authorization
- [ ] Capture
- [ ] Refund
- [ ] Retry
- [ ] Duplicate request prevention
- [ ] Transaction consistency
- [ ] Audit trail
- [ ] Reconciliation
- [ ] Provider failure
- [ ] Webhooks
- [ ] Exactly-once business effect

### Critical

Be able to design around:

```text
Payment Request
      ↓
Idempotency Key
      ↓
Payment State Machine
      ↓
Provider
      ↓
Webhook / Result
      ↓
Reconciliation
```

---

# 7.8 Ride-Sharing System

- [ ] Driver location updates
- [ ] Geospatial indexing
- [ ] Driver matching
- [ ] Real-time location
- [ ] Trip state
- [ ] Pricing
- [ ] Surge pricing
- [ ] ETA
- [ ] Maps
- [ ] Notifications
- [ ] Payment
- [ ] Event processing
- [ ] Regional partitioning

---

# 7.9 Distributed Web Crawler

- [ ] URL frontier
- [ ] URL deduplication
- [ ] Robots.txt
- [ ] Politeness
- [ ] Crawl scheduling
- [ ] DNS
- [ ] Fetch workers
- [ ] Content storage
- [ ] Metadata
- [ ] Retry
- [ ] Dead URLs
- [ ] Priority queue
- [ ] Distributed coordination
- [ ] Partitioning
- [ ] Duplicate content detection

---

# 7.10 Ticket Booking System

- [ ] Inventory
- [ ] Seat availability
- [ ] Temporary reservation
- [ ] Reservation timeout
- [ ] Payment
- [ ] Concurrency
- [ ] Optimistic locking
- [ ] Pessimistic locking
- [ ] Idempotency
- [ ] Overselling prevention
- [ ] Queueing
- [ ] Hot inventory
- [ ] Eventual consistency considerations

### Deep Dive

Understand:

```text
Seat Available
      ↓
Temporary Hold
      ↓
Payment
      ↓
Confirm
      ↓
Release on Failure/Timeout
```

---

# Module 8 — Design Patterns (GoF Masterclass)

# 8.1 Creational Patterns

## Singleton

- [ ] Eager initialization
- [ ] Lazy initialization
- [ ] Thread safety
- [ ] Double-checked locking
- [ ] `volatile`
- [ ] Bill Pugh holder
- [ ] Enum singleton
- [ ] Serialization concerns
- [ ] Reflection concerns
- [ ] Testing drawbacks
- [ ] Dependency Injection alternative

### Best Reference
*Head First Design Patterns*

---

## Factory Method

- [ ] Creator
- [ ] Product
- [ ] Concrete product
- [ ] Encapsulation of object creation
- [ ] Open/closed principle

---

## Abstract Factory

- [ ] Product families
- [ ] Factory interface
- [ ] Concrete factories
- [ ] Product compatibility
- [ ] Factory Method relationship

---

## Builder

- [ ] Step-by-step construction
- [ ] Immutable objects
- [ ] Optional parameters
- [ ] Fluent API
- [ ] Validation
- [ ] Lombok `@Builder`
- [ ] Generated builder structure
- [ ] Builder vs telescoping constructors

---

## Prototype

- [ ] Cloning
- [ ] Shallow copy
- [ ] Deep copy
- [ ] Prototype registry
- [ ] Mutable nested state
- [ ] Java cloning pitfalls

---

# 8.2 Structural Patterns

## Adapter

- [ ] Interface adaptation
- [ ] Legacy integration
- [ ] Object adapter
- [ ] Class adapter concepts

## Decorator

- [ ] Dynamic behavior
- [ ] Composition
- [ ] Layering
- [ ] Decorator vs inheritance

## Proxy

- [ ] Static proxy
- [ ] Dynamic proxy
- [ ] JDK Proxy
- [ ] CGLIB concepts
- [ ] Lazy loading
- [ ] Security
- [ ] Remote proxy
- [ ] Caching proxy

## Facade

- [ ] Simplified interface
- [ ] Subsystem hiding
- [ ] Service facade

## Composite

- [ ] Tree structures
- [ ] Leaf
- [ ] Composite
- [ ] Uniform treatment

## Bridge

- [ ] Abstraction
- [ ] Implementation
- [ ] Independent variation

## Flyweight

- [ ] Shared intrinsic state
- [ ] Extrinsic state
- [ ] Memory optimization
- [ ] Object pooling distinction
- [ ] JVM wrapper caches

### Important

Understand that JVM caches such as wrapper caches are implementation/library behaviors and should not be treated as a generic recommendation to implement Flyweight everywhere.

### Best Book

**Head First Design Patterns — Eric Freeman & Elisabeth Robson**

---

# 8.3 Behavioral Patterns

## Strategy

- [ ] Encapsulated algorithms
- [ ] Runtime selection
- [ ] Composition
- [ ] Strategy vs if/else

## Observer

- [ ] Publisher
- [ ] Subscriber
- [ ] Event notification
- [ ] Synchronous observer
- [ ] Asynchronous eventing
- [ ] Memory-leak considerations

## Command

- [ ] Encapsulate request
- [ ] Queueing
- [ ] Undo
- [ ] Logging

## Template Method

- [ ] Algorithm skeleton
- [ ] Hooks
- [ ] Inheritance
- [ ] Template vs Strategy

## Chain of Responsibility

- [ ] Handler chain
- [ ] Request propagation
- [ ] Filter chains
- [ ] Spring Security relationship

## State

- [ ] State object
- [ ] State transitions
- [ ] State machine
- [ ] Avoiding conditional explosion

## Iterator

- [ ] Traversal abstraction
- [ ] Iterator contract
- [ ] Java Collections relationship

## Mediator

- [ ] Central coordination
- [ ] Decoupling
- [ ] Communication hub
- [ ] Mediator becoming God object

## Memento

- [ ] Snapshot
- [ ] State restoration
- [ ] Encapsulation

## Visitor

- [ ] Double dispatch
- [ ] Object structure
- [ ] Operation separation
- [ ] Visitor trade-offs

### Best Book

**Head First Design Patterns**

### Deeper Reference

**Design Patterns: Elements of Reusable Object-Oriented Software — Gamma, Helm, Johnson & Vlissides**

---

# Module 9 — Enterprise, Architectural & Cloud Patterns

# 9.1 Hexagonal Architecture

- [ ] Domain core
- [ ] Ports
- [ ] Adapters
- [ ] Primary/driving adapters
- [ ] Secondary/driven adapters
- [ ] Dependency direction
- [ ] Domain isolation
- [ ] Testing benefits
- [ ] Infrastructure independence

### Typical Structure

```text
          REST Adapter
               ↓
        ┌──────────────┐
        │ Application  │
        │    Core      │
        └──────────────┘
          ↑          ↓
       DB Adapter   Messaging Adapter
```

---

# 9.2 Clean Architecture

- [ ] Entities
- [ ] Use cases
- [ ] Interface adapters
- [ ] Frameworks/drivers
- [ ] Dependency Rule
- [ ] Dependency inversion
- [ ] Domain independence
- [ ] Testing

---

# 9.3 Onion Architecture

- [ ] Domain core
- [ ] Application services
- [ ] Infrastructure
- [ ] Dependency direction
- [ ] Relationship with Hexagonal Architecture
- [ ] Relationship with Clean Architecture

### Best Books

**Primary:** *Clean Architecture — Robert C. Martin*

**Architecture:** *Architecture Patterns with Python* concepts are useful even outside Python.

**Enterprise:** *Patterns of Enterprise Application Architecture — Martin Fowler*

---

# 9.4 MVC vs MVP vs MVVM

## MVC

- [ ] Model
- [ ] View
- [ ] Controller
- [ ] Request handling
- [ ] Spring MVC relationship

## MVP

- [ ] Presenter
- [ ] View abstraction
- [ ] Testability

## MVVM

- [ ] ViewModel
- [ ] Binding
- [ ] UI state

### Mastery

Understand these as architectural interaction patterns rather than blindly applying them to every application.

---

# 9.5 Repository Pattern vs DAO

## Repository

- [ ] Domain-oriented abstraction
- [ ] Aggregate-oriented access
- [ ] Collection-like interface
- [ ] Domain isolation

## DAO

- [ ] Persistence-oriented abstraction
- [ ] CRUD operations
- [ ] Database-specific concerns
- [ ] Lower-level abstraction

## Comparison

- [ ] Domain model relationship
- [ ] Spring Data repository
- [ ] Transaction boundary
- [ ] Testing

### Best Book

*Patterns of Enterprise Application Architecture — Martin Fowler*

---

# 9.6 Service Layer

- [ ] Application service
- [ ] Use-case orchestration
- [ ] Transaction boundary
- [ ] Domain service distinction
- [ ] Avoiding business logic dumping ground
- [ ] Thin vs thick service layer

---

# 9.7 DTO & Mapper Pattern

- [ ] DTO purpose
- [ ] API boundary
- [ ] Entity exposure risks
- [ ] Request DTO
- [ ] Response DTO
- [ ] Mapping
- [ ] MapStruct
- [ ] Manual mapping
- [ ] Generated mapping
- [ ] Nested mappings
- [ ] Partial updates

---

# 9.8 Specification Pattern

- [ ] Encapsulated business predicates
- [ ] Composable specifications
- [ ] Dynamic queries
- [ ] AND
- [ ] OR
- [ ] NOT
- [ ] Spring Data JPA Specifications
- [ ] Criteria API relationship
- [ ] Avoiding overly complex specifications

---

# 9.9 Cloud & Microservice Patterns

## Circuit Breaker

- [ ] Closed
- [ ] Open
- [ ] Half-open
- [ ] Failure threshold
- [ ] Recovery
- [ ] Fallback
- [ ] Timeout relationship

## Retry

- [ ] Transient failure
- [ ] Exponential backoff
- [ ] Jitter
- [ ] Retry budget
- [ ] Retry storms
- [ ] Idempotency

## Bulkhead

- [ ] Resource isolation
- [ ] Thread pool isolation
- [ ] Semaphore isolation
- [ ] Failure containment

---

# 9.10 CQRS

- [ ] Command model
- [ ] Query model
- [ ] Separate read/write models
- [ ] Event-driven projections
- [ ] Eventual consistency
- [ ] Scaling reads independently
- [ ] Complexity trade-off
- [ ] When CQRS is unnecessary

---

# 9.11 Event Sourcing

- [ ] Event store
- [ ] Events as source of truth
- [ ] State reconstruction
- [ ] Snapshots
- [ ] Event versioning
- [ ] Event schema evolution
- [ ] Replay
- [ ] Projections
- [ ] Temporal history
- [ ] Operational complexity

---

# 9.12 Saga

## Choreography

- [ ] Event-driven coordination
- [ ] Local transactions
- [ ] Compensating actions
- [ ] Coupling through events
- [ ] Debugging complexity

## Orchestration

- [ ] Central coordinator
- [ ] Commands
- [ ] Responses
- [ ] Compensation
- [ ] Visibility
- [ ] Orchestrator failure considerations

### Best Book

**Microservices Patterns — Chris Richardson**

---

# 9.13 Transactional Outbox

- [ ] Business transaction
- [ ] Outbox table
- [ ] Atomic database commit
- [ ] Message publisher
- [ ] Polling publisher
- [ ] CDC
- [ ] Debezium
- [ ] Duplicate publication
- [ ] Idempotent consumers
- [ ] Ordering
- [ ] Cleanup

### Best Reference

*Microservices Patterns — Chris Richardson*

---

# Module 10 — Anti-Patterns & Pitfalls to Avoid

# 10.1 God Class

- [ ] Too many responsibilities
- [ ] Too many dependencies
- [ ] Difficult testing
- [ ] High coupling
- [ ] Low cohesion
- [ ] Refactoring by responsibility
- [ ] Extract services/classes
- [ ] Dependency inversion

---

# 10.2 Anemic Domain Model

- [ ] Data-only domain objects
- [ ] Business logic in services
- [ ] Lost domain behavior
- [ ] Transaction scripts
- [ ] When anemic models are acceptable
- [ ] Rich domain model trade-offs

### Important

An anemic model is not automatically wrong. It can be appropriate for simple CRUD-oriented systems.

---

# 10.3 Spaghetti Code

- [ ] Unstructured dependencies
- [ ] Hidden coupling
- [ ] Difficult control flow
- [ ] Global state
- [ ] Refactoring strategies

---

# 10.4 Lasagna Code

- [ ] Excessive layers
- [ ] Pass-through services
- [ ] Pass-through repositories
- [ ] DTO explosion
- [ ] Abstraction without value
- [ ] Excessive indirection

### Principle

> Add a layer when it provides meaningful isolation, policy, or responsibility—not simply because "architecture requires layers."

---

# 10.5 Distributed Monolith

- [ ] Services tightly coupled
- [ ] Synchronous call chains
- [ ] Shared deployment lifecycle
- [ ] Shared database
- [ ] Shared domain model
- [ ] Coordinated releases
- [ ] Cascading failures
- [ ] Network latency
- [ ] Operational complexity without autonomy

---

# 10.6 Chatty Microservices

- [ ] Excessive synchronous calls
- [ ] N+1 network calls
- [ ] High latency
- [ ] Cascading failures
- [ ] Increased observability complexity
- [ ] API aggregation
- [ ] Batch APIs
- [ ] Event-driven alternatives
- [ ] BFF/API composition

---

# 10.7 Shared Database Across Microservices

- [ ] Shared schema
- [ ] Shared tables
- [ ] Cross-service coupling
- [ ] Schema-change coordination
- [ ] Hidden ownership
- [ ] Transaction coupling
- [ ] Independent deployment problems

### Important Nuance

A shared database is not automatically a technical failure. The key question is whether services have **independent data ownership and deployment autonomy**. In many systems, a modular monolith with one database is preferable to prematurely adopting microservices.

---

# System Design Mastery Checklist

Before finalizing any architecture, ask:

## Requirements

- [ ] What must the system do?
- [ ] What is explicitly out of scope?
- [ ] Read-heavy or write-heavy?
- [ ] Real-time or batch?
- [ ] Strong or eventual consistency?
- [ ] Availability target?
- [ ] Latency target?

## Scale

- [ ] Requests per second?
- [ ] Peak traffic?
- [ ] Data size?
- [ ] Data growth?
- [ ] Concurrent users?
- [ ] Network bandwidth?

## Architecture

- [ ] Load balancer
- [ ] API gateway
- [ ] Services
- [ ] Cache
- [ ] Database
- [ ] Queue/stream
- [ ] Search
- [ ] Object storage
- [ ] CDN

## Data

- [ ] Schema
- [ ] Partition key
- [ ] Indexes
- [ ] Replication
- [ ] Consistency
- [ ] Transactions
- [ ] Retention

## Reliability

- [ ] Timeouts
- [ ] Retries
- [ ] Circuit breakers
- [ ] Bulkheads
- [ ] Idempotency
- [ ] Backpressure
- [ ] Rate limiting
- [ ] Failover
- [ ] Disaster recovery

## Observability

- [ ] Metrics
- [ ] Logs
- [ ] Traces
- [ ] Alerts
- [ ] Dashboards
- [ ] Correlation IDs
- [ ] SLOs

## Trade-offs

- [ ] Why this database?
- [ ] Why this communication protocol?
- [ ] Why synchronous/asynchronous?
- [ ] Why cache?
- [ ] Why partition?
- [ ] Why this consistency model?
- [ ] Why microservices instead of a modular monolith?

---

# Recommended Book by Module

| Module | Best Primary Book | Strong Supplement |
|---|---|---|
| 1. Core System Design & Scalability | **Designing Data-Intensive Applications — Martin Kleppmann** | **System Design Interview — Alex Xu** |
| 2. Load Balancing, Hashing & Rate Limiting | **Designing Data-Intensive Applications** | **System Design Interview — Alex Xu** |
| 3. Caching, Redis & CDN | **Designing Data-Intensive Applications** | **Redis in Action** |
| 4. Database Scaling & Search | **Designing Data-Intensive Applications** | **Elasticsearch: The Definitive Guide** |
| 5. Messaging & Async Processing | **Designing Event-Driven Systems — Ben Stopford** | **Designing Data-Intensive Applications** |
| 6. API Design & Protocols | **API Design Patterns — JJ Geewax** | **Computer Networking: A Top-Down Approach** |
| 7. System Design Interviews | **System Design Interview — Alex Xu** | **System Design Interview Vol. 2 — Alex Xu** |
| 8. GoF Design Patterns | **Head First Design Patterns** | **Design Patterns — GoF** |
| 9. Enterprise & Architectural Patterns | **Patterns of Enterprise Application Architecture — Martin Fowler** | **Clean Architecture — Robert C. Martin** |
| 10. Anti-Patterns | **Refactoring — Martin Fowler** | **Microservices Patterns — Chris Richardson** |

---

# Core Book Collection — Avoid Over-Reading

If you want a compact library for this entire syllabus, prioritize:

## 1. Designing Data-Intensive Applications
**Martin Kleppmann**

Most important book for:

- Distributed systems
- Replication
- Partitioning
- Consistency
- Messaging
- Databases
- Streams
- Distributed-system trade-offs

---

## 2. System Design Interview
**Alex Xu**

Best for:

- Interview-oriented architecture
- Capacity estimation
- High-level design
- Common system-design problems
- Architecture communication

Use it for **practice**, not as your only source of distributed-systems theory.

---

## 3. API Design Patterns
**JJ Geewax**

Best for:

- API resource modeling
- Pagination
- Filtering
- Errors
- Versioning
- API consistency

---

## 4. Computer Networking: A Top-Down Approach
**James Kurose & Keith Ross**

Best for:

- TCP
- UDP
- HTTP
- DNS
- Network architecture
- Transport-layer behavior

---

## 5. Head First Design Patterns
**Eric Freeman & Elisabeth Robson**

Best for learning GoF patterns intuitively and recognizing where they actually apply.

---

## 6. Design Patterns — GoF

Use as the deeper reference after learning the patterns from *Head First Design Patterns*.

---

## 7. Patterns of Enterprise Application Architecture
**Martin Fowler**

Best for:

- Repository
- DAO
- Service Layer
- Unit of Work
- DTO
- Enterprise application architecture

---

## 8. Clean Architecture
**Robert C. Martin**

Best for:

- Dependency direction
- Architecture boundaries
- Use cases
- Domain isolation
- Clean/hexagonal-style thinking

---

## 9. Microservices Patterns
**Chris Richardson**

Best for:

- Saga
- CQRS
- Event Sourcing
- Transactional Outbox
- Microservice architecture patterns

---

## 10. Designing Event-Driven Systems
**Ben Stopford**

Best for:

- Kafka-oriented architecture
- Event-driven systems
- Stream processing
- Event-based architecture

---

# Recommended Study Order

```text
1. Core System Design
        ↓
2. Scalability + Availability
        ↓
3. Load Balancing
        ↓
4. Consistent Hashing + Sharding
        ↓
5. Caching + Redis
        ↓
6. Database Scaling
        ↓
7. Search
        ↓
8. Messaging + Async Processing
        ↓
9. API Design
        ↓
10. Networking Protocols
        ↓
11. GoF Design Patterns
        ↓
12. Enterprise Architecture
        ↓
13. Microservice Patterns
        ↓
14. Anti-Patterns
        ↓
15. Real-World System Design Problems
```

---

# Final Mastery Standard

Do not consider a system-design topic complete merely because you can draw boxes.

For every architecture, you should be able to answer:

### Scale
- [ ] How many users?
- [ ] How many requests/sec?
- [ ] What is the peak?
- [ ] How much data is generated?
- [ ] What grows fastest?

### Data
- [ ] Which database?
- [ ] Why?
- [ ] What is the partition key?
- [ ] What is the consistency model?
- [ ] What happens during replication lag?

### Performance
- [ ] What is the critical path?
- [ ] What is the p99 latency?
- [ ] Where is the bottleneck?
- [ ] Can caching help?
- [ ] Can asynchronous processing help?

### Reliability
- [ ] What happens if the database fails?
- [ ] What happens if a service fails?
- [ ] What happens if a region fails?
- [ ] Can requests be retried safely?
- [ ] Is the operation idempotent?

### Distributed Systems
- [ ] Where can messages be duplicated?
- [ ] Where can messages be lost?
- [ ] Where can ordering break?
- [ ] Where can consistency become eventual?
- [ ] Where can a network partition occur?

### Operations
- [ ] What metrics show failure?
- [ ] What logs are required?
- [ ] How do traces identify the bottleneck?
- [ ] What alert fires?
- [ ] How is the system recovered?

### Architecture

The strongest design is **not the one containing the most technologies**.

It is the one where every component has a clear reason to exist, every trade-off is understood, and the architecture can evolve as requirements and scale change.

> **Requirements → Estimates → Architecture → Data → Communication → Scaling → Reliability → Observability → Trade-offs → Failure Analysis**
