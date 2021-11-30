import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Task {

	<T> List<Callable<T>> wrap(List<Callable<T>> actions) {
		return actions.stream()
				.map(action -> (Callable<T>) () -> {
					return action.call();
				})
				.collect(Collectors.toList());
	}

}