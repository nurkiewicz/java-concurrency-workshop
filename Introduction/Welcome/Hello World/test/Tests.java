import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Tests {

	@Test
	public void testSolution() {
		assertThat(new Task().getAnswer()).isEqualTo(42);
	}
}