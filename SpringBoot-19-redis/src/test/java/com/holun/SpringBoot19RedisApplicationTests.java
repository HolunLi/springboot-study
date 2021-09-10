package com.holun;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.holun.pojo.User;
import com.holun.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringBoot19RedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; //redisTemplate对象用来操作redis中的数据类型，api和redis中命令一样
    @Autowired
    private RedisUtil redisUtil;

    @Test
    void test1() throws JsonProcessingException {
        User user = new User("holun", "123");
        //springboot内置jackson，将user对象转换为json字符串
        //String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    void test2() {
        redisUtil.set("name","holun");
        System.out.println(redisUtil.get("name"));
    }

    @Test
    void contextLoads() {
        //操作string
        redisTemplate.opsForValue().set("name", "holun");
        System.out.println(redisTemplate.opsForValue().get("name"));
        //操作list
        redisTemplate.opsForList();
        //操作set
        redisTemplate.opsForSet();
        //操作sorted set
        redisTemplate.opsForZSet();
        //操作hash
        redisTemplate.opsForHash();

        //获取redis的连接对象
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();
        connection.flushAll();
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

}
