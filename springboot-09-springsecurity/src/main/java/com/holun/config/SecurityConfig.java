package com.holun.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 第一步：导入spring-boot-starter-security依赖（启动器）
 * 第二步：创建一个类继承WebSecurityConfigurerAdapter接口
 * 第三步：将@EnableWebSecurity注解，标注在类上。（启用Web安全）
 * 第四步：重写相应的方法
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 认证（链式编程）
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() //在内存里面存储用户的身份认证和授权信息
                //将root用户认证为vip1,vip2,vip3角色（注意：这里必须对用户的密码进行加密，否则会报错IllegalArgumentException）
                .withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2","vip3")
                .and()
                //将holun用户认证为vip1角色
                .withUser("holun").password(new BCryptPasswordEncoder().encode("123")).roles("vip1")
                .and()
                .withUser("lihaolun").password(new BCryptPasswordEncoder().encode("123")).roles("vip3")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder()); //配置BCrypt加密
    }

    /**
     * 授权（链式编程）
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/index","/index.html","/toLoginPage").permitAll() //无需登录,可以被访问的资源路径（首页）
                .antMatchers("/level/1/**").hasRole("vip1") //只有被认证为vip1的用户，才能访问 /level/1/** 资源路径
                .antMatchers("/level/2/**").hasRole("vip2")
                .antMatchers("/level/3/**").hasRole("vip3")
               // .anyRequest().authenticated() //任何请求都必须在认证后，才能授权访问。（这里要忽略对静态资源的请求）
                .and()
                .formLogin() //开启表单登陆认证功能
                .loginPage("/toLoginPage") //用户未登录或没有权限，重定向到自定义的登陆页面
                .loginProcessingUrl("/login") //登录表单form中action的地址，也就是处理认证请求的路径（SpringSecurity提供了一个 /login请求，可以对登陆的用户进行身份认证）
                .usernameParameter("uname") //登录表单form中用户名输入框input的name名，不修改的话默认是username
                .passwordParameter("upwd")  //登录表单form中密码输入框input的name名，不修改的话默认是password
                .defaultSuccessUrl("/index") //登陆成功后，重定向到的页面
                .and()
                .logout() //开启注销功能（SpringSecurity提供了一个 /logout请求 进行注销操作）
                .logoutSuccessUrl("/toLoginPage") //注销成功后，重定向到指定的页面;
                .and()
                .csrf().disable(); //开启注销功能后，记得禁用跨站csrf攻击防御，否则会出现注销失败的情况。

        //没有请求权限的用户,重定向到登录页面（注意：这个登录页面是SpringSecurity提供的，该页面对应的urlPattern是 "/login"）
        //http.formLogin();

        //开启StringSecurity提供的注销功能（SpringSecurity提供了一个注销页面，该页面对应的urlPattern是 "/logout"）
        //http.logout();

        //开启记住我功能
        //http.rememberMe();
    }

    /*@Override
    public void configure(WebSecurity web) {
        //忽略对静态资源的请求
        web.ignoring().antMatchers("/config/**", "/css/**", "/fonts/**", "/img/**", "/js/**");
    }*/

}
