import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

public class Task {

	<T> CompletableFuture<T> async(Supplier<T> supplier, ExecutorService executorService) {
		return CompletableFuture.supplyAsync(supplier, executorService);
	}
}