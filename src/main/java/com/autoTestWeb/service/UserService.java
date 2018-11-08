package com.autoTestWeb.service;

import com.autoTestWeb.controller.UserController;
import com.autoTestWeb.dao.UserMapper;
import com.autoTestWeb.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService{
	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Resource
	private UserMapper userMapper;

	public int deleteUser(int id) {
		try {
			return userMapper.deleteUser(id);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public List<User> findUserList() {
		return userMapper.findUserList();
	}

	public int insertUser(User user) {
		try {
			return userMapper.insertUser(user);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int updateUser(User user) {
		try {
			return userMapper.updateUser(user);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public User findUserByName(String username) {
		return userMapper.findUserByName(username);
	}

	public User findUserById(int id) {
		User user= userMapper.findUserById(id);
		if(user==null){
			user=new User();
		}
		return user;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}
