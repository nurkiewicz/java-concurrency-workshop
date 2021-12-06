import java.util.concurrent.BlockingQueue;

public class Task {

	void acceptJobs(BlockingQueue<Job> jobs) throws InterruptedException {
		while (true) {
			jobs.take().run();
		}
	}

}