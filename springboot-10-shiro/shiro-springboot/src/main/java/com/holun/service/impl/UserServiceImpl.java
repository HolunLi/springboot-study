package com.holun.service.impl;

import com.holun.entity.User;
import com.holun.mapper.UserMapper;
import com.holun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return null;
    }

}
