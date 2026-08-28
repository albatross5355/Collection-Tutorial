# Module 2.5 — Object Contracts
## Deep Mastery Question Bank

> **Required flow:** Basic → Intermediate → Advanced → Traps → Design → Debugging Challenges → Production
>
> **Mastery standard:** Explain → Implement → Explain internals → Handle edge cases → Discuss trade-offs → Debug → Use in production.

This question bank is based on the Module 2.5 source guide covering `equals()`, `hashCode()`, `Comparable`, `compareTo()`, inheritance implications, hash-based collections, sorted collections, edge cases, debugging, performance, and production design. fileciteturn22file1L257-L284

---

## 1. Object Contracts — Foundations

1. What is an object contract in Java?
2. Why does Java need a consistent equality contract?
3. Why does Java need hashing?
4. Why does Java need ordering?
5. What is logical equality?
6. What is object identity?
7. What is the relationship between equals() and hashCode()?
8. What is the relationship between Comparable and compareTo()?
9. Which collections primarily depend on hashing?
10. Which collections primarily depend on ordering?
11. Why can contract violations appear to work in simple tests but fail in production?
12. Explain the complete chain: equals() → hashCode() → HashMap/HashSet.
13. Explain the complete chain: Comparable → compareTo() → TreeMap/TreeSet/sorting.
14. Why are object contracts important for caches and persistence-oriented code?
15. Design a checklist you would use before implementing equality or ordering.

## 2. equals() — Basic

16. What is equals()?
17. Where is equals() declared?
18. What is the signature of equals()?
19. What does Object.equals() effectively compare?
20. Why would a domain class override equals()?
21. When should two objects be considered logically equal?
22. What is the difference between == and equals() for references?
23. Give an example where two different instances should be equal.
24. Why is equals() important for value objects?
25. Why is equals() important for collections?
26. Why is equals() important for testing?
27. Why is equals() important for caching?
28. Why is equals() important for deduplication?
29. Implement equals() for a UserId value object.
30. Implement equals() for a Money value object.
31. Explain the this == obj fast path.
32. Explain why equals() accepts Object rather than the concrete type.
33. Use Objects.equals() for nullable equality-relevant fields.
34. Write tests proving two equal value objects can be different instances.
35. Write tests proving two different values are not equal.

## 3. equals() Contract — Reflexive, Symmetric, Transitive, Consistent, Null

36. State the reflexivity rule for equals().
37. Why must x.equals(x) be true?
38. Create an implementation that violates reflexivity and test it.
39. State the symmetry rule.
40. Why must x.equals(y) equal y.equals(x)?
41. Create a broken equality implementation that violates symmetry.
42. State the transitivity rule.
43. Why must x.equals(y) and y.equals(z) imply x.equals(z)?
44. Create a three-object test for transitivity.
45. State the consistency rule.
46. What does consistency mean when relevant state has not changed?
47. Why should external mutable state not unexpectedly influence equals()?
48. What should a non-null object's equals(null) return?
49. Why should equals(null) not throw NullPointerException?
50. Write the complete five-rule equality test suite.
51. Explain why these rules are required for predictable collection behavior.
52. Construct an equality implementation that passes pairwise tests but violates transitivity.
53. How can inconsistent normalization cause transitivity problems?
54. How can inheritance create symmetry problems?
55. How can mutable equality state break consistency over time?

## 4. equals() — Implementation and Type Strategy

56. Implement the modern instanceof-based equals() pattern for UserId.
57. When is Objects.equals(a,b) preferable to a.equals(b)?
58. What does instanceof do when its operand is null?
59. Compare getClass() == other.getClass() with instanceof in equals().
60. When does exact runtime-class equality make sense?
61. When can polymorphic equality be desirable?
62. What inheritance consequences follow from instanceof-based equality?
63. What inheritance consequences follow from getClass()-based equality?
64. Why is neither strategy universally correct?
65. Design equality for a final value object.
66. Design equality for an entity identified by database ID.
67. Design equality for a value object containing several components.
68. Decide which fields of User(id,name,createdAt) define equality when id is identity.
69. Explain why equality field selection is a domain-design decision.
70. Identify a field that should not participate in equality and justify the decision.
71. Review an IDE-generated equals() implementation against the domain semantics.
72. Explain why equality and hashCode must use the same equality state.
73. Write an equals() implementation with nullable fields.
74. Write tests for exact-class equality.
75. Write tests for instanceof equality.

