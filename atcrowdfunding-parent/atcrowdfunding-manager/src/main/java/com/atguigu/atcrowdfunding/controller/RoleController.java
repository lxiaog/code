package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.dao.JoinPermissionRoleMapper;
import com.atguigu.atcrowdfunding.entity.Role;
import com.atguigu.atcrowdfunding.service.JoinPermissionRoleService;
import com.atguigu.atcrowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    private final JoinPermissionRoleService joinPermissionRoleService;

    @Autowired
    public RoleController(RoleService roleService, JoinPermissionRoleService joinPermissionRoleService) {
        this.roleService = roleService;
        this.joinPermissionRoleService = joinPermissionRoleService;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Object rolePage(Role Role) {
        return roleService.queryRolePage(Role);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object roleAdd(Role role) {
        return roleService.roleAdd(role);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Object roleEdit(Role role) {
        return roleService.roleEdit(role);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object RoleDelete(Integer id) {
        return roleService.roleDelete(id);
    }

    @RequestMapping(value = "/batch/delete", method = RequestMethod.POST)
    public Object RoleBatchDelete(Integer[] roleIds) {
        return roleService.roleBatchDelete(roleIds);
    }

    @RequestMapping(value = "/assign/permissions", method = RequestMethod.POST)
    public Object roleAssignPermissions(Integer roleId, String[] permissionIds) {
        return joinPermissionRoleService.assignPermissions(roleId, permissionIds);
    }
}
