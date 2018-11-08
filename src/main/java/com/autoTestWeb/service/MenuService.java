package com.autoTestWeb.service;

import com.autoTestWeb.dao.MenuDAO;
import com.autoTestWeb.model.Menu;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
	private static final Logger LOGGER = Logger.getLogger(MenuService.class);

	@Resource
	private MenuDAO menuDao;

	public List<Menu> findMenuList() {
		return menuDao.findMenuList();
	}
	public List<Menu> findParentMenuList()
	{
		return menuDao.findParentMenuList();
	}
	public List<Menu> findMenuListByParentId(int parentMenuId)
	{
		return menuDao.findMenuListByParentId(parentMenuId);
	}
	public List<Menu> findParentRoleMenuList(Menu menu){
		return menuDao.findParentRoleMenuList(menu);
	}
	public int insertMenu(Menu menu) {
		try {
			return menuDao.insertMenu(menu);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int updateMenu(Menu menu) {
		try {
			return menuDao.updateMenu(menu);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public Menu findMenuById(int id) {
		return menuDao.findMenuById(id);
	}

	public int deleteMenu(int id) {
		try {
			int i = menuDao.deleteMenu(id);
			return i;
		} catch (Exception e) {
			LOGGER.info(e.toString());
			throw new RuntimeException(e.toString());
		}

	}
}
