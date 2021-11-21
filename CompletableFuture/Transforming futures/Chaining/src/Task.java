import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;

public abstract class Task {

	CompletableFuture<BigDecimal> todo() {
//		throw new UnsupportedOperationException("TODO");
		return loadFromDb().thenCompose(this::extract);
	}

	abstract CompletableFuture<ResultSet> loadFromDb();

	abstract CompletableFuture<BigDecimal> extract(ResultSet rs);

}