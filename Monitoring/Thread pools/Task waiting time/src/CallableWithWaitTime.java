import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

public class CallableWithWaitTime<T> implements Callable<T> {

	private final Instant created;
	private final Callable<T> target;
	private Instant started;

	public CallableWithWaitTime(Callable<T> target) {
		this.target = target;
		this.created = Instant.now();
	}

	Duration getIdleTime() {
		return Duration.between(created, started);
	}

	@Override
	public T call() throws Exception {
		//TODO Implement here
		started = Instant.now();
		return target.call();
	}
}
