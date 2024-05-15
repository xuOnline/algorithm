import java.util.HashMap;
import java.util.Scanner;

/**
 从前有个村庄，村民们喜欢在各种田地上插上小旗子，旗子上标识了各种不同的数字。

 某天集体村民决定将覆盖相同数字的最小矩阵形的土地分配给村里做出巨大贡献的村民，请问此次分配土地，做出贡献的村民种最大会分配多大面积?

 输入描述
 第一行输入 m 和 n，

 m 代表村子的土地的长
 n 代表土地的宽
 第二行开始输入地图上的具体标识

 输出描述
 此次分配土地，做出贡献的村民种最大会分配多大面积

 备注
 旗子上的数字为1~500，土地边长不超过500
 未插旗子的土地用0标识
 用例1
 输入
 3 3
 1 0 1
 0 0 0
 0 1 0
 输出
 9
 说明
 土地上的旗子为1，其坐标分别为(0,0)，(2,1)以及(0,2)，为了覆盖所有旗子，矩阵需要覆盖的横坐标为0和2，纵坐标为0和2，所以面积为9，即（2-0+1）*（2-0+1）= 9

 用例2
 输入
 3 3
 1 0 2
 0 0 0
 0 3 4
 输出
 1
 说明
 由于不存在成对的小旗子，故而返回1，即一块土地的面积。
 */
public class AllocationLand {
	static class Rect {
		int minRow = Integer.MAX_VALUE;
		int maxRow = Integer.MIN_VALUE;
		int minCol = Integer.MAX_VALUE;
		int maxCol = Integer.MIN_VALUE;

		private void setRow(int row) {
			this.minRow = Math.min(this.minRow, row);
			this.maxRow = Math.max(this.maxRow, row);
		}

		private void setCol(int col) {
			this.minCol = Math.min(this.minCol, col);
			this.maxCol = Math.max(this.maxCol, col);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int m = sc.nextInt(); // 长（行数）
		int n = sc.nextInt(); // 宽（列数）

		HashMap<Integer, Rect> rects = new HashMap<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int num = sc.nextInt();

				if (num > 0) {
					rects.putIfAbsent(num, new Rect());
					rects.get(num).setRow(i);
					rects.get(num).setCol(j);
				}
			}
		}

		int maxArea = 0;
		for (int num : rects.keySet()) {
			Rect rect = rects.get(num);

			maxArea =
					Math.max(maxArea, (rect.maxRow - rect.minRow + 1) * (rect.maxCol - rect.minCol + 1));
		}

		System.out.println(maxArea);
	}
}
