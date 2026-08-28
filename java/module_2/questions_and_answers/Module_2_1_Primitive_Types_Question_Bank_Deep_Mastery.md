# Module 2.1 — Primitive Types
## Deep Mastery Question Bank

> **Goal:** Convert the Primitive Types Deep Mastery Guide into a structured question bank that tests understanding from fundamentals through JVM internals, debugging, performance, concurrency, and production design.

## Question Mastery Cycle

For every topic, work through:

1. What is it?
2. Why does Java have it?
3. Syntax and API
4. Basic example
5. Predict the result before running
6. Explain the internal/language rule
7. Analyze memory/runtime behavior
8. Handle edge cases
9. Identify common mistakes
10. Analyze performance
11. Apply it in production
12. Debug broken code
13. Answer interview questions
14. Complete advanced follow-ups

## Completion Standard

> **Predict → Explain → Implement → Inspect Internals → Debug → Measure → Handle Edge Cases → Apply in Production**

---

# 2.1.1 Primitive Types — Overview

## Basic

1. What are Java's eight primitive types?
2. Which primitive types are integral?
3. Which primitive types are floating-point?
4. Which primitive represents characters?
5. Which primitive represents logical state?
6. Which primitive is 8 bits wide?
7. Which primitive is 16 bits wide?
8. Which primitive is 32 bits wide?
9. Which primitive is 64 bits wide?
10. What is the conceptual distinction between primitive values and reference values?

## Intermediate

11. Why are primitives different from wrapper classes?
12. Which primitive is normally the default choice for ordinary integer arithmetic?
13. When is `long` more appropriate than `int`?
14. When might `byte` be appropriate?
15. Why is `short` uncommon in ordinary application code?
16. What is the difference between `char` and an arbitrary Unicode character?
17. What representation does Java define for `boolean` at the language level?
18. Why should you not assume that every primitive is physically stored using its source-level width in every JVM context?

## Advanced

19. Explain the eight primitives from first principles.
20. Explain their value domains and conceptual representations.
21. Why does Java distinguish primitives from objects?
22. What trade-offs exist between primitive values and object wrappers?
23. Why is `boolean` representation intentionally not treated like an ordinary numeric width?
24. Why should production code choose a primitive based on domain requirements rather than simply its apparent size?

## Traps

25. Is every primitive always stored directly on the Java stack?
26. Is `boolean` guaranteed to occupy exactly one byte?
27. Is `char` a synonym for Unicode code point?
28. Does a smaller primitive always produce better application performance?
29. Should a business value below 128 automatically use `byte`?

## Design

30. Choose primitive types for:
   - request count
   - database ID
   - epoch timestamp
   - binary payload
   - scientific measurement
   - Unicode code-unit operation
   - binary status

31. Explain every choice.

---

# 2.1.2 `byte`

## Basic

32. What is `byte`?
33. What is its size?
34. What is its range?
35. How is the signed range derived?
36. Write a valid `byte` declaration.
37. Why is `byte` useful for binary data?

## Intermediate

38. Why does `byte + byte` produce an `int`?
39. Predict the result of:

```java
byte value = 127;
value++;
```

40. Why does the result wrap?
41. Can a `byte` directly hold `128`?
42. Why does a byte array make sense for network/file data?

## Advanced

43. Explain signed 8-bit two's-complement representation.
44. Explain why `127 + 1` becomes `-128`.
45. Explain integer promotion involving `byte`.
46. Explain the difference between a `byte` value and raw binary data.
47. When should a production API use `byte` versus `int`?

## Traps

48. Does `byte + byte` return `byte`?
49. Does integer overflow throw an exception?
50. Is `byte` automatically more memory-efficient in every context?
51. Is a Java `byte` unsigned?

## Debugging

52. Debug a `byte` calculation that unexpectedly becomes negative.
53. Explain a compiler error caused by assigning `byte + byte` to `byte`.
54. Fix a byte overflow problem using an appropriate design.

## Production

55. Design a binary/network buffer using `byte`.
56. Explain why using `byte` for an ordinary application counter may be a poor decision.
57. Identify a production risk caused by byte overflow.

---

# 2.1.3 `short`

## Basic

58. What is `short`?
59. What is its size?
60. What is its range?
61. Write a valid `short` declaration.
62. Why is its range larger than `byte`?

## Intermediate

63. Why does `short + short` produce `int`?
64. Predict:

```java
short value = 32767;
value++;
```

65. Explain the result.
66. Where might `short` be useful?

## Advanced

67. Explain the two's-complement range mathematically.
68. Explain why `short` is less common than `int`.
69. Discuss `short` in binary formats and interoperability.
70. Discuss the trade-off between source-level width and actual JVM performance.

## Traps

71. Does using `short` automatically make arithmetic use 16-bit CPU operations?
72. Does `short + short` remain `short`?
73. Should all values below 32,767 use `short`?

## Production

74. Identify a real case where `short` is justified.
75. Identify a case where `int` is preferable even though values fit in `short`.

