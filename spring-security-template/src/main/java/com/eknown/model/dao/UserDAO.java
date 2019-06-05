package com.eknown.model.dao;

import com.eknown.model.beans.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {

    User findByName(String name);

    User findById(int id);

    List<User> findAll();

    boolean insert(User user);

    boolean update(User user);
}
