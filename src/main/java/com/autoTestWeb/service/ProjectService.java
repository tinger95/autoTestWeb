package com.autoTestWeb.service;

import com.autoTestWeb.dao.ProjectDAO;
import com.autoTestWeb.model.Project;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectService{
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);

	@Resource
	private ProjectDAO projectDao;

	public List<Project> findProjectList(Project project) {
		return projectDao.findProjectList(project);
	}
	public List<Project> findProjectList(){
		return projectDao.findProjectList();
	}
	public int findProjectCount(Project project) {
		return projectDao.findProjectCount(project);
	}
	public int insertProject(Project project) {
		try {
			return projectDao.insertProject(project);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public Project findProjectById(int id) {
		return projectDao.findProjectById(id);
	}

	public int deleteProject(int id) {
		try {
			return projectDao.deleteProject(id);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int updateProject(Project project) {
		try {
			return projectDao.updateProject(project);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}
}
