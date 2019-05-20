package com.xqz.seckill.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@Order(0)
//@WebFilter(filterName = "loginValdateFilter", urlPatterns = "/*")
public class Demo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("前");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("后");
    }

    @Override
    public void destroy() {
        System.out.println("filter destory");
    }
}
