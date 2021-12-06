public abstract class Task {

	Thread createThread() {
		throw new UnsupportedOperationException("TODO");
	}

	abstract void runThis();

}