---

# 2.1.4 `int`

## Basic

76. What is `int`?
77. What is its size?
78. What is its minimum value?
79. What is its maximum value?
80. What constants expose these boundaries?
81. Why is `int` the normal integer choice in Java?

## Intermediate

82. Predict:

```java
int max = Integer.MAX_VALUE;
int result = max + 1;
```

83. Explain the result.
84. What happens when an `int` calculation exceeds its range?
85. Why are array indexes normally `int`-based?
86. Why are loop counters commonly `int`?

## Advanced

87. Explain signed 32-bit two's-complement representation.
88. Explain integer overflow semantics.
89. Explain why overflow can silently corrupt business logic.
90. Explain how overflow can affect pagination.
91. Explain how overflow can affect capacity calculations.
92. Explain how overflow can become a security issue.

## Traps

93. Does `int` overflow throw `ArithmeticException`?
94. Does Java automatically promote every overflowing `int` calculation to `long`?
95. Is `Integer.MAX_VALUE + 1` automatically safe?

## Debugging

96. Debug a counter that becomes negative.
97. Replace unsafe arithmetic with `Math.addExact()`.
98. Determine whether changing `int` to `long` actually solves the entire problem.

## Production

99. Decide between `int` and `long` for a high-volume request counter.
100. Design overflow-safe pagination arithmetic.

---

# 2.1.5 `long`

## Basic

101. What is `long`?
102. What is its size?
103. What is its range?
104. Why is `L` used on long literals?
105. Write a valid large `long` literal.

## Intermediate

106. Why is this dangerous?

```java
long value = 2_000_000_000 + 2_000_000_000;
```

107. Explain why `L` should be used:

```java
long value = 2_000_000_000L + 2_000_000_000L;
```

108. What can happen if arithmetic is performed as `int` before assignment to `long`?
109. Why are timestamps commonly represented with `long`?

## Advanced

110. Explain expression typing before assignment.
111. Explain how a destination variable does not retroactively change the type of an arithmetic expression.
112. Design safe large-counter arithmetic.
113. Explain long overflow and detection strategies.

## Traps

114. Does assigning to `long` make the operands `long`?
115. Is every large integer literal automatically a `long`?
116. Does `long` eliminate all overflow risks?

## Debugging

117. Fix a timestamp calculation that overflows.
118. Fix a large multiplication that still overflows despite storing the result in `long`.
119. Use `Math.*Exact()` appropriately.

## Production

120. Choose `long` for IDs, timestamps, sequence numbers and byte counts.
121. Explain when even `long` may be insufficient.
122. Design an overflow policy for a monotonically increasing counter.

---

# 2.1.6 `float`

## Basic

123. What is `float`?
124. What is its width?
125. What precision does it approximately provide?
126. Why does a float literal normally need `f`?
127. Write a valid float declaration.

## Intermediate

128. What are `Float.POSITIVE_INFINITY` and `Float.NEGATIVE_INFINITY`?
129. What is `Float.NaN`?
130. What does `Float.MIN_VALUE` actually mean?
131. Why is `Float.MIN_VALUE` not the most negative float?
132. Why is `0.1f` generally approximate?

## Advanced

133. Explain IEEE 754 floating-point representation at a high level.
134. Explain sign, exponent and significand.
135. Explain precision versus range.
136. Explain normal and subnormal values.
137. Explain floating-point underflow.

## Traps

138. Is `Float.MIN_VALUE` negative?
139. Is `float` appropriate for exact monetary values?
140. Does more range necessarily mean more precision?

## Production

141. Identify a workload where `float` is reasonable.
142. Identify a workload where `double` is preferable.
143. Identify a workload where neither is appropriate.

---

# 2.1.7 `double`

## Basic

144. What is `double`?
145. What is its width?
146. What precision does it approximately provide?
147. Why do ordinary floating-point literals default to `double`?
148. Write a valid `double` declaration.

## Intermediate

149. What is `Double.NaN`?
150. What is infinity?
151. Why does:

```java
Double.NaN == Double.NaN
```

return false?
152. How do you test for NaN?
153. How do you test for infinity?

## Advanced

154. Explain binary floating-point approximation.
155. Explain why decimal fractions such as `0.1` can be inexact.
156. Explain the implications of floating-point rounding.
157. Explain why scientific/statistical workloads can use `double`.
158. Explain when `double` is inappropriate.

## Traps

159. Is `double` exact decimal arithmetic?
160. Is `Double.MIN_VALUE` the most negative double?
161. Can NaN be compared normally using `==`?

## Production

162. Decide whether `double`, `BigDecimal`, or integer minor units should represent a monetary amount.
163. Explain the domain requirements that influence the decision.

---

# 2.1.8 `char`

## Basic

164. What is `char`?
165. What is its size?
166. What is its range?
167. What does UTF-16 mean?
168. Write a valid `char`.

## Intermediate

169. Why does:

```java
char c = 'A';
int value = c;
```

