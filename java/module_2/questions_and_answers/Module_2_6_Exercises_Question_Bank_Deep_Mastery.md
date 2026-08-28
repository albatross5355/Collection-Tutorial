# Module 2.6 — Exercises
## Deep Mastery Question Bank

> **Goal:** Convert Modules 2.1–2.5 from theoretical knowledge into implementation, debugging, and production-ready skill.
>
> **Mastery standard:** Predict → Implement → Explain → Inspect Internals → Debug → Optimize → Handle Edge Cases → Apply in Production.

The questions below are derived from the Module 2.6 exercise guide, preserving its progression from hands-on implementation through debugging, performance, OpenJDK investigation, interview drills, and the final Employee Directory challenge. fileciteturn29file0L11-L50

---

## 1. Exercise Mastery Cycle & Execution Discipline

1. State the goal of Module 2.6.
2. Which earlier modules does Module 2.6 convert from theory into practical skill?
3. List every major topic covered by the exercise module.
4. Why does the module require prediction before running code?
5. Why should you implement an exercise instead of only reading its solution?
6. Why must you explain why an implementation works?
7. Why should internal behavior be explained after implementation?
8. Why are edge cases part of exercise completion?
9. Why should common mistakes be identified explicitly?
10. Why should performance be analyzed even for small exercises?
11. Why should deliberately broken versions be debugged?
12. Why should production implications be discussed?
13. Why are related interview questions included?
14. Why are advanced follow-ups included?
15. State the module's completion standard.
16. Explain the difference between Predict → Implement → Explain and simply coding until tests pass.
17. Create a personal checklist that follows the module's mastery cycle.
18. Explain why implementation skill cannot be inferred from theoretical knowledge alone.
19. Explain why debugging broken code is a separate skill from writing correct code.
20. Explain how the final integrated challenge validates multiple earlier topics simultaneously.

## 2.6.1 Implement an Immutable Employee — Basic Design

21. Create an Employee with id, name, department, and salary.
22. Why should the Employee class be final in the exercise?
23. Why should Employee fields be private?
24. Why should the Employee fields be final?
25. Why should all fields be initialized through the constructor?
26. Why should Employee have no setters?
27. Which accessors should the class expose?
28. Why is constructor-based initialization important for immutability?
29. Which Employee fields are inherently immutable in the starter design?
30. Why is String appropriate for name and department in the immutability exercise?
31. Why does the module initially use double for salary and later ask you to reconsider it?
32. Write the Employee class from scratch without looking at the starter.
33. Write a constructor for all four fields.
34. Write getters for all four fields.
35. Create several Employee instances and verify their state cannot be reassigned through setters.
36. Attempt to add a setter and explain exactly why it violates the intended design.
37. Explain what final means for a primitive field.
38. Explain what final means for a reference field.
39. Distinguish final reference from immutable referenced object.
40. Explain whether making the class final alone makes Employee immutable.
41. Explain whether private fields alone make Employee immutable.
42. Explain whether getters alone make Employee immutable.
43. Create a test that attempts every obvious mutation route.
44. Design a second immutable Employee implementation using a record.
45. Compare the traditional final class design with the record design.

## 2.6.1 Employee — Validation

46. Make Employee id strictly positive.
47. Reject a null name.
48. Reject a null department.
49. Reject a negative salary.
50. Decide whether blank names should be allowed.
51. Decide whether blank departments should be allowed.
52. What exception should invalid constructor input produce?
53. Why should validation happen during construction?
54. Why is constructor validation especially important for immutable objects?
55. Can an immutable object safely expose an invalid state if validation is deferred?
56. Design validation for id.
57. Design validation for name.
58. Design validation for department.
59. Design validation for salary.
60. Test id = 0.
61. Test id < 0.
62. Test null name.
63. Test null department.
64. Test salary = 0.
65. Test salary < 0.
66. Test empty name.
67. Test whitespace-only name.
68. Test empty department.
69. Document your blank-string policy.
70. Explain why validation policy is part of object design rather than merely defensive programming.
71. Decide whether salary validation should use double or BigDecimal in the production version.
72. Write constructor tests for every invalid input.
73. Write tests for the valid boundary values.
74. Explain what invariant the constructor establishes.
75. Explain why an immutable object's invariant should hold immediately after construction.

## 2.6.1 Employee — Mutable Field & Defensive Copying

