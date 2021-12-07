import java.util.Optional;

/**
 * <a href="https://javarevisited.blogspot.com/2014/05/double-checked-locking-on-singleton-in-java.html">https://javarevisited.blogspot.com/2014/05/double-checked-locking-on-singleton-in-java.html</a>
 */
public abstract class Task {

	private volatile Singleton singleton = null;

	public Singleton getOrCreate() {
		if (singleton == null) {
			synchronized (this) {
				if (singleton == null) {
					singleton = create();
				}
			}
		}
		return singleton;
	}

	protected abstract Singleton create();

	public Optional<Singleton> get() {
		return Optional.ofNullable(singleton);
	}

}

interface Singleton { }