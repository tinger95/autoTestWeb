package com.autoTestWeb.dao;

import com.autoTestWeb.model.Environment;
import java.util.List;

public interface EnvironmentDAO{
	List<Environment> findEnvironmentList();
	int insertEnvironment(Environment environment);
	Environment findEnvironmentById(int id);
	int deleteEnvironment(int id);
	int updateEnvironment(Environment environment);
	List<Environment> findEnvironmentListByProjectId(int projectId);
}
