package com.autoTestWeb.dao;

import com.autoTestWeb.model.UserGroup;

import java.util.List;

public interface UserGroupDAO {
	List<UserGroup> findUserGroupList();
	int insertUserGroup(UserGroup userGroup);
	int updateUserGroup(UserGroup userGroup);
	UserGroup findUserGroupById(int id);
	int deleteUserGroup(int id);
}
