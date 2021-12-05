import java.lang.invoke.MethodHandles;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tests implements WithAssertions {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	public static final int TOTAL_TASKS = 4;

	private final Events<String> events = new Events<>();
	private final Events<Exception> errors = new Events<>();

	@Test
	public void testSolution() {
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
		Semaphore concurrencyControl = new Semaphore(1);
		new Task().consume(queue);

		CountDownLatch waitForAll = new CountDownLatch(1);
		CountDownLatch howManyPending = new CountDownLatch(TOTAL_TASKS);
		IntStream.rangeClosed(1, TOTAL_TASKS).forEach(i ->
				queue.add(ErrorCollecting.collectErrors(errors, () -> {
					events.log("+ " + i);
					ConcurrentAssertions.acquire(concurrencyControl, "concurrent jobs being processed");
					try {
						ConcurrentAssertions.waitFor(waitForAll, "all jobs to be processed");
					} finally {
						concurrencyControl.release();
						events.log("- " + i);
						howManyPending.countDown();
					}
				})));

		waitForAll.countDown();
		ConcurrentAssertions.waitFor(howManyPending, "all jobs to terminate");
		assertThat(errors.drainToList()).isEmpty();
		assertThat(events.drainToList()).containsExactly(
				"+ 1", "- 1",
				"+ 2", "- 2",
				"+ 3", "- 3",
				"+ 4", "- 4"
		);
	}
}