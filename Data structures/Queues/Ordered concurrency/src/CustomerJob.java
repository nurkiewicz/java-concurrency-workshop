public class CustomerJob implements Runnable {

	private final int key;
	private final Runnable target;

	public CustomerJob(int key, Runnable target) {
		this.key = key;
		this.target = target;
	}

	@Override
	public void run() {
		target.run();
	}

	public int getKey() {
		return key;
	}
}
