import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Task {

	<T> CompletableFuture<T> applyTimeout(CompletableFuture<T> original, Duration timeout) {
		throw new UnsupportedOperationException("TODO");
	}

}