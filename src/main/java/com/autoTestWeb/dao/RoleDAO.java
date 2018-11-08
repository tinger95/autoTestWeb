package com.autoTestWeb.dao;

import com.autoTestWeb.model.Role;

import java.util.List;
public interface RoleDAO {
	List<Role> findRoleList();
	int insertRole(Role role);
	int updateRole(Role role);
	Role findRoleById(int id);
	int deleteRole(int id);
}
