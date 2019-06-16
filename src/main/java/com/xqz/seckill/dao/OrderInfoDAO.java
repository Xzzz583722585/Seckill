package com.xqz.seckill.dao;

import com.xqz.seckill.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoDAO extends JpaRepository<OrderInfo, Integer>{
    OrderInfo findById(Long id);
}
