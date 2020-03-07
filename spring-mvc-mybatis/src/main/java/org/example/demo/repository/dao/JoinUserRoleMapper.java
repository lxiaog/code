package org.example.demo.repository.dao;

import org.apache.ibatis.annotations.Param;
import org.example.demo.repository.entity.JoinUserRole;

import java.util.List;

public interface JoinUserRoleMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int insert(JoinUserRole record);

    int insertSelective(JoinUserRole record);

    JoinUserRole selectByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    List<JoinUserRole> queryAssociationRole();

    List<JoinUserRole> querySwitchAssociationRole();

    int updateByPrimaryKeySelective(JoinUserRole record);

    int updateByPrimaryKey(JoinUserRole record);
}