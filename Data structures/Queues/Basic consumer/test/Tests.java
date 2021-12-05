import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class Tests {

	private final TestJob[] jobs = new TestJob[] {
			new TestJob(),
			new TestJob()
	};

	@Test
	public void shouldRunNoJobsAndSupportInterruption() throws InterruptedException {
		Thread thread = runInSeparateThread(0);
		thread.start();
		thread.interrupt();
	}

	@Test
	public void shouldRunJob() throws InterruptedException {
		Thread thread = runInSeparateThread(1);
		thread.start();
		jobs[0].waitUntilRun();
		thread.interrupt();
	}

	@Test
	public void shouldRunTwoJobs() throws InterruptedException {
		Thread thread = runInSeparateThread(2);
		thread.start();
		jobs[0].waitUntilRun();
		jobs[1].waitUntilRun();
		thread.interrupt();
	}

	private Thread runInSeparateThread(int howManyTasks) throws InterruptedException {
		return new Thread(() -> {
			try {
				Task task = new Task();
				BlockingQueue<Job> jobs = new ArrayBlockingQueue<>(100);
				IntStream.range(0, howManyTasks).forEach(i -> jobs.add(this.jobs[i]));
				task.acceptJobs(jobs);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});
	}
}
