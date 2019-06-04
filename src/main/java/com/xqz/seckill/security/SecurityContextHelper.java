package com.xqz.seckill.security;

import com.xqz.seckill.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextHelper {
    private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public User getUser(){
        return ((LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
