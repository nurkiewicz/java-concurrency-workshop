import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {

	public static final String HELLO = "Hello!";

	@Test
	public void testSolution() {
		AtomicReference<Consumer<String>> callback = new AtomicReference<>();
		MessageListener listener = callback::set;
		CompletableFuture<String> future = new Task().create(listener);
		assertThat(callback.get())
				.describedAs("User defined callback for onMessage")
				.isNotNull();
		assertThat(future.getNow(null)).isNull();

		callback.get().accept(HELLO);
		assertThat(future.getNow(null))
				.describedAs("Value of the future")
				.isEqualTo(HELLO);

	}
}