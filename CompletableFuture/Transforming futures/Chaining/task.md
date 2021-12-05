When `loadFromDb()` completes, invoke `parse()` on its result.
Notice that the result should be an asynchronous `CompletableFuture` as well.
Keep in mind that when working with `CompletableFuture`, blocking operations should be used as a last resort only.

<div class="hint">

Use `CompletableFuture.thenCompose()`.

</div>