package com.atguigu.atcrowdfunding.conf.interceptor;

import com.atguigu.atcrowdfunding.dao.PermissionMapper;
import com.atguigu.atcrowdfunding.entity.Permission;
import com.atguigu.atcrowdfunding.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 授权拦截器
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session
        HttpSession session = request.getSession();
        //获取url
        String url = request.getRequestURI();
        log.info(url);
        //获取servlet路径
        String path = session.getServletContext().getContextPath();
        log.info(path);
        //截取path后边的url
        url = url.replaceAll(path, "");
        log.info(url);
        //获取所有权限
        Set<String> permissionUrlSet = permissionMapper.queryUrlList();
        //判断url是否在权限表中
        if (permissionUrlSet.contains(url)) {
            //验证登录用户的权限
            User user = (User) session.getAttribute("loginUser");
            List<Permission> permissionList = user.getPermissions();
            if (permissionList == null || permissionList.size() <= 0) {
                response.sendRedirect(path + "/web/error");
                return false;
            }
            if (isUrlAuth(permissionList, url)){
                return true;
            }else {
                response.sendRedirect(path + "/web/error");
                return false;
            }
        }
        return true;
    }

    private boolean isUrlAuth(List<Permission> list, String url) {
        for (Permission permission : list) {
            log.info(url + "=" + permission.getUrl());
            if (Objects.equals(url, permission.getUrl())) {
                return true;
            }
            if (permission.getChildren()!=null&&permission.getChildren().size()>0){
                return isUrlAuth(permission.getChildren(),url);
            }
        }
        return false;
    }

}
