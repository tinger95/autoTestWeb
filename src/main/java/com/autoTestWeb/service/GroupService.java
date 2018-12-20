package com.autoTestWeb.service;

import com.autoTestWeb.dao.GroupDAO;
import com.autoTestWeb.model.Group;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupService {
    private static final Logger LOGGER = Logger.getLogger(GroupService.class);
    @Resource
    private GroupDAO groupDao;

    public List<Group> findGroupList(Group group) {
        return groupDao.findGroupList(group);
    }

    public List<Group> findGroupListByProjectId(int projectId) {
        return groupDao.findGroupListByProjectId(projectId);
    }

    public int insertGroup(Group group) {
        try {
            return groupDao.insertGroup(group);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    public int updateGroup(Group group) {
        try {
            return groupDao.updateGroup(group);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    public Group findGroupById(int id) {
        return groupDao.findGroupById(id);
    }

    public int deleteGroup(int id) {
        try {
            int i = groupDao.deleteGroup(id);
            return i;
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }

    }

    public Group findGroupByCode(String code) {
        return groupDao.findGroupByCode(code);
    }

}
