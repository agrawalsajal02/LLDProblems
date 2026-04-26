# Singleton

## What it is
- Singleton ensures only one instance of a class exists in the JVM and gives a single global access point to it.

## Real interview framing
- Interviewers usually care about 2 things:
- Can you make sure only one object is created?
- Can you do it safely in multithreaded code?

## Use when
- You truly need one shared instance for the whole application.
- Examples: config registry, in-memory coordinator, metrics registry, cache metadata holder.

## Do not use when
- You are inside Spring or another DI framework that already manages singleton scope.
- You just want "easy global access".
- You want highly testable code with isolated dependencies.

## Files in this folder
- `SimpleLazySingleton.java`
- `EagerSingleton.java`
- `SynchronizedSingleton.java`
- `DoubleCheckedLockingSingleton.java`
- `BillPughSingleton.java`
- `EnumSingleton.java`
- `SingletonDemo.java`

## Variants

### 1. `SimpleLazySingleton`
```java
if (instance == null) {
    instance = new SimpleLazySingleton();
}
```
- Idea: object banao only when first needed.
- Pros:
- Very easy to write and explain.
- Lazy initialization.
- Cons:
- Not thread-safe.
- Two threads can create two objects at the same time.
- Use:
- Only in strictly single-threaded code or as a teaching example.
- Production:
- Almost never for backend systems.

### 2. `EagerSingleton`
```java
private static final EagerSingleton INSTANCE = new EagerSingleton();
```
- Idea: class load hote hi object bana do.
- Pros:
- Very simple.
- Thread-safe because class loading is safe.
- No synchronization overhead.
- Cons:
- Object is created even if never used.
- Bad if construction is expensive.
- Use:
- When singleton is cheap and definitely required.
- Production:
- Fine for small utility-style singletons.

### 3. `SynchronizedSingleton`
```java
public static synchronized SynchronizedSingleton getInstance() { ... }
```
- Idea: `getInstance()` ko synchronized kar do.
- Pros:
- Easy to reason about.
- Thread-safe.
- Cons:
- Every call takes synchronization cost.
- Simpler than DCL, but less efficient under heavy access.
- Use:
- When simplicity matters more than performance.
- Production:
- Acceptable, but usually not the final preferred Java version.

### 4. `DoubleCheckedLockingSingleton`
```java
if (instance == null) {
    synchronized (DoubleCheckedLockingSingleton.class) {
        if (instance == null) {
            instance = new DoubleCheckedLockingSingleton();
        }
    }
}
```
- Idea: first normal null check, then lock, then second null check.
- Pros:
- Lazy initialization.
- Thread-safe.
- Less locking after instance is created.
- Cons:
- More complex.
- Must use `volatile`, warna instruction reordering bugs aa sakte hain.
- Easy to get wrong in interviews if rushed.
- Use:
- When you need lazy + thread-safe + want to reduce synchronized overhead.
- Production:
- Works, but many teams prefer cleaner options.

### 5. `BillPughSingleton`
```java
private static class Holder {
    private static final BillPughSingleton INSTANCE = new BillPughSingleton();
}
```
- Idea: inner static holder class lazy-loaded hoti hai only when accessed.
- Pros:
- Lazy initialization.
- Thread-safe.
- No explicit synchronized method.
- Cleaner than double-checked locking.
- Cons:
- Slightly less obvious if someone has never seen it before.
- Use:
- Plain Java applications where you want a clean lazy singleton.
- Production:
- Very common and very solid Java answer.

### 6. `EnumSingleton`
```java
public enum EnumSingleton {
    INSTANCE;
}
```
- Idea: enum constant itself is the singleton.
- Pros:
- Simplest robust Java-specific singleton.
- Safe against serialization issues.
- Safer against reflection-related singleton breaking than classic implementations.
- Very small code.
- Cons:
- Enum style does not fit every design.
- Not lazy in the same way people usually expect from holder-based patterns.
- Use:
- When Java-specific simplicity and correctness matter most.
- Production:
- Often the best pure-Java singleton answer.

## Which one is used most in production?
- In modern Java code, `EnumSingleton` and `BillPughSingleton` are the most respected manual implementations.
- `EagerSingleton` is also fine when object creation is cheap.
- `DoubleCheckedLockingSingleton` is used, but less loved because it is harder to read and easier to mess up.
- `SimpleLazySingleton` is mostly for teaching.
- In Spring apps, the most common real answer is:
- "Let the framework manage singleton scope instead of hand-writing singleton logic."

## Quick recommendation
- If interviewer asks "best Java singleton?" -> say `EnumSingleton`.
- If interviewer asks "lazy and thread-safe singleton?" -> say `BillPughSingleton`.
- If interviewer asks "show classic concurrency-safe singleton?" -> `DoubleCheckedLockingSingleton` with `volatile`.

## Where people go wrong
- They write lazy singleton without thread safety.
- They forget `volatile` in double-checked locking.
- They use singleton when dependency injection would be cleaner.
- They hide too much global mutable state inside singleton objects.

## Memory hook
- "Simple is unsafe, eager is easy, synchronized is safe-but-heavy, DCL is tricky, Bill Pugh is clean lazy, enum is the strongest Java answer."
