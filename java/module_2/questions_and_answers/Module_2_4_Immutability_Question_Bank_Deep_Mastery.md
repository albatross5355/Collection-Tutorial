# Module 2.4 — Immutability
## Deep Mastery Question Bank

> **Required flow:** Basic → Intermediate → Advanced → Traps → Design → Debugging Challenges → Production
>
> **Completion standard:** Explain → Implement → Explain internals → Handle edge cases → Discuss trade-offs → Debug → Use in production.

This question bank follows the Module 2.4 Deep Mastery Guide, including immutable classes, final classes/fields, constructor initialization and validation, defensive copying, mutable field hazards, arrays, nested mutable objects, shallow/deep immutability, immutable collections, records, immutable value objects, hash-based collections, caching, sharing, concurrency/JMM, performance, production architecture, debugging, and the final mastery gate. fileciteturn17file1L113-L154 fileciteturn17file4L718-L736

---

# 2.4.1 Immutability — Fundamentals

## Basic

1. What is immutability?
2. What does it mean for an object to be immutable?
3. What must remain unchanged after construction?
4. Why is immutability useful in Java?
5. What is the difference between an immutable object and a mutable object?
6. Give three examples of commonly immutable Java types.
7. Why is `String` a classic immutable type?
8. Why are immutable objects easier to reason about?
9. Why can immutable objects be shared safely?
10. Why are immutable objects useful in concurrent programs?
11. Why can immutable objects be useful as cache values?
12. Why can immutable objects be useful as HashMap keys?
13. What is an invariant?
14. Why should immutable object invariants be established during construction?
15. Why should an immutable object avoid setter-based state changes?

## Intermediate

16. Explain the lifecycle of an immutable object from construction to use.
17. What does "state cannot change" mean when the reference variable itself can be reassigned?
18. Can an immutable object contain references to other objects?
19. Can an immutable object contain a mutable object?
20. Why is immutability about reachable state rather than only syntax?
21. Why is `private final` useful but insufficient by itself?
22. What makes an immutable design robust?
23. Why does immutable state reduce coordination between threads?
24. Why can immutability simplify caching?
25. Why can immutability simplify testing?

## Advanced

26. Distinguish object immutability from reference immutability.
27. Explain why immutability can be shallow or deep.
28. Explain why an object's entire reachable state may need auditing.
29. Explain how immutability interacts with `equals()` and `hashCode()`.
30. Explain why stable state is valuable for hash-based collections.
31. Explain why immutable objects can be safely shared but still consume memory.
32. Explain why immutability does not automatically make every surrounding operation thread-safe.
33. Explain why immutable design is a property of the complete API surface, not just fields.

---

# 2.4.2 How to Create an Immutable Class

## Basic

34. What characteristics should a typical immutable class have?
35. Why are fields usually `private`?
36. Why are fields usually `final`?
37. Why are setters normally absent?
38. Why should construction establish complete valid state?
39. Why is exposing mutable internal state dangerous?
40. Why is class extension a concern for immutable classes?
41. Why is a final class often the simplest design?
42. Implement a simple immutable `Employee`.
43. Implement an immutable `UserId`.
44. Implement an immutable `Money` value object.

## Intermediate

45. What does this guarantee?

```java
private final String name;
```

46. What does it not guarantee?
47. Why is this potentially mutable?

```java
private final List<String> roles;
```

48. Why can a final class still contain mutable state?
49. Why must getters be reviewed for mutation leaks?
50. Why should constructor arguments be reviewed for ownership?
51. What does extension-safe immutable design mean?
52. What alternatives exist to `final` for controlling extension?
53. When could a sealed hierarchy be relevant?
54. When could private constructors/factories help control implementations?

## Advanced

55. Why is `final` on the class not mathematically required for immutability?
56. Why is preventing unsafe subclassing nevertheless a common robust technique?
57. How can a malicious or careless subclass undermine an otherwise immutable design?
58. What does it mean for an immutable class to be extension-safe?
59. How would you review an immutable class for hidden mutation paths?
60. How would you prove that all externally reachable state is stable?

---

# 2.4.3 Final Fields

## Basic

61. What is a final field?
62. When can an instance final field be assigned?
63. What happens after a final field has been initialized?
64. Does final prevent reassignment?
65. Does final make the referenced object immutable?
66. Explain the difference between:

```java
final int x;
```

and:

```java
final List<String> names;
```

## Intermediate

67. Why can `final List<String>` still be mutated?
68. What does this code allow?

```java
names.add("Alice");
```

69. What does it prohibit?

```java
names = anotherList;
```

