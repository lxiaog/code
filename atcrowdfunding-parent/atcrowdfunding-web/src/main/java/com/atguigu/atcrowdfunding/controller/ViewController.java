package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.service.RoleService;
import com.atguigu.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/web")
public class ViewController {


    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public ViewController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/error")
    public String error() {
        return "error";
    }


    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/reg")
    public String reg() {
        return "reg";
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        //所有数据无效
        session.invalidate();
        return "redirect:login";
    }

    //*************************user
    @RequestMapping(value = "/user/index")
    public String userIndex() {
        return "user/index";
    }

    @RequestMapping(value = "/user/add")
    public String userAdd() {
        return "user/add";
    }

    @RequestMapping(value = "/user/edit/{userId}")
    public String userEdit(@PathVariable(value = "userId") Integer userId, Model model) {
        model.addAttribute("userEdit", userService.queryByUserId(userId));
        return "user/edit";
    }

    @RequestMapping(value = "/user/assign/role/{userId}")
    public String userAssignRole(@PathVariable(value = "userId") Integer userId, Model model) {
        model.addAttribute("user", userService.queryByUserId(userId));
        return "user/assignRole";
    }

    //***************************role
    @RequestMapping(value = "/role/index")
    public String roleIndex() {
        return "role/index";
    }

    @RequestMapping(value = "/role/add")
    public String roleAdd() {
        return "role/add";
    }

    @RequestMapping(value = "/role/edit/{roleId}")
    public String roleEdit(@PathVariable(value = "roleId") Integer roleId, Model model) {
        model.addAttribute("roleEdit", roleService.queryByRoleId(roleId));
        return "role/edit";
    }

    @RequestMapping(value = "/role/assign/permission/{roleId}")
    public String roleAssignPermission(@PathVariable("roleId") Integer roleId, Model model) {
        model.addAttribute("role", roleService.queryByRoleId(roleId));
        return "role/assignPermission";
    }

    //********************permission

    @RequestMapping(value = "/permission/index")
    public String permissionIndex() {
        return "permission/index";
    }


}