76. Add List<String> skills to Employee.
77. Initially assign skills directly with this.skills = skills.
78. Create an external mutable list and pass it into Employee.
79. Mutate the external list after Employee construction.
80. Predict whether Employee's skills change.
81. Run the experiment and explain the result.
82. Does final protect the list object?
83. Explain reference immutability versus object immutability.
84. Draw the reference relationship between external skills and Employee.skills.
85. Why is direct reference assignment dangerous in an immutable object?
86. Fix the constructor using List.copyOf(skills).
87. Verify that changing the original list no longer changes Employee.
88. Test adding an element to the original list after construction.
89. Test removing an element from the original list after construction.
90. Test clearing the original list after construction.
91. Test replacing the original list reference.
92. Why does List.copyOf() solve the external-list mutation problem?
93. Test whether employee.getSkills().add("Docker") can mutate internal state.
94. If the getter exposes a mutable list, explain how immutability is broken.
95. Fix the getter so callers cannot mutate Employee state.
96. Compare returning the internal list with returning an immutable list.
97. Explain why copying only on construction is insufficient if the getter leaks the internal list.
98. Explain why copying only on the getter is insufficient if the constructor stores the caller's list directly.
99. Design both constructor-side and getter-side protection.
100. Test that two calls to getSkills() behave safely.
101. Determine whether Employee should store a List or another collection abstraction.
102. Explain why defensive copying is part of an immutable object's boundary design.

## 2.6.1 Employee — Deep Immutability

103. Replace List<String> skills with List<Skill>.
104. Create a Skill class containing a mutable name field.
105. Determine whether Employee remains deeply immutable.
106. Explain why an unmodifiable list does not automatically make its elements immutable.
107. Draw the reference graph for Employee → List<Skill> → Skill.
108. Mutate a Skill after placing it inside Employee.
109. Observe whether Employee's visible state changes.
110. Explain shallow immutability versus deep immutability.
111. Make Skill immutable.
112. Make Skill final with private final state.
113. Validate Skill during construction.
114. Remove mutation paths from Skill.
115. Test whether a caller can mutate Skill through a getter.
116. Determine whether List.copyOf() is sufficient when elements are mutable.
117. Explain the difference between protecting the collection structure and protecting the objects inside it.
118. Design a deeply immutable Employee containing skills.
119. Decide whether defensive copying is required for every nested object.
120. Identify which nested types must themselves be immutable.
121. Explain why deep immutability matters for concurrent use.
122. Create a test proving nested mutation cannot alter Employee.

## 2.6.1 Employee — Production Record Version

123. Create the record Employee with id, name, department, BigDecimal salary, and List<String> skills.
124. Explain why the production exercise switches salary from double to BigDecimal.
125. Validate record components using the compact constructor.
126. Protect the skills list inside the record.
127. Explain why a record does not automatically provide deep immutability.
128. Test mutation of the caller's original skills list.
129. Test mutation through the record accessor.
130. Explain what record-generated accessors do.
131. Explain what record-generated equals() and hashCode() represent conceptually.
132. Decide whether a record Employee should be used as a HashMap key.
133. Decide which Employee fields should define equality.
134. Explain whether id alone should define Employee equality.
135. Compare entity-style equality with value-object equality for Employee.
136. Explain the effect of BigDecimal scale on equality decisions.
137. Document the production Employee invariants.
138. Create a production-ready constructor validation strategy.
139. Test every record boundary condition.
140. Explain what remains mutable if a record contains mutable elements.
141. Design a deeply immutable record-based Employee.
142. Explain when you would prefer a traditional class over a record.

## 2.6.2 Demonstrate String Pool Behavior — Literals & Identity

143. Create two identical String literals.
144. Predict a == b for identical literals.
145. Predict a.equals(b) for identical literals.
146. Run the literal comparison.
147. Explain why identical literals can share a pooled String object.
148. Explain why == is an identity comparison.
149. Explain why equals() is a logical value comparison for String.
150. Create String a = "Java" and b = new String("Java").
151. Predict a == b.
152. Predict a.equals(b).
153. Explain the pool reference versus the newly created String object.
154. Explain whether new String("Java") changes the logical string value.
155. Call intern() on the new String.
156. Compare the interned result with the literal using ==.
157. Explain what intern() returns conceptually.
158. Explain why immutability makes string pooling viable.
159. Determine whether String pooling means all strings with the same value are always the same object.
160. Explain why runtime-created strings can differ in identity from literals.
161. Create a diagram showing literal → pool and new String → separate object.
162. Explain why tests of String identity should not be confused with tests of String equality.

## 2.6.2 String — Compile-Time vs Runtime Concatenation

163. Run "Ja" + "va" compared with "Java" using ==.
164. Predict the result before execution.
165. Explain compile-time constant concatenation.
166. Create a variable x = "Ja" and evaluate x + "va".
167. Compare the runtime-concatenated result with "Java" using ==.
168. Compare the runtime-concatenated result with "Java" using equals().
169. Explain why compile-time and runtime concatenation can differ in identity.
170. Investigate a final String variable initialized with a constant.
171. Compare final String x = "Ja" with non-final String x = "Ja" for concatenation behavior.
172. Explain how compile-time constants affect concatenation.
173. Create multiple concatenation examples and predict identity before running.
174. Identify which expressions are constant expressions.
175. Identify which expressions require runtime concatenation.
176. Explain why value equality remains the correct comparison regardless of pooling.
177. Investigate modern Java's string-concatenation implementation at a conceptual level.
178. Explain why source-level concatenation syntax does not by itself tell you the exact runtime mechanism.
179. Explain why StringBuilder is useful for repeated concatenation.
180. Explain why string concatenation in a large loop can create many intermediate values.
181. Compare a concatenation loop with a StringBuilder loop.
182. Measure runtime, allocations, intermediate objects, and GC pressure.

