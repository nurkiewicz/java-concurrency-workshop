import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {

	@Test
	public void testSolution() throws ExecutionException, InterruptedException, TimeoutException {
		CompletableFuture<Integer> f = new Task().firstOf(new FutureFailingWhenBlocked<>(), CompletableFuture.completedFuture(1));
		assertThat(f.get(1, TimeUnit.SECONDS)).isEqualTo(1);
	}

	@Test
	public void testSolution2() throws ExecutionException, InterruptedException, TimeoutException {
		CompletableFuture<Integer> f = new Task().firstOf(CompletableFuture.completedFuture(2), new FutureFailingWhenBlocked<>());
		assertThat(f.get(1, TimeUnit.SECONDS)).isEqualTo(2);
	}

	@Test
	public void doesNotBlock() throws ExecutionException, InterruptedException, TimeoutException {
		CompletableFuture<Integer> f = new Task().firstOf(new FutureFailingWhenBlocked<>(), new FutureFailingWhenBlocked<>());
	}

}