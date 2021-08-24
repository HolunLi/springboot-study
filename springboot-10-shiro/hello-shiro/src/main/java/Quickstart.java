/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);


    public static void main(String[] args) {
      /*
        //已过期
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        */

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //获取类路径下的资源文件shiro.ini
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        defaultSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //通过当前用户获取session
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("Retrieved the correct value! [" + value + "]");
        }

        //判断当前用户是否已认证，如果当前用户没有被认证，说明当前用户就没有登陆过
        if (!currentUser.isAuthenticated()) {
            //根据用户名和密码，生成一个口令
            UsernamePasswordToken token = new UsernamePasswordToken("ajie", "12345");
            //请记住我，也就是shiro操作cookie实现自动登录，这可以先不了解.
            token.setRememberMe(true);
            try {
                //当前用户，使用口令登录
                currentUser.login(token);
            } catch (UnknownAccountException uae) { //账户未知异常
                log.info("用户" + token.getPrincipal() + "不存在");
            } catch (IncorrectCredentialsException ice) { //密码错误异常
                log.info("用户" + token.getPrincipal() + "的密码不正确");
            } catch (LockedAccountException lae) { //账户锁定异常
                log.info("用户" + token.getPrincipal() + "被锁定，请求联系你的管理员，将其解锁");
            }
            catch (AuthenticationException ae) { //在这里捕获更多的异常
            }
        }
        log.info("用户" + currentUser.getPrincipal() + "登录成功");

        //如果当前用户是xiaoyan角色
        if (currentUser.hasRole("xiaoyan")) {
            log.info("你是萧炎");
        } else {
            log.info("你只是一个凡人");
        }

        //被认证为xiaoyan角色的用户，具有释放青莲地心火的权限
        if (currentUser.isPermitted("fireflower:release")) { //fireflower是"青莲地心火"的意思，release是"释放"的意思
            log.info("你可以释放青莲地心火，毁灭一切!");
        } else {
            log.info("你没有资格使用青莲地心火!");
        }

        if (currentUser.isPermitted(" J-20:drive:eagle5")) {
            log.info("你可以驾驶歼20战机，eagle5是你的代号（驾驶通行证）。");
        } else {
            log.info("你没有资格驾驶歼20战机。");
        }

        //注销当前用户
        currentUser.logout();
        //程序执行结束
        System.exit(0);
    }
}
