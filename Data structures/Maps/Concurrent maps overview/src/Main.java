import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Main {

	public static void main(String[] args) {
		ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
		if(!map.containsKey("key")) {
			map.put("key", 1);
		} else {
			map.put("key", map.get("key") + 1);
		}
	}
}