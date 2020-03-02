package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.entity.JoinUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface JoinUserRoleMapper {

    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int insert(JoinUserRole record);

    int insertSelective(JoinUserRole record);

    JoinUserRole selectByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int updateByPrimaryKeySelective(JoinUserRole record);

    int updateByPrimaryKey(JoinUserRole record);

    List<JoinUserRole> queryByUserId(Integer userId);


}