## 5. equals() — Inheritance and Conceptual Traps

76. Construct the Money/Voucher symmetry trap.
77. Explain why Money.equals(Voucher) can differ from Voucher.equals(Money).
78. How can subclass state affect equality?
79. How can inheritance break transitivity?
80. Why do value objects often use final classes?
81. When should inheritance be avoided for equality-sensitive value objects?
82. Can a subclass safely add equality-relevant state to a superclass?
83. What questions must be answered before allowing superclass/subclass equality?
84. Review an equals() implementation that uses only instanceof.
85. Review an equals() implementation that uses only getClass().
86. Explain why 'extends compiles' does not imply equality semantics are correct.
87. Design an equality policy for an inheritance hierarchy before writing code.
88. Debug asymmetric equality from a production incident.
89. Write a regression test for the inheritance symmetry trap.
90. Explain why equality should be treated as part of an API contract.

## 6. hashCode() — Basic and Contract

91. What is hashCode()?
92. Where is hashCode() declared?
93. What does hashCode() represent conceptually?
94. Why do hash-based collections use hashCode()?
95. State the central hashCode() contract.
96. If x.equals(y) is true, what must be true about their hash codes?
97. Is the reverse implication required?
98. Can two unequal objects have the same hash code?
99. What is a hash collision?
100. Why are collisions allowed?
101. Why does hashCode() not make the final equality decision?
102. Implement hashCode() for UserId.
103. Implement hashCode() for a three-field value object.
104. Why should hashCode() use the same fields as equals()?
105. What happens if equals() ignores a field but hashCode() includes it?
106. What happens if equals() includes a field but hashCode() ignores it?
107. Why is overriding equals() without hashCode() a bug?
108. Explain how equal keys can land in different buckets when hashCode() is wrong.
109. Write a test asserting equal objects have equal hash codes.
110. Write a test proving equal hash codes do not imply equality.

## 7. HashMap / HashSet — Internals and Behavior

111. Explain the conceptual HashMap lookup flow.
112. Why is hashCode() evaluated before equals() during lookup?
113. What is hash spreading conceptually?
114. How is a bucket selected conceptually?
115. Why are candidate entries examined after bucket selection?
116. Why can identity be checked before equals()?
117. Explain how HashSet uses hash/equality semantics.
118. Why can a bad equality implementation produce duplicate logical values in HashSet?
119. Why can contains() fail with a broken contract?
120. Why can remove() fail with a broken contract?
121. Why can HashSet size become surprising?
122. Build a HashMap using UserId keys.
123. Build a HashSet using UserId values.
124. Remove hashCode() from UserId and observe the behavior.
125. Create two unequal objects with the same hash code.
126. Verify that a collision does not imply equality.
127. Explain why HashMap needs both hashing and equality.
128. Explain why HashSet needs both hashing and equality.
129. Trace put(key,value) and get(equalKey) step by step.
130. Trace add(x) and add(equalX) step by step.

## 8. Mutable Hash Keys — Major Production Trap

131. What is a mutable hash key?
132. Why is mutating equality-relevant state after HashMap insertion dangerous?
133. Trace a key before and after its id changes.
134. Why can map.get(key) fail after key mutation?
135. Why can remove(key) fail after key mutation?
136. Why can containsKey(key) fail after key mutation?
137. Build the mutable User key example from the module.
138. Predict the lookup result after mutating the key.
139. Explain old bucket placement versus new hash code.
140. Why does the map not automatically relocate an entry when a key mutates?
141. Why are immutable keys preferred?
142. Convert MutableUserKey into an immutable UserId.
143. Write a regression test preventing mutable-key corruption.
144. Find mutable equality fields during code review.
145. Design an API that prevents callers from changing key identity.
146. Explain the production impact of mutable keys on caches.
147. Explain the production impact of mutable keys on HashSet membership.
148. Explain why defensive copying may be necessary for key state.
149. Create a diagnostic checklist for a missing HashMap entry.
150. Reproduce mutable-key corruption in a minimal test.

