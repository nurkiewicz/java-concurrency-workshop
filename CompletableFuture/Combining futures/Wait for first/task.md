You have two futures from with the same response from two different servers.
One from a server nearby.
Another from a server on another continent.
You don't know which one is which.
You only care about the very first response.

Create a `CompletableFuture` that completes when the first future completes.
No matter which one.

<div class="hint">

Use `CompletableFuture.applyToEither()` or `CompletableFuture.anyOf()` 

</div>