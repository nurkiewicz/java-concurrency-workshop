import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task {

	ExecutorService create() {
//		throw new UnsupportedOperationException("TODO");
		return Executors.newFixedThreadPool(5);
	}
}