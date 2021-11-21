import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests7 {
  @Test
  public void testSolution() {
    ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    Runnable task = () -> {};
    new Task7().schedule(scheduledExecutorService, task);
    assertThat(scheduledExecutorService.getQueue()).hasSize(1);
  }
}