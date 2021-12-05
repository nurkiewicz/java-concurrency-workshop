import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests8 {

	public static final int COMPLETION_AFTER = 200;

	@Test
	public void testSolution() throws Exception {
		CompletableFuture<Object> future = new Task().applyTimeout(new CompletableFuture<>(), Duration.ofMillis(10));
		try {
			future.get(10 + 100, TimeUnit.MILLISECONDS);
			Assertions.failBecauseExceptionWasNotThrown(TimeoutException.class);
		} catch (TimeoutException e) {
			//expected
		} catch (ExecutionException e) {
			assertThat(e).hasCauseInstanceOf(TimeoutException.class);
			//expected
		}

	}

	@Test
	public void testTimeoutIsNotTooShort() throws Exception {
		int expected = 42;
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(COMPLETION_AFTER);
				return expected;
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});
		CompletableFuture<Integer> withTimeout = new Task().applyTimeout(future, Duration.ofMillis(COMPLETION_AFTER * 2));
		Integer result = withTimeout.get(COMPLETION_AFTER * 2, TimeUnit.MILLISECONDS);
		assertThat(result).isEqualTo(expected);

	}
}