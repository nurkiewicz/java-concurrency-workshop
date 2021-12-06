import java.util.concurrent.Callable;

public class Task {

	<T> CallableWithTaskRuntime<T> instrument(Callable<T> raw) {
		//TODO Implement CallableWithTaskRuntime
		return new CallableWithTaskRuntime<>(raw);
	}

}