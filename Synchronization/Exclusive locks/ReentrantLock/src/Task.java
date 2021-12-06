import java.util.concurrent.locks.ReentrantLock;

public class Task {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		try {
			//critical section
		} finally {
			lock.unlock();
		}

	}
}