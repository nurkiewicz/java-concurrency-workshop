import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {

	@Test
	public void testSolution() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		Thread thread = new Task() {
			@Override
			void runThis() {
				latch.countDown();
			}
		}.createThread();
		assertThat(thread).isNotNull();
		assertThat(thread).isOfAnyClassIn(Thread.class);
		thread.start();
		ConcurrentAssertions.waitFor(latch, "condition");
	}
}