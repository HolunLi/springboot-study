package com.holun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(Model model) {
        List<String> students = new ArrayList<>();
        students.add("holun");
        students.add("伦哥");

        model.addAttribute("students", students);
        model.addAttribute("name1", "伦哥无敌");
        model.addAttribute("name2", "<h3>伦哥无敌<h3>");

        return "test";
    }

}