produce `65`?
170. What happens when `char` participates in arithmetic?
171. Is every Unicode character representable by one `char`?

## Advanced

172. Explain why `char` is a UTF-16 code unit.
173. Explain surrogate pairs.
174. Explain Unicode code points.
175. Explain the difference between:
   - UTF-16 code unit
   - Unicode code point
   - user-visible character

176. Explain `Character.codePointAt()`.
177. Explain `Character.charCount()`.
178. Explain `String.codePoints()`.

## Traps

179. Is one `char` always one user-visible character?
180. Is one emoji always one `char`?
181. Does `char` represent UTF-8?
182. Is `char` signed?

## Debugging

183. Debug text processing that incorrectly assumes `length()` equals visible-character count.
184. Debug a Unicode string containing a supplementary code point.

## Production

185. Identify why Unicode mistakes matter in internationalization.
186. Explain why Unicode assumptions can matter in security-sensitive text processing.

---

# 2.1.9 `boolean`

## Basic

187. What values can `boolean` hold?
188. Which operators work with booleans?
189. What does `!` do?
190. What do `&&` and `||` do?
191. What does `^` do for booleans?

## Intermediate

192. Explain short-circuit evaluation.
193. Predict:

```java
user != null && user.isActive()
```

194. Why is the second operand skipped when the first operand is false?
195. Why is the second operand skipped for `true || condition`?

## Advanced

196. Explain short-circuit evaluation as both a correctness and performance mechanism.
197. Explain why Java does not treat `1` as `true`.
198. Explain when multiple booleans should be replaced by an enum.

## Traps

199. Is `if (1)` valid Java?
200. Does `&&` always evaluate both operands?
201. Does `&` have the same short-circuit behavior as `&&`?

## Debugging

202. Diagnose a null-related bug caused by incorrect operand ordering.
203. Diagnose a side-effect bug caused by assuming both operands execute.

## Production

204. Design a safe conditional guard around nullable state.
205. Decide when boolean flags should become an enum.

---

# 2.1.10 Numeric Ranges

## Basic

206. State the ranges of `byte`, `short`, `int`, and `long`.
207. Derive the signed range formula.
208. What is the difference between minimum and maximum representable values?
209. What special values exist for floating-point types?

## Intermediate

210. Distinguish:
   - smallest positive value
   - most negative value
   - largest positive finite value

211. Why is `Float.MIN_VALUE` positive?
212. Why is `Double.MIN_VALUE` easy to misunderstand?

## Advanced

213. Explain numeric ranges from bit representation.
214. Explain why signed two's-complement has one more negative value than positive value.
215. Explain the floating-point value categories:
   - finite
   - zero
   - infinity
   - NaN
   - subnormal

## Traps

216. Is `MIN_VALUE` always the most negative value?
217. Is the floating-point range symmetric around zero?
218. Does greater numeric range imply exactness?

---

# 2.1.11 Overflow

## Basic

219. What is integer overflow?
220. What happens when an integer exceeds its representable range?
221. Predict:

```java
int x = Integer.MAX_VALUE;
int y = x + 1;
```

## Intermediate

222. What is `Math.addExact()`?
223. What is `Math.subtractExact()`?
224. What is `Math.multiplyExact()`?
225. What is `Math.incrementExact()`?
226. What is `Math.decrementExact()`?
227. What is `Math.toIntExact()`?
228. What is `Math.negateExact()`?

## Advanced

229. Explain how `Math.*Exact()` changes overflow handling.
230. Why can explicit overflow detection be important in production?
231. How can overflow corrupt:
   - counters
   - money
   - pagination
   - capacity calculations
   - timestamps
   - security checks

232. Design an overflow-safe arithmetic layer.

## Traps

233. Does Java integer overflow automatically throw an exception?
234. Does casting prevent overflow?
235. Does changing only the result variable type prevent operand overflow?

## Debugging

236. Predict and debug:

```java
int total = 2_000_000_000;
total += 2_000_000_000;
```

237. Fix it using `long`.
238. Fix it using `Math.addExact()`.

## Production

239. Identify an overflow risk in a service processing millions of requests.
240. Design an explicit overflow policy.

---

# 2.1.12 Underflow

## Basic

241. What is integer underflow?
242. What happens when `Integer.MIN_VALUE` is decremented?
243. Predict:

```java
int x = Integer.MIN_VALUE;
x--;
```

## Intermediate

244. What is floating-point underflow?
245. What is a subnormal value?
246. What happens when a floating-point value becomes too small?

## Advanced

247. Explain gradual underflow.
248. Explain precision behavior near zero.
249. Explain IEEE 754 handling of subnormal values.
250. Distinguish integer wraparound from floating-point underflow.

## Traps

251. Are integer underflow and floating-point underflow the same phenomenon?
252. Does floating-point underflow always immediately produce zero?
253. Is a subnormal number the same as zero?

## Production

254. Identify numerical workloads where underflow matters.
255. Explain why numerical algorithms must understand subnormal behavior.

