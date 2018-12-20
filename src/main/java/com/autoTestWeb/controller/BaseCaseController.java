package com.autoTestWeb.controller;

import com.autoTestWeb.model.*;
import com.autoTestWeb.service.BaseCaseService;
import com.autoTestWeb.service.CaseService;
import com.autoTestWeb.service.GroupService;
import com.autoTestWeb.service.ProjectService;
import com.autoTestWeb.util.BaseUtil;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class BaseCaseController {
    private static final Logger LOGGER = Logger.getLogger(BaseCaseController.class);

    @Autowired
    private CaseService caseService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private BaseCaseService baseCaseService;
    @Autowired
    private GroupService groupService;

    /**
     * 基础Case管理初始化basecase表单
     *
     * @param modelMap
     * @param caseId
     * @return
     */
    @RequestMapping(value = "case/initBaseCase.go")
    public String initBaseCase(ModelMap modelMap, @RequestParam("caseId") String caseId) {
        Case executeCase = caseService.findCaseById(Integer.parseInt(caseId));
        List<Project> projectList = projectService.findProjectList();
        modelMap.put("executeCase", executeCase);
        modelMap.put("projectList", projectList);
        modelMap.put("caseId", caseId);
        return "case/baseCaseList";
    }

    /**
     * 查询basecase列表
     *
     * @param modelMap
     * @param baseCase
     * @param response
     */
    @RequestMapping(value = "case/findBaseCaseList.go")
    @ResponseBody
    public void findBaseCaseList(ModelMap modelMap, @RequestBody BaseCase baseCase, HttpServletResponse response) {
        try {
            if (baseCase.getCaseId() > 0) {
                baseCase.setUserId(0);
            }
            List<BaseCase> baseCaseList = baseCaseService.findBaseCaseList(baseCase);
            JSONArray json = new JSONArray();
            for (BaseCase bc : baseCaseList) {
                JSONObject jo = new JSONObject();
                jo.put("id", bc.getId());
                jo.put("name", bc.getName());
                jo.put("comment", bc.getComment());
                jo.put("groupId", bc.getGroupId());
                jo.put("groupName", bc.getGroupName());
                jo.put("parentGroupName", bc.getParentGroupName());
                jo.put("projectId", bc.getProjectId());
                jo.put("projectName", bc.getProjectName());
                jo.put("userName", bc.getUserName());
                jo.put("status", bc.getStatus() == 1 ? "关闭" : "打开");
                jo.put("caseId", bc.getCaseId());
                jo.put("sort", bc.getSort());
                jo.put("kind", bc.getKind() == 1 ? "前台" : "后台");
                jo.put("userId", bc.getUserId());
                json.put(jo);
            }
            BaseUtil.writeJson(baseCaseList.size(), json, response);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    /**
     * 添加basecase
     *
     * @param caseId
     * @param baseCaseId
     * @param baseCase
     * @param request
     * @param response
     */
    @RequestMapping(value = "case/insertBaseCase.go")
    public void insertBaseCase(@RequestParam("caseId") String caseId, @RequestParam("baseCaseId") String baseCaseId, @RequestBody BaseCase baseCase, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        baseCase.setCaseId(Integer.parseInt(caseId));
        baseCase.setUserId(user.getId());
        int maxSort = baseCaseService.findMaxSort(Integer.parseInt(caseId));
        if (Integer.parseInt(caseId) > 0) {
            baseCase.setSort(maxSort + 1);
        } else {
            baseCase.setSort(0);
        }
        int i = baseCaseService.insertBaseCase(baseCase, Integer.parseInt(baseCaseId));
        BaseUtil.writeInteger(i, response);
    }

    /**
     * 获取组信息
     *
     * @param parentGroupId
     * @param projectId
     * @param response
     */
    @RequestMapping(value = "case/findGroupList.go")
    public void findGroupList(@RequestParam("parentGroupId") String parentGroupId, @RequestParam("projectId") String projectId, HttpServletResponse response) {
        try {
            Group g2 = new Group();
            g2.setProjectId(Integer.parseInt(projectId));
            g2.setParentGroupId(Integer.parseInt(parentGroupId));
            List<Group> groupList = groupService.findGroupList(g2);
            JSONArray json = new JSONArray();
            for (Group g : groupList) {
                JSONObject jo = new JSONObject();
                jo.put("id", g.getId());
                jo.put("name", g.getName());
                json.put(jo);
            }
            BaseUtil.writeJson(groupList.size(), json, response);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    /**
     * 根据baseCaseId获取basecase
     *
     * @param baseCaseId
     * @param response
     */
    @RequestMapping(value = "case/findBaseCaseById.go")
    public void findBaseCaseById(@RequestParam("baseCaseId") String baseCaseId, HttpServletResponse response) {
        try {
            BaseCase baseCase = baseCaseService.findBaseCaseById(Integer.parseInt(baseCaseId));
            JSONArray json = new JSONArray();
            JSONObject jo = new JSONObject();
            jo.put("id", baseCase.getId());
            jo.put("name", baseCase.getName());
            jo.put("comment", baseCase.getComment());
            jo.put("groupId", baseCase.getGroupId());
            jo.put("parentGroupId", baseCase.getParentGroupId());
            jo.put("projectId", baseCase.getProjectId());
            jo.put("status", baseCase.getStatus());
            jo.put("kind", baseCase.getKind());
            json.put(jo);
            BaseUtil.writeJson(1, json, response);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    /**
     * 编辑basecase
     *
     * @param baseCase
     * @param response
     */
    @RequestMapping(value = "case/updateBaseCase.go")
    private void updateBaseCase(@RequestBody BaseCase baseCase, HttpServletResponse response) {
        int i = baseCaseService.updateBaseCase(baseCase);
        BaseUtil.writeInteger(i, response);
    }

    /**
     * 删除basecase
     *
     * @param baseCaseId
     * @param response
     */
    @RequestMapping(value = "case/deleteBaseCase.go")
    private void deleteBaseCase(@RequestParam String baseCaseId, HttpServletResponse response) {
        int i = baseCaseService.deleteBaseCase(Integer.parseInt(baseCaseId));
        BaseUtil.writeInteger(i, response);
    }

    /**
     * 复制basecase
     * @param baseCaseId
     * @param request
     * @param response
     */
    @RequestMapping(value = "case/copyBaseCase.go")
    public void copyBaseCase(@RequestParam String baseCaseId, HttpServletRequest request, HttpServletResponse response) {
        BaseCase formerBaseCase = baseCaseService.findBaseCaseById(Integer.parseInt(baseCaseId));
        formerBaseCase.setName(formerBaseCase.getName() + "_copy");
        User user = (User) request.getSession().getAttribute("user");
        formerBaseCase.setCaseId(0);
        formerBaseCase.setUserId(user.getId());
        int maxSort = baseCaseService.findMaxSort(Integer.parseInt(baseCaseId));
        if (Integer.parseInt(baseCaseId) > 0) {
            formerBaseCase.setSort(maxSort + 1);
        } else {
            formerBaseCase.setSort(0);
        }
        int i = baseCaseService.insertBaseCase(formerBaseCase, Integer.parseInt(baseCaseId));
        BaseUtil.writeInteger(i, response);
    }
}
