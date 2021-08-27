package com.holun.controller;

import com.holun.entity.User;
import com.holun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public User getUser(@RequestParam("id")int id) {
        return userService.getById(id);
    }

    @RequestMapping("/addUser")
    public String addUser() {
        User user = new User("小琪", 8);
        boolean save = userService.save(user);
        System.out.println(user.getId());

        if (save)
            return "success";
        return "fail";
    }

    @RequestMapping("/updateUser")
    public String updateUser() {
        User user = new User();
        user.setId(1430512823616475140L);
        user.setName("笨蛋小琪琪");
        boolean update = userService.updateById(user);

        if (update)
            return "success";
        return "fail";
    }

}