## 9. Comparable and compareTo() — Basic

151. What is Comparable<T>?
152. What does implementing Comparable communicate?
153. What is natural ordering?
154. What is the compareTo() signature?
155. What does a negative compareTo() result mean?
156. What does zero mean?
157. What does a positive result mean?
158. Why must callers not depend on exactly -1, 0, or 1?
159. Why should code usually use compareTo() < 0 rather than == -1?
160. Implement Comparable<UserId>.
161. Implement Comparable<Employee> using age.
162. Sort a list using natural ordering.
163. Explain why natural ordering is useful for sorting.
164. List uses of natural ordering besides sorting.
165. How do TreeSet and TreeMap use ordering?
166. How do priority queues benefit from ordering?
167. Why should a natural ordering represent meaningful domain semantics?
168. How would you decide whether a class should implement Comparable?
169. Explain why not every domain class needs a natural ordering.
170. Test negative, zero, and positive compareTo() results.

## 10. Comparable vs Comparator

171. What is the difference between Comparable and Comparator?
172. Where does natural ordering live?
173. Where does an alternative ordering live?
174. Why can a class have one natural ordering but many application-specific orderings?
175. Implement a Comparator by user name.
176. Implement a Comparator by salary.
177. Implement multi-field ordering with thenComparing().
178. When should multiple orderings be represented with Comparator rather than Comparable?
179. How can a Comparator avoid forcing one business ordering on the domain type?
180. How should null ordering be made explicit?
181. What does Comparator.nullsFirst() do conceptually?
182. What does Comparator.nullsLast() do conceptually?
183. Design an Employee ordering by department, salary, then name.
184. Design a second ordering by name, then employee ID.
185. Decide which ordering, if any, should be natural.
186. Explain why ordering is part of collection semantics.
187. Review a Comparator for determinism.
188. Review a Comparator for side effects.
189. Review a Comparator for unnecessary work.
190. Explain when a custom Comparator is safer than changing Comparable.

## 11. compareTo() Contract and Mathematical Reasoning

191. State the sign-symmetry requirement for compareTo().
192. If x.compareTo(y) < 0, what should y.compareTo(x) indicate?
193. State transitivity for natural ordering.
194. Why must ordering be transitive?
195. What does compareTo() == 0 mean?
196. Why is compareTo() == 0 ordering equivalence?
197. Why is compareTo() == 0 not universally the same as equals()?
198. Create values that test transitivity.
199. Create values that test sign reversal.
200. Design property-style tests for compareTo().
201. Explain why inconsistent ordering can break sorted collections.
202. Why should comparison be deterministic?
203. Why should compareTo() be side-effect free?
204. Why should compareTo() not perform database calls?
205. Why should compareTo() not perform network calls?
206. Why should compareTo() avoid heavy computation?
207. Why should compareTo() avoid unnecessary allocation?
208. Review a natural ordering for transitivity.
209. Review a Comparator for consistency.
210. Explain ordering equivalence versus object equality.

## 12. Bad compareTo() — Overflow and Return-Value Traps

211. Why is return this.age - other.age a bad compareTo() implementation?
212. Construct an integer-overflow case for subtraction-based comparison.
213. Replace subtraction with Integer.compare().
214. Why is Long.compare() preferred for long values?
215. Why is compareTo() == -1 incorrect?
216. Why is compareTo() < 0 correct?
217. Why is compareTo() > 0 correct?
218. Why is compareTo() == 0 correct?
219. Build a test proving a valid compareTo() can return a value other than -1 or 1.
220. Debug a production sort that uses subtraction.
221. Debug an ordering bug caused by integer overflow.
222. Review a compareTo() that subtracts timestamps or IDs.
223. Implement safe comparison for multiple numeric fields.
224. Explain why overflow can violate intended ordering.
225. Write a static-review rule for detecting subtraction in compareTo().

