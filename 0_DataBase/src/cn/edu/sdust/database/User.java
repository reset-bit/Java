package cn.edu.sdust.database;

public class User {
	private String username;
	private String password;
	private String permissions;
	
	public User() {}

	public User(String username, String password, String permissions) {
		super();
		this.username = username;
		this.password = password;
		this.permissions = permissions;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
}
