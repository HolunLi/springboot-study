package com.holun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.holun.entity.User;
import com.holun.mapper.UserMapper;
import com.holun.service.UserService;
import org.springframework.stereotype.Service;

/**
 * ServiceImpl是IService（顶级Service接口）的实现类，由Mybatis-Plus提供。我们编写的Service接口的实现类继承它即可
 * 继承ServiceImpl需要传入两个泛型，第一个是使用哪个mapper，第二个是操作哪个表
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
