package com.holun.controller;

import com.holun.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口文档中的接口指的是？
 * 简单理解，这里的接口指的是控制器类中的方法上的url-pattern
 */

@Api(description = "hello控制器") //该注解标注在控制器类上，可以为控制类添加描述信息
@RestController
public class HelloController {

    @ApiOperation("")
    @GetMapping("/hello")
    public String hello() {
        return "hello swagger";
    }

    @GetMapping("/dog")  //在SwaggerConfiguration中配置了，控制器方法上没有使用@ApiOperation注解，该接口就不会被扫描到swagger中
    public String dog() {
        return "dog";
    }

    /**
     * 接口的返回值如果是一个实体类对象，会将该对象对应的实体类，及类中的属性扫描到swagger中
     */
    @ApiOperation("获取用户")
    @GetMapping("/getUser")
    public User getUser() {
        return new User();
    }

    @ApiOperation("打印密码")
    @GetMapping("/printPwd")
    public String printPwd(@ApiParam("密码") String pwd) { //@ApiParam注解标注在控制器方法的形参上，可以为形参添加描述信息
        return "password is " + pwd;
    }

    @ApiOperation("打印用户")
    @PostMapping("/printUser")
    public User printPwd(User user) { //@ApiParam注解标注在控制器方法的形参上，可以为形参添加描述信息
        return user;
    }

}
