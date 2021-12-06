import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public abstract class   Task {

	CompletableFuture<BigDecimal> todo(LocalDate date) throws Exception{
		return loadFromDb(date).thenCompose(this::extract);
	}

	abstract CompletableFuture<ResultSet> loadFromDb(LocalDate date);

	abstract CompletableFuture<BigDecimal> extract(ResultSet rs);

}