import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

public class Tests implements WithAssertions {

	@Test
	public void testSolution() throws Exception {
		Callable<Void> call = () -> {
			TimeUnit.MILLISECONDS.sleep(500);
			return null;
		};
		CallableWithTaskRuntime<Void> instrumented = new Task().instrument(call);
		instrumented.call();
		assertThat(instrumented.getRuntime()).isGreaterThanOrEqualTo(Duration.ofMillis(500));
	}
}