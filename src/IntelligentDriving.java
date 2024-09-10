import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 题目描述
 * 有一辆汽车需要从 m * n 的地图左上角（起点）开往地图的右下角（终点），去往每一个地区都需要消耗一定的油量，加油站可进行加油。
 * <p>
 * 请你计算汽车确保从从起点到达终点时所需的最少初始油量。
 * <p>
 * 说明：
 * <p>
 * 智能汽车可以上下左右四个方向移动
 * 地图上的数字取值是 0 或 -1 或 正整数：
 * -1 ：表示加油站，可以加满油，汽车的油箱容量最大为100；
 * 0 ：表示这个地区是障碍物，汽车不能通过
 * 正整数：表示汽车走过这个地区的耗油量
 * 如果汽车无论如何都无法到达终点，则返回 -1
 * 输入描述
 * 第一行为两个数字，M，N，表示地图的大小为 M * N
 * <p>
 * 0 < M,N ≤ 200
 * 后面一个 M * N 的矩阵，其中的值是 0 或 -1 或正整数，加油站的总数不超过 200 个
 * <p>
 * 输出描述
 * 如果汽车无论如何都无法到达终点，则返回 -1
 * <p>
 * 如果汽车可以到达终点，则返回最少的初始油量
 */
public class IntelligentDriving {
	static int m;
	static int n;
	// 地图
	static int[][] area;
	// 上下左右四个方向
	static int[][] row = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] dist_init = new int[m][n];
	static int[][] dist_remain = new int[m][n];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ss = sc.nextLine();
		String[] array = ss.split(",");
		m = Integer.parseInt(array[0]);
		n = Integer.parseInt(array[1]);
		area = new int[m][n];
		dist_init = new int[m][n];
		dist_remain = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(dist_init[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < m; i++) {
			String[] cur = sc.nextLine().split(",");
			for (int j = 0; j < n; j++) {
				area[i][j] = Integer.parseInt(cur[j]);
			}
		}

		System.out.print(bfs());
	}

	static class Node {
		// x坐标
		int x;
		// y坐标
		int y;
		// 到达此地需要的初始油量
		int init;
		// 到达此地的剩余油量
		int remain;
		// 是否加过油
		boolean flag;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}


	public static int bfs() {
		LinkedList<Node> queue = new LinkedList<>();
		// 如果左上角与右下角不可达，则返回-1
		if (area[0][0] == 0 || area[m - 1][n - 1] == 0 || area[0][0] > 100) {
			return -1;
		}
		// 添加初始节点
		Node first = new Node(0, 0);
		int cur = area[0][0];
		if (cur == -1) {
			first.init = 0;
			first.remain = 100;
			first.flag = true;
			dist_remain[0][0] = 100;
			dist_init[0][0] = 0;
		} else {
			first.init = cur;
			first.remain = 0;
			first.flag = false;
			dist_remain[0][0] = 0;
			dist_init[0][0] = cur;
		}
		queue.add(first);
		while (!queue.isEmpty()) {
			Node node = queue.pop();
			// 向上、下、左、右四个方法深度遍历
			for (int i = 0; i < row.length; i++) {
				int x = node.x + row[i][0];
				int y = node.y + row[i][1];
				// 越界则直接返回
				if (x < 0 || y < 0 || x > m - 1 || y > n - 1 || area[x][y] == 0) {
					continue;
				}
				int cost = area[x][y];
				// 到达此地需要的初始油量
				int init = node.init;;
				// 到达此地的剩余油量
				int remain = node.remain;
				// 是否加过油
				boolean flag = node.flag;
				if (cost == -1) {
					remain = 100;
					flag = true;
				} else {
					remain = remain - cost;
				}
				// 到达新位置后，如果剩余油量小于0
				// 如果加过油，则初始油量不允许添加了
				// 如果加过油，则初始油量可以添加
				if (remain < 0) {
					if (flag) {
						continue;
					} else {
						init = node.init - remain;
						remain = 0;
					}
				}

				// 如果初始油量大于100，则不加入节点
				if (init > 100) {
					continue;
				}
				// 如果走到改地消耗的初始油量大于之前，则不用加入节点继续遍历
				if (init > dist_init[x][y]) {
					continue;
				}
				// 如果走到该地消耗的初始油量小于之前，或剩的油更多，则更新
				if (init < dist_init[x][y] || remain > dist_remain[x][y]) {
					dist_init[x][y] = init;
					dist_remain[x][y] = remain;
					Node no = new Node(x, y);
					no.init = init;
					no.remain = remain;
					no.flag = flag;

					queue.add(no);
				}
			}
		}


		return dist_init[m - 1][n - 1] == Integer.MAX_VALUE ? -1 : dist_init[m - 1][n - 1];
	}
}