package com.holun.service.impl;

import com.holun.entity.User;
import com.holun.mapper.UserMapper;
import com.holun.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author holun
 * @since 2021-08-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
