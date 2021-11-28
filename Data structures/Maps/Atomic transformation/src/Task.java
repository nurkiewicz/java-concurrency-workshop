import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Task {

	Map<String, Integer> createScoresMap() {
//		throw new UnsupportedOperationException("TODO");
		return new ConcurrentHashMap<>();
	}

	void applyScore(Map<String, Integer> scores, String player, int points) {
//		throw new UnsupportedOperationException("TODO");
		scores.merge(player, points, Integer::sum);
	}

}