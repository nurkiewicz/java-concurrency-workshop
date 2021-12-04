import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class Tests {

	@Test
	public void testSolution() {
		Thread mock = Mockito.mock(Thread.class);
		new Task().interrupt(mock);
		verify(mock).interrupt();
	}
}