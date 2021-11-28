Implement a consumer that fetches and runs `Job`s from a queue.
When there are no jobs to run, it should wait indefinitely.
When consumer is interrupted, it should rethrow that exception.

<div class="hint">

Use `BlockingQueue.take()` in a loop.

</div>