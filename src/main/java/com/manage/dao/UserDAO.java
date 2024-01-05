package com.manage.dao;

import java.util.List;

import com.manage.user.User;

public interface UserDAO {
	
	void insertUser(User user);
	
	User selectUser(int id);
	
	List<User> selectAllUsers();
	
	boolean updateUser(User user);
	
	boolean deleteUser(int id);

}
