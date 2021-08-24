package com.holun.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * springMVC在接收请求参数时，使用常见的参数注解
 */
@Controller
public class TestController {

    /**
     * @PathVariable 和 @RequestHeader 注解的使用
     * 接收的每个路径变量名和值，都会作为key-value对被添加到map集合pv中
     * @RequestHeader("User-Agent") String userAgent 用于接收请求头中的信息
     * 请求头中包含的所有key-value对都会被添加到map集合head中
     */
    @GetMapping("/car/{id}/owner/{name}")
    @ResponseBody
    public Map<String, Object> test1(@PathVariable("id")int id,
                                     @PathVariable("name")String name,
                                     @PathVariable Map<String ,String> pv,
                                     @RequestHeader("User-Agent") String userAgent,
                                     @RequestHeader Map<String, String> head) {

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("pv", pv);
        map.put("userAgent", userAgent);
        map.put("head", head);

        return map;
    }

    /**
     *@RequestParam 注解的使用
     * 接收的每个请求参数名和值，都会作为key-value对被添加到map集合rp中
     */
    @GetMapping("/user")
    @ResponseBody
    public Map<String, Object> test2(@RequestParam("id")int id, @RequestParam("hobbies")List<String> hobbies, @RequestParam MultiValueMap<String, String> rp) {

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("hobbies", hobbies);
        map.put("rp", rp);

        return map;
    }

    /**
     * @RequestBody 注解的使用
     * 使用 @RequestBody 注解可以获取请求体中的信息
     * 比如：在前台form表单中输入的信息，就会被封装到请求体中
     */
    @PostMapping("/test3")
    @ResponseBody
    public String test3(@RequestBody String content) {
        return content;
    }

    @GetMapping("/toPage")
    public String toPage(HttpServletRequest request) {
        request.setAttribute("msg", "跳转成功");
        request.setAttribute("statusCode", 200);

        return "forward:/success"; //请求转发
    }

    /**
     *@RequestAttribute 注解的使用
     * 使用 @RequestAttribute 获取请求域中的属性
     * 或者直接使用HttpServletRequest request
     */
    @GetMapping("/success")
    @ResponseBody
    public Map<String, Object> success(@RequestAttribute("msg")String msg, @RequestAttribute("statusCode")Integer statusCode) {

        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("statusCode", statusCode);

        return map;
    }

}
