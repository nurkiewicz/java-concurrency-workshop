import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Tests {

	@Test
	public void testSolution() {
		ExecutorService executorService = new Task().create();
		CountDownLatch latch = new CountDownLatch(1);
		for (int i = 0; i < (5 + 1); i++) {
			executorService.submit((Callable<Void>) () -> {
				latch.await();
				return null;
			});
		}
		try {
			executorService.submit(() -> {});
			Assertions.failBecauseExceptionWasNotThrown(SystemOverflowException.class);
		} catch (SystemOverflowException e) {
			//expected
		}
		latch.countDown();
	}
}