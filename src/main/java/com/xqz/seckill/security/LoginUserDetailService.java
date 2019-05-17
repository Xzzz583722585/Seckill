package com.xqz.seckill.security;

import com.xqz.seckill.dao.UserDAO;
import com.xqz.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        return new LoginUserDetail(user, true,
                true, true, true);
    }

}
