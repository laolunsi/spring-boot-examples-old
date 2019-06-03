package com.eknown.model.dao;

import com.eknown.model.beans.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDAO {

    List<Role> findAll();
}
