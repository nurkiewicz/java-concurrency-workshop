import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

public class Task {
  CompletableFuture<LocalDateTime> combine(CompletableFuture<LocalDate> date, CompletableFuture<LocalTime> time) {
	  throw new UnsupportedOperationException("TODO");
//	  return CompletableFuture.completedFuture(date.join().atTime(time.join()));
  }
}