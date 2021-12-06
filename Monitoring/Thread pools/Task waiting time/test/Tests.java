import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

public class Tests implements WithAssertions {

	@Test
	public void testSolution() throws Exception {
		Callable<Void> call = () -> null;
		CallableWithWaitTime<Void> instrumented = new Task().instrument(call);
		TimeUnit.MILLISECONDS.sleep(500);
		instrumented.call();
		assertThat(instrumented.getIdleTime()).isGreaterThanOrEqualTo(Duration.ofMillis(500));
	}
}