import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {

	@Test
	public void shouldAccept10tasksInQueue() {
		ExecutorService executor = new Task().create();
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i <= 10; i++) {
            executor.submit(() -> task(semaphore));
        }

		assertThat(executor.shutdownNow().size()).isEqualTo(10);
	}

	@Test
	public void shouldReject11thTask() {
		ExecutorService executor = new Task().create();
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i <= 10; i++) {
            executor.submit(() -> task(semaphore));
        }
		try {
			executor.submit(() -> task(semaphore));
			Assertions.failBecauseExceptionWasNotThrown(RejectedExecutionException.class);
		} catch (RejectedExecutionException e) {
			//expected
		}

		assertThat(executor.shutdownNow().size()).isEqualTo(10);
	}

	private Object task(Semaphore semaphore) throws InterruptedException {
		if (!semaphore.tryAcquire()) {
			throw new IllegalStateException("Two tasks running at the same time?");
		}
		try {
			Thread.sleep(1000);
		} finally {
			semaphore.release();
		}
		return null;
	}
}