import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * xx
 *
 * @author xu 2024-07-30 22:11
 */
public class APIClusterLoadStatistics {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int length = sc.nextInt();
		Map<Integer, Map<String, Integer>> map = new HashMap<>(16);
		sc.nextLine();
		for (int i = 1; i <= length; i++) {
			String api = sc.nextLine();
			String[] split = api.substring(1).split("/");
			for (int j = 0; j < split.length; j++) {
				Map<String, Integer> lineMap = map.get(j + 1);
				if (lineMap == null) {
					lineMap = new HashMap<>(16);
				}
				lineMap.put(split[j], lineMap.getOrDefault(split[j], 0) + 1);
				map.put(j + 1, lineMap);
			}
		}
		String result = sc.nextLine();
		String[] split = result.split(" ");
		Integer count = map.get(Integer.parseInt(split[0])).get(split[1]);
		System.out.println(count);

	}
}
