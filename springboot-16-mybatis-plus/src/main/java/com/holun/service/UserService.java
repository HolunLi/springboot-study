package com.holun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.holun.entity.User;

/**
 * IService<User>是Mybatis-Plus提供的顶级Service接口，提供了一套CRUD操作。我们自己编写的Service接口继承他即可
 */
public interface UserService extends IService<User> {
}
