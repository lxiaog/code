package org.example.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.repository.dao.JoinUserRoleMapper;
import org.example.demo.repository.dao.UserMapper;
import org.example.demo.repository.entity.JoinUserRole;
import org.example.demo.repository.entity.User;
import org.example.demo.service.UserService;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JoinUserRoleMapper joinUserRoleMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public Object add(User user) {
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setPassword("admin=admin");
        int row = userMapper.insertSelective(user);
        if (row == 1) {
            log.info("获取自增主键id=" + user.getId());
            return "新增成功";
        }
        return "新增失败";
    }

    @Override
    public Object delete(User user) {
        return 0;
    }

    @Override
    public Object update(User user) {
        return 0;
    }

    @Override
    public Map queryMapById(Integer id) {
        return userMapper.queryMapById(id);
    }

    @Override
    public Map<Integer, User> queryMaps() {
        return userMapper.queryMaps();
    }

    @Override
    public List<JoinUserRole> queryAssociationRole() {
        return joinUserRoleMapper.queryAssociationRole();
    }

    @Override
    public List<JoinUserRole> querySwitchAssociationRole() {
        return joinUserRoleMapper.querySwitchAssociationRole();
    }

    @Override
    public User queryById(Integer id) {

//        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        //测试一级缓存
        User user = userMapper.selectByPrimaryKey(1);

        log.info("user-1=" + user.getId());

        User user2 = userMapper.selectByPrimaryKey(1);

        log.info("user-2=" + user2.getId());
        return userMapper.selectByPrimaryKey(id);
    }
}
