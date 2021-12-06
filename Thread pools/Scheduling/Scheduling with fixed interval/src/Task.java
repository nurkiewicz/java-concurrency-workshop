import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task {
	void schedule(ScheduledExecutorService executorService, Runnable task) {
		executorService.scheduleAtFixedRate(task, 3, 3, TimeUnit.SECONDS);
	}
}