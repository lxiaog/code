package org.example.demo.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", produces = "application/json;charset=utf-8")
    public Object geiList() {
        log.debug("查询list");
        log.info("查询list");
        log.warn("查询list");
        log.error("查询list");
        return userService.add();
    }
}
