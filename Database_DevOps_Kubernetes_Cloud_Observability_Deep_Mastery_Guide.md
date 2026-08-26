# Database, DevOps, Kubernetes, Cloud & Observability — Comprehensive Mastery Guide

> Scope is intentionally limited to the **6 modules provided**. No new major modules are added.
>
> The syllabus is refined into a senior/production-level guide by making the existing subjects more granular, adding internals, trade-offs, failure modes, troubleshooting areas, and practical mastery checkpoints.
>
> Each module also includes recommended books and authoritative references.

---

# How to Use This Guide

For every topic, aim to master:

1. **Concept** — What is it?
2. **Internals** — How does it work?
3. **Implementation** — Can you use it?
4. **Trade-offs** — When should/shouldn't you use it?
5. **Performance** — What affects latency, throughput and resource usage?
6. **Failure modes** — What happens when something breaks?
7. **Operations** — How do you monitor and troubleshoot it?
8. **Security** — What are the important security implications?
9. **Production** — Can you design it for a real workload?
10. **Hands-on** — Can you reproduce the important behavior locally?

---

# Module 1 — Relational Databases & SQL Deep Dive

# 1.1 Relational Database Fundamentals

- [ ] Relational model
- [ ] Tables, rows and columns
- [ ] Primary keys
- [ ] Candidate keys
- [ ] Alternate keys
- [ ] Foreign keys
- [ ] Composite keys
- [ ] Unique constraints
- [ ] NOT NULL constraints
- [ ] CHECK constraints
- [ ] Referential integrity
- [ ] Entity relationships
- [ ] One-to-one
- [ ] One-to-many
- [ ] Many-to-many
- [ ] Surrogate vs natural keys
- [ ] NULL semantics
- [ ] Three-valued SQL logic
- [ ] Constraints vs application validation

### Mastery
- [ ] Design a normalized schema from requirements
- [ ] Explain why every constraint exists
- [ ] Identify where database constraints are preferable to application-only validation

### Best Books
**Primary:** *Database System Concepts* — Abraham Silberschatz, Henry Korth & S. Sudarshan

**Practical SQL:** *SQL Cookbook* — Anthony Molinaro & Robert de Graaf

---

# 1.2 Advanced SQL Querying

## Joins

- [ ] INNER JOIN
- [ ] LEFT JOIN
- [ ] RIGHT JOIN
- [ ] FULL OUTER JOIN
- [ ] CROSS JOIN
- [ ] SELF JOIN
- [ ] Join predicates
- [ ] Multiple joins
- [ ] Join ordering
- [ ] Join cardinality
- [ ] Duplicate rows caused by joins
- [ ] NULL behavior in outer joins
- [ ] Anti-joins
- [ ] Semi-joins
- [ ] `EXISTS`
- [ ] `NOT EXISTS`
- [ ] JOIN vs EXISTS
- [ ] Detecting accidental Cartesian products

## Subqueries

- [ ] Scalar subqueries
- [ ] Single-row subqueries
- [ ] Multi-row subqueries
- [ ] Correlated subqueries
- [ ] Uncorrelated subqueries
- [ ] `IN`
- [ ] `EXISTS`
- [ ] `NOT EXISTS`
- [ ] Subquery performance
- [ ] Rewriting subqueries as joins
- [ ] Rewriting subqueries as CTEs

## Common Table Expressions

- [ ] CTE syntax
- [ ] Multiple CTEs
- [ ] CTE readability
- [ ] CTE materialization concepts
- [ ] Recursive CTE
- [ ] Anchor query
- [ ] Recursive query
- [ ] Termination condition
- [ ] Hierarchical data
- [ ] Graph traversal
- [ ] Recursive query performance

## Window Functions

- [ ] `ROW_NUMBER`
- [ ] `RANK`
- [ ] `DENSE_RANK`
- [ ] `LEAD`
- [ ] `LAG`
- [ ] `NTILE`
- [ ] `FIRST_VALUE`
- [ ] `LAST_VALUE`
- [ ] `NTH_VALUE`
- [ ] `SUM() OVER`
- [ ] `AVG() OVER`
- [ ] Window partitioning
- [ ] Window ordering
- [ ] Window frames
- [ ] `ROWS`
- [ ] `RANGE`
- [ ] Running totals
- [ ] Moving averages
- [ ] Top-N per group
- [ ] Gaps and islands
- [ ] Comparing current vs previous row

## Advanced Grouping

- [ ] `GROUP BY`
- [ ] `HAVING`
- [ ] `GROUPING SETS`
- [ ] `ROLLUP`
- [ ] `CUBE`
- [ ] `GROUPING`
- [ ] Multi-level aggregation
- [ ] Reporting queries

### Best Books
**Primary:** *SQL Cookbook* — Anthony Molinaro & Robert de Graaf

**Deep Dive:** *SQL Performance Explained* — Markus Winand

**Database Theory:** *Database System Concepts* — Silberschatz, Korth & Sudarshan

---

# 1.3 SQL Execution Order

- [ ] `FROM`
- [ ] `JOIN`
- [ ] `WHERE`
- [ ] `GROUP BY`
- [ ] `HAVING`
- [ ] Window functions
- [ ] `SELECT`
- [ ] `DISTINCT`
- [ ] `ORDER BY`
- [ ] `LIMIT/OFFSET`

Understand the difference between:

```text
Logical SQL processing order
vs.
Physical database execution plan
```

### Mastery
- [ ] Explain why aliases may not be usable in `WHERE`
- [ ] Explain why window functions cannot normally be used directly in `WHERE`
- [ ] Rewrite queries using CTEs when necessary

### Best Reference
**SQL Performance Explained — Markus Winand**

---

# 1.4 Stored Procedures & Functions

- [ ] Stored procedures
- [ ] Stored functions
- [ ] Parameters
- [ ] Return values
- [ ] Local variables
- [ ] Conditional logic
- [ ] Loops
- [ ] Exception handling
- [ ] Transaction behavior
- [ ] Security considerations
- [ ] Application logic vs database logic
- [ ] Performance considerations
- [ ] Versioning database code

### Best References
- PostgreSQL documentation
- MySQL documentation
- *Database System Concepts*

---

# 1.5 Triggers & Event Scheduling

## Triggers

- [ ] BEFORE triggers
- [ ] AFTER triggers
- [ ] Row-level triggers
- [ ] Statement-level triggers
- [ ] Trigger ordering
- [ ] Audit triggers
- [ ] Validation triggers
- [ ] Cascading trigger effects
- [ ] Hidden side effects
- [ ] Trigger performance
- [ ] When triggers are appropriate
- [ ] When triggers become dangerous

## Event Schedulers

- [ ] Scheduled database jobs
- [ ] MySQL Event Scheduler
- [ ] PostgreSQL scheduling approaches
- [ ] Job ownership
- [ ] Failure handling
- [ ] Duplicate execution
- [ ] Operational visibility

### Best References
Database vendor documentation

---

# 1.6 Views & Materialized Views

## Views

- [ ] Logical views
- [ ] View abstraction
- [ ] Updatable views
- [ ] Security through views
- [ ] Nested views
- [ ] View performance considerations

## Materialized Views

- [ ] Materialized view
- [ ] Physical result storage
- [ ] Refresh
- [ ] Full refresh
- [ ] Concurrent/incremental refresh concepts
- [ ] Refresh scheduling
- [ ] Staleness
- [ ] Read performance
- [ ] Storage cost
- [ ] Materialized view vs caching

### Best References
**Primary:** PostgreSQL documentation

**Performance:** *SQL Performance Explained*

---

# 1.7 Indexing

## Index Structures

- [ ] B-tree
- [ ] Hash indexes
- [ ] Bitmap indexes
- [ ] GiST
- [ ] GIN
- [ ] BRIN concepts
- [ ] Clustered vs non-clustered index concepts
- [ ] Index selectivity
- [ ] Cardinality

## Index Design

