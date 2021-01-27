package cn.edu.sdust.mapper;

import org.apache.ibatis.annotations.Param;

import cn.edu.sdust.domain.User;

public interface IUserDao {
	public User selectUserByName(@Param("username")String username);
	public User selectUserById(@Param("id")int id);
	public int checkUserByPassword(@Param("username")String username,@Param("password")String password);
}
