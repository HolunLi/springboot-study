package com.holun;

import com.holun.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CunsumerServerApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        userService.buyTicket();
    }

}
