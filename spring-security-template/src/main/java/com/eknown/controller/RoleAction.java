package com.eknown.controller;

import com.eknown.model.beans.common.JsonResult;
import com.eknown.model.beans.entity.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/6/4 15:59
 */
@RestController
@RequestMapping(value = "role")
public class RoleAction {

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('role:list')")
    public JsonResult list() {
        return new JsonResult(false, "暂未实现该接口");
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('role:save')")
    public JsonResult save(Role role) {
        return new JsonResult(false, "暂未实现该接口");
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('role:delete')")
    public JsonResult delete(@PathVariable("id") Integer id) {
        return new JsonResult(false, "暂未实现该接口");
    }

}
