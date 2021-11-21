import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Task {

	<T> CompletableFuture<T> applyTimeout(CompletableFuture<T> original, Duration timeout) {
//		throw new UnsupportedOperationException("TODO");
		return original.orTimeout(timeout.toMillis(), MILLISECONDS);
	}

}