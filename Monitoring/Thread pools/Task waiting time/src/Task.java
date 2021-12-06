import java.util.concurrent.Callable;

public class Task {

	<T> CallableWithWaitTime<T> instrument(Callable<T> raw) {
		//TODO Implement CallableWithWaitTime
		return new CallableWithWaitTime<>(raw);
	}
}

