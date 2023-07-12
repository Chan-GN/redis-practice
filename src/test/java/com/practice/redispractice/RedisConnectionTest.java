package com.practice.redispractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisConnectionTest {

    @Autowired
    private RedisConnectionFactory factory;

    @Test
    public void testRedisConnection() {
        assertNotNull(factory.getConnection());
        assertFalse(factory.getConnection().isClosed());
    }

}