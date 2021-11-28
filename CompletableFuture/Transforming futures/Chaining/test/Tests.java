import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class Tests {
  @Test
  public void testSolution() throws ExecutionException, InterruptedException, TimeoutException {
    test(BigDecimal.ONE);
    test(BigDecimal.ZERO);
    test(BigDecimal.TEN);
  }

  private void test(BigDecimal val) throws InterruptedException, ExecutionException, TimeoutException {
    Task task = new MyTask(val);
    Assertions.assertThat(task.todo().get(1, TimeUnit.SECONDS)).isEqualTo(val);
  }

  private static class MyTask extends Task {

    private final BigDecimal result;

    private MyTask(BigDecimal result) {
      this.result = result;
    }

    @Override
    CompletableFuture<ResultSet> loadFromDb() {
      CompletableFuture<ResultSet> f = new CompletableFuture<>();
      f.complete(Mockito.mock(ResultSet.class));
      return f;
    }

    @Override
    CompletableFuture<BigDecimal> extract(ResultSet rs) {
      CompletableFuture<BigDecimal> f = new CompletableFuture<>();
      f.complete(result);
      return f;
    }
  }
}