- [ ] Single-column indexes
- [ ] Composite indexes
- [ ] Column ordering
- [ ] Covering indexes
- [ ] Included columns
- [ ] Partial indexes
- [ ] Expression/function indexes
- [ ] Unique indexes
- [ ] Foreign-key indexing
- [ ] Index-only scans

## Index Trade-offs

- [ ] Read performance
- [ ] Write penalty
- [ ] Insert overhead
- [ ] Update overhead
- [ ] Delete overhead
- [ ] Storage consumption
- [ ] Index bloat
- [ ] Fragmentation
- [ ] Over-indexing
- [ ] Unused indexes

### Critical Rule

An index is useful only when the database's optimizer can use it efficiently for the workload. More indexes do **not** automatically mean better performance.

### Best Books
**Primary:** *SQL Performance Explained — Markus Winand*

**Deep Dive:** *High Performance MySQL* — Silvia Botros & Jeremy Tinley

**PostgreSQL:** *The Art of PostgreSQL* — Dimitri Fontaine

---

# 1.8 Query Optimization & Execution Plans

- [ ] Query optimizer
- [ ] Cost-based optimization
- [ ] Statistics
- [ ] Cardinality estimation
- [ ] Sequential scan
- [ ] Index scan
- [ ] Index-only scan
- [ ] Bitmap scan
- [ ] Nested-loop join
- [ ] Hash join
- [ ] Merge join
- [ ] Sort operations
- [ ] Hash aggregation
- [ ] Temporary tables
- [ ] Memory spills
- [ ] Parallel query execution

## PostgreSQL

- [ ] `EXPLAIN`
- [ ] `EXPLAIN ANALYZE`
- [ ] `BUFFERS`
- [ ] `ANALYZE`
- [ ] Actual vs estimated rows
- [ ] Execution time
- [ ] Planning time
- [ ] I/O analysis
- [ ] Sequential scans
- [ ] Join strategy

## Query Refactoring

- [ ] Remove unnecessary columns
- [ ] Avoid `SELECT *`
- [ ] Reduce result sets
- [ ] Rewrite correlated subqueries
- [ ] Avoid functions preventing index usage
- [ ] Improve predicates
- [ ] Correct composite-index order
- [ ] Reduce unnecessary sorting
- [ ] Reduce unnecessary joins
- [ ] Pagination optimization

### Best Books
**Primary:** *SQL Performance Explained — Markus Winand*

**PostgreSQL:** *The Art of PostgreSQL*

**MySQL:** *High Performance MySQL*

---

# 1.9 Normalization & Denormalization

- [ ] Functional dependencies
- [ ] 1NF
- [ ] 2NF
- [ ] 3NF
- [ ] BCNF
- [ ] Update anomalies
- [ ] Insert anomalies
- [ ] Delete anomalies
- [ ] Normalization trade-offs
- [ ] Denormalization
- [ ] Read optimization
- [ ] Data duplication
- [ ] Consistency cost
- [ ] Reporting schemas
- [ ] OLTP vs analytical design

### Best Book
**Database System Concepts — Silberschatz, Korth & Sudarshan**

---

# 1.10 Partitioning & Sharding

## Partitioning

- [ ] Horizontal partitioning
- [ ] Range partitioning
- [ ] List partitioning
- [ ] Hash partitioning
- [ ] Partition pruning
- [ ] Partition indexes
- [ ] Partition maintenance
- [ ] Partitioning large tables

## Vertical Partitioning

- [ ] Splitting columns
- [ ] Hot vs cold data
- [ ] Wide-table optimization
- [ ] Access-pattern-driven design

## Sharding

- [ ] Horizontal sharding
- [ ] Shard key
- [ ] Hash-based sharding
- [ ] Range-based sharding
- [ ] Consistent hashing concepts
- [ ] Hot shards
- [ ] Resharding
- [ ] Cross-shard queries
- [ ] Cross-shard transactions
- [ ] Global identifiers
- [ ] Application-level routing

### Best Books
**Primary:** *Designing Data-Intensive Applications — Martin Kleppmann*

**Supplement:** *Database Internals — Alex Petrov*

---

# 1.11 Replication

- [ ] Primary/replica architecture
- [ ] Read replicas
- [ ] Synchronous replication
- [ ] Asynchronous replication
- [ ] Replication lag
- [ ] Failover
- [ ] Promotion
- [ ] Read-after-write consistency
- [ ] Multi-primary / multi-master
- [ ] Conflict resolution
- [ ] Split-brain concepts
- [ ] Disaster recovery

> Terminology such as "master/slave" is commonly replaced with **primary/replica** or **leader/follower**.

### Best Book
**Designing Data-Intensive Applications — Martin Kleppmann**

---

# 1.12 Connection Pooling

- [ ] Why pooling exists
- [ ] Connection acquisition
- [ ] Connection release
- [ ] Pool size
- [ ] Connection timeout
- [ ] Idle timeout
- [ ] Max lifetime
- [ ] Connection leaks
- [ ] Pool exhaustion
- [ ] Database connection limits
- [ ] PgBouncer
- [ ] Session pooling
- [ ] Transaction pooling
- [ ] Statement pooling
- [ ] Application pool vs database pool

### Best References
**Primary:** PgBouncer documentation

**Deep Dive:** *High Performance MySQL* / PostgreSQL documentation

---

# 1.13 Transactions & ACID

- [ ] Atomicity
- [ ] Consistency
- [ ] Isolation
- [ ] Durability
- [ ] Commit
- [ ] Rollback
- [ ] Savepoints
- [ ] Autocommit
- [ ] Transaction boundaries
- [ ] Long-running transactions

### Best Book
**Database System Concepts**

---

# 1.14 Isolation Levels & Concurrency

## Isolation Levels

- [ ] Read Uncommitted
- [ ] Read Committed
- [ ] Repeatable Read
- [ ] Serializable
- [ ] Database-specific implementation differences

## Anomalies

- [ ] Dirty read
- [ ] Non-repeatable read
- [ ] Phantom read
- [ ] Lost update
- [ ] Write skew
- [ ] Serialization anomalies

## Locking

- [ ] Shared locks
- [ ] Exclusive locks
- [ ] Row locks
- [ ] Table locks
- [ ] Predicate/range locking concepts
- [ ] Lock escalation concepts
- [ ] Lock wait

### Best Books
**Primary:** *Designing Data-Intensive Applications*

**Deep Dive:** *Database System Concepts*

---

# 1.15 Deadlocks

- [ ] What is a deadlock?
- [ ] Circular wait
- [ ] Lock ordering
- [ ] Deadlock detection
- [ ] Deadlock timeout
- [ ] Victim transaction
- [ ] Retry strategy
- [ ] Preventing deadlocks
- [ ] Keeping transactions short
- [ ] Consistent resource ordering
- [ ] Diagnosing deadlocks

### Best References
Database-specific transaction/locking documentation

---

# 1.16 PostgreSQL Internals

## MVCC

- [ ] MVCC
- [ ] Tuple versions
- [ ] Transaction IDs
- [ ] Visibility
- [ ] Snapshot
- [ ] Dead tuples
- [ ] Transaction isolation

## VACUUM

- [ ] VACUUM
- [ ] Autovacuum
- [ ] VACUUM FULL
- [ ] Dead tuple cleanup
- [ ] Table/index bloat
- [ ] Analyze/statistics
- [ ] Autovacuum tuning
- [ ] Long-running transaction impact

## JSONB

- [ ] JSON vs JSONB
- [ ] JSONB storage
- [ ] JSON operators
- [ ] JSON path
- [ ] GIN indexing
- [ ] Expression indexes
- [ ] Relational vs JSONB modeling

## Extensions

- [ ] Extension architecture
- [ ] PostGIS
- [ ] UUID-related extensions
- [ ] Full-text search concepts
- [ ] Extension lifecycle

### Best Books
**Primary:** *PostgreSQL: Up and Running* — Regina Obe & Leo Hsu

**Deep Dive:** *The Art of PostgreSQL* — Dimitri Fontaine

**Internals:** PostgreSQL official documentation

---

# 1.17 MySQL / InnoDB Internals

