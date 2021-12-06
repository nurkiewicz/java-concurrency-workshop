import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class Task {

  CompletableFuture<String> create(MessageListener listener) {
	  throw new UnsupportedOperationException("TODO");
  }
}

@FunctionalInterface
interface MessageListener {
  void onMessage(Consumer<String> consumer);
}