package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryByUsername(String username);

    List<User> queryList(User user);

    User queryById(Integer id);

    User queryUserAssignPermissionsById(Integer id);

}