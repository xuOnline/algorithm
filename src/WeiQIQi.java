import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目描述
 * 围棋棋盘由纵横各19条线垂直相交组成，棋盘上一共19 x 19 = 361 个交点，对弈双方一方执白棋，一方执黑棋，落子时只能将棋子置于交点上。
 * <p>
 * “气”是围棋中很重要的一个概念，某个棋子有几口气，是指其上下左右方向四个相邻的交叉点中，有几个交叉点没有棋子，由此可知：
 * <p>
 * 在棋盘的边缘上的棋子最多有 3 口气（黑1），在棋盘角点的棋子最多有2口气（黑2），其他情况最多有4口气（白1）
 * 所有同色棋子的气之和叫做该色棋子的气，需要注意的是，同色棋子重合的气点，对于该颜色棋子来说，只能计算一次气，
 * 比如下图中，黑棋一共4口气，而不是5口气，因为黑1和黑2中间红色三角标出来的气是两个黑棋共有的，对于黑棋整体来说只能算一个气。
 * 本题目只计算气，对于眼也按气计算，如果您不清楚“眼”的概念，可忽略，按照前面描述的规则计算即可
 * 现在，请根据输入的黑棋和白棋得到坐标位置，计算黑棋和白棋一共各有多少气？
 * <p>
 * 输入描述
 * 输入包含两行数据，如：
 * <p>
 * 0 5 8 9 9 10 5 0 9 9 9 8
 * <p>
 * 每行数据以空格分隔，数据个数是2的整数倍，每两个数是一组，代表棋子在棋盘上的坐标；
 * 坐标的原点在棋盘左上角点，第一个值是行号，范围从0到18；第二个值是列号，范围从0到18。
 * 举例说明：第一行数据表示三个坐标（0, 5）、(8, 9)、(9, 10)
 * 第一行表示黑棋的坐标，第二行表示白棋的坐标。
 * 题目保证输入两行数据，无空行且每行按前文要求是偶数个，每个坐标不会超出棋盘范围。
 * 输出描述
 * 8 7
 * <p>
 * 两个数字以空格分隔，第一个数代表黑棋的气数，第二个数代表白棋的气数。
 * <p>
 * 用例1
 * 输入
 * 0 5 8 9 9 10
 * 5 0 9 9 9 8
 * 输出
 */
public class WeiQIQi {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String black = sc.nextLine();
		String white = sc.nextLine();
		List<String> blackMap = arrayToList(black.split(" "));
		List<String> whiteMap = arrayToList(white.split(" "));
		int blackQi = 0;
		int whiteQi = 0;
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				// 该地方有黑子或白子则没气
				if (existsQizi(blackMap, i, j) || existsQizi(whiteMap, i, j)) {
					continue;
				}
				boolean existsBlack = false;
				boolean existsWhite = false;
				// 判断该地上下左右是否有棋子
				if (i - 1 >= 0) {
					if (existsQizi(blackMap, i - 1, j)) {
						existsBlack = true;
					} else if (existsQizi(whiteMap, i - 1, j)) {
						existsWhite = true;
					}

				}
				if (i + 1 < 19) {
					if (existsQizi(blackMap, i + 1, j) && !existsBlack) {
						existsBlack = true;
					} else if (existsQizi(whiteMap, i + 1, j) && !existsWhite) {
						existsWhite = true;
					}
				}

				if (j - 1 >= 0) {
					if (existsQizi(blackMap, i, j - 1) && !existsBlack) {
						existsBlack = true;
					} else if (existsQizi(whiteMap, i, j - 1) && !existsWhite) {
						existsWhite = true;
					}
				}
				if (j + 1 < 19) {
					if (existsQizi(blackMap, i, j + 1) && !existsBlack) {
						existsBlack = true;
					} else if (existsQizi(whiteMap, i, j + 1) && !existsWhite) {
						existsWhite = true;
					}
				}

				if (existsBlack) {
					blackQi++;
				}
				if (existsWhite) {
					whiteQi++;
				}
			}
		}
		System.out.println(blackQi + " " + whiteQi);
	}

	public static boolean existsQizi(List<String> list, int i, int j) {
		return list.contains(Integer.toString(i) + "==" + Integer.toString(j));
	}

	public static List<String> arrayToList(String[] array) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			if ((i - 1) % 2 == 0) {
				list.add(array[i - 1] + "==" + array[i]);
			}
		}
		return list;
	}
}
