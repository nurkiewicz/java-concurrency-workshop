import java.lang.invoke.MethodHandles;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);

		Supplier<Void> call = () -> {
			log.info("Starting");
			try {
				latch.await();
				log.info("Executing");
			} catch (InterruptedException e) {
				log.warn("Interrupted", e);
			}
			return null;
		};

		CompletableFuture.supplyAsync(call);
		TimeUnit.SECONDS.sleep(1);
		CompletableFuture.supplyAsync(call);
		TimeUnit.SECONDS.sleep(1);
		CompletableFuture.supplyAsync(call);
		TimeUnit.SECONDS.sleep(1);
		latch.countDown();
	}



}