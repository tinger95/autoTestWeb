package com.autoTestWeb.controller;

import com.autoTestWeb.model.User;
import com.autoTestWeb.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

@Controller
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private User user;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home.go")
    public String index() {
        System.out.println("首次访问跳转到登陆页面");
        return "login";
    }
    @RequestMapping(value = "login.go")
    public  String login(HttpServletRequest request, ModelMap modelMap){
        user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            return "index";
        }else{
            String userName = request.getParameter("userName");
            String password = request.getParameter("passWord");
            user = userService.findUserByName(userName);
            if (user == null) {
                return "login";
            } else {
                if (password.equals(user.getPassword())) {
                    modelMap.put("user", user);
                    return "index";
                }
            }
        }
        return "login";
    }
}
