import java.util.concurrent.CompletableFuture;

public class Task {

	CompletableFuture<Integer> transform(CompletableFuture<String> future) {
        return future.thenApply(Integer::parseInt);
    }

}