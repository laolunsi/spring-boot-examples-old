package com.eknown.logic.service.impl;

import com.eknown.logic.service.RoleService;
import com.eknown.model.beans.entity.Role;
import com.eknown.model.dao.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/6/5 8:56
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDAO roleDAO;

    @Override
    public List<Role> list() {
        return roleDAO.findAll();
    }

    @Override
    public boolean save(Role role) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
