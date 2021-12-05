import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class Tests10 implements WithAssertions {

	@Test
	public void smokeTest() {
		//given
		Task task = new Task();

		//expect
		assertThat(task.tryLock()).isEqualTo(true);
		assertThat(task.tryLock()).isEqualTo(false);
		assertThat(task.tryLock()).isEqualTo(false);

		//unlocking
		assertThat(task.tryUnlock()).isEqualTo(true);
		assertThat(task.tryUnlock()).isEqualTo(false);
		assertThat(task.tryUnlock()).isEqualTo(false);
	}

	@RepeatedTest(10)
	public void multiThreadedTest() {
		Task task = new Task();
		Random random = new Random();
		Semaphore onlyOneLock = new Semaphore(1);
		Events<String> events = new Events<>();
		AtomicInteger lockOwnersCount = new AtomicInteger();
		Stream.generate(() -> (Runnable) () -> {
					if (random.nextBoolean()) {
						if (task.tryLock()) {
							lockOwnersCount.incrementAndGet();
						}
					} else {
						if (task.tryUnlock()) {
							lockOwnersCount.decrementAndGet();
						}
					}
				})
				.limit(2_000_000)
				.parallel()
				.forEach(Runnable::run);
		assertThat(lockOwnersCount.get()).isBetween(0, 1);
	}
}