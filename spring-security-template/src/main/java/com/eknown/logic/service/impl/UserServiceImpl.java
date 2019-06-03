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
}
