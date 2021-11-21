"Wait" for both `date` and `time` futures, without blocking.
Create a `CompletableFuture<LocalDateTime>` that combines the results of both.

It's tempting to do this:

```java
return CompletableFuture.completedFuture
        (date.join().atTime(time.join()));
```
But it's completely broken

<div class="hint">

* Use `CompletableFuture.allOf()`.
* You will also need `CompletableFuture.join()` but here you are guaranteed it won't block.

</div>