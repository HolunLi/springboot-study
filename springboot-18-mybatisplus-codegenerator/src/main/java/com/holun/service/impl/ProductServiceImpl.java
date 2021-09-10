package com.holun.service.impl;

import com.holun.entity.Product;
import com.holun.mapper.ProductMapper;
import com.holun.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
