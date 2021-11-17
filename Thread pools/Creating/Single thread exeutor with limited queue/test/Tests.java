import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {

	@Test
	public void shouldAccept10tasksInQueue() {
		ExecutorService executor = new Task().create();
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i <= 10; i++) {
            executor.submit(() -> task(semaphore));
        }
        assertEquals(10, executor.shutdownNow().size());
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

		assertEquals(10, executor.shutdownNow().size());
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