## 13. Multi-Field Ordering

226. Implement lastName then firstName ordering.
227. Implement department then salary then name ordering.
228. Implement the same ordering manually with if statements.
229. Implement it using Comparator.comparing().thenComparing().
230. Why must later fields only be considered when earlier fields compare equal?
231. What happens if a tie-breaker is omitted?
232. Can compareTo() return zero for distinct domain objects?
233. When is that acceptable?
234. When can compareTo() == 0 create TreeSet surprises?
235. Design a natural ordering that includes a stable unique tie-breaker.
236. Design an ordering intentionally ignoring a field.
237. Explain the difference between sorting semantics and uniqueness semantics.
238. Test a three-level ordering.
239. Test ties at every level.
240. Test null values in one ordering component.
241. Explain why ordering requirements should be documented.

## 14. BigDecimal — Equality vs Ordering Trap

242. Create BigDecimal("1.0") and BigDecimal("1.00").
243. What does BigDecimal.equals() consider?
244. What does BigDecimal.compareTo() consider?
245. Why can equals() be false while compareTo() is zero?
246. Why is BigDecimal a famous example of inconsistent equality and natural ordering?
247. Test the two values with equals().
248. Test the two values with compareTo().
249. Insert both into a HashSet.
250. Insert both into a TreeSet.
251. Explain the different collection behavior.
252. Why can TreeSet deduplicate values that HashSet keeps distinct?
253. Why should domain developers know this exception?
254. Decide whether scale is business-significant for a Money domain.
255. Document an explicit Money equality policy.
256. Test HashSet and TreeSet against that policy.
257. Explain why consistency between equals() and compareTo() is recommended, not universal.
258. Identify another domain where representation and value might differ.

## 15. TreeSet / TreeMap — Ordering-Based Uniqueness

259. How does TreeSet determine element equivalence?
260. How does TreeMap determine key equivalence?
261. What role does Comparator play in TreeSet?
262. What role does compareTo() play in TreeSet?
263. What happens when compareTo() returns zero for two unequal objects?
264. What happens when Comparator.compare() returns zero for two unequal keys?
265. Why can TreeSet and HashSet have different notions of duplicate?
266. Why can TreeMap replace an apparently different key?
267. Trace TreeMap navigation conceptually.
268. Explain natural ordering versus custom ordering in TreeMap.
269. Build a TreeSet using a Comparator that ignores one field.
270. Observe ordering-based deduplication.
271. Build a TreeMap with an ordering inconsistent with equals().
272. Explain the resulting semantics.
273. Design an ordering that matches intended uniqueness.
274. Document when ordering intentionally differs from equality.
275. Debug a TreeSet that unexpectedly contains fewer values.
276. Debug a TreeMap lookup/replacement surprise.

## 16. Arrays, Collections, Nulls, and Floating-Point Edge Cases

277. Why does array.equals() not perform element-wise equality?
278. Compare two int arrays correctly using Arrays.equals().
279. When should Arrays.deepEquals() be used?
280. How should arrays participate in hashCode calculations?
281. When should Arrays.hashCode() be used?
282. When should Arrays.deepHashCode() be used?
283. Why do collection types have different equality semantics?
284. Explain List equality.
285. Explain Set equality.
286. Explain Map equality.
287. Which collection equality semantics are ordering-sensitive?
288. Which collection equality semantics are ordering-insensitive?
289. What does Objects.equals(a,b) do when both are null?
290. What does Objects.equals(a,b) do when only one is null?
291. What does Objects.equals(a,b) do when both are non-null?
292. How should null ordering be specified?
293. Explain Comparator.nullsFirst().
294. Explain Comparator.nullsLast().
295. What floating-point values require special equality attention?
296. What are NaN, +0.0, -0.0, +Infinity and -Infinity?
297. Compare primitive == with Double.equals() for special values.
298. Compare Double.compare() with primitive ==.
299. Design tests covering floating-point equality and ordering.

## 17. Conceptual / Theory Questions

