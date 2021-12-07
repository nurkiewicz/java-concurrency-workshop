import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tests11 implements WithAssertions {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final BlockingQueue<CustomerJob> queue = new ArrayBlockingQueue<>(1000000);
	private final Events<String> events = new Events<>();

	@Test
	public void singleTaskIsConsumed() throws Exception {
		CountDownLatch waiting = new CountDownLatch(1);
		queue.add(new CustomerJob(1, () -> {
			Sleeper.sleepRandomly(Duration.ofMillis(100));
			waiting.countDown();
		}));
		consumeAsync();
		ConcurrentAssertions.waitFor(waiting, "task to finish");
	}

	@Test
	public void twoTasksWithDifferentKeyMustRunConcurrently() {
		CountDownLatch startLatch = new CountDownLatch(2);
		CountDownLatch completeLatch = new CountDownLatch(2);
		queue.add(new CustomerJob(1, task2(startLatch, completeLatch)));
		queue.add(new CustomerJob(2, task2(startLatch, completeLatch)));
		consumeAsync();
		ConcurrentAssertions.waitFor(completeLatch, "tasks to finish concurrently");
	}

	@RepeatedTest(10)
	@Disabled
	public void randomizedTest() {
		List<Semaphore> semaphores = IntStream.range(0, 10).mapToObj(x -> new Semaphore(1)).collect(Collectors.toList());
		final int KEYS = 10;
		final int TOTAL = 10_000;
		CountDownLatch total = new CountDownLatch(TOTAL);
		IntStream.range(0, TOTAL).mapToObj(x -> new CustomerJob(x % KEYS, () -> {
					try {
						Semaphore semaphore = semaphores.get(x % KEYS);
						ConcurrentAssertions.tryAcquire(semaphore, "jobs running concurrently with key " + x % KEYS);
						try {
							Sleeper.sleepRandomly(Duration.ofMillis(1));
							total.countDown();
						} finally {
							semaphore.release();
						}
					} catch (Exception e) {
						events.log(e.toString());
					}
				}))
				.parallel()
				.forEach(queue::add);
		consumeAsync();
		ConcurrentAssertions.waitFor(total, "All " + TOTAL + " tasks to finish", Duration.ofSeconds(30));
		assertThat(events.drainToList()).isEmpty();
	}

	private Runnable task2(CountDownLatch startLatch, CountDownLatch completeLatch) {
		return () -> {
			try {
				startLatch.countDown();
				ConcurrentAssertions.waitFor(startLatch, "all tasks to start", Duration.ofSeconds(2));
				events.log("Started");
				Sleeper.sleepRandomly(Duration.ofMillis(100));
				events.log("Done");
				completeLatch.countDown();
			} catch (Exception e) {
				events.log(e.toString());
			}
		};
	}

	@Test
	public void twoTasksWithSameKeyCantRunConcurrently() {
		Semaphore noConcurrency = new Semaphore(1);
		CountDownLatch waiting = new CountDownLatch(2);
		queue.add(new CustomerJob(1, () -> task(noConcurrency, waiting)));
		queue.add(new CustomerJob(1, () -> task(noConcurrency, waiting)));
		consumeAsync();
		ConcurrentAssertions.waitFor(waiting, "tasks to finish concurrently");
	}

	private void task(Semaphore noConcurrency, CountDownLatch waiting) {
		ConcurrentAssertions.tryAcquire(noConcurrency, "running CustomerJobs concurrently");
		try {
			Sleeper.sleepRandomly(Duration.ofMillis(100));
			events.log("Done");
		} finally {
			noConcurrency.release();
		}
		waiting.countDown();
	}

	private void consumeAsync() {
		CompletableFuture.supplyAsync(() -> {
			try {
				new Task().consume(queue);
			} catch (Exception e) {
				log.error("Opps", e);
			}
			return null;
		});
	}

}