70. Draw the reference-versus-object model for a final mutable field.
71. Why is "final reference ≠ immutable object" an important rule?
72. Identify all final fields in an immutable class and classify their referenced objects as mutable or immutable.

## Advanced

73. Explain final-field semantics in the Java Memory Model.
74. Why are final fields important for safe construction?
75. What is the relationship between final-field initialization and object publication?
76. Why does final-field semantics not magically make mutable referenced objects safe?
77. Why must mutable references inside final fields still be protected?

---

# 2.4.4 Final Class

78. What does `final class` mean?
79. Why might an immutable class be declared final?
80. How can inheritance complicate immutability?
81. Can a final class be subclassed?
82. Why does preventing subclassing simplify reasoning about invariants?
83. When might a carefully designed inheritance hierarchy still be possible?
84. How do sealed classes change the design space?
85. Why is final commonly the simplest option for ordinary immutable value objects?

---

# 2.4.5 Constructor Initialization

## Basic

86. Why should immutable state be established during construction?
87. What does constructor validation accomplish?
88. Why should invalid state be rejected early?
89. Why are partially configured public objects undesirable?
90. Why does construction-time validation simplify later code?

## Intermediate

91. Implement an immutable `Account` with non-null fields.
92. Use `Objects.requireNonNull()` in an immutable constructor.
93. Validate numeric invariants in an immutable `Money` class.
94. Explain:

```text
constructor
   ↓
validation
   ↓
complete valid state
   ↓
immutable object
```

95. Why is this preferable to post-construction configuration?
96. What risks exist when validation is split across setters?

## Advanced

97. What does a stable invariant mean?
98. Why does immutable construction reduce the number of states an object can occupy?
99. How can construction-time validation reduce defensive checks elsewhere?
100. How does complete construction relate to safe publication?
101. How would you design an immutable object whose constructor receives several interdependent values?

---

# 2.4.6 Defensive Copying

## Basic

102. What is defensive copying?
103. Why is defensive copying necessary for immutable classes?
104. What is wrong with storing a caller-owned mutable list directly?
105. What is wrong with returning an internal mutable list directly?
106. Why are arrays a classic defensive-copying trap?
107. Why must both input and output boundaries be considered?

## Intermediate

108. Explain:

```text
caller-owned mutable object
        ↓
defensive copy
        ↓
internal state
```

109. Explain output protection:

```text
internal mutable state
        ↓
copy / immutable representation
        ↓
caller
```

110. Implement constructor copying for a `List`.
111. Implement getter copying for a `List`.
112. Implement constructor copying for an array.
113. Implement getter copying for an array.
114. Use `List.copyOf()` where appropriate.
115. Compare `new ArrayList<>(roles)` with `List.copyOf(roles)`.
116. When is a defensive copy unnecessary because the input type is itself immutable?

## Advanced

117. Why is defensive copying part of API ownership semantics?
118. How can callers retain aliases to mutable state?
119. Why must every mutable input be audited?
120. Why must every mutable output be audited?
121. How can defensive copying affect memory and performance?
122. When should copying be combined with validation and normalization?
123. How would you defend a large mutable object without copying it unnecessarily?

---

# 2.4.7 Mutable Field Hazards

124. List common mutable field types that can break immutability.
125. Why is `ArrayList` dangerous inside an immutable class?
126. Why is `HashMap` dangerous inside an immutable class?
127. Why are arrays especially dangerous?
128. Why can `Date` be problematic?
129. Why can mutable custom objects be problematic?
130. Why can `StringBuilder` break an immutable design?
131. Why can buffers break immutability?
132. Why can framework objects introduce hidden mutability?
133. Audit a class containing `private final List<String>`.
134. Audit a class containing `private final Map<String, Object>`.
135. Audit a class containing `private final byte[]`.

---

# 2.4.8 Arrays — Major Trap

## Basic

136. Why is an array mutable?
137. Why does `private final byte[] token` not make the token immutable?
138. What happens if a caller mutates the original array after construction?
139. What happens if a getter returns the internal array directly?

## Intermediate

140. Fix:

```java
public User(byte[] token) {
    this.token = token;
}
```

141. Fix:

```java
public byte[] getToken() {
    return token;
}
```

142. Explain `token.clone()` at the input boundary.
143. Explain `token.clone()` at the output boundary.
144. Why must both directions be protected?
145. Write mutation tests proving the object cannot be changed through the original array.

## Advanced

146. What does array defensive copying protect?
147. What does array cloning not automatically protect if the array contains mutable object references?
148. Explain shallow array copying versus deep copying of array elements.
149. Design an immutable object containing `byte[]`.
150. Design an immutable object containing `SomeMutableType[]`.

