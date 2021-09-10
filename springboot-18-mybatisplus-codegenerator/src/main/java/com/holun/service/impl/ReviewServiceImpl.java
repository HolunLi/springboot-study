package com.holun.service.impl;

import com.holun.entity.Review;
import com.holun.mapper.ReviewMapper;
import com.holun.service.ReviewService;
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
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

}
