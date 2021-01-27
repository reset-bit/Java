package cn.edu.sdust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.sdust.domain.User;
import cn.edu.sdust.mapper.IUserDao;
import cn.edu.sdust.service.IUserService;

@Service("IUserService")
public class UserServiceImpl implements IUserService{
	@Autowired
	IUserDao iuserdao;
	
	@Override
	public User selectUserByName(String username) {
		return iuserdao.selectUserByName(username);
	}
	@Override
	public User selectUserById(int id) {
		return iuserdao.selectUserById(id);
	}
	@Override
	public int checkUserByPassword(String username, String password) {
		return iuserdao.checkUserByPassword(username, password);
	}
}
