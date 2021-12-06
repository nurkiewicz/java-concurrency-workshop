public abstract class Task {

	Thread createThread() {
		return new Thread(this::runThis);
	}

	abstract void runThis();

}