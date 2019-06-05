package com.eknown.logic.service;

import com.eknown.model.beans.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> list();

    boolean save(Role role);

    boolean deleteById(Integer id);

}
