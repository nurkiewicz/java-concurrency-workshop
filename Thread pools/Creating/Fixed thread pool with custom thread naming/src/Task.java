import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class Task {

	ExecutorService create() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat("CustomPool-%d")
				.build();
		return new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
	}

}