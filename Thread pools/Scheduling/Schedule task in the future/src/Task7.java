import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task7 {

	void schedule(ScheduledExecutorService executorService, Runnable task) {
		executorService.schedule(task, 3, TimeUnit.SECONDS);
	}
}