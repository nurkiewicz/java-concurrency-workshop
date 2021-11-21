When `loadFromDb()` completes, invoke `parse()` on its result.
Notice that the result should be an asynchronous `CompletableFuture` as well.

<div class="hint">

Use `CompletableFuture.thenCompose()`.

</div>