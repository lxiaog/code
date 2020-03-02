package com.atguigu.atcrowdfunding.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.atcrowdfunding.bean.Preconditions;
import com.atguigu.atcrowdfunding.bean.rest.RestResult;
import com.atguigu.atcrowdfunding.dao.JoinPermissionRoleMapper;
import com.atguigu.atcrowdfunding.entity.JoinPermissionRole;
import com.atguigu.atcrowdfunding.exception.code.ApiParamCode;
import com.atguigu.atcrowdfunding.service.JoinPermissionRoleService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JoinPermissionRoleServiceImpl implements JoinPermissionRoleService {

    private static final String CLASS_NAME = JoinPermissionRoleServiceImpl.class.getName();


    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public JoinPermissionRoleServiceImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public Object assignPermissions(Integer roleId, String[] permissionIds) {
        Preconditions.isNotNull(roleId, ApiParamCode.PARAM_NULL.setClazz(CLASS_NAME).setField("roleId").setMethod("assignPermissions"));
        Preconditions.isNotNull(permissionIds, ApiParamCode.PARAM_NULL.setClazz(CLASS_NAME).setField("permissionIds").setMethod("assignPermissions"));
        JoinPermissionRoleMapper mapper = sqlSessionTemplate.getMapper(JoinPermissionRoleMapper.class);
        //先删除该角色分配的权限
        mapper.deleteByRoleId(roleId);
        //重新分配权限
        for (String permissionId:permissionIds) {
            JoinPermissionRole joinPermissionRole = new JoinPermissionRole();
            joinPermissionRole.setRoleId(roleId);
            joinPermissionRole.setPermissionId(permissionId);
            joinPermissionRole.setCreateDate(new Date());
            joinPermissionRole.setUpdateDate(new Date());
            mapper.insertSelective(joinPermissionRole);
        }
        return RestResult.success().setMsg("分配权限成功");
    }
}
