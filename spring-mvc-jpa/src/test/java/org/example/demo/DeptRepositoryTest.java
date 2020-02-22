package org.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.jpa.entity.Dept;
import org.example.demo.jpa.entity.Manager;
import org.example.demo.jpa.repository.DeptRepository;
import org.example.demo.jpa.repository.ManagerRepository;
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
public class DeptRepositoryTest {

    @Autowired
    private DeptRepository deptRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Test
    public void deptAdd() {
        Dept dept = new Dept();
        dept.setDeptName("IT部门");

        Manager manager = new Manager();
        manager.setManagerName("总监");

        dept.setManager(manager);
        //先保存不维护关联关系的一方
        managerRepository.save(manager);
        //在保存另一方
        deptRepository.save(dept);
    }

    @Test
    public void oneToOneFind() {
//        Dept dept = deptRepository.getOne(1);
//        log.error(dept.getDeptName());

        Manager manager = managerRepository.getOne(1);
        log.error(manager.getManagerName());

    }
}