---

# 2.1.13 Integer / Binary Numeric Promotion

## Basic

256. What is numeric promotion?
257. Why are `byte`, `short`, and `char` commonly promoted to `int`?
258. Predict the type of:

```java
byte a = 10;
byte b = 20;
var result = a + b;
```

259. What is the type of `char + char`?
260. What is the type of `int + long`?
261. What is the type of `long + float`?
262. What is the type of `float + double`?

## Intermediate

263. Explain the simplified promotion ladder:

```text
byte
short
char
  ↓
int
  ↓
long
  ↓
float
  ↓
double
```

264. Why is this ladder only a mental model?
265. Why should the precise language rules be studied?

## Advanced

266. Explain binary numeric promotion from first principles.
267. Predict expression result types without running the code.
268. Explain how promotion interacts with overflow.
269. Explain how promotion interacts with casting.
270. Explain how promotion affects overloaded methods.

## Traps

271. Does `byte + byte` produce `byte`?
272. Does `char + int` produce `char`?
273. Does assignment to `byte` force an arithmetic expression to be byte-sized?

## Debugging

274. Debug:

```java
byte result = a + b;
```

275. Explain the compiler error.
276. Fix it safely.
277. Determine whether the cast can lose information.

---

# 2.1.14 Compound Assignment and Narrowing

## Basic

278. Why does this compile?

```java
byte x = 10;
x += 20;
```

279. Why does this fail?

```java
byte x = 10;
x = x + 20;
```

## Intermediate

280. What implicit conversion occurs in compound assignment?
281. Does compound assignment make overflow impossible?
282. Predict:

```java
byte x = 127;
x += 1;
```

## Advanced

283. Explain compound assignment according to Java's conversion rules.
284. Explain why the apparent convenience can hide information loss.
285. Compare explicit casts with compound assignment.

## Traps

286. Is `x += y` always equivalent to `x = x + y`?
287. Can compound assignment silently narrow?
288. Does successful compilation imply that the result is safe?

## Debugging

289. Debug a byte overflow caused by `+=`.
290. Replace dangerous compound assignment with explicit safe arithmetic.

---

# 2.1.15 Floating-Point Precision

## Basic

291. Why can `0.1` not be represented exactly in binary floating point?
292. Predict:

```java
double result = 0.1 + 0.2;
System.out.println(result == 0.3);
```

293. Why is the result typically false?

## Intermediate

294. What are binary fractions?
295. Why is `1/10` difficult to represent exactly in binary?
296. Why is direct `==` comparison dangerous for approximate calculations?
297. What is an epsilon comparison?

## Advanced

298. Why is there no universal epsilon that works for every numerical problem?
299. Explain absolute error.
300. Explain relative error.
301. Explain ULP-based comparison.
302. When should a numerical library or domain-specific comparison strategy be used?

## Traps

303. Does floating-point arithmetic always produce inaccurate results?
304. Is `0.1` always stored as exactly the same decimal value?
305. Is `Math.abs(a-b) < epsilon` universally correct?

## Production

306. Design a safe comparison policy for scientific calculations.
307. Explain why monetary calculations require a different representation.

---

# 2.1.16 Floating-Point Special Values

## Basic

308. What is NaN?
309. What is positive infinity?
310. What is negative infinity?
311. What are `+0.0` and `-0.0`?
312. What are subnormal values?

## Intermediate

313. Predict:

```java
double x = Double.NaN;
x == x
```

314. Why are comparisons such as `x < x` false for NaN?
315. How should NaN be detected?
316. How should infinity be detected?

## Advanced

317. Explain NaN propagation.
318. Explain signed zero.
319. Explain why signed zero can affect numerical operations.
320. Explain the relationship between special values and IEEE 754.

## Traps

321. Is NaN equal to itself?
322. Is NaN greater than every number?
323. Is `-0.0` identical to `+0.0` in every observable operation?

## Debugging

324. Debug a calculation unexpectedly producing NaN.
325. Debug a calculation unexpectedly producing infinity.
326. Detect and handle special values at an API boundary.

## Production

327. Design validation rules for numerical inputs.
328. Decide whether NaN/infinity should be allowed to enter a business domain.

---

# 2.1.17 Floating Point vs Money

## Basic

329. Why is `double` not exact decimal arithmetic?
330. Why can money calculations expose floating-point problems?
331. What is `BigDecimal`?
332. What are integer minor units?

## Intermediate

333. Compare:

```java
double amount
```

with:

```java
long cents
```

334. Compare both with `BigDecimal`.
335. What domain factors affect the choice?

## Advanced

336. Explain currency and scale requirements.
337. Explain rounding requirements.
338. Explain tax calculation implications.
339. Explain serialization implications.
340. Explain regulatory requirements.
341. Design a monetary representation for a payment service.

## Traps

342. Is `double` always wrong for money?
343. Is `BigDecimal` automatically the best solution for every financial value?
344. Does storing cents in `long` solve every monetary problem?