## 2.6.2 String — StringBuilder & Advanced Investigation

183. Implement a loop that repeatedly uses result += i.
184. Rewrite the loop using StringBuilder.
185. Compare the two implementations for 10,000 iterations.
186. Explain why repeated immutable String concatenation can be expensive.
187. Explain how StringBuilder changes the mutation model.
188. Measure runtime for both approaches.
189. Measure allocations if your tooling allows it.
190. Measure GC activity if your tooling allows it.
191. Investigate modern Java concatenation optimizations.
192. Explain why benchmark results should not be generalized from one machine or one run.
193. Explain what StringBuilder's role is even when the compiler can optimize concatenation.
194. Investigate where string literals are stored conceptually in modern JVMs.
195. Investigate how string-pool behavior changed across older Java versions.
196. Explain when intern() may be useful.
197. Explain when intern() should be avoided.
198. Investigate the memory implications of excessive interning.
199. Explain String immutability in relation to pooling and security.
200. Explain how Unicode/code-point handling relates to String exercises.
201. Create an experiment involving supplementary Unicode characters.
202. Explain why char is not necessarily a complete Unicode code point.

## 2.6.3 Demonstrate Wrapper Caching — Integer

203. Create Integer a = 100 and b = 100.
204. Predict a == b.
205. Predict a.equals(b).
206. Run the experiment.
207. Explain Integer caching conceptually.
208. Create Integer a = 1000 and b = 1000.
209. Predict a == b without assuming identity.
210. Predict a.equals(b).
211. Explain why wrapper identity must never be used for business value comparison.
212. Test the common Integer cache boundary around 127 and 128.
213. Explain what the standard cache guarantee covers.
214. Explain why implementations may cache additional values.
215. Explain why valueOf() is preferred over old wrapper constructors.
216. Compare Integer.valueOf(100) with explicit construction conceptually.
217. Explain why deprecated wrapper constructors should not be used in modern Java.
218. Investigate other wrapper cache behavior.
219. Compare Byte caching with Integer caching.
220. Compare Short caching with Integer caching.
221. Compare Long caching with Integer caching.
222. Compare Character caching with Integer caching.
223. Compare Boolean behavior with numeric wrappers.
224. Do not assume all wrapper classes have identical caching rules.
225. Distinguish language guarantees from implementation details.
226. Explain why cache behavior is an identity optimization rather than an equality mechanism.

## 2.6.3 Wrappers — Autoboxing, Unboxing & Null

227. Explain autoboxing.
228. Explain unboxing.
229. Translate Integer x = 10 conceptually into valueOf-based boxing.
230. Translate int y = x conceptually into unboxing.
231. Explain why List<Integer> accepts an int literal.
232. Trace boxing when adding an int to List<Integer>.
233. Trace unboxing when retrieving an Integer into int.
234. Create Integer value = null and assign it to int.
235. Predict the exception.
236. Explain the implicit operation causing the exception.
237. Rewrite null-unboxing code safely.
238. Investigate null unboxing for Long.
239. Investigate null unboxing for Double.
240. Investigate null unboxing for Float.
241. Investigate null unboxing for Short.
242. Investigate null unboxing for Byte.
243. Investigate null unboxing for Character.
244. Investigate null unboxing for Boolean.
245. Explain why nullable wrappers can represent missing values while primitives cannot.
246. Decide what null means in a configuration API.
247. Decide what null means in a database-backed field.
248. Explain why zero should not automatically be substituted for missing.
249. Create a production example where null unboxing causes a failure.
250. Design an explicit null-handling policy.

## 2.6.3 Wrappers — Expressions, Equality & Performance

251. Analyze Integer a = 10; Integer b = 20; Integer c = a + b.
252. List every unboxing and boxing step.
253. Explain why wrapper arithmetic can create hidden conversions.
254. Compare Integer == Integer with Integer == int.
255. Explain why Integer == int causes unboxing.
256. Explain why Integer == Integer compares references.
257. Use equals() for wrapper value equality.
258. Use Objects.equals() when wrappers may be null.
259. Build an experiment that demonstrates the difference between identity and value equality.
260. Compare int[] with Integer[] memory behavior conceptually.
261. Explain why Integer[] stores references to wrapper objects.
262. Explain why primitive arrays store primitive values directly.
263. List potential wrapper-heavy performance costs.
264. Measure allocation rate in a boxing-heavy loop.
265. Measure GC activity in a boxing-heavy loop.
266. Compare a primitive sum loop with a wrapper-based sum loop.
267. Explain why primitive accumulation is often preferable when null/object semantics are unnecessary.
268. Explain when wrappers are still the correct design choice.
269. Investigate boxing in streams and lambdas.
270. Investigate wrapper use in large collections.
271. Compare HashMap<Integer, ...> with an int[] conceptually.
272. Explain why performance should be measured rather than guessed.

