package com.autoTestWeb.service;

import com.autoTestWeb.dao.RoleDAO;
import com.autoTestWeb.model.Role;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
    private static final Logger LOGGER = Logger.getLogger(RoleService.class);

    @Resource
    private RoleDAO roleDao;

    public List<Role> findRoleList() {
        return roleDao.findRoleList();
    }

    public int insertRole(Role role) {
        try {
            return roleDao.insertRole(role);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    public int updateRole(Role role) {
        try {
            return roleDao.updateRole(role);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    public Role findRoleById(int id) {
        return roleDao.findRoleById(id);
    }

    public int deleteRole(int id) {
        try {
            int i = roleDao.deleteRole(id);
            return i;
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }

    }
}
