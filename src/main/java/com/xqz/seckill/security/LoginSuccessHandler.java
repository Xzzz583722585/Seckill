package com.xqz.seckill.security;

import com.xqz.seckill.common.prefix.UserPrefix;
import com.xqz.seckill.utils.redis.RedisService;
import com.xqz.seckill.utils.token.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    RedisService redis;

    public static final String COOKIE_NAME_TOKEN = "token";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = UUIDUtil.getUUID();
        LoginUserDetails userDetail = (LoginUserDetails)(authentication.getPrincipal());

        redis.set(UserPrefix.token, token, userDetail.getUser(), UserPrefix.token.getExpireSec());
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(UserPrefix.token.getExpireSec());

        response.addCookie(cookie);
        response.sendRedirect("/htm/goods/goods_list.html");
    }

}
