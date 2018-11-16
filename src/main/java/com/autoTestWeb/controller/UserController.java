package com.autoTestWeb.controller;

import com.autoTestWeb.model.Menu;
import com.autoTestWeb.model.Role;
import com.autoTestWeb.model.User;
import com.autoTestWeb.model.UserGroup;
import com.autoTestWeb.service.MenuService;
import com.autoTestWeb.service.RoleService;
import com.autoTestWeb.service.UserService;
import com.autoTestWeb.util.BaseUtil;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired(required = false)
    private BaseUtil baseUtil;

    @Autowired(required = false)
    private User user;
    @Autowired
    private UserService userService;
    @Autowired(required = false)
    private List<Menu> menuList;
    // private UserGroupService userGroupService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired(required = false)
    private List<UserGroup> userGroupList;
    @Autowired(required = false)
    private List<Role> roleList;
    @Autowired(required = false)
    private UserGroup userGroup;
    private int userGroupId;
    private int userId;

    /**
     * 欢迎页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/welcome.go")
    public String welcome(HttpServletRequest request) {
        System.out.println("首次访问跳转到登陆页面");
        return "welcome";
    }

    /**
     * 首页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/home.go")
    public String index(HttpServletRequest request) {
        System.out.println("首次访问跳转到登陆页面");
        return "login";
    }

    /**
     * 登录
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "login.go")
    public String login(HttpServletRequest request, ModelMap modelMap) {
        user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return "index";
        } else {
            String userName = request.getParameter("userName");
            String password = request.getParameter("passWord");
            user = userService.findUserByName(userName);
            if (user == null) {
                return "login";
            } else {
                if (password.equals(user.getPassword())) {
                    //用户名密码正确
                    request.getSession().setAttribute("user", user);
                    //获取菜单信息
                    request.getSession().setAttribute("userId", user.getId());
                    request.getSession().setAttribute("userName", user.getName());
                    Role role = roleService.findRoleById(user.getRoleId());
                    modelMap.put("isAdmin", role.getIsAdmin());
                    Menu conditionMenu = new Menu();
                    conditionMenu.setParentMenuId(-1);
                    conditionMenu.setRoleId(user.getRoleId());
                    menuList = menuService.findParentRoleMenuList(conditionMenu);
                    for (Menu m : menuList) {
                        conditionMenu.setParentMenuId(m.getId());
                        List<Menu> childMenuList = menuService.findParentRoleMenuList(conditionMenu);
                        m.setMenuList(childMenuList);
                    }
                    modelMap.put("menuList", menuList);
                    return "index";
                }
            }
            return "login";
        }
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout.go")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().removeAttribute("user");
            PrintWriter writer;
            try {
                writer = response.getWriter();
                writer.print(1);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                LOGGER.info(e.toString());
                throw new RuntimeException(e.toString());
            }
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.getMessage(), e.getCause());
        }

    }

    /**
     * 获取用户列表
     */
    @RequestMapping(value = "user/findUserList.go")
    public void findUserList(HttpServletResponse response) {
        List<User> userList = userService.findUserList();
        try {
            JSONArray json = new JSONArray();
            for (User user : userList) {
                JSONObject jo = new JSONObject();
                jo.put("id", user.getId());
                jo.put("name", user.getName());
                jo.put("password", user.getPassword());
                jo.put("userGroupName", user.getUserGroupName());
                jo.put("roleName", user.getRoleName());
                json.put(jo);
            }
            baseUtil.writeJson(userList.size(), json, response);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
