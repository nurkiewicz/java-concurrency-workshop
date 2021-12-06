import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

class CallableWithTaskRuntime<T> implements Callable<T> {

	private final Callable<T> target;
	private Instant started;
	private Instant completed;

	public CallableWithTaskRuntime(Callable<T> target) {
		this.target = target;
	}

	public Duration getRuntime() {
		return Duration.between(started, completed);
	}

	@Override
	public T call() throws Exception {
		//TODO Implement here
		return target.call();
	}
}