---

# 2.4.9 Nested Mutable Objects

151. Can an immutable `User` contain a mutable `Address`?
152. Why is this potentially a violation of deep immutability?
153. Explain:

```text
Immutable User
      ↓
Mutable Address
      ↓
Mutable city state
```

154. Why does `final Address address` not solve the problem?
155. What is deep immutability?
156. What does transitive immutability mean?
157. How can an immutable object accidentally expose mutable nested state?
158. How would you redesign a mutable `Address` to support an immutable `User`?
159. When is copying the nested object appropriate?
160. When is replacing it with an immutable value type better?

## Advanced

161. Audit the complete reachable object graph of an immutable object.
162. Identify every path through which external code might mutate state.
163. Explain why deep immutability requires auditing collection elements too.
164. Can an immutable collection contain mutable elements?
165. Does an immutable collection guarantee deep immutability of its elements?
166. Design a deeply immutable object graph.

---

# 2.4.10 Shallow vs Deep Immutability

167. What is shallow immutability?
168. What is deep immutability?
169. Give a shallow immutable example.
170. Give a deeply immutable example.
171. Why is deep immutability stronger?
172. Explain:

```text
Object
 ├── final → mutable object
 └── final → mutable object
```

173. Explain:

```text
Immutable object
       ↓
immutable fields
       ↓
immutable referenced objects
       ↓
immutable nested state
```

174. When is shallow immutability sufficient?
175. When is deep immutability required?
176. How can API documentation clarify which level is promised?
177. How would you test deep immutability?

---

# 2.4.11 Immutable Collections

## Basic

178. What does `List.of()` provide?
179. What does `Set.of()` provide?
180. What does `Map.of()` provide?
181. What happens when you call `add()` on a list created with `List.of()`?
182. What exception is expected for an unsupported structural modification?
183. Why are immutable/unmodifiable collections useful inside immutable objects?

## Intermediate

184. What does `List.copyOf()` provide?
185. What does `Set.copyOf()` provide?
186. What does `Map.copyOf()` provide?
187. Compare `List.of()` with `List.copyOf()`.
188. Compare `Collections.unmodifiableList()` with `List.copyOf()`.
189. What is an unmodifiable view?
190. What is an immutable snapshot/value representation?
191. Why is view-versus-snapshot an important design distinction?
192. If the backing collection changes, what should you expect from a view?
193. If the backing collection changes, what should you expect from an immutable copy/value representation?
194. Which semantics are appropriate for an immutable domain object?

## Advanced

195. Explain why an immutable collection does not automatically make its elements immutable.
196. Design `List<MutableAddress>` safely inside an immutable object.
197. Why might copying the collection be insufficient for deep immutability?
198. How can immutable collection factories affect null handling?
199. How would you document collection ownership and mutability semantics?

---

# 2.4.12 Records and Immutability

## Basic

200. What is a Java record?
201. Why are records useful for data-oriented classes?
202. Do records automatically make all reachable state deeply immutable?
203. Why can a record containing a mutable collection still expose mutability?
204. Why can a record containing an array still expose mutability?
205. What is a compact constructor?
206. How can a compact constructor validate or normalize components?

## Intermediate

207. Create a record with an immutable `List` component.
208. Protect a record's mutable array component.
209. Use `List.copyOf()` inside a record constructor.
210. Use `clone()` for an array record component.
211. Demonstrate a record that is shallowly immutable but not deeply immutable.
212. Explain why record-generated accessors can be mutation leaks for mutable components.

## Advanced

213. When are records appropriate for immutable DTOs?
214. When should a record be avoided because its components have complex mutability semantics?
215. How can compact constructors enforce invariants?
216. How can records participate in an immutable architecture without being blindly treated as deeply immutable?
217. How would you review a record for deep immutability?

---

# 2.4.13 Immutability and Hash-Based Collections

218. Why are immutable objects excellent HashMap keys?
219. What happens conceptually when a mutable key changes after insertion?
220. Explain:

```text
insert key
   ↓
mutate key
   ↓
hashCode changes
   ↓
lookup may fail
```

221. Why must fields used by `equals()` and `hashCode()` remain stable while an object is a hash key?
222. How does immutability provide that stability?
223. Design an immutable key object.
224. Write `equals()` and `hashCode()` for an immutable value object.
225. What production bugs can result from mutable hash keys?
226. How would you debug a HashMap lookup failure caused by key mutation?

## Advanced

227. Explain the relationship:

```text
immutable state
     ↓
stable equals
     ↓
stable hashCode
     ↓
predictable HashMap/HashSet behavior
```

