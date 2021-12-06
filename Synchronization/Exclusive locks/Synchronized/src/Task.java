public class Task {

	public static void main(String[] args) {
		synchronized(Task.class) {
			//Critical section
		}
	}
}