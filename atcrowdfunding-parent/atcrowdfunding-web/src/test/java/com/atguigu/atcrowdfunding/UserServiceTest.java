package com.atguigu.atcrowdfunding;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//        "classpath:/spring/spring-data-source.xml",
//        "classpath:/spring/spring-jpa.xml",
//        "classpath:/spring/springmvc-context.xml",
//        "classpath:/spring/spring-mybatis.xml"
//
//})
//@WebAppConfiguration
@Slf4j
public class UserServiceTest {


    @Test
    public void createUuid() {
        for (int i = 0; i < 50; i++) {
            log.info(UUID.randomUUID().toString().replaceAll("-", "") + "\n");
        }
    }

}
