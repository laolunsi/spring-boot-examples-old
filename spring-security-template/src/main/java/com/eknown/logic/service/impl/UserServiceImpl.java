package com.eknown.logic.service.impl;

import com.eknown.logic.service.UserService;
import com.eknown.model.beans.entity.User;
import com.eknown.model.dao.UserDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/6/3 18:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public List<User> list() {
        return userDAO.findAll();
    }

    @Override
    public User findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public boolean save(User user) {
        boolean res = false;
        if (user != null) {
            if (user.getId() == null) {
                res = userDAO.insert(user);
                if (res && user.getRoleIds() != null && user.getRoleIds().length > 0) {
                    // 设置用户角色

                }
            } else {
                res = userDAO.update(user);
            }
        }
        return false;
    }
}
