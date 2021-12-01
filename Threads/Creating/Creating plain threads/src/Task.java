public abstract class Task {

	Thread createThread() {
//		throw new UnsupportedOperationException("TODO");
		return new Thread(this::runThis);
	}

	abstract void runThis();

}