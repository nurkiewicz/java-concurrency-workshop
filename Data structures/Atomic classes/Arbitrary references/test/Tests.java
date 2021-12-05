import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class Tests implements WithAssertions {

	Singleton instance = new Singleton() {};

	@Test
	public void smoke() {
		AtomicInteger invocationCounter = new AtomicInteger();
		Task task = new Task() {
			@Override
			protected Singleton create() {
				invocationCounter.incrementAndGet();
				Thread.yield();
				return instance;
			}
		};
		assertThat(invocationCounter).hasValue(0);
		assertThat(task.get()).isEmpty();

		assertThat(task.getOrCreate()).isEqualTo(instance);
		assertThat(invocationCounter).hasValue(1);

		assertThat(task.getOrCreate()).isEqualTo(instance);
		assertThat(invocationCounter).hasValue(1);

	}

	@RepeatedTest(100)
	public void multiThreadTest() {
		AtomicInteger invocationCounter = new AtomicInteger();
		Task task = new Task() {
			@Override
			protected Singleton create() {
				invocationCounter.incrementAndGet();
				Thread.yield();
				return instance;
			}
		};
		assertThat(invocationCounter).hasValue(0);
		assertThat(task.get()).isEmpty();

		IntStream
				.range(0, 100)
				.parallel()
				.forEach(i -> assertThat(task.getOrCreate()).isEqualTo(instance));
		assertThat(invocationCounter).hasValue(1);

	}
}