- [ ] InnoDB architecture
- [ ] Clustered primary key
- [ ] Secondary indexes
- [ ] Buffer pool
- [ ] Redo log
- [ ] Undo log
- [ ] MVCC
- [ ] Transactions
- [ ] Isolation
- [ ] Locking
- [ ] Gap locks
- [ ] Next-key locks
- [ ] Change buffering concepts
- [ ] Doublewrite concepts
- [ ] Replication concepts

### Best Book
**High Performance MySQL — Silvia Botros & Jeremy Tinley**

---

# Module 2 — NoSQL & Distributed Data

# 2.1 NoSQL Fundamentals

- [ ] Why NoSQL exists
- [ ] Relational vs NoSQL
- [ ] Access-pattern-driven modeling
- [ ] Horizontal scaling
- [ ] Denormalization
- [ ] Eventual consistency
- [ ] Availability
- [ ] Partition tolerance
- [ ] Operational trade-offs
- [ ] Choosing NoSQL vs relational database

### Best Book
**Designing Data-Intensive Applications — Martin Kleppmann**

---

# 2.2 Document Databases — MongoDB

- [ ] Documents
- [ ] Collections
- [ ] BSON
- [ ] Embedding
- [ ] References
- [ ] Document modeling
- [ ] Aggregation pipeline
- [ ] `$match`
- [ ] `$group`
- [ ] `$project`
- [ ] `$lookup`
- [ ] `$unwind`
- [ ] `$sort`
- [ ] `$facet`
- [ ] Indexing
- [ ] Compound indexes
- [ ] Multikey indexes
- [ ] Partial indexes
- [ ] TTL indexes
- [ ] Transactions
- [ ] Replica sets
- [ ] Sharding
- [ ] Read/write concerns

### Best References
**Primary:** MongoDB official documentation

**Architecture:** *Designing Data-Intensive Applications*

---

# 2.3 Key-Value Stores — Redis

- [ ] Key-value model
- [ ] Strings
- [ ] Lists
- [ ] Sets
- [ ] Sorted sets
- [ ] Hashes
- [ ] Streams
- [ ] Pub/Sub concepts
- [ ] TTL
- [ ] Expiration
- [ ] Persistence
- [ ] RDB
- [ ] AOF
- [ ] Eviction policies
- [ ] Memory management
- [ ] Transactions
- [ ] Lua/scripts concepts
- [ ] Redis replication
- [ ] Redis Sentinel
- [ ] Redis Cluster
- [ ] Distributed locking considerations

### Best Book
**Redis in Action — Josiah L. Carlson**

### Reference
Redis official documentation

---

# 2.4 DynamoDB

- [ ] Partition key
- [ ] Sort key
- [ ] Composite primary key
- [ ] Access-pattern design
- [ ] Query vs Scan
- [ ] Global secondary index
- [ ] Local secondary index
- [ ] Provisioned capacity
- [ ] On-demand capacity
- [ ] Hot partitions
- [ ] Conditional writes
- [ ] Optimistic concurrency
- [ ] Streams
- [ ] TTL
- [ ] Transactions
- [ ] Consistency options

### Best Reference
AWS DynamoDB Developer Guide

### Architecture Book
*Designing Data-Intensive Applications*

---

# 2.5 Column-Family Databases — Cassandra

- [ ] Cassandra architecture
- [ ] Keyspace
- [ ] Partition key
- [ ] Clustering columns
- [ ] Wide rows
- [ ] Data modeling from queries
- [ ] Replication factor
- [ ] Consistency levels
- [ ] Quorum
- [ ] Tunable consistency
- [ ] Compaction
- [ ] SSTables
- [ ] Memtables
- [ ] Commit log
- [ ] Tombstones
- [ ] Tombstone accumulation
- [ ] Read repair concepts
- [ ] Hinted handoff concepts
- [ ] Repair
- [ ] Lightweight transactions
- [ ] Hot partitions

### Best Book
**Cassandra: The Definitive Guide** — Jeff Carpenter & Eben Hewitt

### Architecture
*Designing Data-Intensive Applications*

---

# 2.6 Graph Databases — Neo4j

- [ ] Nodes
- [ ] Relationships
- [ ] Properties
- [ ] Property graphs
- [ ] Graph modeling
- [ ] Cypher
- [ ] MATCH
- [ ] CREATE
- [ ] MERGE
- [ ] WHERE
- [ ] RETURN
- [ ] Variable-length paths
- [ ] Aggregation
- [ ] Indexing
- [ ] Graph traversal
- [ ] When graph databases outperform relational joins

### Best Reference
Neo4j official documentation

---

# 2.7 Time-Series Databases

## InfluxDB
- [ ] Measurements
- [ ] Tags
- [ ] Fields
- [ ] Timestamps
- [ ] Retention policies
- [ ] Downsampling
- [ ] Time-series queries

## TimescaleDB
- [ ] Hypertables
- [ ] Time partitioning
- [ ] Compression
- [ ] Continuous aggregates
- [ ] PostgreSQL integration
- [ ] SQL-based time-series analysis

## Design
- [ ] High-ingestion workloads
- [ ] Time-window queries
- [ ] Retention
- [ ] Aggregation
- [ ] Cardinality

### Best References
InfluxDB and TimescaleDB official documentation

---

# 2.8 CAP Theorem

- [ ] Consistency
- [ ] Availability
- [ ] Partition tolerance
- [ ] Network partition
- [ ] Why partition tolerance matters in distributed systems
- [ ] CAP misconceptions
- [ ] CP systems
- [ ] AP systems
- [ ] Practical database examples

### Best Book
**Designing Data-Intensive Applications**

---

# 2.9 PACELC

- [ ] Partition scenario
- [ ] Availability vs consistency
- [ ] Normal operation
- [ ] Latency vs consistency
- [ ] PACELC trade-offs
- [ ] Database examples

### Best Reference
**Designing Data-Intensive Applications**

---

# 2.10 Consistency Models

- [ ] Strong consistency
- [ ] Eventual consistency
- [ ] Causal consistency
- [ ] Read-your-writes consistency
- [ ] Monotonic reads
- [ ] Tunable consistency
- [ ] Consistency vs latency
- [ ] Consistency vs availability

### Best Book
**Designing Data-Intensive Applications**

---

# 2.11 Quorum Reads & Writes

- [ ] N replicas
- [ ] W writes
- [ ] R reads
- [ ] `W + R > N`
- [ ] Overlapping replica sets
- [ ] Stronger read guarantees
- [ ] Quorum failure
- [ ] Read repair concepts
- [ ] Sloppy quorum concepts
- [ ] Hinted handoff concepts

### Best Reference
**Designing Data-Intensive Applications**

---

# 2.12 Leader Election

- [ ] Leader/follower architecture
- [ ] Leader election
- [ ] Failure detection
- [ ] Terms/epochs
- [ ] Split-brain
- [ ] Quorum
- [ ] Raft concepts
- [ ] Leader election
- [ ] Log replication
- [ ] Commit index
- [ ] Paxos conceptual model
- [ ] Raft vs Paxos at a conceptual level

### Best Book
**Designing Data-Intensive Applications**

### Deep Dive
**Understanding Distributed Systems — Roberto Vitillo**

---

# Module 3 — DevOps, CI/CD & Build Tools

# 3.1 Maven

- [ ] Maven project structure
- [ ] `pom.xml`
- [ ] Coordinates
- [ ] Dependencies
- [ ] Transitive dependencies
- [ ] Dependency scopes
- [ ] Lifecycle
- [ ] Clean lifecycle
- [ ] Default lifecycle
- [ ] Site lifecycle
- [ ] Phases
- [ ] Goals
- [ ] Plugins
- [ ] Plugin configuration
- [ ] Profiles
- [ ] Properties
- [ ] BOM
- [ ] Dependency management
- [ ] Multi-module projects
- [ ] Parent POM
- [ ] Reactor
- [ ] Dependency conflicts
- [ ] `dependency:tree`
- [ ] Build reproducibility

### Best References
**Primary:** Maven official documentation

**Practical:** *Maven: The Definitive Guide* — Sonatype

---

# 3.2 Gradle

