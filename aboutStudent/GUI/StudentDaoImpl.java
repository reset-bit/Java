package homework.aboutstudent.GUI;

import java.util.*;
import java.io.*;
public class StudentDaoImpl implements StudentDao{
	private ArrayList<Student> students;
	private ArrayList<String> allcourse;
	
	public StudentDaoImpl() {
		students = new ArrayList<Student>();
		allcourse = new ArrayList<String>();
	}
	
	public boolean writeStudent(String name, int no, Date birthday, String courses) {
		Student stu = new Student(no, name, birthday);
		String[] tmp = courses.split("/");
		for(int i = 0; i < tmp.length; ++i) {
			String[] t = tmp[i].split("-");
			stu.addCourse(t[0], Integer.parseInt(t[1]));
			allcourse.add(t[0]);
		}
		students.add(stu);
		return true;
	}
	
	public boolean modifyStudent(int no, int n) {
		for(int i = 0; i < students.size(); ++i) {
			if(students.get(i).getNo() == no) {
				students.get(i).setNo(n);
				return true;
			}
		}
		return false;
	}
	
	public boolean modifyStudent(int no, String name) {
		for(int i = 0; i < students.size(); ++i) {
			if(students.get(i).getNo() == no) {
				students.get(i).setName(name);
				return true;
			}
		}
		return false;
	}
	
	public boolean modifyStudent(int no, Date birthday) {
		for(int i = 0; i < students.size(); ++i) {
			if(students.get(i).getNo() == no) {
				students.get(i).setBirthday(birthday);
				return true;
			}
		}
		return false;
	}
	
	public boolean modifyStudent(int no, String course, int score) {
		for(int i = 0; i < students.size(); ++i) {
			if(students.get(i).getNo() == no) {
				Map<String, Integer> map = students.get(i).getCourses();
				for(Map.Entry<String, Integer> entry:map.entrySet()) {
					if(entry.getKey().equals(course)) {
						students.get(i).setCousre(course, score);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean deleteStudent(int no) {
		for(int i = 0; i < students.size(); ++i) {
			if(students.get(i).getNo() == no) {
				students.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public HashMap<String,Integer> getCoursesAverage(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if(allcourse.size() == 0) {
			map.put("无", new Integer(0));
		}
		else {
			for(int i = 0; i < allcourse.size(); ++i) {
				int n = 0;//当前课程全部学生数
				int a = 0;//当前课程全部学生分数和
				for(int j = 0; j < students.size(); ++j) {
					for(Map.Entry<String, Integer> entry:students.get(j).getCourses().entrySet()) {
						if(entry.getKey().equals(allcourse.get(i))) {
							++n;
							a += entry.getValue();
						}
					}
				}
				map.put(allcourse.get(i), (int)(a / n));
			}
		}
		return map;
	}
	
	public Student readStuByNo(int no) {
		for(int i = 0; i < students.size(); ++i) {
			if(students.get(i).getNo() == no) {
				return students.get(i);
			}
		}
		return null;
	}
	
	public List<Student> readStuByCourse(){
		List<Student> list = students;
		Collections.sort(list, new Comparator<Student>() {
			public int compare(Student s1, Student s2) {
				return s1.getScoreAverage() - s2.getScoreAverage();
			}
		});
		return list;
	}
	
	public List<Student> showAllStu(){
		return students;
	}
	
	public boolean storeAllStu() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("d:\\stu.txt"));
			oos.writeObject(students);
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
}
