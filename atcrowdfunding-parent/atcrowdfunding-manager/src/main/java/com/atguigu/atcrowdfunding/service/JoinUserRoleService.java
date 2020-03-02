package com.atguigu.atcrowdfunding.service;

public interface JoinUserRoleService {

    Object assignRoles(Integer userId,Integer[] unAssignRoleIds,Integer[] assignRoleIds);

}
