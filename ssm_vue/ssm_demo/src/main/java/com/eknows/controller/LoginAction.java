package com.eknows.controller;

import com.eknows.model.bean.common.JsonResult;
import com.eknows.model.bean.entity.User;
import com.eknows.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eknows
 * @version 1.0
 * @since 2019/2/26 11:24
 */
@RestController
@RequestMapping
public class LoginAction {

    @Autowired
    private UserDAO userDAO;

    @GetMapping(value = "login")
    public JsonResult login(String name, String password) {
        if (StringUtils.isEmpty(name)) {
            return new JsonResult(false, "用户名不能为空");
        } else if (StringUtils.isEmpty(password)) {
            return new JsonResult(false, "密码不能为空");
        }

        User user = userDAO.find(name, password);
        if (user == null) {
            return new JsonResult(false, "用户名或密码错误");
        }

        JsonResult jsonResult = new JsonResult(true);
        jsonResult.put("user", user);
        return jsonResult;
    }
}
