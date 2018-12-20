package com.autoTestWeb.service;

import com.autoTestWeb.controller.UserController;
import com.autoTestWeb.dao.UserGroupDAO;
import com.autoTestWeb.model.UserGroup;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserGroupService{
	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Resource
	private UserGroupDAO userGroupDao;

	public List<UserGroup> findUserGroupList() {
		return userGroupDao.findUserGroupList();
	}

	public int insertUserGroup(UserGroup userGroup) {
		try {
			return userGroupDao.insertUserGroup(userGroup);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int updateUserGroup(UserGroup userGroup) {
		try {
			return userGroupDao.updateUserGroup(userGroup);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public UserGroup findUserGroupById(int id) {
		return userGroupDao.findUserGroupById(id);
	}

	public int deleteUserGroup(int id) {
		try {
			int i = userGroupDao.deleteUserGroup(id);
			return i;
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}

	}

	public UserGroupDAO getUserGroupDao() {
		return userGroupDao;
	}

	public void setUserGroupDao(UserGroupDAO userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

}
