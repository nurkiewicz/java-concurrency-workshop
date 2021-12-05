import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {
  @Test
  public void testSolution() throws ExecutionException, InterruptedException, TimeoutException {
    CompletableFuture<LocalDateTime> future = new Task().combine(
            new FutureFailingWhenBlocked<>(LocalDate.of(2022, 1, 1)),
            new FutureFailingWhenBlocked<>(LocalTime.of(15, 10)));
    assertThat(future.get(1, TimeUnit.SECONDS)).isEqualTo(LocalDateTime.of(2022, 1, 1, 15, 10));
  }

  @Test
  @Timeout(1)
  public void testShouldNotBlock() {
    CompletableFuture<LocalDateTime> future = new Task().combine(
            new CompletableFuture<>(),
            new CompletableFuture<>());
    //does not block
  }
}