300. Why is equality a domain decision rather than an IDE boilerplate decision?
301. How does entity equality differ from value-object equality?
302. When might an entity use a database ID as identity?
303. When might a business identifier define equality?
304. Why do value objects normally compare all value-defining components?
305. Why are immutable objects excellent equality/hash keys?
306. Why does stable state make hash-based behavior predictable?
307. Why should equality-relevant state normally be immutable?
308. Why can mutable equality state make collection behavior unstable?
309. Why can hash collisions not be treated as equality?
310. Why must hashCode() agree with equals() but not necessarily the reverse?
311. Why does TreeSet use ordering rather than equals() for uniqueness?
312. Why is natural ordering part of the type's semantics?
313. Why can a type need multiple Comparator strategies?
314. Why should comparison methods avoid side effects?
315. Why should object contracts be tested as properties rather than only examples?
316. Why can contract bugs remain hidden until production?
317. Why can a correct-looking implementation still violate symmetry?
318. Why can a correct-looking comparator still violate transitivity?
319. Explain object contracts as agreements between objects and generic collections.

## 18. Major Traps — Identify and Fix

320. Find the bug in overriding equals() without hashCode().
321. Find the bug in using == for value equality.
322. Find the bug in mutating a HashMap key.
323. Find the bug in compareTo() subtraction.
324. Find the bug in compareTo() == -1.
325. Find the bug in assuming compareTo() == 0 means equals() == true.
326. Find the BigDecimal trap.
327. Find the TreeSet/HashSet semantic difference.
328. Find a missing null check in equals().
329. Find asymmetric equality caused by inheritance.
330. Find inconsistent fields between equals() and hashCode().
331. Find array equality implemented with array.equals().
332. Find floating-point equality assumptions.
333. Find the mistake of treating equal hash codes as equal objects.
334. Find a Comparator that performs I/O.
335. Find a Comparator with mutable external state.
336. Find a Comparator that is not transitive.
337. Find a compareTo() implementation that allocates unnecessarily.
338. Find an equality implementation based on mutable collections.
339. Explain the production consequence of each trap.

## 19. Debugging Challenges

340. Debug a HashSet containing duplicate logical UserId values after hashCode() was omitted.
341. Debug a HashMap where get(equalKey) unexpectedly returns null.
342. Debug a mutable-key lookup failure.
343. Debug a HashSet contains() failure after object mutation.
344. Debug a HashSet remove() failure after object mutation.
345. Debug a TreeSet that removes one of two distinct values.
346. Debug a TreeMap that overwrites a key that equals() says is different.
347. Debug BigDecimal 1.0 versus 1.00 behavior.
348. Debug an equals() method that violates reflexivity.
349. Debug an equals() method that violates symmetry.
350. Debug an equals() method that violates transitivity.
351. Debug an equals() method that throws on null.
352. Debug an equality method whose result changes because external mutable state changed.
353. Debug getClass()-based equality in an inheritance hierarchy.
354. Debug instanceof-based equality in an inheritance hierarchy.
355. Debug an array field compared with equals().
356. Debug an array field hashed with the wrong hash function.
357. Debug Double equality around NaN and signed zero.
358. Debug compareTo() subtraction overflow.
359. Debug code that assumes compareTo() returns -1/0/1.
360. Create a minimal reproduction before proposing a fix.
361. Add a regression test after each fix.
362. Produce root cause, fix, test, and prevention notes for each incident.

## 20. Implementation Exercises

363. Implement immutable UserId with equals() and hashCode().
364. Implement immutable ProductId with equals() and hashCode().
365. Implement Money equality according to an explicitly documented domain rule.
366. Implement EmailAddress equality.
367. Implement DateRange equality.
368. Implement Coordinates equality.
369. Implement an entity whose equality uses business identity.
370. Implement a value object whose equality uses all components.
371. Implement array-aware equality and hashing.
372. Implement collection-aware equality and hashing.
373. Implement Comparable for Employee by employee ID.
374. Implement Comparable for Employee by age.
375. Implement a multi-field natural ordering.
376. Implement alternative Comparators for the same class.
377. Implement nullsFirst and nullsLast ordering.
378. Use thenComparing() for tie-breaking.
379. Use Integer.compare() safely.
380. Use Long.compare() safely.
381. Write contract tests for equals().
382. Write contract tests for hashCode().
383. Write contract tests for Comparable.
384. Build HashMap and HashSet demonstrations.
385. Build TreeMap and TreeSet demonstrations.
386. Build a collision demonstration.
387. Build a mutable-key failure demonstration.
388. Build a BigDecimal semantics demonstration.

