package homework.aboutstudent;


/**
 * 学生成绩读取与排序
 * 
 * 本题实现方法进行了简化，没有存储course属性
 * 若希望存储该属性：建立String数组，由数组长度得到最大Student数
 * 从而在Student类中建立长度为最大Student的course数组和score数组
 * 
 * 提示：多维数组/容器
 * 分离main函数
 * */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;
public class ScoreReadingAndSorting {
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
        List<Student> stulist = new ArrayList<Student>();
    	String data = scan.next();//或scan.nextLine()
    	String[] tmp = data.split(",");
    	Comparator cmp = new MyComparator();
    	
        while(!tmp[0].equals("exit")) {
        	if(stulist.size() == 0) {
        		Student s = new Student(tmp[0], Integer.parseInt(tmp[1]), Integer.parseInt(tmp[3]));
    			stulist.add(s);
        	}
        	else {
        		int flag = 0;
        		int tmpno = Integer.parseInt(tmp[1]);
        		for(int i = 0; i < stulist.size(); ++i) {
	        		if(tmpno == stulist.get(i).getNo()) {//学号相同
	        			stulist.get(i).addScore(Integer.parseInt(tmp[3]));
	        			flag = 1;
	        		}
	        	}
        		if(flag == 0) {
        			Student s = new Student(tmp[0], Integer.parseInt(tmp[1]), Integer.parseInt(tmp[3]));
	        		stulist.add(s);
        		}
        	}
        	data = scan.next();
        	tmp = data.split(",");
        }
        Collections.sort(stulist, cmp);
        for(int i = 0; i < stulist.size(); ++i) {
        	System.out.println("No"+(i + 1)+":"+stulist.get(i).show());
        }
        scan.close();
    }
}

class Student{
	private int no;
	private String name;
	private int scores = 0;
	private int numofcourse = 0;
	
	Student(String name, int no, int score){
		this.no = no;
		this.name = name;
		scores += score;
		++numofcourse;
	}
	
	public int getNo() {
		return no;
	}
	
	public String show() {
		return no+","+name;
	}

	public void addScore(int score) {
		scores += score;
		++numofcourse;
	}
	
	public double getAverageScore() {
		return scores / numofcourse;
	}
}

class MyComparator implements Comparator{
	
	/**
	 * 重写compare方法：
	 * 按照平均成绩降序排列（如果平均成绩相同，学号数字小的排在前面）
	 * */
	public int compare(Object student1, Object student2) {
		Student s1 = (Student)student1;
		Student s2 = (Student)student2;
		double tmp = s1.getAverageScore() - s2.getAverageScore();
		if(tmp > 0) {
			return -1;
		}
		else if(tmp < 0) {
			return 1;
		}
		else {
			return s1.getNo() - s2.getNo();
		}
	}
}
