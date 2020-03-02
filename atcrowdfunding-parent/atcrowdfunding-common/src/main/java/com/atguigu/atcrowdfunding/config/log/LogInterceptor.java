package com.atguigu.atcrowdfunding.config.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 日志拦截器，只有在访问接口时才生效
 *
 * @author xiaog.li
 * @date 2020年1月16日15:17:27
 */
@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    //线程ID常量
    private static final String THREAD_ID = "THREAD_ID";

    /**
     * controller方法前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("LogInterceptor=preHandle");
        //使用UUID生成唯一编号
        String threadId = UUID.randomUUID().toString().trim().replaceAll("-", "");
        //判断MDC(log4j中的上下文对象) 中是否有该threadId
        if (StringUtils.isEmpty(MDC.get(THREAD_ID))) {
            //如果没有，添加
            MDC.put(THREAD_ID,threadId);
        }
        //永远返回true
        return true;
    }

    /**
     * preHandle方法返回true之后
     * 在controller方法处理完之后调用
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) throws Exception {
        //controller结束之后删除对应的唯一值
        MDC.remove(THREAD_ID);
    }

    /**
     * preHandle方法返回true之后
     * 在DispatcherServlet进行视图的渲染之后调用
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception e) throws Exception {
    }
}
