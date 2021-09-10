package com.holun.service.impl;

import com.holun.entity.Order;
import com.holun.mapper.OrderMapper;
import com.holun.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
