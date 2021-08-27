package com.holun;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.holun.entity.User;
import com.holun.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Springboot16MybatisPlusApplicationTests {
    @Autowired
    private UserService userService;

    /**
     * 测试mybatis分页
     */
    @Test
    void contextLoads() {
        //查询第一页的数据，每页显示3条
        Page<User> page = new Page<>(2, 3);
        userService.page(page);
        List<User> users = page.getRecords(); //page.getRecords()获取分页查询出来的集合
        users.forEach(System.out :: println);
        System.out.println(page.getPages()); //获取总页数
        System.out.println(page.getCurrent()); //获取当前页
        System.out.println(page.getTotal()); //获取总共有多少条数据
        System.out.println(page.hasPrevious()); //是否有上一页
        System.out.println(page.hasNext()); //是否有下一页
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        wrapper.orderByDesc("age");
        List<User> list = userService.list(wrapper);
        list.forEach(System.out :: println);

    }

}