228. Can a mutable object be safely used as a hash key?
229. Under what strict condition might it work temporarily?
230. Why is immutable design safer than relying on discipline around mutation?
231. How does immutability simplify caching keys?

---

# 2.4.14 Immutability and Caching

232. Why do immutable objects work well as cache values?
233. Why do immutable objects work well as cache keys?
234. What examples of cacheable immutable values are given by the guide?
235. How does configuration benefit from immutability?
236. How do value objects benefit from immutability?
237. How can parsed metadata benefit from immutability?
238. How can request descriptors benefit from immutability?
239. Why can immutable objects still consume significant memory?
240. Why should excessive immutable-object creation still be measured?
241. Design an immutable cache key.
242. Design an immutable cached configuration snapshot.

---

# 2.4.15 Immutability and Sharing

243. Why can immutable objects be shared across threads?
244. Explain:

```text
Thread A ─┐
Thread B ─┼──> Immutable object
Thread C ─┘
```

245. Why is no synchronization required merely to protect immutable state from mutation?
246. Compare sharing an immutable object with sharing a mutable object.
247. Why does mutable sharing require coordination?
248. How can immutability reduce race-condition opportunities?
249. Does immutability eliminate every concurrency problem?
250. Why is immutability not a substitute for synchronization around mutable state elsewhere?
251. Design a request context that can safely be shared.
252. Design immutable configuration for multiple application threads.

---

# 2.4.16 Java Memory Model

253. What are final-field semantics?
254. Why are final fields relevant to immutable objects?
255. Explain:

```text
constructor
    ↓
final field initialization
    ↓
object publication
    ↓
other threads
```

256. What is safe publication?
257. What is a happens-before relationship?
258. What is a data race?
259. Why does immutable design simplify visibility reasoning?
260. Why does final-field semantics not make mutable nested objects safe?
261. Explain the difference between a final reference and an immutable referenced object.
262. Why should partially initialized immutable objects never be published?
263. How can leaking `this` during construction undermine design assumptions?
264. How would you review immutable construction for safe publication?

---

# 2.4.17 Immutability vs Mutability

265. Compare immutable and mutable objects in terms of state changes.
266. Compare thread sharing.
267. Compare defensive copying.
268. Compare caching.
269. Compare suitability as HashMap keys.
270. Compare object replacement versus in-place mutation.
271. Compare large-data update costs.
272. Compare reasoning complexity.
273. When is mutability preferable?
274. When is immutability preferable?
275. Why is there no universal rule that immutable is always faster?
276. Why can immutable transformations create new allocations?
277. Why can mutable updates be cheaper for large frequently changing structures?

---

# 2.4.18 Performance Implications

## Basic

278. What performance benefits can immutability provide?
279. How can immutability reduce synchronization?
280. How can immutability improve caching?
281. How can stable hash codes help?
282. What costs can defensive copying introduce?
283. Why can immutable transformations allocate new objects?
284. Why can copying large collections be expensive?
285. Why can array copying be expensive?

## Intermediate

286. Compare:

```java
object.setValue(...)
```

with:

```java
object = object.withValue(...)
```

287. What measurements should be collected?
288. Why measure allocation rate?
289. Why measure GC activity?
290. Why measure throughput?
291. Why measure CPU?
292. Why measure memory usage?
293. Why measure thread contention?
294. Why measure code complexity?

## Advanced

295. Design a benchmark comparing mutable and immutable data models.
296. How can defensive copying dominate the cost of an otherwise simple operation?
297. When could immutability reduce total system cost despite higher allocation?
298. When could mutability be the better performance choice?
299. Why should optimization be workload-driven?
300. Why should allocation assumptions be verified with profiling?

---

# 2.4.19 Major Traps

301. Why does `final` not mean immutable?
302. Why is `final List<String>` still mutable?
303. Why is returning an internal mutable collection dangerous?
304. Why is storing caller-owned mutable input dangerous?
305. Why are arrays a classic trap?
306. Why are records not automatically deeply immutable?
307. Why are unmodifiable views not necessarily immutable snapshots?
308. Why can immutable collections contain mutable elements?
309. Why can mutable subclasses undermine immutability?
310. Why are mutable nested objects dangerous?
311. Why can partially initialized publication be dangerous?
312. Why is shallow immutability sometimes insufficient?
313. Why can excessive defensive copying hurt performance?
314. Why does immutability not automatically solve every concurrency problem?
315. Why can an immutable root still expose mutable reachable state?
316. Why can `private final` create false confidence in code review?

---