## 2.6.4 Correct equals()/hashCode() — EmployeeId

273. Create immutable EmployeeId with a final class.
274. Give EmployeeId a private final String value.
275. Write the constructor.
276. Implement equals() with same-reference optimization.
277. Make equals(null) return false.
278. Make equals(wrongType) return false.
279. Make equal logical IDs compare true.
280. Make different IDs compare false.
281. Implement matching hashCode().
282. Use Objects.hash() or an appropriate equivalent.
283. Explain why equals() and hashCode() must use the same equality state.
284. Write a reflexivity test.
285. Write a symmetry test.
286. Write a transitivity test.
287. Write a consistency test.
288. Write a null-comparison test.
289. Write a hash-code contract test.
290. Create two equal EmployeeId objects with different references.
291. Verify equal EmployeeId objects have equal hash codes.
292. Create unequal EmployeeId objects with the same hash code.
293. Explain why a collision is allowed.
294. Explain why equal hash codes do not imply equality.

## 2.6.4 equals()/hashCode() — HashSet & HashMap

295. Insert two equal EmployeeId values into HashSet.
296. Predict the expected logical size.
297. Verify that the size is one when the contract is correct.
298. Insert an EmployeeId into HashMap as a key.
299. Retrieve the value using a distinct but equal EmployeeId.
300. Explain why the lookup works.
301. Remove hashCode() from EmployeeId.
302. Repeat the HashMap test.
303. Observe and explain the failure.
304. Trace HashMap lookup from key.hashCode() to bucket selection to equality checks.
305. Explain why HashMap needs both hashCode() and equals().
306. Trace HashSet insertion conceptually.
307. Explain why HashSet can contain logical duplicates when the contract is broken.
308. Explain why contains() can fail when hashing/equality is broken.
309. Explain why remove() can fail when hashing/equality is broken.
310. Create a minimal reproduction for missing hashCode().
311. Fix the bug and add a regression test.
312. Explain why the bug may not be obvious from a simple equals() unit test.
313. Create a production incident narrative involving broken EmployeeId equality.
314. Produce root cause, fix, test, and prevention notes.

## 2.6.4 Mutable Employee Key Debugging

315. Create MutableEmployeeKey with an id field.
316. Base equals()/hashCode() on id.
317. Insert the key into HashMap.
318. Mutate id after insertion.
319. Call map.get(key).
320. Predict the result before running.
321. Explain why the object's hash code can change.
322. Explain why the map still stores the entry according to its original placement.
323. Explain why lookup may use a different bucket after mutation.
324. Test containsKey() after mutation.
325. Test remove() after mutation.
326. Test size() after mutation.
327. Explain why the entry can remain present even when get() fails.
328. Redesign the key as an immutable EmployeeId.
329. Verify lookup after redesign.
330. Explain why immutable keys solve the identity-state mutation problem.
331. Identify mutable equality fields during code review.
332. Design a coding guideline prohibiting mutable hash keys.
333. Create a regression test for key mutation.
334. Explain how this bug could affect a production cache.
335. Explain how this bug could affect HashSet membership.

## 2.6.5 Debug Broken compareTo() — Overflow

336. Implement Employee.compareTo() using this.age - other.age.
337. Identify the potential integer-overflow problem.
338. Construct an input that causes subtraction overflow.
339. Predict the incorrect comparison result.
340. Run the failing example.
341. Replace subtraction with Integer.compare().
342. Explain why Integer.compare() is safer.
343. Apply the same principle to long using Long.compare().
344. Explain why comparison code should not depend on arithmetic subtraction.
345. Create a test around Integer.MAX_VALUE.
346. Create a test around Integer.MIN_VALUE.
347. Create a test comparing extreme positive and negative values.
348. Explain how overflow can reverse an intended ordering.
349. Write a code-review rule for subtraction-based compareTo().
350. Find all subtraction-based numeric comparisons in a sample project.
351. Replace unsafe comparisons with comparison APIs.
352. Explain why this bug can survive ordinary test data.
353. Explain why boundary-value testing is important for compareTo().
354. Create a regression test that would catch the overflow bug.

## 2.6.5 compareTo() — Return Values