- [ ] Gradle project model
- [ ] Build lifecycle
- [ ] Tasks
- [ ] Plugins
- [ ] Groovy DSL
- [ ] Kotlin DSL
- [ ] Dependency management
- [ ] Configurations
- [ ] Multi-project builds
- [ ] Build cache
- [ ] Configuration cache
- [ ] Incremental builds
- [ ] Gradle Wrapper
- [ ] Dependency locking
- [ ] Build performance

### Best Reference
Gradle official documentation

---

# 3.3 Maven vs Gradle

- [ ] Declarative vs programmable build model
- [ ] Build readability
- [ ] Dependency management
- [ ] Plugin ecosystem
- [ ] Incremental builds
- [ ] Build caching
- [ ] Multi-module projects
- [ ] CI performance
- [ ] Choosing Maven vs Gradle

---

# 3.4 Advanced Git

- [ ] Git object model
- [ ] Commit
- [ ] Tree
- [ ] Blob
- [ ] Tag
- [ ] Branch references
- [ ] HEAD
- [ ] Index/staging area
- [ ] Merge
- [ ] Rebase
- [ ] Interactive rebase
- [ ] Squash
- [ ] Fixup
- [ ] Cherry-pick
- [ ] Reflog
- [ ] Bisect
- [ ] Reset
- [ ] Revert
- [ ] Stash
- [ ] Conflict resolution
- [ ] Remote tracking branches
- [ ] Force push
- [ ] `--force-with-lease`

### Best Book
**Pro Git — Scott Chacon & Ben Straub**

---

# 3.5 Branching Strategies

## GitFlow
- [ ] Main
- [ ] Develop
- [ ] Feature
- [ ] Release
- [ ] Hotfix
- [ ] Trade-offs

## Trunk-Based Development
- [ ] Short-lived branches
- [ ] Frequent integration
- [ ] Feature flags
- [ ] Small commits
- [ ] Continuous integration

## Comparison
- [ ] Release cadence
- [ ] Team size
- [ ] Deployment frequency
- [ ] Merge complexity
- [ ] CI maturity

### Best Reference
**Accelerate — Nicole Forsgren, Jez Humble & Gene Kim**

---

# 3.6 CI/CD Pipeline Architecture

- [ ] Source checkout
- [ ] Dependency resolution
- [ ] Compilation
- [ ] Unit tests
- [ ] Static analysis
- [ ] Security scanning
- [ ] Package creation
- [ ] Artifact publication
- [ ] Container image build
- [ ] Image scanning
- [ ] Deployment
- [ ] Smoke tests
- [ ] Rollback
- [ ] Promotion between environments

## Pipeline Design

```text
Commit
  ↓
Build
  ↓
Unit Tests
  ↓
Static Analysis
  ↓
Security Scan
  ↓
Package
  ↓
Container Image
  ↓
Image Scan
  ↓
Artifact Registry
  ↓
Deploy
  ↓
Integration/Smoke Validation
  ↓
Promotion / Rollback
```

### Best Books
**Primary:** *Accelerate* — Forsgren, Humble & Kim

**Practical:** *Continuous Delivery* — Jez Humble & David Farley

---

# 3.7 CI/CD Platforms

## GitHub Actions
- [ ] Workflows
- [ ] Jobs
- [ ] Steps
- [ ] Actions
- [ ] Runners
- [ ] Secrets
- [ ] Environments
- [ ] Artifacts
- [ ] Matrix builds

## GitLab CI
- [ ] `.gitlab-ci.yml`
- [ ] Stages
- [ ] Jobs
- [ ] Runners
- [ ] Artifacts
- [ ] Caching
- [ ] Environments

## Jenkins
- [ ] Controller/agent architecture
- [ ] Pipelines
- [ ] Declarative pipeline
- [ ] Shared libraries
- [ ] Credentials
- [ ] Agents

## Azure DevOps
- [ ] Pipelines
- [ ] YAML
- [ ] Agents
- [ ] Artifacts
- [ ] Environments

### Best Reference
Official documentation for the platform selected for hands-on work.

---

# 3.8 GitOps

- [ ] Git as desired state
- [ ] Declarative configuration
- [ ] Reconciliation
- [ ] Pull-based deployment
- [ ] Drift detection
- [ ] Rollback
- [ ] Auditability
- [ ] Argo CD
- [ ] Flux
- [ ] GitOps repository structure
- [ ] Application promotion
- [ ] Environment overlays

### Best Books
**Primary:** *GitOps Cookbook* — Christian Hernandez

**Platform:** Argo CD / Flux official documentation

---

# 3.9 Artifact Management

## Nexus
- [ ] Maven repository
- [ ] npm repository
- [ ] Docker registry
- [ ] Proxy repositories
- [ ] Hosted repositories

## JFrog Artifactory
- [ ] Repository types
- [ ] Remote repositories
- [ ] Local repositories
- [ ] Virtual repositories
- [ ] Build metadata

## Container Registries
- [ ] Docker Hub
- [ ] AWS ECR
- [ ] Azure ACR
- [ ] Image tags
- [ ] Image digests
- [ ] Immutable tags
- [ ] Retention
- [ ] Vulnerability scanning

### Best References
Official Nexus, JFrog, ECR and ACR documentation

---

# Module 4 — Containers & Orchestration (Kubernetes)

# 4.1 Container Fundamentals

- [ ] Containers vs virtual machines
- [ ] Linux namespaces
- [ ] cgroups
- [ ] Union/overlay filesystems
- [ ] Images
- [ ] Layers
- [ ] Containers
- [ ] Registries
- [ ] Image manifests
- [ ] Image digests
- [ ] Container lifecycle

### Best Book
**Container Security — Liz Rice**

### Practical Reference
Docker documentation

---

# 4.2 Dockerfile

- [ ] `FROM`
- [ ] `RUN`
- [ ] `COPY`
- [ ] `ADD`
- [ ] `WORKDIR`
- [ ] `ENV`
- [ ] `ARG`
- [ ] `EXPOSE`
- [ ] `USER`
- [ ] `ENTRYPOINT`
- [ ] `CMD`
- [ ] Build context
- [ ] Layer caching
- [ ] Cache invalidation
- [ ] Multi-stage builds
- [ ] Minimal base images
- [ ] Distroless images
- [ ] Non-root containers
- [ ] Reproducible builds
- [ ] Image size optimization

### Best References
Docker documentation + *Container Security*

---

# 4.3 Container Security

- [ ] Run as non-root
- [ ] Read-only filesystem
- [ ] Linux capabilities
- [ ] Dropping capabilities
- [ ] Seccomp concepts
- [ ] AppArmor/SELinux concepts
- [ ] Distroless images
- [ ] Minimal images
- [ ] Secret handling
- [ ] Image signing concepts
- [ ] SBOM concepts
- [ ] Vulnerability scanning
- [ ] Trivy
- [ ] CVE severity
- [ ] Base-image patching

### Best Book
**Container Security — Liz Rice**

---

# 4.4 `.dockerignore`

- [ ] Build context
- [ ] Excluding `.git`
- [ ] Excluding build output
- [ ] Excluding IDE files
- [ ] Excluding secrets
- [ ] Reducing context size
- [ ] Build performance

---

# 4.5 Docker Compose

- [ ] Compose services
- [ ] Networks
- [ ] Volumes
- [ ] Environment variables
- [ ] Health checks
- [ ] Service dependencies
- [ ] Profiles
- [ ] Local database
- [ ] Kafka/RabbitMQ
- [ ] Redis
- [ ] Multi-container application testing

### Best Reference
Docker Compose documentation

---

# 4.6 Kubernetes Architecture

- [ ] Cluster
- [ ] Control plane
- [ ] API Server
- [ ] etcd
- [ ] Scheduler
- [ ] Controller Manager
- [ ] Nodes
- [ ] kubelet
- [ ] kube-proxy
- [ ] Container runtime
- [ ] Desired state
- [ ] Reconciliation loop

### Best Book
**Kubernetes: Up & Running, 3rd Edition — Brendan Burns, Joe Beda & Kelsey Hightower**

