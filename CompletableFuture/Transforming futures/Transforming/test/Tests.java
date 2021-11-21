import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {

	@Test
	public void testSolution() throws ExecutionException, InterruptedException, TimeoutException {
      Task task = new Task();

      test(task, "0", 0);
      test(task, "42", 42);
      test(task, "-13", -13);
    }

  private AbstractIntegerAssert<?> test(Task task, String in, int out) throws InterruptedException, ExecutionException, TimeoutException {
    CompletableFuture<String> future = new CompletableFuture<>();
    future.complete(in);
    return assertThat(task.transform(future).get(1, TimeUnit.SECONDS)).isEqualTo(out);
  }
}