package cn.edu.sdust.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.sdust.domain.User;

public interface IUserService {
	public List<User> getAllUsers();
	public User getUserById(int id);
	public List<User> getUserByUsername(String username);
	public List<User> getUserByMobile(String mobile);
	public List<User> getUserByDeptid(int deptid);
	public int addUser(User user);
	public int editUser(User user);
	public int removeUserById(int id);
}
