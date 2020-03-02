package com.atguigu.atcrowdfunding.service;

public interface PermissionService {


    Object queryPermissionListByPid(String pid);


    Object queryAssignPermissionsChecked(Integer roleId,String pid);
}
