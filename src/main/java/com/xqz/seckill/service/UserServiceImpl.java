package com.xqz.seckill.service;

import com.xqz.seckill.dao.UserDAO;
import com.xqz.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public Boolean register(User user) {
        User result = null;
        try {
            result = userDAO.save(user);
        } catch (Exception e){
            return false;
        }finally {
            return result != null;
        }
    }
}
