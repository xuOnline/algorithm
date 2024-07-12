import javax.sound.midi.Soundbank;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 题目描述
 * 给定一个正整数 n，如果能够分解为 m（m > 1）个连续正整数之和，请输出所有分解中，m最小的分解。
 * <p>
 * 如果给定整数无法分解为连续正整数，则输出字符串"N"。
 * <p>
 * 输入描述
 * 输入数据为一整数，范围为 (1, 2^30]
 * <p>
 * 输出描述
 * 比如输入为：
 * <p>
 * 21
 * <p>
 * 输出：
 * <p>
 * 21=10+11
 * <p>
 * 用例1
 * 输入
 * 21
 * 输出
 * 21=10+11
 * 说明
 * 21可以分解的连续正整数组合的形式有多种：
 * <p>
 * 21=1+2+3+4+5+6
 * <p>
 * 21=6+7+8
 * <p>
 * 21=10+11
 * <p>
 * 其中 21=10+11，是最短的分解序列。
 */
// 6 = 1 2 3 
// 8 =  1 2 3 4
// 10 = 1 + 2 + 3 + 4	
// 20 = 1 2 3 4 5 6 7 8 9 10
public class NumberSplit {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		// 如果数字小于2则不可能分解出,
		if (number <= 2) {
			System.out.println("N");
		} else if (number % 2 == 0) {
			// 如果正整数个数为偶数，则中间两个数加起来一定是奇数，找到这个奇数
			int oddMid = number / 2;
			while (oddMid % 2 == 0) {
				oddMid = oddMid / 2;
			}
			// 奇数个正整数的个数为 (number / oddMid  ) * 2
			int oddLen = number / oddMid * 2;
			// 起始数字
			int oddStart = oddMid / 2 - (oddLen / 2 - 1);

			// 如果正整数个数为奇数，则中间数一定是奇数，找到这个奇数
			int evenLen = -1;
			for (int i = 3; i * i < number; i = i + 2) {
				if (number % i == 0) {
					evenLen =  i;
					break;
				}
			}
			
			int evenStart =  number / evenLen - ((evenLen - 1) / 2);

			long len;
			long start;

			// 连续正整数数列中每个元素都是正整数，因此求解出来的首元素需要大于等于1
			if (oddLen >= 1 && evenLen >= 1) {
				if (oddLen < evenLen) {
					len = oddLen;
					start = oddStart;
				} else {
					len = evenLen;
					start = evenStart;
				}
			} else if (oddLen >= 1) {
				len = oddLen;
				start = oddStart;
			} else if (evenLen >= 1) {
				len = evenLen;
				start = evenStart;
			} else {
				System.out.println("N");
				return;
			}

			StringJoiner sj = new StringJoiner("+", number + "=", "");
			for (long i = start; i < start + len; i++) {
				sj.add(i + "");
			}
			System.out.println(sj.toString());


		} else {
			// 如果是奇数则最短的是中间值+后一个
			int mid = number / 2;
			System.out.println(number + "=" + mid + "+" + (mid + 1));
		}
	}
}


//import java.util.Scanner;
//import java.util.StringJoiner;
// 
//public class Main {
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    System.out.println(getResult(sc.nextLong()));
//  }
// 
//  public static String getResult(long n) {
//    if (n == 1) {
//      return "N";
//    }
// 
//    // 如果 n 是奇数, 那么固定分解为两个数
//    if (n % 2 != 0) {
//      return n + "=" + (n / 2) + "+" + (n / 2 + 1);
//    }
// 
//    // 如果 n 是偶数, 那么首先求出 n 的最大奇因数 x
//    long x = n / 2;
//    while (x % 2 == 0) {
//      x /= 2;
//    }
// 
//    // 如果偶数 n 分解出来的连续正整数数列的长度是偶数，则最短长度为minEvenLen
//    long minEvenLen = n / x * 2;
//    //  minEvenLen_start 是偶数长度连续正整数数列的首元素，其中
//    //    x/2是连续正整数中间两个数的左边那个数
//    //    (len / 2 - 1) 是连续正整数数列首元素 ~ 连续正整数中间两个数的左边那个数 的距离
//    long minEvenLen_start = x / 2 - (minEvenLen / 2 - 1);
// 
//    // 如果偶数 n 分解出来的连续正整数数列的长度是奇数，那么最短长度为minOddLen
//    long minOddLen = getMinOddLen(n, x);
//    //  minOddLen_start 是奇数长度连续正整数数列的首元素，其中
//    //    n/len是连续正整数中间的那个数
//    //    ((len - 1) / 2) 是是连续正整数数列首元素 ~ 连续正整数中间那个数 的距离
//    long minOddLen_start = n / minOddLen - ((minOddLen - 1) / 2);
// 
//    long len;
//    long start;
// 
//    // 连续正整数数列中每个元素都是正整数，因此求解出来的首元素需要大于等于1
//    if (minEvenLen_start >= 1 && minOddLen_start >= 1) {
//      if (minEvenLen < minOddLen) {
//        len = minEvenLen;
//        start = minEvenLen_start;
//      } else {
//        len = minOddLen;
//        start = minOddLen_start;
//      }
//    } else if (minEvenLen_start >= 1) {
//      len = minEvenLen;
//      start = minEvenLen_start;
//    } else if (minOddLen_start >= 1) {
//      len = minOddLen;
//      start = minOddLen_start;
//    } else {
//      return "N";
//    }
// 
//    StringJoiner sj = new StringJoiner("+", n + "=", "");
//    for (long i = start; i < start + len; i++) {
//      sj.add(i + "");
//    }
//    return sj.toString();
//  }
// 
//  /**
//   * @param n 要被分解的正整数（偶数）
//   * @param x n的最大奇因数
//   * @return n分解出来的连续正整数数列的最短奇数长度
//   */
//  public static long getMinOddLen(long n, long x) {
//    // 我们需要遍历 3~x 中的所有奇数，尝试作为 n 分解出来的连续正整数数列的长度
//    if (x < 3) {
//      return -1;
//    }
// 
//    // 找到可以被n整除的最小奇数，由于x是n的最大奇因数，因此这里直接对奇数x进行因数分解
//    for (long i = 3; i * i <= x; i += 2) { // 如果x可以分解为两个奇数y,z，则必然：y <= z，因此x分解出来的最小奇数不可能超过sqrt(x)
//      if (n % i == 0) return i;
//    }
// 
//    return x; // 如果上面x无法分解，则返回x
//  }
//}
