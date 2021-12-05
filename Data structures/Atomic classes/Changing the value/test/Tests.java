import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Tests implements WithAssertions {

	private static final String INPUT =
		"9, 19\n" +
				"97, 118\n" +
				"871, 178\n" +
				"6171, 261\n" +
				"77031, 350\n" +
				"837799, 524\n" +
				"8400511, 685\n" +
				"63728127, 949\n" +
				"670617279, 986\n" +
				"9780657630, 1132\n" +
				"75128138247, 1228\n" +
				"989345275647, 1348\n";

	@ParameterizedTest(name = "single threaded: {argumentsWithNames}")
	@CsvSource(textBlock = INPUT)
	public void singleThreaded(long start, int steps) {
		AtomicLong in = new AtomicLong(start);
		Task task = new Task();
		IntStream.range(0, steps).forEach(i -> task.next(in));
		assertThat(in).hasValue(1);
	}

	@ParameterizedTest(name = "multi threaded: {argumentsWithNames}")
	@CsvSource(textBlock = INPUT)
	public void multiThreaded(long start, int steps) {
		AtomicLong in = new AtomicLong(start);
		Task task = new Task();
		IntStream.range(0, steps).parallel().forEach(i -> task.next(in));
		assertThat(in).hasValue(1);
	}
}