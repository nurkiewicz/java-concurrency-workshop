import java.util.concurrent.atomic.AtomicLong;

public class Task {

	void next(AtomicLong in) {
		in.set(collatz(in.get()));
	}

	private long collatz(long in) {
		if (in % 2 == 0) {
			return in / 2;
		} else {
			return 3 * in + 1;
		}
	}

}