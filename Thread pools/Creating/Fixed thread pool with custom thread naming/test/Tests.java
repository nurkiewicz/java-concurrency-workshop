import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

public class Tests implements WithAssertions {

	public static final int THREAD_COUNT = 3;
	private final Events<String> events = new Events<>();

	@Test
	public void testThreadPrefix() {
		ExecutorService executorService = new Task().create();
		String threadName = ConcurrentAssertions.waitFor(executorService.submit(() -> Thread.currentThread().getName()));
		assertThat(threadName)
				.startsWith("CustomPool-")
				.matches("CustomPool-\\d+");
	}

	@Test
	public void testNamesAreUnique() {
		ExecutorService executorService = new Task().create();
		CountDownLatch waitForAll = new CountDownLatch(3);
		for (int i = 0; i < THREAD_COUNT; i++) {
			executorService.submit(() -> {
				events.log(Thread.currentThread().getName());
				waitForAll.countDown();
			});
		}
		ConcurrentAssertions.waitFor(waitForAll, "all tasks");
		List<String> threadNames = events.drainToList();
		assertThat(threadNames)
				.hasSize(THREAD_COUNT)
				.doesNotHaveDuplicates();
	}
}