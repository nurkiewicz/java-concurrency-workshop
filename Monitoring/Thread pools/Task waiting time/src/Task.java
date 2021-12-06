import java.util.concurrent.Callable;

public class Task {

	<T> CallableWithWaitTime<T> instrument(Callable<T> raw) {
		//TODO Implement InstrumentedCallable
		return new CallableWithWaitTime<>(raw);
	}
}

