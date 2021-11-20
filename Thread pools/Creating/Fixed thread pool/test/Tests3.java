import java.lang.invoke.MethodHandles;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tests3 {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static final int THREAD_COUNT = 4;

	@Test
	public void enoughThread() throws ExecutionException, InterruptedException {
		ExecutorService executor = new Task().create();
		CountDownLatch wait = new CountDownLatch(THREAD_COUNT);
		CountDownLatch start = new CountDownLatch(1);
		IntStream.range(0, THREAD_COUNT)
				.mapToObj(x -> executor.submit((Callable<Void>) () -> {
					wait.countDown();
					start.await();
					return null;
				})).collect(Collectors.toList());
		ConcurrentAssertions.waitFor(wait, "all tasks to start on " + THREAD_COUNT + " workers");
		start.countDown();
	}

	@Test
	public void tooManyThreads() throws ExecutionException, InterruptedException {
		//TODO How to test for too many worker thread in a thread pool?
	}
}