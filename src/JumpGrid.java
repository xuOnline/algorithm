import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 题目描述
 * 小明和朋友们一起玩跳格子游戏，
 * <p>
 * 每个格子上有特定的分数 score = [1, -1, -6, 7, -17, 7]，
 * <p>
 * 从起点score[0]开始，每次最大的步长为k，请你返回小明跳到终点 score[n-1] 时，能得到的最大得分。
 * <p>
 * 输入描述
 * 第一行输入总的格子数量 n
 * <p>
 * 第二行输入每个格子的分数 score[i]
 * <p>
 * 第三行输入最大跳的步长 k
 * <p>
 * 输出描述
 * 输出最大得分
 * <p>
 * 备注
 * 格子的总长度 n 和步长 k 的区间在 [1, 100000]
 * 每个格子的分数 score[i] 在 [-10000, 10000] 区间中
 */
public class JumpGrid {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = Integer.parseInt(sc.nextLine());
		int[] scores = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int k = Integer.parseInt(sc.nextLine());

		System.out.println(getResult(n, scores, k));
	}

	public static int getResult(int n, int[] scores, int k) {
		int len = k + 1;
		LinkedList<Integer> queue = new LinkedList<>();
		int[] dp = new int[n];
		dp[0] = scores[0];
		queue.add(dp[0]);
		for (int i = 1; i < Math.min(len, n); i++) {
          dp[i] = queue.getFirst() + scores[i];
		  while (queue.size() > 0 && dp[i] > queue.getLast()){
			  queue.removeLast();
		  }
		  queue.add(dp[i]);
		}
		for (int i = len; i < n; i++) {
			if(dp[i -len] == queue.getFirst()){
				queue.removeFirst();
			}
			dp[i] = queue.getFirst() + scores[i];
			while(queue.size() > 0 && dp[i] > queue.getLast()){
				queue.removeLast();
			}
			queue.add(dp[i]);
		}
		
		return dp[n -1];
	}
}
