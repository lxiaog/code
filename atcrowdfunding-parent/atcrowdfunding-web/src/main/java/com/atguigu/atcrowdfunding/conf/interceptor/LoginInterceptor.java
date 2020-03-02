package com.atguigu.atcrowdfunding.conf.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在控制器执行完成前，完成业务逻辑
     *
     * @return true 表示继续执行，false表示不在继续执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor=preHandle");
        //判断当前用户是否登录
        //获取session
        HttpSession session = request.getSession();
        Object object = session.getAttribute("loginUser");
        if (Objects.isNull(object)) {
            String path = session.getServletContext().getContextPath();
            response.sendRedirect(path + "/web/login");
            return false;
        }
        return true;
    }

    /**
     * 控制器执行完成之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在完成视图渲染之后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
