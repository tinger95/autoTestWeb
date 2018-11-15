package com.autoTestWeb.service;

import java.util.List;

import com.autoTestWeb.dao.CategoryDAO;
import com.autoTestWeb.model.Category;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CategoryService {
	private static final Logger LOGGER = Logger.getLogger(CategoryService.class);

	@Resource
	private CategoryDAO categoryDao;

	public List<Category> findCategoryListByUserId(int userId) {
		return categoryDao.findCategoryListByUserId(userId);
	}

	public int insertCategory(Category category) {
		try {
			return categoryDao.insertCategory(category);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public Category findCategoryById(int id) {
		return categoryDao.findCategoryById(id);
	}

	public int updateCategory(Category category) {
		try {
			return categoryDao.updateCategory(category);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int deleteCategory(int id) {
		try {
			return categoryDao.deleteCategory(id);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}
	public List<Category> findCategoryList(Category category){
		return categoryDao.findCategoryList(category);
	}

}