355. Explain the meaning of a negative compareTo() result.
356. Explain the meaning of zero.
357. Explain the meaning of a positive result.
358. Why should callers not require exactly -1?
359. Find the bug in if (employee.compareTo(other) == -1).
360. Replace it with compareTo(other) < 0.
361. Explain why implementations may return any negative integer.
362. Find code that checks == 1 and fix it.
363. Find code that checks != 0 and determine whether that use is valid.
364. Write tests for less-than, equal-ordering, and greater-than cases.
365. Create a custom Comparable implementation returning -7 for less-than.
366. Show why == -1 would fail with that implementation.
367. Explain why sign is the meaningful contract, not the exact integer.
368. Review comparison code for exact return-value assumptions.

## 2.6.5 compareTo() — Multi-Field Ordering

369. Create ordering by department.
370. Extend ordering by salary.
371. Extend ordering by name.
372. Implement department → salary → name ordering.
373. Use Comparator and thenComparing().
374. Explain why later fields are evaluated only after a tie.
375. Create employees tied on department but different salaries.
376. Create employees tied on department and salary but different names.
377. Create employees tied on all three fields.
378. Determine when compareTo() returns zero.
379. Determine whether compareTo()==0 implies equals() in your design.
380. Explain what happens if a tie-breaker is omitted.
381. Design a stable ordering for employees.
382. Create a second Comparator ordering by salary descending.
383. Create another Comparator ordering by name.
384. Explain why multiple application-specific orderings usually belong in Comparator.
385. Test ordering transitivity.
386. Test sign symmetry.
387. Test equal-ordering cases.
388. Document the intended ordering semantics.

## 2.6.5 TreeSet & BigDecimal Trap

389. Create TreeSet<Employee> using natural ordering.
390. Investigate what happens when compareTo()==0 but equals()==false.
391. Explain why TreeSet uses ordering semantics for uniqueness.
392. Create BigDecimal("1.0") and BigDecimal("1.00").
393. Predict equals().
394. Predict compareTo().
395. Run both operations.
396. Insert both values into HashSet.
397. Insert both values into TreeSet.
398. Explain why HashSet and TreeSet can have different sizes.
399. Explain why TreeSet can treat two unequal values as one ordered element.
400. Explain why compareTo()==0 does not universally mean equals()==true.
401. Document the BigDecimal scale issue.
402. Decide whether scale should matter in your Money domain.
403. Write tests for both HashSet and TreeSet semantics.
404. Explain the consequences for a pricing system.
405. Explain the consequences for a database-facing financial model.
406. Create a production guideline for documenting ordering/equality differences.

## 2.6.5 Broken Comparator — Transitivity

407. Create a deliberately broken Comparator.
408. Design input values that expose inconsistent ordering.
409. Use the Comparator with List.sort().
410. Use the Comparator with TreeSet.
411. Observe the consequences.
412. Identify the violated ordering property.
413. Explain why transitivity matters.
414. Repair the Comparator.
415. Add tests proving transitivity.
416. Add tests proving sign symmetry.
417. Add tests for compare()==0.
418. Explain why a Comparator must be deterministic.
419. Explain why a Comparator should normally be side-effect free.
420. Explain why database calls inside comparison logic are dangerous.
421. Explain why network calls inside comparison logic are dangerous.
422. Explain why heavy computation inside comparison logic can become a performance problem.

## 2.6.6 Integrated Object-Contract Challenge

423. Create EmployeeId as an immutable value object.
424. Create Employee as an immutable domain object.
425. Create Department with well-defined equality.
426. Represent salary with BigDecimal.
427. Implement Employee.equals().
428. Implement Employee.hashCode().
429. Implement Employee.compareTo().
430. Create multiple Employee Comparators.
431. Add defensive copying.
432. Add constructor validation.
433. Decide which fields define Employee equality.
434. Decide which fields define Employee natural ordering.
435. Explain why equality and ordering may use different fields.
436. Test Employee as a HashMap key.
437. Test Employee in HashSet.
438. Test Employee in TreeSet.
439. Test Employee in TreeMap.
440. Test alternative Comparator behavior.
441. Test null handling.
442. Test salary boundaries.
443. Test duplicate EmployeeId values.
444. Test mutable-input collections.
445. Explain every object-contract design decision.

## 2.6.7 Collection Behavior Lab

446. Create identical logical Employee objects.
447. Insert them into HashSet<Employee>.
448. Insert them into TreeSet<Employee>.
449. Insert them into HashMap<EmployeeId, Employee>.
450. Insert them into TreeMap<EmployeeId, Employee>.
451. Record what determines uniqueness in HashSet.
452. Record what determines uniqueness in TreeSet.
453. Record what determines lookup in HashMap.
454. Record what determines lookup in TreeMap.
455. Record what determines ordering in TreeSet.
456. Record what determines ordering in TreeMap.
457. Test behavior when equals() and ordering disagree.
458. Test behavior after key mutation.
459. Test null behavior.
460. Test duplicate logical values.
461. Create a comparison table of HashSet, TreeSet, HashMap, and TreeMap.
462. Explain why collection choice changes semantics.
463. Explain why collection behavior must be included in object-contract testing.
464. Create a production recommendation for selecting the collection.

