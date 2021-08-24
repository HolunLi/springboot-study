package com.holun.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        /**
         * 添加shiro内置的过滤器:
         * anon 无需认证就可以访问
         * authc 必须认证了才能访问
         * user 必须拥有 “记住我” 功能才能用
         * perms 拥有某个资源的权限才能访问
         * role 拥有某个角色的权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //具有add权限的用户，才能访问 /user/add 资源路径
        filterMap.put("/user/add", "perms[add]");  //[ ]里面的add是权限字符串
        filterMap.put("/user/update", "perms[update]");
        //认证成功的用户，才能访问 /user/* 资源路径（注意：语句的顺序不要写错，如果这条语句放在前面，就不会再进行add/update权限判断）
        filterMap.put("/user/*", "authc");

        //设置过滤器链
        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录页面。未登录就会跳转到登录页面（SpringSecurity默认提供给一个登录页面（可以使用默认的，也可以使用自定义的），shiro不提供）
        bean.setLoginUrl("/toLogin");
        //没有权限，就跳转到指定的页面
        bean.setUnauthorizedUrl("/unauthorized");

        return bean;
    }

    /**
    * 创建安全管理对象
     */
    @Bean(name = "securityManager")                                //形参userRealm会先使用Autowired规则从容器寻找bean并装配，找不到则遵循@Qualifier注解。这里不能使用@Resource
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置realm
        defaultWebSecurityManager.setRealm(userRealm);

        return defaultWebSecurityManager;
    }

    /**
     *  创建realm对象，realm需要自定义
     *  拓展：@Bean注解会将类的实例转化成一个bean，注入到ioc容器。
     */
    @Bean(name = "userRealm")  //bean的名字默认是方法名，可以使用@Bean注解中的name属性指定bean的名字
    public UserRealm getUserRealm() {
        return new UserRealm();
    }


    @Bean(name = "shiroDialect") //ShiroDialect是整合shiro和thymeleaf需要的对象
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
