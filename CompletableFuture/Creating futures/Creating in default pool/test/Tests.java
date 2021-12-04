import java.util.concurrent.CompletableFuture;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

public class Tests implements WithAssertions {

	@Test
	public void testSolution() {
		CompletableFuture<String> future = new Task().async(() -> Thread.currentThread().getName());

		assertThat(ConcurrentAssertions.waitFor(future)).startsWith("ForkJoinPool.commonPool-");
	}
}