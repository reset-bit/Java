package homework.aboutstudent;

/**
 * 构造main函数，从键盘中读入三个学生的信息，比较他们的成绩，按照成绩由高到低排列输出
 * 
 * @author Reset
 * @date 2020/5/8 晚
 * */
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class ScoreSorting {
	public static void main(String[] args) {
		final int N = 3;
		Scanner scan = new Scanner(System.in);
		Student1 students[] =new Student1 [N];
		
		for(int i = 0; i < N; i ++ ) {
			students[i] = new Student1(scan.nextInt(), scan.next(), scan.nextInt());
		}
		/**
		 * 对comparator接口的重写（因为student类没有实现comparable接口，故选择comparator）
		 * 
		 * @param students 排序数组
		 * @param new Comparator<student> 新建比较器对象
		 * 
		 * @return >0 s2.score高于s1.score，s2在前
		 * @return <0 s2.score低于s1.score，s2在后
		 * @return =0   按传参顺序并列
		 * */
		Arrays.sort(students, new Comparator<Object>(){
			public int compare(Object o1, Object o2) {
				Student1 s1 = (Student1) o1;
				Student1 s2 = (Student1) o2;
				return s2.getScore() - s1.getScore();
			}
		});
		for(int i = 0; i < N; ++i) {
			students[i].print();
		}
		scan.close();
	}
}
class Student1 {
	private int no;
	private String name;
    private int score;
	
	public Student1(int _no, String _name, int _score) {
		no = _no;
		name = _name;
        score = _score;
	}
    public int getNo() {return no;}
    public String getName() {return name;}
    public int getScore() {return score;}
	public void print(){
		System.out.println(no + " "+name+" "+score);
	}
}