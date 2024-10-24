import java.util.*;

/**
 * xx
 *
 * @author xu 2024-09-24 19:43
 */
public class ThreeSum {
	static List<List<Integer>> res;

	static Set<String> exists;

	public static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		res = new ArrayList<>();
		exists = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int num = nums[i];
			backtrack(nums, i + 1, num, 0 - num);
		}
		return res;
	}

	public static void backtrack(int[] nums, int start, int first, int total) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = start; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				int sec = nums[map.get(nums[i])];
				int three = nums[i];
				String ex = first + "*" + sec + "*" + three;
				if (!exists.contains(ex)) {
					List<Integer> cur = new ArrayList<>();
					cur.add(first);
					cur.add(sec);
					cur.add(three);
					res.add(cur);
					exists.add(ex);
				}

			} else {
				map.put(total - nums[i], i);
			}
		}
	}

}
