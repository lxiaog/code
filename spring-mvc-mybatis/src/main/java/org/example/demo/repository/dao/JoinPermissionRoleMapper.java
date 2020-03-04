package org.example.demo.repository.dao;

import org.apache.ibatis.annotations.Param;
import org.example.demo.repository.entity.JoinPermissionRole;

public interface JoinPermissionRoleMapper {
    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("permissionId") String permissionId);

    int insert(JoinPermissionRole record);

    int insertSelective(JoinPermissionRole record);

    JoinPermissionRole selectByPrimaryKey(@Param("roleId") Integer roleId, @Param("permissionId") String permissionId);

    int updateByPrimaryKeySelective(JoinPermissionRole record);

    int updateByPrimaryKey(JoinPermissionRole record);
}