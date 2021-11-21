import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class Task {

	CompletableFuture<String> create(ExecutorService executorService) {
//		throw new UnsupportedOperationException("TODO");
		return CompletableFuture.supplyAsync(() -> Thread.currentThread().getName(), executorService);
	}
}