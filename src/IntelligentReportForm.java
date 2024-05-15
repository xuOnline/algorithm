import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 小明来到某学校当老师，需要将学生按考试总分或单科分数进行排名，你能帮帮他吗？
 * <p>
 * 输入描述
 * 第 1 行输入两个整数，学生人数 n 和科目数量 m。
 * <p>
 * 0 < n < 100
 * 0 < m < 10
 * 第 2 行输入 m 个科目名称，彼此之间用空格隔开。
 * <p>
 * 科目名称只由英文字母构成，单个长度不超过10个字符。
 * 科目的出现顺序和后续输入的学生成绩一一对应。
 * 不会出现重复的科目名称。
 * 第 3 行开始的 n 行，每行包含一个学生的姓名和该生 m 个科目的成绩（空格隔开）
 * <p>
 * 学生不会重名。
 * 学生姓名只由英文字母构成，长度不超过10个字符。
 * 成绩是0~100的整数，依次对应第2行种输入的科目。
 * 第n+2行，输入用作排名的科目名称。若科目不存在，则按总分进行排序。
 * <p>
 * 输出描述
 * 输出一行，按成绩排序后的学生名字，空格隔开。成绩相同的按照学生姓名字典顺序排序。
 * <p>
 * 用例1
 * 输入
 * 3 2
 * yuwen shuxue
 * fangfang 95 90
 * xiaohua 88 95
 * minmin 100 82
 * shuxue
 * 输出
 * xiaohua fangfang minmin
 */
public class IntelligentReportForm {
	static class Student {
		String name;
		Integer score;

		private void setName(String name) {
			this.name = name;
		}

		private void setScore(int score) {
			this.score = score;
		}

		private String getName() {
			return this.name;
		}

		private Integer getScore() {
			return this.score;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 学生人数
		int studentNum = sc.nextInt();
		// 学科数量
		int subjectNum = sc.nextInt();
		sc.nextLine();
		// 学科名称
		String aaa = sc.nextLine();
		String[] subjectName = aaa.split(" ");
		HashMap<String, List<Student>> map = new HashMap<String, List<Student>>();
		map.put("all", new ArrayList<>());
		// 输入学生与学生成绩
		for (int i = 0; i < studentNum; i++) {
			// 输入学生与学生成绩
			String all = sc.nextLine();
			String[] info = all.split(" ");
			Integer totalScore = 0;
			for (int j = 1; j < info.length; j++) {
				// 生成学生与分数数据
				Student st = new Student();
				st.setName(info[0]);
				Integer score = Integer.parseInt(info[j]);
				totalScore = totalScore + score;
				st.setScore(score);
				if (map.containsKey(subjectName[j-1])) {
					List<Student> list = map.get(subjectName[j-1]);
					list.add(st);
				} else {
					List<Student> list = new ArrayList<>();
					list.add(st);
					map.put(subjectName[j-1], list);
				}
			}
			// 记录学生总分
			List<Student> total = map.get("all");
			Student tost = new Student();
			tost.setName(info[0]);
			tost.setScore(totalScore);
			total.add(tost);
		}

		// 输入学科
		String xueke = sc.nextLine();
		List<Student> sort;
		if (map.containsKey(xueke)) {
			sort = map.get(xueke);
		} else {
			sort = map.get("all");
		}
		// 排序输出
		sort.stream().sorted((y, x) -> x.getScore().equals(y.getScore()) ? y.getName().compareTo(x.getName()) : x.getScore() - y.getScore()).forEach(St -> {
			System.out.print(St.getName() + " ");
		});
		System.out.println();
	}
}

