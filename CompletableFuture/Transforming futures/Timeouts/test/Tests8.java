import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests8 {

	@Test
	public void testSolution() throws ExecutionException, InterruptedException, TimeoutException {
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
}