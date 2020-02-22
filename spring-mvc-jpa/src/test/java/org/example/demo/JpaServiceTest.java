package org.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.service.JpaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/spring/spring-data-source.xml",
        "classpath:/spring/spring-jpa.xml",
        "classpath:/spring/spring-mvc.xml",
        "classpath:/spring/spring-mybatis.xml"

})
@WebAppConfiguration
@Slf4j
public class JpaServiceTest {




    @Autowired
    private JpaService jpaService;


    @Test
    public void personQueryUpdate(){
//        int count =  personRepository.updateEmailById(1,"test@query.com");
        int count =  jpaService.update(2,"test@query.com");
        log.error("更新条数"+count);
    }

}