## Production

345. Choose between `long` minor units and `BigDecimal`.
346. Define rounding rules explicitly.
347. Design serialization for monetary values.
348. Identify overflow risks in integer minor-unit arithmetic.

---

# 2.1.18 Primitive Literals

## Basic

349. What is a decimal integer literal?
350. How is a binary literal written?
351. How is an octal literal written?
352. How is a hexadecimal literal written?
353. Why can a leading zero be dangerous?
354. Why are underscores allowed in numeric literals?

## Intermediate

355. Predict the value of:

```java
int x = 0b1010;
int y = 012;
int z = 0xFF;
```

356. Where can underscores be used?
357. Where can underscores not be used?
358. Why are type suffixes important?

## Advanced

359. Explain literal typing rules.
360. Explain why a large literal may require `L`.
361. Explain why a float literal may require `f`.
362. Design readable numeric literals for production code.

## Traps

363. Is `012` decimal 12?
364. Is `3_000_000_000` automatically a valid `int`?
365. Can underscores appear anywhere inside a literal?

## Debugging

366. Fix an invalid large integer literal.
367. Debug an accidental octal literal.
368. Debug an invalid float literal.

---

# 2.1.19 Type Casting

## Basic

369. What is widening conversion?
370. What is narrowing conversion?
371. Give an example of widening.
372. Give an example of narrowing.
373. What information can narrowing lose?

## Intermediate

374. Predict:

```java
double x = 10.9;
int y = (int) x;
```

375. Why is the fractional part discarded toward zero?
376. What happens when a large `long` is narrowed to `int`?
377. What happens when a floating-point value is narrowed to an integer?

## Advanced

378. Explain widening versus narrowing from the type-system perspective.
379. Explain why casts are not guarantees of safety.
380. Identify information-loss boundaries.
381. Design safe conversion APIs.

## Traps

382. Does a cast round a floating-point number?
383. Is every widening conversion lossless in every conceptual sense?
384. Does casting prevent invalid domain values?

## Debugging

385. Debug a truncation bug.
386. Debug a narrowing overflow.
387. Replace unsafe casts with explicit validation or exact conversion.

---

# 2.1.20 Memory / Runtime Behavior

## Basic

388. Where are primitive local variables conceptually represented during method execution?
389. What are JVM local-variable slots?
390. What happens to a primitive field inside an object?
391. What is a static primitive field?
392. How does a primitive array differ from a wrapper array?

## Intermediate

393. Explain:

```java
int[] numbers = {1, 2, 3};
```

versus:

```java
Integer[] numbers;
```

394. Why does `Integer[]` contain references?
395. Why should you avoid saying "all primitives live on the stack"?

## Advanced

396. Explain how JVM implementation details affect physical representation.
397. Explain JIT compilation.
398. Explain escape analysis at a high level.
399. Explain object layout considerations.
400. Explain why source-level primitive syntax does not guarantee one specific machine representation.

## Traps

401. Are all primitive locals physically on the stack?
402. Are primitive fields stored separately from their containing object?
403. Does a primitive array have the same layout as an object-reference array?

## Production

404. Explain when primitive arrays can provide memory/locality benefits.
405. Explain why JVM implementation assumptions should not be hard-coded into application architecture.

---

# 2.1.21 Primitive Values and Threads

## Basic

406. Is a primitive field automatically thread-safe?
407. Is `count++` atomic?
408. What operations conceptually make up `count++`?

## Intermediate

409. Explain read-modify-write.
410. Why can two threads lose increments?
411. What tools can provide safer updates?
   - `AtomicInteger`
   - `synchronized`
   - `Lock`
   - `LongAdder`

## Advanced

412. Compare `AtomicInteger` and `LongAdder`.
413. Explain when contention matters.
414. Explain visibility versus atomicity.
415. Explain why primitive-ness does not imply thread safety.

## Traps

416. Does `int` being a primitive make `count++` safe?
417. Does `volatile int` make increment atomic?
418. Is `AtomicInteger` simply a wrapper with no concurrency semantics?

## Debugging

419. Reproduce lost updates with a shared counter.
420. Fix the counter with `AtomicInteger`.
421. Fix it with synchronization.
422. Compare the alternatives.

## Production

423. Design a high-throughput metrics counter.
424. Choose between `AtomicLong` and `LongAdder`.
425. Explain the throughput and contention trade-offs.

---

# 2.1.22 Common Mistakes — Diagnostic Questions

426. Why is assuming integer overflow throws an exception dangerous?
427. Why does forgetting byte/short/char promotion cause compilation problems?
428. Why is forgetting `L` dangerous for large literals?
429. Why is forgetting `f` dangerous for float literals?
430. Why is using `double` for exact money dangerous?
431. Why is misunderstanding `Float.MIN_VALUE` dangerous?
432. Why is treating `char` as a complete Unicode character dangerous?
433. Why is blindly using floating-point `==` dangerous?
434. Why is ignoring NaN dangerous?
435. Why is ignoring signed zero potentially dangerous?
436. Why is assuming casts are lossless dangerous?
437. Why is assuming primitives automatically provide thread safety dangerous?
438. Why is the "primitive = stack" model misleading?

