package com.holun.controller;

import com.holun.entity.User;
import com.holun.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list() {
        return userMapper.list();
    }

}
