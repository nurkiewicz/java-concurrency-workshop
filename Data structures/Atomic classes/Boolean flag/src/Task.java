import java.util.concurrent.atomic.AtomicBoolean;

public class Task {

	private final AtomicBoolean lock = new AtomicBoolean(false);

	boolean tryLock() {
		throw new UnsupportedOperationException("TODO");
	}

	boolean tryUnlock() {
		throw new UnsupportedOperationException("TODO");
	}

}