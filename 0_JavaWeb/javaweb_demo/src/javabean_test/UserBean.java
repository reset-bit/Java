package javabean_test;

public class UserBean {
	private String username;
	private String friut;
	
	public UserBean() {
		username = "null";
		friut = "null";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFriut() {
		return friut;
	}
	public void setFriut(String friut) {
		this.friut = friut;
	}
}
