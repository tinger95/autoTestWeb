package com.autoTestWeb.dao;

import com.autoTestWeb.model.Group;

import java.util.List;

public interface GroupDAO {
	List<Group> findGroupList(Group group);
	List<Group> findGroupListByProjectId(int projectId);
	int insertGroup(Group group);
	int updateGroup(Group group);
	Group findGroupById(int id);
	int deleteGroup(int id);
	Group findGroupByCode(String code);
}
