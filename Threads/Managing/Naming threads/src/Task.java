public abstract class Task {

	Thread createThread() {
		return new Thread(this::runThis, "Course");
	}

	abstract void runThis();

}