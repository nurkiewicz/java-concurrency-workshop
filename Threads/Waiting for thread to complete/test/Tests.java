import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Tests {
  @Test
  public void testSolution() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    Thread thread = new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      latch.countDown();
    });
    thread.start();
    new Task().waitFor(thread);
    assertThat(latch.await(1, TimeUnit.MILLISECONDS)).isTrue();
  }
}