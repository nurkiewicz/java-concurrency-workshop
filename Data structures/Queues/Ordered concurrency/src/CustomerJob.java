public abstract class CustomerJob implements Runnable {

	private final int key;

	public CustomerJob(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}
}
