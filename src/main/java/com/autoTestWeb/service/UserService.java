package com.autoTestWeb.service;

import com.autoTestWeb.controller.UserController;
import com.autoTestWeb.dao.UserDao;
import com.autoTestWeb.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService{
	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Resource
	private UserDao userDao;

	public int deleteUser(int id) {
		try {
			return userDao.deleteUser(id);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public List<User> findUserList() {
		return userDao.findUserList();
	}

	public int insertUser(User user) {
		try {
			return userDao.insertUser(user);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int updateUser(User user) {
		try {
			return userDao.updateUser(user);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public User findUserByName(String username) {
		return userDao.findUserByName(username);
	}

	public User findUserById(int id) {
		User user= userDao.findUserById(id);
		if(user==null){
			user=new User();
		}
		return user;
	}
}
