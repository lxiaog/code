package com.atguigu.atcrowdfunding.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.atcrowdfunding.bean.Preconditions;
import com.atguigu.atcrowdfunding.bean.rest.RestResult;
import com.atguigu.atcrowdfunding.dao.JoinUserRoleMapper;
import com.atguigu.atcrowdfunding.dao.RoleMapper;
import com.atguigu.atcrowdfunding.entity.Role;
import com.atguigu.atcrowdfunding.exception.code.ApiParamCode;
import com.atguigu.atcrowdfunding.exception.code.BusinessCode;
import com.atguigu.atcrowdfunding.exception.code.DaoCode;
import com.atguigu.atcrowdfunding.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private final JoinUserRoleMapper joinUserRoleMapper;

    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper, SqlSessionTemplate sqlSessionTemplate, JoinUserRoleMapper joinUserRoleMapper) {
        this.roleMapper = roleMapper;
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.joinUserRoleMapper = joinUserRoleMapper;
    }

    @Override
    public Object queryRolePage(Role role) {
        PageHelper.startPage(role.getPageNum(), role.getPageSize(), true);
        List<Role> roleList = null;
        roleList = roleMapper.queryList(role);


        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        log.info("返回结果" + JSON.toJSONString(pageInfo));
        return RestResult.success().setData(pageInfo);
    }

    @Override
    public Object roleAdd(Role role) {
        Preconditions.isNotNull(role, ApiParamCode.PARAM_NULL
                .setMethod("roleAdd")
                .setField("role"));
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        int count = roleMapper.insertSelective(role);
        Preconditions.isExpression(count == 1,
                DaoCode.INSERT_FAIL.setMsg("添加角色失败"));
        return RestResult.success().setMsg("添加角色成功");
    }

    @Override
    public Object roleEdit(Role role) {
        Preconditions.isNotNull(role, ApiParamCode.PARAM_NULL
                .setMethod("roleEdit")
                .setField("role"));
        Preconditions.isNotNull(role.getId(), ApiParamCode.PARAM_NULL
                .setMethod("roleEdit")
                .setField("id"));
        Preconditions.isNotNullString(role.getRoleName(), ApiParamCode.PARAM_EMPTY
                .setMethod("roleEdit")
                .setField("roleName"));
        role.setUpdateDate(new Date());
        int count = roleMapper.updateByPrimaryKeySelective(role);
        Preconditions.isExpression(count == 1, DaoCode.UPDATE_FAIL.setMsg("编辑角色失败"));
        return RestResult.success().setMsg("角色修改成功");
    }

    @Override
    public Role queryByRoleId(Integer roleId) {
        Preconditions.isNotNull(roleId,
                ApiParamCode.PARAM_NULL.setField("roleId").setMethod("queryByRoleId"));
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public Object roleDelete(Integer roleId) {
        Preconditions.isNotNull(roleId, ApiParamCode.PARAM_NULL.setMethod("roleDelete").setField("roleId"));
        int row = roleMapper.deleteByPrimaryKey(roleId);
        Preconditions.isExpression(row == 1, DaoCode.DELETE_FAIL.setMsg("删除角色失败"));
        return RestResult.success().setMsg("删除角色成功");
    }

    @Override
    public Object roleBatchDelete(Integer[] roleIds) {
        Preconditions.isNotNull(roleIds, ApiParamCode.PARAM_NULL
                .setMethod("roleBatchDelete").setField("roleIds"));
        Preconditions.isExpression(roleIds.length > 0, ApiParamCode.PARAM_NULL
                .setMethod("roleBatchDelete").setField("roleIds"));
        RoleMapper mapper = sqlSessionTemplate.getMapper(RoleMapper.class);
        int count = 0;
        for (Integer id : roleIds) {
            mapper.deleteByPrimaryKey(id);
            count++;
        }
        Preconditions.isExpression(count == roleIds.length, BusinessCode.ROLE_BATCH_DELETE_FAIL);
        return RestResult.success().setMsg("角色批量删除成功");
    }

}
