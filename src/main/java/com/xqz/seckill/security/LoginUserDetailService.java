package com.xqz.seckill.security;

import com.xqz.seckill.common.prefix.UserPrefix;
import com.xqz.seckill.dao.UserDAO;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.utils.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    RedisService redis;
    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = redis.get(UserPrefix.user, username, User.class);
        if(user != null){
            return new LoginUserDetails(user, true,
                    true, true, true);
        }

        user = userDAO.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        redis.set(UserPrefix.user, username, user, UserPrefix.user.getExpireSec());

        return new LoginUserDetails(user, true,
                true, true, true);
    }

}
