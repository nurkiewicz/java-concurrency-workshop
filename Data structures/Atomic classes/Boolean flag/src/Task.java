import java.util.concurrent.atomic.AtomicBoolean;

public class Task {

	private final AtomicBoolean lock = new AtomicBoolean(false);

	boolean tryLock() {
		return lock.compareAndSet(false, true);
	}

	boolean tryUnlock() {
		return lock.compareAndSet(true, false);
	}

}