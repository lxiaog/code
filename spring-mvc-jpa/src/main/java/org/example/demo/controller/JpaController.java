package org.example.demo.controller;

import org.example.demo.common.rest.RestResult;
import org.example.demo.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaController {

    @Autowired
    private JpaService jpaService;

    @RequestMapping(value = "/person/add/{lastName}", produces = "application/json;charset=utf-8")
    public String add(@PathVariable("lastName") String lastNme) {
        return RestResult.success().setData(jpaService.add(lastNme)).toJson();
    }

    @RequestMapping(value = "/person/list", produces = "application/json;charset=utf-8")
    public Object getList() {
        return RestResult.success().setData(jpaService.geiList()).toJson();
    }

    @RequestMapping(value = "/person/test", produces = "application/json;charset=utf-8")
    public Object test() {
        jpaService.test();
        return RestResult.success().setMag("测试成功").toJson();
    }


}
