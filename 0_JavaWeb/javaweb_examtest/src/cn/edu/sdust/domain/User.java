package cn.edu.sdust.domain;

public class User {
	private int id;
	private String username;
	private String mobile;
	private int deptid;
	
	
	public User() {	}
	public User(int id, String username, String mobile, int deptid) {
		super();
		this.id = id;
		this.username = username;
		this.mobile = mobile;
		this.deptid = deptid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
}