# 2.4.20 Edge Cases

317. What should happen when an immutable constructor receives null?
318. When should `Objects.requireNonNull()` be used?
319. Should an immutable constructor normalize null to an empty collection?
320. What domain semantics should drive that decision?
321. What happens when `List.copyOf()` receives a collection containing null?
322. What happens when a record contains an array?
323. What happens when an immutable class contains a mutable element inside an immutable collection?
324. What happens when a defensive copy is made but the elements remain mutable?
325. What happens when a getter returns a new collection but its elements are mutable?
326. What happens when a nested object is final but internally mutable?
327. What happens if `equals()`/`hashCode()` include mutable state?
328. What happens if an immutable object is constructed from a collection that is modified concurrently?
329. What happens if an immutable object leaks a mutable builder or buffer?
330. What happens if a supposedly immutable object exposes an iterator over mutable internal state?

---

# 2.4.21 Debugging Challenges

## Challenge 1 — Final Reference Trap

331. Debug:

```java
final class User {
    private final List<String> roles;

    User(List<String> roles) {
        this.roles = roles;
    }

    List<String> roles() {
        return roles;
    }
}
```

332. Show how a caller can mutate `User`.
333. Fix constructor ownership.
334. Fix getter exposure.
335. Write a mutation test proving the fix.

## Challenge 2 — Array Leak

336. Debug an immutable class containing `byte[]`.
337. Demonstrate mutation through the constructor argument.
338. Demonstrate mutation through the getter.
339. Fix both boundaries.
340. Explain whether cloning the array is shallow or deep.

## Challenge 3 — Nested Mutable Object

341. Debug an immutable `User` containing mutable `Address`.
342. Demonstrate external mutation of the address.
343. Decide between copying and redesigning `Address`.
344. Implement a deeply immutable solution.

## Challenge 4 — Record Trap

345. Debug a record containing `List<String>`.
346. Demonstrate mutation of the component.
347. Protect the component.
348. Explain why the record syntax alone was insufficient.

## Challenge 5 — Unmodifiable View vs Snapshot

349. Compare:

```java
Collections.unmodifiableList(list)
```

with:

```java
List.copyOf(list)
```

350. Mutate the original list and observe the semantic difference.
351. Decide which representation is appropriate for an immutable domain object.

## Challenge 6 — Mutable Hash Key

352. Build a mutable key whose `hashCode()` changes.
353. Insert it into a HashMap.
354. Mutate the key.
355. Attempt lookup.
356. Explain the failure.
357. Replace the key with an immutable value object.

## Challenge 7 — Defensive Copy Performance

358. Create a large collection model.
359. Compare defensive-copy strategies.
360. Measure allocation and throughput.
361. Decide whether the copy cost is acceptable.
362. Document the safety/performance trade-off.

## Challenge 8 — Publication

363. Construct an immutable object incorrectly and publish it before construction completes.
364. Identify the design flaw.
365. Explain why construction should establish complete state.
366. Redesign safe construction/publication.

---

# 2.4.22 Implementation Exercises

## Basic

367. Implement an immutable `Employee`.
368. Implement an immutable `UserId`.
369. Implement an immutable `Account`.
370. Implement an immutable `Money`.
371. Add constructor validation.
372. Add `equals()` and `hashCode()`.
373. Remove setters.
374. Make the class final.
375. Make fields private and final.

## Intermediate

376. Build an immutable class containing a `List`.
377. Add defensive copying.
378. Build an immutable class containing a `Map`.
379. Build an immutable class containing an array.
380. Compare `Collections.unmodifiableList()` and `List.copyOf()`.
381. Create a record with immutable collection components.
382. Create a record containing an array and protect it.
383. Use an immutable object as a HashMap key.
384. Write tests that attempt mutation through every public API.
385. Write tests that mutate original constructor inputs after construction.

## Advanced

386. Build a deeply immutable object graph.
387. Design an immutable money/value object.
388. Design an immutable configuration object.
389. Implement immutable pagination parameters.
390. Build an immutable request context.
391. Benchmark defensive copies for large collections.
392. Analyze object allocation caused by immutable transformations.
393. Investigate final-field semantics in the Java Memory Model.
394. Build immutable DTOs for a REST-style API.
395. Design immutable snapshots for configuration reloads.

---

# 2.4.23 Design Questions

396. Design an immutable domain object for an order.
397. Decide which fields should be primitive, immutable reference types, or defensive copies.
398. Decide how to represent optional collections.
399. Decide how to represent nested addresses.
400. Decide whether to use records.
401. Decide whether to use `List.copyOf()`.
402. Decide whether arrays should be exposed at all.
403. Decide how to implement `withXxx()` methods.
404. Decide how equality should work.
405. Decide which fields participate in `equals()`/`hashCode()`.
406. Decide how the object can safely be used as a HashMap key.
407. Decide how it can safely be shared between threads.

