package com.eknown.logic.service.impl;

import com.eknown.logic.service.MenuService;
import com.eknown.model.beans.entity.Menu;
import com.eknown.model.dao.MenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/6/3 17:06
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDAO menuDAO;

    @Override
    public List<Menu> list() {
        return menuDAO.findAll();
    }

    @Override
    public String findUserPermissions(String userName) {
        List<Menu> list = menuDAO.findByUserName(userName);
        return list.stream().map(Menu::getPermission).collect(Collectors.joining(","));
    }
}
