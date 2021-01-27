package cn.edu.sdust.service;

import cn.edu.sdust.domain.User;

public interface IUserService {
	public User selectUserByName(String username);
	public User selectUserById(int id);
	public int checkUserByPassword(String username, String password);
}
