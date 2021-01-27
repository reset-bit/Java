package cn.edu.sdust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.sdust.domain.User;
import cn.edu.sdust.mapper.IUserMapper;
import cn.edu.sdust.service.IUserService;


@Service("IUserService")
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserMapper usermapper;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return usermapper.selectAllUsers();
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return usermapper.selectUserById(id);
	}

	@Override
	public List<User> getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return usermapper.selectUserByUsername(username);
	}

	@Override
	public List<User> getUserByMobile(String mobile) {
		// TODO Auto-generated method stub
		return usermapper.selectUserByMobile(mobile);
	}

	@Override
	public List<User> getUserByDeptid(int deptid) {
		// TODO Auto-generated method stub
		return usermapper.selectUserByDeptid(deptid);
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return usermapper.insertUser(user);
	}

	@Override
	public int editUser(User user) {
		// TODO Auto-generated method stub
		return usermapper.updateUser(user);
	}

	@Override
	public int removeUserById(int id) {
		// TODO Auto-generated method stub
		return usermapper.deleteUserById(id);
	}

}
