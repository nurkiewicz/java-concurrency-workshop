import com.google.common.base.Throwables;

@FunctionalInterface
public interface UncheckedRunnable extends Runnable {

	default void run() {
		try {
			uncheckedRun();
		} catch (Exception e) {
			Throwables.throwIfUnchecked(e);
			throw new RuntimeException(e);
		}
	}

	void uncheckedRun() throws Exception;
}
