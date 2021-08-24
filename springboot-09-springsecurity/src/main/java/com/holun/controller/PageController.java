package com.holun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping({"/", "/index", "/index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping("/toLoginPage")
    public String toLoginPage() {
        return "views/login";
    }

    @RequestMapping("/level/{id1}/{id2}")
    public String level(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
        return "views/level"+id1+"/"+id2;
    }

}
