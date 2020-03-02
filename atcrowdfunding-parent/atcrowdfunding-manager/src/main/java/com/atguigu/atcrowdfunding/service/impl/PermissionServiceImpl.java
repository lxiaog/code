package com.atguigu.atcrowdfunding.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.atcrowdfunding.dao.PermissionMapper;
import com.atguigu.atcrowdfunding.entity.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {


    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }


    @Override
    public Object queryPermissionListByPid(String pid) {
        //mybatis一对多递归查询
        List<Permission> permissionList = permissionMapper.queryListByPid(pid);
        log.info(JSON.toJSONString(permissionList));
        return permissionList == null ? new ArrayList<>() : permissionList;
    }

    @Override
    public Object queryAssignPermissionsChecked(Integer roleId,String pid) {
        Map<String,Object> map = new HashMap<>();
        map.put("roleId",roleId);
        map.put("pid",pid);
        List<Permission> permissionList = permissionMapper.queryListChecked(map);
        log.info(JSON.toJSONString(permissionList));
        return permissionList == null ? new ArrayList<>() : permissionList;
    }
}
