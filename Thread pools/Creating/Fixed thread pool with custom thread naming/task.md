Create a thread pool where threads are named `CustomPool-NUMBER`.
Pool should have *at least 3 threads*. 
`NUMBER` should be a consecutive, unique number.
This can be done by passing an instance of a `ThreadFactory` as a last argument to `ThreadPoolExecutor`'s constructor.
Luckily there's a `com.google.common.util.concurrent.ThreadFactoryBuilder` in Guava that implements this interface.

<div class="hint">
  Check out `setNameFormat()` method with `%d` argument in `ThreadFactoryBuilder`.
</div>