## Configuration Design

408. Design an immutable configuration system.
409. Validate all values during construction.
410. Normalize configuration during binding.
411. Protect nested collections.
412. Protect mutable framework objects.
413. Provide immutable snapshots.
414. Allow safe sharing across threads.
415. Add tests proving external mutation cannot alter configuration.
416. Document memory/copying trade-offs.

---

# 2.4.24 Production Architecture

417. Explain this architecture:

```text
External Input
      ↓
Validation
      ↓
Normalization
      ↓
Immutable Domain Object
      ↓
Shared Across Services/Threads
      ↓
No mutation
```

418. Why should validation occur before immutable construction?
419. Why should normalization occur before sharing?
420. Why should immutable objects be the boundary after validation?
421. How does this reduce accidental state changes?
422. Design a configuration pipeline:

```text
Configuration files
        ↓
Binding
        ↓
Validation
        ↓
Immutable configuration
        ↓
Application components
```

423. Where should mutable parsing/binding state stop?
424. Where should immutable state begin?
425. How would you design immutable snapshots for configuration refresh?
426. How would you prevent old mutable configuration objects from leaking?

---

# 2.4.25 REST/API Model Design

427. How can mutable DTO fields leak state?
428. Why can returning a mutable collection from an API model be dangerous?
429. How should collection components be protected?
430. How should array components be protected?
431. When is a record appropriate for an API model?
432. Why should records not be blindly treated as deeply immutable?
433. How would you normalize incoming API data into immutable domain objects?
434. Where should validation occur?
435. Where should defensive copying occur?
436. How would you prevent callers from modifying internal state?
437. How would you document null/empty collection semantics?

---

# 2.4.26 Production Review Checklist

438. Is the class final or otherwise extension-safe?
439. Are fields private?
440. Are fields final?
441. Is construction complete and validated?
442. Are mutable inputs copied?
443. Are mutable outputs protected?
444. Are arrays cloned?
445. Are nested objects immutable?
446. Are collection elements immutable when required?
447. Are records being mistaken for deep immutability?
448. Are unmodifiable views being confused with snapshots?
449. Is safe publication considered?
450. Are `equals()`/`hashCode()` fields stable?
451. Are copying costs acceptable?
452. Has the design been tested against mutation attempts?
453. Have all reachable mutable references been audited?
454. Are null semantics explicit?
455. Is the immutability guarantee documented?

---

# 2.4.27 Interview — Basic

456. What is immutability?
457. Why is immutability useful?
458. How do you create an immutable class?
459. Why are fields usually private and final?
460. Why is the class often final?
461. What is defensive copying?
462. Why should immutable objects not have setters?
463. What is the difference between mutable and immutable objects?
464. Why are immutable objects useful for sharing?
465. Why are immutable objects useful as map keys?

---

# 2.4.28 Interview — Intermediate

466. Does `final` make an object immutable?
467. Why is `final List<String>` still mutable?
468. Why must arrays be defensively copied?
469. What is the difference between unmodifiable and immutable collections?
470. What is the difference between `Collections.unmodifiableList()` and `List.copyOf()`?
471. Are Java records immutable?
472. Why are immutable objects good map keys?
473. Can an immutable object contain mutable objects?
474. What is shallow immutability?
475. What is deep immutability?
476. Why should constructor arguments sometimes be copied?
477. Why should getter results sometimes be copied?

---

# 2.4.29 Interview — Advanced

478. Explain shallow versus deep immutability.
479. Explain defensive copying on input and output.
480. Explain record immutability limitations.
481. Explain final-field semantics under the Java Memory Model.
482. Explain safe publication.
483. Explain why immutability simplifies concurrency.
484. Explain mutable nested state.
485. Explain performance costs of defensive copying.
486. Explain why immutable collections do not guarantee immutable elements.
487. Explain why final references do not imply immutable referenced objects.
488. Explain the relationship between immutable state and stable hash codes.

---

# 2.4.30 Interview — Senior / Production

489. How would you design an immutable domain object?
490. When should a mutable object be preferred?
491. How would you make a large configuration object immutable?
492. How would you prevent mutable state leaking through a REST API model?
493. How would you detect accidental mutability in code review?
494. How would you balance defensive-copy cost against safety?
495. How does immutability affect GC pressure?
496. How would you design immutable objects for high-concurrency systems?
497. When are records appropriate for immutable DTOs?
498. How would you guarantee deep immutability across a complex object graph?
499. How would you diagnose a production bug caused by a mutable object being used as a cache key?
500. How would you review an immutable class containing collections and arrays?
501. How would you explain the trade-off between safety and allocation to a senior engineer?

