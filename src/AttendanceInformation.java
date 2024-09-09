import java.util.Arrays;
import java.util.Scanner;

/**
 *题目描述
 * 公司用一个字符串来表示员工的出勤信息
 *
 * absent：缺勤
 * late：迟到
 * leaveearly：早退
 * present：正常上班
 * 现需根据员工出勤信息，判断本次是否能获得出勤奖，能获得出勤奖的条件如下：
 *
 * 缺勤不超过一次；
 * 没有连续的迟到/早退；
 * 任意连续7次考勤，缺勤/迟到/早退不超过3次。
 * 输入描述
 * 用户的考勤数据字符串
 *
 * 记录条数 >= 1；
 * 输入字符串长度 < 10000；
 * 不存在非法输入；
 * 输出描述
 * 根据考勤数据字符串，如果能得到考勤奖，输出”true”；否则输出”false”。
 *
 * 用例1
 * 输入
 * 2
 * present
 * present present
 * 输出
 * true true
 * 用例2
 * 输入
 * 2
 * present
 * present absent present present leaveearly present absent
 * 输出
 * true false
 */
public class AttendanceInformation {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		boolean[] list = new boolean[len];
		sc.nextLine();
		Arrays.fill(list, true);
		for(int i = 0; i< len;i++){
			String person = sc.nextLine();
			String[]  array = person.split(" ");
			int error = 0;
			int absent = 0;
			boolean pre = true;
			int start = 0;
			for(int j = 0 ; j < array.length; j++){
				if(array[j].equals("absent") ){
					absent++;
					error++;
				}
				if(array[j].equals("late") || array[j].equals("leaveearly")){
					if(!pre){
						list[i] = false;
						break;
					}
					error++;
					pre = false;
				}else{
					pre = true;
				}
				while(j - start > 7){
					if(array[start].equals("late") || array[start].equals("leaveearly") || array[start].equals("absent")){
						error--;
					}
					start++;
				}
				if(absent > 1 || error > 3){
					list[i] = false;
					break;
				}
			}
		}
		for(int i = 0;i<len;i++){
			System.out.print(list[i] + " ");
		}
	}
}
