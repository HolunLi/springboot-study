package com.holun.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping({"/index", "/" ,"/index.html"})
    public String index(Model model) {
        model.addAttribute("msg", "hello shiro");
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLoginPage() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String uname, String upwd, Model model) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //以当前用户的用户名、密码为参数，生成口令
        UsernamePasswordToken token = new UsernamePasswordToken(uname, upwd);

        try {
            //使用口令登录，进行身份认证
            subject.login(token);
            return "index";
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "不存在该用户");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "密码错误");
            return "login";
        } catch (LockedAccountException lae) {
            model.addAttribute("msg", "账户被锁定");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //开启注销功能
        subject.logout();
        return "redirect:toLogin";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "user/addUser";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "user/updateUser";
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public String unauthorized() {
        return "你没有权限访问此页面!";
    }
}