---

# 4.7 Pods

- [ ] Pod concept
- [ ] Containers in a pod
- [ ] Shared network namespace
- [ ] Shared volumes
- [ ] Pod lifecycle
- [ ] Init containers
- [ ] Sidecar containers
- [ ] Pod restart policy
- [ ] Pod phases
- [ ] Pod scheduling

### Best Book
**Kubernetes: Up & Running, 3rd Edition**

---

# 4.8 Deployments

- [ ] Deployment
- [ ] ReplicaSet
- [ ] Desired replicas
- [ ] Rolling update
- [ ] Recreate strategy
- [ ] Revision history
- [ ] Rollback
- [ ] Deployment availability
- [ ] Max unavailable
- [ ] Max surge

---

# 4.9 StatefulSets

- [ ] Stateful workload
- [ ] Stable identity
- [ ] Stable network identity
- [ ] Ordered deployment
- [ ] Ordered termination
- [ ] Persistent storage
- [ ] Headless Services
- [ ] Databases on Kubernetes considerations

---

# 4.10 DaemonSets, Jobs & CronJobs

## DaemonSet
- [ ] One pod per node concepts
- [ ] Logging agents
- [ ] Monitoring agents

## Job
- [ ] Completion
- [ ] Retry
- [ ] Parallel jobs

## CronJob
- [ ] Schedule
- [ ] Concurrency policy
- [ ] Job history
- [ ] Missed schedules

---

# 4.11 Kubernetes Networking

## Services
- [ ] ClusterIP
- [ ] NodePort
- [ ] LoadBalancer
- [ ] Service discovery
- [ ] DNS
- [ ] Endpoints/EndpointSlices
- [ ] Port mapping

## Ingress
- [ ] Ingress resource
- [ ] Ingress controller
- [ ] Host routing
- [ ] Path routing
- [ ] TLS termination
- [ ] Ingress vs Gateway API concepts

## Network Policies
- [ ] NetworkPolicy
- [ ] Ingress rules
- [ ] Egress rules
- [ ] Pod selectors
- [ ] Namespace selectors
- [ ] Default deny
- [ ] Network isolation

### Best Book
**Kubernetes: Up & Running, 3rd Edition**

---

# 4.12 ConfigMaps & Secrets

## ConfigMaps
- [ ] Environment variables
- [ ] Mounted files
- [ ] Configuration separation
- [ ] Configuration updates

## Secrets
- [ ] Secret objects
- [ ] Environment injection
- [ ] Volume mounting
- [ ] Encryption-at-rest concepts
- [ ] Secret rotation
- [ ] External secret managers
- [ ] Why Kubernetes Secret encoding is not encryption by itself

### Best References
Kubernetes documentation

---

# 4.13 Persistent Storage

- [ ] PersistentVolume
- [ ] PersistentVolumeClaim
- [ ] StorageClass
- [ ] Dynamic provisioning
- [ ] Access modes
- [ ] Reclaim policies
- [ ] CSI
- [ ] Volume snapshots
- [ ] Stateful applications

### Best Book
**Kubernetes: Up & Running, 3rd Edition**

---

# 4.14 Kubernetes Scaling

## HPA
- [ ] CPU-based scaling
- [ ] Memory-based scaling
- [ ] Custom metrics
- [ ] Scaling behavior
- [ ] Stabilization windows
- [ ] Scale-up
- [ ] Scale-down

## VPA
- [ ] Resource recommendation
- [ ] Resource adjustment
- [ ] Update modes
- [ ] Use cases

## KEDA
- [ ] Event-driven scaling
- [ ] Kafka scaler
- [ ] Queue-based scaling
- [ ] Scale to zero
- [ ] Trigger configuration

### Best References
Kubernetes autoscaling documentation + KEDA documentation

---

# 4.15 RBAC & Namespaces

- [ ] Namespace
- [ ] Role
- [ ] ClusterRole
- [ ] RoleBinding
- [ ] ClusterRoleBinding
- [ ] ServiceAccount
- [ ] Least privilege
- [ ] Resource isolation
- [ ] Multi-tenancy concepts

### Best References
Kubernetes documentation

---

# 4.16 Resource Management

- [ ] CPU requests
- [ ] CPU limits
- [ ] Memory requests
- [ ] Memory limits
- [ ] QoS classes
- [ ] Guaranteed
- [ ] Burstable
- [ ] BestEffort
- [ ] OOMKilled
- [ ] CPU throttling
- [ ] Resource planning

---

# 4.17 Pod Security Context

- [ ] `runAsUser`
- [ ] `runAsNonRoot`
- [ ] `fsGroup`
- [ ] Capabilities
- [ ] Privileged containers
- [ ] Read-only filesystem
- [ ] SecurityContext
- [ ] Pod Security Standards
- [ ] Restricted profile

### Best Book
**Container Security — Liz Rice**

---

# 4.18 Helm

- [ ] Chart
- [ ] `Chart.yaml`
- [ ] Templates
- [ ] Values
- [ ] Release
- [ ] Helm repository
- [ ] Template functions
- [ ] Named templates
- [ ] `helm upgrade`
- [ ] `helm rollback`
- [ ] Dependency charts
- [ ] Environment configuration

### Best Reference
Helm documentation

---

# 4.19 Kustomize

- [ ] Base
- [ ] Overlay
- [ ] Patches
- [ ] ConfigMap generators
- [ ] Secret generators
- [ ] Environment overlays
- [ ] Kustomize vs Helm

### Best Reference
Kubernetes/Kustomize documentation

---

# 4.20 Service Mesh

## Concepts
- [ ] Service mesh
- [ ] Data plane
- [ ] Control plane
- [ ] Sidecar/proxy model
- [ ] mTLS
- [ ] Service identity
- [ ] Traffic routing
- [ ] Traffic splitting
- [ ] Retries
- [ ] Timeouts
- [ ] Fault injection
- [ ] Telemetry
- [ ] Policy enforcement

## Istio
- [ ] Architecture
- [ ] Control plane
- [ ] Envoy
- [ ] VirtualService
- [ ] DestinationRule
- [ ] Gateway
- [ ] PeerAuthentication
- [ ] AuthorizationPolicy

## Linkerd
- [ ] Architecture
- [ ] Data plane
- [ ] Identity
- [ ] Traffic policy
- [ ] Observability

## Trade-offs
- [ ] Operational complexity
- [ ] Latency overhead
- [ ] Debugging complexity
- [ ] When a service mesh is justified

### Best References
**Primary:** *Istio in Action* — Christian Posta & Rinor Maloku

**Alternative:** Linkerd documentation

---

# Module 5 — Cloud Engineering: AWS / Azure / GCP

> **Recommended approach:** Go deep on **one cloud provider**. Understand equivalent services in the other two rather than trying to master all three simultaneously.

---

# 5.1 Infrastructure as Code — Terraform

- [ ] Infrastructure as Code
- [ ] Declarative infrastructure
- [ ] Providers
- [ ] Resources
- [ ] Data sources
- [ ] Variables
- [ ] Outputs
- [ ] Locals
- [ ] Modules
- [ ] Module composition
- [ ] State
- [ ] State locking
- [ ] Remote state
- [ ] State isolation
- [ ] Workspaces
- [ ] Drift
- [ ] Import
- [ ] Plan
- [ ] Apply
- [ ] Destroy
- [ ] Dependency graph
- [ ] Secrets in Terraform
- [ ] CI/CD integration

### Best Book
**Terraform: Up & Running, 3rd Edition — Yevgeniy Brikman**

### Reference
HashiCorp Terraform documentation

---

# 5.2 CloudFormation / AWS CDK

## CloudFormation
- [ ] Templates
- [ ] Resources
- [ ] Parameters
- [ ] Outputs
- [ ] Stack
- [ ] Stack updates
- [ ] Drift detection
- [ ] Change sets

## AWS CDK
- [ ] Constructs
- [ ] Stacks
- [ ] Apps
- [ ] Synthesis
- [ ] CloudFormation generation
- [ ] Reusable constructs

