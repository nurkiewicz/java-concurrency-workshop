import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ConcurrentAssertions {

	public static void waitFor(CountDownLatch latch) throws InterruptedException {
		assertThat(latch.await(5, TimeUnit.SECONDS))
				.describedAs("Waiting for condition")
				.isTrue();
	}

}
