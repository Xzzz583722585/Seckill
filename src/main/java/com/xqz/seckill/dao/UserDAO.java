package com.xqz.seckill.dao;

import com.xqz.seckill.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer>{
}
