package com.autoTestWeb.service;

import com.autoTestWeb.dao.EnvironmentDAO;
import com.autoTestWeb.model.Environment;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EnvironmentService {
	private static final Logger LOGGER = Logger.getLogger(EnvironmentService.class);

	@Resource
	private EnvironmentDAO environmentDao;

	public List<Environment> findEnvironmentList() {
		return environmentDao.findEnvironmentList();
	}
	public List<Environment> findEnvironmentListByProjectId(int projectId){
		return environmentDao.findEnvironmentListByProjectId(projectId);
	}
	public int insertEnvironment(Environment environment) {
		try {
			return environmentDao.insertEnvironment(environment);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public Environment findEnvironmentById(int id) {
		return environmentDao.findEnvironmentById(id);
	}

	public int deleteEnvironment(int id) {
		try {
			return environmentDao.deleteEnvironment(id);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int updateEnvironment(Environment environment) {
		try {
			return environmentDao.updateEnvironment(environment);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

}
