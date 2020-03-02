package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.entity.Role;

public interface RoleService {

    Object queryRolePage(Role role);

    Object roleAdd(Role role);

    Object roleEdit(Role role);

    Role queryByRoleId(Integer roleId);

    Object roleDelete(Integer roleId);

    Object roleBatchDelete(Integer[] roleIds);



}
