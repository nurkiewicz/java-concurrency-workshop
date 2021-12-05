import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Task {

	AtomicReference<Singleton> singleton = new AtomicReference<Singleton>();

	public Singleton getOrCreate() {
//		return singleton.updateAndGet(cur -> cur == null ? create() : cur);
		if(singleton.get() == null) {
			singleton.set(create());
		}
		return singleton.get();
	}

	protected abstract Singleton create();

	public Optional<Singleton> get() {
		return Optional.ofNullable(singleton.get());
	}

}

interface Singleton { }