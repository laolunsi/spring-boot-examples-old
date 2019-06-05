package com.eknown.logic.service;

import com.eknown.model.beans.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> list();

    String findUserPermissions(String userName);
}
