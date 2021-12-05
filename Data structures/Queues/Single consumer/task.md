When there's just a single consumer of a queue, jobs are run sequentially, not concurrently.
Consume the incoming queue of jobs in such a way that two jobs never run concurrently.
Do not use any locking/synchronization mechanism.

<div class="hint">
  Jobs can run concurrently only if more than two threads are processing the same queue.
</div>
