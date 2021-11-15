import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {
	@Test
	public void testSolution() throws InterruptedException {
		AtomicReference<String> threadName = new AtomicReference<>();
		CountDownLatch latch = new CountDownLatch(1);
		Task task = new Task() {
			@Override
			void runThis() {
				threadName.set(Thread.currentThread().getName());
				latch.countDown();
			}
		};
		assertThat(threadName).hasValue(null);
		task.startThread();
		ConcurrentAssertions.waitFor(latch);
		assertThat(threadName.get()).startsWith("Thread-");
	}
}