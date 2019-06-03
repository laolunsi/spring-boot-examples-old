package com.eknown.model.dao;

import com.eknown.model.beans.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDAO {

    List<Menu> findAll();

    /**
     * 查询对应用户具有操作权限的所有menu项
     * @param userName
     * @return
     */
    List<Menu> findByUserName(String userName);
}
