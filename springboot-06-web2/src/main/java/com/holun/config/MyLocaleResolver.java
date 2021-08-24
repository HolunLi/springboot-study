package com.holun.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 我们在做项目的时候，往往有很多项目需要根据用户的需要来切换不同的语言，在springboot中使用国际化就可以轻松解决。
 * 方法：自定义LocaleResolver（区域解析器）来切换语言
 */
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 处理区域
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取请求中的语言参数
        String language = httpServletRequest.getParameter("language");

        //如何请求链接中没有携带了与国际化相关的参数，就是使用默认的
        Locale locale = Locale.getDefault();

        //如何请求的URL中携带了与国际化相关的参数
        if (language != null) {
            String[] split = language.split("_");
            locale = new Locale(split[0], split[1]);
            return locale;
        }

        return locale;
    }

    /**
     * 设置区域
     */
    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
