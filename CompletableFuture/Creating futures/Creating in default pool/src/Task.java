import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class Task {

	<T> CompletableFuture<T> async(Supplier<T> supplier) {
		throw new UnsupportedOperationException("TODO");
	}
}