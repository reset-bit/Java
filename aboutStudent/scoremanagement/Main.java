package homework.aboutstudent.scoremanagement;

/**
 * 学生成绩管理系统模拟：
 * （1）提供Student信息的保存功能：通过控制台输入若干个学生的学号、姓名以及每个学生所修课程的课程名和成绩，将其信息保存到data.dat中；
 * （2）数据读取显示：能够从data.dat文件中读取学生及其课程成绩并显示于控制台。
 * 
 * 以ctrl+Z作为结束学生录入，以end作为结束课程及成绩录入
 * 测试用例：
 * 1 Tom Java 90 C 79 end
 * 3 Bob math 77 Java 89 end
 * 4 Mary math 90 end
 * */

import java.io.*;
import java.lang.String;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		creatStu();
		readStu();
	}
	
	public static void creatStu() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input the student id, name, course and grade of certain students:");
		List<Student> list = new ArrayList<Student>();
		while(scan.hasNext()) {
			Student stu = new Student(scan.nextInt(), scan.next());
			String temp = scan.next();
			while(!"end".equals(temp)) {
				stu.addCourse(temp, scan.nextInt());
				temp = scan.next();
			}
			list.add(stu);
			System.out.println("Write to successful");
		}
		
		ObjectOutputStream foo = null;
		try {
			foo = new ObjectOutputStream(new FileOutputStream("d:\\data.dat", true));
			foo.writeObject(list);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				foo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		scan.close();
	}
	
	public static void readStu(){
		ObjectInputStream foi = null;
		try {
			foi = new ObjectInputStream(new FileInputStream(new File("d:\\data.dat")));
			List<Student> list = (List<Student>)foi.readObject();
			for(Student s:list) {
				System.out.print("No."+s.getNo()+"   "+s.getName()+"   ");
				s.showCourses();
				System.out.println();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				foi.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}