---

# 2.1.23 Performance

## Basic

439. Why can primitives avoid wrapper-object overhead?
440. What is the difference between `int[]` and `Integer[]`?
441. What is memory locality?
442. Why can primitive arrays have favorable locality?

## Intermediate

443. Compare the memory implications of:

```java
int[] values
```

and:

```java
Integer[] values
```

444. Why can wrapper arrays involve more references and objects?
445. How can object allocation affect throughput?

## Advanced

446. Explain cache locality at a high level.
447. Explain why compact primitive arrays can benefit numerical processing.
448. Explain why source-level assumptions about CPU instructions can be misleading.
449. Explain why real workloads should be measured.

## Traps

450. Is primitive code always faster?
451. Is a smaller primitive always better?
452. Does one benchmark prove a general JVM performance rule?

## Production

453. Decide whether changing wrappers to primitives is worth the complexity.
454. Design a JMH benchmark comparing primitive and wrapper arrays.
455. Explain what measurements matter.

---

# 2.1.24 Production Use Cases

## Basic

456. Choose an appropriate primitive for binary data.
457. Choose one for ordinary counters.
458. Choose one for timestamps.
459. Choose one for scientific calculations.
460. Choose one for binary state.

## Intermediate

461. Why should range influence type selection?
462. Why should precision influence type selection?
463. Why should concurrency influence type selection?
464. Why should persistence and serialization influence type selection?
465. Why should interoperability influence type selection?

## Advanced

466. Design a primitive-type policy for a large Java service.
467. Decide when a primitive should be replaced by a wrapper.
468. Decide when a primitive should be replaced by `BigDecimal`.
469. Decide when a primitive should be replaced by an enum.
470. Decide when a primitive should be replaced by a domain value object.

## Production Review

471. For every selected primitive, answer:
   1. What is the maximum value?
   2. What is the minimum value?
   3. Can it grow?
   4. Can arithmetic overflow?
   5. Is exact arithmetic required?
   6. Is decimal precision required?
   7. Is it accessed concurrently?
   8. Does memory footprint matter?
   9. Is interoperability involved?
   10. Does persistence use a different range?
   11. Is serialization involved?
   12. Are negative values valid?
   13. Is it a code/unit rather than a mathematical quantity?
   14. Will it cross an API boundary?

---

# 2.1.25 Debugging Challenges

## Challenge 1 — Overflow

472. Predict:

```java
int total = 2_000_000_000;
total += 2_000_000_000;
System.out.println(total);
```

473. Explain the result.
474. Fix it with `long`.
475. Fix it with `Math.addExact()`.

## Challenge 2 — Integer Promotion

476. Predict:

```java
byte a = 100;
byte b = 27;
byte result = (byte) (a + b);
```

477. Why is the cast required?
478. Is information lost?
479. Remove the cast and explain the compiler error.

## Challenge 3 — Long Literal

480. Explain:

```java
long value = 3_000_000_000;
```

481. Why does it fail?
482. Fix it.

## Challenge 4 — Floating Point

483. Predict:

```java
double total = 0.1 + 0.2;
System.out.println(total);
System.out.println(total == 0.3);
```

484. Explain the result.
485. Design a better comparison.

## Challenge 5 — NaN

486. Predict:

```java
double x = Double.NaN;
System.out.println(x == x);
```

487. Explain the result.
488. Show the correct NaN check.

## Challenge 6 — Character Promotion

489. Predict:

```java
char c = 'A';
System.out.println(c + 1);
```

490. What is the result type?
491. What is the output?
492. Why?

## Challenge 7 — Compound Assignment

493. Compare:

```java
byte x = 1;
x += 2;
```

with:

```java
byte x = 1;
x = x + 2;
```

494. Explain the language rule.
495. Modify the example so that information loss occurs.

---

# 2.1.26 Coding Exercises

## Basic

496. Print the range of every integral primitive.
497. Print floating-point constants and special values.
498. Demonstrate byte overflow.
499. Demonstrate short overflow.
500. Demonstrate int overflow.
501. Demonstrate long overflow.
502. Demonstrate integer underflow.
503. Demonstrate floating-point underflow.
504. Demonstrate char-to-int conversion.
505. Demonstrate boolean short-circuiting.

## Intermediate

506. Build a numeric-promotion demonstration.
507. Demonstrate widening conversions.
508. Demonstrate narrowing conversions.
509. Demonstrate information loss from narrowing.
510. Demonstrate NaN and infinity.
511. Demonstrate positive and negative zero.
512. Demonstrate Unicode surrogate pairs.
513. Compare `int[]` and `Integer[]`.
514. Implement safe arithmetic using `Math.*Exact()`.

## Advanced

