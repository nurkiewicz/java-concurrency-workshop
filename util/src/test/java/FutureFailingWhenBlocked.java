import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class FutureFailingWhenBlocked<T> extends CompletableFuture<T> {

	public FutureFailingWhenBlocked() {
	}

	public FutureFailingWhenBlocked(T value) {
		this.complete(value);
	}

	@Override
	public T join() {
		throw new UnsupportedOperationException("Blocking is not supported!");
	}

	@Override
	public T get() throws InterruptedException, ExecutionException {
		return join();
	}

	@Override
	public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return join();
	}

	@Override
	public T getNow(T valueIfAbsent) {
		return join();
	}
}
