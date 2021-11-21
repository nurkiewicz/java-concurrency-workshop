import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {

	@Test
	public void testSolution() throws ExecutionException, InterruptedException, TimeoutException {
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("CF-Pool-%d").build();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5,
				60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
				threadFactory);
		CompletableFuture<String> future = new Task().create(threadPoolExecutor);
		assertThat(future.get(1, TimeUnit.SECONDS)).matches("CF-Pool-\\d+");
	}
}