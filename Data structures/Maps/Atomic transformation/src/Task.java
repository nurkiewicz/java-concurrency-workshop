import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Task {

	Map<String, Integer> createScoresMap() {
		return new ConcurrentHashMap<>();
	}

	void applyScore(Map<String, Integer> scores, String player, int points) {
		scores.merge(player, points, Integer::sum);
	}

}