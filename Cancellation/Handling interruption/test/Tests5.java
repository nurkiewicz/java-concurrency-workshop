import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests5 {
  @Test
  public void testSolution() throws InterruptedException {
    Semaphore semaphore = new Semaphore(0);
    AtomicBoolean cancelled = new AtomicBoolean();
    CountDownLatch started = new CountDownLatch(1);
    Thread thread = new Task5().create(
            () -> {
              started.countDown();
              semaphore.acquire();
              return null;
            },
            () -> cancelled.set(true));
    thread.start();
    ConcurrentAssertions.waitFor(started, "task start");
    thread.interrupt();
    assertThat(cancelled).isTrue();
  }
}