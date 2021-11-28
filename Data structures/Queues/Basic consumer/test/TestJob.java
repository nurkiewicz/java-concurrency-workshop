import java.util.concurrent.CountDownLatch;

public class TestJob implements Job {

	final CountDownLatch run = new CountDownLatch(1);

	@Override
	public void run() {
		run.countDown();
	}

	void waitUntilRun() throws InterruptedException {
		ConcurrentAssertions.waitFor(run, "job was run");
	}

}
