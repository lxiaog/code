package org.example.demo;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.repository.dao.*;
import org.example.demo.repository.entity.*;
import org.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:/spring/spring-*xml",
        "classpath:/spring/springmvc-context.xml"
})
@Slf4j
public class MapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private JoinUserRoleMapper joinUserRoleMapper;


    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private JoinPermissionRoleMapper joinPermissionRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void queryAssociationRole() {
        List<JoinUserRole> list = joinUserRoleMapper.queryAssociationRole();
        for (JoinUserRole joinUserRole : list) {
            log.info(JSON.toJSONString(joinUserRole));
        }
    }

    @Test
    public void querySwitchAssociationRole() {
        List<JoinUserRole> list = joinUserRoleMapper.querySwitchAssociationRole();
        for (JoinUserRole joinUserRole : list) {
            log.info("joinUserRole.id=" + joinUserRole.toString());
            log.info("joinUserRole.id=" + joinUserRole.getRole().toString());
//            log.info(JSON.toJSONString(joinUserRole));
        }
    }

    @Test
    public void queryListByRoleId() {
        List<JoinPermissionRole> list = joinPermissionRoleMapper.queryListByRoleId(1);
        log.info("list.size=" + list.size());
        for (JoinPermissionRole joinPermissionRole : list) {
            log.info(joinPermissionRole.getPermissionId());
            log.info(joinPermissionRole.getPermission().getName());

        }
    }

    @Test
    public void queryRoleCollectionPermissions() {
        Role role = roleMapper.queryRoleCollectionPermissions(1);
        log.info(role.getRoleName());
        log.info("getJoinPermissionRoles.size" + role.getJoinPermissionRoles().size());

        for (JoinPermissionRole joinPermissionRole : role.getJoinPermissionRoles()) {
            log.info(joinPermissionRole.getPermissionId());
            log.info(joinPermissionRole.getPermission().getName());
        }
    }

    @Test
    public void firstLavelCache() {

//        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);

        User user = userMapper.selectByPrimaryKey(1);

        log.info("user-11="+user.getId());

        User user2 = userMapper.selectByPrimaryKey(1);

        log.info("user-21="+user2.getId());

//         userService.queryById(1);
    }
    @Test
    public void pageHelper(){
        Permission permission = new Permission();
        permission.setPageNum(1);
        permission.setPageSize(10);
        PageHelper.startPage(permission.getPageNum(),permission.getPageSize(),true);
        List<Permission> permissionList = permissionMapper.queryList(permission);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissionList);
        log.info(JSON.toJSONString(pageInfo));
    }

}
