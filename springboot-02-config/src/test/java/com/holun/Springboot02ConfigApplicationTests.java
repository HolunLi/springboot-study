package com.holun;

import com.holun.pojo.Dog;
import com.holun.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot02ConfigApplicationTests {
    @Autowired
    private Person person;
    @Autowired
    private Dog dog;

    @Test
    void contextLoads() {
        System.out.println(dog);
        System.out.println(person);

    }

}
