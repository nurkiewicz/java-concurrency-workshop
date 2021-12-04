Create a single-threaded thread pool with a queue of **5** tasks.
When too many tasks are submitted, throw `SystemOverflowException`.

<div class="hint">
  You can pass custom `RejectedExecutionHandler` to the `ThreadPoolExecutor`'s constructor.
</div>