## 21. Production / Design

389. Design an immutable ProductId value object for a microservice.
390. Design an immutable Money value object for pricing.
391. Define equality semantics for an Order entity.
392. Define equality semantics for an OrderItem value object.
393. Decide which fields participate in equality.
394. Decide which fields must remain immutable.
395. Design safe HashMap keys.
396. Design safe HashSet elements.
397. Design natural ordering only where it has domain meaning.
398. Design multiple alternative orderings using Comparator.
399. Document equality semantics for an enterprise domain type.
400. Document ordering semantics for an enterprise domain type.
401. Review a cache key for mutable identity state.
402. Review a DTO used as a HashMap key.
403. Review an ORM/domain entity for equality risks.
404. Explain how persistence identity can differ from value equality.
405. Design a high-throughput service's object contracts.
406. Prevent database/network work inside compareTo().
407. Prevent unnecessary allocation in equals()/hashCode()/compareTo().
408. Decide whether hashCode() should use Objects.hash() as a baseline.
409. Analyze whether specialized hash logic is justified by measured workload.
410. Design regression tests for collection behavior.
411. Design monitoring/diagnostics for a production lookup failure.

## 22. Performance

412. Why can equals() be a hot path?
413. Why can hashCode() be a hot path?
414. Why can compareTo() be a hot path?
415. What performance cost can deep equality traversal introduce?
416. What performance cost can large collection equality introduce?
417. What performance cost can array equality introduce?
418. What performance cost can repeated hashing introduce?
419. How can poor hash distribution hurt performance?
420. Why do collisions increase equality checks?
421. Why should equals() avoid unnecessary allocation?
422. Why should hashCode() avoid unnecessary allocation?
423. Why should compareTo() avoid unnecessary allocation?
424. Why should compareTo() avoid database access?
425. Why should compareTo() avoid network access?
426. How would you benchmark equality cost?
427. How would you benchmark hash distribution?
428. How would you benchmark ordering?
429. Why is a simple System.nanoTime() benchmark insufficient for strong claims?
430. When should JMH be used?
431. How would a profiler help identify contract-method hotspots?
432. How would you decide whether custom hash optimization is justified?

## 23. Testing the Equality Contract

433. Write a reflexivity test.
434. Write a symmetry test.
435. Write a transitivity test.
436. Write a consistency test.
437. Write a null test.
438. Write the equal-hash test.
439. Write a test for unequal objects sharing a hash code.
440. Write a HashSet uniqueness test.
441. Write a HashMap lookup test using an equal but distinct key.
442. Write a test that fails when hashCode() is removed.
443. Write a mutable-key regression test.
444. Write an inheritance equality test.
445. Write getClass()-based equality tests.
446. Write instanceof-based equality tests.
447. Write array equality tests.
448. Write nested-array equality tests.
449. Write floating-point equality tests.
450. Write compareTo() sign-symmetry tests.
451. Write compareTo() transitivity tests.
452. Write compareTo()==0 versus equals() tests.
453. Write BigDecimal HashSet/TreeSet tests.
454. Build property-based tests for equality.
455. Build property-based tests for ordering.

## 24. Advanced Follow-Ups

