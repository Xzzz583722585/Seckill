package com.xqz.seckill.filter;

import com.xqz.seckill.domain.User;
import com.xqz.seckill.security.LoginSuccessHandler;
import com.xqz.seckill.utils.prefix.UserPrefix;
import com.xqz.seckill.utils.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(0)
@WebFilter(filterName = "tokenFilter", urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Autowired
    RedisService redis;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        for(Cookie cookie: request.getCookies()){
            if(LoginSuccessHandler.COOKIE_NAME_TOKEN.equals(cookie.getName())){
                User user = redis.get(UserPrefix.token, cookie.getValue(), User.class);
                if(user != null){
                    redis.set(UserPrefix.token, cookie.getValue(), user, UserPrefix.token.getExpireSec());
                    cookie.setMaxAge(UserPrefix.token.getExpireSec());
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
