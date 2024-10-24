import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * xx
 *
 * @author xu 2024-09-17 17:16
 */
public class NumberGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Integer> result = new ArrayList<>();
		while (sc.hasNextLine()) {
			String first = sc.nextLine();
			String[] split = first.split(" ");
			int num = Integer.parseInt(split[1]);
			int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int ju = judge(nums, num);
			result.add(ju);
		}
		for (Integer integer : result) {
			System.out.println(integer);
		}
	}

	private static int judge(int[] nums, int num) {
		for (int i = 0; i < nums.length; i++) {
			int total = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				total = total +  nums[j];
				if (total % num == 0) {
					return 1;
				}
			}
		}
		return 0;
	}
}
