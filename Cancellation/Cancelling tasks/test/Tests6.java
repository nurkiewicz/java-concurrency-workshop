import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import com.google.common.base.Throwables;
import org.junit.Test;

public class Tests6 {

	@Test
	public void testSolution() throws InterruptedException {
		CountDownLatch wasInterrupted = new CountDownLatch(1);
		Callable<Void> task = new Task6().task(() -> {}, wasInterrupted::countDown);

		Thread thread = new Thread(() -> {
			try {
				task.call();
			} catch (Exception e) {
				Throwables.throwIfUnchecked(e);
				throw new RuntimeException(e);
			}
		});
		thread.start();
		Thread.sleep(100);
		thread.interrupt();
		ConcurrentAssertions.waitFor(wasInterrupted, "task to be interrupted");
	}

	@Test
	public void interruptPrematurelyStopsTask() throws InterruptedException, ExecutionException {
		//TODO check if code was indeed interrupted
	}
}