515. Build an integer overflow detector.
516. Implement safe `long` to `int` conversion.
517. Implement absolute floating-point comparison.
518. Implement relative floating-point comparison.
519. Implement ULP-aware floating-point comparison.
520. Build fixed-point money using minor currency units.
521. Compare fixed-point, `double`, and `BigDecimal`.
522. Benchmark primitive arrays versus wrapper arrays using JMH.
523. Inspect bytecode for primitive arithmetic.
524. Investigate JIT optimizations involving primitive values.

## Production-Style

525. Build a **High-Throughput Metrics Counter** that:
   - tracks request count
   - tracks error count
   - tracks latency
   - prevents counter overflow
   - supports concurrent updates
   - compares `long`, `AtomicLong`, and `LongAdder`
   - benchmarks under contention
   - explains memory and throughput trade-offs

---

# 2.1.27 Interview Questions

## Basic

526. What are Java's eight primitive types?
527. What is the range of `byte`?
528. What is the range of `int`?
529. What is the range of `long`?
530. What is the difference between `float` and `double`?
531. What is the range of `char`?
532. What values can `boolean` hold?
533. What is integer overflow?

## Intermediate

534. Why does `byte + byte` produce `int`?
535. What is integer promotion?
536. Why can a long calculation unexpectedly overflow as `int`?
537. Why is `f` required for float literals?
538. Why is `L` used for long literals?
539. What is widening conversion?
540. What is narrowing conversion?
541. Why does compound assignment behave differently?
542. Why can floating-point equality be problematic?

## Advanced

543. Explain binary numeric promotion.
544. Explain integer overflow semantics.
545. Explain IEEE 754 at a high level.
546. Why does `0.1 + 0.2` not exactly equal `0.3`?
547. What is NaN?
548. Why is NaN not equal to itself?
549. What is floating-point underflow?
550. What is a subnormal number?
551. What does `char` actually represent?
552. Why can one Unicode code point require two `char`s?

## Senior / Production

553. When should you use `int` versus `long`?
554. When should you use primitives versus wrappers?
555. When is `double` inappropriate?
556. How would you design safe monetary arithmetic?
557. How can integer overflow become a production/security issue?
558. How would you detect overflow in a high-throughput service?
559. Why is `count++` unsafe under concurrent access?
560. How would you choose `AtomicLong` versus `LongAdder`?
561. How can primitive-array layout affect cache behavior?
562. What JVM memory assumptions about primitives are unsafe?

---

# 2.1.28 Advanced Follow-Ups

## Java Language Specification

563. What does the JLS define about primitive types?
564. What does it define about numeric types?
565. What are numeric promotion rules?
566. What are widening conversions?
567. What are narrowing conversions?
568. What are assignment conversions?
569. What special rules apply to compound assignment?
570. How do equality operators interact with numeric types?
571. What floating-point semantics are language-defined?

## JVM

572. Trace:

```text
Java source
    ↓
javac
    ↓
Bytecode
    ↓
JVM
    ↓
Interpreter / JIT
    ↓
Machine code
```

573. What are JVM local-variable slots?
574. How are primitive fields represented conceptually?
575. How are primitive arrays represented conceptually?
576. What bytecodes can represent integer arithmetic?
577. What bytecodes can represent floating-point arithmetic?
578. What is JIT compilation?
579. What is escape analysis?
580. What is register allocation?
581. Why can JVM optimizations invalidate simplistic memory assumptions?

## IEEE 754

582. What is the sign component?
583. What is the exponent?
584. What is the significand?
585. What is a normalized value?
586. What is a subnormal value?
587. What is NaN?
588. What is infinity?
589. What is signed zero?
590. What is rounding?
591. How can numerical error propagate?

---

# 2.1.29 OpenJDK / Specification Investigation

## Specification

592. Study the JLS primitive-type rules.
593. Study JLS numeric-type rules.
594. Study JLS numeric-promotion rules.
595. Study JLS conversions and contexts.
596. Study JVM specification rules related to primitive types and values.

## API Investigation

597. Investigate `java.lang.Math`.
598. Investigate `java.lang.StrictMath`.
599. Investigate `Integer`.
600. Investigate `Long`.
601. Investigate `Float`.
602. Investigate `Double`.
603. Investigate `Character`.

## Implementation Investigation

604. Find the relevant OpenJDK implementation.
605. Inspect generated bytecode.
606. Compare source code with bytecode.
607. Identify where implementation details differ from simplified mental models.
608. Explain which observations are language guarantees and which are implementation details.

---

# 2.1.30 Production Review Scenarios

## Scenario A — Request Counter

609. A service currently uses `int` for request count. The service may run continuously for years. Is this safe?
610. What happens if the counter overflows?
611. Would `long` be enough?
612. Should overflow be detected explicitly?

## Scenario B — Money

613. A developer stores currency as `double`. Review the design.
614. What questions should you ask before changing it?
615. Compare `long` minor units and `BigDecimal`.

## Scenario C — Unicode

616. A developer uses `char` to count user-visible characters. Review the implementation.
617. What Unicode issue could occur?
618. What APIs should be investigated?

