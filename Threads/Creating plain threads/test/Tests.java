import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {
  @Test
  public void testSolution() throws InterruptedException {
    AtomicReference<String> threadName = new AtomicReference<>();
    CountDownLatch latch = new CountDownLatch(1);
    Thread thread = new Task() {
      @Override
      void runThis() {
        threadName.set(Thread.currentThread().getName());
        latch.countDown();
      }
    }.createThread();

    assertThat(threadName).hasValue(null);
    assertThat(thread).isNotNull();
    thread.start();
    ConcurrentAssertions.waitFor(latch);
    assertThat(threadName.get()).startsWith("Thread-");
  }
}