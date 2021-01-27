package homework.aboutstudent.GUI;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int no;
	private String name;
	private Date birthday;
	private Map<String, Integer> courses = new HashMap<>();
	private int sum;//课程数目
	
	Student(){
		this.no = 0;
		this.name = null;
	}
	
	Student(int no, String name, Date birthday){
		this.no = no;
		this.name = name;
		this.birthday = birthday;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public Map<String, Integer> getCourses() {
		return courses;
	}

	public int getScoreAverage() {
		int sumScore = 0;
		for(Map.Entry<String, Integer> entry:courses.entrySet()) {
			sumScore += entry.getValue();
		}
		if(sum != 0) {
			return sumScore / sum;
		}
		return 0;
	}

	public boolean addCourse(String course, int score) {
		if(!courses.containsKey(course)) {
			courses.put(course, new Integer(score));
			++sum;
			return true;
		}
		return false;
	}
	
	public boolean setCousre(String course, int score) {
		if(courses.containsKey(course)) {
			courses.put(course, new Integer(score));
			return true;
		}
		return false;
	}
	
	public boolean deleteCourse(String course) {
		if(courses.containsKey(course)) {
			courses.remove(course);
			--sum;
			return true;
		}
		return false;
	}
}
