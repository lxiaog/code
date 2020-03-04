package org.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.repository.entity.User;
import org.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
}