### Best Reference
AWS CloudFormation and AWS CDK documentation

---

# 5.3 AWS — Core Compute

## EC2
- [ ] Instance types
- [ ] AMI
- [ ] EBS
- [ ] Security Groups
- [ ] Auto Scaling
- [ ] User data
- [ ] Instance lifecycle

## Lambda
- [ ] Function
- [ ] Runtime
- [ ] Handler
- [ ] Cold start
- [ ] Concurrency
- [ ] Provisioned concurrency
- [ ] Event sources
- [ ] Timeouts
- [ ] Stateless execution

## ECS / EKS
- [ ] ECS concepts
- [ ] ECS task
- [ ] ECS service
- [ ] Fargate
- [ ] EKS
- [ ] Kubernetes control plane concepts
- [ ] When ECS vs EKS

### Best Book
**Amazon Web Services in Action, 3rd Edition — Michael Wittig & Andreas Wittig**

### Reference
AWS official documentation

---

# 5.4 AWS Storage — S3

- [ ] Buckets
- [ ] Objects
- [ ] Object keys
- [ ] Storage classes
- [ ] Standard
- [ ] Intelligent-Tiering
- [ ] Glacier classes
- [ ] Lifecycle policies
- [ ] Versioning
- [ ] Object Lock concepts
- [ ] Encryption
- [ ] Presigned URLs
- [ ] Multipart upload
- [ ] Event notifications

### Best References
AWS S3 documentation

---

# 5.5 AWS Databases

## RDS
- [ ] Managed relational database
- [ ] Multi-AZ
- [ ] Read replicas
- [ ] Backups
- [ ] Failover
- [ ] Parameter groups

## DynamoDB
- [ ] Partition key
- [ ] Sort key
- [ ] GSI
- [ ] LSI
- [ ] Capacity modes
- [ ] Conditional writes
- [ ] Streams
- [ ] TTL

### Best References
AWS documentation + *Designing Data-Intensive Applications*

---

# 5.6 AWS Integration

## SQS
- [ ] Standard
- [ ] FIFO
- [ ] Visibility timeout
- [ ] Long polling
- [ ] DLQ
- [ ] Message retention

## SNS
- [ ] Topics
- [ ] Subscriptions
- [ ] Fanout
- [ ] Filtering

## API Gateway
- [ ] HTTP APIs
- [ ] REST APIs
- [ ] Routing
- [ ] Throttling
- [ ] Integration
- [ ] Authentication concepts

## EventBridge
- [ ] Event bus
- [ ] Rules
- [ ] Event patterns
- [ ] Targets
- [ ] Scheduled events
- [ ] Event-driven architecture

### Best Reference
AWS official documentation

---

# 5.7 AWS Networking

## VPC
- [ ] VPC
- [ ] CIDR
- [ ] Public subnet
- [ ] Private subnet
- [ ] Route table
- [ ] Internet Gateway
- [ ] NAT Gateway
- [ ] VPC endpoints
- [ ] Security Groups
- [ ] Network ACLs
- [ ] Availability Zones

## Route 53
- [ ] Hosted zones
- [ ] DNS records
- [ ] Routing policies
- [ ] Health checks

### Best Book
**Amazon Web Services in Action**

### Reference
AWS networking documentation

---

# 5.8 AWS IAM

- [ ] IAM users
- [ ] IAM roles
- [ ] Policies
- [ ] Policy statements
- [ ] Actions
- [ ] Resources
- [ ] Conditions
- [ ] Least privilege
- [ ] Role assumption
- [ ] Instance roles
- [ ] Workload identity concepts
- [ ] Policy evaluation

### Best References
AWS IAM documentation

---

# 5.9 AWS CloudWatch & CloudTrail

## CloudWatch
- [ ] Metrics
- [ ] Logs
- [ ] Alarms
- [ ] Dashboards
- [ ] Log Insights
- [ ] Application monitoring

## CloudTrail
- [ ] API activity
- [ ] Audit logs
- [ ] Event history
- [ ] Trails
- [ ] Security investigation

### Best References
AWS documentation

---

# 5.10 Azure

## Compute
- [ ] AKS
- [ ] App Service
- [ ] Azure Functions

## Integration
- [ ] Service Bus
- [ ] Event Grid

## Data
- [ ] Cosmos DB
- [ ] Azure SQL

## Security
- [ ] Key Vault

## Observability
- [ ] Application Insights
- [ ] Azure Monitor

### Comparison Mapping

| Concept | AWS | Azure |
|---|---|---|
| Kubernetes | EKS | AKS |
| Serverless Functions | Lambda | Azure Functions |
| Object Storage | S3 | Blob Storage |
| Relational DB | RDS | Azure SQL |
| Queue | SQS | Service Bus |
| Secrets | Secrets Manager | Key Vault |
| Monitoring | CloudWatch | Azure Monitor |

### Best References
Microsoft Azure official documentation

---

# 5.11 GCP

## Compute
- [ ] GKE
- [ ] Cloud Run
- [ ] Cloud Functions

## Messaging
- [ ] Pub/Sub

## Data
- [ ] BigQuery
- [ ] Cloud Spanner
- [ ] Cloud SQL

### Comparison Mapping

| Concept | AWS | GCP |
|---|---|---|
| Kubernetes | EKS | GKE |
| Serverless containers | ECS/Fargate / App Runner concepts | Cloud Run |
| Functions | Lambda | Cloud Functions |
| Messaging | SQS/SNS | Pub/Sub |
| Relational | RDS | Cloud SQL |
| Globally distributed relational | Aurora/global concepts | Spanner |
| Analytics | Redshift | BigQuery |

### Best References
Google Cloud official documentation

---

# 5.12 Cloud Architecture Mastery

For the selected primary cloud:

- [ ] Design highly available architecture
- [ ] Multi-AZ deployment
- [ ] Auto scaling
- [ ] Disaster recovery
- [ ] Backup strategy
- [ ] Cost optimization
- [ ] IAM least privilege
- [ ] Network segmentation
- [ ] Observability
- [ ] Infrastructure as Code
- [ ] Immutable deployments
- [ ] Failure recovery
- [ ] RTO
- [ ] RPO

### Best Book
**AWS Well-Architected Framework** / corresponding cloud architecture framework

---

# Module 6 — Observability, APM & Reliability

# 6.1 Observability Fundamentals

- [ ] Monitoring vs observability
- [ ] Metrics
- [ ] Logs
- [ ] Traces
- [ ] Events
- [ ] Correlation
- [ ] Cardinality
- [ ] Sampling
- [ ] Golden signals
- [ ] RED method
- [ ] USE method
- [ ] Service-level indicators

### Best Books
**Primary:** *Distributed Systems Observability* — Cindy Sridharan

**SRE:** *Site Reliability Engineering* — Google

---

# 6.2 Metrics & Micrometer

- [ ] Counter
- [ ] Gauge
- [ ] Timer
- [ ] Distribution summary
- [ ] Histograms
- [ ] Percentiles
- [ ] Tags
- [ ] Cardinality
- [ ] JVM metrics
- [ ] HTTP metrics
- [ ] Database metrics
- [ ] Custom application metrics
- [ ] Business metrics

### Best Reference
Micrometer documentation

---

# 6.3 Prometheus

- [ ] Pull model
- [ ] Scraping
- [ ] Targets
- [ ] Exporters
- [ ] Labels
- [ ] Time series
- [ ] PromQL
- [ ] Rate functions
- [ ] Histograms
- [ ] Recording rules
- [ ] Alerting rules
- [ ] Service discovery
- [ ] High-cardinality problems
- [ ] Retention

### Best Reference
Prometheus documentation

---

# 6.4 Grafana

- [ ] Data sources
- [ ] Dashboards
- [ ] Panels
- [ ] Variables
- [ ] Prometheus queries
- [ ] Alerting
- [ ] Dashboard design
- [ ] Service dashboards
- [ ] Infrastructure dashboards
- [ ] Business dashboards

### Best Reference
Grafana documentation

---

# 6.5 Alerting & Incident Management

