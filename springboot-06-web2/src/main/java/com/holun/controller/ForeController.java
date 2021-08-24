package com.holun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ForeController {

    @PostMapping("/foreLogin")
    public String login(String uname, String upwd, Model model, HttpSession session) {
        if (uname.length() != 0 && "123".equals(upwd)) {
            session.setAttribute("uname", uname);
            return "redirect:foreToDashboardPage";
        }

        String msg = "用户名或密码错误";
        model.addAttribute("msg", msg);
        return "index";
    }

    @RequestMapping("/foreSignOut")
    public String signOut(HttpSession session) {
        session.removeAttribute("uname");

        return "redirect:index.html";
    }
}
