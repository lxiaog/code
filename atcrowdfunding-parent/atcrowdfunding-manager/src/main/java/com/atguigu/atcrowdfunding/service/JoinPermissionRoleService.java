package com.atguigu.atcrowdfunding.service;

public interface JoinPermissionRoleService {

    Object assignPermissions(Integer roleId, String[] permissionIds);

}
