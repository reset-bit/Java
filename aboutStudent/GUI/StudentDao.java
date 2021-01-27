package homework.aboutstudent.GUI;

import java.util.*;
public interface StudentDao {
	
	boolean writeStudent(String name, int no, Date birthday, String courses);
	
	boolean modifyStudent(int no, int n);
	
	boolean modifyStudent(int no, String name);
	
	boolean modifyStudent(int no, Date birthday);
	
	boolean modifyStudent(int no, String course, int score);
	
	boolean deleteStudent(int no);
	
	HashMap<String,Integer> getCoursesAverage();
	
	Student readStuByNo(int no);
	
	List<Student> readStuByCourse();
	
	List<Student> showAllStu();
	
	boolean storeAllStu();
}
