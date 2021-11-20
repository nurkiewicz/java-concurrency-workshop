import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Task {

	ExecutorService create() {
//		throw new UnsupportedOperationException("TODO");
		RejectedExecutionHandler handler = (runnable, threadPoolExecutor) -> {
			throw new SystemOverflowException();
		};
		return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(5), handler);
	}
}