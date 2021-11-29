Multiple threads are waiting to start at the same time.
Implement such a synchronization using `CountDownLatch`.

<div class="hint">

When `CountDownLatch` goes from `1` to `0`, all waiting threads are woken up.

</div>