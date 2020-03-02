package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.entity.User;
import com.atguigu.atcrowdfunding.service.JoinUserRoleService;
import com.atguigu.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final JoinUserRoleService joinUserRoleService;

    @Autowired
    public UserController(UserService userService, JoinUserRoleService joinUserRoleService) {
        this.userService = userService;
        this.joinUserRoleService = joinUserRoleService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object userLogin(User user, HttpSession session) {
        return userService.queryByUsername(user, session);
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Object userPage(User user) {
        return userService.queryUserPage(user);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object userList(Integer userId) {
        return userService.queryRoleListByUserId(userId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object userAdd(User user) {
        return userService.userAdd(user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Object userEdit(User user) {
        return userService.userEdit(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object userDelete(Integer id) {
        return userService.userDelete(id);
    }

    @RequestMapping(value = "/batch/delete", method = RequestMethod.POST)
    public Object userBatchDelete(Integer[] userIds) {
        return userService.userBatchDelete(userIds);
    }

    @RequestMapping(value = "/assign/roles", method = RequestMethod.POST)
    public Object userAssignRoles(Integer userId, Integer[] unAssignRoleIds, Integer[] assignRoleIds) {
        return joinUserRoleService.assignRoles(userId, unAssignRoleIds, assignRoleIds);
    }

}
