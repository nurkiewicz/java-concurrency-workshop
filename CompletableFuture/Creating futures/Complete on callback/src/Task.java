import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class Task {
  CompletableFuture<String> create(MessageListener listener) {
	  CompletableFuture<String> future = new CompletableFuture<>();
	  listener.onMessage(future::complete);
	  return future;
  }
}

@FunctionalInterface
interface MessageListener {
  void onMessage(Consumer<String> consumer);
}