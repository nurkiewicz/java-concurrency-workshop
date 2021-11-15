public abstract class Task {

	void startThread() {
		throw new UnsupportedOperationException("TODO");
//		new Thread(this::runThis).start();
	}

	abstract void runThis();

}