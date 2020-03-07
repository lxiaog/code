package org.example.demo;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.repository.entity.JoinUserRole;
import org.example.demo.repository.entity.User;
import org.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/spring/spring-*.xml",
        "classpath:/spring/springmvc-context.xml"
})
@WebAppConfiguration
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void userAdd() {
        User user = new User();
        user.setUsername("a1234");
        user.setName("A1234");
        user.setPassword("admin=admin");
        user.setEmail("a1234@163.com");
        Object object = userService.add(user);
        log.info("" + object);
    }


    @Test
    public void returnTypeMap() {
        Map map = userService.queryMapById(1);
        log.info(JSON.toJSONString(map));
    }

    @Test
    public void returnTypeMapKey() {
        Map map = userService.queryMaps();
        log.info(JSON.toJSONString(map));
    }

    @Test
    public void queryAssociationRole() {
        List<JoinUserRole> list = userService.queryAssociationRole();
        for (JoinUserRole joinUserRole : list) {
            log.info(JSON.toJSONString(joinUserRole));
        }
    }

    @Test
    public void querySwitchAssociationRole() {
        List<JoinUserRole> list = userService.querySwitchAssociationRole();
        for (JoinUserRole joinUserRole : list) {
            log.info("joinUserRole.id=" + joinUserRole.getId());
            log.info("joinUserRole.id=" + joinUserRole.getRole().getId());
//            log.info(JSON.toJSONString(joinUserRole));
        }
    }

    @Test
    public void firstLavelCache() {


        User user = userService.queryById(1);
    }



}
