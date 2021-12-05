import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Events<T> {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final BlockingQueue<T> queue = new ArrayBlockingQueue<>(1_000_000);

	void log(T event) {
		log.info("Event {}", event);
		queue.add(event);
	}

	List<T> drainToList() {
		List<T> list = new ArrayList<>();
		queue.drainTo(list);
		return list;
	}

	ArrayList<T> toList() {
		return new ArrayList<>(queue);
	}

}
