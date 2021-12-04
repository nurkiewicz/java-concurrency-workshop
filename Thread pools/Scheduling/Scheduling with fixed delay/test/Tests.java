import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {

	@Test
	public void testSolution() {
		ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
		CountDownLatch done = new CountDownLatch(1);
		new Task().schedule(scheduledExecutorService, done::countDown);
		assertThat(scheduledExecutorService.getQueue()).hasSize(1);
		ConcurrentAssertions.waitFor(done, "scheduled task to be executed", Duration.ofSeconds(5));
		assertThat(scheduledExecutorService.getQueue()).hasSize(1);
	}
}