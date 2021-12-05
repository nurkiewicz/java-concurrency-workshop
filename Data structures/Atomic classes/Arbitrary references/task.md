# `AtomicReference`

**Warning**: this task is tricky.

Using `AtomicReference` try to implement a singleton pattern.
`Singleton` should be created lazily and only once.
Even if multiple threads are calling `getOrCreate` method concurrently.

<div class="hint">
  This is actual impossible with `AtomicReference` alone, without any extra synchronization (!)
</div>