## Scenario D — Concurrent Counter

619. A developer writes:

```java
class Counter {
    int count;

    void increment() {
        count++;
    }
}
```

620. Review it for concurrency.
621. What failure can occur?
622. Which alternative would you choose under high contention?

## Scenario E — Large Calculation

623. A developer writes:

```java
long result = a * b;
```

where both operands are `int`.

624. What type is the multiplication performed in?
625. Can overflow happen before assignment?
626. How would you fix it?

---

# 2.1.31 Final Mastery Gate

## Primitive Fundamentals

627. Explain all eight primitive types.
628. State all integral ranges from memory.
629. Explain signed two's-complement integers.
630. Explain floating-point representation at a high level.
631. Explain `char` as a UTF-16 code unit.
632. Explain boolean semantics.

## Numeric Operations

633. Explain integer promotion.
634. Explain binary numeric promotion.
635. Explain compound-assignment conversion.
636. Explain widening conversions.
637. Explain narrowing conversions.
638. Predict expression result types without running code.

## Overflow / Underflow

639. Demonstrate integer overflow.
640. Demonstrate integer underflow.
641. Detect overflow using `Math.*Exact()`.
642. Explain floating-point underflow.
643. Explain subnormal values.

## Floating Point

644. Explain decimal/binary representation.
645. Explain `0.1 + 0.2`.
646. Explain NaN.
647. Explain infinity.
648. Explain signed zero.
649. Compare floating-point values appropriately.
650. Explain when `BigDecimal` or fixed-point representation is preferable.

## Memory / Runtime

651. Explain primitive fields.
652. Explain primitive arrays.
653. Explain JVM local-variable slots.
654. Avoid the "all primitives live on the stack" misconception.
655. Explain primitive concurrency limitations.

## Production

656. Select the correct primitive for a real requirement.
657. Identify overflow risks.
658. Identify precision risks.
659. Identify concurrency risks.
660. Explain memory/performance trade-offs.
661. Debug primitive arithmetic bugs.
662. Benchmark when performance matters.

## Interview

663. Answer basic questions.
664. Answer intermediate questions.
665. Answer advanced questions.
666. Answer senior/production questions.
667. Explain numeric promotion from first principles.
668. Explain floating-point precision from first principles.

---

# 2.1.32 Final Integrated Challenge

Build a **Production-Grade Metrics and Numerical Processing Component**.

## Requirements

### Numeric Model

669. Use appropriate primitives for:
   - counters
   - timestamps
   - IDs
   - latency
   - byte counts
   - status flags

### Safety

670. Prevent integer overflow.
671. Detect unsafe narrowing conversions.
672. Handle NaN.
673. Handle infinity.
674. Define floating-point comparison rules.
675. Document precision assumptions.

### Concurrency

676. Implement a concurrent counter.
677. Compare `AtomicLong` and `LongAdder`.
678. Benchmark under contention.
679. Explain visibility and atomicity.

### Memory

680. Compare primitive arrays and wrapper arrays.
681. Measure memory implications.
682. Explain locality.
683. Explain why "primitive = stack" is an oversimplification.

### Unicode

684. Process text containing supplementary Unicode code points.
685. Demonstrate why one `char` is not necessarily one visible character.

### Debugging

686. Intentionally introduce:
   - integer overflow
   - integer promotion bug
   - long-literal bug
   - floating-point comparison bug
   - NaN bug
   - Unicode `char` bug
   - compound-assignment overflow
   - concurrent counter bug

687. Predict the failures.
688. Run the code.
689. Explain each failure.
690. Fix each failure.
691. Explain the production implications.

### Performance

692. Benchmark primitive arrays versus wrapper arrays.
693. Benchmark concurrent counters.
694. Inspect bytecode for selected arithmetic.
695. Explain which observations are guaranteed by Java and which are JVM implementation details.

---

# 2.1.33 Senior Design Questions

696. Why is `int` usually the default integer choice?
697. When is `long` mandatory rather than merely preferable?
698. When is `byte` appropriate in a production system?
699. Why is `short` rarely chosen for ordinary application logic?
700. When is `double` appropriate?
701. When should `double` be rejected?
702. When should a domain use `BigDecimal`?
703. When should a domain use integer minor units?
704. How do database ranges affect primitive selection?
705. How do serialization formats affect primitive selection?
706. How do API boundaries affect primitive selection?
707. How can overflow become a security vulnerability?
708. How can primitive representation affect performance?
709. When is optimizing primitive representation premature?
710. How would you review primitive-type choices in a production Java service?

---

# Final Module Status

- [ ] NOT STARTED
- [ ] LEARNING
- [ ] IMPLEMENTED
- [ ] TESTED
- [ ] INTERNALS UNDERSTOOD
- [ ] EDGE CASES MASTERED
- [ ] PRODUCTION READY
- [ ] INTERVIEW READY
- [ ] DEEP MASTERY COMPLETE
