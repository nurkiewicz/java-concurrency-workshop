import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import org.junit.Assert;
import org.junit.Test;

public class Tests {

	@Test
	public void testSingleThread() throws ExecutionException, InterruptedException {
		ExecutorService executor = new Task().create();
		Semaphore semaphore = new Semaphore(1);
		Future<Object> f1 = executor.submit(() -> task(semaphore));
		Future<Object> f2 = executor.submit(() -> task(semaphore));
		f1.get();
		f2.get();
	}

	@Test
	public void shouldAccept10tasksInQueue() {
		ExecutorService executor = new Task().create();
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i <= 10; i++) {
            executor.submit(() -> task(semaphore));
        }
        Assert.assertEquals(10, executor.shutdownNow().size());
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