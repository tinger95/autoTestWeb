package com.autoTestWeb.controller;

import com.autoTestWeb.model.*;
import com.autoTestWeb.service.*;
import com.autoTestWeb.util.BaseUtil;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class CaseController {
    private static final Logger LOGGER = Logger.getLogger(CaseController.class);

    @Autowired(required = false)
    private List<Category> categoryList;
    @Autowired(required = false)
    private List<Project> projectList;
    @Autowired(required = false)
    private List<Environment> environmentList;
    @Autowired(required = false)
    private List<Client> clientList;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private EnvironmentService environmentService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CaseService caseService;

    @RequestMapping(value = "case/initCase.go")
    public String initCase(HttpServletRequest request, ModelMap modelMap) {
        // groupList = groupService.findGroupList();
        User user = (User) request.getSession().getAttribute("user");
        categoryList = categoryService.findCategoryListByUserId(user.getId());
        projectList = projectService.findProjectList();
        environmentList = environmentService.findEnvironmentList();
        clientList = clientService.findClientList();
        modelMap.put("categoryList", categoryList);
        modelMap.put("projectList", projectList);
        modelMap.put("environmentList", environmentList);
        modelMap.put("clientList", clientList);
        return "case/caseList";
    }

    @RequestMapping(value = "case/findCaseList.go", method = RequestMethod.POST)
    @ResponseBody
    public void findCaseList(HttpServletRequest request, HttpServletResponse response, @RequestBody Case executeCase) {
        try {
            executeCase.initPage(request);
            List<Case> caseList = caseService.findCaseList(executeCase);
            int size = caseService.findCaseCount(executeCase);
            JSONArray json = new JSONArray();
            for (Case c : caseList) {
                JSONObject jo = new JSONObject();
                jo.put("id", c.getId());
                jo.put("name", c.getName());
                jo.put("comment", c.getComment());
                jo.put("projectId", c.getProjectId());
                jo.put("projectName", c.getProjectName());
                jo.put("category", c.getCategory());
                jo.put("categoryName", c.getCategory() == 0 ? "未区分" : c.getCategoryName());
                jo.put("userId", c.getUserId());
                jo.put("userName", c.getUserName());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jo.put("insertTime", sdf.format(c.getInsertTime()));
                jo.put("updateTime", sdf.format(c.getUpdateTime()));
                json.put(jo);
            }
            BaseUtil.writeJson(size, json, response);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
