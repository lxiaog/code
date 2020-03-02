package com.atguigu.atcrowdfunding.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.atcrowdfunding.bean.Preconditions;
import com.atguigu.atcrowdfunding.bean.rest.RestResult;
import com.atguigu.atcrowdfunding.dao.JoinUserRoleMapper;
import com.atguigu.atcrowdfunding.dao.PermissionMapper;
import com.atguigu.atcrowdfunding.dao.RoleMapper;
import com.atguigu.atcrowdfunding.dao.UserMapper;
import com.atguigu.atcrowdfunding.entity.Permission;
import com.atguigu.atcrowdfunding.entity.Role;
import com.atguigu.atcrowdfunding.entity.User;
import com.atguigu.atcrowdfunding.exception.BusinessException;
import com.atguigu.atcrowdfunding.exception.code.ApiParamCode;
import com.atguigu.atcrowdfunding.exception.code.BusinessCode;
import com.atguigu.atcrowdfunding.exception.code.DaoCode;
import com.atguigu.atcrowdfunding.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String CLASS_NAME = UserServiceImpl.class.getName();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public RestResult queryByUsername(User user, HttpSession session) {
        Preconditions.isNotNull(user,
                ApiParamCode.PARAM_NULL.setClazz(CLASS_NAME)
                        .setMethod("queryByUsername").setField("user"));
        Preconditions.isNotNullString(user.getUsername(),
                ApiParamCode.PARAM_EMPTY.setClazz(CLASS_NAME)
                        .setMethod("queryByUsername").setField("username"));
        Preconditions.isNotNullString(user.getPassword(),
                ApiParamCode.PARAM_EMPTY.setClazz(CLASS_NAME)
                        .setMethod("queryByUsername").setField("password"));
        User userEntity = userMapper.queryByUsername(user.getUsername());
        Preconditions.isNotNull(userEntity,
                BusinessCode.USER_USERNAME_NON.setClazz(CLASS_NAME)
                        .setMethod("queryByUsername").setField("password"));
        if (Objects.equals(user.getPassword(), userEntity.getPassword())) {
            //权限表中查询
//            Map<String, Object> map = new HashMap<>();
//            map.put("userId", userEntity.getId());
//            map.put("pid", "");
//            List<Permission> permissionList = permissionMapper.queryListByUserId(map);
//            log.info(JSON.toJSONString(permissionList));
            //通过关联，直接将用户对应的权限查出
            User userAssignPermissions = userMapper.queryUserAssignPermissionsById(userEntity.getId());
            log.warn(JSON.toJSONString(userAssignPermissions));
            session.setAttribute("loginUser", userAssignPermissions);
//            session.setAttribute("userPermissions", userAssignPermissions.getPermissions());
            return RestResult.success().setMsg("登陆成功");
        }
        throw new BusinessException(BusinessCode.USER_PASSWORD_WRONG
                .setClazz(CLASS_NAME).setMethod("queryByUsername"));
    }

    @Override
    public Object queryUserPage(User user) {
        Preconditions.isNotNull(user,
                ApiParamCode.PARAM_NULL.setClazz(CLASS_NAME)
                        .setMethod("queryByUsername").setField("user"));
        Preconditions.isNotNull(user.getPageNum(),
                ApiParamCode.PARAM_NULL.setClazz(CLASS_NAME)
                        .setMethod("queryByUsername").setField("pageNum"));
        Preconditions.isNotNull(user.getPageSize(),
                ApiParamCode.PARAM_NULL.setClazz(CLASS_NAME)
                        .setMethod("queryByUsername").setField("pageSize"));
        PageHelper.startPage(user.getPageNum(), user.getPageSize(), true);
        List<User> userList = userMapper.queryList(user);
        Preconditions.isNotNull(userList, DaoCode.SELECT_FAIL.setClazz("UserMapper").setMethod("queryList"));
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return RestResult.success().setData(pageInfo);
    }

    @Override
    public Object userAdd(User user) {
        user.setPassword("adminadmin");
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        int row = userMapper.insertSelective(user);
        if (row == 1) {
            return RestResult.success().setMsg("添加用户成功");
        }
        throw new BusinessException(DaoCode.INSERT_FAIL
                .setMsg("添加用户失败").setClazz(CLASS_NAME).setMethod("userAdd"));
    }

    @Override
    public Object userEdit(User user) {
        user.setUpdateDate(new Date());
        int row = userMapper.updateByPrimaryKeySelective(user);
        if (row == 1) {
            return RestResult.success().setMsg("更新成功");
        }
        throw new BusinessException(DaoCode.INSERT_FAIL
                .setMsg("更新用户失败").setClazz(CLASS_NAME).setMethod("userEdit"));
    }

    @Override
    public User queryByUserId(Integer userId) {
        Preconditions.isNotNull(userId,
                ApiParamCode.PARAM_NULL.setClazz(CLASS_NAME)
                        .setField("userId").setMethod("queryByUserId"));
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Object userDelete(Integer userId) {
        int row = userMapper.deleteByPrimaryKey(userId);
        if (row == 1) {
            return RestResult.success().setMsg("用户删除成功");
        }
        throw new BusinessException(DaoCode.DELETE_FAIL.setMsg("删除用户失败")
                .setClazz(CLASS_NAME).setMethod("userDelete"));
    }

    @Override
    public Object userBatchDelete(Integer[] userIds) {
        Preconditions.isNotNull(userIds,
                ApiParamCode.PARAM_NULL.setClazz(CLASS_NAME)
                        .setMethod("userBatchDelete").setField("userIds"));
        Preconditions.isExpression(userIds.length > 0,
                ApiParamCode.PARAM_NULL.setClazz(CLASS_NAME)
                        .setMethod("userBatchDelete").setField("userIds"));
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        int row = 0;
        for (Integer id : userIds) {
            mapper.deleteByPrimaryKey(id);
            row++;
        }
        if (row == userIds.length) {
            return RestResult.success().setMsg("批量删除用户成功");
        }
        throw new BusinessException(BusinessCode.USER_BATCH_DELETE_FAIL);

    }

    @Override
    public Object queryRoleListByUserId(Integer userId) {
        Preconditions.isNotNull(userId, ApiParamCode.PARAM_NULL
                .setMethod("queryRoleListByUserId")
                .setField("userId")
                .setClazz(CLASS_NAME));
        //已分配的角色信息
        User user = userMapper.queryById(userId);
        List<Role> unAssignRoles = roleMapper.queryUnAssionRolesByUserId(userId);
        Map<String, List> map = new HashMap<>();
        if (user.getRoles() != null) {
            map.put("assignRoles", user.getRoles());
        } else {
            map.put("assignRoles", new ArrayList());
        }
        map.put("unAssignRoles", unAssignRoles);
        return RestResult.success().setData(map);
    }

}
