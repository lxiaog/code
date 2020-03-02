package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.entity.JoinPermissionRole;
import org.apache.ibatis.annotations.Param;

public interface JoinPermissionRoleMapper {
    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("permissionId") String permissionId);

    int insert(JoinPermissionRole record);

    int insertSelective(JoinPermissionRole record);

    JoinPermissionRole selectByPrimaryKey(@Param("roleId") Integer roleId, @Param("permissionId") String permissionId);

    int updateByPrimaryKeySelective(JoinPermissionRole record);

    int updateByPrimaryKey(JoinPermissionRole record);

    int deleteByRoleId(Integer roleId);
}