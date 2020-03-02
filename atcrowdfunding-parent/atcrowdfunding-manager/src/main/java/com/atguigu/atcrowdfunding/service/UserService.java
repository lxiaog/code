package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.rest.RestResult;
import com.atguigu.atcrowdfunding.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService {

    RestResult queryByUsername(User user, HttpSession session);

    Object queryUserPage(User user);

    Object userAdd(User user);

    Object userEdit(User user);

    User queryByUserId(Integer userId);

    Object userDelete(Integer userId);

    Object userBatchDelete(Integer[] userIds);

    Object queryRoleListByUserId(Integer userId);

}
