import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;

public class Main {

	void consume(BlockingQueue<Runnable> queue) {
		Executors.newSingleThreadExecutor().submit(() -> {
			while (true) {
				try {
					queue.take().run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

}