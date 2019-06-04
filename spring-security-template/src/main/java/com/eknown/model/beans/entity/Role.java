package com.eknown.model.beans.entity;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/6/3 11:21
 */
public class Role {

    private Integer id;
    private String name;
    private String realName;

    //
    private Integer[] menuIds; // 角色具有的菜单权限数组

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Integer[] menuIds) {
        this.menuIds = menuIds;
    }
}
