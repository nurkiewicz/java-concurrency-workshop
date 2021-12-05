import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class Tests {

	private static final LocalDate DATE = LocalDate.MIN;

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 10})
	public void testSolution(int x) throws Exception {
		BigDecimal val = BigDecimal.valueOf(x);
		Task task = new MyTask(val);
		Assertions.assertThat(task.todo(DATE).get(1, TimeUnit.SECONDS)).isEqualTo(val);
	}

	private static class MyTask extends Task {

		private final BigDecimal result;

		private MyTask(BigDecimal result) {
			this.result = result;
		}

		@Override
		CompletableFuture<ResultSet> loadFromDb(LocalDate date) {
			return new FutureFailingWhenBlocked<>(Mockito.mock(ResultSet.class));
		}

		@Override
		CompletableFuture<BigDecimal> extract(ResultSet rs) {
			return new FutureFailingWhenBlocked<>(result);
		}
	}
}