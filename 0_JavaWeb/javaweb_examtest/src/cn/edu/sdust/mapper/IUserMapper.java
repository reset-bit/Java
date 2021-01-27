package cn.edu.sdust.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.sdust.domain.User;

public interface IUserMapper {
	public List<User> selectAllUsers();
	public User selectUserById(@Param("id")int id);
	public List<User> selectUserByUsername(String username);
	public List<User> selectUserByMobile(String mobile);
	public List<User> selectUserByDeptid(int deptid);
	public int insertUser(User user);
	public int updateUser(User user);
	public int deleteUserById(@Param("id")int id);
}
