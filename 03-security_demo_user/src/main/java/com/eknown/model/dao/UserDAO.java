package com.eknown.model.dao;

import com.eknown.model.beans.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

    User findByName(String name);

    User findById(int id);
}