456. Explain the full equals() → hashCode() → HashMap/HashSet relationship.
457. Explain the full Comparable → compareTo() → TreeMap/TreeSet relationship.
458. Explain why HashMap needs hashCode() and equals().
459. Explain why TreeMap needs ordering rather than equals().
460. Explain why HashSet and TreeSet can disagree about duplicates.
461. Explain why TreeMap and HashMap can disagree about key equivalence.
462. Explain equality field selection for entities.
463. Explain equality field selection for value objects.
464. Explain immutable keys as a design strategy.
465. Explain mutable-key corruption in terms of bucket selection.
466. Explain hash collisions in terms of candidate comparison.
467. Explain hash distribution and performance.
468. Explain getClass() versus instanceof in equality.
469. Explain inheritance symmetry failures.
470. Explain BigDecimal consistency exception.
471. Explain null ordering.
472. Explain floating-point edge cases.
473. Explain array equality and hashing.
474. Explain collection equality semantics.
475. Explain why contract methods should be deterministic.
476. Explain why contract methods should be side-effect free.

## 25. Production Review Checklists

477. Before approving equals(), identify logical identity.
478. Before approving equals(), verify reflexivity.
479. Before approving equals(), verify symmetry.
480. Before approving equals(), verify transitivity.
481. Before approving equals(), verify consistency.
482. Before approving equals(), verify null behavior.
483. Before approving hashCode(), verify the equality-state fields match.
484. Verify equality fields are immutable.
485. Verify the object is safe as a map/set key.
486. Verify inheritance equality semantics are deliberate.
487. Verify array fields use array-aware equality.
488. Verify floating-point edge cases are understood.
489. Verify equality performance is acceptable.
490. Before approving compareTo(), define ordering explicitly.
491. Verify ordering transitivity.
492. Verify sign symmetry.
493. Verify overflow is impossible.
494. Verify null behavior is explicit.
495. Verify ordering is deterministic.
496. Verify compareTo()==0 semantics are understood.
497. Verify multiple orderings use Comparator where appropriate.
498. Verify compareTo()/Comparator.compare() has no expensive side effects.
499. Verify TreeMap/TreeSet semantics are understood.

## 26. Production Debugging Workflow

500. When map.get(key) unexpectedly returns null, check equality first.
501. Then check hashCode().
502. Then check whether the key mutated.
503. Then check class/type equality.
504. Then inspect collisions/distribution if relevant.
505. Then verify a different instance is logically equal.
506. Reproduce the problem with a minimal HashMap test.
507. When TreeSet unexpectedly deduplicates values, inspect compareTo().
508. Inspect the Comparator.
509. Check compare()==0 cases.
510. Compare ordering with equals().
511. Check whether the ordering matches intended uniqueness.
512. Produce root cause and regression test.
513. Add a production prevention strategy.
514. Create a reusable object-contract incident checklist.

## 27. Interview — Basic

515. What is equals()?
516. What is hashCode()?
517. Why override equals()?
518. Why override hashCode() with equals()?
519. What is reflexivity?
520. What is symmetry?
521. What is transitivity?
522. What is consistency?
523. What should equals(null) return?
524. What is compareTo()?
525. What does negative/zero/positive compareTo() mean?
526. What is Comparable?
527. What is natural ordering?
528. What is Comparator?
529. What is a hash collision?
530. Why are immutable keys useful?

## 28. Interview — Intermediate

531. What happens if equals() is overridden but hashCode() is not?
532. Can unequal objects have the same hash code?
533. Why is == wrong for logical object equality?
534. Why shouldn't compareTo() use subtraction?
535. Why shouldn't code check compareTo()==-1?
536. How does HashMap use hashCode() and equals()?
537. How does HashSet determine duplicates?
538. What happens when a HashMap key is mutated?
539. Why is an immutable value object a good key?
540. Comparable vs Comparator?
541. What is compareTo()==0?
542. Why can TreeSet differ from HashSet?
543. Why can BigDecimal.equals() differ from compareTo()?
544. What is the difference between getClass() and instanceof in equals()?
545. Why are arrays special for equality?
546. How should null ordering be defined?

## 29. Interview — Advanced

