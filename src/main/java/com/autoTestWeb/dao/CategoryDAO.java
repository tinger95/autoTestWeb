package com.autoTestWeb.dao;

import com.autoTestWeb.model.Category;

import java.util.List;

public interface CategoryDAO {
	List<Category> findCategoryListByUserId(int userId);
	int insertCategory(Category category);
	Category findCategoryById(int id);
	int updateCategory(Category category);
	int deleteCategory(int id);
	List<Category> findCategoryList(Category category);
}
