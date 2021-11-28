import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

public class Tests {

	public static final int PLAYERS = 20000;
	public static final int SCORE_RANGE = 200;
	private final Task task = new Task();

	@Test
	public void smoke() {
		Map<String, Integer> scores = task.createScoresMap();
		task.applyScore(scores, "a", 1);
		task.applyScore(scores, "b", 2);
		task.applyScore(scores, "a", 3);
		assertThat(scores).containsExactly(
				entry("a", 4),
				entry("b", 2)
		);
	}

	@RepeatedTest(10)
	public void tonOfConcurrentEvents() {
		Map<String, Integer> scores = task.createScoresMap();
		List<Map.Entry<String, Integer>> actions = IntStream.range(0, PLAYERS)
				.mapToObj(Integer::toString)
				.flatMap(player -> IntStream.rangeClosed(-SCORE_RANGE, SCORE_RANGE).mapToObj(i -> entry(player, i)))
				.collect(Collectors.toList());
		Collections.shuffle(actions);
		actions
				.parallelStream()
				.forEach(e -> task.applyScore(scores, e.getKey(), e.getValue()));
		assertThat(scores)
				.hasSize(PLAYERS);
		assertThat(new HashSet<>(scores.values())).isEqualTo(new HashSet<>(List.of(0)));
	}

}