---

# 2.4.31 Java Memory Model — Advanced Follow-Ups

502. Explain final-field semantics in practical terms.
503. Explain safe publication of an immutable object.
504. Explain happens-before relationships relevant to publication.
505. Explain why data races are harder to reason about with mutable shared state.
506. Explain why immutable state reduces the number of synchronization requirements.
507. Explain why a final reference to a mutable object remains a concurrency concern.
508. Explain how mutable nested state can invalidate assumptions about immutable roots.
509. Explain why constructor completion matters.
510. Explain why leaking references during construction is dangerous.
511. Explain the relationship between immutable construction and visibility guarantees.

---

# 2.4.32 Advanced Object-Graph Audit

512. Given an immutable root, enumerate every reachable object.
513. Mark every node as immutable or mutable.
514. Identify every alias that external code can retain.
515. Identify every mutable collection.
516. Identify every mutable array.
517. Identify every mutable collection element.
518. Identify every getter that exposes internal state.
519. Identify every constructor argument that can retain caller ownership.
520. Determine whether the graph is shallowly or deeply immutable.
521. Redesign the graph for deep immutability.
522. Write tests proving no mutation path remains.

---

# 2.4.33 Performance Investigation

523. Build Version A using mutable setters.
524. Build Version B using immutable `withXxx()` transformations.
525. Measure allocation rate.
526. Measure GC activity.
527. Measure throughput.
528. Measure CPU.
529. Measure memory usage.
530. Measure thread contention.
531. Measure code complexity.
532. Explain which workload characteristics favor each design.
533. Explain why the guide says not to assume mutable or immutable is universally faster.
534. Identify where defensive copying dominates the cost.
535. Decide whether optimization is justified from the measurements.

---

# 2.4.34 Production-Style Project

## Immutable Configuration System

536. Define the external configuration model.
537. Define validation rules.
538. Define normalization rules.
539. Build immutable configuration objects.
540. Protect all mutable collection inputs.
541. Protect all mutable array inputs.
542. Protect nested mutable objects.
543. Use records where appropriate.
544. Create immutable snapshots.
545. Ensure safe sharing across threads.
546. Write mutation-attempt tests.
547. Add equality/hash-code tests.
548. Add HashMap-key tests.
549. Add concurrent-read tests.
550. Measure memory and copying costs.
551. Document ownership and immutability guarantees.
552. Perform a production-style code review.
553. Identify every possible mutable-reference leak.
554. Explain the final design to a senior engineer.

---

# 2.4.35 Production Incident Scenarios

## Incident A — Cache Misses

555. A supposedly immutable key changes after being inserted into a HashMap. Diagnose it.
556. Determine which field was mutable.
557. Explain the hash-code consequence.
558. Redesign the key.

## Incident B — Configuration Changes Unexpectedly

559. A component modifies a collection obtained from configuration. Diagnose the leak.
560. Determine whether the configuration used a view or a snapshot.
561. Fix the ownership boundary.
562. Add a regression test.

## Incident C — Concurrent State Corruption

563. Multiple threads share an object assumed to be immutable.
564. Find the nested mutable reference.
565. Explain why final fields did not prevent mutation.
566. Redesign for deep immutability.

## Incident D — Latency Regression

567. A service becomes slower after introducing defensive copies.
568. Measure allocation and CPU.
569. Identify which copies are necessary.
570. Identify unnecessary copies.
571. Balance safety and performance.

## Incident E — Record Mutation Leak

572. A record exposes a mutable list.
573. Show how callers mutate it.
574. Fix the record.
575. Explain why record syntax did not guarantee deep immutability.

---

# 2.4.36 Advanced Reasoning

576. Why is immutability an API-level property?
577. Why can a class with only final fields still be mutable?
578. Why must input ownership be considered?
579. Why must output ownership be considered?
580. Why is deep immutability transitive?
581. Why do immutable objects work well with caching?
582. Why do immutable objects work well as HashMap keys?
583. Why does immutability simplify concurrent sharing?
584. Why does defensive copying increase allocation?
585. Why can immutable transformations be expensive for large structures?
586. Why can mutability sometimes be the better engineering choice?
587. Why should performance decisions be measured?
588. Why are records convenient but not a substitute for understanding mutability?
589. Why should view-versus-snapshot semantics be explicit?
590. Why does safe publication matter even when an object is conceptually immutable?

