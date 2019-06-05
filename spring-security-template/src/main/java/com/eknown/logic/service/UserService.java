package com.eknown.logic.service;

import com.eknown.model.beans.entity.User;

import java.util.List;

public interface UserService {

    List<User> list();

    User findByName(String name);

    boolean save(User user);
}
