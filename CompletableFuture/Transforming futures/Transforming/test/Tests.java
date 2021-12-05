import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {

	@ParameterizedTest
	@CsvSource({
			"0,     0",
			"42,    42",
			"-1,    -1"
	})
	public void testSolution(String s, int i) throws ExecutionException, InterruptedException, TimeoutException {
		Task task = new Task();
		CompletableFuture<String> future = CompletableFuture.completedFuture(s);
		assertThat(task.transform(future).get(1, TimeUnit.SECONDS)).isEqualTo(i);
	}

}