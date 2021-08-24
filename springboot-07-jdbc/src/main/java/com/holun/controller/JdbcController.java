package com.holun.controller;

import com.holun.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HelloService helloService;

    @RequestMapping("/add")
    public String add() {
        String sql = "insert into user values(?,?,?)";
        int count = jdbcTemplate.update(sql, 10, "lihaolun", "123");
        if (count > 0)
            return "增加成功";
        return "增加失败";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        String sql = "delete from user where id=" + id;
        int count = jdbcTemplate.update(sql);
        if (count > 0)
            return "删除成功";
        return "删除失败";
    }

    @RequestMapping("/update")
    public String update() {
        String sql = "update user set name=?,pwd=? where id=?";
        Object[] objects = new Object[3];
        objects[0] = "李豪伦";
        objects[1] = "haolun123";
        objects[2] = 9;
        int count = jdbcTemplate.update(sql, objects);
        if (count > 0)
            return "更新成功";
        return "更新失败";
    }

    /**
     *没有实体类，从数据库表中的查询出来的数据怎么获取，使用map来获取
     */
    @RequestMapping("/userList")
    public List<Map<String, Object>> userList () {
        String sql = "select * from user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        return list;
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return helloService.sayHello("李豪伦");
    }

}
