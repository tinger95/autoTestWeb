package com.autoTestWeb.controller;

import com.autoTestWeb.model.*;
import com.autoTestWeb.service.CategoryService;
import com.autoTestWeb.service.ClientService;
import com.autoTestWeb.service.EnvironmentService;
import com.autoTestWeb.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CaseController {

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

    @RequestMapping(value = "case/initCase.go")
    public String initCase(HttpServletRequest request) {
        // groupList = groupService.findGroupList();
        User user = (User) request.getSession().getAttribute("user");
        categoryList = categoryService.findCategoryListByUserId(user.getId());
        projectList = projectService.findProjectList();
        environmentList = environmentService.findEnvironmentList();
        clientList = clientService.findClientList();
        return "case/caseList";
    }
}
