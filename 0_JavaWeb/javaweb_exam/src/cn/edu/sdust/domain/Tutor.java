package cn.edu.sdust.domain;

public class Tutor {
	private int tutorid;
	private String tutorname;
	private int years;
	private String tutorinformation;
	
	
	public Tutor() {	}
	public Tutor(int tutorid, String tutorname, int years, String tutorinformation) {
		super();
		this.tutorid = tutorid;
		this.tutorname = tutorname;
		this.years = years;
		this.tutorinformation = tutorinformation;
	}
	public int getTutorid() {
		return tutorid;
	}
	public void setTutorid(int tutorid) {
		this.tutorid = tutorid;
	}
	public String getTutorname() {
		return tutorname;
	}
	public void setTutorname(String tutorname) {
		this.tutorname = tutorname;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public String getTutorinformation() {
		return tutorinformation;
	}
	public void setTutorinformation(String tutorinformation) {
		this.tutorinformation = tutorinformation;
	}
	
}
