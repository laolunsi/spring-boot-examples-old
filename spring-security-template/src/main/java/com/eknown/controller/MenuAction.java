package com.eknown.controller;

import com.eknown.model.beans.common.JsonResult;
import com.eknown.model.beans.entity.Menu;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/6/4 16:06
 */
@RestController
@RequestMapping(value = "menu")
public class MenuAction {

    @GetMapping(value = "list")
    @PreAuthorize("hasAuthority('menu:list')")
    public JsonResult list() {
        return new JsonResult(false, "暂未实现该接口");
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('menu:save')")
    public JsonResult save(Menu menu) {
        return new JsonResult(false, "暂未实现该接口");
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('menu:delete')")
    public JsonResult delete(@PathVariable("id") Integer id) {
        return new JsonResult(false, "暂未实现该接口");
    }
}
