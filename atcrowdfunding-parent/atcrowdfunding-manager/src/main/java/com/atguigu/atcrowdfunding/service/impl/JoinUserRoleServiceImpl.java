package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.Preconditions;
import com.atguigu.atcrowdfunding.bean.rest.RestResult;
import com.atguigu.atcrowdfunding.dao.JoinUserRoleMapper;
import com.atguigu.atcrowdfunding.entity.JoinUserRole;
import com.atguigu.atcrowdfunding.exception.code.ApiParamCode;
import com.atguigu.atcrowdfunding.service.JoinUserRoleService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JoinUserRoleServiceImpl implements JoinUserRoleService {

    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public JoinUserRoleServiceImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public Object assignRoles(Integer userId, Integer[] unAssignRoleIds, Integer[] assignRoleIds) {
        Preconditions.isNotNull(userId, ApiParamCode.PARAM_NULL.setMethod("assignRoles").setField("userId"));
        JoinUserRoleMapper mapper = sqlSessionTemplate.getMapper(JoinUserRoleMapper.class);
        //已分配角色的id去删除
        if (assignRoleIds != null && assignRoleIds.length > 0) {
            for (Integer assignRoleId : assignRoleIds) {
                mapper.deleteByPrimaryKey(userId, assignRoleId);
            }
        }
        //未分配角色的id，去分配角色
        if (unAssignRoleIds != null && unAssignRoleIds.length > 0) {
            for (Integer unAssignRoleId : unAssignRoleIds) {
                JoinUserRole userRole = new JoinUserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(unAssignRoleId);
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                mapper.insertSelective(userRole);
            }
        }
        return RestResult.success().setMsg("分配角色成功");
    }
}
