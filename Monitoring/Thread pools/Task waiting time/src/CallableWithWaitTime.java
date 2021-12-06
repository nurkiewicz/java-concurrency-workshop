import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

public class CallableWithWaitTime<T> implements Callable<T> {

	private final Instant created;
	private Callable<T> target;
	private Instant started;

	public CallableWithWaitTime(Callable<T> target) {
		this.created = Instant.now();
	}

	Duration getIdleTime() {
		return Duration.between(created, started);
	}

	@Override
	public T call() throws Exception {
		//TODO Implement here
		return target.call();
	}
}