## 2.6.8 Primitive & Wrapper Edge-Case Lab

465. Test int overflow.
466. Test long overflow.
467. Test floating-point precision.
468. Test primitive versus wrapper boxing.
469. Test unboxing.
470. Test wrapper caching.
471. Test null unboxing.
472. Test wrapper ==.
473. Test wrapper equals().
474. Test wrapper compareTo().
475. Test Double.NaN.
476. Test Double.POSITIVE_INFINITY.
477. Test Double.NEGATIVE_INFINITY.
478. Test +0.0.
479. Test -0.0.
480. Compare primitive == with wrapper equals().
481. Compare wrapper compareTo() with primitive comparison.
482. Explain each observed difference.
483. Identify which behaviors are guaranteed by the language versus implementation-dependent.
484. Create a consolidated edge-case table.

## 2.6.9 String + Wrapper + Equality Challenge

485. Predict String a = "100"; String b = new String("100"); a == b.
486. Predict a.equals(b).
487. Predict Integer x = 100; Integer y = 100; x == y.
488. Predict x.equals(y).
489. Run the complete example.
490. Explain String pool behavior.
491. Explain Integer cache behavior.
492. Explain object identity.
493. Explain logical equality.
494. Explain why both String and Integer can make == appear to work.
495. Explain why neither case makes == a correct general value-comparison technique.
496. Modify the Integer example to 1000.
497. Modify the String example using runtime concatenation.
498. Predict the new results.
499. Create a single table comparing identity and equality across String and Integer.

## 2.6.10 Production Money Value Object

500. Design Money as a value object.
501. Make Money immutable.
502. Use BigDecimal for amount.
503. Represent currency with a currency code.
504. Validate constructor arguments.
505. Implement equals().
506. Implement hashCode().
507. Decide whether Money should implement Comparable.
508. Explain when natural ordering is justified.
509. Make Money safe as a HashMap key.
510. Explain why Money should be safe across threads.
511. Define a clear null policy.
512. Test scale behavior.
513. Compare BigDecimal("10.0") with BigDecimal("10.00").
514. Decide whether your domain treats them as equal.
515. Document that equality decision.
516. Explain why double is unsuitable for this exercise's money representation.
517. Design arithmetic operations without mutation.
518. Explain how immutable arithmetic could return new Money instances.
519. Test Money in HashSet.
520. Test Money in HashMap.
521. Test Money with different currencies.
522. Define invalid currency input behavior.
523. Define invalid amount behavior.
524. Create production-level tests for Money.

## 2.6.11 Production Debugging Scenario

525. Analyze an incident where HashSet contains duplicate Employees.
526. Analyze an incident where employees.contains(employee) returns false after a previous add.
527. Check equals() first.
528. Check hashCode() second.
529. Check mutable equality fields third.
530. Check whether the object was mutated.
531. Check inheritance equality.
532. Check null behavior.
533. Check hash distribution.
534. Produce a minimal reproduction.
535. Produce the root cause.
536. Produce the fixed implementation.
537. Produce a regression test.
538. Produce a production prevention strategy.
539. Explain how broken equality can create duplicates.
540. Explain how broken hashing can make contains() fail.
541. Explain how mutable identity can make lookup fail.
542. Explain how inheritance can cause asymmetry.
543. Explain how poor hash distribution can affect performance.
544. Turn the investigation into a reusable incident checklist.

## 2.6.12 Interview Drill — Basic

545. Why is String immutable?
546. What is the String pool?
547. What is autoboxing?
548. What is unboxing?
549. What is wrapper caching?
550. What is immutability?
551. What is equals()?
552. What is hashCode()?
553. What is Comparable?
554. What is natural ordering?
555. What is compareTo()?
556. What is a hash collision?
557. Why are wrappers needed for generics?
558. Why does List<int> not work?
559. Why does List<Integer> work?
560. What is the difference between parseInt() and valueOf()?
561. What happens when a wrapper is null and gets unboxed?
562. Why is == dangerous for wrapper values?
563. Why is == dangerous for String value comparison?
564. Why are immutable keys useful?

## 2.6.12 Interview Drill — Intermediate

565. Why doesn't final make a List immutable?
566. Why must arrays be defensively copied?
567. Why must equals() and hashCode() agree?
568. What happens if a HashMap key is mutated?
569. Why does compareTo() return an integer?
570. Why shouldn't compareTo() use subtraction?
571. Why shouldn't code check compareTo()==-1?
572. What is the difference between Comparable and Comparator?
573. Why can TreeSet and HashSet behave differently?
574. Why can TreeMap and HashMap behave differently?
575. Why can BigDecimal.equals() and compareTo() disagree?
576. Why can equal objects have the same hash code?
577. Can unequal objects have the same hash code?
578. Why does HashMap need equals() after hashing?
579. Why is constructor validation important for immutable objects?
580. What is defensive copying?
581. What is deep immutability?
582. Why can a record still contain mutable state?
583. Why can boxing affect performance?
584. Why can null wrappers be useful at API boundaries?