---

# 2.4.37 Mastery Drill

591. Explain immutability in 30 seconds.
592. Explain it in two minutes.
593. Implement an immutable class from scratch.
594. Explain why `final` does not imply immutability.
595. Demonstrate the final-reference trap.
596. Implement defensive copying.
597. Protect a mutable output.
598. Protect an array.
599. Protect a nested object.
600. Explain shallow versus deep immutability.
601. Explain `List.of()`.
602. Explain `List.copyOf()`.
603. Explain `Collections.unmodifiableList()`.
604. Explain view versus snapshot.
605. Explain records' immutability limitations.
606. Explain final-field semantics.
607. Explain safe publication.
608. Explain immutable sharing.
609. Explain HashMap-key stability.
610. Explain caching benefits.
611. Explain defensive-copy performance costs.
612. Debug a mutable-state leak.
613. Debug a mutable hash key.
614. Debug a record mutation leak.
615. Debug a concurrent mutation problem.
616. Design immutable configuration.
617. Explain all trade-offs to a senior engineer.

---

# 2.4.38 Final Integrated Challenge

Design and implement a **Production-Grade Immutable Configuration + Domain Model System**.

## Requirements

618. Accept external mutable input.
619. Validate all input during construction.
620. Normalize input before publication.
621. Create immutable domain objects.
622. Protect mutable collections using appropriate copy/immutable semantics.
623. Protect arrays.
624. Protect nested mutable objects.
625. Use records where they provide a clear benefit.
626. Ensure record components do not accidentally expose mutable state.
627. Implement stable `equals()` and `hashCode()`.
628. Use immutable objects as HashMap keys.
629. Create immutable configuration snapshots.
630. Share snapshots safely across threads.
631. Add mutation-attempt tests.
632. Add aliasing tests.
633. Add null/edge-case tests.
634. Add concurrent-read tests.
635. Benchmark mutable versus immutable update strategies.
636. Measure allocation, GC, CPU, throughput, memory, and contention.
637. Inspect the complete reachable object graph.
638. Prove whether the design is shallowly or deeply immutable.
639. Identify all defensive-copy boundaries.
640. Identify unnecessary defensive copies.
641. Document ownership semantics.
642. Document null/empty semantics.
643. Document record limitations.
644. Document safe-publication assumptions.
645. Perform a senior-level production code review.
646. Explain every design trade-off.

---

# 2.4.39 Final Mastery Gate

## Fundamentals

647. Explain immutability.
648. Explain why Java benefits from immutable objects.
649. Explain final classes.
650. Explain final fields.
651. Explain constructor initialization.
652. Explain immutable invariants.

## Defensive Copying

653. Explain defensive copying.
654. Protect mutable constructor arguments.
655. Protect mutable return values.
656. Protect arrays.
657. Protect collections.
658. Protect nested mutable objects.

## Collections

659. Explain immutable/unmodifiable collection semantics.
660. Explain `List.of()`.
661. Explain `List.copyOf()`.
662. Explain `Collections.unmodifiableList()`.
663. Distinguish view versus snapshot.
664. Explain shallow versus deep immutability.
665. Explain why collection elements may remain mutable.

## Records

666. Explain record-generated state.
667. Explain why records are not automatically deeply immutable.
668. Protect mutable record components.
669. Use compact constructors for normalization and validation.

## Concurrency

670. Explain why immutable objects are easier to share.
671. Explain final-field semantics.
672. Explain safe publication.
673. Explain happens-before relationships relevant to immutable publication.
674. Explain why immutability reduces synchronization requirements.
675. Explain why immutability does not solve every concurrency problem.

## Performance

676. Explain defensive-copy costs.
677. Explain allocation implications.
678. Explain GC implications.
679. Explain immutable transformation costs.
680. Measure rather than assume.
681. Compare mutable and immutable approaches using workload evidence.

## Production

682. Design an immutable value object.
683. Design immutable configuration.
684. Design immutable API models.
685. Prevent mutable state leakage.
686. Use immutable objects safely across threads.
687. Use immutable objects as map/set keys.
688. Explain trade-offs to senior engineers.

## Interview

689. Answer basic questions.
690. Answer intermediate questions.
691. Answer advanced questions.
692. Answer senior/production questions.
693. Explain why final does not mean immutable.
694. Explain records' immutability limitations.
695. Explain defensive copying.
696. Explain shallow versus deep immutability.
697. Explain immutability and the Java Memory Model.

---

# Final Module Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PERFORMANCE UNDERSTOOD
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
