package com.autoTestWeb.dao;

import com.autoTestWeb.model.Project;

import java.util.List;

public interface ProjectDAO{
	List<Project> findProjectList(Project project);
	List<Project> findProjectList();
	int findProjectCount(Project project);
	int insertProject(Project project);
	Project findProjectById(int id);
	int deleteProject(int id);
	int updateProject(Project project);
}
