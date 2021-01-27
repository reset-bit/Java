package homework.aboutstudent.scoremanagement;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable{
	private int no;
	private String name;
	private Map<String, Integer> courses = new HashMap<>();
	
	Student(){
		this.no = 0;
		this.name = null;
	}
	
	Student(int no, String name){
		this.no = no;
		this.name = name;
	}
	
	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}
	
	public void showCourses() {
		for(Map.Entry<String, Integer> entry:courses.entrySet()) {
			System.out.print("course:"+entry.getKey()+"---score:"+entry.getValue()+"   ");
		}
	}

	public void addCourse(String course, int score) {
		if(!courses.containsKey(course)) {
			courses.put(course, new Integer(score));
		}
	}
}