- [ ] Alert thresholds
- [ ] Static thresholds
- [ ] Dynamic thresholds
- [ ] Alert fatigue
- [ ] Actionable alerts
- [ ] Severity
- [ ] Escalation
- [ ] On-call
- [ ] PagerDuty
- [ ] Incident lifecycle
- [ ] Runbooks
- [ ] Postmortems
- [ ] Error budgets

### Best Books
**Primary:** *Site Reliability Engineering*

**Practical:** *The Site Reliability Workbook*

---

# 6.6 Centralized Logging

## Java Logging
- [ ] SLF4J
- [ ] Logback
- [ ] Log4j2
- [ ] Logging facade vs implementation
- [ ] Log levels
- [ ] Structured logging
- [ ] JSON logging
- [ ] Exception logging

## Contextual Logging
- [ ] MDC
- [ ] Correlation ID
- [ ] Trace ID
- [ ] Span ID
- [ ] ThreadLocal behavior
- [ ] Async context propagation
- [ ] Reactive context considerations

## Aggregation
- [ ] Elasticsearch
- [ ] Logstash
- [ ] Fluentd
- [ ] Kibana
- [ ] EFK
- [ ] ELK
- [ ] Loki
- [ ] Log shipping
- [ ] Retention
- [ ] Indexing
- [ ] Search
- [ ] Cost management
- [ ] Sensitive data handling

### Best References
**Primary:** *Distributed Systems Observability*

**Implementation:** Elastic and Grafana Loki documentation

---

# 6.7 Distributed Tracing

## OpenTelemetry

- [ ] Instrumentation
- [ ] Automatic instrumentation
- [ ] Manual instrumentation
- [ ] Trace
- [ ] Span
- [ ] Parent span
- [ ] Attributes
- [ ] Events
- [ ] Status
- [ ] Sampling
- [ ] Context propagation

## W3C Trace Context

- [ ] `traceparent`
- [ ] `tracestate`
- [ ] Trace ID
- [ ] Span ID
- [ ] Trace flags
- [ ] HTTP propagation
- [ ] Messaging propagation

## Backends

- [ ] Jaeger
- [ ] Zipkin
- [ ] Trace storage
- [ ] Trace querying
- [ ] Sampling strategies

### Best References
**Primary:** OpenTelemetry documentation

**Concepts:** *Distributed Systems Observability*

---

# 6.8 APM

Understand what commercial APM platforms provide rather than memorizing individual UI features.

## Datadog
- [ ] APM
- [ ] Infrastructure metrics
- [ ] Logs
- [ ] Traces
- [ ] Service maps

## New Relic
- [ ] APM
- [ ] Distributed tracing
- [ ] Logs
- [ ] Metrics
- [ ] Alerting

## Dynatrace
- [ ] APM
- [ ] Service topology
- [ ] Automatic instrumentation
- [ ] Root-cause analysis

## AppDynamics
- [ ] Application monitoring
- [ ] Business transactions
- [ ] Performance analysis

### Cross-Platform Concepts
- [ ] Request traces
- [ ] Service dependency maps
- [ ] Database monitoring
- [ ] Error analysis
- [ ] JVM monitoring
- [ ] CPU profiling
- [ ] Memory profiling
- [ ] Alerting
- [ ] Anomaly detection

### Best Reference
Vendor documentation for the APM platform used in your organization.

---

# 6.9 Production Profiling

## CPU Problems
- [ ] CPU saturation
- [ ] Hot methods
- [ ] Thread contention
- [ ] Lock contention
- [ ] Excessive GC
- [ ] Profiling with JFR
- [ ] Async-profiler concepts

## Memory Problems
- [ ] Heap growth
- [ ] Memory leak
- [ ] Allocation rate
- [ ] Old-generation pressure
- [ ] GC pressure
- [ ] Heap dump
- [ ] Dominator tree
- [ ] Retained size
- [ ] Object histogram

## Thread Problems
- [ ] Thread dump
- [ ] Blocked threads
- [ ] Waiting threads
- [ ] Deadlock
- [ ] Thread pool exhaustion

### Best References
**Primary:** *Optimizing Java* — Benjamin J. Evans, James Gough & Chris Newland

**Practical:** Java Flight Recorder / Mission Control documentation

---

# 6.10 Health Checks

- [ ] Liveness
- [ ] Readiness
- [ ] Startup
- [ ] Dependency health
- [ ] Application health
- [ ] Kubernetes probes
- [ ] Failure thresholds
- [ ] Initial delays
- [ ] Probe timeouts
- [ ] Probe intervals
- [ ] Avoiding expensive health checks
- [ ] Avoiding dependency cascades

### Best References
Spring Boot Actuator documentation + Kubernetes documentation

---

# 6.11 Graceful Shutdown

- [ ] Why graceful shutdown matters
- [ ] SIGTERM
- [ ] Container termination
- [ ] Kubernetes termination lifecycle
- [ ] `terminationGracePeriodSeconds`
- [ ] Stop accepting new traffic
- [ ] Readiness transition
- [ ] Finish in-flight requests
- [ ] Close connections
- [ ] Stop background workers
- [ ] Close message consumers
- [ ] Drain queues
- [ ] Application shutdown hooks
- [ ] Forced termination after grace period

### Best References
Spring Boot graceful shutdown documentation + Kubernetes documentation

---

# Critical Production Flows to Master

## Database Query

```text
Application
    ↓
Connection Pool
    ↓
Database Connection
    ↓
SQL Parser
    ↓
Query Optimizer
    ↓
Execution Plan
    ↓
Index / Table Scan
    ↓
Join / Aggregate / Sort
    ↓
Result
```

Be able to diagnose:

- [ ] Slow SQL
- [ ] Bad query plan
- [ ] Missing index
- [ ] Wrong index
- [ ] Cardinality estimation error
- [ ] Lock wait
- [ ] Connection-pool exhaustion
- [ ] Replication lag

---

# Kubernetes Deployment Flow

```text
Git
 ↓
CI Pipeline
 ↓
Container Build
 ↓
Image Registry
 ↓
Helm/Kustomize
 ↓
GitOps / kubectl
 ↓
Kubernetes API Server
 ↓
Scheduler
 ↓
Node
 ↓
kubelet
 ↓
Container Runtime
 ↓
Pod
```

Understand:

- [ ] Desired state
- [ ] Reconciliation
- [ ] Scheduling
- [ ] Rolling update
- [ ] Readiness
- [ ] Liveness
- [ ] Autoscaling
- [ ] Rollback

---

# CI/CD Flow

```text
Developer
   ↓
Git Commit
   ↓
CI
   ↓
Build
   ↓
Unit Tests
   ↓
Static Analysis
   ↓
Security / Image Scan
   ↓
Artifact Registry
   ↓
Deployment
   ↓
Health Checks
   ↓
Observability
   ↓
Promotion / Rollback
```

---

# Cloud Network Flow

```text
Internet
   ↓
DNS
   ↓
Load Balancer / API Gateway
   ↓
Public Subnet
   ↓
Private Subnet
   ↓
Application
   ↓
Database
```

Understand:

- [ ] Route tables
- [ ] NAT
- [ ] Internet Gateway
- [ ] Security groups
- [ ] Network ACLs
- [ ] Private endpoints
- [ ] Availability zones

---

# Observability Investigation Flow

When production is slow:

```text
Alert
 ↓
Metrics
 ↓
Identify affected service
 ↓
Trace
 ↓
Find slow span
 ↓
Logs
 ↓
Correlate Trace ID
 ↓
Profile if necessary
 ↓
Identify bottleneck
 ↓
Mitigate
 ↓
Postmortem
```

---

# Highest-Priority Deep-Mastery Areas

## Tier 1 — Must Master

### Databases
- [ ] Advanced SQL
- [ ] Joins
- [ ] CTEs
- [ ] Window functions
- [ ] Index design
- [ ] EXPLAIN / execution plans
- [ ] Transactions
- [ ] Isolation
- [ ] Locks
- [ ] Deadlocks
- [ ] PostgreSQL MVCC
- [ ] Connection pooling

