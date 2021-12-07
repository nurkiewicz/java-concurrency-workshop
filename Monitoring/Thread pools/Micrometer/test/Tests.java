import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.graphite.GraphiteConfig;
import io.micrometer.graphite.GraphiteMeterRegistry;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tests implements WithAssertions {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Test
	public void smokeTest() {
		SimpleMeterRegistry registry = new SimpleMeterRegistry();
		new Task().addMetrics(registry, Executors.newFixedThreadPool(10));

		Set<String> metrics = registry
				.getMeters()
				.stream()
				.map(Meter::getId)
				.map(Meter.Id::getName)
				.collect(Collectors.toSet());
		assertThat(metrics)
				.contains("executor.queued", "executor.active", "executor.pool.size");
	}

	@Test
	@Disabled
	public void graphite() throws InterruptedException {
		MeterRegistry registry = new GraphiteMeterRegistry(new GraphiteConfig() {
			@Override
			public Duration step() {
				return Duration.ofSeconds(2);
			}

			@Override
			public String get(String k) {
				return null;
			}
		}, Clock.SYSTEM);
		ExecutorService pool = new Task().addMetrics(registry, Executors.newFixedThreadPool(10));

		Set<String> metrics = registry
				.getMeters()
				.stream()
				.map(Meter::getId)
				.map(Meter.Id::getName)
				.collect(Collectors.toSet());
		assertThat(metrics)
				.contains("executor.queued", "executor.active", "executor.pool.size");

		Random random = new Random();
		Semaphore semaphore = new Semaphore(1);
		while (true) {
			semaphore.acquire();
			pool.submit(() -> {
				log.info("Executing");
				TimeUnit.MILLISECONDS.sleep((long) (500 + random.nextGaussian() * 200));
				semaphore.release();
				return 42;
			});
		}

	}
}