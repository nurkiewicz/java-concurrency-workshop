import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task {

	public static final int EXECUTORS = 16;

	void consume(BlockingQueue<CustomerJob> queue) throws Exception {
		List<ExecutorService> executors = IntStream
				.range(0, EXECUTORS)
				.mapToObj(x -> Executors.newSingleThreadExecutor())
				.collect(Collectors.toUnmodifiableList());
		while(true){
			CustomerJob job = queue.take();
			executors.get(job.getKey() % EXECUTORS).submit(job);
		}
	}
}