## 2.6.12 Interview Drill — Advanced

585. Explain String pool internals conceptually.
586. Explain compile-time versus runtime string concatenation.
587. Explain wrapper cache behavior.
588. Explain final-field semantics in relation to immutable objects.
589. Explain the complete HashMap lookup process.
590. Explain the complete HashSet insertion process.
591. Explain equality contract violations.
592. Explain BigDecimal.equals() versus compareTo().
593. Explain ordering-based uniqueness in TreeSet.
594. Explain why TreeMap uses ordering semantics.
595. Explain how mutable hash keys become corrupted from a lookup perspective.
596. Explain why wrapper == can appear to work.
597. Explain mixed wrapper/primitive comparison.
598. Explain defensive copying of nested mutable state.
599. Explain why records do not guarantee deep immutability.
600. Explain why primitive arrays and wrapper arrays differ in memory behavior.
601. Explain StringBuilder's role in repeated concatenation.
602. Explain comparator transitivity.
603. Explain compareTo() overflow.
604. Explain entity equality versus value-object equality.

## 2.6.12 Interview Drill — Senior / Production

605. Design an immutable value object.
606. Define entity equality.
607. Define value-object equality.
608. Diagnose a broken HashMap lookup.
609. Diagnose duplicate objects in a HashSet.
610. Design safe natural ordering.
611. Decide when natural ordering should not be implemented.
612. Explain the performance impact of boxing.
613. Explain defensive-copy trade-offs.
614. Design object contracts for a high-throughput service.
615. Design a production-safe Employee domain model.
616. Design a production-safe Money value object.
617. Design a collection strategy for Employee lookup and sorting.
618. Explain how to prevent mutable-key bugs through API design.
619. Explain how to test object contracts systematically.
620. Explain how to document equality and ordering semantics.
621. Explain how to debug a TreeSet that loses apparently distinct values.
622. Explain how to debug wrapper identity surprises.
623. Explain how to debug unexpected immutability violations.
624. Explain how to decide whether a record is appropriate.

## 2.6.13 OpenJDK Investigation

625. Inspect String implementation.
626. Inspect Integer.valueOf().
627. Inspect Integer.IntegerCache.
628. Inspect Object.equals().
629. Inspect Object.hashCode().
630. Inspect HashMap.
631. Inspect HashSet.
632. Inspect TreeMap.
633. Inspect TreeSet.
634. Inspect StringBuilder.
635. Investigate how the String pool is represented conceptually.
636. Investigate how Integer caching is implemented.
637. Investigate how HashMap calculates a bucket.
638. Investigate how HashMap resolves collisions.
639. Investigate how TreeMap navigates its tree.
640. Investigate how sorted collections use comparison.
641. Relate implementation details back to the observable behavior of earlier exercises.
642. Distinguish implementation details from Java language/API contracts.
643. Record which observations are safe to depend on in production.
644. Record which observations should not be treated as portable guarantees.

## 2.6.14 Performance Lab

645. Benchmark primitive int operations.
646. Benchmark Integer operations.
647. Benchmark String concatenation.
648. Benchmark StringBuilder.
649. Benchmark HashMap with good hash distribution.
650. Benchmark HashMap with intentionally poor hash distribution.
651. Measure throughput.
652. Measure allocation rate.
653. Measure heap usage.
654. Measure GC activity.
655. Measure CPU usage.
656. Measure latency.
657. Explain why a single System.nanoTime() measurement is insufficient.
658. Design a repeated benchmark.
659. Control warmup where appropriate.
660. Control input sizes.
661. Explain why benchmark methodology affects conclusions.
662. Compare primitive and wrapper performance.
663. Compare String and StringBuilder performance.
664. Compare good and poor hash distribution.
665. Explain why performance should be measured rather than assumed.
666. Identify which benchmark results are relevant to production decisions.

## 2.6.15 Final Integrated Challenge — Employee Directory

