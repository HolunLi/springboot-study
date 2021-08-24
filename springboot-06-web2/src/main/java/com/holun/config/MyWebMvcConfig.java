package com.holun.config;

import com.holun.interceptor.AdminInterceptor;
import com.holun.interceptor.ForeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 扩展SpringMVC
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    /**
     *添加视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ForeInterceptor()).addPathPatterns("/fore**").excludePathPatterns("/foreLogin", "/foreSignOut");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin**");
    }

    /**
     *将自定义的LocaleResolver（区域解析器）注入到容器中
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
