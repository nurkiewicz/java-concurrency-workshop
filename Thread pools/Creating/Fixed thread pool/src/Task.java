import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task {

	ExecutorService create() {
		return Executors.newFixedThreadPool(5);
	}
}