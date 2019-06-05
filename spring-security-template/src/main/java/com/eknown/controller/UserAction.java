package com.eknown.controller;

import com.eknown.logic.service.UserService;
import com.eknown.model.beans.common.JsonResult;
import com.eknown.model.beans.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        JsonResult jsonResult = verifySaveForm(user);
        if (!jsonResult.getSuccess()) {
            return jsonResult;
        }

        boolean res = userService.save(user);
        return new JsonResult(false, "暂未实现该接口");
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public JsonResult delete(@PathVariable("id") Integer id) {
        return new JsonResult(false, "暂未实现该接口");
    }

    // ---- private methods -----

    /**
     * 验证save参数
     * add or update
     * @param user
     * @return
     */
    private JsonResult verifySaveForm(User user) {
        if (user == null) {
            return new JsonResult(false, "参数错误");
        }

        if (StringUtils.isBlank(user.getName())) {
            return new JsonResult(false, "用户名不能为空");
        } else if (StringUtils.isBlank(user.getPassword())) {
            return new JsonResult(false, "密码不能为空");
        }

        User sameUser = userService.findByName(user.getName());
        if (sameUser != null && sameUser.getId() != null) {
            if (user.getId() == null) {
                return new JsonResult(false, "用户名已存在");
            } else {
                if (!user.getId().equals(sameUser.getId())) {
                    return new JsonResult(false, "用户名已存在");
                }
            }
        }

        return new JsonResult(true);
    }
}
