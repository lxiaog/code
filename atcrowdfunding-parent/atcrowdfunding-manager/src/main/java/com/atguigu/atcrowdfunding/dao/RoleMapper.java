package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> queryList(Role role);

    List<Role> queryUnAssionRolesByUserId(Integer userId);
}