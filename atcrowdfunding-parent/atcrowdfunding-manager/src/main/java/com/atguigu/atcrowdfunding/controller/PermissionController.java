package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.service.PermissionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/permission")
public class PermissionController {


    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object queryPermissionList() {
        return permissionService.queryPermissionListByPid("");
    }

    @RequestMapping(value = "/checked/list/{roleId}", method = RequestMethod.POST)
    public Object queryAssignPermissionsChecked(@PathVariable("roleId") Integer roleId) {
        return permissionService.queryAssignPermissionsChecked(roleId, "");
    }


}
