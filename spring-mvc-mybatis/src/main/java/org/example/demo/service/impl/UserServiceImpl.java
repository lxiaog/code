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
    public Object add(User user) {
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setPassword("admin=admin");
        int row = userMapper.insertSelective(user);
        if (row==1){
            log.info("获取自增主键id="+user.getId());
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
}
