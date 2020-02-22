package org.example.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.repository.dao.UserMapper;
import org.example.demo.repository.entity.User;
import org.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Object add() {
        User user = new User();
        user.setUserName("admin");
        user.setRealName("管理员");
        user.setSex("男");
        user.setTel("13118398056");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        return "add user success";
    }
}
