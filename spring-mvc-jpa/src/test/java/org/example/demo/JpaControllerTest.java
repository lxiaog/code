package org.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.utils.MockMvcUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/spring/spring-data-source.xml",
        "classpath:/spring/spring-jpa.xml",
        "classpath:/spring/spring-mvc.xml",
        "classpath:/spring/spring-mybatis.xml"

})
@WebAppConfiguration
@Slf4j
public class JpaControllerTest {

    @Autowired
    private WebApplicationContext context;

    //模拟mvc环境
    private MockMvc mvc;

    @Before
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void personAdd() {
        try {
            String url = "/person/add";
            String lastName = "dd,ee,ff,gg,hh,ii,jj,kk,ll,nn,oo,pp,xx";
            for (String name : lastName.split(",")) {
                MockMvcUtil.requestKeyAndValue(url + "/" + name, mvc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void personList() {
        try {
            String url = "/person/list";
            MockMvcUtil.requestKeyAndValue(url, mvc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void personTest() {
        try {
            String url = "/person/test";
            MockMvcUtil.requestKeyAndValue(url, mvc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
