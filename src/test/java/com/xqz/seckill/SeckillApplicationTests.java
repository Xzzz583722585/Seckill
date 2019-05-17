package com.xqz.seckill;

import com.xqz.seckill.dao.UserDAO;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.domain.UserKey;
import com.xqz.seckill.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillApplicationTests {

	@Autowired
    RedisService redis;
	@Autowired
	UserDAO userDAO;

	@Test
	@Transactional(readOnly = false, rollbackFor = Throwable.class)
	public void contextLoads() {
//	    User u = new User();
//	    u.setId(1);
//	    u.setName("xqz");
//
//	    redis.set(UserKey.getById, "num", 1);
        redis.decr(UserKey.getById, "num", Integer.class);
        System.out.println(redis.get(UserKey.getById, "num", Integer.class));
	}

	@Test
	public void testUser() {
		System.out.println(userDAO.findAll());
	}

}
