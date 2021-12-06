public abstract class Task {

	void startThread() {
		new Thread(this::runThis).start();
	}

	abstract void runThis();

}