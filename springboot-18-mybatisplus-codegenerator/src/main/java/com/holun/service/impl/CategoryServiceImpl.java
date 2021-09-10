package com.holun.service.impl;

import com.holun.entity.Category;
import com.holun.mapper.CategoryMapper;
import com.holun.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
