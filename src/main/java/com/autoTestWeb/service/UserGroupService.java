package com.autoTestWeb.service;

import java.util.List;

import com.autoTestWeb.dao.UserGroupDAO;
import com.autoTestWeb.model.UserGroup;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserGroupService {
    private static final Logger LOGGER = Logger.getLogger(UserGroupService.class);
    @Resource
    private UserGroupDAO userGroupDao;

    public List<UserGroup> findUserGroupList() {
        return userGroupDao.findUserGroupList();
    }

    public int insertUserGroup(UserGroup userGroup) {
        try {
            return userGroupDao.insertUserGroup(userGroup);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    public int updateUserGroup(UserGroup userGroup) {
        try {
            return userGroupDao.updateUserGroup(userGroup);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    public UserGroup findUserGroupById(int id) {
        return userGroupDao.findUserGroupById(id);
    }

    public int deleteUserGroup(int id) {
        try {
            int i = userGroupDao.deleteUserGroup(id);
            return i;
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }

    }

}
