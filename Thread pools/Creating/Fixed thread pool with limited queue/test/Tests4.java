import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.IntStream;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import static org.awaitility.Awaitility.await;

public class Tests4 implements WithAssertions {

	private final Events<String> events = new Events<>();

	@Test
	public void poolAcceptsFourTasks() throws InterruptedException {
		ExecutorService executorService = new Task().create();
		CountDownLatch firstStage = new CountDownLatch(4);
		CountDownLatch secondStage = new CountDownLatch(1);
		IntStream.range(0, 4).forEach(i ->
				executorService.submit((UncheckedRunnable) () -> {
					events.log("Task " + i + " started");
					firstStage.countDown();
					ConcurrentAssertions.waitFor(secondStage, "first 4 tasks to fire");
				}));
		ConcurrentAssertions.waitFor(firstStage, "first 4 tasks started");
		assertThat(events.drainToList())
				.containsExactlyInAnyOrder("Task 0 started", "Task 1 started", "Task 2 started", "Task 3 started");
	}

	@Test
	public void poolAcceptsTwoTasksInAQueue() throws InterruptedException {
		ExecutorService executorService = new Task().create();
		CountDownLatch firstStage = new CountDownLatch(4);
		CountDownLatch secondStage = new CountDownLatch(1);
		IntStream.range(0, 6).forEach(i ->
				executorService.submit((UncheckedRunnable) () -> {
					events.log("Task " + i + " started");
					firstStage.countDown();
					ConcurrentAssertions.waitFor(secondStage, "first 4 tasks to fire");
				}));
		ConcurrentAssertions.waitFor(firstStage, "first 4 tasks started");
		assertThat(events.drainToList())
				.containsExactlyInAnyOrder("Task 0 started", "Task 1 started", "Task 2 started", "Task 3 started");
		secondStage.countDown();
		await().untilAsserted(() -> assertThat(events.toList()).containsExactlyInAnyOrder("Task 4 started", "Task 5 started"));
	}

	@Test
	public void failsToAccept7thTask() throws InterruptedException {
		ExecutorService executorService = new Task().create();
		CountDownLatch firstStage = new CountDownLatch(4);
		CountDownLatch secondStage = new CountDownLatch(1);
		IntStream.range(0, 6).forEach(i ->
				executorService.submit((UncheckedRunnable) () -> {
					events.log("Task " + i + " started");
					firstStage.countDown();
					ConcurrentAssertions.waitFor(secondStage, "first 4 tasks to fire");
				}));
		ConcurrentAssertions.waitFor(firstStage, "first 4 tasks started");
		try {
			executorService.submit(() -> {
			});
			failBecauseExceptionWasNotThrown(RejectedExecutionException.class);
		} catch (RejectedExecutionException expected) {

		}
	}

}