547. Explain the complete HashMap lookup flow.
548. Explain hash spreading and bucket selection conceptually.
549. Explain hash collision handling.
550. Explain why equal objects must have equal hashes.
551. Explain mutable-key corruption.
552. Explain HashSet uniqueness.
553. Explain TreeSet ordering-based uniqueness.
554. Explain TreeMap comparator-based key equivalence.
555. Explain equality symmetry problems caused by inheritance.
556. Explain getClass() versus instanceof equality.
557. Explain entity versus value-object equality.
558. Explain BigDecimal equality versus ordering.
559. Explain compareTo() transitivity and sign symmetry.
560. Explain array equality and hashing.
561. Explain floating-point equality edge cases.
562. Explain null ordering.
563. Explain performance considerations for contract methods.
564. Explain why consistency between equals() and compareTo() is recommended but not universal.

## 30. Interview — Senior / Production

565. How would you define equality for a domain entity?
566. How would you define equality for a value object?
567. Which fields should participate in equals() and hashCode()?
568. How would you design immutable map keys?
569. How would you review an equality implementation?
570. How would you systematically test an equality contract?
571. How would you diagnose HashMap.get() returning null for an apparently present key?
572. How would you diagnose duplicate values in HashSet?
573. How would you design ordering for a type with multiple valid sort orders?
574. When should natural ordering intentionally differ from equality?
575. How would you handle equality in an inheritance hierarchy?
576. How would you design object contracts for a high-throughput service?
577. How would you prevent mutable-key bugs in code review?
578. How would you document TreeSet semantics to consumers?
579. How would you decide whether custom hash optimization is justified?
580. How would you investigate equality-related production latency?
581. How would you explain object contracts to a senior engineering team?

## 31. Integrated Production Project

582. Build an Immutable Domain Identity and Pricing Model.
583. Implement ProductId as a value object.
584. Implement Money as a value object.
585. Define and document equality semantics.
586. Implement matching hashCode().
587. Make equality state immutable.
588. Implement natural ordering where meaningful.
589. Provide alternative Comparators.
590. Use ProductId safely in HashMap.
591. Use ProductId safely in HashSet.
592. Use Money safely in HashMap/HashSet.
593. Test TreeMap behavior.
594. Test TreeSet behavior.
595. Test null and boundary values.
596. Test BigDecimal semantics if Money uses BigDecimal.
597. Test array fields if any domain object contains arrays.
598. Add equality contract tests.
599. Add ordering contract tests.
600. Add mutable-key failure demonstration and prevention.
601. Profile contract methods if the project becomes large.
602. Document production trade-offs.
603. Perform a senior-level code review.
604. Produce a root-cause guide for lookup failures.
605. Explain every design decision.

## 32. Final Mastery Gate

606. Explain logical equality.
607. Explain identity versus equality.
608. Implement equals().
609. Explain reflexivity.
610. Explain symmetry.
611. Explain transitivity.
612. Explain consistency.
613. Handle null correctly.
614. Handle inheritance deliberately.
615. Handle arrays correctly.
616. Explain the hashCode contract.
617. Implement matching hashCode().
618. Explain collisions.
619. Explain HashMap lookup.
620. Explain HashSet uniqueness.
621. Debug a missing-hashCode bug.
622. Debug a mutable-key bug.
623. Explain hash distribution.
624. Explain natural ordering.
625. Implement Comparable.
626. Implement compareTo().
627. Explain negative/zero/positive semantics.
628. Avoid subtraction overflow.
629. Handle multiple fields.
630. Understand null ordering.
631. Explain equals() versus compareTo().
632. Explain consistency between them.
633. Explain why consistency is recommended but not universal.
634. Explain the BigDecimal example.
635. Explain TreeSet/TreeMap semantics.
636. Explain HashSet/HashMap semantics.
637. Explain hash distribution.
638. Explain equality cost.
639. Explain comparison cost.
640. Avoid allocation in hot contract methods.
641. Measure behavior where necessary.
642. Design a correct immutable value object.
643. Safely use it as a HashMap key.
644. Safely use it in HashSet.
645. Define natural ordering where appropriate.
646. Provide alternative Comparators.
647. Diagnose collection lookup failures.
648. Diagnose TreeSet/TreeMap surprises.
649. Explain entity versus value-object equality.
650. Answer basic questions.
651. Answer intermediate questions.
652. Answer advanced questions.
653. Answer senior/production questions.

---

## Final Module Status

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