### Distributed Data
- [ ] CAP
- [ ] PACELC
- [ ] Consistency models
- [ ] Quorum
- [ ] Replication
- [ ] Sharding
- [ ] Cassandra partitioning
- [ ] Redis fundamentals

### DevOps
- [ ] Maven
- [ ] Git
- [ ] CI/CD
- [ ] Artifact management
- [ ] GitOps

### Kubernetes
- [ ] Pods
- [ ] Deployments
- [ ] Services
- [ ] Ingress
- [ ] ConfigMaps/Secrets
- [ ] Storage
- [ ] HPA
- [ ] RBAC
- [ ] Resource limits
- [ ] Helm
- [ ] Network Policies

### Cloud
- [ ] Terraform
- [ ] One cloud provider deeply
- [ ] IAM
- [ ] Networking
- [ ] Compute
- [ ] Storage
- [ ] Databases
- [ ] Messaging
- [ ] Monitoring

### Observability
- [ ] Metrics
- [ ] Prometheus
- [ ] Grafana
- [ ] Structured logging
- [ ] MDC
- [ ] OpenTelemetry
- [ ] Distributed tracing
- [ ] Health probes
- [ ] Graceful shutdown

---

# Tier 2 — Strong Senior-Level Knowledge

- [ ] Recursive CTEs
- [ ] Grouping sets / Rollup / Cube
- [ ] Materialized views
- [ ] Advanced PostgreSQL indexes
- [ ] InnoDB internals
- [ ] MongoDB aggregation
- [ ] DynamoDB modeling
- [ ] Neo4j/Cypher
- [ ] Time-series databases
- [ ] Raft/Paxos concepts
- [ ] Gradle
- [ ] GitFlow vs trunk-based development
- [ ] GitHub Actions/GitLab/Jenkins/Azure DevOps
- [ ] Docker security
- [ ] StatefulSets
- [ ] KEDA
- [ ] VPA
- [ ] Kustomize
- [ ] Service mesh
- [ ] Azure
- [ ] GCP
- [ ] APM platforms
- [ ] Production profiling

---

# Tier 3 — Advanced / Specialist

- [ ] Query optimizer behavior
- [ ] PostgreSQL VACUUM/autovacuum tuning
- [ ] PostgreSQL GIN/GiST internals
- [ ] InnoDB redo/undo internals
- [ ] Advanced sharding strategies
- [ ] Distributed consensus
- [ ] Kubernetes scheduler behavior
- [ ] Advanced CNI/networking
- [ ] Service-mesh traffic engineering
- [ ] Advanced Terraform state architecture
- [ ] Multi-cloud architecture
- [ ] Advanced PromQL
- [ ] OpenTelemetry pipeline architecture
- [ ] JVM production profiling
- [ ] Distributed-system incident investigation

---

# Best Books — Module Summary

| Module | Best Primary Book | Best Deep-Dive / Reference |
|---|---|---|
| Module 1 — Relational DB & SQL | **SQL Performance Explained — Markus Winand** | **Database System Concepts** |
| Module 1 — PostgreSQL | **PostgreSQL: Up and Running** | **The Art of PostgreSQL** |
| Module 1 — MySQL | **High Performance MySQL** | MySQL/InnoDB documentation |
| Module 2 — NoSQL & Distributed Data | **Designing Data-Intensive Applications — Martin Kleppmann** | **Database Internals — Alex Petrov** |
| Module 2 — Cassandra | **Cassandra: The Definitive Guide** | Cassandra documentation |
| Module 2 — Redis | **Redis in Action** | Redis documentation |
| Module 3 — DevOps / CI/CD | **Accelerate** | **Continuous Delivery** |
| Module 3 — Git | **Pro Git** | Git documentation |
| Module 3 — Maven | **Maven: The Definitive Guide** | Maven documentation |
| Module 4 — Docker/Kubernetes | **Kubernetes: Up & Running, 3rd Ed.** | **Container Security — Liz Rice** |
| Module 4 — Service Mesh | **Istio in Action** | Istio/Linkerd documentation |
| Module 5 — Cloud | **Terraform: Up & Running, 3rd Ed.** | **AWS in Action** / cloud provider docs |
| Module 6 — Observability | **Distributed Systems Observability** | OpenTelemetry documentation |
| Module 6 — SRE | **Site Reliability Engineering** | **The Site Reliability Workbook** |
| Module 6 — Java Profiling | **Optimizing Java** | JFR/JMC documentation |

---

# Recommended Core Book Collection

If you want to avoid an enormous reading list, prioritize these:

## 1. SQL Performance Explained — Markus Winand
Your main SQL-performance book.

## 2. Database System Concepts — Silberschatz, Korth & Sudarshan
Your database-theory foundation.

## 3. Designing Data-Intensive Applications — Martin Kleppmann
The most important book for:
- Distributed databases
- Replication
- Partitioning
- Consistency
- CAP
- Distributed systems

## 4. Pro Git — Scott Chacon & Ben Straub
Your Git reference.

## 5. Accelerate — Forsgren, Humble & Kim
Your CI/CD and engineering-performance foundation.

## 6. Continuous Delivery — Jez Humble & David Farley
Your deeper CI/CD book.

## 7. Kubernetes: Up & Running — Burns, Beda & Hightower
Your Kubernetes foundation.

## 8. Container Security — Liz Rice
Your container/Kubernetes security foundation.

## 9. Terraform: Up & Running — Yevgeniy Brikman
Your IaC foundation.

## 10. Distributed Systems Observability — Cindy Sridharan
Your observability foundation.

## 11. Site Reliability Engineering — Google
Your SRE foundation.

## 12. Optimizing Java
Your production JVM performance companion.

---

# Recommended Study Order

```text
SQL Fundamentals
      ↓
Advanced SQL
      ↓
Indexing + EXPLAIN
      ↓
Transactions + Concurrency
      ↓
PostgreSQL / MySQL Internals
      ↓
NoSQL Fundamentals
      ↓
Distributed Data + CAP/PACELC
      ↓
Git
      ↓
Maven / Gradle
      ↓
CI/CD
      ↓
Docker
      ↓
Kubernetes
      ↓
Helm + Kustomize
      ↓
Terraform
      ↓
Primary Cloud Provider
      ↓
Prometheus + Grafana
      ↓
Structured Logging
      ↓
OpenTelemetry + Tracing
      ↓
APM + Profiling
      ↓
Reliability + Incident Management
```

---

# Final Mastery Standard

Do not mark a topic complete merely because you can use the command or configuration.

For example, don't consider database indexing complete because you know:

```sql
CREATE INDEX idx_user_email ON users(email);
```

You should be able to explain:

```text
Query
 ↓
Optimizer
 ↓
Statistics
 ↓
Execution Plan
 ↓
Index Scan / Sequential Scan
 ↓
Rows
 ↓
Result
```

And answer:

- [ ] Why did the optimizer choose this plan?
- [ ] Why did it ignore the index?
- [ ] Is the index selective enough?
- [ ] Would a composite index be better?
- [ ] What is the write cost?
- [ ] Is the table/index bloated?
- [ ] What happens under high concurrency?
- [ ] Could a lock be causing the latency?
- [ ] Could connection-pool exhaustion be the real problem?

Likewise, don't consider Kubernetes complete because you can write:

```yaml
kind: Deployment
```

You should be able to explain:

```text
Deployment
   ↓
ReplicaSet
   ↓
Pod
   ↓
Scheduler
   ↓
Node
   ↓
kubelet
   ↓
Container Runtime
   ↓
Container
```

And diagnose:

- [ ] Pending pod
- [ ] ImagePullBackOff
- [ ] CrashLoopBackOff
- [ ] OOMKilled
- [ ] Readiness failure
- [ ] Liveness failure
- [ ] Service connectivity failure
- [ ] DNS failure
- [ ] NetworkPolicy block
- [ ] Resource starvation
- [ ] Failed rollout

For cloud and observability, use the same standard.

> **Understand → Implement → Inspect Internals → Measure → Diagnose Failure → Optimize → Automate → Operate in Production → Explain the Trade-offs**
