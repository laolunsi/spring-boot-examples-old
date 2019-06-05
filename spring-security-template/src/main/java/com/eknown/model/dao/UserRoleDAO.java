package com.eknown.model.dao;

import com.eknown.model.beans.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleDAO {

    boolean insert(UserRole userRole);

    boolean batchInsert(@Param("userRoleList") List<UserRole> userRoleList);

    boolean delete(int id);

    List<UserRole> findByUserId(int userId);
}