667. Build the Employee Directory project.
668. Implement EmployeeId as an immutable value object.
669. Implement correct EmployeeId equality.
670. Implement correct EmployeeId hash code.
671. Make EmployeeId safe as a HashMap key.
672. Implement Employee as an immutable domain object.
673. Use defensive copying in Employee.
674. Validate Employee constructor inputs.
675. Implement correct Employee equality semantics.
676. Implement stable Employee hash code.
677. Support sorting by employee ID.
678. Support sorting by name.
679. Support sorting by salary.
680. Support sorting by department plus name.
681. Implement add.
682. Implement findById.
683. Implement remove.
684. Implement findByDepartment.
685. Implement sortBySalary.
686. Implement topNBySalary.
687. Test duplicate IDs.
688. Test null IDs.
689. Test mutable input collections.
690. Test duplicate Employees.
691. Test HashMap lookup.
692. Test TreeSet behavior.
693. Test Comparator consistency.
694. Test salary edge cases.
695. Test concurrent reads.
696. Explain the complete architecture from EmployeeId to sorted views.
697. Explain why EmployeeId is a value object.
698. Explain why Employee is immutable.
699. Explain why Comparators are used for multiple sort views.
700. Explain the collection choices.
701. Document all invariants.
702. Document all equality semantics.
703. Document all ordering semantics.
704. Create a final production-style test suite.

## 2.6.16 Debugging Mastery Checklist

705. Diagnose HashMap.get() unexpectedly returning null.
706. Diagnose HashSet containing duplicates.
707. Diagnose HashSet.remove() unexpectedly failing.
708. Diagnose TreeSet losing apparently distinct values.
709. Diagnose TreeMap replacing an apparently different key.
710. Diagnose wrapper == behaving unexpectedly.
711. Diagnose String == behaving unexpectedly.
712. Diagnose an immutable object changing unexpectedly.
713. Diagnose a getter exposing mutable internal state.
714. Diagnose an inconsistent Comparator.
715. Diagnose compareTo() failure for extreme integers.
716. Diagnose excessive boxing and allocation.
717. For each failure, identify the smallest reproducible example.
718. For each failure, state the likely root cause.
719. For each failure, identify the relevant contract.
720. For each failure, produce a regression test.
721. For each failure, produce a prevention strategy.

## 2.6.17 Final Mastery Gate — Primitive Types

722. Implement primitive operations.
723. Explain numeric ranges.
724. Debug overflow.
725. Explain integer promotion.
726. Explain floating-point precision.
727. Create tests for primitive boundaries.
728. Explain why integer overflow is relevant to compareTo().
729. Explain why floating-point edge cases are relevant to equality.

## 2.6.17 Final Mastery Gate — Wrappers

730. Explain autoboxing.
731. Explain unboxing.
732. Explain wrapper caching.
733. Debug null unboxing.
734. Explain boxing performance.
735. Use equals() instead of == for wrapper value comparison.
736. Explain mixed wrapper/primitive comparisons.
737. Explain wrapper nullability at API boundaries.
738. Explain wrapper memory overhead.
739. Explain why primitive types may be preferable in hot numerical paths.

## 2.6.17 Final Mastery Gate — Strings

740. Explain String immutability.
741. Explain the String pool.
742. Explain literals.
743. Explain new String().
744. Explain intern().
745. Explain compile-time concatenation.
746. Explain runtime concatenation.
747. Use StringBuilder appropriately.
748. Explain Unicode/code points at a practical level.
749. Explain why String identity is not a value-comparison mechanism.

## 2.6.17 Final Mastery Gate — Immutability

750. Build an immutable Employee.
751. Use final fields correctly.
752. Protect mutable fields.
753. Implement defensive copying.
754. Explain record limitations.
755. Explain thread-safety benefits.
756. Design deep immutability when required.
757. Explain why mutable nested objects break deep immutability.
758. Test both constructor and getter mutation paths.
759. Explain immutable object invariants.

## 2.6.17 Final Mastery Gate — Object Contracts

760. Implement equals().
761. Implement hashCode().
762. Prove equality contract compliance with tests.
763. Explain HashMap behavior.
764. Explain HashSet behavior.
765. Debug mutable-key failures.
766. Implement Comparable.
767. Implement safe compareTo().
768. Use Comparator correctly.
769. Explain TreeMap and TreeSet semantics.
770. Explain equality/order consistency.
771. Explain BigDecimal equality/order differences.
772. Explain hash collisions.
773. Explain why equal hash codes do not imply equality.
774. Explain why compareTo()==0 does not universally imply equals()==true.

## 2.6.17 Final Mastery Gate — Deep Integration

775. Complete the Employee Directory.
776. Explain every major design choice.
777. Demonstrate each major failure mode intentionally.
778. Fix each failure mode.
779. Write regression tests for each failure.
780. Run edge-case tests.
781. Run performance experiments.
782. Inspect relevant JDK implementations.
783. Explain the internal behavior behind observable results.
784. Explain production implications.
785. Answer basic interview questions.
786. Answer intermediate interview questions.
787. Answer advanced interview questions.
788. Answer senior/production questions.
789. Teach the entire module without referring to notes.
790. Implement the integrated project from a blank repository.
791. Debug an intentionally broken version of the integrated project.
792. Perform a production-style code review.
793. Document the final architecture.
794. Mark Deep Mastery Complete only after all major gates are satisfied.

---

## Final Status

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