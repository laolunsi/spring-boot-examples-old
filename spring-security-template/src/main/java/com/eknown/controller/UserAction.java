package com.eknown.controller;

import com.eknown.logic.service.UserService;
import com.eknown.model.beans.common.JsonResult;
import com.eknown.model.beans.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/6/3 18:16
 */
@RestController
@RequestMapping(value = "user")
public class UserAction {

    @Autowired
    private UserService userService;

    @GetMapping(value = "list")
    @PreAuthorize("hasAuthority('user:list')")
    public JsonResult list() {
        JsonResult jsonResult = new JsonResult(true);
        List<User> userList = userService.list();
        jsonResult.put("userList", userList);
        return jsonResult;
    }

    @PostMapping(value = "save")
    @PreAuthorize("hasAnyAuthority('user:add', 'user:update')")
    public JsonResult save(User user) {
        return new JsonResult(false, "暂未实现该接口");
    }

}
