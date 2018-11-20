package com.autoTestWeb.controller;

import com.autoTestWeb.model.Category;
import com.autoTestWeb.service.CategoryService;
import com.autoTestWeb.util.BaseUtil;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ManageController {
    private static final Logger LOGGER = Logger.getLogger(ManageController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "manage/findCategoryListByUserId.go")
    public void findCategoryListByUserId(@RequestParam("userId") String userId,HttpServletResponse response) {
        try {
            Category category = new Category();
            category.setUserId(Integer.parseInt(userId));
            List<Category> categoryList = categoryService.findCategoryList(category);
            JSONArray json = new JSONArray();
            for (Category c : categoryList) {
                JSONObject jo = new JSONObject();
                jo.put("id", c.getId());
                jo.put("name", c.getName());
                json.put(jo);
            }
            BaseUtil.writeJson(categoryList.size(), json, response);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }
}
