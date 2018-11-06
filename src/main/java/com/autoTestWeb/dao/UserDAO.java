package com.autoTestWeb.dao;

import java.util.List;

import com.autoTestWeb.model.User;

public interface UserDAO {
	User findUserByName(String username);
	List<User> findUserList();
	int deleteUser(int id);
	int insertUser(User user);
	int updateUser(User user);
	User findUserById(int id);
}
