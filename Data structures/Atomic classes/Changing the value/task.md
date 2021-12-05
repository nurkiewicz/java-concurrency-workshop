# Changing value atomically

Consider the following integer:

```java
AtomicInteger atom = new AtomicInteger(0);
```

This is not thread safe (lost update):

```java
atom.set(atom.get() * 2);
```
This is thread safe, but annoying:

```java
int current;
do {
	current = atom.get();
} while(!atom.compareAndSet(current, current * 2));
```

# Your task

Rewrite the `next()` method in such a way that it works even when invoked from multiple threads.
Can you write it as one-liner?

<div class="hint">
  See `AtomicInteger.updateAndGet()`.
</div>


Tests in this exercise use [Collatz conjecture](https://en.wikipedia.org/wiki/Collatz_conjecture) formula.