package com.atguigu.atcrowdfunding.conf.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        application.setAttribute("APP_PATH",application.getContextPath());

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
